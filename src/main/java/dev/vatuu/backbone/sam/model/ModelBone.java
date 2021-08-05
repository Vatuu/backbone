package dev.vatuu.backbone.sam.model;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import dev.vatuu.backbone.entity.meta.ArmorStandEntityMeta;
import dev.vatuu.backbone.entity.meta.base.EntityMeta;
import dev.vatuu.backbone.item.meta.ItemMeta;
import dev.vatuu.backbone.utils.math.Matrix4;
import dev.vatuu.backbone.utils.math.Quaternion;
import dev.vatuu.backbone.utils.math.Vector3;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityEquipmentUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;
import java.util.List;

public class ModelBone {

    private static final Vector3 STAND_PIVOT = new Vector3(8, 0, 8);

    private final boolean isRoot;
    private final Item modelMaterial;
    private final int customModelValue;
    private final Vector3 pivot;
    private final List<ModelBone> children = new ArrayList<>();
    private final World world;

    private ArmorStandEntity modelArmorStand, queuedStand;
    private Vec3d location;
    private float pitch, yaw, roll;

    private final LinkedList<List<Commands>> commandQueue = new LinkedList<>();
    private boolean creationQueued;

    public ModelBone(Item model, int value, Vector3 pivot, Vec3d loc, World w, boolean isRoot) {
        this.isRoot = isRoot;
        this.modelMaterial = model;
        this.customModelValue = value;
        this.pivot = pivot;
        this.location = loc;
        this.world = w;
        
        this.pitch = this.yaw = this.roll = 0;
    }

    public ModelBone(Item model, int value, Vector3 pivot, Vec3d loc, World w) {
        this(model, value, pivot, loc, w, false);
    }

    public ModelBone(Item model, int value, Vec3d loc, World w) {
        this(model, value, STAND_PIVOT, loc, w, false);
    }

    public ModelBone(Vec3d loc, World w) {
        this(Items.AIR, 0, STAND_PIVOT, loc, w, true);
    }

    public void addChild(ModelBone... parts) {
        children.addAll(Arrays.asList(parts));
    }

    public void render(ServerPlayerEntity p) {
        if(!commandQueue.isEmpty()) {
            commandQueue.getFirst().forEach(c -> {
                switch (c) {
                    case CREATE: this.commandCreate(p); break;
                    case SHOW: this.commandShow(p); break;
                    case DELETE: this.commandDelete(p); break;
                }
            });
            commandQueue.removeFirst();
        }
        this.children.forEach(mp -> mp.render(p));
    }

    public void destroy(ServerPlayerEntity p) {
        if(this.modelArmorStand != null && !this.isRoot) {
            EntitiesDestroyS2CPacket packet = new EntitiesDestroyS2CPacket(this.modelArmorStand.getEntityId());
            p.networkHandler.connection.send(packet);
            this.modelArmorStand = this.queuedStand = null;
        }

        this.children.forEach(b -> b.destroy(p));
    }

    public void show(boolean followChildren) {
        update();

        if(followChildren)
            this.children.forEach(p -> p.show(true));
    }

    public void show() {
        this.show(true);
    }

    public void hide(boolean followChildren) {
        commandQueue.add(Collections.singletonList(Commands.DELETE));
        this.creationQueued = false;
        this.queuedStand = null;

        if(followChildren)
            this.children.forEach(p -> p.hide(true));
    }

    public void hide() {
        this.hide(true);
    }

    public void translate(Vec3d vec, boolean followChildren) {
        moveTo(this.location.add(vec), followChildren);

        if(followChildren)
            this.children.forEach(p -> p.translate(vec, true));
    }

    public void translate(Vec3d vec) {
        this.translate(vec, true);
    }

    public void rotate(float pitch, float yaw, float roll, boolean followChildren) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        update();

        if(followChildren)
            this.children.forEach(p -> p.rotate(pitch, yaw, roll, true));
    }

    public void rotate(float pitch, float yaw, float roll) {
        rotate(pitch, yaw, roll, true);
    }

    public void moveTo(Vec3d loc, boolean followChildren) {
        this.location = loc;
        update();

        if(followChildren)
            this.children.forEach(p -> p.moveTo(loc, true));
    }

    public void moveTo(Vec3d loc) {
        this.moveTo(loc, true);
    }

    public Vec3d getPos() {
        return location.add(0, 1, 0);
    }

    private void update() {
        if(!creationQueued) {
            commandQueue.add(Collections.singletonList(Commands.CREATE));
            commandQueue.add(Lists.newArrayList(Commands.DELETE, Commands.SHOW));
            this.creationQueued = true;
        }
    }

    private Vector3 determineRotationTranslate(float pitch, float yaw, float roll) {
        Vector3 pivot = new Vector3(16 - this.pivot.getX(), this.pivot.getY() - 1, 16 - this.pivot.getZ());
        Vector3 transform = STAND_PIVOT.subtract(pivot);

        transform = new Matrix4(new Quaternion(Vector3f.NEGATIVE_Z, roll)).transform(transform);
        transform = new Matrix4(new Quaternion(Vector3f.NEGATIVE_Y, yaw)).transform(transform);
        transform = new Matrix4(new Quaternion(Vector3f.POSITIVE_X, pitch)).transform(transform);

        transform = transform.add(pivot).subtract(STAND_PIVOT).divide(16F);

        return transform;
    }

    private void commandCreate(ServerPlayerEntity p) {
        if(this.isRoot)
            return;

        Vec3d renderPos = this.location;
        Vector3 transform = determineRotationTranslate(pitch, yaw, roll);
        renderPos = renderPos.add(transform.getX(), transform.getY(), transform.getZ());

        this.queuedStand = new ArmorStandEntity(world, renderPos.getX(), renderPos.getY(), renderPos.getZ());
        EntityMeta.<ArmorStandEntityMeta>getMeta(queuedStand)
                .setInvisible(true)
                .setNoGravity(true)
                .setBaseHidden(true)
                .setSilent(true)
                .setLockedSlots(ArmorStandEntityMeta.DisabledSlots.DISABLE_ALL)
                .setHeadRotation(pitch, yaw, roll)
                .setRotation(0, 0);

        Packet<?> spawn = queuedStand.createSpawnPacket();
        EntityTrackerUpdateS2CPacket data = new EntityTrackerUpdateS2CPacket(queuedStand.getEntityId(), queuedStand.getDataTracker(), false);
        p.networkHandler.connection.send(spawn);
        p.networkHandler.connection.send(data);
        this.creationQueued = false;
    }
    private void commandDelete(ServerPlayerEntity p) {
        if(this.modelArmorStand == null || this.isRoot)
            return;

        EntitiesDestroyS2CPacket packet = new EntitiesDestroyS2CPacket(this.modelArmorStand.getEntityId());
        p.networkHandler.connection.send(packet);
        this.modelArmorStand.remove();
        this.modelArmorStand = null;
    }
    private void commandShow(ServerPlayerEntity p) {
        if(this.isRoot || this.queuedStand == null)
            return;

        this.modelArmorStand = this.queuedStand;
        this.queuedStand = null;
        EntityEquipmentUpdateS2CPacket equip = new EntityEquipmentUpdateS2CPacket(
                this.modelArmorStand.getEntityId(),
                Collections.singletonList(new Pair<>(EquipmentSlot.HEAD, ItemMeta.of(this.modelMaterial)
                        .setCustomModelValue(this.customModelValue)
                        .apply())));
        p.networkHandler.connection.send(equip);
    }

    private enum Commands {
        DELETE,
        CREATE,
        SHOW
    }
}

package dev.vatuu.backbone.sam;

import dev.vatuu.backbone.sam.model.ModelBone;
import dev.vatuu.backbone.sam.model.ServerAnimatableModel;
import dev.vatuu.backbone.utils.math.Vector3;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Player1pxHeadModel extends ServerAnimatableModel {

    public final ModelBone root;

    public final ModelBone head;
    public final ModelBone eyeLeft, eyeRight;
    public final ModelBone eyebrowLeft, eyebrowRight;
    public final ModelBone eyelidLeftUpper, eyelidLeftLower, eyelidRightUpper, eyelidRightLower;

    public Player1pxHeadModel(World w, Vec3d loc, int partCount, int offset, int starting, double renderDistance) {
        super(w, renderDistance);

        this.root = new ModelBone(loc, w);

        this.head = new ModelBone(Items.NAME_TAG, 1 + (partCount * offset) + starting, loc, w);
        this.root.addChild(head);
        this.eyeLeft = new ModelBone(Items.NAME_TAG, 2 + (partCount * offset) + starting, new Vector3(6.5F, 3.5F, 4.2F), loc, w);
        this.eyeRight = new ModelBone(Items.NAME_TAG, 3 + (partCount * offset) + starting, new Vector3(9.5F, 3.5F, 4.2F), loc, w);
        this.root.addChild(eyeLeft, eyeRight);
        this.eyelidLeftUpper = new ModelBone(Items.NAME_TAG, 4 + (partCount * offset) + starting, new Vector3(6, 4, 4.05F), loc, w);
        this.eyelidLeftLower = new ModelBone(Items.NAME_TAG, 5 + (partCount * offset) + starting, new Vector3(6, 3, 4.05F), loc, w);
        this.eyelidRightUpper = new ModelBone(Items.NAME_TAG, 6 + (partCount * offset) + starting, new Vector3(10, 4, 4.05F), loc, w);
        this.eyelidRightLower = new ModelBone(Items.NAME_TAG, 7 + (partCount * offset) + starting, new Vector3(10, 3, 4.05F), loc, w);
        this.root.addChild(eyelidLeftUpper, eyelidLeftLower, eyelidRightUpper, eyelidRightLower);
        this.eyebrowLeft = new ModelBone(Items.NAME_TAG, 8 + (partCount * offset) + starting, new Vector3(6,4,4), loc, w);
        this.eyebrowRight = new ModelBone(Items.NAME_TAG, 9 + (partCount * offset) + starting, new Vector3(10, 4, 4), loc, w);
        this.root.addChild(eyebrowLeft, eyebrowRight);
    }

    @Override
    public ModelBone getRootBone() {
        return root;
    }
}

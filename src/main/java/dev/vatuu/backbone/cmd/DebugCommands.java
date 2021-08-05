package dev.vatuu.backbone.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.vatuu.backbone.Backbone;
import dev.vatuu.backbone.entity.meta.EndCrystalEntityMeta;
import dev.vatuu.backbone.entity.meta.base.EntityMeta;
import dev.vatuu.backbone.events.ServerLifecycleEvents;
import dev.vatuu.backbone.events.ServerTickEvents;
import dev.vatuu.backbone.gambit.hud.GambitHUD;
import dev.vatuu.backbone.sam.Player1pxHeadModel;
import dev.vatuu.backbone.sam.SamManager;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.server.ServerNetworkIo;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class DebugCommands {

    private static final Identifier MODEL = Backbone.id("model");
    private static ServerTickEvents.StartTick listener;
    private static float rotation = 0;
    public static GambitHUD hud;

    public static void registerTestCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        ServerLifecycleEvents.SERVER_STOPPING.register(s -> {
            if(hud != null)
                hud.destroy();
        });
        dispatcher.register(
                literal("sam")
                        .requires(src -> src.hasPermissionLevel(4))
                        .then(literal("summon")
                                .executes(DebugCommands::samSummon))
                        .then(literal("start")
                                .then(argument("axis", StringArgumentType.word())
                                        .executes(ctx -> samStart(ctx, StringArgumentType.getString(ctx, "axis"))))));

        dispatcher.register(
                literal("gambithud")
                        .requires(src -> src.hasPermissionLevel(4))
                        .then(literal("hide")
                                .executes(ctx -> hideHud()))
                        .then(literal("show")
                                .executes(ctx -> showHud(ctx.getSource().getWorld())))
                        .then(literal("random")
                                .executes(ctx -> updateHudRandom(ctx.getSource().getWorld()))));

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            if(hud != null)
                hud.destroy();
        });
    }

    private static int samSummon(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        Vec3d pos = ctx.getSource().getPlayer().getPos();
        World w = ctx.getSource().getWorld();
        SamManager sam = SamManager.INSTANCE;

        if(sam.modelExists(MODEL))
            sam.unregister(MODEL);
        sam.register(MODEL, new Player1pxHeadModel(w, pos, 9, 0, 0, 16 * 8));
        Player1pxHeadModel model = (Player1pxHeadModel)sam.getModel(MODEL);
        model.eyelidRightLower.translate(new Vec3d(0, -(.5F / 16), 0));
        model.eyelidLeftLower.translate(new Vec3d(0, -(.5F / 16), 0));
        model.eyelidRightUpper.translate(new Vec3d(0, .5F / 16, 0));
        model.eyelidLeftUpper.translate(new Vec3d(0, .5F / 16, 0));

        sam.addPlayer(MODEL, ctx.getSource().getPlayer());
        model.spawn();

        return 1;
    }

    private static int samStart(CommandContext<ServerCommandSource> ctx, String word) {
        SamManager sam = SamManager.INSTANCE;
        if(!sam.modelExists(MODEL)) {
            ctx.getSource().sendError(new LiteralText("Sam has not been spawned."));
            return 1;
        }

        Player1pxHeadModel model = (Player1pxHeadModel)sam.getModel(MODEL);

        if(listener != null) {
            ServerTickEvents.START_SERVER_TICK.unregister(listener);
            rotation = 0;
            model.eyebrowLeft.rotate(0, 0, 0);
            model.eyebrowRight.rotate(0, 0, 0);
        }

        listener = ServerTickEvents.START_SERVER_TICK.register(s -> {
            rotation += 1;
            if(word.startsWith("x")) {
                model.eyebrowLeft.rotate(90, 0, 90);
                model.eyebrowRight.rotate(-90, 0, -90);
            } else if(word.startsWith("y")) {
                model.eyebrowLeft.rotate(0, rotation, 0);
                model.eyebrowRight.rotate(0, -rotation, 0);
            } else if(word.startsWith("z")) {
                model.eyebrowLeft.rotate(0, 0, rotation);
                model.eyebrowRight.rotate(0, 0, -rotation);
            } else {
                model.eyebrowLeft.rotate(0, 0, 0);
                model.eyebrowRight.rotate(0, 0, 0);
            }

            if(rotation >= 360)
                rotation = 0;
        });
        return 1;
    }

    private static int showHud(World w) {
        if(hud == null)
            hud = new GambitHUD(w, false);
        hud.update();
        return 1;
    }

    private static int hideHud() {
        if(hud == null)
            return 1;
        hud.destroy();
        return 1;
    }

    private static int updateHudRandom(World w) {
        if(hud == null)
            hud = new GambitHUD(w, false);

        Random r = new Random();
        int score1 = r.nextInt(100);
        int score2 = r.nextInt(100);

        hud.setLeftScore(score1)
                .setRightScore(score2)
                .setLeftBlockers(hud.getRandomBlockers())
                .setRightBlockers(hud.getRandomBlockers())
                .update();

        return 1;
    }
}

package dev.vatuu.backbone.gambit.hud;

import com.google.common.collect.Lists;
import dev.vatuu.backbone.gambit.Blockers;
import dev.vatuu.backbone.gambit.Gambit;
import dev.vatuu.backbone.hud.BossbarHud;
import dev.vatuu.backbone.hud.UnicodeHUD;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GambitHUD {

    private static final Identifier BASE = Gambit.id("hud_base");
    private static final Identifier SCORE = Gambit.id("hud_score");
    private static final Identifier PROGRESS = Gambit.id("hud_progress");
    private static final Identifier OVERLAY = Gambit.id("hud_overlay");
    private static final Identifier BLOCKERS = Gambit.id("hud_blockers");

    private static final String SCORE_SPACE = "\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821";
    private static final String PROGRESS_SPACE = "\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821";
    private static final String BLOCKER_SPACE = "\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821\uF821";

    private final World w;

    private int scoreLeft, scoreRight;
    private List<Blockers> blockersLeft, blockersRight;

    private BossbarHud hud;

    public GambitHUD(World w, boolean createHidden) {
        this.w = w;
        if(!createHidden)
            show();
        this.scoreLeft = this.scoreRight = 0;
        this.blockersLeft = new ArrayList<>();
        this.blockersRight = new ArrayList<>();
    }

    public void update() {
        if(hud == null)
            return;

        hud.setText(BASE,
                new LiteralText(Character.toString(GambitUnicode.BAR_BG_LEFT_MOTES))
                        .append(new TranslatableText("space.64"))
                        .append(new LiteralText(Character.toString(GambitUnicode.BAR_BG_RIGHT_MOTES))))
        .setText(SCORE,
                new LiteralText(getScore(scoreLeft))
                        .append(new LiteralText(SCORE_SPACE))
                        .append(new LiteralText(getScore(scoreRight))))
        .setText(PROGRESS,
                new LiteralText(getProgress(true, scoreLeft, scoreRight))
                        .append(new LiteralText(PROGRESS_SPACE))
                        .append(new LiteralText(getProgress(false, scoreRight, scoreLeft))))
        .setText(OVERLAY,
                new LiteralText(Character.toString(GambitUnicode.BAR_OVERLAY_LEFT_MOTES))
                        .append(new TranslatableText("space.64"))
                        .append(new LiteralText(Character.toString(GambitUnicode.BAR_OVERLAY_RIGHT_MOTES))))
        .setText(BLOCKERS,
                new LiteralText(getBlockers(true, blockersLeft, blockersRight.size()))
                        .append(new LiteralText(BLOCKER_SPACE))
                        .append(new LiteralText(getBlockers(false, blockersRight, blockersLeft.size()))));
    }

    public void show() {
        this.hud = new BossbarHud(Lists.newArrayList(BASE, SCORE, PROGRESS, OVERLAY, BLOCKERS), w);
    }

    public void destroy() {
        this.hud.destroy();
        this.hud = null;
    }

    public GambitHUD setLeftScore(int score) {
        this.scoreLeft = score;
        return this;
    }

    public GambitHUD setRightScore(int score) {
        this.scoreRight = score;
        return this;
    }

    public GambitHUD setLeftBlockers(List<Blockers> blockers) {
        this.blockersLeft = blockers;
        return this;
    }

    public GambitHUD setRightBlockers(List<Blockers> blockers) {
        this.blockersRight = blockers;
        return this;
    }

    public List<Blockers> getRandomBlockers() {
        List<Blockers> blockers = new ArrayList<>();
        Random r = new Random();
        IntStream.of(0, 1, 2, 3).forEach($ -> {
            if((r.nextInt(100) + 1) % 2 != 0) {
                int random = r.nextInt(3);
                switch(random) {
                    case 0:
                        blockers.add(Blockers.SMALL);
                        break;
                    case 1:
                        blockers.add(Blockers.MEDIUM);
                        break;
                    case 2:
                        blockers.add(Blockers.LARGE);
                        break;
                }
            }
        });

        return blockers;
    }

    private String getScore(int score) {
        int digit1 = score / 10;
        int digit2 = score % 10;
        return String.valueOf(UnicodeHUD.Numbers.get(digit1, true)) +
                UnicodeHUD.SPACE_1PX +
                UnicodeHUD.Numbers.get(digit2, true);
    }

    private String getBlockers(boolean isLeft, List<Blockers> blockers, int align) {
        StringBuilder builder = new StringBuilder();
        if(!blockers.isEmpty()) {
            for(int i = 0; i < blockers.size() - 1; i++)
                if(isLeft)
                    builder.insert(0, blockers.get(i).getUnicode()).insert(0, UnicodeHUD.SPACE_2PX);
                else
                    builder.append(blockers.get(i).getUnicode()).append(UnicodeHUD.SPACE_2PX);

            if(isLeft)
                builder.insert(0, blockers.get(blockers.size() - 1).getUnicode());
            else
                builder.append(blockers.get(blockers.size() - 1).getUnicode());
        }

        if(blockers.size() == align)
            return builder.toString();

        for(int i = 0; i < align - blockers.size(); i++)
            if(isLeft)
                builder.insert(0, UnicodeHUD.SPACE_5PX).insert(0, UnicodeHUD.SPACE_2PX);
            else
                builder.append(UnicodeHUD.SPACE_5PX).append(UnicodeHUD.SPACE_2PX);

        return builder.toString();
    }

    private String getProgress(boolean isLeft, int progress, int align) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < progress - 1; i++)
            if(isLeft)
                builder.insert(0, GambitUnicode.BAR_FILL_LEFT_MOTES).insert(0, UnicodeHUD.BACK_1PX);
            else
                builder.append(GambitUnicode.BAR_FILL_RIGHT_MOTES).append(UnicodeHUD.BACK_1PX);

        if(isLeft)
            builder.insert(0, GambitUnicode.BAR_FILL_LEFT_MOTES);
        else
            builder.append(GambitUnicode.BAR_FILL_RIGHT_MOTES);

        if(progress == align)
            return builder.toString();

        for(int i = 0; i < align - progress; i++)
            if(isLeft)
                builder.insert(0, UnicodeHUD.SPACE_2PX);
            else
                builder.append(UnicodeHUD.SPACE_2PX);

        return builder.toString();
    }
}

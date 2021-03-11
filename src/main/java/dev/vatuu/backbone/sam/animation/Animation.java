package dev.vatuu.backbone.sam.animation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.vatuu.backbone.utils.Codecs;
import net.minecraft.util.Identifier;

import java.util.Map;

public final class Animation {

    private final Identifier id;
    private final Map<Double, Keyframe> animData;

    public Animation(Identifier id, Map<Double, Keyframe> animData) {
        this.id = id;
        this.animData = animData;
    }

    public static final Codec<Animation> CODEC = RecordCodecBuilder.create(i -> i.group(
        Codecs.IDENTIFIER.fieldOf("id").forGetter((Animation o) -> o.id),
        Codec.unboundedMap(Codec.DOUBLE, Keyframe.CODEC).fieldOf("data").forGetter((Animation o) -> o.animData)
    ).apply(i, Animation::new));
}

package dev.vatuu.backbone.utils;

import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;

public final class Codecs {

    public static final Codec<Identifier> IDENTIFIER = Codec.STRING.xmap(Identifier::new, Identifier::toString);
}

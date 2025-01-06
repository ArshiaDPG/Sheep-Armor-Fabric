package net.digitalpear.armored_wool.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.DyeColor;

import java.util.List;
import java.util.Objects;

public record WoolColorEntry(DyeColor color, int weight) {
    public static final List<WoolColorEntry> DEFAULT_SHEEP_COLORS = List.of(
            new WoolColorEntry(DyeColor.BLACK, 5),
            new WoolColorEntry(DyeColor.GRAY, 5),
            new WoolColorEntry(DyeColor.LIGHT_GRAY, 5),
            new WoolColorEntry(DyeColor.BROWN, 3),
            new WoolColorEntry(DyeColor.PINK, 1),
            new WoolColorEntry(DyeColor.WHITE, 481)
    );

    public static final Codec<WoolColorEntry> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    DyeColor.CODEC.fieldOf("color").orElse(DyeColor.WHITE).forGetter(woolColorEntry -> woolColorEntry.color),
                    Codec.INT.fieldOf("weight").orElse(1).forGetter(woolColorEntry -> woolColorEntry.weight)
            ).apply(instance, WoolColorEntry::new));


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WoolColorEntry that = (WoolColorEntry) o;
        return weight == that.weight && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, weight);
    }
}

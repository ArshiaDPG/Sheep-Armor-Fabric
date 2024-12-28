package net.digitalpear.sheep_armor.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WoolColorEntry{
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
                    Codec.intRange(1, 500).fieldOf("weight").orElse(1).forGetter(woolColorEntry -> woolColorEntry.weight)
            ).apply(instance, WoolColorEntry::new));


    private final DyeColor color;
    private final int weight;
    public WoolColorEntry(DyeColor color, int weight){
        this.color = color;
        this.weight = weight;
    }

    public static List<WoolColorEntry> createList(WoolColorEntry... entries){
        return new ArrayList<>(Arrays.asList(entries));
    }

    public static DyeColor generateColor(List<WoolColorEntry> entryList, Random random){
        if (entryList.isEmpty()){
            return DyeColor.WHITE;
        }
        int totalWeight = entryList.stream().mapToInt(wc -> wc.weight).sum();
        int randomValue = random.nextInt(totalWeight);

        for (WoolColorEntry wc : entryList) {
            if (randomValue < wc.weight) {
                return wc.color;
            }
            randomValue -= wc.weight;
        }

        throw new IllegalStateException("Failed to generate a color");
    }
}

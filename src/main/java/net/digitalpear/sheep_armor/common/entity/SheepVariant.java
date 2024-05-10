package net.digitalpear.sheep_armor.common.entity;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public record SheepVariant(Identifier name, List<Predicate<Context>> predicates) {
    public Identifier getName() {
        return this.name;
    }

    public List<Predicate<Context>> getPredicates() {
        return predicates;
    }

    public RegistryEntry<SheepVariant> getRegistryEntry(){
        return SheepVariantRegistry.SHEEP_VARIANT.getEntry(this);
    }


    public record Context(BlockPos pos, World world) {
        public BlockPos getPos(){
            return this.pos;
        }
        public World getWorld(){
            return this.world;
        }
    }

}

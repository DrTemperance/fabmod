package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class BuildyBunchMod implements ModInitializer {
    public static final Block hardened_milk = new Block(FabricBlockSettings.create().strength(4.0f, 3.0f)
            .sounds(BlockSoundGroup.SLIME).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block hannah_block = new Block(
            FabricBlockSettings.create().strength(6.0f, 4.0f).sounds(BlockSoundGroup.AMETHYST_BLOCK));

    @Override
    public void onInitialize() {
        registerBlock("hardened_milk", hardened_milk);
        registerBlockItem("hardened_milk", hardened_milk);
        registerBlock("hannah_block", hannah_block);
        registerBlockItem("hannah_block", hannah_block);
        registerItemsToFoodAndDrinkGroup(hardened_milk, hannah_block);
    }

    private void registerBlock(String id, Block block) {
        Registry.register(Registries.BLOCK, new Identifier("buildybunchmod", id), block);
    }

    private void registerBlockItem(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier("buildybunchmod", id),
                new BlockItem(block, new FabricItemSettings()));
    }

    private void registerItemsToFoodAndDrinkGroup(Block... blocks) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            Arrays.stream(blocks).forEach(content::add);
        });
    }
}

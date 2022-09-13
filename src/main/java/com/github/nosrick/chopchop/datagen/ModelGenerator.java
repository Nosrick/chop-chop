package com.github.nosrick.chopchop.datagen;

import com.github.nosrick.chopchop.ChopChopMod;
import com.github.nosrick.chopchop.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsGen) {

        /*
        var woodenPlanksOptional = Registry.BLOCK.getEntryList(BlockTags.PLANKS);

        if (woodenPlanksOptional.isEmpty()) {
            return;
        }

        RegistryEntryList.Named<Block> woodenPlanks = woodenPlanksOptional.get();
        for (RegistryEntry<Block> plankEntry : woodenPlanks) {
            Block plankBlock = plankEntry.value();
         */

        Block[] woodenBlocks = new Block[] { Blocks.OAK_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.BIRCH_PLANKS };
        for(Block plankBlock : woodenBlocks) {

            bsGen.blockStateCollector.accept(
                    VariantsBlockStateSupplier
                            .create(
                                    BlockRegistry.CHOPPING_BOARD.get(),
                                    BlockStateVariant.create()
                                            .put(VariantSettings.MODEL,
                                                    ChopChopTexturedModels.TEMPLATE_CHOPPING_BOARD
                                                            .upload(
                                                                    plankBlock,
                                                                    bsGen.modelCollector)))
                            .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator imGen) {
    }

    protected abstract class ChopChopModels {
        protected static final TextureKey[] SIDE_TOP_BOTTOM = new TextureKey[]{TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM};

        public static final Model CHOPPING_BOARD = new Model(
                Optional.of(
                        ChopChopMod.getId("block/chopping_board")),
                Optional.empty(),
                SIDE_TOP_BOTTOM);
    }

    protected abstract class ChopChopTexturedModels {
        public static final TexturedModel.Factory TEMPLATE_CHOPPING_BOARD = TexturedModel.makeFactory(TextureMap::sideTopBottom, ChopChopModels.CHOPPING_BOARD);
    }
}

package com.github.nosrick.chopchop.datagen;

import com.github.nosrick.chopchop.ChopChopMod;
import com.github.nosrick.chopchop.registry.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsGen) {
        bsGen.blockStateCollector.accept(
                VariantsBlockStateSupplier
                        .create(BlockRegistry.CHOPPING_BOARD.get(),
                                BlockStateVariant.create()
                                        .put(
                                                VariantSettings.MODEL,
                                                ChopChopTexturedModels.TEMPLATE_CHOPPING_BOARD
                                                        .upload(
                                                                BlockRegistry.CHOPPING_BOARD.get(),
                                                                bsGen.modelCollector)))
                        .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }

    @Override
    public void generateItemModels(ItemModelGenerator imGen) {
    }

    protected abstract class ChopChopModels {
        protected static final TextureKey[] SIDE_TOP_BOTTOM = new TextureKey[]{TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM};

        public static final Model CHOPPING_BOARD = new Model(
                Optional.of(
                        ChopChopMod.getId("block/chopping_board_template")),
                Optional.empty(),
                TextureKey.ALL);
    }

    protected abstract class ChopChopTexturedModels {
        public static final TexturedModel.Factory TEMPLATE_CHOPPING_BOARD = TexturedModel.makeFactory(TextureMap::all, ChopChopModels.CHOPPING_BOARD);
    }
}

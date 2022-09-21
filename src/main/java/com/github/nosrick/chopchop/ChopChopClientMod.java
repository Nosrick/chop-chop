package com.github.nosrick.chopchop;

import com.github.nosrick.chopchop.block.entity.BlockEntityTypeRegistry;
import com.github.nosrick.chopchop.client.render.block.ChoppingBoardBlockEntityRenderer;
import com.github.nosrick.chopchop.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class ChopChopClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenderLayer();

        BlockEntityRendererRegistry.register(BlockEntityTypeRegistry.CHOPPING_BOARD.get(), ChoppingBoardBlockEntityRenderer::new);
    }

    public static void registerRenderLayer() {
        for (BlockRegistry value : BlockRegistry.values()) {
            if (value.isCutout()) {
                BlockRenderLayerMap.INSTANCE.putBlock(value.get(), RenderLayer.getCutout());
            }
        }
    }
}

package com.github.nosrick.chopchop.client;

import com.github.nosrick.chopchop.main.block.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ChopChopClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenderLayer();
    }

    public static void registerRenderLayer() {
        for (BlockRegistry value : BlockRegistry.values()) {
            if (value.isCutout()) {
                BlockRenderLayerMap.INSTANCE.putBlock(value.get(), RenderLayer.getCutout());
            }
        }
    }
}

package com.github.nosrick.chopchop.client.render.block;

import com.github.nosrick.chopchop.block.entity.ChoppingBoardBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

public class ChoppingBoardBlockEntityRenderer  implements BlockEntityRenderer<ChoppingBoardBlockEntity> {

    public ChoppingBoardBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }


    @Override
    public void render(ChoppingBoardBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemStack itemStack = entity.getItemStack();
        Direction direction = entity.getCachedState().get(Properties.HORIZONTAL_FACING).getOpposite();

        if(!itemStack.isEmpty()) {
            int intPos = (int)entity.getPos().asLong();

            matrices.push();

            ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

            boolean blockItem = itemRenderer.getModel(itemStack, entity.getWorld(), null, intPos).hasDepth();

            if(!blockItem) {
                matrices.translate(0.5d, 0.08d, 0.5d);

                float f = -direction.asRotation();
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90f));

                matrices.scale(0.6f, 0.6f, 0.6f);
            }
            else {
                matrices.translate(0.5d, 0.23d, 0.5d);

                float f = -direction.asRotation() + 180;
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(f));

                matrices.scale(0.8f, 0.8f, 0.8f);
            }

            itemRenderer.renderItem(
                    itemStack,
                    ModelTransformation.Mode.FIXED,
                    light,
                    overlay,
                    matrices,
                    vertexConsumers,
                    intPos);

            matrices.pop();
        }
    }
}

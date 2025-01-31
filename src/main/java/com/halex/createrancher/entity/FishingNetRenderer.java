package com.halex.createrancher.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.mojang.text2speech.Narrator.LOGGER;

public class FishingNetRenderer extends EntityRenderer<FishingNetEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("createrancher", "textures/entity/fishing_net_entity.png");

    private final FishingNetModel<FishingNetEntity> model;

    public FishingNetRenderer(EntityRendererProvider.Context context) {
        super(context);
        LOGGER.info("Baking model layer: " + FishingNetModel.LAYER_LOCATION);
        this.model = new FishingNetModel<>(context.bakeLayer(FishingNetModel.LAYER_LOCATION));
    }

    @Override
    public void render(FishingNetEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light) {
            poseStack.pushPose();
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));
            this.model.renderToBuffer(poseStack, vertexConsumer, light, 15728880, 1.0f, 1.0f, 1.0f, 1.0f);
            poseStack.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FishingNetEntity entity) {
        return TEXTURE;
    }
}
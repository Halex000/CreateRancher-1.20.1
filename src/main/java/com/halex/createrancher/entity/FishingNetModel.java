package com.halex.createrancher.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class FishingNetModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("createrancher", "fishing_net"), "main");
    private final ModelPart net;

    public FishingNetModel(ModelPart root) {
        this.net = root.getChild("net");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition net = partdefinition.addOrReplaceChild("net", CubeListBuilder.create().texOffs(0, 111).addBox(-23.0F, -2.0F, -23.0F, 44.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(21.0F, -2.0F, -23.0F, 2.0F, 2.0F, 48.0F, new CubeDeformation(0.0F))
                .texOffs(0, 169).addBox(-23.0F, -2.0F, 23.0F, 44.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 117).addBox(-25.0F, -2.0F, -23.0F, 2.0F, 2.0F, 48.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-23.0F, 0.0F, -21.0F, 44.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-23.0F, 0.0F, 23.0F, 44.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(-44, 0).addBox(-23.0F, 12.0F, -21.0F, 44.0F, 0.0F, 44.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition cube_r1 = net.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 45).addBox(-17.0F, -2.0F, -1.0F, 44.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.0F, 2.0F, -4.0F, 0.0F, -1.5708F, 0.0F));
        PartDefinition cube_r2 = net.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 45).addBox(-17.0F, -2.0F, -1.0F, 44.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.0F, 2.0F, -4.0F, 0.0F, -1.5708F, 0.0F));
        return LayerDefinition.create(meshdefinition, 100, 173);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Model has no animation
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        net.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
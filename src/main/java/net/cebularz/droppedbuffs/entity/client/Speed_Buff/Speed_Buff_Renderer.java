package net.cebularz.droppedbuffs.entity.client.Speed_Buff;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.cebularz.droppedbuffs.ColorConverter;
import net.cebularz.droppedbuffs.DroppedBuffs;
import net.cebularz.droppedbuffs.entity.client.Haste_Buff.Haste_Buff_Model;
import net.cebularz.droppedbuffs.entity.client.ModModelLayers;
import net.cebularz.droppedbuffs.entity.custom.Haste_Buff_Entity;
import net.cebularz.droppedbuffs.entity.custom.Speed_Buff_Entity;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class Speed_Buff_Renderer extends EntityRenderer<Speed_Buff_Entity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(DroppedBuffs.MOD_ID, "textures/entity/speed_buff.png");

    private final Speed_Buff_Model<Speed_Buff_Entity> model;
    public Speed_Buff_Renderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new Speed_Buff_Model<>(context.bakeLayer(ModModelLayers.SPEED_BUFF_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(Speed_Buff_Entity pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(Speed_Buff_Entity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.pushPose();
        double bobOffset = Math.sin((pEntity.tickCount + pPartialTick) * 0.075) * 0.075;
        pPoseStack.translate(0.0D, 0.75D + bobOffset, 0.0D);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.rotationX));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.rotationY));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntity.rotationZ));
        float size = 1.0F;
        pPoseStack.scale( size, size,size);

        float alpha = pEntity.alpha;
        int color = ColorConverter.convertToARGB(alpha,1F,1F,1F);
        VertexConsumer consumer = pBuffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        model.renderToBuffer(pPoseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,color);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
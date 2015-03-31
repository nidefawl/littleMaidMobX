/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelBase;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelBoxBase;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelRenderer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import network.W_Message;
/*     */ import network.W_Network;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Client
/*     */ {
/*     */   public static void clientCustomPayload(W_Message var2)
/*     */   {
/*  59 */     byte lmode = var2.data[0];
/*  60 */     int leid = 0;
/*  61 */     Entity lentity = null;
/*  62 */     if ((lmode & 0x80) != 0) {
/*  63 */       leid = MMM_Helper.getInt(var2.data, 1);
/*  64 */       lentity = MMM_Helper.getEntity(var2.data, 1, MMM_Helper.mc.theWorld);
/*  65 */       if (lentity == null) return;
/*     */     }
/*  67 */     MMMLib.Debug("MMM|Upd Clt Call[%2x:%d].", new Object[] { Byte.valueOf(lmode), Integer.valueOf(leid) });
/*     */     
/*  69 */     switch (lmode)
/*     */     {
/*     */     case 1: 
/*  72 */       MMM_TextureManager.instance.reciveFormServerSetTexturePackIndex(var2.data);
/*  73 */       break;
/*     */     
/*     */     case 2: 
/*  76 */       MMM_TextureManager.instance.reciveFromServerSetTexturePackName(var2.data);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isIntegratedServerRunning()
/*     */   {
/* 103 */     return Minecraft.getMinecraft().isIntegratedServerRunning();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void renderArrowsStuckInEntity(EntityLivingBase par1EntityLiving, float par2, Render pRender, ModelBase pModel)
/*     */   {
/* 113 */     int lacount = par1EntityLiving.getArrowCountInEntity();
/*     */     
/* 115 */     if (lacount > 0) {
/* 116 */       EntityArrow larrow = new EntityArrow(par1EntityLiving.worldObj, par1EntityLiving.posX, par1EntityLiving.posY, par1EntityLiving.posZ);
/* 117 */       Random lrand = new Random(par1EntityLiving.getEntityId());
/* 118 */       RenderHelper.disableStandardItemLighting();
/*     */       
/* 120 */       for (int var6 = 0; var6 < lacount; var6++) {
/* 121 */         GL11.glPushMatrix();
/* 122 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 123 */         ModelRenderer var7 = pModel.getRandomModelBox(lrand);
/* 124 */         ModelBoxBase var8 = (ModelBoxBase)var7.cubeList.get(lrand.nextInt(var7.cubeList.size()));
/* 125 */         var7.postRender(0.0625F);
/* 126 */         float var9 = lrand.nextFloat();
/* 127 */         float var10 = lrand.nextFloat();
/* 128 */         float var11 = lrand.nextFloat();
/* 129 */         float var12 = (var8.posX1 + (var8.posX2 - var8.posX1) * var9) / 16.0F;
/* 130 */         float var13 = (var8.posY1 + (var8.posY2 - var8.posY1) * var10) / 16.0F;
/* 131 */         float var14 = (var8.posZ1 + (var8.posZ2 - var8.posZ1) * var11) / 16.0F;
/* 132 */         GL11.glTranslatef(var12, var13, var14);
/* 133 */         var9 = var9 * 2.0F - 1.0F;
/* 134 */         var10 = var10 * 2.0F - 1.0F;
/* 135 */         var11 = var11 * 2.0F - 1.0F;
/* 136 */         var9 *= -1.0F;
/* 137 */         var10 *= -1.0F;
/* 138 */         var11 *= -1.0F;
/* 139 */         float var15 = MathHelper.sqrt_float(var9 * var9 + var11 * var11);
/* 140 */         larrow.prevRotationYaw = (larrow.rotationYaw = (float)(Math.atan2(var9, var11) * 180.0D / 3.141592653589793D));
/* 141 */         larrow.prevRotationPitch = (larrow.rotationPitch = (float)(Math.atan2(var10, var15) * 180.0D / 3.141592653589793D));
/* 142 */         double var16 = 0.0D;
/* 143 */         double var18 = 0.0D;
/* 144 */         double var20 = 0.0D;
/* 145 */         float var22 = 0.0F;
/*     */         
/* 147 */         RenderManager.instance.renderEntityWithPosYaw(larrow, var16, var18, var20, var22, par2);
/* 148 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 151 */       RenderHelper.enableStandardItemLighting();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setLightmapTextureCoords(int pValue)
/*     */   {
/* 165 */     int ls = pValue & 0xFFFF;
/* 166 */     int lt = pValue >>> 16;
/* 167 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, ls / 1.0F, lt / 1.0F);
/*     */   }
/*     */   
/*     */   public static void setTexture(ResourceLocation pRLocation)
/*     */   {
/* 172 */     if (pRLocation != null) {
/* 173 */       Minecraft.getMinecraft().renderEngine.bindTexture(pRLocation);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static World getMCtheWorld()
/*     */   {
/* 184 */     return Minecraft.getMinecraft().theWorld;
/*     */   }
/*     */   
/*     */   public static void sendToServer(byte[] ldata) {
/* 188 */     W_Network.sendPacketToServer(1, ldata);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/Client.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
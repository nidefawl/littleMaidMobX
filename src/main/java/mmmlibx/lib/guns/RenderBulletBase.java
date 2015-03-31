/*    */ package mmmlibx.lib.guns;
/*    */ 
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderBulletBase
/*    */   extends Render
/*    */ {
/*    */   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
/*    */   {
/* 16 */     doRender((EntityBulletBase)var1, var2, var4, var6, var8, var9);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation getEntityTexture(Entity var1)
/*    */   {
/* 22 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void doRender(EntityBulletBase pEntity, double pX, double pY, double pZ, float var8, float var9)
/*    */   {
/* 37 */     GL11.glPushMatrix();
/* 38 */     GL11.glTranslatef((float)pX, (float)pY, (float)pZ);
/* 39 */     GL11.glRotatef(pEntity.prevRotationYaw + (pEntity.rotationYaw - pEntity.prevRotationYaw) * var9 - 90.0F, 0.0F, 1.0F, 0.0F);
/* 40 */     GL11.glRotatef(pEntity.prevRotationPitch + (pEntity.rotationPitch - pEntity.prevRotationPitch) * var9, 0.0F, 0.0F, 1.0F);
/* 41 */     Tessellator tessellator = Tessellator.instance;
/* 42 */     float f10 = 0.05625F;
/* 43 */     GL11.glEnable(32826);
/*    */     
/* 45 */     GL11.glDisable(3553);
/* 46 */     GL11.glScalef(f10, f10, f10);
/*    */     
/* 48 */     int lcolor = getColor(pEntity);
/*    */     
/* 50 */     GL11.glPushMatrix();
/* 51 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/* 52 */     GL11.glTranslatef(-4.7F, 0.0F, 0.0F);
/* 53 */     GL11.glNormal3f(f10, 0.0F, 0.0F);
/* 54 */     tessellator.startDrawingQuads();
/* 55 */     tessellator.setColorOpaque_I(lcolor);
/*    */     
/* 57 */     tessellator.addVertex(4.5D, -0.5D, 0.0D);
/* 58 */     tessellator.addVertex(4.5D, 0.0D, -0.5D);
/* 59 */     tessellator.addVertex(4.5D, 0.5D, 0.0D);
/* 60 */     tessellator.addVertex(4.5D, 0.0D, 0.5D);
/* 61 */     tessellator.draw();
/* 62 */     GL11.glNormal3f(-f10, 0.0F, 0.0F);
/* 63 */     tessellator.startDrawingQuads();
/*    */     
/* 65 */     tessellator.setColorOpaque_I(lcolor);
/* 66 */     tessellator.addVertex(4.5D, 0.0D, 0.5D);
/* 67 */     tessellator.addVertex(4.5D, 0.5D, 0.0D);
/* 68 */     tessellator.addVertex(4.5D, 0.0D, -0.5D);
/* 69 */     tessellator.addVertex(4.5D, -0.5D, 0.0D);
/* 70 */     tessellator.draw();
/* 71 */     for (int j = 0; j < 4; j++) {
/* 72 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 73 */       GL11.glNormal3f(0.0F, 0.0F, f10);
/* 74 */       tessellator.startDrawingQuads();
/* 75 */       tessellator.setColorOpaque_I(lcolor);
/*    */       
/* 77 */       tessellator.addVertex(4.5D, -0.5D, 0.0D);
/* 78 */       tessellator.addVertex(6.5D, -0.5D, 0.0D);
/* 79 */       tessellator.addVertex(6.5D, 0.5D, 0.0D);
/* 80 */       tessellator.addVertex(4.5D, 0.5D, 0.0D);
/* 81 */       tessellator.draw();
/*    */     }
/* 83 */     GL11.glPopMatrix();
/* 84 */     GL11.glEnable(3553);
/* 85 */     renderOptional(pEntity, pX, pY, pZ, var8, var9);
/* 86 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   public int getColor(EntityBulletBase pEntity)
/*    */   {
/* 92 */     return pEntity.getBulletColor();
/*    */   }
/*    */   
/*    */   public void renderOptional(EntityBulletBase pEntity, double pX, double pY, double pZ, float var8, float var9) {}
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/guns/RenderBulletBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
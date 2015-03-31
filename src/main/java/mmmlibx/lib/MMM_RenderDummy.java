/*    */ package mmmlibx.lib;
/*    */ 
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class MMM_RenderDummy
/*    */   extends Render
/*    */ {
/*    */   public MMM_RenderDummy()
/*    */   {
/* 16 */     this.shadowSize = 0.0F;
/*    */   }
/*    */   
/*    */ 
/*    */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 22 */     GL11.glPushMatrix();
/* 23 */     GL11.glDisable(2896);
/* 24 */     GL11.glEnable(32826);
/* 25 */     GL11.glEnable(2903);
/*    */     
/* 27 */     GL11.glEnable(3042);
/* 28 */     GL11.glDisable(2884);
/* 29 */     GL11.glBlendFunc(770, 771);
/* 30 */     GL11.glDisable(3553);
/* 31 */     GL11.glDepthMask(false);
/* 32 */     GL11.glTranslatef((float)d, (float)d1, (float)d2);
/*    */     
/* 34 */     RenderHelper.disableStandardItemLighting();
/* 35 */     Tessellator tessellator = Tessellator.instance;
/* 36 */     tessellator.startDrawingQuads();
/*    */     
/* 38 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 39 */     if ((entity instanceof MMM_EntityDummy)) {
/* 40 */       int cc = ((MMM_EntityDummy)entity).getColor();
/* 41 */       int cr = cc >> 16 & 0xFF;
/* 42 */       int cg = cc >> 8 & 0xFF;
/* 43 */       int cb = cc & 0xFF;
/* 44 */       int ca = MathHelper.floor_float(((MMM_EntityDummy)entity).getAlpha(1.0F) * 256.0F);
/*    */       
/* 46 */       tessellator.setColorRGBA_I(cc, ca);
/*    */     }
/* 48 */     double xa = 0.3D;
/* 49 */     double xb = 0.7D;
/*    */     
/* 51 */     double yy = 0.015625D;
/* 52 */     double za = 0.3D;
/* 53 */     double zb = 0.7D;
/* 54 */     tessellator.addVertex(xa, yy, za);
/* 55 */     tessellator.addVertex(xa, yy, zb);
/* 56 */     tessellator.addVertex(xb, yy, zb);
/* 57 */     tessellator.addVertex(xb, yy, za);
/*    */     
/* 59 */     tessellator.draw();
/* 60 */     RenderHelper.disableStandardItemLighting();
/* 61 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 62 */     GL11.glEnable(3553);
/* 63 */     GL11.glDisable(3042);
/* 64 */     GL11.glEnable(2884);
/* 65 */     GL11.glDepthMask(true);
/* 66 */     GL11.glEnable(2896);
/* 67 */     GL11.glDisable(32826);
/* 68 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected ResourceLocation getEntityTexture(Entity var1)
/*    */   {
/* 75 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_RenderDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
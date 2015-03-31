/*    */ package zabuton;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class VZN_RenderZabuton
/*    */   extends Render
/*    */ {
/*    */   protected ModelBase baseZabuton;
/* 13 */   protected static final ResourceLocation[] textures = { new ResourceLocation("zabuton", "textures/entity/zabuton_f.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_e.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_d.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_c.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_b.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_a.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_9.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_8.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_7.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_6.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_5.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_4.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_3.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_2.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_1.png"), new ResourceLocation("zabuton", "textures/entity/zabuton_0.png") };
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public VZN_RenderZabuton()
/*    */   {
/* 33 */     this.shadowSize = 0.0F;
/* 34 */     this.baseZabuton = new VZN_ModelZabuton();
/*    */   }
/*    */   
/*    */   public void doRenderZabuton(VZN_EntityZabuton entityzabuton, double d, double d1, double d2, float f, float f1) {
/* 38 */     if ((entityzabuton.color >= 0) && (entityzabuton.color < 16))
/*    */     {
/* 40 */       this.shadowSize = 0.5F;
/*    */       
/*    */ 
/* 43 */       GL11.glPushMatrix();
/* 44 */       GL11.glTranslatef((float)d, (float)d1, (float)d2);
/* 45 */       GL11.glRotatef(180.0F - f, 0.0F, 1.0F, 0.0F);
/*    */       
/* 47 */       bindEntityTexture(entityzabuton);
/* 48 */       GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 49 */       this.baseZabuton.render(entityzabuton, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 50 */       GL11.glPopMatrix();
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 55 */       this.shadowSize = 0.0F;
/*    */     }
/*    */   }
/*    */   
/*    */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 61 */     doRenderZabuton((VZN_EntityZabuton)entity, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity var1)
/*    */   {
/* 66 */     return textures[(((VZN_EntityZabuton)var1).color & 0xF)];
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_RenderZabuton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
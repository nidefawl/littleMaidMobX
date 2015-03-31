/*    */ package mmmlibx.lib;
/*    */ 
/*    */ import mmmlibx.lib.multiModel.model.mc162.ModelRenderer;
/*    */ import mmmlibx.lib.multiModel.model.mc162.ModelStabilizerBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class MMM_ModelStabilizer_WitchHat extends ModelStabilizerBase
/*    */ {
/* 11 */   public static ResourceLocation ftex = new ResourceLocation("/mob/littleMaid/ALTERNATIVE/Stabilizer_MagicHat.png");
/*    */   
/*    */   public ModelRenderer WitchHat;
/*    */   public ModelRenderer WitchHat1;
/*    */   public ModelRenderer WitchHat2;
/*    */   public ModelRenderer WitchHat3;
/*    */   
/*    */   public MMM_ModelStabilizer_WitchHat()
/*    */   {
/* 20 */     this.textureWidth = 64;
/* 21 */     this.textureHeight = 32;
/*    */     
/* 23 */     this.WitchHat = new ModelRenderer(this, 0, 0);
/* 24 */     this.WitchHat1 = new ModelRenderer(this, 0, 0);
/* 25 */     this.WitchHat2 = new ModelRenderer(this, 0, 0);
/* 26 */     this.WitchHat3 = new ModelRenderer(this, 0, 0);
/* 27 */     this.WitchHat.setTextureOffset(0, 15).addBox(-8.0F, 0.0F, -8.0F, 16, 1, 16, 0.0F);
/* 28 */     this.WitchHat.setTextureOffset(0, 0).addBox(-4.5F, -4.0F, -4.5F, 9, 4, 9);
/* 29 */     this.WitchHat1.setTextureOffset(40, 4).addBox(-3.0F, -3.0F, -3.0F, 6, 3, 6).setRotationPoint(0.0F, -4.0F, 0.0F);
/* 30 */     this.WitchHat2.setTextureOffset(28, 0).addBox(-2.0F, -2.0F, -2.0F, 4, 2, 4).setRotationPoint(0.0F, -3.0F, 0.0F);
/* 31 */     this.WitchHat3.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2).setRotationPoint(0.0F, -2.0F, 0.0F);
/*    */     
/* 33 */     this.WitchHat.addChild(this.WitchHat1);
/* 34 */     this.WitchHat1.addChild(this.WitchHat2);
/* 35 */     this.WitchHat2.addChild(this.WitchHat3);
/*    */   }
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 40 */     GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/* 41 */     this.WitchHat.render(f5);
/*    */   }
/*    */   
/*    */   public ResourceLocation getTexture()
/*    */   {
/* 46 */     return ftex;
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 51 */     return "WitchHat";
/*    */   }
/*    */   
/*    */   public boolean isLoadAnotherTexture()
/*    */   {
/* 56 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public float[] getArmorModelsSize()
/*    */   {
/* 62 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ModelStabilizer_WitchHat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
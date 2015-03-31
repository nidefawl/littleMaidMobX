/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelLittleMaid_SR2
/*    */   extends ModelLittleMaidBase
/*    */ {
/*    */   public ModelRenderer eyeR;
/*    */   
/*    */   public ModelRenderer eyeL;
/*    */   
/*    */ 
/*    */   public ModelLittleMaid_SR2() {}
/*    */   
/*    */ 
/*    */   public ModelLittleMaid_SR2(float psize)
/*    */   {
/* 18 */     super(psize);
/*    */   }
/*    */   
/* 21 */   public ModelLittleMaid_SR2(float psize, float pyoffset, int pTextureWidth, int pTextureHeight) { super(psize, pyoffset, pTextureWidth, pTextureHeight); }
/*    */   
/*    */ 
/*    */ 
/*    */   public void initModel(float psize, float pyoffset)
/*    */   {
/* 27 */     super.initModel(psize, pyoffset);
/*    */     
/*    */ 
/* 30 */     this.eyeR = new ModelRenderer(this, 32, 19);
/* 31 */     this.eyeR.addPlate(-4.0F, -5.0F, -4.001F, 4, 4, 0, psize);
/* 32 */     this.eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 33 */     this.eyeL = new ModelRenderer(this, 42, 19);
/* 34 */     this.eyeL.addPlate(0.0F, -5.0F, -4.001F, 4, 4, 0, psize);
/* 35 */     this.eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 36 */     this.bipedHead.addChild(this.eyeR);
/* 37 */     this.bipedHead.addChild(this.eyeL);
/*    */   }
/*    */   
/*    */   public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3, float pRenderPartialTicks)
/*    */   {
/* 42 */     super.setLivingAnimations(pEntityCaps, par2, par3, pRenderPartialTicks);
/*    */     
/* 44 */     float f3 = this.entityTicksExisted + pRenderPartialTicks + this.entityIdFactor;
/*    */     
/* 46 */     if (0.0F > mh_sin(f3 * 0.05F) + mh_sin(f3 * 0.13F) + mh_sin(f3 * 0.7F) + 2.55F) {
/* 47 */       this.eyeR.setVisible(true);
/* 48 */       this.eyeL.setVisible(true);
/*    */     } else {
/* 50 */       this.eyeR.setVisible(false);
/* 51 */       this.eyeL.setVisible(false);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*    */   {
/* 58 */     super.setRotationAngles(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
/* 59 */     if (this.aimedBow) {
/* 60 */       if (ModelCapsHelper.getCapsValueInt(pEntityCaps, 293, new Object[0]) == 0) {
/* 61 */         this.eyeL.setVisible(true);
/*    */       } else {
/* 63 */         this.eyeR.setVisible(true);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public String getUsingTexture()
/*    */   {
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelLittleMaid_SR2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
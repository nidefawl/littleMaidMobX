/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ 
/*    */ public class ModelLittleMaid_AC
/*    */   extends ModelMultiMMMBase
/*    */ {
/*    */   public ModelRenderer bipedHead;
/*    */   
/*    */   public ModelRenderer bipedBody;
/*    */   
/*    */   public ModelRenderer bipedRightArm;
/*    */   
/*    */   public ModelRenderer bipedLeftArm;
/*    */   
/*    */   public ModelRenderer bipedRightLeg;
/*    */   public ModelRenderer bipedLeftLeg;
/*    */   public ModelRenderer Skirt;
/*    */   public ModelRenderer ChignonR;
/*    */   public ModelRenderer ChignonL;
/*    */   public ModelRenderer ChignonB;
/*    */   public ModelRenderer Tail;
/*    */   public ModelRenderer SideTailR;
/*    */   public ModelRenderer SideTailL;
/*    */   
/*    */   public ModelLittleMaid_AC() {}
/*    */   
/*    */   public ModelLittleMaid_AC(float psize)
/*    */   {
/* 29 */     super(psize);
/*    */   }
/*    */   
/*    */   public ModelLittleMaid_AC(float psize, float pyoffset) {
/* 33 */     super(psize, pyoffset, 64, 32);
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
/*    */ 
/*    */ 
/*    */ 
/*    */   public void initModel(float psize, float pyoffset) {}
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
/*    */   public float[] getArmorModelsSize()
/*    */   {
/* 65 */     return new float[] { 0.1F, 0.5F };
/*    */   }
/*    */   
/*    */   public float getHeight()
/*    */   {
/* 70 */     return 1.35F;
/*    */   }
/*    */   
/*    */   public float getWidth()
/*    */   {
/* 75 */     return 0.5F;
/*    */   }
/*    */   
/*    */   public float getyOffset()
/*    */   {
/* 80 */     return 1.215F;
/*    */   }
/*    */   
/*    */   public float getMountedYOffset()
/*    */   {
/* 85 */     return 0.35F;
/*    */   }
/*    */   
/*    */   public void renderItems(IModelCaps pEntityCaps) {}
/*    */   
/*    */   public void renderFirstPersonHand(IModelCaps pEntityCaps) {}
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelLittleMaid_AC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
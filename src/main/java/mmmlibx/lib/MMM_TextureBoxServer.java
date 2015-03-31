/*    */ package mmmlibx.lib;
/*    */ 
/*    */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*    */ import mmmlibx.lib.multiModel.model.mc162.ModelCapsHelper;
/*    */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*    */ 
/*    */ public class MMM_TextureBoxServer extends MMM_TextureBoxBase
/*    */ {
/*    */   protected MMM_TextureBox localBox;
/*    */   
/*    */   public MMM_TextureBoxServer() {}
/*    */   
/*    */   public MMM_TextureBoxServer(MMM_TextureBox pBox)
/*    */   {
/* 15 */     this.localBox = pBox;
/* 16 */     this.contractColor = pBox.getContractColorBits();
/* 17 */     this.wildColor = pBox.getWildColorBits();
/* 18 */     this.textureName = pBox.textureName;
/* 19 */     this.isUpdateSize = ((pBox.models != null) && (pBox.models[0] != null) ? ModelCapsHelper.getCapsValueBoolean(pBox.models[0], 4, new Object[0]) : false);
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
/*    */   public void setValue(byte[] pData)
/*    */   {
/* 35 */     this.contractColor = MMM_Helper.getShort(pData, 2);
/* 36 */     this.wildColor = MMM_Helper.getShort(pData, 4);
/* 37 */     this.modelHeight = MMM_Helper.getFloat(pData, 6);
/* 38 */     this.modelWidth = MMM_Helper.getFloat(pData, 10);
/* 39 */     this.modelYOffset = MMM_Helper.getFloat(pData, 14);
/* 40 */     this.modelMountedYOffset = MMM_Helper.getFloat(pData, 18);
/* 41 */     this.textureName = MMM_Helper.getStr(pData, 22);
/*    */   }
/*    */   
/*    */   public float getHeight(IModelCaps pEntityCaps)
/*    */   {
/* 46 */     return this.localBox != null ? this.localBox.models[0].getHeight(pEntityCaps) : this.modelHeight;
/*    */   }
/*    */   
/*    */   public float getWidth(IModelCaps pEntityCaps)
/*    */   {
/* 51 */     return this.localBox != null ? this.localBox.models[0].getWidth(pEntityCaps) : this.modelWidth;
/*    */   }
/*    */   
/*    */   public float getYOffset(IModelCaps pEntityCaps)
/*    */   {
/* 56 */     return this.localBox != null ? this.localBox.models[0].getyOffset(pEntityCaps) : this.modelYOffset;
/*    */   }
/*    */   
/*    */   public float getMountedYOffset(IModelCaps pEntityCaps)
/*    */   {
/* 61 */     return this.localBox != null ? this.localBox.models[0].getMountedYOffset(pEntityCaps) : this.modelMountedYOffset;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_TextureBoxServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
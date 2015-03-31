/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ import net.minecraft.client.model.PositionTextureVertex;
/*    */ import net.minecraft.client.model.TexturedQuad;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ModelBoxBase
/*    */ {
/*    */   protected PositionTextureVertex[] vertexPositions;
/*    */   protected TexturedQuad[] quadList;
/*    */   public float posX1;
/*    */   public float posY1;
/*    */   public float posZ1;
/*    */   public float posX2;
/*    */   public float posY2;
/*    */   public float posZ2;
/*    */   public String boxName;
/*    */   
/*    */   public ModelBoxBase(ModelRenderer pMRenderer, Object... pArg) {}
/*    */   
/*    */   public void render(Tessellator par1Tessellator, float par2)
/*    */   {
/* 29 */     for (int var3 = 0; var3 < this.quadList.length; var3++) {
/* 30 */       this.quadList[var3].draw(par1Tessellator, par2);
/*    */     }
/*    */   }
/*    */   
/*    */   public ModelBoxBase setBoxName(String pName) {
/* 35 */     this.boxName = pName;
/* 36 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelBoxBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
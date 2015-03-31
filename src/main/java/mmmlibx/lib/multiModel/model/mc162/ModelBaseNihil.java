/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.Entity;
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
/*    */ public class ModelBaseNihil
/*    */   extends ModelBase
/*    */ {
/*    */   public RendererLivingEntity rendererLivingEntity;
/*    */   public boolean isAlphablend;
/*    */   public boolean isModelAlphablend;
/*    */   public IModelBaseMMM capsLink;
/*    */   public int lighting;
/*    */   public IModelCaps entityCaps;
/*    */   public boolean isRendering;
/*    */   public int renderCount;
/*    */   
/*    */   public void showAllParts() {}
/*    */   
/*    */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*    */   {
/* 35 */     this.renderCount += 1;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelBaseNihil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
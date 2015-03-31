/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ModelStabilizerBase
/*    */   extends ModelBase
/*    */ {
/*    */   public ResourceLocation getTexture()
/*    */   {
/* 15 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean checkEquipment(String pName)
/*    */   {
/* 23 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract String getName();
/*    */   
/*    */ 
/*    */ 
/*    */   public int getExclusive()
/*    */   {
/* 35 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean isLoadAnotherTexture()
/*    */   {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   public void init(EquippedStabilizer pequipped) {}
/*    */   
/*    */   public void render(ModelMultiBase pModel, Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {}
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelStabilizerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
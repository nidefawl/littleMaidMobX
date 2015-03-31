/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIWander;
/*    */ 
/*    */ public class LMM_EntityAIWander extends EntityAIWander implements LMM_IEntityAI
/*    */ {
/*    */   protected boolean isEnable;
/*    */   
/*    */   public LMM_EntityAIWander(net.minecraft.entity.EntityCreature par1EntityCreature, float par2)
/*    */   {
/* 11 */     super(par1EntityCreature, par2);
/*    */     
/* 13 */     this.isEnable = false;
/*    */   }
/*    */   
/*    */   public boolean shouldExecute()
/*    */   {
/* 18 */     return (this.isEnable) && (super.shouldExecute());
/*    */   }
/*    */   
/*    */   public void setEnable(boolean pFlag)
/*    */   {
/* 23 */     this.isEnable = pFlag;
/*    */   }
/*    */   
/*    */   public boolean getEnable()
/*    */   {
/* 28 */     return this.isEnable;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIWander.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
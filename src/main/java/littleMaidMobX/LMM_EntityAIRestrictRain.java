/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ 
/*    */ public class LMM_EntityAIRestrictRain extends net.minecraft.entity.ai.EntityAIBase implements LMM_IEntityAI
/*    */ {
/*    */   protected EntityLiving theEntity;
/*    */   protected boolean isEnable;
/*    */   
/*    */   public LMM_EntityAIRestrictRain(EntityLiving par1EntityLiving)
/*    */   {
/* 13 */     this.theEntity = par1EntityLiving;
/* 14 */     this.isEnable = false;
/*    */   }
/*    */   
/*    */   public boolean shouldExecute()
/*    */   {
/* 19 */     return (this.isEnable) && (this.theEntity.worldObj.isRaining());
/*    */   }
/*    */   
/*    */   public void startExecuting()
/*    */   {
/* 24 */     this.theEntity.getNavigator().setAvoidSun(true);
/*    */   }
/*    */   
/*    */   public void resetTask()
/*    */   {
/* 29 */     this.theEntity.getNavigator().setAvoidSun(false);
/*    */   }
/*    */   
/*    */ 
/*    */   public void setEnable(boolean pFlag)
/*    */   {
/* 35 */     this.isEnable = pFlag;
/*    */   }
/*    */   
/*    */   public boolean getEnable()
/*    */   {
/* 40 */     return this.isEnable;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIRestrictRain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
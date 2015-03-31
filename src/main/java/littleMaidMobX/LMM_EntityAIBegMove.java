/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ 
/*    */ public class LMM_EntityAIBegMove extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private LMM_EntityLittleMaid theMaid;
/*    */   private net.minecraft.entity.player.EntityPlayer thePlayer;
/*    */   private float moveSpeed;
/*    */   
/*    */   public LMM_EntityAIBegMove(LMM_EntityLittleMaid pEntityLittleMaid, float pmoveSpeed)
/*    */   {
/* 13 */     this.theMaid = pEntityLittleMaid;
/* 14 */     this.moveSpeed = pmoveSpeed;
/*    */     
/* 16 */     setMutexBits(1);
/*    */   }
/*    */   
/*    */   public boolean shouldExecute()
/*    */   {
/* 21 */     return this.theMaid.isLookSuger();
/*    */   }
/*    */   
/*    */   public void startExecuting()
/*    */   {
/* 26 */     this.thePlayer = this.theMaid.aiBeg.getPlayer();
/*    */   }
/*    */   
/*    */   public void resetTask()
/*    */   {
/* 31 */     this.thePlayer = null;
/*    */   }
/*    */   
/*    */   public boolean continueExecuting()
/*    */   {
/* 36 */     return shouldExecute();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void updateTask()
/*    */   {
/* 44 */     if ((this.theMaid.aiBeg.getDistanceSq() < 3.5D) || (this.thePlayer == null)) {
/* 45 */       this.theMaid.getNavigator().clearPathEntity();
/*    */     } else {
/* 47 */       this.theMaid.getNavigator().tryMoveToEntityLiving(this.thePlayer, this.moveSpeed);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIBegMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
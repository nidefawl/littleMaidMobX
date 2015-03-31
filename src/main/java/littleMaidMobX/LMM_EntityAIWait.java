/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAISit;
/*    */ 
/*    */ public class LMM_EntityAIWait extends EntityAISit
/*    */ {
/*    */   public LMM_EntityLittleMaid theMaid;
/*    */   
/*    */   public LMM_EntityAIWait(LMM_EntityLittleMaid pEntity)
/*    */   {
/* 11 */     super(pEntity);
/* 12 */     setMutexBits(5);
/*    */     
/* 14 */     this.theMaid = pEntity;
/*    */   }
/*    */   
/*    */   public boolean shouldExecute()
/*    */   {
/* 19 */     return (this.theMaid.isMaidWaitEx()) || ((!this.theMaid.isFreedom()) && (this.theMaid.mstatMasterEntity == null));
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIWait.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
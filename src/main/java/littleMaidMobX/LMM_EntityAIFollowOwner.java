/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ 
/*     */ public class LMM_EntityAIFollowOwner extends EntityAIBase implements LMM_IEntityAI
/*     */ {
/*     */   private LMM_EntityLittleMaid theMaid;
/*     */   private Entity theOwner;
/*     */   private net.minecraft.world.World theWorld;
/*     */   private float moveSpeed;
/*     */   private PathNavigate petPathfinder;
/*     */   private int field_48310_h;
/*     */   protected double maxDist;
/*     */   protected double minDist;
/*     */   protected double sprintDist;
/*     */   protected double toDistance;
/*     */   private boolean lastAvoidWater;
/*     */   protected boolean isEnable;
/*     */   
/*     */   public LMM_EntityAIFollowOwner(LMM_EntityLittleMaid par1EntityLittleMaid, float pSpeed, double pMin, double pMax, double pSprintDistSQ)
/*     */   {
/*  25 */     this.theMaid = par1EntityLittleMaid;
/*  26 */     this.theWorld = par1EntityLittleMaid.worldObj;
/*  27 */     this.moveSpeed = pSpeed;
/*  28 */     this.petPathfinder = par1EntityLittleMaid.getNavigator();
/*  29 */     this.minDist = pMin;
/*  30 */     this.maxDist = pMax;
/*  31 */     this.sprintDist = pSprintDistSQ;
/*  32 */     this.isEnable = true;
/*  33 */     setMutexBits(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean shouldExecute()
/*     */   {
/*  40 */     if (!this.isEnable) {
/*  41 */       return false;
/*     */     }
/*  43 */     Entity entityliving = this.theMaid.getOwner();
/*  44 */     if (entityliving == null) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     if (this.theMaid.isSitting()) {
/*  49 */       return false;
/*     */     }
/*     */     
/*  52 */     this.toDistance = this.theMaid.getDistanceSqToEntity(entityliving);
/*  53 */     if (this.toDistance < this.minDist) {
/*  54 */       return false;
/*     */     }
/*  56 */     this.theOwner = entityliving;
/*  57 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean continueExecuting()
/*     */   {
/*  65 */     this.toDistance = this.theMaid.getDistanceSqToEntity(this.theOwner);
/*  66 */     return (!this.petPathfinder.noPath()) && (this.toDistance > this.maxDist) && (!this.theMaid.isSitting());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/*  75 */     this.field_48310_h = 0;
/*  76 */     this.lastAvoidWater = this.petPathfinder.getAvoidsWater();
/*  77 */     this.petPathfinder.setAvoidsWater(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetTask()
/*     */   {
/*  84 */     this.theMaid.setSprinting(false);
/*  85 */     this.theOwner = null;
/*  86 */     this.petPathfinder.clearPathEntity();
/*  87 */     this.petPathfinder.setAvoidsWater(this.lastAvoidWater);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void updateTask()
/*     */   {
/*  94 */     this.theMaid.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, this.theMaid.getVerticalFaceSpeed());
/*     */     
/*     */ 
/*  97 */     if (this.theMaid.isSitting()) {
/*  98 */       return;
/*     */     }
/*     */     
/* 101 */     this.theMaid.setSprinting(this.toDistance > this.sprintDist);
/* 102 */     if (--this.field_48310_h > 0) {
/* 103 */       return;
/*     */     }
/*     */     
/* 106 */     this.field_48310_h = 10;
/*     */     
/* 108 */     this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.moveSpeed);
/*     */   }
/*     */   
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/* 113 */     this.isEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/* 118 */     return this.isEnable;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIFollowOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
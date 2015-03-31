/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class LMM_EntityAIHurtByTarget extends EntityAIHurtByTarget
/*     */ {
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   private boolean nearbyOnly;
/*     */   private int targetSearchStatus;
/*     */   private int targetSearchDelay;
/*     */   
/*     */   public LMM_EntityAIHurtByTarget(LMM_EntityLittleMaid par1EntityLiving, boolean par2)
/*     */   {
/*  19 */     super(par1EntityLiving, par2);
/*     */     
/*  21 */     this.theMaid = par1EntityLiving;
/*  22 */     this.nearbyOnly = false;
/*  23 */     this.targetSearchStatus = 0;
/*  24 */     this.targetSearchDelay = 0;
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  29 */     if ((this.theMaid.isContract()) && (!this.theMaid.isBlocking()) && (this.theMaid.mstatMasterEntity != null))
/*     */     {
/*  31 */       EntityLivingBase lentity = this.theMaid.mstatMasterEntity.getAITarget();
/*  32 */       if (isSuitableTarget(lentity, false)) {
/*  33 */         this.theMaid.setRevengeTarget(lentity);
/*  34 */         return true;
/*     */       }
/*     */     }
/*  37 */     return super.shouldExecute();
/*     */   }
/*     */   
/*     */   public void startExecuting()
/*     */   {
/*  42 */     super.startExecuting();
/*     */   }
/*     */   
/*     */   public void updateTask()
/*     */   {
/*  47 */     super.updateTask();
/*  48 */     String s1 = this.taskOwner.getAITarget() == null ? "Null" : this.taskOwner.getAITarget().getClass().toString();
/*  49 */     String s2 = this.taskOwner.getAttackTarget() == null ? "Null" : this.taskOwner.getAttackTarget().getClass().toString();
/*     */     
/*     */ 
/*     */ 
/*  53 */     EntityLivingBase leliving = this.taskOwner.getAITarget();
/*  54 */     if ((leliving != null) && (leliving != this.taskOwner.getAttackTarget())) {
/*  55 */       this.taskOwner.setAttackTarget(null);
/*  56 */       System.out.println(String.format("ID:%d, ChangeTarget.", new Object[] { Integer.valueOf(this.taskOwner.getEntityId()) }));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
/*     */   {
/*  64 */     if (par1EntityLiving == null) {
/*  65 */       return false;
/*     */     }
/*  67 */     if (par1EntityLiving == this.taskOwner) {
/*  68 */       return false;
/*     */     }
/*  70 */     if (par1EntityLiving == this.theMaid.mstatMasterEntity) {
/*  71 */       return false;
/*     */     }
/*  73 */     if (!par1EntityLiving.isEntityAlive()) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     LMM_EntityModeBase lailm = this.theMaid.getActiveModeClass();
/*  78 */     if ((lailm != null) && (lailm.isSearchEntity())) {
/*  79 */       if (!lailm.checkEntity(this.theMaid.getMaidModeInt(), par1EntityLiving)) {
/*  80 */         return false;
/*     */       }
/*     */     }
/*  83 */     else if (this.theMaid.getIFF(par1EntityLiving)) {
/*  84 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  89 */     if (!this.taskOwner.isWithinHomeDistance(MathHelper.floor_double(par1EntityLiving.posX), MathHelper.floor_double(par1EntityLiving.posY), MathHelper.floor_double(par1EntityLiving.posZ))) {
/*  90 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  94 */     if ((this.shouldCheckSight) && (!this.taskOwner.getEntitySenses().canSee(par1EntityLiving))) {
/*  95 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  99 */     if (this.nearbyOnly) {
/* 100 */       if (--this.targetSearchDelay <= 0) {
/* 101 */         this.targetSearchStatus = 0;
/*     */       }
/*     */       
/* 104 */       if (this.targetSearchStatus == 0) {
/* 105 */         this.targetSearchStatus = (canEasilyReach(par1EntityLiving) ? 1 : 2);
/*     */       }
/*     */       
/* 108 */       if (this.targetSearchStatus == 2) {
/* 109 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean canEasilyReach(Entity par1EntityLiving) {
/* 117 */     this.targetSearchDelay = (10 + this.taskOwner.getRNG().nextInt(5));
/* 118 */     net.minecraft.pathfinding.PathEntity var2 = this.taskOwner.getNavigator().getPathToXYZ(par1EntityLiving.posX, par1EntityLiving.posY, par1EntityLiving.posZ);
/*     */     
/*     */ 
/* 121 */     if (var2 == null) {
/* 122 */       return false;
/*     */     }
/* 124 */     PathPoint var3 = var2.getFinalPathPoint();
/*     */     
/* 126 */     if (var3 == null) {
/* 127 */       return false;
/*     */     }
/* 129 */     int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
/* 130 */     int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
/* 131 */     return var4 * var4 + var5 * var5 <= 2.25D;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIHurtByTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
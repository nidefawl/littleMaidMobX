/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ public class LMM_EntityAIAvoidPlayer
/*     */   extends EntityAIBase
/*     */   implements LMM_IEntityAI
/*     */ {
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected EntityPlayer theMaster;
/*     */   protected float speedNormal;
/*     */   protected PathEntity avoidPath;
/*     */   protected PathNavigate entityPathNavigate;
/*     */   protected boolean isEnable;
/*     */   public boolean isActive;
/*     */   public int minDist;
/*     */   
/*     */   public LMM_EntityAIAvoidPlayer(LMM_EntityLittleMaid pEntityLittleMaid, float pSpeed, int pMinDist)
/*     */   {
/*  27 */     this.theMaid = pEntityLittleMaid;
/*  28 */     this.speedNormal = pSpeed;
/*  29 */     this.entityPathNavigate = pEntityLittleMaid.getNavigator();
/*  30 */     this.isActive = false;
/*  31 */     this.isEnable = false;
/*  32 */     this.minDist = pMinDist;
/*  33 */     setMutexBits(1);
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  38 */     if ((!this.isEnable) || (!this.isActive) || (!this.theMaid.isContract())) {
/*  39 */       this.isActive = false;
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     this.theMaster = this.theMaid.mstatMasterEntity;
/*     */     
/*     */ 
/*     */ 
/*  47 */     if (this.theMaster == null)
/*     */     {
/*  49 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  53 */     if (!this.theMaid.getEntitySenses().canSee(this.theMaster)) {
/*  54 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  58 */     Vec3 vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theMaid, this.minDist, 7, Vec3.createVectorHelper(this.theMaster.posX, this.theMaster.posY, this.theMaster.posZ));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  63 */     if (vec3d == null) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     if (this.theMaster.getDistanceSq(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord) < this.theMaid.mstatMasterDistanceSq) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     this.avoidPath = this.entityPathNavigate.getPathToXYZ(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
/*     */     
/*  73 */     if (this.avoidPath == null) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     return this.avoidPath.isDestinationSame(vec3d);
/*     */   }
/*     */   
/*     */   public boolean continueExecuting()
/*     */   {
/*  82 */     if (this.theMaster == null) return false;
/*  83 */     return (!this.entityPathNavigate.noPath()) && (this.theMaid.getDistanceSqToEntity(this.theMaster) < 144.0D);
/*     */   }
/*     */   
/*     */   public void startExecuting()
/*     */   {
/*  88 */     this.entityPathNavigate.setPath(this.avoidPath, this.speedNormal);
/*     */   }
/*     */   
/*     */   public void resetTask()
/*     */   {
/*  93 */     this.isActive = false;
/*     */   }
/*     */   
/*     */   public void setActive()
/*     */   {
/*  98 */     this.isActive = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/* 104 */     this.isEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/* 109 */     return this.isEnable;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIAvoidPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
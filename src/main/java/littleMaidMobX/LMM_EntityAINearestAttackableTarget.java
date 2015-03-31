/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class LMM_EntityAINearestAttackableTarget extends EntityAINearestAttackableTarget
/*     */ {
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected Entity targetEntity;
/*     */   protected Class targetClass;
/*     */   protected int targetChance;
/*     */   protected LMM_EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
/*     */   private boolean fretarget;
/*     */   private int fcanAttack;
/*     */   private int fretryCounter;
/*     */   
/*     */   public LMM_EntityAINearestAttackableTarget(LMM_EntityLittleMaid par1EntityLiving, Class par2Class, int par4, boolean par5)
/*     */   {
/*  27 */     this(par1EntityLiving, par2Class, par4, par5, false);
/*     */   }
/*     */   
/*     */   public LMM_EntityAINearestAttackableTarget(LMM_EntityLittleMaid par1, Class par2, int par4, boolean par5, boolean par6) {
/*  31 */     super(par1, par2, par4, par5, par6, null);
/*  32 */     this.targetClass = par2;
/*  33 */     this.targetChance = par4;
/*  34 */     this.theNearestAttackableTargetSorter = new LMM_EntityAINearestAttackableTargetSorter(par1);
/*  35 */     this.fretarget = par6;
/*  36 */     this.theMaid = par1;
/*     */     
/*  38 */     setMutexBits(1);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldExecute()
/*     */   {
/*  44 */     if ((this.targetChance > 0) && (this.taskOwner.getRNG().nextInt(this.targetChance) != 0)) {
/*  45 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  49 */     double lfollowRange = getTargetDistance();
/*  50 */     List llist = this.taskOwner.worldObj.getEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(lfollowRange, 8.0D, lfollowRange));
/*  51 */     if ((this.theMaid.mstatMasterEntity != null) && (!this.theMaid.isBloodsuck()))
/*     */     {
/*  53 */       this.theNearestAttackableTargetSorter.setEntity(this.theMaid.mstatMasterEntity);
/*     */     }
/*     */     else {
/*  56 */       this.theNearestAttackableTargetSorter.setEntity(this.theMaid);
/*     */     }
/*  58 */     java.util.Collections.sort(llist, this.theNearestAttackableTargetSorter);
/*  59 */     Iterator var2 = llist.iterator();
/*  60 */     while (var2.hasNext()) {
/*  61 */       Entity var3 = (Entity)var2.next();
/*  62 */       if ((var3.isEntityAlive()) && (isSuitableTargetLM(var3, false))) {
/*  63 */         this.targetEntity = var3;
/*  64 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  68 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/*  74 */     super.startExecuting();
/*  75 */     if ((this.targetEntity instanceof EntityLivingBase)) {
/*  76 */       this.theMaid.setAttackTarget((EntityLivingBase)this.targetEntity);
/*     */     } else {
/*  78 */       this.theMaid.setTarget(this.targetEntity);
/*     */     }
/*  80 */     this.fcanAttack = 0;
/*  81 */     this.fretryCounter = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean isSuitableTargetLM(Entity pTarget, boolean par2)
/*     */   {
/*  88 */     if (pTarget == null) {
/*  89 */       return false;
/*     */     }
/*     */     
/*  92 */     if (pTarget == this.taskOwner) {
/*  93 */       return false;
/*     */     }
/*  95 */     if (pTarget == this.theMaid.mstatMasterEntity) {
/*  96 */       return false;
/*     */     }
/*     */     
/*  99 */     if (!pTarget.isEntityAlive()) {
/* 100 */       return false;
/*     */     }
/*     */     
/* 103 */     LMM_EntityModeBase lailm = this.theMaid.getActiveModeClass();
/* 104 */     if ((lailm != null) && (lailm.isSearchEntity())) {
/* 105 */       if (!lailm.checkEntity(this.theMaid.getMaidModeInt(), pTarget)) {
/* 106 */         return false;
/*     */       }
/*     */     }
/* 109 */     else if (this.theMaid.getIFF(pTarget)) {
/* 110 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     if ((this.shouldCheckSight) && (!this.taskOwner.getEntitySenses().canSee(pTarget))) {
/* 122 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 126 */     if (this.fretarget) {
/* 127 */       if (--this.fretryCounter <= 0) {
/* 128 */         this.fcanAttack = 0;
/*     */       }
/*     */       
/* 131 */       if (this.fcanAttack == 0) {
/* 132 */         this.fcanAttack = (canEasilyReach(pTarget) ? 1 : 2);
/*     */       }
/*     */       
/* 135 */       if (this.fcanAttack == 2) {
/* 136 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean canEasilyReach(Entity par1EntityLiving)
/*     */   {
/* 145 */     this.fretryCounter = (10 + this.taskOwner.getRNG().nextInt(5));
/* 146 */     PathEntity var2 = this.taskOwner.getNavigator().getPathToXYZ(par1EntityLiving.posX, par1EntityLiving.posY, par1EntityLiving.posZ);
/*     */     
/*     */ 
/* 149 */     if (var2 == null) {
/* 150 */       return false;
/*     */     }
/* 152 */     PathPoint var3 = var2.getFinalPathPoint();
/*     */     
/* 154 */     if (var3 == null) {
/* 155 */       return false;
/*     */     }
/* 157 */     int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
/* 158 */     int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
/* 159 */     return var4 * var4 + var5 * var5 <= 2.25D;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAINearestAttackableTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
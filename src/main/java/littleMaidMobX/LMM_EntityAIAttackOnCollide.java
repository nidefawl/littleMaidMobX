/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityAIAttackOnCollide extends net.minecraft.entity.ai.EntityAIBase implements LMM_IEntityAI
/*     */ {
/*     */   protected boolean fEnable;
/*     */   protected World worldObj;
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected Entity entityTarget;
/*     */   protected float moveSpeed;
/*     */   protected boolean isReroute;
/*     */   protected PathEntity pathToTarget;
/*     */   protected int rerouteTimer;
/*     */   protected double attackRange;
/*     */   public boolean isGuard;
/*     */   
/*     */   public LMM_EntityAIAttackOnCollide(LMM_EntityLittleMaid par1EntityLittleMaid, float par2, boolean par3)
/*     */   {
/*  30 */     this.theMaid = par1EntityLittleMaid;
/*  31 */     this.worldObj = par1EntityLittleMaid.worldObj;
/*  32 */     this.moveSpeed = par2;
/*  33 */     this.isReroute = par3;
/*  34 */     this.isGuard = false;
/*  35 */     setMutexBits(3);
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  40 */     if (!this.fEnable) {
/*  41 */       return false;
/*     */     }
/*  43 */     Entity lentity = this.theMaid.getAttackTarget();
/*  44 */     if (lentity == null) {
/*  45 */       lentity = this.theMaid.getEntityToAttack();
/*  46 */       if (lentity == null) {
/*  47 */         return false;
/*     */       }
/*     */     }
/*     */     
/*  51 */     this.entityTarget = lentity;
/*  52 */     this.pathToTarget = this.theMaid.getNavigator().getPathToXYZ(this.entityTarget.posX, this.entityTarget.posY, this.entityTarget.posZ);
/*     */     
/*  54 */     this.attackRange = (this.theMaid.width + this.entityTarget.width + 0.4D);
/*  55 */     this.attackRange *= this.attackRange;
/*     */     
/*  57 */     if ((this.pathToTarget != null) || (this.theMaid.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= this.attackRange)) {
/*  58 */       return true;
/*     */     }
/*  60 */     this.theMaid.setAttackTarget(null);
/*  61 */     this.theMaid.setTarget(null);
/*  62 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/*  69 */     this.theMaid.getNavigator().setPath(this.pathToTarget, this.moveSpeed);
/*  70 */     this.rerouteTimer = 0;
/*  71 */     this.theMaid.playSound(this.theMaid.isBloodsuck() ? LMM_EnumSound.findTarget_B : LMM_EnumSound.findTarget_N, false);
/*  72 */     this.theMaid.maidAvatar.stopUsingItem();
/*     */   }
/*     */   
/*     */   public boolean continueExecuting()
/*     */   {
/*  77 */     Entity lentity = this.theMaid.getAttackTarget();
/*  78 */     if (lentity == null) {
/*  79 */       lentity = this.theMaid.getEntityToAttack();
/*     */     }
/*  81 */     if ((lentity == null) || (this.entityTarget != lentity)) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     if (this.entityTarget.isDead) {
/*  86 */       this.theMaid.setAttackTarget(null);
/*  87 */       this.theMaid.setTarget(null);
/*  88 */       this.theMaid.getNavigator().clearPathEntity();
/*  89 */       return false;
/*     */     }
/*     */     
/*  92 */     if (!this.entityTarget.isEntityAlive()) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     if (!this.isReroute) {
/*  97 */       return !this.theMaid.getNavigator().noPath();
/*     */     }
/*     */     
/* 100 */     return this.theMaid.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ));
/*     */   }
/*     */   
/*     */   public void resetTask()
/*     */   {
/* 105 */     this.entityTarget = null;
/*     */     
/* 107 */     this.theMaid.maidAvatar.stopUsingItem();
/*     */   }
/*     */   
/*     */   public void updateTask()
/*     */   {
/* 112 */     this.theMaid.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 119 */     if (--this.rerouteTimer <= 0) {
/* 120 */       if (this.isReroute)
/*     */       {
/* 122 */         this.rerouteTimer = (4 + this.theMaid.getRNG().nextInt(7));
/* 123 */         this.theMaid.getNavigator().tryMoveToXYZ(this.entityTarget.posX, this.entityTarget.posY, this.entityTarget.posZ, this.moveSpeed);
/*     */       }
/* 125 */       if (this.theMaid.getEntitySenses().canSee(this.entityTarget))
/*     */       {
/* 127 */         this.rerouteTimer = (4 + this.theMaid.getRNG().nextInt(7));
/* 128 */         this.theMaid.getNavigator().tryMoveToXYZ(this.entityTarget.posX, this.entityTarget.posY, this.entityTarget.posZ, this.moveSpeed);
/*     */       } else {
/* 130 */         this.theMaid.setAttackTarget(null);
/* 131 */         this.theMaid.setTarget(null);
/*     */       }
/*     */     }
/*     */     
/* 135 */     boolean lguard = false;
/* 136 */     if (this.theMaid.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) > this.attackRange) {
/* 137 */       if ((this.isGuard) && (this.theMaid.isMaskedMaid())) {
/* 138 */         EntityLivingBase lel = null;
/* 139 */         if ((this.entityTarget instanceof EntityCreature)) {
/* 140 */           lel = ((EntityCreature)this.entityTarget).getAttackTarget();
/*     */         }
/* 142 */         else if ((this.entityTarget instanceof EntityLivingBase)) {
/* 143 */           lel = ((EntityLivingBase)this.entityTarget).getAITarget();
/*     */         }
/* 145 */         if (lel == this.theMaid) {
/* 146 */           ItemStack li = this.theMaid.getCurrentEquippedItem();
/* 147 */           if ((li != null) && (li.getItemUseAction() == net.minecraft.item.EnumAction.block)) {
/* 148 */             li.useItemRightClick(this.worldObj, this.theMaid.maidAvatar);
/* 149 */             lguard = true;
/*     */           }
/*     */         }
/*     */       }
/* 153 */       return;
/*     */     }
/* 155 */     if ((this.theMaid.maidAvatar.isUsingItem()) && (!lguard)) {
/* 156 */       this.theMaid.maidAvatar.stopUsingItem();
/*     */     }
/*     */     
/* 159 */     if (!this.theMaid.getSwingStatusDominant().canAttack()) {
/* 160 */       return;
/*     */     }
/*     */     
/* 163 */     double tdx = this.entityTarget.posX - this.theMaid.posX;
/* 164 */     double tdz = this.entityTarget.posZ - this.theMaid.posZ;
/* 165 */     double vdx = -Math.sin(this.theMaid.renderYawOffset * 3.1415927F / 180.0F);
/* 166 */     double vdz = Math.cos(this.theMaid.renderYawOffset * 3.1415927F / 180.0F);
/* 167 */     double ld = (tdx * vdx + tdz * vdz) / (Math.sqrt(tdx * tdx + tdz * tdz) * Math.sqrt(vdx * vdx + vdz * vdz));
/*     */     
/* 169 */     if (ld < -0.35D) {
/* 170 */       return;
/*     */     }
/*     */     
/*     */ 
/* 174 */     this.theMaid.attackEntityAsMob(this.entityTarget);
/* 175 */     if (this.theMaid.getActiveModeClass().isChangeTartget(this.entityTarget))
/*     */     {
/* 177 */       this.theMaid.setAttackTarget(null);
/* 178 */       this.theMaid.setTarget(null);
/* 179 */       this.theMaid.getNavigator().clearPathEntity();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/* 187 */     this.fEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/* 192 */     return this.fEnable;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIAttackOnCollide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityAIBeg extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   protected LMM_EntityLittleMaid littleMaid;
/*     */   protected EntityPlayer targetPlayer;
/*     */   protected double targetRangeSq;
/*     */   protected World worldObj;
/*     */   protected float effectiveRange;
/*     */   protected double effectiveRangeSq;
/*     */   protected int field_48347_e;
/*     */   
/*     */   public LMM_EntityAIBeg(LMM_EntityLittleMaid pentityLittlemaid, float par2)
/*     */   {
/*  19 */     this.littleMaid = pentityLittlemaid;
/*  20 */     this.worldObj = pentityLittlemaid.worldObj;
/*  21 */     this.effectiveRange = par2;
/*  22 */     this.effectiveRangeSq = (par2 * par2);
/*  23 */     setMutexBits(2);
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  28 */     if (this.littleMaid.isContract()) {
/*  29 */       this.targetPlayer = (this.littleMaid.mstatMasterDistanceSq > this.effectiveRangeSq ? null : this.littleMaid.mstatMasterEntity);
/*     */     }
/*     */     else {
/*  32 */       this.targetPlayer = this.worldObj.getClosestPlayerToEntity(this.littleMaid, this.effectiveRange);
/*     */     }
/*     */     
/*  35 */     if (this.targetPlayer == null) {
/*  36 */       return false;
/*     */     }
/*  38 */     return checkItem(this.targetPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean continueExecuting()
/*     */   {
/*  45 */     if ((this.targetPlayer == null) || (!this.targetPlayer.isEntityAlive())) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     if (this.littleMaid.isContract()) {
/*  50 */       this.targetRangeSq = this.littleMaid.mstatMasterDistanceSq;
/*     */     } else {
/*  52 */       this.targetRangeSq = this.littleMaid.getDistanceSqToEntity(this.targetPlayer);
/*     */     }
/*     */     
/*  55 */     if (this.targetRangeSq > this.effectiveRangeSq) {
/*  56 */       return false;
/*     */     }
/*  58 */     return (this.field_48347_e > 0) && (checkItem(this.targetPlayer));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/*  65 */     this.field_48347_e = (40 + this.littleMaid.getRNG().nextInt(40));
/*  66 */     this.littleMaid.setLookSuger(true);
/*     */   }
/*     */   
/*     */   public void resetTask()
/*     */   {
/*  71 */     this.littleMaid.setLooksWithInterest(false);
/*     */     
/*  73 */     this.littleMaid.setLookSuger(false);
/*     */   }
/*     */   
/*     */ 
/*     */   public void updateTask()
/*     */   {
/*  79 */     this.littleMaid.getLookHelper().setLookPositionWithEntity(this.targetPlayer, 10.0F, this.littleMaid.getVerticalFaceSpeed());
/*     */     
/*  81 */     if (this.littleMaid.getNavigator().noPath()) {
/*  82 */       this.littleMaid.setLooksWithInterest(true);
/*     */     } else {
/*  84 */       this.littleMaid.setLooksWithInterest(false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean checkItem(EntityPlayer par1EntityPlayer)
/*     */   {
/*  91 */     net.minecraft.item.ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
/*     */     
/*  93 */     if (itemstack == null) {
/*  94 */       return false;
/*     */     }
/*  96 */     return this.littleMaid.isBreedingItem(itemstack);
/*     */   }
/*     */   
/*     */   public EntityPlayer getPlayer() {
/* 100 */     return this.targetPlayer;
/*     */   }
/*     */   
/*     */   public double getDistanceSq() {
/* 104 */     return this.targetRangeSq;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIBeg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
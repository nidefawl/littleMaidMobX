/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityAICollectItem extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected float moveSpeed;
/*     */   protected EntityItem targetItem;
/*     */   protected boolean lastAvoidWater;
/*     */   
/*     */   public LMM_EntityAICollectItem(LMM_EntityLittleMaid pEntityLittleMaid, float pmoveSpeed)
/*     */   {
/*  25 */     this.theMaid = pEntityLittleMaid;
/*  26 */     this.moveSpeed = pmoveSpeed;
/*  27 */     setMutexBits(3);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldExecute()
/*     */   {
/*  33 */     if (this.theMaid.maidInventory.getFirstEmptyStack() > -1) {
/*  34 */       List llist = this.theMaid.worldObj.getEntitiesWithinAABB(EntityItem.class, this.theMaid.boundingBox.expand(8.0D, 2.0D, 8.0D));
/*  35 */       if (!llist.isEmpty()) {
/*  36 */         int li = this.theMaid.getRNG().nextInt(llist.size());
/*  37 */         EntityItem ei = (EntityItem)llist.get(li);
/*  38 */         EntityPlayer ep = this.theMaid.mstatMasterEntity != null ? this.theMaid.mstatMasterEntity : this.theMaid.worldObj.getClosestPlayerToEntity(this.theMaid, 16.0D);
/*     */         
/*  40 */         if ((!ei.isDead) && (ei.onGround) && (ei.delayBeforeCanPickup <= 0) && (!ei.isBurning()) && (canEntityItemBeSeen(ei)) && ((ep == null) || (ep.getDistanceSq(ei.posX + MathHelper.sin(ep.rotationYaw * 0.017453292F) * 2.0D, ei.posY, ei.posZ - MathHelper.cos(ep.rotationYaw * 0.017453292F) * 2.0D) > 7.5D)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  47 */           ItemStack lstack = ei.getEntityItem();
/*  48 */           if (lstack.getItem() != net.minecraft.init.Items.sugar) {
/*  49 */             if (this.theMaid.maidActiveModeClass == null) {
/*  50 */               return false;
/*     */             }
/*  52 */             if (!this.theMaid.maidActiveModeClass.checkItemStack(lstack)) {
/*  53 */               return false;
/*     */             }
/*     */           }
/*  56 */           this.theMaid.playSound(LMM_EnumSound.findTarget_I, false);
/*  57 */           this.targetItem = ei;
/*  58 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public void startExecuting()
/*     */   {
/*  68 */     this.lastAvoidWater = this.theMaid.getNavigator().getAvoidsWater();
/*  69 */     this.theMaid.getNavigator().setAvoidsWater(true);
/*     */   }
/*     */   
/*     */   public boolean continueExecuting()
/*     */   {
/*  74 */     return (!this.targetItem.isDead) && (this.theMaid.maidInventory.getFirstEmptyStack() > -1) && (this.theMaid.getDistanceSqToEntity(this.targetItem) < 100.0D);
/*     */   }
/*     */   
/*     */   public void resetTask()
/*     */   {
/*  79 */     this.targetItem = null;
/*  80 */     this.theMaid.getNavigator().clearPathEntity();
/*  81 */     this.theMaid.getNavigator().setAvoidsWater(this.lastAvoidWater);
/*     */   }
/*     */   
/*     */   public void updateTask()
/*     */   {
/*  86 */     this.theMaid.getLookHelper().setLookPositionWithEntity(this.targetItem, 30.0F, this.theMaid.getVerticalFaceSpeed());
/*     */     
/*  88 */     PathNavigate lnavigater = this.theMaid.getNavigator();
/*  89 */     if (lnavigater.noPath()) {
/*  90 */       if (this.targetItem.isInWater()) {
/*  91 */         lnavigater.setAvoidsWater(false);
/*     */       }
/*  93 */       net.minecraft.pathfinding.PathEntity lpath = lnavigater.getPathToXYZ(this.targetItem.posX, this.targetItem.posY, this.targetItem.posZ);
/*  94 */       lnavigater.setPath(lpath, this.moveSpeed);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canEntityItemBeSeen(Entity entity)
/*     */   {
/* 100 */     return this.theMaid.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.theMaid.posX, this.theMaid.posY + this.theMaid.getEyeHeight(), this.theMaid.posZ), Vec3.createVectorHelper(entity.posX, entity.posY + (entity.boundingBox.minY - entity.boundingBox.minY) / 2.0D, entity.posZ)) == null;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAICollectItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
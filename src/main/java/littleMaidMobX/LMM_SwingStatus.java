/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_SwingStatus
/*     */ {
/*     */   public int index;
/*     */   public int lastIndex;
/*     */   public boolean isSwingInProgress;
/*     */   public float swingProgress;
/*     */   public float prevSwingProgress;
/*     */   public int swingProgressInt;
/*     */   public float onGround;
/*     */   public int attackTime;
/*     */   public int itemInUseCount;
/*     */   protected ItemStack itemInUse;
/*     */   
/*     */   public LMM_SwingStatus()
/*     */   {
/*  32 */     this.index = (this.lastIndex = -1);
/*  33 */     this.isSwingInProgress = false;
/*  34 */     this.swingProgress = (this.prevSwingProgress = 0.0F);
/*  35 */     this.onGround = 0.0F;
/*  36 */     this.attackTime = 0;
/*  37 */     this.itemInUseCount = 0;
/*  38 */     this.itemInUse = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onEntityUpdate(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  45 */     this.prevSwingProgress = this.swingProgress;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUpdate(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  52 */     this.prevSwingProgress = this.swingProgress;
/*  53 */     if (this.attackTime > 0) {
/*  54 */       this.attackTime -= 1;
/*     */     }
/*     */     
/*     */ 
/*  58 */     int li = pEntity.getSwingSpeedModifier();
/*  59 */     if (this.isSwingInProgress) {
/*  60 */       this.swingProgressInt += 1;
/*  61 */       if (this.swingProgressInt >= li) {
/*  62 */         this.swingProgressInt = 0;
/*  63 */         this.isSwingInProgress = false;
/*     */       }
/*     */     } else {
/*  66 */       this.swingProgressInt = 0;
/*     */     }
/*  68 */     this.swingProgress = (this.swingProgressInt / li);
/*     */     
/*  70 */     if (isUsingItem()) {
/*  71 */       ItemStack itemstack = pEntity.maidInventory.getStackInSlot(this.index);
/*  72 */       Entity lrentity = pEntity.worldObj.isRemote ? null : pEntity;
/*     */       
/*  74 */       if (itemstack != this.itemInUse) {
/*  75 */         clearItemInUse(lrentity);
/*     */       } else {
/*  77 */         if ((this.itemInUseCount <= 25) && (this.itemInUseCount % 4 == 0))
/*     */         {
/*  79 */           updateItemUse(pEntity, 5);
/*     */         }
/*  81 */         if ((--this.itemInUseCount <= 0) && (lrentity != null)) {
/*  82 */           onItemUseFinish(pEntity.maidAvatar);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSlotIndex(int pIndex)
/*     */   {
/*  92 */     this.index = pIndex;
/*  93 */     this.lastIndex = -2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack getItemStack(LMM_EntityLittleMaid pEntity)
/*     */   {
/* 100 */     if (this.index > -1) {
/* 101 */       return pEntity.maidInventory.getStackInSlot(this.index);
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canAttack()
/*     */   {
/* 108 */     return this.attackTime <= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getSwingProgress(float ltime)
/*     */   {
/* 117 */     float lf = this.swingProgress - this.prevSwingProgress;
/*     */     
/* 119 */     if (lf < 0.0F) {
/* 120 */       lf += 1.0F;
/*     */     }
/*     */     
/* 123 */     return this.onGround = this.prevSwingProgress + lf * ltime;
/*     */   }
/*     */   
/*     */   public boolean setSwinging() {
/* 127 */     if ((!this.isSwingInProgress) || (this.swingProgressInt < 0)) {
/* 128 */       this.swingProgressInt = -1;
/* 129 */       this.isSwingInProgress = true;
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkChanged()
/*     */   {
/* 140 */     boolean lflag = this.index != this.lastIndex;
/* 141 */     this.lastIndex = this.index;
/* 142 */     return lflag;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getItemInUse()
/*     */   {
/* 148 */     return this.itemInUse;
/*     */   }
/*     */   
/*     */   public int getItemInUseCount() {
/* 152 */     return this.itemInUseCount;
/*     */   }
/*     */   
/*     */   public boolean isUsingItem() {
/* 156 */     return this.itemInUse != null;
/*     */   }
/*     */   
/*     */   public int getItemInUseDuration() {
/* 160 */     return isUsingItem() ? this.itemInUse.getMaxItemUseDuration() - this.itemInUseCount : 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void stopUsingItem(Entity pEntity)
/*     */   {
/* 169 */     if ((this.itemInUse != null) && ((pEntity instanceof EntityPlayer))) {
/* 170 */       this.itemInUse.onPlayerStoppedUsing(pEntity.worldObj, (EntityPlayer)pEntity, this.itemInUseCount);
/*     */     }
/*     */     
/* 173 */     clearItemInUse(pEntity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clearItemInUse(Entity pEntity)
/*     */   {
/* 182 */     this.itemInUse = null;
/* 183 */     this.itemInUseCount = 0;
/*     */     
/* 185 */     if (pEntity != null) {
/* 186 */       pEntity.setEating(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isBlocking() {
/* 191 */     return (isUsingItem()) && (this.itemInUse.getItemUseAction() == EnumAction.block);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItemInUse(ItemStack par1ItemStack, int par2, Entity pEntity)
/*     */   {
/* 202 */     if (par1ItemStack != this.itemInUse) {
/* 203 */       this.itemInUse = par1ItemStack;
/* 204 */       this.itemInUseCount = par2;
/*     */       
/* 206 */       if (pEntity != null) {
/* 207 */         pEntity.setEating(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void updateItemUse(Entity pEntity, int par2) {
/* 213 */     if (this.itemInUse.getItemUseAction() == EnumAction.drink) {
/* 214 */       pEntity.playSound("random.drink", 0.5F, pEntity.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */     }
/*     */     
/* 217 */     Random rand = new Random();
/*     */     
/* 219 */     if (this.itemInUse.getItemUseAction() == EnumAction.eat) {
/* 220 */       for (int var3 = 0; var3 < par2; var3++) {
/* 221 */         Vec3 var4 = Vec3.createVectorHelper((rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 222 */         var4.rotateAroundX(-pEntity.rotationPitch * 3.1415927F / 180.0F);
/* 223 */         var4.rotateAroundY(-pEntity.rotationYaw * 3.1415927F / 180.0F);
/* 224 */         Vec3 var5 = Vec3.createVectorHelper((rand.nextFloat() - 0.5D) * 0.3D, -rand.nextFloat() * 0.6D - 0.3D, 0.6D);
/* 225 */         var5.rotateAroundX(-pEntity.rotationPitch * 3.1415927F / 180.0F);
/* 226 */         var5.rotateAroundY(-pEntity.rotationYaw * 3.1415927F / 180.0F);
/* 227 */         var5 = var5.addVector(pEntity.posX, pEntity.posY + pEntity.getEyeHeight(), pEntity.posZ);
/* 228 */         pEntity.worldObj.spawnParticle("iconcrack_" + this.itemInUse.getItem(), var5.xCoord, var5.yCoord, var5.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord);
/*     */       }
/*     */       
/* 231 */       pEntity.playSound("random.eat", 0.5F + 0.5F * rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void onItemUseFinish(EntityPlayer pEntityPlayer) {
/* 236 */     if (this.itemInUse != null) {
/* 237 */       updateItemUse(pEntityPlayer, 16);
/* 238 */       int var1 = this.itemInUse.stackSize;
/* 239 */       ItemStack var2 = this.itemInUse.onFoodEaten(pEntityPlayer.worldObj, pEntityPlayer);
/*     */       
/* 241 */       if ((var2 != this.itemInUse) || ((var2 != null) && (var2.stackSize != var1))) {
/* 242 */         if (var2.stackSize == 0) {
/* 243 */           pEntityPlayer.inventory.setInventorySlotContents(this.index, null);
/*     */         } else {
/* 245 */           pEntityPlayer.inventory.setInventorySlotContents(this.index, var2);
/*     */         }
/*     */       }
/*     */       
/* 249 */       clearItemInUse(pEntityPlayer);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_SwingStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
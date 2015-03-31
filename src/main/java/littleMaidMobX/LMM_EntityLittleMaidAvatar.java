/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import wrapper.W_Common;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_EntityLittleMaidAvatar
/*     */   extends EntityPlayer
/*     */ {
/*     */   public LMM_EntityLittleMaid avatar;
/*     */   public boolean isItemTrigger;
/*     */   public boolean isItemReload;
/*     */   private boolean isItemPreReload;
/*     */   private double appendX;
/*     */   private double appendY;
/*     */   private double appendZ;
/*     */   
/*     */   public LMM_EntityLittleMaidAvatar(World par1World)
/*     */   {
/*  40 */     super(par1World, W_Common.newGameProfile("1", "LMM_EntityLittleMaidAvatar"));
/*     */   }
/*     */   
/*     */   public LMM_EntityLittleMaidAvatar(World par1World, LMM_EntityLittleMaid par2EntityLittleMaid) {
/*  44 */     super(par1World, W_Common.newGameProfile("1", "LMM_EntityLittleMaidAvatar"));
/*     */     
/*     */ 
/*  47 */     this.avatar = par2EntityLittleMaid;
/*  48 */     this.dataWatcher = this.avatar.getDataWatcher();
/*     */     
/*  50 */     this.dataWatcher.addObject(28, Float.valueOf(0.0F));
/*     */     
/*  52 */     this.inventory = this.avatar.maidInventory;
/*  53 */     this.inventory.player = this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void entityInit()
/*     */   {
/*  60 */     super.entityInit();
/*     */   }
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound var1) {
/*  64 */     super.readEntityFromNBT(var1);
/*     */   }
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound var1) {
/*  68 */     super.writeEntityToNBT(var1);
/*     */   }
/*     */   
/*     */   public ItemStack getHeldItem() {
/*  72 */     return super.getHeldItem();
/*     */   }
/*     */   
/*     */ 
/*  76 */   public void setCurrentItemOrArmor(int var1, ItemStack var2) { super.setCurrentItemOrArmor(var1, var2); }
/*     */   
/*  78 */   public String getCommandSenderName() { return super.getCommandSenderName(); }
/*  79 */   public World getEntityWorld() { return super.getEntityWorld(); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void applyEntityAttributes()
/*     */   {
/*  86 */     super.applyEntityAttributes();
/*     */   }
/*     */   
/*     */ 
/*     */   public float getEyeHeight()
/*     */   {
/*  92 */     return this.avatar.getEyeHeight();
/*     */   }
/*     */   
/*     */   protected String getHurtSound()
/*     */   {
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   protected String getDeathSound()
/*     */   {
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int var1, String var2)
/*     */   {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addStat(StatBase par1StatBase, int par2) {}
/*     */   
/*     */ 
/*     */   public void addScore(int par1) {}
/*     */   
/*     */ 
/*     */   public void onUpdate()
/*     */   {
/* 119 */     EntityPlayer lep = this.avatar.getMaidMasterEntity();
/* 120 */     setEntityId(this.avatar.getEntityId());
/*     */     
/* 122 */     if (lep != null) {
/* 123 */       this.capabilities.isCreativeMode = lep.capabilities.isCreativeMode;
/*     */     }
/*     */     
/* 126 */     if (this.xpCooldown > 0) {
/* 127 */       this.xpCooldown -= 1;
/*     */     }
/* 129 */     this.avatar.setExperienceValue(this.experienceTotal);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onItemPickup(Entity entity, int i)
/*     */   {
/* 135 */     if (this.worldObj.isRemote)
/*     */     {
/* 137 */       LMM_LittleMaidMobX.proxy.onItemPickup(this, entity, i);
/*     */     } else {
/* 139 */       super.onItemPickup(entity, i);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onCriticalHit(Entity par1Entity)
/*     */   {
/* 145 */     if (this.worldObj.isRemote)
/*     */     {
/* 147 */       LMM_LittleMaidMobX.proxy.onCriticalHit(this, par1Entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onEnchantmentCritical(Entity par1Entity)
/*     */   {
/* 155 */     if (this.worldObj.isRemote) {
/* 156 */       LMM_LittleMaidMobX.proxy.onEnchantmentCritical(this, par1Entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void attackTargetEntityWithCurrentItem(Entity par1Entity)
/*     */   {
/* 165 */     float ll = 0.0F;
/* 166 */     if ((par1Entity instanceof EntityLivingBase)) {
/* 167 */       ll = ((EntityLivingBase)par1Entity).getHealth();
/*     */     }
/* 169 */     super.attackTargetEntityWithCurrentItem(par1Entity);
/* 170 */     if ((par1Entity instanceof EntityLivingBase)) {
/* 171 */       ((EntityLivingBase)par1Entity).setRevengeTarget(this.avatar);
/*     */     }
/* 173 */     if ((par1Entity instanceof EntityCreature)) {
/* 174 */       ((EntityCreature)par1Entity).setTarget(this.avatar);
/*     */     }
/* 176 */     if (ll > 0.0F) {
/* 177 */       LMM_LittleMaidMobX.Debug(String.format("ID:%d Given Damege:%f", new Object[] { Integer.valueOf(this.avatar.getEntityId()), Float.valueOf(ll - ((EntityLivingBase)par1Entity).getHealth()) }), new Object[0]);
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getCurrentEquippedItem()
/*     */   {
/* 183 */     return this.avatar.getCurrentEquippedItem();
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack getCurrentArmor(int par1)
/*     */   {
/* 189 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack getEquipmentInSlot(int par1)
/*     */   {
/* 194 */     return this.avatar.getEquipmentInSlot(par1);
/*     */   }
/*     */   
/*     */   public ItemStack[] getLastActiveItems()
/*     */   {
/* 199 */     return this.avatar.getLastActiveItems();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void destroyCurrentEquippedItem()
/*     */   {
/* 212 */     this.inventory.setInventorySlotContents(this.inventory.currentItem, (ItemStack)null);
/* 213 */     this.avatar.getNextEquipItem();
/*     */   }
/*     */   
/*     */   public void onKillEntity(EntityLivingBase entityliving)
/*     */   {
/* 218 */     this.avatar.onKillEntity(entityliving);
/*     */   }
/*     */   
/*     */   protected Entity getEntityServer() {
/* 222 */     return this.worldObj.isRemote ? null : this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getItemInUseDuration(int pIndex)
/*     */   {
/* 228 */     return this.avatar.getSwingStatus(pIndex).getItemInUseDuration();
/*     */   }
/*     */   
/*     */   public int getItemInUseDuration()
/*     */   {
/* 233 */     return getItemInUseDuration(this.avatar.maidDominantArm);
/*     */   }
/*     */   
/*     */   public ItemStack getItemInUse(int pIndex) {
/* 237 */     return this.avatar.getSwingStatus(pIndex).getItemInUse();
/*     */   }
/*     */   
/*     */   public ItemStack getItemInUse()
/*     */   {
/* 242 */     return getItemInUse(this.avatar.maidDominantArm);
/*     */   }
/*     */   
/*     */   public int getItemInUseCount(int pIndex) {
/* 246 */     return this.avatar.getSwingStatus(pIndex).getItemInUseCount();
/*     */   }
/*     */   
/*     */   public int getItemInUseCount()
/*     */   {
/* 251 */     return getItemInUseCount(this.avatar.maidDominantArm);
/*     */   }
/*     */   
/*     */   public boolean isUsingItem(int pIndex) {
/* 255 */     return this.avatar.getSwingStatus(pIndex).isUsingItem();
/*     */   }
/*     */   
/*     */   public boolean isUsingItem()
/*     */   {
/* 260 */     return isUsingItem(this.avatar.maidDominantArm);
/*     */   }
/*     */   
/* 263 */   public boolean isUsingItemLittleMaid() { return super.isUsingItem() | this.isItemTrigger; }
/*     */   
/*     */   public void clearItemInUse(int pIndex)
/*     */   {
/* 267 */     this.isItemTrigger = false;
/* 268 */     this.isItemReload = (this.isItemPreReload = 0);
/* 269 */     this.avatar.getSwingStatus(pIndex).clearItemInUse(getEntityServer());
/*     */   }
/*     */   
/*     */ 
/*     */   public void clearItemInUse()
/*     */   {
/* 275 */     this.isItemTrigger = false;
/* 276 */     this.isItemReload = (this.isItemPreReload = 0);
/* 277 */     clearItemInUse(this.avatar.maidDominantArm);
/*     */   }
/*     */   
/*     */   public void stopUsingItem(int pIndex) {
/* 281 */     this.isItemTrigger = false;
/* 282 */     this.isItemReload = (this.isItemPreReload = 0);
/* 283 */     this.avatar.getSwingStatus(pIndex).stopUsingItem(getEntityServer());
/*     */   }
/*     */   
/*     */ 
/*     */   public void stopUsingItem()
/*     */   {
/* 289 */     this.isItemTrigger = false;
/* 290 */     this.isItemReload = (this.isItemPreReload = 0);
/* 291 */     stopUsingItem(this.avatar.maidDominantArm);
/*     */   }
/*     */   
/*     */   public void setItemInUse(int pIndex, ItemStack itemstack, int i) {
/* 295 */     this.isItemTrigger = true;
/* 296 */     this.isItemReload = this.isItemPreReload;
/* 297 */     this.avatar.getSwingStatus(pIndex).setItemInUse(itemstack, i, getEntityServer());
/*     */   }
/*     */   
/*     */ 
/*     */   public void setItemInUse(ItemStack itemstack, int i)
/*     */   {
/* 303 */     this.isItemTrigger = true;
/* 304 */     this.isItemReload = this.isItemPreReload;
/* 305 */     setItemInUse(this.avatar.maidDominantArm, itemstack, i);
/*     */   }
/*     */   
/*     */   public void setEating(boolean par1)
/*     */   {
/* 310 */     this.avatar.setEating(par1);
/*     */   }
/*     */   
/*     */   public boolean isEating()
/*     */   {
/* 315 */     return this.avatar.isEating();
/*     */   }
/*     */   
/*     */   public void setAir(int par1)
/*     */   {
/* 320 */     this.avatar.setAir(par1);
/*     */   }
/*     */   
/*     */   public int getAir()
/*     */   {
/* 325 */     return this.avatar.getAir();
/*     */   }
/*     */   
/*     */   public void setFire(int par1)
/*     */   {
/* 330 */     this.avatar.setFire(par1);
/*     */   }
/*     */   
/*     */   public boolean isBurning()
/*     */   {
/* 335 */     return this.avatar.isBurning();
/*     */   }
/*     */   
/*     */   protected void setFlag(int par1, boolean par2)
/*     */   {
/* 340 */     this.avatar.setFlag(par1, par2);
/*     */   }
/*     */   
/*     */   public boolean isBlocking()
/*     */   {
/* 345 */     return this.avatar.isBlocking();
/*     */   }
/*     */   
/*     */   public void playSound(String par1Str, float par2, float par3)
/*     */   {
/* 350 */     this.avatar.playSound(par1Str, par2, par3);
/*     */   }
/*     */   
/*     */   public ChunkCoordinates getPlayerCoordinates() {
/* 354 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addChatMessage(IChatComponent var1) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setHideCape(int par1, boolean par2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean getHideCape(int par1)
/*     */   {
/* 374 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setScore(int par1) {}
/*     */   
/*     */   public int getScore()
/*     */   {
/* 382 */     return 0;
/*     */   }
/*     */   
/*     */   public void setAbsorptionAmount(float par1) {
/* 386 */     this.avatar.setAbsorptionAmount(par1);
/*     */   }
/*     */   
/*     */   public float getAbsorptionAmount() {
/* 390 */     return this.avatar.getAbsorptionAmount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BaseAttributeMap getAttributeMap()
/*     */   {
/* 398 */     return this.avatar == null ? super.getAttributeMap() : this.avatar.getAttributeMap();
/*     */   }
/*     */   
/*     */   public void addPotionEffect(PotionEffect par1PotionEffect)
/*     */   {
/* 403 */     this.avatar.addPotionEffect(par1PotionEffect);
/*     */   }
/*     */   
/*     */   public PotionEffect getActivePotionEffect(Potion par1Potion)
/*     */   {
/* 408 */     return this.avatar.getActivePotionEffect(par1Potion);
/*     */   }
/*     */   
/*     */   public Collection getActivePotionEffects()
/*     */   {
/* 413 */     return this.avatar.getActivePotionEffects();
/*     */   }
/*     */   
/*     */   public void clearActivePotions()
/*     */   {
/* 418 */     this.avatar.clearActivePotions();
/*     */   }
/*     */   
/*     */   protected void onChangedPotionEffect(PotionEffect par1PotionEffect, boolean par2)
/*     */   {
/* 423 */     this.avatar.onChangedPotionEffect(par1PotionEffect, par2);
/*     */   }
/*     */   
/*     */   public void getValue()
/*     */   {
/* 428 */     setPosition(this.avatar.posX, this.avatar.posY, this.avatar.posZ);
/* 429 */     this.prevPosX = this.avatar.prevPosX;
/* 430 */     this.prevPosY = this.avatar.prevPosY;
/* 431 */     this.prevPosZ = this.avatar.prevPosZ;
/* 432 */     this.rotationPitch = this.avatar.rotationPitch;
/* 433 */     this.rotationYaw = this.avatar.rotationYaw;
/* 434 */     this.prevRotationPitch = this.avatar.prevRotationPitch;
/* 435 */     this.prevRotationYaw = this.avatar.prevRotationYaw;
/* 436 */     this.yOffset = this.avatar.yOffset;
/* 437 */     this.renderYawOffset = this.avatar.renderYawOffset;
/* 438 */     this.prevRenderYawOffset = this.avatar.prevRenderYawOffset;
/* 439 */     this.rotationYawHead = this.avatar.rotationYawHead;
/* 440 */     this.attackTime = this.avatar.attackTime;
/*     */   }
/*     */   
/*     */   public void getValueVector(double atx, double aty, double atz, double atl)
/*     */   {
/* 445 */     double l = MathHelper.sqrt_double(atl);
/* 446 */     this.appendX = (atx / l);
/* 447 */     this.appendY = (aty / l);
/* 448 */     this.appendZ = (atz / l);
/* 449 */     this.posX = (this.avatar.posX + this.appendX);
/* 450 */     this.posY = (this.avatar.posY + this.appendY);
/* 451 */     this.posZ = (this.avatar.posZ + this.appendZ);
/* 452 */     this.prevPosX = (this.avatar.prevPosX + this.appendX);
/* 453 */     this.prevPosY = (this.avatar.prevPosY + this.appendY);
/* 454 */     this.prevPosZ = (this.avatar.prevPosZ + this.appendZ);
/* 455 */     this.rotationPitch = this.avatar.rotationPitch;
/* 456 */     this.prevRotationPitch = this.avatar.prevRotationPitch;
/* 457 */     this.rotationYaw = this.avatar.rotationYaw;
/* 458 */     this.prevRotationYaw = this.avatar.prevRotationYaw;
/* 459 */     this.renderYawOffset = this.avatar.renderYawOffset;
/* 460 */     this.prevRenderYawOffset = this.avatar.prevRenderYawOffset;
/* 461 */     this.rotationYawHead = this.avatar.rotationYawHead;
/* 462 */     this.prevRotationYawHead = this.avatar.prevRotationYawHead;
/* 463 */     this.yOffset = this.avatar.yOffset;
/* 464 */     this.motionX = this.avatar.motionX;
/* 465 */     this.motionY = this.avatar.motionY;
/* 466 */     this.motionZ = this.avatar.motionZ;
/* 467 */     this.isSwingInProgress = this.avatar.getSwinging();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void getValueVectorFire(double atx, double aty, double atz, double atl)
/*     */   {
/* 475 */     double l = MathHelper.sqrt_double(atl);
/* 476 */     this.appendX = (atx / l);
/* 477 */     this.appendY = (aty / l);
/* 478 */     this.appendZ = (atz / l);
/* 479 */     this.posX = (this.avatar.posX + this.appendX);
/* 480 */     this.posY = (this.avatar.posY + this.appendY);
/* 481 */     this.posZ = (this.avatar.posZ + this.appendZ);
/* 482 */     this.prevPosX = (this.avatar.prevPosX + this.appendX);
/* 483 */     this.prevPosY = (this.avatar.prevPosY + this.appendY);
/* 484 */     this.prevPosZ = (this.avatar.prevPosZ + this.appendZ);
/* 485 */     this.rotationPitch = updateDirection(this.avatar.rotationPitch);
/* 486 */     this.prevRotationPitch = updateDirection(this.avatar.prevRotationPitch);
/* 487 */     this.rotationYaw = updateDirection(this.avatar.rotationYawHead);
/* 488 */     this.prevRotationYaw = updateDirection(this.avatar.prevRotationYawHead);
/* 489 */     this.renderYawOffset = updateDirection(this.avatar.renderYawOffset);
/* 490 */     this.prevRenderYawOffset = updateDirection(this.avatar.prevRenderYawOffset);
/* 491 */     this.rotationYawHead = updateDirection(this.avatar.rotationYawHead);
/* 492 */     this.prevRotationYawHead = updateDirection(this.avatar.prevRotationYawHead);
/* 493 */     this.yOffset = this.avatar.yOffset;
/* 494 */     this.motionX = this.avatar.motionX;
/* 495 */     this.motionY = this.avatar.motionY;
/* 496 */     this.motionZ = this.avatar.motionZ;
/* 497 */     this.isSwingInProgress = this.avatar.getSwinging();
/*     */   }
/*     */   
/*     */   protected float updateDirection(float pValue) {
/* 501 */     pValue %= 360.0F;
/* 502 */     if (pValue < 0.0F) pValue += 360.0F;
/* 503 */     return pValue;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setValue()
/*     */   {
/* 509 */     this.avatar.setPosition(this.posX, this.posY, this.posZ);
/* 510 */     this.avatar.prevPosX = this.prevPosX;
/* 511 */     this.avatar.prevPosY = this.prevPosY;
/* 512 */     this.avatar.prevPosZ = this.prevPosZ;
/* 513 */     this.avatar.rotationPitch = this.rotationPitch;
/* 514 */     this.avatar.rotationYaw = this.rotationYaw;
/* 515 */     this.avatar.prevRotationPitch = this.prevRotationPitch;
/* 516 */     this.avatar.prevRotationYaw = this.prevRotationYaw;
/* 517 */     this.avatar.yOffset = this.yOffset;
/* 518 */     this.avatar.renderYawOffset = this.renderYawOffset;
/* 519 */     this.avatar.prevRenderYawOffset = this.prevRenderYawOffset;
/* 520 */     this.avatar.getSwingStatusDominant().attackTime = (this.avatar.attackTime = this.attackTime);
/*     */   }
/*     */   
/*     */   public void setValueRotation()
/*     */   {
/* 525 */     this.avatar.rotationPitch = this.rotationPitch;
/* 526 */     this.avatar.rotationYaw = this.rotationYaw;
/* 527 */     this.avatar.prevRotationPitch = this.prevRotationPitch;
/* 528 */     this.avatar.prevRotationYaw = this.prevRotationYaw;
/* 529 */     this.avatar.renderYawOffset = this.renderYawOffset;
/* 530 */     this.avatar.prevRenderYawOffset = this.prevRenderYawOffset;
/* 531 */     this.avatar.motionX = this.motionX;
/* 532 */     this.avatar.motionY = this.motionY;
/* 533 */     this.avatar.motionZ = this.motionZ;
/* 534 */     if (this.isSwingInProgress) { this.avatar.setSwinging(LMM_EnumSound.Null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setValueVector()
/*     */   {
/* 540 */     this.avatar.posX = (this.posX - this.appendX);
/* 541 */     this.avatar.posY = (this.posY - this.appendY);
/* 542 */     this.avatar.posZ = (this.posZ - this.appendZ);
/* 543 */     this.avatar.prevPosX = (this.prevPosX - this.appendX);
/* 544 */     this.avatar.prevPosY = (this.prevPosY - this.appendY);
/* 545 */     this.avatar.prevPosZ = (this.prevPosZ - this.appendZ);
/* 546 */     this.avatar.rotationPitch = this.rotationPitch;
/* 547 */     this.avatar.prevRotationPitch = this.prevRotationPitch;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 552 */     this.avatar.motionX = this.motionX;
/* 553 */     this.avatar.motionY = this.motionY;
/* 554 */     this.avatar.motionZ = this.motionZ;
/* 555 */     if (this.isSwingInProgress) this.avatar.setSwinging(LMM_EnumSound.Null);
/*     */   }
/*     */   
/*     */   protected void damageArmor(float par1) {
/* 559 */     super.damageArmor(par1);
/*     */   }
/*     */   
/*     */   public float applyArmorCalculations(DamageSource par1DamageSource, float par2)
/*     */   {
/* 564 */     return super.applyArmorCalculations(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */   protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2)
/*     */   {
/* 569 */     return super.applyPotionDamageCalculations(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */   public void damageEntity(DamageSource par1DamageSource, float par2)
/*     */   {
/* 574 */     super.damageEntity(par1DamageSource, par2);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityLittleMaidAvatar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
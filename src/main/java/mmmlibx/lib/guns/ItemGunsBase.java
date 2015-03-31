/*     */ package mmmlibx.lib.guns;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemGunsBase
/*     */   extends ItemBow
/*     */ {
/*     */   public static final String Tag_State = "State";
/*     */   public static final String Tag_MaxLoad = "MaxLoad";
/*     */   public static final String Tag_Magazin = "Magazin";
/*     */   public static final String Tag_Burst = "Burst";
/*     */   public static final String Tag_Cycle = "Cycle";
/*     */   public static final String Tag_ReloadTime = "ReloadTime";
/*     */   public static final String Tag_BurstCount = "BurstCount";
/*     */   public static final String Tag_CycleCount = "CycleCount";
/*     */   public static final String Tag_Efficiency = "Efficiency";
/*     */   public static final String Tag_Stability = "Stability";
/*     */   public static final String Tag_StabilityY = "StabilityY";
/*     */   public static final String Tag_StabilityYO = "StabilityYO";
/*     */   public static final String Tag_StabilityP = "StabilityP";
/*     */   public static final String Tag_StabilityPO = "StabilityPO";
/*     */   public static final String Tag_Accuracy = "Accuracy";
/*  38 */   protected static byte State_Ready = 0;
/*  39 */   protected static byte State_Empty = 16;
/*  40 */   protected static byte State_Reload = 32;
/*  41 */   protected static byte State_ReloadTac = 48;
/*  42 */   protected static byte State_ReloadCre = 64;
/*  43 */   protected static byte State_ReleseMag = 80;
/*  44 */   protected static byte State_ReloadEnd = 96;
/*     */   
/*     */ 
/*     */   public String soundEmpty;
/*     */   
/*     */   public String soundRelease;
/*     */   
/*     */   public String soundReload;
/*     */   
/*     */   public float volume;
/*     */   
/*     */   public int reloadTime;
/*     */   
/*     */   public int burstCount;
/*     */   
/*     */   public short cycleCount;
/*     */   
/*     */   public float efficiency;
/*     */   
/*     */   public float stability;
/*     */   
/*     */   public float stabilityYaw;
/*     */   
/*     */   public float stabilityYawOffset;
/*     */   
/*     */   public float stabilityPitch;
/*     */   
/*     */   public float stabilityPitchOffset;
/*     */   
/*     */   public float accuracy;
/*     */   
/*     */   public String[] bullets;
/*     */   
/*     */   public String[] iconNames;
/*     */   
/*     */   protected IIcon[] icons;
/*     */   
/*     */   protected Item[] ammos;
/*     */   
/*     */ 
/*     */   public ItemGunsBase()
/*     */   {
/*  86 */     this.maxStackSize = 1;
/*  87 */     setFull3D();
/*     */     
/*  89 */     this.volume = 0.5F;
/*  90 */     this.reloadTime = 40;
/*  91 */     this.burstCount = 0;
/*  92 */     this.cycleCount = 2;
/*  93 */     this.efficiency = 1.0F;
/*  94 */     this.stability = 1.0F;
/*  95 */     this.stabilityPitch = 5.0F;
/*  96 */     this.stabilityPitchOffset = 5.0F;
/*  97 */     this.stabilityYaw = 3.0F;
/*  98 */     this.stabilityYawOffset = 0.0F;
/*  99 */     this.accuracy = 1.0F;
/*     */     
/* 101 */     this.iconNames = new String[] { "", "", "" };
/* 102 */     this.bullets = new String[] { "" };
/*     */     
/* 104 */     GunsBase.appendItem(this);
/*     */   }
/*     */   
/*     */   public void init() {
/* 108 */     this.ammos = new Item[this.bullets.length];
/* 109 */     for (int li = 0; li < this.bullets.length; li++) {
/* 110 */       this.ammos[li] = ((Item)Item.itemRegistry.getObject(this.bullets[li]));
/*     */     }
/*     */   }
/*     */   
/*     */   public void playSoundEmpty(World pWorld, EntityPlayer pPlayer, ItemStack pGun) {
/* 115 */     pWorld.playSoundAtEntity(pPlayer, this.soundEmpty, this.volume, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*     */   }
/*     */   
/*     */   public void playSoundRelease(World pWorld, EntityPlayer pPlayer, ItemStack pGun)
/*     */   {
/* 120 */     pWorld.playSoundAtEntity(pPlayer, this.soundRelease, this.volume, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*     */   }
/*     */   
/*     */   public void playSoundReload(World pWorld, EntityPlayer pPlayer, ItemStack pGun)
/*     */   {
/* 125 */     pWorld.playSoundAtEntity(pPlayer, this.soundReload, this.volume, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*     */   }
/*     */   
/*     */   public int getReloadTime(ItemStack pGun)
/*     */   {
/* 130 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("ReloadTime"))) {
/* 131 */       return pGun.getTagCompound().getInteger("ReloadTime");
/*     */     }
/* 133 */     return this.reloadTime;
/*     */   }
/*     */   
/*     */   public int getBurstCount(ItemStack pGun) {
/* 137 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("BurstCount"))) {
/* 138 */       return pGun.getTagCompound().getInteger("BurstCount");
/*     */     }
/* 140 */     return this.burstCount;
/*     */   }
/*     */   
/*     */   public short getCycleCount(ItemStack pGun)
/*     */   {
/* 145 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("CycleCount"))) {
/* 146 */       return pGun.getTagCompound().getShort("CycleCount");
/*     */     }
/* 148 */     return this.cycleCount;
/*     */   }
/*     */   
/*     */   public float getEfficiency(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 152 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("Efficiency"))) {
/* 153 */       return pGun.getTagCompound().getFloat("Efficiency");
/*     */     }
/* 155 */     return this.efficiency;
/*     */   }
/*     */   
/*     */   public float getStability(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 159 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("Stability"))) {
/* 160 */       return pGun.getTagCompound().getFloat("Stability");
/*     */     }
/* 162 */     return this.stability;
/*     */   }
/*     */   
/*     */   public float getStabilityY(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 166 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("StabilityY"))) {
/* 167 */       return pGun.getTagCompound().getFloat("StabilityY");
/*     */     }
/* 169 */     return this.stabilityYaw;
/*     */   }
/*     */   
/*     */   public float getStabilityYO(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 173 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("StabilityYO"))) {
/* 174 */       return pGun.getTagCompound().getFloat("StabilityYO");
/*     */     }
/* 176 */     return this.stabilityYawOffset;
/*     */   }
/*     */   
/*     */   public float getStabilityP(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 180 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("StabilityP"))) {
/* 181 */       return pGun.getTagCompound().getFloat("StabilityP");
/*     */     }
/* 183 */     return this.stabilityPitch;
/*     */   }
/*     */   
/*     */   public float getStabilityPO(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 187 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("StabilityPO"))) {
/* 188 */       return pGun.getTagCompound().getFloat("StabilityPO");
/*     */     }
/* 190 */     return this.stabilityPitchOffset;
/*     */   }
/*     */   
/*     */   public float getAccuracy(ItemStack pGun, EntityPlayer pPlayer, int pUseCount) {
/* 194 */     if ((pGun.hasTagCompound()) && (pGun.getTagCompound().hasKey("Accuracy"))) {
/* 195 */       return pGun.getTagCompound().getFloat("Accuracy");
/*     */     }
/* 197 */     return this.accuracy;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getMaxDamage(ItemStack stack)
/*     */   {
/* 203 */     NBTTagCompound ltag = getTagCompound(stack);
/* 204 */     if (ltag.hasKey("MaxLoad")) {
/* 205 */       return ltag.getInteger("MaxLoad");
/*     */     }
/* 207 */     return super.getMaxDamage();
/*     */   }
/*     */   
/*     */   protected NBTTagCompound getTagCompound(ItemStack pGun) {
/* 211 */     if (!pGun.hasTagCompound()) {
/* 212 */       pGun.setTagCompound(new NBTTagCompound());
/*     */     }
/* 214 */     return pGun.getTagCompound();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSkipBlank()
/*     */   {
/* 223 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBurst(ItemStack pGun)
/*     */   {
/* 232 */     return getBurstCount(pGun) > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void registerIcons(IIconRegister par1IconRegister)
/*     */   {
/* 239 */     this.icons = new IIcon[3];
/*     */     
/*     */ 
/*     */ 
/* 243 */     this.icons[0] = par1IconRegister.registerIcon(this.iconNames[0]);
/* 244 */     this.icons[1] = par1IconRegister.registerIcon(this.iconNames[1]);
/* 245 */     this.icons[2] = par1IconRegister.registerIcon(this.iconNames[2]);
/* 246 */     this.itemIcon = this.icons[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public IIcon getItemIconForUseDuration(int par1)
/*     */   {
/* 253 */     return this.itemIcon;
/*     */   }
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/* 258 */     int li = getState(stack);
/* 259 */     if ((li >= State_ReleseMag) && (li < State_ReloadEnd)) {
/* 260 */       return this.icons[2];
/*     */     }
/* 262 */     if ((li >= State_Empty) && (li < State_ReleseMag)) {
/* 263 */       return this.icons[1];
/*     */     }
/* 265 */     return this.icons[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 273 */     GunsBase.Debug("%s - trigger", new Object[] { (par3EntityPlayer instanceof EntityPlayerMP) ? "MP" : "SP" });
/* 274 */     int li = getState(par1ItemStack);
/* 275 */     if (par3EntityPlayer.isSwingInProgress) {
/* 276 */       setState(par1ItemStack, State_ReloadTac);
/* 277 */       GunsBase.Debug("Tactical Reload.", new Object[0]);
/*     */     }
/* 279 */     else if (isBurst(par1ItemStack))
/*     */     {
/* 281 */       if ((li >= State_Empty) && (li < State_Reload)) {
/* 282 */         if (hasAmmo(par1ItemStack, par2World, par3EntityPlayer))
/*     */         {
/* 284 */           setState(par1ItemStack, State_Reload);
/* 285 */           GunsBase.Debug("Reload.", new Object[0]);
/*     */         }
/*     */         else {
/* 288 */           playSoundEmpty(par2World, par3EntityPlayer, par1ItemStack);
/* 289 */           GunsBase.Debug("Empty.", new Object[0]);
/*     */         }
/* 291 */       } else if (li < State_Empty)
/*     */       {
/* 293 */         resetBolt(par1ItemStack);
/* 294 */         resetBurst(par1ItemStack);
/*     */       }
/*     */       
/*     */     }
/* 298 */     else if ((isAmmoEmpty(par1ItemStack)) && (li < State_Reload) && 
/* 299 */       (hasAmmo(par1ItemStack, par2World, par3EntityPlayer))) {
/* 300 */       setState(par1ItemStack, State_Reload);
/* 301 */       GunsBase.Debug("Reload.", new Object[0]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 307 */     GunsBase.Debug("%s - ItemStack: %s", new Object[] { (par3EntityPlayer instanceof EntityPlayerMP) ? "MP" : "SP", par1ItemStack.toString() });
/*     */     
/*     */ 
/*     */ 
/* 311 */     GunsBase.setUncheckedItemStack(par1ItemStack, par3EntityPlayer);
/* 312 */     par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
/* 313 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/* 318 */     int li = getState(stack);
/* 319 */     if (li == State_Reload) {
/* 320 */       setState(stack, State_ReleseMag);
/* 321 */       releaseMagazin(stack, player.worldObj, player);
/* 322 */       GunsBase.setUncheckedItemStack(stack, player);
/*     */     }
/* 324 */     if (li == State_ReloadTac) {
/* 325 */       setState(stack, State_ReleseMag);
/* 326 */       releaseMagazin(stack, player.worldObj, player);
/* 327 */       GunsBase.setUncheckedItemStack(stack, player);
/*     */     }
/* 329 */     onFireTick(stack, player.worldObj, player, count, li);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onFireTick(ItemStack pGun, World pWorld, EntityPlayer pPlayer, int count, int pState)
/*     */   {
/* 341 */     if ((isBurst(pGun)) && 
/* 342 */       (pState == State_Ready) && (!isAmmoEmpty(pGun))) {
/* 343 */       if ((checkBolt(pGun)) && (decBurst(pGun) > 0))
/*     */       {
/* 345 */         if (fireBullet(pGun, pWorld, pPlayer, count) <= 0) {
/* 346 */           setState(pGun, State_Empty);
/*     */         }
/*     */       }
/* 349 */       GunsBase.setUncheckedItemStack(pGun, pPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 357 */     int li = getState(par1ItemStack);
/* 358 */     if ((li >= State_ReleseMag) && (li < State_ReloadEnd)) {
/* 359 */       reloadMagazin(par1ItemStack, par2World, par3EntityPlayer);
/* 360 */       setState(par1ItemStack, State_ReloadEnd);
/* 361 */       GunsBase.setUncheckedItemStack(par1ItemStack, par3EntityPlayer);
/*     */     }
/* 363 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
/*     */   {
/* 369 */     int li = getState(par1ItemStack);
/* 370 */     if (li == State_ReloadEnd) {
/* 371 */       if (isAmmoEmpty(par1ItemStack)) {
/* 372 */         setState(par1ItemStack, State_Empty);
/*     */       } else {
/* 374 */         setState(par1ItemStack, State_Ready);
/*     */       }
/* 376 */     } else if (!isBurst(par1ItemStack)) {
/* 377 */       if (!isAmmoEmpty(par1ItemStack))
/*     */       {
/* 379 */         if (fireBullet(par1ItemStack, par2World, par3EntityPlayer, par4) <= 0)
/*     */         {
/* 381 */           setState(par1ItemStack, State_Empty);
/*     */         }
/* 383 */       } else if (li < State_Reload)
/*     */       {
/* 385 */         playSoundEmpty(par2World, par3EntityPlayer, par1ItemStack);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack)
/*     */   {
/* 395 */     int li = getState(par1ItemStack);
/* 396 */     if ((li >= State_Empty) && (li < State_ReloadEnd)) {
/* 397 */       return getReloadTime(par1ItemStack);
/*     */     }
/* 399 */     return 72000;
/*     */   }
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack)
/*     */   {
/* 404 */     int li = getState(par1ItemStack);
/* 405 */     if (li < State_ReloadTac) {
/* 406 */       return EnumAction.bow;
/*     */     }
/* 408 */     return EnumAction.block;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasAmmo(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 419 */     return (par3EntityPlayer.capabilities.isCreativeMode) || (getAmmoIndex(par3EntityPlayer) > -1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int fireBullet(ItemStack pGun, World pWorld, EntityPlayer pPlayer, int pUseCount)
/*     */   {
/* 431 */     int ldamage = getDamage(pGun);
/* 432 */     int lmdamage = getMaxDamage(pGun);
/*     */     ItemStack lbullet;
/*     */     do
/*     */     {
/* 436 */       lbullet = getBullet(pGun, ldamage);
/* 437 */       ldamage++;
/*     */       
/* 439 */       setDamage(pGun, ldamage);
/* 440 */     } while ((lbullet == null) && 
/* 441 */       (!isSkipBlank()) && (ldamage < lmdamage));
/*     */     
/* 443 */     ldamage = lmdamage - ldamage;
/* 444 */     if (lbullet == null) { return ldamage;
/*     */     }
/* 446 */     ItemBulletBase libullet = null;
/* 447 */     if ((lbullet.getItem() instanceof ItemBulletBase)) {
/* 448 */       libullet = (ItemBulletBase)lbullet.getItem();
/*     */     }
/*     */     
/* 451 */     if (libullet != null) {
/* 452 */       libullet.playSoundFire(pWorld, pPlayer, pGun, lbullet);
/*     */     }
/*     */     
/*     */ 
/* 456 */     if (!pWorld.isRemote)
/*     */     {
/* 458 */       GunsBase.Debug("Bulle: %s-%s", new Object[] { lbullet == null ? "NULL" : lbullet.toString(), lbullet.hasTagCompound() ? lbullet.getTagCompound().toString() : "" });
/*     */       
/*     */ 
/* 461 */       if (libullet != null) {
/* 462 */         Entity lentity = libullet.getBulletEntity(pGun, lbullet, pWorld, pPlayer, 72000 - pUseCount);
/* 463 */         pWorld.spawnEntityInWorld(lentity);
/*     */       }
/*     */     }
/*     */     
/* 467 */     if (libullet != null) {
/* 468 */       onRecoile(pGun, lbullet, pWorld, pPlayer, 72000 - pUseCount);
/*     */     }
/* 470 */     return ldamage;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onRecoile(ItemStack pGun, ItemStack pBullet, World pWorld, EntityPlayer pPlayer, int pUseCount)
/*     */   {
/* 482 */     float lsn = pPlayer.isSneaking() ? 0.5F : 1.0F;
/* 483 */     lsn *= ((ItemBulletBase)pBullet.getItem()).getReaction(pBullet);
/*     */     
/* 485 */     pPlayer.rotationPitch -= (pPlayer.getRNG().nextFloat() * getStabilityP(pGun, pPlayer, pUseCount) + getStabilityPO(pGun, pPlayer, pUseCount)) * lsn;
/*     */     
/* 487 */     pPlayer.rotationYaw += (pPlayer.getRNG().nextFloat() * getStabilityY(pGun, pPlayer, pUseCount) + getStabilityYO(pGun, pPlayer, pUseCount)) * lsn;
/*     */     
/*     */ 
/* 490 */     lsn *= getStability(pGun, pPlayer, pUseCount);
/* 491 */     pPlayer.motionX += MathHelper.sin(pPlayer.rotationYawHead * 0.017453292F) * lsn;
/* 492 */     pPlayer.motionZ -= MathHelper.cos(pPlayer.rotationYawHead * 0.017453292F) * lsn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack getBullet(ItemStack pGun, int pIndex)
/*     */   {
/* 502 */     if (pGun.hasTagCompound()) {
/* 503 */       NBTTagCompound ltag = pGun.getTagCompound();
/* 504 */       NBTTagCompound lbullet = ltag.getCompoundTag("Magazin");
/* 505 */       String ls = String.format("%04d", new Object[] { Integer.valueOf(pIndex) });
/* 506 */       if (lbullet.hasKey(ls)) {
/* 507 */         return ItemStack.loadItemStackFromNBT(lbullet.getCompoundTag(ls));
/*     */       }
/*     */     }
/* 510 */     return null;
/*     */   }
/*     */   
/*     */   public void setBullet(ItemStack pGun, int pIndex, ItemStack pBullet) {
/* 514 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 515 */     NBTTagCompound lmagazin = ltag.getCompoundTag("Magazin");
/* 516 */     ltag.setTag("Magazin", lmagazin);
/* 517 */     String ls = String.format("%04d", new Object[] { Integer.valueOf(pIndex) });
/* 518 */     if (pBullet == null) {
/* 519 */       lmagazin.removeTag(ls);
/*     */     } else {
/* 521 */       NBTTagCompound lbullet = ltag.getCompoundTag(ls);
/* 522 */       lmagazin.setTag(ls, lbullet);
/* 523 */       pBullet.writeToNBT(lbullet);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadBullet(ItemStack pGun, ItemStack pBullet)
/*     */   {
/* 534 */     int li = getDamage(pGun);
/* 535 */     while (li > 0) {
/* 536 */       li--;
/* 537 */       ItemStack lis = getBullet(pGun, li);
/* 538 */       pGun.setItemDamage(li);
/* 539 */       if (lis == null) {
/* 540 */         setBullet(pGun, li, pBullet.splitStack(1));
/* 541 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAmmoEmpty(ItemStack pGun)
/*     */   {
/* 552 */     return getDamage(pGun) >= getMaxDamage(pGun);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkAmmo(ItemStack pItemStack)
/*     */   {
/* 561 */     Item litem = pItemStack.getItem();
/* 562 */     for (Item li : this.ammos) {
/* 563 */       if (litem == li) return true;
/*     */     }
/* 565 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAmmoIndex(EntityPlayer pPlayer)
/*     */   {
/* 574 */     for (int li = 0; li < pPlayer.inventory.mainInventory.length; li++) {
/* 575 */       ItemStack lis = pPlayer.inventory.mainInventory[li];
/* 576 */       if ((lis != null) && (checkAmmo(lis))) {
/* 577 */         return li;
/*     */       }
/*     */     }
/* 580 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void releaseMagazin(ItemStack pGun, World pWorld, EntityPlayer pPlayer)
/*     */   {
/* 590 */     playSoundRelease(pWorld, pPlayer, pGun);
/* 591 */     if (!pPlayer.capabilities.isCreativeMode)
/*     */     {
/* 593 */       for (int li = 0; li < getDamage(pGun); li++) {
/* 594 */         setBullet(pGun, li, null);
/*     */       }
/*     */     }
/* 597 */     GunsBase.Debug(pGun.toString(), new Object[0]);
/* 598 */     for (int li = 0; li < getMaxDamage(pGun); li++) {
/* 599 */       ItemStack lis = getBullet(pGun, li);
/* 600 */       GunsBase.Debug("%04d: %s", new Object[] { Integer.valueOf(li), lis == null ? "null" : lis.toString() });
/*     */     }
/*     */     
/* 603 */     setDamage(pGun, getMaxDamage(pGun));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reloadMagazin(ItemStack pGun, World pWorld, EntityPlayer pPlayer)
/*     */   {
/* 612 */     while (getDamage(pGun) > 0) {
/* 613 */       int li = getAmmoIndex(pPlayer);
/* 614 */       if (li == -1) {
/*     */         break;
/*     */       }
/* 617 */       ItemStack lis = pPlayer.inventory.mainInventory[li];
/* 618 */       loadBullet(pGun, lis);
/* 619 */       if (lis.stackSize <= 0) {
/* 620 */         pPlayer.inventory.setInventorySlotContents(li, null);
/*     */       }
/*     */     }
/* 623 */     playSoundReload(pWorld, pPlayer, pGun);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(ItemStack pGun, byte pState)
/*     */   {
/* 629 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 630 */     ltag.setByte("State", pState);
/*     */   }
/*     */   
/*     */   public byte getState(ItemStack pGun) {
/* 634 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 635 */     return ltag.getByte("State");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean checkBolt(ItemStack pGun)
/*     */   {
/* 641 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 642 */     short lval = ltag.getShort("Cycle");
/* 643 */     lval = (short)(lval - 1); if (lval <= 0) {
/* 644 */       ltag.setShort("Cycle", getCycleCount(pGun));
/* 645 */       return true;
/*     */     }
/* 647 */     ltag.setShort("Cycle", lval);
/* 648 */     return false;
/*     */   }
/*     */   
/*     */   public void resetBolt(ItemStack pGun) {
/* 652 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 653 */     ltag.setShort("Cycle", getCycleCount(pGun));
/*     */   }
/*     */   
/*     */ 
/*     */   public int decBurst(ItemStack pGun)
/*     */   {
/* 659 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 660 */     int lburst = ltag.getInteger("Burst");
/* 661 */     if (lburst > 0) {
/* 662 */       ltag.setInteger("Burst", lburst - 1);
/*     */     }
/* 664 */     return lburst;
/*     */   }
/*     */   
/*     */   public void resetBurst(ItemStack pGun) {
/* 668 */     NBTTagCompound ltag = getTagCompound(pGun);
/* 669 */     ltag.setInteger("Burst", getBurstCount(pGun));
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/guns/ItemGunsBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
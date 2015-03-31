/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockTNT;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_InventoryLittleMaid
/*     */   extends InventoryPlayer
/*     */ {
/*     */   public static final int maxInventorySize = 18;
/*     */   public LMM_EntityLittleMaid entityLittleMaid;
/*     */   public ItemStack[] prevItems;
/*     */   
/*     */   public LMM_InventoryLittleMaid(LMM_EntityLittleMaid par1EntityLittleMaid)
/*     */   {
/*  43 */     super(par1EntityLittleMaid.maidAvatar);
/*     */     
/*  45 */     this.entityLittleMaid = par1EntityLittleMaid;
/*  46 */     this.mainInventory = new ItemStack[18];
/*  47 */     this.prevItems = new ItemStack[this.mainInventory.length + this.armorInventory.length];
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagList par1nbtTagList)
/*     */   {
/*  52 */     this.mainInventory = new ItemStack[18];
/*  53 */     this.armorInventory = new ItemStack[4];
/*     */     
/*  55 */     for (int i = 0; i < par1nbtTagList.tagCount(); i++) {
/*  56 */       NBTTagCompound nbttagcompound = par1nbtTagList.getCompoundTagAt(i);
/*  57 */       int j = nbttagcompound.getByte("Slot") & 0xFF;
/*  58 */       ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
/*     */       
/*  60 */       if (itemstack != null)
/*     */       {
/*     */ 
/*     */ 
/*  64 */         if ((j >= 0) && (j < this.mainInventory.length)) {
/*  65 */           this.mainInventory[j] = itemstack;
/*     */         }
/*     */         
/*  68 */         if ((j >= 100) && (j < this.armorInventory.length + 100)) {
/*  69 */           this.armorInventory[(j - 100)] = itemstack;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String getInventoryName() {
/*  76 */     return "InsideSkirt";
/*     */   }
/*     */   
/*     */ 
/*     */   public int getSizeInventory()
/*     */   {
/*  82 */     return this.mainInventory.length + this.armorInventory.length;
/*     */   }
/*     */   
/*     */   public void openInventory()
/*     */   {
/*  87 */     this.entityLittleMaid.onGuiOpened();
/*     */   }
/*     */   
/*     */   public void closeInventory()
/*     */   {
/*  92 */     this.entityLittleMaid.onGuiClosed();
/*     */   }
/*     */   
/*     */   public void decrementAnimations()
/*     */   {
/*  97 */     for (int li = 0; li < this.mainInventory.length; li++) {
/*  98 */       if (this.mainInventory[li] != null) {
/*     */         try {
/* 100 */           this.mainInventory[li].updateAnimation(this.player.worldObj, this.entityLittleMaid, li, this.currentItem == li);
/*     */         }
/*     */         catch (ClassCastException e) {
/* 103 */           this.mainInventory[li].updateAnimation(this.player.worldObj, this.entityLittleMaid.maidAvatar, li, this.currentItem == li);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getTotalArmorValue()
/*     */   {
/* 114 */     ItemStack lis = this.armorInventory[3];
/* 115 */     this.armorInventory[3] = null;
/*     */     
/* 117 */     int li = super.getTotalArmorValue();
/*     */     
/* 119 */     for (int lj = 0; lj < this.armorInventory.length; lj++) {
/* 120 */       if ((this.armorInventory[lj] != null) && ((this.armorInventory[lj].getItem() instanceof ItemArmor)))
/*     */       {
/* 122 */         li++;
/*     */       }
/*     */     }
/* 125 */     this.armorInventory[3] = lis;
/* 126 */     return li;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void damageArmor(float pDamage)
/*     */   {
/* 133 */     ItemStack lis = this.armorInventory[3];
/* 134 */     this.armorInventory[3] = null;
/* 135 */     super.damageArmor(pDamage);
/* 136 */     this.armorInventory[3] = lis;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void dropAllItems(boolean detonator)
/*     */   {
/* 162 */     Explosion lexp = null;
/* 163 */     if (detonator)
/*     */     {
/* 165 */       lexp = new Explosion(this.entityLittleMaid.worldObj, this.entityLittleMaid, this.entityLittleMaid.posX, this.entityLittleMaid.posY, this.entityLittleMaid.posZ, 3.0F);
/*     */       
/* 167 */       lexp.isFlaming = false;
/* 168 */       lexp.isSmoking = this.entityLittleMaid.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
/*     */     }
/*     */     
/* 171 */     this.armorInventory[3] = null;
/* 172 */     for (int i = 0; i < getSizeInventory(); i++) {
/* 173 */       ItemStack it = getStackInSlot(i);
/* 174 */       if (it != null) {
/* 175 */         if ((detonator) && (isItemExplord(i))) {
/* 176 */           Item j = it.getItem();
/* 177 */           for (int l = 0; l < it.stackSize; l++)
/*     */           {
/* 179 */             ((BlockTNT)Block.getBlockFromItem(j)).onBlockDestroyedByExplosion(this.entityLittleMaid.worldObj, MathHelper.floor_double(this.entityLittleMaid.posX) + this.entityLittleMaid.getRNG().nextInt(7) - 3, MathHelper.floor_double(this.entityLittleMaid.posY) + this.entityLittleMaid.getRNG().nextInt(7) - 3, MathHelper.floor_double(this.entityLittleMaid.posZ) + this.entityLittleMaid.getRNG().nextInt(7) - 3, lexp);
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 189 */           this.entityLittleMaid.entityDropItem(it, 0.0F);
/*     */         }
/*     */       }
/* 192 */       setInventorySlotContents(i, null);
/*     */     }
/* 194 */     if (detonator) {
/* 195 */       lexp.doExplosionA();
/* 196 */       lexp.doExplosionB(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void dropAllItems()
/*     */   {
/* 202 */     dropAllItems(false);
/*     */   }
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer entityplayer)
/*     */   {
/* 207 */     if (this.entityLittleMaid.isDead) {
/* 208 */       return false;
/*     */     }
/* 210 */     return entityplayer.getDistanceSqToEntity(this.entityLittleMaid) <= 64.0D;
/*     */   }
/*     */   
/*     */   public ItemStack getCurrentItem()
/*     */   {
/* 215 */     if ((this.currentItem >= 0) && (this.currentItem < this.mainInventory.length)) {
/* 216 */       return this.mainInventory[this.currentItem];
/*     */     }
/* 218 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addItemStackToInventory(ItemStack par1ItemStack)
/*     */   {
/* 224 */     markDirty();
/* 225 */     return super.addItemStackToInventory(par1ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack getHeadMount()
/*     */   {
/* 232 */     return this.mainInventory[(this.mainInventory.length - 1)];
/*     */   }
/*     */   
/*     */   public void setInventoryCurrentSlotContents(ItemStack itemstack) {
/* 236 */     if (this.currentItem > -1) {
/* 237 */       setInventorySlotContents(this.currentItem, itemstack);
/*     */     }
/*     */   }
/*     */   
/*     */   protected int getInventorySlotContainItem(Item item)
/*     */   {
/* 243 */     for (int j = 0; j < this.mainInventory.length; j++) {
/* 244 */       if ((this.mainInventory[j] != null) && (this.mainInventory[j].getItem() == item)) {
/* 245 */         return j;
/*     */       }
/*     */     }
/*     */     
/* 249 */     return -1;
/*     */   }
/*     */   
/*     */   protected int getInventorySlotContainItem(Class itemClass)
/*     */   {
/* 254 */     for (int j = 0; j < this.mainInventory.length; j++)
/*     */     {
/*     */ 
/*     */ 
/* 258 */       if ((this.mainInventory[j] != null) && (itemClass.isAssignableFrom(this.mainInventory[j].getItem().getClass())))
/*     */       {
/* 260 */         return j;
/*     */       }
/*     */     }
/*     */     
/* 264 */     return -1;
/*     */   }
/*     */   
/*     */   protected int getInventorySlotContainItemAndDamage(Item item, int damege)
/*     */   {
/* 269 */     for (int i = 0; i < this.mainInventory.length; i++) {
/* 270 */       if ((this.mainInventory[i] != null) && (this.mainInventory[i].getItem() == item) && (this.mainInventory[i].getItemDamage() == damege))
/*     */       {
/* 272 */         return i;
/*     */       }
/*     */     }
/*     */     
/* 276 */     return -1;
/*     */   }
/*     */   
/*     */   protected ItemStack getInventorySlotContainItemStack(Item item)
/*     */   {
/* 281 */     int j = getInventorySlotContainItem(item);
/* 282 */     return j > -1 ? this.mainInventory[j] : null;
/*     */   }
/*     */   
/*     */   protected ItemStack getInventorySlotContainItemStackAndDamege(Item item, int damege)
/*     */   {
/* 287 */     int j = getInventorySlotContainItemAndDamage(item, damege);
/* 288 */     return j > -1 ? this.mainInventory[j] : null;
/*     */   }
/*     */   
/*     */   public int getInventorySlotContainItemFood()
/*     */   {
/* 293 */     for (int j = 0; j < this.mainInventory.length; j++) {
/* 294 */       ItemStack mi = this.mainInventory[j];
/* 295 */       if ((mi != null) && ((mi.getItem() instanceof ItemFood)) && 
/* 296 */         (((ItemFood)mi.getItem()).func_150905_g(mi) > 0)) {
/* 297 */         return j;
/*     */       }
/*     */     }
/*     */     
/* 301 */     return -1;
/*     */   }
/*     */   
/*     */   public int getSmeltingItem()
/*     */   {
/* 306 */     for (int i = 0; i < this.mainInventory.length; i++) {
/* 307 */       if ((isItemSmelting(i)) && (i != this.currentItem)) {
/* 308 */         ItemStack mi = this.mainInventory[i];
/* 309 */         if ((mi.getMaxDamage() <= 0) || (mi.getItemDamage() != 0))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 314 */           return i; }
/*     */       }
/*     */     }
/* 317 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getInventorySlotContainItemPotion(boolean flag, int potionID, boolean isUndead)
/*     */   {
/*     */     label199:
/* 324 */     for (int j = 0; j < this.mainInventory.length; j++) {
/* 325 */       if ((this.mainInventory[j] != null) && ((this.mainInventory[j].getItem() instanceof ItemPotion)))
/*     */       {
/* 327 */         ItemStack is = this.mainInventory[j];
/* 328 */         List list = ((ItemPotion)is.getItem()).getEffects(is);
/* 329 */         if (list != null)
/*     */         {
/* 331 */           Iterator iterator = list.iterator();
/* 332 */           while (iterator.hasNext()) {
/* 333 */             PotionEffect potioneffect = (PotionEffect)iterator.next();
/* 334 */             if (potioneffect.getPotionID() != potionID)
/* 335 */               if (potioneffect.getPotionID() == Potion.heal.id) {
/* 336 */                 if ((!flag) && (isUndead)) break label199; if ((flag) && (!isUndead)) {
/*     */                   break label199;
/*     */                 }
/* 339 */               } else if (potioneffect.getPotionID() == Potion.harm.id) {
/* 340 */                 if ((flag) && (isUndead)) break label199; if ((!flag) && (!isUndead)) {
/*     */                   break label199;
/*     */                 }
/* 343 */               } else if (Potion.potionTypes[potioneffect.getPotionID()].isBadEffect() != flag) {
/*     */                 break label199;
/*     */               }
/*     */           }
/* 347 */           return j;
/*     */         }
/*     */       }
/*     */     }
/* 351 */     return -1;
/*     */   }
/*     */   
/*     */   public int getFirstEmptyStack() {
/* 355 */     for (int i = 0; i < this.mainInventory.length; i++) {
/* 356 */       if (this.mainInventory[i] == null) {
/* 357 */         return i;
/*     */       }
/*     */     }
/*     */     
/* 361 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean isItemBurned(int index)
/*     */   {
/* 366 */     return (index > -1) && (isItemBurned(getStackInSlot(index)));
/*     */   }
/*     */   
/*     */   public static boolean isItemBurned(ItemStack pItemstack) {
/* 370 */     return (pItemstack != null) && (TileEntityFurnace.getItemBurnTime(pItemstack) > 0);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isItemSmelting(int index)
/*     */   {
/* 376 */     return isItemSmelting(getStackInSlot(index));
/*     */   }
/*     */   
/*     */   public static boolean isItemSmelting(ItemStack pItemstack) {
/* 380 */     return (pItemstack != null) && (MMM_Helper.getSmeltingResult(pItemstack) != null);
/*     */   }
/*     */   
/*     */   public boolean isItemExplord(int index)
/*     */   {
/* 385 */     return (index >= 0) && (isItemExplord(getStackInSlot(index)));
/*     */   }
/*     */   
/*     */   public static boolean isItemExplord(ItemStack pItemstack) {
/* 389 */     if (pItemstack == null)
/* 390 */       return false;
/* 391 */     Item li = pItemstack.getItem();
/* 392 */     return (pItemstack != null) && ((li instanceof ItemBlock)) && (Block.getBlockFromItem(li).getMaterial() == Material.tnt);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isChanged(int pIndex)
/*     */   {
/* 398 */     ItemStack lis = getStackInSlot(pIndex);
/* 399 */     return !ItemStack.areItemStacksEqual(lis, this.prevItems[pIndex]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChanged(int pIndex)
/*     */   {
/* 407 */     this.prevItems[pIndex] = new ItemStack(Items.sugar);
/*     */   }
/*     */   
/*     */   public void resetChanged(int pIndex)
/*     */   {
/* 412 */     ItemStack lis = getStackInSlot(pIndex);
/* 413 */     this.prevItems[pIndex] = (lis == null ? null : lis.copy());
/*     */   }
/*     */   
/*     */   public void clearChanged()
/*     */   {
/* 418 */     ItemStack lis = new ItemStack(Items.sugar);
/* 419 */     for (int li = 0; li < this.prevItems.length; li++) {
/* 420 */       this.prevItems[li] = lis;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_InventoryLittleMaid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
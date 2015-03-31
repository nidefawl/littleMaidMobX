/*     */ package littleMaidMobX;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ContainerPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_ContainerInventory extends ContainerPlayer
/*     */ {
/*     */   protected final LMM_InventoryLittleMaid littlemaidInventory;
/*     */   protected final int numRows;
/*     */   protected final LMM_EntityLittleMaid owner;
/*     */   
/*     */   public LMM_ContainerInventory(IInventory iinventory, LMM_EntityLittleMaid pEntity)
/*     */   {
/*  23 */     super(pEntity.maidInventory, !pEntity.worldObj.isRemote, pEntity.maidAvatar);
/*  24 */     this.inventorySlots.clear();
/*  25 */     this.inventoryItemStacks.clear();
/*     */     
/*     */ 
/*  28 */     LMM_InventoryLittleMaid linventory = pEntity.maidInventory;
/*  29 */     this.owner = pEntity;
/*  30 */     this.numRows = (linventory.getSizeInventory() / 9);
/*  31 */     this.littlemaidInventory = linventory;
/*  32 */     this.littlemaidInventory.openInventory();
/*     */     
/*  34 */     for (int ly = 0; ly < this.numRows; ly++) {
/*  35 */       for (int lx = 0; lx < 9; lx++) {
/*  36 */         addSlotToContainer(new Slot(linventory, lx + ly * 9, 8 + lx * 18, 76 + ly * 18));
/*     */       }
/*     */     }
/*     */     
/*  40 */     int lyoffset = (this.numRows - 4) * 18 + 59;
/*  41 */     for (int ly = 0; ly < 3; ly++) {
/*  42 */       for (int lx = 0; lx < 9; lx++) {
/*  43 */         addSlotToContainer(new Slot(iinventory, lx + ly * 9 + 9, 8 + lx * 18, 103 + ly * 18 + lyoffset));
/*     */       }
/*     */     }
/*     */     
/*  47 */     for (int lx = 0; lx < 9; lx++) {
/*  48 */       addSlotToContainer(new Slot(iinventory, lx, 8 + lx * 18, 161 + lyoffset));
/*     */     }
/*     */     
/*  51 */     for (int j = 0; j < 3; j++)
/*     */     {
/*     */ 
/*     */ 
/*  55 */       final int armorIndex = 1 + j;
/*  56 */       addSlotToContainer(new Slot(linventory, linventory.getSizeInventory() - 2 - j, 8, 8 + j * 18)
/*     */       {
/*     */         private static final String __OBFID = "CL_00001755";
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         public int getSlotStackLimit()
/*     */         {
/*  65 */           return 1;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */         public boolean isItemValid(ItemStack par1ItemStack)
/*     */         {
/*  72 */           if (par1ItemStack == null) return false;
/*  73 */           return par1ItemStack.getItem().isValidArmor(par1ItemStack, armorIndex, LMM_ContainerInventory.this.owner);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */         @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */         public IIcon getBackgroundIconIndex()
/*     */         {
/*  81 */           return ItemArmor.func_94602_b(armorIndex);
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canInteractWith(EntityPlayer entityplayer)
/*     */   {
/*  90 */     LMM_EntityLittleMaid entitylittlemaid = this.littlemaidInventory.entityLittleMaid;
/*  91 */     if (entitylittlemaid.isDead)
/*     */     {
/*  93 */       return false;
/*     */     }
/*  95 */     return entityplayer.getDistanceSqToEntity(entitylittlemaid) <= 64.0D;
/*     */   }
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int pIndex)
/*     */   {
/* 100 */     ItemStack litemstack = null;
/* 101 */     Slot slot = (Slot)this.inventorySlots.get(pIndex);
/* 102 */     if ((slot != null) && (slot.getHasStack())) {
/* 103 */       ItemStack itemstack1 = slot.getStack();
/*     */       
/* 105 */       if (!LMM_LittleMaidMobX.isMaidIgnoreItem(itemstack1))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 111 */         litemstack = itemstack1.copy();
/* 112 */         int lline = this.numRows * 9;
/* 113 */         if (pIndex < lline) {
/* 114 */           if (!mergeItemStack(itemstack1, lline, lline + 36, true)) {
/* 115 */             return null;
/*     */           }
/* 117 */         } else if ((pIndex >= lline) && (pIndex < lline + 36)) {
/* 118 */           if (!mergeItemStack(itemstack1, 0, lline, false)) {
/* 119 */             return null;
/*     */           }
/*     */         }
/* 122 */         else if (!mergeItemStack(itemstack1, 0, lline + 36, false)) {
/* 123 */           return null;
/*     */         }
/*     */         
/* 126 */         if (itemstack1.stackSize == 0) {
/* 127 */           slot.putStack(null);
/*     */         } else {
/* 129 */           slot.onSlotChanged();
/*     */         }
/*     */       }
/*     */     }
/* 133 */     return litemstack;
/*     */   }
/*     */   
/*     */   public void onContainerClosed(EntityPlayer par1EntityPlayer)
/*     */   {
/* 138 */     super.onContainerClosed(par1EntityPlayer);
/* 139 */     this.littlemaidInventory.closeInventory();
/*     */   }
/*     */   
/*     */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/*     */   {
/* 144 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_ContainerInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import mmmlibx.lib.ContainerCreative;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ 
/*     */ public class LMM_ContainerTriggerSelect extends ContainerCreative
/*     */ {
/*  18 */   public List<ItemStack> weaponSelect = new ArrayList();
/*     */   public String weaponSelectName;
/*     */   public List<Item> weaponSelectList;
/*     */   public int weaponOffset;
/*     */   
/*     */   public LMM_ContainerTriggerSelect(EntityPlayer entityplayer) {
/*  24 */     super(entityplayer);
/*     */     
/*  26 */     this.inventorySlots.clear();
/*  27 */     for (int l2 = 0; l2 < 5; l2++) {
/*  28 */       for (int j3 = 0; j3 < 8; j3++) {
/*  29 */         addSlotToContainer(new Slot(LMM_GuiTriggerSelect.getInventory1(), j3 + l2 * 8, 8 + j3 * 18, 18 + l2 * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  34 */     for (int l2 = 0; l2 < 4; l2++) {
/*  35 */       for (int j3 = 0; j3 < 8; j3++) {
/*  36 */         addSlotToContainer(new Slot(LMM_GuiTriggerSelect.getInventory2(), j3 + l2 * 8, 8 + j3 * 18, 121 + l2 * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  42 */     setWeaponSelect(mmmlibx.lib.MMM_Helper.getPlayerName(entityplayer), (String)LMM_TriggerSelect.selector.get(0));
/*     */     
/*  44 */     initAllSelections();
/*  45 */     scrollTo(0.0F);
/*  46 */     setWeaponlist(0.0F);
/*     */   }
/*     */   
/*     */   private void initAllSelections()
/*     */   {
/*  51 */     this.itemList.clear();
/*     */     
/*  53 */     for (Object o : Item.itemRegistry.getKeys())
/*     */     {
/*  55 */       Item item = (Item)Item.itemRegistry.getObject(o);
/*     */       
/*  57 */       if ((item != null) && (item.getCreativeTab() != null)) {
/*  58 */         item.getSubItems(item, (CreativeTabs)null, this.itemList);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  63 */     Comparator cmp = new Comparator()
/*     */     {
/*     */       public int compare(ItemStack i1, ItemStack i2)
/*     */       {
/*  67 */         Item item1 = i1.getItem();
/*  68 */         Item item2 = i2.getItem();
/*  69 */         CreativeTabs ct1 = item1.getCreativeTab();
/*  70 */         CreativeTabs ct2 = item2.getCreativeTab();
/*  71 */         if ((ct1 != null) && (ct2 != null))
/*     */         {
/*  73 */           if (ct1.getTabIndex() != ct2.getTabIndex())
/*     */           {
/*  75 */             return ct1.getTabIndex() < ct2.getTabIndex() ? -1 : 1;
/*     */           }
/*     */         }
/*  78 */         if (item1 == item2)
/*     */         {
/*  80 */           System.out.println(i1.getDisplayName() + " : " + i2.getDisplayName());
/*  81 */           return i1.getItemDamage() < i2.getItemDamage() ? -1 : 1;
/*     */         }
/*     */         
/*  84 */         return item1.getUnlocalizedName().compareTo(item2.getUnlocalizedName());
/*     */       }
/*  86 */     };
/*  87 */     java.util.Collections.sort(this.itemList, cmp);
/*     */   }
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer entityplayer)
/*     */   {
/*  92 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void scrollTo(float f)
/*     */   {
/*  98 */     int i = this.itemList.size() / 8 - 5 + 1;
/*  99 */     int j = (int)(f * i + 0.5D);
/* 100 */     if (j < 0) {
/* 101 */       j = 0;
/*     */     }
/* 103 */     for (int k = 0; k < 5; k++) {
/* 104 */       for (int l = 0; l < 8; l++) {
/* 105 */         int i1 = l + (k + j) * 8;
/* 106 */         if ((i1 >= 0) && (i1 < this.itemList.size())) {
/* 107 */           LMM_GuiTriggerSelect.getInventory1().setInventorySlotContents(l + k * 8, (ItemStack)this.itemList.get(i1));
/*     */         } else {
/* 109 */           LMM_GuiTriggerSelect.getInventory1().setInventorySlotContents(l + k * 8, null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack slotClick(int i, int j, int flag, EntityPlayer entityplayer)
/*     */   {
/* 119 */     if (i >= 40)
/*     */     {
/* 121 */       int lk = i - 40 + this.weaponOffset * 8;
/* 122 */       while (this.weaponSelect.size() <= lk + 7) {
/* 123 */         this.weaponSelect.add(null);
/*     */       }
/* 125 */       this.weaponSelect.set(lk, entityplayer.inventory.getItemStack());
/*     */     }
/*     */     
/* 128 */     if (i == 64537) {
/* 129 */       entityplayer.inventory.setItemStack(null);
/*     */     }
/* 131 */     ItemStack is = super.slotClick(i, j, flag, entityplayer);
/*     */     
/* 133 */     return is;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
/*     */   {
/* 139 */     ItemStack itemstack = null;
/* 140 */     Slot slot = (Slot)this.inventorySlots.get(i);
/* 141 */     if ((slot != null) && (slot.getHasStack())) {
/* 142 */       if (i < 40) {
/* 143 */         ItemStack itemstack1 = slot.getStack();
/* 144 */         itemstack = itemstack1.copy();
/* 145 */         mergeItemStack(itemstack1, 40, 72, false);
/*     */       } else {
/* 147 */         slot.putStack(null);
/*     */       }
/*     */     }
/* 150 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean mergeItemStack(ItemStack itemstack, int i, int j, boolean flag)
/*     */   {
/* 156 */     boolean flag1 = false;
/* 157 */     int k = 0;
/*     */     
/*     */ 
/* 160 */     while ((itemstack.stackSize > 0) && (k < this.weaponSelect.size())) {
/* 161 */       ItemStack itemstack1 = (ItemStack)this.weaponSelect.get(k);
/* 162 */       if (itemstack1 != null) {
/* 163 */         if (itemstack1.isItemEqual(itemstack))
/*     */         {
/* 165 */           flag1 = true;
/* 166 */           break;
/*     */         }
/*     */       } else {
/* 169 */         this.weaponSelect.set(k, itemstack);
/* 170 */         flag1 = true;
/* 171 */         break;
/*     */       }
/* 173 */       k++;
/*     */     }
/* 175 */     if (!flag1) {
/* 176 */       this.weaponSelect.add(itemstack);
/* 177 */       setWeaponlist(1.0F);
/*     */     } else {
/* 179 */       int m = this.weaponSelect.size() / 8 - 4 + 1;
/* 180 */       int n = k / 8;
/* 181 */       float f = n / m;
/* 182 */       if (f < 0.0F)
/* 183 */         f = 0.0F;
/* 184 */       if (f > 1.0F)
/* 185 */         f = 1.0F;
/* 186 */       setWeaponlist(f);
/*     */     }
/*     */     
/* 189 */     return flag1;
/*     */   }
/*     */   
/*     */   public void setWeaponlist(float f)
/*     */   {
/* 194 */     int i = this.weaponSelect.size() / 8 - 4 + 1;
/* 195 */     this.weaponOffset = ((int)(f * i + 0.5D));
/* 196 */     if (this.weaponOffset < 0) {
/* 197 */       this.weaponOffset = 0;
/*     */     }
/* 199 */     for (int k = 0; k < 4; k++) {
/* 200 */       for (int l = 0; l < 8; l++) {
/* 201 */         int i1 = l + (k + this.weaponOffset) * 8;
/* 202 */         if ((i1 >= 0) && (i1 < this.weaponSelect.size())) {
/* 203 */           LMM_GuiTriggerSelect.getInventory2().setInventorySlotContents(k * 8 + l, (ItemStack)this.weaponSelect.get(i1));
/*     */         } else {
/* 205 */           LMM_GuiTriggerSelect.getInventory2().setInventorySlotContents(k * 8 + l, null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setWeaponSelect(String pUsername, String pName) {
/* 212 */     this.weaponSelect.clear();
/* 213 */     this.weaponSelectName = pName;
/* 214 */     this.weaponSelectList = LMM_TriggerSelect.getuserTriggerList(pUsername, pName);
/*     */     
/* 216 */     for (Item li : this.weaponSelectList) {
/* 217 */       if (li != null)
/*     */       {
/* 219 */         this.weaponSelect.add(new ItemStack(li));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public List getItemList() {
/* 225 */     return this.weaponSelectList;
/*     */   }
/*     */   
/*     */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/*     */   {
/* 230 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canDragIntoSlot(Slot par1Slot)
/*     */   {
/* 235 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_ContainerTriggerSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
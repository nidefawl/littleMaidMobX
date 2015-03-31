/*     */ package littleMaidMobX;
/*     */ 
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntityBrewingStand;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityMode_Pharmacist extends LMM_EntityModeBlockBase
/*     */ {
/*     */   public static final int mmode_Pharmacist = 34;
/*     */   protected int inventryPos;
/*     */   
/*     */   public LMM_EntityMode_Pharmacist(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  20 */     super(pEntity);
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  25 */     return 6100;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting)
/*     */   {
/*  41 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  42 */     ltasks[0] = pDefaultMove;
/*  43 */     ltasks[1] = pDefaultTargeting;
/*     */     
/*  45 */     this.owner.addMaidMode(ltasks, "Pharmacist", 34);
/*     */   }
/*     */   
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/*  50 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/*  51 */     if ((litemstack != null) && 
/*  52 */       ((litemstack.getItem() instanceof ItemPotion)) && (!MMM_Helper.hasEffect(litemstack))) {
/*  53 */       this.owner.setMaidMode("Pharmacist");
/*  54 */       return true;
/*     */     }
/*     */     
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/*  62 */     switch (pMode) {
/*     */     case 34: 
/*  64 */       this.owner.setBloodsuck(false);
/*  65 */       this.owner.aiJumpTo.setEnable(false);
/*  66 */       this.owner.aiFollow.setEnable(false);
/*  67 */       this.owner.aiAttack.setEnable(false);
/*  68 */       this.owner.aiShooting.setEnable(false);
/*  69 */       this.inventryPos = 0;
/*  70 */       return true;
/*     */     }
/*     */     
/*  73 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/*  82 */     switch (pMode) {
/*     */     case 34: 
/*  84 */       ItemStack litemstack = this.owner.getCurrentEquippedItem();
/*  85 */       if ((this.inventryPos <= 0) || (litemstack == null) || (litemstack.getItem().isPotionIngredient(litemstack))) {
/*  86 */         for (int li = 0; li < 18; li++) {
/*  87 */           litemstack = this.owner.maidInventory.getStackInSlot(li);
/*  88 */           if (litemstack != null)
/*     */           {
/*  90 */             if (((litemstack.getItem() instanceof ItemPotion)) && (!MMM_Helper.hasEffect(litemstack))) {
/*  91 */               return li;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/*  99 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSearchBlock()
/*     */   {
/* 109 */     if (!super.isSearchBlock()) { return false;
/*     */     }
/* 111 */     if (this.owner.getCurrentEquippedItem() != null) {
/* 112 */       this.fDistance = Double.MAX_VALUE;
/* 113 */       this.owner.clearTilePos();
/* 114 */       this.owner.setSneaking(false);
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldBlock(int pMode)
/*     */   {
/* 123 */     return ((this.owner.maidTileEntity instanceof TileEntityBrewingStand)) && ((((TileEntityBrewingStand)this.owner.maidTileEntity).getBrewTime() > 0) || (this.owner.getCurrentEquippedItem() != null) || (this.inventryPos > 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean checkBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 130 */     if (this.owner.getCurrentEquippedItem() == null) {
/* 131 */       return false;
/*     */     }
/* 133 */     net.minecraft.tileentity.TileEntity ltile = this.owner.worldObj.getTileEntity(px, py, pz);
/* 134 */     if (!(ltile instanceof TileEntityBrewingStand)) {
/* 135 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 139 */     checkWorldMaid(ltile);
/*     */     
/* 141 */     if (this.owner.isUsingTile(ltile)) { return true;
/*     */     }
/* 143 */     double ldis = this.owner.getDistanceTilePosSq(ltile);
/* 144 */     if (this.fDistance > ldis) {
/* 145 */       this.owner.setTilePos(ltile);
/* 146 */       this.fDistance = ldis;
/*     */     }
/*     */     
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public boolean executeBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 154 */     TileEntityBrewingStand ltile = (TileEntityBrewingStand)this.owner.maidTileEntity;
/* 155 */     if (this.owner.worldObj.getTileEntity(px, py, pz) != ltile) {
/* 156 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 160 */     boolean lflag = false;
/* 161 */     LMM_SwingStatus lswing = this.owner.getSwingStatusDominant();
/*     */     
/*     */ 
/*     */ 
/* 165 */     if ((ltile.getStackInSlot(0) != null) || (ltile.getStackInSlot(1) != null) || (ltile.getStackInSlot(2) != null) || (ltile.getStackInSlot(3) != null) || (!lswing.canAttack()))
/*     */     {
/* 167 */       this.owner.setWorking(true);
/*     */     }
/*     */     
/* 170 */     if (lswing.canAttack()) {
/* 171 */       ItemStack litemstack2 = ltile.getStackInSlot(3);
/*     */       
/* 173 */       if ((litemstack2 != null) && (ltile.getBrewTime() <= 0))
/*     */       {
/* 175 */         if (py <= this.owner.posY) {
/* 176 */           this.owner.setSneaking(true);
/*     */         }
/* 178 */         lflag = true;
/* 179 */         if (this.owner.maidInventory.addItemStackToInventory(litemstack2)) {
/* 180 */           ltile.setInventorySlotContents(3, null);
/* 181 */           this.owner.playSound("random.pop");
/* 182 */           this.owner.setSwing(5, LMM_EnumSound.Null);
/*     */         }
/*     */       }
/*     */       
/* 186 */       if ((!lflag) && (this.inventryPos > this.owner.maidInventory.mainInventory.length))
/*     */       {
/* 188 */         for (int li = 0; (li < 3) && (!lflag); li++) {
/* 189 */           ItemStack litemstack1 = ltile.getStackInSlot(li);
/* 190 */           if ((litemstack1 != null) && (this.owner.maidInventory.addItemStackToInventory(litemstack1))) {
/* 191 */             ltile.setInventorySlotContents(li, null);
/* 192 */             this.owner.playSound("random.pop");
/* 193 */             this.owner.setSwing(5, LMM_EnumSound.Null);
/* 194 */             lflag = true;
/*     */           }
/*     */         }
/* 197 */         if (!lflag) {
/* 198 */           this.inventryPos = 0;
/* 199 */           this.owner.getNextEquipItem();
/* 200 */           lflag = true;
/*     */         }
/*     */       }
/*     */       
/* 204 */       ItemStack litemstack1 = this.owner.maidInventory.getCurrentItem();
/* 205 */       if ((!lflag) && (litemstack1 != null) && ((litemstack1.getItem() instanceof ItemPotion)) && (!MMM_Helper.hasEffect(litemstack1)))
/*     */       {
/* 207 */         int li = 0;
/* 208 */         for (li = 0; (li < 3) && (!lflag); li++) {
/* 209 */           if (ltile.getStackInSlot(li) == null) {
/* 210 */             ltile.setInventorySlotContents(li, litemstack1);
/* 211 */             this.owner.maidInventory.setInventoryCurrentSlotContents(null);
/* 212 */             this.owner.playSound("random.pop");
/* 213 */             this.owner.setSwing(5, LMM_EnumSound.Null);
/* 214 */             this.owner.getNextEquipItem();
/* 215 */             lflag = true;
/*     */           }
/*     */         }
/*     */       }
/* 219 */       if ((!lflag) && ((ltile.getStackInSlot(0) != null) || (ltile.getStackInSlot(1) != null) || (ltile.getStackInSlot(2) != null)) && ((this.owner.maidInventory.currentItem == -1) || ((litemstack1 != null) && ((litemstack1.getItem() instanceof ItemPotion)) && (!MMM_Helper.hasEffect(litemstack1))))) {
/* 223 */         for (; 
/*     */             
/*     */ 
/* 223 */             this.inventryPos < this.owner.maidInventory.mainInventory.length; this.inventryPos += 1) {
/* 224 */           litemstack1 = this.owner.maidInventory.getStackInSlot(this.inventryPos);
/* 225 */           if ((litemstack1 != null) && (!(litemstack1.getItem() instanceof ItemPotion))) {
/* 226 */             this.owner.setEquipItem(this.inventryPos);
/* 227 */             lflag = true;
/* 228 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 233 */       if ((!lflag) && (litemstack2 == null) && ((ltile.getStackInSlot(0) != null) || (ltile.getStackInSlot(1) != null) || (ltile.getStackInSlot(2) != null)))
/*     */       {
/* 235 */         if ((litemstack1 != null) && (!(litemstack1.getItem() instanceof ItemPotion)) && (litemstack1.getItem().isPotionIngredient(litemstack1))) {
/* 236 */           ltile.setInventorySlotContents(3, litemstack1);
/* 237 */           this.owner.maidInventory.setInventorySlotContents(this.inventryPos, null);
/* 238 */           this.owner.playSound("random.pop");
/* 239 */           this.owner.setSwing(15, LMM_EnumSound.Null);
/* 240 */           lflag = true;
/*     */         }
/* 242 */         else if ((litemstack1 == null) || (((litemstack1.getItem() instanceof ItemPotion)) && (MMM_Helper.hasEffect(litemstack1))) || (!litemstack1.getItem().isPotionIngredient(litemstack1)))
/*     */         {
/* 244 */           this.inventryPos = this.owner.maidInventory.mainInventory.length;
/* 245 */           lflag = true;
/*     */         }
/* 247 */         this.inventryPos += 1;
/*     */         
/* 249 */         this.owner.setEquipItem(this.inventryPos);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 254 */       if ((this.owner.getSwingStatusDominant().index == -1) && (litemstack2 == null)) {
/* 255 */         this.owner.getNextEquipItem();
/*     */       }
/*     */     } else {
/* 258 */       lflag = true;
/*     */     }
/* 260 */     if ((ltile.getBrewTime() > 0) || (this.inventryPos > 0)) {
/* 261 */       this.owner.setWorking(true);
/* 262 */       lflag = true;
/*     */     }
/* 264 */     return lflag;
/*     */   }
/*     */   
/*     */   public void startBlock(int pMode)
/*     */   {
/* 269 */     this.inventryPos = 0;
/*     */   }
/*     */   
/*     */   public void resetBlock(int pMode)
/*     */   {
/* 274 */     this.owner.setSneaking(false);
/*     */   }
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound)
/*     */   {
/* 279 */     this.inventryPos = par1nbtTagCompound.getInteger("InventryPos");
/*     */   }
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound)
/*     */   {
/* 284 */     par1nbtTagCompound.setInteger("InventryPos", this.inventryPos);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Pharmacist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
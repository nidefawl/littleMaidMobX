/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityMode_Cooking extends LMM_EntityModeBlockBase
/*     */ {
/*     */   public static final int mmode_Cooking = 33;
/*     */   
/*     */   public LMM_EntityMode_Cooking(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  17 */     super(pEntity);
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  22 */     return 6000;
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
/*  38 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  39 */     ltasks[0] = pDefaultMove;
/*  40 */     ltasks[1] = new EntityAITasks(this.owner.aiProfiler);
/*     */     
/*  42 */     this.owner.addMaidMode(ltasks, "Cooking", 33);
/*     */   }
/*     */   
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/*  47 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/*  48 */     if ((litemstack != null) && 
/*  49 */       (this.owner.maidInventory.isItemBurned(0))) {
/*  50 */       this.owner.setMaidMode("Cooking");
/*  51 */       return true;
/*     */     }
/*     */     
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/*  59 */     switch (pMode) {
/*     */     case 33: 
/*  61 */       this.owner.setBloodsuck(false);
/*  62 */       this.owner.aiJumpTo.setEnable(false);
/*  63 */       this.owner.aiFollow.setEnable(false);
/*  64 */       this.owner.aiAvoidPlayer.setEnable(false);
/*  65 */       this.owner.aiAttack.setEnable(false);
/*  66 */       this.owner.aiShooting.setEnable(false);
/*  67 */       return true;
/*     */     }
/*     */     
/*  70 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/*  79 */     switch (pMode) {
/*     */     case 33: 
/*  81 */       for (int li = 0; li < 18; li++)
/*     */       {
/*  83 */         if (this.owner.maidInventory.isItemBurned(li)) {
/*  84 */           return li;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     
/*  90 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/*  95 */     return (LMM_InventoryLittleMaid.isItemBurned(pItemStack)) || (LMM_InventoryLittleMaid.isItemSmelting(pItemStack));
/*     */   }
/*     */   
/*     */   public boolean isSearchBlock()
/*     */   {
/* 100 */     if (!super.isSearchBlock()) { return false;
/*     */     }
/*     */     
/* 103 */     if ((this.owner.getCurrentEquippedItem() != null) && (this.owner.maidInventory.getSmeltingItem() > -1)) {
/* 104 */       this.fDistance = Double.MAX_VALUE;
/* 105 */       this.owner.clearTilePos();
/* 106 */       this.owner.setSneaking(false);
/* 107 */       return true;
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public boolean shouldBlock(int pMode)
/*     */   {
/* 114 */     return ((this.owner.maidTileEntity instanceof TileEntityFurnace)) && ((((TileEntityFurnace)this.owner.maidTileEntity).isBurning()) || (LMM_InventoryLittleMaid.isItemBurned(this.owner.getCurrentEquippedItem())));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean checkBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 121 */     net.minecraft.tileentity.TileEntity ltile = this.owner.worldObj.getTileEntity(px, py, pz);
/* 122 */     if (!(ltile instanceof TileEntityFurnace)) {
/* 123 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 127 */     if (checkWorldMaid(ltile)) { return false;
/*     */     }
/* 129 */     if (this.owner.isUsingTile(ltile)) { return true;
/*     */     }
/* 131 */     double ldis = this.owner.getDistanceTilePosSq(ltile);
/* 132 */     if (this.fDistance > ldis) {
/* 133 */       this.owner.setTilePos(ltile);
/* 134 */       this.fDistance = ldis;
/*     */     }
/*     */     
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public boolean executeBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 142 */     if (!this.owner.isEqualTile()) {
/* 143 */       return false;
/*     */     }
/*     */     
/* 146 */     TileEntityFurnace ltile = (TileEntityFurnace)this.owner.maidTileEntity;
/*     */     
/* 148 */     boolean lflag = false;
/*     */     
/*     */ 
/* 151 */     if (this.owner.getSwingStatusDominant().canAttack())
/*     */     {
/* 153 */       ItemStack litemstack = ltile.getStackInSlot(2);
/* 154 */       if (litemstack != null) {
/* 155 */         if (litemstack.stackSize > 0) {
/* 156 */           int li = litemstack.stackSize;
/* 157 */           if (this.owner.maidInventory.addItemStackToInventory(litemstack)) {
/* 158 */             dropExpOrb(litemstack, li - litemstack.stackSize);
/* 159 */             this.owner.playSound("random.pop");
/* 160 */             this.owner.setSwing(5, LMM_EnumSound.cookingOver);
/*     */             
/* 162 */             this.owner.getNextEquipItem();
/*     */             
/* 164 */             lflag = true;
/*     */           }
/*     */         }
/* 167 */         ltile.setInventorySlotContents(2, null);
/*     */       }
/*     */       
/*     */ 
/* 171 */       if ((!lflag) && (ltile.getStackInSlot(0) == null)) {
/* 172 */         litemstack = ltile.getStackInSlot(2);
/* 173 */         int li = this.owner.maidInventory.getSmeltingItem();
/* 174 */         this.owner.setEquipItem(li);
/* 175 */         if (li > -1) {
/* 176 */           litemstack = this.owner.maidInventory.getStackInSlot(li);
/*     */           
/* 178 */           if (litemstack.stackSize >= ltile.getInventoryStackLimit()) {
/* 179 */             ltile.setInventorySlotContents(0, litemstack.splitStack(ltile.getInventoryStackLimit()));
/*     */           } else {
/* 181 */             ltile.setInventorySlotContents(0, litemstack.splitStack(litemstack.stackSize));
/*     */           }
/* 183 */           if (litemstack.stackSize <= 0) {
/* 184 */             this.owner.maidInventory.setInventorySlotContents(li, null);
/*     */           }
/* 186 */           this.owner.playSound("random.pop");
/* 187 */           this.owner.setSwing(5, LMM_EnumSound.cookingStart);
/* 188 */           lflag = true;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 193 */       if ((!lflag) && (ltile.getStackInSlot(1) == null) && (ltile.getStackInSlot(0) != null)) {
/* 194 */         this.owner.getNextEquipItem();
/* 195 */         litemstack = this.owner.getCurrentEquippedItem();
/* 196 */         if (LMM_InventoryLittleMaid.isItemBurned(litemstack)) {
/* 197 */           if (litemstack.stackSize >= ltile.getInventoryStackLimit()) {
/* 198 */             ltile.setInventorySlotContents(1, litemstack.splitStack(ltile.getInventoryStackLimit()));
/*     */           } else {
/* 200 */             ltile.setInventorySlotContents(1, litemstack.splitStack(litemstack.stackSize));
/*     */           }
/* 202 */           if (litemstack.stackSize <= 0) {
/* 203 */             this.owner.maidInventory.setInventoryCurrentSlotContents(null);
/*     */           }
/* 205 */           this.owner.getNextEquipItem();
/* 206 */           this.owner.playSound("random.pop");
/* 207 */           this.owner.setSwing(5, LMM_EnumSound.addFuel);
/* 208 */           lflag = true;
/*     */         }
/* 210 */         else if (ltile.isBurning()) {
/* 211 */           lflag = true;
/*     */         }
/*     */         else {
/* 214 */           ItemStack litemstack2 = ltile.getStackInSlotOnClosing(0);
/* 215 */           if (this.owner.maidInventory.addItemStackToInventory(litemstack2)) {
/* 216 */             this.owner.playSound("random.pop");
/* 217 */             this.owner.setSwing(5, LMM_EnumSound.Null);
/* 218 */             this.owner.getNextEquipItem();
/* 219 */             lflag = false;
/*     */           } else {
/* 221 */             ltile.setInventorySlotContents(0, litemstack2);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 228 */       if ((!lflag) && (!ltile.isBurning()) && (ltile.getStackInSlot(1) != null)) {
/* 229 */         ItemStack litemstack2 = ltile.getStackInSlotOnClosing(1);
/* 230 */         if (this.owner.maidInventory.addItemStackToInventory(litemstack2)) {
/* 231 */           this.owner.playSound("random.pop");
/* 232 */           this.owner.setSwing(5, LMM_EnumSound.Null);
/* 233 */           this.owner.getNextEquipItem();
/* 234 */           lflag = LMM_InventoryLittleMaid.isItemBurned(this.owner.getCurrentEquippedItem());
/*     */         } else {
/* 236 */           ltile.setInventorySlotContents(1, litemstack2);
/*     */         }
/*     */       }
/*     */     } else {
/* 240 */       lflag = true;
/*     */     }
/* 242 */     if (ltile.isBurning()) {
/* 243 */       this.owner.setWorking(true);
/* 244 */       this.owner.setSneaking(py - (int)this.owner.posY <= 0);
/* 245 */       lflag = true;
/*     */     }
/*     */     
/* 248 */     return lflag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startBlock(int pMode) {}
/*     */   
/*     */ 
/*     */   public void resetBlock(int pMode)
/*     */   {
/* 258 */     this.owner.setSneaking(false);
/*     */   }
/*     */   
/*     */ 
/*     */   public void dropExpOrb(ItemStack pItemStack, int pCount)
/*     */   {
/* 264 */     if (!this.owner.worldObj.isRemote) {
/* 265 */       float var3 = pItemStack.getItem().getSmeltingExperience(pItemStack);
/*     */       
/*     */ 
/* 268 */       if (var3 == 0.0F) {
/* 269 */         pCount = 0;
/* 270 */       } else if (var3 < 1.0F) {
/* 271 */         int var4 = MathHelper.floor_float(pCount * var3);
/*     */         
/* 273 */         if ((var4 < MathHelper.ceiling_float_int(pCount * var3)) && ((float)Math.random() < pCount * var3 - var4)) {
/* 274 */           var4++;
/*     */         }
/*     */         
/* 277 */         pCount = var4 == 0 ? 1 : var4;
/*     */       }
/*     */       
/* 280 */       while (pCount > 0) {
/* 281 */         int var4 = EntityXPOrb.getXPSplit(pCount);
/* 282 */         pCount -= var4;
/* 283 */         this.owner.worldObj.spawnEntityInWorld(new EntityXPOrb(this.owner.worldObj, this.owner.posX, this.owner.posY + 0.5D, this.owner.posZ + 0.5D, var4));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Cooking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
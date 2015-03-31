/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.material.MaterialLiquid;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityMode_Torcher extends LMM_EntityModeBase
/*     */ {
/*     */   public static final int mmode_Torcher = 32;
/*     */   
/*     */   public LMM_EntityMode_Torcher(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  22 */     super(pEntity);
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  27 */     return 6200;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init()
/*     */   {
/*  38 */     LMM_TriggerSelect.appendTriggerItem(null, "Torch", "");
/*     */   }
/*     */   
/*     */ 
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting)
/*     */   {
/*  44 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  45 */     ltasks[0] = pDefaultMove;
/*  46 */     ltasks[1] = pDefaultTargeting;
/*     */     
/*  48 */     this.owner.addMaidMode(ltasks, "Torcher", 32);
/*     */   }
/*     */   
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/*  53 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/*  54 */     if ((litemstack != null) && (
/*  55 */       (litemstack.getItem() == Item.getItemFromBlock(Blocks.torch)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Torch", litemstack)))) {
/*  56 */       this.owner.setMaidMode("Torcher");
/*  57 */       return true;
/*     */     }
/*     */     
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/*  65 */     switch (pMode) {
/*     */     case 32: 
/*  67 */       this.owner.setBloodsuck(false);
/*  68 */       this.owner.aiAttack.setEnable(false);
/*  69 */       this.owner.aiShooting.setEnable(false);
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
/*     */     case 32: 
/*  84 */       for (int li = 0; li < 18; li++) {
/*  85 */         ItemStack litemstack = this.owner.maidInventory.getStackInSlot(li);
/*  86 */         if (litemstack != null)
/*     */         {
/*     */ 
/*  89 */           if ((litemstack.getItem() == Item.getItemFromBlock(Blocks.torch)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Torch", litemstack))) {
/*  90 */             return li;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  96 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/* 101 */     return pItemStack.getItem() == Item.getItemFromBlock(Blocks.torch);
/*     */   }
/*     */   
/*     */   public boolean isSearchBlock()
/*     */   {
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldBlock(int pMode)
/*     */   {
/* 111 */     return this.owner.getCurrentEquippedItem() != null;
/*     */   }
/*     */   
/*     */   protected int getBlockLighting(int i, int j, int k) {
/* 115 */     World worldObj = this.owner.worldObj;
/* 116 */     if ((worldObj.getBlock(i, j - 1, k).getMaterial().isSolid()) && (worldObj.getBlock(i, j, k) == Blocks.air)) {
/* 117 */       return worldObj.getBlockLightValue(i, j, k);
/*     */     }
/* 119 */     return 32;
/*     */   }
/*     */   
/*     */   public boolean checkBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 124 */     int v = getBlockLighting(px, py, pz);
/* 125 */     if ((v < 8) && (canBlockBeSeen(px, py - 1, pz, true, true, false)) && 
/* 126 */       (this.owner.getNavigator().tryMoveToXYZ(px, py, pz, 1.0D))) {
/* 127 */       this.owner.playSound(LMM_EnumSound.findTarget_D, false);
/* 128 */       return true;
/*     */     }
/*     */     
/* 131 */     return false;
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
/*     */   public boolean executeBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 154 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canPlaceItemBlockOnSide(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack, ItemBlock pItemBlock)
/*     */   {
/* 160 */     Block var8 = par1World.getBlock(par2, par3, par4);
/*     */     
/* 162 */     if (Block.isEqualTo(var8, Blocks.snow)) {
/* 163 */       par5 = 1;
/* 164 */     } else if ((!Block.isEqualTo(var8, Blocks.vine)) && (!Block.isEqualTo(var8, Blocks.tallgrass)) && (!Block.isEqualTo(var8, Blocks.deadbush)))
/*     */     {
/* 166 */       if (par5 == 0) {
/* 167 */         par3--;
/*     */       }
/* 169 */       if (par5 == 1) {
/* 170 */         par3++;
/*     */       }
/* 172 */       if (par5 == 2) {
/* 173 */         par4--;
/*     */       }
/* 175 */       if (par5 == 3) {
/* 176 */         par4++;
/*     */       }
/* 178 */       if (par5 == 4) {
/* 179 */         par2--;
/*     */       }
/* 181 */       if (par5 == 5) {
/* 182 */         par2++;
/*     */       }
/*     */     }
/*     */     
/* 186 */     Material lmat = par1World.getBlock(par2, par3, par4).getMaterial();
/* 187 */     if ((lmat instanceof MaterialLiquid)) {
/* 188 */       return false;
/*     */     }
/*     */     
/* 191 */     return par1World.canPlaceEntityOnSide(Block.getBlockFromItem(pItemBlock), par2, par3, par4, false, par5, (net.minecraft.entity.Entity)null, par7ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */   public void updateAITick(int pMode)
/*     */   {
/* 197 */     if ((pMode == 32) && (this.owner.getNextEquipItem())) {
/* 198 */       ItemStack lis = this.owner.getCurrentEquippedItem();
/* 199 */       int lic = lis.stackSize;
/* 200 */       Item lii = lis.getItem();
/* 201 */       World lworld = this.owner.worldObj;
/*     */       
/*     */ 
/* 204 */       int lxx = MathHelper.floor_double(this.owner.posX);
/* 205 */       int lyy = MathHelper.floor_double(this.owner.posY);
/* 206 */       int lzz = MathHelper.floor_double(this.owner.posZ);
/* 207 */       int lym = MathHelper.floor_float(this.owner.height) + 1;
/*     */       
/* 209 */       int ll = 8;
/* 210 */       int ltx = lxx;int lty = lyy;int ltz = lzz;
/* 211 */       int[] lil = { lyy, lyy - 1, lyy + 1 };
/* 212 */       this.owner.maidAvatar.getValue();
/* 213 */       for (int x = -1; x < 2; x++) {
/* 214 */         for (int z = -1; z < 2; z++) {
/* 215 */           for (int lyi : lil) {
/* 216 */             int lv = lworld.getBlockLightValue(lxx + x, lyi, lzz + z);
/* 217 */             if ((ll > lv) && ((lii instanceof ItemBlock)) && (canPlaceItemBlockOnSide(lworld, lxx + x, lyi - 1, lzz + z, 1, this.owner.maidAvatar, lis, (ItemBlock)lii)) && (canBlockBeSeen(lxx + x, lyi - 1, lzz + z, true, false, true)))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 224 */               ll = lv;
/* 225 */               ltx = lxx + x;
/* 226 */               lty = lyi - 1;
/* 227 */               ltz = lzz + z;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 234 */       if ((ll < 8) && (lis.tryPlaceItemIntoWorld(this.owner.maidAvatar, this.owner.worldObj, ltx, lty, ltz, 1, 0.5F, 1.0F, 0.5F)))
/*     */       {
/* 236 */         this.owner.setSwing(10, LMM_EnumSound.installation);
/* 237 */         this.owner.getNavigator().clearPathEntity();
/* 238 */         if (this.owner.maidAvatar.capabilities.isCreativeMode) {
/* 239 */           lis.stackSize = lic;
/*     */         }
/* 241 */         if (lis.stackSize <= 0) {
/* 242 */           this.owner.maidInventory.setInventoryCurrentSlotContents(null);
/* 243 */           this.owner.getNextEquipItem();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Torcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
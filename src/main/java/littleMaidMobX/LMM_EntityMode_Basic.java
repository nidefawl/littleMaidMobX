/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest2;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemAppleGold;
/*     */ import net.minecraft.item.ItemBucketMilk;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityMode_Basic extends LMM_EntityModeBlockBase
/*     */ {
/*     */   public static final int mmode_Wild = 0;
/*     */   public static final int mmode_Escorter = 1;
/*     */   private IInventory myInventory;
/*     */   private IInventory myChest;
/*     */   private List<IInventory> fusedTiles;
/*     */   private boolean isWorking;
/*     */   private double lastdistance;
/*     */   private int maidSearchCount;
/*     */   
/*     */   public LMM_EntityMode_Basic(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  41 */     super(pEntity);
/*  42 */     this.fusedTiles = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int priority()
/*     */   {
/*  49 */     return 9000;
/*     */   }
/*     */   
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting)
/*     */   {
/*  70 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  71 */     ltasks[0] = new EntityAITasks(null);
/*  72 */     ltasks[1] = new EntityAITasks(null);
/*     */     
/*  74 */     ltasks[0].addTask(1, this.owner.aiSwiming);
/*  75 */     ltasks[0].addTask(2, this.owner.aiAttack);
/*  76 */     ltasks[0].addTask(3, this.owner.aiPanic);
/*  77 */     ltasks[0].addTask(4, this.owner.aiBegMove);
/*  78 */     ltasks[0].addTask(4, this.owner.aiBeg);
/*  79 */     ltasks[0].addTask(5, this.owner.aiRestrictRain);
/*  80 */     ltasks[0].addTask(6, this.owner.aiFreeRain);
/*     */     
/*     */ 
/*     */ 
/*  84 */     ltasks[0].addTask(9, this.owner.aiCollectItem);
/*  85 */     ltasks[0].addTask(10, new EntityAILeapAtTarget(this.owner, 0.3F));
/*  86 */     ltasks[0].addTask(11, this.owner.aiWander);
/*  87 */     ltasks[0].addTask(12, new EntityAIWatchClosest2(this.owner, net.minecraft.entity.EntityLivingBase.class, 10.0F, 0.02F));
/*  88 */     ltasks[0].addTask(13, new EntityAIWatchClosest2(this.owner, LMM_EntityLittleMaid.class, 10.0F, 0.02F));
/*  89 */     ltasks[0].addTask(13, new EntityAIWatchClosest2(this.owner, EntityPlayer.class, 10.0F, 0.02F));
/*  90 */     ltasks[0].addTask(13, new EntityAILookIdle(this.owner));
/*     */     
/*  92 */     ltasks[1].addTask(1, new LMM_EntityAIHurtByTarget(this.owner, false));
/*     */     
/*  94 */     this.owner.addMaidMode(ltasks, "Wild", 0);
/*     */     
/*     */ 
/*  97 */     ltasks = new EntityAITasks[2];
/*  98 */     ltasks[0] = pDefaultMove;
/*  99 */     ltasks[1] = pDefaultTargeting;
/* 100 */     this.owner.addMaidMode(ltasks, "Escorter", 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/* 107 */     this.owner.setMaidMode("Escorter");
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/* 113 */     switch (pMode) {
/*     */     case 0: 
/* 115 */       this.owner.setFreedom(true);
/*     */       
/* 117 */       return true;
/*     */     case 1: 
/* 119 */       this.owner.aiAvoidPlayer.setEnable(false);
/* 120 */       for (int li = 0; li < this.owner.mstatSwingStatus.length; li++) {
/* 121 */         this.owner.setEquipItem(li, -1);
/*     */       }
/* 123 */       return true;
/*     */     }
/*     */     
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/* 131 */     return pMode == 0 ? 0 : -1;
/*     */   }
/*     */   
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/* 136 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSearchBlock()
/*     */   {
/* 141 */     if ((this.owner.getMaidModeInt() == 1) && (this.owner.isFreedom()) && (this.owner.maidInventory.getFirstEmptyStack() == -1))
/*     */     {
/*     */ 
/* 144 */       this.fDistance = 100.0D;
/* 145 */       return this.myInventory == null;
/*     */     }
/*     */     
/*     */ 
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public boolean shouldBlock(int pMode)
/*     */   {
/* 154 */     return this.myInventory instanceof TileEntity;
/*     */   }
/*     */   
/*     */   public boolean checkBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 159 */     TileEntity ltile = this.owner.worldObj.getTileEntity(px, py, pz);
/* 160 */     if (!(ltile instanceof IInventory)) {
/* 161 */       return false;
/*     */     }
/* 163 */     if (((IInventory)ltile).getSizeInventory() < 18)
/*     */     {
/* 165 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 169 */     if (checkWorldMaid(ltile)) { return false;
/*     */     }
/* 171 */     if (this.fusedTiles.contains(ltile))
/*     */     {
/* 173 */       return false;
/*     */     }
/*     */     
/* 176 */     double ldis = this.owner.getDistanceTilePosSq(ltile);
/* 177 */     if (this.fDistance > ldis) {
/* 178 */       this.myInventory = ((IInventory)ltile);
/* 179 */       this.fDistance = ldis;
/*     */     }
/* 181 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean overlooksBlock(int pMode)
/*     */   {
/* 187 */     List<Entity> list = this.owner.worldObj.getEntitiesWithinAABB(IInventory.class, this.owner.boundingBox.expand(8.0D, 2.0D, 8.0D));
/* 188 */     double cartl = 256.0D;
/* 189 */     for (Entity lentity : list) {
/* 190 */       if (!this.fusedTiles.contains(lentity))
/* 191 */         if (((IInventory)lentity).getSizeInventory() >= 18)
/*     */         {
/*     */ 
/*     */ 
/* 195 */           double lr = lentity.getDistanceSqToEntity(this.owner);
/*     */           
/*     */ 
/* 198 */           if ((this.fDistance > lr) && (this.owner.getEntitySenses().canSee(lentity))) {
/* 199 */             this.myInventory = ((IInventory)lentity);
/* 200 */             this.fDistance = lr;
/*     */           }
/*     */         }
/*     */     }
/* 204 */     this.lastdistance = -1.0D;
/* 205 */     this.myChest = null;
/* 206 */     this.maidSearchCount = 0;
/* 207 */     if ((this.myInventory instanceof TileEntity)) {
/* 208 */       this.owner.setTilePos((TileEntity)this.myInventory);
/* 209 */       return this.myInventory != null;
/*     */     }
/* 211 */     this.owner.setTarget((Entity)this.myInventory);
/* 212 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startBlock(int pMode) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetBlock(int pMode)
/*     */   {
/* 223 */     clearMy();
/*     */   }
/*     */   
/*     */   protected void clearMy()
/*     */   {
/* 228 */     this.myInventory = null;
/* 229 */     if (this.myChest != null) {
/* 230 */       this.myChest.closeInventory();
/* 231 */       this.myChest = null;
/*     */     }
/* 233 */     this.owner.clearTilePos();
/* 234 */     this.owner.setTarget(null);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean executeBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 240 */     if ((this.myInventory instanceof TileEntityChest))
/*     */     {
/* 242 */       TileEntityChest lchest = (TileEntityChest)this.myInventory;
/* 243 */       if (!lchest.isInvalid())
/*     */       {
/* 245 */         if (MMM_Helper.canBlockBeSeen(this.owner, lchest.xCoord, lchest.yCoord, lchest.zCoord, false, true, false)) {
/* 246 */           if (this.myChest == null) {
/* 247 */             getChest();
/* 248 */             if (this.myChest != null) {
/* 249 */               this.myChest.openInventory();
/*     */             }
/*     */             else {
/* 252 */               this.myInventory = null;
/*     */             }
/*     */           }
/*     */           
/* 256 */           this.owner.setWorking(true);
/* 257 */           putChest();
/* 258 */           return true;
/*     */         }
/*     */         
/* 261 */         clearMy();
/*     */       }
/*     */       else
/*     */       {
/* 265 */         clearMy();
/*     */       }
/*     */     }
/*     */     else {
/* 269 */       if (this.myInventory != null) {
/* 270 */         this.fusedTiles.add(this.myInventory);
/*     */       }
/* 272 */       clearMy();
/*     */     }
/* 274 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean outrangeBlock(int pMode, int pX, int pY, int pZ)
/*     */   {
/* 280 */     boolean lf = false;
/* 281 */     if (!this.owner.isMaidWaitEx()) {
/*     */       double distance;
/* 283 */       if ((this.myInventory instanceof TileEntity)) {
/* 284 */         double distance = this.owner.getDistanceTilePos();
/* 285 */         if (distance == this.lastdistance)
/*     */         {
/*     */ 
/* 288 */           LMM_LittleMaidMobX.Debug("Assert.", new Object[0]);
/* 289 */           this.owner.updateWanderPath();
/* 290 */           lf = true;
/*     */         } else {
/* 292 */           lf = MMM_Helper.setPathToTile(this.owner, (TileEntity)this.myInventory, false);
/*     */         }
/*     */       } else {
/* 295 */         distance = 0.0D;
/*     */       }
/* 297 */       this.lastdistance = distance;
/*     */       
/* 299 */       if (this.myChest != null) {
/* 300 */         this.myChest.closeInventory();
/* 301 */         this.myChest = null;
/*     */       }
/*     */     }
/* 304 */     return lf;
/*     */   }
/*     */   
/*     */   public void farrangeBlock()
/*     */   {
/* 309 */     super.farrangeBlock();
/* 310 */     clearMy();
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean getChest()
/*     */   {
/* 316 */     if (this.myInventory == null) {
/* 317 */       return false;
/*     */     }
/*     */     
/* 320 */     this.fusedTiles.add(this.myInventory);
/* 321 */     if ((this.myInventory instanceof TileEntityChest)) {
/* 322 */       TileEntityChest lchest = (TileEntityChest)this.myInventory;
/* 323 */       if (!lchest.adjacentChestChecked) {
/* 324 */         lchest.checkForAdjacentChests();
/*     */       }
/* 326 */       this.fusedTiles.add(lchest.adjacentChestXNeg);
/* 327 */       this.fusedTiles.add(lchest.adjacentChestXPos);
/* 328 */       this.fusedTiles.add(lchest.adjacentChestZNeg);
/* 329 */       this.fusedTiles.add(lchest.adjacentChestZPos);
/*     */     }
/*     */     
/* 332 */     TileEntity ltile = (TileEntity)this.myInventory;
/* 333 */     Block lblock = this.owner.worldObj.getBlock(ltile.xCoord, ltile.yCoord, ltile.zCoord);
/* 334 */     this.myChest = this.myInventory;
/* 335 */     if ((lblock instanceof BlockChest)) {
/* 336 */       this.myChest = ((BlockChest)lblock).func_149951_m(this.owner.worldObj, ltile.xCoord, ltile.yCoord, ltile.zCoord);
/*     */     }
/*     */     
/* 339 */     return this.myChest != null;
/*     */   }
/*     */   
/*     */   protected void putChest()
/*     */   {
/* 344 */     if ((this.owner.getSwingStatusDominant().canAttack()) && (this.myChest != null))
/*     */     {
/*     */ 
/* 347 */       LMM_LittleMaidMobX.Debug(String.format("getChest:%d", new Object[] { Integer.valueOf(this.maidSearchCount) }), new Object[0]);
/* 348 */       ItemStack is; while (((is = this.owner.maidInventory.getStackInSlot(this.maidSearchCount)) == null) && (this.maidSearchCount < this.owner.maidInventory.mainInventory.length)) {
/* 349 */         this.maidSearchCount += 1;
/*     */       }
/* 351 */       if ((is != null) && (is.getItem() != Items.sugar) && (is.getItem() != Items.clock) && (is != this.owner.maidInventory.armorItemInSlot(3)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 359 */         boolean f = false;
/* 360 */         for (int j = 0; (j < this.myChest.getSizeInventory()) && (is.stackSize > 0); j++)
/*     */         {
/* 362 */           ItemStack isc = this.myChest.getStackInSlot(j);
/* 363 */           if (isc == null)
/*     */           {
/*     */ 
/* 366 */             this.myChest.setInventorySlotContents(j, is.copy());
/* 367 */             is.stackSize = 0;
/* 368 */             f = true;
/* 369 */             break;
/*     */           }
/* 371 */           if ((isc.isStackable()) && (isc.isItemEqual(is)))
/*     */           {
/*     */ 
/* 374 */             f = true;
/* 375 */             isc.stackSize += is.stackSize;
/* 376 */             if (isc.stackSize > isc.getMaxStackSize())
/*     */             {
/* 378 */               isc.stackSize -= isc.getMaxStackSize();
/* 379 */               isc.stackSize = isc.getMaxStackSize();
/*     */             } else {
/* 381 */               is.stackSize = 0;
/* 382 */               break;
/*     */             }
/*     */           }
/*     */         }
/* 386 */         if (is.stackSize <= 0) {
/* 387 */           this.owner.maidInventory.setInventorySlotContents(this.maidSearchCount, null);
/*     */         }
/* 389 */         if (f) {
/* 390 */           this.owner.playSound("random.pop");
/* 391 */           this.owner.setSwing(2, LMM_EnumSound.Null);
/*     */         }
/*     */       }
/*     */       
/* 395 */       if (++this.maidSearchCount >= this.owner.maidInventory.mainInventory.length)
/*     */       {
/*     */ 
/* 398 */         clearMy();
/* 399 */         this.lastdistance = 0.0D;
/* 400 */         LMM_LittleMaidMobX.Debug("endChest.", new Object[0]);
/*     */         
/* 402 */         if (this.owner.maidInventory.getFirstEmptyStack() > -1) {
/* 403 */           LMM_LittleMaidMobX.Debug("Search clear.", new Object[0]);
/* 404 */           this.fusedTiles.clear();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean attackEntityAsMob(int pMode, Entity pEntity)
/*     */   {
/* 412 */     if (pEntity == this.myInventory)
/*     */     {
/* 414 */       Entity lentity = (Entity)this.myInventory;
/* 415 */       if (!lentity.isDead) {
/* 416 */         if (this.owner.getDistanceSqToEntity(lentity) < 5.0D) {
/* 417 */           this.owner.getNavigator().clearPathEntity();
/* 418 */           if (this.myChest == null) {
/* 419 */             this.myChest = ((IInventory)lentity);
/* 420 */             this.fusedTiles.add(this.myChest);
/* 421 */             this.myChest.openInventory();
/*     */           }
/* 423 */           if (this.myChest != null) {
/* 424 */             this.owner.getLookHelper().setLookPositionWithEntity(lentity, 30.0F, 40.0F);
/*     */           }
/*     */           
/* 427 */           putChest();
/*     */ 
/*     */         }
/* 430 */         else if (!this.owner.isMaidWaitEx()) {
/* 431 */           double distance = this.owner.getDistanceSqToEntity(lentity);
/* 432 */           if (distance == this.lastdistance)
/*     */           {
/* 434 */             LMM_LittleMaidMobX.Debug("Assert.", new Object[0]);
/* 435 */             this.owner.updateWanderPath();
/*     */           } else {
/* 437 */             this.owner.getNavigator().tryMoveToXYZ(lentity.posX, lentity.posY, lentity.posZ, 1.0D);
/*     */           }
/* 439 */           this.lastdistance = distance;
/*     */           
/* 441 */           if (this.myChest != null) {
/* 442 */             this.myChest.closeInventory();
/* 443 */             this.myChest = null;
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */       else {
/* 449 */         clearMy();
/*     */       }
/* 451 */       return true;
/*     */     }
/*     */     
/* 454 */     clearMy();
/*     */     
/* 456 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isChangeTartget(Entity pTarget)
/*     */   {
/* 461 */     if ((pTarget instanceof IInventory)) {
/* 462 */       return false;
/*     */     }
/* 464 */     return super.isChangeTartget(pTarget);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean preInteract(EntityPlayer pentityplayer, ItemStack pitemstack)
/*     */   {
/* 470 */     if (pentityplayer.isSneaking()) {
/* 471 */       return false;
/*     */     }
/* 473 */     if (this.owner.isContract())
/*     */     {
/* 475 */       if ((this.owner.isEntityAlive()) && (this.owner.isMaidContractOwner(pentityplayer)) && 
/* 476 */         (pitemstack != null))
/*     */       {
/* 478 */         this.owner.setPathToEntity(null);
/* 479 */         if (this.owner.isRemainsContract()) {
/* 480 */           if ((pitemstack.getItem() instanceof ItemAppleGold))
/*     */           {
/* 482 */             if (!this.owner.worldObj.isRemote) {
/* 483 */               ((ItemAppleGold)pitemstack.getItem()).onEaten(pitemstack, this.owner.worldObj, this.owner.maidAvatar);
/*     */             }
/*     */             
/* 486 */             return true;
/*     */           }
/* 488 */           if (((pitemstack.getItem() instanceof ItemBucketMilk)) && (!this.owner.getActivePotionEffects().isEmpty()))
/*     */           {
/* 490 */             if (!this.owner.worldObj.isRemote) {
/* 491 */               this.owner.clearActivePotions();
/*     */             }
/* 493 */             MMM_Helper.decPlayerInventory(pentityplayer, -1, 1);
/* 494 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 500 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Basic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
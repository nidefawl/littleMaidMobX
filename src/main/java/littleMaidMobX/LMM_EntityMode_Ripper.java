/*     */ package littleMaidMobX;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import mmmlibx.lib.MMM_Counter;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemShears;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ 
/*     */ public class LMM_EntityMode_Ripper extends LMM_EntityModeBase
/*     */ {
/*     */   public static final int mmode_Ripper = 129;
/*     */   public static final int mmode_TNTD = 193;
/*     */   public static final int mmode_Detonator = 194;
/*     */   public int timeSinceIgnited;
/*     */   public int lastTimeSinceIgnited;
/*     */   
/*     */   public LMM_EntityMode_Ripper(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  28 */     super(pEntity);
/*  29 */     this.timeSinceIgnited = -1;
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  34 */     return 3100;
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
/*     */   public void init() {}
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
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting)
/*     */   {
/*  66 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  67 */     ltasks[0] = new EntityAITasks(this.owner.aiProfiler);
/*  68 */     ltasks[1] = new EntityAITasks(this.owner.aiProfiler);
/*     */     
/*  70 */     ltasks[0].addTask(1, this.owner.aiSwiming);
/*  71 */     ltasks[0].addTask(2, this.owner.func_70907_r());
/*  72 */     ltasks[0].addTask(3, this.owner.aiJumpTo);
/*  73 */     ltasks[0].addTask(4, this.owner.aiAttack);
/*  74 */     ltasks[0].addTask(5, this.owner.aiPanic);
/*  75 */     ltasks[0].addTask(6, this.owner.aiBeg);
/*  76 */     ltasks[0].addTask(7, this.owner.aiBegMove);
/*  77 */     ltasks[0].addTask(8, this.owner.aiAvoidPlayer);
/*     */     
/*     */ 
/*     */ 
/*  81 */     ltasks[0].addTask(10, this.owner.aiFollow);
/*     */     
/*  83 */     ltasks[0].addTask(11, this.owner.aiWander);
/*  84 */     ltasks[0].addTask(12, new EntityAIWatchClosest(this.owner, EntityLivingBase.class, 10.0F));
/*  85 */     ltasks[0].addTask(12, new net.minecraft.entity.ai.EntityAILookIdle(this.owner));
/*     */     
/*  87 */     ltasks[1].addTask(1, new LMM_EntityAINearestAttackableTarget(this.owner, EntityCreeper.class, 0, true));
/*  88 */     ltasks[1].addTask(2, new LMM_EntityAINearestAttackableTarget(this.owner, EntityTNTPrimed.class, 0, true));
/*  89 */     ltasks[1].addTask(3, new LMM_EntityAINearestAttackableTarget(this.owner, EntitySheep.class, 0, true));
/*     */     
/*  91 */     this.owner.addMaidMode(ltasks, "Ripper", 129);
/*     */     
/*     */ 
/*     */ 
/*  95 */     EntityAITasks[] ltasks2 = new EntityAITasks[2];
/*  96 */     ltasks2[0] = ltasks[0];
/*  97 */     ltasks2[1] = new EntityAITasks(this.owner.aiProfiler);
/*  98 */     ltasks2[1].addTask(1, new LMM_EntityAINearestAttackableTarget(this.owner, EntityCreeper.class, 0, true));
/*  99 */     ltasks2[1].addTask(2, new LMM_EntityAINearestAttackableTarget(this.owner, EntityTNTPrimed.class, 0, true));
/*     */     
/* 101 */     this.owner.addMaidMode(ltasks2, "TNT-D", 193);
/*     */     
/*     */ 
/*     */ 
/* 105 */     EntityAITasks[] ltasks3 = new EntityAITasks[2];
/* 106 */     ltasks3[0] = pDefaultMove;
/* 107 */     ltasks3[1] = new EntityAITasks(this.owner.aiProfiler);
/* 108 */     ltasks2[1].addTask(1, new LMM_EntityAINearestAttackableTarget(this.owner, EntityLivingBase.class, 0, true));
/*     */     
/* 110 */     this.owner.addMaidMode(ltasks2, "Detonator", 194);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void updateAITick(int pMode)
/*     */   {
/* 117 */     ItemStack litemstack = this.owner.maidInventory.getCurrentItem();
/* 118 */     if ((litemstack != null) && (((this.owner.getAttackTarget() instanceof EntityCreeper)) || ((this.owner.getEntityToAttack() instanceof EntityTNTPrimed))))
/*     */     {
/* 120 */       if (pMode == 129) {
/* 121 */         this.owner.setMaidMode("TNT-D");
/* 122 */         this.owner.maidOverDriveTime.setEnable(true);
/* 123 */       } else if ((this.owner.getMaidModeInt() == 193) && ((litemstack.getItem() instanceof ItemShears))) {
/* 124 */         this.owner.maidOverDriveTime.setEnable(true);
/*     */       }
/*     */     }
/* 127 */     if ((!this.owner.maidOverDriveTime.isEnable()) && (pMode == 193)) {
/* 128 */       this.owner.setMaidMode("Ripper");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUpdate(int pMode)
/*     */   {
/* 136 */     if ((pMode == 194) && (this.owner.isEntityAlive())) {
/* 137 */       if (this.timeSinceIgnited < 0) {
/* 138 */         if (this.lastTimeSinceIgnited != this.timeSinceIgnited) {
/* 139 */           this.owner.getDataWatcher().updateObject(31, Integer.valueOf(0));
/*     */         }
/* 141 */         else if (this.owner.getDataWatcher().getWatchableObjectInt(31) == 1) {
/* 142 */           this.lastTimeSinceIgnited = (this.timeSinceIgnited = 0);
/*     */         }
/*     */       }
/* 145 */       this.lastTimeSinceIgnited = this.timeSinceIgnited;
/* 146 */       if (this.timeSinceIgnited > -1)
/*     */       {
/* 148 */         if ((this.owner.isMovementCeased()) || (this.timeSinceIgnited > 22)) {
/* 149 */           this.owner.getLookHelper().setLookPositionWithEntity(this.owner.getMaidMasterEntity(), 40.0F, 40.0F);
/*     */         }
/* 151 */         LMM_LittleMaidMobX.Debug(String.format("ID:%d(%s)-dom:%d(%d)", new Object[] { Integer.valueOf(this.owner.getEntityId()), this.owner.worldObj.isRemote ? "C" : "W", Integer.valueOf(this.owner.maidDominantArm), Integer.valueOf(this.owner.maidInventory.currentItem) }), new Object[0]);
/*     */         
/* 153 */         if ((this.owner.maidInventory.isItemExplord(this.owner.maidInventory.currentItem)) && (this.timeSinceIgnited++ > 30))
/*     */         {
/* 155 */           this.owner.maidInventory.decrStackSize(this.owner.maidInventory.currentItem, 1);
/*     */           
/* 157 */           this.owner.maidInventory.dropAllItems(true);
/* 158 */           this.timeSinceIgnited = -1;
/* 159 */           this.owner.setDead();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/* 170 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/* 171 */     if (litemstack != null) {
/* 172 */       if ((litemstack.getItem() instanceof ItemShears)) {
/* 173 */         this.owner.setMaidMode("Ripper");
/* 174 */         return true;
/*     */       }
/* 176 */       if (this.owner.maidInventory.isItemExplord(0)) {
/* 177 */         this.owner.setMaidMode("Detonator");
/* 178 */         return true;
/*     */       }
/*     */     }
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/* 186 */     switch (pMode) {
/*     */     case 129: 
/* 188 */       this.owner.setBloodsuck(false);
/* 189 */       return true;
/*     */     case 193: 
/* 191 */       this.owner.setBloodsuck(false);
/* 192 */       return true;
/*     */     case 194: 
/* 194 */       this.owner.setBloodsuck(true);
/*     */       
/* 196 */       this.timeSinceIgnited = -1;
/*     */       
/* 198 */       return true;
/*     */     }
/*     */     
/* 201 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/*     */     int li;
/*     */     
/* 210 */     switch (pMode) {
/*     */     case 129: 
/*     */     case 193: 
/* 213 */       for (li = 0; li < 18;) {
/* 214 */         ItemStack litemstack = this.owner.maidInventory.getStackInSlot(li);
/* 215 */         if (litemstack != null)
/*     */         {
/*     */ 
/* 218 */           if ((litemstack.getItem() instanceof ItemShears)) {
/* 219 */             return li;
/*     */           }
/*     */         }
/* 213 */         li++; continue;
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
/* 224 */         for (li = 0; li < 18; li++)
/*     */         {
/* 226 */           if (this.owner.maidInventory.isItemExplord(li)) {
/* 227 */             return li;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 233 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean attackEntityAsMob(int pMode, Entity pEntity)
/*     */   {
/* 240 */     if (pMode == 194)
/*     */     {
/* 242 */       return false;
/*     */     }
/*     */     
/* 245 */     if (this.owner.getSwingStatusDominant().canAttack()) {
/* 246 */       ItemStack lis = this.owner.getCurrentEquippedItem();
/* 247 */       if ((pEntity instanceof EntityCreeper))
/*     */       {
/*     */         try
/*     */         {
/* 251 */           lis.damageItem(((Integer)ObfuscationReflectionHelper.getPrivateValue(EntityCreeper.class, (EntityCreeper)pEntity, new String[] { "field_70833_d", "timeSinceIgnited" })).intValue(), this.owner);
/*     */           
/*     */ 
/* 254 */           ObfuscationReflectionHelper.setPrivateValue(EntityCreeper.class, (EntityCreeper)pEntity, Integer.valueOf(1), new String[] { "field_70833_d", "timeSinceIgnited" });
/*     */         } catch (Exception e) {
/* 256 */           e.printStackTrace();
/*     */         }
/*     */         
/* 259 */         this.owner.setSwing(20, LMM_EnumSound.attack_bloodsuck);
/* 260 */       } else if ((pEntity instanceof EntityTNTPrimed)) {
/* 261 */         pEntity.setDead();
/* 262 */         lis.damageItem(1, this.owner);
/*     */         
/* 264 */         this.owner.setSwing(20, LMM_EnumSound.attack_bloodsuck);
/*     */       } else {
/* 266 */         this.owner.maidAvatar.interactWith(pEntity);
/* 267 */         this.owner.setSwing(20, LMM_EnumSound.attack);
/*     */       }
/* 269 */       if (lis.stackSize <= 0) {
/* 270 */         this.owner.maidInventory.setInventoryCurrentSlotContents(null);
/* 271 */         this.owner.getNextEquipItem();
/*     */       }
/*     */     }
/*     */     
/* 275 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSearchEntity()
/*     */   {
/* 280 */     return true;
/*     */   }
/*     */   
/*     */   public boolean checkEntity(int pMode, Entity pEntity)
/*     */   {
/* 285 */     if (this.owner.maidInventory.currentItem < 0) {
/* 286 */       return false;
/*     */     }
/* 288 */     switch (pMode) {
/*     */     case 194: 
/* 290 */       return !this.owner.getIFF(pEntity);
/*     */     case 129: 
/* 292 */       if ((pEntity instanceof EntitySheep)) {
/* 293 */         EntitySheep les = (EntitySheep)pEntity;
/* 294 */         if ((!les.getSheared()) && (!les.isChild())) {
/* 295 */           return true;
/*     */         }
/*     */       }
/*     */     case 193: 
/* 299 */       if ((pEntity instanceof EntityCreeper)) {
/* 300 */         return true;
/*     */       }
/* 302 */       if ((pEntity instanceof EntityTNTPrimed)) {
/* 303 */         return true;
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/* 308 */     return false;
/*     */   }
/*     */   
/*     */   protected float setLittleMaidFlashTime(float f)
/*     */   {
/* 313 */     if (this.timeSinceIgnited > -1) {
/* 314 */       return (this.lastTimeSinceIgnited + (this.timeSinceIgnited - this.lastTimeSinceIgnited) * f) / 28.0F;
/*     */     }
/* 316 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public int colorMultiplier(float pLight, float pPartialTicks)
/*     */   {
/* 322 */     float f2 = setLittleMaidFlashTime(pPartialTicks);
/*     */     
/* 324 */     if ((int)(f2 * 10.0F) % 2 == 0) {
/* 325 */       return 0;
/*     */     }
/* 327 */     int i = (int)(f2 * 0.2F * 255.0F);
/* 328 */     if (i < 0)
/*     */     {
/* 330 */       i = 0;
/*     */     }
/* 332 */     if (i > 255)
/*     */     {
/* 334 */       i = 255;
/*     */     }
/* 336 */     LMM_LittleMaidMobX.Debug(String.format("%2x", new Object[] { Integer.valueOf(i) }), new Object[0]);
/* 337 */     char c = 'ÿ';
/* 338 */     char c1 = 'ÿ';
/* 339 */     char c2 = 'ÿ';
/* 340 */     return i << 24 | c << '\020' | c1 << '\b' | c2;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean damageEntity(int pMode, DamageSource par1DamageSource, float par2)
/*     */   {
/* 346 */     if ((pMode == 194) && (LMM_InventoryLittleMaid.isItemExplord(this.owner.getCurrentEquippedItem()))) {
/* 347 */       if (this.timeSinceIgnited == -1) {
/* 348 */         this.owner.playSound("random.fuse", 1.0F, 0.5F);
/* 349 */         this.owner.getDataWatcher().updateObject(31, Integer.valueOf(1));
/*     */       }
/*     */       
/* 352 */       this.owner.setMaidWait(true);
/*     */     }
/*     */     
/* 355 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Ripper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
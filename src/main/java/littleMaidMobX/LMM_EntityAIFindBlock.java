/*     */ package littleMaidMobX;
/*     */ 
/*     */ import mmmlibx.lib.MMM_EntityDummy;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_EntityAIFindBlock
/*     */   extends EntityAIBase
/*     */   implements LMM_IEntityAI
/*     */ {
/*     */   protected boolean isEnable;
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected LMM_EntityModeBase fmodeBase;
/*     */   
/*     */   public LMM_EntityAIFindBlock(LMM_EntityLittleMaid pEntityLittleMaid)
/*     */   {
/*  21 */     this.theMaid = pEntityLittleMaid;
/*  22 */     this.isEnable = true;
/*     */     
/*     */ 
/*  25 */     setMutexBits(3);
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  30 */     this.fmodeBase = this.theMaid.getActiveModeClass();
/*     */     
/*     */ 
/*  33 */     if ((!this.isEnable) || (this.theMaid.isMaidWait()) || (this.fmodeBase == null)) {
/*  34 */       return false;
/*     */     }
/*  36 */     if (!this.fmodeBase.isSearchBlock()) {
/*  37 */       return this.fmodeBase.shouldBlock(this.theMaid.maidMode);
/*     */     }
/*     */     
/*     */ 
/*  41 */     int lx = MathHelper.floor_double(this.theMaid.posX);
/*  42 */     int ly = MathHelper.floor_double(this.theMaid.posY);
/*  43 */     int lz = MathHelper.floor_double(this.theMaid.posZ);
/*  44 */     int vt = MathHelper.floor_float(this.theMaid.rotationYawHead * 4.0F / 360.0F + 2.5F) & 0x3;
/*  45 */     int xx = lx;
/*  46 */     int yy = ly;
/*  47 */     int zz = lz;
/*     */     
/*     */ 
/*  50 */     MMM_EntityDummy.clearDummyEntity(this.theMaid);
/*  51 */     boolean flagdammy = false;
/*     */     
/*     */ 
/*  54 */     for (int d = 0; d < 4; d++) {
/*  55 */       for (int a = 0; a < 18; a += 2) {
/*  56 */         int del = a / 2;
/*  57 */         if (vt == 0) {
/*  58 */           xx = lx - del;
/*  59 */           zz = lz - del;
/*     */         }
/*  61 */         else if (vt == 1) {
/*  62 */           xx = lx + del;
/*  63 */           zz = lz - del;
/*     */         }
/*  65 */         else if (vt == 2) {
/*  66 */           xx = lx + del;
/*  67 */           zz = lz + del;
/*     */         }
/*  69 */         else if (vt == 3) {
/*  70 */           xx = lx - del;
/*  71 */           zz = lz + del;
/*     */         }
/*     */         
/*  74 */         if (!flagdammy) {
/*  75 */           MMM_EntityDummy.setDummyEntity(this.theMaid, 16731983, xx, ly, zz);
/*  76 */           flagdammy = true;
/*     */         }
/*  78 */         int b = 0;
/*     */         do {
/*  80 */           for (int c = 0; c < 3; c++) {
/*  81 */             yy = ly + (c == 2 ? -1 : c);
/*  82 */             if ((this.fmodeBase.checkBlock(this.theMaid.maidMode, xx, yy, zz)) && 
/*  83 */               (this.fmodeBase.outrangeBlock(this.theMaid.maidMode, xx, yy, zz))) {
/*  84 */               this.theMaid.setTilePos(xx, yy, zz);
/*     */               
/*  86 */               MMM_EntityDummy.setDummyEntity(this.theMaid, 5242703, xx, yy, zz);
/*  87 */               flagdammy = true;
/*  88 */               return true;
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*  93 */           if (!flagdammy) {
/*  94 */             MMM_EntityDummy.setDummyEntity(this.theMaid, 16777167, xx, ly, zz);
/*  95 */             flagdammy = true;
/*     */           }
/*     */           
/*  98 */           flagdammy = false;
/*     */           
/* 100 */           if (vt == 0) {
/* 101 */             xx++;
/*     */           }
/* 103 */           else if (vt == 1) {
/* 104 */             zz++;
/*     */           }
/* 106 */           else if (vt == 2) {
/* 107 */             xx--;
/*     */           }
/* 109 */           else if (vt == 3) {
/* 110 */             zz--;
/*     */           }
/*     */           
/* 113 */           b++; } while (b < a);
/*     */       }
/* 115 */       vt = vt + 1 & 0x3;
/*     */     }
/* 117 */     if (this.fmodeBase.overlooksBlock(this.theMaid.maidMode)) {
/* 118 */       TileEntity ltile = this.theMaid.maidTileEntity;
/* 119 */       if (ltile != null) {
/* 120 */         lx = ltile.xCoord;
/* 121 */         ly = ltile.yCoord;
/* 122 */         lz = ltile.zCoord;
/*     */         
/* 124 */         MMM_EntityDummy.setDummyEntity(this.theMaid, 5242703, lx, ly, lz);
/* 125 */         flagdammy = true;
/*     */       }
/* 127 */       return true;
/*     */     }
/* 129 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean continueExecuting()
/*     */   {
/* 135 */     this.fmodeBase.updateBlock();
/*     */     
/* 137 */     if (!this.theMaid.getNavigator().noPath()) { return true;
/*     */     }
/* 139 */     double ld = this.theMaid.getDistanceTilePos();
/* 140 */     if (ld > 100.0D)
/*     */     {
/* 142 */       this.theMaid.getActiveModeClass().farrangeBlock();
/* 143 */       return false; }
/* 144 */     if (ld > 5.0D)
/*     */     {
/* 146 */       return this.theMaid.getActiveModeClass().outrangeBlock(this.theMaid.maidMode);
/*     */     }
/*     */     
/* 149 */     return this.theMaid.getActiveModeClass().executeBlock(this.theMaid.maidMode);
/*     */   }
/*     */   
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/* 155 */     this.fmodeBase.startBlock(this.theMaid.maidMode);
/*     */   }
/*     */   
/*     */   public void resetTask()
/*     */   {
/* 160 */     this.fmodeBase.resetBlock(this.theMaid.maidMode);
/*     */   }
/*     */   
/*     */ 
/*     */   public void updateTask()
/*     */   {
/* 166 */     this.theMaid.looksTilePos();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/* 172 */     this.isEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/* 177 */     return this.isEnable;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIFindBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
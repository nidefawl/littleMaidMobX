/*     */ package littleMaidMobX;
/*     */ 
/*     */ import mmmlibx.lib.MMM_EntityDummy;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityAITracerMove extends EntityAIBase implements LMM_IEntityAI
/*     */ {
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected World world;
/*     */   protected boolean isEnable;
/*     */   protected int tileX;
/*     */   protected int tileY;
/*     */   protected int tileZ;
/*     */   
/*     */   public LMM_EntityAITracerMove(LMM_EntityLittleMaid pEntityLittleMaid)
/*     */   {
/*  20 */     this.theMaid = pEntityLittleMaid;
/*  21 */     this.world = pEntityLittleMaid.worldObj;
/*  22 */     this.isEnable = false;
/*     */     
/*  24 */     setMutexBits(1);
/*     */   }
/*     */   
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/*  29 */     this.isEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/*  34 */     return this.isEnable;
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  39 */     return (this.isEnable) && (!this.theMaid.isMaidWaitEx()) && (this.theMaid.getNavigator().noPath());
/*     */   }
/*     */   
/*     */   public boolean continueExecuting()
/*     */   {
/*  44 */     return !this.theMaid.getNavigator().noPath();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/*  51 */     int ox = MathHelper.floor_double(this.theMaid.posX);
/*  52 */     int oy = MathHelper.floor_double(this.theMaid.posY);
/*  53 */     int oz = MathHelper.floor_double(this.theMaid.posZ);
/*  54 */     int vt = MathHelper.floor_float(this.theMaid.rotationYawHead * 4.0F / 360.0F + 2.5F) & 0x3;
/*  55 */     int xx = ox;
/*  56 */     int yy = oy;
/*  57 */     int zz = oz;
/*  58 */     double lrange = Double.MAX_VALUE;
/*     */     
/*     */ 
/*  61 */     MMM_EntityDummy.clearDummyEntity(this.theMaid);
/*  62 */     boolean flagdammy = false;
/*     */     
/*     */ 
/*  65 */     for (int d = 0; d < 4; d++) {
/*  66 */       for (int a = 2; a < 14; a += 2) {
/*  67 */         int del = a / 2;
/*  68 */         if (vt == 0) {
/*  69 */           xx = ox - del;
/*  70 */           zz = oz - del;
/*     */         }
/*  72 */         else if (vt == 1) {
/*  73 */           xx = ox + del;
/*  74 */           zz = oz - del;
/*     */         }
/*  76 */         else if (vt == 2) {
/*  77 */           xx = ox + del;
/*  78 */           zz = oz + del;
/*     */         }
/*  80 */         else if (vt == 3) {
/*  81 */           xx = ox - del;
/*  82 */           zz = oz + del;
/*     */         }
/*     */         
/*  85 */         if (!flagdammy) {
/*  86 */           MMM_EntityDummy.setDummyEntity(this.theMaid, 16731983, xx, oy, zz);
/*  87 */           flagdammy = true;
/*     */         }
/*  89 */         int b = 0;
/*     */         do {
/*  91 */           for (int c = 0; c < 3; c++) {
/*  92 */             yy = oy + (c == 2 ? -1 : c);
/*  93 */             if (checkBlock(xx, yy, zz))
/*     */             {
/*  95 */               double lr = this.theMaid.getDistanceSq(xx, yy, zz);
/*  96 */               if ((lr < lrange) && 
/*  97 */                 (doFindBlock(xx, yy, zz))) {
/*  98 */                 lrange = lr;
/*  99 */                 this.tileX = xx;
/* 100 */                 this.tileY = yy;
/* 101 */                 this.tileZ = zz;
/*     */                 
/* 103 */                 this.theMaid.setHomeArea(xx, yy, zz, 16);
/*     */                 
/* 105 */                 MMM_EntityDummy.setDummyEntity(this.theMaid, 5197823, xx, yy, zz);
/* 106 */                 flagdammy = true;
/* 107 */                 return;
/*     */               }
/*     */               
/*     */ 
/* 111 */               MMM_EntityDummy.setDummyEntity(this.theMaid, 5242703, xx, yy, zz);
/* 112 */               flagdammy = true;
/*     */             }
/*     */           }
/*     */           
/* 116 */           if (!flagdammy) {
/* 117 */             MMM_EntityDummy.setDummyEntity(this.theMaid, 16777167, xx, oy, zz);
/* 118 */             flagdammy = true;
/*     */           }
/*     */           
/* 121 */           flagdammy = false;
/*     */           
/* 123 */           if (vt == 0) {
/* 124 */             xx++;
/*     */           }
/* 126 */           else if (vt == 1) {
/* 127 */             zz++;
/*     */           }
/* 129 */           else if (vt == 2) {
/* 130 */             xx--;
/*     */           }
/* 132 */           else if (vt == 3) {
/* 133 */             zz--;
/*     */           }
/*     */           
/* 136 */           b++; } while (b < a);
/*     */       }
/* 138 */       vt = vt + 1 & 0x3;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean checkBlock(int px, int py, int pz)
/*     */   {
/* 146 */     return (this.world.getBlockPowerInput(px, py, pz) > 0) && (this.world.getBlock(px, py + 1, pz).getMaterial() == net.minecraft.block.material.Material.air);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean doFindBlock(int px, int py, int pz)
/*     */   {
/* 154 */     return this.theMaid.getNavigator().tryMoveToXYZ(px, py, pz, 1.0D);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAITracerMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
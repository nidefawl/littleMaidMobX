/*     */ package littleMaidMobX;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityAIJumpToMaster extends net.minecraft.entity.ai.EntityAIBase implements LMM_IEntityAI
/*     */ {
/*     */   protected LMM_EntityLittleMaid theMaid;
/*     */   protected EntityLivingBase theOwner;
/*     */   protected World theWorld;
/*     */   protected boolean isEnable;
/*     */   private boolean jumpTarget;
/*     */   protected AxisAlignedBB boundingBox;
/*     */   
/*     */   public LMM_EntityAIJumpToMaster(LMM_EntityLittleMaid pEntityLittleMaid)
/*     */   {
/*  21 */     this.theMaid = pEntityLittleMaid;
/*  22 */     this.theWorld = pEntityLittleMaid.worldObj;
/*  23 */     this.isEnable = true;
/*  24 */     this.boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  29 */     if ((!this.isEnable) || (!this.theMaid.isContractEX()) || (this.theMaid.isMaidWaitEx()))
/*     */     {
/*  31 */       return false;
/*     */     }
/*  33 */     if (this.theMaid.getLeashed())
/*     */     {
/*  35 */       return false;
/*     */     }
/*  37 */     if (this.theMaid.isFreedom())
/*     */     {
/*  39 */       if (this.theMaid.homeWorld != this.theMaid.dimension) {
/*  40 */         LMM_LittleMaidMobX.Debug(String.format("ID:%d, %d -> %d, Change HomeWorld. reset HomePosition.", new Object[] { Integer.valueOf(this.theMaid.getEntityId()), Integer.valueOf(this.theMaid.homeWorld), Integer.valueOf(this.theMaid.worldObj.provider.dimensionId) }), new Object[0]);
/*     */         
/*     */ 
/*  43 */         this.theMaid.setHomeArea(MathHelper.floor_double(this.theMaid.posX), MathHelper.floor_double(this.theMaid.posY), MathHelper.floor_double(this.theMaid.posZ), 16);
/*     */         
/*     */ 
/*     */ 
/*  47 */         return false;
/*     */       }
/*     */       
/*  50 */       if (this.theMaid.getHomePosition().getDistanceSquared(MathHelper.floor_double(this.theMaid.posX), MathHelper.floor_double(this.theMaid.posY), MathHelper.floor_double(this.theMaid.posZ)) > 400.0D)
/*     */       {
/*     */ 
/*     */ 
/*  54 */         this.jumpTarget = false;
/*  55 */         LMM_LittleMaidMobX.Debug(String.format("ID:%d(%s) Jump To Home.", new Object[] { Integer.valueOf(this.theMaid.getEntityId()), this.theMaid.worldObj.isRemote ? "C" : "W" }), new Object[0]);
/*     */         
/*     */ 
/*  58 */         return true;
/*     */       }
/*     */     } else {
/*  61 */       this.jumpTarget = true;
/*  62 */       this.theOwner = this.theMaid.getMaidMasterEntity();
/*  63 */       if (this.theMaid.getAttackTarget() == null) {
/*  64 */         if (this.theMaid.mstatMasterDistanceSq < 144.0D) {
/*  65 */           return false;
/*     */         }
/*     */         
/*     */       }
/*  69 */       else if (this.theMaid.mstatMasterDistanceSq < (this.theMaid.isBloodsuck() ? 1024.0D : 256.0D)) {
/*  70 */         return false;
/*     */       }
/*     */       
/*  73 */       LMM_LittleMaidMobX.Debug("ID:%d(%s) Jump To Master.", new Object[] { Integer.valueOf(this.theMaid.getEntityId()), this.theMaid.worldObj.isRemote ? "C" : "W" });
/*     */       
/*     */ 
/*  76 */       return true;
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public void startExecuting()
/*     */   {
/*  83 */     if (this.jumpTarget) {
/*  84 */       int i = MathHelper.floor_double(this.theOwner.posX) - 2;
/*  85 */       int j = MathHelper.floor_double(this.theOwner.posZ) - 2;
/*  86 */       int k = MathHelper.floor_double(this.theOwner.boundingBox.minY);
/*     */       
/*  88 */       for (int l = 0; l <= 4; l++) {
/*  89 */         for (int i1 = 0; i1 <= 4; i1++) {
/*  90 */           if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (this.theWorld.getBlock(i + l, k - 1, j + i1).isNormalCube()) && (!this.theWorld.getBlock(i + l, k, j + i1).isNormalCube()) && (!this.theWorld.getBlock(i + l, k + 1, j + i1).isNormalCube()))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*  95 */             double dd = this.theOwner.getDistanceSq(i + l + 0.5D + MathHelper.sin(this.theOwner.rotationYaw * 0.017453292F) * 2.0D, k, j + i1 - MathHelper.cos(this.theOwner.rotationYaw * 0.017453292F) * 2.0D);
/*     */             
/*     */ 
/*     */ 
/*  99 */             if (dd > 8.0D)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 104 */               this.theMaid.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, this.theMaid.rotationYaw, this.theMaid.rotationPitch);
/*     */               
/*     */ 
/* 107 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 114 */       int lx = this.theMaid.getHomePosition().posX;
/* 115 */       int ly = this.theMaid.getHomePosition().posY;
/* 116 */       int lz = this.theMaid.getHomePosition().posZ;
/* 117 */       if (!isCanJump(lx, ly, lz))
/*     */       {
/* 119 */         LMM_LittleMaidMobX.Debug(String.format("ID:%d(%s) home lost.", new Object[] { Integer.valueOf(this.theMaid.getEntityId()), this.theMaid.worldObj.isRemote ? "C" : "W" }), new Object[0]);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */         boolean f = false;
/*     */         
/* 127 */         for (int a = 1; (a < 6) && (!f); a++) {
/* 128 */           if (isCanJump(lx, ly + a, lz)) {
/* 129 */             f = true;
/* 130 */             ly += a;
/* 131 */             break;
/*     */           }
/*     */         }
/* 134 */         for (a = -1; (a > -6) && (!f); a--) {
/* 135 */           if (isCanJump(lx, ly + a, lz)) {
/* 136 */             f = true;
/* 137 */             ly += a;
/* 138 */             break;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 143 */         for (a = 2; (a < 18) && (!f); a += 2) {
/* 144 */           lx--;
/* 145 */           lz--;
/* 146 */           for (int c = 0; c < 4; c++)
/* 147 */             for (int b = 0; b <= a; b++)
/*     */             {
/* 149 */               if (isCanJump(lx, ly + a, lz)) {
/* 150 */                 f = true;
/*     */                 break label597;
/*     */               }
/* 153 */               if (c == 0) {
/* 154 */                 lx++;
/* 155 */               } else if (c == 1) {
/* 156 */                 lz++;
/* 157 */               } else if (c == 2) {
/* 158 */                 lx--;
/* 159 */               } else if (c == 3)
/* 160 */                 lz--;
/*     */             }
/*     */         }
/*     */         label597:
/* 164 */         if (f)
/*     */         {
/* 166 */           this.theMaid.setHomeArea(lx, ly, lz, (int)this.theMaid.func_110174_bM());
/* 167 */           LMM_LittleMaidMobX.Debug(String.format("Find new position:%d, %d, %d.", new Object[] { Integer.valueOf(lx), Integer.valueOf(ly), Integer.valueOf(lz) }), new Object[0]);
/*     */         }
/*     */         else {
/* 170 */           if (isCanJump(lx, ly - 6, lz)) {
/* 171 */             ly -= 6;
/*     */           }
/* 173 */           LMM_LittleMaidMobX.Debug(String.format("loss new position:%d, %d, %d.", new Object[] { Integer.valueOf(lx), Integer.valueOf(ly), Integer.valueOf(lz) }), new Object[0]);
/*     */         }
/*     */       }
/*     */       else {
/* 177 */         LMM_LittleMaidMobX.Debug(String.format("ID:%d(%s) home solid.", new Object[] { Integer.valueOf(this.theMaid.getEntityId()), this.theMaid.worldObj.isRemote ? "C" : "W" }), new Object[0]);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 185 */       this.theMaid.setLocationAndAngles(lx + 5.0D, ly, lz + 0.5D, this.theMaid.rotationYaw, this.theMaid.rotationPitch);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 190 */     this.theMaid.setTarget(null);
/* 191 */     this.theMaid.setAttackTarget(null);
/* 192 */     this.theMaid.setRevengeTarget(null);
/* 193 */     this.theMaid.getNavigator().clearPathEntity();
/* 194 */     LMM_LittleMaidMobX.Debug(String.format("ID:%d(%s) Jump Fail.", new Object[] { Integer.valueOf(this.theMaid.getEntityId()), this.theMaid.worldObj.isRemote ? "C" : "W" }), new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isCanJump(int px, int py, int pz)
/*     */   {
/* 202 */     double lw = this.theMaid.width / 2.0D;
/* 203 */     double ly = py - (this.theMaid.yOffset + this.theMaid.ySize);
/* 204 */     this.boundingBox.setBounds(px - lw, ly, pz - lw, px + lw, ly + this.theMaid.height, pz + lw);
/*     */     
/*     */ 
/* 207 */     return (this.theWorld.getBlock(px, py - 1, pz).getMaterial().isSolid()) && (this.theWorld.func_147461_a(this.boundingBox).isEmpty());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean continueExecuting()
/*     */   {
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/* 218 */     this.isEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/* 223 */     return this.isEnable;
/*     */   }
/*     */   
/*     */   public boolean isInterruptible()
/*     */   {
/* 228 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIJumpToMaster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
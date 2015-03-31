/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySnowball;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemSnowball;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityMode_Playing extends LMM_EntityModeBase
/*     */ {
/*     */   public static final int mmode_Playing = 255;
/*     */   public static final int mpr_NULL = 0;
/*     */   public static final int mpr_QuickShooter = 16;
/*     */   public static final int mpr_StockShooter = 32;
/*     */   public int fcounter;
/*     */   
/*     */   public LMM_EntityMode_Playing(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  31 */     super(pEntity);
/*  32 */     this.fcounter = 0;
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  37 */     return 900;
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
/*  53 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  54 */     ltasks[0] = pDefaultMove;
/*  55 */     ltasks[1] = pDefaultTargeting;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  61 */     this.owner.addMaidMode(ltasks, "Playing", 255);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean checkSnows(int x, int y, int z)
/*     */   {
/*  67 */     int snowCnt = 0;
/*  68 */     snowCnt += (Block.isEqualTo(this.owner.worldObj.getBlock(x, y, z), Blocks.snow_layer) ? 3 : 0);
/*  69 */     snowCnt += (Block.isEqualTo(this.owner.worldObj.getBlock(x + 1, y, z), Blocks.snow_layer) ? 1 : 0);
/*  70 */     snowCnt += (Block.isEqualTo(this.owner.worldObj.getBlock(x - 1, y, z), Blocks.snow_layer) ? 1 : 0);
/*  71 */     snowCnt += (Block.isEqualTo(this.owner.worldObj.getBlock(x, y, z + 1), Blocks.snow_layer) ? 1 : 0);
/*  72 */     snowCnt += (Block.isEqualTo(this.owner.worldObj.getBlock(x, y, z - 1), Blocks.snow_layer) ? 1 : 0);
/*     */     
/*  74 */     return snowCnt >= 5;
/*     */   }
/*     */   
/*     */   protected boolean movePlaying()
/*     */   {
/*  79 */     int x = MathHelper.floor_double(this.owner.posX);
/*  80 */     int y = MathHelper.floor_double(this.owner.posY);
/*  81 */     int z = MathHelper.floor_double(this.owner.posZ);
/*  82 */     PathEntity pe = null;
/*     */     
/*     */ 
/*     */ 
/*  86 */     for (int a = 2; (a < 18) && (pe == null); a += 2) {
/*  87 */       x--;
/*  88 */       z--;
/*  89 */       for (int b = 0; b < a; b++)
/*     */       {
/*  91 */         for (int c = 0; c < 4; c++) {
/*  92 */           if (checkSnows(x, y, z)) {
/*  93 */             pe = this.owner.worldObj.getEntityPathToXYZ(this.owner, x, y - 1, z, 10.0F, true, false, false, true);
/*  94 */             if (pe != null) {
/*     */               break label174;
/*     */             }
/*     */           }
/*  98 */           if (c == 0) x++;
/*  99 */           if (c == 1) z++;
/* 100 */           if (c == 2) x--;
/* 101 */           if (c == 3) z--;
/*     */         }
/*     */       }
/*     */     }
/*     */     label174:
/* 106 */     if (pe != null) {
/* 107 */       this.owner.getNavigator().setPath(pe, 1.0D);
/* 108 */       LMM_LittleMaidMobX.Debug("Find Snow Area-%d:%d, %d, %d.", new Object[] { Integer.valueOf(this.owner.getEntityId()), Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   protected void playingSnowWar()
/*     */   {
/* 116 */     switch (this.fcounter)
/*     */     {
/*     */     case 0: 
/* 119 */       this.owner.setSitting(false);
/* 120 */       this.owner.setSneaking(false);
/* 121 */       if (!this.owner.getNextEquipItem()) {
/* 122 */         this.owner.setAttackTarget(null);
/*     */         
/* 124 */         this.owner.getNavigator().clearPathEntity();
/* 125 */         this.fcounter = 1;
/* 126 */       } else if (this.owner.getAttackTarget() == null)
/*     */       {
/* 128 */         java.util.List<Entity> list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, this.owner.boundingBox.expand(16.0D, 4.0D, 16.0D));
/* 129 */         for (Entity e : list) {
/* 130 */           if ((e != null) && (((e instanceof EntityPlayer)) || ((e instanceof LMM_EntityLittleMaid))) && 
/* 131 */             (this.owner.getRNG().nextBoolean())) {
/* 132 */             this.owner.setAttackTarget((net.minecraft.entity.EntityLivingBase)e);
/* 133 */             break;
/*     */           }
/*     */         }
/*     */       }
/* 137 */       break;
/*     */     
/*     */ 
/*     */     case 1: 
/* 141 */       this.owner.setAttackTarget(null);
/* 142 */       if (this.owner.getNavigator().noPath()) {
/* 143 */         this.fcounter = 2;
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     case 2: 
/* 149 */       if ((this.owner.getAttackTarget() == null) && (this.owner.getNavigator().noPath())) {
/* 150 */         if (movePlaying()) {
/* 151 */           this.fcounter = 3;
/*     */         } else {
/* 153 */           this.owner.setPlayingRole(0);
/* 154 */           this.fcounter = 0;
/*     */         }
/*     */       } else {
/* 157 */         this.owner.setAttackTarget(null);
/*     */       }
/*     */       
/* 160 */       break;
/*     */     
/*     */     case 3: 
/* 163 */       if (this.owner.getNavigator().noPath()) {
/* 164 */         if (checkSnows(MathHelper.floor_double(this.owner.posX), MathHelper.floor_double(this.owner.posY), MathHelper.floor_double(this.owner.posZ)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 169 */           this.owner.attackTime = 30;
/* 170 */           if (this.owner.getPlayingRole() == 16) {
/* 171 */             this.fcounter = 8;
/*     */           } else {
/* 173 */             this.fcounter = 4;
/*     */           }
/*     */         }
/*     */         else {
/* 177 */           this.fcounter = 2;
/*     */         }
/*     */       }
/*     */       
/*     */       break;
/*     */     case 4: 
/*     */     case 5: 
/*     */     case 6: 
/*     */     case 7: 
/* 186 */       if (this.owner.attackTime <= 0) {
/* 187 */         if (this.owner.maidInventory.addItemStackToInventory(new ItemStack(Items.snowball))) {
/* 188 */           this.owner.playSound("random.pop");
/* 189 */           if (this.owner.getPlayingRole() == 32) {
/* 190 */             this.owner.setSwing(5, LMM_EnumSound.collect_snow);
/* 191 */             this.fcounter = 0;
/*     */           } else {
/* 193 */             this.owner.setSwing(30, LMM_EnumSound.collect_snow);
/* 194 */             this.fcounter += 1;
/*     */           }
/*     */         } else {
/* 197 */           this.owner.setPlayingRole(0);
/* 198 */           this.fcounter = 0;
/*     */         }
/*     */       }
/*     */       
/* 202 */       this.owner.setJumping(false);
/* 203 */       this.owner.getNavigator().clearPathEntity();
/* 204 */       this.owner.getLookHelper().setLookPosition(MathHelper.floor_double(this.owner.posX), MathHelper.floor_double(this.owner.posY - 1.0D), MathHelper.floor_double(this.owner.posZ), 30.0F, 40.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 209 */       this.owner.setSitting(true);
/* 210 */       break;
/*     */     
/*     */ 
/*     */     case 8: 
/* 214 */       if (this.owner.attackTime <= 0) {
/* 215 */         if (this.owner.maidInventory.addItemStackToInventory(new ItemStack(Items.snowball))) {
/* 216 */           this.owner.setSwing(5, LMM_EnumSound.collect_snow);
/* 217 */           this.owner.playSound("random.pop");
/* 218 */           this.fcounter = 0;
/*     */         } else {
/* 220 */           this.owner.setPlayingRole(0);
/* 221 */           this.fcounter = 0;
/*     */         }
/*     */       }
/*     */       
/* 225 */       this.owner.setSneaking(true);
/* 226 */       this.owner.getLookHelper().setLookPosition(MathHelper.floor_double(this.owner.posX), MathHelper.floor_double(this.owner.posY - 1.0D), MathHelper.floor_double(this.owner.posZ), 30.0F, 40.0F);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateAITick(int pMode)
/*     */   {
/* 239 */     if (this.owner.isFreedom())
/*     */     {
/* 241 */       if (this.owner.worldObj.isDaytime())
/*     */       {
/*     */ 
/*     */ 
/* 245 */         if (!this.owner.isPlaying())
/*     */         {
/* 247 */           int xx = MathHelper.floor_double(this.owner.posX);
/* 248 */           int yy = MathHelper.floor_double(this.owner.posY);
/* 249 */           int zz = MathHelper.floor_double(this.owner.posZ);
/*     */           
/*     */ 
/* 252 */           boolean f = true;
/* 253 */           for (int z = -1; z < 2; z++) {
/* 254 */             for (int x = -1; x < 2; x++) {
/* 255 */               f &= Block.isEqualTo(this.owner.worldObj.getBlock(xx + x, yy, zz + z), Blocks.snow_layer);
/*     */             }
/*     */           }
/* 258 */           int lpr = this.owner.getRNG().nextInt(100) - 97;
/* 259 */           lpr = (f) && (lpr > 0) ? 32 : lpr == 1 ? 16 : 0;
/* 260 */           this.owner.setPlayingRole(lpr);
/* 261 */           this.fcounter = 0;
/* 262 */           if (!f) {}
/*     */ 
/*     */ 
/*     */         }
/* 266 */         else if (this.owner.getPlayingRole() >= 32768)
/*     */         {
/* 268 */           this.owner.setPlayingRole(0);
/* 269 */           this.fcounter = 0;
/*     */ 
/*     */         }
/* 272 */         else if ((this.owner.getPlayingRole() == 16) || (this.owner.getPlayingRole() == 32))
/*     */         {
/* 274 */           playingSnowWar();
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */       }
/* 281 */       else if (this.owner.isPlaying())
/*     */       {
/*     */ 
/* 284 */         if (this.owner.getPlayingRole() < 32768)
/*     */         {
/* 286 */           this.owner.setPlayingRole(0);
/* 287 */           this.fcounter = 0;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 296 */       if ((this.owner.getAttackTarget() != null) || (this.owner.maidInventory.getFirstEmptyStack() != -1)) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float attackEntityFrom(DamageSource par1DamageSource, float par2)
/*     */   {
/* 305 */     if ((par1DamageSource.getSourceOfDamage() instanceof EntitySnowball))
/*     */     {
/* 307 */       this.owner.maidDamegeSound = LMM_EnumSound.hurt_snow;
/* 308 */       if ((!this.owner.isContract()) || (this.owner.isFreedom())) {
/* 309 */         this.owner.setPlayingRole(16);
/* 310 */         this.owner.setMaidWait(false);
/* 311 */         this.owner.setMaidWaitCount(0);
/* 312 */         LMM_LittleMaidMobX.Debug("playingMode Enable.", new Object[0]);
/*     */       }
/*     */     }
/* 315 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/* 320 */     switch (pMode) {
/*     */     case 255: 
/* 322 */       this.owner.aiAttack.setEnable(false);
/* 323 */       this.owner.aiShooting.setEnable(true);
/* 324 */       this.owner.setBloodsuck(false);
/* 325 */       return true;
/*     */     }
/*     */     
/* 328 */     return false;
/*     */   }
/*     */   
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/* 333 */     ItemStack litemstack = null;
/* 334 */     if (this.owner.getPlayingRole() != 0) {
/* 335 */       for (int li = 0; li < 18; li++) {
/* 336 */         litemstack = this.owner.maidInventory.getStackInSlot(li);
/* 337 */         if (litemstack != null)
/*     */         {
/*     */ 
/* 340 */           if ((litemstack.getItem() instanceof ItemSnowball))
/* 341 */             return li;
/*     */         }
/*     */       }
/*     */     }
/* 345 */     return -1;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Playing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
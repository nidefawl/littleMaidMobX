/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LMM_EntityModeBase
/*     */ {
/*     */   public final LMM_EntityLittleMaid owner;
/*     */   public int fpriority;
/*     */   
/*     */   public LMM_EntityModeBase(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  33 */     this.owner = pEntity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int priority();
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
/*     */   public void initEntity() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void addEntityMode(EntityAITasks paramEntityAITasks1, EntityAITasks paramEntityAITasks2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void showSpecial(LMM_RenderLittleMaid prenderlittlemaid, double px, double py, double pz) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateAITick(int pMode) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUpdate(int pMode) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean preInteract(EntityPlayer pentityplayer, ItemStack pitemstack)
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean interact(EntityPlayer pentityplayer, ItemStack pitemstack)
/*     */   {
/* 105 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean setMode(int pMode)
/*     */   {
/* 120 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/* 129 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/* 138 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean attackEntityAsMob(int pMode, Entity pEntity)
/*     */   {
/* 147 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSearchBlock()
/*     */   {
/* 155 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean shouldBlock(int pMode)
/*     */   {
/* 162 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 170 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean overlooksBlock(int pMode)
/*     */   {
/* 177 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void farrangeBlock()
/*     */   {
/* 188 */     this.owner.getNavigator().clearPathEntity();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean outrangeBlock(int pMode, int pX, int pY, int pZ)
/*     */   {
/* 195 */     return this.owner.getNavigator().tryMoveToXYZ(pX, pY, pZ, 1.0D);
/*     */   }
/*     */   
/* 198 */   public boolean outrangeBlock(int pMode) { return outrangeBlock(pMode, this.owner.maidTile[0], this.owner.maidTile[1], this.owner.maidTile[2]); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean executeBlock(int pMode, int px, int py, int pz)
/*     */   {
/* 206 */     return false;
/*     */   }
/*     */   
/* 209 */   public boolean executeBlock(int pMode) { return executeBlock(pMode, this.owner.maidTile[0], this.owner.maidTile[1], this.owner.maidTile[2]); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void startBlock(int pMode) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void resetBlock(int pMode) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateBlock() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSearchEntity()
/*     */   {
/* 235 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean checkEntity(int pMode, Entity pEntity)
/*     */   {
/* 242 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int colorMultiplier(float pLight, float pPartialTicks)
/*     */   {
/* 249 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float attackEntityFrom(DamageSource par1DamageSource, float par2)
/*     */   {
/* 259 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean damageEntity(int pMode, DamageSource par1DamageSource, float par2)
/*     */   {
/* 266 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isUsingTile(TileEntity pTile)
/*     */   {
/* 273 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<TileEntity> getTiles()
/*     */   {
/* 280 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean canBlockBeSeen(int pX, int pY, int pZ, boolean toTop, boolean do1, boolean do2)
/*     */   {
/* 289 */     World worldObj = this.owner.worldObj;
/* 290 */     Block lblock = worldObj.getBlock(pX, pY, pZ);
/* 291 */     if (lblock == null) {
/* 292 */       LMM_LittleMaidMobX.Debug("block-null: %d, %d, %d", new Object[] { Integer.valueOf(pX), Integer.valueOf(pY), Integer.valueOf(pZ) });
/* 293 */       return false;
/*     */     }
/* 295 */     lblock.setBlockBoundsBasedOnState(worldObj, pX, pY, pZ);
/*     */     
/* 297 */     Vec3 vec3do = Vec3.createVectorHelper(this.owner.posX, this.owner.posY + this.owner.getEyeHeight(), this.owner.posZ);
/* 298 */     Vec3 vec3dt = Vec3.createVectorHelper(pX + 0.5D, pY + (lblock.getBlockBoundsMaxY() + lblock.getBlockBoundsMinY()) * (toTop ? 0.9D : 0.5D), pZ + 0.5D);
/* 299 */     MovingObjectPosition movingobjectposition = worldObj.func_147447_a(vec3do, vec3dt, do1, do2, false);
/*     */     
/* 301 */     if ((movingobjectposition != null) && (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK))
/*     */     {
/* 303 */       if ((movingobjectposition.blockX == pX) && (movingobjectposition.blockY == pY) && (movingobjectposition.blockZ == pZ))
/*     */       {
/*     */ 
/* 306 */         return true;
/*     */       }
/*     */     }
/* 309 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRangeToMaster(int pIndex)
/*     */   {
/* 320 */     return pIndex == 1 ? 25.0D : pIndex == 0 ? 36.0D : 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isChangeTartget(Entity pTarget)
/*     */   {
/* 329 */     return !this.owner.isBloodsuck();
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityModeBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
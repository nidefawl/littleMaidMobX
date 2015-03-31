/*     */ package mmmlibx.lib.guns;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlowerPot;
/*     */ import net.minecraft.block.BlockPane;
/*     */ import net.minecraft.block.BlockTNT;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityBulletBase extends Entity implements IProjectile, IEntityAdditionalSpawnData
/*     */ {
/*     */   protected EntityLivingBase thrower;
/*     */   protected String throwerName;
/*     */   protected Block inBlock;
/*  37 */   protected int inX = -1;
/*  38 */   protected int inY = -1;
/*  39 */   protected int inZ = -1;
/*     */   
/*  41 */   protected int ticksInGround = 1200;
/*  42 */   protected int ticksInAir = 0;
/*     */   
/*     */   protected boolean inGround;
/*  45 */   protected List<Entity> exclusionEntity = new ArrayList();
/*     */   
/*     */   public ItemStack bullet;
/*     */   
/*     */   public ItemStack gun;
/*     */   
/*     */   public float speed;
/*     */   
/*     */   public EntityBulletBase(World pWorld)
/*     */   {
/*  55 */     super(pWorld);
/*  56 */     setSize(0.25F, 0.25F);
/*  57 */     this.yOffset = 0.0F;
/*  58 */     this.renderDistanceWeight = 10.0D;
/*     */   }
/*     */   
/*  61 */   public EntityBulletBase(World pWorld, double par2, double par4, double par6) { this(pWorld);
/*  62 */     setPosition(par2, par4, par6);
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
/*     */   public EntityBulletBase(World pWorld, EntityLivingBase pEntity, ItemStack pGun, ItemStack pBullet, float pSpeed, float pf)
/*     */   {
/*  75 */     this(pWorld);
/*  76 */     this.thrower = pEntity;
/*  77 */     this.gun = pGun;
/*  78 */     this.bullet = pBullet;
/*  79 */     setSize(0.25F, 0.25F);
/*  80 */     setLocationAndAngles(pEntity.posX, pEntity.posY + pEntity.getEyeHeight(), pEntity.posZ, pEntity.rotationYaw, pEntity.rotationPitch);
/*  81 */     this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F;
/*  82 */     this.posY -= 0.10000000149011612D;
/*  83 */     this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F;
/*  84 */     setPosition(this.posX, this.posY, this.posZ);
/*  85 */     float f = 0.4F;
/*  86 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
/*  87 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
/*  88 */     this.motionY = (-MathHelper.sin((this.rotationPitch + appendAngle()) / 180.0F * 3.1415927F) * f);
/*  89 */     setThrowableHeading(this.motionX, this.motionY, this.motionZ, pSpeed, pf);
/*     */   }
/*     */   
/*     */   protected float appendAngle() {
/*  93 */     return 0.0F;
/*     */   }
/*     */   
/*     */   protected float getGravityVelocity() {
/*  97 */     return 0.03F;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getShadowSize() {
/* 102 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void entityInit() {}
/*     */   
/*     */ 
/*     */   public void setThrowableHeading(double pMotionX, double pMotionY, double pMotionZ, float pSpeed, float pReaction)
/*     */   {
/* 112 */     float lf = MathHelper.sqrt_double(pMotionX * pMotionX + pMotionY * pMotionY + pMotionZ * pMotionZ);
/* 113 */     pMotionX /= lf;
/* 114 */     pMotionY /= lf;
/* 115 */     pMotionZ /= lf;
/* 116 */     pMotionX += this.rand.nextGaussian() * 0.007499999832361937D * pReaction;
/* 117 */     pMotionY += this.rand.nextGaussian() * 0.007499999832361937D * pReaction;
/* 118 */     pMotionZ += this.rand.nextGaussian() * 0.007499999832361937D * pReaction;
/* 119 */     pMotionX *= pSpeed;
/* 120 */     pMotionY *= pSpeed;
/* 121 */     pMotionZ *= pSpeed;
/* 122 */     this.motionX = pMotionX;
/* 123 */     this.motionY = pMotionY;
/* 124 */     this.motionZ = pMotionZ;
/* 125 */     float f3 = MathHelper.sqrt_double(pMotionX * pMotionX + pMotionZ * pMotionZ);
/* 126 */     this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(pMotionX, pMotionZ) * 180.0D / 3.141592653589793D));
/* 127 */     this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(pMotionY, f3) * 180.0D / 3.141592653589793D));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean isInRangeToRenderDist(double par1) {
/* 132 */     double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/* 133 */     d1 *= 64.0D;
/* 134 */     return par1 < d1 * d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
/*     */   {
/* 141 */     if (!this.inGround) {
/* 142 */       setPosition(par1, par3, par5);
/* 143 */       setRotation(par7, par8);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void setVelocity(double par1, double par3, double par5) {
/* 149 */     this.motionX = par1;
/* 150 */     this.motionY = par3;
/* 151 */     this.motionZ = par5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 163 */     par1NBTTagCompound.setInteger("xTile", this.inX);
/* 164 */     par1NBTTagCompound.setInteger("yTile", this.inY);
/* 165 */     par1NBTTagCompound.setInteger("zTile", this.inZ);
/* 166 */     par1NBTTagCompound.setInteger("inTile", Block.getIdFromBlock(this.inBlock));
/*     */     
/* 168 */     par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/*     */     
/* 170 */     if (((this.throwerName == null) || (this.throwerName.length() == 0)) && (this.thrower != null) && ((this.thrower instanceof EntityPlayer)))
/*     */     {
/* 172 */       this.throwerName = this.thrower.getCommandSenderName();
/*     */     }
/*     */     
/* 175 */     par1NBTTagCompound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
/*     */     
/* 177 */     if (this.bullet != null) {
/* 178 */       NBTTagCompound lbullet = new NBTTagCompound();
/* 179 */       this.bullet.writeToNBT(lbullet);
/* 180 */       par1NBTTagCompound.setTag("bullet", lbullet);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 185 */     this.inX = par1NBTTagCompound.getInteger("xTile");
/* 186 */     this.inY = par1NBTTagCompound.getInteger("yTile");
/* 187 */     this.inZ = par1NBTTagCompound.getInteger("zTile");
/* 188 */     this.inBlock = Block.getBlockById(par1NBTTagCompound.getInteger("inTile"));
/* 189 */     this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
/* 190 */     this.throwerName = par1NBTTagCompound.getString("ownerName");
/*     */     
/* 192 */     if ((this.throwerName != null) && (this.throwerName.length() == 0)) {
/* 193 */       this.throwerName = null;
/*     */     }
/*     */     
/* 196 */     if (par1NBTTagCompound.hasKey("bullet")) {
/* 197 */       NBTTagCompound lbullet = par1NBTTagCompound.getCompoundTag("bullet");
/* 198 */       this.bullet = ItemStack.loadItemStackFromNBT(lbullet);
/*     */     }
/*     */   }
/*     */   
/*     */   public EntityLivingBase getThrower()
/*     */   {
/* 204 */     if ((this.thrower == null) && (this.throwerName != null) && (this.throwerName.length() > 0)) {
/* 205 */       this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
/*     */     }
/*     */     
/* 208 */     return this.thrower;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addExclusion(Entity pEntity)
/*     */   {
/* 217 */     this.exclusionEntity.add(pEntity);
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 221 */     this.lastTickPosX = this.posX;
/* 222 */     this.lastTickPosY = this.posY;
/* 223 */     this.lastTickPosZ = this.posZ;
/* 224 */     super.onUpdate();
/*     */     
/*     */ 
/* 227 */     if (this.inGround) {
/* 228 */       this.speed = 0.0F;
/* 229 */       if ((this.worldObj.getBlock(this.inX, this.inY, this.inZ) != this.inBlock) || (--this.ticksInGround <= 0))
/*     */       {
/*     */ 
/* 232 */         setDead();
/*     */       }
/* 234 */       return;
/*     */     }
/*     */     
/* 237 */     this.ticksInAir += 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 242 */     Vec3 lvo = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/* 243 */     Vec3 lvt = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */     
/*     */     MovingObjectPosition movingobjectposition;
/*     */     do
/*     */     {
/* 248 */       movingobjectposition = this.worldObj.func_147447_a(lvo, lvt, false, true, false);
/*     */       
/* 250 */       lvo = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
/* 251 */       lvt = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */       
/* 253 */       if (movingobjectposition != null) {
/* 254 */         lvt = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 260 */       if (!this.worldObj.isRemote) {
/* 261 */         Entity entity = null;
/*     */         
/* 263 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
/*     */         
/* 265 */         double d0 = 0.0D;
/* 266 */         EntityLivingBase entitylivingbase = getThrower();
/*     */         
/* 268 */         for (int j = 0; j < list.size(); j++) {
/* 269 */           Entity entity1 = (Entity)list.get(j);
/* 270 */           if (!this.exclusionEntity.contains(entity1))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 276 */             if ((entity1.canBeCollidedWith()) && ((entity1 != entitylivingbase) || (this.ticksInAir >= 5)))
/*     */             {
/* 278 */               float f = 0.3F;
/* 279 */               AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
/*     */               
/* 281 */               MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(lvo, lvt);
/*     */               
/*     */ 
/* 284 */               if (movingobjectposition1 != null) {
/* 285 */                 double d1 = lvo.distanceTo(movingobjectposition1.hitVec);
/*     */                 
/* 287 */                 if ((d1 < d0) || (d0 == 0.0D)) {
/* 288 */                   entity = entity1;
/* 289 */                   d0 = d1;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 295 */         if (entity != null) {
/* 296 */           movingobjectposition = new MovingObjectPosition(entity);
/*     */         }
/*     */       }
/*     */       
/* 300 */       this.speed = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/* 301 */       if (movingobjectposition == null) break;
/* 302 */       if ((movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) && (this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 307 */         setInPortal();
/* 308 */         break;
/*     */       }
/* 310 */     } while (!onImpact(movingobjectposition));
/* 311 */     GunsBase.Debug("hit %f, %f, %f", new Object[] { Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ) });
/* 312 */     return;
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
/* 325 */     this.posX += this.motionX;
/* 326 */     this.posY += this.motionY;
/* 327 */     this.posZ += this.motionZ;
/* 328 */     float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 329 */     this.rotationYaw = ((float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D));
/*     */     
/* 331 */     this.rotationPitch = ((float)(Math.atan2(this.motionY, f1) * 180.0D / 3.141592653589793D));
/* 332 */     while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
/* 333 */       this.prevRotationPitch -= 360.0F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 338 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
/* 339 */       this.prevRotationPitch += 360.0F;
/*     */     }
/* 341 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
/* 342 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/* 344 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
/* 345 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 348 */     this.rotationPitch = (this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F);
/* 349 */     this.rotationYaw = (this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F);
/* 350 */     float f2 = 0.99F;
/* 351 */     float f3 = getGravityVelocity();
/*     */     
/* 353 */     if (isInWater()) {
/* 354 */       for (int i = 0; i < 4; i++) {
/* 355 */         float f4 = 0.25F;
/* 356 */         this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 363 */       f2 = 0.8F;
/*     */     }
/*     */     
/* 366 */     this.motionX *= f2;
/* 367 */     this.motionY *= f2;
/* 368 */     this.motionZ *= f2;
/* 369 */     this.motionY -= f3;
/* 370 */     setPosition(this.posX, this.posY, this.posZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean onImpact(MovingObjectPosition var1)
/*     */   {
/* 380 */     if (var1.entityHit != null) {
/* 381 */       if (this.bullet != null) {
/* 382 */         return ((ItemBulletBase)this.bullet.getItem()).onHitEntity(var1, this, var1.entityHit);
/*     */       }
/* 384 */       var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 1.0F);
/*     */       
/* 386 */       if (!this.worldObj.isRemote) {
/* 387 */         setDead();
/*     */       }
/* 389 */       return true;
/*     */     }
/*     */     
/* 392 */     Block lblock = this.worldObj.getBlock(var1.blockX, var1.blockY, var1.blockZ);
/* 393 */     int lmeta = this.worldObj.getBlockMetadata(var1.blockX, var1.blockY, var1.blockZ);
/* 394 */     if (checkDestroyBlock(var1, var1.blockX, var1.blockY, var1.blockZ, lblock, lmeta)) {
/* 395 */       return onBreakBlock(var1, var1.blockX, var1.blockY, var1.blockZ, lblock, lmeta);
/*     */     }
/*     */     
/* 398 */     this.posX = var1.hitVec.xCoord;
/* 399 */     this.posY = var1.hitVec.yCoord;
/* 400 */     this.posZ = var1.hitVec.zCoord;
/* 401 */     this.motionX = 0.0D;
/* 402 */     this.motionY = 0.0D;
/* 403 */     this.motionZ = 0.0D;
/* 404 */     this.inGround = true;
/* 405 */     this.inBlock = lblock;
/* 406 */     this.inX = var1.blockX;
/* 407 */     this.inY = var1.blockY;
/* 408 */     this.inZ = var1.blockZ;
/* 409 */     setPosition(this.posX, this.posY, this.posZ);
/*     */     
/* 411 */     for (int i = 0; i < 8; i++)
/*     */     {
/* 413 */       this.worldObj.spawnParticle("smoke", var1.hitVec.xCoord, var1.hitVec.yCoord, var1.hitVec.zCoord, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/*     */ 
/* 417 */     return true;
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
/*     */   public boolean checkDestroyBlock(MovingObjectPosition var1, int pX, int pY, int pZ, Block pBlock, int pMetadata)
/*     */   {
/* 433 */     if ((((pBlock instanceof BlockPane)) && (pBlock.getMaterial() == Material.glass)) || ((pBlock instanceof BlockFlowerPot)) || ((pBlock instanceof BlockTNT)))
/*     */     {
/*     */ 
/* 436 */       return true;
/*     */     }
/* 438 */     return false;
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
/*     */   public boolean onBreakBlock(MovingObjectPosition var1, int pX, int pY, int pZ, Block pBlock, int pMetadata)
/*     */   {
/* 452 */     GunsBase.Debug("destroy: %d, %d, %d", new Object[] { Integer.valueOf(pX), Integer.valueOf(pY), Integer.valueOf(pZ) });
/* 453 */     if ((pBlock instanceof BlockTNT)) {
/* 454 */       removeBlock(pX, pY, pZ, pBlock, pMetadata);
/* 455 */       pBlock.onBlockDestroyedByExplosion(this.worldObj, pX, pY, pZ, new Explosion(this.worldObj, getThrower(), pX, pY, pZ, 0.0F));
/* 456 */       return true;
/*     */     }
/* 458 */     removeBlock(pX, pY, pZ, pBlock, pMetadata);
/* 459 */     pBlock.onBlockDestroyedByPlayer(this.worldObj, pX, pY, pZ, pMetadata);
/* 460 */     return false;
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
/*     */   protected void removeBlock(int pX, int pY, int pZ, Block pBlock, int pMetadata)
/*     */   {
/* 473 */     this.worldObj.playAuxSFX(2001, pX, pY, pZ, Block.getIdFromBlock(pBlock) + (pMetadata << 12));
/* 474 */     this.worldObj.setBlockToAir(pX, pY, pZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeSpawnData(ByteBuf buffer)
/*     */   {
/* 480 */     PacketBuffer lpbuf = new PacketBuffer(buffer);
/* 481 */     lpbuf.writeInt(getEntityId());
/* 482 */     lpbuf.writeBoolean(this.inGround);
/* 483 */     if (this.inGround) {
/* 484 */       lpbuf.writeInt(this.inX);
/* 485 */       lpbuf.writeInt(this.inY);
/* 486 */       lpbuf.writeInt(this.inZ);
/* 487 */       lpbuf.writeInt(Block.getIdFromBlock(this.inBlock));
/*     */     } else {
/* 489 */       lpbuf.writeInt(Float.floatToIntBits((float)this.motionX));
/* 490 */       lpbuf.writeInt(Float.floatToIntBits((float)this.motionY));
/* 491 */       lpbuf.writeInt(Float.floatToIntBits((float)this.motionZ));
/*     */     }
/*     */     try {
/* 494 */       lpbuf.writeItemStackToBuffer(this.bullet);
/*     */     } catch (Exception e) {
/* 496 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void readSpawnData(ByteBuf additionalData)
/*     */   {
/* 503 */     PacketBuffer lpbuf = new PacketBuffer(additionalData);
/* 504 */     Entity lentity = this.worldObj.getEntityByID(lpbuf.readInt());
/* 505 */     if ((lentity instanceof EntityLivingBase)) {
/* 506 */       this.thrower = ((EntityLivingBase)lentity);
/*     */     }
/* 508 */     this.inGround = lpbuf.readBoolean();
/* 509 */     if (this.inGround) {
/* 510 */       this.inX = lpbuf.readInt();
/* 511 */       this.inY = lpbuf.readInt();
/* 512 */       this.inZ = lpbuf.readInt();
/* 513 */       this.inBlock = Block.getBlockById(lpbuf.readInt());
/*     */     } else {
/* 515 */       this.motionX = Float.intBitsToFloat(lpbuf.readInt());
/* 516 */       this.motionY = Float.intBitsToFloat(lpbuf.readInt());
/* 517 */       this.motionZ = Float.intBitsToFloat(lpbuf.readInt());
/* 518 */       setVelocity(this.motionX, this.motionY, this.motionZ);
/*     */     }
/*     */     try {
/* 521 */       this.bullet = lpbuf.readItemStackFromBuffer();
/*     */     } catch (Exception e) {
/* 523 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getBulletColor()
/*     */   {
/* 534 */     if (this.bullet != null) {
/* 535 */       return ((ItemBulletBase)this.bullet.getItem()).getBulletColor(this.bullet);
/*     */     }
/* 537 */     return 8404992;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/guns/EntityBulletBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package zabuton;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class VZN_EntityZabuton
/*     */   extends Entity
/*     */   implements IProjectile, IEntityAdditionalSpawnData
/*     */ {
/*     */   protected double zabutonX;
/*     */   protected double zabutonY;
/*     */   protected double zabutonZ;
/*     */   protected double zabutonYaw;
/*     */   protected double zabutonPitch;
/*     */   protected double velocityX;
/*     */   protected double velocityY;
/*     */   protected double velocityZ;
/*     */   protected int health;
/*     */   public boolean isDispensed;
/*     */   public byte color;
/*     */   protected int boatPosRotationIncrements;
/*     */   
/*     */   public VZN_EntityZabuton(World world)
/*     */   {
/*  50 */     super(world);
/*  51 */     this.preventEntitySpawning = true;
/*  52 */     setSize(0.81F, 0.2F);
/*  53 */     this.yOffset = 0.0F;
/*  54 */     this.health = 20;
/*  55 */     this.isDispensed = false;
/*  56 */     this.color = -1;
/*     */   }
/*     */   
/*     */   public VZN_EntityZabuton(World world, byte pColor) {
/*  60 */     this(world);
/*  61 */     this.color = pColor;
/*     */   }
/*     */   
/*     */   public VZN_EntityZabuton(World world, ItemStack itemstack) {
/*  65 */     this(world, (byte)(itemstack.getItemDamage() & 0xF));
/*     */   }
/*     */   
/*     */   public VZN_EntityZabuton(World world, double x, double y, double z, byte pColor) {
/*  69 */     this(world, pColor);
/*  70 */     setPositionAndRotation(x, y + this.yOffset, z, 0.0F, 0.0F);
/*  71 */     this.motionX = 0.0D;
/*  72 */     this.motionY = 0.0D;
/*  73 */     this.motionZ = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setThrowableHeading(double px, double py, double pz, float f, float f1)
/*     */   {
/*  79 */     float f2 = MathHelper.sqrt_double(px * px + py * py + pz * pz);
/*  80 */     px /= f2;
/*  81 */     py /= f2;
/*  82 */     pz /= f2;
/*  83 */     px += this.rand.nextGaussian() * 0.007499999832361937D * f1;
/*  84 */     py += this.rand.nextGaussian() * 0.007499999832361937D * f1;
/*  85 */     pz += this.rand.nextGaussian() * 0.007499999832361937D * f1;
/*  86 */     px *= f;
/*  87 */     py *= f;
/*  88 */     pz *= f;
/*  89 */     this.motionX = px;
/*  90 */     this.motionY = py;
/*  91 */     this.motionZ = pz;
/*  92 */     float f3 = MathHelper.sqrt_double(px * px + pz * pz);
/*  93 */     this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(px, pz) * 180.0D / 3.1415927410125732D));
/*  94 */     this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(py, f3) * 180.0D / 3.1415927410125732D));
/*  95 */     setDispensed(true);
/*     */   }
/*     */   
/*     */   protected boolean canTriggerWalking()
/*     */   {
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   protected void entityInit()
/*     */   {
/* 105 */     this.dataWatcher.addObject(17, new Byte((byte)(this.isDispensed ? 1 : 0)));
/* 106 */     this.dataWatcher.addObject(18, Integer.valueOf(0));
/* 107 */     this.dataWatcher.addObject(19, new Byte((byte)-1));
/*     */   }
/*     */   
/*     */   public AxisAlignedBB getCollisionBox(Entity par1Entity)
/*     */   {
/* 112 */     return par1Entity.boundingBox;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB getBoundingBox()
/*     */   {
/* 117 */     return this.boundingBox;
/*     */   }
/*     */   
/*     */   public boolean canBePushed()
/*     */   {
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 127 */     this.color = nbttagcompound.getByte("Color");
/* 128 */     this.health = nbttagcompound.getShort("Health");
/*     */   }
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 132 */     nbttagcompound.setByte("Color", (byte)(this.color & 0xF));
/* 133 */     nbttagcompound.setShort("Health", (short)(byte)this.health);
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 138 */     data.writeByte(this.color);
/* 139 */     data.writeFloat(this.rotationYaw);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/* 143 */     this.color = data.readByte();
/* 144 */     setRotation(data.readFloat(), 0.0F);
/*     */   }
/*     */   
/*     */   public double getMountedYOffset()
/*     */   {
/* 149 */     if ((this.riddenByEntity instanceof EntitySpider)) {
/* 150 */       return this.height * 0.0D - 0.1D;
/*     */     }
/* 152 */     if (((this.riddenByEntity instanceof EntityZombie)) || ((this.riddenByEntity instanceof EntityEnderman)))
/*     */     {
/* 154 */       return this.height * 0.0D - 0.4D;
/*     */     }
/*     */     
/* 157 */     return this.height * 0.0D + 0.1D;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean handleWaterMovement()
/*     */   {
/* 163 */     int var4 = MathHelper.floor_double(this.boundingBox.minX);
/* 164 */     int var5 = MathHelper.floor_double(this.boundingBox.maxX + 1.0D);
/* 165 */     int var6 = MathHelper.floor_double(this.boundingBox.minY);
/* 166 */     int var7 = MathHelper.floor_double(this.boundingBox.maxY + 1.0D);
/* 167 */     int var8 = MathHelper.floor_double(this.boundingBox.minZ);
/* 168 */     int var9 = MathHelper.floor_double(this.boundingBox.maxZ + 1.0D);
/*     */     
/* 170 */     if (!this.worldObj.checkChunksExist(var4, var6, var8, var5, var7, var9)) {
/* 171 */       return false;
/*     */     }
/* 173 */     boolean var10 = false;
/*     */     
/* 175 */     for (int var12 = var4; var12 < var5; var12++) {
/* 176 */       for (int var13 = var6; var13 < var7; var13++) {
/* 177 */         for (int var14 = var8; var14 < var9; var14++) {
/* 178 */           Block var15 = this.worldObj.getBlock(var12, var13, var14);
/*     */           
/* 180 */           if ((var15 != null) && (var15.getMaterial() == Material.water)) {
/* 181 */             this.inWater = true;
/* 182 */             double var16 = var13 + 1 - BlockLiquid.getLiquidHeightPercent(this.worldObj.getBlockMetadata(var12, var13, var14));
/*     */             
/* 184 */             if (var7 >= var16) {
/* 185 */               var10 = true;
/*     */             }
/*     */           } else {
/* 188 */             this.inWater = false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 193 */     return var10;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float pDammage)
/*     */   {
/* 199 */     Entity entity = damagesource.getEntity();
/* 200 */     if ((this.worldObj.isRemote) || (this.isDead)) {
/* 201 */       return true;
/*     */     }
/* 203 */     setBeenAttacked();
/* 204 */     if ((entity instanceof EntityPlayer)) {
/* 205 */       if ((this.color >= 0) && (this.color < 16) && (!((EntityPlayer)entity).capabilities.isCreativeMode))
/*     */       {
/* 207 */         entityDropItem(new ItemStack(VZN_Zabuton.zabuton, 1, this.color), 0.0F);
/*     */       }
/* 209 */       setDead();
/*     */     } else {
/* 211 */       this.health = ((int)(this.health - pDammage));
/* 212 */       if (this.health <= 0) {
/* 213 */         setDead();
/*     */       }
/*     */     }
/* 216 */     if ((this.isDead) && (this.riddenByEntity != null)) {
/* 217 */       this.riddenByEntity.mountEntity(null);
/* 218 */       setRiddenByEntityID(this.riddenByEntity);
/*     */     }
/* 220 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canBeCollidedWith()
/*     */   {
/* 225 */     return !this.isDead;
/*     */   }
/*     */   
/*     */   public void setPositionAndRotation2(double px, double py, double pz, float f, float f1, int i)
/*     */   {
/* 230 */     setPosition(px, py, pz);
/* 231 */     setRotation(f, f1);
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
/*     */ 
/*     */ 
/*     */   public void setVelocity(double d, double d1, double d2)
/*     */   {
/* 256 */     this.velocityX = (this.motionX = d);
/* 257 */     this.velocityY = (this.motionY = d1);
/* 258 */     this.velocityZ = (this.motionZ = d2);
/*     */   }
/*     */   
/*     */   public void onUpdate()
/*     */   {
/* 263 */     super.onUpdate();
/*     */     
/*     */ 
/* 266 */     if (!this.worldObj.isRemote)
/*     */     {
/* 268 */       this.dataWatcher.updateObject(19, Byte.valueOf(this.color));
/*     */     }
/*     */     else
/*     */     {
/* 272 */       this.color = this.dataWatcher.getWatchableObjectByte(19);
/*     */     }
/*     */     
/* 275 */     this.prevPosX = this.posX;
/* 276 */     this.prevPosY = this.posY;
/* 277 */     this.prevPosZ = this.posZ;
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
/* 288 */     double var24 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */     
/* 290 */     if (this.worldObj.isRemote)
/*     */     {
/* 292 */       if (this.boatPosRotationIncrements > 0) {
/* 293 */         double var6 = this.posX + (this.zabutonX - this.posX) / this.boatPosRotationIncrements;
/* 294 */         double var8 = this.posY + (this.zabutonY - this.posY) / this.boatPosRotationIncrements;
/* 295 */         double var26 = this.posZ + (this.zabutonZ - this.posZ) / this.boatPosRotationIncrements;
/* 296 */         double var12 = MathHelper.wrapAngleTo180_double(this.zabutonYaw - this.rotationYaw);
/* 297 */         this.rotationYaw = ((float)(this.rotationYaw + var12 / this.boatPosRotationIncrements));
/* 298 */         this.rotationPitch = ((float)(this.rotationPitch + (this.zabutonPitch - this.rotationPitch) / this.boatPosRotationIncrements));
/* 299 */         this.boatPosRotationIncrements -= 1;
/* 300 */         setPosition(var6, var8, var26);
/* 301 */         setRotation(this.rotationYaw, this.rotationPitch);
/*     */       } else {
/* 303 */         this.motionY -= 0.08D;
/* 304 */         if (this.onGround) {
/* 305 */           this.motionX *= 0.5D;
/* 306 */           this.motionY *= 0.5D;
/* 307 */           this.motionZ *= 0.5D;
/* 308 */           setDispensed(false);
/*     */         }
/* 310 */         moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */         
/* 312 */         this.motionX *= 0.9900000095367432D;
/* 313 */         this.motionY *= 0.949999988079071D;
/* 314 */         this.motionZ *= 0.9900000095367432D;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 319 */       this.motionY -= 0.08D;
/*     */       
/*     */ 
/* 322 */       if ((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityPlayer))) {
/* 323 */         this.motionX += this.riddenByEntity.motionX * 0.2D;
/* 324 */         this.motionZ += this.riddenByEntity.motionZ * 0.2D;
/*     */       }
/*     */       
/*     */ 
/* 328 */       Double lmaxspeed = Double.valueOf(isDispensed() ? 10.0D : 0.35D);
/* 329 */       double var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 330 */       if (var6 > lmaxspeed.doubleValue()) {
/* 331 */         double var8 = lmaxspeed.doubleValue() / var6;
/* 332 */         this.motionX *= var8;
/* 333 */         this.motionZ *= var8;
/* 334 */         var6 = lmaxspeed.doubleValue();
/*     */       }
/* 336 */       if (this.onGround) {
/* 337 */         this.motionX *= 0.5D;
/* 338 */         this.motionY *= 0.5D;
/* 339 */         this.motionZ *= 0.5D;
/* 340 */         setDispensed(false);
/*     */       }
/*     */       
/*     */ 
/* 344 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */       
/* 346 */       this.motionX *= 0.9900000095367432D;
/* 347 */       this.motionY *= 0.949999988079071D;
/* 348 */       this.motionZ *= 0.9900000095367432D;
/*     */       
/*     */ 
/* 351 */       this.rotationPitch = 0.0F;
/* 352 */       double var8 = this.rotationYaw;
/* 353 */       double var26 = this.prevPosX - this.posX;
/* 354 */       double var12 = this.prevPosZ - this.posZ;
/*     */       
/* 356 */       if (var26 * var26 + var12 * var12 > 0.001D) {
/* 357 */         var8 = (float)(Math.atan2(var12, var26) * 180.0D / 3.141592653589793D);
/*     */       }
/*     */       
/* 360 */       double var14 = MathHelper.wrapAngleTo180_double(var8 - this.rotationYaw);
/* 361 */       if (var14 > 20.0D) {
/* 362 */         var14 = 20.0D;
/*     */       }
/* 364 */       if (var14 < -20.0D) {
/* 365 */         var14 = -20.0D;
/*     */       }
/*     */       
/* 368 */       this.rotationYaw = ((float)(this.rotationYaw + var14));
/* 369 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */       
/*     */ 
/* 372 */       List var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.17D, 0.0D, 0.17D));
/* 373 */       if ((var16 != null) && (!var16.isEmpty())) {
/* 374 */         Iterator var28 = var16.iterator();
/*     */         
/* 376 */         while (var28.hasNext()) {
/* 377 */           Entity var18 = (Entity)var28.next();
/*     */           
/* 379 */           if ((var18 != this.riddenByEntity) && (var18.canBePushed()) && ((var18 instanceof VZN_EntityZabuton))) {
/* 380 */             var18.applyEntityCollision(this);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 385 */     if (this.riddenByEntity != null) {
/* 386 */       if ((this.riddenByEntity instanceof EntityMob))
/*     */       {
/* 388 */         setEntityLivingAge((EntityLivingBase)this.riddenByEntity, 0);
/*     */       }
/* 390 */       if (this.riddenByEntity.isDead)
/*     */       {
/* 392 */         this.riddenByEntity = null;
/* 393 */         setRiddenByEntityID(this.riddenByEntity);
/*     */       }
/* 395 */       else if (this.inWater)
/*     */       {
/* 397 */         this.riddenByEntity.mountEntity(null);
/* 398 */         setRiddenByEntityID(this.riddenByEntity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setEntityLivingAge(EntityLivingBase entity, int a)
/*     */   {
/* 405 */     ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, entity, Integer.valueOf(a), new String[] { "field_70708_bq", "entityAge" });
/*     */   }
/*     */   
/*     */ 
/*     */   public void applyEntityCollision(Entity entity)
/*     */   {
/* 411 */     if (this.worldObj.isRemote) {
/* 412 */       return;
/*     */     }
/* 414 */     if (entity == this.riddenByEntity) {
/* 415 */       return;
/*     */     }
/* 417 */     if (((entity instanceof EntityLiving)) && (!(entity instanceof EntityPlayer)) && (this.riddenByEntity == null) && (entity.ridingEntity == null)) {
/* 418 */       entity.mountEntity(this);
/* 419 */       setRiddenByEntityID(this.riddenByEntity);
/*     */     }
/* 421 */     super.applyEntityCollision(entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean interactFirst(EntityPlayer entityplayer)
/*     */   {
/* 427 */     if ((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityPlayer)) && (this.riddenByEntity != entityplayer)) {
/* 428 */       return true;
/*     */     }
/* 430 */     if (!this.worldObj.isRemote) {
/* 431 */       entityplayer.mountEntity(this);
/*     */     }
/* 433 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDispensed()
/*     */   {
/* 438 */     return this.dataWatcher.getWatchableObjectByte(17) > 0;
/*     */   }
/*     */   
/*     */   public void setDispensed(boolean isDispensed) {
/* 442 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)(isDispensed ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public int getRiddenByEntityID()
/*     */   {
/* 447 */     int li = this.dataWatcher.getWatchableObjectInt(18);
/* 448 */     return li;
/*     */   }
/*     */   
/*     */   public Entity getRiddenByEntity() {
/* 452 */     return ((WorldClient)this.worldObj).getEntityByID(getRiddenByEntityID());
/*     */   }
/*     */   
/*     */   public void setRiddenByEntityID(Entity pentity) {
/* 456 */     this.dataWatcher.updateObject(18, Integer.valueOf(pentity == null ? 0 : pentity.getEntityId()));
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_EntityZabuton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
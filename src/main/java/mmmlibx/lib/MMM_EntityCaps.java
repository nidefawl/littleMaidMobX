/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ 
/*     */ public class MMM_EntityCaps
/*     */   implements IModelCaps
/*     */ {
/*     */   protected EntityLivingBase owner;
/*  20 */   private static Map<String, Integer> caps = new HashMap();
/*  21 */   static { caps.put("Entity", Integer.valueOf(32));
/*  22 */     caps.put("health", Integer.valueOf(33));
/*  23 */     caps.put("healthFloat", Integer.valueOf(37));
/*  24 */     caps.put("ticksExisted", Integer.valueOf(34));
/*  25 */     caps.put("heldItems", Integer.valueOf(18));
/*  26 */     caps.put("currentEquippedItem", Integer.valueOf(35));
/*  27 */     caps.put("currentArmor", Integer.valueOf(36));
/*  28 */     caps.put("onGround", Integer.valueOf(1));
/*  29 */     caps.put("isRiding", Integer.valueOf(2));
/*  30 */     caps.put("isChild", Integer.valueOf(3));
/*  31 */     caps.put("isWet", Integer.valueOf(48));
/*  32 */     caps.put("isDead", Integer.valueOf(49));
/*  33 */     caps.put("isJumping", Integer.valueOf(50));
/*  34 */     caps.put("isInWeb", Integer.valueOf(51));
/*  35 */     caps.put("isSwingInProgress", Integer.valueOf(52));
/*  36 */     caps.put("isSneak", Integer.valueOf(19));
/*  37 */     caps.put("isBlocking", Integer.valueOf(262));
/*  38 */     caps.put("isBurning", Integer.valueOf(54));
/*  39 */     caps.put("isInWater", Integer.valueOf(55));
/*  40 */     caps.put("isInvisible", Integer.valueOf(56));
/*  41 */     caps.put("isSprinting", Integer.valueOf(57));
/*     */     
/*  43 */     caps.put("PosBlockID", Integer.valueOf(129));
/*  44 */     caps.put("PosBlockMeta", Integer.valueOf(130));
/*  45 */     caps.put("PosBlockAir", Integer.valueOf(131));
/*  46 */     caps.put("PosBlockLight", Integer.valueOf(132));
/*  47 */     caps.put("PosBlockPower", Integer.valueOf(133));
/*  48 */     caps.put("isRidingPlayer", Integer.valueOf(134));
/*  49 */     caps.put("posX", Integer.valueOf(96));
/*  50 */     caps.put("posY", Integer.valueOf(97));
/*  51 */     caps.put("posZ", Integer.valueOf(98));
/*  52 */     caps.put("pos", Integer.valueOf(99));
/*  53 */     caps.put("motionX", Integer.valueOf(100));
/*  54 */     caps.put("motionY", Integer.valueOf(101));
/*  55 */     caps.put("motionZ", Integer.valueOf(102));
/*  56 */     caps.put("motion", Integer.valueOf(99));
/*  57 */     caps.put("WorldTotalTime", Integer.valueOf(65280));
/*  58 */     caps.put("WorldTime", Integer.valueOf(65281));
/*  59 */     caps.put("MoonPhase", Integer.valueOf(65282));
/*  60 */     caps.put("rotationYaw", Integer.valueOf(105));
/*  61 */     caps.put("rotationPitch", Integer.valueOf(106));
/*  62 */     caps.put("prevRotationYaw", Integer.valueOf(107));
/*  63 */     caps.put("prevRotationPitch", Integer.valueOf(108));
/*  64 */     caps.put("renderYawOffset", Integer.valueOf(109));
/*  65 */     caps.put("TextureEntity", Integer.valueOf(38));
/*     */   }
/*     */   
/*     */ 
/*     */   public static Map<String, Integer> getStaticModelCaps()
/*     */   {
/*  71 */     return caps;
/*     */   }
/*     */   
/*     */   public MMM_EntityCaps(EntityLivingBase pOwner) {
/*  75 */     this.owner = pOwner;
/*     */   }
/*     */   
/*     */   public Map<String, Integer> getModelCaps()
/*     */   {
/*  80 */     return caps;
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/*  85 */     switch (pIndex) {
/*     */     case 32: 
/*  87 */       return this.owner;
/*     */     case 33: 
/*  89 */       return Integer.valueOf((int)this.owner.getHealth());
/*     */     case 37: 
/*  91 */       return Float.valueOf(this.owner.getHealth());
/*     */     case 34: 
/*  93 */       return Integer.valueOf(this.owner.ticksExisted);
/*     */     case 18: 
/*     */     case 35: 
/*  96 */       return this.owner.getHeldItem();
/*     */     case 36: 
/*  98 */       return this.owner.getEquipmentInSlot(((Integer)pArg[0]).intValue() + 1);
/*     */     case 96: 
/* 100 */       return Double.valueOf(this.owner.posX);
/*     */     case 97: 
/* 102 */       return Double.valueOf(this.owner.posY);
/*     */     case 98: 
/* 104 */       return Double.valueOf(this.owner.posZ);
/*     */     case 99: 
/* 106 */       if (pArg == null) {
/* 107 */         return new Double[] { Double.valueOf(this.owner.posX), Double.valueOf(this.owner.posY), Double.valueOf(this.owner.posZ) };
/*     */       }
/* 109 */       return Double.valueOf(((Integer)pArg[0]).intValue() == 1 ? this.owner.posY : ((Integer)pArg[0]).intValue() == 0 ? this.owner.posX : this.owner.posZ);
/*     */     
/*     */     case 100: 
/* 112 */       return Double.valueOf(this.owner.motionX);
/*     */     case 101: 
/* 114 */       return Double.valueOf(this.owner.motionY);
/*     */     case 102: 
/* 116 */       return Double.valueOf(this.owner.motionZ);
/*     */     case 103: 
/* 118 */       if (pArg == null) {
/* 119 */         return new Double[] { Double.valueOf(this.owner.motionX), Double.valueOf(this.owner.motionY), Double.valueOf(this.owner.motionZ) };
/*     */       }
/* 121 */       return Double.valueOf(((Integer)pArg[0]).intValue() == 1 ? this.owner.motionY : ((Integer)pArg[0]).intValue() == 0 ? this.owner.motionX : this.owner.motionZ);
/*     */     
/*     */ 
/*     */     case 105: 
/* 125 */       return Float.valueOf(this.owner.rotationYaw);
/*     */     case 106: 
/* 127 */       return Float.valueOf(this.owner.rotationPitch);
/*     */     case 107: 
/* 129 */       return Float.valueOf(this.owner.prevRotationYaw);
/*     */     case 108: 
/* 131 */       return Float.valueOf(this.owner.prevRotationPitch);
/*     */     case 109: 
/* 133 */       return Float.valueOf(this.owner.renderYawOffset);
/*     */     
/*     */     case 1: 
/* 136 */       return Boolean.valueOf(this.owner.onGround);
/*     */     case 2: 
/* 138 */       return Boolean.valueOf(this.owner.isRiding());
/*     */     case 134: 
/* 140 */       return Boolean.valueOf(this.owner.ridingEntity instanceof EntityPlayer);
/*     */     case 3: 
/* 142 */       return Boolean.valueOf(this.owner.isChild());
/*     */     case 48: 
/* 144 */       return Boolean.valueOf(this.owner.isWet());
/*     */     case 49: 
/* 146 */       return Boolean.valueOf(this.owner.isDead);
/*     */     case 50: 
/* 148 */       return Boolean.valueOf(false);
/*     */     case 51: 
/* 150 */       return Boolean.valueOf(false);
/*     */     case 52: 
/* 152 */       return Boolean.valueOf(this.owner.isSwingInProgress);
/*     */     case 19: 
/* 154 */       return Boolean.valueOf(this.owner.isSneaking());
/*     */     
/*     */ 
/*     */     case 54: 
/* 158 */       return Boolean.valueOf(this.owner.isBurning());
/*     */     case 55: 
/* 160 */       return Boolean.valueOf(this.owner.isInWater());
/*     */     case 56: 
/* 162 */       return Boolean.valueOf(this.owner.isInvisible());
/*     */     case 57: 
/* 164 */       return Boolean.valueOf(this.owner.isSprinting());
/*     */     case 129: 
/* 166 */       return this.owner.worldObj.getBlock(MathHelper.floor_double(this.owner.posX + ((Double)pArg[0]).doubleValue()), MathHelper.floor_double(this.owner.posY + ((Double)pArg[1]).doubleValue()), MathHelper.floor_double(this.owner.posZ + ((Double)pArg[2]).doubleValue()));
/*     */     
/*     */ 
/*     */ 
/*     */     case 130: 
/* 171 */       return Integer.valueOf(this.owner.worldObj.getBlockMetadata(MathHelper.floor_double(this.owner.posX + ((Double)pArg[0]).doubleValue()), MathHelper.floor_double(this.owner.posY + ((Double)pArg[1]).doubleValue()), MathHelper.floor_double(this.owner.posZ + ((Double)pArg[2]).doubleValue())));
/*     */     
/*     */ 
/*     */ 
/*     */     case 131: 
/* 176 */       return Boolean.valueOf(this.owner.worldObj.isAirBlock(MathHelper.floor_double(this.owner.posX + ((Double)pArg[0]).doubleValue()), MathHelper.floor_double(this.owner.posY + ((Double)pArg[1]).doubleValue()), MathHelper.floor_double(this.owner.posZ + ((Double)pArg[2]).doubleValue())));
/*     */     
/*     */ 
/*     */ 
/*     */     case 132: 
/* 181 */       return Integer.valueOf(this.owner.worldObj.getBlockLightValue(MathHelper.floor_double(this.owner.posX + ((Double)pArg[0]).doubleValue()), MathHelper.floor_double(this.owner.posY + ((Double)pArg[1]).doubleValue()), MathHelper.floor_double(this.owner.posZ + ((Double)pArg[2]).doubleValue())));
/*     */     
/*     */ 
/*     */ 
/*     */     case 133: 
/* 186 */       return Integer.valueOf(this.owner.worldObj.getBlockPowerInput(MathHelper.floor_double(this.owner.posX + ((Double)pArg[0]).doubleValue()), MathHelper.floor_double(this.owner.posY + ((Double)pArg[1]).doubleValue()), MathHelper.floor_double(this.owner.posZ + ((Double)pArg[2]).doubleValue())));
/*     */     
/*     */ 
/*     */ 
/*     */     case 104: 
/* 191 */       if (pArg == null) {
/* 192 */         return this.owner.boundingBox;
/*     */       }
/* 194 */       switch (((Integer)pArg[0]).intValue()) {
/*     */       case 0: 
/* 196 */         return Double.valueOf(this.owner.boundingBox.maxX);
/*     */       case 1: 
/* 198 */         return Double.valueOf(this.owner.boundingBox.maxY);
/*     */       case 2: 
/* 200 */         return Double.valueOf(this.owner.boundingBox.maxZ);
/*     */       case 3: 
/* 202 */         return Double.valueOf(this.owner.boundingBox.minX);
/*     */       case 4: 
/* 204 */         return Double.valueOf(this.owner.boundingBox.minY);
/*     */       case 5: 
/* 206 */         return Double.valueOf(this.owner.boundingBox.minZ);
/*     */       }
/*     */       
/*     */     case 58: 
/* 210 */       return Boolean.valueOf(((this.owner instanceof EntityLiving)) && (((EntityLiving)this.owner).getLeashed()));
/*     */     case 59: 
/* 212 */       return this.owner.ridingEntity == null ? "" : EntityList.getEntityString(this.owner.ridingEntity);
/*     */     
/*     */ 
/*     */     case 65280: 
/* 216 */       return Long.valueOf(this.owner.worldObj.getWorldInfo().getWorldTotalTime());
/*     */     case 65281: 
/* 218 */       return Long.valueOf(this.owner.worldObj.getWorldInfo().getWorldTime());
/*     */     case 65282: 
/* 220 */       return Integer.valueOf(this.owner.worldObj.getMoonPhase());
/*     */     case 38: 
/* 222 */       return this.owner;
/*     */     }
/*     */     
/* 225 */     return null;
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 230 */     switch (pIndex) {
/*     */     case 33: 
/* 232 */       this.owner.setHealth(((Integer)pArg[0]).intValue());
/* 233 */       return true;
/*     */     case 34: 
/* 235 */       this.owner.ticksExisted = ((Integer)pArg[0]).intValue();
/* 236 */       return true;
/*     */     case 18: 
/*     */     case 35: 
/* 239 */       this.owner.setCurrentItemOrArmor(((Integer)pArg[0]).intValue(), (ItemStack)pArg[1]);
/* 240 */       return true;
/*     */     case 36: 
/* 242 */       this.owner.setCurrentItemOrArmor(((Integer)pArg[0]).intValue() + 1, (ItemStack)pArg[1]);
/* 243 */       return true;
/*     */     case 96: 
/* 245 */       this.owner.posX = ((Double)pArg[0]).doubleValue();
/* 246 */       return true;
/*     */     case 97: 
/* 248 */       this.owner.posY = ((Double)pArg[0]).doubleValue();
/* 249 */       return true;
/*     */     case 98: 
/* 251 */       this.owner.posZ = ((Double)pArg[0]).doubleValue();
/* 252 */       return true;
/*     */     case 99: 
/* 254 */       this.owner.setPosition(((Double)pArg[0]).doubleValue(), ((Double)pArg[1]).doubleValue(), ((Double)pArg[2]).doubleValue());
/* 255 */       return true;
/*     */     case 100: 
/* 257 */       this.owner.motionX = ((Double)pArg[0]).doubleValue();
/* 258 */       return true;
/*     */     case 101: 
/* 260 */       this.owner.motionY = ((Double)pArg[0]).doubleValue();
/* 261 */       return true;
/*     */     case 102: 
/* 263 */       this.owner.motionZ = ((Double)pArg[0]).doubleValue();
/* 264 */       return true;
/*     */     case 103: 
/* 266 */       this.owner.setVelocity(((Double)pArg[0]).doubleValue(), ((Double)pArg[1]).doubleValue(), ((Double)pArg[2]).doubleValue());
/* 267 */       return true;
/*     */     case 1: 
/* 269 */       this.owner.onGround = ((Boolean)pArg[0]).booleanValue();
/* 270 */       return true;
/*     */     case 2: 
/* 272 */       return this.owner.isRiding();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     case 19: 
/* 280 */       this.owner.setSneaking(((Boolean)pArg[0]).booleanValue());
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 288 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_EntityCaps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
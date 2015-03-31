/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*     */ 
/*     */ public class MMM_Helper
/*     */ {
/*  44 */   public static final Package fpackage = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  49 */   public static Class entityRegistry = null;
/*  50 */   public static Method registerModEntity = null;
/*  51 */   protected static final Map<Class, Class> replaceEntitys = new HashMap();
/*  52 */   protected static Map<String, Integer> entityIDList = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   static
/*     */   {
/*  59 */     Minecraft lm = null;
/*     */     try {
/*  61 */       lm = Minecraft.getMinecraft();
/*     */     }
/*     */     catch (Exception e) {}catch (Error e) {}
/*     */     
/*     */ 
/*     */ 
/*  67 */     mc = lm; }
/*  68 */   public static final boolean isClient = mc != null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final boolean isForge = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final Minecraft mc;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isLocalPlay()
/*     */   {
/*  91 */     return (isClient) && (mc.isIntegratedServerRunning());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void updateCheckinghSlot(Entity pEntity, ItemStack pItemstack)
/*     */   {
/* 101 */     if ((pEntity instanceof EntityPlayerMP))
/*     */     {
/* 103 */       EntityPlayerMP lep = (EntityPlayerMP)pEntity;
/* 104 */       Container lctr = lep.openContainer;
/* 105 */       for (int li = 0; li < lctr.inventorySlots.size(); li++) {
/* 106 */         ItemStack lis = lctr.getSlot(li).getStack();
/* 107 */         if (lis == pItemstack) {
/* 108 */           lctr.inventoryItemStacks.set(li, pItemstack.copy());
/* 109 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getPlayerName(EntityPlayer player)
/*     */   {
/* 117 */     return player.getGameProfile().getName();
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
/*     */   public static Class getNameOfClass(String pName)
/*     */   {
/* 137 */     if (fpackage != null) {
/* 138 */       pName = fpackage.getName() + "." + pName;
/*     */     }
/* 140 */     Class lclass = null;
/*     */     try {
/* 142 */       lclass = Class.forName(pName);
/*     */     } catch (Exception e) {
/* 144 */       MMMLib.Debug("Class:%s is not found.", new Object[] { pName });
/*     */     }
/*     */     
/* 147 */     return lclass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void setValue(byte[] pData, int pIndex, int pVal, int pSize)
/*     */   {
/* 154 */     for (int li = 0; li < pSize; li++) {
/* 155 */       pData[(pIndex++)] = ((byte)(pVal & 0xFF));
/* 156 */       pVal >>>= 8;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setInt(byte[] pData, int pIndex, int pVal) {
/* 161 */     pData[(pIndex + 3)] = ((byte)(pVal & 0xFF));
/* 162 */     pData[(pIndex + 2)] = ((byte)(pVal >>> 8 & 0xFF));
/* 163 */     pData[(pIndex + 1)] = ((byte)(pVal >>> 16 & 0xFF));
/* 164 */     pData[(pIndex + 0)] = ((byte)(pVal >>> 24 & 0xFF));
/*     */   }
/*     */   
/*     */   public static int getInt(byte[] pData, int pIndex) {
/* 168 */     return pData[(pIndex + 3)] & 0xFF | (pData[(pIndex + 2)] & 0xFF) << 8 | (pData[(pIndex + 1)] & 0xFF) << 16 | (pData[(pIndex + 0)] & 0xFF) << 24;
/*     */   }
/*     */   
/*     */   public static void setFloat(byte[] pData, int pIndex, float pVal) {
/* 172 */     setInt(pData, pIndex, Float.floatToIntBits(pVal));
/*     */   }
/*     */   
/*     */   public static float getFloat(byte[] pData, int pIndex) {
/* 176 */     return Float.intBitsToFloat(getInt(pData, pIndex));
/*     */   }
/*     */   
/*     */   public static void setShort(byte[] pData, int pIndex, int pVal) {
/* 180 */     pData[(pIndex++)] = ((byte)(pVal & 0xFF));
/* 181 */     pData[pIndex] = ((byte)(pVal >>> 8 & 0xFF));
/*     */   }
/*     */   
/*     */   public static short getShort(byte[] pData, int pIndex) {
/* 185 */     return (short)(pData[pIndex] & 0xFF | (pData[(pIndex + 1)] & 0xFF) << 8);
/*     */   }
/*     */   
/*     */   public static String getStr(byte[] pData, int pIndex, int pLen) {
/* 189 */     String ls = new String(pData, pIndex, pLen);
/* 190 */     return ls;
/*     */   }
/*     */   
/* 193 */   public static String getStr(byte[] pData, int pIndex) { return getStr(pData, pIndex, pData.length - pIndex); }
/*     */   
/*     */   public static void setStr(byte[] pData, int pIndex, String pVal)
/*     */   {
/* 197 */     byte[] lb = pVal.getBytes();
/* 198 */     for (int li = pIndex; li < pData.length; li++) {
/* 199 */       pData[li] = lb[(li - pIndex)];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean canBlockBeSeen(Entity pEntity, int x, int y, int z, boolean toTop, boolean do1, boolean do2)
/*     */   {
/* 206 */     Vec3 vec3d = Vec3.createVectorHelper(pEntity.posX, pEntity.posY + pEntity.getEyeHeight(), pEntity.posZ);
/* 207 */     Vec3 vec3d1 = Vec3.createVectorHelper(x + 0.5D, y + (toTop ? 0.9D : 0.5D), z + 0.5D);
/*     */     
/* 209 */     MovingObjectPosition movingobjectposition = pEntity.worldObj.func_147447_a(vec3d, vec3d1, do1, do2, false);
/* 210 */     if (movingobjectposition == null) {
/* 211 */       return false;
/*     */     }
/* 213 */     if ((movingobjectposition.typeOfHit == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && 
/* 214 */       (movingobjectposition.blockX == MathHelper.floor_double(vec3d1.xCoord)) && (movingobjectposition.blockY == MathHelper.floor_double(vec3d1.yCoord)) && (movingobjectposition.blockZ == MathHelper.floor_double(vec3d1.zCoord)))
/*     */     {
/*     */ 
/* 217 */       return true;
/*     */     }
/*     */     
/* 220 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean setPathToTile(EntityLiving pEntity, TileEntity pTarget, boolean flag)
/*     */   {
/* 225 */     PathNavigate lpn = pEntity.getNavigator();
/* 226 */     float lspeed = 1.0F;
/*     */     
/* 228 */     int i = (pTarget.yCoord == MathHelper.floor_double(pEntity.posY)) && (flag) ? 2 : 1;
/* 229 */     switch (pEntity.worldObj.getBlockMetadata(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord)) {
/*     */     case 3: 
/* 231 */       return lpn.tryMoveToXYZ(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord + i, lspeed);
/*     */     case 2: 
/* 233 */       return lpn.tryMoveToXYZ(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord - i, lspeed);
/*     */     case 5: 
/* 235 */       return lpn.tryMoveToXYZ(pTarget.xCoord + 1, pTarget.yCoord, pTarget.zCoord, lspeed);
/*     */     case 4: 
/* 237 */       return lpn.tryMoveToXYZ(pTarget.xCoord - i, pTarget.yCoord, pTarget.zCoord, lspeed);
/*     */     }
/* 239 */     return lpn.tryMoveToXYZ(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord, lspeed);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int getModEntityID(String uniqueModeName)
/*     */   {
/* 334 */     int li = 0;
/* 335 */     if (entityIDList.containsKey(uniqueModeName)) {
/* 336 */       li = ((Integer)entityIDList.get(uniqueModeName)).intValue();
/*     */     }
/* 338 */     entityIDList.put(uniqueModeName, Integer.valueOf(li + 1));
/* 339 */     return li;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Entity getEntity(byte[] pData, int pIndex, World pWorld)
/*     */   {
/* 346 */     return pWorld.getEntityByID(getInt(pData, pIndex));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Entity getAvatarEntity(Entity pEntity)
/*     */   {
/* 356 */     if (pEntity == null) return null;
/*     */     try
/*     */     {
/* 359 */       Field field = pEntity.getClass().getField("avatar");
/* 360 */       pEntity = (EntityLivingBase)field.get(pEntity);
/*     */     }
/*     */     catch (NoSuchFieldException e) {}catch (Exception e) {
/* 363 */       e.printStackTrace();
/*     */     } catch (Error e) {
/* 365 */       e.printStackTrace();
/*     */     }
/*     */     
/* 368 */     return pEntity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Entity getAvatarPlayer(Entity entity)
/*     */   {
/*     */     try
/*     */     {
/* 379 */       Field field = entity.getClass().getField("maidAvatar");
/* 380 */       entity = (Entity)field.get(entity);
/*     */     }
/*     */     catch (NoSuchFieldException e) {}catch (Exception e) {}
/*     */     
/*     */ 
/*     */ 
/* 386 */     return entity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static ItemStack decPlayerInventory(EntityPlayer par1EntityPlayer, int par2Index, int par3DecCount)
/*     */   {
/* 393 */     if (par1EntityPlayer == null) {
/* 394 */       return null;
/*     */     }
/*     */     
/* 397 */     if (par2Index == -1) {
/* 398 */       par2Index = par1EntityPlayer.inventory.currentItem;
/*     */     }
/* 400 */     ItemStack itemstack1 = par1EntityPlayer.inventory.getStackInSlot(par2Index);
/* 401 */     if (itemstack1 == null) {
/* 402 */       return null;
/*     */     }
/*     */     
/* 405 */     if (!par1EntityPlayer.capabilities.isCreativeMode)
/*     */     {
/* 407 */       itemstack1.stackSize -= par3DecCount;
/*     */     }
/*     */     
/* 410 */     if ((itemstack1.getItem() instanceof ItemPotion)) {
/* 411 */       if (itemstack1.stackSize <= 0) {
/* 412 */         par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Items.glass_bottle, par3DecCount));
/* 413 */         return null;
/*     */       }
/* 415 */       par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle, par3DecCount));
/*     */ 
/*     */     }
/* 418 */     else if (itemstack1.stackSize <= 0) {
/* 419 */       par1EntityPlayer.inventory.setInventorySlotContents(par2Index, null);
/* 420 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 424 */     return itemstack1;
/*     */   }
/*     */   
/*     */   protected static float convRevision(String pRev) {
/* 428 */     Pattern lp = Pattern.compile("(\\d+)(\\w*)");
/* 429 */     Matcher lm = lp.matcher(pRev);
/* 430 */     float lf = 0.0F;
/* 431 */     if (lm.find()) {
/* 432 */       lf = Integer.valueOf(lm.group(1)).intValue();
/* 433 */       if (!lm.group(2).isEmpty()) {
/* 434 */         lf = (float)(lf + (lm.group(2).charAt(0) - '`') * 0.01D);
/*     */       }
/*     */     }
/* 437 */     return lf;
/*     */   }
/*     */   
/*     */   protected static float convRevision() {
/* 441 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void checkRevision(String pRev)
/*     */   {
/* 448 */     if (convRevision() < convRevision(pRev)) {}
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
/*     */   public static void replaceEntityList(Class pSrcClass, Class pDestClass)
/*     */   {
/*     */     try
/*     */     {
/* 465 */       int lint = 0;
/* 466 */       String ls = "";
/* 467 */       Map lmap = EntityList.stringToClassMapping;
/* 468 */       for (Map.Entry<String, Class> le : lmap.entrySet()) {
/* 469 */         if (le.getValue() == pSrcClass) {
/* 470 */           le.setValue(pDestClass);
/*     */         }
/*     */       }
/*     */       
/* 474 */       lmap = EntityList.classToStringMapping;
/* 475 */       if (lmap.containsKey(pSrcClass)) {
/* 476 */         ls = (String)lmap.get(pSrcClass);
/*     */         
/* 478 */         lmap.put(pDestClass, ls);
/*     */       }
/*     */       
/* 481 */       lmap = EntityList.IDtoClassMapping;
/* 482 */       for (Map.Entry<Integer, Class> le : lmap.entrySet()) {
/* 483 */         if (le.getValue() == pSrcClass) {
/* 484 */           le.setValue(pDestClass);
/*     */         }
/*     */       }
/*     */       
/* 488 */       lmap = (Map)ObfuscationReflectionHelper.getPrivateValue(EntityList.class, null, new String[] { "field_75624_e", "classToIDMapping" });
/* 489 */       if (lmap.containsKey(pSrcClass)) {
/* 490 */         lint = ((Integer)lmap.get(pSrcClass)).intValue();
/*     */         
/* 492 */         lmap.put(pDestClass, Integer.valueOf(lint));
/*     */       }
/* 494 */       replaceEntitys.put(pSrcClass, pDestClass);
/* 495 */       MMMLib.Debug("Replace %s -> %s(EntityListID: %d, EntityListString: %s)", new Object[] { pSrcClass.getSimpleName(), pDestClass.getSimpleName(), Integer.valueOf(lint), ls });
/*     */     } catch (Exception e) {
/* 497 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void replaceCreatureList(List<BiomeGenBase.SpawnListEntry> pMobs) {
/* 502 */     if (pMobs == null) return;
/* 503 */     for (Map.Entry<Class, Class> le : replaceEntitys.entrySet()) {
/* 504 */       for (int j = 0; j < pMobs.size(); j++) {
/* 505 */         if (((BiomeGenBase.SpawnListEntry)pMobs.get(j)).entityClass == le.getKey()) {
/* 506 */           ((BiomeGenBase.SpawnListEntry)pMobs.get(j)).entityClass = ((Class)le.getValue());
/* 507 */           MMMLib.Debug("ReplaceCreatureList: %s -> %s", new Object[] { ((Class)le.getKey()).getSimpleName(), ((Class)le.getValue()).getSimpleName() });
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void replaceBaiomeSpawn()
/*     */   {
/* 519 */     if (replaceEntitys.isEmpty()) return;
/* 520 */     BiomeGenBase[] biomeList = BiomeGenBase.getBiomeGenArray();
/* 521 */     for (int i = 0; i < biomeList.length; i++) {
/* 522 */       if (biomeList[i] != null)
/*     */       {
/* 524 */         MMMLib.Debug("ReplaceBaiomeSpawn:%s", new Object[] { biomeList[i].biomeName });
/* 525 */         MMMLib.Debug("[Creature]", new Object[0]);
/* 526 */         replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.creature));
/* 527 */         MMMLib.Debug("[WaterCreature]", new Object[0]);
/* 528 */         replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.waterCreature));
/* 529 */         MMMLib.Debug("[CaveCreature]", new Object[0]);
/* 530 */         replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.ambient));
/* 531 */         MMMLib.Debug("[Monster]", new Object[0]);
/* 532 */         replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.monster));
/*     */       }
/*     */     }
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
/*     */   public static Entity getRayTraceEntity(EntityLivingBase pEntity, double pRange, float pDelta, float pExpand)
/*     */   {
/* 549 */     Vec3 lvpos = Vec3.createVectorHelper(pEntity.posX, pEntity.posY + pEntity.getEyeHeight(), pEntity.posZ);
/*     */     
/*     */ 
/* 552 */     Vec3 lvlook = pEntity.getLook(pDelta);
/* 553 */     Vec3 lvview = lvpos.addVector(lvlook.xCoord * pRange, lvlook.yCoord * pRange, lvlook.zCoord * pRange);
/* 554 */     Entity ltarget = null;
/* 555 */     List llist = pEntity.worldObj.getEntitiesWithinAABBExcludingEntity(pEntity, pEntity.boundingBox.addCoord(lvlook.xCoord * pRange, lvlook.yCoord * pRange, lvlook.zCoord * pRange).expand(pExpand, pExpand, pExpand));
/* 556 */     double ltdistance = pRange * pRange;
/*     */     
/* 558 */     for (int var13 = 0; var13 < llist.size(); var13++) {
/* 559 */       Entity lentity = (Entity)llist.get(var13);
/*     */       
/* 561 */       if (lentity.canBeCollidedWith()) {
/* 562 */         float lexpand = lentity.getCollisionBorderSize() + 0.3F;
/* 563 */         AxisAlignedBB laabb = lentity.boundingBox.expand(lexpand, lexpand, lexpand);
/* 564 */         MovingObjectPosition lmop = laabb.calculateIntercept(lvpos, lvview);
/*     */         
/* 566 */         if (laabb.isVecInside(lvpos)) {
/* 567 */           if ((0.0D < ltdistance) || (ltdistance == 0.0D)) {
/* 568 */             ltarget = lentity;
/* 569 */             ltdistance = 0.0D;
/*     */           }
/* 571 */         } else if (lmop != null) {
/* 572 */           double ldis = lvpos.squareDistanceTo(lmop.hitVec);
/*     */           
/* 574 */           if ((ldis < ltdistance) || (ltdistance == 0.0D)) {
/* 575 */             ltarget = lentity;
/* 576 */             ltdistance = ldis;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 581 */     return ltarget;
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
/*     */   public static ItemStack getSmeltingResult(ItemStack pItemstack)
/*     */   {
/* 599 */     return FurnaceRecipes.smelting().getSmeltingResult(pItemstack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean hasEffect(ItemStack pItemStack)
/*     */   {
/* 610 */     if (pItemStack != null) {
/* 611 */       Item litem = pItemStack.getItem();
/* 612 */       if ((litem instanceof ItemPotion)) {
/* 613 */         List llist = ((ItemPotion)litem).getEffects(pItemStack);
/* 614 */         return (llist != null) && (!llist.isEmpty());
/*     */       }
/*     */     }
/* 617 */     return false;
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
/*     */   public static int getHexToInt(String pValue)
/*     */   {
/* 661 */     String ls = "00000000".concat(pValue);
/* 662 */     int llen = ls.length();
/* 663 */     int li = Integer.parseInt(ls.substring(llen - 4, llen), 16);
/* 664 */     int lj = Integer.parseInt(ls.substring(llen - 8, llen - 4), 16);
/* 665 */     return lj << 16 | li;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double getAttackVSEntity(ItemStack pItemStack)
/*     */   {
/* 674 */     AttributeModifier lam = (AttributeModifier)pItemStack.getAttributeModifiers().get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
/* 675 */     return lam == null ? 0.0D : lam.getAmount();
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_Helper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
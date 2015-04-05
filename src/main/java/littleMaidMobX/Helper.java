package littleMaidMobX;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class Helper {

	public static final boolean isClient;
	public static final Package fpackage = null;
//	public static final String packegeBase;
	public static final boolean isForge = true;//ModLoader.isModLoaded("Forge");
	public static final Minecraft mc;
//	public static Method methGetSmeltingResultForge = null;
	public static Class entityRegistry = null;
	public static Method registerModEntity = null;
	protected static final Map<Class, Class>replaceEntitys = new HashMap<Class, Class>();
	protected static Map<String, Integer> entityIDList = new HashMap<String, Integer>();
	
	static {
		// TODO å¿…è¦�ã�ªã�„ï¼Ÿ
//		fpackage = ModLoader.class.getPackage();
//		packegeBase = "";//fpackage == null ? "" : fpackage.getName().concat(".");

		Minecraft lm = null;
		try {
			lm =  Minecraft.getMinecraft();// ModLoader.getMinecraftInstance();
		} catch (Exception e) {
//			e.printStackTrace();
		} catch (Error e) {
//			e.printStackTrace();
		}
		mc = lm;
		isClient = mc != null;
		/*
		if (isForge) {
			try {
				methGetSmeltingResultForge = FurnaceRecipes.class.getMethod("getExperience", ItemStack.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				entityRegistry = getNameOfClass("cpw.mods.fml.common.registry.EntityRegistry");
				registerModEntity = entityRegistry.getMethod("registerModEntity",
						Class.class, String.class, int.class, Object.class, int.class, int.class, boolean.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
	}

	/**
	 * ç�¾åœ¨ã�®å®Ÿè¡Œç’°å¢ƒã�Œãƒ­ãƒ¼ã‚«ãƒ«ã�‹ã�©ã�†ã�‹ã‚’åˆ¤å®šã�™ã‚‹ã€‚
	 */
	public static boolean isLocalPlay() {
		return isClient && mc.isIntegratedServerRunning();
	}

	/**
	 * ãƒžãƒ«ãƒ�å¯¾å¿œç”¨ã€‚
	 * ItemStackã�«æƒ…å ±æ›´æ–°ã‚’è¡Œã�†ã�¨ã€�ã‚µãƒ¼ãƒ�ãƒ¼å�´ã�¨ã�®å·®ç•°ã�‹ã‚‰Slotã�®ã‚¢ãƒƒãƒ—ãƒ‡ãƒ¼ãƒˆã�Œè¡Œã‚�ã‚Œã‚‹ã€‚
	 * ã��ã�®éš›ã€�UsingItemã�®æ›´æ–°å‡¦ç�†ã�Œè¡Œã‚�ã‚Œã�ªã�„ã�Ÿã‚�é�•ã�†ã‚¢ã‚¤ãƒ†ãƒ ã�«æŒ�æ›¿ã�ˆã‚‰ã‚Œã�Ÿã�¨åˆ¤å®šã�•ã‚Œã‚‹ã€‚
	 * ã�“ã�“ã�§ã�¯æ¯”è¼ƒç”¨ã�«ä½¿ã‚�ã‚Œã‚‹ã‚¹ã‚¿ãƒƒã‚¯ãƒªã‚¹ãƒˆã‚’å¼·åˆ¶çš„ã�«æ›¸æ�›ã�ˆã‚‹äº‹ã�«ã‚ˆã‚Šå¯¾å¿œã�—ã�Ÿã€‚
	 */
	public static void updateCheckinghSlot(Entity pEntity, ItemStack pItemstack) {
		if (pEntity instanceof EntityPlayerMP) {
			// ã‚µãƒ¼ãƒ�ãƒ¼å�´ã�§ã�®ã�¿å‡¦ç�†
			EntityPlayerMP lep = (EntityPlayerMP)pEntity;
			Container lctr = lep.openContainer;
			for (int li = 0; li < lctr.inventorySlots.size(); li++) {
				ItemStack lis = ((Slot)lctr.getSlot(li)).getStack(); 
				if (lis == pItemstack) {
					lctr.inventoryItemStacks.set(li, pItemstack.copy());
					break;
				}
			}
		}
	}
	
	public static String getPlayerName(EntityPlayer player)
	{
		return player.getGameProfile().getName();
	}
	
	/**
	 * Forgeç”¨ã‚¯ãƒ©ã‚¹ç�²å¾—ã€‚
	 */
	/*
	ä½¿ç”¨ç®‡æ‰€ç„¡ã�— å‰Šé™¤
	public static Class getForgeClass(BaseMod pMod, String pName) {
		if (isForge) {
			pName = pName.concat("_Forge");
		}
		return getNameOfClass(pName);
	}
	*/

	/**
	 * å��å‰�ã�‹ã‚‰ã‚¯ãƒ©ã‚¹ã‚’ç�²å¾—ã�™ã‚‹
	 */
	public static Class getNameOfClass(String pName) {
		if (fpackage != null) {
			pName = fpackage.getName() + "." + pName;
		}
		Class lclass = null;
		try {
			lclass = Class.forName(pName);
		} catch (Exception e) {
			LittleMaidMobX.Debug("Class:%s is not found.", pName);
		}
		
		return lclass;
	}

	/**
	 * é€�ä¿¡ç”¨ãƒ‡ãƒ¼ã‚¿ã�®ã‚»ãƒƒãƒˆ
	 */
	public static void setValue(byte[] pData, int pIndex, int pVal, int pSize) {
		for (int li = 0; li < pSize; li++) {
			pData[pIndex++] = (byte)(pVal & 0xff);
			pVal = pVal >>> 8;
		}
	}
	
	public static void setInt(byte[] pData, int pIndex, int pVal) {
		pData[pIndex + 3]	= (byte)(pVal & 0xff);
		pData[pIndex + 2]	= (byte)((pVal >>> 8) & 0xff);
		pData[pIndex + 1]	= (byte)((pVal >>> 16) & 0xff);
		pData[pIndex + 0]	= (byte)((pVal >>> 24) & 0xff);
	}
	
	public static int getInt(byte[] pData, int pIndex) {
		return (pData[pIndex + 3] & 0xff) | ((pData[pIndex + 2] & 0xff) << 8) | ((pData[pIndex + 1] & 0xff) << 16) | ((pData[pIndex + 0] & 0xff) << 24);
	}

	public static void setFloat(byte[] pData, int pIndex, float pVal) {
		setInt(pData, pIndex, Float.floatToIntBits(pVal));
	}

	public static float getFloat(byte[] pData, int pIndex) {
		return Float.intBitsToFloat(getInt(pData, pIndex));
	}

	public static void setShort(byte[] pData, int pIndex, int pVal) {
		pData[pIndex++]	= (byte)(pVal & 0xff);
		pData[pIndex]	= (byte)((pVal >>> 8) & 0xff);
	}

	public static short getShort(byte[] pData, int pIndex) {
		return (short)((pData[pIndex] & 0xff) | ((pData[pIndex + 1] & 0xff) << 8));
	}

	public static String getStr(byte[] pData, int pIndex, int pLen) {
		String ls = new String(pData, pIndex, pLen);
		return ls;
	}
	public static String getStr(byte[] pData, int pIndex) {
		return getStr(pData, pIndex, pData.length - pIndex);
	}

	public static void setStr(byte[] pData, int pIndex, String pVal) {
		byte[] lb = pVal.getBytes();
		for (int li = pIndex; li < pData.length; li++) {
			pData[li] = lb[li - pIndex];
		}
	}

	// çŠ¶æ³�åˆ¤æ–­è¦�é–¢æ•°ç¾¤
	public static boolean canBlockBeSeen(Entity pEntity, int x, int y, int z, boolean toTop, boolean do1, boolean do2) {
		// ãƒ–ãƒ­ãƒƒã‚¯ã�®å�¯è¦–åˆ¤å®š
		Vec3 vec3d = Vec3.createVectorHelper(pEntity.posX, pEntity.posY + pEntity.getEyeHeight(), pEntity.posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper((double)x + 0.5D, (double)y + (toTop ? 0.9D : 0.5D), (double)z + 0.5D);
		
		MovingObjectPosition movingobjectposition = pEntity.worldObj.func_147447_a(vec3d, vec3d1, do1, do2, false);
		if (movingobjectposition == null) {
			return false;
		}
		if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
			if (movingobjectposition.blockX == MathHelper.floor_double(vec3d1.xCoord) && 
				movingobjectposition.blockY == MathHelper.floor_double(vec3d1.yCoord) &&
				movingobjectposition.blockZ == MathHelper.floor_double(vec3d1.zCoord)) {
				return true;
			}
		}
		return false;
	}

	public static boolean setPathToTile(EntityLiving pEntity, TileEntity pTarget, boolean flag) {
		// Tileã�¾ã�§ã�®ãƒ‘ã‚¹ã‚’ä½œã‚‹
		PathNavigate lpn = pEntity.getNavigator();
		float lspeed = 1.0F;
		// å�‘ã��ã�«å�ˆã‚�ã�›ã�¦è·�é›¢ã‚’èª¿æ•´
		int i = (pTarget.yCoord == MathHelper.floor_double(pEntity.posY) && flag) ? 2 : 1;
		switch (pEntity.worldObj.getBlockMetadata(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord)) {
		case 3:
			return lpn.tryMoveToXYZ(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord + i, lspeed);
		case 2:
			return lpn.tryMoveToXYZ(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord - i, lspeed);
		case 5:
			return lpn.tryMoveToXYZ(pTarget.xCoord + 1, pTarget.yCoord, pTarget.zCoord, lspeed);
		case 4:
			return lpn.tryMoveToXYZ(pTarget.xCoord - i, pTarget.yCoord, pTarget.zCoord, lspeed);
		default:
			return lpn.tryMoveToXYZ(pTarget.xCoord, pTarget.yCoord, pTarget.zCoord, lspeed);
		}
	}

	/**
	 * Modloaderç’°å¢ƒä¸‹ã�§ç©ºã�„ã�¦ã�„ã‚‹EntityIDã‚’è¿”ã�™ã€‚
	 * æœ‰åŠ¹ã�ªå€¤ã‚’ç�²å¾—ã�§ã��ã�ªã�‘ã‚Œã�°-1ã‚’è¿”ã�™ã€‚
	 */
	/*
	private static int getNextEntityID(boolean isLiving) {
		if (isLiving) {
			// ç”Ÿç‰©ç”¨
			for (int li = 1; li < 256; li++) {
				if (EntityList.getClassFromID(li) == null) {
					return li;
				}
			}
		} else {
			// ç‰©ç”¨
			for (int li = MMMLib.cfg_startVehicleEntityID; li < MMMLib.cfg_startVehicleEntityID + 2048; li++) {
				if (EntityList.getClassFromID(li) == null) {
					return li;
				}
			}
		}
		return -1;
	}
	*/

	/**
	 * Entityã‚’ç™»éŒ²ã�™ã‚‹ã€‚
	 * RMLã€�Forgeä¸¡å¯¾å¿œã€‚
	 * @param entityclass
	 * @param entityName
	 * @param defaultId
	 * 0 : ã‚ªãƒ¼ãƒˆã‚¢ã‚µã‚¤ãƒ³
	 * @param mod
	 * @param uniqueModeName
	 * @param trackingRange
	 * @param updateFrequency
	 * @param sendVelocityUpdate
	 */
	/*
	public static int registerEntity(
			Class<? extends Entity> entityclass, String entityName, int defaultId,
			BaseMod mod, int trackingRange, int updateFrequency, boolean sendVelocityUpdate,
			int pEggColor1, int pEggColor2) {
		int lid = 0;
		lid = getModEntityID(mod.getName());
		if (isForge) {
			try {
				Method lmethod;
				// EntityIDã�®ç�²å¾—
				lmethod = entityRegistry.getMethod("findGlobalUniqueEntityId");
				defaultId = (Integer)lmethod.invoke(null);
				
				if (pEggColor1 == 0 && pEggColor2 == 0) {
					lmethod = entityRegistry.getMethod("registerGlobalEntityID",
							Class.class, String.class, int.class);
					lmethod.invoke(null, entityclass, entityName, defaultId);
				} else {
					lmethod = entityRegistry.getMethod("registerGlobalEntityID",
							Class.class, String.class, int.class, int.class, int.class);
					lmethod.invoke(null, entityclass, entityName, defaultId, pEggColor1, pEggColor2);
				}
				// EntityListã�¸ã�®ç™»éŒ²ã�¯é�©å½“ã�ªæ•°å­—ã�§ã‚ˆã�„ã€‚
				registerModEntity.invoke(
						null, entityclass, entityName, lid,
						mod, trackingRange, updateFrequency, sendVelocityUpdate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// EntityListã�¸ã�®ç™»éŒ²ã�¯
			if (defaultId == 0) {
				defaultId = getNextEntityID(entityclass.isAssignableFrom(EntityLivingBase.class));
			}
			if (pEggColor1 == 0 && pEggColor2 == 0) {
				ModLoader.registerEntityID(entityclass, entityName, defaultId);
			} else {
				ModLoader.registerEntityID(entityclass, entityName, defaultId, pEggColor1, pEggColor2);
			}
			ModLoader.addEntityTracker(mod, entityclass, defaultId, trackingRange, updateFrequency, sendVelocityUpdate);
		}
		LMM_LittleMaidMobX.Debug("RegisterEntity ID:%d / %s-%d : %s", defaultId, mod.getName(), lid, entityName);
		return defaultId;
	}
	public static int registerEntity(
			Class<? extends Entity> entityclass, String entityName, int defaultId,
			BaseMod mod, int trackingRange, int updateFrequency, boolean sendVelocityUpdate) {
		return registerEntity(entityclass, entityName, defaultId, mod, trackingRange, updateFrequency, sendVelocityUpdate, 0, 0);
	}
	*/

	private static int getModEntityID(String uniqueModeName) {
		int li = 0;
		if (entityIDList.containsKey(uniqueModeName)) {
			li = entityIDList.get(uniqueModeName);
		}
		entityIDList.put(uniqueModeName, li + 1);
		return li;
	}

	/**
	 * Entityã‚’è¿”ã�™ã€‚
	 */
	public static Entity getEntity(byte[] pData, int pIndex, World pWorld) {
		return pWorld.getEntityByID(Helper.getInt(pData, pIndex));
	}

	/**
	 * å¤‰æ•°ã€Œavatarã€�ã�‹ã‚‰å€¤ã‚’å�–ã‚Šå‡ºã�—æˆ»ã‚Šå€¤ã�¨ã�—ã�¦è¿”ã�™ã€‚
	 * avatarã�Œå­˜åœ¨ã�—ã�ªã�„å ´å�ˆã�¯å…ƒã�®å€¤ã‚’è¿”ã�™ã€‚
	 * avatarã�¯EntityLivingäº’æ�›ã€‚
	 */
	public static Entity getAvatarEntity(Entity pEntity){
		// littleMaidç”¨ã‚³ãƒ¼ãƒ‰ã�“ã�“ã�‹ã‚‰
		if (pEntity == null) return null;
		try {
			// å°„æ‰‹ã�®æƒ…å ±ã‚’EntityLittleMaidAvatarã�‹ã‚‰EntityLittleMaidã�¸ç½®ã��æ�›ã�ˆã‚‹
			Field field = pEntity.getClass().getField("avatar");
			pEntity = (EntityLivingBase)field.get(pEntity);
		} catch (NoSuchFieldException e) {
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
		// ã�“ã�“ã�¾ã�§
		return pEntity;
	}

	/**
	 * å¤‰æ•°ã€ŒmaidAvatarã€�ã�‹ã‚‰å€¤ã‚’å�–ã‚Šå‡ºã�—æˆ»ã‚Šå€¤ã�¨ã�—ã�¦è¿”ã�™ã€‚
	 * maidAvatarã�Œå­˜åœ¨ã�—ã�ªã�„å ´å�ˆã�¯å…ƒã�®å€¤ã‚’è¿”ã�™ã€‚
	 * maidAvatarã�¯EntityPlayeräº’æ�›ã€‚
	 */
	public static Entity getAvatarPlayer(Entity entity) {
		// ãƒ¡ã‚¤ãƒ‰ã�•ã‚“ãƒ�ã‚§ãƒƒã‚¯
		try {
			Field field = entity.getClass().getField("maidAvatar");
			entity = (Entity)field.get(entity);
		}
		catch (NoSuchFieldException e) {
		}
		catch (Exception e) {
		}
		return entity;
	}

	/**
	 * ãƒ—ãƒ¬ãƒ¼ãƒ¤ã�®ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã�‹ã‚‰ã‚¢ã‚¤ãƒ†ãƒ ã‚’æ¸›ã‚‰ã�™
	 */
	public static ItemStack decPlayerInventory(EntityPlayer par1EntityPlayer, int par2Index, int par3DecCount) {
		if (par1EntityPlayer == null) {
			return null;
		}
		
		if (par2Index == -1) {
			par2Index = par1EntityPlayer.inventory.currentItem;
		}
		ItemStack itemstack1 = par1EntityPlayer.inventory.getStackInSlot(par2Index);
		if (itemstack1 == null) {
			return null;
		}
		
		if (!par1EntityPlayer.capabilities.isCreativeMode) {
			// ã‚¯ãƒªã‚¨ã‚¤ãƒ†ã‚£ãƒ–ã� ã�¨æ¸›ã‚‰ã�ªã�„
			itemstack1.stackSize -= par3DecCount;
		}
		
		if (itemstack1.getItem() instanceof ItemPotion) {
			if(itemstack1.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Items.glass_bottle, par3DecCount));
				return null;
			} else {
				par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle, par3DecCount));
			}
		} else {
			if (itemstack1.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par2Index, null);
				return null;
			}
		}
		
		return itemstack1;
	}

	protected static float convRevision(String pRev) {
		Pattern lp = Pattern.compile("(\\d+)(\\w*)");
		Matcher lm = lp.matcher(pRev);
		float lf = 0;
		if (lm.find()) {
			lf = Integer.valueOf(lm.group(1));
			if (!lm.group(2).isEmpty()) {
				lf += (float)(lm.group(2).charAt(0) - 96) * 0.01;
			}
		}
		return lf;
	}
	protected static float convRevision() {
		// TODO â˜…å¾Œå›žã�—
		return 0;// convRevision(MMMLib.Revision);
	}

	/**
	 * æŒ‡å®šã�•ã‚Œã�Ÿãƒªãƒ“ã‚¸ãƒ§ãƒ³ã‚ˆã‚Šã‚‚å�¤ã�‘ã‚Œã�°ä¾‹å¤–ã‚’æŠ•ã�’ã�¦ã‚¹ãƒˆãƒƒãƒ—
	 */
	public static void checkRevision(String pRev) {
		if (convRevision() < convRevision(pRev)) {
			// é�©å�ˆãƒ�ãƒ¼ã‚¸ãƒ§ãƒ³ã�§ã�¯ã�ªã�„ã�®ã�§ã‚¹ãƒˆãƒƒãƒ—
		// TODO â˜…å¾Œå›žã�—
		//	ModLoader.getLogger().warning("you must check MMMLib revision.");
		//	throw new RuntimeException("The revision of MMMLib is old.");
		}
	}

	/**
	 * EntityListã�«ç™»éŒ²ã�•ã‚Œã�¦ã�„ã�„ã‚‹Entityã‚’ç½®ã��æ�›ã�ˆã‚‹ã€‚
	 */
	public static void replaceEntityList(Class pSrcClass, Class pDestClass) {
		// EntityListç™»éŒ²æƒ…å ±ã‚’ç½®ã��æ�›ã�ˆ
		// å�¤ã�„Entityã�§ã‚‚ã‚¹ãƒ�ãƒ¼ãƒ³ã�§ã��ã‚‹ã‚ˆã�†ã�«ä¸€éƒ¨ã�®ç‰©ã�¯äºŒé‡�ç™»éŒ²
		try {
			// stringToClassMapping
			Map lmap;
			int lint = 0;
			String ls = "";
			lmap = EntityList.stringToClassMapping;
			for (Entry<String, Class> le : ((Map<String, Class>)lmap).entrySet()) {
				if (le.getValue() == pSrcClass) {
					le.setValue(pDestClass);
				}
			}
			// classToStringMapping
			lmap = EntityList.classToStringMapping;
			if (lmap.containsKey(pSrcClass)) {
				ls = (String)lmap.get(pSrcClass);
//				lmap.remove(pSrcClass);
				lmap.put(pDestClass, ls);
			}
			// IDtoClassMapping
			lmap = EntityList.IDtoClassMapping;
			for (Entry<Integer, Class> le : ((Map<Integer, Class>)lmap).entrySet()) {
				if (le.getValue() == pSrcClass) {
					le.setValue(pDestClass);
				}
			}
			// classToIDMapping ã�ªã‚“ã�œã‚³ã‚¤ãƒ„ã� ã�‘privateã�®ã�¾ã�¾ï¼Ÿ
			lmap = (Map)ObfuscationReflectionHelper.getPrivateValue(EntityList.class, null, "field_75624_e", "classToIDMapping");
			if (lmap.containsKey(pSrcClass)) {
				lint = (Integer)lmap.get(pSrcClass);
//				lmap.remove(pSrcClass);
				lmap.put(pDestClass, lint);
			}
			replaceEntitys.put(pSrcClass, pDestClass);
			LittleMaidMobX.Debug("Replace %s -> %s(EntityListID: %d, EntityListString: %s)", pSrcClass.getSimpleName(), pDestClass.getSimpleName(), lint, ls);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void replaceCreatureList(List<SpawnListEntry> pMobs) {
		if (pMobs == null) return;
		for (Entry<Class, Class> le : replaceEntitys.entrySet()) {
			for (int j = 0; j < pMobs.size(); j++) {
				if (pMobs.get(j).entityClass == le.getKey()) {
					pMobs.get(j).entityClass = le.getValue();
					LittleMaidMobX.Debug("ReplaceCreatureList: %s -> %s", le.getKey().getSimpleName(), le.getValue().getSimpleName());
				}
			}
		}
	}

	/**
	 * ãƒ�ã‚¤ã‚ªãƒ¼ãƒ ã�®è¨­å®šEntityã‚’ç½®ã��æ�›ã�ˆã‚‰ã‚Œã�ŸEntityã�¸ç½®ã��æ�›ã�ˆã‚‹ã€‚
	 * åŸºæœ¬çš„ã�«MMMLibä»¥å¤–ã�‹ã‚‰ã�¯å‘¼ã�°ã‚Œã�ªã�„ã€‚
	 */
	protected static void replaceBaiomeSpawn() {
		// ãƒ�ã‚¤ã‚ªãƒ¼ãƒ ã�®ç™ºç”Ÿå‡¦ç�†ã‚’ã�®ã�£ã�¨ã‚‹
		if (replaceEntitys.isEmpty()) return;
		BiomeGenBase[] biomeList = BiomeGenBase.getBiomeGenArray();
		for (int i = 0; i < biomeList.length; i++) {
			if (biomeList[i] == null) continue;
			List<SpawnListEntry> mobs;
			LittleMaidMobX.Debug("ReplaceBaiomeSpawn:%s", biomeList[i].biomeName);
			LittleMaidMobX.Debug("[Creature]");
			replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.creature));//.spawnableCreatureList);
			LittleMaidMobX.Debug("[WaterCreature]");
			replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.waterCreature));//.spawnableWaterCreatureList);
			LittleMaidMobX.Debug("[CaveCreature]");
			replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.ambient));//.spawnableCaveCreatureList);
			LittleMaidMobX.Debug("[Monster]");
			replaceCreatureList(biomeList[i].getSpawnableList(EnumCreatureType.monster));//.spawnableMonsterList);
		}
	}

	/**
	 * è¦–ç·šã�®å…ˆã�«ã�„ã‚‹æœ€åˆ�ã�®Entityã‚’è¿”ã�™
	 * @param pEntity
	 * è¦–ç‚¹
	 * @param pRange
	 * è¦–ç·šã�®æœ‰åŠ¹è·�é›¢
	 * @param pDelta
	 * æ™‚åˆ»è£œæ­£
	 * @param pExpand
	 * æ¤œçŸ¥é ˜åŸŸã�®æ‹¡å¤§ç¯„å›²
	 * @return
	 */
	public static Entity getRayTraceEntity(EntityLivingBase pEntity, double pRange, float pDelta, float pExpand) {
		Vec3 lvpos = Vec3.createVectorHelper(
				pEntity.posX, pEntity.posY + pEntity.getEyeHeight(), pEntity.posZ);
//		Vec3 lvpos = pEntity.getPosition(pDelta).addVector(0D, pEntity.getEyeHeight(), 0D);
		Vec3 lvlook = pEntity.getLook(pDelta);
		Vec3 lvview = lvpos.addVector(lvlook.xCoord * pRange, lvlook.yCoord * pRange, lvlook.zCoord * pRange);
		Entity ltarget = null;
		List llist = pEntity.worldObj.getEntitiesWithinAABBExcludingEntity(pEntity, pEntity.boundingBox.addCoord(lvlook.xCoord * pRange, lvlook.yCoord * pRange, lvlook.zCoord * pRange).expand((double)pExpand, (double)pExpand, (double)pExpand));
		double ltdistance = pRange * pRange;
		
		for (int var13 = 0; var13 < llist.size(); ++var13) {
			Entity lentity = (Entity)llist.get(var13);
			
			if (lentity.canBeCollidedWith()) {
				float lexpand = lentity.getCollisionBorderSize() + 0.3F;
				AxisAlignedBB laabb = lentity.boundingBox.expand((double)lexpand, (double)lexpand, (double)lexpand);
				MovingObjectPosition lmop = laabb.calculateIntercept(lvpos, lvview);
				
				if (laabb.isVecInside(lvpos)) {
					if (0.0D < ltdistance || ltdistance == 0.0D) {
						ltarget = lentity;
						ltdistance = 0.0D;
					}
				} else if (lmop != null) {
					double ldis = lvpos.squareDistanceTo(lmop.hitVec);
					
					if (ldis < ltdistance || ltdistance == 0.0D) {
						ltarget = lentity;
						ltdistance = ldis;
					}
				}
			}
		}
		return ltarget;
	}


	// Forgeå¯¾ç­–

	/**
	 * Forgeå¯¾ç­–ç”¨ã�®ãƒ¡ã‚½ãƒƒãƒ‰
	 */
	public static ItemStack getSmeltingResult(ItemStack pItemstack) {
/*
		if (methGetSmeltingResultForge != null) {
			try {
				return (ItemStack)methGetSmeltingResultForge.invoke(FurnaceRecipes.smelting(), pItemstack);
			}catch (Exception e) {
			}
		}
*/
		return FurnaceRecipes.smelting().getSmeltingResult(pItemstack);
	}

	/**
	 * ã‚¢ã‚¤ãƒ†ãƒ ã�«è¿½åŠ åŠ¹æžœã�Œåœ¨ã‚‹ã�‹ã‚’åˆ¤å®šã�™ã‚‹ã€‚
	 * Forgeå¯¾ç­–ã€‚
	 * @param pItemStack
	 * @return
	 */
	public static boolean hasEffect(ItemStack pItemStack) {
		// ãƒžã‚¸ClientSIDEã�¨ã�‹è¾žã‚�ã�¦ã�»ã�—ã�„ã€‚
		if (pItemStack != null) {
			Item litem = pItemStack.getItem();
			if (litem instanceof ItemPotion) {
				List llist = ((ItemPotion)litem).getEffects(pItemStack);
				return llist != null && !llist.isEmpty();
			}
		}
		return false;
	}

	/**
	 * Blockã�®ã‚¤ãƒ³ã‚¹ã‚¿ãƒ³ã‚¹ã‚’ç½®ã��æ�›ã�ˆã‚‹ã€‚
	 * static finalã�®å¤‰æ•°ã�«å¯¾ã�—ã�¦è¡Œã�†ã�®ã�§Forgeã�§ã�¯ç„¡åŠ¹ã€‚
	 * @param pOriginal
	 * @param pReplace
	 * @return
	 */
	/*
	public static boolean replaceBlock(Block pOriginal, Block pReplace) {
		if (isForge) {
			return false;
		}
		try {
			// Blockã�®static finalåˆ†ã�®ç½®æ�›ã�ˆ
			Field[] lfield = Block.class.getDeclaredFields();
			for (int li = 0; li < lfield.length; li++) {
				if (!Modifier.isStatic(lfield[li].getModifiers())) {
					// staticä»¥å¤–ã�¯å¯¾è±¡å¤–
					continue;
				}
				
				Object lobject = lfield[li].get(null);
				if (lobject == pOriginal) {
					ModLoader.setPrivateValue(Block.class, null, li, pReplace);
					return true;
				}
			}
		}
		catch(Exception exception) {
		}
		return false;
	}
	*/

	/**
	 * 16é€²æ•°ã�®æ–‡å­—åˆ—ã‚’Intã�¸å¤‰æ�›ã�™ã‚‹ã€‚
	 * 0xffffffffå¯¾ç­–ã€‚
	 * @param pValue
	 * @return
	 */
	public static int getHexToInt(String pValue) {
		String ls = "00000000".concat(pValue);
		int llen = ls.length();
		int li = Integer.parseInt(ls.substring(llen - 4, llen), 16);
		int lj = Integer.parseInt(ls.substring(llen - 8, llen - 4), 16);
		return (lj << 16) | li;
	}

	/**
	 *  ã‚¢ã‚¤ãƒ†ãƒ ã�«è¨­å®šã�•ã‚Œã�Ÿæ”»æ’ƒåŠ›ã‚’è¦‹ã‚‹
	 * @param pItemStack
	 * @return
	 */
	public static double getAttackVSEntity(ItemStack pItemStack) {
		AttributeModifier lam = (AttributeModifier)pItemStack.getAttributeModifiers().get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
		return lam == null ? 0 : lam.getAmount();
	}


	public static float sin(float f) {
		return MathHelper.sin(f);
	}


	public static float cos(float f) {
		return MathHelper.cos(f);
	}


	public static float sqrt(float f) {
		return MathHelper.sqrt_float(f);
	}


	public static int getEntityTicksExisted(Object entity) {
		if (entity instanceof Entity) {
			return ((Entity)entity).ticksExisted;
		}
		return 0;
	}


	public static Object getRidingEntity(Object entity) {
		if (entity instanceof Entity) {
			return ((Entity)entity).ridingEntity;
		}
		return null;
	}


	public static boolean isRidingMaster(Object entityliving) {
		// TODO Auto-generated method stub
//		Modchu_Reflect.loadClass("EntityPlayer").isInstance(Modchu_Reflect.getFieldObject("Entity", "field_70154_o", "ridingEntity", entityliving));
		return false;
	}


	public static int normalize(int i, int min, int max, int minOver, int maxOver) {
		return i < min ? minOver : i > max ? maxOver : i;
	}

	public static long normalize(long l, long min, long max, long minOver, long maxOver) {
		return l < min ? minOver : l > max ? maxOver : l;
	}

	public static float normalize(float f, float min, float max, float minOver, float maxOver) {
		return f < min ? minOver : f > max ? maxOver : f;
	}

	public static double normalize(double d, double min, double max, double minOver, double maxOver) {
		return d < min ? minOver : d > max ? maxOver : d;
	}
	public static Block getBlock(Object itemstack) {
		if (itemstack instanceof ItemStack) {
			Item item = ((ItemStack)itemstack).getItem();
			return item != null ? Block.getBlockFromItem(item) : null;
		}
		return null;
	}

	public static boolean isCamouflage(Object itemstack) {
		Block block = getBlock(itemstack);
		if (block instanceof BlockLeavesBase || block instanceof BlockPumpkin) {
			return true;
		}
		return false;
	}

	public static boolean isPlanter(Object stack) {
		Block itemStackOrBlockOrItem = getBlock(stack);
		return itemStackOrBlockOrItem instanceof BlockFlower
				|| itemStackOrBlockOrItem instanceof BlockDoublePlant
				|| itemStackOrBlockOrItem instanceof BlockMushroom
				|| itemStackOrBlockOrItem instanceof BlockSapling
				|| itemStackOrBlockOrItem instanceof BlockTallGrass;
	}
}

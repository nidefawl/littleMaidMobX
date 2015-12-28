package littleMaidMobX;

import java.io.File;

import littleMaidMobX.aimodes.IFF;
import littleMaidMobX.entity.EntityLittleMaid;
import littleMaidMobX.gui.GuiCommonHandler;
import littleMaidMobX.io.Config;
import littleMaidMobX.item.ItemSpawnEgg;
import littleMaidMobX.network.Message;
import littleMaidMobX.network.NetConstants;
import littleMaidMobX.network.Network;
import littleMaidMobX.registry.ModelManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.BiomeDictionary;
import static net.minecraftforge.common.BiomeDictionary.Type;

@Mod(	modid = LittleMaidMobX.DOMAIN,
		name  = LittleMaidMobX.DOMAIN, version = "2")
		
public class LittleMaidMobX {
	
	public static final String DOMAIN = "lmmx";

	public static class Categories
	{
        public static final String CATEGORY_MAIDS = "Maids";
		public static final String CATEGORY_MMMLIB = "MMMLib";
		public static final String CATEGORY_ITEMS = "Items";
	}
	
	public static int cfg_spawnWeight;
	public static int cfg_spawnLimit;
	public static int cfg_minGroupSize;
	public static int cfg_maxGroupSize;
	public static boolean cfg_canDespawn;
	public static boolean cfg_checkOwnerName;
	public static boolean cfg_antiDoppelganger;
	public static boolean cfg_enableSpawnEgg;
	public static boolean cfg_VoiceDistortion;
	public static String cfg_defaultTexture;
	public static boolean cfg_DeathMessage;
	public static boolean cfg_Dominant;
	public static boolean cfg_Aggressive;
	public static String cfg_IgnoreItemList;
	public static boolean cfg_makeNoise;
	
	public static boolean cfg_isModelAlphaBlend = true;

	public static Achievement ac_Contract;

	public static boolean isDebugMessage = true;
	public static boolean isDebugModels = false;
	public static boolean isModelAlphaBlend = true;
	
	public static void Debug(String pText, Object... pData) {
		if (isDebugMessage) {
			// TODO: use Logger class instead with proper names and a way to enable/disable (one logger for sound, one for AI, one for models, etc)
//			if (pText.contains("Sound")) { 
//				return;
//			}			
//			if (pText.contains("daytime")) { 
//				return;
//			}
//			System.out.println(String.format("MMMLib-" + pText, pData));
		}
	}
	public static void DebugModel(String string) {
		System.out.println("LMM Models: " + string);
	}
	public static void Debug(boolean isRemote, String pText, Object... pData) {
		if (isDebugMessage) {
//			System.out.println(String.format("["+(isRemote? "Client":"Server")+"]MMMLib-" + pText, pData));
		}
	}
	
	@SidedProxy(
			clientSide = "littleMaidMobX.ProxyClient",
			serverSide = "littleMaidMobX.ProxyServer")
	public static ProxyCommon proxy;

	@Instance(DOMAIN)
	public static LittleMaidMobX instance;
	
	public static ItemSpawnEgg spawnEgg;

	public static boolean isServer = false;

	public static boolean isForge = true;

	public String getName() {
		return "littleMaidMobX";
	}

	public String getPriorities() {
		
		return "required-after:mod_MMM_MMMLib";
	}

	public String getVersion() {
		return "1.7.2-x";
	}

	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());
			config.load();
			//MMMLib
			isDebugMessage		= true;//config.get("MMMLib", "isDebugMessage", false).getBoolean(false);
			isModelAlphaBlend	= config.getBoolean("Is Model Alpha Blend", Categories.CATEGORY_MMMLIB, true, "true: AlphaBlend(requires more power), false: AlphaTest(faster)");
			cfg_isModelAlphaBlend = isModelAlphaBlend;
			//public static boolean AlphaBlend = true;
			
			//Maids
			cfg_spawnWeight = config.getInt("Spawn Weight", Categories.CATEGORY_MAIDS, 3, 0, 10, "Relative spawn weight. The lower the less common. 10=pigs. 0=off");
			cfg_spawnLimit = config.getInt("Spawn Limit", Categories.CATEGORY_MAIDS, 30, 10, 100, "Maximum spawn count in the World.");
			cfg_minGroupSize = config.getInt("Minimum Group Size", Categories.CATEGORY_MAIDS, 1, 1, 10, "Minimum spawn group count.");
			cfg_maxGroupSize = config.getInt("Maximum Group Size", Categories.CATEGORY_MAIDS, 3, 1, 10, "Maximum spawn group count.");
			cfg_canDespawn = config.getBoolean("Can Despawn", Categories.CATEGORY_MAIDS, false, "It will despawn, if it lets things go.");
			cfg_checkOwnerName = config.getBoolean("Check Owner Name", Categories.CATEGORY_MAIDS, false, "At local, make sure the name of the owner.");
			cfg_antiDoppelganger = config.getBoolean("Anti Doppelganger", Categories.CATEGORY_MAIDS, true, "Not to survive the doppelganger.");
			cfg_VoiceDistortion = config.getBoolean("Voice Distortionion", Categories.CATEGORY_MAIDS, true, "Enables Maid voices to distort based of hair color");
			cfg_defaultTexture = config.getString("Default Texture", Categories.CATEGORY_MAIDS, "", "Default selected Texture Packege. Null is Random");
			cfg_DeathMessage = config.getBoolean("Print Death Message", Categories.CATEGORY_MAIDS, true, "Prints message on the death of your maid.");
			cfg_Aggressive = config.getBoolean("Agressive", Categories.CATEGORY_MAIDS, true, "true: Will be hostile, false: Is a pacifist");
			cfg_IgnoreItemList = config.getString("Ignore Item List", Categories.CATEGORY_MAIDS, "arsmagica2", "?");
			cfg_makeNoise = config.getBoolean("Maids Make Sounds", Categories.CATEGORY_MAIDS, true, "Determines whether or not maids will make noises");
			//Used to be Spawn Anywhere but I don't know what it does now.
			cfg_Dominant = false;
			
			//Items
			cfg_enableSpawnEgg = config.getBoolean("Enable Spawn Egg", Categories.CATEGORY_ITEMS, true, "Enables Little Maid SpawnEgg Recipe.");

			config.save();
			
			proxy.loadTextures();
//			ModelManager.instance.loadTextures();
			if (Helper.isClient)
			{
//				MMM_TextureManager.loadTextures();
				Debug("Localmode: InitTextureList.");
				ModelManager.instance.initTextureList(true);
			}
			else
			{
				ModelManager.instance.loadTextureServer();
			}
		//Config.init();
		
		
//		MMM_Helper.checkRevision("6");
		//Config.checkConfig(this.getClass());
		
		cfg_defaultTexture = cfg_defaultTexture.trim();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiCommonHandler());
		

		EntityRegistry.registerModEntity(EntityLittleMaid.class, "LittleMaidX", 0, instance, 80, 3, true);

		
		
		spawnEgg = new ItemSpawnEgg();
		spawnEgg.setUnlocalizedName(DOMAIN + ":spawn_lmmx_egg");
		spawnEgg.setTextureName(DOMAIN + ":spawn_lmmx_egg");
		GameRegistry.registerItem(spawnEgg, "spawn_lmmx_egg");
		if (cfg_enableSpawnEgg) {
			
			GameRegistry.addRecipe(new ItemStack(spawnEgg, 1), new Object[] {
				"scs",
				"sbs",
				" e ",
				Character.valueOf('s'), Items.sugar,
				Character.valueOf('c'), new ItemStack(Items.dye, 1, 3),
				Character.valueOf('b'), Items.slime_ball,
				Character.valueOf('e'), Items.egg,
			});
		}
		
		//Achievment Stuff
		ac_Contract = new Achievement("achievement.contract", "contract", 0, 0, Items.cake, null).initIndependentStat().registerStat();
		Achievement[] achievements = new Achievement[] { ac_Contract };
		AchievementPage.registerAchievementPage(new AchievementPage("LittleMaidX", achievements));

		if (Helper.isClient)
		{
			proxy.init();
		}
		
		Network.init(DOMAIN);

		proxy.loadSounds();
		
//		Debug("GUID-sneak: %s", LMM_EntityLittleMaid.maidUUIDSneak.toString());

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
		
		// "aaa, bbb,ccc  " -> "aaa" "bbb" "ccc"
		ignoreItemList = cfg_IgnoreItemList.trim().split("\\s*,\\s*");
		
		MinecraftForge.EVENT_BUS.register(new EventHook());
		
		
		ModelManager.instance.setDefaultTexture(EntityLittleMaid.class, ModelManager.instance.getTextureBox("default_Orign"));
		
		//Determine Biomes to generate in
		if (cfg_spawnWeight > 0)
		{
			BiomeDictionary.registerAllBiomes();
			BiomeGenBase[] biomeList = null;
			/*if (cfg_Dominant)
			{
				biomeList = BiomeGenBase.getBiomeGenArray();
			}
			else
			{
				
				biomeList = new BiomeGenBase[]{
						BiomeGenBase.desert,
						BiomeGenBase.plains,
						BiomeGenBase.savanna,
						BiomeGenBase.mushroomIsland,
						BiomeGenBase.forest,
						BiomeGenBase.birchForest,
						BiomeGenBase.swampland,
						BiomeGenBase.taiga,
				};
			}
			for(BiomeGenBase biome : biomeList)
			{
				if(biome!=null)
				{
					EntityRegistry.addSpawn(EntityLittleMaid.class,
							cfg_spawnWeight, cfg_minGroupSize, cfg_maxGroupSize, EnumCreatureType.creature, biome);
				}
			}*/
			for(int i = 1; i<=12; i++)
			{
				switch (i)
				{
					case 1:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.HOT);
						break;
					}
					case 2:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.DRY);
						break;
					}
					case 3:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.SANDY);
						break;
					}
					case 4:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.PLAINS);
						break;
					}
					case 5:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.SAVANNA);
						break;
					}
					case 6:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.SPARSE);
						break;
					}
					case 7:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.MUSHROOM);
						break;
					}
					case 8:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.FOREST);
						break;
					}
					case 9:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.WET);
						break;
					}
					case 10:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.SWAMP);
						break;
					}
					case 11:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.COLD);
						break;
					}
					case 12:
					{
						biomeList = BiomeDictionary.getBiomesForType(Type.CONIFEROUS);
						break;
					}
				}
				for(BiomeGenBase biome : biomeList)
				{
					if (biome!=null && (!BiomeDictionary.isBiomeOfType(biome, Type.OCEAN) && !BiomeDictionary.isBiomeOfType(biome, Type.MOUNTAIN) && !BiomeDictionary.isBiomeOfType(biome, Type.HILLS) && !BiomeDictionary.isBiomeOfType(biome, Type.RIVER) && !BiomeDictionary.isBiomeOfType(biome, Type.BEACH) && !BiomeDictionary.isBiomeOfType(biome, Type.END) && !BiomeDictionary.isBiomeOfType(biome, Type.NETHER) && !BiomeDictionary.isBiomeOfType(biome, Type.JUNGLE) && !BiomeDictionary.isBiomeOfType(biome, Type.SNOWY) && !BiomeDictionary.isBiomeOfType(biome, Type.MESA) && !BiomeDictionary.isBiomeOfType(biome, Type.DEAD) && !BiomeDictionary.isBiomeOfType(biome, Type.SPOOKY) && !BiomeDictionary.isBiomeOfType(biome, Type.MAGICAL)))
					{
						//Registering Spawn in Biome
						EntityRegistry.addSpawn(EntityLittleMaid.class, cfg_spawnWeight, cfg_minGroupSize, cfg_maxGroupSize, EnumCreatureType.creature, biome);
					}
				}
			}
		}
		IFF.loadIFFs();
	}
	
	private static String ignoreItemList[] = new String[]{};

	public static boolean isMaidIgnoreItem(ItemStack item)
	{
		return item!=null && item.getItem()!=null && isMaidIgnoreItem(item.getItem());
	}
	public static boolean isMaidIgnoreItem(Item item)
	{
		if(item!=null)
		{
			String name = Item.itemRegistry.getNameForObject(item);
			for(String ignoreItemName : ignoreItemList)
			{
				if(name.indexOf(ignoreItemName) != -1)
				{
					return true;
				}
			}
		}
		return false;
	}
	// TODO: Merge this with LMM_Net, no need for 2 channels
	public static void serverCustomPayload(EntityPlayer playerEntity, Message var2)
	{
		byte lmode = var2.data[0];
		int leid = 0;
		Entity lentity = null;
		if ((lmode & 0x80) != 0) {
			leid = Helper.getInt(var2.data, 1);
			lentity = Helper.getEntity(var2.data, 1, playerEntity.worldObj);
			if (lentity == null) {
//				System.out.println("lentity == null");
				return;
			}
		}
		Debug("MMM|Upd Srv Call[%2x:%d].", lmode, leid);
//		byte[] ldata;
		
		switch (lmode) {
		case NetConstants.Server_SetTexturePackIndex:
//			System.out.println("SetTexturePackIndex");
			ModelManager.instance.reciveFromClientSetTexturePackIndex(lentity, var2.data);
			break;
		case NetConstants.Server_GetTextureIndex:
			ModelManager.instance.reciveFromClientGetTexturePackIndex(playerEntity, var2.data);
//			System.out.println("GetTextureIndex");
			break;
		case NetConstants.Server_GetTexturePackName:
//			System.out.println("GetTexturePackName");
			ModelManager.instance.reciveFromClientGetTexturePackName(playerEntity, var2.data);
			break;
		}
	}

	public static void sendToClient(EntityPlayer player, byte[] ldata)
	{
//		System.out.println("SendToClient");
		Network.sendPacketToPlayer(1, player, ldata);
	}

	public static String getModelName(String t, String t1) {
		int i = t != null && !t.isEmpty() ? t.lastIndexOf(t1) : -1;
		int i1 = i > -1 ? i + t1.length() : -1;
		if (i1 > -1)
			return t.length() > i1 ? t.substring(i1) : null;
		return t;
	}
}

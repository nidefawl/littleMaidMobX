package littleMaidMobX.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import littleMaidMobX.LittleMaidMobX;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;


public class Config
{

        public static final String CATEGORY_MAIDS = "maids";
		public static final String CATEGORY_MMMLIB = "mmmlib";
		public static final String CATEGORY_ITEMS = "items";
	
	public static Configuration config;
	
	public static int spawnWeight;
	public static int spawnLimit;
	public static int minGroupSize;
	public static int maxGroupSize;
	public static boolean canDespawn;
	public static boolean checkOwnerName;
	public static boolean antiDoppelganger;
	public static boolean cfg_enableSpawnEgg;
	public static boolean VoiceDistortion;
	public static String defaultTexture;
	public static boolean DeathMessage;
	public static boolean Dominant;
	public static boolean Aggressive;
	public static String IgnoreItemList;
	public static boolean makeNoise;
	
	public static boolean isModelAlphaBlend = true;
	
	public static boolean isDebugMessage = true;
	public static boolean isDebugModels = false;

	/*public static File configDir;
	
	public static String[] cfg_comment = {"test code", "can br ?" };
	public static int cfg_testi = 1;
	public static byte cfg_testb = 2;
	public static String cfg_tests = "test string";*/
	
	public static void init(FMLPreInitializationEvent event)
	{
		//configDir = new File(FileManager.minecraftDir, "config");
		config = new Configuration(event.getSuggestedConfigurationFile());
	}

	/*protected static File getConfigFile(Class pClass) {
		return new File(configDir, pClass.getSimpleName() + ".cfg");
	}

	protected static List getConfigFields(Class pClass) {
		List<Field> llist = new ArrayList<Field>();
		Field lfeilds[] = pClass.getDeclaredFields();
		if (lfeilds != null) {
			for (Field lf : lfeilds) {
				int li = lf.getModifiers();
				if (Modifier.isStatic(li) && !Modifier.isPrivate(li)) {
					if (lf.getName().startsWith("cfg_")) {
						llist.add(lf);
					}
				}
			}
		}
		
		return llist;
	}*/

	
	public static void saveConfig() //(Class pClass)
	{
		/*File lfile = getConfigFile(pClass);
		List<Field> llist = getConfigFields(pClass);
		StringBuilder lsb = new StringBuilder();
		Properties lprop = new Properties();
		
		try {
			for (Field lf : llist) {
				if (lf.getName().equals("cfg_comment")) {
					String[] ls = (String[])lf.get(null);
					for (String lt : ls) {
						lsb.append(lt).append("\n");
					}
				} else {
					lprop.setProperty(lf.getName(), lf.get(null).toString());
				}
			}
			lprop.store(new FileOutputStream(lfile), lsb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		config.save();
	}

	public static void loadConfig() //(Class pClass)
	{
		/*File lfile = getConfigFile(pClass);
		if (!lfile.exists() || !lfile.isFile()) return;
		
		List<Field> llist = getConfigFields(pClass);
		StringBuilder lsb = new StringBuilder();
		Properties lprop = new Properties();
		
		try {
			lprop.load(new FileInputStream(lfile));
			for (Field lf : llist) {
				if (lprop.containsKey(lf.getName())) {
					String ls = lprop.getProperty(lf.getName());
					Class lc = lf.getType();
					if (lc.isAssignableFrom(String.class)) {
						lf.set(null, ls);
					}
					else if (lc.isAssignableFrom(Byte.TYPE)) {
						lf.setByte(null, Byte.parseByte(ls));
					}
					else if (lc.isAssignableFrom(Short.TYPE)) {
						lf.setShort(null, Short.parseShort(ls));
					}
					else if (lc.isAssignableFrom(Integer.TYPE)) {
						if (ls.startsWith("0x")) {
							lf.setInt(null, Integer.parseInt(ls, 16));
						} else {
							lf.setInt(null, Integer.parseInt(ls));
						}
					}
					else if (lc.isAssignableFrom(Long.TYPE)) {
						if (ls.startsWith("0x")) {
							lf.setLong(null, Long.parseLong(ls, 16));
						} else {
							lf.setLong(null, Long.parseLong(ls));
						}
					}
					else if (lc.isAssignableFrom(Boolean.TYPE)) {
						lf.setBoolean(null, Boolean.parseBoolean(ls));
					}
					else if (lc.isAssignableFrom(Float.TYPE)) {
						lf.setFloat(null, Float.parseFloat(ls));
					}
					else if (lc.isAssignableFrom(Double.TYPE)) {
						lf.setDouble(null, Double.parseDouble(ls));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		config.load();
	}
	
	public static void syncConfig()
	{
		//MMMLib
		isDebugMessage		= true;//config.get("MMMLib", "isDebugMessage", false).getBoolean(false);
		isModelAlphaBlend	= config.getBoolean("Is Model Alpha Blend", CATEGORY_MMMLIB, true, "true: AlphaBlend(requires more power), false: AlphaTest(faster)");
		//public static boolean AlphaBlend = true;
		
		//Maids
		spawnWeight = config.getInt("Spawn Weight", CATEGORY_MAIDS, 5, 0, 10, "Relative spawn weight. The lower the less common. 10=pigs. 0=off");
		spawnLimit = config.getInt("Spawn Limit", CATEGORY_MAIDS, 20, 10, 100, "Maximum spawn count in the World.");
		minGroupSize = config.getInt("Minimum Group Size", CATEGORY_MAIDS, 1, 1, 10, "Minimum spawn group count.");
		maxGroupSize = config.getInt("Maximum Group Size", CATEGORY_MAIDS, 3, 1, 10, "Maximum spawn group count.");
		canDespawn = config.getBoolean("Can Despawn", CATEGORY_MAIDS, false, "It will despawn, if it lets things go.");
		checkOwnerName = config.getBoolean("Check Owner Name", CATEGORY_MAIDS, false, "At local, make sure the name of the owner.");
		antiDoppelganger = config.getBoolean("Anti Doppelganger", CATEGORY_MAIDS, true, "Not to survive the doppelganger.");
		VoiceDistortion = config.getBoolean("Voice Distortion", CATEGORY_MAIDS, true, "Enables Maid voices to distort based of hair color");
		defaultTexture = config.getString("Default Texture", CATEGORY_MAIDS, "", "Default selected Texture Packege. Null is Random");
		DeathMessage = config.getBoolean("Print Death Message", CATEGORY_MAIDS, true, "Prints message on the death of your maid.");
		Aggressive = config.getBoolean("Agressive", CATEGORY_MAIDS, true, "true: Will be hostile, false: Is a pacifist");
		IgnoreItemList = config.getString("Ignore Item List", CATEGORY_MAIDS, "arsmagica2", "?");
		makeNoise = config.getBoolean("Maids Make Sounds", CATEGORY_MAIDS, true, "Determines whether or not maids will make noises");
		Dominant = config.getBoolean("Maids Spawn Everywhere", CATEGORY_MAIDS, false, "If true maids will spawn in all biomes, if false maids will spawn in biomes of approved types.");
		
		//Items
		cfg_enableSpawnEgg = config.getBoolean("Enable Spawn Egg", CATEGORY_ITEMS, true, "Enables Little Maid SpawnEgg Recipe.");
	}
	
	public static void checkConfig() //(Class pClass)
	{
		loadConfig();
		syncConfig();
		if (config.hasChanged())
		{
			saveConfig();
		}
	}
}

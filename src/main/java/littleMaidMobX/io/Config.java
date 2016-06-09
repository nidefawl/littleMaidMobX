package littleMaidMobX.io;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


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
	
	public static boolean isModelAlphaBlend;
	
	public static boolean isDebugMessage;
	public static boolean isDebugModels = true;

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
		isDebugMessage		= config.getBoolean("Display Debug Messages", CATEGORY_MMMLIB, false, "Whether or not LittleMaid debug messages will appear in the log, set to TRUE if you want to report an error.");
		isModelAlphaBlend	= config.getBoolean("Is Model Alpha Blend", CATEGORY_MMMLIB, true, "true: AlphaBlend(requires more power), false: AlphaTest(faster)");
		//public static boolean AlphaBlend = true;
		
		//Maids
		spawnWeight = config.getInt("Spawn Weight", CATEGORY_MAIDS, 5, 0, 10, "Relative spawn weight. The lower the less common. 10=pigs 0=off (Requires Restart)");
		spawnLimit = config.getInt("Spawn Limit", CATEGORY_MAIDS, 20, 10, 100, "Maximum spawn count in the World. (Requires Restart)");//Needs more lookup
		minGroupSize = config.getInt("Minimum Group Size", CATEGORY_MAIDS, 1, 1, 10, "Minimum spawn group count. (Requires Restart)");
		maxGroupSize = config.getInt("Maximum Group Size", CATEGORY_MAIDS, 3, 1, 10, "Maximum spawn group count. (Requires Restart)");
		canDespawn = config.getBoolean("Can Despawn", CATEGORY_MAIDS, false, "Whether or not a Maid without a contract can despawn.");
		checkOwnerName = config.getBoolean("Check Owner Name", CATEGORY_MAIDS, true, "Checks name of owner, if you do multiplayer keep this true.");
		antiDoppelganger = config.getBoolean("Anti Doppelganger", CATEGORY_MAIDS, true, "Not to survive the doppelganger.");//What is this
		VoiceDistortion = config.getBoolean("Voice Distortion", CATEGORY_MAIDS, true, "Enables Maid voices to distort based of hair color");
		defaultTexture = config.getString("Default Texture", CATEGORY_MAIDS, "", "Default selected Texture Package. Null is Random");
		DeathMessage = config.getBoolean("Print Death Message", CATEGORY_MAIDS, true, "Prints message on the death of your maid.");
		Aggressive = config.getBoolean("Agressive", CATEGORY_MAIDS, true, "true: Will be hostile, false: Is a pacifist");//What is this
		IgnoreItemList = config.getString("Ignore Item List", CATEGORY_MAIDS, "arsmagica2", "?");//What is this
		makeNoise = config.getBoolean("Maids Make Sounds", CATEGORY_MAIDS, true, "Determines whether or not maids will make noises");
		Dominant = config.getBoolean("Maids Spawn Everywhere", CATEGORY_MAIDS, false, "If true maids will spawn in all biomes, if false maids will only spawn in biomes of approved types. (Requires Restart)");
		
		//Items
		cfg_enableSpawnEgg = config.getBoolean("Enable Spawn Egg", CATEGORY_ITEMS, true, "Enables Little Maid SpawnEgg Recipe. (Requires Restart)");
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

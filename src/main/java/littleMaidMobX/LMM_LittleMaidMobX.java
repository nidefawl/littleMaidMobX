/*     */ package littleMaidMobX;
/*     */ 
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.registry.EntityRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import java.io.PrintStream;
/*     */ import mmmlibx.lib.MMM_Config;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import mmmlibx.lib.MMM_TextureManager;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.AchievementPage;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import network.W_Network;
/*     */ 
/*     */ @Mod(modid="lmmx", name="lmmx")
/*     */ public class LMM_LittleMaidMobX
/*     */ {
/*     */   public static final String DOMAIN = "lmmx";
/*  32 */   public static String[] cfg_comment = { "spawnWeight = Relative spawn weight. The lower the less common. 10=pigs. 0=off", "spawnLimit = Maximum spawn count in the World.", "minGroupSize = Minimum spawn group count.", "maxGroupSize = Maximum spawn group count.", "canDespawn = It will despawn, if it lets things go. ", "checkOwnerName = At local, make sure the name of the owner. ", "antiDoppelganger = Not to survive the doppelganger. ", "enableSpawnEgg = Enable LMM SpawnEgg Recipe. ", "VoiceDistortion = LittleMaid Voice distortion.", "defaultTexture = Default selected Texture Packege. Null is Random", "DebugMessage = Print Debug Massages.", "DeathMessage = Print Death Massages.", "Dominant = Spawn Anywhere.", "Aggressive = true: Will be hostile, false: Is a pacifist", "IgnoreItemList = aaa, bbb, ccc: Items little maid to ignore" };
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
/*  53 */   public static int cfg_spawnWeight = 5;
/*     */   
/*  55 */   public static int cfg_spawnLimit = 20;
/*     */   
/*  57 */   public static int cfg_minGroupSize = 1;
/*     */   
/*  59 */   public static int cfg_maxGroupSize = 3;
/*     */   
/*  61 */   public static boolean cfg_canDespawn = false;
/*     */   
/*  63 */   public static boolean cfg_checkOwnerName = false;
/*     */   
/*  65 */   public static boolean cfg_antiDoppelganger = true;
/*     */   
/*  67 */   public static boolean cfg_enableSpawnEgg = true;
/*     */   
/*     */ 
/*     */ 
/*  71 */   public static boolean cfg_VoiceDistortion = true;
/*     */   
/*     */ 
/*  74 */   public static String cfg_defaultTexture = "";
/*     */   
/*  76 */   public static boolean cfg_PrintDebugMessage = false;
/*     */   
/*  78 */   public static boolean cfg_DeathMessage = true;
/*     */   
/*  80 */   public static boolean cfg_Dominant = false;
/*     */   
/*     */ 
/*     */ 
/*  84 */   public static boolean cfg_Aggressive = true;
/*  85 */   public static String cfg_IgnoreItemList = "arsmagica2";
/*     */   
/*     */ 
/*     */   public static Achievement ac_Contract;
/*     */   
/*     */   @SidedProxy(clientSide="littleMaidMobX.LMM_ProxyClient", serverSide="littleMaidMobX.LMM_ProxyCommon")
/*     */   public static LMM_ProxyCommon proxy;
/*     */   
/*     */   @Mod.Instance("lmmx")
/*     */   public static LMM_LittleMaidMobX instance;
/*     */   
/*     */   public static LMM_ItemSpawnEgg spawnEgg;
/*     */   
/*     */ 
/*     */   public static void Debug(String pText, Object... pVals)
/*     */   {
/* 101 */     if (cfg_PrintDebugMessage) {
/* 102 */       System.out.println(String.format("littleMaidMob-" + pText, pVals));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getName() {
/* 107 */     return "littleMaidMobX";
/*     */   }
/*     */   
/*     */   public String getPriorities()
/*     */   {
/* 112 */     return "required-after:mod_MMM_MMMLib";
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 116 */     return "1.7.2-x";
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void PreInit(FMLPreInitializationEvent evt)
/*     */   {
/* 122 */     mmmlibx.lib.FileManager.setSrcPath(evt.getSourceFile());
/* 123 */     MMM_Config.init();
/*     */     
/*     */ 
/*     */ 
/* 127 */     MMM_Config.checkConfig(getClass());
/*     */     
/* 129 */     cfg_defaultTexture = cfg_defaultTexture.trim();
/*     */     
/* 131 */     NetworkRegistry.INSTANCE.registerGuiHandler(instance, new LMM_GuiCommonHandler());
/*     */     
/* 133 */     MMM_TextureManager.instance.init();
/*     */     
/* 135 */     EntityRegistry.registerModEntity(LMM_EntityLittleMaid.class, "LittleMaidX", 0, instance, 80, 3, true);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */     spawnEgg = new LMM_ItemSpawnEgg();
/* 143 */     spawnEgg.setUnlocalizedName("lmmx:spawn_lmmx_egg");
/* 144 */     spawnEgg.setTextureName("lmmx:spawn_lmmx_egg");
/* 145 */     GameRegistry.registerItem(spawnEgg, "spawn_lmmx_egg");
/* 146 */     if (cfg_enableSpawnEgg)
/*     */     {
/* 148 */       GameRegistry.addRecipe(new ItemStack(spawnEgg, 1), new Object[] { "scs", "sbs", " e ", Character.valueOf('s'), Items.sugar, Character.valueOf('c'), new ItemStack(Items.dye, 1, 3), Character.valueOf('b'), Items.slime_ball, Character.valueOf('e'), Items.egg });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */     ac_Contract = new Achievement("achievement.contract", "contract", 0, 0, Items.cake, null).initIndependentStat().registerStat();
/* 160 */     Achievement[] achievements = { ac_Contract };
/* 161 */     AchievementPage.registerAchievementPage(new AchievementPage("LittleMaidX", achievements));
/*     */     
/* 163 */     if (MMM_Helper.isClient)
/*     */     {
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
/* 175 */       proxy.init();
/*     */     }
/*     */     
/*     */ 
/* 179 */     LMM_EntityModeManager.init();
/*     */     
/*     */ 
/* 182 */     W_Network.init("lmmx");
/*     */     
/*     */ 
/* 185 */     proxy.loadSounds();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void postInit(FMLPostInitializationEvent evt)
/*     */   {
/* 195 */     ignoreItemList = cfg_IgnoreItemList.trim().split("\\s*,\\s*");
/*     */     
/* 197 */     MinecraftForge.EVENT_BUS.register(new LMM_EventHook());
/*     */     
/*     */ 
/* 200 */     MMM_TextureManager.instance.setDefaultTexture(LMM_EntityLittleMaid.class, MMM_TextureManager.instance.getTextureBox("default_Orign"));
/*     */     
/*     */ 
/* 203 */     BiomeGenBase[] biomeList = null;
/* 204 */     if (cfg_spawnWeight > 0) {
/* 205 */       if (cfg_Dominant)
/*     */       {
/* 207 */         biomeList = BiomeGenBase.getBiomeGenArray();
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 212 */         biomeList = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.plains, BiomeGenBase.savanna, BiomeGenBase.mushroomIsland, BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.swampland, BiomeGenBase.taiga };
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 223 */       for (BiomeGenBase biome : biomeList)
/*     */       {
/* 225 */         if (biome != null)
/*     */         {
/* 227 */           EntityRegistry.addSpawn(LMM_EntityLittleMaid.class, cfg_spawnWeight, cfg_minGroupSize, cfg_maxGroupSize, EnumCreatureType.creature, new BiomeGenBase[] { biome });
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 234 */     LMM_EntityModeManager.loadEntityMode();
/* 235 */     LMM_EntityModeManager.showLoadedModes();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 241 */     LMM_IFF.loadIFFs();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 246 */   private static String[] ignoreItemList = new String[0];
/*     */   
/*     */   public static boolean isMaidIgnoreItem(ItemStack item)
/*     */   {
/* 250 */     return (item != null) && (item.getItem() != null) && (isMaidIgnoreItem(item.getItem()));
/*     */   }
/*     */   
/*     */   public static boolean isMaidIgnoreItem(Item item) {
/* 254 */     if (item != null)
/*     */     {
/* 256 */       String name = Item.itemRegistry.getNameForObject(item);
/* 257 */       for (String ignoreItemName : ignoreItemList)
/*     */       {
/* 259 */         if (name.indexOf(ignoreItemName) != -1)
/*     */         {
/* 261 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 265 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_LittleMaidMobX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
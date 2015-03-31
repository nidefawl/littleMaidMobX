/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import mmmlibx.lib.guns.GunsBase;
/*     */ import mmmlibx.lib.multiModel.texture.MultiModelManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;
/*     */ import network.W_Message;
/*     */ import network.W_Network;
/*     */ 
/*     */ 
/*     */ @Mod(modid="MMMLibX", name="MMMLibX", version="1.7.x-srg-1")
/*     */ public class MMMLib
/*     */ {
/*  27 */   public static boolean cfg_isModelAlphaBlend = true;
/*     */   
/*  29 */   public static boolean isDebugMessage = true;
/*  30 */   public static boolean isModelAlphaBlend = true;
/*     */   
/*     */ 
/*     */   @SidedProxy(clientSide="mmmlibx.lib.MMM_ProxyClient", serverSide="mmmlibx.lib.MMM_ProxyCommon")
/*     */   public static MMM_ProxyCommon proxy;
/*     */   
/*     */ 
/*     */ 
/*     */   public static void Debug(String pText, Object... pData)
/*     */   {
/*  40 */     if (isDebugMessage) {
/*  41 */       System.out.println(String.format("MMMLib-" + pText, pData));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void Debug(boolean isRemote, String pText, Object... pData) {
/*  46 */     if (isDebugMessage) {
/*  47 */       System.out.println(String.format("[" + (isRemote ? "Client" : "Server") + "]MMMLib-" + pText, pData));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void preInit(FMLPreInitializationEvent pEvent)
/*     */   {
/*  55 */     mmmlibx.lib.multiModel.MMMLoader.MMMTransformer.isEnable = true;
/*     */     
/*     */ 
/*  58 */     File configFile = pEvent.getSuggestedConfigurationFile();
/*  59 */     Configuration lconf = new Configuration(configFile);
/*  60 */     lconf.load();
/*  61 */     isDebugMessage = lconf.get("MMMLib", "isDebugMessage", false).getBoolean(false);
/*  62 */     isModelAlphaBlend = lconf.get("MMMLib", "isModelAlphaBlend", true).getBoolean(true);
/*  63 */     cfg_isModelAlphaBlend = isModelAlphaBlend;
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
/*  85 */     lconf.save();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  90 */     MMM_StabilizerManager.init();
/*     */     
/*     */ 
/*  93 */     MMM_TextureManager.instance.loadTextures();
/*     */     
/*  95 */     if (MMM_Helper.isClient)
/*     */     {
/*     */ 
/*  98 */       MMM_StabilizerManager.loadStabilizer();
/*     */       
/* 100 */       Debug("Localmode: InitTextureList.", new Object[0]);
/* 101 */       MMM_TextureManager.instance.initTextureList(true);
/*     */     } else {
/* 103 */       MMM_TextureManager.instance.loadTextureServer();
/*     */     }
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void init(FMLInitializationEvent pEvent) {
/* 109 */     if (pEvent.getSide() == Side.CLIENT) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Mod.EventHandler
/*     */   public void loaded(FMLPostInitializationEvent pEvent)
/*     */   {
/* 119 */     GunsBase.initAppend();
/*     */     
/*     */ 
/* 122 */     mmmlibx.lib.multiModel.MMMLoader.MMMTransformer.isEnable = true;
/* 123 */     MultiModelManager.instance.execute();
/*     */     
/*     */ 
/* 126 */     List<File> llist = FileManager.getAllmodsFiles(getClass().getClassLoader(), true);
/* 127 */     for (File lf : llist) {
/* 128 */       Debug("targetFiles: %s", new Object[] { lf.getAbsolutePath() });
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 133 */       Class<?> lc = ReflectionHelper.getClass(getClass().getClassLoader(), new String[] { "net.minecraft.entity.EntityLivingBase" });
/* 134 */       Debug("test-getClass: %s", new Object[] { lc.toString() });
/*     */     } catch (Exception e) {
/* 136 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void serverCustomPayload(EntityPlayer playerEntity, W_Message var2)
/*     */   {
/* 144 */     byte lmode = var2.data[0];
/* 145 */     int leid = 0;
/* 146 */     Entity lentity = null;
/* 147 */     if ((lmode & 0x80) != 0) {
/* 148 */       leid = MMM_Helper.getInt(var2.data, 1);
/* 149 */       lentity = MMM_Helper.getEntity(var2.data, 1, playerEntity.worldObj);
/* 150 */       if (lentity == null) return;
/*     */     }
/* 152 */     Debug("MMM|Upd Srv Call[%2x:%d].", new Object[] { Byte.valueOf(lmode), Integer.valueOf(leid) });
/*     */     
/*     */ 
/* 155 */     switch (lmode)
/*     */     {
/*     */     case -128: 
/* 158 */       MMM_TextureManager.instance.reciveFromClientSetTexturePackIndex(lentity, var2.data);
/* 159 */       break;
/*     */     
/*     */     case 1: 
/* 162 */       MMM_TextureManager.instance.reciveFromClientGetTexturePackIndex(playerEntity, var2.data);
/* 163 */       break;
/*     */     
/*     */     case 2: 
/* 166 */       MMM_TextureManager.instance.reciveFromClientGetTexturePackName(playerEntity, var2.data);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public static void sendToClient(EntityPlayer player, byte[] ldata)
/*     */   {
/* 173 */     W_Network.sendPacketToPlayer(1, player, ldata);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMMLib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
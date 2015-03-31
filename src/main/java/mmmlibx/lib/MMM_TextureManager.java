/*      */ package mmmlibx.lib;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Random;
/*      */ import java.util.TreeMap;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.zip.ZipEntry;
/*      */ import java.util.zip.ZipInputStream;
/*      */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*      */ import net.minecraft.client.renderer.entity.RenderBiped;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MMM_TextureManager
/*      */ {
/*   36 */   public static MMM_TextureManager instance = new MMM_TextureManager();
/*      */   
/*   38 */   public static String nameTextureIndex = "config/mod_MMM_textureList.cfg";
/*   39 */   public static String defaultModelName = "Orign";
/*      */   
/*      */   public static final int tx_oldwild = 16;
/*      */   
/*      */   public static final int tx_oldarmor1 = 17;
/*      */   
/*      */   public static final int tx_oldarmor2 = 18;
/*      */   
/*      */   public static final int tx_oldeye = 19;
/*      */   public static final int tx_gui = 32;
/*      */   public static final int tx_wild = 48;
/*      */   public static final int tx_armor1 = 64;
/*      */   public static final int tx_armor2 = 80;
/*      */   public static final int tx_eye = 96;
/*      */   public static final int tx_eyecontract = 96;
/*      */   public static final int tx_eyewild = 112;
/*      */   public static final int tx_armor1light = 128;
/*      */   public static final int tx_armor2light = 144;
/*      */   public static String[] armorFilenamePrefix;
/*   58 */   protected static String[] defNames = { "mob_littlemaid0.png", "mob_littlemaid1.png", "mob_littlemaid2.png", "mob_littlemaid3.png", "mob_littlemaid4.png", "mob_littlemaid5.png", "mob_littlemaid6.png", "mob_littlemaid7.png", "mob_littlemaid8.png", "mob_littlemaid9.png", "mob_littlemaida.png", "mob_littlemaidb.png", "mob_littlemaidc.png", "mob_littlemaidd.png", "mob_littlemaide.png", "mob_littlemaidf.png", "mob_littlemaidw.png", "mob_littlemaid_a00.png", "mob_littlemaid_a01.png" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   74 */   protected Map<String, ModelMultiBase[]> modelMap = new TreeMap();
/*      */   
/*      */ 
/*      */ 
/*   78 */   private List<MMM_TextureBox> textures = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*   82 */   public Map<MMM_TextureBox, Integer> textureServerIndex = new HashMap();
/*      */   
/*      */ 
/*      */ 
/*   86 */   public List<MMM_TextureBoxServer> textureServer = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   91 */   protected Map<Class, MMM_TextureBox> defaultTextures = new HashMap();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   96 */   protected String[] requestString = { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
/*      */   
/*      */ 
/*      */ 
/*  100 */   protected int[] requestStringCounter = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */   
/*      */ 
/*      */ 
/*  104 */   protected int[] requestIndex = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*      */   
/*      */ 
/*      */ 
/*  108 */   protected int[] requestIndexCounter = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */   
/*      */ 
/*      */ 
/*  112 */   protected Map<ITextureEntity, int[]> stackGetTexturePack = new HashMap();
/*  113 */   protected Map<ITextureEntity, Object[]> stackSetTexturePack = new HashMap();
/*      */   
/*  115 */   protected List<String[]> searchPrefix = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void init()
/*      */   {
/*  122 */     FileManager.getModFile("mmmlibx", "littleMaidMob");
/*  123 */     FileManager.getModFile("mmmlibx", "mmmlibx");
/*  124 */     FileManager.getModFile("mmmlibx", "ModelMulti");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  129 */     addSearch("mmmlibx", "/assets/minecraft/textures/entity/ModelMulti/", "ModelMulti_");
/*  130 */     addSearch("mmmlibx", "/assets/minecraft/textures/entity/littleMaid/", "ModelMulti_");
/*  131 */     addSearch("mmmlibx", "/assets/minecraft/textures/entity/littleMaid/", "ModelLittleMaid_");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected String[] getSearch(String pName)
/*      */   {
/*  152 */     for (String[] lss : this.searchPrefix) {
/*  153 */       if (lss[0].equals(pName)) {
/*  154 */         return lss;
/*      */       }
/*      */     }
/*  157 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addSearch(String pName, String pTextureDir, String pClassPrefix)
/*      */   {
/*  164 */     this.searchPrefix.add(new String[] { pName, pTextureDir, pClassPrefix });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MMM_TextureBox getTextureBox(String pName)
/*      */   {
/*  171 */     for (MMM_TextureBox ltb : ) {
/*  172 */       if (ltb.textureName.equals(pName)) {
/*  173 */         return ltb;
/*      */       }
/*      */     }
/*  176 */     return null;
/*      */   }
/*      */   
/*      */   public static List<MMM_TextureBox> getTextureList()
/*      */   {
/*  181 */     return instance.textures;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MMM_TextureBox getTextureBox(MMM_TextureBoxBase pBoxBase)
/*      */   {
/*  190 */     if ((pBoxBase instanceof MMM_TextureBox))
/*  191 */       return (MMM_TextureBox)pBoxBase;
/*  192 */     if ((pBoxBase instanceof MMM_TextureBoxServer)) {
/*  193 */       return getTextureBox(pBoxBase.textureName);
/*      */     }
/*  195 */     return null;
/*      */   }
/*      */   
/*      */   public MMM_TextureBoxServer getTextureBoxServer(String pName) {
/*  199 */     for (MMM_TextureBoxServer lbox : this.textureServer) {
/*  200 */       if (lbox.textureName.equals(pName)) {
/*  201 */         return lbox;
/*      */       }
/*      */     }
/*  204 */     return null;
/*      */   }
/*      */   
/*      */   public MMM_TextureBoxServer getTextureBoxServer(int pIndex)
/*      */   {
/*  209 */     if (this.textureServer.size() > pIndex) {
/*  210 */       return (MMM_TextureBoxServer)this.textureServer.get(pIndex);
/*      */     }
/*  212 */     return null;
/*      */   }
/*      */   
/*      */   protected void getArmorPrefix() {
/*  216 */     armorFilenamePrefix = RenderBiped.bipedArmorFilenamePrefix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean loadTextures()
/*      */   {
/*  232 */     MMMLib.Debug("loadTexturePacks.", new Object[0]);
/*      */     
/*  234 */     if (MMM_Helper.isClient) {
/*  235 */       getArmorPrefix();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  251 */     for (String[] lss : this.searchPrefix) {
/*  252 */       MMMLib.Debug("getTexture[%s:%s].", new Object[] { lss[0], lss[1] });
/*      */       
/*  254 */       for (i$ = FileManager.getFileList(lss[0]).iterator(); i$.hasNext();) { lf = (File)i$.next();
/*  255 */         for (String[] lst : this.searchPrefix) { boolean lflag;
/*      */           boolean lflag;
/*  257 */           if (lf.isDirectory())
/*      */           {
/*  259 */             lflag = addTexturesDir(lf, lst);
/*      */           }
/*      */           else {
/*  262 */             lflag = addTexturesZip(lf, lst);
/*      */           }
/*  264 */           MMMLib.Debug("getTexture-append-%s-%s.", new Object[] { lf.getName(), lflag ? "done" : "fail" });
/*      */         }
/*      */       }
/*      */     }
/*      */     Iterator i$;
/*      */     File lf;
/*  270 */     buildCrafterTexture();
/*      */     
/*      */ 
/*  273 */     ModelMultiBase[] ldm = (ModelMultiBase[])this.modelMap.get(defaultModelName);
/*  274 */     if ((ldm == null) && (!this.modelMap.isEmpty())) {
/*  275 */       ldm = (ModelMultiBase[])this.modelMap.values().toArray()[0];
/*      */     }
/*  277 */     for (MMM_TextureBox ltb : this.textures) {
/*  278 */       if (ltb.modelName.isEmpty()) {
/*  279 */         ltb.setModels(defaultModelName, null, ldm);
/*      */       }
/*  281 */       else if (this.modelMap.containsKey(ltb.modelName)) {
/*  282 */         ltb.setModels(ltb.modelName, (ModelMultiBase[])this.modelMap.get(ltb.modelName), ldm);
/*      */       }
/*      */     }
/*      */     
/*  286 */     for (Map.Entry<String, ModelMultiBase[]> le : this.modelMap.entrySet()) {
/*  287 */       String ls = ((ModelMultiBase[])le.getValue())[0].getUsingTexture();
/*  288 */       if ((ls != null) && 
/*  289 */         (getTextureBox(ls + "_" + (String)le.getKey()) == null)) {
/*  290 */         MMM_TextureBox lbox = null;
/*  291 */         for (MMM_TextureBox ltb : this.textures) {
/*  292 */           if (ltb.packegeName.equals(ls)) {
/*  293 */             lbox = ltb;
/*  294 */             break;
/*      */           }
/*      */         }
/*  297 */         if (lbox != null) {
/*  298 */           lbox = lbox.duplicate();
/*  299 */           lbox.setModels((String)le.getKey(), null, (ModelMultiBase[])le.getValue());
/*  300 */           this.textures.add(lbox);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  305 */     MMMLib.Debug("Loaded Texture Lists.(%d)", new Object[] { Integer.valueOf(this.textures.size()) });
/*  306 */     for (MMM_TextureBox lbox : this.textures) {
/*  307 */       MMMLib.Debug("texture: %s(%s) - hasModel:%b", new Object[] { lbox.textureName, lbox.fileName, Boolean.valueOf(lbox.models != null ? 1 : false) });
/*      */     }
/*  309 */     for (int li = this.textures.size() - 1; li >= 0; li--) {
/*  310 */       if (((MMM_TextureBox)this.textures.get(li)).models == null) {
/*  311 */         this.textures.remove(li);
/*      */       }
/*      */     }
/*  314 */     MMMLib.Debug("Rebuild Texture Lists.(%d)", new Object[] { Integer.valueOf(this.textures.size()) });
/*  315 */     for (MMM_TextureBox lbox : this.textures) {
/*  316 */       MMMLib.Debug("texture: %s(%s) - hasModel:%b", new Object[] { lbox.textureName, lbox.fileName, Boolean.valueOf(lbox.models != null ? 1 : false) });
/*      */     }
/*      */     
/*      */ 
/*  320 */     setDefaultTexture(EntityLivingBase.class, getTextureBox("default_" + defaultModelName));
/*      */     
/*  322 */     return false;
/*      */   }
/*      */   
/*      */   public void buildCrafterTexture()
/*      */   {
/*  327 */     MMM_TextureBox lbox = new MMM_TextureBox("Crafter_Steve", new String[] { "", "", "" });
/*  328 */     lbox.fileName = "";
/*      */     
/*  330 */     lbox.addTexture(12, "/assets/minecraft/textures/entity/steve.png");
/*  331 */     if ((armorFilenamePrefix != null) && (armorFilenamePrefix.length > 0)) {
/*  332 */       for (String ls : armorFilenamePrefix) {
/*  333 */         Map<Integer, ResourceLocation> lmap = new HashMap();
/*  334 */         lmap.put(Integer.valueOf(64), new ResourceLocation("textures/models/armor/" + ls + "_layer_2.png"));
/*      */         
/*  336 */         lmap.put(Integer.valueOf(80), new ResourceLocation("textures/models/armor/" + ls + "_layer_1.png"));
/*      */         
/*  338 */         lbox.armors.put(ls, lmap);
/*      */       }
/*      */     }
/*      */     
/*  342 */     this.textures.add(lbox);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean loadTextureServer()
/*      */   {
/*  349 */     this.textureServer.clear();
/*  350 */     for (MMM_TextureBox lbox : getTextureList()) {
/*  351 */       this.textureServer.add(new MMM_TextureBoxServer(lbox));
/*      */     }
/*      */     
/*  354 */     File lfile = MinecraftServer.getServer().getFile(nameTextureIndex);
/*  355 */     if ((lfile.exists()) && (lfile.isFile())) {
/*      */       try {
/*  357 */         FileReader fr = new FileReader(lfile);
/*  358 */         BufferedReader br = new BufferedReader(fr);
/*      */         
/*      */         String ls;
/*  361 */         while ((ls = br.readLine()) != null) {
/*  362 */           String[] lt = ls.split(",");
/*  363 */           if (lt.length >= 7)
/*      */           {
/*  365 */             MMM_TextureBoxServer lbox = getTextureBoxServer(lt[6]);
/*  366 */             if (lbox == null) {
/*  367 */               lbox = new MMM_TextureBoxServer();
/*  368 */               this.textureServer.add(lbox);
/*      */             }
/*  370 */             lbox.contractColor = MMM_Helper.getHexToInt(lt[0]);
/*  371 */             lbox.wildColor = MMM_Helper.getHexToInt(lt[1]);
/*  372 */             lbox.setModelSize(Float.valueOf(lt[2]).floatValue(), Float.valueOf(lt[3]).floatValue(), Float.valueOf(lt[4]).floatValue(), Float.valueOf(lt[5]).floatValue());
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  377 */             lbox.textureName = lt[6];
/*      */           }
/*      */         }
/*      */         
/*  381 */         br.close();
/*  382 */         fr.close();
/*      */       } catch (Exception e) {
/*  384 */         e.printStackTrace();
/*      */       }
/*      */       
/*  387 */       MMMLib.Debug("Loaded ServerBoxList.(%d)", new Object[] { Integer.valueOf(this.textureServer.size()) });
/*  388 */       for (int li = 0; li < this.textureServer.size(); li++) {
/*  389 */         MMM_TextureBoxServer lbox = (MMM_TextureBoxServer)this.textureServer.get(li);
/*  390 */         MMMLib.Debug("%04d=%s:%04x:%04x", new Object[] { Integer.valueOf(li), lbox.textureName, Integer.valueOf(lbox.contractColor), Integer.valueOf(lbox.wildColor) });
/*      */       }
/*  392 */       return true;
/*      */     }
/*      */     
/*      */ 
/*  396 */     return false;
/*      */   }
/*      */   
/*      */   public void saveTextureServer()
/*      */   {
/*  401 */     File lfile = MinecraftServer.getServer().getFile(nameTextureIndex);
/*      */     try {
/*  403 */       FileWriter fw = new FileWriter(lfile);
/*  404 */       BufferedWriter bw = new BufferedWriter(fw);
/*      */       
/*  406 */       for (MMM_TextureBoxServer lbox : this.textureServer) {
/*  407 */         bw.write(String.format("%04x,%04x,%f,%f,%f,%f,%s", new Object[] { Integer.valueOf(lbox.getContractColorBits()), Integer.valueOf(lbox.getWildColorBits()), Float.valueOf(lbox.getHeight(null)), Float.valueOf(lbox.getWidth(null)), Float.valueOf(lbox.getYOffset(null)), Float.valueOf(lbox.getMountedYOffset(null)), lbox.textureName }));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  416 */         bw.newLine();
/*      */       }
/*      */       
/*  419 */       bw.close();
/*  420 */       fw.close();
/*      */     } catch (Exception e) {
/*  422 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void initTextureList(boolean pFlag)
/*      */   {
/*  430 */     MMMLib.Debug("Clear TextureBoxServer.", new Object[0]);
/*  431 */     this.textureServerIndex.clear();
/*  432 */     this.textureServer.clear();
/*  433 */     if (pFlag) {
/*  434 */       int li = 0;
/*  435 */       for (MMM_TextureBox lbc : getTextureList()) {
/*  436 */         MMM_TextureBoxServer lbs = new MMM_TextureBoxServer(lbc);
/*  437 */         this.textureServer.add(lbs);
/*  438 */         this.textureServerIndex.put(lbc, Integer.valueOf(li++));
/*      */       }
/*  440 */       MMMLib.Debug("Rebuild TextureBoxServer(%d).", new Object[] { Integer.valueOf(this.textureServer.size()) });
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void addModelClass(String fname, String[] pSearch)
/*      */   {
/*  452 */     int lfindprefix = fname.indexOf(pSearch[2]);
/*  453 */     if ((lfindprefix > -1) && (fname.endsWith(".class"))) {
/*  454 */       String cn = fname.replace(".class", "");
/*  455 */       String pn = cn.substring(pSearch[2].length() + lfindprefix);
/*      */       
/*  457 */       if (this.modelMap.containsKey(pn)) { return;
/*      */       }
/*  459 */       ClassLoader lclassloader = MMMLib.class.getClassLoader();
/*  460 */       Package lpackage = MMMLib.class.getPackage();
/*      */       try { Class lclass;
/*      */         Class lclass;
/*  463 */         if (lpackage != null)
/*      */         {
/*  465 */           cn = cn.replace("/", ".");
/*  466 */           System.out.println("MMM_TextureManager.addModelClass : " + cn);
/*  467 */           lclass = lclassloader.loadClass(cn);
/*      */         } else {
/*  469 */           lclass = Class.forName(cn);
/*      */         }
/*  471 */         if ((!ModelMultiBase.class.isAssignableFrom(lclass)) || (Modifier.isAbstract(lclass.getModifiers()))) {
/*  472 */           MMMLib.Debug("getModelClass-fail.", new Object[0]);
/*  473 */           return;
/*      */         }
/*  475 */         ModelMultiBase[] mlm = new ModelMultiBase[3];
/*  476 */         Constructor<ModelMultiBase> cm = lclass.getConstructor(new Class[] { Float.TYPE });
/*  477 */         mlm[0] = ((ModelMultiBase)cm.newInstance(new Object[] { Float.valueOf(0.0F) }));
/*  478 */         float[] lsize = mlm[0].getArmorModelsSize();
/*  479 */         mlm[1] = ((ModelMultiBase)cm.newInstance(new Object[] { Float.valueOf(lsize[0]) }));
/*  480 */         mlm[2] = ((ModelMultiBase)cm.newInstance(new Object[] { Float.valueOf(lsize[1]) }));
/*  481 */         this.modelMap.put(pn, mlm);
/*  482 */         MMMLib.Debug("getModelClass-%s:%s", new Object[] { pn, cn });
/*      */       }
/*      */       catch (Exception exception) {
/*  485 */         MMMLib.Debug("getModelClass-Exception: %s", new Object[] { fname });
/*      */         
/*  487 */         exception.printStackTrace();
/*      */       }
/*      */       catch (Error error) {
/*  490 */         MMMLib.Debug("getModelClass-Error: %s", new Object[] { fname });
/*  491 */         error.printStackTrace();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void addTextureName(String fname, String[] pSearch)
/*      */   {
/*  498 */     if (!fname.startsWith("/")) {
/*  499 */       fname = "/" + fname;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  506 */     if (fname.startsWith(pSearch[1])) {
/*  507 */       int i = fname.lastIndexOf("/");
/*  508 */       if (pSearch[1].length() < i) {
/*  509 */         String pn = fname.substring(pSearch[1].length(), i);
/*  510 */         pn = pn.replace('/', '.');
/*  511 */         String fn = fname.substring(i);
/*  512 */         int lindex = getIndex(fn);
/*  513 */         if (lindex > -1) {
/*  514 */           String an = null;
/*  515 */           if (lindex == 17) {
/*  516 */             lindex = 64;
/*  517 */             an = "default";
/*      */           }
/*  519 */           if (lindex == 18) {
/*  520 */             lindex = 80;
/*  521 */             an = "default";
/*      */           }
/*  523 */           if (lindex == 16) {
/*  524 */             lindex = 60;
/*      */           }
/*  526 */           MMM_TextureBox lts = getTextureBox(pn);
/*  527 */           if (lts == null) {
/*  528 */             lts = new MMM_TextureBox(pn, pSearch);
/*  529 */             this.textures.add(lts);
/*  530 */             MMMLib.Debug("getTextureName-append-texturePack-%s", new Object[] { pn });
/*      */           }
/*  532 */           lts.addTexture(lindex, fname);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean addTexturesZip(File file, String[] pSearch)
/*      */   {
/*  540 */     if ((file == null) || (file.isDirectory())) {
/*  541 */       return false;
/*      */     }
/*      */     try {
/*  544 */       FileInputStream fileinputstream = new FileInputStream(file);
/*  545 */       ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
/*      */       for (;;)
/*      */       {
/*  548 */         ZipEntry zipentry = zipinputstream.getNextEntry();
/*  549 */         if (zipentry == null) {
/*      */           break;
/*      */         }
/*      */         
/*  553 */         if (!zipentry.isDirectory()) {
/*  554 */           if (zipentry.getName().endsWith(".class")) {
/*  555 */             addModelClass(zipentry.getName(), pSearch);
/*      */           } else {
/*  557 */             addTextureName(zipentry.getName(), pSearch);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  562 */       zipinputstream.close();
/*  563 */       fileinputstream.close();
/*      */       
/*  565 */       return true;
/*      */     } catch (Exception exception) {
/*  567 */       MMMLib.Debug("addTextureZip-Exception.", new Object[0]); }
/*  568 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean addTexturesDir(File file, String[] pSearch)
/*      */   {
/*  621 */     if (file == null) {
/*  622 */       return false;
/*      */     }
/*      */     try
/*      */     {
/*  626 */       for (File t : file.listFiles()) {
/*  627 */         if (t.isDirectory()) {
/*  628 */           addTexturesDir(t, pSearch);
/*      */         }
/*  630 */         else if (t.getName().endsWith(".class")) {
/*  631 */           addModelClass(t.getName(), pSearch);
/*      */         } else {
/*  633 */           String s = t.getPath().replace('\\', '/');
/*  634 */           int i = s.indexOf(pSearch[1]);
/*  635 */           if (i > -1)
/*      */           {
/*  637 */             addTextureName(s.substring(i), pSearch);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  643 */       return true;
/*      */     } catch (Exception e) {
/*  645 */       MMMLib.Debug("addTextureDebug-Exception.", new Object[0]); }
/*  646 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   protected int getIndex(String name)
/*      */   {
/*  652 */     for (int i = 0; i < defNames.length; i++) {
/*  653 */       if (name.endsWith(defNames[i])) {
/*  654 */         return i;
/*      */       }
/*      */     }
/*      */     
/*  658 */     Pattern p = Pattern.compile("_([0-9a-f]+).png");
/*  659 */     Matcher m = p.matcher(name);
/*  660 */     if (m.find()) {
/*  661 */       return Integer.decode("0x" + m.group(1)).intValue();
/*      */     }
/*      */     
/*  664 */     return -1;
/*      */   }
/*      */   
/*      */   public MMM_TextureBox getNextPackege(MMM_TextureBox pNowBox, int pColor)
/*      */   {
/*  669 */     boolean f = false;
/*  670 */     MMM_TextureBox lreturn = null;
/*  671 */     for (MMM_TextureBox ltb : getTextureList()) {
/*  672 */       if (ltb.hasColor(pColor)) {
/*  673 */         if (f) {
/*  674 */           return ltb;
/*      */         }
/*  676 */         if (lreturn == null) {
/*  677 */           lreturn = ltb;
/*      */         }
/*      */       }
/*  680 */       if (ltb == pNowBox) {
/*  681 */         f = true;
/*      */       }
/*      */     }
/*  684 */     return lreturn == null ? null : lreturn;
/*      */   }
/*      */   
/*      */   public MMM_TextureBox getPrevPackege(MMM_TextureBox pNowBox, int pColor)
/*      */   {
/*  689 */     MMM_TextureBox lreturn = null;
/*  690 */     for (MMM_TextureBox ltb : getTextureList()) {
/*  691 */       if ((ltb == pNowBox) && 
/*  692 */         (lreturn != null)) {
/*      */         break;
/*      */       }
/*      */       
/*  696 */       if (ltb.hasColor(pColor)) {
/*  697 */         lreturn = ltb;
/*      */       }
/*      */     }
/*  700 */     return lreturn == null ? null : lreturn;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getTextureCount()
/*      */   {
/*  707 */     return getTextureList().size();
/*      */   }
/*      */   
/*      */   public MMM_TextureBox getNextArmorPackege(MMM_TextureBox pNowBox)
/*      */   {
/*  712 */     boolean f = false;
/*  713 */     MMM_TextureBox lreturn = null;
/*  714 */     for (MMM_TextureBox ltb : getTextureList()) {
/*  715 */       if (ltb.hasArmor()) {
/*  716 */         if (f) {
/*  717 */           return ltb;
/*      */         }
/*  719 */         if (lreturn == null) {
/*  720 */           lreturn = ltb;
/*      */         }
/*      */       }
/*  723 */       if (ltb == pNowBox) {
/*  724 */         f = true;
/*      */       }
/*      */     }
/*  727 */     return lreturn;
/*      */   }
/*      */   
/*      */   public MMM_TextureBox getPrevArmorPackege(MMM_TextureBox pNowBox)
/*      */   {
/*  732 */     MMM_TextureBox lreturn = null;
/*  733 */     for (MMM_TextureBox ltb : getTextureList()) {
/*  734 */       if ((ltb == pNowBox) && 
/*  735 */         (lreturn != null)) {
/*      */         break;
/*      */       }
/*      */       
/*  739 */       if (ltb.hasArmor()) {
/*  740 */         lreturn = ltb;
/*      */       }
/*      */     }
/*  743 */     return lreturn;
/*      */   }
/*      */   
/*      */   public String getRandomTextureString(Random pRand) {
/*  747 */     return getRandomTexture(pRand).textureName;
/*      */   }
/*      */   
/*      */   public MMM_TextureBoxServer getRandomTexture(Random pRand) {
/*  751 */     if (this.textureServer.isEmpty()) {
/*  752 */       return null;
/*      */     }
/*      */     
/*  755 */     List<MMM_TextureBoxServer> llist = new ArrayList();
/*  756 */     for (MMM_TextureBoxServer lbox : this.textureServer) {
/*  757 */       if (lbox.getWildColorBits() > 0) {
/*  758 */         llist.add(lbox);
/*      */       }
/*      */     }
/*  761 */     return (MMM_TextureBoxServer)llist.get(pRand.nextInt(llist.size()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getIndexTextureBoxServer(ITextureEntity pEntity, String pPackName)
/*      */   {
/*  773 */     for (int li = 0; li < this.textureServer.size(); li++) {
/*  774 */       if (((MMM_TextureBoxServer)this.textureServer.get(li)).textureName.equals(pPackName)) {
/*  775 */         return li;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  780 */     MMM_TextureBox lbox = getDefaultTexture(pEntity);
/*  781 */     if (lbox != null) {
/*  782 */       pPackName = lbox.textureName;
/*  783 */       for (int li = 0; li < this.textureServer.size(); li++) {
/*  784 */         if (((MMM_TextureBoxServer)this.textureServer.get(li)).textureName.equals(pPackName)) {
/*  785 */           return li;
/*      */         }
/*      */       }
/*      */     }
/*  789 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getIndexTextureBoxServerIndex(MMM_TextureBox pBox)
/*      */   {
/*  798 */     return ((Integer)this.textureServerIndex.get(pBox)).intValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  805 */   public void setDefaultTexture(ITextureEntity pEntity, MMM_TextureBox pBox) { setDefaultTexture(pEntity.getClass(), pBox); }
/*      */   
/*      */   public void setDefaultTexture(Class pEntityClass, MMM_TextureBox pBox) {
/*  808 */     this.defaultTextures.put(pEntityClass, pBox);
/*  809 */     MMMLib.Debug("appendDefaultTexture:%s(%s)", new Object[] { pEntityClass.getSimpleName(), pBox == null ? "NULL" : pBox.textureName });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  817 */   public MMM_TextureBox getDefaultTexture(ITextureEntity pEntity) { return getDefaultTexture(pEntity.getClass()); }
/*      */   
/*      */   public MMM_TextureBox getDefaultTexture(Class pEntityClass) {
/*  820 */     if (this.defaultTextures.containsKey(pEntityClass)) {
/*  821 */       return (MMM_TextureBox)this.defaultTextures.get(pEntityClass);
/*      */     }
/*  823 */     Class lsuper = pEntityClass.getSuperclass();
/*  824 */     if (lsuper != null) {
/*  825 */       MMM_TextureBox lbox = getDefaultTexture(lsuper);
/*  826 */       if (lbox != null) {
/*  827 */         setDefaultTexture(pEntityClass, lbox);
/*      */       }
/*  829 */       return lbox;
/*      */     }
/*  831 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int getRequestStringIndex(String pVal)
/*      */   {
/*  843 */     int lblank = -1;
/*  844 */     for (int li = 0; li < this.requestString.length; li++) {
/*  845 */       if (this.requestString[li] == null) {
/*  846 */         lblank = li;
/*  847 */         this.requestStringCounter[li] = 0;
/*  848 */       } else if (this.requestString[li].equals(pVal))
/*      */       {
/*  850 */         return -2;
/*      */       }
/*      */     }
/*  853 */     if (lblank >= 0) {
/*  854 */       this.requestString[lblank] = pVal;
/*      */     } else {
/*  856 */       MMMLib.Debug("requestString Overflow!", new Object[0]);
/*      */     }
/*  858 */     return lblank;
/*      */   }
/*      */   
/*      */   protected String getRequestString(int pIndex) {
/*  862 */     String ls = this.requestString[pIndex];
/*  863 */     this.requestString[pIndex] = null;
/*  864 */     return ls;
/*      */   }
/*      */   
/*      */   protected int getRequestIndex(int pTextureServerBoxIndex) {
/*  868 */     int lblank = -1;
/*  869 */     for (int li = 0; li < this.requestIndex.length; li++) {
/*  870 */       if (this.requestIndex[li] == -1) {
/*  871 */         lblank = li;
/*  872 */         this.requestIndexCounter[li] = 0;
/*  873 */       } else if (this.requestIndex[li] == pTextureServerBoxIndex)
/*      */       {
/*  875 */         return -2;
/*      */       }
/*      */     }
/*  878 */     if (lblank >= 0) {
/*  879 */       this.requestIndex[lblank] = pTextureServerBoxIndex;
/*      */     } else {
/*  881 */       MMMLib.Debug("requestIndex Overflow!", new Object[0]);
/*      */     }
/*  883 */     return lblank;
/*      */   }
/*      */   
/*      */   protected boolean clearRequestIndex(int pTextureServerBoxIndex) {
/*  887 */     for (int li = 0; li < this.requestIndex.length; li++) {
/*  888 */       if (this.requestIndex[li] == pTextureServerBoxIndex)
/*      */       {
/*  890 */         this.requestIndex[li] = -1;
/*  891 */         return true;
/*      */       }
/*      */     }
/*  894 */     return false;
/*      */   }
/*      */   
/*      */   public MMM_TextureBox getTextureBoxServerIndex(int pIndex)
/*      */   {
/*  899 */     for (Map.Entry<MMM_TextureBox, Integer> le : this.textureServerIndex.entrySet()) {
/*  900 */       if (((Integer)le.getValue()).intValue() == pIndex) {
/*  901 */         return (MMM_TextureBox)le.getKey();
/*      */       }
/*      */     }
/*  904 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void postSetTexturePack(ITextureEntity pEntity, int pColor, MMM_TextureBoxBase[] pBox)
/*      */   {
/*  915 */     if (!(pEntity instanceof Entity)) { return;
/*      */     }
/*  917 */     int[] lindex = new int[pBox.length];
/*  918 */     boolean lflag = true;
/*      */     
/*      */ 
/*  921 */     for (int li = 0; li < pBox.length; li++) {
/*  922 */       lindex[li] = checkTextureBoxServer((MMM_TextureBox)pBox[li]);
/*  923 */       if (lindex[li] < 0) {
/*  924 */         lflag = false;
/*      */       }
/*      */     }
/*      */     
/*  928 */     if (lflag)
/*      */     {
/*  930 */       sendToServerSetTexturePackIndex(pEntity, pColor, lindex);
/*      */     }
/*      */     else {
/*  933 */       Object[] lo = new Object[1 + pBox.length];
/*  934 */       lo[0] = Integer.valueOf(pColor);
/*  935 */       for (int li = 0; li < pBox.length; li++) {
/*  936 */         lo[(li + 1)] = pBox[li];
/*      */       }
/*  938 */       this.stackSetTexturePack.put(pEntity, lo);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int checkTextureBoxServer(MMM_TextureBox pBox)
/*      */   {
/*  949 */     if (this.textureServerIndex.containsKey(pBox)) {
/*  950 */       return ((Integer)this.textureServerIndex.get(pBox)).intValue();
/*      */     }
/*  952 */     int ll = getRequestStringIndex(pBox.textureName);
/*  953 */     if (ll > -1) {
/*  954 */       sendToServerGetTextureIndex(ll, pBox);
/*  955 */       return -1;
/*      */     }
/*  957 */     return ll;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void sendToServerSetTexturePackIndex(ITextureEntity pEntity, int pColor, int[] pIndex)
/*      */   {
/*  965 */     if ((pEntity instanceof Entity)) {
/*  966 */       byte[] ldata = new byte[6 + pIndex.length * 2];
/*  967 */       ldata[0] = Byte.MIN_VALUE;
/*  968 */       MMM_Helper.setInt(ldata, 1, ((Entity)pEntity).getEntityId());
/*  969 */       ldata[5] = ((byte)pColor);
/*  970 */       int li = 6;
/*  971 */       for (int ll : pIndex) {
/*  972 */         MMM_Helper.setShort(ldata, li, ll);
/*  973 */         li += 2;
/*      */       }
/*  975 */       Client.sendToServer(ldata);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void reciveFromClientSetTexturePackIndex(Entity pEntity, byte[] pData)
/*      */   {
/*  981 */     if ((pEntity instanceof ITextureEntity))
/*      */     {
/*  983 */       int lcount = (pData.length - 6) / 2;
/*  984 */       if (lcount < 1) return;
/*  985 */       int[] lindex = new int[lcount];
/*      */       
/*  987 */       for (int li = 0; li < lcount; li++) {
/*  988 */         lindex[li] = MMM_Helper.getShort(pData, 6 + li * 2);
/*      */       }
/*  990 */       MMMLib.Debug("reciveFromClientSetTexturePackIndex: %d, %4x", new Object[] { Byte.valueOf(pData[5]), Integer.valueOf(lindex[0]) });
/*  991 */       ((ITextureEntity)pEntity).setTexturePackIndex(pData[5], lindex);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void sendToServerGetTextureIndex(int pBufIndex, MMM_TextureBox pBox)
/*      */   {
/* 1000 */     byte[] ldata = new byte[22 + pBox.textureName.length()];
/* 1001 */     ldata[0] = 1;
/* 1002 */     ldata[1] = ((byte)pBufIndex);
/* 1003 */     MMM_Helper.setShort(ldata, 2, pBox.getContractColorBits());
/* 1004 */     MMM_Helper.setShort(ldata, 4, pBox.getWildColorBits());
/* 1005 */     MMM_Helper.setFloat(ldata, 6, pBox.getHeight(null));
/* 1006 */     MMM_Helper.setFloat(ldata, 10, pBox.getWidth(null));
/* 1007 */     MMM_Helper.setFloat(ldata, 14, pBox.getYOffset(null));
/* 1008 */     MMM_Helper.setFloat(ldata, 18, pBox.getMountedYOffset(null));
/* 1009 */     MMM_Helper.setStr(ldata, 22, pBox.textureName);
/* 1010 */     Client.sendToServer(ldata);
/* 1011 */     MMMLib.Debug("Server_GetTextureIndex: %s", new Object[] { pBox.textureName });
/*      */   }
/*      */   
/*      */ 
/*      */   protected void reciveFromClientGetTexturePackIndex(EntityPlayer player, byte[] pData)
/*      */   {
/* 1017 */     String lpackname = MMM_Helper.getStr(pData, 22);
/* 1018 */     MMM_TextureBoxServer lboxsrv = getTextureBoxServer(lpackname);
/*      */     int li;
/* 1020 */     if (lboxsrv == null) {
/* 1021 */       int li = this.textureServer.size();
/* 1022 */       lboxsrv = new MMM_TextureBoxServer();
/* 1023 */       this.textureServer.add(lboxsrv);
/*      */     } else {
/* 1025 */       li = this.textureServer.indexOf(lboxsrv);
/*      */     }
/* 1027 */     lboxsrv.setValue(pData);
/*      */     
/* 1029 */     byte[] ldata = new byte[4];
/* 1030 */     ldata[0] = 1;
/* 1031 */     ldata[1] = pData[1];
/* 1032 */     MMM_Helper.setShort(ldata, 2, li);
/* 1033 */     MMMLib.Debug("reciveFromClientGetTexturePackIndex: %s, %04x", new Object[] { lpackname, Integer.valueOf(li) });
/* 1034 */     MMMLib.sendToClient(player, ldata);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void reciveFormServerSetTexturePackIndex(byte[] pData)
/*      */   {
/* 1040 */     MMM_TextureBox lbox = getTextureBox(getRequestString(pData[1]));
/* 1041 */     this.textureServerIndex.put(lbox, Integer.valueOf(MMM_Helper.getShort(pData, 2)));
/* 1042 */     MMMLib.Debug("reciveFormServerSetTexturePackIndex: %s, %04x", new Object[] { lbox.textureName, Integer.valueOf(MMM_Helper.getShort(pData, 2)) });
/*      */     
/*      */ 
/* 1045 */     Map<ITextureEntity, Object[]> lmap = new HashMap(this.stackSetTexturePack);
/* 1046 */     this.stackSetTexturePack.clear();
/* 1047 */     for (Map.Entry<ITextureEntity, Object[]> le : lmap.entrySet()) {
/* 1048 */       Object[] lo = (Object[])le.getValue();
/* 1049 */       MMM_TextureBox[] ls = new MMM_TextureBox[((Object[])le.getValue()).length - 1];
/* 1050 */       int lc = ((Integer)lo[0]).intValue();
/* 1051 */       for (int li = 1; li < lo.length; li++) {
/* 1052 */         ls[(li - 1)] = ((MMM_TextureBox)lo[li]);
/*      */       }
/* 1054 */       postSetTexturePack((ITextureEntity)le.getKey(), lc, ls);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void postGetTexturePack(ITextureEntity pEntity, int[] pIndex)
/*      */   {
/* 1068 */     MMM_TextureBox[] lbox = new MMM_TextureBox[pIndex.length];
/* 1069 */     boolean lflag = true;
/*      */     
/*      */ 
/* 1072 */     for (int li = 0; li < pIndex.length; li++) {
/* 1073 */       lbox[li] = getTextureBoxServerIndex(pIndex[li]);
/* 1074 */       if (lbox[li] == null) {
/* 1075 */         if (getRequestIndex(pIndex[li]) > -1) {
/* 1076 */           sendToServerGetTexturePackName(pIndex[li]);
/*      */         }
/* 1078 */         lflag = false;
/*      */       }
/*      */     }
/*      */     
/* 1082 */     if (lflag)
/*      */     {
/* 1084 */       pEntity.setTexturePackName(lbox);
/*      */     }
/*      */     else {
/* 1087 */       this.stackGetTexturePack.put(pEntity, pIndex);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void sendToServerGetTexturePackName(int pIndex)
/*      */   {
/* 1094 */     if (pIndex < 0) {
/* 1095 */       MMMLib.Debug("request range out.", new Object[0]);
/* 1096 */       return;
/*      */     }
/* 1098 */     byte[] ldata = new byte[3];
/* 1099 */     ldata[0] = 2;
/* 1100 */     MMM_Helper.setShort(ldata, 1, pIndex);
/* 1101 */     Client.sendToServer(ldata);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void reciveFromClientGetTexturePackName(EntityPlayer player, byte[] pData)
/*      */   {
/* 1107 */     int lindex = MMM_Helper.getShort(pData, 1);
/* 1108 */     MMM_TextureBoxServer lboxserver = getTextureBoxServer(lindex);
/*      */     
/*      */ 
/* 1111 */     byte[] ldata = new byte[23 + lboxserver.textureName.length()];
/* 1112 */     ldata[0] = 2;
/* 1113 */     MMM_Helper.setShort(ldata, 1, lindex);
/* 1114 */     MMM_Helper.setShort(ldata, 3, lboxserver.getContractColorBits());
/* 1115 */     MMM_Helper.setShort(ldata, 5, lboxserver.getWildColorBits());
/* 1116 */     MMM_Helper.setFloat(ldata, 7, lboxserver.getHeight(null));
/* 1117 */     MMM_Helper.setFloat(ldata, 11, lboxserver.getWidth(null));
/* 1118 */     MMM_Helper.setFloat(ldata, 15, lboxserver.getYOffset(null));
/* 1119 */     MMM_Helper.setFloat(ldata, 19, lboxserver.getMountedYOffset(null));
/* 1120 */     MMM_Helper.setStr(ldata, 23, lboxserver.textureName);
/* 1121 */     MMMLib.sendToClient(player, ldata);
/* 1122 */     MMMLib.Debug("SetTexturePackName:%04x - %s", new Object[] { Integer.valueOf(lindex), lboxserver.textureName });
/*      */   }
/*      */   
/*      */ 
/*      */   protected void reciveFromServerSetTexturePackName(byte[] pData)
/*      */   {
/* 1128 */     String lpackname = MMM_Helper.getStr(pData, 23);
/* 1129 */     MMM_TextureBox lbox = getTextureBox(lpackname);
/* 1130 */     if (lbox == null)
/*      */     {
/*      */ 
/* 1133 */       lbox = getTextureBox("default_Orign").duplicate();
/* 1134 */       lbox.textureName = lpackname;
/*      */       
/* 1136 */       lbox.setModelSize(MMM_Helper.getFloat(pData, 7), MMM_Helper.getFloat(pData, 11), MMM_Helper.getFloat(pData, 15), MMM_Helper.getFloat(pData, 19));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1141 */       this.textures.add(lbox);
/*      */     }
/* 1143 */     int lindex = MMM_Helper.getShort(pData, 1);
/* 1144 */     this.textureServerIndex.put(lbox, Integer.valueOf(lindex));
/* 1145 */     clearRequestIndex(lindex);
/*      */     
/*      */ 
/* 1148 */     Map<ITextureEntity, int[]> lmap = new HashMap(this.stackGetTexturePack);
/* 1149 */     this.stackGetTexturePack.clear();
/* 1150 */     for (Map.Entry<ITextureEntity, int[]> le : lmap.entrySet()) {
/* 1151 */       postGetTexturePack((ITextureEntity)le.getKey(), (int[])le.getValue());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void onUpdate()
/*      */   {
/* 1159 */     for (int li = 0; li < this.requestString.length; li++)
/*      */     {
/* 1161 */       if (this.requestString[li] != null) { int tmp25_24 = li; int[] tmp25_21 = this.requestStringCounter; int tmp27_26 = tmp25_21[tmp25_24];tmp25_21[tmp25_24] = (tmp27_26 + 1); if (tmp27_26 > 600) {
/* 1162 */           this.requestString[li] = null;
/* 1163 */           this.requestStringCounter[li] = 0;
/*      */         } }
/* 1165 */       if (this.requestIndex[li] != -1) { int tmp66_65 = li; int[] tmp66_62 = this.requestIndexCounter; int tmp68_67 = tmp66_62[tmp66_65];tmp66_62[tmp66_65] = (tmp68_67 + 1); if (tmp68_67 > 600) {
/* 1166 */           this.requestIndex[li] = -1;
/* 1167 */           this.requestIndexCounter[li] = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_TextureManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
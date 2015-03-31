/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import mmmlibx.lib.FileManager;
/*     */ import mmmlibx.lib.MMMLib;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class LMM_SoundManager
/*     */ {
/*  32 */   private static File soundDir = null;
/*     */   
/*  34 */   private static File soundPackDir = null;
/*  35 */   private static Map<String, InputStream> soundStreamMap = new HashMap();
/*     */   
/*     */ 
/*     */   public static final String SoundConfigName = "littleMaidMob.cfg";
/*     */   
/*  40 */   public static Map<Integer, String> soundsDefault = new HashMap();
/*     */   
/*  42 */   public static Map<Integer, Map<String, Map<Integer, String>>> soundsTexture = new HashMap();
/*     */   public static float soundRateDefault;
/*  44 */   public static Map<String, Map<Integer, Float>> soundRateTexture = new HashMap();
/*     */   
/*     */ 
/*     */   public static void init()
/*     */   {
/*  49 */     soundDir = new File(FileManager.dirMods, "/littleMaidMobX/");
/*  50 */     if ((!getSoundDir().exists()) || (!getSoundDir().isDirectory())) {
/*  51 */       getSoundDir().mkdirs();
/*  52 */       LMM_LittleMaidMobX.Debug("Create SoundDir: %s", new Object[] { getSoundDir().toString() });
/*     */     } else {
/*  54 */       LMM_LittleMaidMobX.Debug("SoundDir: %s", new Object[] { getSoundDir().toString() });
/*     */     }
/*     */   }
/*     */   
/*     */   public static File getSoundDir()
/*     */   {
/*  60 */     return soundDir;
/*     */   }
/*     */   
/*     */ 
/*     */   public static InputStream getSoundJson()
/*     */   {
/*     */     try
/*     */     {
/*  68 */       return new FileInputStream(new File(getSoundDir(), "sounds.json"));
/*     */     }
/*     */     catch (FileNotFoundException e) {}
/*     */     
/*  72 */     return null;
/*     */   }
/*     */   
/*     */   public static InputStream getResourceStream(ResourceLocation resource)
/*     */   {
/*  77 */     String path = resource.getResourcePath().toLowerCase();
/*     */     
/*     */ 
/*  80 */     if (path.endsWith(".mcmeta"))
/*     */     {
/*  82 */       path = path.substring(0, path.length() - 7);
/*     */     }
/*     */     
/*  85 */     if (path.equalsIgnoreCase("sounds.json"))
/*     */     {
/*  87 */       return getSoundJson();
/*     */     }
/*     */     
/*  90 */     String fileName = path;
/*  91 */     int c = fileName.lastIndexOf('/');
/*  92 */     if (c >= 0)
/*     */     {
/*  94 */       fileName = fileName.substring(c + 1);
/*     */     }
/*     */     
/*  97 */     if ((soundStreamMap.size() > 0) && (fileName.endsWith(".ogg")))
/*     */     {
/*  99 */       return (InputStream)soundStreamMap.get(fileName);
/*     */     }
/*     */     
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public static void setSoundRate(int soundindex, String value, String target)
/*     */   {
/* 107 */     String[] arg = value.split(",");
/*     */     
/*     */ 
/* 110 */     if (target == null) {
/* 111 */       target = "";
/*     */     } else {
/* 113 */       target = target.trim();
/*     */     }
/*     */     
/* 116 */     for (String s : arg)
/* 117 */       if (s.indexOf(';') == -1)
/*     */       {
/* 119 */         s = s.trim();
/* 120 */         float lf = s.isEmpty() ? 1.0F : Float.valueOf(s).floatValue();
/* 121 */         if (target.isEmpty()) {
/* 122 */           soundRateDefault = lf;
/*     */         } else {
/* 124 */           Map<Integer, Float> mif = (Map)soundRateTexture.get(target);
/* 125 */           if (mif == null) {
/* 126 */             mif = new HashMap();
/* 127 */             soundRateTexture.put(target.trim(), mif);
/*     */           }
/* 129 */           mif.put(Integer.valueOf(-1), Float.valueOf(lf));
/*     */         }
/*     */       }
/*     */       else {
/* 133 */         String[] ss = s.trim().split(";");
/*     */         
/* 135 */         if (ss.length >= 2) { String[] ls;
/* 136 */           String[] ls; if (target.isEmpty()) { String[] ls;
/* 137 */             if (ss.length > 2) {
/* 138 */               ss[0] = ss[0].trim();
/* 139 */               ls = new String[] { ss[0].isEmpty() ? ";" : ss[0], ss[1].trim(), ss[2].trim() };
/*     */             } else {
/* 141 */               ls = new String[] { ";", ss[0].trim(), ss[1].trim() };
/*     */             }
/*     */           } else { String[] ls;
/* 144 */             if (ss.length > 2) {
/* 145 */               ls = new String[] { target, ss[1].trim(), ss[2].trim() };
/*     */             } else {
/* 147 */               ls = new String[] { target, ss[0].trim(), ss[1].trim() };
/*     */             }
/*     */           }
/*     */           
/* 151 */           int li = ls[1].isEmpty() ? -1 : Integer.valueOf(ls[1]).intValue();
/* 152 */           float lf = ls[2].isEmpty() ? 1.0F : Float.valueOf(ls[2]).floatValue();
/* 153 */           Map<Integer, Float> mif = (Map)soundRateTexture.get(ls[0]);
/* 154 */           if (mif == null) {
/* 155 */             mif = new HashMap();
/* 156 */             soundRateTexture.put(ls[0], mif);
/*     */           }
/* 158 */           mif.put(Integer.valueOf(li), Float.valueOf(lf));
/*     */         }
/*     */       }
/*     */   }
/*     */   
/*     */   public static float getSoundRate(String texturename, int colorvalue) {
/* 164 */     if ((texturename == null) || (texturename.length() == 0)) texturename = ";";
/* 165 */     Map<Integer, Float> mif = (Map)soundRateTexture.get(texturename);
/* 166 */     if (mif == null)
/*     */     {
/* 168 */       mif = (Map)soundRateTexture.get(";");
/* 169 */       if (mif == null) {
/* 170 */         return soundRateDefault;
/*     */       }
/*     */     }
/* 173 */     Float lf = (Float)mif.get(Integer.valueOf(colorvalue));
/* 174 */     if (lf == null) {
/* 175 */       lf = (Float)mif.get(Integer.valueOf(-1));
/* 176 */       if (lf == null) {
/* 177 */         return soundRateDefault;
/*     */       }
/*     */     }
/* 180 */     return lf.floatValue();
/*     */   }
/*     */   
/*     */   public static void setSoundValue(int soundindex, String value, String target)
/*     */   {
/* 185 */     String[] arg = value.split(",");
/*     */     
/* 187 */     for (String s : arg) { String tvalue;
/*     */       String tvalue;
/* 189 */       if (s.indexOf(';') == -1) {
/*     */         String tvalue;
/* 191 */         if ((target == null) || (target.isEmpty())) {
/* 192 */           tvalue = value;
/*     */         } else {
/* 194 */           tvalue = target + ";-1;" + value;
/*     */         }
/*     */       }
/*     */       else {
/* 198 */         String[] ss = s.trim().split(";");
/* 199 */         String tvalue; if (ss.length == 2) {
/* 200 */           tvalue = target + ";" + value;
/*     */         } else {
/* 202 */           tvalue = value;
/*     */         }
/*     */       }
/* 205 */       setSoundValue(soundindex, tvalue);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setSoundValue(int soundindex, String value)
/*     */   {
/* 211 */     String[] arg = value.split(",");
/*     */     
/* 213 */     for (String s : arg) {
/* 214 */       if (s.indexOf(';') == -1)
/*     */       {
/* 216 */         soundsDefault.put(Integer.valueOf(soundindex), s.trim());
/*     */       }
/*     */       else {
/* 219 */         Map<String, Map<Integer, String>> msi = (Map)soundsTexture.get(Integer.valueOf(soundindex));
/* 220 */         if (msi == null) {
/* 221 */           msi = new HashMap();
/* 222 */           soundsTexture.put(Integer.valueOf(soundindex), msi);
/*     */         }
/* 224 */         String[] ss = s.trim().split(";");
/* 225 */         if (ss.length >= 2) {
/* 226 */           if (ss[0].length() == 0) ss[0] = ";";
/* 227 */           Map<Integer, String> mst = (Map)msi.get(ss[0]);
/* 228 */           if (mst == null) {
/* 229 */             mst = new HashMap();
/* 230 */             msi.put(ss[0], mst);
/*     */           }
/* 232 */           ss[1] = ss[1].trim();
/* 233 */           int i = ss[1].length() == 0 ? -1 : Integer.valueOf(ss[1]).intValue();
/* 234 */           if (ss.length < 3) {
/* 235 */             mst.put(Integer.valueOf(i), "");
/*     */           } else
/* 237 */             mst.put(Integer.valueOf(i), ss[2].trim());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getSoundValue(LMM_EnumSound enumsound, String texturename, int colorvalue) {
/* 244 */     if (enumsound == LMM_EnumSound.Null) { return null;
/*     */     }
/* 246 */     Map<String, Map<Integer, String>> msi = (Map)soundsTexture.get(Integer.valueOf(enumsound.index));
/* 247 */     if (msi == null) {
/* 248 */       return (String)soundsDefault.get(Integer.valueOf(enumsound.index));
/*     */     }
/*     */     
/* 251 */     if ((texturename == null) || (texturename.length() == 0)) texturename = ";";
/* 252 */     Map<Integer, String> mst = (Map)msi.get(texturename);
/* 253 */     if (mst == null)
/*     */     {
/* 255 */       mst = (Map)msi.get(";");
/* 256 */       if (mst == null) {
/* 257 */         return (String)soundsDefault.get(Integer.valueOf(enumsound.index));
/*     */       }
/*     */     }
/* 260 */     String s = (String)mst.get(Integer.valueOf(colorvalue));
/* 261 */     if (s == null) {
/* 262 */       s = (String)mst.get(Integer.valueOf(-1));
/* 263 */       if (s == null) {
/* 264 */         return (String)soundsDefault.get(Integer.valueOf(enumsound.index));
/*     */       }
/*     */     }
/* 267 */     return "lmmx:" + s;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void rebuildSoundPack()
/*     */   {
/* 273 */     Map<Integer, String> lmap = new HashMap();
/* 274 */     lmap.putAll(soundsDefault);
/* 275 */     for (Map.Entry<Integer, String> lt : soundsDefault.entrySet()) {
/* 276 */       int li = ((Integer)lt.getKey()).intValue();
/* 277 */       if (((String)lt.getValue()).equals("^")) {
/* 278 */         String ls = (String)lmap.get(Integer.valueOf(li & 0xFFFFFFF0));
/* 279 */         if ((ls != null) && ((li & 0xF) != 0) && (!ls.equals("^"))) {
/* 280 */           lmap.put(Integer.valueOf(li), ls);
/*     */           
/* 282 */           LMM_LittleMaidMobX.Debug(String.format("soundsDefault[%d] = [%d]", new Object[] { Integer.valueOf(li), Integer.valueOf(li & 0xFFFFFFF0) }), new Object[0]);
/*     */         }
/*     */         else {
/* 285 */           LMM_LittleMaidMobX.Debug(String.format("soundsDefault[%d] removed.", new Object[] { Integer.valueOf(li) }), new Object[0]);
/*     */         }
/*     */       } else {
/* 288 */         lmap.put(Integer.valueOf(li), lt.getValue());
/*     */       }
/*     */     }
/* 291 */     soundsDefault = lmap;
/*     */     
/*     */ 
/* 294 */     for (Iterator i$ = soundsTexture.entrySet().iterator(); i$.hasNext();) { mim = (Map.Entry)i$.next();
/* 295 */       for (i$ = ((Map)mim.getValue()).entrySet().iterator(); i$.hasNext();) { msm = (Map.Entry)i$.next();
/*     */         
/* 297 */         for (Map.Entry<Integer, String> mis : ((Map)msm.getValue()).entrySet())
/* 298 */           if (((String)mis.getValue()).equals("^")) {
/* 299 */             boolean lf = false;
/* 300 */             if ((((Integer)mim.getKey()).intValue() & 0xF) != 0) {
/* 301 */               Map<String, Map<Integer, String>> lmsm = (Map)soundsTexture.get(Integer.valueOf(((Integer)mim.getKey()).intValue() & 0xFFFFFFF0));
/* 302 */               if (lmsm != null) {
/* 303 */                 Map<Integer, String> lmis = (Map)lmsm.get(msm.getKey());
/* 304 */                 if (lmis != null) {
/* 305 */                   String ls = (String)lmis.get(mis.getKey());
/* 306 */                   if ((ls != null) && (!ls.equals("^"))) {
/* 307 */                     ((Map)msm.getValue()).put(mis.getKey(), ls);
/* 308 */                     lf = true;
/* 309 */                     LMM_LittleMaidMobX.Debug(String.format("soundsTexture[%d, %s, %d] = [%d]", new Object[] { mim.getKey(), msm.getKey(), mis.getKey(), Integer.valueOf(((Integer)mim.getKey()).intValue() & 0xFFFFFFF0) }), new Object[0]);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/* 314 */             if (!lf) {
/* 315 */               ((Map)msm.getValue()).remove(mis.getKey());
/* 316 */               LMM_LittleMaidMobX.Debug(String.format("soundsTexture[%d, %s, %d] removed.", new Object[] { mim.getKey(), msm.getKey(), mis.getKey() }), new Object[0]);
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/*     */     Map.Entry<Integer, Map<String, Map<Integer, String>>> mim;
/*     */     Iterator i$;
/*     */     Map.Entry<String, Map<Integer, String>> msm;
/*     */   }
/*     */   
/*     */   public static void decodeSoundPack(String fileName, Reader reader, boolean iswrite, boolean isdefault) {
/* 327 */     try { List<LMM_EnumSound> list1 = new ArrayList();
/* 328 */       list1.addAll(Arrays.asList(LMM_EnumSound.values()));
/* 329 */       list1.remove(LMM_EnumSound.Null);
/* 330 */       BufferedReader breader = new BufferedReader(reader);
/* 331 */       boolean loadsoundrate = false;
/*     */       
/* 333 */       String packname = fileName;
/* 334 */       packname = packname.substring(0, packname.lastIndexOf("."));
/* 335 */       String str; while ((str = breader.readLine()) != null) {
/* 336 */         str = str.trim();
/* 337 */         if ((!str.isEmpty()) && (!str.startsWith("#"))) {
/* 338 */           int i = str.indexOf('=');
/* 339 */           if (i > -1) {
/* 340 */             String name = str.substring(0, i).trim();
/* 341 */             String value = str.substring(i + 1).trim();
/*     */             
/* 343 */             int index = -1;
/* 344 */             if (name.startsWith("se_"))
/*     */             {
/*     */ 
/* 347 */               int cd = value.lastIndexOf('.');
/* 348 */               if (cd >= 0) { value = value.substring(cd + 1);
/*     */               }
/*     */               
/* 351 */               value = value.replaceAll("\\d+$", "");
/*     */               
/* 353 */               String ss = name.substring(3);
/*     */               try {
/* 355 */                 index = LMM_EnumSound.valueOf(ss).index;
/* 356 */                 list1.remove(LMM_EnumSound.valueOf(ss));
/*     */               }
/*     */               catch (Exception exception) {
/* 359 */                 LMM_LittleMaidMobX.Debug(String.format("unknown sound parameter:%s.cfg - %s", new Object[] { packname, ss }), new Object[0]);
/*     */               }
/* 361 */             } else if (name.equals("LivingVoiceRate")) {
/* 362 */               if (isdefault) {
/* 363 */                 setSoundRate(index, value, null);
/*     */               } else {
/* 365 */                 setSoundRate(index, value, packname);
/*     */               }
/* 367 */               loadsoundrate = true;
/*     */             }
/* 369 */             if (index > -1) {
/* 370 */               if (isdefault) {
/* 371 */                 setSoundValue(index, value);
/*     */               } else {
/* 373 */                 setSoundValue(index, value, packname);
/*     */               }
/* 375 */               LMM_LittleMaidMobX.Debug(String.format("%s(%d) = %s", new Object[] { name, Integer.valueOf(index), value }), new Object[0]);
/*     */             }
/*     */           }
/*     */         } }
/* 379 */       breader.close();
/*     */       
/*     */ 
/* 382 */       if (iswrite)
/*     */       {
/*     */ 
/* 385 */         if (!list1.isEmpty()) {
/* 386 */           BufferedWriter bwriter = new BufferedWriter(new FileWriter(fileName, true));
/* 387 */           for (int i = 0; i < list1.size(); i++) {
/* 388 */             writeBuffer(bwriter, (LMM_EnumSound)list1.get(i));
/*     */           }
/* 390 */           bwriter.close();
/*     */         }
/* 392 */         if (!loadsoundrate) {
/* 393 */           BufferedWriter bwriter = new BufferedWriter(new FileWriter(fileName, true));
/* 394 */           writeBufferSoundRate(bwriter, 1.0F);
/* 395 */           bwriter.close();
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception exception) {
/* 400 */       LMM_LittleMaidMobX.Debug("decodeSound Exception.", new Object[0]);
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
/*     */   public static void loadSoundPack() {}
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
/*     */   public static void loadDefaultSoundPack()
/*     */   {
/*     */     try
/*     */     {
/* 429 */       boolean loadCfg = loadSoundPackCfg();
/*     */       
/* 431 */       if (!loadCfg)
/*     */       {
/* 433 */         File soundCfg = new File(getSoundDir(), "default_littleMaidMob.cfg");
/* 434 */         soundPackDir = null;
/*     */         
/* 436 */         if ((soundCfg.exists()) && (soundCfg.isFile()))
/*     */         {
/* 438 */           LMM_LittleMaidMobX.Debug(soundCfg.getName(), new Object[0]);
/*     */           
/* 440 */           Reader reader = new FileReader(soundCfg);
/* 441 */           decodeSoundPack(soundCfg.getName(), reader, true, true);
/* 442 */           reader.close();
/*     */         }
/*     */         else
/*     */         {
/* 446 */           LMM_LittleMaidMobX.Debug("no Default Sound cfg.", new Object[0]);
/* 447 */           createDefaultSoundPack(soundCfg);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 453 */       LMM_LittleMaidMobX.Debug("Error: Create Sound cfg failed.", new Object[0]);
/* 454 */       e.printStackTrace();
/*     */     }
/* 456 */     rebuildSoundPack();
/*     */   }
/*     */   
/*     */   public static boolean loadSoundPackCfg()
/*     */     throws IOException
/*     */   {
/* 462 */     for (File file : FileManager.dirMods.listFiles())
/*     */     {
/* 464 */       if (file.isDirectory())
/*     */       {
/* 466 */         if (searchSoundCfgDir(file))
/*     */         {
/* 468 */           soundPackDir = file;
/* 469 */           putAllSoundStream(file);
/* 470 */           createSoundJson(file);
/* 471 */           return true;
/*     */         }
/*     */       }
/* 474 */       else if (file.getName().toLowerCase().endsWith(".zip"))
/*     */       {
/* 476 */         if (searchSoundCfgZip(file))
/*     */         {
/* 478 */           soundPackDir = file;
/* 479 */           createSoundJson(file);
/* 480 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 484 */     return false;
/*     */   }
/*     */   
/*     */   public static void putAllSoundStream(File dir) throws IOException
/*     */   {
/* 489 */     for (File file : dir.listFiles())
/*     */     {
/* 491 */       String name = file.getName().toLowerCase();
/* 492 */       if (file.isDirectory())
/*     */       {
/* 494 */         putAllSoundStream(file);
/*     */       }
/* 496 */       else if (name.endsWith(".ogg"))
/*     */       {
/* 498 */         soundStreamMap.put(name, new FileInputStream(file));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean searchSoundCfgDir(File dir)
/*     */     throws IOException
/*     */   {
/* 507 */     for (File file : dir.listFiles())
/*     */     {
/* 509 */       if (file.isDirectory())
/*     */       {
/* 511 */         if (searchSoundCfgDir(file))
/*     */         {
/* 513 */           return true;
/*     */         }
/*     */       }
/* 516 */       else if (file.getName().equalsIgnoreCase("littleMaidMob.cfg"))
/*     */       {
/* 518 */         Reader reader = new FileReader(file);
/*     */         
/* 520 */         decodeSoundPack(file.getName(), reader, false, true);
/*     */         
/* 522 */         reader.close();
/*     */         
/* 524 */         return true;
/*     */       }
/*     */     }
/* 527 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean searchSoundCfgZip(File file)
/*     */   {
/* 534 */     boolean foundCfg = false;
/*     */     try
/*     */     {
/* 537 */       FileInputStream fileinputstream = new FileInputStream(file);
/* 538 */       ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
/*     */       ZipEntry zipentry;
/*     */       for (;;)
/*     */       {
/* 542 */         zipentry = zipinputstream.getNextEntry();
/* 543 */         if (zipentry == null) {
/*     */           break;
/*     */         }
/*     */         
/* 547 */         if (!zipentry.isDirectory())
/*     */         {
/* 549 */           String name = zipentry.getName();
/* 550 */           int c = name.lastIndexOf("/");
/* 551 */           if (c >= 0)
/*     */           {
/* 553 */             name = name.substring(c + 1);
/*     */           }
/* 555 */           name = name.toLowerCase();
/* 556 */           if ((!foundCfg) && (name.equalsIgnoreCase("littleMaidMob.cfg")))
/*     */           {
/* 558 */             ZipFile zipFile = new ZipFile(file);
/* 559 */             InputStream inputStream = zipFile.getInputStream(zipentry);
/* 560 */             Reader reader = new InputStreamReader(inputStream);
/* 561 */             decodeSoundPack(name, reader, false, true);
/* 562 */             reader.close();
/* 563 */             inputStream.close();
/* 564 */             zipFile.close();
/*     */             
/* 566 */             foundCfg = true;
/* 567 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 573 */       zipinputstream.close();
/* 574 */       fileinputstream.close();
/*     */       
/*     */ 
/* 577 */       if (foundCfg)
/*     */       {
/* 579 */         fileinputstream = new FileInputStream(file);
/* 580 */         zipinputstream = new ZipInputStream(fileinputstream);
/*     */         for (;;)
/*     */         {
/* 583 */           zipentry = zipinputstream.getNextEntry();
/* 584 */           if (zipentry == null) {
/*     */             break;
/*     */           }
/*     */           
/* 588 */           if (!zipentry.isDirectory())
/*     */           {
/* 590 */             String name = zipentry.getName();
/* 591 */             int c = name.lastIndexOf("/");
/* 592 */             if (c >= 0)
/*     */             {
/* 594 */               name = name.substring(c + 1);
/*     */             }
/* 596 */             name = name.toLowerCase();
/* 597 */             if (name.endsWith(".ogg"))
/*     */             {
/* 599 */               soundStreamMap.put(name, new ZipFile(file).getInputStream(zipentry));
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 605 */         zipinputstream.close();
/* 606 */         fileinputstream.close();
/*     */       }
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/* 611 */       MMMLib.Debug("Load Sound pack Zip-Exception.", new Object[0]);
/*     */     }
/* 613 */     return foundCfg;
/*     */   }
/*     */   
/*     */   public static boolean createDefaultSoundPack(File file1)
/*     */   {
/* 618 */     for (LMM_EnumSound eslm : ) {
/* 619 */       if (eslm != LMM_EnumSound.Null) {
/* 620 */         setSoundValue(eslm.index, eslm.DefaultValue);
/*     */       }
/*     */     }
/*     */     
/* 624 */     if (file1.exists()) {
/* 625 */       return false;
/*     */     }
/*     */     try {
/* 628 */       if (file1.createNewFile()) {
/* 629 */         BufferedWriter bwriter = new BufferedWriter(new FileWriter(file1));
/*     */         
/* 631 */         for (LMM_EnumSound eslm : LMM_EnumSound.values()) {
/* 632 */           writeBuffer(bwriter, eslm);
/*     */         }
/*     */         
/* 635 */         writeBufferSoundRate(bwriter, 1.0F);
/*     */         
/* 637 */         bwriter.close();
/* 638 */         LMM_LittleMaidMobX.Debug("Success create Default Sound cfg.", new Object[0]);
/*     */       }
/*     */     } catch (IOException e) {
/* 641 */       LMM_LittleMaidMobX.Debug("Failed create Default Sound cfg(%s).", new Object[] { file1.getAbsolutePath() });
/* 642 */       e.printStackTrace();
/* 643 */       return false;
/*     */     }
/* 645 */     return true;
/*     */   }
/*     */   
/*     */   protected static void writeBuffer(BufferedWriter buffer, LMM_EnumSound enumsound) throws IOException
/*     */   {
/* 650 */     if (enumsound == LMM_EnumSound.Null) { return;
/*     */     }
/* 652 */     buffer.write("# ");
/* 653 */     buffer.write(enumsound.info);
/* 654 */     buffer.newLine();
/*     */     
/* 656 */     buffer.write("se_");
/* 657 */     buffer.write(enumsound.name());
/* 658 */     buffer.write("=");
/* 659 */     buffer.write(enumsound.DefaultValue);
/* 660 */     buffer.newLine();
/* 661 */     buffer.newLine();
/*     */   }
/*     */   
/*     */   protected static void writeBufferSoundRate(BufferedWriter buffer, float prate) throws IOException
/*     */   {
/* 666 */     buffer.write("# Living Voice Rate. 1.0=100%, 0.5=50%, 0.0=0%");
/* 667 */     buffer.newLine();
/* 668 */     buffer.write("LivingVoiceRate=" + prate);
/* 669 */     buffer.newLine();
/* 670 */     buffer.newLine();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void createSoundJson(File dir)
/*     */   {
/* 676 */     if ((!getSoundDir().exists()) || (!getSoundDir().isDirectory()))
/*     */     {
/* 678 */       return;
/*     */     }
/*     */     
/* 681 */     File file1 = new File(getSoundDir(), "sounds.json");
/*     */     try {
/* 683 */       BufferedWriter bwriter = new BufferedWriter(new FileWriter(file1));
/*     */       
/* 685 */       String str = searchSoundAndWriteFile("", dir, "");
/* 686 */       bwriter.write("{\n" + str + "\n}\n");
/* 687 */       bwriter.newLine();
/*     */       
/* 689 */       bwriter.close();
/* 690 */       LMM_LittleMaidMobX.Debug("Success create Sounds.json(%s).", new Object[] { file1.getAbsolutePath() });
/*     */     } catch (IOException e) {
/* 692 */       LMM_LittleMaidMobX.Debug("Failed create Sounds.json(%s).", new Object[] { file1.getAbsolutePath() });
/* 693 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private static String searchSoundAndWriteFile(String string, File dir, String string2) throws IOException
/*     */   {
/* 699 */     if (dir.isDirectory())
/*     */     {
/* 701 */       return searchSoundAndWriteFileDir(string, dir, string2);
/*     */     }
/*     */     
/*     */ 
/* 705 */     return searchSoundAndWriteFileZip(string, dir);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String searchSoundAndWriteFileDir(String output, File dir, String path)
/*     */     throws IOException
/*     */   {
/* 718 */     for (File file : dir.listFiles())
/*     */     {
/* 720 */       if (file.isDirectory())
/*     */       {
/* 722 */         output = output + searchSoundAndWriteFileDir(output, file, new StringBuilder().append(path).append(file.getName()).append(".").toString());
/*     */       }
/*     */     }
/*     */     
/* 726 */     Map<String, List<String>> map = new LinkedHashMap();
/* 727 */     for (File file : dir.listFiles())
/*     */     {
/* 729 */       if ((file.isFile()) && (file.getName().endsWith(".ogg")))
/*     */       {
/* 731 */         String fileName = file.getName().substring(0, file.getName().length() - 4);
/* 732 */         String soundName = fileName.replaceAll("\\d+$", "");
/* 733 */         String name = fileName.replace(".", "/");
/* 734 */         if (!map.containsKey(soundName))
/*     */         {
/* 736 */           map.put(soundName, new ArrayList());
/*     */         }
/* 738 */         ((List)map.get(soundName)).add(name);
/*     */       }
/*     */     }
/* 741 */     for (String key : map.keySet())
/*     */     {
/* 743 */       String s = "";
/* 744 */       for (String name : (List)map.get(key))
/*     */       {
/* 746 */         if (s.isEmpty())
/*     */         {
/* 748 */           s = "\"" + key + "\":{\"category\":\"master\",\"sounds\":[";
/*     */         }
/*     */         else
/*     */         {
/* 752 */           s = s + ",";
/*     */         }
/* 754 */         s = s + "\"" + name + "\"";
/*     */       }
/* 756 */       s = s + "]}";
/*     */       
/* 758 */       if (!output.isEmpty())
/*     */       {
/* 760 */         output = output + ",\n";
/*     */       }
/* 762 */       output = output + s;
/*     */     }
/*     */     
/* 765 */     return output;
/*     */   }
/*     */   
/*     */   public static String searchSoundAndWriteFileZip(String output, File dir) throws IOException {
/* 769 */     Map<String, List<String>> map = new LinkedHashMap();
/*     */     try
/*     */     {
/* 772 */       FileInputStream fileinputstream = new FileInputStream(dir);
/* 773 */       ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
/*     */       
/*     */       for (;;)
/*     */       {
/* 777 */         ZipEntry zipentry = zipinputstream.getNextEntry();
/* 778 */         if (zipentry == null) {
/*     */           break;
/*     */         }
/*     */         
/* 782 */         String fileNameInZip = zipentry.getName();
/* 783 */         if ((!zipentry.isDirectory()) && (fileNameInZip.endsWith(".ogg")))
/*     */         {
/* 785 */           String fileName = fileNameInZip.substring(0, fileNameInZip.length() - 4);
/* 786 */           int c = fileName.lastIndexOf('/');
/* 787 */           if (c >= 0)
/*     */           {
/* 789 */             fileName = fileName.substring(c + 1);
/*     */           }
/*     */           
/* 792 */           String soundName = fileName.replaceAll("\\d+$", "");
/* 793 */           String name = fileName.replace(".", "/");
/* 794 */           if (!map.containsKey(soundName))
/*     */           {
/* 796 */             map.put(soundName, new ArrayList());
/*     */           }
/* 798 */           ((List)map.get(soundName)).add(name);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 803 */       zipinputstream.close();
/* 804 */       fileinputstream.close();
/*     */       
/* 806 */       for (String key : map.keySet())
/*     */       {
/* 808 */         String s = "";
/* 809 */         for (String name : (List)map.get(key))
/*     */         {
/* 811 */           if (s.isEmpty())
/*     */           {
/* 813 */             s = "\"" + key + "\":{\"category\":\"master\",\"sounds\":[";
/*     */           }
/*     */           else
/*     */           {
/* 817 */             s = s + ",";
/*     */           }
/* 819 */           s = s + "\"" + name + "\"";
/*     */         }
/* 821 */         s = s + "]}";
/*     */         
/* 823 */         if (!output.isEmpty())
/*     */         {
/* 825 */           output = output + ",\n";
/*     */         }
/* 827 */         output = output + s;
/*     */       }
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/* 832 */       exception.printStackTrace();
/*     */     }
/*     */     
/* 835 */     return output;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_SoundManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
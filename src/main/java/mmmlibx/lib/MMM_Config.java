/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMM_Config
/*     */ {
/*     */   public static File configDir;
/*  23 */   public static String[] cfg_comment = { "test code", "can br ?" };
/*  24 */   public static int cfg_testi = 1;
/*  25 */   public static byte cfg_testb = 2;
/*  26 */   public static String cfg_tests = "test string";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void init()
/*     */   {
/*  33 */     configDir = new File(FileManager.minecraftDir, "config");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static File getConfigFile(Class pClass)
/*     */   {
/*  42 */     return new File(configDir, pClass.getSimpleName() + ".cfg");
/*     */   }
/*     */   
/*     */   protected static List getConfigFields(Class pClass) {
/*  46 */     List<Field> llist = new ArrayList();
/*  47 */     Field[] lfeilds = pClass.getDeclaredFields();
/*  48 */     if (lfeilds != null) {
/*  49 */       for (Field lf : lfeilds) {
/*  50 */         int li = lf.getModifiers();
/*  51 */         if ((Modifier.isStatic(li)) && (!Modifier.isPrivate(li)) && 
/*  52 */           (lf.getName().startsWith("cfg_"))) {
/*  53 */           llist.add(lf);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  59 */     return llist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void saveConfig(Class pClass)
/*     */   {
/*  67 */     File lfile = getConfigFile(pClass);
/*  68 */     List<Field> llist = getConfigFields(pClass);
/*  69 */     StringBuilder lsb = new StringBuilder();
/*  70 */     Properties lprop = new Properties();
/*     */     try
/*     */     {
/*  73 */       for (Field lf : llist) {
/*  74 */         if (lf.getName().equals("cfg_comment")) {
/*  75 */           String[] ls = (String[])lf.get(null);
/*  76 */           for (String lt : ls) {
/*  77 */             lsb.append(lt).append("\n");
/*     */           }
/*     */         } else {
/*  80 */           lprop.setProperty(lf.getName(), lf.get(null).toString());
/*     */         }
/*     */       }
/*  83 */       lprop.store(new FileOutputStream(lfile), lsb.toString());
/*     */     } catch (Exception e) {
/*  85 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void loadConfig(Class pClass) {
/*  90 */     File lfile = getConfigFile(pClass);
/*  91 */     if ((!lfile.exists()) || (!lfile.isFile())) { return;
/*     */     }
/*  93 */     List<Field> llist = getConfigFields(pClass);
/*  94 */     StringBuilder lsb = new StringBuilder();
/*  95 */     Properties lprop = new Properties();
/*     */     try
/*     */     {
/*  98 */       lprop.load(new FileInputStream(lfile));
/*  99 */       for (Field lf : llist) {
/* 100 */         if (lprop.containsKey(lf.getName())) {
/* 101 */           String ls = lprop.getProperty(lf.getName());
/* 102 */           Class lc = lf.getType();
/* 103 */           if (lc.isAssignableFrom(String.class)) {
/* 104 */             lf.set(null, ls);
/*     */           }
/* 106 */           else if (lc.isAssignableFrom(Byte.TYPE)) {
/* 107 */             lf.setByte(null, Byte.parseByte(ls));
/*     */           }
/* 109 */           else if (lc.isAssignableFrom(Short.TYPE)) {
/* 110 */             lf.setShort(null, Short.parseShort(ls));
/*     */           }
/* 112 */           else if (lc.isAssignableFrom(Integer.TYPE)) {
/* 113 */             if (ls.startsWith("0x")) {
/* 114 */               lf.setInt(null, Integer.parseInt(ls, 16));
/*     */             } else {
/* 116 */               lf.setInt(null, Integer.parseInt(ls));
/*     */             }
/*     */           }
/* 119 */           else if (lc.isAssignableFrom(Long.TYPE)) {
/* 120 */             if (ls.startsWith("0x")) {
/* 121 */               lf.setLong(null, Long.parseLong(ls, 16));
/*     */             } else {
/* 123 */               lf.setLong(null, Long.parseLong(ls));
/*     */             }
/*     */           }
/* 126 */           else if (lc.isAssignableFrom(Boolean.TYPE)) {
/* 127 */             lf.setBoolean(null, Boolean.parseBoolean(ls));
/*     */           }
/* 129 */           else if (lc.isAssignableFrom(Float.TYPE)) {
/* 130 */             lf.setFloat(null, Float.parseFloat(ls));
/*     */           }
/* 132 */           else if (lc.isAssignableFrom(Double.TYPE)) {
/* 133 */             lf.setDouble(null, Double.parseDouble(ls));
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 138 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void checkConfig(Class pClass)
/*     */   {
/* 147 */     loadConfig(pClass);
/* 148 */     saveConfig(pClass);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
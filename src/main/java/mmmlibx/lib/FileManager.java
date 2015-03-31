/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.FMLInjectionData;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FileManager
/*     */ {
/*     */   public static File dirMinecraft;
/*     */   public static File dirMods;
/*     */   public static File dirModsVersion;
/*     */   public static List<File> files;
/*  20 */   public static String minecraftDir = "";
/*     */   
/*  22 */   public static String assetsDir = "";
/*     */   public static boolean isDevdir;
/*  24 */   public static Map<String, List<File>> fileList = new HashMap();
/*     */   
/*     */   static
/*     */   {
/*  28 */     Object[] lo = FMLInjectionData.data();
/*  29 */     dirMinecraft = (File)lo[6];
/*  30 */     minecraftDir = dirMinecraft.getPath();
/*  31 */     dirMods = new File(dirMinecraft, "mods");
/*  32 */     dirModsVersion = new File(dirMods, (String)lo[4]);
/*  33 */     MMMLib.Debug("init FileManager.", new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setSrcPath(File file)
/*     */   {
/*  42 */     assetsDir = file.getPath() + "/assets";
/*     */     
/*  44 */     isDevdir = file.getName().equalsIgnoreCase("bin");
/*  45 */     if (isDevdir)
/*     */     {
/*  47 */       dirMods = new File(file.getParent() + "/eclipse/mods");
/*     */     }
/*     */     else
/*     */     {
/*  51 */       dirMods = new File(file.getParent());
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
/*     */   public static List<File> getAllmodsFiles(ClassLoader pClassLoader, boolean pFlag)
/*     */   {
/*  95 */     List<File> llist = new ArrayList();
/*  96 */     if ((pClassLoader instanceof URLClassLoader)) {
/*  97 */       for (URL lurl : ((URLClassLoader)pClassLoader).getURLs()) {
/*     */         try {
/*  99 */           String ls = lurl.toString();
/* 100 */           if ((ls.endsWith("/bin/")) || (ls.indexOf("/mods/") > -1)) {
/* 101 */             llist.add(new File(lurl.toURI()));
/*     */           }
/*     */         } catch (Exception e) {
/* 104 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 108 */     if (pFlag) {
/* 109 */       if (dirMods.exists()) {
/* 110 */         for (File lf : dirMods.listFiles()) {
/* 111 */           addList(llist, lf);
/*     */         }
/*     */       }
/* 114 */       if (dirModsVersion.exists()) {
/* 115 */         for (File lf : dirModsVersion.listFiles()) {
/* 116 */           addList(llist, lf);
/*     */         }
/*     */       }
/*     */     }
/* 120 */     files = llist;
/* 121 */     return llist;
/*     */   }
/*     */   
/*     */   protected static boolean addList(List<File> pList, File pFile) {
/* 125 */     for (File lf : pList) {
/*     */       try {
/* 127 */         if (pFile.getCanonicalPath().compareTo(lf.getCanonicalPath()) == 0) {
/* 128 */           return false;
/*     */         }
/*     */       } catch (Exception e) {
/* 131 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 134 */     pList.add(pFile);
/* 135 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static List<File> getModFile(String pname, String pprefix)
/*     */   {
/*     */     List<File> llist;
/*     */     
/*     */ 
/*     */     List<File> llist;
/*     */     
/*     */ 
/* 149 */     if (fileList.containsKey(pname)) {
/* 150 */       llist = (List)fileList.get(pname);
/*     */     } else {
/* 152 */       llist = new ArrayList();
/* 153 */       fileList.put(pname, llist);
/*     */     }
/*     */     
/* 156 */     MMMLib.Debug("getModFile:[%s]:%s", new Object[] { pname, dirMods.getAbsolutePath() });
/*     */     try
/*     */     {
/* 159 */       if (dirMods.isDirectory()) {
/* 160 */         MMMLib.Debug("getModFile-get:%d.", new Object[] { Integer.valueOf(dirMods.list().length) });
/* 161 */         for (File t : dirMods.listFiles()) {
/* 162 */           if (t.getName().indexOf(pprefix) != -1) {
/* 163 */             if ((t.getName().endsWith(".zip")) || (t.getName().endsWith(".jar"))) {
/* 164 */               llist.add(t);
/* 165 */               MMMLib.Debug("getModFile-file:%s", new Object[] { t.getName() });
/* 166 */             } else if (t.isDirectory()) {
/* 167 */               llist.add(t);
/* 168 */               MMMLib.Debug("getModFile-file:%s", new Object[] { t.getName() });
/*     */             }
/*     */           }
/*     */         }
/* 172 */         MMMLib.Debug("getModFile-files:%d", new Object[] { Integer.valueOf(llist.size()) });
/*     */       }
/*     */       else {
/* 175 */         MMMLib.Debug("getModFile-fail.", new Object[0]);
/*     */       }
/* 177 */       return llist;
/*     */     }
/*     */     catch (Exception exception) {
/* 180 */       MMMLib.Debug("getModFile-Exception.", new Object[0]); }
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   public static void debugPrintAllFileList()
/*     */   {
/* 186 */     for (Iterator i$ = fileList.keySet().iterator(); i$.hasNext();) { key = (String)i$.next();
/*     */       
/* 188 */       List<File> list = (List)fileList.get(key);
/* 189 */       for (File f : list)
/*     */       {
/* 191 */         System.out.println("MMMLib-AllFileList ### " + key + " : " + f.getPath());
/*     */       }
/*     */     }
/*     */     String key;
/*     */   }
/*     */   
/*     */   public static List<File> getFileList(String pname) {
/* 198 */     return (List)fileList.get(pname);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/FileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
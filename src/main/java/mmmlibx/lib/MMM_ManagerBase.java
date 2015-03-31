/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MMM_ManagerBase
/*     */ {
/*     */   protected abstract String getPreFix();
/*     */   
/*     */   protected abstract boolean append(Class paramClass);
/*     */   
/*     */   protected void load()
/*     */   {
/*  24 */     Package lpackage = MMMLib.class.getPackage();
/*  25 */     String ls = "";
/*  26 */     if (lpackage != null) {
/*  27 */       ls = MMMLib.class.getPackage().getName().replace('.', File.separatorChar);
/*     */     }
/*  29 */     File lf1 = new File(FileManager.dirMods, ls);
/*     */     
/*     */ 
/*  32 */     if (lf1.isDirectory())
/*     */     {
/*  34 */       decodeDirectory(lf1);
/*     */     }
/*     */     else {
/*  37 */       decodeZip(lf1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  42 */     for (Map.Entry<String, List<File>> le : FileManager.fileList.entrySet()) {
/*  43 */       for (File lf : (List)le.getValue()) {
/*  44 */         if (lf.isDirectory())
/*     */         {
/*  46 */           decodeDirectory(lf);
/*     */         }
/*     */         else {
/*  49 */           decodeZip(lf);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void decodeDirectory(File pfile)
/*     */   {
/*  57 */     for (File lf : pfile.listFiles()) {
/*  58 */       if (lf.isFile()) {
/*  59 */         String lname = lf.getName();
/*  60 */         if ((lname.indexOf(getPreFix()) > 0) && (lname.endsWith(".class")))
/*     */         {
/*  62 */           loadClass(lf.getName());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void decodeZip(File pfile)
/*     */   {
/*     */     try {
/*  71 */       FileInputStream fileinputstream = new FileInputStream(pfile);
/*  72 */       ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
/*     */       
/*     */       for (;;)
/*     */       {
/*  76 */         ZipEntry zipentry = zipinputstream.getNextEntry();
/*  77 */         if (zipentry == null) {
/*     */           break;
/*     */         }
/*  80 */         if (!zipentry.isDirectory()) {
/*  81 */           String lname = zipentry.getName();
/*  82 */           if ((lname.indexOf(getPreFix()) > 0) && (lname.endsWith(".class"))) {
/*  83 */             loadClass(zipentry.getName());
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  88 */       zipinputstream.close();
/*  89 */       fileinputstream.close();
/*     */     }
/*     */     catch (Exception exception) {
/*  92 */       MMMLib.Debug("add%sZip-Exception.", new Object[] { getPreFix() });
/*     */     }
/*     */   }
/*     */   
/*     */   private void loadClass(String pname)
/*     */   {
/*  98 */     String lclassname = "";
/*     */     try
/*     */     {
/* 101 */       ClassLoader lclassLoader = MMMLib.class.getClassLoader();
/* 102 */       Package lpackage = MMMLib.class.getPackage();
/* 103 */       lclassname = pname.replace(".class", "");
/*     */       Class lclass;
/* 105 */       Class lclass; if (lpackage != null)
/*     */       {
/* 107 */         lclassname = lclassname.replace("/", ".");
/*     */         
/* 109 */         lclass = lclassLoader.loadClass(lclassname);
/*     */       } else {
/* 111 */         lclass = Class.forName(lclassname);
/*     */       }
/* 113 */       if (Modifier.isAbstract(lclass.getModifiers())) {
/* 114 */         return;
/*     */       }
/* 116 */       if (append(lclass)) {
/* 117 */         MMMLib.Debug("get%sClass-done: %s", new Object[] { getPreFix(), lclassname });
/*     */       } else {
/* 119 */         MMMLib.Debug("get%sClass-fail: %s", new Object[] { getPreFix(), lclassname });
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 133 */       MMMLib.Debug("get%sClass-Exception.(%s)", new Object[] { getPreFix(), lclassname });
/* 134 */       exception.printStackTrace();
/*     */     }
/*     */     catch (Error error) {
/* 137 */       MMMLib.Debug("get%sClass-Error: %s", new Object[] { getPreFix(), lclassname });
/* 138 */       error.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ManagerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
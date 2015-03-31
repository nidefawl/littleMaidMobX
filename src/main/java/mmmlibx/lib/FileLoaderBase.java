/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FileLoaderBase
/*     */ {
/*     */   public void execute()
/*     */   {
/*  23 */     List<File> llist = FileManager.getAllmodsFiles(getClass().getClassLoader(), true);
/*  24 */     for (File lf : llist) {
/*  25 */       String ls = lf.getName();
/*  26 */       if ((isZipLoad()) && (ls.matches("(.+).(zip|jar)$")))
/*  27 */         decodeZip(lf);
/*  28 */       if (lf.isDirectory()) {
/*  29 */         decodeDir(lf, lf);
/*     */       } else {
/*     */         try {
/*  32 */           preLoad(lf, ls, new FileInputStream(lf));
/*     */         } catch (Exception e) {
/*  34 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void decodeZip(File pFile)
/*     */   {
/*     */     try
/*     */     {
/*  46 */       ZipFile lzf = new ZipFile(pFile);
/*  47 */       FileInputStream lfis = new FileInputStream(pFile);
/*  48 */       ZipInputStream lzis = new ZipInputStream(lfis);
/*     */       
/*  50 */       for (ZipEntry lze = lzis.getNextEntry(); lze != null; lze = lzis.getNextEntry()) {
/*  51 */         if (!lze.isDirectory()) {
/*  52 */           preLoad(pFile, lze.getName(), lzf.getInputStream(lze));
/*     */         }
/*     */       }
/*     */       
/*  56 */       lzis.close();
/*  57 */       lfis.close();
/*  58 */       lzf.close();
/*     */     } catch (Exception e) {
/*  60 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void decodeDir(File pBaseDir, File pFile)
/*     */   {
/*  69 */     for (File lf : pFile.listFiles()) {
/*  70 */       if (lf.isDirectory()) {
/*  71 */         decodeDir(pBaseDir, lf);
/*     */       } else {
/*     */         try {
/*  74 */           preLoad(lf, lf.getAbsolutePath().substring(pBaseDir.getAbsolutePath().length()), new FileInputStream(lf));
/*     */         } catch (Exception e) {
/*  76 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isZipLoad()
/*     */   {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean load(File pFile, String pFileName, InputStream pInputStream)
/*     */   {
/*  97 */     System.out.println("load# " + pFile.getPath() + " # " + pFileName);
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean preLoad(File pFile, String pFileName, InputStream pInputStream)
/*     */   {
/* 106 */     pFileName = pFileName.replace("\\", "/");
/* 107 */     return load(pFile, pFileName, pInputStream);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/FileLoaderBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
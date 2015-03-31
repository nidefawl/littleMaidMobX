/*     */ package mmmlibx.lib.multiModel.texture;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import mmmlibx.lib.FileLoaderBase;
/*     */ import mmmlibx.lib.MMMLib;
/*     */ import mmmlibx.lib.MMM_ProxyCommon;
/*     */ import mmmlibx.lib.multiModel.model.AbstractModelBase;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Orign;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiModelManager
/*     */   extends FileLoaderBase
/*     */ {
/*  26 */   protected static final String[] defNames = { "mob_littlemaid0.png", "mob_littlemaid1.png", "mob_littlemaid2.png", "mob_littlemaid3.png", "mob_littlemaid4.png", "mob_littlemaid5.png", "mob_littlemaid6.png", "mob_littlemaid7.png", "mob_littlemaid8.png", "mob_littlemaid9.png", "mob_littlemaida.png", "mob_littlemaidb.png", "mob_littlemaidc.png", "mob_littlemaidd.png", "mob_littlemaide.png", "mob_littlemaidf.png", "mob_littlemaidw.png", "mob_littlemaid_a00.png", "mob_littlemaid_a01.png" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  35 */   public static MultiModelManager instance = new MultiModelManager();
/*     */   
/*     */   protected AbstractModelBase[] defaultModel;
/*     */   protected Map<String, AbstractModelBase[]> models;
/*     */   protected Map<String, String> modelNames;
/*     */   protected Map<String, MultiModelContainer> textures;
/*     */   protected List<String> preFixs;
/*     */   
/*     */   public MultiModelManager()
/*     */   {
/*  45 */     this.models = new HashMap();
/*  46 */     this.modelNames = new HashMap();
/*  47 */     this.defaultModel = getModelBase(ModelLittleMaid_Orign.class);
/*  48 */     this.preFixs = new ArrayList();
/*  49 */     this.preFixs.add("/mob/littleMaid/");
/*  50 */     this.preFixs.add("/textures/entity/littleMaid/");
/*  51 */     this.preFixs.add("/multiModel/");
/*  52 */     this.textures = new HashMap();
/*     */   }
/*     */   
/*     */   public boolean isZipLoad()
/*     */   {
/*  57 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void execute()
/*     */   {
/*  63 */     super.execute();
/*     */     
/*  65 */     setModels();
/*     */   }
/*     */   
/*     */ 
/*     */   public void decodeZip(File pFile)
/*     */   {
/*  71 */     super.decodeZip(pFile);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean load(File pFile, String pFileName, InputStream pInputStream)
/*     */   {
/*  77 */     if (MMMLib.proxy.isClient())
/*     */     {
/*  79 */       if (addModelClass(pFileName))
/*     */       {
/*  81 */         return true;
/*     */       }
/*     */     }
/*  84 */     return addTexture(pFile, pFileName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean addModelClass(String pFileName)
/*     */   {
/*  94 */     if ((pFileName.endsWith(".class")) && (pFileName.indexOf("$") == -1)) {
/*  95 */       String lcname = pFileName.substring(0, pFileName.length() - 6);
/*  96 */       if (lcname.startsWith("/")) {
/*  97 */         lcname = lcname.substring(1);
/*     */       }
/*  99 */       lcname = lcname.replace("/", ".");
/*     */       try
/*     */       {
/* 102 */         ClassLoader lcl = getClass().getClassLoader();
/* 103 */         Class<?> lc = lcl.loadClass(lcname);
/* 104 */         if ((AbstractModelBase.class.isAssignableFrom(lc)) && (!Modifier.isAbstract(lc.getModifiers()))) {
/* 105 */           Class<? extends AbstractModelBase> lca = lc;
/* 106 */           int lindex = lcname.lastIndexOf('_');
/* 107 */           if (lindex > -1) {
/* 108 */             String lname = lcname.substring(lindex + 1, lcname.length());
/* 109 */             this.models.put(lcname, getModelBase(lca));
/* 110 */             this.modelNames.put(lname, lcname);
/* 111 */             MMMLib.Debug("get MultiModelClass: %s(%s)", new Object[] { lname, lcname });
/* 112 */             return true;
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {
/* 116 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean addTexture(File pFile, String pFileName) {
/* 123 */     if (pFileName.endsWith(".png")) {
/* 124 */       if (!pFileName.startsWith("/")) {
/* 125 */         pFileName = "/" + pFileName;
/*     */       }
/* 127 */       for (String lfix : this.preFixs) {
/* 128 */         int lindex = pFileName.indexOf(lfix);
/* 129 */         if (lindex > -1) {
/* 130 */           String lname = pFileName.substring(pFileName.lastIndexOf('/'));
/* 131 */           int lcol = getIndex(lname);
/* 132 */           if (lcol > -1) {
/* 133 */             lname = pFileName.substring(lindex + lfix.length(), pFileName.lastIndexOf('/'));
/* 134 */             lname.replace('/', '.');
/* 135 */             MultiModelContainer lcon = (MultiModelContainer)this.textures.get(lname);
/* 136 */             if (lcon == null) {
/* 137 */               lcon = new MultiModelContainer();
/* 138 */               this.textures.put(lname, lcon);
/*     */             }
/*     */             
/* 141 */             lcon.addTexture(lcol, new ResourceLocation(pFileName));
/* 142 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */   
/*     */   protected void setModels() {
/* 151 */     MMMLib.Debug("setModels execute.", new Object[0]);
/* 152 */     for (Map.Entry<String, String> le : this.modelNames.entrySet()) {
/* 153 */       MMMLib.Debug("models: %s - %s", new Object[] { le.getKey(), le.getValue() });
/*     */     }
/*     */     
/* 156 */     for (Map.Entry<String, MultiModelContainer> le : this.textures.entrySet()) {
/* 157 */       String lmname = (String)le.getKey();
/* 158 */       MultiModelContainer lcont = (MultiModelContainer)le.getValue();
/* 159 */       if (lcont.defaultModel == null) {
/* 160 */         MMMLib.Debug("checkModel: %s(%d)", new Object[] { lmname, Integer.valueOf(lcont.getTextureCount()) });
/* 161 */         int lindex = lmname.lastIndexOf('_');
/* 162 */         if (lindex > 0) {
/* 163 */           lmname = lmname.substring(lindex + 1, lmname.length());
/* 164 */           lcont.defaultModel = getModelFromName(lmname);
/*     */         } else {
/* 166 */           lcont.defaultModel = this.defaultModel;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected AbstractModelBase[] getModelFromName(String pName) {
/* 173 */     String ls = (String)this.modelNames.get(pName);
/* 174 */     return ls == null ? this.defaultModel : (AbstractModelBase[])this.models.get(ls);
/*     */   }
/*     */   
/*     */   protected AbstractModelBase[] getModelBase(Class<? extends AbstractModelBase> pClass) {
/*     */     try {
/* 179 */       Object lo = pClass.getConstructor(new Class[0]).newInstance(new Object[0]);
/* 180 */       if ((lo instanceof AbstractModelBase)) {
/* 181 */         AbstractModelBase lmodel = (AbstractModelBase)lo;
/* 182 */         float[] lsize = lmodel.getArmorModelsSize();
/*     */         AbstractModelBase[] lamb;
/* 184 */         if ((lsize != null) && (lsize.length > 0)) {
/* 185 */           AbstractModelBase[] lamb = new AbstractModelBase[1 + lsize.length];
/* 186 */           int li = 1;
/* 187 */           Constructor<?> lc = pClass.getConstructor(new Class[] { Float.TYPE });
/* 188 */           for (float lf : lsize) {
/* 189 */             lamb[(li++)] = ((AbstractModelBase)lc.newInstance(new Object[] { Float.valueOf(lf) }));
/*     */           }
/*     */         } else {
/* 192 */           lamb = new AbstractModelBase[1];
/*     */         }
/* 194 */         lamb[0] = lmodel;
/* 195 */         return lamb;
/*     */       }
/*     */     } catch (Exception e) {
/* 198 */       e.printStackTrace();
/*     */     }
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   protected int getIndex(String name)
/*     */   {
/* 205 */     for (int i = 0; i < defNames.length; i++) {
/* 206 */       if (name.endsWith(defNames[i])) {
/* 207 */         return i;
/*     */       }
/*     */     }
/* 210 */     Pattern p = Pattern.compile("_([0-9a-f]+).png");
/* 211 */     Matcher m = p.matcher(name);
/* 212 */     if (m.find()) {
/* 213 */       return Integer.decode("0x" + m.group(1)).intValue();
/*     */     }
/* 215 */     return -1;
/*     */   }
/*     */   
/*     */   public MultiModelContainer getMultiModel(String pName) {
/* 219 */     MultiModelContainer lcont = (MultiModelContainer)this.textures.get(pName);
/* 220 */     if (lcont == null) {
/* 221 */       lcont = (MultiModelContainer)this.textures.get("default");
/*     */     }
/* 223 */     return lcont;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/texture/MultiModelManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Random;
/*     */ import java.util.TreeMap;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ import net.minecraft.world.World;
/*     */ import wrapper.W_Common;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_IFF
/*     */ {
/*     */   public static final int iff_Enemy = 0;
/*     */   public static final int iff_Unknown = 1;
/*     */   public static final int iff_Friendry = 2;
/*  41 */   public static Map<String, Integer> DefaultIFF = new TreeMap();
/*     */   
/*     */ 
/*     */ 
/*  45 */   public static Map<String, Map<String, Integer>> UserIFF = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   public static Map<String, Integer> getUserIFF(String pUsername)
/*     */   {
/*  51 */     if (pUsername == null) {
/*  52 */       return DefaultIFF;
/*     */     }
/*  54 */     if (MMM_Helper.isLocalPlay()) {
/*  55 */       pUsername = "";
/*     */     }
/*     */     
/*  58 */     if (!UserIFF.containsKey(pUsername))
/*     */     {
/*  60 */       if (pUsername.isEmpty()) {
/*  61 */         UserIFF.put(pUsername, DefaultIFF);
/*     */       } else {
/*  63 */         Map<String, Integer> lmap = new HashMap();
/*  64 */         lmap.putAll(DefaultIFF);
/*  65 */         UserIFF.put(pUsername, lmap);
/*     */       }
/*     */     }
/*     */     
/*  69 */     return (Map)UserIFF.get(pUsername);
/*     */   }
/*     */   
/*     */   public static void setIFFValue(String pUsername, String pName, int pValue) {
/*  73 */     Map<String, Integer> lmap = getUserIFF(pUsername);
/*  74 */     lmap.put(pName, Integer.valueOf(pValue));
/*     */   }
/*     */   
/*     */   protected static int checkEntityStatic(String pName, Entity pEntity, int pIndex, Map<String, Entity> pMap)
/*     */   {
/*  79 */     int liff = 1;
/*  80 */     if ((pEntity instanceof EntityLivingBase)) {
/*  81 */       if ((pEntity instanceof LMM_EntityLittleMaid)) {
/*  82 */         switch (pIndex)
/*     */         {
/*     */         case 0: 
/*  85 */           liff = 1;
/*  86 */           break;
/*     */         
/*     */         case 1: 
/*  89 */           pName = pName + ":Contract";
/*  90 */           ((LMM_EntityLittleMaid)pEntity).setContract(true);
/*  91 */           liff = 2;
/*  92 */           break;
/*     */         
/*     */         case 2: 
/*  95 */           pName = pName + ":Others";
/*  96 */           ((LMM_EntityLittleMaid)pEntity).setContract(true);
/*  97 */           liff = 2;
/*     */         }
/*     */       }
/* 100 */       else if ((pEntity instanceof IEntityOwnable)) {
/* 101 */         switch (pIndex)
/*     */         {
/*     */         case 0: 
/*     */           break;
/*     */         
/*     */         case 1: 
/* 107 */           pName = pName + ":Taim";
/* 108 */           if ((pEntity instanceof EntityTameable)) {
/* 109 */             ((EntityTameable)pEntity).setTamed(true);
/*     */           }
/* 111 */           liff = 2;
/* 112 */           break;
/*     */         
/*     */         case 2: 
/* 115 */           pName = pName + ":Others";
/* 116 */           if ((pEntity instanceof EntityTameable)) {
/* 117 */             ((EntityTameable)pEntity).setTamed(true);
/*     */           }
/* 119 */           liff = 1;
/*     */         }
/*     */         
/* 122 */         if ((pIndex != 0) && 
/* 123 */           ((pEntity instanceof EntityOcelot))) {
/* 124 */           ((EntityOcelot)pEntity).setTameSkin(1 + new Random().nextInt(3));
/*     */         }
/*     */       }
/*     */       
/* 128 */       if (pMap != null)
/*     */       {
/* 130 */         pMap.put(pName, pEntity);
/* 131 */         LMM_LittleMaidMobX.Debug(pName + " added.", new Object[0]);
/*     */       }
/*     */       
/*     */ 
/* 135 */       if (!DefaultIFF.containsKey(pName)) {
/* 136 */         if ((pEntity instanceof IMob)) {
/* 137 */           liff = 0;
/*     */         }
/* 139 */         DefaultIFF.put(pName, Integer.valueOf(liff));
/*     */       }
/*     */     }
/*     */     
/* 143 */     return liff;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static int getIFF(String pUsername, String entityname, World world)
/*     */   {
/* 150 */     if (entityname == null) {
/* 151 */       return LMM_LittleMaidMobX.cfg_Aggressive ? 0 : 2;
/*     */     }
/* 153 */     int lt = 0;
/* 154 */     Map<String, Integer> lmap = getUserIFF(pUsername);
/* 155 */     if (lmap.containsKey(entityname)) {
/* 156 */       lt = ((Integer)lmap.get(entityname)).intValue();
/* 157 */     } else if ((lmap != DefaultIFF) && (DefaultIFF.containsKey(entityname)))
/*     */     {
/* 159 */       lt = ((Integer)DefaultIFF.get(entityname)).intValue();
/* 160 */       lmap.put(entityname, Integer.valueOf(lt));
/*     */     }
/*     */     else {
/* 163 */       int li = entityname.indexOf(":");
/*     */       String ls;
/* 165 */       String ls; if (li > -1) {
/* 166 */         ls = entityname.substring(0, li);
/*     */       } else {
/* 168 */         ls = entityname;
/*     */       }
/* 170 */       Entity lentity = EntityList.createEntityByName(ls, world);
/* 171 */       li = 0;
/* 172 */       if (entityname.indexOf(":Contract") > -1) {
/* 173 */         li = 1;
/*     */       }
/* 175 */       else if (entityname.indexOf(":Taim") > -1) {
/* 176 */         li = 1;
/*     */       }
/* 178 */       else if (entityname.indexOf(":Others") > -1) {
/* 179 */         li = 2;
/*     */       }
/* 181 */       lt = checkEntityStatic(ls, lentity, li, null);
/* 182 */       lmap.put(entityname, Integer.valueOf(lt));
/*     */     }
/* 184 */     return lt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static int getIFF(String pUsername, Entity entity)
/*     */   {
/* 191 */     if ((entity == null) || (!(entity instanceof EntityLivingBase))) {
/* 192 */       return LMM_LittleMaidMobX.cfg_Aggressive ? 0 : 2;
/*     */     }
/* 194 */     String lename = EntityList.getEntityString(entity);
/* 195 */     String lcname = lename;
/* 196 */     if (lename == null)
/*     */     {
/* 198 */       return 2;
/*     */     }
/*     */     
/*     */ 
/* 202 */     int li = 0;
/* 203 */     if ((entity instanceof LMM_EntityLittleMaid)) {
/* 204 */       if (((LMM_EntityLittleMaid)entity).isContract()) {
/* 205 */         if (((LMM_EntityLittleMaid)entity).getMaidMaster().contentEquals(pUsername))
/*     */         {
/* 207 */           lcname = lename + ":Contract";
/* 208 */           li = 1;
/*     */         }
/*     */         else {
/* 211 */           lcname = lename + ":Others";
/* 212 */           li = 2;
/*     */         }
/*     */       }
/* 215 */     } else if ((entity instanceof IEntityOwnable)) {
/* 216 */       String loname = W_Common.getOwnerName((IEntityOwnable)entity);
/* 217 */       if (!loname.isEmpty()) {
/* 218 */         if (loname.contentEquals(pUsername))
/*     */         {
/* 220 */           lcname = lename + ":Taim";
/* 221 */           li = 1;
/*     */         }
/*     */         else {
/* 224 */           lcname = lename + ":Others";
/* 225 */           li = 2;
/*     */         }
/*     */       }
/*     */     }
/* 229 */     if (!getUserIFF(pUsername).containsKey(lcname)) {
/* 230 */       checkEntityStatic(lename, entity, li, null);
/*     */     }
/* 232 */     return getIFF(pUsername, lcname, entity.worldObj);
/*     */   }
/*     */   
/*     */   public static void loadIFFs()
/*     */   {
/* 237 */     if (!MMM_Helper.isClient)
/*     */     {
/* 239 */       loadIFF("");
/* 240 */       File lfile = MinecraftServer.getServer().getFile("");
/* 241 */       for (File lf : lfile.listFiles()) {
/* 242 */         if (lf.getName().endsWith("littleMaidMob.iff")) {
/* 243 */           String ls = lf.getName().substring(17, lf.getName().length() - 20);
/* 244 */           LMM_LittleMaidMobX.Debug(ls, new Object[0]);
/* 245 */           loadIFF(ls);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 250 */       loadIFF(null);
/*     */     }
/*     */   }
/*     */   
/*     */   protected static File getFile(String pUsername) { File lfile;
/*     */     File lfile;
/* 256 */     if (pUsername == null) {
/* 257 */       lfile = new File(MMM_Helper.mc.mcDataDir, "config/littleMaidMob.iff");
/*     */     } else { String lfilename;
/*     */       String lfilename;
/* 260 */       if (pUsername.isEmpty()) {
/* 261 */         lfilename = "config/littleMaidMob.iff";
/*     */       } else {
/* 263 */         lfilename = "config/littleMaidMob_".concat(pUsername).concat(".iff");
/*     */       }
/* 265 */       lfile = MinecraftServer.getServer().getFile(lfilename);
/*     */     }
/* 267 */     LMM_LittleMaidMobX.Debug(lfile.getAbsolutePath(), new Object[0]);
/* 268 */     return lfile;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void loadIFF(String pUsername)
/*     */   {
/* 274 */     File lfile = getFile(pUsername);
/* 275 */     if ((!lfile.exists()) || (!lfile.canRead())) {
/* 276 */       return;
/*     */     }
/* 278 */     Map<String, Integer> lmap = getUserIFF(pUsername);
/*     */     try
/*     */     {
/* 281 */       FileReader fr = new FileReader(lfile);
/* 282 */       BufferedReader br = new BufferedReader(fr);
/*     */       
/*     */       String s;
/* 285 */       while ((s = br.readLine()) != null) {
/* 286 */         String[] t = s.split("=");
/* 287 */         if (t.length > 1) {
/* 288 */           if (t[0].startsWith("triggerWeapon")) {
/* 289 */             LMM_TriggerSelect.appendTriggerItem(pUsername, t[0].substring(13), t[1]);
/*     */           }
/*     */           else {
/* 292 */             int i = Integer.valueOf(t[1]).intValue();
/* 293 */             if (i > 2) {
/* 294 */               i = 1;
/*     */             }
/* 296 */             lmap.put(t[0], Integer.valueOf(i));
/*     */           }
/*     */         }
/*     */       }
/* 300 */       br.close();
/* 301 */       fr.close();
/*     */     } catch (Exception e) {
/* 303 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void saveIFF(String pUsername)
/*     */   {
/* 309 */     File lfile = getFile(LMM_LittleMaidMobX.proxy.isSinglePlayer() ? null : pUsername);
/* 310 */     Map<String, Integer> lmap = getUserIFF(pUsername);
/*     */     try
/*     */     {
/* 313 */       if (((lfile.exists()) || (lfile.createNewFile())) && (lfile.canWrite())) {
/* 314 */         FileWriter fw = new FileWriter(lfile);
/* 315 */         BufferedWriter bw = new BufferedWriter(fw);
/*     */         
/*     */ 
/* 318 */         for (Map.Entry<Integer, List<Item>> le : LMM_TriggerSelect.getUserTrigger(pUsername).entrySet())
/*     */         {
/* 320 */           StringBuilder sb = new StringBuilder();
/* 321 */           sb.append("triggerWeapon").append((String)LMM_TriggerSelect.selector.get(((Integer)le.getKey()).intValue())).append("=");
/*     */           
/*     */ 
/* 324 */           if (!((List)le.getValue()).isEmpty()) {
/* 325 */             String itemName = Item.itemRegistry.getNameForObject(((List)le.getValue()).get(0));
/* 326 */             sb.append(itemName);
/* 327 */             for (int i = 1; i < ((List)le.getValue()).size(); i++) {
/* 328 */               itemName = Item.itemRegistry.getNameForObject(((List)le.getValue()).get(i));
/* 329 */               sb.append(",").append(itemName);
/*     */             }
/*     */           }
/* 332 */           sb.append("\r\n");
/* 333 */           bw.write(sb.toString());
/*     */         }
/*     */         
/* 336 */         for (Map.Entry<String, Integer> me : lmap.entrySet()) {
/* 337 */           bw.write(String.format("%s=%d\r\n", new Object[] { me.getKey(), me.getValue() }));
/*     */         }
/*     */         
/*     */ 
/* 341 */         bw.close();
/* 342 */         fw.close();
/*     */       }
/*     */     } catch (Exception e) {
/* 345 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_IFF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
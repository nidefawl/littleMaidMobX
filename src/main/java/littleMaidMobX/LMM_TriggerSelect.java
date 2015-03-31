/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.RegistryNamespaced;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_TriggerSelect
/*     */ {
/*  19 */   public static List<String> selector = new ArrayList();
/*  20 */   public static Map<String, Map<Integer, List<Item>>> usersTrigger = new HashMap();
/*  21 */   public static Map<Integer, List<Item>> defaultTrigger = new HashMap();
/*     */   
/*     */   public static Map<Integer, List<Item>> getUserTrigger(String pUsername)
/*     */   {
/*  25 */     if (pUsername == null) {
/*  26 */       return defaultTrigger;
/*     */     }
/*  28 */     if (MMM_Helper.isLocalPlay())
/*     */     {
/*  30 */       pUsername = "";
/*     */     }
/*     */     
/*  33 */     if (!usersTrigger.containsKey(pUsername)) {
/*  34 */       if (pUsername.isEmpty())
/*     */       {
/*  36 */         usersTrigger.put(pUsername, defaultTrigger);
/*     */       } else {
/*  38 */         Map<Integer, List<Item>> lmap = new HashMap();
/*  39 */         lmap.putAll(defaultTrigger);
/*  40 */         usersTrigger.put(pUsername, lmap);
/*     */       }
/*     */     }
/*     */     
/*  44 */     return (Map)usersTrigger.get(pUsername);
/*     */   }
/*     */   
/*     */   public static List<Item> getuserTriggerList(String pUsername, String pSelector) {
/*  48 */     if (!selector.contains(pSelector)) {
/*  49 */       selector.add(pSelector);
/*     */     }
/*  51 */     int lindex = selector.indexOf(pSelector);
/*  52 */     Map<Integer, List<Item>> lmap = getUserTrigger(pUsername);
/*     */     List<Item> llist;
/*  54 */     List<Item> llist; if (lmap.containsKey(Integer.valueOf(lindex))) {
/*  55 */       llist = (List)lmap.get(Integer.valueOf(lindex));
/*     */     } else {
/*  57 */       llist = new ArrayList();
/*  58 */       lmap.put(Integer.valueOf(lindex), llist);
/*     */     }
/*  60 */     return llist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void appendTriggerItem(String pUsername, String pSelector, String pIndexstr)
/*     */   {
/*  69 */     appendWeaponsIndex(pIndexstr, getuserTriggerList(pUsername, pSelector));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void appendWeaponsIndex(String indexstr, List<Item> indexlist)
/*     */   {
/*  76 */     if (indexstr.isEmpty()) return;
/*  77 */     String[] s = indexstr.split(",");
/*  78 */     for (String t : s) {
/*  79 */       Object o = Item.itemRegistry.getObject(t);
/*  80 */       if ((o instanceof Item))
/*     */       {
/*  82 */         indexlist.add((Item)o);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean checkWeapon(String pUsername, String pSelector, ItemStack pItemStack)
/*     */   {
/*  91 */     if (!selector.contains(pSelector)) {
/*  92 */       return false;
/*     */     }
/*  94 */     if (MMM_Helper.isLocalPlay()) {
/*  95 */       return getuserTriggerList(null, pSelector).contains(pItemStack.getItem());
/*     */     }
/*  97 */     if (!usersTrigger.containsKey(pUsername)) {
/*  98 */       return false;
/*     */     }
/* 100 */     return getuserTriggerList(pUsername, pSelector).contains(pItemStack.getItem());
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_TriggerSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
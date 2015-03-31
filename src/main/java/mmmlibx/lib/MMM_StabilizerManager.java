/*    */ package mmmlibx.lib;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.TreeMap;
/*    */ import mmmlibx.lib.multiModel.model.mc162.EquippedStabilizer;
/*    */ import mmmlibx.lib.multiModel.model.mc162.ModelBase;
/*    */ import mmmlibx.lib.multiModel.model.mc162.ModelStabilizerBase;
/*    */ 
/*    */ 
/*    */ public class MMM_StabilizerManager
/*    */   extends MMM_ManagerBase
/*    */ {
/*    */   public static final String preFix = "ModelStabilizer";
/* 15 */   public static Map<String, ModelStabilizerBase> stabilizerList = new TreeMap();
/*    */   
/*    */ 
/*    */   public static void init()
/*    */   {
/* 20 */     FileManager.getModFile("Stabilizer", "ModelStabilizer");
/*    */   }
/*    */   
/*    */   public static void loadStabilizer() {
/* 24 */     new MMM_StabilizerManager().load();
/*    */   }
/*    */   
/*    */   protected String getPreFix()
/*    */   {
/* 29 */     return "ModelStabilizer";
/*    */   }
/*    */   
/*    */   protected boolean append(Class pclass)
/*    */   {
/* 34 */     if (!ModelStabilizerBase.class.isAssignableFrom(pclass)) {
/* 35 */       return false;
/*    */     }
/*    */     try
/*    */     {
/* 39 */       ModelStabilizerBase lms = (ModelStabilizerBase)pclass.newInstance();
/* 40 */       stabilizerList.put(lms.getName(), lms);
/* 41 */       return true;
/*    */     }
/*    */     catch (Exception e) {}
/*    */     
/* 45 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static EquippedStabilizer getStabilizer(String pname, String pequippoint)
/*    */   {
/* 52 */     if (!stabilizerList.containsKey(pname)) {
/* 53 */       return null;
/*    */     }
/*    */     
/* 56 */     EquippedStabilizer lequip = new EquippedStabilizer();
/* 57 */     lequip.stabilizer = ((ModelStabilizerBase)stabilizerList.get(pname));
/* 58 */     lequip.stabilizer.init(lequip);
/* 59 */     lequip.equipPointName = pequippoint;
/*    */     
/* 61 */     return lequip;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static void updateEquippedPoint(Map<String, EquippedStabilizer> pMap, ModelBase pModel)
/*    */   {
/* 68 */     for (Map.Entry<String, EquippedStabilizer> le : pMap.entrySet()) {
/* 69 */       ((EquippedStabilizer)le.getValue()).updateEquippedPoint(pModel);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_StabilizerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
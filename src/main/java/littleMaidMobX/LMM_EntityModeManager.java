/*    */ package littleMaidMobX;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import mmmlibx.lib.FileManager;
/*    */ import mmmlibx.lib.MMM_ManagerBase;
/*    */ 
/*    */ public class LMM_EntityModeManager extends MMM_ManagerBase
/*    */ {
/*    */   public static final String prefix = "EntityMode";
/* 12 */   public static List<LMM_EntityModeBase> maidModeList = new ArrayList();
/*    */   
/*    */ 
/*    */   public static void init()
/*    */   {
/* 17 */     FileManager.getModFile("EntityMode", "EntityMode");
/*    */   }
/*    */   
/*    */   public static void loadEntityMode() {
/* 21 */     new LMM_EntityModeManager().load();
/*    */   }
/*    */   
/*    */   protected String getPreFix()
/*    */   {
/* 26 */     return "EntityMode";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected boolean append(Class pclass)
/*    */   {
/* 33 */     if (!LMM_EntityModeBase.class.isAssignableFrom(pclass)) {
/* 34 */       return false;
/*    */     }
/*    */     try
/*    */     {
/* 38 */       LMM_EntityModeBase lemb = null;
/* 39 */       lemb = (LMM_EntityModeBase)pclass.getConstructor(new Class[] { LMM_EntityLittleMaid.class }).newInstance(new Object[] { (LMM_EntityLittleMaid)null });
/* 40 */       lemb.init();
/*    */       
/* 42 */       if ((maidModeList.isEmpty()) || (lemb.priority() >= ((LMM_EntityModeBase)maidModeList.get(maidModeList.size() - 1)).priority())) {
/* 43 */         maidModeList.add(lemb);
/*    */       } else {
/* 45 */         for (int li = 0; li < maidModeList.size(); li++) {
/* 46 */           if (lemb.priority() < ((LMM_EntityModeBase)maidModeList.get(li)).priority()) {
/* 47 */             maidModeList.add(li, lemb);
/* 48 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 53 */       return true;
/*    */     }
/*    */     catch (Exception e) {}catch (Error e) {}
/*    */     
/*    */ 
/* 58 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static List<LMM_EntityModeBase> getModeList(LMM_EntityLittleMaid pentity)
/*    */   {
/* 65 */     List<LMM_EntityModeBase> llist = new ArrayList();
/* 66 */     for (LMM_EntityModeBase lmode : maidModeList) {
/*    */       try {
/* 68 */         llist.add(lmode.getClass().getConstructor(new Class[] { LMM_EntityLittleMaid.class }).newInstance(new Object[] { pentity }));
/*    */       }
/*    */       catch (Exception e) {}catch (Error e) {}
/*    */     }
/*    */     
/*    */ 
/* 74 */     return llist;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static void showLoadedModes()
/*    */   {
/* 81 */     LMM_LittleMaidMobX.Debug("Loaded Mode lists(%d)", new Object[] { Integer.valueOf(maidModeList.size()) });
/* 82 */     for (LMM_EntityModeBase lem : maidModeList) {
/* 83 */       LMM_LittleMaidMobX.Debug("%04d : %s", new Object[] { Integer.valueOf(lem.priority()), lem.getClass().getSimpleName() });
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityModeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
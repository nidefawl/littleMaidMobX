/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ModelCapsHelper
/*    */ {
/*    */   public static Object getCapsValue(IModelCaps pOwner, int pCapsIndex, Object... pArg)
/*    */   {
/*  9 */     return pOwner == null ? null : pOwner.getCapsValue(pCapsIndex, pArg);
/*    */   }
/*    */   
/*    */   public static Object getCapsValue(IModelCaps pOwner, String pCapsName, Object... pArg) {
/* 13 */     return pOwner == null ? null : pOwner.getCapsValue(((Integer)pOwner.getModelCaps().get(pCapsName)).intValue(), pArg);
/*    */   }
/*    */   
/*    */   public static int getCapsValueInt(IModelCaps pOwner, int pIndex, Object... pArg) {
/* 17 */     if (pOwner == null) return 0;
/* 18 */     Integer li = (Integer)pOwner.getCapsValue(pIndex, pArg);
/* 19 */     return li == null ? 0 : li.intValue();
/*    */   }
/*    */   
/*    */   public static float getCapsValueFloat(IModelCaps pOwner, int pIndex, Object... pArg) {
/* 23 */     if (pOwner == null) return 0.0F;
/* 24 */     Float lf = (Float)pOwner.getCapsValue(pIndex, pArg);
/* 25 */     return lf == null ? 0.0F : lf.floatValue();
/*    */   }
/*    */   
/*    */   public static double getCapsValueDouble(IModelCaps pOwner, int pIndex, Object... pArg) {
/* 29 */     if (pOwner == null) return 0.0D;
/* 30 */     Double ld = (Double)pOwner.getCapsValue(pIndex, pArg);
/* 31 */     return ld == null ? 0.0D : ld.doubleValue();
/*    */   }
/*    */   
/*    */   public static boolean getCapsValueBoolean(IModelCaps pOwner, int pIndex, Object... pArg) {
/* 35 */     if (pOwner == null) return false;
/* 36 */     Boolean lb = (Boolean)pOwner.getCapsValue(pIndex, pArg);
/* 37 */     return lb == null ? false : lb.booleanValue();
/*    */   }
/*    */   
/*    */   public static boolean setCapsValue(IModelCaps pOwner, String pCapsName, Object... pArg) {
/* 41 */     return pOwner == null ? false : pOwner.setCapsValue(((Integer)pOwner.getModelCaps().get(pCapsName)).intValue(), pArg);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelCapsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
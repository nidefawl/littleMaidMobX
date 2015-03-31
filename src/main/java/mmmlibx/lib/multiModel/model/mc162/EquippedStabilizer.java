/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquippedStabilizer
/*    */ {
/*    */   public ModelStabilizerBase stabilizer;
/*    */   public ModelRenderer equipPoint;
/*    */   public String equipPointName;
/*    */   public Map<String, Object> localValues;
/*    */   
/*    */   public boolean updateEquippedPoint(ModelBase pmodel)
/*    */   {
/* 24 */     for (int li = 0; li < pmodel.boxList.size(); li++) {
/* 25 */       ModelRenderer lmr = (ModelRenderer)pmodel.boxList.get(li);
/* 26 */       if ((lmr.boxName != null) && (lmr.boxName.equalsIgnoreCase(this.equipPointName))) {
/* 27 */         this.equipPoint = lmr;
/* 28 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 32 */     this.equipPoint = null;
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/EquippedStabilizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
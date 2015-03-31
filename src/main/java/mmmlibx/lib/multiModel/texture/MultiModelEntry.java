/*    */ package mmmlibx.lib.multiModel.texture;
/*    */ 
/*    */ import mmmlibx.lib.multiModel.model.AbstractModelBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MultiModelEntry
/*    */ {
/*    */   protected String name;
/*    */   protected int color;
/*    */   protected AbstractModelBase model;
/*    */   protected ResourceLocation texture;
/*    */   
/*    */   public AbstractModelBase getModel()
/*    */   {
/* 19 */     return this.model;
/*    */   }
/*    */   
/*    */   public int getColor() {
/* 23 */     return this.color;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/texture/MultiModelEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
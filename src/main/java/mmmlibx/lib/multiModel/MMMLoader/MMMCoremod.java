/*    */ package mmmlibx.lib.multiModel.MMMLoader;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
/*    */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
/*    */ import java.util.Map;
/*    */ 
/*    */ @IFMLLoadingPlugin.TransformerExclusions({"mmmlibx.lib.multiModel.MMMLoader"})
/*    */ public class MMMCoremod
/*    */   implements IFMLLoadingPlugin
/*    */ {
/*    */   public String[] getASMTransformerClass()
/*    */   {
/* 13 */     return new String[] { "mmmlibx.lib.multiModel.MMMLoader.MMMTransformer" };
/*    */   }
/*    */   
/*    */   public String getModContainerClass()
/*    */   {
/* 18 */     return "mmmlibx.lib.multiModel.MMMLoader.MMMModContainer";
/*    */   }
/*    */   
/*    */ 
/*    */   public String getSetupClass()
/*    */   {
/* 24 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void injectData(Map<String, Object> data) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public String getAccessTransformerClass()
/*    */   {
/* 36 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/MMMLoader/MMMCoremod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
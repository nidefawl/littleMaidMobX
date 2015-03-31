/*   */ package zabuton;
/*   */ 
/*   */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*   */ 
/*   */ public class VZN_ProxyClient extends VZN_ProxyCommon
/*   */ {
/*   */   public void RegistRenderer()
/*   */   {
/* 9 */     RenderingRegistry.registerEntityRenderingHandler(VZN_EntityZabuton.class, new VZN_RenderZabuton());
/*   */   }
/*   */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_ProxyClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
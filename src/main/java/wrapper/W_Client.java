/*    */ package wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.Loader;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
/*    */ 
/*    */ 
/*    */ public class W_Client
/*    */ {
/*  9 */   private static final W_IClient instance = ;
/*    */   
/*    */   private static W_IClient getInstance()
/*    */   {
/* 13 */     String VER = Loader.instance().getMCVersionString();
/* 14 */     if (VER.equalsIgnoreCase("1.7.2"))
/*    */     {
/* 16 */       return new wrapper.mc172.W_CClient();
/*    */     }
/* 18 */     if (VER.equalsIgnoreCase("1.7.10"))
/*    */     {
/* 20 */       return new wrapper.mc1710.W_CClient();
/*    */     }
/* 22 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void renderSkeletonHead(TileEntitySkullRenderer skullRenderer, float x, float y, float z, int p_147530_4_, float p_147530_5_, int p_147530_6_, String p_147530_7_)
/*    */   {
/* 30 */     instance.renderSkeletonHead(skullRenderer, x, y, z, p_147530_4_, p_147530_5_, p_147530_6_, p_147530_7_);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/wrapper/W_Client.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
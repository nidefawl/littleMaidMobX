/*    */ package network;
/*    */ 
/*    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ 
/*    */ public class W_Network
/*    */ {
/*    */   private static SimpleNetworkWrapper INSTANCE;
/*    */   
/*    */   public static void init(String ch)
/*    */   {
/* 16 */     INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ch);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 22 */     INSTANCE.registerMessage(W_MessageHandler.class, W_Message.class, 0, Side.SERVER);
/* 23 */     INSTANCE.registerMessage(W_MessageHandler.class, W_Message.class, 0, Side.CLIENT);
/*    */   }
/*    */   
/*    */   public static void sendPacketToServer(int ch, byte[] data)
/*    */   {
/* 28 */     INSTANCE.sendToServer(new W_Message(ch, data));
/*    */   }
/*    */   
/*    */   public static void sendPacketToPlayer(int ch, EntityPlayer player, byte[] data)
/*    */   {
/* 33 */     if ((player instanceof EntityPlayerMP))
/*    */     {
/* 35 */       INSTANCE.sendTo(new W_Message(ch, data), (EntityPlayerMP)player);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void sendPacketToAllPlayer(int ch, byte[] data)
/*    */   {
/* 41 */     INSTANCE.sendToAll(new W_Message(ch, data));
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/network/W_Network.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
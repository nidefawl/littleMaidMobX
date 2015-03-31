/*    */ package network;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import littleMaidMobX.LMM_LittleMaidMobX;
/*    */ import littleMaidMobX.LMM_ProxyCommon;
/*    */ import mmmlibx.lib.MMMLib;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ 
/*    */ public class W_MessageHandler implements IMessageHandler<W_Message, IMessage>
/*    */ {
/*    */   public IMessage onMessage(W_Message message, MessageContext ctx)
/*    */   {
/* 16 */     if (message.data != null)
/*    */     {
/* 18 */       if (ctx.side.isClient())
/*    */       {
/* 20 */         LMM_LittleMaidMobX.proxy.clientCustomPayload(message);
/*    */       }
/*    */       else
/*    */       {
/* 24 */         if (message.ch == 1)
/*    */         {
/* 26 */           MMMLib.serverCustomPayload(ctx.getServerHandler().playerEntity, message);
/*    */         }
/* 28 */         if (message.ch == 2)
/*    */         {
/* 30 */           littleMaidMobX.LMM_Net.serverCustomPayload(ctx.getServerHandler().playerEntity, message);
/*    */         }
/*    */       }
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/network/W_MessageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
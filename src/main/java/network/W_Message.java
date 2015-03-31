/*    */ package network;
/*    */ 
/*    */ import cpw.mods.fml.common.network.simpleimpl.IMessage;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_Message
/*    */   implements IMessage
/*    */ {
/*    */   public byte ch;
/*    */   public byte[] data;
/*    */   
/*    */   public W_Message() {}
/*    */   
/*    */   public W_Message(int ch, byte[] sendData)
/*    */   {
/* 21 */     this.ch = ((byte)ch);
/* 22 */     this.data = sendData;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void fromBytes(ByteBuf buf)
/*    */   {
/* 33 */     int len = buf.array().length;
/* 34 */     if (len > 2)
/*    */     {
/* 36 */       this.data = new byte[len - 2];
/* 37 */       this.ch = buf.getByte(0);
/* 38 */       buf.getBytes(1, this.data);
/*    */     }
/*    */     else
/*    */     {
/* 42 */       this.data = new byte[] { 0 };
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void toBytes(ByteBuf buf)
/*    */   {
/* 49 */     buf.writeByte(this.ch);
/* 50 */     buf.writeBytes(this.data);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/network/W_Message.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*    */ package wrapper.mc172;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.IEntityOwnable;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ 
/*    */ public class W_CCommon implements wrapper.W_ICommon
/*    */ {
/*    */   public void setOwner(EntityTameable entity, String name)
/*    */   {
/* 14 */     entity.func_70910_a(name);
/*    */   }
/*    */   
/*    */   public String getOwnerName(IEntityOwnable entity)
/*    */   {
/* 19 */     return entity.func_70905_p();
/*    */   }
/*    */   
/*    */   public GameProfile newGameProfile(String UUIDid, String name)
/*    */   {
/* 24 */     return new GameProfile(UUIDid, name);
/*    */   }
/*    */   
/*    */   public void notifyAdmins(ICommandSender p_152374_0_, ICommand p_152374_1_, int p_152374_2_, String p_152374_3_, Object... p_152374_4_)
/*    */   {
/* 29 */     CommandBase.func_71524_a(p_152374_0_, p_152374_2_, p_152374_3_, p_152374_4_);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/wrapper/mc172/W_CCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
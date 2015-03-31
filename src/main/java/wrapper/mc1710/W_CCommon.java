/*    */ package wrapper.mc1710;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.IEntityOwnable;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import wrapper.W_ICommon;
/*    */ 
/*    */ public class W_CCommon implements W_ICommon
/*    */ {
/*    */   public void setOwner(EntityTameable entity, String name)
/*    */   {
/* 16 */     entity.func_152115_b(name);
/*    */   }
/*    */   
/*    */   public String getOwnerName(IEntityOwnable entity)
/*    */   {
/* 21 */     return entity.func_152113_b();
/*    */   }
/*    */   
/*    */   public GameProfile newGameProfile(String UUIDid, String name)
/*    */   {
/* 26 */     return new GameProfile(UUID.randomUUID(), name);
/*    */   }
/*    */   
/*    */   public void notifyAdmins(ICommandSender p_152374_0_, ICommand p_152374_1_, int p_152374_2_, String p_152374_3_, Object... p_152374_4_)
/*    */   {
/* 31 */     CommandBase.func_152374_a(p_152374_0_, p_152374_1_, p_152374_2_, p_152374_3_, p_152374_4_);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/wrapper/mc1710/W_CCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
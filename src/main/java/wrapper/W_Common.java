/*    */ package wrapper;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import cpw.mods.fml.common.Loader;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.IEntityOwnable;
/*    */ import net.minecraft.entity.passive.EntityTameable;
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
/*    */ public class W_Common
/*    */ {
/* 21 */   private static final W_ICommon instance = ;
/*    */   
/*    */   private static W_ICommon getInstance()
/*    */   {
/* 25 */     String VER = Loader.instance().getMCVersionString();
/* 26 */     if (VER.endsWith("1.7.2"))
/*    */     {
/* 28 */       return new wrapper.mc172.W_CCommon();
/*    */     }
/* 30 */     if (VER.endsWith("1.7.10"))
/*    */     {
/* 32 */       return new wrapper.mc1710.W_CCommon();
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */   
/*    */   public static void setOwner(EntityTameable entity, String name)
/*    */   {
/* 39 */     instance.setOwner(entity, name);
/*    */   }
/*    */   
/*    */   public static String getOwnerName(IEntityOwnable entity) {
/* 43 */     String ownerName = instance.getOwnerName(entity);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 49 */     return ownerName != null ? ownerName : "";
/*    */   }
/*    */   
/*    */   public static GameProfile newGameProfile(String UUIDid, String name)
/*    */   {
/* 54 */     return instance.newGameProfile(UUIDid, name);
/*    */   }
/*    */   
/*    */   public static void notifyAdmins(ICommandSender sender, ICommand cmd, int p_152374_2_, String s, Object... p_152374_4_)
/*    */   {
/* 59 */     instance.notifyAdmins(sender, cmd, p_152374_2_, s, p_152374_4_);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/wrapper/W_Common.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
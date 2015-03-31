/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class LMM_ProxyCommon { public void init() {}
/*    */   
/*    */   public void onItemPickup(LMM_EntityLittleMaidAvatar lmm_EntityLittleMaidAvatar, Entity entity, int i) {}
/*    */   
/*    */   public void onCriticalHit(LMM_EntityLittleMaidAvatar pAvatar, Entity par1Entity) {}
/*    */   
/*    */   public void onEnchantmentCritical(LMM_EntityLittleMaidAvatar pAvatar, Entity par1Entity) {}
/*    */   
/*    */   public void clientCustomPayload(network.W_Message var2) {}
/*    */   
/* 15 */   public net.minecraft.entity.player.EntityPlayer getClientPlayer() { return null; }
/*    */   
/*    */   public void loadSounds() {}
/*    */   
/*    */   public boolean isSinglePlayer() {
/* 20 */     return net.minecraft.server.MinecraftServer.getServer().isSinglePlayer();
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_ProxyCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*    */ package littleMaidMobX;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*    */ 
/*    */ public class LMM_EventHook
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onEntityItemPickupEvent(EntityItemPickupEvent event)
/*    */   {
/* 11 */     if ((event.entityPlayer instanceof LMM_EntityLittleMaidAvatar))
/*    */     {
/* 13 */       if ((event.item != null) && (LMM_LittleMaidMobX.isMaidIgnoreItem(event.item.getEntityItem())))
/*    */       {
/* 15 */         event.setCanceled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EventHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*    */ package littleMaidMobX;
/*    */ 
/*    */ import cpw.mods.fml.common.network.IGuiHandler;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LMM_GuiCommonHandler
/*    */   implements IGuiHandler
/*    */ {
/* 16 */   public static LMM_EntityLittleMaid maidClient = null;
/* 17 */   public static LMM_EntityLittleMaid maidServer = null;
/*    */   
/*    */   public static final int GUI_ID_INVVENTORY = 0;
/*    */   
/*    */   public static final int GUI_ID_IFF = 1;
/*    */   
/*    */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*    */   {
/* 25 */     Object o = null;
/* 26 */     switch (ID)
/*    */     {
/*    */     case 0: 
/* 29 */       if (maidServer != null)
/*    */       {
/* 31 */         o = new LMM_ContainerInventory(player.inventory, maidServer);
/* 32 */         maidServer = null;
/*    */       }
/*    */       
/*    */ 
/*    */       break;
/*    */     }
/*    */     
/*    */     
/* 40 */     return o;
/*    */   }
/*    */   
/*    */ 
/*    */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
/*    */   {
/* 46 */     Object o = null;
/* 47 */     switch (ID)
/*    */     {
/*    */     case 0: 
/* 50 */       if (maidClient != null)
/*    */       {
/* 52 */         o = new LMM_GuiInventory(player, maidClient);
/* 53 */         maidClient = null;
/*    */       }
/*    */       
/*    */       break;
/*    */     case 1: 
/* 58 */       o = new LMM_GuiIFF(player.worldObj, maidClient);
/*    */     }
/*    */     
/* 61 */     return o;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_GuiCommonHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
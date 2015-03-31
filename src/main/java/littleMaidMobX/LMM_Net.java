/*     */ package littleMaidMobX;
/*     */ 
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityTracker;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import network.W_Message;
/*     */ import network.W_Network;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_Net
/*     */ {
/*     */   public static void sendToAllEClient(LMM_EntityLittleMaid pEntity, byte[] pData)
/*     */   {
/*  27 */     MMM_Helper.setInt(pData, 1, pEntity.getEntityId());
/*     */     
/*  29 */     EntityTracker et = ((WorldServer)pEntity.worldObj).getEntityTracker();
/*  30 */     for (EntityPlayer player : et.getTrackingPlayers(pEntity))
/*     */     {
/*  32 */       W_Network.sendPacketToPlayer(2, player, pData);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void sendToClient(EntityPlayer player, byte[] pData)
/*     */   {
/*  48 */     W_Network.sendPacketToPlayer(2, player, pData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void sendToEServer(LMM_EntityLittleMaid pEntity, byte[] pData)
/*     */   {
/*  57 */     MMM_Helper.setInt(pData, 1, pEntity.getEntityId());
/*  58 */     W_Network.sendPacketToServer(2, pData);
/*     */     
/*  60 */     LMM_LittleMaidMobX.Debug(String.format("LMM|Upd:send:%2x:%d", new Object[] { Byte.valueOf(pData[0]), Integer.valueOf(pEntity.getEntityId()) }), new Object[0]);
/*     */   }
/*     */   
/*     */   public static void sendToServer(byte[] pData) {
/*  64 */     W_Network.sendPacketToServer(2, pData);
/*     */     
/*  66 */     LMM_LittleMaidMobX.Debug(String.format("LMM|Upd:%2x:NOEntity", new Object[] { Byte.valueOf(pData[0]) }), new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void saveIFF()
/*     */   {
/*  73 */     sendToServer(new byte[] { 6 });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static LMM_EntityLittleMaid getLittleMaid(byte[] pData, int pIndex, World pWorld)
/*     */   {
/*  81 */     Entity lentity = MMM_Helper.getEntity(pData, pIndex, pWorld);
/*  82 */     if ((lentity instanceof LMM_EntityLittleMaid))
/*     */     {
/*  84 */       return (LMM_EntityLittleMaid)lentity;
/*     */     }
/*     */     
/*     */ 
/*  88 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void serverCustomPayload(EntityPlayer playerEntity, W_Message pPayload)
/*     */   {
/*  97 */     byte lmode = pPayload.data[0];
/*  98 */     int leid = 0;
/*  99 */     LMM_EntityLittleMaid lemaid = null;
/* 100 */     if ((lmode & 0x80) != 0) {
/* 101 */       leid = MMM_Helper.getInt(pPayload.data, 1);
/* 102 */       lemaid = getLittleMaid(pPayload.data, 1, playerEntity.worldObj);
/* 103 */       if (lemaid == null) return;
/*     */     }
/* 105 */     LMM_LittleMaidMobX.Debug(String.format("LMM|Upd Srv Call[%2x:%d].", new Object[] { Byte.valueOf(lmode), Integer.valueOf(leid) }), new Object[0]);
/*     */     
/*     */     int lval;
/*     */     
/*     */     int lindex;
/*     */     String lname;
/* 111 */     switch (lmode)
/*     */     {
/*     */ 
/*     */     case -128: 
/* 115 */       lemaid.maidInventory.clearChanged();
/* 116 */       for (LMM_SwingStatus lswing : lemaid.mstatSwingStatus) {
/* 117 */         lswing.lastIndex = -1;
/*     */       }
/* 119 */       break;
/*     */     
/*     */ 
/*     */ 
/*     */     case 2: 
/* 124 */       int lcolor2 = pPayload.data[1];
/* 125 */       if (!playerEntity.capabilities.isCreativeMode) {
/* 126 */         for (int li = 0; li < playerEntity.inventory.mainInventory.length; li++) {
/* 127 */           ItemStack lis = playerEntity.inventory.mainInventory[li];
/* 128 */           if ((lis != null) && (lis.getItem() == Items.dye) && 
/* 129 */             (lis.getItemDamage() == 15 - lcolor2)) {
/* 130 */             MMM_Helper.decPlayerInventory(playerEntity, li, 1);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       break;
/*     */     case 4: 
/* 139 */       lval = pPayload.data[1];
/* 140 */       lindex = MMM_Helper.getInt(pPayload.data, 2);
/* 141 */       lname = MMM_Helper.getStr(pPayload.data, 6);
/* 142 */       LMM_LittleMaidMobX.Debug("setIFF-SV user:%s %s(%d)=%d", new Object[] { MMM_Helper.getPlayerName(playerEntity), lname, Integer.valueOf(lindex), Integer.valueOf(lval) });
/* 143 */       LMM_IFF.setIFFValue(MMM_Helper.getPlayerName(playerEntity), lname, lval);
/* 144 */       sendIFFValue(playerEntity, lval, lindex);
/* 145 */       break;
/*     */     
/*     */     case 5: 
/* 148 */       lindex = MMM_Helper.getInt(pPayload.data, 1);
/* 149 */       lname = MMM_Helper.getStr(pPayload.data, 5);
/* 150 */       lval = LMM_IFF.getIFF(MMM_Helper.getPlayerName(playerEntity), lname, playerEntity.worldObj);
/* 151 */       LMM_LittleMaidMobX.Debug("getIFF-SV user:%s %s(%d)=%d", new Object[] { MMM_Helper.getPlayerName(playerEntity), lname, Integer.valueOf(lindex), Integer.valueOf(lval) });
/* 152 */       sendIFFValue(playerEntity, lval, lindex);
/* 153 */       break;
/*     */     
/*     */     case 6: 
/* 156 */       LMM_IFF.saveIFF(MMM_Helper.getPlayerName(playerEntity));
/* 157 */       if (!playerEntity.worldObj.isRemote) {
/* 158 */         LMM_IFF.saveIFF("");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static void sendIFFValue(EntityPlayer player, int pValue, int pIndex)
/*     */   {
/* 172 */     byte[] ldata = { 4, 0, 0, 0, 0, 0 };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 177 */     ldata[1] = ((byte)pValue);
/* 178 */     MMM_Helper.setInt(ldata, 2, pIndex);
/* 179 */     sendToClient(player, ldata);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_Net.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
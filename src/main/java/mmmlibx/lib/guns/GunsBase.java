/*     */ package mmmlibx.lib.guns;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GunsBase
/*     */ {
/*  20 */   public static ArrayList<EntityPlayer> playerList = new ArrayList();
/*  21 */   public static boolean isDebugMessage = true;
/*     */   
/*  23 */   protected static List<ItemGunsBase> appendList = new ArrayList();
/*     */   
/*     */ 
/*     */   public static void Debug(String pText, Object... pData)
/*     */   {
/*  28 */     if (isDebugMessage) {
/*  29 */       System.out.println(String.format("GunsBase-" + pText, pData));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void init()
/*     */   {
/*  35 */     GunsBase lgunsbase = new GunsBase();
/*  36 */     FMLCommonHandler.instance().bus().register(lgunsbase);
/*  37 */     MinecraftForge.EVENT_BUS.register(lgunsbase);
/*     */   }
/*     */   
/*     */   public static void setholdSight(EntityPlayer pPlayer) {
/*  41 */     playerList.add(pPlayer);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setUncheckedItemStack(ItemStack pGun, EntityPlayer pPlayer)
/*     */   {
/*     */     try
/*     */     {
/*  67 */       Class<?> lclass = ReflectionHelper.getClass(pGun.getClass().getClassLoader(), new String[] { "net.minecraft.entity.EntityLivingBase" });
/*  68 */       Field lfield = ReflectionHelper.findField(lclass, new String[] { "previousEquipment", "field_82180_bT" });
/*  69 */       ItemStack[] lequipments = (ItemStack[])lfield.get(pPlayer);
/*  70 */       lequipments[0] = pGun.copy();
/*  71 */       lfield.set(pPlayer, lequipments);
/*     */       
/*  73 */       if ((pPlayer instanceof EntityPlayer)) {
/*  74 */         Container lctr = pPlayer.openContainer;
/*  75 */         for (int li = 0; li < lctr.inventorySlots.size(); li++) {
/*  76 */           ItemStack lis = lctr.getSlot(li).getStack();
/*  77 */           if (lis == pGun) {
/*  78 */             lctr.inventoryItemStacks.set(li, pGun.copy());
/*  79 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/*  84 */       e.printStackTrace();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void appendItem(ItemGunsBase pItem)
/*     */   {
/* 129 */     appendList.add(pItem);
/*     */   }
/*     */   
/*     */   public static void initAppend() {
/* 133 */     for (ItemGunsBase li : appendList) {
/* 134 */       li.init();
/*     */     }
/* 136 */     appendList.clear();
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/guns/GunsBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
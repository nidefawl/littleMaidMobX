/*    */ package zabuton;
/*    */ 
/*    */ import cpw.mods.fml.common.Mod;
/*    */ import cpw.mods.fml.common.Mod.EventHandler;
/*    */ import cpw.mods.fml.common.SidedProxy;
/*    */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*    */ import cpw.mods.fml.common.registry.EntityRegistry;
/*    */ import cpw.mods.fml.common.registry.GameRegistry;
/*    */ import java.io.PrintStream;
/*    */ import net.minecraft.block.BlockDispenser;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IRegistry;
/*    */ 
/*    */ 
/*    */ @Mod(modid="zabuton", name="zabuton", dependencies="required-after:Forge@[10.12.2.1121,)")
/*    */ public class VZN_Zabuton
/*    */ {
/* 22 */   public static boolean isDebugMessage = true;
/*    */   
/*    */   public static Item zabuton;
/*    */   
/*    */   public static final String DOMAIN = "zabuton";
/*    */   
/*    */   @SidedProxy(clientSide="zabuton.VZN_ProxyClient", serverSide="zabuton.VZN_ProxyCommon")
/*    */   public static VZN_ProxyCommon proxy;
/*    */   
/*    */ 
/*    */   public static void Debug(String pText, Object... pData)
/*    */   {
/* 34 */     if (isDebugMessage) {
/* 35 */       System.out.println(String.format("Zabuton-" + pText, pData));
/*    */     }
/*    */   }
/*    */   
/*    */   public String getPriorities() {
/* 40 */     return "required-after:mod_MMM_MMMLib";
/*    */   }
/*    */   
/*    */   public String getVersion() {
/* 44 */     return "1.7.2";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @Mod.EventHandler
/*    */   public void PreInit(FMLPreInitializationEvent evt)
/*    */   {
/* 53 */     zabuton = new VZN_ItemZabuton();
/* 54 */     zabuton.setUnlocalizedName("zabuton:zabuton");
/* 55 */     zabuton.setTextureName("zabuton:zabuton");
/* 56 */     zabuton.setCreativeTab(CreativeTabs.tabTransport);
/* 57 */     GameRegistry.registerItem(zabuton, "zabuton");
/* 58 */     for (int i = 0; i < 16; i++)
/*    */     {
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
/* 70 */       GameRegistry.addRecipe(new ItemStack(zabuton, 1, 15 - i), new Object[] { "s ", "##", Character.valueOf('s'), Items.string, Character.valueOf('#'), new ItemStack(Blocks.wool, 1, i) });
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 77 */     EntityRegistry.registerModEntity(VZN_EntityZabuton.class, "zabuton", 0, this, 80, 3, true);
/* 78 */     proxy.RegistRenderer();
/*    */     
/* 80 */     BlockDispenser.dispenseBehaviorRegistry.putObject(zabuton, new VZN_BehaviorZabutonDispense());
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_Zabuton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
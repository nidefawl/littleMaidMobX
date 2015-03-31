/*    */ package mmmlibx.lib.multiModel.MMMLoader;
/*    */ 
/*    */ import com.google.common.eventbus.EventBus;
/*    */ import cpw.mods.fml.common.DummyModContainer;
/*    */ import cpw.mods.fml.common.LoadController;
/*    */ import cpw.mods.fml.common.ModMetadata;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class MMMModContainer
/*    */   extends DummyModContainer
/*    */ {
/*    */   public MMMModContainer()
/*    */   {
/* 14 */     super(new ModMetadata());
/* 15 */     ModMetadata lmeta = getMetadata();
/*    */     
/* 17 */     lmeta.modId = "OldModelLoader";
/* 18 */     lmeta.name = "OldModelLoader";
/* 19 */     lmeta.version = "1.0";
/* 20 */     lmeta.authorList = Arrays.asList(new String[] { "MMM" });
/* 21 */     lmeta.description = "The MultiModel before 1.6.2 is read.";
/* 22 */     lmeta.url = "";
/* 23 */     lmeta.credits = "";
/* 24 */     setEnabledState(true);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean registerBus(EventBus bus, LoadController controller)
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public Class<?> getCustomResourcePackClass()
/*    */   {
/* 36 */     return MMMResourcePack.class;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/MMMLoader/MMMModContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
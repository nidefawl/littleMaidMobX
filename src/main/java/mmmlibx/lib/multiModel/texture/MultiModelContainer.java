/*    */ package mmmlibx.lib.multiModel.texture;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import mmmlibx.lib.multiModel.model.AbstractModelBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MultiModelContainer
/*    */ {
/* 19 */   public int[] defaultVisivles = new int[16];
/*    */   
/*    */ 
/*    */   protected AbstractModelBase[] defaultModel;
/*    */   
/*    */ 
/*    */   protected Map<Integer, AbstractModelBase[]> models;
/*    */   
/*    */   protected Map<Integer, ResourceLocation> textures;
/*    */   
/*    */   protected boolean isDecodeJSON;
/*    */   
/*    */ 
/*    */   public MultiModelContainer()
/*    */   {
/* 34 */     this.models = new HashMap();
/* 35 */     this.textures = new HashMap();
/* 36 */     this.isDecodeJSON = false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean loadJSON(FileInputStream pStream)
/*    */   {
/* 45 */     this.isDecodeJSON = true;
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public void addTexture(int pIndex, ResourceLocation pResource) {
/* 50 */     this.textures.put(Integer.valueOf(pIndex), pResource);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ResourceLocation getTexture(int pIndex)
/*    */   {
/* 59 */     return (ResourceLocation)this.textures.get(Integer.valueOf(pIndex));
/*    */   }
/*    */   
/*    */   public AbstractModelBase[] getModelClass(int pIndex) {
/* 63 */     if (this.models.containsKey(Integer.valueOf(pIndex))) {
/* 64 */       return (AbstractModelBase[])this.models.get(Integer.valueOf(pIndex));
/*    */     }
/* 66 */     return this.defaultModel;
/*    */   }
/*    */   
/* 69 */   public AbstractModelBase[] getModelClass() { return this.defaultModel; }
/*    */   
/*    */   public MultiModelEntry getMultiModel()
/*    */   {
/* 73 */     return new MultiModelEntry();
/*    */   }
/*    */   
/*    */   public int getTextureCount() {
/* 77 */     return this.textures.size();
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/texture/MultiModelContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
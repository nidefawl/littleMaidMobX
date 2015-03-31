/*    */ package mmmlibx.lib;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.entity.RenderItem;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class MMM_RenderItem
/*    */   extends RenderItem
/*    */ {
/*    */   private Random random;
/*    */   
/*    */   public MMM_RenderItem()
/*    */   {
/* 17 */     this.random = new Random();
/*    */   }
/*    */   
/*    */   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
/*    */   {
/* 22 */     if ((entity instanceof EntityItem)) {
/* 23 */       EntityItem ei = (EntityItem)entity;
/* 24 */       Item litem = ei.getEntityItem().getItem();
/* 25 */       if ((MMM_ItemRenderManager.isEXRender(litem)) && 
/* 26 */         (MMM_ItemRenderManager.getEXRender(litem).renderItemWorld(ei, d, d1, d2, f, f1))) {
/* 27 */         return;
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 32 */     super.doRender(entity, d, d1, d2, f, f1);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_RenderItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
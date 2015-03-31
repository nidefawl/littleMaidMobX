/*    */ package mmmlibx.lib;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class MMM_ItemRendererForge implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*    */   {
/* 16 */     if ((item == null) || (!MMM_ItemRenderManager.isEXRender(item.getItem()))) { return false;
/*    */     }
/* 18 */     MMM_ItemRenderManager lirm = MMM_ItemRenderManager.getEXRender(item.getItem());
/*    */     
/*    */ 
/* 21 */     switch (type) {
/*    */     case ENTITY: 
/* 23 */       return lirm.isRenderItemWorld(item);
/*    */     case EQUIPPED: 
/* 25 */       return lirm.isRenderItem(item);
/*    */     case EQUIPPED_FIRST_PERSON: 
/* 27 */       return lirm.isRenderItemInFirstPerson(item);
/*    */     }
/*    */     
/*    */     
/*    */ 
/* 32 */     return false;
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*    */   {
/* 42 */     MMM_ItemRenderManager lirm = MMM_ItemRenderManager.getEXRender(item.getItem());
/* 43 */     switch (type) {
/*    */     case ENTITY: 
/* 45 */       EntityItem lei = (EntityItem)data[1];
/* 46 */       lirm.renderItemWorld(lei, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 47 */       break;
/*    */     
/*    */     case EQUIPPED: 
/* 50 */       GL11.glScalef(0.625F, -0.625F, 0.625F);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 55 */       GL11.glTranslatef(1.4F, -0.35F, -0.2F);
/* 56 */       GL11.glRotatef(135.0F, 0.0F, 0.0F, 1.0F);
/* 57 */       GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*    */       
/* 59 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 60 */       GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
/* 61 */       GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
/*    */       
/* 63 */       renderItem(item, (EntityLivingBase)data[1], lirm);
/* 64 */       break;
/*    */     case EQUIPPED_FIRST_PERSON: 
/* 66 */       Client.setTexture(lirm.getRenderTexture(item));
/* 67 */       GL11.glTranslatef(1.5F, 0.1F, 0.3F);
/*    */       
/*    */ 
/* 70 */       GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/* 71 */       GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
/* 72 */       GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
/* 73 */       GL11.glEnable(3008);
/* 74 */       GL11.glEnable(32826);
/*    */       
/* 76 */       renderItem(item, (EntityLivingBase)data[1], lirm);
/* 77 */       break;
/*    */     }
/*    */     
/*    */   }
/*    */   
/*    */ 
/*    */   protected void renderItem(ItemStack item, EntityLivingBase pEntity, MMM_ItemRenderManager pItemRenderManager)
/*    */   {
/* 85 */     if (item.getItem().requiresMultipleRenderPasses()) {
/* 86 */       pItemRenderManager.renderItemLocal(pEntity, item, 0);
/* 87 */       float var9 = 1.0F;
/* 88 */       int var28 = item.getItem().getColorFromItemStack(item, 1);
/* 89 */       float lcr = (var28 >> 16 & 0xFF) / 255.0F;
/* 90 */       float lcg = (var28 >> 8 & 0xFF) / 255.0F;
/* 91 */       float lcb = (var28 & 0xFF) / 255.0F;
/* 92 */       GL11.glColor4f(var9 * lcr, var9 * lcg, var9 * lcb, 1.0F);
/* 93 */       pItemRenderManager.renderItemLocal(pEntity, item, 1);
/*    */     } else {
/* 95 */       pItemRenderManager.renderItemLocal(pEntity, item, 0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ItemRendererForge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
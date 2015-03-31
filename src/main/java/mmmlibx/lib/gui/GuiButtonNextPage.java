/*    */ package mmmlibx.lib.gui;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiButtonNextPage
/*    */   extends GuiButton
/*    */ {
/* 16 */   private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
/*    */   
/*    */ 
/*    */   private final boolean nextPage;
/*    */   
/*    */ 
/*    */ 
/*    */   public GuiButtonNextPage(int par1, int par2, int par3, boolean par4)
/*    */   {
/* 25 */     super(par1, par2, par3, 23, 13, "");
/* 26 */     this.nextPage = par4;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void drawButton(Minecraft par1Minecraft, int par2, int par3)
/*    */   {
/* 34 */     if (this.visible)
/*    */     {
/* 36 */       boolean flag = (par2 >= this.xPosition) && (par3 >= this.yPosition) && (par2 < this.xPosition + this.width) && (par3 < this.yPosition + this.height);
/* 37 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 38 */       par1Minecraft.getTextureManager().bindTexture(bookGuiTextures);
/* 39 */       int k = 0;
/* 40 */       int l = 192;
/*    */       
/* 42 */       if (flag)
/*    */       {
/* 44 */         k += 23;
/*    */       }
/*    */       
/* 47 */       if (!this.nextPage)
/*    */       {
/* 49 */         l += 13;
/*    */       }
/*    */       
/* 52 */       drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/gui/GuiButtonNextPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
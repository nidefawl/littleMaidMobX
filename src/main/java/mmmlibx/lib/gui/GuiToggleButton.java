/*    */ package mmmlibx.lib.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ public class GuiToggleButton
/*    */   extends GuiButton
/*    */ {
/*  8 */   public boolean isDown = false;
/*    */   
/*    */   public GuiToggleButton(int par1, int par2, int par3, String par4Str) {
/* 11 */     super(par1, par2, par3, par4Str);
/*    */   }
/*    */   
/*    */   public GuiToggleButton(int par1, int par2, int par3, int par4, int par5, String par6Str)
/*    */   {
/* 16 */     super(par1, par2, par3, par4, par5, par6Str);
/*    */   }
/*    */   
/*    */   public int getHoverState(boolean par1)
/*    */   {
/* 21 */     if ((!this.enabled) || (this.isDown))
/* 22 */       return 0;
/* 23 */     if (par1) {
/* 24 */       return 2;
/*    */     }
/* 26 */     return 1;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/gui/GuiToggleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
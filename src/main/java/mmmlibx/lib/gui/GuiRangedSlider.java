/*    */ package mmmlibx.lib.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiRangedSlider
/*    */   extends GuiButton
/*    */ {
/*    */   public String prefixStr;
/*    */   public float sliderValue;
/*    */   public boolean dragging;
/* 13 */   public String strFormat = "%s : %.2f";
/* 14 */   public float sliderMultiply = 1.0F;
/* 15 */   public float sliderOffset = 0.0F;
/* 16 */   public float sliderStep = 0.0F;
/*    */   
/*    */   public GuiRangedSlider(int i, int j, int k, String s, float f)
/*    */   {
/* 20 */     super(i, j, k, 100, 20, "");
/* 21 */     this.sliderValue = 1.0F;
/* 22 */     this.dragging = false;
/* 23 */     this.sliderValue = f;
/* 24 */     this.prefixStr = s;
/*    */   }
/*    */   
/*    */   public GuiRangedSlider(int i, int j, int k, String s, float f, float m, float o) {
/* 28 */     this(i, j, k, s, f);
/* 29 */     this.sliderMultiply = m;
/* 30 */     this.sliderOffset = o;
/*    */   }
/*    */   
/*    */   public int getHoverState(boolean p_146114_1_)
/*    */   {
/* 35 */     return 0;
/*    */   }
/*    */   
/*    */   protected void mouseDragged(Minecraft minecraft, int i, int j)
/*    */   {
/* 40 */     if (!this.visible) {
/* 41 */       return;
/*    */     }
/* 43 */     if (this.dragging) {
/* 44 */       this.sliderValue = ((i - (this.xPosition + 4)) / (this.width - 8));
/* 45 */       if (this.sliderStep > 0.0F) {
/* 46 */         this.sliderValue = ((int)(this.sliderValue / this.sliderStep) * this.sliderStep);
/*    */       }
/* 48 */       if (this.sliderValue < 0.0F) {
/* 49 */         this.sliderValue = 0.0F;
/*    */       }
/* 51 */       if (this.sliderValue > 1.0F) {
/* 52 */         this.sliderValue = 1.0F;
/*    */       }
/* 54 */       setDisplayString();
/*    */     }
/* 56 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 57 */     drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/*    */     
/* 59 */     drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean mousePressed(Minecraft minecraft, int i, int j)
/*    */   {
/* 65 */     if (super.mousePressed(minecraft, i, j))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 74 */       this.dragging = true;
/* 75 */       return true;
/*    */     }
/* 77 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void mouseReleased(int i, int j)
/*    */   {
/* 83 */     this.dragging = false;
/*    */   }
/*    */   
/*    */   public float getSliderValue() {
/* 87 */     return this.sliderValue * this.sliderMultiply + this.sliderOffset;
/*    */   }
/*    */   
/*    */   public GuiRangedSlider setDisplayString() {
/* 91 */     this.displayString = String.format(this.strFormat, new Object[] { this.prefixStr, Float.valueOf(getSliderValue()) });
/* 92 */     return this;
/*    */   }
/*    */   
/*    */   public GuiRangedSlider setStrFormat(String s) {
/* 96 */     this.strFormat = s;
/* 97 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/gui/GuiRangedSlider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
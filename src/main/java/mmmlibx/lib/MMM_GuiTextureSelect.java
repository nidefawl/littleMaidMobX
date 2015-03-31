/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MMM_GuiTextureSelect
/*     */   extends GuiScreen
/*     */ {
/*  17 */   private String screenTitle = "Texture Select";
/*     */   protected GuiScreen owner;
/*     */   protected MMM_GuiTextureSlot selectPanel;
/*  20 */   protected GuiButton[] modeButton = new GuiButton[2];
/*     */   public ITextureEntity target;
/*     */   public int canSelectColor;
/*     */   public int selectColor;
/*     */   protected boolean toServer;
/*     */   
/*     */   public MMM_GuiTextureSelect(GuiScreen pOwner, ITextureEntity pTarget, int pColor, boolean pToServer)
/*     */   {
/*  28 */     this.owner = pOwner;
/*  29 */     this.target = pTarget;
/*  30 */     this.canSelectColor = pColor;
/*  31 */     this.selectColor = pTarget.getColor();
/*  32 */     this.toServer = pToServer;
/*     */   }
/*     */   
/*     */   public void initGui()
/*     */   {
/*  37 */     this.selectPanel = new MMM_GuiTextureSlot(this);
/*  38 */     this.selectPanel.registerScrollButtons(3, 4);
/*  39 */     this.buttonList.add(this.modeButton[0] = new GuiButton(100, this.width / 2 - 55, this.height - 55, 80, 20, "Texture"));
/*  40 */     this.buttonList.add(this.modeButton[1] = new GuiButton(101, this.width / 2 + 30, this.height - 55, 80, 20, "Armor"));
/*  41 */     this.buttonList.add(new GuiButton(200, this.width / 2 - 10, this.height - 30, 120, 20, "Select"));
/*  42 */     this.modeButton[0].enabled = false;
/*     */   }
/*     */   
/*     */   protected void keyTyped(char par1, int par2)
/*     */   {
/*  47 */     if (par2 == 1) {
/*  48 */       this.mc.displayGuiScreen(this.owner);
/*     */     }
/*     */   }
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3)
/*     */   {
/*  54 */     drawDefaultBackground();
/*  55 */     this.selectPanel.drawScreen(par1, par2, par3);
/*  56 */     drawCenteredString(this.mc.fontRenderer, StatCollector.translateToLocal(this.screenTitle), this.width / 2, 4, 16777215);
/*     */     
/*  58 */     super.drawScreen(par1, par2, par3);
/*     */     
/*  60 */     GL11.glPushMatrix();
/*  61 */     GL11.glEnable(32826);
/*  62 */     GL11.glEnable(2903);
/*  63 */     GL11.glEnable(2929);
/*  64 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*  65 */     RenderHelper.enableGUIStandardItemLighting();
/*  66 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
/*  67 */     MMM_TextureBox lbox = this.selectPanel.getSelectedBox();
/*  68 */     GL11.glTranslatef(this.width / 2 - 115.0F, this.height - 5.0F, 100.0F);
/*  69 */     GL11.glScalef(60.0F, -60.0F, 60.0F);
/*  70 */     this.selectPanel.entity.renderYawOffset = -25.0F;
/*  71 */     this.selectPanel.entity.rotationYawHead = -10.0F;
/*     */     
/*  73 */     if (this.selectPanel.mode) {
/*  74 */       this.selectPanel.entity.textureData.textureBox[0] = MMM_GuiTextureSlot.blankBox;
/*  75 */       this.selectPanel.entity.textureData.textureBox[1] = lbox;
/*  76 */       this.selectPanel.entity.setTextureNames("default");
/*     */     } else {
/*  78 */       this.selectPanel.entity.textureData.textureBox[0] = lbox;
/*  79 */       this.selectPanel.entity.textureData.textureBox[1] = MMM_GuiTextureSlot.blankBox;
/*  80 */       this.selectPanel.entity.setColor(this.selectColor);
/*  81 */       this.selectPanel.entity.setTextureNames();
/*     */     }
/*  83 */     RenderManager.instance.renderEntityWithPosYaw(this.selectPanel.entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/*  84 */     for (int li = 0; li < 16; li++) {
/*  85 */       if (lbox.hasColor(li)) {
/*     */         break;
/*     */       }
/*     */     }
/*  89 */     GL11.glDisable(32826);
/*  90 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton)
/*     */   {
/*  95 */     switch (par1GuiButton.id) {
/*     */     case 100: 
/*  97 */       this.modeButton[0].enabled = false;
/*  98 */       this.modeButton[1].enabled = true;
/*  99 */       this.selectPanel.setMode(false);
/* 100 */       break;
/*     */     case 101: 
/* 102 */       this.modeButton[0].enabled = true;
/* 103 */       this.modeButton[1].enabled = false;
/* 104 */       this.selectPanel.setMode(true);
/* 105 */       break;
/*     */     case 200: 
/* 107 */       boolean lflag = false;
/* 108 */       this.target.setColor(this.selectColor);
/* 109 */       if (this.selectPanel.texsel[0] > -1) {
/* 110 */         this.target.getTextureBox()[0] = this.selectPanel.getSelectedBox(false);
/*     */       }
/* 112 */       if (this.selectPanel.texsel[1] > -1) {
/* 113 */         this.target.getTextureBox()[1] = this.selectPanel.getSelectedBox(true);
/*     */       }
/* 115 */       this.target.getTextureData().setTextureNames();
/* 116 */       if (this.toServer) {
/* 117 */         MMM_TextureManager.instance.postSetTexturePack(this.target, this.selectColor, this.target.getTextureBox());
/*     */       } else {
/* 119 */         MMM_TextureBox[] lboxs = new MMM_TextureBox[2];
/* 120 */         lboxs[0] = ((MMM_TextureBox)this.target.getTextureBox()[0]);
/* 121 */         lboxs[1] = ((MMM_TextureBox)this.target.getTextureBox()[1]);
/* 122 */         this.target.setTexturePackName(lboxs);
/*     */       }
/* 124 */       System.out.println(String.format("select: %d(%d/%s), %d(%d/%s)", new Object[] { Integer.valueOf(this.selectPanel.texsel[0]), Integer.valueOf(this.target.getTextureIndex()[0]), this.target.getTextureBox()[0].textureName, Integer.valueOf(this.selectPanel.texsel[1]), Integer.valueOf(this.target.getTextureIndex()[1]), this.target.getTextureBox()[1].textureName }));
/*     */       
/*     */ 
/* 127 */       this.mc.displayGuiScreen(this.owner);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_GuiTextureSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
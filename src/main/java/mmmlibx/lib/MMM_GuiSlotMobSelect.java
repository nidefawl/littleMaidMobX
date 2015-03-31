/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiSlot;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MMM_GuiSlotMobSelect
/*     */   extends GuiSlot
/*     */ {
/*     */   protected int selected;
/*     */   protected Minecraft mc;
/*     */   public MMM_GuiMobSelect ownerGui;
/*     */   
/*     */   public MMM_GuiSlotMobSelect(Minecraft pMinecraft, MMM_GuiMobSelect pOwner)
/*     */   {
/*  23 */     super(pMinecraft, pOwner.width, pOwner.height, 32, pOwner.height - 52, 36);
/*  24 */     this.mc = pMinecraft;
/*  25 */     this.ownerGui = pOwner;
/*  26 */     this.selected = -1;
/*     */   }
/*     */   
/*     */   protected int getSize()
/*     */   {
/*  31 */     return this.ownerGui.entityMap.size();
/*     */   }
/*     */   
/*     */   protected void elementClicked(int var1, boolean var2, int a, int b)
/*     */   {
/*  36 */     String s = this.ownerGui.entityMap.keySet().toArray()[var1].toString();
/*  37 */     EntityLivingBase lel = (EntityLivingBase)this.ownerGui.entityMap.get(s);
/*  38 */     this.ownerGui.clickSlot(var1, var2, s, lel);
/*  39 */     this.selected = var1;
/*     */   }
/*     */   
/*     */   protected boolean isSelected(int var1)
/*     */   {
/*  44 */     return var1 == this.selected;
/*     */   }
/*     */   
/*     */   protected void drawBackground()
/*     */   {
/*  49 */     this.ownerGui.drawDefaultBackground();
/*     */   }
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3)
/*     */   {
/*  54 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int a, int b)
/*     */   {
/*  62 */     String s = this.ownerGui.entityMap.keySet().toArray()[var1].toString();
/*  63 */     boolean lf = MMM_GuiMobSelect.exclusionList.contains(s);
/*  64 */     EntityLivingBase entityliving = lf ? null : (EntityLivingBase)this.ownerGui.entityMap.get(s);
/*     */     
/*     */ 
/*  67 */     this.ownerGui.drawSlot(var1, var2, var3, var4, var5, s, entityliving);
/*     */     
/*     */ 
/*  70 */     if (lf) {
/*  71 */       this.ownerGui.drawString(this.ownerGui.mc.fontRenderer, "NoImage", var2 + 15, var3 + 12, 16777215);
/*     */       
/*  73 */       return;
/*     */     }
/*  75 */     entityliving.setWorld(this.mc.theWorld);
/*     */     
/*     */ 
/*     */ 
/*  79 */     GL11.glEnable(2903);
/*  80 */     GL11.glPushMatrix();
/*  81 */     float f1 = 15.0F;
/*  82 */     if (entityliving.height > 2.0F) {
/*  83 */       f1 = f1 * 3.0F / entityliving.height;
/*     */     }
/*  85 */     float lxp = (var1 & 0x1) == 0 ? var2 + 30.0F : this.ownerGui.width - var2 - 30.0F;
/*  86 */     GL11.glTranslatef(lxp, var3 + 30.0F, 50.0F + f1);
/*  87 */     GL11.glScalef(-f1, f1, f1);
/*  88 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  89 */     float f5 = lxp - this.mouseX;
/*  90 */     float f6 = var3 + 30 - 10 - this.mouseY;
/*  91 */     GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
/*  92 */     RenderHelper.enableStandardItemLighting();
/*  93 */     GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/*  94 */     GL11.glRotatef(-(float)Math.atan(f6 / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
/*  95 */     entityliving.renderYawOffset = ((float)Math.atan(f5 / 40.0F) * 20.0F);
/*  96 */     entityliving.rotationYaw = ((float)Math.atan(f5 / 40.0F) * 40.0F);
/*  97 */     entityliving.rotationPitch = (-(float)Math.atan(f6 / 40.0F) * 20.0F);
/*  98 */     entityliving.prevRotationYawHead = entityliving.rotationYawHead;
/*  99 */     entityliving.rotationYawHead = entityliving.rotationYaw;
/* 100 */     GL11.glTranslatef(0.0F, entityliving.yOffset, 0.0F);
/* 101 */     RenderManager.instance.playerViewY = 180.0F;
/*     */     try {
/* 103 */       RenderManager.instance.renderEntityWithPosYaw(entityliving, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/*     */     }
/*     */     catch (Exception e) {
/* 106 */       MMM_GuiMobSelect.exclusionList.add(s);
/*     */     }
/*     */     
/* 109 */     GL11.glPopMatrix();
/* 110 */     RenderHelper.disableStandardItemLighting();
/* 111 */     GL11.glDisable(32826);
/* 112 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 113 */     GL11.glDisable(3553);
/* 114 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_GuiSlotMobSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
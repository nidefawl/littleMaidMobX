/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiSlot;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MMM_GuiTextureSlot
/*     */   extends GuiSlot
/*     */ {
/*     */   public MMM_GuiTextureSelect owner;
/*     */   public int selected;
/*     */   public MMM_EntitySelect entity;
/*     */   public List<MMM_TextureBox> indexTexture;
/*     */   public List<MMM_TextureBox> indexArmor;
/*     */   public boolean mode;
/*  26 */   public int[] texsel = new int[2];
/*     */   public int color;
/*     */   public int selectColor;
/*  29 */   private ItemStack[] armors = { new ItemStack(Items.leather_boots), new ItemStack(Items.leather_leggings), new ItemStack(Items.leather_chestplate), new ItemStack(Items.leather_helmet) };
/*     */   
/*     */ 
/*     */   protected boolean isContract;
/*     */   
/*     */ 
/*     */   protected static MMM_TextureBox blankBox;
/*     */   
/*     */ 
/*     */   public MMM_GuiTextureSlot(MMM_GuiTextureSelect pOwner)
/*     */   {
/*  40 */     super(pOwner.mc, pOwner.width, pOwner.height, 16, pOwner.height - 64, 36);
/*  41 */     this.owner = pOwner;
/*  42 */     this.entity = new MMM_EntitySelect(this.owner.mc.theWorld);
/*  43 */     this.color = this.owner.target.getColor();
/*  44 */     this.selectColor = -1;
/*  45 */     blankBox = new MMM_TextureBox();
/*  46 */     blankBox.models = new ModelMultiBase[] { null, null, null };
/*     */     
/*  48 */     this.texsel[0] = 0;
/*  49 */     this.texsel[1] = 0;
/*  50 */     this.indexTexture = new ArrayList();
/*  51 */     this.indexArmor = new ArrayList();
/*  52 */     this.isContract = this.owner.target.isContract();
/*  53 */     this.entity.setContract(this.isContract);
/*  54 */     MMM_TextureBoxBase[] ltbox = this.owner.target.getTextureBox();
/*  55 */     for (int li = 0; li < MMM_TextureManager.instance.getTextureCount(); li++) {
/*  56 */       MMM_TextureBox lbox = (MMM_TextureBox)MMM_TextureManager.getTextureList().get(li);
/*  57 */       if (this.isContract) {
/*  58 */         if (lbox.getContractColorBits() > 0) {
/*  59 */           this.indexTexture.add(lbox);
/*     */         }
/*     */       }
/*  62 */       else if (lbox.getWildColorBits() > 0) {
/*  63 */         this.indexTexture.add(lbox);
/*     */       }
/*     */       
/*  66 */       if (lbox.hasArmor()) {
/*  67 */         this.indexArmor.add(lbox);
/*     */       }
/*  69 */       if (lbox == ltbox[0]) {
/*  70 */         this.texsel[0] = (this.indexTexture.size() - 1);
/*     */       }
/*  72 */       if (lbox == ltbox[1]) {
/*  73 */         this.texsel[1] = (this.indexArmor.size() - 1);
/*     */       }
/*     */     }
/*  76 */     setMode(false);
/*     */   }
/*     */   
/*     */   protected int getSize()
/*     */   {
/*  81 */     return this.mode ? this.indexArmor.size() : this.indexTexture.size();
/*     */   }
/*     */   
/*     */   protected void elementClicked(int var1, boolean var2, int var3, int var4)
/*     */   {
/*  86 */     if (this.mode) {
/*  87 */       this.selected = var1;
/*  88 */       this.texsel[1] = var1;
/*     */     } else {
/*  90 */       MMM_TextureBox lbox = getSelectedBox(var1);
/*  91 */       if ((lbox.hasColor(this.selectColor, this.isContract)) && ((this.owner.canSelectColor & 1 << this.selectColor) > 0)) {
/*  92 */         this.selected = var1;
/*  93 */         this.texsel[0] = var1;
/*  94 */         this.owner.selectColor = this.selectColor;
/*  95 */       } else if (lbox.hasColor(this.color, this.isContract)) {
/*  96 */         this.selected = var1;
/*  97 */         this.texsel[0] = var1;
/*  98 */         this.owner.selectColor = this.color;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean isSelected(int var1)
/*     */   {
/* 105 */     return this.selected == var1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void drawBackground() {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7)
/*     */   {
/*     */     
/*     */     
/* 118 */     if (!this.mode) {
/* 119 */       for (int li = 0; li < 16; li++) {
/* 120 */         int lx = var2 + 15 + 12 * li;
/* 121 */         this.selectColor = ((this.mouseX - (var2 + 15)) / 12);
/* 122 */         if ((this.selectColor < 0) && (this.selectColor > 15)) {
/* 123 */           this.selectColor = -1;
/*     */         }
/* 125 */         if (this.color == li) {
/* 126 */           MMM_GuiTextureSelect.drawRect(lx, var3, lx + 11, var3 + 36, -2004344286);
/* 127 */         } else if (this.owner.selectColor == li) {
/* 128 */           MMM_GuiTextureSelect.drawRect(lx, var3, lx + 11, var3 + 36, -2011011550);
/* 129 */         } else if ((this.owner.canSelectColor & 1 << li) > 0) {
/* 130 */           MMM_GuiTextureSelect.drawRect(lx, var3, lx + 11, var3 + 36, -2011028856);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     MMM_TextureBox lbox;
/* 136 */     if (this.mode) {
/* 137 */       MMM_TextureBox lbox = (MMM_TextureBox)this.indexArmor.get(var1);
/* 138 */       this.entity.textureData.textureBox[0] = blankBox;
/* 139 */       this.entity.textureData.textureBox[1] = lbox;
/*     */     } else {
/* 141 */       lbox = (MMM_TextureBox)this.indexTexture.get(var1);
/* 142 */       this.entity.textureData.textureBox[0] = lbox;
/* 143 */       this.entity.textureData.textureBox[1] = blankBox;
/*     */     }
/* 145 */     MMM_TextureManager.instance.checkTextureBoxServer(lbox);
/* 146 */     GL11.glDisable(3042);
/*     */     
/* 148 */     this.owner.drawString(this.owner.mc.fontRenderer, lbox.textureName, var2 + 16, var3 + 25, -1);
/* 149 */     GL11.glTranslatef(var2 + 8.0F, var3 + 25.0F, 50.0F);
/* 150 */     GL11.glScalef(12.0F, -12.0F, 12.0F);
/* 151 */     this.entity.renderYawOffset = 30.0F;
/* 152 */     this.entity.rotationYawHead = 15.0F;
/* 153 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
/* 154 */     if (this.mode)
/*     */     {
/*     */ 
/* 157 */       GL11.glTranslatef(1.0F, 0.0F, 0.0F);
/* 158 */       Map<Integer, ResourceLocation> lmap = (Map)lbox.armors.get("default");
/* 159 */       if (lmap != null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 166 */         this.entity.setTextureNames("default");
/* 167 */         RenderManager.instance.renderEntityWithPosYaw(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/*     */       }
/* 169 */       for (String ls : MMM_TextureManager.armorFilenamePrefix) {
/* 170 */         GL11.glTranslatef(1.0F, 0.0F, 0.0F);
/* 171 */         if (lbox.armors.containsKey(ls))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 178 */           this.entity.setTextureNames(ls);
/* 179 */           RenderManager.instance.renderEntityWithPosYaw(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 180 */           Client.setLightmapTextureCoords(240);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 185 */       for (int li = 0; li < 16; li++) {
/* 186 */         GL11.glTranslatef(1.0F, 0.0F, 0.0F);
/* 187 */         if (lbox.hasColor(li, this.isContract)) {
/* 188 */           this.entity.setColor(li);
/* 189 */           this.entity.setContract(this.isContract);
/* 190 */           this.entity.setTextureNames();
/*     */           
/* 192 */           RenderManager.instance.renderEntityWithPosYaw(this.entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 193 */           Client.setLightmapTextureCoords(240);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 198 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public MMM_TextureBox getSelectedBox() {
/* 202 */     return getSelectedBox(this.selected);
/*     */   }
/*     */   
/*     */   public MMM_TextureBox getSelectedBox(int pIndex) {
/* 206 */     return this.mode ? (MMM_TextureBox)this.indexArmor.get(pIndex) : (MMM_TextureBox)this.indexTexture.get(pIndex);
/*     */   }
/*     */   
/*     */   public MMM_TextureBox getSelectedBox(boolean pMode) {
/* 210 */     return pMode ? (MMM_TextureBox)this.indexArmor.get(this.texsel[1]) : (MMM_TextureBox)this.indexTexture.get(this.texsel[0]);
/*     */   }
/*     */   
/*     */   public void setMode(boolean pFlag) {
/* 214 */     scrollBy(this.slotHeight * -getSize());
/* 215 */     if (pFlag) {
/* 216 */       this.selected = this.texsel[1];
/* 217 */       this.mode = true;
/* 218 */       this.entity.setCurrentItemOrArmor(1, this.armors[0]);
/* 219 */       this.entity.setCurrentItemOrArmor(2, this.armors[1]);
/* 220 */       this.entity.setCurrentItemOrArmor(3, this.armors[2]);
/* 221 */       this.entity.setCurrentItemOrArmor(4, this.armors[3]);
/*     */     } else {
/* 223 */       this.selected = this.texsel[0];
/* 224 */       this.mode = false;
/* 225 */       this.entity.setCurrentItemOrArmor(1, null);
/* 226 */       this.entity.setCurrentItemOrArmor(2, null);
/* 227 */       this.entity.setCurrentItemOrArmor(3, null);
/* 228 */       this.entity.setCurrentItemOrArmor(4, null);
/*     */     }
/* 230 */     scrollBy(this.slotHeight * this.selected);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_GuiTextureSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
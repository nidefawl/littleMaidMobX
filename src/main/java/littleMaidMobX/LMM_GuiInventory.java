/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mmmlibx.lib.Client;
/*     */ import mmmlibx.lib.MMM_TextureData;
/*     */ import mmmlibx.lib.gui.GuiButtonNextPage;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class LMM_GuiInventory extends GuiContainer
/*     */ {
/*     */   private Random rand;
/*     */   private IInventory upperChestInventory;
/*     */   private IInventory lowerChestInventory;
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   private int ySizebk;
/*     */   private int updateCounter;
/*     */   public LMM_EntityLittleMaid entitylittlemaid;
/*  41 */   public GuiButtonNextPage[] txbutton = new GuiButtonNextPage[4];
/*     */   
/*     */   public GuiButton selectbutton;
/*     */   public boolean isChangeTexture;
/*  45 */   protected static final ResourceLocation fguiTex = new ResourceLocation("lmmx", "textures/gui/container/littlemaidinventory.png");
/*     */   
/*     */ 
/*     */   public LMM_GuiInventory(EntityPlayer pPlayer, LMM_EntityLittleMaid elmaid)
/*     */   {
/*  50 */     super(new LMM_ContainerInventory(pPlayer.inventory, elmaid));
/*  51 */     this.rand = new Random();
/*  52 */     this.upperChestInventory = pPlayer.inventory;
/*  53 */     this.lowerChestInventory = elmaid.maidInventory;
/*  54 */     this.allowUserInput = false;
/*  55 */     this.updateCounter = 0;
/*  56 */     this.ySizebk = this.ySize;
/*  57 */     this.ySize = 207;
/*  58 */     this.isChangeTexture = true;
/*     */     
/*  60 */     this.entitylittlemaid = elmaid;
/*     */   }
/*     */   
/*     */ 
/*     */   public void initGui()
/*     */   {
/*  66 */     super.initGui();
/*  67 */     if (!this.entitylittlemaid.getActivePotionEffects().isEmpty()) {
/*  68 */       this.guiLeft = (160 + (this.width - this.xSize - 200) / 2);
/*     */     }
/*  70 */     this.buttonList.add(this.txbutton[0] = new GuiButtonNextPage(100, this.guiLeft + 25, this.guiTop + 7, false));
/*  71 */     this.buttonList.add(this.txbutton[1] = new GuiButtonNextPage(101, this.guiLeft + 55, this.guiTop + 7, true));
/*  72 */     this.buttonList.add(this.txbutton[2] = new GuiButtonNextPage(110, this.guiLeft + 25, this.guiTop + 47, false));
/*  73 */     this.buttonList.add(this.txbutton[3] = new GuiButtonNextPage(111, this.guiLeft + 55, this.guiTop + 47, true));
/*  74 */     this.buttonList.add(this.selectbutton = new GuiButton(200, this.guiLeft + 25, this.guiTop + 25, 53, 17, "select"));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2)
/*     */   {
/*  80 */     this.mc.fontRenderer.drawString(StatCollector.translateToLocal(this.lowerChestInventory.getInventoryName()), 8, 64, 4210752);
/*     */     
/*  82 */     this.mc.fontRenderer.drawString(StatCollector.translateToLocal(this.upperChestInventory.getInventoryName()), 8, 114, 4210752);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  88 */     this.mc.fontRenderer.drawString(StatCollector.translateToLocal("littleMaidMob.text.STATUS"), 86, 8, 4210752);
/*     */     
/*     */ 
/*  91 */     this.mc.fontRenderer.drawString(StatCollector.translateToLocal("littleMaidMob.mode.".concat(this.entitylittlemaid.getMaidModeString())), 86, 61, 4210752);
/*     */     
/*  93 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*     */ 
/*  96 */     int lj = 0;
/*  97 */     int lk = 0;
/*  98 */     GL11.glEnable(32826);
/*  99 */     GL11.glEnable(2903);
/* 100 */     GL11.glPushMatrix();
/* 101 */     GL11.glTranslatef(lj + 51, lk + 57, 50.0F);
/* 102 */     float f1 = 30.0F;
/* 103 */     GL11.glScalef(-f1, f1, f1);
/* 104 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 105 */     float f2 = this.entitylittlemaid.renderYawOffset;
/* 106 */     float f3 = this.entitylittlemaid.rotationYaw;
/* 107 */     float f4 = this.entitylittlemaid.rotationYawHead;
/* 108 */     float f5 = this.entitylittlemaid.rotationPitch;
/*     */     
/*     */ 
/* 111 */     float f8 = this.guiLeft + 51 - par1;
/* 112 */     float f9 = this.guiTop + 22 - par2;
/* 113 */     GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
/* 114 */     RenderHelper.enableStandardItemLighting();
/* 115 */     GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/* 116 */     GL11.glRotatef(-(float)Math.atan(f9 / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
/* 117 */     this.entitylittlemaid.renderYawOffset = ((float)Math.atan(f8 / 40.0F) * 20.0F);
/* 118 */     this.entitylittlemaid.rotationYawHead = (this.entitylittlemaid.rotationYaw = (float)Math.atan(f8 / 40.0F) * 40.0F);
/* 119 */     this.entitylittlemaid.rotationPitch = (-(float)Math.atan(f9 / 40.0F) * 20.0F);
/* 120 */     GL11.glTranslatef(0.0F, this.entitylittlemaid.yOffset, 0.0F);
/* 121 */     RenderManager.instance.playerViewY = 180.0F;
/* 122 */     RenderManager.instance.renderEntityWithPosYaw(this.entitylittlemaid, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 123 */     this.entitylittlemaid.renderYawOffset = f2;
/* 124 */     this.entitylittlemaid.rotationYaw = f3;
/* 125 */     this.entitylittlemaid.rotationYawHead = f4;
/* 126 */     this.entitylittlemaid.rotationPitch = f5;
/* 127 */     GL11.glPopMatrix();
/* 128 */     RenderHelper.disableStandardItemLighting();
/* 129 */     GL11.glDisable(32826);
/* 130 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
/* 131 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
/*     */   {
/* 137 */     ResourceLocation lrl = this.entitylittlemaid.textureData.getGUITexture();
/* 138 */     if (lrl == null) {
/* 139 */       lrl = fguiTex;
/*     */     }
/* 141 */     Client.setTexture(lrl);
/* 142 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 143 */     int lj = this.guiLeft;
/* 144 */     int lk = this.guiTop;
/* 145 */     drawTexturedModalRect(lj, lk, 0, 0, this.xSize, this.ySize);
/*     */     
/*     */ 
/* 148 */     displayDebuffEffects();
/*     */     
/*     */ 
/* 151 */     drawHeathArmor(0, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawHeathArmor(int par1, int par2)
/*     */   {
/* 212 */     boolean var3 = this.entitylittlemaid.hurtResistantTime / 3 % 2 == 1;
/*     */     
/* 214 */     if (this.entitylittlemaid.hurtResistantTime < 10) {
/* 215 */       var3 = false;
/*     */     }
/*     */     
/* 218 */     Client.setTexture(icons);
/* 219 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 221 */     int lhealth = MathHelper.ceiling_float_int(this.entitylittlemaid.getHealth());
/* 222 */     int llasthealth = MathHelper.ceiling_float_int(this.entitylittlemaid.prevHealth);
/* 223 */     this.rand.setSeed(this.updateCounter * 312871);
/* 224 */     boolean var6 = false;
/*     */     
/*     */ 
/*     */ 
/* 228 */     IAttributeInstance var10 = this.entitylittlemaid.getEntityAttribute(net.minecraft.entity.SharedMonsterAttributes.maxHealth);
/* 229 */     int var13 = par2 - 39;
/* 230 */     float var14 = (float)var10.getAttributeValue();
/* 231 */     float var15 = this.entitylittlemaid.getAbsorptionAmount();
/* 232 */     int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
/* 233 */     int var17 = Math.max(10 - (var16 - 2), 3);
/* 234 */     float var19 = var15;
/* 235 */     int var21 = -1;
/*     */     
/* 237 */     if (this.entitylittlemaid.isPotionActive(Potion.regeneration)) {
/* 238 */       var21 = this.updateCounter % MathHelper.ceiling_float_int(var14 + 5.0F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 245 */     int larmor = this.entitylittlemaid.getTotalArmorValue();
/* 246 */     int ldrawy = this.guiTop + 36;
/* 247 */     for (int li = 0; li < 10; li++) {
/* 248 */       if (larmor > 0) {
/* 249 */         int ldrawx = this.guiLeft + li * 8 + 86;
/*     */         
/* 251 */         if (li * 2 + 1 < larmor) {
/* 252 */           drawTexturedModalRect(ldrawx, ldrawy, 34, 9, 9, 9);
/*     */         }
/* 254 */         if (li * 2 + 1 == larmor) {
/* 255 */           drawTexturedModalRect(ldrawx, ldrawy, 25, 9, 9, 9);
/*     */         }
/* 257 */         if (li * 2 + 1 > larmor) {
/* 258 */           drawTexturedModalRect(ldrawx, ldrawy, 16, 9, 9, 9);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 264 */     for (int li = MathHelper.ceiling_float_int((var14 + var15) / 2.0F) - 1; li >= 0; li--) {
/* 265 */       int var23 = 16;
/* 266 */       if (this.entitylittlemaid.isPotionActive(Potion.poison)) {
/* 267 */         var23 += 36;
/* 268 */       } else if (this.entitylittlemaid.isPotionActive(Potion.wither)) {
/* 269 */         var23 += 72;
/*     */       }
/*     */       
/* 272 */       int var25 = MathHelper.ceiling_float_int((li + 1) / 10.0F);
/* 273 */       int ldrawx = this.guiLeft + li % 10 * 8 + 86;
/* 274 */       ldrawy = this.guiTop + 7 + var25 * var17;
/*     */       
/* 276 */       if (lhealth <= 4) {
/* 277 */         ldrawy += this.rand.nextInt(2);
/*     */       }
/* 279 */       if (li == var21) {
/* 280 */         ldrawy -= 2;
/*     */       }
/*     */       
/* 283 */       drawTexturedModalRect(ldrawx, ldrawy, var3 ? 25 : 16, 0, 9, 9);
/*     */       
/* 285 */       if (var3) {
/* 286 */         if (li * 2 + 1 < llasthealth) {
/* 287 */           drawTexturedModalRect(ldrawx, ldrawy, var23 + 54, 0, 9, 9);
/*     */         }
/* 289 */         if (li * 2 + 1 == llasthealth) {
/* 290 */           drawTexturedModalRect(ldrawx, ldrawy, var23 + 63, 0, 9, 9);
/*     */         }
/*     */       }
/*     */       
/* 294 */       if (var19 > 0.0F) {
/* 295 */         if ((var19 == var15) && (var15 % 2.0F == 1.0F)) {
/* 296 */           drawTexturedModalRect(ldrawx, ldrawy, var23 + 153, 0, 9, 9);
/*     */         } else {
/* 298 */           drawTexturedModalRect(ldrawx, ldrawy, var23 + 144, 0, 9, 9);
/*     */         }
/*     */         
/* 301 */         var19 -= 2.0F;
/*     */       } else {
/* 303 */         if (li * 2 + 1 < lhealth) {
/* 304 */           drawTexturedModalRect(ldrawx, ldrawy, var23 + 36, 0, 9, 9);
/*     */         }
/* 306 */         if (li * 2 + 1 == lhealth) {
/* 307 */           drawTexturedModalRect(ldrawx, ldrawy, var23 + 45, 0, 9, 9);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 313 */     ldrawy = this.guiTop + 46;
/* 314 */     if (this.entitylittlemaid.isInsideOfMaterial(net.minecraft.block.material.Material.water)) {
/* 315 */       int var23 = this.entitylittlemaid.getAir();
/* 316 */       int var35 = MathHelper.ceiling_double_int((var23 - 2) * 10.0D / 300.0D);
/* 317 */       int var25 = MathHelper.ceiling_double_int(var23 * 10.0D / 300.0D) - var35;
/*     */       
/* 319 */       for (int var26 = 0; var26 < var35 + var25; var26++) {
/* 320 */         int ldrawx = this.guiLeft + var26 * 8 + 86;
/* 321 */         if (var26 < var35) {
/* 322 */           drawTexturedModalRect(ldrawx, ldrawy, 16, 18, 9, 9);
/*     */         } else {
/* 324 */           drawTexturedModalRect(ldrawx, ldrawy, 25, 18, 9, 9);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void drawScreen(int i, int j, float f)
/*     */   {
/* 333 */     super.drawScreen(i, j, f);
/* 334 */     this.xSize_lo = i;
/* 335 */     this.ySize_lo = j;
/*     */     
/* 337 */     int ii = i - this.guiLeft;
/* 338 */     int jj = j - this.guiTop;
/* 339 */     if ((ii > 25) && (ii < 78) && (jj > 7) && (jj < 60))
/*     */     {
/* 341 */       this.txbutton[0].visible = true;
/* 342 */       this.txbutton[1].visible = true;
/* 343 */       this.txbutton[2].visible = true;
/* 344 */       this.txbutton[3].visible = true;
/* 345 */       this.selectbutton.visible = true;
/*     */       
/*     */ 
/* 348 */       GL11.glPushMatrix();
/* 349 */       GL11.glTranslatef(i - ii, j - jj, 0.0F);
/* 350 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 351 */       RenderHelper.disableStandardItemLighting();
/* 352 */       GL11.glDisable(2896);
/* 353 */       GL11.glDisable(2929);
/*     */       
/* 355 */       if (this.entitylittlemaid.textureData.textureBox[0] != null) {
/* 356 */         String ls1 = this.entitylittlemaid.textureData.getTextureName(0);
/* 357 */         String ls2 = this.entitylittlemaid.textureData.getTextureName(1);
/* 358 */         int ltw1 = this.mc.fontRenderer.getStringWidth(ls1);
/* 359 */         int ltw2 = this.mc.fontRenderer.getStringWidth(ls2);
/* 360 */         int ltwmax = ltw1 > ltw2 ? ltw1 : ltw2;
/* 361 */         int lbx = 52 - ltwmax / 2;
/* 362 */         int lby = 68;
/*     */         
/* 364 */         int lcolor = jj < 20 ? -1064820190 : -1073741824;
/* 365 */         drawGradientRect(lbx - 3, lby - 4, lbx + ltwmax + 3, lby + 8, lcolor, lcolor);
/* 366 */         drawString(this.mc.fontRenderer, ls1, 52 - ltw1 / 2, lby - 2, -1);
/* 367 */         lcolor = jj > 46 ? -1064820190 : -1073741824;
/* 368 */         drawGradientRect(lbx - 3, lby + 8, lbx + ltwmax + 3, lby + 16 + 4, lcolor, lcolor);
/* 369 */         drawString(this.mc.fontRenderer, ls2, 52 - ltw2 / 2, lby + 10, -1);
/*     */       }
/*     */       
/* 372 */       GL11.glPopMatrix();
/* 373 */       GL11.glEnable(2896);
/* 374 */       GL11.glEnable(2929);
/*     */     } else {
/* 376 */       this.txbutton[0].visible = false;
/* 377 */       this.txbutton[1].visible = false;
/* 378 */       this.txbutton[2].visible = false;
/* 379 */       this.txbutton[3].visible = false;
/* 380 */       this.selectbutton.visible = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void updateScreen()
/*     */   {
/* 387 */     super.updateScreen();
/* 388 */     this.updateCounter += 1;
/*     */   }
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k)
/*     */   {
/* 393 */     super.mouseClicked(i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void actionPerformed(GuiButton par1GuiButton)
/*     */   {
/* 415 */     switch (par1GuiButton.id) {
/*     */     case 100: 
/* 417 */       this.entitylittlemaid.setNextTexturePackege(0);
/* 418 */       this.entitylittlemaid.setTextureNames();
/* 419 */       break;
/*     */     case 101: 
/* 421 */       this.entitylittlemaid.setPrevTexturePackege(0);
/* 422 */       this.entitylittlemaid.setTextureNames();
/* 423 */       break;
/*     */     case 110: 
/* 425 */       this.entitylittlemaid.setNextTexturePackege(1);
/* 426 */       this.entitylittlemaid.setTextureNames();
/* 427 */       break;
/*     */     case 111: 
/* 429 */       this.entitylittlemaid.setPrevTexturePackege(1);
/* 430 */       this.entitylittlemaid.setTextureNames();
/* 431 */       break;
/*     */     case 200: 
/* 433 */       int ldye = 0;
/* 434 */       if (this.mc.thePlayer.capabilities.isCreativeMode) {
/* 435 */         ldye = 65535;
/*     */       } else {
/* 437 */         for (ItemStack lis : this.mc.thePlayer.inventory.mainInventory) {
/* 438 */           if ((lis != null) && (lis.getItem() == Items.dye)) {
/* 439 */             ldye |= 1 << 15 - lis.getItemDamage();
/*     */           }
/*     */         }
/*     */       }
/* 443 */       this.isChangeTexture = false;
/* 444 */       this.mc.displayGuiScreen(new LMM_GuiTextureSelect(this, this.entitylittlemaid, ldye, true));
/*     */     }
/*     */   }
/*     */   
/*     */   public void onGuiClosed()
/*     */   {
/* 450 */     super.onGuiClosed();
/*     */     
/* 452 */     if (this.isChangeTexture) {
/* 453 */       this.entitylittlemaid.sendTextureToServer();
/*     */     }
/*     */   }
/*     */   
/*     */   private void displayDebuffEffects()
/*     */   {
/* 459 */     int lx = this.guiLeft - 124;
/* 460 */     int ly = this.guiTop;
/* 461 */     Collection collection = this.entitylittlemaid.getActivePotionEffects();
/* 462 */     if (collection.isEmpty()) {
/* 463 */       return;
/*     */     }
/* 465 */     int lh = 33;
/* 466 */     if (collection.size() > 5) {
/* 467 */       lh = 132 / (collection.size() - 1);
/*     */     }
/* 469 */     for (Iterator iterator = this.entitylittlemaid.getActivePotionEffects().iterator(); iterator.hasNext();) {
/* 470 */       PotionEffect potioneffect = (PotionEffect)iterator.next();
/* 471 */       Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
/* 472 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 473 */       Client.setTexture(field_147001_a);
/* 474 */       drawTexturedModalRect(lx, ly, 0, this.ySizebk, 140, 32);
/* 475 */       if (potion.hasStatusIcon()) {
/* 476 */         int i1 = potion.getStatusIconIndex();
/* 477 */         drawTexturedModalRect(lx + 6, ly + 7, 0 + i1 % 8 * 18, this.ySizebk + 32 + i1 / 8 * 18, 18, 18);
/*     */       }
/*     */       
/* 480 */       String ls = StatCollector.translateToLocal(potion.getName());
/* 481 */       if (potioneffect.getAmplifier() > 0) {
/* 482 */         ls = ls + " " + StatCollector.translateToLocal(new StringBuilder().append("potion.potency.").append(potioneffect.getAmplifier()).toString());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 488 */       this.mc.fontRenderer.drawStringWithShadow(ls, lx + 10 + 18, ly + 6, 16777215);
/* 489 */       String s1 = Potion.getDurationString(potioneffect);
/* 490 */       this.mc.fontRenderer.drawStringWithShadow(s1, lx + 10 + 18, ly + 6 + 10, 8355711);
/* 491 */       ly += lh;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_GuiInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
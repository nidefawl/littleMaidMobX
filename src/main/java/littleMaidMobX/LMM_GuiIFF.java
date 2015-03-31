/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.MMM_GuiMobSelect;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.StringTranslate;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_GuiIFF extends MMM_GuiMobSelect
/*     */ {
/*  16 */   public static final String[] IFFString = { "ENEMY", "UNKNOWN", "FRIENDLY" };
/*     */   
/*     */ 
/*     */ 
/*     */   protected LMM_EntityLittleMaid target;
/*     */   
/*     */ 
/*     */ 
/*     */   public LMM_GuiIFF(World world, LMM_EntityLittleMaid pEntity)
/*     */   {
/*  26 */     super(world);
/*  27 */     this.screenTitle = "LittleMaid IFF";
/*  28 */     this.target = pEntity;
/*     */     
/*     */     int li;
/*  31 */     if (!mmmlibx.lib.Client.isIntegratedServerRunning()) {
/*  32 */       li = 0;
/*  33 */       for (String ls : LMM_IFF.DefaultIFF.keySet()) {
/*  34 */         byte[] ldata = new byte[5 + ls.length()];
/*  35 */         ldata[0] = 5;
/*  36 */         MMM_Helper.setInt(ldata, 1, li);
/*  37 */         MMM_Helper.setStr(ldata, 5, ls);
/*  38 */         LMM_LittleMaidMobX.Debug("RequestIFF %s(%d)", new Object[] { ls, Integer.valueOf(li) });
/*  39 */         LMM_Net.sendToServer(ldata);
/*  40 */         li++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean checkEntity(String pName, Entity pEntity, int pIndex)
/*     */   {
/*  47 */     boolean lf = false;
/*     */     
/*  49 */     int liff = LMM_IFF.checkEntityStatic(pName, pEntity, pIndex, this.entityMap);
/*  50 */     if ((pEntity instanceof EntityLivingBase)) {
/*  51 */       if ((pEntity instanceof LMM_EntityLittleMaid)) {
/*  52 */         if ((pIndex == 0) || (pIndex == 1))
/*     */         {
/*  54 */           lf = true;
/*     */         }
/*     */         
/*     */       }
/*  58 */       else if (((pEntity instanceof net.minecraft.entity.IEntityOwnable)) && (
/*  59 */         (pIndex == 0) || (pIndex == 1)))
/*     */       {
/*  61 */         lf = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  68 */     return lf;
/*     */   }
/*     */   
/*     */   public void initGui()
/*     */   {
/*  73 */     super.initGui();
/*     */     
/*  75 */     StringTranslate stringtranslate = new StringTranslate();
/*     */     
/*  77 */     this.buttonList.add(new GuiButton(200, this.width / 2 - 130, this.height - 40, 120, 20, stringtranslate.translateKey("gui.done")));
/*     */     
/*  79 */     this.buttonList.add(new GuiButton(201, this.width / 2 + 10, this.height - 40, 120, 20, "Trigger Select"));
/*     */   }
/*     */   
/*     */ 
/*     */   protected void actionPerformed(GuiButton guibutton)
/*     */   {
/*  85 */     if (!guibutton.enabled) {
/*  86 */       return;
/*     */     }
/*  88 */     if (guibutton.id == 200) {
/*  89 */       this.mc.displayGuiScreen(null);
/*     */     }
/*  91 */     if (guibutton.id == 201) {
/*  92 */       this.mc.displayGuiScreen(new LMM_GuiTriggerSelect(this.mc.thePlayer, this));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean doesGuiPauseGame()
/*     */   {
/*  98 */     return true;
/*     */   }
/*     */   
/*     */   public void onGuiClosed()
/*     */   {
/* 103 */     LMM_Net.saveIFF();
/* 104 */     super.onGuiClosed();
/*     */   }
/*     */   
/*     */   public void clickSlot(int pIndex, boolean pDoubleClick, String pName, EntityLivingBase pEntity)
/*     */   {
/* 109 */     if (pDoubleClick) {
/* 110 */       int tt = LMM_IFF.getIFF(null, pName, pEntity.worldObj);
/* 111 */       tt++;
/* 112 */       if (tt > 2) {
/* 113 */         tt = 0;
/*     */       }
/*     */       int li;
/* 116 */       if (!this.mc.isIntegratedServerRunning())
/*     */       {
/* 118 */         li = 0;
/* 119 */         for (String ls : LMM_IFF.DefaultIFF.keySet()) {
/* 120 */           if (ls.contains(pName)) {
/* 121 */             byte[] ldata = new byte[pName.length() + 6];
/* 122 */             ldata[0] = 4;
/* 123 */             ldata[1] = ((byte)tt);
/* 124 */             MMM_Helper.setInt(ldata, 2, li);
/* 125 */             MMM_Helper.setStr(ldata, 6, pName);
/* 126 */             LMM_LittleMaidMobX.Debug("SendIFF %s(%d) = %d", new Object[] { pName, Integer.valueOf(li), Integer.valueOf(tt) });
/* 127 */             LMM_Net.sendToServer(ldata);
/*     */           }
/* 129 */           li++;
/*     */         }
/*     */       } else {
/* 132 */         LMM_IFF.setIFFValue(null, pName, tt);
/*     */       }
/*     */       
/* 135 */       Entity player = this.mc.thePlayer;
/* 136 */       pEntity.worldObj.playSound(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "random.click", 1.0F, 1.0F, false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawSlot(int pSlotindex, int pX, int pY, int pDrawheight, net.minecraft.client.renderer.Tessellator pTessellator, String pName, Entity pEntity)
/*     */   {
/* 144 */     int tt = LMM_IFF.getIFF(null, pName, pEntity.worldObj);
/* 145 */     int c = 16777215;
/* 146 */     switch (tt) {
/*     */     case 2: 
/* 148 */       c = 4194111;
/* 149 */       break;
/*     */     case 1: 
/* 151 */       c = 16776960;
/* 152 */       break;
/*     */     case 0: 
/* 154 */       c = 16727871;
/*     */     }
/*     */     
/* 157 */     drawString(this.mc.fontRenderer, IFFString[tt], (this.width - this.mc.fontRenderer.getStringWidth(IFFString[tt])) / 2, pY + 18, c);
/*     */     
/* 159 */     drawString(this.mc.fontRenderer, pName, (this.width - this.mc.fontRenderer.getStringWidth(pName)) / 2, pY + 6, 16777215);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_GuiIFF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
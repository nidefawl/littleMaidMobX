/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiSlot;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class MMM_GuiMobSelect extends GuiScreen
/*     */ {
/*     */   public Map<String, Entity> entityMap;
/*  23 */   public static Map<Class, String> entityMapClass = new HashMap();
/*  24 */   public static java.util.List<String> exclusionList = new ArrayList();
/*     */   
/*     */   protected String screenTitle;
/*     */   
/*     */   protected GuiSlot selectPanel;
/*     */   
/*     */   public MMM_GuiMobSelect(World pWorld)
/*     */   {
/*  32 */     this.entityMap = new TreeMap();
/*  33 */     initEntitys(pWorld, true);
/*     */   }
/*     */   
/*     */   public MMM_GuiMobSelect(World pWorld, Map<String, Entity> pMap) {
/*  37 */     this.entityMap = pMap;
/*  38 */     initEntitys(pWorld, false);
/*     */   }
/*     */   
/*     */ 
/*     */   public void initEntitys(World world, boolean pForce)
/*     */   {
/*  44 */     if (entityMapClass.isEmpty()) {
/*     */       try {
/*  46 */         Map lmap = EntityList.classToStringMapping;
/*  47 */         entityMapClass.putAll(lmap);
/*     */       }
/*     */       catch (Exception e) {
/*  50 */         MMMLib.Debug("EntityClassMap copy failed.", new Object[0]);
/*     */       }
/*     */     }
/*     */     
/*  54 */     if (this.entityMap == null) return;
/*  55 */     if ((!pForce) && (!this.entityMap.isEmpty())) { return;
/*     */     }
/*  57 */     for (Map.Entry<Class, String> le : entityMapClass.entrySet()) {
/*  58 */       if (!Modifier.isAbstract(((Class)le.getKey()).getModifiers())) {
/*  59 */         int li = 0;
/*  60 */         Entity lentity = null;
/*     */         try
/*     */         {
/*     */           do {
/*  64 */             lentity = (EntityLivingBase)((Class)le.getKey()).getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*     */             
/*  66 */             if (lentity == null) break; } while (checkEntity((String)le.getValue(), lentity, li++));
/*     */         } catch (Exception e) {
/*  68 */           MMMLib.Debug("Entity [" + (String)le.getValue() + "] can't created.", new Object[0]);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean checkEntity(String pName, Entity pEntity, int pIndex)
/*     */   {
/*  78 */     this.entityMap.put(pName, pEntity);
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public void initGui()
/*     */   {
/*  84 */     this.selectPanel = new MMM_GuiSlotMobSelect(this.mc, this);
/*  85 */     this.selectPanel.registerScrollButtons(3, 4);
/*     */   }
/*     */   
/*     */   public void drawScreen(int px, int py, float pf)
/*     */   {
/*  90 */     float lhealthScale = BossStatus.healthScale;
/*  91 */     int lstatusBarLength = BossStatus.statusBarTime;
/*  92 */     String lbossName = BossStatus.bossName;
/*  93 */     boolean lfield_82825_d = BossStatus.hasColorModifier;
/*     */     
/*  95 */     drawDefaultBackground();
/*  96 */     this.selectPanel.drawScreen(px, py, pf);
/*  97 */     drawCenteredString(this.mc.fontRenderer, net.minecraft.util.StatCollector.translateToLocal(this.screenTitle), this.width / 2, 20, 16777215);
/*  98 */     super.drawScreen(px, py, pf);
/*     */     
/*     */ 
/* 101 */     BossStatus.healthScale = lhealthScale;
/* 102 */     BossStatus.statusBarTime = lstatusBarLength;
/* 103 */     BossStatus.bossName = lbossName;
/* 104 */     BossStatus.hasColorModifier = lfield_82825_d;
/*     */   }
/*     */   
/*     */   public abstract void clickSlot(int paramInt, boolean paramBoolean, String paramString, EntityLivingBase paramEntityLivingBase);
/*     */   
/*     */   public abstract void drawSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Tessellator paramTessellator, String paramString, Entity paramEntity);
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_GuiMobSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package littleMaidMobX;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import mmmlibx.lib.MMM_EntityDummy;
/*     */ import mmmlibx.lib.MMM_EntitySelect;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import mmmlibx.lib.MMM_RenderDummy;
/*     */ import mmmlibx.lib.multiModel.model.mc162.RenderModelMulti;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.particle.EntityCrit2FX;
/*     */ import net.minecraft.client.particle.EntityPickupFX;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import network.W_Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_ProxyClient
/*     */   extends LMM_ProxyCommon
/*     */ {
/*     */   public void init()
/*     */   {
/*  28 */     RenderingRegistry.registerEntityRenderingHandler(LMM_EntityLittleMaid.class, new LMM_RenderLittleMaid(0.3F));
/*  29 */     RenderingRegistry.registerEntityRenderingHandler(MMM_EntitySelect.class, new RenderModelMulti(0.0F));
/*  30 */     RenderingRegistry.registerEntityRenderingHandler(MMM_EntityDummy.class, new MMM_RenderDummy());
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
/*     */   public void onItemPickup(LMM_EntityLittleMaidAvatar pAvatar, Entity entity, int i)
/*     */   {
/*  54 */     MMM_Helper.mc.effectRenderer.addEffect(new EntityPickupFX(MMM_Helper.mc.theWorld, entity, pAvatar.avatar, 0.1F));
/*     */   }
/*     */   
/*     */   public void onCriticalHit(LMM_EntityLittleMaidAvatar pAvatar, Entity par1Entity) {
/*  58 */     MMM_Helper.mc.effectRenderer.addEffect(new EntityCrit2FX(MMM_Helper.mc.theWorld, par1Entity));
/*     */   }
/*     */   
/*     */   public void onEnchantmentCritical(LMM_EntityLittleMaidAvatar pAvatar, Entity par1Entity) {
/*  62 */     EntityCrit2FX entitycrit2fx = new EntityCrit2FX(MMM_Helper.mc.theWorld, par1Entity, "magicCrit");
/*  63 */     MMM_Helper.mc.effectRenderer.addEffect(entitycrit2fx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clientCustomPayload(W_Message var2)
/*     */   {
/*  71 */     byte lmode = var2.data[0];
/*  72 */     int leid = 0;
/*  73 */     LMM_EntityLittleMaid lemaid = null;
/*  74 */     if ((lmode & 0x80) != 0) {
/*  75 */       leid = MMM_Helper.getInt(var2.data, 1);
/*  76 */       lemaid = LMM_Net.getLittleMaid(var2.data, 1, MMM_Helper.mc.theWorld);
/*  77 */       if (lemaid == null) return;
/*     */     }
/*  79 */     LMM_LittleMaidMobX.Debug(String.format("LMM|Upd Clt Call[%2x:%d].", new Object[] { Byte.valueOf(lmode), Integer.valueOf(leid) }), new Object[0]);
/*     */     
/*  81 */     switch (lmode)
/*     */     {
/*     */     case -127: 
/*  84 */       byte larm = var2.data[5];
/*  85 */       LMM_EnumSound lsound = LMM_EnumSound.getEnumSound(MMM_Helper.getInt(var2.data, 6));
/*  86 */       lemaid.setSwinging(larm, lsound);
/*     */       
/*  88 */       break;
/*     */     
/*     */ 
/*     */     case 4: 
/*  92 */       int lval = var2.data[1];
/*  93 */       int lindex = MMM_Helper.getInt(var2.data, 2);
/*  94 */       String lname = (String)LMM_IFF.DefaultIFF.keySet().toArray()[lindex];
/*  95 */       LMM_LittleMaidMobX.Debug("setIFF-CL %s(%d)=%d", new Object[] { lname, Integer.valueOf(lindex), Integer.valueOf(lval) });
/*  96 */       LMM_IFF.setIFFValue(null, lname, lval);
/*  97 */       break;
/*     */     
/*     */ 
/*     */     case -119: 
/* 101 */       LMM_EnumSound lsound9 = LMM_EnumSound.getEnumSound(MMM_Helper.getInt(var2.data, 5));
/* 102 */       lemaid.playLittleMaidSound(lsound9, true);
/* 103 */       LMM_LittleMaidMobX.Debug(String.format("playSound:%s", new Object[] { lsound9.name() }), new Object[0]);
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityPlayer getClientPlayer()
/*     */   {
/* 111 */     return Minecraft.getMinecraft().thePlayer;
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
/*     */   public void loadSounds()
/*     */   {
/* 124 */     LMM_SoundManager.init();
/*     */     
/* 126 */     LMM_SoundManager.loadDefaultSoundPack();
/* 127 */     LMM_SoundManager.loadSoundPack();
/*     */   }
/*     */   
/*     */   public boolean isSinglePlayer()
/*     */   {
/* 132 */     return Minecraft.getMinecraft().isSingleplayer();
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_ProxyClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
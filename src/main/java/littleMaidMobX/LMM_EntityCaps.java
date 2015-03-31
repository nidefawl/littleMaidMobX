/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.MMM_Counter;
/*     */ import mmmlibx.lib.MMM_EntityCaps;
/*     */ import mmmlibx.lib.MMM_TextureBoxBase;
/*     */ import mmmlibx.lib.MMM_TextureData;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_EntityCaps
/*     */   extends MMM_EntityCaps
/*     */ {
/*     */   private LMM_EntityLittleMaid owner;
/*  21 */   private static Map<String, Integer> caps = new HashMap();
/*  22 */   static { caps.putAll(getStaticModelCaps());
/*  23 */     caps.put("isBloodsuck", Integer.valueOf(257));
/*  24 */     caps.put("isFreedom", Integer.valueOf(258));
/*  25 */     caps.put("isTracer", Integer.valueOf(259));
/*  26 */     caps.put("isPlaying", Integer.valueOf(260));
/*  27 */     caps.put("isLookSuger", Integer.valueOf(261));
/*  28 */     caps.put("isBlocking", Integer.valueOf(262));
/*  29 */     caps.put("isWait", Integer.valueOf(263));
/*  30 */     caps.put("isWaitEX", Integer.valueOf(264));
/*  31 */     caps.put("isOpenInv", Integer.valueOf(265));
/*  32 */     caps.put("isWorking", Integer.valueOf(266));
/*  33 */     caps.put("isWorkingDelay", Integer.valueOf(267));
/*  34 */     caps.put("isContract", Integer.valueOf(268));
/*  35 */     caps.put("isContractEX", Integer.valueOf(269));
/*  36 */     caps.put("isRemainsC", Integer.valueOf(270));
/*  37 */     caps.put("isClock", Integer.valueOf(271));
/*  38 */     caps.put("isMasked", Integer.valueOf(272));
/*  39 */     caps.put("isCamouflage", Integer.valueOf(273));
/*  40 */     caps.put("isPlanter", Integer.valueOf(274));
/*  41 */     caps.put("isOverdrive", Integer.valueOf(275));
/*  42 */     caps.put("isOverdriveDelay", Integer.valueOf(276));
/*  43 */     caps.put("entityIdFactor", Integer.valueOf(288));
/*  44 */     caps.put("height", Integer.valueOf(289));
/*  45 */     caps.put("width", Integer.valueOf(290));
/*  46 */     caps.put("YOffset", Integer.valueOf(291));
/*  47 */     caps.put("mountedYOffset", Integer.valueOf(292));
/*  48 */     caps.put("dominantArm", Integer.valueOf(293));
/*     */     
/*     */ 
/*  51 */     caps.put("HeadMount", Integer.valueOf(306));
/*     */     
/*  53 */     caps.put("stabiliser", Integer.valueOf(308));
/*  54 */     caps.put("Items", Integer.valueOf(309));
/*  55 */     caps.put("Actions", Integer.valueOf(310));
/*  56 */     caps.put("Grounds", Integer.valueOf(311));
/*  57 */     caps.put("Ground", Integer.valueOf(313));
/*  58 */     caps.put("Inventory", Integer.valueOf(312));
/*  59 */     caps.put("interestedAngle", Integer.valueOf(336));
/*     */     
/*     */ 
/*  62 */     caps.put("currentArmor", Integer.valueOf(36));
/*  63 */     caps.put("currentEquippedItem", Integer.valueOf(35));
/*     */   }
/*     */   
/*     */   public LMM_EntityCaps(LMM_EntityLittleMaid pOwner) {
/*  67 */     super(pOwner);
/*  68 */     this.owner = pOwner;
/*     */   }
/*     */   
/*     */   public Map<String, Integer> getModelCaps()
/*     */   {
/*  73 */     return caps;
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/*  78 */     int li = 0;
/*     */     
/*  80 */     switch (pIndex)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     case 257: 
/*  88 */       return Boolean.valueOf(this.owner.isBloodsuck());
/*     */     case 258: 
/*  90 */       return Boolean.valueOf(this.owner.isFreedom());
/*     */     case 259: 
/*  92 */       return Boolean.valueOf(this.owner.isTracer());
/*     */     case 260: 
/*  94 */       return Boolean.valueOf(this.owner.isPlaying());
/*     */     case 261: 
/*  96 */       return Boolean.valueOf(this.owner.isLookSuger());
/*     */     case 262: 
/*  98 */       return Boolean.valueOf(this.owner.isBlocking());
/*     */     case 263: 
/* 100 */       return Boolean.valueOf(this.owner.isMaidWait());
/*     */     case 264: 
/* 102 */       return Boolean.valueOf(this.owner.isMaidWaitEx());
/*     */     case 265: 
/* 104 */       return Boolean.valueOf(this.owner.isOpenInventory());
/*     */     case 266: 
/* 106 */       return Boolean.valueOf(this.owner.isWorking());
/*     */     case 267: 
/* 108 */       return Boolean.valueOf(this.owner.isWorkingDelay());
/*     */     case 268: 
/* 110 */       return Boolean.valueOf(this.owner.isContract());
/*     */     case 269: 
/* 112 */       return Boolean.valueOf(this.owner.isContractEX());
/*     */     case 270: 
/* 114 */       return Boolean.valueOf(this.owner.isRemainsContract());
/*     */     case 271: 
/* 116 */       return Boolean.valueOf(this.owner.isClockMaid());
/*     */     case 272: 
/* 118 */       return Boolean.valueOf(this.owner.isMaskedMaid());
/*     */     case 273: 
/* 120 */       return Boolean.valueOf(this.owner.isCamouflage());
/*     */     case 274: 
/* 122 */       return Boolean.valueOf(this.owner.isPlanter());
/*     */     case 275: 
/* 124 */       return Boolean.valueOf(this.owner.maidOverDriveTime.isEnable());
/*     */     case 276: 
/* 126 */       return Boolean.valueOf(this.owner.maidOverDriveTime.isDelay());
/*     */     case 288: 
/* 128 */       return Float.valueOf(this.owner.entityIdFactor);
/*     */     case 289: 
/* 130 */       return this.owner.textureData.textureBox[0] == null ? null : Float.valueOf(this.owner.textureData.textureBox[0].getHeight(this));
/*     */     case 290: 
/* 132 */       return this.owner.textureData.textureBox[0] == null ? null : Float.valueOf(this.owner.textureData.textureBox[0].getWidth(this));
/*     */     case 291: 
/* 134 */       return this.owner.textureData.textureBox[0] == null ? null : Float.valueOf(this.owner.textureData.textureBox[0].getYOffset(this));
/*     */     case 292: 
/* 136 */       return this.owner.textureData.textureBox[0] == null ? null : Float.valueOf(this.owner.textureData.textureBox[0].getMountedYOffset(this));
/*     */     case 293: 
/* 138 */       return Integer.valueOf(this.owner.maidDominantArm);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     case 306: 
/* 144 */       return this.owner.maidInventory.getStackInSlot(17);
/*     */     
/*     */     case 308: 
/* 147 */       return this.owner.maidStabilizer;
/*     */     case 309: 
/* 149 */       ItemStack[] lstacks = new ItemStack[this.owner.mstatSwingStatus.length];
/* 150 */       for (LMM_SwingStatus ls : this.owner.mstatSwingStatus) {
/* 151 */         lstacks[(li++)] = ls.getItemStack(this.owner);
/*     */       }
/* 153 */       return lstacks;
/*     */     case 310: 
/* 155 */       EnumAction[] lactions = new EnumAction[this.owner.mstatSwingStatus.length];
/* 156 */       for (LMM_SwingStatus ls : this.owner.mstatSwingStatus) {
/* 157 */         lactions[(li++)] = (ls.isUsingItem() ? ls.getItemStack(this.owner).getItemUseAction() : null);
/*     */       }
/* 159 */       return lactions;
/*     */     case 311: 
/* 161 */       float[] lgrounds = new float[this.owner.mstatSwingStatus.length];
/* 162 */       for (LMM_SwingStatus ls : this.owner.mstatSwingStatus) {
/* 163 */         lgrounds[(li++)] = ls.onGround;
/*     */       }
/* 165 */       return lgrounds;
/*     */     
/*     */     case 313: 
/* 168 */       if (this.owner.mstatSwingStatus.length < ((Integer)pArg[0]).intValue()) {
/* 169 */         return pArg[1];
/*     */       }
/* 171 */       return Float.valueOf(this.owner.mstatSwingStatus[((Integer)pArg[0]).intValue()].onGround);
/*     */     case 312: 
/* 173 */       return this.owner.maidInventory;
/*     */     case 336: 
/* 175 */       return Float.valueOf(this.owner.getInterestedAngle(((Float)pArg[0]).floatValue()));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     case 513: 
/* 181 */       return Integer.valueOf(this.owner.textureData.selectValue);
/*     */     case 787: 
/* 183 */       return this.owner.textureData;
/*     */     }
/*     */     
/* 186 */     return super.getCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 191 */     switch (pIndex) {
/*     */     case 513: 
/* 193 */       this.owner.textureData.selectValue = ((Integer)pArg[0]).intValue();
/*     */     }
/* 195 */     return super.setCapsValue(pIndex, pArg);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityCaps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
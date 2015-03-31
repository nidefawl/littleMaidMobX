/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ 
/*     */ public class ModelLittleMaid_RX0
/*     */   extends ModelLittleMaidBase
/*     */ {
/*     */   public ModelRenderer bipedForelock;
/*     */   
/*     */   public ModelRenderer bipedForelockRight;
/*     */   public ModelRenderer bipedForelockLeft;
/*     */   public ModelRenderer bipedBust;
/*     */   public ModelRenderer bipedTrunk;
/*     */   public ModelRenderer bipedWaist;
/*     */   public ModelRenderer bipedHipRight;
/*     */   public ModelRenderer bipedHipLeft;
/*     */   public ModelRenderer bipedForearmRight;
/*     */   public ModelRenderer bipedForearmLeft;
/*     */   public ModelRenderer bipedShinRight;
/*     */   public ModelRenderer bipedTiptoeRight;
/*     */   public ModelRenderer bipedHeelRight;
/*     */   public ModelRenderer bipedShinLeft;
/*     */   public ModelRenderer bipedTiptoeLeft;
/*     */   public ModelRenderer bipedHeelLeft;
/*     */   public ModelRenderer bipedRibbon;
/*     */   public ModelRenderer bipedRibbon1;
/*     */   public ModelRenderer bipedRibbon2;
/*     */   public ModelRenderer bipedTail;
/*     */   public ModelRenderer SkirtRU;
/*     */   public ModelRenderer SkirtRB;
/*     */   public ModelRenderer SkirtLU;
/*     */   public ModelRenderer SkirtLB;
/*     */   public ModelRenderer bipedRibbonR;
/*     */   public ModelRenderer bipedRibbonRSensorU;
/*     */   public ModelRenderer bipedRibbonRSensorB;
/*     */   public ModelRenderer bipedSideTailR;
/*     */   public ModelRenderer bipedRibbonL;
/*     */   public ModelRenderer bipedRibbonLSensorU;
/*     */   public ModelRenderer bipedRibbonLSensorB;
/*     */   public ModelRenderer bipedSideTailL;
/*     */   
/*     */   public ModelLittleMaid_RX0()
/*     */   {
/*  43 */     this(0.0F);
/*     */   }
/*     */   
/*  46 */   public ModelLittleMaid_RX0(float psize) { this(psize, 0.0F, 128, 64); }
/*     */   
/*     */   public ModelLittleMaid_RX0(float psize, float pyoffset, int pTextureWidth, int pTextureHeight) {
/*  49 */     super(psize, pyoffset, pTextureWidth, pTextureHeight);
/*     */   }
/*     */   
/*     */ 
/*     */   public void initModel(float psize, float pyoffset)
/*     */   {
/*  55 */     this.bipedHead = new ModelRenderer(this);
/*  56 */     this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize - 0.2F);
/*  57 */     this.bipedForelock = new ModelRenderer(this);
/*  58 */     this.bipedForelockRight = new ModelRenderer(this);
/*  59 */     this.bipedForelockRight.setTextureOffset(0, 50).addBox(0.0F, 0.0F, -0.5F, 3, 13, 1, psize);
/*  60 */     this.bipedForelockLeft = new ModelRenderer(this);
/*  61 */     this.bipedForelockLeft.setTextureOffset(56, 50).addBox(-3.0F, 0.0F, -0.5F, 3, 13, 1, psize);
/*  62 */     this.bipedRibbon = new ModelRenderer(this);
/*  63 */     this.bipedRibbon.setTextureOffset(116, 10).addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
/*  64 */     this.bipedRibbon1 = new ModelRenderer(this);
/*  65 */     this.bipedRibbon1.setTextureOffset(116, 0).addBox(-4.0F, 0.0F, -2.0F, 4, 3, 2);
/*  66 */     this.bipedRibbon2 = new ModelRenderer(this);
/*  67 */     this.bipedRibbon2.setTextureOffset(116, 5).addBox(0.0F, 0.0F, -2.0F, 4, 3, 2);
/*  68 */     this.bipedTail = new ModelRenderer(this);
/*  69 */     this.bipedTail.setTextureOffset(108, 0).addBox(-1.5F, -0.5F, -0.5F, 3, 11, 1, psize);
/*     */     
/*  71 */     this.bipedRibbonR = new ModelRenderer(this);
/*  72 */     this.bipedRibbonR.setTextureOffset(80, 0).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.1F);
/*  73 */     this.bipedRibbonRSensorU = new ModelRenderer(this);
/*  74 */     this.bipedRibbonRSensorU.setTextureOffset(80, 4).addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.2F);
/*  75 */     this.bipedRibbonRSensorB = new ModelRenderer(this);
/*  76 */     this.bipedRibbonRSensorB.setTextureOffset(74, 0).addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1, 0.1F);
/*  77 */     this.bipedSideTailR = new ModelRenderer(this);
/*  78 */     this.bipedSideTailR.setTextureOffset(96, 0).addBox(-1.0F, -0.5F, -0.5F, 2, 11, 1, psize);
/*     */     
/*  80 */     this.bipedRibbonL = new ModelRenderer(this);
/*  81 */     this.bipedRibbonL.setTextureOffset(88, 0).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.1F);
/*  82 */     this.bipedRibbonLSensorU = new ModelRenderer(this);
/*  83 */     this.bipedRibbonLSensorU.setTextureOffset(88, 4).addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.2F);
/*  84 */     this.bipedRibbonLSensorB = new ModelRenderer(this);
/*  85 */     this.bipedRibbonLSensorB.setTextureOffset(74, 3).addBox(-1.0F, 0.0F, 0.0F, 2, 2, 1, 0.1F);
/*  86 */     this.bipedSideTailL = new ModelRenderer(this);
/*  87 */     this.bipedSideTailL.setTextureOffset(102, 0).addBox(-1.0F, -0.5F, -0.5F, 2, 11, 1, psize);
/*     */     
/*     */ 
/*     */ 
/*  91 */     this.bipedBody = new ModelRenderer(this);
/*  92 */     this.bipedBody.setTextureOffset(32, 7).addBox(-3.0F, 0.0F, -1.0F, 6, 3, 3, psize);
/*  93 */     this.bipedBody.setTextureOffset(26, 40).addBox(-1.5F, -1.0F, -1.5F, 3, 3, 3, psize);
/*  94 */     this.bipedBust = new ModelRenderer(this);
/*  95 */     this.bipedBust.setTextureOffset(32, 0).addBox(-3.0F, -2.5F, 0.0F, 6, 4, 3, psize - 0.04F);
/*     */     
/*  97 */     this.bipedRightArm = new ModelRenderer(this);
/*  98 */     this.bipedRightArm.setTextureOffset(8, 47).addBox(-2.0F, -0.5F, -1.0F, 2, 7, 2, psize);
/*  99 */     this.bipedForearmRight = new ModelRenderer(this);
/* 100 */     this.bipedForearmRight.setTextureOffset(0, 40).addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2, psize - 0.05F);
/* 101 */     this.bipedLeftArm = new ModelRenderer(this);
/* 102 */     this.bipedLeftArm.setTextureOffset(48, 47).addBox(0.0F, -0.5F, -1.0F, 2, 7, 2, psize);
/* 103 */     this.bipedForearmLeft = new ModelRenderer(this);
/* 104 */     this.bipedForearmLeft.setTextureOffset(56, 40).addBox(-1.0F, -1.0F, -1.0F, 2, 8, 2, psize - 0.05F);
/*     */     
/* 106 */     this.bipedTrunk = new ModelRenderer(this);
/* 107 */     this.bipedWaist = new ModelRenderer(this);
/* 108 */     this.bipedWaist.setTextureOffset(24, 46).addBox(-2.5F, 0.0F, -1.95F, 5, 7, 3, psize);
/* 109 */     this.bipedHipRight = new ModelRenderer(this);
/* 110 */     this.bipedHipRight.setTextureOffset(50, 0).addBox(0.0F, -1.5F, -2.0F, 3, 4, 4, psize);
/* 111 */     this.bipedHipLeft = new ModelRenderer(this);
/* 112 */     this.bipedHipLeft.setTextureOffset(50, 8).addBox(-3.0F, -1.5F, -2.0F, 3, 4, 4, psize);
/*     */     
/* 114 */     this.bipedRightLeg = new ModelRenderer(this);
/* 115 */     this.bipedRightLeg.setTextureOffset(0, 29).addBox(-3.0F, 0.0F, -2.0F, 3, 7, 4, psize);
/* 116 */     this.bipedShinRight = new ModelRenderer(this);
/* 117 */     this.bipedShinRight.setTextureOffset(0, 16).addBox(-3.0F, 0.0F, -3.0F, 3, 9, 4, psize - 0.2F);
/* 118 */     this.bipedTiptoeRight = new ModelRenderer(this);
/* 119 */     this.bipedTiptoeRight.setTextureOffset(12, 26).addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, psize);
/* 120 */     this.bipedHeelRight = new ModelRenderer(this);
/* 121 */     this.bipedHeelRight.setTextureOffset(10, 16).addBox(-1.0F, 0.25F, -3.25F, 2, 1, 3, psize + 0.25F);
/*     */     
/* 123 */     this.bipedLeftLeg = new ModelRenderer(this);
/* 124 */     this.bipedLeftLeg.setTextureOffset(50, 29).addBox(0.0F, 0.0F, -2.0F, 3, 7, 4, psize);
/* 125 */     this.bipedShinLeft = new ModelRenderer(this);
/* 126 */     this.bipedShinLeft.setTextureOffset(50, 16).addBox(0.0F, 0.0F, -3.0F, 3, 9, 4, psize - 0.2F);
/* 127 */     this.bipedTiptoeLeft = new ModelRenderer(this);
/* 128 */     this.bipedTiptoeLeft.setTextureOffset(38, 26).addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, psize);
/* 129 */     this.bipedHeelLeft = new ModelRenderer(this);
/* 130 */     this.bipedHeelLeft.setTextureOffset(20, 16).addBox(-1.0F, 0.25F, -3.25F, 2, 1, 3, psize + 0.25F);
/*     */     
/* 132 */     this.Skirt = new ModelRenderer(this);
/* 133 */     this.Skirt.setTextureOffset(20, 26).addBox(-3.0F, 0.0F, -3.0F, 6, 8, 6, psize + 0.05F);
/* 134 */     this.SkirtRU = new ModelRenderer(this);
/* 135 */     this.SkirtRU.setTextureOffset(8, 34).addBox(2.0F, 2.0F, -3.0F, 3, 7, 6, psize);
/* 136 */     this.SkirtRB = new ModelRenderer(this);
/* 137 */     this.SkirtRB.setTextureOffset(8, 48).addBox(-4.0F, 0.0F, -4.0F, 4, 8, 8, psize);
/* 138 */     this.SkirtLU = new ModelRenderer(this);
/* 139 */     this.SkirtLU.setTextureOffset(38, 34).addBox(-5.0F, 2.0F, -3.0F, 3, 7, 6, psize);
/* 140 */     this.SkirtLB = new ModelRenderer(this);
/* 141 */     this.SkirtLB.setTextureOffset(32, 48).addBox(0.0F, 0.0F, -4.0F, 4, 8, 8, psize);
/*     */     
/* 143 */     this.mainFrame = new ModelRenderer(this);
/* 144 */     this.bipedNeck = new ModelRenderer(this);
/* 145 */     this.bipedTorso = new ModelRenderer(this);
/* 146 */     this.bipedPelvic = new ModelRenderer(this);
/*     */     
/* 148 */     this.Arms[0] = new ModelRenderer(this);
/* 149 */     this.Arms[0].setRotationPoint(0.0F, 5.0F, -1.0F);
/* 150 */     this.Arms[1] = new ModelRenderer(this);
/* 151 */     this.Arms[1].setRotationPoint(0.0F, 5.0F, -1.0F);
/* 152 */     this.Arms[1].isInvertX = true;
/*     */     
/* 154 */     this.HeadMount.setRotationPoint(0.0F, -4.0F, 0.0F);
/* 155 */     this.HeadTop.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 157 */     this.mainFrame.addChild(this.bipedTorso);
/* 158 */     this.bipedTorso.addChild(this.bipedNeck);
/* 159 */     this.bipedNeck.addChild(this.bipedHead);
/* 160 */     this.bipedHead.addChild(this.bipedForelock);
/* 161 */     this.bipedForelock.addChild(this.bipedForelockRight);
/* 162 */     this.bipedForelock.addChild(this.bipedForelockLeft);
/* 163 */     this.bipedHead.addChild(this.bipedRibbon);
/* 164 */     this.bipedRibbon.addChild(this.bipedRibbon1);
/* 165 */     this.bipedRibbon.addChild(this.bipedRibbon2);
/* 166 */     this.bipedRibbon.addChild(this.bipedTail);
/* 167 */     this.bipedHead.addChild(this.bipedRibbonR);
/* 168 */     this.bipedRibbonR.addChild(this.bipedRibbonRSensorU);
/* 169 */     this.bipedRibbonR.addChild(this.bipedRibbonRSensorB);
/* 170 */     this.bipedRibbonR.addChild(this.bipedSideTailR);
/* 171 */     this.bipedHead.addChild(this.bipedRibbonL);
/* 172 */     this.bipedRibbonL.addChild(this.bipedRibbonLSensorU);
/* 173 */     this.bipedRibbonL.addChild(this.bipedRibbonLSensorB);
/* 174 */     this.bipedRibbonL.addChild(this.bipedSideTailL);
/* 175 */     this.bipedNeck.addChild(this.bipedRightArm);
/* 176 */     this.bipedRightArm.addChild(this.bipedForearmRight);
/* 177 */     this.bipedNeck.addChild(this.bipedLeftArm);
/* 178 */     this.bipedLeftArm.addChild(this.bipedForearmLeft);
/* 179 */     this.bipedTorso.addChild(this.bipedBody);
/* 180 */     this.bipedBody.addChild(this.bipedBust);
/* 181 */     this.bipedTorso.addChild(this.bipedTrunk);
/* 182 */     this.bipedTrunk.addChild(this.bipedWaist);
/* 183 */     this.bipedWaist.addChild(this.bipedHipRight);
/* 184 */     this.bipedWaist.addChild(this.bipedHipLeft);
/* 185 */     this.bipedTrunk.addChild(this.bipedPelvic);
/* 186 */     this.bipedPelvic.addChild(this.bipedRightLeg);
/* 187 */     this.bipedRightLeg.addChild(this.bipedShinRight);
/* 188 */     this.bipedShinRight.addChild(this.bipedTiptoeRight);
/* 189 */     this.bipedTiptoeRight.addChild(this.bipedHeelRight);
/* 190 */     this.bipedPelvic.addChild(this.bipedLeftLeg);
/* 191 */     this.bipedLeftLeg.addChild(this.bipedShinLeft);
/* 192 */     this.bipedShinLeft.addChild(this.bipedTiptoeLeft);
/* 193 */     this.bipedTiptoeLeft.addChild(this.bipedHeelLeft);
/* 194 */     this.bipedPelvic.addChild(this.Skirt);
/* 195 */     this.Skirt.addChild(this.SkirtRU);
/* 196 */     this.SkirtRU.addChild(this.SkirtRB);
/* 197 */     this.Skirt.addChild(this.SkirtLU);
/* 198 */     this.SkirtLU.addChild(this.SkirtLB);
/*     */     
/* 200 */     this.bipedForearmRight.addChild(this.Arms[0]);
/* 201 */     this.bipedForearmLeft.addChild(this.Arms[1]);
/* 202 */     this.bipedHead.addChild(this.HeadTop);
/* 203 */     this.bipedHead.addChild(this.HeadMount);
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
/*     */   public void setDefaultPause(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 224 */     super.setDefaultPause(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
/* 225 */     int lvisible = ModelCapsHelper.getCapsValueInt(pEntityCaps, 513, new Object[0]);
/*     */     
/*     */ 
/* 228 */     this.bipedRibbon.setVisible(true);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 233 */     this.scaleFactor = 0.8F;
/* 234 */     if ((!ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 276, new Object[0])) && (!ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 257, new Object[0])) && ((lvisible & 0x1) == 0))
/*     */     {
/*     */ 
/*     */ 
/* 238 */       this.bipedBust.setRotationPoint(0.0F, 2.5F, -2.0F);
/* 239 */       this.bipedBust.setRotateAngleDegX(-0.0F);
/* 240 */       this.bipedForearmRight.setRotationPoint(-1.025F, 0.5F, 0.0F);
/* 241 */       this.bipedForearmLeft.setRotationPoint(1.025F, 0.5F, 0.0F);
/* 242 */       this.bipedForearmRight.setRotateAngleDegX(0.0F);
/* 243 */       this.bipedForearmLeft.setRotateAngleDegX(0.0F);
/*     */       
/* 245 */       this.bipedTrunk.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 246 */       this.bipedForelock.setRotationPoint(0.0F, -7.5F, -4.0F);
/* 247 */       this.bipedForelockRight.setRotationPoint(-3.9F, 0.0F, 0.0F);
/* 248 */       this.bipedForelockLeft.setRotationPoint(3.9F, 0.0F, 0.0F);
/* 249 */       this.bipedForelockRight.setRotateAngleDegY(0.0F);
/* 250 */       this.bipedForelockLeft.setRotateAngleDegY(0.0F);
/* 251 */       float lf = this.bipedHead.rotateAngleX > 0.0F ? -this.bipedHead.rotateAngleX / 2.0F : 0.0F;
/* 252 */       this.bipedForelock.setRotateAngleX(lf);
/* 253 */       this.bipedRibbon.setRotationPoint(0.0F, -0.2F, 4.0F);
/* 254 */       this.bipedRibbon.setRotateAngleDegX(20.0F - this.bipedHead.getRotateAngleDegX() + (this.isSneak ? 20.0F : 0.0F));
/* 255 */       this.bipedRibbon1.setRotationPoint(-1.5F, 0.0F, 1.0F);
/* 256 */       this.bipedRibbon2.setRotationPoint(1.5F, 0.0F, 1.0F);
/* 257 */       this.bipedRibbon1.setRotateAngleDegY(0.0F);
/* 258 */       this.bipedRibbon2.setRotateAngleDegY(0.0F);
/* 259 */       this.bipedTail.setRotationPoint(0.0F, 3.0F, 0.0F);
/* 260 */       this.bipedTail.setRotateAngle(0.0F, 0.0F, 0.0F);
/*     */       
/* 262 */       this.bipedRibbonR.setRotationPoint(-3.5F, 0.0F, 4.0F).setRotateAngleDeg(15.0F - this.bipedHead.getRotateAngleDegX() + (this.isSneak ? 10.0F : 0.0F), 0.0F, 10.0F);
/* 263 */       this.bipedRibbonRSensorU.setRotationPoint(0.0F, 1.0F, -0.2F).setRotateAngleDeg(10.0F, 0.0F, 0.0F);
/* 264 */       this.bipedRibbonRSensorB.setRotationPoint(0.0F, 2.0F, -0.9F).setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 265 */       this.bipedSideTailR.setRotationPoint(0.0F, 2.0F, 0.0F).setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/*     */       
/* 267 */       this.bipedRibbonL.setRotationPoint(3.5F, 0.0F, 4.0F).setRotateAngleDeg(15.0F - this.bipedHead.getRotateAngleDegX() + (this.isSneak ? 10.0F : 0.0F), 0.0F, -10.0F);
/* 268 */       this.bipedRibbonLSensorU.setRotationPoint(0.0F, 1.0F, -0.2F).setRotateAngleDeg(10.0F, 0.0F, 0.0F);
/* 269 */       this.bipedRibbonLSensorB.setRotationPoint(0.0F, 2.0F, -0.9F).setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 270 */       this.bipedSideTailL.setRotationPoint(0.0F, 2.0F, 0.0F).setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/*     */       
/*     */ 
/* 273 */       this.bipedHead.setRotationPoint(0.0F, 0.2F, 0.0F);
/* 274 */       this.bipedHipRight.setRotationPoint(-3.0F, 4.5F, 0.0F);
/* 275 */       this.bipedHipLeft.setRotationPoint(3.0F, 4.5F, 0.0F);
/* 276 */       this.bipedHipRight.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 277 */       this.bipedHipLeft.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/*     */       
/* 279 */       this.bipedRightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 280 */       this.bipedRightLeg.setRotateAngleDegZ(0.0F);
/* 281 */       this.bipedShinRight.setRotationPoint(0.0F, 0.0F, 1.0F);
/* 282 */       this.bipedShinRight.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 283 */       this.bipedTiptoeRight.setRotationPoint(-1.5F, 7.0F, 1.0F);
/* 284 */       this.bipedTiptoeRight.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 285 */       this.bipedHeelRight.setRotationPoint(0.0F, 0.0F, -0.05F);
/* 286 */       this.bipedHeelRight.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/*     */       
/* 288 */       this.bipedLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 289 */       this.bipedLeftLeg.setRotateAngleDegZ(0.0F);
/* 290 */       this.bipedShinLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
/* 291 */       this.bipedShinLeft.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 292 */       this.bipedTiptoeLeft.setRotationPoint(1.5F, 7.0F, 1.0F);
/* 293 */       this.bipedTiptoeLeft.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/* 294 */       this.bipedHeelLeft.setRotationPoint(0.0F, 0.0F, -0.05F);
/* 295 */       this.bipedHeelLeft.setRotateAngleDeg(0.0F, 0.0F, 0.0F);
/*     */       
/* 297 */       this.bipedPelvic.setRotationPoint(0.0F, 7.0F, 0.0F);
/*     */       
/* 299 */       this.Skirt.setRotationPoint(0.0F, -2.0F, 0.0F);
/* 300 */       this.SkirtRU.setRotationPoint(-5.0F, -1.0F, 0.0F);
/* 301 */       this.SkirtRU.setRotateAngleDegZ(0.0F);
/* 302 */       this.SkirtRB.setRotationPoint(5.0F, 1.0F, 0.0F);
/* 303 */       this.SkirtLU.setRotationPoint(5.0F, -1.0F, 0.0F);
/* 304 */       this.SkirtLU.setRotateAngleDegZ(0.0F);
/* 305 */       this.SkirtLB.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */       
/*     */ 
/* 308 */       this.bipedTorso.setRotationPointY(0.0F);
/*     */     }
/*     */     else
/*     */     {
/* 312 */       this.bipedBody.setRotationPoint(0.0F, 0.0F, -0.5F);
/* 313 */       this.bipedBust.setRotationPoint(0.0F, 2.5F, -2.0F);
/* 314 */       this.bipedBust.setRotateAngleDegX(-41.0F);
/* 315 */       this.bipedForearmRight.setRotationPoint(-1.025F, 6.0F, 0.0F);
/* 316 */       this.bipedForearmLeft.setRotationPoint(1.025F, 6.0F, 0.0F);
/* 317 */       this.bipedForearmRight.setRotateAngleDegX(-30.0F);
/* 318 */       this.bipedForearmLeft.setRotateAngleDegX(-30.0F);
/* 319 */       this.bipedTrunk.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 320 */       this.bipedForelock.setRotationPoint(0.0F, -8.5F, -2.0F);
/* 321 */       this.bipedForelockRight.setRotationPoint(-3.8F, 0.0F, 0.0F);
/* 322 */       this.bipedForelockLeft.setRotationPoint(3.8F, 0.0F, 0.0F);
/* 323 */       this.bipedForelockRight.setRotateAngleDegY(90.0F);
/* 324 */       this.bipedForelockLeft.setRotateAngleDegY(-90.0F);
/*     */       
/* 326 */       float lf = -this.bipedHead.rotateAngleX / (this.bipedHead.rotateAngleX > 0.0F ? 2.0F : 4.0F);
/* 327 */       this.bipedForelock.setRotateAngleX(lf);
/* 328 */       this.bipedRibbon.setRotationPoint(0.0F, -6.2F, 3.8F);
/* 329 */       this.bipedRibbon.setRotateAngleDegX(90.0F);
/* 330 */       this.bipedRibbon1.setRotationPoint(-1.5F, 0.0F, 0.5F);
/* 331 */       this.bipedRibbon1.setRotateAngleDegY(30.0F);
/* 332 */       this.bipedRibbon2.setRotationPoint(1.5F, 0.0F, 0.5F);
/* 333 */       this.bipedRibbon2.setRotateAngleDegY(-30.0F);
/* 334 */       this.bipedTail.setRotationPoint(0.0F, 3.5F, -0.0F);
/* 335 */       lf = this.bipedHead.rotateAngleX < 0.0F ? -this.bipedHead.rotateAngleX : 0.0F;
/* 336 */       this.bipedTail.setRotateAngleX(lf);
/* 337 */       this.bipedTail.addRotateAngleDegX(-80.0F);
/*     */       
/* 339 */       lf = this.bipedHead.getRotateAngleDegX() / 2.0F;
/* 340 */       this.bipedRibbonR.setRotationPoint(-4.0F, -6.0F, 2.0F).setRotateAngleDeg(0.0F, -90.0F + lf, 90.0F);
/* 341 */       this.bipedRibbonRSensorU.setRotationPoint(0.0F, 1.0F, -0.2F).setRotateAngleDeg(30.0F, 0.0F, 0.0F);
/* 342 */       this.bipedRibbonRSensorB.setRotationPoint(0.0F, 2.0F, -0.9F).setRotateAngleDeg(-45.0F, 0.0F, 0.0F);
/* 343 */       this.bipedSideTailR.setRotationPoint(0.0F, 2.0F, 0.0F).setRotateAngleDeg(-80.0F, 0.0F, 0.0F);
/*     */       
/* 345 */       this.bipedRibbonL.setRotationPoint(4.0F, -6.0F, 2.0F).setRotateAngleDeg(0.0F, 90.0F - lf, -90.0F);
/* 346 */       this.bipedRibbonLSensorU.setRotationPoint(0.0F, 1.0F, -0.2F).setRotateAngleDeg(30.0F, 0.0F, 0.0F);
/* 347 */       this.bipedRibbonLSensorB.setRotationPoint(0.0F, 2.0F, -0.9F).setRotateAngleDeg(-45.0F, 0.0F, 0.0F);
/* 348 */       this.bipedSideTailL.setRotationPoint(0.0F, 2.0F, 0.0F).setRotateAngleDeg(-80.0F, 0.0F, 0.0F);
/*     */       
/* 350 */       this.bipedHead.setRotationPoint(0.0F, 0.2F, 0.0F);
/* 351 */       this.bipedHipRight.setRotationPoint(-3.0F, 4.5F, 0.45F);
/* 352 */       this.bipedHipLeft.setRotationPoint(3.0F, 4.5F, 0.45F);
/* 353 */       this.bipedHipRight.setRotateAngleDeg(15.0F, 0.0F, 15.0F);
/* 354 */       this.bipedHipLeft.setRotateAngleDeg(15.0F, 0.0F, -15.0F);
/*     */       
/* 356 */       this.bipedRightLeg.setRotationPoint(-0.7F, -0.5F, 1.0F);
/* 357 */       this.bipedRightLeg.addRotateAngleDegX(-5.0F);
/* 358 */       this.bipedRightLeg.addRotateAngleDegZ(-6.0F);
/* 359 */       this.bipedShinRight.setRotationPoint(-0.1F, 6.5F, 1.0F);
/* 360 */       this.bipedShinRight.setRotateAngleDeg(5.0F, 0.0F, 4.0F);
/* 361 */       this.bipedTiptoeRight.setRotationPoint(-1.5F, 7.0F, -0.5F);
/* 362 */       this.bipedTiptoeRight.setRotateAngleDeg(30.0F, 0.0F, 2.0F);
/* 363 */       this.bipedHeelRight.setRotationPoint(0.0F, 0.0F, -0.05F);
/* 364 */       this.bipedHeelRight.setRotateAngleDegX(60.0F);
/* 365 */       this.bipedLeftLeg.setRotationPoint(0.7F, -0.5F, 1.0F);
/* 366 */       this.bipedLeftLeg.addRotateAngleDegX(-5.0F);
/* 367 */       this.bipedLeftLeg.addRotateAngleDegZ(6.0F);
/* 368 */       this.bipedShinLeft.setRotationPoint(0.1F, 6.5F, 1.0F);
/* 369 */       this.bipedShinLeft.setRotateAngleDeg(5.0F, 0.0F, -4.0F);
/* 370 */       this.bipedTiptoeLeft.setRotationPoint(1.5F, 7.0F, -0.5F);
/* 371 */       this.bipedTiptoeLeft.setRotateAngleDeg(30.0F, 0.0F, -2.0F);
/* 372 */       this.bipedHeelLeft.setRotationPoint(0.0F, 0.0F, -0.05F);
/* 373 */       this.bipedHeelLeft.setRotateAngleDegX(60.0F);
/* 374 */       this.bipedPelvic.setRotationPoint(0.0F, 7.0F, 0.0F);
/*     */       
/* 376 */       this.Skirt.setRotationPoint(0.0F, -4.5F, 0.2F);
/* 377 */       this.SkirtRU.setRotationPoint(-5.0F, -1.0F, 0.0F);
/* 378 */       this.SkirtRU.setRotateAngleDegZ(13.0F);
/* 379 */       this.SkirtLU.setRotationPoint(5.0F, -1.0F, 0.0F);
/* 380 */       this.SkirtLU.setRotateAngleDegZ(-13.0F);
/* 381 */       if ((lvisible & 0x2) > 0) {
/* 382 */         this.SkirtRB.setRotationPoint(5.0F, 1.0F, 0.0F);
/* 383 */         this.SkirtLB.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */       } else {
/* 385 */         this.SkirtRB.setRotationPoint(5.0F, 7.0F, 0.0F);
/* 386 */         this.SkirtLB.setRotationPoint(-5.0F, 7.0F, 0.0F);
/*     */       }
/*     */       
/*     */ 
/* 390 */       this.bipedTorso.setRotationPointY(-12.0F);
/* 391 */       this.bipedRightArm.setRotationPoint(-3.0F, 1.0F, 0.0F);
/* 392 */       this.bipedLeftArm.setRotationPoint(3.0F, 1.0F, 0.0F);
/* 393 */       this.bipedRightArm.addRotateAngleDegZ(-15.0F);
/* 394 */       this.bipedLeftArm.addRotateAngleDegZ(15.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 400 */     switch (pIndex) {
/*     */     case 516: 
/* 402 */       return "DestroyMode,mimiSkirt,noSkirt";
/*     */     case 4: 
/* 404 */       return Boolean.valueOf(true);
/*     */     }
/*     */     
/* 407 */     return super.getCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */   public float getHeight(IModelCaps pEntityCaps)
/*     */   {
/* 412 */     int lvisible = ModelCapsHelper.getCapsValueInt(pEntityCaps, 513, new Object[0]);
/* 413 */     boolean lf = (!ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 276, new Object[0])) && (!ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 257, new Object[0])) && ((lvisible & 0x1) == 0);
/*     */     
/*     */ 
/* 416 */     return lf ? 1.152F : 1.58F;
/*     */   }
/*     */   
/*     */   public float getWidth(IModelCaps pEntityCaps)
/*     */   {
/* 421 */     return super.getWidth(pEntityCaps);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int showArmorParts(int parts, int index)
/*     */   {
/* 430 */     boolean f = parts == 3;
/* 431 */     this.bipedHead.setVisible(f);
/*     */     
/* 433 */     f = parts == 2;
/* 434 */     this.bipedBody.setVisible(f);
/* 435 */     this.bipedWaist.setVisible(f);
/* 436 */     this.bipedRightArm.setVisible(f);
/* 437 */     this.bipedLeftArm.setVisible(f);
/*     */     
/* 439 */     f = parts == 1;
/* 440 */     this.Skirt.setVisible(f);
/*     */     
/* 442 */     f = parts == 0;
/* 443 */     this.bipedRightLeg.setVisible(f);
/* 444 */     this.bipedLeftLeg.setVisible(f);
/*     */     
/* 446 */     return -1;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelLittleMaid_RX0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
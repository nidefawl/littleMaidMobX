/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import net.minecraft.client.model.PositionTextureVertex;
/*     */ import net.minecraft.client.model.TexturedQuad;
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
/*     */ public class ModelPlate
/*     */   extends ModelBoxBase
/*     */ {
/*     */   @Deprecated
/*     */   public static final int planeXY = 0;
/*     */   @Deprecated
/*     */   public static final int planeZY = 1;
/*     */   @Deprecated
/*     */   public static final int planeXZ = 2;
/*     */   @Deprecated
/*     */   public static final int planeXYInv = 4;
/*     */   @Deprecated
/*     */   public static final int planeZYInv = 5;
/*     */   @Deprecated
/*     */   public static final int planeXZInv = 6;
/*     */   public static final int planeXYFront = 16;
/*     */   public static final int planeXYBack = 20;
/*     */   public static final int planeZYRight = 17;
/*     */   public static final int planeZYLeft = 21;
/*     */   public static final int planeXZTop = 18;
/*     */   public static final int planeXZBottom = 22;
/*     */   
/*     */   public ModelPlate(ModelRenderer pMRenderer, Object... pArg)
/*     */   {
/*  42 */     super(pMRenderer, pArg);
/*  43 */     init(pMRenderer, ((Integer)pArg[0]).intValue(), ((Integer)pArg[1]).intValue(), ((Float)pArg[2]).floatValue(), ((Float)pArg[3]).floatValue(), ((Float)pArg[4]).floatValue(), ((Integer)pArg[5]).intValue(), ((Integer)pArg[6]).intValue(), ((Integer)pArg[7]).intValue(), pArg.length < 9 ? 0.0F : ((Float)pArg[8]).floatValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void init(ModelRenderer modelrenderer, int pTextureX, int pTextureY, float pX, float pY, float pZ, int pWidth, int pHeight, int pPlane, float pZoom)
/*     */   {
/*  54 */     boolean lotherplane = (pPlane & 0x4) > 0;
/*  55 */     int lPlane = pPlane & 0x3;
/*     */     float lx;
/*     */     float ly;
/*  58 */     float lz; switch (lPlane)
/*     */     {
/*     */     case 0: 
/*  61 */       this.posX1 = pX;
/*  62 */       this.posY1 = pY;
/*  63 */       this.posZ1 = pZ;
/*  64 */       this.posX2 = (lx = pX + pWidth);
/*  65 */       this.posY2 = (ly = pY + pHeight);
/*  66 */       this.posZ2 = (lz = pZ);
/*  67 */       pX -= pZoom;
/*  68 */       pY -= pZoom;
/*  69 */       lx += pZoom;
/*  70 */       ly += pZoom;
/*  71 */       if (lotherplane) {
/*  72 */         pZ += pZoom;
/*  73 */         lz += pZoom;
/*     */       } else {
/*  75 */         pZ -= pZoom;
/*  76 */         lz -= pZoom;
/*     */       }
/*  78 */       break;
/*     */     
/*     */     case 1: 
/*  81 */       this.posX1 = pX;
/*  82 */       this.posY1 = pY;
/*  83 */       this.posZ1 = pZ;
/*  84 */       this.posX2 = (lx = pX);
/*  85 */       this.posY2 = (ly = pY + pHeight);
/*  86 */       this.posZ2 = (lz = pZ + pWidth);
/*  87 */       pY -= pZoom;
/*  88 */       pZ -= pZoom;
/*  89 */       ly += pZoom;
/*  90 */       lz += pZoom;
/*  91 */       if (lotherplane) {
/*  92 */         pX += pZoom;
/*  93 */         lx += pZoom;
/*     */       } else {
/*  95 */         pX -= pZoom;
/*  96 */         lx -= pZoom;
/*     */       }
/*  98 */       break;
/*     */     
/*     */     case 2: 
/*     */     default: 
/* 102 */       this.posX1 = pX;
/* 103 */       this.posY1 = pY;
/* 104 */       this.posZ1 = pZ;
/* 105 */       this.posX2 = (lx = pX + pWidth);
/* 106 */       this.posY2 = (ly = pY);
/* 107 */       this.posZ2 = (lz = pZ + pHeight);
/* 108 */       pX -= pZoom;
/* 109 */       pZ -= pZoom;
/* 110 */       lx += pZoom;
/* 111 */       lz += pZoom;
/* 112 */       if (lotherplane) {
/* 113 */         pY += pZoom;
/* 114 */         ly += pZoom;
/*     */       } else {
/* 116 */         pY -= pZoom;
/* 117 */         ly -= pZoom;
/*     */       }
/*     */       break;
/*     */     }
/*     */     
/* 122 */     this.quadList = new TexturedQuad[1];
/*     */     
/* 124 */     if (modelrenderer.mirror) {
/* 125 */       if (lPlane == 0)
/*     */       {
/* 127 */         float f7 = lx;
/* 128 */         lx = pX;
/* 129 */         pX = f7;
/* 130 */       } else if (lPlane == 1)
/*     */       {
/* 132 */         float f7 = lz;
/* 133 */         lz = pZ;
/* 134 */         pZ = f7;
/*     */       }
/*     */       else {
/* 137 */         float f7 = lx;
/* 138 */         lx = pX;
/* 139 */         pX = f7;
/*     */       }
/*     */     }
/*     */     
/* 143 */     switch (pPlane) {
/*     */     case 16: 
/*     */     case 17: 
/* 146 */       this.vertexPositions = new PositionTextureVertex[] { new PositionTextureVertex(pX, pY, lz, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY)), new PositionTextureVertex(pX, ly, lz, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(lx, ly, pZ, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(lx, pY, pZ, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY)) };
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */       lotherplane = false;
/* 153 */       break;
/*     */     case 20: 
/*     */     case 21: 
/* 156 */       this.vertexPositions = new PositionTextureVertex[] { new PositionTextureVertex(lx, pY, pZ, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY)), new PositionTextureVertex(lx, ly, pZ, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(pX, ly, lz, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(pX, pY, lz, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY)) };
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 162 */       lotherplane = false;
/* 163 */       break;
/*     */     case 18: 
/* 165 */       this.vertexPositions = new PositionTextureVertex[] { new PositionTextureVertex(pX, pY, lz, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY)), new PositionTextureVertex(pX, ly, pZ, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(lx, ly, pZ, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(lx, pY, lz, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY)) };
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 171 */       lotherplane = false;
/* 172 */       break;
/*     */     case 22: 
/* 174 */       this.vertexPositions = new PositionTextureVertex[] { new PositionTextureVertex(lx, pY, lz, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY)), new PositionTextureVertex(lx, ly, pZ, getU(modelrenderer, pTextureX + pWidth), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(pX, ly, pZ, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY + pHeight)), new PositionTextureVertex(pX, pY, lz, getU(modelrenderer, pTextureX), getV(modelrenderer, pTextureY)) };
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 180 */       lotherplane = false;
/* 181 */       break;
/*     */     case 0: case 1: case 2: 
/*     */     case 3: case 4: case 5: 
/*     */     case 6: case 7: case 8: 
/*     */     case 9: case 10: case 11: 
/*     */     case 12: case 13: 
/*     */     case 14: case 15: 
/*     */     case 19: default: 
/* 189 */       this.vertexPositions = new PositionTextureVertex[] { new PositionTextureVertex(pX, pY, pZ, 0.0F, 0.0F), new PositionTextureVertex(lx, pY, lz, 0.0F, 8.0F), new PositionTextureVertex(lx, ly, lz, 8.0F, 8.0F), new PositionTextureVertex(pX, ly, pZ, 8.0F, 0.0F) };
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 198 */     if ((pPlane & 0x10) > 0) {
/* 199 */       this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { this.vertexPositions[0], this.vertexPositions[1], this.vertexPositions[2], this.vertexPositions[3] });
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 205 */       if (modelrenderer.mirror) {
/* 206 */         this.quadList[0].flipFace();
/*     */       }
/*     */     }
/* 209 */     else if (lotherplane)
/*     */     {
/* 211 */       this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { this.vertexPositions[0], this.vertexPositions[1], this.vertexPositions[2], this.vertexPositions[3] }, pTextureX, pTextureY, pTextureX + pWidth, pTextureY + pHeight, modelrenderer.textureWidth, modelrenderer.textureHeight);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 221 */       this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { this.vertexPositions[1], this.vertexPositions[0], this.vertexPositions[3], this.vertexPositions[2] }, pTextureX, pTextureY, pTextureX + pWidth, pTextureY + pHeight, modelrenderer.textureWidth, modelrenderer.textureHeight);
/*     */     }
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
/*     */   public float getU(ModelRenderer pRender, int pU)
/*     */   {
/* 235 */     float lf = pU / pRender.textureWidth;
/* 236 */     return lf;
/*     */   }
/*     */   
/*     */   public float getV(ModelRenderer pRender, int pV) {
/* 240 */     float lf = pV / pRender.textureHeight;
/* 241 */     return lf;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelPlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
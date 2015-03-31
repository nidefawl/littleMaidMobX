/*    */ package mmmlibx.lib.multiModel.model.mc162;
/*    */ 
/*    */ import net.minecraft.client.model.PositionTextureVertex;
/*    */ import net.minecraft.client.model.TexturedQuad;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBox
/*    */   extends ModelBoxBase
/*    */ {
/*    */   public ModelBox(ModelRenderer pMRenderer, Object... pArg)
/*    */   {
/* 14 */     super(pMRenderer, pArg);
/* 15 */     init(pMRenderer, ((Integer)pArg[0]).intValue(), ((Integer)pArg[1]).intValue(), ((Float)pArg[2]).floatValue(), ((Float)pArg[3]).floatValue(), ((Float)pArg[4]).floatValue(), ((Integer)pArg[5]).intValue(), ((Integer)pArg[6]).intValue(), ((Integer)pArg[7]).intValue(), pArg.length < 9 ? 0.0F : ((Float)pArg[8]).floatValue());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void init(ModelRenderer pMRenderer, int pTexX, int pTexY, float pX, float pY, float pZ, int pW, int pH, int pD, float pSize)
/*    */   {
/* 24 */     this.posX1 = pX;
/* 25 */     this.posY1 = pY;
/* 26 */     this.posZ1 = pZ;
/* 27 */     this.posX2 = (pX + pW);
/* 28 */     this.posY2 = (pY + pH);
/* 29 */     this.posZ2 = (pZ + pD);
/* 30 */     this.vertexPositions = new PositionTextureVertex[8];
/* 31 */     this.quadList = new TexturedQuad[6];
/* 32 */     float lx = pX + pW;
/* 33 */     float ly = pY + pH;
/* 34 */     float lz = pZ + pD;
/* 35 */     pX -= pSize;
/* 36 */     pY -= pSize;
/* 37 */     pZ -= pSize;
/* 38 */     lx += pSize;
/* 39 */     ly += pSize;
/* 40 */     lz += pSize;
/*    */     
/* 42 */     if (pMRenderer.mirror) {
/* 43 */       float var14 = lx;
/* 44 */       lx = pX;
/* 45 */       pX = var14;
/*    */     }
/*    */     
/* 48 */     PositionTextureVertex lxyz = new PositionTextureVertex(pX, pY, pZ, 0.0F, 0.0F);
/* 49 */     PositionTextureVertex lwyz = new PositionTextureVertex(lx, pY, pZ, 0.0F, 8.0F);
/* 50 */     PositionTextureVertex lwhz = new PositionTextureVertex(lx, ly, pZ, 8.0F, 8.0F);
/* 51 */     PositionTextureVertex lxhz = new PositionTextureVertex(pX, ly, pZ, 8.0F, 0.0F);
/* 52 */     PositionTextureVertex lxyd = new PositionTextureVertex(pX, pY, lz, 0.0F, 0.0F);
/* 53 */     PositionTextureVertex lwyd = new PositionTextureVertex(lx, pY, lz, 0.0F, 8.0F);
/* 54 */     PositionTextureVertex lwhd = new PositionTextureVertex(lx, ly, lz, 8.0F, 8.0F);
/* 55 */     PositionTextureVertex lxhd = new PositionTextureVertex(pX, ly, lz, 8.0F, 0.0F);
/* 56 */     this.vertexPositions[0] = lxyz;
/* 57 */     this.vertexPositions[1] = lwyz;
/* 58 */     this.vertexPositions[2] = lwhz;
/* 59 */     this.vertexPositions[3] = lxhz;
/* 60 */     this.vertexPositions[4] = lxyd;
/* 61 */     this.vertexPositions[5] = lwyd;
/* 62 */     this.vertexPositions[6] = lwhd;
/* 63 */     this.vertexPositions[7] = lxhd;
/* 64 */     this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { lwyd, lwyz, lwhz, lwhd }, pTexX + pD + pW, pTexY + pD, pTexX + pD + pW + pD, pTexY + pD + pH, pMRenderer.textureWidth, pMRenderer.textureHeight);
/* 65 */     this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] { lxyz, lxyd, lxhd, lxhz }, pTexX, pTexY + pD, pTexX + pD, pTexY + pD + pH, pMRenderer.textureWidth, pMRenderer.textureHeight);
/* 66 */     this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] { lwyd, lxyd, lxyz, lwyz }, pTexX + pD, pTexY, pTexX + pD + pW, pTexY + pD, pMRenderer.textureWidth, pMRenderer.textureHeight);
/* 67 */     this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] { lwhz, lxhz, lxhd, lwhd }, pTexX + pD + pW, pTexY + pD, pTexX + pD + pW + pW, pTexY, pMRenderer.textureWidth, pMRenderer.textureHeight);
/* 68 */     this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] { lwyz, lxyz, lxhz, lwhz }, pTexX + pD, pTexY + pD, pTexX + pD + pW, pTexY + pD + pH, pMRenderer.textureWidth, pMRenderer.textureHeight);
/* 69 */     this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] { lxyd, lwyd, lwhd, lxhd }, pTexX + pD + pW + pD, pTexY + pD, pTexX + pD + pW + pD + pW, pTexY + pD + pH, pMRenderer.textureWidth, pMRenderer.textureHeight);
/*    */     
/* 71 */     if (pMRenderer.mirror) {
/* 72 */       for (int li = 0; li < this.quadList.length; li++) {
/* 73 */         this.quadList[li].flipFace();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
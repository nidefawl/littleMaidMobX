/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.TextureOffset;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemCloth;
/*     */ import net.minecraft.item.ItemSkull;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import wrapper.W_Client;
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
/*     */ public class ModelRenderer
/*     */ {
/*     */   public float textureWidth;
/*     */   public float textureHeight;
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   public float rotationPointX;
/*     */   public float rotationPointY;
/*     */   public float rotationPointZ;
/*     */   public float rotateAngleX;
/*     */   public float rotateAngleY;
/*     */   public float rotateAngleZ;
/*     */   protected boolean compiled;
/*     */   protected int displayList;
/*     */   public boolean mirror;
/*     */   public boolean showModel;
/*     */   public boolean isHidden;
/*     */   public boolean isRendering;
/*     */   public List<ModelBoxBase> cubeList;
/*     */   public List<ModelRenderer> childModels;
/*     */   public final String boxName;
/*     */   protected ModelBase baseModel;
/*     */   public ModelRenderer pearent;
/*     */   public float offsetX;
/*     */   public float offsetY;
/*     */   public float offsetZ;
/*     */   public float scaleX;
/*     */   public float scaleY;
/*     */   public float scaleZ;
/*     */   public static final float radFactor = 57.295776F;
/*     */   public static final float degFactor = 0.017453292F;
/*     */   public int rotatePriority;
/*     */   public static final int RotXYZ = 0;
/*     */   public static final int RotXZY = 1;
/*     */   public static final int RotYXZ = 2;
/*     */   public static final int RotYZX = 3;
/*     */   public static final int RotZXY = 4;
/*     */   public static final int RotZYX = 5;
/*     */   protected ItemStack itemstack;
/*     */   public boolean adjust;
/*     */   public FloatBuffer matrix;
/*     */   public boolean isInvertX;
/*  98 */   private RenderBlocks renderBlocksIr = new RenderBlocks();
/*     */   
/*     */ 
/*     */   public ModelRenderer(ModelBase pModelBase, String pName)
/*     */   {
/* 103 */     this.textureWidth = 64.0F;
/* 104 */     this.textureHeight = 32.0F;
/* 105 */     this.compiled = false;
/* 106 */     this.displayList = 0;
/* 107 */     this.mirror = false;
/* 108 */     this.showModel = true;
/* 109 */     this.isHidden = false;
/* 110 */     this.isRendering = true;
/* 111 */     this.cubeList = new ArrayList();
/* 112 */     this.baseModel = pModelBase;
/* 113 */     pModelBase.boxList.add(this);
/* 114 */     this.boxName = pName;
/* 115 */     setTextureSize(pModelBase.textureWidth, pModelBase.textureHeight);
/*     */     
/* 117 */     this.rotatePriority = 0;
/* 118 */     this.itemstack = null;
/* 119 */     this.adjust = true;
/* 120 */     this.matrix = BufferUtils.createFloatBuffer(16);
/* 121 */     this.isInvertX = false;
/*     */     
/* 123 */     this.scaleX = 1.0F;
/* 124 */     this.scaleY = 1.0F;
/* 125 */     this.scaleZ = 1.0F;
/*     */     
/* 127 */     this.pearent = null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ModelRenderer(ModelBase pModelBase, int px, int py)
/*     */   {
/* 133 */     this(pModelBase, null);
/* 134 */     setTextureOffset(px, py);
/*     */   }
/*     */   
/*     */   public ModelRenderer(ModelBase pModelBase) {
/* 138 */     this(pModelBase, (String)null);
/*     */   }
/*     */   
/*     */   public ModelRenderer(ModelBase pModelBase, int px, int py, float pScaleX, float pScaleY, float pScaleZ) {
/* 142 */     this(pModelBase, px, py);
/* 143 */     this.scaleX = pScaleX;
/* 144 */     this.scaleY = pScaleY;
/* 145 */     this.scaleZ = pScaleZ;
/*     */   }
/*     */   
/*     */   public ModelRenderer(ModelBase pModelBase, float pScaleX, float pScaleY, float pScaleZ) {
/* 149 */     this(pModelBase);
/* 150 */     this.scaleX = pScaleX;
/* 151 */     this.scaleY = pScaleY;
/* 152 */     this.scaleZ = pScaleZ;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addChild(ModelRenderer pModelRenderer)
/*     */   {
/* 158 */     if (this.childModels == null) {
/* 159 */       this.childModels = new ArrayList();
/*     */     }
/* 161 */     this.childModels.add(pModelRenderer);
/* 162 */     pModelRenderer.pearent = this;
/*     */   }
/*     */   
/*     */   public ModelRenderer setTextureOffset(int pOffsetX, int pOffsetY) {
/* 166 */     this.textureOffsetX = pOffsetX;
/* 167 */     this.textureOffsetY = pOffsetY;
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addBox(String pName, float pX, float pY, float pZ, int pWidth, int pHeight, int pDepth)
/*     */   {
/* 173 */     addParts(ModelBox.class, pName, new Object[] { Float.valueOf(pX), Float.valueOf(pY), Float.valueOf(pZ), Integer.valueOf(pWidth), Integer.valueOf(pHeight), Integer.valueOf(pDepth), Float.valueOf(0.0F) });
/* 174 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addBox(float pX, float pY, float pZ, int pWidth, int pHeight, int pDepth)
/*     */   {
/* 179 */     addParts(ModelBox.class, new Object[] { Float.valueOf(pX), Float.valueOf(pY), Float.valueOf(pZ), Integer.valueOf(pWidth), Integer.valueOf(pHeight), Integer.valueOf(pDepth), Float.valueOf(0.0F) });
/* 180 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addBox(float pX, float pY, float pZ, int pWidth, int pHeight, int pDepth, float pSizeAdjust)
/*     */   {
/* 185 */     addParts(ModelBox.class, new Object[] { Float.valueOf(pX), Float.valueOf(pY), Float.valueOf(pZ), Integer.valueOf(pWidth), Integer.valueOf(pHeight), Integer.valueOf(pDepth), Float.valueOf(pSizeAdjust) });
/* 186 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer setRotationPoint(float pX, float pY, float pZ) {
/* 190 */     this.rotationPointX = pX;
/* 191 */     this.rotationPointY = pY;
/* 192 */     this.rotationPointZ = pZ;
/* 193 */     return this;
/*     */   }
/*     */   
/*     */   public void render(float par1, boolean pIsRender)
/*     */   {
/* 198 */     if (this.isHidden) return;
/* 199 */     if (!this.showModel) { return;
/*     */     }
/* 201 */     if (!this.compiled) {
/* 202 */       compileDisplayList(par1);
/*     */     }
/*     */     
/* 205 */     GL11.glPushMatrix();
/* 206 */     GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
/*     */     
/* 208 */     if ((this.rotationPointX != 0.0F) || (this.rotationPointY != 0.0F) || (this.rotationPointZ != 0.0F)) {
/* 209 */       GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
/*     */     }
/* 211 */     if ((this.rotateAngleX != 0.0F) || (this.rotateAngleY != 0.0F) || (this.rotateAngleZ != 0.0F)) {
/* 212 */       setRotation();
/*     */     }
/* 214 */     renderObject(par1, pIsRender);
/* 215 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/* 218 */   public void render(float par1) { render(par1, true); }
/*     */   
/*     */   public void renderWithRotation(float par1)
/*     */   {
/* 222 */     if (this.isHidden) return;
/* 223 */     if (!this.showModel) { return;
/*     */     }
/* 225 */     if (!this.compiled) {
/* 226 */       compileDisplayList(par1);
/*     */     }
/*     */     
/* 229 */     GL11.glPushMatrix();
/* 230 */     GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
/*     */     
/* 232 */     setRotation();
/*     */     
/* 234 */     GL11.glCallList(this.displayList);
/* 235 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void postRender(float par1) {
/* 239 */     if (this.isHidden) return;
/* 240 */     if (!this.showModel) { return;
/*     */     }
/* 242 */     if (!this.compiled) {
/* 243 */       compileDisplayList(par1);
/*     */     }
/*     */     
/* 246 */     if (this.pearent != null) {
/* 247 */       this.pearent.postRender(par1);
/*     */     }
/*     */     
/* 250 */     GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
/*     */     
/* 252 */     if ((this.rotationPointX != 0.0F) || (this.rotationPointY != 0.0F) || (this.rotationPointZ != 0.0F)) {
/* 253 */       GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
/*     */     }
/* 255 */     if ((this.rotateAngleX != 0.0F) || (this.rotateAngleY != 0.0F) || (this.rotateAngleZ != 0.0F)) {
/* 256 */       setRotation();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void compileDisplayList(float par1) {
/* 261 */     this.displayList = GLAllocation.generateDisplayLists(1);
/* 262 */     GL11.glNewList(this.displayList, 4864);
/* 263 */     Tessellator tessellator = Tessellator.instance;
/*     */     
/* 265 */     for (int i = 0; i < this.cubeList.size(); i++) {
/* 266 */       ((ModelBoxBase)this.cubeList.get(i)).render(tessellator, par1);
/*     */     }
/*     */     
/* 269 */     GL11.glEndList();
/* 270 */     this.compiled = true;
/*     */   }
/*     */   
/*     */   public ModelRenderer setTextureSize(int pWidth, int pHeight) {
/* 274 */     this.textureWidth = pWidth;
/* 275 */     this.textureHeight = pHeight;
/* 276 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ModelRenderer addCubeList(ModelBoxBase pModelBoxBase)
/*     */   {
/* 286 */     this.cubeList.add(pModelBoxBase);
/* 287 */     return this;
/*     */   }
/*     */   
/*     */   protected ModelBoxBase getModelBoxBase(Class<? extends ModelBoxBase> pModelBoxBase, Object... pArg) {
/*     */     try {
/* 292 */       Constructor<? extends ModelBoxBase> lconstructor = pModelBoxBase.getConstructor(new Class[] { ModelRenderer.class, Object[].class });
/*     */       
/* 294 */       return (ModelBoxBase)lconstructor.newInstance(new Object[] { this, pArg });
/*     */     } catch (Exception e) {
/* 296 */       e.printStackTrace();
/*     */     }
/* 298 */     return null;
/*     */   }
/*     */   
/*     */   protected Object[] getArg(Object... pArg) {
/* 302 */     Object[] lobject = new Object[pArg.length + 2];
/* 303 */     lobject[0] = Integer.valueOf(this.textureOffsetX);
/* 304 */     lobject[1] = Integer.valueOf(this.textureOffsetY);
/* 305 */     for (int li = 0; li < pArg.length; li++) {
/* 306 */       lobject[(2 + li)] = pArg[li];
/*     */     }
/* 308 */     return lobject;
/*     */   }
/*     */   
/*     */   public ModelRenderer addParts(Class<? extends ModelBoxBase> pModelBoxBase, String pName, Object... pArg) {
/* 312 */     pName = this.boxName + "." + pName;
/* 313 */     TextureOffset ltextureoffset = this.baseModel.getTextureOffset(pName);
/* 314 */     setTextureOffset(ltextureoffset.textureOffsetX, ltextureoffset.textureOffsetY);
/* 315 */     addCubeList(getModelBoxBase(pModelBoxBase, getArg(pArg)).setBoxName(pName));
/* 316 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addParts(Class<? extends ModelBoxBase> pModelBoxBase, Object... pArg) {
/* 320 */     addCubeList(getModelBoxBase(pModelBoxBase, getArg(pArg)));
/* 321 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ModelRenderer addPartsTexture(Class<? extends ModelBoxBase> pModelBoxBase, String pName, Object... pArg)
/*     */   {
/* 329 */     pName = this.boxName + "." + pName;
/* 330 */     addCubeList(getModelBoxBase(pModelBoxBase, pArg).setBoxName(pName));
/* 331 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ModelRenderer addPartsTexture(Class<? extends ModelBoxBase> pModelBoxBase, Object... pArg)
/*     */   {
/* 339 */     addCubeList(getModelBoxBase(pModelBoxBase, pArg));
/* 340 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public ModelRenderer addPlate(float pX, float pY, float pZ, int pWidth, int pHeight, int pFacePlane)
/*     */   {
/* 346 */     addParts(ModelPlate.class, new Object[] { Float.valueOf(pX), Float.valueOf(pY), Float.valueOf(pZ), Integer.valueOf(pWidth), Integer.valueOf(pHeight), Integer.valueOf(pFacePlane), Float.valueOf(0.0F) });
/* 347 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addPlate(float pX, float pY, float pZ, int pWidth, int pHeight, int pFacePlane, float pSizeAdjust)
/*     */   {
/* 352 */     addParts(ModelPlate.class, new Object[] { Float.valueOf(pX), Float.valueOf(pY), Float.valueOf(pZ), Integer.valueOf(pWidth), Integer.valueOf(pHeight), Integer.valueOf(pFacePlane), Float.valueOf(pSizeAdjust) });
/* 353 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addPlate(String pName, float pX, float pY, float pZ, int pWidth, int pHeight, int pFacePlane)
/*     */   {
/* 358 */     addParts(ModelPlate.class, pName, new Object[] { Float.valueOf(pX), Float.valueOf(pY), Float.valueOf(pZ), Integer.valueOf(pWidth), Integer.valueOf(pHeight), Integer.valueOf(pFacePlane), Float.valueOf(0.0F) });
/* 359 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clearCubeList()
/*     */   {
/* 366 */     this.cubeList.clear();
/* 367 */     this.compiled = false;
/* 368 */     if (this.childModels != null) {
/* 369 */       this.childModels.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean renderItems(ModelMultiBase pModelMulti, IModelCaps pEntityCaps, boolean pRealBlock, int pIndex)
/*     */   {
/* 375 */     ItemStack[] litemstacks = (ItemStack[])ModelCapsHelper.getCapsValue(pEntityCaps, 309, new Object[0]);
/* 376 */     if (litemstacks == null) return false;
/* 377 */     EnumAction[] lactions = (EnumAction[])ModelCapsHelper.getCapsValue(pEntityCaps, 310, new Object[0]);
/* 378 */     EntityLivingBase lentity = (EntityLivingBase)pEntityCaps.getCapsValue(32, new Object[0]);
/*     */     
/* 380 */     renderItems(lentity, pModelMulti.render, pRealBlock, lactions[pIndex], litemstacks[pIndex]);
/* 381 */     return true;
/*     */   }
/*     */   
/*     */   public void renderItemsHead(ModelMultiBase pModelMulti, IModelCaps pEntityCaps) {
/* 385 */     ItemStack lis = (ItemStack)pEntityCaps.getCapsValue(306, new Object[0]);
/* 386 */     EntityLivingBase lentity = (EntityLivingBase)pEntityCaps.getCapsValue(32, new Object[0]);
/*     */     
/* 388 */     renderItems(lentity, pModelMulti.render, true, null, lis);
/*     */   }
/*     */   
/*     */   protected void renderItems(EntityLivingBase pEntityLiving, Render pRender, boolean pRealBlock, EnumAction pAction, ItemStack pItemStack)
/*     */   {
/* 393 */     this.itemstack = pItemStack;
/* 394 */     renderItems(pEntityLiving, pRender, pRealBlock, pAction);
/*     */   }
/*     */   
/*     */   protected void renderItems(EntityLivingBase pEntityLiving, Render pRender, boolean pRealBlock, EnumAction pAction) {
/* 398 */     if (this.itemstack == null) { return;
/*     */     }
/*     */     
/* 401 */     GL11.glPushMatrix();
/* 402 */     Item litem = this.itemstack.getItem();
/*     */     
/*     */ 
/* 405 */     if (this.adjust)
/*     */     {
/*     */ 
/* 408 */       if ((pRealBlock) && ((litem instanceof ItemBlock))) {
/* 409 */         float f2 = 0.625F;
/* 410 */         GL11.glScalef(f2, -f2, -f2);
/* 411 */         GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/* 412 */       } else if ((pRealBlock) && ((litem instanceof ItemSkull))) {
/* 413 */         float f2 = 1.0625F;
/* 414 */         GL11.glScalef(f2, -f2, -f2);
/*     */ 
/*     */       }
/* 417 */       else if (((litem instanceof ItemBlock)) && (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(litem).getRenderType())))
/*     */       {
/* 419 */         float var6 = 0.5F;
/*     */         
/* 421 */         GL11.glTranslatef(0.0F, 0.1875F, -0.2125F);
/* 422 */         var6 *= 0.75F;
/* 423 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 424 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 425 */         GL11.glScalef(var6, -var6, var6);
/* 426 */       } else if ((litem instanceof ItemBow)) {
/* 427 */         float var6 = 0.625F;
/* 428 */         GL11.glTranslatef(-0.05F, 0.125F, 0.3125F);
/* 429 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 430 */         GL11.glScalef(var6, -var6, var6);
/* 431 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 432 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 433 */       } else if (litem.isFull3D()) {
/* 434 */         float var6 = 0.625F;
/*     */         
/* 436 */         if (litem.shouldRotateAroundWhenRendering()) {
/* 437 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 438 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 441 */         if (pAction == EnumAction.block) {
/* 442 */           GL11.glTranslatef(0.05F, 0.0F, -0.1F);
/* 443 */           GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
/* 444 */           GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
/* 445 */           GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
/*     */         }
/*     */         
/* 448 */         GL11.glTranslatef(0.0F, 0.1875F, 0.1F);
/* 449 */         GL11.glScalef(var6, -var6, var6);
/* 450 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 451 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 453 */         float var6 = 0.375F;
/* 454 */         GL11.glTranslatef(0.15F, 0.15F, -0.05F);
/*     */         
/* 456 */         GL11.glScalef(var6, var6, var6);
/* 457 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 458 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 459 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 465 */     if ((pRealBlock) && ((litem instanceof ItemSkull))) {
/* 466 */       String lsowner = "";
/* 467 */       if ((this.itemstack.hasTagCompound()) && (this.itemstack.getTagCompound().hasKey("SkullOwner"))) {
/* 468 */         lsowner = this.itemstack.getTagCompound().getString("SkullOwner");
/*     */       }
/* 470 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*     */ 
/* 473 */       W_Client.renderSkeletonHead(TileEntitySkullRenderer.field_147536_b, -0.5F, 0.0F, -0.5F, 1, 180.0F, this.itemstack.getItemDamage(), lsowner);
/* 474 */     } else if ((pRealBlock) && ((litem instanceof ItemBlock)))
/*     */     {
/*     */ 
/* 477 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 479 */       int var4 = pEntityLiving.getBrightnessForRender(0.0F);
/* 480 */       int var5 = var4 % 65536;
/* 481 */       int var6 = var4 / 65536;
/* 482 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var5 / 1.0F, var6 / 1.0F);
/*     */       
/*     */ 
/* 485 */       GL11.glEnable(2884);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 491 */       renderBlock(this.itemstack);
/*     */       
/* 493 */       GL11.glDisable(2884);
/*     */     }
/*     */     else
/*     */     {
/* 497 */       for (int j = 0; j <= (litem.requiresMultipleRenderPasses() ? 1 : 0); j++) {
/* 498 */         int k = this.itemstack.getItem().getColorFromItemStack(this.itemstack, j);
/* 499 */         float f15 = (k >> 16 & 0xFF) / 255.0F;
/* 500 */         float f17 = (k >> 8 & 0xFF) / 255.0F;
/* 501 */         float f19 = (k & 0xFF) / 255.0F;
/* 502 */         GL11.glColor4f(f15, f17, f19, 1.0F);
/* 503 */         RenderManager.instance.itemRenderer.renderItem(pEntityLiving, this.itemstack, j);
/*     */       }
/*     */     }
/*     */     
/* 507 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void renderBlock(ItemStack par2ItemStack)
/*     */   {
/* 512 */     GL11.glPushMatrix();
/* 513 */     TextureManager texturemanager = Minecraft.getMinecraft().renderEngine;
/* 514 */     Item item = par2ItemStack.getItem();
/* 515 */     Block block = Block.getBlockFromItem(item);
/*     */     
/* 517 */     if ((par2ItemStack.getItemSpriteNumber() == 0) && ((item instanceof ItemBlock)))
/*     */     {
/* 519 */       texturemanager.bindTexture(texturemanager.getResourceLocation(0));
/*     */       
/* 521 */       GL11.glDisable(2896);
/* 522 */       if ((item instanceof ItemCloth))
/*     */       {
/* 524 */         GL11.glEnable(3042);
/* 525 */         GL11.glDepthMask(false);
/* 526 */         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
/*     */         
/* 528 */         GL11.glAlphaFunc(516, 0.001F);
/*     */         
/* 530 */         this.renderBlocksIr.renderBlockAsItem(block, par2ItemStack.getItemDamage(), 1.0F);
/* 531 */         GL11.glDepthMask(true);
/* 532 */         GL11.glDisable(3042);
/*     */       }
/*     */       else
/*     */       {
/* 536 */         this.renderBlocksIr.renderBlockAsItem(block, par2ItemStack.getItemDamage(), 1.0F);
/*     */       }
/* 538 */       GL11.glEnable(2896);
/*     */     }
/*     */     
/* 541 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotatePriority(int pValue)
/*     */   {
/* 550 */     this.rotatePriority = pValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setRotation()
/*     */   {
/* 558 */     switch (this.rotatePriority) {
/*     */     case 0: 
/* 560 */       if (this.rotateAngleZ != 0.0F) {
/* 561 */         GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/* 563 */       if (this.rotateAngleY != 0.0F) {
/* 564 */         GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 566 */       if (this.rotateAngleX != 0.0F) {
/* 567 */         GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       break;
/*     */     case 1: 
/* 571 */       if (this.rotateAngleY != 0.0F) {
/* 572 */         GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 574 */       if (this.rotateAngleZ != 0.0F) {
/* 575 */         GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/* 577 */       if (this.rotateAngleX != 0.0F) {
/* 578 */         GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       break;
/*     */     case 2: 
/* 582 */       if (this.rotateAngleZ != 0.0F) {
/* 583 */         GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/* 585 */       if (this.rotateAngleX != 0.0F) {
/* 586 */         GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 588 */       if (this.rotateAngleY != 0.0F) {
/* 589 */         GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       break;
/*     */     case 3: 
/* 593 */       if (this.rotateAngleX != 0.0F) {
/* 594 */         GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 596 */       if (this.rotateAngleZ != 0.0F) {
/* 597 */         GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/* 599 */       if (this.rotateAngleY != 0.0F) {
/* 600 */         GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       break;
/*     */     case 4: 
/* 604 */       if (this.rotateAngleY != 0.0F) {
/* 605 */         GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 607 */       if (this.rotateAngleX != 0.0F) {
/* 608 */         GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 610 */       if (this.rotateAngleZ != 0.0F) {
/* 611 */         GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       break;
/*     */     case 5: 
/* 615 */       if (this.rotateAngleX != 0.0F) {
/* 616 */         GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */       }
/* 618 */       if (this.rotateAngleY != 0.0F) {
/* 619 */         GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 621 */       if (this.rotateAngleZ != 0.0F) {
/* 622 */         GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/*     */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   protected void renderObject(float par1, boolean pRendering)
/*     */   {
/* 633 */     GL11.glGetFloat(2982, this.matrix);
/* 634 */     if ((pRendering) && (this.isRendering)) {
/* 635 */       GL11.glPushMatrix();
/* 636 */       GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
/* 637 */       GL11.glCallList(this.displayList);
/* 638 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 641 */     if (this.childModels != null) {
/* 642 */       for (int li = 0; li < this.childModels.size(); li++) {
/* 643 */         ((ModelRenderer)this.childModels.get(li)).render(par1, pRendering);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ModelRenderer loadMatrix()
/*     */   {
/* 652 */     GL11.glLoadMatrix(this.matrix);
/* 653 */     if (this.isInvertX) {
/* 654 */       GL11.glScalef(-1.0F, 1.0F, 1.0F);
/*     */     }
/* 656 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean getMirror()
/*     */   {
/* 663 */     return this.mirror;
/*     */   }
/*     */   
/*     */   public ModelRenderer setMirror(boolean flag) {
/* 667 */     this.mirror = flag;
/* 668 */     return this;
/*     */   }
/*     */   
/*     */   public boolean getVisible() {
/* 672 */     return this.showModel;
/*     */   }
/*     */   
/*     */   public void setVisible(boolean flag) {
/* 676 */     this.showModel = flag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getRotateAngleX()
/*     */   {
/* 683 */     return this.rotateAngleX;
/*     */   }
/*     */   
/*     */   public float getRotateAngleDegX() {
/* 687 */     return this.rotateAngleX * 57.295776F;
/*     */   }
/*     */   
/*     */   public float setRotateAngleX(float value) {
/* 691 */     return this.rotateAngleX = value;
/*     */   }
/*     */   
/*     */   public float setRotateAngleDegX(float value) {
/* 695 */     return this.rotateAngleX = value * 0.017453292F;
/*     */   }
/*     */   
/*     */   public float addRotateAngleX(float value) {
/* 699 */     return this.rotateAngleX += value;
/*     */   }
/*     */   
/*     */   public float addRotateAngleDegX(float value) {
/* 703 */     return this.rotateAngleX += value * 0.017453292F;
/*     */   }
/*     */   
/*     */   public float getRotateAngleY() {
/* 707 */     return this.rotateAngleY;
/*     */   }
/*     */   
/*     */   public float getRotateAngleDegY() {
/* 711 */     return this.rotateAngleY * 57.295776F;
/*     */   }
/*     */   
/*     */   public float setRotateAngleY(float value) {
/* 715 */     return this.rotateAngleY = value;
/*     */   }
/*     */   
/*     */   public float setRotateAngleDegY(float value) {
/* 719 */     return this.rotateAngleY = value * 0.017453292F;
/*     */   }
/*     */   
/*     */   public float addRotateAngleY(float value) {
/* 723 */     return this.rotateAngleY += value;
/*     */   }
/*     */   
/*     */   public float addRotateAngleDegY(float value) {
/* 727 */     return this.rotateAngleY += value * 0.017453292F;
/*     */   }
/*     */   
/*     */   public float getRotateAngleZ() {
/* 731 */     return this.rotateAngleZ;
/*     */   }
/*     */   
/*     */   public float getRotateAngleDegZ() {
/* 735 */     return this.rotateAngleZ * 57.295776F;
/*     */   }
/*     */   
/*     */   public float setRotateAngleZ(float value) {
/* 739 */     return this.rotateAngleZ = value;
/*     */   }
/*     */   
/*     */   public float setRotateAngleDegZ(float value) {
/* 743 */     return this.rotateAngleZ = value * 0.017453292F;
/*     */   }
/*     */   
/*     */   public float addRotateAngleZ(float value) {
/* 747 */     return this.rotateAngleZ += value;
/*     */   }
/*     */   
/*     */   public float addRotateAngleDegZ(float value) {
/* 751 */     return this.rotateAngleZ += value * 0.017453292F;
/*     */   }
/*     */   
/*     */   public ModelRenderer setRotateAngle(float x, float y, float z) {
/* 755 */     this.rotateAngleX = x;
/* 756 */     this.rotateAngleY = y;
/* 757 */     this.rotateAngleZ = z;
/* 758 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer setRotateAngleDeg(float x, float y, float z) {
/* 762 */     this.rotateAngleX = (x * 0.017453292F);
/* 763 */     this.rotateAngleY = (y * 0.017453292F);
/* 764 */     this.rotateAngleZ = (z * 0.017453292F);
/* 765 */     return this;
/*     */   }
/*     */   
/*     */   public float getRotationPointX() {
/* 769 */     return this.rotationPointX;
/*     */   }
/*     */   
/*     */   public float setRotationPointX(float value) {
/* 773 */     return this.rotationPointX = value;
/*     */   }
/*     */   
/*     */   public float addRotationPointX(float value) {
/* 777 */     return this.rotationPointX += value;
/*     */   }
/*     */   
/*     */   public float getRotationPointY() {
/* 781 */     return this.rotationPointY;
/*     */   }
/*     */   
/*     */   public float setRotationPointY(float value) {
/* 785 */     return this.rotationPointY = value;
/*     */   }
/*     */   
/*     */   public float addRotationPointY(float value) {
/* 789 */     return this.rotationPointY += value;
/*     */   }
/*     */   
/*     */   public float getRotationPointZ() {
/* 793 */     return this.rotationPointZ;
/*     */   }
/*     */   
/*     */   public float setRotationPointZ(float value) {
/* 797 */     return this.rotationPointZ = value;
/*     */   }
/*     */   
/*     */   public float addRotationPointZ(float value) {
/* 801 */     return this.rotationPointZ += value;
/*     */   }
/*     */   
/*     */   public ModelRenderer setScale(float pX, float pY, float pZ) {
/* 805 */     this.scaleX = pX;
/* 806 */     this.scaleY = pY;
/* 807 */     this.scaleZ = pZ;
/* 808 */     return this;
/*     */   }
/*     */   
/*     */   public float setScaleX(float pValue) {
/* 812 */     return this.scaleX = pValue;
/*     */   }
/*     */   
/*     */   public float setScaleY(float pValue) {
/* 816 */     return this.scaleY = pValue;
/*     */   }
/*     */   
/*     */   public float setScaleZ(float pValue) {
/* 820 */     return this.scaleZ = pValue;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
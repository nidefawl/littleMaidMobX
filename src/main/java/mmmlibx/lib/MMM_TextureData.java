/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import littleMaidMobX.LMM_EntityLittleMaid;
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
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
/*     */ public class MMM_TextureData
/*     */ {
/*     */   public EntityLivingBase owner;
/*     */   public IModelCaps entityCaps;
/*     */   public ResourceLocation[][] textures;
/*     */   public int color;
/*     */   public boolean contract;
/*     */   public MMM_TextureBoxBase[] textureBox;
/*     */   public int[] textureIndex;
/*     */   public ModelMultiBase[] textureModel;
/*     */   public int selectValue;
/*  48 */   public int data_Color = 19;
/*  49 */   public int data_Texture = 20;
/*  50 */   public int data_Value = 21;
/*     */   
/*     */   public MMM_TextureData(EntityLivingBase pEntity, IModelCaps pCaps)
/*     */   {
/*  54 */     this.owner = pEntity;
/*  55 */     this.entityCaps = pCaps;
/*  56 */     this.textures = new ResourceLocation[][] { { null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } };
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
/*  78 */     this.color = 12;
/*  79 */     this.contract = false;
/*  80 */     this.textureBox = new MMM_TextureBoxBase[2];
/*  81 */     this.textureBox[0] = (this.textureBox[1] = MMM_TextureManager.instance.getDefaultTexture(this.owner.getClass()));
/*  82 */     this.textureIndex = new int[] { 0, 0 };
/*  83 */     this.textureModel = new ModelMultiBase[3];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean setTextureNames()
/*     */   {
/*  90 */     this.textureModel[0] = null;
/*  91 */     this.textureModel[1] = null;
/*  92 */     this.textureModel[2] = null;
/*     */     
/*  94 */     if (this.owner.worldObj.isRemote) {
/*  95 */       return setTextureNamesClient();
/*     */     }
/*  97 */     return setTextureNamesServer();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean setTextureNamesClient()
/*     */   {
/* 106 */     boolean lf = false;
/*     */     
/*     */ 
/* 109 */     if ((this.textureBox[0] instanceof MMM_TextureBox)) {
/* 110 */       int lc = (this.color & 0xFF) + (this.contract ? 0 : 48);
/* 111 */       MMM_TextureBox lbox = (MMM_TextureBox)this.textureBox[0];
/* 112 */       if (lbox.hasColor(lc)) {
/* 113 */         this.textures[0][0] = lbox.getTextureName(lc);
/* 114 */         lc = (this.color & 0xFF) + (this.contract ? 96 : 112);
/* 115 */         this.textures[0][1] = lbox.getTextureName(lc);
/* 116 */         lf = true;
/* 117 */         this.textureModel[0] = lbox.models[0];
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 122 */         lbox = MMM_TextureManager.instance.getDefaultTexture((ITextureEntity)this.owner);
/* 123 */         this.textureBox[0] = (this.textureBox[1] = lbox);
/*     */         
/* 125 */         if (lbox.hasColor(lc)) {
/* 126 */           this.textures[0][0] = lbox.getTextureName(lc);
/* 127 */           lc = (this.color & 0xFF) + (this.contract ? 96 : 112);
/* 128 */           this.textures[0][1] = lbox.getTextureName(lc);
/* 129 */           lf = true;
/* 130 */           this.textureModel[0] = lbox.models[0];
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 138 */       this.textureBox[0] = MMM_TextureManager.instance.getTextureBoxServerIndex(this.textureIndex[0]);
/*     */     }
/* 140 */     if (((this.textureBox[1] instanceof MMM_TextureBox)) && (this.owner != null)) {
/* 141 */       MMM_TextureBox lbox = (MMM_TextureBox)this.textureBox[1];
/* 142 */       for (int i = 0; i < 4; i++) {
/* 143 */         ItemStack is = this.owner.getEquipmentInSlot(i + 1);
/* 144 */         this.textures[1][i] = lbox.getArmorTextureName(64, is);
/* 145 */         this.textures[2][i] = lbox.getArmorTextureName(80, is);
/* 146 */         this.textures[3][i] = lbox.getArmorTextureName(128, is);
/* 147 */         this.textures[4][i] = lbox.getArmorTextureName(144, is);
/*     */       }
/* 149 */       this.textureModel[1] = lbox.models[1];
/* 150 */       this.textureModel[2] = lbox.models[2];
/*     */     } else {
/* 152 */       this.textureBox[0] = MMM_TextureManager.instance.getTextureBoxServerIndex(this.textureIndex[0]);
/*     */     }
/* 154 */     return lf;
/*     */   }
/*     */   
/*     */   protected boolean setTextureNamesServer()
/*     */   {
/* 159 */     boolean lf = false;
/*     */     
/* 161 */     if ((this.textureBox[0] instanceof MMM_TextureBoxServer)) {
/* 162 */       MMM_TextureBoxServer lbox = (MMM_TextureBoxServer)this.textureBox[0];
/* 163 */       if (lbox.localBox != null) {
/* 164 */         int lc = (this.color & 0xFF) + (this.contract ? 0 : 48);
/* 165 */         if (lbox.localBox.hasColor(lc)) {
/* 166 */           if (MMM_Helper.isClient) {
/* 167 */             this.textures[0][0] = lbox.localBox.getTextureName(lc);
/* 168 */             lc = (this.color & 0xFF) + (this.contract ? 96 : 112);
/* 169 */             this.textures[0][1] = lbox.localBox.getTextureName(lc);
/*     */           }
/* 171 */           lf = true;
/* 172 */           this.textureModel[0] = lbox.localBox.models[0];
/*     */         }
/*     */       }
/*     */     }
/* 176 */     if (((this.textureBox[1] instanceof MMM_TextureBoxServer)) && (this.owner != null)) {
/* 177 */       MMM_TextureBoxServer lbox = (MMM_TextureBoxServer)this.textureBox[1];
/* 178 */       if (lbox.localBox != null) {
/* 179 */         if (MMM_Helper.isClient) {
/* 180 */           for (int i = 0; i < 4; i++) {
/* 181 */             ItemStack is = this.owner.getEquipmentInSlot(i + 1);
/* 182 */             this.textures[1][i] = lbox.localBox.getArmorTextureName(64, is);
/* 183 */             this.textures[2][i] = lbox.localBox.getArmorTextureName(80, is);
/* 184 */             this.textures[3][i] = lbox.localBox.getArmorTextureName(128, is);
/* 185 */             this.textures[4][i] = lbox.localBox.getArmorTextureName(144, is);
/*     */           }
/*     */         }
/* 188 */         this.textureModel[1] = lbox.localBox.models[1];
/* 189 */         this.textureModel[2] = lbox.localBox.models[2];
/*     */       }
/*     */     }
/* 192 */     return lf;
/*     */   }
/*     */   
/*     */   public void setNextTexturePackege(int pTargetTexture) {
/* 196 */     if (pTargetTexture == 0) {
/* 197 */       int lc = getColor() + (isContract() ? 0 : 48);
/*     */       
/* 199 */       if ((this.textureBox[0] instanceof MMM_TextureBox))
/*     */       {
/* 201 */         this.textureBox[0] = MMM_TextureManager.instance.getNextPackege((MMM_TextureBox)this.textureBox[0], lc);
/*     */       }
/*     */       else
/*     */       {
/* 205 */         this.textureBox[0] = null;
/*     */       }
/* 207 */       if (this.textureBox[0] == null)
/*     */       {
/* 209 */         this.textureBox[0] = (this.textureBox[1] = MMM_TextureManager.instance.getDefaultTexture((ITextureEntity)this.owner));
/* 210 */         setColor(12);
/*     */       } else {
/* 212 */         this.textureBox[1] = this.textureBox[0];
/*     */       }
/* 214 */       if (!((MMM_TextureBox)this.textureBox[1]).hasArmor()) {
/* 215 */         pTargetTexture = 1;
/*     */       }
/*     */     }
/* 218 */     if (pTargetTexture == 1) {
/* 219 */       this.textureBox[1] = MMM_TextureManager.instance.getNextArmorPackege((MMM_TextureBox)this.textureBox[1]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPrevTexturePackege(int pTargetTexture) {
/* 224 */     if (pTargetTexture == 0) {
/* 225 */       int lc = getColor() + (isContract() ? 0 : 48);
/* 226 */       this.textureBox[0] = MMM_TextureManager.instance.getPrevPackege((MMM_TextureBox)this.textureBox[0], lc);
/* 227 */       this.textureBox[1] = this.textureBox[0];
/* 228 */       if (!((MMM_TextureBox)this.textureBox[1]).hasArmor()) {
/* 229 */         pTargetTexture = 1;
/*     */       }
/*     */     }
/* 232 */     if (pTargetTexture == 1) {
/* 233 */       this.textureBox[1] = MMM_TextureManager.instance.getPrevArmorPackege((MMM_TextureBox)this.textureBox[1]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUpdate()
/*     */   {
/* 244 */     if ((this.textureBox != null) && (this.textureBox.length > 0) && (this.textureBox[0] != null))
/*     */     {
/*     */ 
/* 247 */       if (this.textureBox[0].isUpdateSize) {
/* 248 */         setSize();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setSize()
/*     */   {
/* 255 */     if ((this.textureBox != null) && (this.textureBox.length > 0) && (this.textureBox[0] != null))
/*     */     {
/*     */ 
/*     */ 
/* 259 */       if ((this.owner instanceof LMM_EntityLittleMaid))
/*     */       {
/* 261 */         ((LMM_EntityLittleMaid)this.owner).setSize2(this.textureBox[0].getWidth(this.entityCaps), this.textureBox[0].getHeight(this.entityCaps));
/*     */       }
/* 263 */       else if ((this.owner instanceof MMM_EntitySelect))
/*     */       {
/* 265 */         ((MMM_EntitySelect)this.owner).setSize2(this.textureBox[0].getWidth(this.entityCaps), this.textureBox[0].getHeight(this.entityCaps));
/*     */       }
/*     */       
/* 268 */       if ((this.owner instanceof EntityAgeable))
/*     */       {
/* 270 */         ((EntityAgeable)this.owner).setScaleForAge(this.owner.isChild());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTexturePackIndex(int pColor, int[] pIndex)
/*     */   {
/* 279 */     for (int li = 0; li < pIndex.length; li++) {
/* 280 */       this.textureIndex[li] = pIndex[li];
/* 281 */       this.textureBox[li] = MMM_TextureManager.instance.getTextureBoxServer(this.textureIndex[li]);
/*     */     }
/* 283 */     this.color = pColor;
/* 284 */     setSize();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTexturePackName(MMM_TextureBox[] pTextureBox)
/*     */   {
/* 290 */     for (int li = 0; li < pTextureBox.length; li++) {
/* 291 */       this.textureBox[li] = pTextureBox[li];
/*     */     }
/* 293 */     setSize();
/*     */   }
/*     */   
/*     */   public boolean setColor(int pColor)
/*     */   {
/* 298 */     boolean lf = this.color != pColor;
/* 299 */     this.color = pColor;
/* 300 */     return lf;
/*     */   }
/*     */   
/*     */   public int getColor()
/*     */   {
/* 305 */     return this.color & 0xFF;
/*     */   }
/*     */   
/*     */   public void setContract(boolean pContract)
/*     */   {
/* 310 */     this.contract = pContract;
/*     */   }
/*     */   
/*     */   public boolean isContract()
/*     */   {
/* 315 */     return this.contract;
/*     */   }
/*     */   
/*     */   public void setTextureBox(MMM_TextureBoxBase[] pTextureBox)
/*     */   {
/* 320 */     this.textureBox = pTextureBox;
/*     */   }
/*     */   
/*     */   public MMM_TextureBoxBase[] getTextureBox()
/*     */   {
/* 325 */     return this.textureBox;
/*     */   }
/*     */   
/*     */   public void setTextureIndex(int[] pTextureIndex)
/*     */   {
/* 330 */     this.textureIndex = pTextureIndex;
/*     */   }
/*     */   
/*     */   public int[] getTextureIndex()
/*     */   {
/* 335 */     return this.textureIndex;
/*     */   }
/*     */   
/*     */   public void setTextures(int pIndex, ResourceLocation[] pNames)
/*     */   {
/* 340 */     this.textures[pIndex] = pNames;
/*     */   }
/*     */   
/*     */   public ResourceLocation[] getTextures(int pIndex)
/*     */   {
/* 345 */     return this.textures[pIndex];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWildColor()
/*     */   {
/* 353 */     return this.textureBox[0].getRandomWildColor(this.owner.getRNG());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTextureInitServer(String pName)
/*     */   {
/* 361 */     MMMLib.Debug("request Init Texture: %s", new Object[] { pName });
/* 362 */     this.textureIndex[0] = (this.textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer((ITextureEntity)this.owner, pName));
/*     */     
/* 364 */     this.textureBox[0] = (this.textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(this.textureIndex[0]));
/* 365 */     this.color = this.textureBox[0].getRandomWildColor(this.owner.getRNG());
/*     */   }
/*     */   
/* 368 */   public void setTextureInitClient() { MMM_TextureBox lbox = MMM_TextureManager.instance.getDefaultTexture(this.owner.getClass());
/* 369 */     for (int li = 0; li < this.textureBox.length; li++) {
/* 370 */       this.textureBox[li] = lbox;
/* 371 */       this.textureIndex[li] = MMM_TextureManager.instance.getIndexTextureBoxServerIndex(lbox);
/*     */     }
/* 373 */     this.color = this.textureBox[0].getRandomWildColor(this.owner.getRNG());
/*     */   }
/*     */   
/*     */   public String getTextureName(int pIndex) {
/* 377 */     return this.textureBox[pIndex].textureName;
/*     */   }
/*     */   
/*     */   public ResourceLocation getGUITexture() {
/* 381 */     return ((MMM_TextureBox)this.textureBox[0]).getTextureName(32);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isValueFlag(int pIndex)
/*     */   {
/* 390 */     return (this.selectValue >>> pIndex & 0x1) == 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueFlag(int pIndex, boolean pFlag)
/*     */   {
/* 399 */     this.selectValue |= (pFlag ? 1 : 0) << pIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeToNBT(NBTTagCompound par1nbtTagCompound)
/*     */   {
/* 408 */     NBTTagCompound lnbt = new NBTTagCompound();
/* 409 */     lnbt.setIntArray("Textures", this.textureIndex);
/* 410 */     lnbt.setInteger("Color", this.color);
/* 411 */     lnbt.setBoolean("Contract", this.contract);
/* 412 */     lnbt.setInteger("SelectValue", this.selectValue);
/* 413 */     par1nbtTagCompound.setTag("TextureData", lnbt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readToNBT(NBTTagCompound par1nbtTagCompound)
/*     */   {
/* 422 */     if (par1nbtTagCompound.hasKey("TextureData")) {
/* 423 */       NBTTagCompound lnbt = par1nbtTagCompound.getCompoundTag("TextureData");
/* 424 */       this.color = lnbt.getInteger("Color");
/* 425 */       this.contract = lnbt.getBoolean("Contract");
/* 426 */       this.selectValue = lnbt.getInteger("SelectValue");
/* 427 */       int[] intList = lnbt.getIntArray("Textures");
/*     */       
/* 429 */       if (intList.length > 0) {
/* 430 */         setTexturePackIndex(this.color, intList);
/*     */       }
/*     */       else {
/* 433 */         MMM_TextureBox lbox = MMM_TextureManager.instance.getDefaultTexture((ITextureEntity)this.owner);
/* 434 */         int li = MMM_TextureManager.instance.getIndexTextureBoxServerIndex(lbox);
/* 435 */         setTexturePackIndex(this.color, new int[] { li, li });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean updateTexture(int pColor, int[] pTextureIndex)
/*     */   {
/* 447 */     boolean lf = false;
/* 448 */     lf |= setColor(pColor);
/* 449 */     for (int li = 0; li < pTextureIndex.length; li++) {
/* 450 */       if (this.textureIndex[li] != pTextureIndex[li]) {
/* 451 */         this.textureIndex[li] = pTextureIndex[li];
/* 452 */         lf |= true;
/*     */       }
/*     */     }
/* 455 */     if (lf) {
/* 456 */       setTextureNames();
/*     */     }
/*     */     
/* 459 */     return lf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void entityInit(DataWatcher pDataWatcher)
/*     */   {
/* 469 */     pDataWatcher.addObject(this.data_Color, Byte.valueOf((byte)0));
/*     */     
/* 471 */     pDataWatcher.addObject(this.data_Texture, Integer.valueOf(0));
/*     */     
/* 473 */     pDataWatcher.addObject(this.data_Value, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   protected void setWatchedColor(int pColor) {
/* 477 */     this.owner.getDataWatcher().updateObject(this.data_Color, Byte.valueOf((byte)pColor));
/*     */   }
/*     */   
/*     */   protected int getWatchedColor() {
/* 481 */     return this.owner.getDataWatcher().getWatchableObjectByte(this.data_Color);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_TextureData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelCapsHelper;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ public class MMM_TextureBox
/*     */   extends MMM_TextureBoxBase
/*     */ {
/*     */   public String packegeName;
/*     */   public Map<Integer, ResourceLocation> textures;
/*     */   public Map<String, Map<Integer, ResourceLocation>> armors;
/*     */   public String modelName;
/*     */   public ModelMultiBase[] models;
/*     */   public String[] textureDir;
/*     */   public String fileName;
/*     */   
/*     */   public MMM_TextureBox()
/*     */   {
/*  46 */     this.textures = new HashMap();
/*  47 */     this.armors = new TreeMap();
/*  48 */     this.modelHeight = (this.modelWidth = this.modelYOffset = this.modelMountedYOffset = 0.0F);
/*  49 */     this.contractColor = -1;
/*  50 */     this.wildColor = -1;
/*     */   }
/*     */   
/*     */   public MMM_TextureBox(String pTextureName, String[] pSearch) {
/*  54 */     this();
/*  55 */     this.textureName = pTextureName;
/*  56 */     this.fileName = pTextureName;
/*  57 */     int li = pTextureName.lastIndexOf("_");
/*  58 */     if (li > -1) {
/*  59 */       this.packegeName = pTextureName.substring(0, li);
/*  60 */       this.modelName = pTextureName.substring(li + 1);
/*     */     } else {
/*  62 */       this.packegeName = pTextureName;
/*  63 */       this.modelName = "";
/*     */     }
/*  65 */     this.textureDir = pSearch;
/*     */   }
/*     */   
/*     */   public void setModels(String pModelName, ModelMultiBase[] pModels, ModelMultiBase[] pDefModels) {
/*  69 */     this.modelName = pModelName;
/*  70 */     this.models = (pModels == null ? pDefModels : pModels);
/*  71 */     this.textureName = (this.packegeName + "_" + this.modelName);
/*  72 */     this.isUpdateSize = ((this.models != null) && (this.models[0] != null) ? ModelCapsHelper.getCapsValueBoolean(this.models[0], 4, new Object[0]) : false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResourceLocation getTextureName(int pIndex)
/*     */   {
/*  80 */     if (this.textures.containsKey(Integer.valueOf(pIndex)))
/*  81 */       return (ResourceLocation)this.textures.get(Integer.valueOf(pIndex));
/*  82 */     if ((pIndex >= 96) && (pIndex < 112))
/*  83 */       return getTextureName(19);
/*  84 */     if ((pIndex >= 112) && (pIndex < 128)) {
/*  85 */       return getTextureName(19);
/*     */     }
/*  87 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ResourceLocation getArmorTextureName(int pIndex, ItemStack itemstack)
/*     */   {
/*  93 */     if ((this.armors.isEmpty()) || (itemstack == null)) return null;
/*  94 */     if (!(itemstack.getItem() instanceof ItemArmor)) { return null;
/*     */     }
/*  96 */     int l = 0;
/*  97 */     if (itemstack.getMaxDamage() > 0) {
/*  98 */       l = 10 * itemstack.getItemDamage() / itemstack.getMaxDamage();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 104 */     int renderIndex = ((ItemArmor)itemstack.getItem()).renderIndex;
/* 105 */     if ((renderIndex >= MMM_TextureManager.armorFilenamePrefix.length) && (MMM_TextureManager.armorFilenamePrefix.length > 0))
/*     */     {
/* 107 */       renderIndex %= MMM_TextureManager.armorFilenamePrefix.length;
/*     */     }
/*     */     
/* 110 */     return getArmorTextureName(pIndex, MMM_TextureManager.armorFilenamePrefix[renderIndex], l);
/*     */   }
/*     */   
/*     */   public ResourceLocation getArmorTextureName(int pIndex, String pArmorPrefix, int pDamage) {
/* 114 */     if ((this.armors.isEmpty()) || (pArmorPrefix == null)) { return null;
/*     */     }
/* 116 */     Map<Integer, ResourceLocation> m = (Map)this.armors.get(pArmorPrefix);
/* 117 */     if (m == null) {
/* 118 */       m = (Map)this.armors.get("default");
/* 119 */       if (m == null)
/*     */       {
/* 121 */         m = (Map)this.armors.values().toArray()[0];
/*     */       }
/*     */     }
/* 124 */     ResourceLocation ls = null;
/*     */     
/* 126 */     for (int i = pIndex + pDamage; i >= pIndex; i--) {
/* 127 */       ls = (ResourceLocation)m.get(Integer.valueOf(i));
/* 128 */       if (ls != null) break;
/*     */     }
/* 130 */     return ls;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getContractColorBits()
/*     */   {
/* 138 */     if (this.contractColor == -1) {
/* 139 */       int li = 0;
/* 140 */       for (Integer i : this.textures.keySet()) {
/* 141 */         if ((i.intValue() >= 0) && (i.intValue() <= 15)) {
/* 142 */           li |= 1 << (i.intValue() & 0xF);
/*     */         }
/*     */       }
/* 145 */       this.contractColor = li;
/*     */     }
/* 147 */     return this.contractColor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWildColorBits()
/*     */   {
/* 154 */     if (this.wildColor == -1) {
/* 155 */       int li = 0;
/* 156 */       for (Integer i : this.textures.keySet()) {
/* 157 */         if ((i.intValue() >= 48) && (i.intValue() <= 63)) {
/* 158 */           li |= 1 << (i.intValue() & 0xF);
/*     */         }
/*     */       }
/* 161 */       this.wildColor = li;
/*     */     }
/* 163 */     return this.wildColor;
/*     */   }
/*     */   
/*     */   public boolean hasColor(int pIndex) {
/* 167 */     return this.textures.containsKey(Integer.valueOf(pIndex));
/*     */   }
/*     */   
/*     */   public boolean hasColor(int pIndex, boolean pContract) {
/* 171 */     return this.textures.containsKey(Integer.valueOf(pIndex + (pContract ? 0 : 48)));
/*     */   }
/*     */   
/*     */   public boolean hasArmor() {
/* 175 */     return !this.armors.isEmpty();
/*     */   }
/*     */   
/*     */   public float getHeight(IModelCaps pEntityCaps)
/*     */   {
/* 180 */     return this.models != null ? this.models[0].getHeight(pEntityCaps) : this.modelHeight;
/*     */   }
/*     */   
/*     */   public float getWidth(IModelCaps pEntityCaps)
/*     */   {
/* 185 */     return this.models != null ? this.models[0].getWidth(pEntityCaps) : this.modelWidth;
/*     */   }
/*     */   
/*     */   public float getYOffset(IModelCaps pEntityCaps)
/*     */   {
/* 190 */     return this.models != null ? this.models[0].getyOffset(pEntityCaps) : this.modelYOffset;
/*     */   }
/*     */   
/*     */   public float getMountedYOffset(IModelCaps pEntityCaps)
/*     */   {
/* 195 */     return this.models != null ? this.models[0].getMountedYOffset(pEntityCaps) : this.modelMountedYOffset;
/*     */   }
/*     */   
/*     */   public MMM_TextureBox duplicate() {
/* 199 */     MMM_TextureBox lbox = new MMM_TextureBox();
/* 200 */     lbox.textureName = this.textureName;
/* 201 */     lbox.packegeName = this.packegeName;
/* 202 */     lbox.fileName = this.fileName;
/* 203 */     lbox.modelName = this.modelName;
/* 204 */     lbox.textureDir = this.textureDir;
/* 205 */     lbox.textures = this.textures;
/* 206 */     lbox.armors = this.armors;
/* 207 */     lbox.models = this.models;
/* 208 */     lbox.isUpdateSize = lbox.isUpdateSize;
/*     */     
/* 210 */     return lbox;
/*     */   }
/*     */   
/*     */   public boolean addTexture(int pIndex, String pLocation)
/*     */   {
/* 215 */     String ls = "/assets/minecraft/";
/* 216 */     if (pLocation.startsWith(ls)) {
/* 217 */       pLocation = pLocation.substring(ls.length());
/*     */     }
/*     */     
/*     */ 
/* 221 */     boolean lflag = false;
/* 222 */     switch (pIndex & 0xFFF0) {
/*     */     case 17: 
/*     */     case 18: 
/*     */     case 64: 
/*     */     case 80: 
/*     */     case 128: 
/*     */     case 144: 
/* 229 */       ls = pLocation.substring(pLocation.lastIndexOf("/") + 1, pLocation.lastIndexOf("_"));
/*     */       Map<Integer, ResourceLocation> lmap;
/* 231 */       Map<Integer, ResourceLocation> lmap; if (this.armors.containsKey(ls)) {
/* 232 */         lmap = (Map)this.armors.get(ls);
/*     */       } else {
/* 234 */         lmap = new HashMap();
/* 235 */         this.armors.put(ls, lmap);
/*     */       }
/* 237 */       lmap.put(Integer.valueOf(pIndex), new ResourceLocation(pLocation));
/* 238 */       break;
/*     */     
/*     */     default: 
/* 241 */       this.textures.put(Integer.valueOf(pIndex), new ResourceLocation(pLocation));
/* 242 */       return true;
/*     */     }
/* 244 */     return lflag;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_TextureBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
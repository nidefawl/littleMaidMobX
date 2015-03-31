/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLiving;
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
/*     */ public class MMM_EntitySelect
/*     */   extends EntityLiving
/*     */   implements IModelCaps, ITextureEntity
/*     */ {
/*     */   protected MMM_EntityCaps entityCaps;
/*     */   public MMM_TextureData textureData;
/*     */   
/*     */   public MMM_EntitySelect(World par1World)
/*     */   {
/*  29 */     super(par1World);
/*  30 */     this.entityCaps = new MMM_EntityCaps(this);
/*  31 */     this.textureData = new MMM_TextureData(this, this.entityCaps);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void entityInit()
/*     */   {
/*  37 */     super.entityInit();
/*     */     
/*  39 */     this.dataWatcher.addObject(19, Integer.valueOf(0));
/*     */     
/*  41 */     this.dataWatcher.addObject(20, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getBrightness(float par1)
/*     */   {
/*  52 */     return this.worldObj == null ? 0.0F : super.getBrightness(par1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<String, Integer> getModelCaps()
/*     */   {
/*  59 */     return this.entityCaps.getModelCaps();
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/*  64 */     return this.entityCaps.getCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/*  69 */     return this.entityCaps.setCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTexturePackIndex(int pColor, int[] pIndex)
/*     */   {
/*  77 */     this.textureData.setTexturePackIndex(pColor, pIndex);
/*     */     
/*     */ 
/*     */ 
/*  81 */     this.dataWatcher.updateObject(20, Integer.valueOf(Integer.valueOf(pIndex[0]).intValue() & 0xFFFF | (Integer.valueOf(pIndex[1]).intValue() & 0xFFFF) << 16));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTexturePackName(MMM_TextureBox[] pTextureBox)
/*     */   {
/*  91 */     this.textureData.setTexturePackName(pTextureBox);
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
/*     */   protected void setTextureNames()
/*     */   {
/* 106 */     this.textureData.setTextureNames();
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
/*     */   public void setTextureNames(String pArmorName)
/*     */   {
/* 129 */     this.textureData.textureModel[0] = null;
/* 130 */     this.textureData.textureModel[1] = null;
/* 131 */     this.textureData.textureModel[2] = null;
/*     */     
/* 133 */     if ((this.textureData.textureBox[0] instanceof MMM_TextureBox)) {
/* 134 */       int lc = (this.textureData.color & 0xFF) + (this.textureData.contract ? 0 : 48);
/* 135 */       MMM_TextureBox lbox = (MMM_TextureBox)this.textureData.textureBox[0];
/* 136 */       if (lbox.hasColor(lc)) {
/* 137 */         this.textureData.textures[0][0] = lbox.getTextureName(lc);
/* 138 */         lc = (this.textureData.color & 0xFF) + (this.textureData.contract ? 96 : 112);
/* 139 */         this.textureData.textures[0][1] = lbox.getTextureName(lc);
/* 140 */         this.textureData.textureModel[0] = lbox.models[0];
/*     */       }
/*     */     }
/* 143 */     if ((this.textureData.textureBox[1] instanceof MMM_TextureBox)) {
/* 144 */       MMM_TextureBox lbox = (MMM_TextureBox)this.textureData.textureBox[1];
/* 145 */       for (int i = 0; i < 4; i++) {
/* 146 */         this.textureData.textures[1][i] = lbox.getArmorTextureName(64, pArmorName, 0);
/* 147 */         this.textureData.textures[2][i] = lbox.getArmorTextureName(80, pArmorName, 0);
/* 148 */         this.textureData.textures[3][i] = lbox.getArmorTextureName(128, pArmorName, 0);
/* 149 */         this.textureData.textures[4][i] = lbox.getArmorTextureName(144, pArmorName, 0);
/*     */       }
/* 151 */       this.textureData.textureModel[1] = lbox.models[1];
/* 152 */       this.textureData.textureModel[2] = lbox.models[2];
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
/*     */   public void setColor(int pColor)
/*     */   {
/* 177 */     this.textureData.setColor(pColor);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getColor()
/*     */   {
/* 183 */     return this.textureData.getColor();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setContract(boolean pContract)
/*     */   {
/* 189 */     this.textureData.setContract(pContract);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isContract()
/*     */   {
/* 195 */     return this.textureData.isContract();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTextureBox(MMM_TextureBoxBase[] pTextureBox)
/*     */   {
/* 201 */     this.textureData.setTextureBox(pTextureBox);
/*     */   }
/*     */   
/*     */ 
/*     */   public MMM_TextureBoxBase[] getTextureBox()
/*     */   {
/* 207 */     return this.textureData.getTextureBox();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTextures(int pIndex, ResourceLocation[] pNames)
/*     */   {
/* 213 */     this.textureData.setTextures(pIndex, pNames);
/*     */   }
/*     */   
/*     */ 
/*     */   public ResourceLocation[] getTextures(int pIndex)
/*     */   {
/* 219 */     return this.textureData.getTextures(pIndex);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTextureIndex(int[] pTextureIndex)
/*     */   {
/* 225 */     this.textureData.setTextureIndex(pTextureIndex);
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] getTextureIndex()
/*     */   {
/* 231 */     return this.textureData.getTextureIndex();
/*     */   }
/*     */   
/*     */ 
/*     */   public MMM_TextureData getTextureData()
/*     */   {
/* 237 */     return this.textureData;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getBrightnessForRender(float par1)
/*     */   {
/* 243 */     return 15728880;
/*     */   }
/*     */   
/*     */   protected void setSize2(float par1, float par2)
/*     */   {
/* 248 */     super.setSize(par1, par2);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_EntitySelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
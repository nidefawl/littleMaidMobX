/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMM_ItemRenderer
/*     */   extends ItemRenderer
/*     */ {
/*     */   public Minecraft mc;
/*     */   public ItemStack itemToRender;
/*     */   public float equippedProgress;
/*     */   public float prevEquippedProgress;
/*     */   protected static ResourceLocation texGlint;
/*     */   
/*     */   public MMM_ItemRenderer(Minecraft minecraft)
/*     */   {
/*  25 */     super(minecraft);
/*     */     
/*  27 */     this.mc = minecraft;
/*     */     try
/*     */     {
/*  30 */       texGlint = (ResourceLocation)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, null, 0);
/*     */     } catch (Exception e) {
/*  32 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public Minecraft getMC() {
/*  37 */     return this.mc;
/*     */   }
/*     */   
/*     */   public ItemStack getItemToRender() {
/*  41 */     return this.itemToRender;
/*     */   }
/*     */   
/*     */   public float getEquippedProgress() {
/*  45 */     return this.equippedProgress;
/*     */   }
/*     */   
/*     */   public float getPrevEquippedProgress() {
/*  49 */     return this.prevEquippedProgress;
/*     */   }
/*     */   
/*     */   public void renderItem(EntityLivingBase entityliving, ItemStack itemstack, int i)
/*     */   {
/*  54 */     Item litem = itemstack.getItem();
/*  55 */     if (MMM_ItemRenderManager.isEXRender(litem))
/*     */     {
/*  57 */       MMM_ItemRenderManager lii = MMM_ItemRenderManager.getEXRender(litem);
/*  58 */       Client.setTexture(lii.getRenderTexture(itemstack));
/*  59 */       GL11.glPushMatrix();
/*  60 */       boolean lflag = lii.renderItem(entityliving, itemstack, i);
/*  61 */       GL11.glPopMatrix();
/*  62 */       if (lflag) {
/*  63 */         if ((itemstack != null) && (itemstack.hasEffect()) && (i == 0)) {
/*  64 */           GL11.glDepthFunc(514);
/*  65 */           GL11.glDisable(2896);
/*  66 */           Client.setTexture(texGlint);
/*  67 */           GL11.glEnable(3042);
/*  68 */           GL11.glBlendFunc(768, 1);
/*  69 */           float var14 = 0.76F;
/*  70 */           GL11.glColor4f(0.5F * var14, 0.25F * var14, 0.8F * var14, 1.0F);
/*  71 */           float var15 = 0.125F;
/*     */           
/*  73 */           GL11.glPushMatrix();
/*  74 */           GL11.glMatrixMode(5890);
/*  75 */           GL11.glLoadIdentity();
/*  76 */           GL11.glScalef(var15, var15, var15);
/*  77 */           float var16 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
/*  78 */           GL11.glTranslatef(var16, 0.0F, 0.0F);
/*  79 */           GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/*  80 */           GL11.glMatrixMode(5888);
/*  81 */           lii.renderItem(entityliving, itemstack, 0);
/*     */           
/*  83 */           GL11.glPopMatrix();
/*     */           
/*  85 */           GL11.glPushMatrix();
/*  86 */           GL11.glMatrixMode(5890);
/*  87 */           GL11.glLoadIdentity();
/*  88 */           GL11.glScalef(var15, var15, var15);
/*  89 */           var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
/*  90 */           GL11.glTranslatef(-var16, 0.0F, 0.0F);
/*  91 */           GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/*  92 */           GL11.glMatrixMode(5888);
/*  93 */           lii.renderItem(entityliving, itemstack, 0);
/*     */           
/*  95 */           GL11.glPopMatrix();
/*     */           
/*  97 */           GL11.glMatrixMode(5890);
/*  98 */           GL11.glLoadIdentity();
/*  99 */           GL11.glMatrixMode(5888);
/* 100 */           GL11.glDisable(3042);
/* 101 */           GL11.glEnable(2896);
/* 102 */           GL11.glDepthFunc(515);
/*     */         }
/* 104 */         return;
/*     */       }
/*     */     }
/* 107 */     super.renderItem(entityliving, itemstack, i);
/*     */   }
/*     */   
/*     */   public void renderItemInFirstPerson(float f)
/*     */   {
/* 112 */     this.itemToRender = null;
/* 113 */     this.equippedProgress = 0.0F;
/* 114 */     this.prevEquippedProgress = 0.0F;
/*     */     
/*     */     try
/*     */     {
/* 118 */       this.itemToRender = ((ItemStack)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, this, 4));
/* 119 */       this.equippedProgress = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, this, 5)).floatValue();
/* 120 */       this.prevEquippedProgress = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, this, 6)).floatValue();
/*     */     } catch (Exception e) {
/* 122 */       e.printStackTrace();
/*     */     }
/*     */     
/* 125 */     if (this.itemToRender != null) {
/* 126 */       Item litem = this.itemToRender.getItem();
/* 127 */       if ((MMM_ItemRenderManager.isEXRender(litem)) && 
/* 128 */         (MMM_ItemRenderManager.getEXRender(litem).renderItemInFirstPerson(MMM_Helper.mc.thePlayer, this.itemToRender, f))) {
/* 129 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 134 */     super.renderItemInFirstPerson(f);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ItemRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
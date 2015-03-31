/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMM_ItemRenderManager
/*     */ {
/*  29 */   protected static Map<Object, MMM_ItemRenderManager> classList = new HashMap();
/*  30 */   protected static List<Object> checkList = new ArrayList();
/*     */   
/*     */   protected static MMM_ItemRendererForge forgeRender;
/*     */   
/*     */   protected Random random;
/*     */   
/*     */   protected Object fobject;
/*     */   protected Method frenderItem;
/*     */   protected Method frenderItemInFirstPerson;
/*     */   protected Method fgetRenderTexture;
/*     */   protected Method frenderItemWorld;
/*     */   protected MMM_IItemRenderManager exRender;
/*     */   
/*     */   private MMM_ItemRenderManager(Object pObject, Method prenderItem, Method prenderItemInFirstPerson, Method pgetRenderTexture, Method prenderItemWorld)
/*     */   {
/*  45 */     this.fobject = pObject;
/*  46 */     this.exRender = null;
/*  47 */     this.frenderItem = prenderItem;
/*  48 */     this.frenderItemInFirstPerson = prenderItemInFirstPerson;
/*  49 */     this.fgetRenderTexture = pgetRenderTexture;
/*  50 */     this.frenderItemWorld = prenderItemWorld;
/*  51 */     this.random = new Random();
/*     */   }
/*     */   
/*     */   private MMM_ItemRenderManager(Object pObject, MMM_IItemRenderManager pEXRender) {
/*  55 */     this.fobject = pObject;
/*  56 */     this.exRender = pEXRender;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  61 */     this.random = new Random();
/*     */     
/*  63 */     registerForge((Item)pObject, pEXRender);
/*     */   }
/*     */   
/*     */   public static void registerForge(Item pItem, MMM_IItemRenderManager pEXRender)
/*     */   {
/*  68 */     if (forgeRender == null) {
/*  69 */       forgeRender = new MMM_ItemRendererForge();
/*     */     }
/*  71 */     MinecraftForgeClient.registerItemRenderer(pItem, forgeRender);
/*  72 */     MMMLib.Debug("registerForge:%s", new Object[] { pItem.getClass().getSimpleName() });
/*     */   }
/*     */   
/*     */   public static boolean setEXRender(Item pItem, MMM_IItemRenderManager pEXRender)
/*     */   {
/*  77 */     if ((pItem == null) || (pEXRender == null)) { return false;
/*     */     }
/*  79 */     checkList.add(pItem);
/*  80 */     classList.put(pItem, new MMM_ItemRenderManager(pItem, pEXRender));
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isEXRender(Item pItem) {
/*  85 */     if (checkList.contains(pItem)) {
/*  86 */       return classList.containsKey(pItem);
/*     */     }
/*  88 */     checkList.add(pItem);
/*  89 */     Method lrenderItem = null;
/*  90 */     Method lrenderItemInFirstPerson = null;
/*  91 */     Method lgetRenderTexture = null;
/*  92 */     Method lrenderItemWorld = null;
/*  93 */     Class lc = pItem.getClass();
/*     */     try
/*     */     {
/*  96 */       lrenderItem = lc.getMethod("renderItem", new Class[] { EntityLivingBase.class, ItemStack.class, Integer.TYPE });
/*     */     } catch (Exception e) {
/*  98 */       e.printStackTrace();
/*     */     }
/*     */     try {
/* 101 */       lrenderItemInFirstPerson = lc.getMethod("renderItemInFirstPerson", new Class[] { Float.TYPE });
/*     */     } catch (Exception e) {
/* 103 */       e.printStackTrace();
/*     */     }
/*     */     try {
/* 106 */       lgetRenderTexture = lc.getMethod("getRenderTexture", new Class[0]);
/*     */     } catch (Exception e) {
/* 108 */       e.printStackTrace();
/*     */     }
/*     */     try {
/* 111 */       lrenderItemWorld = lc.getMethod("isRenderItemWorld", new Class[0]);
/*     */     } catch (Exception e) {
/* 113 */       e.printStackTrace();
/*     */     }
/* 115 */     if ((lrenderItem != null) || (lrenderItemInFirstPerson != null) || (lgetRenderTexture != null)) {
/* 116 */       classList.put(pItem, new MMM_ItemRenderManager(pItem, lrenderItem, lrenderItemInFirstPerson, lgetRenderTexture, lrenderItemWorld));
/*     */       
/*     */ 
/* 119 */       return true;
/*     */     }
/*     */     
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public static MMM_ItemRenderManager getEXRender(Item pItem) {
/* 126 */     return (MMM_ItemRenderManager)classList.get(pItem);
/*     */   }
/*     */   
/*     */   public void renderItemLocal(EntityLivingBase entityliving, ItemStack itemstack, int i)
/*     */   {
/* 131 */     Item litem = itemstack.getItem();
/*     */     
/* 133 */     Client.setTexture(getRenderTexture(itemstack));
/* 134 */     GL11.glPushMatrix();
/* 135 */     boolean lflag = renderItem(entityliving, itemstack, i);
/* 136 */     GL11.glPopMatrix();
/* 137 */     if (lflag) {
/* 138 */       if ((itemstack != null) && (itemstack.hasEffect()) && (i == 0)) {
/* 139 */         GL11.glDepthFunc(514);
/* 140 */         GL11.glDisable(2896);
/* 141 */         Client.setTexture(MMM_ItemRenderer.texGlint);
/* 142 */         GL11.glEnable(3042);
/* 143 */         GL11.glBlendFunc(768, 1);
/* 144 */         float var14 = 0.76F;
/* 145 */         GL11.glColor4f(0.5F * var14, 0.25F * var14, 0.8F * var14, 1.0F);
/* 146 */         float var15 = 0.125F;
/*     */         
/* 148 */         GL11.glPushMatrix();
/* 149 */         GL11.glMatrixMode(5890);
/* 150 */         GL11.glLoadIdentity();
/* 151 */         GL11.glScalef(var15, var15, var15);
/* 152 */         float var16 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
/* 153 */         GL11.glTranslatef(var16, 0.0F, 0.0F);
/* 154 */         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 155 */         GL11.glMatrixMode(5888);
/* 156 */         renderItem(entityliving, itemstack, 0);
/*     */         
/* 158 */         GL11.glPopMatrix();
/*     */         
/* 160 */         GL11.glPushMatrix();
/* 161 */         GL11.glMatrixMode(5890);
/* 162 */         GL11.glLoadIdentity();
/* 163 */         GL11.glScalef(var15, var15, var15);
/* 164 */         var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
/* 165 */         GL11.glTranslatef(-var16, 0.0F, 0.0F);
/* 166 */         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 167 */         GL11.glMatrixMode(5888);
/* 168 */         renderItem(entityliving, itemstack, 0);
/*     */         
/* 170 */         GL11.glPopMatrix();
/*     */         
/* 172 */         GL11.glMatrixMode(5890);
/* 173 */         GL11.glLoadIdentity();
/* 174 */         GL11.glMatrixMode(5888);
/* 175 */         GL11.glDisable(3042);
/* 176 */         GL11.glEnable(2896);
/* 177 */         GL11.glDepthFunc(515);
/*     */       }
/* 179 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean renderItem(Entity pEntity, ItemStack pItemstack, int pIndex) {
/* 184 */     if (this.exRender != null)
/* 185 */       return this.exRender.renderItem(pEntity, pItemstack, pIndex);
/* 186 */     if (this.frenderItem != null) {
/*     */       try {
/* 188 */         return ((Boolean)this.frenderItem.invoke(this.fobject, new Object[] { pEntity, pItemstack, Integer.valueOf(pIndex) })).booleanValue();
/*     */       } catch (Exception e) {
/* 190 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */   
/*     */   public boolean renderItemInFirstPerson(Entity pEntity, ItemStack pItemStack, float pDeltaTimepRenderPhatialTick)
/*     */   {
/* 198 */     if (this.exRender != null) {
/* 199 */       return this.exRender.renderItemInFirstPerson(MMM_Helper.mc.thePlayer, pItemStack, pDeltaTimepRenderPhatialTick);
/*     */     }
/* 201 */     if (this.frenderItemInFirstPerson != null) {
/*     */       try {
/* 203 */         return ((Boolean)this.frenderItemInFirstPerson.invoke(this.fobject, new Object[] { MMM_Helper.mc.thePlayer, pItemStack, Float.valueOf(pDeltaTimepRenderPhatialTick) })).booleanValue();
/*     */       } catch (Exception e) {
/* 205 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public boolean renderItemWorld(EntityItem entityitem, double d, double d1, double d2, float f, float f1) {
/* 212 */     ItemStack lis = entityitem.getEntityItem();
/* 213 */     MMM_ItemRenderManager lirm = getEXRender(lis.getItem());
/* 214 */     if (!lirm.isRenderItemWorld(lis)) { return false;
/*     */     }
/*     */     
/* 217 */     Client.setTexture(lirm.getRenderTexture(lis));
/*     */     
/* 219 */     this.random.setSeed(187L);
/* 220 */     GL11.glPushMatrix();
/* 221 */     float f2 = MathHelper.sin((entityitem.age + f1) / 10.0F + entityitem.hoverStart) * 0.1F + 0.1F;
/* 222 */     float f3 = ((entityitem.age + f1) / 20.0F + entityitem.hoverStart) * 57.29578F;
/* 223 */     byte byte0 = 1;
/* 224 */     if (lis.stackSize > 1) {
/* 225 */       byte0 = 2;
/*     */     }
/* 227 */     if (lis.stackSize > 5) {
/* 228 */       byte0 = 3;
/*     */     }
/* 230 */     if (lis.stackSize > 20) {
/* 231 */       byte0 = 4;
/*     */     }
/* 233 */     GL11.glTranslatef((float)d, (float)d1 + f2, (float)d2);
/* 234 */     GL11.glEnable(32826);
/* 235 */     GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
/* 236 */     float f4 = 1.0F;
/* 237 */     for (int j = 0; j < byte0; j++) {
/* 238 */       GL11.glPushMatrix();
/* 239 */       if (j > 0) {
/* 240 */         float f5 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
/* 241 */         float f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
/* 242 */         float f9 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
/* 243 */         GL11.glTranslatef(f5, f7, f9);
/*     */       }
/* 245 */       lirm.renderItem(null, lis, 0);
/* 246 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 249 */     GL11.glPopMatrix();
/* 250 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public ResourceLocation getRenderTexture(ItemStack pItemStack)
/*     */   {
/* 256 */     if (this.exRender != null)
/* 257 */       return this.exRender.getRenderTexture(pItemStack);
/* 258 */     if (this.fgetRenderTexture != null) {
/*     */       try {
/* 260 */         return (ResourceLocation)this.fgetRenderTexture.invoke(this.fobject, new Object[0]);
/*     */       } catch (Exception e) {
/* 262 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 265 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isRenderItemWorld(ItemStack pItemStack) {
/* 269 */     if (this.exRender != null)
/* 270 */       return this.exRender.isRenderItemWorld(pItemStack);
/* 271 */     if (this.frenderItemWorld != null) {
/*     */       try {
/* 273 */         return ((Boolean)this.frenderItemWorld.invoke(this.fobject, new Object[0])).booleanValue();
/*     */       } catch (Exception e) {
/* 275 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 278 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isRenderItemInFirstPerson(ItemStack pItemStack) {
/* 282 */     if (this.exRender != null) {
/* 283 */       return this.exRender.isRenderItemInFirstPerson(pItemStack);
/*     */     }
/* 285 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isRenderItem(ItemStack pItemStack) {
/* 289 */     if (this.exRender != null) {
/* 290 */       return this.exRender.isRenderItem(pItemStack);
/*     */     }
/* 292 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ItemRenderManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
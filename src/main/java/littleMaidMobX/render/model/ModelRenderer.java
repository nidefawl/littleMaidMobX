package littleMaidMobX.render.model;

import static littleMaidMobX.model.caps.IModelCaps.caps_Actions;
import static littleMaidMobX.model.caps.IModelCaps.caps_Entity;
import static littleMaidMobX.model.caps.IModelCaps.caps_HeadMount;
import static littleMaidMobX.model.caps.IModelCaps.caps_Items;

import java.lang.reflect.Constructor;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import littleMaidMobX.model.ModelBase;
import littleMaidMobX.model.ModelMultiBase;
import littleMaidMobX.model.caps.IModelCaps;
import littleMaidMobX.model.caps.ModelCapsHelper;
import littleMaidMobX.model.maids.MultiModel_Elsie2;
import littleMaidMobX.model.maids.MultiModel_NM2;
import littleMaidMobX.wrapper.MinecraftClientWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ModelRenderer {

	
	public float textureWidth;
	public float textureHeight;
	private int textureOffsetX;
	private int textureOffsetY;
	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;
	protected boolean compiled;
	protected int displayList;
	public boolean mirror;
	public boolean showModel;
	public boolean isHidden;
	
	public boolean isRendering;
	public List<ModelBoxBase> cubeList;
	public List<ModelRenderer> childModels;
	public final String boxName;
	protected ModelBase baseModel;
	public ModelRenderer pearent;
	public float offsetX;
	public float offsetY;
	public float offsetZ;
	public float scaleX;
	public float scaleY;
	public float scaleZ;
	
	
//	public static final float radFactor = 57.295779513082320876798154814105F;
	public static final float radFactor = 180F / (float)Math.PI;
//	public static final float degFactor = 0.01745329251994329576923690768489F;
	public static final float degFactor = (float)Math.PI / 180F;
	
	
	public int rotatePriority;
	public static final int RotXYZ = 0;
	public static final int RotXZY = 1;
	public static final int RotYXZ = 2;
	public static final int RotYZX = 3;
	public static final int RotZXY = 4;
	public static final int RotZYX = 5;
	
//	public static final int ModeEquip = 0x000;
//	public static final int ModeInventory = 0x001;
//	public static final int ModeItemStack = 0x002;
//	public static final int ModeParts = 0x010;
	protected ItemStack itemstack;
	
	public boolean adjust;
	public FloatBuffer matrix;
	public boolean isInvertX;

	private RenderBlocks renderBlocksIr = new RenderBlocks();



	public ModelRenderer(ModelBase pModelBase, String pName) {
		textureWidth = 64.0F;
		textureHeight = 32.0F;
		compiled = false;
		displayList = 0;
		mirror = false;
		showModel = true;
		isHidden = false;
		isRendering = true;
		cubeList = new ArrayList<ModelBoxBase>();
		baseModel = pModelBase;
		if (pModelBase instanceof MultiModel_NM2 && pModelBase.modelSize==0) {
			System.out.println("our: "+pModelBase.boxList.size()+" = "+pName);
		}
		
		pModelBase.boxList.add(this);
		boxName = pName;
		setTextureSize(pModelBase.textureWidth, pModelBase.textureHeight);
		
		rotatePriority = RotXYZ;
		itemstack = null;
		adjust = true;
		matrix = BufferUtils.createFloatBuffer(16);
		isInvertX = false;
		
		scaleX = 1.0F;
		scaleY = 1.0F;
		scaleZ = 1.0F;
		
		pearent = null;
		
//		renderBlocksIr.useInventoryTint = false;
	}

	public ModelRenderer(ModelBase pModelBase, int px, int py, String s) {
		this(pModelBase, s);
		setTextureOffset(px, py);
	}
	public ModelRenderer(ModelBase pModelBase, int px, int py) {
		this(pModelBase, null);
		setTextureOffset(px, py);
	}

	public ModelRenderer(ModelBase pModelBase) {
		this(pModelBase, (String)null);
	}

	public ModelRenderer(ModelBase pModelBase, int px, int py, float pScaleX, float pScaleY, float pScaleZ) {
		this(pModelBase, px, py);
		this.scaleX = pScaleX;
		this.scaleY = pScaleY;
		this.scaleZ = pScaleZ;
	}

	public ModelRenderer(ModelBase pModelBase, float pScaleX, float pScaleY, float pScaleZ) {
		this(pModelBase);
		this.scaleX = pScaleX;
		this.scaleY = pScaleY;
		this.scaleZ = pScaleZ;
	}

	

	public void addChild(ModelRenderer pModelRenderer) {
		if (childModels == null) {
			childModels = new ArrayList<ModelRenderer>();
		}
		childModels.add(pModelRenderer);
		pModelRenderer.pearent = this;
	}

	public ModelRenderer setTextureOffset(int pOffsetX, int pOffsetY) {
		textureOffsetX = pOffsetX;
		textureOffsetY = pOffsetY;
		return this;
	}

	public ModelRenderer addBox(String pName, float pX, float pY, float pZ,
			int pWidth, int pHeight, int pDepth) {
		addParts(ModelBox.class, pName, pX, pY, pZ, pWidth, pHeight, pDepth, 0.0F);
		return this;
	}

	public ModelRenderer addBox(float pX, float pY, float pZ,
			int pWidth, int pHeight, int pDepth) {
		addParts(ModelBox.class, pX, pY, pZ, pWidth, pHeight, pDepth, 0.0F);
		return this;
	}

	public ModelRenderer addBox(float pX, float pY, float pZ,
			int pWidth, int pHeight, int pDepth, float pSizeAdjust) {
		addParts(ModelBox.class, pX, pY, pZ, pWidth, pHeight, pDepth, pSizeAdjust);
		return this;
	}

	public ModelRenderer setRotationPoint(float pX, float pY, float pZ) {
		rotationPointX = pX;
		rotationPointY = pY;
		rotationPointZ = pZ;
		return this;
	}

	
	public void render(float par1, boolean pIsRender) {
//		boolean hide = isHidden || !showModel;
//		if (Minecraft.FRAME_NUM%100==0) {
//			if (this.baseModel instanceof MultiModel_NM2) {
//				MultiModel_NM2 mm = (MultiModel_NM2)this.baseModel;	
//				if (this.boxName!=null&&this.boxName.toLowerCase().contains("skirt")&&!this.boxName.equals("Skirt")) {
//					System.out.println("render skirt "+boxName+" "+this.baseModel+ " - "+hide);
//					System.out.println("render skirt rotationPointX "+rotationPointX);
//					System.out.println("render skirt rotationPointY "+rotationPointY);
//					System.out.println("render skirt rotationPointZ "+rotationPointZ);
//					System.out.println("render skirt offsetX "+offsetX);
//					System.out.println("render skirt offsetY "+offsetY);
//					System.out.println("render skirt offsetZ "+offsetZ);
//				}
//	//			int a = 0;
//	//			for (ModelRenderer mb : this.baseModel.boxList) {
//	//				if (mb == mm.SkirtTop) {
//	//					a++;
//	//				}
//	//			}
//	//			if (a > 0) {
//	//				System.out.println("found "+a+" skirttops");
//	//			}
//			}
//		}
		if (isHidden) {
			return;
		}
		if (!compiled && showModel) {
			compileDisplayList(par1);
		}
		
		GL11.glPushMatrix();
		GL11.glTranslatef(offsetX, offsetY, offsetZ);
		
		if (rotationPointX != 0.0F || rotationPointY != 0.0F || rotationPointZ != 0.0F) {
			GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);
		}
		if (rotateAngleX != 0.0F || rotateAngleY != 0.0F || rotateAngleZ != 0.0F) {
			setRotation();
		}
		renderObject(par1, pIsRender);
		GL11.glPopMatrix();
	}
	public void render(float par1) {
		render(par1, true);
	}

	public void renderWithRotation(float par1) {
		if (isHidden || !showModel) {
			return;
		}
		if (!compiled) {
			compileDisplayList(par1);
		}
		
		GL11.glPushMatrix();
		GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);
		
		setRotation();

		GL11.glCallList(displayList);
		GL11.glPopMatrix();
	}

	public void postRender(float par1) {
		if (isHidden) return;
		if (!showModel) return;
		
		if (!compiled) {
			compileDisplayList(par1);
		}
		
		if (pearent != null) {
			pearent.postRender(par1);
		}
		
		GL11.glTranslatef(offsetX, offsetY, offsetZ);
		
		if (rotationPointX != 0.0F || rotationPointY != 0.0F || rotationPointZ != 0.0F) {
			GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);
		}
		if (rotateAngleX != 0.0F || rotateAngleY != 0.0F || rotateAngleZ != 0.0F) {
			setRotation();
		}
	}

	protected void compileDisplayList(float par1) {
		if (displayList <= 0) {
			displayList = GLAllocation.generateDisplayLists(1);			
		}
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		Tessellator tessellator = Tessellator.instance;
		
		for (int i = 0; i < cubeList.size(); i++) {
			cubeList.get(i).render(tessellator, par1);
		}
		
		GL11.glEndList();
		compiled = true;
	}

	public ModelRenderer setTextureSize(int pWidth, int pHeight) {
		textureWidth = (float)pWidth;
		textureHeight = (float)pHeight;
		return this;
	}


	

	
	public ModelRenderer addCubeList(ModelBoxBase pModelBoxBase) {
		cubeList.add(pModelBoxBase);
		return this;
	}

	protected ModelBoxBase getModelBoxBase(Class<? extends ModelBoxBase> pModelBoxBase, Object ... pArg) {
		try {
			Constructor<? extends ModelBoxBase> lconstructor =
					pModelBoxBase.getConstructor(ModelRenderer.class, Object[].class);
			return lconstructor.newInstance(this, pArg);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Object[] getArg(Object ... pArg) {
		Object lobject[] = new Object[pArg.length + 2];
		lobject[0] = textureOffsetX;
		lobject[1] = textureOffsetY;
		for (int li = 0; li < pArg.length; li++) {
			lobject[2 + li] = pArg[li];
		}
		return lobject;
	}

	public ModelRenderer addParts(Class<? extends ModelBoxBase> pModelBoxBase, String pName, Object ... pArg) {
		pName = (new StringBuilder()).append(boxName).append(".").append(pName).toString();
		TextureOffset ltextureoffset = baseModel.getTextureOffset(pName);
		setTextureOffset(ltextureoffset.textureOffsetX, ltextureoffset.textureOffsetY);
		addCubeList(getModelBoxBase(pModelBoxBase, getArg(pArg)).setBoxName(pName));
		return this;
	}

	public ModelRenderer addParts(Class<? extends ModelBoxBase> pModelBoxBase, Object ... pArg) {
		addCubeList(getModelBoxBase(pModelBoxBase, getArg(pArg)));
		return this;
	}

	
	public ModelRenderer addPartsTexture(Class<? extends ModelBoxBase> pModelBoxBase, String pName, Object ... pArg) {
		pName = (new StringBuilder()).append(boxName).append(".").append(pName).toString();
		addCubeList(getModelBoxBase(pModelBoxBase, pArg).setBoxName(pName));
		return this;
	}

	
	public ModelRenderer addPartsTexture(Class<? extends ModelBoxBase> pModelBoxBase, Object ... pArg) {
		addCubeList(getModelBoxBase(pModelBoxBase, pArg));
		return this;
	}


	public ModelRenderer addPlate(float pX, float pY, float pZ,
			int pWidth, int pHeight, int pFacePlane) {
		addParts(ModelBoxPlate.class, pX, pY, pZ, pWidth, pHeight, pFacePlane, 0.0F);
		return this;
	}

	public ModelRenderer addPlate(float pX, float pY, float pZ,
			int pWidth, int pHeight, int pFacePlane, float pSizeAdjust) {
		addParts(ModelBoxPlate.class, pX, pY, pZ, pWidth, pHeight, pFacePlane, pSizeAdjust);
		return this;
	}

	public ModelRenderer addPlate(String pName, float pX, float pY, float pZ,
			int pWidth, int pHeight, int pFacePlane) {
		addParts(ModelBoxPlate.class, pName, pX, pY, pZ, pWidth, pHeight, pFacePlane, 0.0F);
		return this;
	}

	
	public void clearCubeList() {
		cubeList.clear();
		compiled = false;
		if (childModels != null) {
			childModels.clear();
		}
	}

	
	public boolean renderItems(ModelMultiBase pModelMulti, IModelCaps pEntityCaps, boolean pRealBlock, int pIndex) {
		ItemStack[] litemstacks = (ItemStack[])ModelCapsHelper.getCapsValue(pEntityCaps, caps_Items);
		if (litemstacks == null) return false;
		EnumAction[] lactions = (EnumAction[])ModelCapsHelper.getCapsValue(pEntityCaps, caps_Actions);
		EntityLivingBase lentity = (EntityLivingBase)pEntityCaps.getCapsValue(caps_Entity);
		
		renderItems(lentity, pModelMulti.render, pRealBlock, lactions[pIndex], litemstacks[pIndex]);
		return true;
	}

	public void renderItemsHead(ModelMultiBase pModelMulti, IModelCaps pEntityCaps) {
		ItemStack lis = (ItemStack)pEntityCaps.getCapsValue(caps_HeadMount);
		EntityLivingBase lentity = (EntityLivingBase)pEntityCaps.getCapsValue(caps_Entity);
		
		renderItems(lentity, pModelMulti.render, true, null, lis);
	}

	protected void renderItems(EntityLivingBase pEntityLiving, Render pRender,
			boolean pRealBlock, EnumAction pAction, ItemStack pItemStack) {
		itemstack = pItemStack;
		renderItems(pEntityLiving, pRender, pRealBlock, pAction);
	}

	protected void renderItems(EntityLivingBase pEntityLiving, Render pRender, boolean pRealBlock, EnumAction pAction) {
		if (itemstack == null) return;
		
		
		GL11.glPushMatrix();
		Item litem = itemstack.getItem();
		
		
		if (adjust) {
			// GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			
			if (pRealBlock && (litem instanceof ItemBlock)) {
				float f2 = 0.625F;
				GL11.glScalef(f2, -f2, -f2);
				GL11.glRotatef(270F, 0F, 1F, 0);
			} else if (pRealBlock && (litem instanceof ItemSkull)) {
				float f2 = 1.0625F;
				GL11.glScalef(f2, -f2, -f2);
			} else {
				float var6;
				if ((litem instanceof ItemBlock)
						&& RenderBlocks.renderItemIn3d(Block.getBlockFromItem(litem).getRenderType())) {
					var6 = 0.5F;
					// GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
					GL11.glTranslatef(0.0F, 0.1875F, -0.2125F);
					var6 *= 0.75F;
					GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var6, -var6, var6);
				} else if (litem instanceof ItemBow) {
					var6 = 0.625F;
					GL11.glTranslatef(-0.05F, 0.125F, 0.3125F);
					GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(var6, -var6, var6);
					GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				} else if (litem.isFull3D()) {
					var6 = 0.625F;
					
					if (litem.shouldRotateAroundWhenRendering()) {
						GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
						GL11.glTranslatef(0.0F, -0.125F, 0.0F);
					}
					
					if (pAction == EnumAction.block) {
						GL11.glTranslatef(0.05F, 0.0F, -0.1F);
						GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
						GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
					}
					
					GL11.glTranslatef(0.0F, 0.1875F, 0.1F);
					GL11.glScalef(var6, -var6, var6);
					GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				} else {
					var6 = 0.375F;
					GL11.glTranslatef(0.15F, 0.15F, -0.05F);
					// GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
					GL11.glScalef(var6, var6, var6);
					GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
				}
			}
		}
		
		
		if (pRealBlock && litem instanceof ItemSkull) {
			String lsowner = "";
			if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("SkullOwner")) {
				lsowner = itemstack.getTagCompound().getString("SkullOwner");
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//			TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, -0.25F, -0.5F, 1, 180.0F,
//					itemstack.getItemDamage(), lsowner);
			MinecraftClientWrapper.renderSkeletonHead(TileEntitySkullRenderer.field_147536_b, -0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), lsowner);
		} else if (pRealBlock && litem instanceof ItemBlock) {
//			Client.setTexture(TextureMap.field_110575_b);
//			pRender.loadTexture("/terrain.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			int var4 = pEntityLiving.getBrightnessForRender(0.0F);
			int var5 = var4 % 65536;
			int var6 = var4 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var5 / 1.0F, (float)var6 / 1.0F);

			
			GL11.glEnable(GL11.GL_CULL_FACE);
//			pRender.renderBlocks.renderBlockAsItem(
//					Block.blocksList[itemstack.itemID],
//					itemstack.getItemDamage(), 1.0F);


			renderBlock(itemstack);

			GL11.glDisable(GL11.GL_CULL_FACE);
		} else {
			
//			pRender.loadTexture("/gui/items.png");
			for (int j = 0; j <= (litem.requiresMultipleRenderPasses() ? 1 : 0); j++) {
				int k = itemstack.getItem().getColorFromItemStack(itemstack, j);
				float f15 = (float) (k >> 16 & 0xff) / 255F;
				float f17 = (float) (k >> 8 & 0xff) / 255F;
				float f19 = (float) (k & 0xff) / 255F;
				GL11.glColor4f(f15, f17, f19, 1.0F);
				RenderManager.instance.itemRenderer.renderItem(pEntityLiving, itemstack, j);
			}
		}
		
		GL11.glPopMatrix();
	}
	
	private void renderBlock(ItemStack par2ItemStack)
	{
		GL11.glPushMatrix();
		TextureManager texturemanager = Minecraft.getMinecraft().renderEngine;
		Item item = par2ItemStack.getItem();
		Block block = Block.getBlockFromItem(item);

		if (par2ItemStack.getItemSpriteNumber() == 0 && item instanceof ItemBlock)// && RenderBlocks.renderItemIn3d(block.getRenderType()))
		{
			texturemanager.bindTexture(texturemanager.getResourceLocation(0));

			GL11.glDisable(GL11.GL_LIGHTING);
			if (item instanceof ItemCloth)
			{
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDepthMask(false);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				
				GL11.glAlphaFunc(GL11.GL_GREATER, 0.001F);
				
				this.renderBlocksIr.renderBlockAsItem(block, par2ItemStack.getItemDamage(), 1.0F);
				GL11.glDepthMask(true);
				GL11.glDisable(GL11.GL_BLEND);
			}
			else
			{
				this.renderBlocksIr.renderBlockAsItem(block, par2ItemStack.getItemDamage(), 1.0F);
			}
			GL11.glEnable(GL11.GL_LIGHTING);
		}

		GL11.glPopMatrix();
	}

	
	public void setRotatePriority(int pValue) {
		rotatePriority = pValue;
	}

	
	protected void setRotation() {
		
		switch (rotatePriority) {
		case RotXYZ:
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * radFactor, 0.0F, 0.0F, 1.0F);
			}
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * radFactor, 0.0F, 1.0F, 0.0F);
			}
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * radFactor, 1.0F, 0.0F, 0.0F);
			}
			break;
		case RotXZY:
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * radFactor, 0.0F, 1.0F, 0.0F);
			}
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * radFactor, 0.0F, 0.0F, 1.0F);
			}
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * radFactor, 1.0F, 0.0F, 0.0F);
			}
			break;
		case RotYXZ:
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * radFactor, 0.0F, 0.0F, 1.0F);
			}
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * radFactor, 1.0F, 0.0F, 0.0F);
			}
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * radFactor, 0.0F, 1.0F, 0.0F);
			}
			break;
		case RotYZX:
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * radFactor, 1.0F, 0.0F, 0.0F);
			}
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * radFactor, 0.0F, 0.0F, 1.0F);
			}
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * radFactor, 0.0F, 1.0F, 0.0F);
			}
			break;
		case RotZXY:
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * radFactor, 0.0F, 1.0F, 0.0F);
			}
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * radFactor, 1.0F, 0.0F, 0.0F);
			}
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * radFactor, 0.0F, 0.0F, 1.0F);
			}
			break;
		case RotZYX:
			if (rotateAngleX != 0.0F) {
				GL11.glRotatef(rotateAngleX * radFactor, 1.0F, 0.0F, 0.0F);
			}
			if (rotateAngleY != 0.0F) {
				GL11.glRotatef(rotateAngleY * radFactor, 0.0F, 1.0F, 0.0F);
			}
			if (rotateAngleZ != 0.0F) {
				GL11.glRotatef(rotateAngleZ * radFactor, 0.0F, 0.0F, 1.0F);
			}
			break;
		}
	}

	
	protected void renderObject(float par1, boolean pRendering) {
		
		/** Download the matrix from GPU
		 * 
		 * This is very bad practice
		 */
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, matrix); 
		
		if (pRendering && isRendering && showModel) {
			GL11.glPushMatrix();
			GL11.glScalef(scaleX, scaleY, scaleZ);
			GL11.glCallList(displayList);
			GL11.glPopMatrix();
		}
		
		if (childModels != null) {
			for (int li = 0; li < childModels.size(); li++) {
				childModels.get(li).render(par1, pRendering);
			}
		}
	}

	
	public ModelRenderer loadMatrix() {
		GL11.glLoadMatrix(matrix);
		if (isInvertX) {
			GL11.glScalef(-1F, 1F, 1F);
		}
		return this;
	}


	

	public boolean getMirror() {
		return mirror;
	}

	public ModelRenderer setMirror(boolean flag) {
		mirror = flag;
		return this;
	}

	public boolean getVisible() {
		return showModel;
	}

	public void setVisible(boolean flag) {
		if (boxName!=null&&boxName.toLowerCase().contains("Arm")) {
			System.err.println(this.baseModel+" set arm "+(flag?"visible":"hidden"));
			Thread.dumpStack();
		}
		showModel = flag;
	}


	

	public float getRotateAngleX() {
		return rotateAngleX;
	}

	public float getRotateAngleDegX() {
		return rotateAngleX * radFactor;
	}

	public float setRotateAngleX(float value) {
		return rotateAngleX = value;
	}

	public float setRotateAngleDegX(float value) {
		return rotateAngleX = value * degFactor;
	}

	public float addRotateAngleX(float value) {
		return rotateAngleX += value;
	}

	public float addRotateAngleDegX(float value) {
		return rotateAngleX += value * degFactor;
	}

	public float getRotateAngleY() {
		return rotateAngleY;
	}

	public float getRotateAngleDegY() {
		return rotateAngleY * radFactor;
	}

	public float setRotateAngleY(float value) {
		return rotateAngleY = value;
	}

	public float setRotateAngleDegY(float value) {
		return rotateAngleY = value * degFactor;
	}

	public float addRotateAngleY(float value) {
		return rotateAngleY += value;
	}

	public float addRotateAngleDegY(float value) {
		return rotateAngleY += value * degFactor;
	}

	public float getRotateAngleZ() {
		return rotateAngleZ;
	}

	public float getRotateAngleDegZ() {
		return rotateAngleZ * radFactor;
	}

	public float setRotateAngleZ(float value) {
		return rotateAngleZ = value;
	}

	public float setRotateAngleDegZ(float value) {
		return rotateAngleZ = value * degFactor;
	}

	public float addRotateAngleZ(float value) {
		return rotateAngleZ += value;
	}

	public float addRotateAngleDegZ(float value) {
		return rotateAngleZ += value * degFactor;
	}

	public ModelRenderer setRotateAngle(float x, float y, float z) {
		rotateAngleX = x;
		rotateAngleY = y;
		rotateAngleZ = z;
		return this;
	}

	public ModelRenderer setRotateAngleDeg(float x, float y, float z) {
		rotateAngleX = x * degFactor;
		rotateAngleY = y * degFactor;
		rotateAngleZ = z * degFactor;
		return this;
	}

	public float getRotationPointX() {
		return rotationPointX;
	}

	public float setRotationPointX(float value) {
		return rotationPointX = value;
	}

	public float addRotationPointX(float value) {
		return rotationPointX += value;
	}

	public float getRotationPointY() {
		return rotationPointY;
	}

	public float setRotationPointY(float value) {
		return rotationPointY = value;
	}

	public float addRotationPointY(float value) {
		return rotationPointY += value;
	}

	public float getRotationPointZ() {
		return rotationPointZ;
	}

	public float setRotationPointZ(float value) {
		return rotationPointZ = value;
	}

	public float addRotationPointZ(float value) {
		return rotationPointZ += value;
	}

	public ModelRenderer setScale(float pX, float pY, float pZ) {
		scaleX = pX;
		scaleY = pY;
		scaleZ = pZ;
		return this;
	}

	public float setScaleX(float pValue) {
		return scaleX = pValue;
	}

	public float setScaleY(float pValue) {
		return scaleY = pValue;
	}

	public float setScaleZ(float pValue) {
		return scaleZ = pValue;
	}

	public void removeChild(ModelRenderer par1ModelRenderer) {
		if (this.childModels != null)
			this.childModels.remove(par1ModelRenderer);
	}
	public void clearChildModels() {
		if (this.childModels != null)
			this.childModels.clear();
	}



//
//	public int getBoxSizeX() {
//		return getboxSizeX(0);
//	}
//
//	public int getBoxSizeY() {
//		return getboxSizeY(0);
//	}
//
//	public int getBoxSizeZ() {
//		return getboxSizeZ(0);
//	}
//
//	public int getboxSizeX(int i) {
//		return this.cubeList != null
//				&& this.cubeList.size() > i ? (Integer) Modchu_Reflect.getFieldObject(this.cubeList.get(i).getClass(), "boxSizeX", this.cubeList.get(i)) : -1;
//	}
//
//	public int getboxSizeY(int i) {
//		return this.cubeList != null
//				&& this.cubeList.size() > i ? (Integer) Modchu_Reflect.getFieldObject(this.cubeList.get(i).getClass(), "boxSizeY", this.cubeList.get(i)) : -1;
//	}
//
//	public int getboxSizeZ(int i) {
//		return this.cubeList != null
//				&& this.cubeList.size() > i ? this.cubeList.get(i).setBoxName(Integer) Modchu_Reflect.getFieldObject(this.cubeList.get(i).getClass(), "boxSizeZ", this.cubeList.get(i)) : -1;
//	}
}

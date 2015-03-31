/*     */ package littleMaidMobX;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_ItemSpawnEgg
/*     */   extends Item
/*     */ {
/*     */   public LMM_ItemSpawnEgg()
/*     */   {
/*  30 */     setHasSubtypes(true);
/*  31 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
/*     */   {
/*  37 */     if (par3World.isRemote)
/*     */     {
/*  39 */       return true;
/*     */     }
/*     */     
/*     */ 
/*  43 */     Block block = par3World.getBlock(par4, par5, par6);
/*  44 */     par4 += net.minecraft.util.Facing.offsetsXForSide[par7];
/*  45 */     par5 += net.minecraft.util.Facing.offsetsYForSide[par7];
/*  46 */     par6 += net.minecraft.util.Facing.offsetsZForSide[par7];
/*  47 */     double d0 = 0.0D;
/*     */     
/*  49 */     if ((par7 == 1) && (block.getRenderType() == 11))
/*     */     {
/*  51 */       d0 = 0.5D;
/*     */     }
/*     */     
/*  54 */     Entity entity = spawnMaid(par3World, par1ItemStack.getItemDamage(), par4 + 0.5D, par5 + d0, par6 + 0.5D);
/*     */     
/*  56 */     if (entity != null)
/*     */     {
/*  58 */       if (((entity instanceof EntityLivingBase)) && (par1ItemStack.hasDisplayName()))
/*     */       {
/*  60 */         ((EntityLiving)entity).setCustomNameTag(par1ItemStack.getDisplayName());
/*     */       }
/*     */       
/*  63 */       if (!par2EntityPlayer.capabilities.isCreativeMode)
/*     */       {
/*  65 */         par1ItemStack.stackSize -= 1;
/*     */       }
/*     */     }
/*     */     
/*  69 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/*  76 */     if (par2World.isRemote)
/*     */     {
/*  78 */       return par1ItemStack;
/*     */     }
/*     */     
/*     */ 
/*  82 */     MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
/*     */     
/*  84 */     if (movingobjectposition == null)
/*     */     {
/*  86 */       return par1ItemStack;
/*     */     }
/*     */     
/*     */ 
/*  90 */     if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
/*     */     {
/*  92 */       int i = movingobjectposition.blockX;
/*  93 */       int j = movingobjectposition.blockY;
/*  94 */       int k = movingobjectposition.blockZ;
/*     */       
/*  96 */       if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
/*     */       {
/*  98 */         return par1ItemStack;
/*     */       }
/*     */       
/* 101 */       if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
/*     */       {
/* 103 */         return par1ItemStack;
/*     */       }
/*     */       
/* 106 */       if ((par2World.getBlock(i, j, k) instanceof BlockLiquid))
/*     */       {
/* 108 */         Entity entity = spawnMaid(par2World, par1ItemStack.getItemDamage(), i, j, k);
/*     */         
/* 110 */         if (entity != null)
/*     */         {
/* 112 */           if (((entity instanceof EntityLivingBase)) && (par1ItemStack.hasDisplayName()))
/*     */           {
/* 114 */             ((EntityLiving)entity).setCustomNameTag(par1ItemStack.getDisplayName());
/*     */           }
/*     */           
/* 117 */           if (!par3EntityPlayer.capabilities.isCreativeMode)
/*     */           {
/* 119 */             par1ItemStack.stackSize -= 1;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 125 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Entity spawnMaid(World par0World, int par1, double par2, double par4, double par6)
/*     */   {
/* 132 */     Entity entity = null;
/*     */     try {
/* 134 */       entity = new LMM_EntityLittleMaid(par0World);
/*     */       
/* 136 */       EntityLiving entityliving = (EntityLiving)entity;
/* 137 */       entity.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
/* 138 */       entityliving.rotationYawHead = entityliving.rotationYaw;
/* 139 */       entityliving.renderYawOffset = entityliving.rotationYaw;
/* 140 */       entityliving.onSpawnWithEgg((IEntityLivingData)null);
/* 141 */       par0World.spawnEntityInWorld(entity);
/* 142 */       entityliving.playLivingSound();
/*     */     } catch (Exception e) {
/* 144 */       e.printStackTrace();
/*     */     }
/*     */     
/* 147 */     return entity;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void getSubItems(Item par1, CreativeTabs par2, List par3)
/*     */   {
/* 154 */     par3.add(new ItemStack(par1, 1));
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_ItemSpawnEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
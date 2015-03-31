/*     */ package zabuton;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class VZN_ItemZabuton extends Item
/*     */ {
/*  19 */   public static final String[] colorNamesJP = { "黒", "赤", "緑", "茶", "青", "紫", "空", "銀", "灰", "撫子", "鶸", "黄", "淡青", "紅紫", "橙", "白" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  25 */   public static final int[] colorValues = { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 10526895, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VZN_ItemZabuton()
/*     */   {
/*  33 */     setMaxStackSize(8);
/*  34 */     setHasSubtypes(true);
/*  35 */     setMaxDamage(0);
/*  36 */     setCreativeTab(CreativeTabs.tabTransport);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
/*     */   {
/*  42 */     float f = 1.0F;
/*  43 */     float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
/*     */     
/*  45 */     float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
/*     */     
/*  47 */     double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * f;
/*     */     
/*  49 */     double d1 = entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * f + 1.62D - entityplayer.yOffset;
/*     */     
/*     */ 
/*  52 */     double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * f;
/*     */     
/*  54 */     Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
/*  55 */     float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
/*  56 */     float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
/*  57 */     float f5 = -MathHelper.cos(-f1 * 0.01745329F);
/*  58 */     float f6 = MathHelper.sin(-f1 * 0.01745329F);
/*  59 */     float f7 = f4 * f5;
/*  60 */     float f8 = f6;
/*  61 */     float f9 = f3 * f5;
/*  62 */     double d3 = 5.0D;
/*  63 */     Vec3 vec3d1 = vec3d.addVector(f7 * d3, f8 * d3, f9 * d3);
/*     */     
/*  65 */     MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, true);
/*  66 */     if (movingobjectposition == null) {
/*  67 */       return itemstack;
/*     */     }
/*  69 */     if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  70 */       int i = movingobjectposition.blockX;
/*  71 */       int j = movingobjectposition.blockY;
/*  72 */       int k = movingobjectposition.blockZ;
/*  73 */       if (world.getBlock(i, j + 1, k).getMaterial() == Material.air) {
/*  74 */         if (!world.isRemote) {
/*  75 */           VZN_EntityZabuton ez = new VZN_EntityZabuton(world, i + 0.5F, j + 1.0F, k + 0.5F, (byte)(itemstack.getItemDamage() & 0xF));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  81 */           ez.rotationYaw = ((MathHelper.floor_double(entityplayer.rotationYaw * 4.0F / 360.0F + 2.5D) & 0x3) * 90);
/*  82 */           world.spawnEntityInWorld(ez);
/*     */         }
/*  84 */         if (!entityplayer.capabilities.isCreativeMode) {
/*  85 */           itemstack.stackSize -= 1;
/*     */         }
/*     */       }
/*     */     }
/*  89 */     return itemstack;
/*     */   }
/*     */   
/*     */   public int getColorFromDamage(int pdamage, int pindex) {
/*  93 */     int li = colorValues[pdamage];
/*  94 */     return li;
/*     */   }
/*     */   
/*     */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
/*     */   {
/*  99 */     return getColorFromDamage(par1ItemStack.getItemDamage(), par2);
/*     */   }
/*     */   
/*     */   public String getUnlocalizedName(ItemStack par1ItemStack)
/*     */   {
/* 104 */     return super.getUnlocalizedName() + "." + net.minecraft.item.ItemDye.field_150923_a[par1ItemStack.getItemDamage()];
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean requiresMultipleRenderPasses()
/*     */   {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 115 */     for (int li = 0; li < 16; li++) {
/* 116 */       par3List.add(new ItemStack(par1, 1, li));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_ItemZabuton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
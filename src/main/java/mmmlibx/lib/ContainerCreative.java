/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ContainerCreative
/*     */   extends Container
/*     */ {
/*  15 */   private static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
/*     */   
/*  17 */   public List itemList = new ArrayList();
/*     */   private static final String __OBFID = "CL_00000753";
/*     */   
/*     */   public ContainerCreative(EntityPlayer par1EntityPlayer)
/*     */   {
/*  22 */     InventoryPlayer inventoryplayer = par1EntityPlayer.inventory;
/*     */     
/*     */ 
/*  25 */     for (int i = 0; i < 5; i++)
/*     */     {
/*  27 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  29 */         addSlotToContainer(new Slot(field_147060_v, i * 9 + j, 9 + j * 18, 18 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  33 */     for (i = 0; i < 9; i++)
/*     */     {
/*  35 */       addSlotToContainer(new Slot(inventoryplayer, i, 9 + i * 18, 112));
/*     */     }
/*     */     
/*  38 */     scrollTo(0.0F);
/*     */   }
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void scrollTo(float p_148329_1_)
/*     */   {
/*  51 */     int i = this.itemList.size() / 9 - 5 + 1;
/*  52 */     int j = (int)(p_148329_1_ * i + 0.5D);
/*     */     
/*  54 */     if (j < 0)
/*     */     {
/*  56 */       j = 0;
/*     */     }
/*     */     
/*  59 */     for (int k = 0; k < 5; k++)
/*     */     {
/*  61 */       for (int l = 0; l < 9; l++)
/*     */       {
/*  63 */         int i1 = l + (k + j) * 9;
/*     */         
/*  65 */         if ((i1 >= 0) && (i1 < this.itemList.size()))
/*     */         {
/*  67 */           field_147060_v.setInventorySlotContents(l + k * 9, (ItemStack)this.itemList.get(i1));
/*     */         }
/*     */         else
/*     */         {
/*  71 */           field_147060_v.setInventorySlotContents(l + k * 9, (ItemStack)null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_148328_e()
/*     */   {
/*  79 */     return this.itemList.size() > 45;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  90 */     if ((par2 >= this.inventorySlots.size() - 9) && (par2 < this.inventorySlots.size()))
/*     */     {
/*  92 */       Slot slot = (Slot)this.inventorySlots.get(par2);
/*     */       
/*  94 */       if ((slot != null) && (slot.getHasStack()))
/*     */       {
/*  96 */         slot.putStack((ItemStack)null);
/*     */       }
/*     */     }
/*     */     
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
/*     */   {
/* 105 */     return par2Slot.yDisplayPosition > 90;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canDragIntoSlot(Slot par1Slot)
/*     */   {
/* 114 */     return ((par1Slot.inventory instanceof InventoryPlayer)) || ((par1Slot.yDisplayPosition > 90) && (par1Slot.xDisplayPosition <= 162));
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/ContainerCreative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
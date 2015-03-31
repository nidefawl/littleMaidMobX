/*    */ package zabuton;
/*    */ 
/*    */ import net.minecraft.dispenser.BehaviorProjectileDispense;
/*    */ import net.minecraft.dispenser.IBlockSource;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class VZN_BehaviorZabutonDispense
/*    */   extends BehaviorProjectileDispense
/*    */ {
/*    */   protected ItemStack fitemstack;
/*    */   
/*    */   public ItemStack dispenseStack(IBlockSource par1iBlockSource, ItemStack par2ItemStack)
/*    */   {
/* 17 */     this.fitemstack = par2ItemStack;
/* 18 */     return super.dispenseStack(par1iBlockSource, par2ItemStack);
/*    */   }
/*    */   
/*    */   protected IProjectile getProjectileEntity(World var1, IPosition var2)
/*    */   {
/* 23 */     return new VZN_EntityZabuton(var1, var2.getX(), var2.getY(), var2.getZ(), (byte)this.fitemstack.getItemDamage());
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_BehaviorZabutonDispense.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
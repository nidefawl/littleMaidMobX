package mmmlibx.lib;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract interface MMM_IItemRenderManager
{
  public static final int VM_FIRST_PERSON = 0;
  public static final int VM_THERD_PERSON = 1;
  public static final int VM_THERD_PERSON_INV = 2;
  
  public abstract boolean renderItem(Entity paramEntity, ItemStack paramItemStack, int paramInt);
  
  public abstract boolean renderItemInFirstPerson(Entity paramEntity, ItemStack paramItemStack, float paramFloat);
  
  public abstract boolean renderItemWorld(ItemStack paramItemStack);
  
  public abstract ResourceLocation getRenderTexture(ItemStack paramItemStack);
  
  public abstract boolean isRenderItem(ItemStack paramItemStack);
  
  public abstract boolean isRenderItemInFirstPerson(ItemStack paramItemStack);
  
  public abstract boolean isRenderItemWorld(ItemStack paramItemStack);
}


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_IItemRenderManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
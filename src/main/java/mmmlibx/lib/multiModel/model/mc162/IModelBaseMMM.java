package mmmlibx.lib.multiModel.model.mc162;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;

public abstract interface IModelBaseMMM
  extends IModelCaps
{
  public abstract void renderItems(EntityLivingBase paramEntityLivingBase, Render paramRender);
  
  public abstract void showArmorParts(int paramInt);
  
  public abstract void setEntityCaps(IModelCaps paramIModelCaps);
  
  public abstract void setRender(Render paramRender);
  
  public abstract void setArmorRendering(boolean paramBoolean);
}


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/IModelBaseMMM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
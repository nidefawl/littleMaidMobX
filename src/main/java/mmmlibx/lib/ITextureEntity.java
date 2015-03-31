package mmmlibx.lib;

import net.minecraft.util.ResourceLocation;

public abstract interface ITextureEntity
{
  public abstract void setTexturePackIndex(int paramInt, int[] paramArrayOfInt);
  
  public abstract void setTexturePackName(MMM_TextureBox[] paramArrayOfMMM_TextureBox);
  
  public abstract void setColor(int paramInt);
  
  public abstract int getColor();
  
  public abstract void setContract(boolean paramBoolean);
  
  public abstract boolean isContract();
  
  public abstract void setTextureBox(MMM_TextureBoxBase[] paramArrayOfMMM_TextureBoxBase);
  
  public abstract MMM_TextureBoxBase[] getTextureBox();
  
  public abstract void setTextureIndex(int[] paramArrayOfInt);
  
  public abstract int[] getTextureIndex();
  
  public abstract void setTextures(int paramInt, ResourceLocation[] paramArrayOfResourceLocation);
  
  public abstract ResourceLocation[] getTextures(int paramInt);
  
  public abstract MMM_TextureData getTextureData();
}


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/ITextureEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
package wrapper;

import com.mojang.authlib.GameProfile;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.passive.EntityTameable;

public abstract interface W_ICommon
{
  public abstract void setOwner(EntityTameable paramEntityTameable, String paramString);
  
  public abstract String getOwnerName(IEntityOwnable paramIEntityOwnable);
  
  public abstract GameProfile newGameProfile(String paramString1, String paramString2);
  
  public abstract void notifyAdmins(ICommandSender paramICommandSender, ICommand paramICommand, int paramInt, String paramString, Object... paramVarArgs);
}


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/wrapper/W_ICommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
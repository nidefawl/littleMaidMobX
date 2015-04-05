package littleMaidMobX;

import littleMaidMobX.entity.EntityLittleMaidAvatar;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHook
{
	@SubscribeEvent
	public void onEntityItemPickupEvent(EntityItemPickupEvent event)
	{
		if(event.entityPlayer instanceof EntityLittleMaidAvatar)
		{
			if(event.item!=null && LittleMaidMobX.isMaidIgnoreItem(event.item.getEntityItem()))
			{
				event.setCanceled(true);
			}
		}
	}
}

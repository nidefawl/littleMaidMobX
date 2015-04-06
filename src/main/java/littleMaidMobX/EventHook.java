package littleMaidMobX;

import littleMaidMobX.entity.EntityLittleMaidAvatar;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
	@SubscribeEvent
	public void onEntitySpawned(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityArrow) {
			EntityArrow arrow = (EntityArrow) event.entity;
			if (arrow.shootingEntity instanceof EntityLittleMaidAvatar) {
				EntityLittleMaidAvatar avatar = (EntityLittleMaidAvatar) arrow.shootingEntity;
				arrow.shootingEntity = avatar.avatar;
				LittleMaidMobX.Debug("Set "+event.entity.getClass()+" field shootingEntity from avator to maid");

			}
		}
	}
}

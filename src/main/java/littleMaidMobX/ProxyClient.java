package littleMaidMobX;

import static littleMaidMobX.Statics.LMN_Client_PlaySound;
import static littleMaidMobX.Statics.LMN_Client_SetIFFValue;
import static littleMaidMobX.Statics.LMN_Client_SwingArm;
import littleMaidMobX.aimodes.IFF;
import littleMaidMobX.entity.EntityLittleMaid;
import littleMaidMobX.entity.EntityLittleMaidAvatar;
import littleMaidMobX.entity.EntityDummy;
import littleMaidMobX.entity.EntitySelect;
import littleMaidMobX.network.Net;
import littleMaidMobX.network.Message;
import littleMaidMobX.render.RenderLittleMaid;
import littleMaidMobX.render.RenderDummy;
import littleMaidMobX.render.RenderModelMulti;
import littleMaidMobX.sound.EnumSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * クライアント専用処理。
 * マルチ用に分離。
 * 分離しとかないとNoSuchMethodで落ちる。
 */
public class ProxyClient extends ProxyCommon
{

	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLittleMaid.class,new RenderLittleMaid(0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySelect.class,	new RenderModelMulti(0.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDummy.class,		new RenderDummy());
// TODO ★		RenderingRegistry.registerEntityRenderingHandler(EntityItem.class,			new MMM_RenderItem());
	}

	/* 呼び出し箇所なし
	public GuiContainer getContainerGUI(EntityClientPlayerMP var1, int var2,
			int var3, int var4, int var5) {
		Entity lentity = var1.worldObj.getEntityByID(var3);
		if (lentity instanceof LMM_EntityLittleMaid) {
			LMM_GuiInventory lgui = new LMM_GuiInventory(var1, (LMM_EntityLittleMaid)lentity);
//			var1.openContainer = lgui.inventorySlots;
			return lgui;
		} else {
			return null;
		}
	}
	*/

// Avatarr
	
	public void onItemPickup(EntityLittleMaidAvatar pAvatar, Entity entity, int i) {
		// アイテム回収のエフェクト
		// TODO:こっちを使うか？
//        mc.effectRenderer.addEffect(new EntityPickupFX(mc.theWorld, entity, avatar, -0.5F));
		Helper.mc.effectRenderer.addEffect(new EntityPickupFX(Helper.mc.theWorld, entity, pAvatar.avatar, 0.1F));
	}

	public void onCriticalHit(EntityLittleMaidAvatar pAvatar, Entity par1Entity) {
		Helper.mc.effectRenderer.addEffect(new EntityCrit2FX(Helper.mc.theWorld, par1Entity));
	}

	public void onEnchantmentCritical(EntityLittleMaidAvatar pAvatar, Entity par1Entity) {
		EntityCrit2FX entitycrit2fx = new EntityCrit2FX(Helper.mc.theWorld, par1Entity, "magicCrit");
		Helper.mc.effectRenderer.addEffect(entitycrit2fx);
	}

	
// Network

	public void clientCustomPayload(Message var2) {
		// クライアント側の特殊パケット受信動作
		byte lmode = var2.data[0];
		int leid = 0;
		EntityLittleMaid lemaid = null;
		if ((lmode & 0x80) != 0) {
			leid = Helper.getInt(var2.data, 1);
			lemaid =Net.getLittleMaid(var2.data, 1, Helper.mc.theWorld);
			if (lemaid == null) return;
		}
		LittleMaidMobX.Debug(String.format("LMM|Upd Clt Call[%2x:%d].", lmode, leid));
		
		switch (lmode) {
		case LMN_Client_SwingArm : 
			// 腕振り
			byte larm = var2.data[5];
			EnumSound lsound = EnumSound.getEnumSound(Helper.getInt(var2.data, 6));
			lemaid.setSwinging(larm, lsound);
//			mod_LMM_littleMaidMob.Debug(String.format("SwingSound:%s", lsound.name()));
			break;
			
		case LMN_Client_SetIFFValue:
			// IFFの設定値を受信
			int lval = var2.data[1];
			int lindex = Helper.getInt(var2.data, 2);
			String lname = (String)IFF.DefaultIFF.keySet().toArray()[lindex];
			LittleMaidMobX.Debug("setIFF-CL %s(%d)=%d", lname, lindex, lval);
			IFF.setIFFValue(null, lname, lval);
			break;
			
		case LMN_Client_PlaySound : 
			// 音声再生
			EnumSound lsound9 = EnumSound.getEnumSound(Helper.getInt(var2.data, 5));
			lemaid.playLittleMaidSound(lsound9, true);
			LittleMaidMobX.Debug(String.format("playSound:%s", lsound9.name()));
			break;
			
		}
	}

	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}


	public boolean isSinglePlayer()
	{
		return Minecraft.getMinecraft().isSingleplayer();
	}
}

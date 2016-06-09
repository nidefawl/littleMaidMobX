package littleMaidMobX.aimodes;

import java.util.List;

import littleMaidMobX.LittleMaidMobX;
import littleMaidMobX.ai.AIHurtByTarget;
import littleMaidMobX.ai.AINearestAttackableTarget;
import littleMaidMobX.entity.EntityLittleMaid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Mode_Archer extends ModeBase {

	public static final int mmode_Archer		= 0x0083;
	public static final int mmode_Blazingstar	= 0x00c3;
	
	
	@Override
	public int priority() {
		return 3200;
	}

	public Mode_Archer(EntityLittleMaid pEntity) {
		super(pEntity);
		TriggerSelect.appendTriggerItem(null, "Bow", "");
		TriggerSelect.appendTriggerItem(null, "Arrow", "");
	}

	@Override
	public void init()
	{

	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Archer:0x0083
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = new EntityAITasks(owner.aiProfiler);
		
//		ltasks[1].addTask(1, new EntityAIOwnerHurtByTarget(owner));
//		ltasks[1].addTask(2, new EntityAIOwnerHurtTarget(owner));
		ltasks[1].addTask(3, new AIHurtByTarget(owner, true));
		ltasks[1].addTask(4, new AINearestAttackableTarget(owner, EntityLivingBase.class, 0, true));
		
		owner.addMaidMode(ltasks, "Archer", mmode_Archer);
		
		
		// Blazingstar:0x00c3
		EntityAITasks[] ltasks2 = new EntityAITasks[2];
		ltasks2[0] = pDefaultMove;
		ltasks2[1] = new EntityAITasks(owner.aiProfiler);
		
		ltasks2[1].addTask(1, new AIHurtByTarget(owner, true));
		ltasks2[1].addTask(2, new AINearestAttackableTarget(owner, EntityLivingBase.class, 0, true));
		
		owner.addMaidMode(ltasks2, "Blazingstar", mmode_Blazingstar);
	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
		if (litemstack != null) {
			if (litemstack.getItem() instanceof ItemBow || TriggerSelect.checkItem(owner.getMaidMaster(), "Bow", litemstack)) {
				if (owner.maidInventory.getInventorySlotContainItem(ItemFlintAndSteel.class) > -1) {
					owner.setMaidMode("Blazingstar");
				} else {
					owner.setMaidMode("Archer");
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Archer :
			owner.aiAttack.setEnable(false);
			owner.aiShooting.setEnable(true);
			owner.setBloodsuck(false);
			return true;
		case mmode_Blazingstar :
			owner.aiAttack.setEnable(false);
			owner.aiShooting.setEnable(true);
			owner.setBloodsuck(true);
			return true;
		}
		
		return false;
	}
	
	@Override
	public int getNextEquipItem(int pMode) {
		int li;
		ItemStack litemstack;

		
		switch (pMode)
		{
		case mmode_Archer :
		case mmode_Blazingstar :
			for (li = 0; li < owner.maidInventory.maxInventorySize; li++)
			{
				litemstack = owner.maidInventory.getStackInSlot(li);
				if (litemstack == null) continue;
				
				if (litemstack.getItem() instanceof ItemBow || TriggerSelect.checkItem(owner.getMaidMaster(), "Bow", litemstack))
				{
					return li;
				}
			}
			break;
		}
		return -1;
	}
	
	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		String ls = owner.getMaidMaster();
		return (pItemStack.getItem() == Items.sugar
				|| pItemStack.getItem() instanceof ItemBow
				|| pItemStack.getItem() == Items.arrow
				|| TriggerSelect.checkItem(ls, "Bow", pItemStack)
				|| TriggerSelect.checkItem(ls, "Arrow", pItemStack)
				|| TriggerSelect.checkItem(ls, "Pickup", pItemStack));
	}

	@Override
	public void onUpdate(int pMode) {
		switch (pMode) {
		case mmode_Archer:
		case mmode_Blazingstar:
			owner.getWeaponStatus();
//			updateGuns();
			break;
		}

	}

	@Override
	public void updateAITick(int pMode) {
		switch (pMode) {
		case mmode_Archer:
//			owner.getWeaponStatus();
			updateGuns();
			break;
		case mmode_Blazingstar:
//			owner.getWeaponStatus();
			updateGuns();
			
			World lworld = owner.worldObj;
			List<Entity> llist = lworld.getEntitiesWithinAABB(Entity.class, owner.boundingBox.expand(16D, 16D, 16D));
			for (int li = 0; li < llist.size(); li++) {
				Entity lentity = llist.get(li); 
				if (lentity.isEntityAlive() && lentity.isBurning() && owner.getRNG().nextFloat() > 0.9F) {
					
					int lx = (int)owner.posX;
					int ly = (int)owner.posY;
					int lz = (int)owner.posZ;
					if (lworld.getBlock(lx, ly, lz) == Blocks.air || lworld.getBlock(lx, ly, lz).getMaterial().getCanBurn()) {
						lworld.playSoundEffect((double)lx + 0.5D, (double)ly + 0.5D, (double)lz + 0.5D, "fire.ignite", 1.0F, owner.getRNG().nextFloat() * 0.4F + 0.8F);
						lworld.setBlock(lx, ly, lz, Blocks.fire);
					}
				}
			}
			break;
		}
	}

	protected void updateGuns() {
		if (owner.getAttackTarget() == null || !owner.getAttackTarget().isEntityAlive()) {
			
			if (!owner.weaponReload) {
				if (owner.maidAvatar.isUsingItem()) {
					
					if (owner.maidAvatar.isItemReload) {
						owner.maidAvatar.stopUsingItem();
						LittleMaidMobX.Debug(String.format("id:%d cancel reload.", owner.getEntityId()));
					} else {
						owner.maidAvatar.clearItemInUse();
						LittleMaidMobX.Debug(String.format("id:%d clear.", owner.getEntityId()));
					}
				}
			} else {
				owner.mstatAimeBow = true;
			}
		}
		if (owner.weaponReload && !owner.maidAvatar.isUsingItem()) {
			
			owner.maidInventory.getCurrentItem().useItemRightClick(owner.worldObj, owner.maidAvatar);
			LittleMaidMobX.Debug("id:%d force reload.", owner.getEntityId());
			owner.mstatAimeBow = true;
		}

	}
	
}

package littleMaidMobX.inventory;

import littleMaidMobX.LittleMaidMobX;
import littleMaidMobX.entity.EntityLittleMaid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerInventory extends ContainerPlayer {
	
	protected final InventoryLittleMaid littlemaidInventory;
	protected final int numRows;
	protected final EntityLittleMaid owner;


	public ContainerInventory(IInventory iinventory, EntityLittleMaid pEntity) {
		// >
		
		super(pEntity.maidInventory, !pEntity.worldObj.isRemote, pEntity.maidAvatar);
		inventorySlots.clear();
		inventoryItemStacks.clear();
		// <
		
		InventoryLittleMaid linventory = pEntity.maidInventory;
		owner = pEntity;
		numRows = linventory.getSizeInventory() / 9;
		littlemaidInventory = linventory;
		littlemaidInventory.openInventory();
		
		for (int ly = 0; ly < numRows; ly++) {
			for (int lx = 0; lx < 9; lx++) {
				addSlotToContainer(new Slot(linventory, lx + ly * 9, 8 + lx * 18, 76 + ly * 18));
			}
		}
		
		int lyoffset = (numRows - 4) * 18 + 59;
		for (int ly = 0; ly < 3; ly++) {
			for (int lx = 0; lx < 9; lx++) {
				addSlotToContainer(new Slot(iinventory, lx + ly * 9 + 9, 8 + lx * 18, 103 + ly * 18 + lyoffset));
			}
		}
		
		for (int lx = 0; lx < 9; lx++) {
			addSlotToContainer(new Slot(iinventory, lx, 8 + lx * 18, 161 + lyoffset));
		}
		
		for (int j = 0; j < 3; j++) {
//			int j1 = j + 1;
//			addSlotToContainer(new SlotArmor(this, linventory, linventory.getSizeInventory() - 2 - j, 8, 8 + j * 18, j1));

			final int armorIndex = 1 + j; 
			this.addSlotToContainer(new Slot(linventory, linventory.getSizeInventory() - 2 - j, 8, 8 + j * 18)
			{
				private static final String __OBFID = "CL_00001755";
				/**
				 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
				 * in the case of armor slots)
				 */
				public int getSlotStackLimit()
				{
					return 1;
				}
				/**
				 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
				 */
				public boolean isItemValid(ItemStack par1ItemStack)
				{
					if (par1ItemStack == null) return false;
					return par1ItemStack.getItem().isValidArmor(par1ItemStack, armorIndex, owner);
				}
				/**
				 * Returns the icon index on items.png that is used as background image of the slot.
				 */
				@SideOnly(Side.CLIENT)
				public IIcon getBackgroundIconIndex()
				{
					return ItemArmor.func_94602_b(armorIndex);
				}
			});
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		
		EntityLittleMaid entitylittlemaid = littlemaidInventory.entityLittleMaid; 
		if(entitylittlemaid.isDead) {
//		if(entitylittlemaid.isDead || entitylittlemaid.isOpenInventory()) {
			return false;
		}
		return entityplayer.getDistanceSqToEntity(entitylittlemaid) <= 64D;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int pIndex) {
		ItemStack litemstack = null;
		Slot slot = (Slot)inventorySlots.get(pIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			
			if(LittleMaidMobX.isMaidIgnoreItem(itemstack1))
			{
				
			}
			else
			{
				litemstack = itemstack1.copy();
				int lline = numRows * 9;
				if (pIndex < lline) {
					if (!this.mergeItemStack(itemstack1, lline, lline + 36, true)) {
						return null;
					}
				} else if (pIndex >= lline && pIndex < lline + 36) {
					if (!this.mergeItemStack(itemstack1, 0, lline, false)) {
						return null;
					}
				} else {
					if (!this.mergeItemStack(itemstack1, 0, lline + 36, false)) {
						return null;
					}
				}
				if (itemstack1.stackSize == 0) {
					slot.putStack(null);
				} else {
					slot.onSlotChanged();
				}
			}
		}
		return litemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		littlemaidInventory.closeInventory();
	}

	@Override
	public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
		return true;
	}

}

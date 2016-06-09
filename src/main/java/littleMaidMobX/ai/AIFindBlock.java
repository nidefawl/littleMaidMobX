package littleMaidMobX.ai;

import littleMaidMobX.aimodes.ModeBase;
import littleMaidMobX.entity.EntityDummy;
import littleMaidMobX.entity.EntityLittleMaid;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class AIFindBlock extends EntityAIBase implements IEntityAI {

	protected boolean isEnable;
	protected EntityLittleMaid theMaid;
	protected ModeBase fmodeBase;
//	protected MovingObjectPosition theBlock;
//	protected int tileX;
//	protected int tileY;
//	protected int tileZ;
//	protected boolean isFind;
	
	
	public AIFindBlock(EntityLittleMaid pEntityLittleMaid) {
		theMaid = pEntityLittleMaid;
		isEnable = true;
//		theBlock = null;
		
		setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		fmodeBase = theMaid.getActiveModeClass();
//		LMM_EntityModeBase llmode = theMaid.getActiveModeClass();
//		if (!isEnable || theMaid.isWait() || theMaid.getActiveModeClass() == null || !theMaid.getActiveModeClass().isSearchBlock() || theMaid.getCurrentEquippedItem() == null) {
		if (!isEnable || theMaid.isMaidWait() || fmodeBase == null) {
			return false;
		}
		if (!fmodeBase.isSearchBlock())
		{
			return fmodeBase.shouldBlock(theMaid.maidMode);
		}
		
		
		int lx = MathHelper.floor_double(theMaid.posX);
		int ly = MathHelper.floor_double(theMaid.posY);
		int lz = MathHelper.floor_double(theMaid.posZ);
		int vt = MathHelper.floor_float(((theMaid.rotationYawHead * 4F) / 360F) + 2.5F) & 3;
		int xx = lx;
		int yy = ly;
		int zz = lz;
		
		// TODO:Dummy
		EntityDummy.clearDummyEntity(theMaid);
		boolean flagdammy = false;
		
		
		for (int d = 0; d < 4; d++) {
			for (int a = 0; a < 18; a += 2) {
				int del = a / 2;
				if (vt == 0) {
					xx = lx - del;
					zz = lz - del;
				} 
				else if (vt == 1) { 
					xx = lx + del;
					zz = lz - del;
				} 
				else if (vt == 2) { 
					xx = lx + del;
					zz = lz + del;
				} 
				else if (vt == 3) { 
					xx = lx - del;
					zz = lz + del;
				}
				// TODO:Dummay
				if (!flagdammy) {
					EntityDummy.setDummyEntity(theMaid, 0x00ff4f4f, xx, ly, zz);
					flagdammy = true;
				}
				int b = 0;
				do {
					for (int c = 0; c < 3; c++) {
						yy = ly + (c == 2 ? -1 : c);
						if (fmodeBase.checkBlock(theMaid.maidMode, xx, yy, zz))
						{
							if (fmodeBase.outrangeBlock(theMaid.maidMode, xx, yy, zz))
							{
								theMaid.setTilePos(xx, yy, zz);
								// TODO:Dummay
								EntityDummy.setDummyEntity(theMaid, 0x004fff4f, xx, yy, zz);
								flagdammy = true;
								return true;
							}
						}
					}
					// TODO:Dummy
					if (!flagdammy) {
						EntityDummy.setDummyEntity(theMaid, 0x00ffffcf, xx, ly, zz);
						flagdammy = true;
					}
					// TODO:dammy
					flagdammy = false;
					
					if (vt == 0) {
						xx++;
					} 
					else if (vt == 1) { 
						zz++;
					} 
					else if (vt == 2) { 
						xx--;
					} 
					else if (vt == 3) { 
						zz--;
					}
					
				} while(++b < a);
			}
			vt = (vt + 1) & 3;
		}
		if (fmodeBase.overlooksBlock(theMaid.maidMode)) {
			TileEntity ltile = theMaid.maidTileEntity;
			if (ltile != null) {
				lx = ltile.xCoord;
				ly = ltile.yCoord;
				lz = ltile.zCoord;
				// TODO:Dummay
				EntityDummy.setDummyEntity(theMaid, 0x004fff4f, lx, ly, lz);
				flagdammy = true;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean continueExecuting() {
		fmodeBase.updateBlock();
		
		if (!theMaid.getNavigator().noPath()) return true;
		
		double ld = theMaid.getDistanceTilePos();
		if (ld > 100.0D) {
			
			theMaid.getActiveModeClass().farrangeBlock();
			return false;
		} else if (ld > 5.0D) {
			
			return theMaid.getActiveModeClass().outrangeBlock(theMaid.maidMode);
		} else {
			
			return theMaid.getActiveModeClass().executeBlock(theMaid.maidMode);
		}
	}

	@Override
	public void startExecuting() {
		fmodeBase.startBlock(theMaid.maidMode);
	}

	@Override
	public void resetTask() {
		fmodeBase.resetBlock(theMaid.maidMode);
	}

	@Override
	public void updateTask() {
		
		theMaid.looksTilePos();
	}


	@Override
	public void setEnable(boolean pFlag) {
		isEnable = pFlag;
	}

	@Override
	public boolean getEnable() {
		return isEnable;
	}

}

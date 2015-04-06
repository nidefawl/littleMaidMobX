package littleMaidMobX.aimodes;

import java.util.ArrayList;
import java.util.List;

import littleMaidMobX.entity.EntityLittleMaid;

public class ModeManager {
	
	public static ModeManager instance = new ModeManager();

	public List<Class<? extends ModeBase>> maidModeList = new ArrayList<Class<? extends ModeBase>>();

	public ModeManager() {
		maidModeList.add(Mode_Archer.class);
		maidModeList.add(Mode_BookDecorder.class);
		maidModeList.add(Mode_Fencer.class);
		maidModeList.add(Mode_Healer.class);
		maidModeList.add(Mode_Playing.class);
		maidModeList.add(Mode_Ripper.class);
		maidModeList.add(Mode_Test.class);
		maidModeList.add(Mode_Torcher.class);
		maidModeList.add(Mode_Cooking.class);
		maidModeList.add(Mode_Pharmacist.class);
		maidModeList.add(Mode_Basic.class);
	}

	public List<ModeBase> createMaidModes(EntityLittleMaid pentity) {
		List<ModeBase> llist = new ArrayList<ModeBase>();
		for (Class<? extends ModeBase> lmode : maidModeList) {
			try {
				llist.add(lmode.getConstructor(EntityLittleMaid.class).newInstance(pentity));
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return llist;
	}

}

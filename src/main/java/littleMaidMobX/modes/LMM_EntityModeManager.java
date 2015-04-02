package littleMaidMobX.modes;

import java.util.ArrayList;
import java.util.List;

import littleMaidMobX.LMM_EntityLittleMaid;

public class LMM_EntityModeManager {
	
	public static LMM_EntityModeManager instance = new LMM_EntityModeManager();

	public List<Class<? extends LMM_EntityModeBase>> maidModeList = new ArrayList<Class<? extends LMM_EntityModeBase>>();

	public LMM_EntityModeManager() {
		maidModeList.add(LMM_EntityMode_Archer.class);
		maidModeList.add(LMM_EntityMode_BookDecorder.class);
		maidModeList.add(LMM_EntityMode_Fencer.class);
		maidModeList.add(LMM_EntityMode_Healer.class);
		maidModeList.add(LMM_EntityMode_Playing.class);
		maidModeList.add(LMM_EntityMode_Ripper.class);
		maidModeList.add(LMM_EntityMode_Test.class);
		maidModeList.add(LMM_EntityMode_Torcher.class);
		maidModeList.add(LMM_EntityMode_Basic.class);
		maidModeList.add(LMM_EntityMode_Cooking.class);
		maidModeList.add(LMM_EntityMode_Pharmacist.class);
	}

	public List<LMM_EntityModeBase> createMaidModes(LMM_EntityLittleMaid pentity) {
		List<LMM_EntityModeBase> llist = new ArrayList<LMM_EntityModeBase>();
		for (Class<? extends LMM_EntityModeBase> lmode : maidModeList) {
			try {
				llist.add(lmode.getConstructor(LMM_EntityLittleMaid.class).newInstance(pentity));
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return llist;
	}

}

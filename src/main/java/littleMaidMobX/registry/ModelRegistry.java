package littleMaidMobX.registry;

import java.util.HashSet;

import littleMaidMobX.model.maids.ModelLittleMaid_AC;
import littleMaidMobX.model.maids.ModelLittleMaid_Archetype;
import littleMaidMobX.model.maids.ModelLittleMaid_Aug;
import littleMaidMobX.model.maids.ModelLittleMaid_Orign;
import littleMaidMobX.model.maids.ModelLittleMaid_RX0;
import littleMaidMobX.model.maids.ModelLittleMaid_SR2;
import littleMaidMobX.model.maids.ModelMulti_Stef;
import littleMaidMobX.model.maids.ModelMulti_Steve;
import littleMaidMobX.model.maids.MultiModel_Elsie;
import littleMaidMobX.model.maids.MultiModel_Evelyn4;

public class ModelRegistry {
	public static final HashSet<Class> list = new HashSet<Class>();
	static {
//		list.add(ModelLittleMaid_AC.class);
		list.add(ModelLittleMaid_Orign.class);
//		list.add(ModelLittleMaid_Archetype.class);
//		list.add(ModelLittleMaid_Aug.class);
//		list.add(ModelLittleMaid_RX0.class);
//		list.add(ModelLittleMaid_SR2.class);
//		list.add(ModelMulti_Stef.class);
//		list.add(ModelMulti_Steve.class);
//		list.add(MultiModel_Evelyn4.class);
		list.add(MultiModel_Elsie.class);
	}

}

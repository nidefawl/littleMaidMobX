package littleMaidMobX.registry;

import java.util.HashSet;

import littleMaidMobX.model.maids.*;

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
		list.add(MultiModel_Elsie2.class);
		list.add(MultiModel_NM.class);
	}

}

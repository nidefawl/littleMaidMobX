package littleMaidMobX.ai;

import littleMaidMobX.entity.EntityLittleMaid;
import net.minecraft.entity.ai.EntityAISit;

public class AIWait extends EntityAISit {

	public EntityLittleMaid theMaid;


	public AIWait(EntityLittleMaid pEntity) {
		super(pEntity);
		this.setMutexBits(5);

		theMaid = pEntity;
	}

	@Override
	public boolean shouldExecute() {
		return theMaid.isMaidWaitEx() || (!theMaid.isFreedom() && theMaid.mstatMasterEntity == null);
	}

}

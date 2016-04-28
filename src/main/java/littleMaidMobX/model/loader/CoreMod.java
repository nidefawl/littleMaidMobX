package littleMaidMobX.model.loader;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class CoreMod implements IFMLLoadingPlugin{

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"littleMaidMobX.model.loader.Transformer"};
	}

	@Override
	public String getModContainerClass() {
		return "littleMaidMobX.model.loader.ModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
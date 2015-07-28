package littleMaidMobX;

import littleMaidMobX.registry.ModelManager;

public class ProxyServer extends ProxyCommon {
	public void loadTextures() {
		ModelManager.instance.loadTextures(true);
	}
}

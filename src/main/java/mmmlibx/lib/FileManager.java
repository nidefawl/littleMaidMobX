package mmmlibx.lib;

import java.io.File;

import cpw.mods.fml.relauncher.FMLInjectionData;

public class FileManager {

	public static File minecraftDir;


	static {
		//TODO: better way?! this is called when loading configs
		// look at other mods how they load configs
		Object[] lo = FMLInjectionData.data();
		minecraftDir = (File)lo[6];
	}
}

package mmmlibx.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import littleMaidMobX.LMM_LittleMaidMobX;
import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_AC;
import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Archetype;
import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Aug;
import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Orign;
import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_RX0;
import mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_SR2;
import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class MMM_TextureManager {

	/**
	 * 継承クラスで置き換えることを考慮。
	 */
	public static MMM_TextureManager instance = new MMM_TextureManager();

	public static String nameTextureIndex = "config/mod_MMM_textureList.cfg";
	public static String defaultModelName = "Orign";

	public static final int tx_oldwild = 0x10; //16;
	public static final int tx_oldarmor1 = 0x11; //17;
	public static final int tx_oldarmor2 = 0x12; //18;
	public static final int tx_oldeye = 0x13; //19;
	public static final int tx_gui = 0x20; //32;
	public static final int tx_wild = 0x30; //48;
	public static final int tx_armor1 = 0x40; //64;
	public static final int tx_armor2 = 0x50; //80;
	public static final int tx_eye = 0x60; //96;
	public static final int tx_eyecontract = 0x60; //96;
	public static final int tx_eyewild = 0x70; //112;
	public static final int tx_armor1light = 0x80; //128;
	public static final int tx_armor2light = 0x90; //144;
	public static String[] armorFilenamePrefix;
	/**
	 * 旧タイプのファイル名
	 */
	protected static String defNames[] = {
		"mob_littlemaid0.png", "mob_littlemaid1.png",
			"mob_littlemaid2.png", "mob_littlemaid3.png",
			"mob_littlemaid4.png", "mob_littlemaid5.png",
			"mob_littlemaid6.png", "mob_littlemaid7.png",
			"mob_littlemaid8.png", "mob_littlemaid9.png",
			"mob_littlemaida.png", "mob_littlemaidb.png",
			"mob_littlemaidc.png", "mob_littlemaidd.png",
			"mob_littlemaide.png", "mob_littlemaidf.png",
			"mob_littlemaidw.png",
			"mob_littlemaid_a00.png", "mob_littlemaid_a01.png"
	};

	/**
	 * ローカルで保持しているモデルのリスト
	 */
	protected Map < String, ModelMultiBase[] > modelMap = new TreeMap < String, ModelMultiBase[] > ();
	/**
	 * ローカルで保持しているテクスチャパック
	 */
	private List < MMM_TextureBox > textures = new ArrayList < MMM_TextureBox > ();
	/**
	 * サーバー側での管理番号を識別するのに使う、クライアント用。
	 */
	public Map < MMM_TextureBox, Integer > textureServerIndex = new HashMap < MMM_TextureBox, Integer > ();
	/**
	 * サーバー・クライアント間でテクスチャパックの名称リストの同期を取るのに使う、サーバー用。
	 */
	public List < MMM_TextureBoxServer > textureServer = new ArrayList < MMM_TextureBoxServer > ();
	/**
	 * Entity毎にデフォルトテクスチャを参照。
	 * 構築方法はEntityListを参照のこと。
	 */
	protected Map < Class, MMM_TextureBox > defaultTextures = new HashMap < Class, MMM_TextureBox > ();

	/**
	 * クライアント側で使う
	 */
	protected String[] requestString = new String[] {
		null, null, null, null, null, null, null, null,
		null, null, null, null, null, null, null, null
	};
	protected int[] requestStringCounter = new int[] {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	protected int[] requestIndex = new int[] {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
	};
	protected int[] requestIndexCounter = new int[] {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	protected Map < ITextureEntity, int[] > stackGetTexturePack = new HashMap < ITextureEntity, int[] > ();
	protected Map < ITextureEntity, Object[] > stackSetTexturePack = new HashMap < ITextureEntity, Object[] > ();


	public MMM_TextureBox getTextureBox(String pName) {
		for (MMM_TextureBox ltb: getTextureList()) {
			if (ltb.textureName.equals(pName)) {
				return ltb;
			}
		}
		return null;
	}

	public static List < MMM_TextureBox > getTextureList() {
		return instance.textures;
	}

	/**
	 * 渡されたTextureBoxBaseを判定してTextureBoxを返す。
	 * @param pBoxBase
	 * @return
	 */
	public MMM_TextureBox getTextureBox(MMM_TextureBoxBase pBoxBase) {
		if (pBoxBase instanceof MMM_TextureBox) {
			return (MMM_TextureBox) pBoxBase;
		} else if (pBoxBase instanceof MMM_TextureBoxServer) {
			return getTextureBox(pBoxBase.textureName);
		}
		return null;
	}

	public MMM_TextureBoxServer getTextureBoxServer(String pName) {
		for (MMM_TextureBoxServer lbox: textureServer) {
			if (lbox.textureName.equals(pName)) {
				return lbox;
			}
		}
		return null;
	}

	public MMM_TextureBoxServer getTextureBoxServer(int pIndex) {
		//		LMM_LittleMaidMobX.Debug("getTextureBoxServer: %d / %d", pIndex, textureServer.size());
		if (textureServer.size() > pIndex) {
			return textureServer.get(pIndex);
		}
		return null;
	}

	protected void getArmorPrefix() {
		armorFilenamePrefix = RenderBiped.bipedArmorFilenamePrefix;
		/*
		// アーマーファイルのプリフィックスを獲得
		try {
			armorFilenamePrefix = (String[])ModLoader.getPrivateValue(RenderBiped.class, null, 5);
			return;
		} catch (Exception e) {
		} catch (Error e) {
			e.printStackTrace();
		}
		armorFilenamePrefix = null;
*/
	}

	boolean init = false;
	public boolean loadTextures() {
		// アーマーのファイル名を識別するための文字列を獲得する
		if (MMM_Helper.isClient) {
			getArmorPrefix();
		}
		
		// this is called server and client side

		// this can be simplified
		addModelClass(ModelLittleMaid_AC.class, "AC", "mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_AC");
		addModelClass(ModelLittleMaid_Orign.class, "Orign", "mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Orign");
		addModelClass(ModelLittleMaid_Archetype.class, "Archetype", "mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Archetype");
		addModelClass(ModelLittleMaid_Aug.class, "Aug", "mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_Aug");
		addModelClass(ModelLittleMaid_RX0.class, "RX0", "mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_RX0");
		addModelClass(ModelLittleMaid_SR2.class, "SR2", "mmmlibx.lib.multiModel.model.mc162.ModelLittleMaid_SR2");
		addModelClass(MMM_ModelMulti_Stef.class, "Stef", "mmmlibx.lib.MMM_ModelMulti_Stef");
		addModelClass(MMM_ModelMulti_Steve.class, "Steve", "mmmlibx.lib.MMM_ModelMulti_Steve");
		addModelClass(MMM_ModelMulti_Steve.class, "Steve", "mmmlibx.lib.MMM_ModelMulti_Steve");
		

		// these textures are not loaded until they get rendered
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/default_40.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/default_50.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/littlemaidinventory_20.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_3c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_9.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_d.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/Stabilizer_MagicHat.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/ALTERNATIVE/testx64.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/default_40.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/default_50.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_00.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_01.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_02.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_03.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_04.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_05.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_06.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_07.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_08.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_09.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_0a.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_0b.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_0c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_0d.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_0e.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_0f.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/default/mob_littlemaid_3c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/maiduv.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/MMM_Aug/Aug_02.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/MMM_Aug/Aug_08.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/MMM_Aug/Aug_0c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/MMM_Aug/Aug_0d.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/MMM_SR2/EngageOctaver_0c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/MMM_SR2/EngageOctaver_3c.png");
		addTexture("/assets/littlemaid/textures/entity/littleMaid/mob_littlemaid.png");
		
		// TODO:実験コード
		buildCrafterTexture();

		// テクスチャパッケージにモデルクラスを紐付け
		ModelMultiBase[] ldm = modelMap.get(defaultModelName);
		if (ldm == null && !modelMap.isEmpty()) {
			ldm = (ModelMultiBase[]) modelMap.values().toArray()[0];
		}
		for (MMM_TextureBox ltb: textures) {
			if (ltb.modelName.isEmpty()) {
				ltb.setModels(defaultModelName, null, ldm);
			} else {
				if (modelMap.containsKey(ltb.modelName)) {
					ltb.setModels(ltb.modelName, modelMap.get(ltb.modelName), ldm);
				}
			}
		}
		for (Entry < String, ModelMultiBase[] > le: modelMap.entrySet()) {
			String ls = le.getValue()[0].getUsingTexture();
			if (ls != null) {
				if (getTextureBox(ls + "_" + le.getKey()) == null) {
					MMM_TextureBox lbox = null;
					for (MMM_TextureBox ltb: textures) {
						if (ltb.packegeName.equals(ls)) {
							lbox = ltb;
							break;
						}
					}
					if (lbox != null) {
						lbox = lbox.duplicate();
						lbox.setModels(le.getKey(), null, le.getValue());
						textures.add(lbox);
					}
				}
			}
		}
		LMM_LittleMaidMobX.Debug("Loaded Texture Lists.(%d)", textures.size());
		for (MMM_TextureBox lbox: textures) {
			LMM_LittleMaidMobX.Debug("texture: %s(%s) - hasModel:%b", lbox.textureName, lbox.fileName, lbox.models != null);
		}
		for (int li = textures.size() - 1; li >= 0; li--) {
			if (textures.get(li).models == null) {
				textures.remove(li);
			}
		}
		LMM_LittleMaidMobX.Debug("Rebuild Texture Lists.(%d)", textures.size());
		for (MMM_TextureBox lbox: textures) {
			LMM_LittleMaidMobX.Debug("texture: %s(%s) - hasModel:%b", lbox.textureName, lbox.fileName, lbox.models != null);
		}


		setDefaultTexture(EntityLivingBase.class, getTextureBox("default_" + defaultModelName));

		return false;
	}

	public void buildCrafterTexture() {
		// TODO:実験コード標準モデルテクスチャで構築
		MMM_TextureBox lbox = new MMM_TextureBox("Crafter_Steve");
		lbox.fileName = "";

		lbox.addTexture(0x0c, "/assets/minecraft/textures/entity/steve.png");
		if (armorFilenamePrefix != null && armorFilenamePrefix.length > 0) {
			for (String ls: armorFilenamePrefix) {
				Map < Integer, ResourceLocation > lmap = new HashMap < Integer, ResourceLocation > ();
				lmap.put(tx_armor1, new ResourceLocation(
				(new StringBuilder()).append("textures/models/armor/").append(ls).append("_layer_2.png").toString()));
				lmap.put(tx_armor2, new ResourceLocation(
				(new StringBuilder()).append("textures/models/armor/").append(ls).append("_layer_1.png").toString()));
				lbox.armors.put(ls, lmap);
			}
		}

		textures.add(lbox);
	}


	public boolean loadTextureServer() {
		// サーバー用テクスチャ名称のインデクッスローダー
		// 先ずは手持ちのテクスチャパックを追加する。
		textureServer.clear();
		for (MMM_TextureBox lbox: getTextureList()) {
			textureServer.add(new MMM_TextureBoxServer(lbox));
		}
		// ファイルからロード
		File lfile = MinecraftServer.getServer().getFile(nameTextureIndex);
		if (lfile.exists() && lfile.isFile()) {
			try {
				FileReader fr = new FileReader(lfile);
				BufferedReader br = new BufferedReader(fr);
				String ls;

				while ((ls = br.readLine()) != null) {
					String lt[] = ls.split(",");
					if (lt.length >= 7) {
						// ファイルのほうが優先
						MMM_TextureBoxServer lbox = getTextureBoxServer(lt[6]);
						if (lbox == null) {
							lbox = new MMM_TextureBoxServer();
							textureServer.add(lbox);
						}
						lbox.contractColor = MMM_Helper.getHexToInt(lt[0]);
						lbox.wildColor = MMM_Helper.getHexToInt(lt[1]);
						lbox.setModelSize(
						Float.valueOf(lt[2]),
						Float.valueOf(lt[3]),
						Float.valueOf(lt[4]),
						Float.valueOf(lt[5]));
						lbox.textureName = lt[6];
					}
				}

				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			LMM_LittleMaidMobX.Debug("Loaded ServerBoxList.(%d)", textureServer.size());
			for (int li = 0; li < textureServer.size(); li++) {
				MMM_TextureBoxServer lbox = textureServer.get(li);
				LMM_LittleMaidMobX.Debug("%04d=%s:%04x:%04x", li, lbox.textureName, lbox.contractColor, lbox.wildColor);
			}
			return true;
		} else {}

		return false;
	}

	public void saveTextureServer() {
		// サーバー用テクスチャ名称のインデクッスセーバー
		File lfile = MinecraftServer.getServer().getFile(nameTextureIndex);
		try {
			FileWriter fw = new FileWriter(lfile);
			BufferedWriter bw = new BufferedWriter(fw);

			for (MMM_TextureBoxServer lbox: textureServer) {
				bw.write(String.format("%04x,%04x,%f,%f,%f,%f,%s",
				lbox.getContractColorBits(),
				lbox.getWildColorBits(),
				lbox.getHeight(null),
				lbox.getWidth(null),
				lbox.getYOffset(null),
				lbox.getMountedYOffset(null),
				lbox.textureName));
				bw.newLine();
			}

			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * テクスチャインデックスを構築。
	 */
	public void initTextureList(boolean pFlag) {
		LMM_LittleMaidMobX.Debug("Clear TextureBoxServer.");
		textureServerIndex.clear();
		textureServer.clear();
		if (pFlag) {
			int li = 0;
			for (MMM_TextureBox lbc: getTextureList()) {
				MMM_TextureBoxServer lbs = new MMM_TextureBoxServer(lbc);
				textureServer.add(lbs);
				textureServerIndex.put(lbc, li++);
			}
			LMM_LittleMaidMobX.Debug("Rebuild TextureBoxServer(%d).", textureServer.size());
		}
	}

	private void addModelClass(Class lclass, String pn, String cn) {
		try {
			if (!(ModelMultiBase.class).isAssignableFrom(lclass) || Modifier.isAbstract(lclass.getModifiers())) {
				LMM_LittleMaidMobX.Debug("getModelClass-fail.");
				return;
			}
			ModelMultiBase mlm[] = new ModelMultiBase[3];
			Constructor < ModelMultiBase > cm = lclass.getConstructor(float.class);
			mlm[0] = cm.newInstance(0.0F);
			float[] lsize = mlm[0].getArmorModelsSize();
			mlm[1] = cm.newInstance(lsize[0]);
			mlm[2] = cm.newInstance(lsize[1]);
			modelMap.put(pn, mlm);
			LMM_LittleMaidMobX.Debug("getModelClass-%s:%s", pn, cn);
		} catch (Exception exception) {
			LMM_LittleMaidMobX.Debug("getModelClass-Exception-%s:%s", pn, cn);
			// TODO ★一時的に無効化
			exception.printStackTrace();
		} catch (Error error) {
			LMM_LittleMaidMobX.Debug("getModelClass-Error-%s:%s", pn, cn);
			error.printStackTrace();
		}
	}
	protected void addTexture(String path) {

		String packageName3 = path.substring(0, path.lastIndexOf("/"));
		String packageName = packageName3.substring(packageName3.lastIndexOf("/")+1);
		String fn = path.substring(path.lastIndexOf("/")+1);
		int lindex = getIndex(fn);
		if (lindex > -1) {
			if (lindex == tx_oldarmor1) {
				lindex = tx_armor1;
			}
			if (lindex == tx_oldarmor2) {
				lindex = tx_armor2;
			}
			if (lindex == tx_oldwild) {
				lindex = tx_wild + 12;
			}
			MMM_TextureBox lts = getTextureBox(packageName);
			if (lts == null) {
				lts = new MMM_TextureBox(packageName);
				textures.add(lts);
				LMM_LittleMaidMobX.Debug("getTextureName-append-texturePack-%s", packageName);
			}
			lts.addTexture(lindex, path);
		} else {
			System.err.println("Texture does not have index assigned: "+path);
		}
	}


	protected int getIndex(String name) {
		// 名前からインデックスを取り出す
		for (int i = 0; i < defNames.length; i++) {
			if (name.endsWith(defNames[i])) {
				return i;
			}
		}

		Pattern p = Pattern.compile("_([0-9a-f]+).png");
		Matcher m = p.matcher(name);
		if (m.find()) {
			return Integer.decode("0x" + m.group(1));
		}

		return -1;
	}

	public MMM_TextureBox getNextPackege(MMM_TextureBox pNowBox, int pColor) {
		// 次のテクスチャパッケージの名前を返す
		boolean f = false;
		MMM_TextureBox lreturn = null;
		for (MMM_TextureBox ltb: getTextureList()) {
			if (ltb.hasColor(pColor)) {
				if (f) {
					return ltb;
				}
				if (lreturn == null) {
					lreturn = ltb;
				}
			}
			if (ltb == pNowBox) {
				f = true;
			}
		}
		return lreturn == null ? null : lreturn;
	}

	public MMM_TextureBox getPrevPackege(MMM_TextureBox pNowBox, int pColor) {
		// 前のテクスチャパッケージの名前を返す
		MMM_TextureBox lreturn = null;
		for (MMM_TextureBox ltb: getTextureList()) {
			if (ltb == pNowBox) {
				if (lreturn != null) {
					break;
				}
			}
			if (ltb.hasColor(pColor)) {
				lreturn = ltb;
			}
		}
		return lreturn == null ? null : lreturn;
	}

	/**
	 * ローカルで読み込まれているテクスチャパックの数。
	 */
	public int getTextureCount() {
		return getTextureList().size();
	}

	public MMM_TextureBox getNextArmorPackege(MMM_TextureBox pNowBox) {
		// 次のテクスチャパッケージの名前を返す
		boolean f = false;
		MMM_TextureBox lreturn = null;
		for (MMM_TextureBox ltb: getTextureList()) {
			if (ltb.hasArmor()) {
				if (f) {
					return ltb;
				}
				if (lreturn == null) {
					lreturn = ltb;
				}
			}
			if (ltb == pNowBox) {
				f = true;
			}
		}
		return lreturn;
	}

	public MMM_TextureBox getPrevArmorPackege(MMM_TextureBox pNowBox) {
		// 前のテクスチャパッケージの名前を返す
		MMM_TextureBox lreturn = null;
		for (MMM_TextureBox ltb: getTextureList()) {
			if (ltb == pNowBox) {
				if (lreturn != null) {
					break;
				}
			}
			if (ltb.hasArmor()) {
				lreturn = ltb;
			}
		}
		return lreturn;
	}

	public String getRandomTextureString(Random pRand) {
		return getRandomTexture(pRand).textureName;
	}

	public MMM_TextureBoxServer getRandomTexture(Random pRand) {
		if (textureServer.isEmpty()) {
			return null;
		} else {
			// 野生色があるものをリストアップ
			List < MMM_TextureBoxServer > llist = new ArrayList < MMM_TextureBoxServer > ();
			for (MMM_TextureBoxServer lbox: textureServer) {
				if (lbox.getWildColorBits() > 0) {
					llist.add(lbox);
				}
			}
			return llist.get(pRand.nextInt(llist.size()));
		}
	}

	/**
	 * テクスチャパック名に対応するインデックスを返す。
	 * 基本サーバー用。
	 * @param pEntity
	 * @param pPackName
	 * @return
	 */
	public int getIndexTextureBoxServer(ITextureEntity pEntity, String pPackName) {
		for (int li = 0; li < textureServer.size(); li++) {
			if (textureServer.get(li).textureName.equals(pPackName)) {
				return li;
			}
		}
		// 見当たらなかったのでEntityに対応するデフォルトを返す
		//		int li = textureServerIndex.get(getDefaultTexture(pEntity));
		MMM_TextureBox lbox = getDefaultTexture(pEntity);
		if (lbox != null) {
			pPackName = lbox.textureName;
			for (int li = 0; li < textureServer.size(); li++) {
				if (textureServer.get(li).textureName.equals(pPackName)) {
					return li;
				}
			}
		}
		return 0;
	}

	/**
	 * 指定されたテクスチャパックのサーバー側の管理番号を返す。
	 * @param pBox
	 * @return
	 */
	public int getIndexTextureBoxServerIndex(MMM_TextureBox pBox) {
		return textureServerIndex.get(pBox);
	}

	/**
	 * Entityに対応するデフォルトのテクスチャを設定する。
	 */
	public void setDefaultTexture(ITextureEntity pEntity, MMM_TextureBox pBox) {
		setDefaultTexture(pEntity.getClass(), pBox);
	}
	public void setDefaultTexture(Class pEntityClass, MMM_TextureBox pBox) {
		defaultTextures.put(pEntityClass, pBox);
		LMM_LittleMaidMobX.Debug("appendDefaultTexture:%s(%s)",
		pEntityClass.getSimpleName(), pBox == null ? "NULL" : pBox.textureName);
	}

	/**
	 * Entityに対応するデフォルトモデルを返す。
	 */
	public MMM_TextureBox getDefaultTexture(ITextureEntity pEntity) {
		return getDefaultTexture(pEntity.getClass());
	}
	public MMM_TextureBox getDefaultTexture(Class pEntityClass) {
		if (defaultTextures.containsKey(pEntityClass)) {
			return defaultTextures.get(pEntityClass);
		} else {
			Class lsuper = pEntityClass.getSuperclass();
			if (lsuper != null) {
				MMM_TextureBox lbox = getDefaultTexture(lsuper);
				if (lbox != null) {
					setDefaultTexture(pEntityClass, lbox);
				}
				return lbox;
			}
			return null;
		}
	}



	/*
	 * サーバークライアント間でのテクスチャ管理関数群
	 */

	// ネットワーク越しにテクスチャインデクスを得る際に使う
	protected int getRequestStringIndex(String pVal) {
		int lblank = -1;
		for (int li = 0; li < requestString.length; li++) {
			if (requestString[li] == null) {
				lblank = li;
				requestStringCounter[li] = 0;
			} else if (requestString[li].equals(pVal)) {
				// 既に要求中
				return -2;
			}
		}
		if (lblank >= 0) {
			requestString[lblank] = pVal;
		} else {
			LMM_LittleMaidMobX.Debug("requestString Overflow!");
		}
		return lblank;
	}

	protected String getRequestString(int pIndex) {
		String ls = requestString[pIndex];
		requestString[pIndex] = null;
		return ls;
	}

	protected int getRequestIndex(int pTextureServerBoxIndex) {
		int lblank = -1;
		for (int li = 0; li < requestIndex.length; li++) {
			if (requestIndex[li] == -1) {
				lblank = li;
				requestIndexCounter[li] = 0;
			} else if (requestIndex[li] == pTextureServerBoxIndex) {
				// 既に要求中
				return -2;
			}
		}
		if (lblank >= 0) {
			requestIndex[lblank] = pTextureServerBoxIndex;
		} else {
			LMM_LittleMaidMobX.Debug("requestIndex Overflow!");
		}
		return lblank;
	}

	protected boolean clearRequestIndex(int pTextureServerBoxIndex) {
		for (int li = 0; li < requestIndex.length; li++) {
			if (requestIndex[li] == pTextureServerBoxIndex) {
				// 要求中だったので消す。
				requestIndex[li] = -1;
				return true;
			}
		}
		return false;
	}


	public MMM_TextureBox getTextureBoxServerIndex(int pIndex) {
		for (Entry < MMM_TextureBox, Integer > le: textureServerIndex.entrySet()) {
			if (le.getValue() == pIndex) {
				return le.getKey();
			}
		}
		return null;
	}


	/**
	 * テクスチャパックを設定するため、サーバーへ情報を送る。
	 * @param pEntity
	 * @param pBox
	 */
	public void postSetTexturePack(ITextureEntity pEntity, int pColor, MMM_TextureBoxBase[] pBox) {
		// Client
		if (!(pEntity instanceof Entity)) return;
		// テクスチャパックを設定するため、サーバーへ情報を送る。
		int lindex[] = new int[pBox.length];
		boolean lflag = true;

		// PackeNameからサーバー側のテクスチャインデックスを獲得する。
		for (int li = 0; li < pBox.length; li++) {
			lindex[li] = checkTextureBoxServer((MMM_TextureBox) pBox[li]);
			if (lindex[li] < 0) {
				lflag = false;
			}
		}

		if (lflag) {
			// すべての名称からインデックスを取り出せた場合、サーバーへポストする。
			sendToServerSetTexturePackIndex(pEntity, pColor, lindex);
		} else {
			// ローカルに設定値がない場合、バッファにジョブをスタックし終了。
			Object lo[] = new Object[1 + pBox.length];
			lo[0] = pColor;
			for (int li = 0; li < pBox.length; li++) {
				lo[li + 1] = pBox[li];
			}
			stackSetTexturePack.put(pEntity, lo);
		}
	}

	/**
	 * TextureBoxにサーバー識別番号が付与されているかを確認し、なければ問い合わせを行う。
	 * @param pBox
	 * @return
	 */
	public int checkTextureBoxServer(MMM_TextureBox pBox) {
		// Client
		if (textureServerIndex.containsKey(pBox)) {
			return textureServerIndex.get(pBox);
		} else {
			int ll = getRequestStringIndex(pBox.textureName);
			if (ll > -1) {
				sendToServerGetTextureIndex(ll, pBox);
				return -1;
			} else {
				return ll;
			}
		}
	}

	protected void sendToServerSetTexturePackIndex(ITextureEntity pEntity, int pColor, int[] pIndex) {
		// Client
		// サーバー側へテクスチャパックのインデックスが変更されたことを通知する。
		if (pEntity instanceof Entity) {
			byte ldata[] = new byte[6 + pIndex.length * 2];
			ldata[0] = MMM_Statics.Server_SetTexturePackIndex;
			MMM_Helper.setInt(ldata, 1, ((Entity) pEntity).getEntityId());
			ldata[5] = (byte) pColor;
			int li = 6;
			for (int ll: pIndex) {
				MMM_Helper.setShort(ldata, li, ll);
				li += 2;
			}
			Client.sendToServer(ldata);
		}
	}

	public void reciveFromClientSetTexturePackIndex(Entity pEntity, byte[] pData) {
		// Server
		if (pEntity instanceof ITextureEntity) {
			// クライアント側からテクスチャパックのインデックスが変更された通知を受け取ったので処理を行う。
			int lcount = (pData.length - 6) / 2;
			if (lcount < 1) return;
			int lindex[] = new int[lcount];

			for (int li = 0; li < lcount; li++) {
				lindex[li] = MMM_Helper.getShort(pData, 6 + li * 2);
			}
			LMM_LittleMaidMobX.Debug("reciveFromClientSetTexturePackIndex: %d, %4x", pData[5], lindex[0]);
			((ITextureEntity) pEntity).setTexturePackIndex(pData[5], lindex);
		}
	}

	protected void sendToServerGetTextureIndex(int pBufIndex, MMM_TextureBox pBox) {
		// Client
		// サーバー側へテクスチャパックの管理番号を問い合わせる。
		// 呼び出し側のクライアントへのみ返す。
		// 返すときはNameは不要、BufIndexのみで識別させる
		byte ldata[] = new byte[22 + pBox.textureName.length()];
		ldata[0] = MMM_Statics.Server_GetTextureIndex;
		ldata[1] = (byte) pBufIndex;
		MMM_Helper.setShort(ldata, 2, pBox.getContractColorBits());
		MMM_Helper.setShort(ldata, 4, pBox.getWildColorBits());
		MMM_Helper.setFloat(ldata, 6, pBox.getHeight(null));
		MMM_Helper.setFloat(ldata, 10, pBox.getWidth(null));
		MMM_Helper.setFloat(ldata, 14, pBox.getYOffset(null));
		MMM_Helper.setFloat(ldata, 18, pBox.getMountedYOffset(null));
		MMM_Helper.setStr(ldata, 22, pBox.textureName);
		Client.sendToServer(ldata);
		LMM_LittleMaidMobX.Debug("Server_GetTextureIndex: %s", pBox.textureName);
	}

	public void reciveFromClientGetTexturePackIndex(EntityPlayer player, byte[] pData) {
		// Server
		// クライアント側へテクスチャパックの管理番号を返す。
		String lpackname = MMM_Helper.getStr(pData, 22);
		MMM_TextureBoxServer lboxsrv = getTextureBoxServer(lpackname);
		int li;
		if (lboxsrv == null) {
			li = textureServer.size();
			lboxsrv = new MMM_TextureBoxServer();
			textureServer.add(lboxsrv);
		} else {
			li = textureServer.indexOf(lboxsrv);
		}
		lboxsrv.setValue(pData);

		byte ldata[] = new byte[4];
		ldata[0] = MMM_Statics.Client_SetTextureIndex;
		ldata[1] = pData[1];
		MMM_Helper.setShort(ldata, 2, li);
		LMM_LittleMaidMobX.Debug("reciveFromClientGetTexturePackIndex: %s, %04x", lpackname, li);
		LMM_LittleMaidMobX.sendToClient(player, ldata);
	}

	public void reciveFormServerSetTexturePackIndex(byte[] pData) {
		// Client
		// サーバー側からテクスチャパックのインデックスを受け取ったので値を登録する。
		MMM_TextureBox lbox = getTextureBox(getRequestString(pData[1]));
		textureServerIndex.put(lbox, (int) MMM_Helper.getShort(pData, 2));
		LMM_LittleMaidMobX.Debug("reciveFormServerSetTexturePackIndex: %s, %04x", lbox.textureName, (int) MMM_Helper.getShort(pData, 2));

		// スタックされたジョブから処理可能な物があれば実行する。
		Map < ITextureEntity, Object[] > lmap = new HashMap < ITextureEntity, Object[] > (stackSetTexturePack);
		stackSetTexturePack.clear();
		for (Entry < ITextureEntity, Object[] > le: lmap.entrySet()) {
			Object lo[] = le.getValue();
			MMM_TextureBox ls[] = new MMM_TextureBox[le.getValue().length - 1];
			int lc = (Integer) lo[0];
			for (int li = 1; li < lo.length; li++) {
				ls[li - 1] = (MMM_TextureBox) lo[li];
			}
			postSetTexturePack(le.getKey(), lc, ls);
		}
	}



	/**
	 * サーバーから設定されたテクスチャインデックスからテクスチャパックを取得する。
	 * @param pEntity
	 * @param pIndex
	 */
	public void postGetTexturePack(ITextureEntity pEntity, int[] pIndex) {
		// Client
		// クライアント側で指定されたインデックスに対してテクスチャパックの名称を返し設定させる
		MMM_TextureBox lbox[] = new MMM_TextureBox[pIndex.length];
		boolean lflag = true;

		// ローカルインデックスに名称が登録されていなければサーバーへ問い合わせる。
		for (int li = 0; li < pIndex.length; li++) {
			lbox[li] = getTextureBoxServerIndex(pIndex[li]);
			if (lbox[li] == null) {
				if (getRequestIndex(pIndex[li]) > -1) {
					sendToServerGetTexturePackName(pIndex[li]);
				}
				lflag = false;
			}
		}

		if (lflag) {
			// 全ての値が取れる場合はEntityへ値を設定する。
			pEntity.setTexturePackName(lbox);
		} else {
			// 不明値がある場合は処理をスタックする。
			stackGetTexturePack.put(pEntity, pIndex);
		}
	}

	protected void sendToServerGetTexturePackName(int pIndex) {
		// Client
		// サーバー側へテクスチャパックの名称を問い合わせる
		if (pIndex < 0) {
			LMM_LittleMaidMobX.Debug("request range out.");
			return;
		}
		byte ldata[] = new byte[3];
		ldata[0] = MMM_Statics.Server_GetTexturePackName;
		MMM_Helper.setShort(ldata, 1, pIndex);
		Client.sendToServer(ldata);
	}

	public void reciveFromClientGetTexturePackName(EntityPlayer player, byte[] pData) {
		// Server
		// クライアントからテクスチャパックの名称が問い合わせられた。
		int lindex = MMM_Helper.getShort(pData, 1);
		MMM_TextureBoxServer lboxserver = getTextureBoxServer(lindex);

		// Clientへ管理番号に登録されているテクスチャ名称をポストする
		byte ldata[] = new byte[23 + lboxserver.textureName.length()];
		ldata[0] = MMM_Statics.Client_SetTexturePackName;
		MMM_Helper.setShort(ldata, 1, lindex);
		MMM_Helper.setShort(ldata, 3, lboxserver.getContractColorBits());
		MMM_Helper.setShort(ldata, 5, lboxserver.getWildColorBits());
		MMM_Helper.setFloat(ldata, 7, lboxserver.getHeight(null));
		MMM_Helper.setFloat(ldata, 11, lboxserver.getWidth(null));
		MMM_Helper.setFloat(ldata, 15, lboxserver.getYOffset(null));
		MMM_Helper.setFloat(ldata, 19, lboxserver.getMountedYOffset(null));
		MMM_Helper.setStr(ldata, 23, lboxserver.textureName);
		LMM_LittleMaidMobX.sendToClient(player, ldata);
		LMM_LittleMaidMobX.Debug("SetTexturePackName:%04x - %s", lindex, lboxserver.textureName);
	}

	protected void reciveFromServerSetTexturePackName(byte[] pData) {
		// Client
		// サーバーからインデックスに対する名称の設定があった。
		String lpackname = MMM_Helper.getStr(pData, 23);
		MMM_TextureBox lbox = getTextureBox(lpackname);
		if (lbox == null) {
			// ローカルには存在しないテクスチャパック
			// TODO:この辺要修正
			lbox = getTextureBox("default_Orign").duplicate();
			lbox.textureName = lpackname;
			//			lbox = new MMM_TextureBox(lpackname, null);
			lbox.setModelSize(
			MMM_Helper.getFloat(pData, 7),
			MMM_Helper.getFloat(pData, 11),
			MMM_Helper.getFloat(pData, 15),
			MMM_Helper.getFloat(pData, 19));
			textures.add(lbox);
		}
		int lindex = MMM_Helper.getShort(pData, 1);
		textureServerIndex.put(lbox, lindex);
		clearRequestIndex(lindex);

		// 処理可能な物がスタックされている場合は処理を行う。
		Map < ITextureEntity, int[] > lmap = new HashMap < ITextureEntity, int[] > (stackGetTexturePack);
		stackGetTexturePack.clear();
		for (Entry < ITextureEntity, int[] > le: lmap.entrySet()) {
			postGetTexturePack(le.getKey(), le.getValue());
		}
	}

	/**
	 * Request系の値を一定カウントで消去
	 */
	protected void onUpdate() {
		for (int li = 0; li < requestString.length; li++) {
			// 約30秒で解放
			if (requestString[li] != null && requestStringCounter[li]++ > 600) {
				requestString[li] = null;
				requestStringCounter[li] = 0;
			}
			if (requestIndex[li] != -1 && requestIndexCounter[li]++ > 600) {
				requestIndex[li] = -1;
				requestIndexCounter[li] = 0;
			}
		}
	}

}

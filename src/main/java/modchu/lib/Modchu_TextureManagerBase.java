package modchu.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import littleMaidMobX.LittleMaidMobX;
import modchu.lib.characteristic.Modchu_AS;
import modchu.lib.characteristic.Modchu_TextureBoxBase;
import modchu.lib.characteristic.Modchu_TextureBoxServer;
import modchu.model.multimodel.MultiModel_Angel;
import modchu.model.multimodel.MultiModel_Aokise;
import modchu.model.multimodel.MultiModel_Beverly2;
import modchu.model.multimodel.MultiModel_Beverly3;
import modchu.model.multimodel.MultiModel_Beverly4;
import modchu.model.multimodel.MultiModel_Beverly5;
import modchu.model.multimodel.MultiModel_Beverly6;
import modchu.model.multimodel.MultiModel_ChibiNeko;
import modchu.model.multimodel.MultiModel_DogAngel;
import modchu.model.multimodel.MultiModel_DogAngel2;
import modchu.model.multimodel.MultiModel_DressYukari;
import modchu.model.multimodel.MultiModel_Elsa;
import modchu.model.multimodel.MultiModel_Elsa2;
import modchu.model.multimodel.MultiModel_Elsa3;
import modchu.model.multimodel.MultiModel_Elsa4;
import modchu.model.multimodel.MultiModel_Elsie;
import modchu.model.multimodel.MultiModel_Evelyn3;
import modchu.model.multimodel.MultiModel_ExtraArms;
import modchu.model.multimodel.MultiModel_Kagami;
import modchu.model.multimodel.MultiModel_Kelo;
import modchu.model.multimodel.MultiModel_MS;
import modchu.model.multimodel.MultiModel_MS1;
import modchu.model.multimodel.MultiModel_Mabel;
import modchu.model.multimodel.MultiModel_NM;
import modchu.model.multimodel.MultiModel_NM1;
import modchu.model.multimodel.MultiModel_Pawapro;
import modchu.model.multimodel.MultiModel_Petit;
import modchu.model.multimodel.MultiModel_QB;
import modchu.model.multimodel.MultiModel_SA;
import modchu.model.multimodel.MultiModel_Shion;
import modchu.model.multimodel.MultiModel_Tenshi;
import modchu.model.multimodel.MultiModel_Utsuho;
import modchu.model.multimodel.MultiModel_VUD1;
import modchu.model.multimodel.MultiModel_Yomu;
import modchu.model.multimodel.MultiModel_Yukari;
import modchu.model.multimodel.MultiModel_Yukkuri;
import modchu.model.multimodel.MultiModel_bgs;
import modchu.model.multimodel.MultiModel_brs;
import modchu.model.multimodel.MultiModel_ch;
import modchu.model.multimodel.MultiModel_chrno;
import modchu.model.multimodel.MultiModel_chu;
import modchu.model.multimodel.MultiModel_dm;
import modchu.model.multimodel.MultiModel_long;
import modchu.model.multimodel.MultiModel_mahoro;
import modchu.model.multimodel.MultiModel_naz;
import modchu.model.multimodel.MultiModel_st;
import modchu.model.multimodel.MultiModel_suika;
import modchu.model.multimodel.MultiModel_taremimi;
import modchu.model.multimodel.MultiModel_tareusa;
import modchu.model.multimodel.MultiModel_twinD;
import modchu.model.multimodel.MultiModel_usagi;
import modchu.model.multimodel.base.MultiModelBaseBiped;
import net.minecraft.util.ResourceLocation;

public class Modchu_TextureManagerBase {

	/**
	 * 継承クラスで置き換えることを考慮。
	 */
	public static Modchu_TextureManagerBase instance = new Modchu_TextureManagerBase();

	public static String nameTextureIndex = "config/mod_ModchuModel_textureList.cfg";
	public static String defaultModelName = "Orign";

	public static final int tx_oldwild		= 0x10; //16;
	public static final int tx_oldarmor1	= 0x11; //17;
	public static final int tx_oldarmor2	= 0x12; //18;
	public static final int tx_oldeye		= 0x13; //19;
	public static final int tx_gui			= 0x20; //32;
	public static final int tx_wild			= 0x30; //48;
	public static final int tx_armor1		= 0x40; //64;
	public static final int tx_armor2		= 0x50; //80;
	public static final int tx_eye			= 0x60; //96;
	public static final int tx_eyecontract	= 0x60; //96;
	public static final int tx_eyewild		= 0x70; //112;
	public static final int tx_armor1light	= 0x80; //128;
	public static final int tx_armor2light	= 0x90; //144;
	public static String[] armorFilenamePrefix;
	/**
	 * 旧タイプのファイル名
	 */
	public static String defNames[] = {
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
	public Map<String, MultiModelBaseBiped[]> modelMap = new TreeMap<String, MultiModelBaseBiped[]>();
	/**
	 * ローカルで保持しているテクスチャパック
	 */
	public List<Modchu_TextureBoxBase> textures = new ArrayList<Modchu_TextureBoxBase>();
	/**
	 * サーバー側での管理番号を識別するのに使う、クライアント用。
	 */
	public Map<Modchu_TextureBoxBase, Integer> textureServerIndex = new HashMap<Modchu_TextureBoxBase, Integer>();
	/**
	 * サーバー・クライアント間でテクスチャパックの名称リストの同期を取るのに使う、サーバー用。
	 */
	public List<Modchu_TextureBoxServer> textureServer = new ArrayList<Modchu_TextureBoxServer>();
	/**
	 * Entity毎にデフォルトテクスチャを参照。
	 * 構築方法はEntityListを参照のこと。
	 */
	public Map<Class, Modchu_TextureBoxBase> defaultTextures = new HashMap<Class, Modchu_TextureBoxBase>();

	/**
	 * クライアント側で使う
	 */
	public String[] requestString = new String[] {
		null, null, null, null, null, null, null, null,
		null, null, null, null, null, null, null, null
	};
	public int[] requestStringCounter = new int[] {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	public int[] requestIndex = new int[] {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1
	};
	public int[] requestIndexCounter = new int[] {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};

	public List<Object[]> searchPrefix = new ArrayList();

	private LinkedList<String> searchSettledList = new LinkedList();



	public void init() {
	}

	public Modchu_TextureBoxBase getTextureBox(String pName) {
		//Modchu_Debug.mDebug("Modchu_TextureManagerBase getTextureBox pName="+pName);
		if (pName != null
				&& !pName.isEmpty()); else return null;
		for (Modchu_TextureBoxBase ltb : textures) {
			if (ltb.textureName != null
					&& ltb.textureName.equals(pName)) {
				//Modchu_Debug.mDebug("Modchu_TextureManagerBase getTextureBox ltb ok.");
				return ltb;
			}
		}
		return null;
	}

	public void getArmorPrefix() {
		// アーマーファイルのプリフィックスを獲得
		armorFilenamePrefix = Modchu_AS.getStringArray(Modchu_AS.renderBipedBipedArmorFilenamePrefix);
	}

	public boolean loadTextures() {
		Modchu_Debug.tDebug("loadTexturePacks.");
		// アーマーのファイル名を識別するための文字列を獲得する
		if (!LittleMaidMobX.isServer) {
			getArmorPrefix();
		}

//		// ファイルを解析してテクスチャを追加
//		// jar内のテクスチャを追加
//		Modchu_Debug.tDebug("Modchu_TextureManagerBase loadTextures jar load.");
//		if (Modchu_FileManager.minecraftJar == null) {
//			Modchu_Debug.tDebug("getTexture-append-jar-file not founded.");
//		} else {
//			for (Object[] lss : searchPrefix) {
//				String[] s1 = (String[]) lss[0];
//				String[] s2 = (String[]) lss[1];
//				String[] s3 = (String[]) lss[2];
//				for (String s4 : s1) {
//					for (String s5 : s2) {
//						for (String s6 : s3) {
//							String[] lst = new String[]{ s4, s5, s6 };
//							Modchu_Debug.tDebug("getTexture minecraftJar [%s] [%s] [%s]", s4, s5, s6);
//							addTexturesJar(Modchu_FileManager.minecraftJar, lst);
//						}
//					}
//				}
//			}
//		}
//		Modchu_Debug.tDebug("Modchu_TextureManagerBase loadTextures jar load end.");
//		// jar内のテクスチャを追加
//		if (Modchu_FileManager.minecraftJar == null) Modchu_Debug.tDebug("getTexture-append-jar-file not founded.");
//		Modchu_Debug.tDebug("Modchu_TextureManagerBase loadTextures mods load.");
//		for (Object[] lss : searchPrefix) {
//			String[] s1 = (String[]) lss[0];
//			String[] s2 = (String[]) lss[1];
//			String[] s3 = (String[]) lss[2];
//			// mods
//			for (String fileName : s1) {
//				for (File lf : Modchu_FileManager.getFileList(fileName)) {
//					for (String s4 : s1) {
//						for (String s5 : s2) {
//							for (String s6 : s3) {
//								String[] lst = new String[]{ s4, s5, s6 };
//								Modchu_Debug.tDebug("getTexture [%s] [%s] [%s]", s4, s5, s6);
//								boolean lflag;
//								//Modchu_Debug.mDebug("Modchu_TextureManagerBase loadTextures lf="+lf);
//								if (lf.isDirectory()) {
//									// ディレクトリ
//									lflag = addTexturesDir(lf, lst);
//								} else {
//									// zip
//									//Modchu_Debug.mDebug("Modchu_TextureManagerBase loadTextures zip");
//									lflag = addTexturesZip(lf, lst);
//								}
//								Modchu_Debug.tDebug("getTexture-append-%s-%s.", lf.getName(), lflag ? "done" : "fail");
//							}
//						}
//					}
//				}
//			}
//		}
//		addModelClass(MultiModel_Angel.class, "Angel");
//		addModelClass(MultiModel_Aokise.class, "Aokise");
//		addModelClass(MultiModel_Beverly2.class, "Beverly2");
//		addModelClass(MultiModel_Beverly3.class, "Beverly3");
//		addModelClass(MultiModel_Beverly4.class, "Beverly4");
//		addModelClass(MultiModel_Beverly5.class, "Beverly5");
//		addModelClass(MultiModel_Beverly6.class, "Beverly6");
//		addModelClass(MultiModel_bgs.class, "bgs");
//		addModelClass(MultiModel_brs.class, "brs");
//		addModelClass(MultiModel_ch.class, "ch");
//		addModelClass(MultiModel_ChibiNeko.class, "ChibiNeko");
//		addModelClass(MultiModel_chrno.class, "chrno");
//		addModelClass(MultiModel_chu.class, "chu");
//		addModelClass(MultiModel_dm.class, "dm");
//		addModelClass(MultiModel_DogAngel.class, "DogAngel");
//		addModelClass(MultiModel_DogAngel2.class, "DogAngel2");
//		addModelClass(MultiModel_DressYukari.class, "DressYukari");
//		addModelClass(MultiModel_Elsa.class, "Elsa");
//		addModelClass(MultiModel_Elsa2.class, "Elsa2");
//		addModelClass(MultiModel_Elsa3.class, "Elsa3");
//		addModelClass(MultiModel_Elsa4.class, "Elsa4");
		addModelClass(MultiModel_Elsie.class, "Elsie");
//		addModelClass(MultiModel_Evelyn3.class, "Evelyn3");
//		addModelClass(MultiModel_ExtraArms.class, "ExtraArms");
//		addModelClass(MultiModel_Kagami.class, "Kagami");
//		addModelClass(MultiModel_Kelo.class, "Kelo");
//		addModelClass(MultiModel_long.class, "long");
//		addModelClass(MultiModel_Mabel.class, "Mabel");
//		addModelClass(MultiModel_mahoro.class, "mahoro");
//		addModelClass(MultiModel_MS.class, "MS");
//		addModelClass(MultiModel_MS1.class, "MS1");
//		addModelClass(MultiModel_naz.class, "naz");
//		addModelClass(MultiModel_NM.class, "NM");
//		addModelClass(MultiModel_NM1.class, "NM1");
//		addModelClass(MultiModel_Pawapro.class, "Pawapro");
//		addModelClass(MultiModel_Petit.class, "Petit");
//		addModelClass(MultiModel_QB.class, "QB");
//		addModelClass(MultiModel_SA.class, "SA");
//		addModelClass(MultiModel_Shion.class, "Shion");
//		addModelClass(MultiModel_st.class, "st");
//		addModelClass(MultiModel_suika.class, "suika");
//		addModelClass(MultiModel_taremimi.class, "taremimi");
//		addModelClass(MultiModel_tareusa.class, "tareusa");
//		addModelClass(MultiModel_Tenshi.class, "Tenshi");
//		addModelClass(MultiModel_twinD.class, "twinD");
//		addModelClass(MultiModel_usagi.class, "usagi");
//		addModelClass(MultiModel_Utsuho.class, "Utsuho");
//		addModelClass(MultiModel_VUD1.class, "VUD1");
//		addModelClass(MultiModel_Yomu.class, "Yomu");
//		addModelClass(MultiModel_Yukari.class, "Yukari");
//		addModelClass(MultiModel_Yukkuri.class, "Yukkuri");


//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/littlemaidinventory_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/mob_littlemaid_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/Stabilizer_MagicHat.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ALTERNATIVE/testx64.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Ar_NM/littlemaid_Ar_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Ar_NM/littlemaid_Ar_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b14color_Beverly5/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15color_Beverly6/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b15under_Beverly6/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/b2color_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b2color_Beverly2/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/diamond_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/diamond_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/gold_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/gold_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/iron_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b3color_Beverly4/iron_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/diamond_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/diamond_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/gold_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/gold_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/iron_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4color_Beverly3/iron_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/default_42.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/default_46.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/b4under_Beverly4/default_49.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Batter_Pawapro/Batter_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Batter_Pawapro/Batter_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Catcher_Pawapro/Catcher_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Catcher_Pawapro/Catcher_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_10.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/Kagami_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_Kagami/kagami_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_10.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_long/long_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_10.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CF_twinD/twinD_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/Chu-CV_10.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_45.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_47.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_49.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_55.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_57.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/chu_chu/default_59.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Cirno_chrno/chrno_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/CV_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_45.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_47.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_49.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_55.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_57.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ColorVariation_chu/default_59.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/Angel_3c1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/diamond_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/diamond_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/gold_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/gold_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/iron_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/iron_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/leather_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/leather_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Angel/littlemaidinventory_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0d2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_32.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_33.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_37.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_38.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_3b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/ChibiNeko_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/defaul_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/diamond_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/diamond_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/gold_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/gold_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/iron_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/iron_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/leather_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/leather_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_ChibiNeko/littlemaidinventory_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_32.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_33.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_37.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_38.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_3b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel/DogAngel_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_32.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_33.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_37.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_38.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_3b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DogAngel2/DogAngel_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DressYukari/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DressYukari/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_DressYukari/yukari_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Pawapro/Pawapro_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_taremimi/TareWankoCV_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/diamond_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/diamond_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/gold_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/gold_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/iron_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/iron_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/leather_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/leather_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/littlemaidinventory_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Tenshi/Tenshi_3b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_usagi/UsagiCV_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/littlemaidinventory_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Utsuho/Utsuho_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/diamond_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/diamond_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/gold_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/gold_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/iron_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/iron_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/leather_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/leather_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/littlemaidinventory_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/CV_Yomu/Yomu_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default/mob_littlemaid_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/default_Custom/Custom_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e10color_Elsa/e10color_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa2/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11color_Elsa3/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e11under_Elsa3/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e12color_Elsa3/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14color_Elsa4/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e14under_Elsa4/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/contract_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/e20colorQ_Evelyn3/default_50.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/default_40.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/default_50.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_0.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_1.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_2.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_3.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_4.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_5.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_6.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_7.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_8.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_9.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_a.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_b.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_c.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_d.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_e.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/e2color_f.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/gold_40.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/gold_50.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/iron_40.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/iron_50.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/leather_40.png");
		addTexture("/assets/minecraft/textures/entity/littleMaid/e2color_Elsie/leather_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/erasearmor/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/erasearmor/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/ERYI_Aokise/Aokise_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/default_45.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/default_47.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/default_49.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/default_55.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_10.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/hituji_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Hituji/mob_littlemaid.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_pl_Shion/littleMaid_kimono_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/kimono_Shion/littleMaid_kimono_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleForces_ExtraArms/littleMaid_littleForces_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_bgs/bgs_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_bgs/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_bgs/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_brs/brs_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_brs/brs_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_brs/brs_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_ch/ch_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_ch/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_ch/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_dm/deadmaster_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_dm/deadmaster_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_dm/deadmaster_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_MS/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_MS/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_MS/sisters_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMAidMob_MS1/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMAidMob_MS1/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMAidMob_MS1/sisters_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_ai_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_Ar_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_Ar_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_Ar_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_sword_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_S_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM/littlemaid_Yu_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM1/Axe_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM1/Axe_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleMaidMob_NM1/NM1_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_st/default_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_st/default_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/littleMaidMob_st/st_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aki_VUD1/littlemaid_Aki_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aki_VUD1/littlemaid_Aki_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aki_VUD1/littlemaid_Aki_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aki_VUD1/littlemaid_Aki_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aku_VUD1/littlemaid_Aku_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aku_VUD1/littlemaid_Aku_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aku_VUD1/littlemaid_Aku_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Aku_VUD1/littlemaid_Aku_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Fuyu_VUD1/littlemaid_Fuyu_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Fuyu_VUD1/littlemaid_Fuyu_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Fuyu_VUD1/littlemaid_Fuyu_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Fuyu_VUD1/littlemaid_Fuyu_32.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Fuyu_VUD1/littlemaid_Fuyu_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Fuyu_VUD1/littlemaid_Fuyu_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Gumi_VUD1/littlemaid_Gumi_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Gumi_VUD1/littlemaid_Gumi_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Gumi_VUD1/littlemaid_Gumi_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Gumi_VUD1/littlemaid_Gumi_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haku_VUD1/littlemaid_Haku_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haku_VUD1/littlemaid_Haku_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haku_VUD1/littlemaid_Haku_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haku_VUD1/littlemaid_Haku_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haru_VUD1/littlemaid_Haru_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haru_VUD1/littlemaid_Haru_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haru_VUD1/littlemaid_Haru_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haru_VUD1/littlemaid_Haru_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haru_VUD1/littlemaid_Haru_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Haru_VUD1/littlemaid_Haru_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ia_VUD1/littlemaid_Ia_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ia_VUD1/littlemaid_Ia_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ia_VUD1/littlemaid_Ia_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ia_VUD1/littlemaid_Ia_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Kiku_VUD1/littlemaid_Kiku_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Kiku_VUD1/littlemaid_Kiku_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Kiku_VUD1/littlemaid_Kiku_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Kiku_VUD1/littlemaid_Kiku_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Lily_VUD1/littlemaid_Lily_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Lily_VUD1/littlemaid_Lily_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Lily_VUD1/littlemaid_Lily_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Lily_VUD1/littlemaid_Lily_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Luka_VUD1/littlemaid_Luka_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Luka_VUD1/littlemaid_Luka_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Luka_VUD1/littlemaid_Luka_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Luka_VUD1/littlemaid_Luka_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Mako_VUD1/littlemaid_Mako_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Mako_VUD1/littlemaid_Mako_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Mako_VUD1/littlemaid_Mako_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Mako_VUD1/littlemaid_Mako_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Meiko_VUD1/littlemaid_Meiko_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Meiko_VUD1/littlemaid_Meiko_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Meiko_VUD1/littlemaid_Meiko_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Meiko_VUD1/littlemaid_Meiko_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miki_VUD1/littlemaid_Miki_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miki_VUD1/littlemaid_Miki_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miki_VUD1/littlemaid_Miki_36 .png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miki_VUD1/littlemaid_Miki_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miku_VUD1/littlemaid_Miku_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miku_VUD1/littlemaid_Miku_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miku_VUD1/littlemaid_Miku_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Miku_VUD1/littlemaid_Miku_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Momo_VUD1/littlemaid_Momo_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Momo_VUD1/littlemaid_Momo_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Momo_VUD1/littlemaid_Momo_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Momo_VUD1/littlemaid_Momo_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Natsu_VUD1/littlemaid_Natsu_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Natsu_VUD1/littlemaid_Natsu_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Natsu_VUD1/littlemaid_Natsu_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Natsu_VUD1/littlemaid_Natsu_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Neru_VUD1/littlemaid_Neru_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Neru_VUD1/littlemaid_Neru_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Neru_VUD1/littlemaid_Neru_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Neru_VUD1/littlemaid_Neru_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Oto_VUD1/littlemaid_Oto_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Oto_VUD1/littlemaid_Oto_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Oto_VUD1/littlemaid_Oto_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Oto_VUD1/littlemaid_Oto_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rina_VUD1/littlemaid_Rina_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rina_VUD1/littlemaid_Rina_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rina_VUD1/littlemaid_Rina_37.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rina_VUD1/littlemaid_Rina_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rin_VUD1/littlemaid_Rin_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rin_VUD1/littlemaid_Rin_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rin_VUD1/littlemaid_Rin_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Rin_VUD1/littlemaid_Rin_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ruko_VUD1/littlemaid_Ruko_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ruko_VUD1/littlemaid_Ruko_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ruko_VUD1/littlemaid_Ruko_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Ruko_VUD1/littlemaid_Ruko_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Sara_VUD1/littlemaid_Sara_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Sara_VUD1/littlemaid_Sara_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Sara_VUD1/littlemaid_Sara_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Sara_VUD1/littlemaid_Sara_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Tei_VUD1/littlemaid_Tei_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Tei_VUD1/littlemaid_Tei_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Tei_VUD1/littlemaid_Tei_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Tei_VUD1/littlemaid_Tei_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Teto_VUD1/littlemaid_teto_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Teto_VUD1/littlemaid_teto_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Teto_VUD1/littlemaid_Teto_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Teto_VUD1/littlemaid_Teto_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Uta_VUD1/littlemaid_Uta_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Uta_VUD1/littlemaid_Uta_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Uta_VUD1/littlemaid_Uta_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Uta_VUD1/littlemaid_Uta_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari0_VUD1/littlemaid_Yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari0_VUD1/littlemaid_Yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari0_VUD1/littlemaid_Yukari_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari0_VUD1/littlemaid_Yukari_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari1_VUD1/0_SR2/littleMaid_yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari1_VUD1/0_SR2/littleMaid_yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari1_VUD1/littlemaid_Yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari1_VUD1/littlemaid_Yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/Yukari1_VUD1/littlemaid_Yukari_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/ZMiku_VUD1/littlemaid_ZMiku_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/ZMiku_VUD1/littlemaid_ZMiku_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/ZMiku_VUD1/littlemaid_ZMiku_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/LittleVocal_VUD1/ZMiku_VUD1/littlemaid_ZMiku_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/m1color_Mabel/m1color_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Mahoro_mahoro/littleMaid_Mahoro_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Mahoro_mahoro/littleMaid_Mahoro_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Mahoro_mahoro/littleMaid_Mahoro_3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Mahoro_mahoro/littleMaid_Mahoro_b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MaidCV_Petit/PetitCV_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/maiduv.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Manju_Yukkuri/Yukkuri_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MMM_Aug/Aug_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MMM_Aug/Aug_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MMM_Aug/Aug_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MMM_Aug/Aug_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MMM_SR2/EngageOctaver_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/MMM_SR2/EngageOctaver_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/mob_littlemaid.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/moyu_SA/mob_littlemaidSA_f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/naz_naz/naz_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/NetaPetit_Petit/Neta_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid0.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid1.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid2.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid3.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid4.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid5.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid6.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid7.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid8.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid9.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaida.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaidb.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaidc.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaidd.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/neta_chu/mob_littlemaid_w.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_33.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Rana_Kelo/Rana_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/suika_suika/suika_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Sword_NM/littlemaid_sword_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Sword_NM/littlemaid_sword_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Tei_tareusa/Tei_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_20.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/Udonge_usagi/Udonge_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariM_Yukari/littleMaid_yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariM_Yukari/littleMaid_yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariM_Yukari/littleMaid_yukari_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariSM_Yukari/littleMaid_yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariSM_Yukari/littleMaid_yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariSM_Yukari/littleMaid_yukari_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariS_Yukari/littleMaid_yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariS_Yukari/littleMaid_yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariS_Yukari/littleMaid_yukari_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/YukariS_Yukari/littleMaid_yukari_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/Yukari_Yukari/littleMaid_yukari_11.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/Yukari_Yukari/littleMaid_yukari_12.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/Yukari_Yukari/littleMaid_yukari_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/VOICEROID/Yukari_Yukari/littleMaid_yukari_a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_32.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_33.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_37.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_38.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_3b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x16_QB/QB16_3f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/default_40.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/default_50.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_00.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_01.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_02.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_03.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_04.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_05.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_06.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_07.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_08.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_09.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_0a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_0b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_0c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_0d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_0e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_0f.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_30.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_31.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_32.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_33.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_34.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_35.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_36.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_37.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_38.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_39.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_3a.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_3b.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_3c.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_3d.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_3e.png");
//		addTexture("/assets/minecraft/textures/entity/littleMaid/x32_QB/QB_3f.png");
//		addTexture("/assets/minecraft/textures/gui/title/modchulib_title.png");
//		addTexture("/assets/minecraft/textures/gui/title/modchumodel_title.png");

		Modchu_Debug.tDebug("Modchu_TextureManagerBase loadTextures mods load end.");
		// TODO:実験コード
		buildCrafterTexture();
		setModels();

		Modchu_Debug.tDebug("Modchu_TextureManagerBase loadTextures end.");
		return false;
	}

	public void setModels() {
		Modchu_Debug.tDebug("Modchu_TextureManagerBase setModels");
		// テクスチャパッケージにモデルクラスを紐付け
		MultiModelBaseBiped[] ldm = modelMap.get(defaultModelName);
		if (ldm == null && !modelMap.isEmpty()) {
			ldm = (MultiModelBaseBiped[])modelMap.values().toArray()[0];
		}
		if (textures != null
				&& !textures.isEmpty()) {
			for (Modchu_TextureBoxBase ltb : textures) {
				if (ltb.modelName.isEmpty()) {
					ltb.setModels(defaultModelName, null, ldm);
				} else {
					if (modelMap.containsKey(ltb.modelName)) {
						ltb.setModels(ltb.modelName, modelMap.get(ltb.modelName), ldm);
					}
				}
			}
		}
		Modchu_Debug.tDebug("Modchu_TextureManagerBase setModels 1 modelMap.size()="+(modelMap != null ? modelMap.size() : null));
		if (modelMap != null
				&& !modelMap.isEmpty()) {
			for (Entry<String, MultiModelBaseBiped[]> le : modelMap.entrySet()) {
				String key = le.getKey();
				MultiModelBaseBiped[] value = le.getValue();
				String ls = value != null
						&& value.length > 0 ? value[0].getUsingTexture() : null;
				//Modchu_Debug.tDebug("Modchu_TextureManagerBase setModels ls="+ls);
				if (ls != null) {
					if (getTextureBox(ls + "_" + key) == null) {
						Modchu_TextureBoxBase lbox = null;
						if (textures != null
								&& !textures.isEmpty()) {
							for (Modchu_TextureBoxBase ltb : textures) {
								String packegeName = ltb.packegeName;
								if (packegeName != null
										&& ls != null
										&& packegeName.equals(ls)) {
									lbox = ltb;
									break;
								}
							}
						}
						if (lbox != null) {
							lbox = (Modchu_TextureBoxBase) lbox.duplicate();
							lbox.setModels(key, null, value);
							textures.add(lbox);
						}
					}
				}
			}
		}
		Modchu_Debug.tDebug("Loaded Texture Lists.(%d)", textures.size());
		if (textures != null
				&& !textures.isEmpty()) {
			for (Modchu_TextureBoxBase lbox : textures) {
				Modchu_Debug.tDebug("texture: %s(%s) - hasModel:%b", lbox.textureName, lbox.fileName, lbox.models != null);
			}
/*
			for (int li = textures.size() - 1; li >= 0; li--) {
				if (textures.get(li).models == null) {
					textures.remove(li);
				}
			}
			Modchu_Debug.tDebug("Rebuild Texture Lists.(%d)", textures.size());
			for (Modchu_TextureBoxBase lbox : textures) {
				Modchu_Debug.tDebug("texture: %s(%s) - hasModel:%b", lbox.textureName, lbox.fileName, lbox.models != null);
			}
*/
		}
	}

	public void buildCrafterTexture() {
		// TODO:実験コード標準モデルテクスチャで構築
		Modchu_TextureBoxBase lbox = new Modchu_TextureBoxBase("Crafter_Steve");
		lbox.fileName = "";

		lbox.addTexture(0x0c, Modchu_Main.getMinecraftVersion() > 162 ? "textures/entity/steve.png" : "/assets/minecraft/textures/entity/steve.png");
		if (armorFilenamePrefix != null && armorFilenamePrefix.length > 0) {
			for (String ls : armorFilenamePrefix) {
				Map<Integer, Object> lmap = new HashMap();
				lmap.put(tx_armor1, new ResourceLocation((new StringBuilder()).append("textures/models/armor/").append(ls).append("_layer_2.png").toString()));
				lmap.put(tx_armor2, new ResourceLocation((new StringBuilder()).append("textures/models/armor/").append(ls).append("_layer_1.png").toString()));
				lbox.armors.put(ls, lmap);
			}
		}

		textures.add(lbox);
	}


	public void saveTextureServer() {
		// サーバー用テクスチャ名称のインデクッスセーバー
		File lfile = Modchu_AS.getFile(Modchu_AS.minecraftServerGetFile, nameTextureIndex);
		try {
			FileWriter fw = new FileWriter(lfile);
			BufferedWriter bw = new BufferedWriter(fw);

			for (Modchu_TextureBoxServer lbox : textureServer) {
				bw.write(String.format(
						"%04x,%04x,%f,%f,%f,%f,%s",
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
		Modchu_Debug.tDebug("Clear TextureBoxServer.");
		textureServerIndex.clear();
		textureServer.clear();
		if (pFlag) {
			int li = 0;
			for (Modchu_TextureBoxBase lbc : textures) {
				Modchu_TextureBoxServer lbs = new Modchu_TextureBoxServer(lbc);
				textureServer.add(lbs);
				textureServerIndex.put(lbc, li++);
			}
			Modchu_Debug.tDebug("Rebuild TextureBoxServer(%d).", textureServer.size());
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
			Modchu_TextureBoxBase lts = getTextureBox(packageName);
			if (lts == null) {
				lts = new Modchu_TextureBoxBase(packageName);
				textures.add(lts);
				LittleMaidMobX.Debug("getTextureName-append-texturePack-%s", packageName);
			}
			lts.addTexture(lindex, path);
		} else {
			System.err.println("Texture does not have index assigned: "+path);
		}
	}
	public void addModelClass(Class lclass, String name) {
		Modchu_Debug.tDebug("addModelClass name="+name);
		if (modelMap.containsKey(name)) return;

		try {
			if (lclass != null) {
				if (!(MultiModelBaseBiped.class).isAssignableFrom(lclass) || Modifier.isAbstract(lclass.getModifiers())) {
					Modchu_Debug.tDebug("getModelClass-fail.");
					return;
				}
				MultiModelBaseBiped mlm[] = new MultiModelBaseBiped[3];
				Constructor<MultiModelBaseBiped> cm = lclass.getConstructor(float.class);
				mlm[0] = cm.newInstance(0.0F);
				float[] lsize = mlm[0].getArmorModelsSize();
				mlm[1] = cm.newInstance(lsize[0]);
				mlm[2] = cm.newInstance(lsize[1]);
				modelMap.put(name, mlm);
				Modchu_Debug.tDebug("getModelClass: %s", name);
			} else {
				Modchu_Debug.tDebug("getModelClass-class == null !!: %s", name);
			}
		}
		catch (Exception exception) {
			Modchu_Debug.tDebug("getModelClass-Exception: %s", name);
			exception.printStackTrace();
		}
		catch (Error error) {
			Modchu_Debug.tDebug("getModelClass-Error: %s", name);
		}
	}
	public void addModelClass(String fname, String[] pSearch) {
		// モデルを追加
		int lfindprefix = fname.indexOf(pSearch[2]);
		//Modchu_Debug.tDebug("addModelClass 1 pSearch[2]="+pSearch[2]);
		//Modchu_Debug.tDebug("addModelClass 2 fname="+fname+" lfindprefix="+lfindprefix);
		if (lfindprefix > -1
				&& fname.endsWith(".class")) {
			String cn = Modchu_FileManager.classNameProcessing(fname);
			int version = Modchu_Main.getMinecraftVersion();
			String pn = version > 162 ? fname.substring(pSearch[2].length() + lfindprefix) : fname.substring(pSearch[2].length() + lfindprefix).replace(".class", "");
			if (version > 162) pn = pn.substring(0, pn.length() - 6);
			Modchu_Debug.tDebug("addModelClass cn="+cn+" pn="+pn);
			if (modelMap.containsKey(pn)) return;

			Class lclass;
			try {
				lclass = Modchu_Reflect.loadClass(cn);
				if (lclass != null) {
					if (!(MultiModelBaseBiped.class).isAssignableFrom(lclass) || Modifier.isAbstract(lclass.getModifiers())) {
						Modchu_Debug.tDebug("getModelClass-fail.");
						return;
					}
					MultiModelBaseBiped mlm[] = new MultiModelBaseBiped[3];
					Constructor<MultiModelBaseBiped> cm = lclass.getConstructor(float.class);
					mlm[0] = cm.newInstance(0.0F);
					float[] lsize = mlm[0].getArmorModelsSize();
					//Modchu_Debug.mDebug("getModelClass lclass="+lclass+" lsize[0]="+lsize[0]+" lsize[1]="+lsize[1]);
					mlm[1] = cm.newInstance(lsize[0]);
					mlm[2] = cm.newInstance(lsize[1]);
					modelMap.put(pn, mlm);
					Modchu_Debug.tDebug("getModelClass-%s:%s", pn, cn);
				} else {
					Modchu_Debug.tDebug("getModelClass-class == null !!: %s", cn);
				}
			}
			catch (Exception exception) {
				Modchu_Debug.tDebug("getModelClass-Exception: %s", fname);
				exception.printStackTrace();
			}
			catch (Error error) {
				Modchu_Debug.tDebug("getModelClass-Error: %s", fname);
			}
		}
	}

	public void addTextureName(String fname, String[] pSearch) {
		// パッケージにテクスチャを登録
		String s = fname
				+ (pSearch != null 
				&& pSearch.length > 0 ? pSearch[0] : "")
				+ (pSearch != null 
						&& pSearch.length > 1 ? pSearch[1] : "");
		if (getSearchSettledList().contains(s)) {
			//Modchu_Debug.mDebug("Modchu_TextureManagerBase addTextureName getSearchSettledList().contains return.");
			return;
		}
		getSearchSettledList().add(s);
		if (!fname.startsWith("/")) {
			fname = (new StringBuilder()).append("/").append(fname).toString();
		} else {

		}

		if (fname.startsWith(pSearch[1])) {
			//Modchu_Debug.tDebug("Modchu_TextureManagerBase addTextureName fname="+fname);
			int i = fname.lastIndexOf("/");
			if (pSearch[1].length() < i) {
				String pn = fname.substring(pSearch[1].length(), i);
				pn = pn.replace('/', '.');
				String fn = fname.substring(i);
				int lindex = getIndex(fn);
				if (lindex > -1) {
					String an = null;
					if (lindex == tx_oldarmor1) {
						lindex = tx_armor1;
						an = "default";
					}
					if (lindex == tx_oldarmor2) {
						lindex = tx_armor2;
						an = "default";
					}
					if (lindex == tx_oldwild) {
						lindex = tx_wild + 12;
					}
					Modchu_TextureBoxBase lts = getTextureBox(pn);
					if (lts == null) {
						lts = new Modchu_TextureBoxBase(pn);
						textures.add(lts);
						Modchu_Debug.tDebug("getTextureName-append-texturePack-%s", pn);
					}
					lts.addTexture(lindex, fname);
				}
			}
		}
	}

	public boolean addTexturesZip(File file, String[] pSearch) {
		//
		if (file == null || file.isDirectory()) {
			return false;
		}
		try {
			FileInputStream fileinputstream = new FileInputStream(file);
			ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
			ZipEntry zipentry;
			do {
				zipentry = zipinputstream.getNextEntry();
				if(zipentry == null)
				{
					break;
				}
				//Modchu_Debug.tDebug("addTextureZip zipentry.getName()="+zipentry.getName());
				if (!zipentry.isDirectory()) {
					if (zipentry.getName().endsWith(".class")) {
						//Modchu_Debug.tDebug("addTextureZip zipentry. addModelClass");
						addModelClass(zipentry.getName(), pSearch);
					} else {
						addTextureName(zipentry.getName(), pSearch);
					}
				}
			} while(true);

			zipinputstream.close();
			fileinputstream.close();

			return true;
		} catch (Exception e) {
			Modchu_Debug.lDebug("addTextureZip-Exception.", 2, e);
			return false;
		}
	}

	public void addTexturesJar(File file, String[] pSearch) {
		if (file.isFile()) {
			Modchu_Debug.tDebug("addTextureJar-zip.");
			if (addTexturesZip(file, pSearch)) {
				Modchu_Debug.tDebug("getTexture-append-jar-done.");
			} else {
				Modchu_Debug.tDebug("getTexture-append-jar-fail.");
			}
		}

		if (file.isDirectory()) {
			Modchu_Debug.tDebug("addTextureJar-file.");
			boolean lflag = false;

			for (File t : file.listFiles()) {
				if (t.isDirectory()) {
					lflag |= addTexturesDir(t, pSearch);
				}
			}
			if (lflag) {
				Modchu_Debug.tDebug("getTexture-append-jar-done.");
			} else {
				Modchu_Debug.tDebug("getTexture-append-jar-fail.");
			}

			if (addTexturesDir(file, pSearch)) {
				Modchu_Debug.tDebug("getTexture-append-jar-done.");
			} else {
				Modchu_Debug.tDebug("getTexture-append-jar-fail.");
			}

		}
	}

	public boolean addTexturesDir(File file, String[] lst) {
		return addTexturesDir(file, lst, false);
	}

	public boolean addTexturesDir(File file, String[] lst, boolean debug) {
		// modsフォルダに突っ込んであるものも検索、再帰で。
		if (file == null) {
			return false;
		}

		try {
			for (File t : file.listFiles()) {
				if (debug) Modchu_Debug.tDebug("Modchu_TextureManagerBase addTexturesDir t="+t);
				if (t.isDirectory()) {
					addTexturesDir(t, lst);
				} else {
					String s = t.getPath().replace('\\', '/');
					if (t.getName().endsWith(".class")) {
						addModelClass(t.getAbsolutePath(), lst);
					} else {
						int i = s.indexOf(lst[1]);
						if (i > -1) {
							// 対象はテクスチャディレクトリ
							addTextureName(s.substring(i), lst);
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			Modchu_Debug.lDebug("addTextureDebug-Exception.", 2, e);
			return false;
		}
	}

	public int getIndex(String name) {
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



	/**
	 * テクスチャパックを設定するため、サーバーへ情報を送る。
	 * @param pEntity
	 * @param pBox
	 */
/*
	public void postSetTexturePack(Modchu_ITextureEntityBase pEntity, int pColor, ModchuModel_TextureBoxBase[] pBox) {
		// Client
		if (!(pEntity instanceof Entity)) return;
		// テクスチャパックを設定するため、サーバーへ情報を送る。
		int lindex[] = new int[pBox.length];
		boolean lflag = true;

		// PackeNameからサーバー側のテクスチャインデックスを獲得する。
		for (int li = 0; li < pBox.length; li++) {
			lindex[li] = checkTextureBoxServer((ModchuModel_TextureBox)pBox[li]);
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
*/
	/**
	 * TextureBoxにサーバー識別番号が付与されているかを確認し、なければ問い合わせを行う。
	 * @param pBox
	 * @return
	 */
/*
	public int checkTextureBoxServer(ModchuModel_TextureBox pBox) {
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

	public void sendToServerSetTexturePackIndex(Modchu_ITextureEntityBase pEntity, int pColor, int[] pIndex) {
		// Client
		// サーバー側へテクスチャパックのインデックスが変更されたことを通知する。
		if (pEntity instanceof Entity) {
			byte ldata[] = new byte[6 + pIndex.length * 2];
			ldata[0] = ModchuModel_Statics.Server_SetTexturePackIndex;
			ModchuModel_Helper.setInt(ldata, 1, ((Entity)pEntity).entityId);
			ldata[5] = (byte)pColor;
			int li = 6;
			for (int ll  : pIndex) {
				ModchuModel_Helper.setShort(ldata, li, ll);
				li += 2;
			}
			ModchuModel_Client.sendToServer(ldata);
		}
	}

	public void reciveFromClientSetTexturePackIndex(Entity pEntity, byte[] pData) {
		// Server
		if (pEntity instanceof Modchu_ITextureEntityBase) {
			// クライアント側からテクスチャパックのインデックスが変更された通知を受け取ったので処理を行う。
			int lcount = (pData.length - 6) / 2;
			if (lcount < 1) return;
			int lindex[] = new int[lcount];

			for (int li = 0; li < lcount; li++) {
				lindex[li] = ModchuModel_Helper.getShort(pData, 6 + li * 2);
			}
			Modchu_Debug.tDebug("reciveFromClientSetTexturePackIndex: %d, %4x", pData[5], lindex[0]);
			((Modchu_ITextureEntityBase)pEntity).setTexturePackIndex(pData[5], lindex);
		}
	}

	public void sendToServerGetTextureIndex(int pBufIndex, ModchuModel_TextureBox pBox) {
		// Client
		// サーバー側へテクスチャパックの管理番号を問い合わせる。
		// 呼び出し側のクライアントへのみ返す。
		// 返すときはNameは不要、BufIndexのみで識別させる
		byte ldata[] = new byte[22 + pBox.textureName.length()];
		ldata[0] = ModchuModel_Statics.Server_GetTextureIndex;
		ldata[1] = (byte)pBufIndex;
		ModchuModel_Helper.setShort(ldata, 2, pBox.getContractColorBits());
		ModchuModel_Helper.setShort(ldata, 4, pBox.getWildColorBits());
		ModchuModel_Helper.setFloat(ldata, 6, pBox.getHeight(null));
		ModchuModel_Helper.setFloat(ldata, 10, pBox.getWidth(null));
		ModchuModel_Helper.setFloat(ldata, 14, pBox.getYOffset(null));
		ModchuModel_Helper.setFloat(ldata, 18, pBox.getMountedYOffset(null));
		ModchuModel_Helper.setStr(ldata, 22, pBox.textureName);
		ModchuModel_Client.sendToServer(ldata);
		Modchu_Debug.tDebug("Server_GetTextureIndex: %s", pBox.textureName);
	}

	public void reciveFromClientGetTexturePackIndex(NetServerHandler pHandler, byte[] pData) {
		// Server
		// クライアント側へテクスチャパックの管理番号を返す。
		String lpackname = ModchuModel_Helper.getStr(pData, 22);
		ModchuModel_TextureBoxServer lboxsrv = getTextureBoxServer(lpackname);
		int li;
		if (lboxsrv == null) {
			li = textureServer.size();
			lboxsrv = new ModchuModel_TextureBoxServer();
			textureServer.add(lboxsrv);
		} else {
			li = textureServer.indexOf(lboxsrv);
		}
		lboxsrv.setValue(pData);

		byte ldata[] = new byte[4];
		ldata[0] = ModchuModel_Statics.Client_SetTextureIndex;
		ldata[1] = pData[1];
		ModchuModel_Helper.setShort(ldata, 2, li);
		Modchu_Debug.tDebug("reciveFromClientGetTexturePackIndex: %s, %04x", lpackname, li);
		Modchu_Debug.sendToClient(pHandler, ldata);
	}

	public void reciveFormServerSetTexturePackIndex(byte[] pData) {
		// Client
		// サーバー側からテクスチャパックのインデックスを受け取ったので値を登録する。
		ModchuModel_TextureBox lbox = getTextureBox(getRequestString(pData[1]));
		textureServerIndex.put(lbox, (int)ModchuModel_Helper.getShort(pData, 2));
		Modchu_Debug.tDebug("reciveFormServerSetTexturePackIndex: %s, %04x", lbox.textureName, (int)ModchuModel_Helper.getShort(pData, 2));

		// スタックされたジョブから処理可能な物があれば実行する。
		Map<Modchu_ITextureEntityBase, Object[]> lmap = new HashMap<Modchu_ITextureEntityBase, Object[]>(stackSetTexturePack);
		stackSetTexturePack.clear();
		for (Entry<Modchu_ITextureEntityBase, Object[]> le : lmap.entrySet()) {
			Object lo[] = le.getValue();
			ModchuModel_TextureBox ls[] = new ModchuModel_TextureBox[le.getValue().length - 1];
			int lc = (Integer)lo[0];
			for (int li = 1; li < lo.length; li++) {
				ls[li - 1] = (ModchuModel_TextureBox)lo[li];
			}
			postSetTexturePack(le.getKey(), lc, ls);
		}
	}
*/


	/**
	 * サーバーから設定されたテクスチャインデックスからテクスチャパックを取得する。
	 * @param pEntity
	 * @param pIndex
	 */
/*
	public void postGetTexturePack(Modchu_ITextureEntityBase pEntity, int[] pIndex) {
		// Client
		// クライアント側で指定されたインデックスに対してテクスチャパックの名称を返し設定させる
		ModchuModel_TextureBox lbox[] = new ModchuModel_TextureBox[pIndex.length];
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

	public void sendToServerGetTexturePackName(int pIndex) {
		// Client
		// サーバー側へテクスチャパックの名称を問い合わせる
		if (pIndex < 0) {
			Modchu_Debug.tDebug("request range out.");
			return;
		}
		byte ldata[] = new byte[3];
		ldata[0] = ModchuModel_Statics.Server_GetTexturePackName;
		ModchuModel_Helper.setShort(ldata, 1, pIndex);
		ModchuModel_Client.sendToServer(ldata);
	}

	public void reciveFromClientGetTexturePackName(NetServerHandler pHandler, byte[] pData) {
		// Server
		// クライアントからテクスチャパックの名称が問い合わせられた。
		int lindex = ModchuModel_Helper.getShort(pData, 1);
		ModchuModel_TextureBoxServer lboxserver = getTextureBoxServer(lindex);

		// Clientへ管理番号に登録されているテクスチャ名称をポストする
		byte ldata[] = new byte[23 + lboxserver.textureName.length()];
		ldata[0] = ModchuModel_Statics.Client_SetTexturePackName;
		ModchuModel_Helper.setShort(ldata, 1, lindex);
		ModchuModel_Helper.setShort(ldata, 3, lboxserver.getContractColorBits());
		ModchuModel_Helper.setShort(ldata, 5, lboxserver.getWildColorBits());
		ModchuModel_Helper.setFloat(ldata, 7, lboxserver.getHeight(null));
		ModchuModel_Helper.setFloat(ldata, 11, lboxserver.getWidth(null));
		ModchuModel_Helper.setFloat(ldata, 15, lboxserver.getYOffset(null));
		ModchuModel_Helper.setFloat(ldata, 19, lboxserver.getMountedYOffset(null));
		ModchuModel_Helper.setStr(ldata, 23, lboxserver.textureName);
		Modchu_Debug.sendToClient(pHandler, ldata);
		Modchu_Debug.tDebug("SetTexturePackName:%04x - %s", lindex, lboxserver.textureName);
	}

	public void reciveFromServerSetTexturePackName(byte[] pData) {
		// Client
		// サーバーからインデックスに対する名称の設定があった。
		String lpackname = ModchuModel_Helper.getStr(pData, 23);
		ModchuModel_TextureBox lbox = getTextureBox(lpackname);
		if (lbox == null) {
			// ローカルには存在しないテクスチャパック
			// TODO:この辺要修正
			lbox = getTextureBox("default_Orign").duplicate();
			lbox.textureName = lpackname;
//			lbox = new ModchuModel_TextureBox(lpackname, null);
			lbox.setModelSize(
					ModchuModel_Helper.getFloat(pData, 7),
					ModchuModel_Helper.getFloat(pData, 11),
					ModchuModel_Helper.getFloat(pData, 15),
					ModchuModel_Helper.getFloat(pData, 19));
			textures.add(lbox);
		}
		int lindex = ModchuModel_Helper.getShort(pData, 1);
		textureServerIndex.put(lbox, lindex);
		clearRequestIndex(lindex);

		// 処理可能な物がスタックされている場合は処理を行う。
		Map<Modchu_ITextureEntityBase, int[]> lmap = new HashMap<Modchu_ITextureEntityBase, int[]>(stackGetTexturePack);
		stackGetTexturePack.clear();
		for (Entry<Modchu_ITextureEntityBase, int[]> le : lmap.entrySet()) {
			postGetTexturePack(le.getKey(), le.getValue());
		}
	}
*/
	/**
	 * Request系の値を一定カウントで消去
	 */
	public void onUpdate() {
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

	public LinkedList getSearchSettledList() {
		return searchSettledList;
	}

}

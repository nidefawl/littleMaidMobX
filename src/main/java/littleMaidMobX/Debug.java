package littleMaidMobX;

import littleMaidMobX.model.maids.MultiModel_Elsie2;
import littleMaidMobX.render.model.ModelBox;
import modchu.lib.characteristic.Modchu_ModelBoxBase;
import modchu.model.multimodel.MultiModel_Elsie;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.util.Vec3;

public class Debug {

	public static MultiModel_Elsie elsieChu = null;
	public static MultiModel_Elsie2 elsieOur = null;

	public static void doDebug() {
		try {


			if (elsieChu != null && elsieOur != null) {

				PositionTextureVertex[] ourUV = null;
				PositionTextureVertex[] chuUV = null;

				if (elsieOur.bipedLeftArm.cubeList.size() == 1) {
					ModelBox b = (ModelBox)elsieOur.bipedLeftArm.cubeList.get(0);
					ourUV = b.vertexPositions;
				}
				if (elsieChu.bipedLeftArm.cubeList.size() == 1) {
					Modchu_ModelBoxBase b = elsieChu.bipedLeftArm.cubeList.get(0);
					chuUV = b.vertexPositions;
				}

				if (ourUV == null) {
					System.err.println("no uv in our model");
					return;
				}
				if (chuUV == null) {
					System.err.println("no uv in chu model");
					return;
				}
				if (ourUV.length != chuUV.length) {
					System.err.println("uv array len missmatch");
					return;
				}
				for (int i = 0; i < ourUV.length; i++) {
					Vec3 v1 = ourUV[i].vector3D;
					Vec3 v2 = chuUV[i].vector3D;
					if (v1.xCoord != v2.xCoord || v1.yCoord != v2.yCoord || v1.zCoord != v2.zCoord) {
						System.err.println("Vec3 at pos "+i+" missmatch");
						return;
					}
					if (ourUV[i].texturePositionX != chuUV[i].texturePositionX) {
						System.err.println("texturePositionX at pos "+i+" missmatch");
					}
					if (ourUV[i].texturePositionY != chuUV[i].texturePositionY) {
						System.err.println("texturePositionY at pos "+i+" missmatch");
					}
				}

				System.out.println("all UVs matched: "+ourUV.length);
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

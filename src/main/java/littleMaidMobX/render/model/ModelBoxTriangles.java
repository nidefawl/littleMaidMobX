package littleMaidMobX.render.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;

public class ModelBoxTriangles implements IRenderable {

	private PositionTextureVertex[] positionTextureVertex;
	private TextureTriangle[] triList;
	public final float posX1;
	public final float posY1;
	public final float posZ1;
	public final float posX2;
	public final float posY2;
	public final float posZ2;
	public final float posX3;
	public final float posY3;
	public final float posZ3;
	public final float[][] vn;


	public ModelBoxTriangles(ModelRenderer var1, int var2, int var3,
			float[][] var4, float[][] var5, float[][] var6, float[] var7, float var8) {
		int var21 = var4.length;
		posX1 = var4[0][0];
		posY1 = var4[0][1];
		posZ1 = var4[0][2];
		posX2 = var4[1][0];
		posY2 = var4[1][1];
		posZ2 = var4[1][2];
		posX3 = var4[2][0];
		posY3 = var4[2][1];
		posZ3 = var4[2][2];
		vn = var6;
		float var22 = 8.0F;
		float var23 = 8.0F;
		positionTextureVertex = new PositionTextureVertex[var21];
		//Modchu_Debug.mDebug("ModchuModel_ModelPlateFreeShape positionTextureVertex="+positionTextureVertex);
		triList = new TextureTriangle[1];

		for (int var24 = 0; var24 < var21; ++var24) {
			positionTextureVertex[var24] = new PositionTextureVertex(var4[var24][0], var4[var24][1], var4[var24][2], 0.0F, 0.0F );
			//Modchu_Debug.mDebug("ModchuModel_ModelPlateFreeShape positionTextureVertex["+var24+"]="+positionTextureVertex[var24]);
		}

		float[][] var27 = new float[3][2];
		var27[0][0] = (float)var2 + var22;
		var27[0][1] = (float)var3;
		var27[1][0] = (float)var2;
		var27[1][1] = (float)var3;
		var27[2][0] = (float)var2;
		var27[2][1] = (float)var3 + var23;
		triList[0] = new TextureTriangle(positionTextureVertex, var5, vn, var7, 0.0F, 0.0F );
		//Modchu_Debug.mDebug("ModchuModel_ModelPlateFreeShape triList[0]="+triList[0]);

		if (var1.mirror) {
			for (var2 = 0; var2 < triList.length; ++var2) {
				triList[var2].flipFace();
				//Modchu_Debug.mDebug("ModchuModel_ModelPlateFreeShape triList["+var2+"]="+triList[var2]);
			}
		}
	}

	public void render(Tessellator o, float f) {
		for (int var3 = 0; var3 < triList.length; ++var3) {
			triList[var3].draw(o, f);
		}
	}

}
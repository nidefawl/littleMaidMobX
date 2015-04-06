package littleMaidMobX.render.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

public class TextureTriangle {

	//keep it private, if it doesnt change we can calculate normals once, plus use display lists later on
	private PositionTextureVertex[] vertexPositions;
	
	public int nVertices;
	public float[][] vn;
	private boolean invertNormal;
	public int red;
	public int green;
	public int blue;
	public int alpha;
	private boolean setColor;
	Vec3[] normals;

	public TextureTriangle(PositionTextureVertex[] positionTextureVertex, float[][] var2, float[][] var3, float[] var4, float var5, float var6) {
		if (var4 != null) {
			red = (int) (255.0F * var4[0]);
			green = (int) (255.0F * var4[1]);
			blue = (int) (255.0F * var4[2]);
			alpha = (int) (255.0F * var4[3] * 0.5F);
			setColor = true;
		} else {
			red = 255;
			green = 255;
			blue = 255;
			alpha = 255;
			setColor = false;
		}

		if (var5 == 0.0F && var6 == 0.0F) {
			var6 = 1.0F;
			var5 = 1.0F;
		}

		nVertices = positionTextureVertex.length;

		for (int i = 0; i < nVertices; ++i) {
			positionTextureVertex[i] = positionTextureVertex[i].setTexturePosition(var2[i][0] / var5, var2[i][1] / var6);
		}
		vertexPositions = positionTextureVertex;
		normals = new Vec3[nVertices];

		if (vn == null) {
			Vec3 vec3_1 = vertexPositions[1].vector3D;
			Vec3 vec3_0 = vec3_1.subtract(vertexPositions[0].vector3D);
			Vec3 vec3_2 = vec3_1.subtract(vertexPositions[2].vector3D);

			for (int i = 0; i < nVertices; ++i) {
				normals[i] = vec3_2.crossProduct(vec3_0).normalize();
			}
		} else {
			for (int i = 0; i < nVertices; ++i) {
				normals[i] = Vec3.createVectorHelper((double) vn[i][0], (double) vn[i][1], (double) vn[i][2]);
			}
		}
	}

	public void flipFace() {
		PositionTextureVertex[] o = new PositionTextureVertex[vertexPositions.length];
		for (int var2 = 0; var2 < vertexPositions.length; ++var2) {
			o[var2] = vertexPositions[vertexPositions.length - var2 - 1];
		}
		vertexPositions = o;
	}

	
	public void draw(Tessellator tessellator, float f) {

		GL11.glShadeModel(GL11.GL_SMOOTH);
		byte by = 6;

		if (nVertices % 4 == 0) {
			by = 7;
		}
		tessellator.startDrawing(by);

		for (int i1 = 0; i1 < nVertices; ++i1) {
			if (invertNormal) {
				tessellator.setNormal((float)-normals[i1].xCoord, (float)-normals[i1].yCoord, (float)-normals[i1].zCoord);
			} else {
				tessellator.setNormal((float) normals[i1].xCoord, (float) normals[i1].yCoord, (float) normals[i1].zCoord);
			}

			PositionTextureVertex vertexPosition = vertexPositions[i1];
			Vec3 vertexPositionVec3 = vertexPosition.vector3D;
			tessellator.addVertexWithUV(
					vertexPositionVec3.xCoord*f,
					vertexPositionVec3.yCoord*f,
					vertexPositionVec3.zCoord*f,
					vertexPosition.texturePositionX,
					vertexPosition.texturePositionY
					);
		}

		tessellator.draw();
	}

}
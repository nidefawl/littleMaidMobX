/*    */ package littleMaidMobX;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class LMM_EntityAIFleeRain extends net.minecraft.entity.ai.EntityAIBase implements LMM_IEntityAI
/*    */ {
/*    */   protected EntityCreature theCreature;
/*    */   protected double shelterX;
/*    */   protected double shelterY;
/*    */   protected double shelterZ;
/*    */   protected float movespeed;
/*    */   protected World theWorld;
/*    */   protected boolean isEnable;
/*    */   
/*    */   public LMM_EntityAIFleeRain(EntityCreature par1EntityCreature, float pMoveSpeed)
/*    */   {
/* 22 */     this.theCreature = par1EntityCreature;
/* 23 */     this.movespeed = pMoveSpeed;
/* 24 */     this.theWorld = par1EntityCreature.worldObj;
/* 25 */     this.isEnable = false;
/* 26 */     setMutexBits(1);
/*    */   }
/*    */   
/*    */   public boolean shouldExecute()
/*    */   {
/* 31 */     if ((!this.isEnable) || (!this.theWorld.isRaining())) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (!this.theCreature.isWet()) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     if (!this.theWorld.canBlockSeeTheSky(MathHelper.floor_double(this.theCreature.posX), (int)this.theCreature.boundingBox.minY, MathHelper.floor_double(this.theCreature.posZ)))
/*    */     {
/*    */ 
/*    */ 
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     Vec3 vec3d = findPossibleShelter();
/*    */     
/* 48 */     if (vec3d == null) {
/* 49 */       return false;
/*    */     }
/* 51 */     this.shelterX = vec3d.xCoord;
/* 52 */     this.shelterY = vec3d.yCoord;
/* 53 */     this.shelterZ = vec3d.zCoord;
/* 54 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean continueExecuting()
/*    */   {
/* 60 */     return !this.theCreature.getNavigator().noPath();
/*    */   }
/*    */   
/*    */   public void startExecuting()
/*    */   {
/* 65 */     this.theCreature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movespeed);
/*    */   }
/*    */   
/*    */   private Vec3 findPossibleShelter() {
/* 69 */     Random random = this.theCreature.getRNG();
/*    */     
/* 71 */     for (int i = 0; i < 10; i++) {
/* 72 */       int j = MathHelper.floor_double(this.theCreature.posX + random.nextInt(20) - 10.0D);
/*    */       
/* 74 */       int k = MathHelper.floor_double(this.theCreature.boundingBox.minY + random.nextInt(6) - 3.0D);
/*    */       
/* 76 */       int l = MathHelper.floor_double(this.theCreature.posZ + random.nextInt(20) - 10.0D);
/*    */       
/*    */ 
/* 79 */       if ((!this.theWorld.canBlockSeeTheSky(j, k, l)) && (this.theCreature.getBlockPathWeight(j, k, l) > -0.5F))
/*    */       {
/* 81 */         return Vec3.createVectorHelper(j, k, l);
/*    */       }
/*    */     }
/*    */     
/* 85 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setEnable(boolean pFlag)
/*    */   {
/* 91 */     this.isEnable = pFlag;
/*    */   }
/*    */   
/*    */   public boolean getEnable()
/*    */   {
/* 96 */     return this.isEnable;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIFleeRain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
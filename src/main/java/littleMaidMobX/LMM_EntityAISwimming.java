/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.ai.EntityAISwimming;
/*    */ 
/*    */ public class LMM_EntityAISwimming extends EntityAISwimming
/*    */ {
/*    */   protected EntityLiving theEntity;
/*    */   
/*    */   public LMM_EntityAISwimming(EntityLiving par1EntityLiving)
/*    */   {
/* 12 */     super(par1EntityLiving);
/* 13 */     this.theEntity = par1EntityLiving;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean shouldExecute()
/*    */   {
/* 19 */     if (this.theEntity.getNavigator().noPath() ? (this.theEntity.onGround) || (!this.theEntity.isInsideOfMaterial(net.minecraft.block.material.Material.water)) : !this.theEntity.isInWater()) {} return this.theEntity.handleLavaMovement();
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAISwimming.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
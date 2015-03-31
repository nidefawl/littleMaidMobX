/*    */ package littleMaidMobX;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class LMM_EntityAINearestAttackableTargetSorter implements Comparator
/*    */ {
/*    */   private Entity theEntity;
/*    */   
/*    */   public LMM_EntityAINearestAttackableTargetSorter(Entity par1Entity)
/*    */   {
/* 12 */     this.theEntity = par1Entity;
/*    */   }
/*    */   
/*    */   public int compareDistanceSq(Entity par1Entity, Entity par2Entity) {
/* 16 */     double var3 = this.theEntity.getDistanceSqToEntity(par1Entity);
/* 17 */     double var5 = this.theEntity.getDistanceSqToEntity(par2Entity);
/* 18 */     return var3 > var5 ? 1 : var3 < var5 ? -1 : 0;
/*    */   }
/*    */   
/*    */   public int compare(Object par1Obj, Object par2Obj) {
/* 22 */     return compareDistanceSq((Entity)par1Obj, (Entity)par2Obj);
/*    */   }
/*    */   
/*    */   public void setEntity(Entity pEntity) {
/* 26 */     this.theEntity = pEntity;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAINearestAttackableTargetSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
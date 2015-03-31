/*    */ package zabuton;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class VZN_ModelZabuton extends ModelBase
/*    */ {
/*    */   public ModelRenderer zabuton;
/*    */   
/*    */   public VZN_ModelZabuton()
/*    */   {
/* 13 */     this.zabuton = new ModelRenderer(this, 0, 0);
/* 14 */     this.zabuton.addBox(-6.0F, -3.0F, -6.0F, 12, 3, 12);
/*    */   }
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*    */   {
/* 19 */     this.zabuton.render(f5);
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/zabuton/VZN_ModelZabuton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
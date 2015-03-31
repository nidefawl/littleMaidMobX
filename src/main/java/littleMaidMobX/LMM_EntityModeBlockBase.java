/*    */ package littleMaidMobX;
/*    */ 
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class LMM_EntityModeBlockBase extends LMM_EntityModeBase
/*    */ {
/*    */   protected double fDistance;
/*    */   
/*    */   public LMM_EntityModeBlockBase(LMM_EntityLittleMaid pEntity)
/*    */   {
/* 12 */     super(pEntity);
/*    */   }
/*    */   
/*    */ 
/*    */   public void updateBlock()
/*    */   {
/* 18 */     this.owner.setTilePos(0);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isSearchBlock()
/*    */   {
/* 26 */     boolean lflag = false;
/* 27 */     for (int li = 0; li < this.owner.maidTiles.length; li++) {
/* 28 */       if (this.owner.maidTiles[li] != null) {
/* 29 */         TileEntity ltile = this.owner.getTileEntity(li);
/* 30 */         if ((ltile != null) && (!checkWorldMaid(ltile))) {
/* 31 */           if (!lflag) {
/* 32 */             this.owner.setTilePos(ltile);
/*    */           }
/* 34 */           lflag = true;
/*    */         }
/*    */       }
/*    */     }
/* 38 */     return !lflag;
/*    */   }
/*    */   
/*    */   public boolean overlooksBlock(int pMode)
/*    */   {
/* 43 */     if (this.owner.isTilePos()) {
/* 44 */       this.owner.setTilePos(0);
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean checkWorldMaid(TileEntity pTile)
/*    */   {
/* 56 */     for (Object lo : this.owner.worldObj.loadedEntityList) {
/* 57 */       if (lo != this.owner) {
/* 58 */         if ((lo instanceof LMM_EntityLittleMaid)) {
/* 59 */           LMM_EntityLittleMaid lem = (LMM_EntityLittleMaid)lo;
/* 60 */           if (lem.isUsingTile(pTile))
/*    */           {
/* 62 */             return true; }
/*    */         }
/*    */       }
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityModeBlockBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
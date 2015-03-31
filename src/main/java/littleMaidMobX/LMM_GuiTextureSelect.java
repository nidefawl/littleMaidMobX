/*    */ package littleMaidMobX;
/*    */ 
/*    */ import mmmlibx.lib.ITextureEntity;
/*    */ import mmmlibx.lib.MMM_GuiTextureSelect;
/*    */ import mmmlibx.lib.MMM_GuiTextureSlot;
/*    */ import mmmlibx.lib.MMM_TextureManager;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ 
/*    */ public class LMM_GuiTextureSelect
/*    */   extends MMM_GuiTextureSelect
/*    */ {
/*    */   public LMM_GuiTextureSelect(GuiScreen pOwner, ITextureEntity pTarget, int pColor, boolean pToServer)
/*    */   {
/* 16 */     super(pOwner, pTarget, pColor, pToServer);
/*    */   }
/*    */   
/*    */   protected void actionPerformed(GuiButton par1GuiButton)
/*    */   {
/* 21 */     super.actionPerformed(par1GuiButton);
/* 22 */     switch (par1GuiButton.id) {
/*    */     case 200: 
/* 24 */       if (this.toServer) {
/* 25 */         MMM_TextureManager.instance.postSetTexturePack(this.target, this.selectColor, this.target.getTextureBox());
/* 26 */         if (this.selectColor != this.selectPanel.color)
/*    */         {
/*    */ 
/*    */ 
/* 30 */           byte[] ldata = new byte[2];
/* 31 */           ldata[0] = 2;
/* 32 */           ldata[1] = ((byte)this.selectColor);
/* 33 */           LMM_Net.sendToServer(ldata);
/*    */         }
/*    */       }
/*    */       break;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_GuiTextureSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
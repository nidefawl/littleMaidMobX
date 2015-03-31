/*    */ package littleMaidMobX;
/*    */ 
/*    */ import mmmlibx.lib.MMM_TextureBoxBase;
/*    */ import mmmlibx.lib.MMM_TextureManager;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemWritableBook;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ 
/*    */ public class LMM_EntityMode_BookDecorder extends LMM_EntityModeBase
/*    */ {
/*    */   public LMM_EntityMode_BookDecorder(LMM_EntityLittleMaid pEntity)
/*    */   {
/* 15 */     super(pEntity);
/*    */   }
/*    */   
/*    */   public int priority()
/*    */   {
/* 20 */     return 8000;
/*    */   }
/*    */   
/*    */ 
/*    */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {}
/*    */   
/*    */ 
/*    */   public boolean interact(EntityPlayer pentityplayer, ItemStack pitemstack)
/*    */   {
/* 29 */     if ((pitemstack.getItem() instanceof ItemWritableBook)) {
/* 30 */       ItemWritableBook lwb = (ItemWritableBook)pitemstack.getItem();
/* 31 */       if (ItemWritableBook.func_150930_a(pitemstack.getTagCompound())) {
/* 32 */         NBTTagList llist = pitemstack.getTagCompound().getTagList("pages", 8);
/* 33 */         String ls = "";
/* 34 */         for (int li = 0; li < llist.tagCount(); li++)
/*    */         {
/* 36 */           ls = ls + llist.getStringTagAt(li);
/*    */         }
/*    */         
/* 39 */         String[] lcommands = ls.split(";");
/*    */         
/* 41 */         MMM_TextureBoxBase[] lboxs = { this.owner.textureData.textureBox[0], this.owner.textureData.textureBox[1] };
/*    */         
/*    */ 
/*    */ 
/* 45 */         int lcolor = this.owner.getColor();
/* 46 */         boolean lflag = false;
/* 47 */         for (String lt : lcommands) {
/* 48 */           String[] lcom = lt.split(":");
/* 49 */           if (lcom.length > 1) {
/* 50 */             lcom[0] = lcom[0].trim();
/* 51 */             lcom[1] = lcom[1].trim();
/*    */             try
/*    */             {
/* 54 */               if (lcom[0].equals("color")) {
/* 55 */                 lcolor = Integer.valueOf(lcom[1]).intValue() & 0xF;
/* 56 */                 lflag = true;
/*    */               }
/* 58 */               else if (lcom[0].equals("texture")) {
/* 59 */                 MMM_TextureBoxBase lbox = MMM_TextureManager.instance.getTextureBox(lcom[1]);
/* 60 */                 if (lbox != null) {
/* 61 */                   lboxs[0] = lbox;
/* 62 */                   lflag = true;
/*    */                 }
/*    */               }
/* 65 */               else if (lcom[0].equals("armor")) {
/* 66 */                 MMM_TextureBoxBase lbox = MMM_TextureManager.instance.getTextureBox(lcom[1]);
/* 67 */                 if (lbox != null) {
/* 68 */                   lboxs[1] = lbox;
/* 69 */                   lflag = true;
/*    */                 }
/*    */               }
/* 72 */               else if (lcom[0].equals("dominantArm")) {
/* 73 */                 int li = Integer.valueOf(lcom[1]).intValue();
/* 74 */                 this.owner.setDominantArm(li);
/*    */               }
/*    */             }
/*    */             catch (Exception e) {
/* 78 */               e.printStackTrace();
/*    */             }
/*    */           }
/*    */         }
/* 82 */         if ((pentityplayer.worldObj.isRemote) && 
/* 83 */           (lflag)) {
/* 84 */           MMM_TextureManager.instance.postSetTexturePack(this.owner, lcolor, lboxs);
/*    */         }
/*    */         
/* 87 */         return true;
/*    */       }
/*    */     }
/* 90 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_BookDecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
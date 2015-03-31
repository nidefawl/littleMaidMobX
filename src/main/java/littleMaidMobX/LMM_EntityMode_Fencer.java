/*     */ package littleMaidMobX;
/*     */ 
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemAxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LMM_EntityMode_Fencer
/*     */   extends LMM_EntityModeBase
/*     */ {
/*     */   public static final int mmode_Fencer = 128;
/*     */   public static final int mmode_Bloodsucker = 192;
/*     */   
/*     */   public LMM_EntityMode_Fencer(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  21 */     super(pEntity);
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  26 */     return 3000;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init()
/*     */   {
/*  46 */     LMM_TriggerSelect.appendTriggerItem(null, "Sword", "");
/*  47 */     LMM_TriggerSelect.appendTriggerItem(null, "Axe", "");
/*     */   }
/*     */   
/*     */ 
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting)
/*     */   {
/*  53 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  54 */     ltasks[0] = pDefaultMove;
/*  55 */     ltasks[1] = new EntityAITasks(this.owner.aiProfiler);
/*     */     
/*     */ 
/*     */ 
/*  59 */     ltasks[1].addTask(3, new LMM_EntityAIHurtByTarget(this.owner, true));
/*  60 */     ltasks[1].addTask(4, new LMM_EntityAINearestAttackableTarget(this.owner, EntityLivingBase.class, 0, true));
/*     */     
/*  62 */     this.owner.addMaidMode(ltasks, "Fencer", 128);
/*     */     
/*     */ 
/*     */ 
/*  66 */     EntityAITasks[] ltasks2 = new EntityAITasks[2];
/*  67 */     ltasks2[0] = pDefaultMove;
/*  68 */     ltasks2[1] = new EntityAITasks(this.owner.aiProfiler);
/*     */     
/*  70 */     ltasks2[1].addTask(1, new LMM_EntityAIHurtByTarget(this.owner, true));
/*  71 */     ltasks2[1].addTask(2, new LMM_EntityAINearestAttackableTarget(this.owner, EntityLivingBase.class, 0, true));
/*     */     
/*  73 */     this.owner.addMaidMode(ltasks2, "Bloodsucker", 192);
/*     */   }
/*     */   
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/*  78 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/*  79 */     if (litemstack != null) {
/*  80 */       if (((litemstack.getItem() instanceof ItemSword)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Sword", litemstack))) {
/*  81 */         this.owner.setMaidMode("Fencer");
/*  82 */         return true; }
/*  83 */       if (((litemstack.getItem() instanceof ItemAxe)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Axe", litemstack))) {
/*  84 */         this.owner.setMaidMode("Bloodsucker");
/*  85 */         return true;
/*     */       }
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/*  93 */     switch (pMode)
/*     */     {
/*     */     case 128: 
/*  96 */       this.owner.setBloodsuck(false);
/*  97 */       this.owner.aiAttack.isGuard = true;
/*  98 */       return true;
/*     */     
/*     */     case 192: 
/* 101 */       this.owner.setBloodsuck(true);
/* 102 */       return true;
/*     */     }
/*     */     
/* 105 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/* 111 */     int ll = -1;
/* 112 */     double ld = 0.0D;
/*     */     
/*     */ 
/*     */     int li;
/*     */     
/* 117 */     switch (pMode) {
/*     */     case 128: 
/* 119 */       for (li = 0; li < 18;) {
/* 120 */         ItemStack litemstack = this.owner.maidInventory.getStackInSlot(li);
/* 121 */         if (litemstack != null)
/*     */         {
/*     */ 
/* 124 */           if (((litemstack.getItem() instanceof ItemSword)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Sword", litemstack))) {
/* 125 */             return li;
/*     */           }
/*     */           
/*     */ 
/* 129 */           double lld = 1.0D;
/*     */           try {
/* 131 */             lld = MMM_Helper.getAttackVSEntity(litemstack);
/*     */           }
/*     */           catch (Exception e) {}
/*     */           
/* 135 */           if (lld > ld) {
/* 136 */             ll = li;
/* 137 */             ld = lld;
/*     */           }
/*     */         }
/* 119 */         li++; continue;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */         for (li = 0; li < 18; li++) {
/* 143 */           litemstack = this.owner.maidInventory.getStackInSlot(li);
/* 144 */           if (litemstack != null)
/*     */           {
/*     */ 
/* 147 */             if (((litemstack.getItem() instanceof ItemAxe)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Axe", litemstack))) {
/* 148 */               return li;
/*     */             }
/*     */             
/*     */ 
/* 152 */             double lld = 1.0D;
/*     */             try {
/* 154 */               lld = MMM_Helper.getAttackVSEntity(litemstack);
/*     */             }
/*     */             catch (Exception e) {}
/*     */             
/* 158 */             if (lld > ld) {
/* 159 */               ll = li;
/* 160 */               ld = lld;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 166 */     return ll;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/* 172 */     return ((pItemStack.getItem() instanceof ItemSword)) || ((pItemStack.getItem() instanceof ItemAxe));
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Fencer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.FoodStats;
/*     */ 
/*     */ public class LMM_EntityMode_Healer extends LMM_EntityModeBase
/*     */ {
/*     */   public static final int mmode_Healer = 130;
/*     */   
/*     */   public LMM_EntityMode_Healer(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  22 */     super(pEntity);
/*     */   }
/*     */   
/*     */   public int priority()
/*     */   {
/*  27 */     return 3300;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting)
/*     */   {
/*  44 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  45 */     ltasks[0] = pDefaultMove;
/*  46 */     ltasks[1] = new EntityAITasks(this.owner.aiProfiler);
/*     */     
/*     */ 
/*  49 */     ltasks[1].addTask(1, new EntityAIHurtByTarget(this.owner, true));
/*  50 */     this.owner.addMaidMode(ltasks, "Healer", 130);
/*     */   }
/*     */   
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/*  55 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/*  56 */     if ((litemstack != null) && (
/*  57 */       ((litemstack.getItem() instanceof ItemFood)) || (((litemstack.getItem() instanceof ItemPotion)) && (MMM_Helper.hasEffect(litemstack))))) {
/*  58 */       this.owner.setMaidMode("Healer");
/*  59 */       return true;
/*     */     }
/*     */     
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/*  67 */     switch (pMode) {
/*     */     case 130: 
/*  69 */       this.owner.setBloodsuck(false);
/*  70 */       this.owner.aiAttack.setEnable(false);
/*  71 */       this.owner.aiShooting.setEnable(false);
/*  72 */       return true;
/*     */     }
/*     */     
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/*  80 */     switch (pMode)
/*     */     {
/*     */     case 130: 
/*  83 */       for (int i = 0; i < this.owner.maidInventory.getSizeInventory(); i++) {
/*  84 */         ItemStack is = this.owner.maidInventory.getStackInSlot(i);
/*  85 */         if (is != null)
/*     */         {
/*  87 */           if (((is.getItem() instanceof ItemFood)) || (((is.getItem() instanceof ItemPotion)) && (MMM_Helper.hasEffect(is)))) {
/*  88 */             return i;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  93 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/*  98 */     return ((pItemStack.getItem() instanceof ItemFood)) || ((pItemStack.getItem() instanceof ItemPotion));
/*     */   }
/*     */   
/*     */   public void updateAITick(int pMode)
/*     */   {
/* 103 */     if (pMode == 130)
/*     */     {
/* 105 */       if (this.owner.getSwingStatusDominant().canAttack())
/*     */       {
/* 107 */         if ((this.owner.isContractEX()) && (this.owner.mstatMasterDistanceSq < 16.0D) && (this.owner.mstatMasterEntity != null) && (this.owner.mstatMasterEntity.isEntityAlive()) && ((this.owner.mstatMasterEntity instanceof EntityPlayer)) && (this.owner.canEntityBeSeen(this.owner.mstatMasterEntity)))
/*     */         {
/*     */ 
/*     */ 
/* 111 */           EntityPlayer lmaster = this.owner.mstatMasterEntity;
/* 112 */           int h = lmaster.getFoodStats().getFoodLevel();
/*     */           
/* 114 */           if (this.owner.isMaskedMaid())
/*     */           {
/* 116 */             if (lmaster.getHealth() < 9.0F)
/*     */             {
/* 118 */               int j = this.owner.maidInventory.getInventorySlotContainItemPotion(false, Potion.heal.id, lmaster.isEntityUndead());
/* 119 */               if (j > -1) {
/* 120 */                 this.owner.setEquipItem(j);
/*     */                 break label210;
/*     */               }
/*     */             }
/* 124 */             if (h < 18)
/*     */             {
/* 126 */               int j = this.owner.maidInventory.getInventorySlotContainItemFood();
/* 127 */               if (j > -1) {
/* 128 */                 this.owner.setEquipItem(j);
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*     */           label210:
/*     */           
/* 135 */           ItemStack itemstack1 = this.owner.maidInventory.getCurrentItem();
/* 136 */           if (itemstack1 != null) {
/* 137 */             if ((itemstack1.getItem() instanceof ItemFood))
/*     */             {
/* 139 */               if (h < 18) {
/* 140 */                 this.owner.setSwing(10, LMM_EnumSound.healing);
/* 141 */                 itemstack1 = itemstack1.onFoodEaten(this.owner.worldObj, lmaster);
/*     */                 
/* 143 */                 if (itemstack1.stackSize <= 0) {
/* 144 */                   itemstack1 = null;
/*     */                 }
/* 146 */                 this.owner.maidInventory.setInventoryCurrentSlotContents(itemstack1);
/* 147 */                 this.owner.getNextEquipItem();
/*     */               }
/*     */             }
/* 150 */             else if ((itemstack1.getItem() instanceof ItemPotion)) {
/* 151 */               boolean lswing = true;
/*     */               
/* 153 */               List list = ((ItemPotion)itemstack1.getItem()).getEffects(itemstack1);
/* 154 */               Iterator iterator; if (list != null)
/*     */               {
/* 156 */                 for (iterator = list.iterator(); iterator.hasNext();) {
/* 157 */                   PotionEffect potioneffect = (PotionEffect)iterator.next();
/* 158 */                   if (potioneffect.getPotionID() == Potion.heal.id) {
/* 159 */                     if (6 << potioneffect.getAmplifier() <= lmaster.getMaxHealth() - lmaster.getHealth())
/*     */                     {
/* 161 */                       lswing = true;
/*     */                     } else {
/* 163 */                       lswing = false;
/*     */                     }
/*     */                     
/*     */                   }
/* 167 */                   else if ((Potion.potionTypes[potioneffect.getPotionID()].isBadEffect()) || (lmaster.isPotionActive(potioneffect.getPotionID())))
/*     */                   {
/* 169 */                     lswing = false;
/*     */                   }
/*     */                 }
/*     */               }
/*     */               
/*     */ 
/*     */ 
/* 176 */               if (lswing) {
/* 177 */                 this.owner.setSwing(10, LMM_EnumSound.healing_potion);
/* 178 */                 this.owner.usePotionTotarget(lmaster);
/*     */                 
/* 180 */                 this.owner.getNextEquipItem();
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public double getRangeToMaster(int pIndex)
/*     */   {
/* 191 */     return pIndex == 1 ? 9.0D : pIndex == 0 ? 16.0D : 0.0D;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Healer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityMode_Archer extends LMM_EntityModeBase
/*     */ {
/*     */   public static final int mmode_Archer = 131;
/*     */   public static final int mmode_Blazingstar = 195;
/*     */   
/*     */   public int priority()
/*     */   {
/*  24 */     return 3200;
/*     */   }
/*     */   
/*     */   public LMM_EntityMode_Archer(LMM_EntityLittleMaid pEntity) {
/*  28 */     super(pEntity);
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
/*     */   public void init()
/*     */   {
/*  46 */     LMM_TriggerSelect.appendTriggerItem(null, "Bow", "");
/*  47 */     LMM_TriggerSelect.appendTriggerItem(null, "Arrow", "");
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
/*  62 */     this.owner.addMaidMode(ltasks, "Archer", 131);
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
/*  73 */     this.owner.addMaidMode(ltasks2, "Blazingstar", 195);
/*     */   }
/*     */   
/*     */   public boolean changeMode(EntityPlayer pentityplayer)
/*     */   {
/*  78 */     ItemStack litemstack = this.owner.maidInventory.getStackInSlot(0);
/*  79 */     if ((litemstack != null) && (
/*  80 */       ((litemstack.getItem() instanceof ItemBow)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Bow", litemstack)))) {
/*  81 */       if (this.owner.maidInventory.getInventorySlotContainItem(net.minecraft.item.ItemFlintAndSteel.class) > -1) {
/*  82 */         this.owner.setMaidMode("Blazingstar");
/*     */       } else {
/*  84 */         this.owner.setMaidMode("Archer");
/*     */       }
/*  86 */       return true;
/*     */     }
/*     */     
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setMode(int pMode)
/*     */   {
/*  94 */     switch (pMode) {
/*     */     case 131: 
/*  96 */       this.owner.aiAttack.setEnable(false);
/*  97 */       this.owner.aiShooting.setEnable(true);
/*  98 */       this.owner.setBloodsuck(false);
/*  99 */       return true;
/*     */     case 195: 
/* 101 */       this.owner.aiAttack.setEnable(false);
/* 102 */       this.owner.aiShooting.setEnable(true);
/* 103 */       this.owner.setBloodsuck(true);
/* 104 */       return true;
/*     */     }
/*     */     
/* 107 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNextEquipItem(int pMode)
/*     */   {
/* 116 */     switch (pMode) {
/*     */     case 131: 
/*     */     case 195: 
/* 119 */       for (int li = 0; li < 18; li++) {
/* 120 */         ItemStack litemstack = this.owner.maidInventory.getStackInSlot(li);
/* 121 */         if (litemstack != null)
/*     */         {
/*     */ 
/* 124 */           if (((litemstack.getItem() instanceof ItemBow)) || (LMM_TriggerSelect.checkWeapon(this.owner.getMaidMaster(), "Bow", litemstack))) {
/* 125 */             return li;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 131 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean checkItemStack(ItemStack pItemStack)
/*     */   {
/* 136 */     String ls = this.owner.getMaidMaster();
/* 137 */     return ((pItemStack.getItem() instanceof ItemBow)) || (pItemStack.getItem() == Items.arrow) || (LMM_TriggerSelect.checkWeapon(ls, "Bow", pItemStack)) || (LMM_TriggerSelect.checkWeapon(ls, "Arrow", pItemStack));
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUpdate(int pMode)
/*     */   {
/* 143 */     switch (pMode) {
/*     */     case 131: 
/*     */     case 195: 
/* 146 */       this.owner.getWeaponStatus();
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void updateAITick(int pMode)
/*     */   {
/* 155 */     switch (pMode)
/*     */     {
/*     */     case 131: 
/* 158 */       updateGuns();
/* 159 */       break;
/*     */     
/*     */     case 195: 
/* 162 */       updateGuns();
/*     */       
/* 164 */       World lworld = this.owner.worldObj;
/* 165 */       List<Entity> llist = lworld.getEntitiesWithinAABB(Entity.class, this.owner.boundingBox.expand(16.0D, 16.0D, 16.0D));
/* 166 */       for (int li = 0; li < llist.size(); li++) {
/* 167 */         Entity lentity = (Entity)llist.get(li);
/* 168 */         if ((lentity.isEntityAlive()) && (lentity.isBurning()) && (this.owner.getRNG().nextFloat() > 0.9F))
/*     */         {
/* 170 */           int lx = (int)this.owner.posX;
/* 171 */           int ly = (int)this.owner.posY;
/* 172 */           int lz = (int)this.owner.posZ;
/* 173 */           if ((lworld.getBlock(lx, ly, lz) == Blocks.air) || (lworld.getBlock(lx, ly, lz).getMaterial().getCanBurn())) {
/* 174 */             lworld.playSoundEffect(lx + 0.5D, ly + 0.5D, lz + 0.5D, "fire.ignite", 1.0F, this.owner.getRNG().nextFloat() * 0.4F + 0.8F);
/* 175 */             lworld.setBlock(lx, ly, lz, Blocks.fire);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void updateGuns()
/*     */   {
/* 184 */     if ((this.owner.getAttackTarget() == null) || (!this.owner.getAttackTarget().isEntityAlive()))
/*     */     {
/* 186 */       if (!this.owner.weaponReload) {
/* 187 */         if (this.owner.maidAvatar.isUsingItem())
/*     */         {
/* 189 */           if (this.owner.maidAvatar.isItemReload) {
/* 190 */             this.owner.maidAvatar.stopUsingItem();
/* 191 */             LMM_LittleMaidMobX.Debug(String.format("id:%d cancel reload.", new Object[] { Integer.valueOf(this.owner.getEntityId()) }), new Object[0]);
/*     */           } else {
/* 193 */             this.owner.maidAvatar.clearItemInUse();
/* 194 */             LMM_LittleMaidMobX.Debug(String.format("id:%d clear.", new Object[] { Integer.valueOf(this.owner.getEntityId()) }), new Object[0]);
/*     */           }
/*     */         }
/*     */       } else {
/* 198 */         this.owner.mstatAimeBow = true;
/*     */       }
/*     */     }
/* 201 */     if ((this.owner.weaponReload) && (!this.owner.maidAvatar.isUsingItem()))
/*     */     {
/* 203 */       this.owner.maidInventory.getCurrentItem().useItemRightClick(this.owner.worldObj, this.owner.maidAvatar);
/* 204 */       LMM_LittleMaidMobX.Debug("id:%d force reload.", new Object[] { Integer.valueOf(this.owner.getEntityId()) });
/* 205 */       this.owner.mstatAimeBow = true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Archer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
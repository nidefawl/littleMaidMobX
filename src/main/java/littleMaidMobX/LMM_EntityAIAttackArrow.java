/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ import mmmlibx.lib.MMM_Helper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntitySenses;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class LMM_EntityAIAttackArrow extends EntityAIBase implements LMM_IEntityAI
/*     */ {
/*     */   protected boolean fEnable;
/*     */   protected LMM_EntityLittleMaid fMaid;
/*     */   protected LMM_EntityLittleMaidAvatar fAvatar;
/*     */   protected LMM_InventoryLittleMaid fInventory;
/*     */   protected LMM_SwingStatus swingState;
/*     */   protected World worldObj;
/*     */   protected EntityLivingBase fTarget;
/*     */   protected int fForget;
/*     */   
/*     */   public LMM_EntityAIAttackArrow(LMM_EntityLittleMaid pEntityLittleMaid)
/*     */   {
/*  33 */     this.fMaid = pEntityLittleMaid;
/*  34 */     this.fAvatar = pEntityLittleMaid.maidAvatar;
/*  35 */     this.fInventory = pEntityLittleMaid.maidInventory;
/*  36 */     this.swingState = pEntityLittleMaid.getSwingStatusDominant();
/*  37 */     this.worldObj = pEntityLittleMaid.worldObj;
/*  38 */     this.fEnable = false;
/*  39 */     setMutexBits(3);
/*     */   }
/*     */   
/*     */   public boolean shouldExecute()
/*     */   {
/*  44 */     EntityLivingBase entityliving = this.fMaid.getAttackTarget();
/*     */     
/*  46 */     if ((!this.fEnable) || (entityliving == null) || (entityliving.isDead)) {
/*  47 */       this.fMaid.setAttackTarget(null);
/*  48 */       this.fMaid.setTarget(null);
/*  49 */       if (entityliving != null) {
/*  50 */         this.fMaid.getNavigator().clearPathEntity();
/*     */       }
/*  52 */       this.fTarget = null;
/*  53 */       return false;
/*     */     }
/*  55 */     this.fTarget = entityliving;
/*  56 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void startExecuting()
/*     */   {
/*  62 */     super.startExecuting();
/*  63 */     this.fMaid.playSound(this.fMaid.isBloodsuck() ? LMM_EnumSound.findTarget_B : LMM_EnumSound.findTarget_N, false);
/*  64 */     this.swingState = this.fMaid.getSwingStatusDominant();
/*     */   }
/*     */   
/*     */   public boolean continueExecuting()
/*     */   {
/*  69 */     return (shouldExecute()) || ((this.fTarget != null) && (!this.fMaid.getNavigator().noPath()));
/*     */   }
/*     */   
/*     */   public void resetTask()
/*     */   {
/*  74 */     this.fTarget = null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void updateTask()
/*     */   {
/*  80 */     double backupPosX = this.fMaid.posX;
/*  81 */     double backupPosZ = this.fMaid.posZ;
/*     */     
/*     */ 
/*     */ 
/*  85 */     if ((this.fMaid.ridingEntity instanceof net.minecraft.entity.player.EntityPlayer))
/*     */     {
/*  87 */       double dtx = this.fTarget.posX - this.fMaid.posX;
/*  88 */       double dtz = this.fTarget.posZ - this.fMaid.posZ;
/*  89 */       double distTarget = MathHelper.sqrt_double(dtx * dtx + dtz * dtz);
/*  90 */       this.fMaid.posX += dtx / distTarget * 1.0D;
/*  91 */       this.fMaid.posZ += dtz / distTarget * 1.0D;
/*     */     }
/*     */     
/*  94 */     double lrange = 225.0D;
/*  95 */     double ldist = this.fMaid.getDistanceSqToEntity(this.fTarget);
/*  96 */     boolean lsee = this.fMaid.getEntitySenses().canSee(this.fTarget);
/*     */     
/*     */ 
/*  99 */     if (lsee) {
/* 100 */       this.fForget = 0;
/*     */     } else {
/* 102 */       this.fForget += 1;
/*     */     }
/*     */     
/*     */ 
/* 106 */     this.fMaid.getLookHelper().setLookPositionWithEntity(this.fTarget, 30.0F, 30.0F);
/*     */     
/* 108 */     if (ldist < lrange)
/*     */     {
/* 110 */       double atx = this.fTarget.posX - this.fMaid.posX;
/* 111 */       double aty = this.fTarget.posY - this.fMaid.posY;
/* 112 */       double atz = this.fTarget.posZ - this.fMaid.posZ;
/* 113 */       if (this.fTarget.isEntityAlive()) {
/* 114 */         ItemStack litemstack = this.fMaid.getCurrentEquippedItem();
/*     */         
/* 116 */         double atl = atx * atx + aty * aty + atz * atz;
/* 117 */         double il = -1.0D;
/* 118 */         double milsq = 10.0D;
/* 119 */         Entity masterEntity = this.fMaid.getMaidMasterEntity();
/* 120 */         if ((masterEntity != null) && (!this.fMaid.isPlaying()))
/*     */         {
/* 122 */           double amx = masterEntity.posX - this.fMaid.posX;
/* 123 */           double amy = masterEntity.posY - this.fMaid.posY;
/* 124 */           double amz = masterEntity.posZ - this.fMaid.posZ;
/*     */           
/*     */ 
/* 127 */           il = (amx * atx + amy * aty + amz * atz) / atl;
/*     */           
/*     */ 
/* 130 */           double mix = this.fMaid.posX + il * atx - masterEntity.posX;
/* 131 */           double miy = this.fMaid.posY + il * aty - masterEntity.posY;
/* 132 */           double miz = this.fMaid.posZ + il * atz - masterEntity.posZ;
/*     */           
/* 134 */           milsq = mix * mix + miy * miy + miz * miz;
/*     */         }
/*     */         
/*     */ 
/* 138 */         if ((litemstack != null) && (!(litemstack.getItem() instanceof ItemFood)) && (!this.fMaid.weaponReload)) {
/* 139 */           int lastentityid = this.worldObj.loadedEntityList.size();
/* 140 */           int itemcount = litemstack.stackSize;
/* 141 */           this.fMaid.mstatAimeBow = true;
/* 142 */           this.fAvatar.getValueVectorFire(atx, aty, atz, atl);
/*     */           
/* 144 */           boolean lcanattack = true;
/* 145 */           boolean ldotarget = false;
/* 146 */           double tpr = Math.sqrt(atl);
/* 147 */           Entity lentity = MMM_Helper.getRayTraceEntity(this.fMaid.maidAvatar, tpr + 1.0D, 1.0F, 1.0F);
/* 148 */           Item helmid = !this.fMaid.isMaskedMaid() ? null : this.fInventory.armorInventory[3].getItem();
/* 149 */           if ((helmid == Items.diamond_helmet) || (helmid == Items.golden_helmet))
/*     */           {
/* 151 */             if ((lentity != null) && (this.fMaid.getIFF(lentity))) {
/* 152 */               lcanattack = false;
/*     */             }
/*     */           }
/*     */           
/* 156 */           if (lentity == this.fTarget) {
/* 157 */             ldotarget = true;
/*     */           }
/* 159 */           lcanattack &= ((milsq > 3.0D) || (il < 0.0D));
/* 160 */           lcanattack &= ldotarget;
/*     */           
/* 162 */           if (!lcanattack)
/*     */           {
/* 164 */             double tpx = this.fMaid.posX;
/* 165 */             double tpy = this.fMaid.posY;
/* 166 */             double tpz = this.fMaid.posZ;
/*     */             
/* 168 */             tpr *= 0.5D;
/* 169 */             if (this.fMaid.isBloodsuck())
/*     */             {
/* 171 */               tpx += atz / tpr;
/* 172 */               tpz -= atx / tpr;
/*     */             }
/*     */             else {
/* 175 */               tpx -= atz / tpr;
/* 176 */               tpz += atx / tpr;
/*     */             }
/* 178 */             this.fMaid.getNavigator().tryMoveToXYZ(tpx, tpy, tpz, 1.0D);
/*     */           }
/* 180 */           else if ((lsee & ldist < 100.0D)) {
/* 181 */             this.fMaid.getNavigator().clearPathEntity();
/*     */           }
/*     */           
/*     */ 
/* 185 */           lcanattack &= lsee;
/*     */           
/* 187 */           if (((this.fMaid.weaponFullAuto) && (!lcanattack)) || ((lcanattack) && (this.fMaid.getSwingStatusDominant().canAttack()) && (this.fAvatar.isItemTrigger)))
/*     */           {
/*     */ 
/* 190 */             LMM_LittleMaidMobX.Debug("id:%d shoot.", new Object[] { Integer.valueOf(this.fMaid.getEntityId()) });
/* 191 */             this.fAvatar.stopUsingItem();
/* 192 */             this.fMaid.setSwing(30, LMM_EnumSound.shoot);
/*     */ 
/*     */           }
/* 195 */           else if (litemstack.getMaxItemUseDuration() > 500)
/*     */           {
/*     */ 
/* 198 */             if (!this.fAvatar.isUsingItemLittleMaid())
/*     */             {
/* 200 */               if ((!this.fMaid.weaponFullAuto) || (lcanattack))
/*     */               {
/* 202 */                 int at = (helmid == Items.iron_helmet) || (helmid == Items.diamond_helmet) ? 26 : 16;
/* 203 */                 if (this.swingState.attackTime < at) {
/* 204 */                   this.fMaid.setSwing(at, LMM_EnumSound.sighting);
/* 205 */                   litemstack = litemstack.useItemRightClick(this.worldObj, this.fAvatar);
/* 206 */                   LMM_LittleMaidMobX.Debug("id:%d redygun.", new Object[] { Integer.valueOf(this.fMaid.getEntityId()) });
/*     */                 }
/*     */               } else {
/* 209 */                 LMM_LittleMaidMobX.Debug(String.format("ID:%d-friendly fire FullAuto.", new Object[] { Integer.valueOf(this.fMaid.getEntityId()) }), new Object[0]);
/*     */               }
/*     */             }
/*     */           }
/* 213 */           else if (litemstack.getMaxItemUseDuration() == 0)
/*     */           {
/* 215 */             if ((this.swingState.canAttack()) && (!this.fAvatar.isUsingItem())) {
/* 216 */               if (lcanattack) {
/* 217 */                 litemstack = litemstack.useItemRightClick(this.worldObj, this.fAvatar);
/*     */                 
/* 219 */                 this.fMaid.mstatAimeBow = false;
/* 220 */                 this.fMaid.setSwing(10, litemstack.stackSize == itemcount ? LMM_EnumSound.shoot_burst : LMM_EnumSound.Null);
/* 221 */                 LMM_LittleMaidMobX.Debug(String.format("id:%d throw weapon.(%d:%f:%f)", new Object[] { Integer.valueOf(this.fMaid.getEntityId()), Integer.valueOf(this.swingState.attackTime), Float.valueOf(this.fMaid.rotationYaw), Float.valueOf(this.fMaid.rotationYawHead) }), new Object[0]);
/*     */               } else {
/* 223 */                 LMM_LittleMaidMobX.Debug(String.format("ID:%d-friendly fire throw weapon.", new Object[] { Integer.valueOf(this.fMaid.getEntityId()) }), new Object[0]);
/*     */               }
/*     */             }
/*     */           }
/*     */           else {
/* 228 */             if (!this.fAvatar.isUsingItemLittleMaid()) {
/* 229 */               litemstack = litemstack.useItemRightClick(this.worldObj, this.fAvatar);
/* 230 */               LMM_LittleMaidMobX.Debug(String.format("%d reload.", new Object[] { Integer.valueOf(this.fMaid.getEntityId()) }), new Object[0]);
/*     */             }
/*     */             
/* 233 */             this.swingState.attackTime = 5;
/*     */           }
/*     */           
/*     */ 
/* 237 */           this.fAvatar.setValueVector();
/*     */           
/* 239 */           if (litemstack.stackSize <= 0) {
/* 240 */             this.fMaid.destroyCurrentEquippedItem();
/* 241 */             this.fMaid.getNextEquipItem();
/*     */           } else {
/* 243 */             this.fInventory.setInventoryCurrentSlotContents(litemstack);
/*     */           }
/*     */           
/*     */ 
/* 247 */           List<Entity> newentitys = this.worldObj.loadedEntityList.subList(lastentityid, this.worldObj.loadedEntityList.size());
/* 248 */           boolean shootingflag = false;
/* 249 */           if ((newentitys != null) && (newentitys.size() > 0)) {
/* 250 */             LMM_LittleMaidMobX.Debug(String.format("new FO entity %d", new Object[] { Integer.valueOf(newentitys.size()) }), new Object[0]);
/* 251 */             for (Entity te : newentitys) {
/* 252 */               if (te.isDead) {
/* 253 */                 shootingflag = true;
/*     */               }
/*     */               else {
/*     */                 try
/*     */                 {
/* 258 */                   Field[] fd = te.getClass().getDeclaredFields();
/*     */                   
/* 260 */                   for (Field ff : fd)
/*     */                   {
/* 262 */                     ff.setAccessible(true);
/* 263 */                     Object eo = ff.get(te);
/* 264 */                     if (eo.equals(this.fAvatar)) {
/* 265 */                       ff.set(te, this);
/* 266 */                       LMM_LittleMaidMobX.Debug("Replace FO Owner.", new Object[0]);
/*     */                     }
/*     */                   }
/*     */                 }
/*     */                 catch (Exception exception) {}
/*     */               }
/*     */             }
/*     */           }
/*     */           
/* 275 */           if (shootingflag) {
/* 276 */             for (Object obj : this.worldObj.loadedEntityList) {
/* 277 */               if (((obj instanceof EntityCreature)) && (!(obj instanceof LMM_EntityLittleMaid))) {
/* 278 */                 EntityCreature ecr = (EntityCreature)obj;
/* 279 */                 if (ecr.getEntityToAttack() == this.fAvatar) {
/* 280 */                   ecr.setTarget(this.fMaid);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 289 */       if (this.fMaid.getNavigator().noPath()) {
/* 290 */         this.fMaid.getNavigator().tryMoveToEntityLiving(this.fTarget, 1.0D);
/*     */       }
/* 292 */       if (this.fMaid.getNavigator().noPath()) {
/* 293 */         LMM_LittleMaidMobX.Debug("id:%d Target renge out.", new Object[] { Integer.valueOf(this.fMaid.getEntityId()) });
/* 294 */         this.fMaid.setAttackTarget(null);
/*     */       }
/* 296 */       if ((this.fMaid.weaponFullAuto) && (this.fAvatar.isItemTrigger)) {
/* 297 */         this.fAvatar.stopUsingItem();
/*     */       } else {
/* 299 */         this.fAvatar.clearItemInUse();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 306 */     this.fMaid.posX = backupPosX;
/* 307 */     this.fMaid.posZ = backupPosZ;
/*     */   }
/*     */   
/*     */   public void setEnable(boolean pFlag)
/*     */   {
/* 312 */     this.fEnable = pFlag;
/*     */   }
/*     */   
/*     */   public boolean getEnable()
/*     */   {
/* 317 */     return this.fEnable;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityAIAttackArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
/*     */ package mmmlibx.lib.guns;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemBulletBase
/*     */   extends Item
/*     */ {
/*     */   protected static final String Tag_Speed = "speed";
/*     */   protected static final String Tag_Reaction = "reaction";
/*     */   protected static final String Tag_Power = "power";
/*     */   public String soundFire;
/*     */   float speed;
/*     */   float reaction;
/*     */   float power;
/*     */   
/*     */   public ItemBulletBase()
/*     */   {
/*  32 */     setCreativeTab(CreativeTabs.tabCombat);
/*     */     
/*  34 */     this.speed = 25.0F;
/*  35 */     this.reaction = 1.0F;
/*  36 */     this.power = 0.2F;
/*     */   }
/*     */   
/*     */   public float getSpeed(ItemStack pBullet) {
/*  40 */     if ((pBullet.hasTagCompound()) && (pBullet.getTagCompound().hasKey("speed"))) {
/*  41 */       return pBullet.getTagCompound().getFloat("speed");
/*     */     }
/*  43 */     return this.speed;
/*     */   }
/*     */   
/*     */   public float getReaction(ItemStack pBullet) {
/*  47 */     if ((pBullet.hasTagCompound()) && (pBullet.getTagCompound().hasKey("reaction"))) {
/*  48 */       return pBullet.getTagCompound().getFloat("reaction");
/*     */     }
/*  50 */     return this.reaction;
/*     */   }
/*     */   
/*     */   public float getPower(ItemStack pBullet) {
/*  54 */     if ((pBullet.hasTagCompound()) && (pBullet.getTagCompound().hasKey("power"))) {
/*  55 */       return pBullet.getTagCompound().getFloat("power");
/*     */     }
/*  57 */     return this.power;
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
/*     */   public EntityBulletBase getBulletEntity(ItemStack pGun, ItemStack pBullet, World pWorld, EntityPlayer pPlayer, int pUseCount)
/*     */   {
/*  70 */     ItemGunsBase lgun = (ItemGunsBase)pGun.getItem();
/*  71 */     return new EntityBulletBase(pWorld, pPlayer, pGun, pBullet, getSpeed(pBullet) * lgun.getEfficiency(pGun, pPlayer, pUseCount), lgun.getAccuracy(pGun, pPlayer, pUseCount));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void playSoundFire(World pWorld, EntityPlayer pPlayer, ItemStack pGun, ItemStack pBullet)
/*     */   {
/*  83 */     if ((this.soundFire != null) && (!this.soundFire.isEmpty())) {
/*  84 */       Item lgun = pGun.getItem();
/*  85 */       float lvol = 0.5F;
/*  86 */       if ((lgun instanceof ItemGunsBase)) {
/*  87 */         lvol = ((ItemGunsBase)lgun).volume;
/*     */       }
/*  89 */       pWorld.playSoundAtEntity(pPlayer, this.soundFire, lvol, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getBulletColor(ItemStack pBullet)
/*     */   {
/* 100 */     return 8404992;
/*     */   }
/*     */   
/*     */   public float getHitDamage(EntityBulletBase pBullrtEntity, Entity pTargetEntity, ItemStack pBullet) {
/* 104 */     float ldam = pBullrtEntity.speed * getPower(pBullet);
/* 105 */     GunsBase.Debug("damage: %f", new Object[] { Float.valueOf(ldam) });
/* 106 */     return ldam;
/*     */   }
/*     */   
/*     */   public boolean onHitEntity(MovingObjectPosition var1, EntityBulletBase pBullrtEntity, Entity pTargetEntity) {
/* 110 */     pTargetEntity.hurtResistantTime = 0;
/* 111 */     pTargetEntity.attackEntityFrom(DamageSource.causeThrownDamage(pBullrtEntity, pBullrtEntity.getThrower()), getHitDamage(pBullrtEntity, pTargetEntity, pBullrtEntity.bullet));
/*     */     
/* 113 */     if (!pBullrtEntity.worldObj.isRemote) {
/* 114 */       pBullrtEntity.setDead();
/*     */     }
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/guns/ItemBulletBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
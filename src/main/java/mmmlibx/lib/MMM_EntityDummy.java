/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMM_EntityDummy
/*     */   extends Entity
/*     */ {
/*     */   private int livecount;
/*  16 */   private final int maxlivecount = 16;
/*     */   
/*     */   private int entityColor;
/*     */   
/*     */   public Entity entityOwner;
/*     */   
/*  22 */   public static boolean isEnable = false;
/*     */   
/*  24 */   public static List<MMM_EntityDummy> appendList = new ArrayList();
/*     */   
/*     */   public MMM_EntityDummy(World world, int color, Entity owner)
/*     */   {
/*  28 */     super(world);
/*  29 */     this.livecount = 16;
/*  30 */     this.entityColor = color;
/*     */     
/*  32 */     this.entityOwner = owner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void entityInit() {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUpdate()
/*     */   {
/*  51 */     if ((--this.livecount < 0) || (!isEnable)) {
/*  52 */       setDead();
/*     */     }
/*     */   }
/*     */   
/*     */   public float getAlpha(float max) {
/*  57 */     if (this.livecount >= 0) {
/*  58 */       return max * this.livecount / 16.0F;
/*     */     }
/*  60 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public int getColor()
/*     */   {
/*  65 */     return this.entityColor;
/*     */   }
/*     */   
/*     */   public boolean setOwnerdEntityDead(Entity entity) {
/*  69 */     if (this.entityOwner == entity) {
/*  70 */       setDead();
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void clearDummyEntity(Entity entity)
/*     */   {
/*  80 */     if (!isEnable) return;
/*  81 */     if (!MMM_Helper.isClient) { return;
/*     */     }
/*  83 */     List<Entity> liste = entity.worldObj.loadedEntityList;
/*  84 */     for (Entity entity1 : liste) {
/*  85 */       if ((entity1 instanceof MMM_EntityDummy)) {
/*  86 */         ((MMM_EntityDummy)entity1).setOwnerdEntityDead(entity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void setDummyEntity(Entity owner, int color, double posx, double posy, double posz)
/*     */   {
/*  95 */     if (!isEnable) return;
/*  96 */     if (!MMM_Helper.isClient) { return;
/*     */     }
/*     */     
/*  99 */     if (owner.worldObj.isRemote) {
/* 100 */       MMMLib.Debug("L", new Object[0]);
/*     */     }
/*     */     
/* 103 */     MMM_EntityDummy ed = new MMM_EntityDummy(Client.getMCtheWorld(), color, owner);
/* 104 */     ed.setPosition(posx, posy, posz);
/* 105 */     appendList.add(ed);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_EntityDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
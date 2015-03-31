/*      */ package littleMaidMobX;
/*      */ 
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import mmmlibx.lib.ITextureEntity;
/*      */ import mmmlibx.lib.MMMLib;
/*      */ import mmmlibx.lib.MMM_Counter;
/*      */ import mmmlibx.lib.MMM_Helper;
/*      */ import mmmlibx.lib.MMM_TextureBox;
/*      */ import mmmlibx.lib.MMM_TextureBoxBase;
/*      */ import mmmlibx.lib.MMM_TextureData;
/*      */ import mmmlibx.lib.MMM_TextureManager;
/*      */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockDoublePlant;
/*      */ import net.minecraft.block.BlockLeaves;
/*      */ import net.minecraft.block.BlockPumpkin;
/*      */ import net.minecraft.entity.DataWatcher;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EntityTracker;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*      */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*      */ import net.minecraft.entity.ai.EntityAIPanic;
/*      */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*      */ import net.minecraft.entity.ai.EntityAITasks;
/*      */ import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
/*      */ import net.minecraft.entity.ai.EntityAITempt;
/*      */ import net.minecraft.entity.ai.EntityLookHelper;
/*      */ import net.minecraft.entity.ai.EntitySenses;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityMob;
/*      */ import net.minecraft.entity.passive.EntityChicken;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.passive.EntitySquid;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.PlayerCapabilities;
/*      */ import net.minecraft.entity.projectile.EntityArrow;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemArmor;
/*      */ import net.minecraft.item.ItemBlock;
/*      */ import net.minecraft.item.ItemPotion;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.NetHandlerPlayServer;
/*      */ import net.minecraft.network.play.server.S04PacketEntityEquipment;
/*      */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*      */ import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
/*      */ import net.minecraft.pathfinding.PathEntity;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import wrapper.W_Common;
/*      */ 
/*      */ public class LMM_EntityLittleMaid extends EntityTameable implements ITextureEntity
/*      */ {
/*   93 */   protected static final UUID maidUUID = UUID.fromString("e2361272-644a-3028-8416-8536667f0efb");
/*      */   
/*   95 */   protected static final UUID maidUUIDSneak = UUID.fromString("5649cf91-29bb-3a0c-8c31-b170a1045560");
/*   96 */   protected static AttributeModifier attCombatSpeed = new AttributeModifier(maidUUID, "Combat speed boost", 0.07D, 0).setSaved(false);
/*   97 */   protected static AttributeModifier attAxeAmp = new AttributeModifier(maidUUID, "Axe Attack boost", 0.5D, 1).setSaved(false);
/*   98 */   protected static AttributeModifier attSneakingSpeed = new AttributeModifier(maidUUIDSneak, "Sneking speed ampd", -0.4D, 2).setSaved(false);
/*      */   
/*      */   protected int maidContractLimit;
/*      */   
/*      */   protected long maidAnniversary;
/*      */   
/*      */   protected int maidDominantArm;
/*      */   
/*      */   public MMM_TextureData textureData;
/*      */   
/*  108 */   public Map<String, mmmlibx.lib.multiModel.model.mc162.EquippedStabilizer> maidStabilizer = new HashMap();
/*      */   
/*      */   public LMM_InventoryLittleMaid maidInventory;
/*      */   
/*      */   public LMM_EntityLittleMaidAvatar maidAvatar;
/*      */   
/*      */   public LMM_EntityCaps maidCaps;
/*      */   public List<LMM_EntityModeBase> maidEntityModeList;
/*      */   public Map<Integer, EntityAITasks[]> maidModeList;
/*      */   public Map<String, Integer> maidModeIndexList;
/*      */   public int maidMode;
/*      */   public boolean maidTracer;
/*      */   public boolean maidFreedom;
/*      */   public boolean maidWait;
/*      */   public int homeWorld;
/*  123 */   protected int[][] maidTiles = new int[9][3];
/*  124 */   public int[] maidTile = new int[3];
/*      */   
/*      */   public TileEntity maidTileEntity;
/*      */   
/*      */   protected EntityPlayer mstatMasterEntity;
/*      */   
/*      */   protected double mstatMasterDistanceSq;
/*      */   
/*      */   protected Entity mstatgotcha;
/*      */   
/*      */   protected boolean mstatBloodsuck;
/*      */   
/*      */   protected boolean mstatClockMaid;
/*      */   
/*      */   protected int mstatMaskSelect;
/*      */   
/*      */   protected boolean mstatCamouflage;
/*      */   
/*      */   protected boolean mstatPlanter;
/*      */   
/*      */   protected int mstatWaitCount;
/*      */   
/*      */   protected int mstatTime;
/*      */   
/*      */   protected MMM_Counter maidOverDriveTime;
/*      */   
/*      */   protected boolean mstatFirstLook;
/*      */   
/*      */   protected boolean mstatLookSuger;
/*      */   
/*      */   protected MMM_Counter mstatWorkingCount;
/*      */   
/*      */   protected int mstatPlayingRole;
/*      */   
/*      */   protected int mstatWorkingInt;
/*      */   
/*      */   protected String mstatModeName;
/*      */   protected boolean mstatOpenInventory;
/*      */   public LMM_SwingStatus[] mstatSwingStatus;
/*      */   public boolean mstatAimeBow;
/*      */   private boolean looksWithInterest;
/*      */   private boolean looksWithInterestAXIS;
/*      */   private float rotateAngleHead;
/*      */   private float prevRotateAngleHead;
/*      */   public float entityIdFactor;
/*      */   public boolean weaponFullAuto;
/*      */   public boolean weaponReload;
/*      */   public boolean maidCamouflage;
/*      */   protected LMM_EnumSound maidDamegeSound;
/*      */   protected int maidSoundInterval;
/*      */   protected float maidSoundRate;
/*  175 */   private int firstload = 1;
/*  176 */   public String statusMessage = "";
/*      */   
/*      */   public EntityAITempt aiTempt;
/*      */   
/*      */   public LMM_EntityAIBeg aiBeg;
/*      */   
/*      */   public LMM_EntityAIBegMove aiBegMove;
/*      */   
/*      */   public EntityAIOpenDoor aiOpenDoor;
/*      */   public EntityAIRestrictOpenDoor aiCloseDoor;
/*      */   public LMM_EntityAIAvoidPlayer aiAvoidPlayer;
/*      */   public LMM_EntityAIFollowOwner aiFollow;
/*      */   public LMM_EntityAIAttackOnCollide aiAttack;
/*      */   public LMM_EntityAIAttackArrow aiShooting;
/*      */   public LMM_EntityAICollectItem aiCollectItem;
/*      */   public LMM_EntityAIRestrictRain aiRestrictRain;
/*      */   public LMM_EntityAIFleeRain aiFreeRain;
/*      */   public LMM_EntityAIWander aiWander;
/*      */   public LMM_EntityAIJumpToMaster aiJumpTo;
/*      */   public LMM_EntityAIFindBlock aiFindBlock;
/*      */   public LMM_EntityAITracerMove aiTracer;
/*      */   public net.minecraft.entity.ai.EntityAISwimming aiSwiming;
/*      */   public EntityAIPanic aiPanic;
/*      */   protected LMM_EntityModeBase maidActiveModeClass;
/*      */   public net.minecraft.profiler.Profiler aiProfiler;
/*      */   
/*      */   public LMM_EntityLittleMaid(World par1World)
/*      */   {
/*  204 */     super(par1World);
/*      */     
/*  206 */     this.maidInventory = new LMM_InventoryLittleMaid(this);
/*  207 */     if (par1World != null) {
/*  208 */       this.maidAvatar = new LMM_EntityLittleMaidAvatar(par1World, this);
/*      */     }
/*  210 */     this.mstatOpenInventory = false;
/*      */     
/*  212 */     this.mstatTime = 6000;
/*  213 */     this.maidOverDriveTime = new MMM_Counter(5, 300, -100);
/*  214 */     this.mstatWorkingCount = new MMM_Counter(11, 10, -10);
/*      */     
/*      */ 
/*  217 */     this.maidCaps = new LMM_EntityCaps(this);
/*      */     
/*      */ 
/*  220 */     this.textureData = new MMM_TextureData(this, this.maidCaps);
/*  221 */     this.textureData.setColor(12);
/*  222 */     MMM_TextureBox[] ltb = new MMM_TextureBox[2];
/*  223 */     ltb[0] = (ltb[1] = MMM_TextureManager.instance.getDefaultTexture(this));
/*  224 */     setTexturePackName(ltb);
/*      */     
/*  226 */     this.entityIdFactor = (getEntityId() * 70);
/*      */     
/*  228 */     this.mstatSwingStatus = new LMM_SwingStatus[] { new LMM_SwingStatus(), new LMM_SwingStatus() };
/*  229 */     setDominantArm(this.rand.nextInt(this.mstatSwingStatus.length));
/*      */     
/*      */ 
/*      */ 
/*  233 */     this.maidDamegeSound = LMM_EnumSound.hurt;
/*  234 */     this.maidSoundInterval = 0;
/*      */     
/*      */ 
/*  237 */     setHealth(15.0F);
/*      */     
/*      */ 
/*  240 */     getNavigator().setAvoidsWater(true);
/*  241 */     getNavigator().setBreakDoors(true);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  250 */     this.maidEntityModeList = LMM_EntityModeManager.getModeList(this);
/*      */     
/*  252 */     this.maidActiveModeClass = null;
/*  253 */     this.maidModeList = new HashMap();
/*  254 */     this.maidModeIndexList = new HashMap();
/*  255 */     initModeList();
/*  256 */     this.mstatModeName = "";
/*  257 */     this.maidMode = 65535;
/*      */     
/*  259 */     for (LMM_EntityModeBase lem : this.maidEntityModeList) {
/*  260 */       lem.initEntity();
/*      */     }
/*      */   }
/*      */   
/*      */   public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData)
/*      */   {
/*      */     String ls;
/*      */     String ls;
/*  268 */     if (LMM_LittleMaidMobX.cfg_defaultTexture.isEmpty()) {
/*  269 */       ls = MMM_TextureManager.instance.getRandomTextureString(this.rand);
/*      */     } else {
/*  271 */       ls = LMM_LittleMaidMobX.cfg_defaultTexture;
/*      */     }
/*  273 */     this.textureData.setTextureInitServer(ls);
/*  274 */     LMM_LittleMaidMobX.Debug("init-ID:%d, %s:%d", new Object[] { Integer.valueOf(getEntityId()), this.textureData.textureBox[0].textureName, Integer.valueOf(this.textureData.getColor()) });
/*  275 */     setTexturePackIndex(this.textureData.getColor(), this.textureData.textureIndex);
/*  276 */     setMaidMode("Wild");
/*  277 */     return super.onSpawnWithEgg(par1EntityLivingData);
/*      */   }
/*      */   
/*      */   protected void applyEntityAttributes()
/*      */   {
/*  282 */     super.applyEntityAttributes();
/*      */     
/*  284 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
/*      */     
/*  286 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
/*      */     
/*  288 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
/*      */   }
/*      */   
/*      */   protected void entityInit()
/*      */   {
/*  293 */     super.entityInit();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  314 */     this.dataWatcher.addObject(18, Float.valueOf(0.0F));
/*      */     
/*      */ 
/*      */ 
/*  318 */     this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
/*      */     
/*  320 */     this.dataWatcher.addObject(20, Integer.valueOf(0));
/*      */     
/*  322 */     this.dataWatcher.addObject(21, Integer.valueOf(0));
/*      */     
/*  324 */     this.dataWatcher.addObject(22, Integer.valueOf(0));
/*      */     
/*  326 */     this.dataWatcher.addObject(23, Integer.valueOf(0));
/*      */     
/*  328 */     this.dataWatcher.addObject(24, Short.valueOf((short)0));
/*      */     
/*  330 */     this.dataWatcher.addObject(25, Byte.valueOf((byte)0));
/*      */     
/*  332 */     this.dataWatcher.addObject(26, Integer.valueOf(0));
/*      */     
/*  334 */     this.dataWatcher.addObject(27, Integer.valueOf(0));
/*      */     
/*      */ 
/*      */ 
/*  338 */     this.dataWatcher.addObject(31, new Integer(0));
/*      */   }
/*      */   
/*      */   public void initModeList()
/*      */   {
/*  343 */     this.aiBeg = new LMM_EntityAIBeg(this, 8.0F);
/*  344 */     this.aiBegMove = new LMM_EntityAIBegMove(this, 1.0F);
/*  345 */     this.aiOpenDoor = new EntityAIOpenDoor(this, true);
/*  346 */     this.aiCloseDoor = new EntityAIRestrictOpenDoor(this);
/*  347 */     this.aiAvoidPlayer = new LMM_EntityAIAvoidPlayer(this, 1.0F, 3);
/*  348 */     this.aiFollow = new LMM_EntityAIFollowOwner(this, 1.0F, 36.0D, 25.0D, 81.0D);
/*  349 */     this.aiAttack = new LMM_EntityAIAttackOnCollide(this, 1.0F, true);
/*  350 */     this.aiShooting = new LMM_EntityAIAttackArrow(this);
/*  351 */     this.aiCollectItem = new LMM_EntityAICollectItem(this, 1.0F);
/*  352 */     this.aiRestrictRain = new LMM_EntityAIRestrictRain(this);
/*  353 */     this.aiFreeRain = new LMM_EntityAIFleeRain(this, 1.0F);
/*  354 */     this.aiWander = new LMM_EntityAIWander(this, 1.0F);
/*  355 */     this.aiJumpTo = new LMM_EntityAIJumpToMaster(this);
/*  356 */     this.aiFindBlock = new LMM_EntityAIFindBlock(this);
/*  357 */     this.aiSwiming = new LMM_EntityAISwimming(this);
/*  358 */     this.aiPanic = new EntityAIPanic(this, 2.0D);
/*  359 */     this.aiTracer = new LMM_EntityAITracerMove(this);
/*  360 */     this.aiSit = new LMM_EntityAIWait(this);
/*      */     
/*      */ 
/*  363 */     this.aiProfiler = ((this.worldObj != null) && (this.worldObj.theProfiler != null) ? this.worldObj.theProfiler : null);
/*      */     
/*      */ 
/*  366 */     EntityAITasks[] ltasks = new EntityAITasks[2];
/*  367 */     ltasks[0] = new EntityAITasks(this.aiProfiler);
/*  368 */     ltasks[1] = new EntityAITasks(this.aiProfiler);
/*      */     
/*      */ 
/*  371 */     ltasks[0].addTask(1, this.aiSwiming);
/*  372 */     ltasks[0].addTask(2, this.aiSit);
/*  373 */     ltasks[0].addTask(3, this.aiJumpTo);
/*  374 */     ltasks[0].addTask(4, this.aiFindBlock);
/*  375 */     ltasks[0].addTask(6, this.aiAttack);
/*  376 */     ltasks[0].addTask(7, this.aiShooting);
/*      */     
/*  378 */     ltasks[0].addTask(10, this.aiBeg);
/*  379 */     ltasks[0].addTask(11, this.aiBegMove);
/*  380 */     ltasks[0].addTask(20, this.aiAvoidPlayer);
/*  381 */     ltasks[0].addTask(21, this.aiFreeRain);
/*  382 */     ltasks[0].addTask(22, this.aiCollectItem);
/*      */     
/*  384 */     ltasks[0].addTask(30, this.aiTracer);
/*  385 */     ltasks[0].addTask(31, this.aiFollow);
/*  386 */     ltasks[0].addTask(32, this.aiWander);
/*  387 */     ltasks[0].addTask(33, new EntityAILeapAtTarget(this, 0.3F));
/*      */     
/*  389 */     ltasks[0].addTask(40, this.aiCloseDoor);
/*  390 */     ltasks[0].addTask(41, this.aiOpenDoor);
/*  391 */     ltasks[0].addTask(42, this.aiRestrictRain);
/*      */     
/*  393 */     ltasks[0].addTask(51, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityLivingBase.class, 10.0F));
/*  394 */     ltasks[0].addTask(52, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*      */     
/*      */ 
/*  397 */     for (LMM_EntityModeBase ieml : this.maidEntityModeList) {
/*  398 */       ieml.addEntityMode(ltasks[0], ltasks[1]);
/*      */     }
/*      */   }
/*      */   
/*      */   public void addMaidMode(EntityAITasks[] peaiTasks, String pmodeName, int pmodeIndex)
/*      */   {
/*  404 */     this.maidModeList.put(Integer.valueOf(pmodeIndex), peaiTasks);
/*  405 */     this.maidModeIndexList.put(pmodeName, Integer.valueOf(pmodeIndex));
/*      */   }
/*      */   
/*      */   public int getMaidModeInt()
/*      */   {
/*  410 */     return this.maidMode;
/*      */   }
/*      */   
/*      */   public String getMaidModeString() {
/*  414 */     if (!isContract())
/*  415 */       return getMaidModeString(this.maidMode);
/*  416 */     if (!isRemainsContract())
/*  417 */       return "Strike";
/*  418 */     if (isMaidWait())
/*  419 */       return "Wait";
/*  420 */     if (isPlaying()) {
/*  421 */       return "Playing";
/*      */     }
/*  423 */     String ls = getMaidModeString(this.maidMode);
/*  424 */     if (this.maidOverDriveTime.isEnable()) {
/*  425 */       ls = "D-" + ls;
/*      */     }
/*  427 */     else if (isTracer()) {
/*  428 */       ls = "T-" + ls;
/*      */     }
/*  430 */     else if (isFreedom()) {
/*  431 */       ls = "F-" + ls;
/*      */     }
/*  433 */     return ls;
/*      */   }
/*      */   
/*      */ 
/*      */   public String getMaidModeString(int pindex)
/*      */   {
/*  439 */     String ls = "";
/*  440 */     for (Map.Entry<String, Integer> le : this.maidModeIndexList.entrySet()) {
/*  441 */       if (((Integer)le.getValue()).intValue() == pindex) {
/*  442 */         ls = (String)le.getKey();
/*  443 */         break;
/*      */       }
/*      */     }
/*  446 */     return ls;
/*      */   }
/*      */   
/*      */   public boolean setMaidMode(String pname) {
/*  450 */     return setMaidMode(pname, false);
/*      */   }
/*      */   
/*      */   public boolean setMaidMode(String pname, boolean pplaying) {
/*  454 */     if (!this.maidModeIndexList.containsKey(pname)) {
/*  455 */       return false;
/*      */     }
/*  457 */     return setMaidMode(((Integer)this.maidModeIndexList.get(pname)).intValue(), pplaying);
/*      */   }
/*      */   
/*      */   public boolean setMaidMode(int pindex) {
/*  461 */     return setMaidMode(pindex, false);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean setMaidMode(int pindex, boolean pplaying)
/*      */   {
/*  467 */     this.velocityChanged = true;
/*  468 */     if (!this.maidModeList.containsKey(Integer.valueOf(pindex))) return false;
/*  469 */     if (this.maidMode == pindex) { return true;
/*      */     }
/*  471 */     if (!pplaying)
/*      */     {
/*      */ 
/*  474 */       this.mstatWorkingInt = pindex;
/*      */     }
/*  476 */     this.mstatModeName = getMaidModeString(pindex);
/*  477 */     this.maidMode = pindex;
/*  478 */     this.dataWatcher.updateObject(24, Short.valueOf((short)this.maidMode));
/*  479 */     EntityAITasks[] ltasks = (EntityAITasks[])this.maidModeList.get(Integer.valueOf(pindex));
/*      */     
/*      */ 
/*  482 */     if ((ltasks.length > 0) && (ltasks[0] != null)) {
/*  483 */       setMaidModeAITasks(ltasks[0], this.tasks);
/*      */     } else {
/*  485 */       setMaidModeAITasks(null, this.tasks);
/*      */     }
/*  487 */     if ((ltasks.length > 1) && (ltasks[1] != null)) {
/*  488 */       setMaidModeAITasks(ltasks[1], this.targetTasks);
/*      */     } else {
/*  490 */       setMaidModeAITasks(null, this.targetTasks);
/*      */     }
/*      */     
/*      */ 
/*  494 */     this.maidAvatar.stopUsingItem();
/*  495 */     setSitting(false);
/*  496 */     setSneaking(false);
/*  497 */     setActiveModeClass(null);
/*  498 */     this.aiJumpTo.setEnable(true);
/*      */     
/*  500 */     this.aiAttack.setEnable(true);
/*  501 */     this.aiShooting.setEnable(false);
/*  502 */     this.aiAvoidPlayer.setEnable(true);
/*      */     
/*  504 */     setBloodsuck(false);
/*  505 */     clearTilePosAll();
/*  506 */     for (int li = 0; li < this.maidEntityModeList.size(); li++) {
/*  507 */       LMM_EntityModeBase iem = (LMM_EntityModeBase)this.maidEntityModeList.get(li);
/*  508 */       if (iem.setMode(this.maidMode)) {
/*  509 */         setActiveModeClass(iem);
/*  510 */         this.aiFollow.minDist = iem.getRangeToMaster(0);
/*  511 */         this.aiFollow.maxDist = iem.getRangeToMaster(1);
/*  512 */         break;
/*      */       }
/*      */     }
/*  515 */     getNextEquipItem();
/*      */     
/*  517 */     return true;
/*      */   }
/*      */   
/*      */   protected void setMaidModeAITasks(EntityAITasks pTasksSRC, EntityAITasks pTasksDEST)
/*      */   {
/*      */     try
/*      */     {
/*  524 */       ArrayList<EntityAITasks.EntityAITaskEntry> ltasksDoDEST = getEntityAITasks_taskEntries(pTasksDEST);
/*  525 */       ArrayList<EntityAITasks.EntityAITaskEntry> ltasksExeDEST = getEntityAITasks_executingTaskEntries(pTasksDEST);
/*      */       
/*  527 */       if (pTasksSRC == null) {
/*  528 */         ltasksDoDEST.clear();
/*  529 */         ltasksExeDEST.clear();
/*      */       } else {
/*  531 */         ArrayList<EntityAITasks.EntityAITaskEntry> ltasksDoSRC = getEntityAITasks_taskEntries(pTasksSRC);
/*  532 */         ArrayList<EntityAITasks.EntityAITaskEntry> ltasksExeSRC = getEntityAITasks_executingTaskEntries(pTasksSRC);
/*      */         
/*      */ 
/*  535 */         Iterator iterator = ltasksExeDEST.iterator();
/*  536 */         while (iterator.hasNext()) {
/*  537 */           EntityAITasks.EntityAITaskEntry ltaskentory = (EntityAITasks.EntityAITaskEntry)iterator.next();
/*  538 */           ltaskentory.action.resetTask();
/*      */         }
/*  540 */         ltasksExeDEST.clear();
/*      */         
/*  542 */         ltasksDoDEST.clear();
/*  543 */         ltasksDoDEST.addAll(ltasksDoSRC);
/*      */         
/*  545 */         for (EntityAITasks.EntityAITaskEntry ltask : ltasksDoSRC) {
/*  546 */           if (!(ltask instanceof LMM_IEntityAI)) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception s) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ArrayList<EntityAITasks.EntityAITaskEntry> getEntityAITasks_taskEntries(EntityAITasks task)
/*      */   {
/*  557 */     return (ArrayList)ObfuscationReflectionHelper.getPrivateValue(EntityAITasks.class, task, new String[] { "field_75782_a", "taskEntries" });
/*      */   }
/*      */   
/*      */   public static ArrayList<EntityAITasks.EntityAITaskEntry> getEntityAITasks_executingTaskEntries(EntityAITasks task) {
/*  561 */     return (ArrayList)ObfuscationReflectionHelper.getPrivateValue(EntityAITasks.class, task, new String[] { "field_75780_b", "executingTaskEntries" });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public LMM_EntityModeBase getActiveModeClass()
/*      */   {
/*  568 */     return this.maidActiveModeClass;
/*      */   }
/*      */   
/*      */   public void setActiveModeClass(LMM_EntityModeBase pEntityMode) {
/*  572 */     this.maidActiveModeClass = pEntityMode;
/*      */   }
/*      */   
/*      */   public boolean isActiveModeClass() {
/*  576 */     return this.maidActiveModeClass != null;
/*      */   }
/*      */   
/*      */ 
/*      */   protected String getHurtSound()
/*      */   {
/*  582 */     playSound(this.maidDamegeSound, true);
/*  583 */     return null;
/*      */   }
/*      */   
/*      */   protected String getDeathSound()
/*      */   {
/*  588 */     playSound(LMM_EnumSound.death, true);
/*  589 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   protected String getLivingSound()
/*      */   {
/*  595 */     LMM_EnumSound so = LMM_EnumSound.Null;
/*  596 */     if (getHealth() < 10.0F) {
/*  597 */       so = LMM_EnumSound.living_whine;
/*  598 */     } else if (this.rand.nextFloat() < this.maidSoundRate) {
/*  599 */       if ((this.mstatTime > 23500) || (this.mstatTime < 1500)) {
/*  600 */         so = LMM_EnumSound.living_morning;
/*  601 */       } else if (this.mstatTime < 12500) {
/*  602 */         if (isContract()) {
/*  603 */           BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double(this.posX + 0.5D), MathHelper.floor_double(this.posZ + 0.5D));
/*  604 */           float ltemp = biomegenbase.getFloatTemperature((int)this.posX, (int)this.posY, (int)this.posZ);
/*  605 */           if (ltemp <= 0.15F) {
/*  606 */             so = LMM_EnumSound.living_cold;
/*  607 */           } else if (ltemp > 1.0F) {
/*  608 */             so = LMM_EnumSound.living_hot;
/*      */           } else {
/*  610 */             so = LMM_EnumSound.living_daytime;
/*      */           }
/*  612 */           if (this.worldObj.isRaining()) {
/*  613 */             if (biomegenbase.canSpawnLightningBolt()) {
/*  614 */               so = LMM_EnumSound.living_rain;
/*  615 */             } else if (biomegenbase.getEnableSnow()) {
/*  616 */               so = LMM_EnumSound.living_snow;
/*      */             }
/*      */           }
/*      */         } else {
/*  620 */           so = LMM_EnumSound.living_daytime;
/*      */         }
/*      */       } else {
/*  623 */         so = LMM_EnumSound.living_night;
/*      */       }
/*      */     }
/*      */     
/*  627 */     LMM_LittleMaidMobX.Debug("id:%d LivingSound:%s", new Object[] { Integer.valueOf(getEntityId()), this.worldObj.isRemote ? "Client" : this.worldObj == null ? "null" : "Server" });
/*  628 */     playLittleMaidSound(so, false);
/*  629 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void playSound(String pname)
/*      */   {
/*  636 */     playSound(pname, 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void playSound(LMM_EnumSound enumsound, boolean force)
/*      */   {
/*  643 */     if (((this.maidSoundInterval > 0) && (!force)) || (enumsound == LMM_EnumSound.Null)) return;
/*  644 */     this.maidSoundInterval = 20;
/*  645 */     if (!this.worldObj.isRemote)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  652 */       LMM_LittleMaidMobX.Debug("id:%d-%s, seps:%04x-%s", new Object[] { Integer.valueOf(getEntityId()), this.worldObj.isRemote ? "Client" : "Server", Integer.valueOf(enumsound.index), enumsound.name() });
/*  653 */       byte[] lbuf = { -119, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  658 */       MMM_Helper.setInt(lbuf, 5, enumsound.index);
/*  659 */       LMM_Net.sendToAllEClient(this, lbuf);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void playLittleMaidSound(LMM_EnumSound enumsound, boolean force)
/*      */   {
/*  669 */     if (((this.maidSoundInterval > 0) && (!force)) || (enumsound == LMM_EnumSound.Null)) return;
/*  670 */     this.maidSoundInterval = 20;
/*  671 */     if (this.worldObj.isRemote)
/*      */     {
/*  673 */       String s = LMM_SoundManager.getSoundValue(enumsound, this.textureData.getTextureName(0), this.textureData.getColor());
/*  674 */       if ((!s.isEmpty()) && (!s.startsWith("minecraft:")))
/*      */       {
/*  676 */         s = "lmmx:" + s;
/*      */       }
/*  678 */       LMM_LittleMaidMobX.Debug(String.format("id:%d, se:%04x-%s (%s)", new Object[] { Integer.valueOf(getEntityId()), Integer.valueOf(enumsound.index), enumsound.name(), s }), new Object[0]);
/*  679 */       if (!s.isEmpty())
/*      */       {
/*  681 */         float lpitch = LMM_LittleMaidMobX.cfg_VoiceDistortion ? this.rand.nextFloat() * 0.2F + 0.95F : 1.0F;
/*  682 */         this.worldObj.playSound(this.posX, this.posY, this.posZ, s, getSoundVolume(), lpitch, false);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void onKillEntity(EntityLivingBase par1EntityLiving)
/*      */   {
/*  689 */     super.onKillEntity(par1EntityLiving);
/*  690 */     if (isBloodsuck())
/*      */     {
/*  692 */       playSound(LMM_EnumSound.laughter, true);
/*      */     } else {
/*  694 */       setTarget(null);
/*  695 */       setAttackTarget(null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected boolean canDespawn()
/*      */   {
/*  702 */     return (LMM_LittleMaidMobX.cfg_canDespawn) || (super.canDespawn());
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean getCanSpawnHere()
/*      */   {
/*  708 */     if (LMM_LittleMaidMobX.cfg_spawnLimit <= getMaidCount()) {
/*  709 */       LMM_LittleMaidMobX.Debug("Spawn Limit.", new Object[0]);
/*  710 */       return false;
/*      */     }
/*  712 */     int lx = MathHelper.floor_double(this.posX);
/*  713 */     int ly = MathHelper.floor_double(this.boundingBox.minY);
/*  714 */     int lz = MathHelper.floor_double(this.posZ);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  724 */     if (LMM_LittleMaidMobX.cfg_Dominant)
/*      */     {
/*  726 */       return (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox)) && (getBlockPathWeight(lx, ly, lz) >= 0.0F);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  731 */     return super.getCanSpawnHere();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDead()
/*      */   {
/*  737 */     if (this.mstatgotcha != null)
/*      */     {
/*  739 */       EntityItem entityitem = new EntityItem(this.worldObj, this.mstatgotcha.posX, this.mstatgotcha.posY, this.mstatgotcha.posZ, new ItemStack(Items.string));
/*  740 */       this.worldObj.spawnEntityInWorld(entityitem);
/*  741 */       this.mstatgotcha = null;
/*      */     }
/*  743 */     super.setDead();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMaidCount()
/*      */   {
/*  750 */     int lj = 0;
/*  751 */     for (int li = 0; li < this.worldObj.loadedEntityList.size(); li++) {
/*  752 */       if ((this.worldObj.loadedEntityList.get(li) instanceof LMM_EntityLittleMaid)) {
/*  753 */         lj++;
/*      */       }
/*      */     }
/*  756 */     return lj;
/*      */   }
/*      */   
/*      */ 
/*      */   public EntityAgeable createChild(EntityAgeable var1)
/*      */   {
/*  762 */     return null;
/*      */   }
/*      */   
/*      */   protected void showParticleFX(String s)
/*      */   {
/*  767 */     showParticleFX(s, 1.0D, 1.0D, 1.0D);
/*      */   }
/*      */   
/*      */   protected void showParticleFX(String s, double d, double d1, double d2) {
/*  771 */     showParticleFX(s, d, d1, d2, 0.0D, 0.0D, 0.0D);
/*      */   }
/*      */   
/*      */   protected void showParticleFX(String s, double d, double d1, double d2, double d3, double d4, double d5) {
/*  775 */     for (int i = 0; i < 7; i++) {
/*  776 */       double d6 = this.rand.nextGaussian() * d + d3;
/*  777 */       double d7 = this.rand.nextGaussian() * d1 + d4;
/*  778 */       double d8 = this.rand.nextGaussian() * d2 + d5;
/*  779 */       this.worldObj.spawnParticle(s, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d6, d7, d8);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void handleHealthUpdate(byte par1)
/*      */   {
/*  786 */     switch (par1)
/*      */     {
/*      */     case 10: 
/*  789 */       showParticleFX("smoke", 0.02D, 0.02D, 0.02D);
/*  790 */       break;
/*      */     
/*      */     case 11: 
/*  793 */       double a = getContractLimitDays() / 7.0D;
/*  794 */       double d6 = a * 0.3D;
/*  795 */       double d7 = a;
/*  796 */       double d8 = a * 0.3D;
/*  797 */       this.worldObj.spawnParticle("note", this.posX, this.posY + this.height + 0.1D, this.posZ, d6, d7, d8);
/*  798 */       break;
/*      */     
/*      */     case 12: 
/*  801 */       showParticleFX("reddust", 0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
/*  802 */       break;
/*      */     
/*      */     case 13: 
/*  805 */       showParticleFX("smoke", 0.02D, 0.02D, 0.02D);
/*  806 */       break;
/*      */     
/*      */     case 14: 
/*  809 */       showParticleFX("explode", 0.3D, 0.3D, 0.3D, 0.0D, 0.0D, 0.0D);
/*  810 */       break;
/*      */     
/*      */     default: 
/*  813 */       super.handleHealthUpdate(par1);
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   public void setAbsorptionAmount(float par1)
/*      */   {
/*  820 */     if (par1 < 0.0F) {
/*  821 */       par1 = 0.0F;
/*      */     }
/*      */     
/*  824 */     getDataWatcher().updateObject(18, Float.valueOf(par1));
/*      */   }
/*      */   
/*  827 */   public float getAbsorptionAmount() { return getDataWatcher().getWatchableObjectFloat(18); }
/*      */   
/*      */ 
/*      */ 
/*      */   public int colorMultiplier(float pLight, float pPartialTicks)
/*      */   {
/*  833 */     int lbase = 0;
/*  834 */     if (this.maidOverDriveTime.isDelay()) { int i;
/*      */       int i;
/*  836 */       if (this.maidOverDriveTime.isEnable()) {
/*  837 */         i = 100;
/*      */       } else {
/*  839 */         i = 100 + this.maidOverDriveTime.getValue();
/*      */       }
/*  841 */       lbase = i << 24 | 0xDF0F0F;
/*      */     }
/*      */     
/*  844 */     if (isActiveModeClass()) {
/*  845 */       lbase |= getActiveModeClass().colorMultiplier(pLight, pPartialTicks);
/*      */     }
/*      */     
/*  848 */     return lbase;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean isAIEnabled()
/*      */   {
/*  856 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getIFF(Entity pEntity)
/*      */   {
/*  864 */     if ((pEntity == null) || (pEntity == this.mstatMasterEntity)) {
/*  865 */       return true;
/*      */     }
/*      */     
/*  868 */     int tt = LMM_IFF.getIFF(getMaidMaster(), pEntity);
/*  869 */     switch (tt) {
/*      */     case 0: 
/*  871 */       return false;
/*      */     case 2: 
/*  873 */       return true;
/*      */     case 1: 
/*  875 */       if (isBloodsuck())
/*      */       {
/*  877 */         return false;
/*      */       }
/*  879 */       if ((pEntity instanceof LMM_EntityLittleMaid))
/*      */       {
/*  881 */         if (((LMM_EntityLittleMaid)pEntity).mstatPlayingRole > 0) {
/*  882 */           return true;
/*      */         }
/*      */       }
/*  885 */       if ((pEntity instanceof EntityCreature))
/*      */       {
/*  887 */         Entity et = ((EntityCreature)pEntity).getEntityToAttack();
/*  888 */         if ((et != null) && (et == this.mstatMasterEntity)) {
/*  889 */           return false;
/*      */         }
/*  891 */         if (et == this) {
/*  892 */           return false;
/*      */         }
/*  894 */         if ((et instanceof LMM_EntityLittleMaid))
/*      */         {
/*  896 */           if (((LMM_EntityLittleMaid)et).getMaidMasterEntity() == this.mstatMasterEntity) {
/*  897 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*  901 */       return true;
/*      */     }
/*      */     
/*  904 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean canAttackClass(Class par1Class)
/*      */   {
/*  911 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean attackEntityAsMob(Entity par1Entity)
/*      */   {
/*  918 */     if ((getHealth() < 10.0F) && (!isBloodsuck()) && (this.maidInventory.hasItem(Items.sugar))) {
/*  919 */       return true;
/*      */     }
/*      */     
/*      */ 
/*  923 */     if ((isActiveModeClass()) && (getActiveModeClass().attackEntityAsMob(this.maidMode, par1Entity))) {
/*  924 */       return true;
/*      */     }
/*      */     
/*      */ 
/*  928 */     setSwing(20, isBloodsuck() ? LMM_EnumSound.attack_bloodsuck : LMM_EnumSound.attack);
/*  929 */     this.maidAvatar.attackTargetEntityWithCurrentItem(par1Entity);
/*  930 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isBreedingItem(ItemStack par1ItemStack)
/*      */   {
/*  936 */     if (isContractEX()) {
/*  937 */       return par1ItemStack.getItem() == Items.sugar;
/*      */     }
/*  939 */     return par1ItemStack.getItem() == Items.cake;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound)
/*      */   {
/*  947 */     super.writeEntityToNBT(par1nbtTagCompound);
/*      */     
/*  949 */     par1nbtTagCompound.setTag("Inventory", this.maidInventory.writeToNBT(new NBTTagList()));
/*  950 */     par1nbtTagCompound.setString("Mode", getMaidModeString(this.mstatWorkingInt));
/*  951 */     par1nbtTagCompound.setBoolean("Wait", isMaidWait());
/*  952 */     par1nbtTagCompound.setBoolean("Freedom", isFreedom());
/*  953 */     par1nbtTagCompound.setBoolean("Tracer", isTracer());
/*  954 */     par1nbtTagCompound.setInteger("LimitCount", this.maidContractLimit);
/*  955 */     par1nbtTagCompound.setLong("Anniversary", this.maidAnniversary);
/*  956 */     par1nbtTagCompound.setInteger("EXP", this.experienceValue);
/*  957 */     par1nbtTagCompound.setInteger("DominantArm", this.maidDominantArm);
/*  958 */     par1nbtTagCompound.setInteger("Color", this.textureData.getColor());
/*  959 */     par1nbtTagCompound.setString("texName", this.textureData.getTextureName(0));
/*  960 */     par1nbtTagCompound.setString("texArmor", this.textureData.getTextureName(1));
/*      */     
/*  962 */     par1nbtTagCompound.setInteger("homeX", getHomePosition().posX);
/*  963 */     par1nbtTagCompound.setInteger("homeY", getHomePosition().posY);
/*  964 */     par1nbtTagCompound.setInteger("homeZ", getHomePosition().posZ);
/*  965 */     par1nbtTagCompound.setInteger("homeWorld", this.homeWorld);
/*      */     
/*  967 */     NBTTagCompound lnbt = new NBTTagCompound();
/*  968 */     par1nbtTagCompound.setTag("Tiles", lnbt);
/*  969 */     for (int li = 0; li < this.maidTiles.length; li++) {
/*  970 */       if (this.maidTiles[li] != null) {
/*  971 */         lnbt.setIntArray(String.valueOf(li), this.maidTiles[li]);
/*      */       }
/*      */     }
/*      */     
/*  975 */     for (int li = 0; li < this.maidEntityModeList.size(); li++) {
/*  976 */       ((LMM_EntityModeBase)this.maidEntityModeList.get(li)).writeEntityToNBT(par1nbtTagCompound);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound)
/*      */   {
/*  983 */     super.readEntityFromNBT(par1nbtTagCompound);
/*      */     
/*  985 */     if (par1nbtTagCompound.hasKey("ModeColor"))
/*      */     {
/*  987 */       String s = par1nbtTagCompound.getString("Master");
/*  988 */       if (s.length() > 0) {
/*  989 */         W_Common.setOwner(this, s);
/*  990 */         setContract(true);
/*      */       }
/*  992 */       NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Inventory", 10);
/*  993 */       this.maidInventory.readFromNBT(nbttaglist);
/*      */       
/*  995 */       ItemStack[] armi = new ItemStack[4];
/*  996 */       for (int i = 0; i < 4; i++) {
/*  997 */         ItemStack is = this.maidInventory.armorItemInSlot(i);
/*  998 */         if (is != null) {
/*  999 */           armi[(3 - ((ItemArmor)is.getItem()).armorType)] = is;
/*      */         }
/*      */       }
/* 1002 */       this.maidInventory.armorInventory = armi;
/*      */       
/* 1004 */       setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
/* 1005 */       setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
/* 1006 */       setTracer(par1nbtTagCompound.getBoolean("Tracer"));
/* 1007 */       this.textureData.textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texName"));
/* 1008 */       this.textureData.textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texArmor"));
/* 1009 */       this.textureData.textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(this.textureData.textureIndex[0]);
/* 1010 */       this.textureData.textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(this.textureData.textureIndex[1]);
/* 1011 */       byte b = par1nbtTagCompound.getByte("ModeColor");
/* 1012 */       setColor(b & 0xF);
/* 1013 */       switch ((b & 0xF0) >> 4) {
/*      */       case 0: 
/* 1015 */         setMaidMode(0);
/* 1016 */         break;
/*      */       case 2: 
/* 1018 */         setMaidMode(1);
/* 1019 */         break;
/*      */       case 4: 
/* 1021 */         setMaidMode(128);
/* 1022 */         break;
/*      */       case 5: 
/* 1024 */         setMaidMode(0);
/* 1025 */         break;
/*      */       case 6: 
/* 1027 */         setMaidMode(33);
/* 1028 */         break;
/*      */       case 7: 
/* 1030 */         setMaidMode(192);
/* 1031 */         break;
/*      */       case 8: 
/* 1033 */         setMaidMode(131);
/* 1034 */         break;
/*      */       case 9: 
/* 1036 */         setMaidMode(195);
/* 1037 */         break;
/*      */       case 10: 
/* 1039 */         setMaidMode(129);
/* 1040 */         break;
/*      */       case 11: 
/* 1042 */         setMaidMode(194);
/* 1043 */         break;
/*      */       case 12: 
/* 1045 */         setMaidMode(193);
/* 1046 */         break;
/*      */       case 13: 
/* 1048 */         setMaidMode(32);
/* 1049 */         break;
/*      */       case 15: 
/* 1051 */         setMaidMode(0);
/* 1052 */         break;
/*      */       case 1: case 3: case 14: default: 
/* 1054 */         setMaidMode(0);
/*      */       }
/*      */       
/* 1057 */       int lhx = MathHelper.floor_double(this.posX);
/* 1058 */       int lhy = MathHelper.floor_double(this.posY);
/* 1059 */       int lhz = MathHelper.floor_double(this.posZ);
/*      */       
/* 1061 */       getHomePosition().set(lhx, lhy, lhz);
/* 1062 */       long lcl = par1nbtTagCompound.getLong("Limit");
/* 1063 */       if ((isContract()) && (lcl == 0L)) {
/* 1064 */         this.maidContractLimit = 24000;
/*      */       } else {
/* 1066 */         this.maidContractLimit = ((int)(lcl - this.worldObj.getTotalWorldTime()));
/*      */       }
/* 1068 */       this.maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
/* 1069 */       if ((this.maidAnniversary == 0L) && (isContract()))
/*      */       {
/* 1071 */         this.maidAnniversary = (this.worldObj.getWorldTime() - getEntityId());
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1076 */       LMM_LittleMaidMobX.Debug("read." + this.worldObj.isRemote, new Object[0]);
/*      */       
/* 1078 */       this.maidInventory.readFromNBT(par1nbtTagCompound.getTagList("Inventory", 10));
/* 1079 */       setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
/* 1080 */       setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
/* 1081 */       setTracer(par1nbtTagCompound.getBoolean("Tracer"));
/* 1082 */       setMaidMode(par1nbtTagCompound.getString("Mode"));
/* 1083 */       if (par1nbtTagCompound.hasKey("LimitCount")) {
/* 1084 */         this.maidContractLimit = par1nbtTagCompound.getInteger("LimitCount");
/*      */       } else {
/* 1086 */         long lcl = par1nbtTagCompound.getLong("Limit");
/* 1087 */         if ((isContract()) && (lcl == 0L)) {
/* 1088 */           this.maidContractLimit = 24000;
/*      */         } else {
/* 1090 */           this.maidContractLimit = ((int)(lcl - this.worldObj.getWorldTime()));
/*      */         }
/*      */       }
/* 1093 */       if ((isContract()) && (this.maidContractLimit == 0))
/*      */       {
/*      */ 
/* 1096 */         this.maidContractLimit = 24000;
/*      */       }
/* 1098 */       this.maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
/* 1099 */       if ((this.maidAnniversary == 0L) && (isContract()))
/*      */       {
/* 1101 */         this.maidAnniversary = (this.worldObj.getWorldTime() - getEntityId());
/*      */       }
/* 1103 */       if (this.maidAvatar != null) {
/* 1104 */         this.maidAvatar.experienceTotal = par1nbtTagCompound.getInteger("EXP");
/*      */       }
/* 1106 */       setDominantArm(par1nbtTagCompound.getInteger("DominantArm"));
/* 1107 */       if (this.mstatSwingStatus.length <= this.maidDominantArm) {
/* 1108 */         this.maidDominantArm = 0;
/*      */       }
/* 1110 */       this.textureData.textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texName"));
/* 1111 */       this.textureData.textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texArmor"));
/* 1112 */       this.textureData.textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(this.textureData.textureIndex[0]);
/* 1113 */       this.textureData.textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(this.textureData.textureIndex[1]);
/* 1114 */       this.textureData.setColor(par1nbtTagCompound.getInteger("Color"));
/* 1115 */       setTexturePackIndex(this.textureData.color, this.textureData.getTextureIndex());
/*      */       
/*      */ 
/* 1118 */       int lhx = par1nbtTagCompound.getInteger("homeX");
/* 1119 */       int lhy = par1nbtTagCompound.getInteger("homeY");
/* 1120 */       int lhz = par1nbtTagCompound.getInteger("homeZ");
/*      */       
/* 1122 */       getHomePosition().set(lhx, lhy, lhz);
/* 1123 */       this.homeWorld = par1nbtTagCompound.getInteger("homeWorld");
/*      */       
/*      */ 
/* 1126 */       NBTTagCompound lnbt = par1nbtTagCompound.getCompoundTag("Tiles");
/* 1127 */       for (int li = 0; li < this.maidTiles.length; li++) {
/* 1128 */         int[] ltile = lnbt.getIntArray(String.valueOf(li));
/* 1129 */         this.maidTiles[li] = (ltile.length > 0 ? ltile : null);
/*      */       }
/*      */       
/*      */ 
/* 1133 */       for (int li = 0; li < this.maidEntityModeList.size(); li++) {
/* 1134 */         ((LMM_EntityModeBase)this.maidEntityModeList.get(li)).readEntityFromNBT(par1nbtTagCompound);
/*      */       }
/*      */     }
/* 1137 */     onInventoryChanged();
/*      */     
/*      */ 
/* 1140 */     if ((LMM_LittleMaidMobX.cfg_antiDoppelganger) && (this.maidAnniversary > 0L)) {
/* 1141 */       for (int i = 0; i < this.worldObj.loadedEntityList.size(); i++) {
/* 1142 */         Entity entity1 = (Entity)this.worldObj.loadedEntityList.get(i);
/* 1143 */         if ((!entity1.isDead) && ((entity1 instanceof LMM_EntityLittleMaid))) {
/* 1144 */           LMM_EntityLittleMaid elm = (LMM_EntityLittleMaid)entity1;
/* 1145 */           if ((elm != this) && (elm.isContract()) && (elm.maidAnniversary == this.maidAnniversary) && (elm.getMaidMaster().equalsIgnoreCase(getMaidMaster())))
/*      */           {
/*      */ 
/* 1148 */             if (getEntityId() > elm.getEntityId()) {
/* 1149 */               LMM_LittleMaidMobX.Debug(String.format("Load Doppelganger ID:%d, %d", new Object[] { Integer.valueOf(elm.getEntityId()), Long.valueOf(this.maidAnniversary) }), new Object[0]);
/* 1150 */               elm.setDead();
/*      */             } else {
/* 1152 */               LMM_LittleMaidMobX.Debug(String.format("Load Doppelganger ID:%d, %d", new Object[] { Integer.valueOf(getEntityId()), Long.valueOf(this.maidAnniversary) }), new Object[0]);
/* 1153 */               setDead();
/* 1154 */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     } else {
/* 1160 */       LMM_LittleMaidMobX.Debug(String.format("Load ID:%d, MaidMaster:%s, x:%.1f, y:%.1f, z:%.1f, %d", new Object[] { Integer.valueOf(getEntityId()), getMaidMaster(), Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ), Long.valueOf(this.maidAnniversary) }), new Object[0]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public IIcon getItemIcon(ItemStack par1ItemStack, int par2)
/*      */   {
/* 1168 */     if (this.maidAvatar != null) {
/* 1169 */       return this.maidAvatar.getItemIcon(par1ItemStack, par2);
/*      */     }
/*      */     
/* 1172 */     if (par1ItemStack.getItem().requiresMultipleRenderPasses()) {
/* 1173 */       return par1ItemStack.getItem().getIconFromDamageForRenderPass(par1ItemStack.getItemDamage(), par2);
/*      */     }
/* 1175 */     return super.getItemIcon(par1ItemStack, par2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean canBePushed()
/*      */   {
/* 1183 */     if ((this.ridingEntity != null) && (this.ridingEntity == this.mstatMasterEntity) && 
/* 1184 */       ((this.ridingEntity.ridingEntity instanceof EntityHorse)))
/*      */     {
/* 1186 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1191 */     return !this.isDead;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean canBeCollidedWith()
/*      */   {
/* 1197 */     if ((this.ridingEntity != null) && (this.ridingEntity == this.mstatMasterEntity)) {
/* 1198 */       ItemStack litemstack = this.mstatMasterEntity.getCurrentEquippedItem();
/* 1199 */       return (litemstack == null) || (litemstack.getItem() == Items.saddle);
/*      */     }
/* 1201 */     return super.canBeCollidedWith();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean canAttackWithItem()
/*      */   {
/* 1207 */     if ((this.ridingEntity != null) && (this.ridingEntity == this.mstatMasterEntity)) {
/* 1208 */       return false;
/*      */     }
/* 1210 */     return super.canAttackWithItem();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getMountedYOffset()
/*      */   {
/* 1217 */     if ((this.riddenByEntity instanceof EntityChicken)) {
/* 1218 */       return this.height + 0.03D;
/*      */     }
/* 1220 */     if ((this.riddenByEntity instanceof EntitySquid)) {
/* 1221 */       return this.height - 0.2D;
/*      */     }
/* 1223 */     return super.getMountedYOffset() + 0.35D;
/*      */   }
/*      */   
/*      */   public double getYOffset()
/*      */   {
/* 1228 */     if ((this.ridingEntity instanceof EntityPlayer))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1237 */       if ((this.ridingEntity.ridingEntity instanceof EntityHorse))
/*      */       {
/* 1239 */         if (this.worldObj.isRemote)
/*      */         {
/* 1241 */           return this.yOffset - 2.8F;
/*      */         }
/*      */         
/*      */ 
/* 1245 */         return this.yOffset - 1.0F;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1251 */       return this.yOffset - 2.0F;
/*      */     }
/*      */     
/*      */ 
/* 1255 */     return this.yOffset - 0.25F;
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateRidden()
/*      */   {
/* 1261 */     this.ticksExisted += 1;
/*      */     
/*      */ 
/* 1264 */     if ((this.ridingEntity instanceof EntityPlayer)) {
/* 1265 */       EntityPlayer lep = (EntityPlayer)this.ridingEntity;
/*      */       
/*      */ 
/* 1268 */       this.renderYawOffset = lep.renderYawOffset;
/* 1269 */       this.prevRenderYawOffset = lep.prevRenderYawOffset;
/* 1270 */       double llpx = this.lastTickPosX;
/* 1271 */       double llpy = this.lastTickPosY;
/* 1272 */       double llpz = this.lastTickPosZ;
/*      */       
/*      */ 
/* 1275 */       super.updateRidden();
/*      */       
/* 1277 */       this.renderYawOffset = lep.renderYawOffset;
/* 1278 */       if ((this.rotationYaw - this.renderYawOffset) % 360.0F > 90.0F) {
/* 1279 */         this.rotationYaw = (this.renderYawOffset + 90.0F);
/*      */       }
/* 1281 */       if ((this.rotationYaw - this.renderYawOffset) % 360.0F < -90.0F) {
/* 1282 */         this.rotationYaw = (this.renderYawOffset - 90.0F);
/*      */       }
/* 1284 */       if ((this.rotationYawHead - this.renderYawOffset) % 360.0F > 90.0F) {
/* 1285 */         this.rotationYawHead = (this.renderYawOffset + 90.0F);
/*      */       }
/* 1287 */       if ((this.rotationYawHead - this.renderYawOffset) % 360.0F < -90.0F) {
/* 1288 */         this.rotationYawHead = (this.renderYawOffset - 90.0F);
/*      */       }
/*      */       
/*      */       double dz;
/*      */       
/*      */       double dx;
/*      */       double dz;
/* 1295 */       if ((lep.ridingEntity instanceof EntityHorse))
/*      */       {
/* 1297 */         EntityHorse horse = (EntityHorse)lep.ridingEntity;
/* 1298 */         double dz; if (this.worldObj.isRemote)
/*      */         {
/* 1300 */           double dx = Math.sin(horse.renderYawOffset * 3.141592653589793D / 180.0D) * 0.5D;
/* 1301 */           dz = Math.cos(horse.renderYawOffset * 3.141592653589793D / 180.0D) * 0.5D;
/*      */         }
/*      */         else
/*      */         {
/* 1305 */           double dx = Math.sin(horse.renderYawOffset * 3.141592653589793D / 180.0D) * 0.9D;
/* 1306 */           dz = Math.cos(horse.renderYawOffset * 3.141592653589793D / 180.0D) * 0.9D;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1311 */         dx = Math.sin(lep.renderYawOffset * 3.141592653589793D / 180.0D) * 0.35D;
/* 1312 */         dz = Math.cos(lep.renderYawOffset * 3.141592653589793D / 180.0D) * 0.35D;
/*      */       }
/*      */       
/*      */ 
/* 1316 */       setPosition(lep.posX + dx, this.posY, lep.posZ - dz);
/* 1317 */       this.lastTickPosX = llpx;
/* 1318 */       this.lastTickPosY = llpy;
/* 1319 */       this.lastTickPosZ = llpz;
/*      */     } else {
/* 1321 */       super.updateRidden();
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateRiderPosition()
/*      */   {
/* 1327 */     super.updateRiderPosition();
/*      */   }
/*      */   
/*      */   public float getSwingProgress(float par1)
/*      */   {
/* 1332 */     for (LMM_SwingStatus lswing : this.mstatSwingStatus) {
/* 1333 */       lswing.getSwingProgress(par1);
/*      */     }
/* 1335 */     return getSwingStatusDominant().onGround;
/*      */   }
/*      */   
/*      */   public void setLooksWithInterest(boolean f)
/*      */   {
/* 1340 */     if (this.looksWithInterest != f) {
/* 1341 */       this.looksWithInterest = f;
/* 1342 */       if (this.numTicksToChaseTarget <= 0) {
/* 1343 */         this.looksWithInterestAXIS = this.rand.nextBoolean();
/*      */       }
/* 1345 */       int li = this.dataWatcher.getWatchableObjectInt(22);
/* 1346 */       li = this.looksWithInterest ? li | 0x1 : li & 0xFFFFFFFE;
/* 1347 */       li = this.looksWithInterestAXIS ? li | 0x2 : li & 0xFFFFFFFD;
/* 1348 */       this.dataWatcher.updateObject(22, Integer.valueOf(li));
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getLooksWithInterest() {
/* 1353 */     this.looksWithInterest = ((this.dataWatcher.getWatchableObjectInt(22) & 0x1) > 0);
/* 1354 */     this.looksWithInterestAXIS = ((this.dataWatcher.getWatchableObjectInt(22) & 0x2) > 0);
/*      */     
/* 1356 */     return this.looksWithInterest;
/*      */   }
/*      */   
/*      */   public float getInterestedAngle(float f) {
/* 1360 */     return (this.prevRotateAngleHead + (this.rotateAngleHead - this.prevRotateAngleHead) * f) * ((this.looksWithInterestAXIS ? 0.08F : -0.08F) * 3.1415927F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isBlocking()
/*      */   {
/* 1367 */     return getSwingStatusDominant().isBlocking();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void damageArmor(float pDamage)
/*      */   {
/* 1373 */     this.maidInventory.damageArmor(pDamage);
/* 1374 */     this.maidAvatar.damageArmor(pDamage);
/*      */   }
/*      */   
/*      */   public int getTotalArmorValue()
/*      */   {
/* 1379 */     return this.maidAvatar.getTotalArmorValue();
/*      */   }
/*      */   
/*      */   protected float applyArmorCalculations(DamageSource par1DamageSource, float par2)
/*      */   {
/* 1384 */     return this.maidAvatar.applyArmorCalculations(par1DamageSource, par2);
/*      */   }
/*      */   
/*      */   protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2)
/*      */   {
/* 1389 */     return this.maidAvatar.applyPotionDamageCalculations(par1DamageSource, par2);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void damageEntity(DamageSource par1DamageSource, float par2)
/*      */   {
/* 1395 */     if (par1DamageSource == DamageSource.fall) {
/* 1396 */       this.maidDamegeSound = LMM_EnumSound.hurt_fall;
/*      */     }
/* 1398 */     if ((!par1DamageSource.isUnblockable()) && (isBlocking()))
/*      */     {
/*      */ 
/* 1401 */       LMM_LittleMaidMobX.Debug(String.format("Blocking success ID:%d, %f -> %f", new Object[] { Integer.valueOf(getEntityId()), Float.valueOf(par2), Float.valueOf(par2 = (1.0F + par2) * 0.5F) }), new Object[0]);
/* 1402 */       this.maidDamegeSound = LMM_EnumSound.hurt_guard;
/*      */     }
/*      */     
/*      */ 
/* 1406 */     float llasthealth = getHealth();
/* 1407 */     if ((par2 > 0.0F) && (getActiveModeClass() != null) && (!getActiveModeClass().damageEntity(this.maidMode, par1DamageSource, par2))) {
/* 1408 */       this.maidAvatar.damageEntity(par1DamageSource, par2);
/*      */       
/*      */ 
/* 1411 */       setMaidWait(false);
/*      */     }
/*      */     
/* 1414 */     if ((llasthealth == getHealth()) && (this.maidDamegeSound == LMM_EnumSound.hurt)) {
/* 1415 */       this.maidDamegeSound = LMM_EnumSound.hurt_nodamege;
/*      */     }
/* 1417 */     LMM_LittleMaidMobX.Debug(String.format("GetDamage ID:%d, %s, %f/ %f", new Object[] { Integer.valueOf(getEntityId()), par1DamageSource.damageType, Float.valueOf(llasthealth - getHealth()), Float.valueOf(par2) }), new Object[0]);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
/*      */   {
/* 1423 */     Entity entity = par1DamageSource.getEntity();
/*      */     
/* 1425 */     if (par1DamageSource.getDamageType().equalsIgnoreCase("thrown"))
/*      */     {
/* 1427 */       if ((entity != null) && (this.maidAvatar != null) && (entity.getEntityId() == this.maidAvatar.getEntityId()))
/*      */       {
/* 1429 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1433 */     LMM_LittleMaidMobX.Debug("LMM_EntityLittleMaid.attackEntityFrom " + this + "(" + this.maidAvatar + ") <= " + entity, new Object[0]);
/*      */     
/*      */ 
/* 1436 */     this.maidDamegeSound = LMM_EnumSound.hurt;
/* 1437 */     if ((par1DamageSource == DamageSource.inFire) || (par1DamageSource == DamageSource.onFire) || (par1DamageSource == DamageSource.lava)) {
/* 1438 */       this.maidDamegeSound = LMM_EnumSound.hurt_fire;
/*      */     }
/* 1440 */     for (LMM_EntityModeBase lm : this.maidEntityModeList) {
/* 1441 */       float li = lm.attackEntityFrom(par1DamageSource, par2);
/* 1442 */       if (li > 0.0F) { return li != 1.0F;
/*      */       }
/*      */     }
/* 1445 */     setMaidWait(false);
/* 1446 */     setMaidWaitCount(0);
/* 1447 */     if (par2 > 0.0F)
/*      */     {
/* 1449 */       setPlayingRole(0);
/* 1450 */       getNextEquipItem();
/*      */     }
/*      */     
/* 1453 */     if (((isContract()) && ((entity instanceof EntityLivingBase))) || ((entity instanceof EntityArrow))) {
/* 1454 */       if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
/* 1455 */         par2 = 0.0F;
/*      */       }
/* 1457 */       if ((this.worldObj.difficultySetting == EnumDifficulty.EASY) && (par2 > 0.0F)) {
/* 1458 */         par2 = par2 / 2.0F + 1.0F;
/*      */       }
/* 1460 */       if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
/* 1461 */         par2 = par2 * 3.0F / 2.0F;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1466 */     if (par2 == 0.0F)
/*      */     {
/* 1468 */       if (this.maidDamegeSound == LMM_EnumSound.hurt) {
/* 1469 */         this.maidDamegeSound = LMM_EnumSound.hurt_nodamege;
/*      */       }
/* 1471 */       playSound(this.maidDamegeSound, true);
/* 1472 */       return false;
/*      */     }
/*      */     
/* 1475 */     if (super.attackEntityFrom(par1DamageSource, par2))
/*      */     {
/* 1477 */       if ((isContract()) && (entity != null)) {
/* 1478 */         if ((getIFF(entity)) && (!isPlaying())) {
/* 1479 */           this.fleeingTick = 0;
/* 1480 */           return true;
/*      */         }
/* 1482 */       } else if (this.maidInventory.getCurrentItem() == null) {
/* 1483 */         return true;
/*      */       }
/* 1485 */       this.fleeingTick = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1496 */       return true;
/*      */     }
/* 1498 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void usePotionTotarget(EntityLivingBase entityliving)
/*      */   {
/* 1509 */     ItemStack itemstack = this.maidInventory.getCurrentItem();
/* 1510 */     if ((itemstack != null) && ((itemstack.getItem() instanceof ItemPotion)))
/*      */     {
/* 1512 */       itemstack.stackSize -= 1;
/* 1513 */       List list = ((ItemPotion)itemstack.getItem()).getEffects(itemstack);
/* 1514 */       if (list != null) {
/*      */         PotionEffect potioneffect;
/* 1516 */         for (Iterator iterator = list.iterator(); iterator.hasNext(); entityliving.addPotionEffect(new PotionEffect(potioneffect))) {
/* 1517 */           potioneffect = (PotionEffect)iterator.next();
/*      */         }
/*      */       }
/* 1520 */       if (itemstack.stackSize <= 0) {
/* 1521 */         this.maidInventory.setInventoryCurrentSlotContents(null);
/*      */       }
/* 1523 */       this.maidInventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void dropFewItems(boolean par1, int par2)
/*      */   {
/* 1530 */     int k = this.rand.nextInt(3 + par2);
/* 1531 */     for (int j = 0; j <= k; j++) {
/* 1532 */       if (this.rand.nextInt(30) == 0) {
/* 1533 */         dropItem(Items.slime_ball, 1);
/*      */       }
/* 1535 */       if (this.rand.nextInt(50) == 0) {
/* 1536 */         entityDropItem(new ItemStack(Items.dye, 1, 3), 0.0F);
/*      */       }
/* 1538 */       dropItem(Items.sugar, 1);
/*      */     }
/*      */     
/*      */ 
/* 1542 */     this.maidInventory.dropAllItems();
/*      */   }
/*      */   
/*      */   protected Item getDropItem()
/*      */   {
/* 1547 */     return Items.sugar;
/*      */   }
/*      */   
/*      */   protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
/*      */   {
/* 1552 */     return this.experienceValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void applyEntityCollision(Entity par1Entity)
/*      */   {
/* 1559 */     super.applyEntityCollision(par1Entity);
/*      */     
/* 1561 */     if ((par1Entity instanceof LMM_EntityLittleMaid)) {
/* 1562 */       if (((LMM_EntityLittleMaid)par1Entity).aiAvoidPlayer.isActive) {
/* 1563 */         this.aiAvoidPlayer.isActive = true;
/*      */       }
/* 1565 */     } else if (par1Entity == this.mstatMasterEntity) {
/* 1566 */       this.aiAvoidPlayer.setActive();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void updateAITick()
/*      */   {
/* 1576 */     for (LMM_EntityModeBase ieml : this.maidEntityModeList) {
/* 1577 */       ieml.updateAITick(getMaidModeInt());
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateAITasks() {
/* 1582 */     super.updateAITasks();
/*      */   }
/*      */   
/*      */   public void onEntityUpdate()
/*      */   {
/* 1587 */     super.onEntityUpdate();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean isBlockTranslucent(int par1, int par2, int par3)
/*      */   {
/* 1594 */     return this.worldObj.getBlock(par1, par2, par3).isNormalCube();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean func_145771_j(double par1, double par3, double par5)
/*      */   {
/* 1603 */     return pushOutOfBlocks(par1, par3, par5);
/*      */   }
/*      */   
/*      */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 1607 */     int var7 = MathHelper.floor_double(par1);
/* 1608 */     int var8 = MathHelper.floor_double(par3);
/* 1609 */     int var9 = MathHelper.floor_double(par5);
/* 1610 */     double var10 = par1 - var7;
/* 1611 */     double var12 = par5 - var9;
/*      */     
/* 1613 */     boolean lflag = false;
/* 1614 */     for (int li = 0; li < this.height; li++) {
/* 1615 */       lflag |= isBlockTranslucent(var7, var8 + li, var9);
/*      */     }
/* 1617 */     if (lflag) {
/* 1618 */       boolean var14 = (!isBlockTranslucent(var7 - 1, var8, var9)) && (!isBlockTranslucent(var7 - 1, var8 + 1, var9));
/* 1619 */       boolean var15 = (!isBlockTranslucent(var7 + 1, var8, var9)) && (!isBlockTranslucent(var7 + 1, var8 + 1, var9));
/* 1620 */       boolean var16 = (!isBlockTranslucent(var7, var8, var9 - 1)) && (!isBlockTranslucent(var7, var8 + 1, var9 - 1));
/* 1621 */       boolean var17 = (!isBlockTranslucent(var7, var8, var9 + 1)) && (!isBlockTranslucent(var7, var8 + 1, var9 + 1));
/* 1622 */       byte var18 = -1;
/* 1623 */       double var19 = 9999.0D;
/*      */       
/* 1625 */       if ((var14) && (var10 < var19)) {
/* 1626 */         var19 = var10;
/* 1627 */         var18 = 0;
/*      */       }
/*      */       
/* 1630 */       if ((var15) && (1.0D - var10 < var19)) {
/* 1631 */         var19 = 1.0D - var10;
/* 1632 */         var18 = 1;
/*      */       }
/*      */       
/* 1635 */       if ((var16) && (var12 < var19)) {
/* 1636 */         var19 = var12;
/* 1637 */         var18 = 4;
/*      */       }
/*      */       
/* 1640 */       if ((var17) && (1.0D - var12 < var19)) {
/* 1641 */         var19 = 1.0D - var12;
/* 1642 */         var18 = 5;
/*      */       }
/*      */       
/* 1645 */       float var21 = 0.1F;
/*      */       
/* 1647 */       if (var18 == 0) {
/* 1648 */         this.motionX = (-var21);
/*      */       }
/*      */       
/* 1651 */       if (var18 == 1) {
/* 1652 */         this.motionX = var21;
/*      */       }
/*      */       
/* 1655 */       if (var18 == 4) {
/* 1656 */         this.motionZ = (-var21);
/*      */       }
/*      */       
/* 1659 */       if (var18 == 5) {
/* 1660 */         this.motionZ = var21;
/*      */       }
/*      */       
/* 1663 */       return !(var14 | var15 | var16 | var17);
/*      */     }
/*      */     
/* 1666 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void onLivingUpdate()
/*      */   {
/* 1672 */     float lhealth = getHealth();
/* 1673 */     if ((lhealth > 0.0F) && 
/* 1674 */       (!this.worldObj.isRemote) && 
/* 1675 */       (getSwingStatusDominant().canAttack()) && 
/* 1676 */       (!isBloodsuck()))
/*      */     {
/* 1678 */       if ((lhealth < getMaxHealth()) && 
/* 1679 */         (this.maidInventory.consumeInventoryItem(Items.sugar))) {
/* 1680 */         eatSugar(true, false);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1688 */     super.onLivingUpdate();
/*      */     
/* 1690 */     this.maidInventory.decrementAnimations();
/*      */     
/* 1692 */     boolean grave = true;
/* 1693 */     grave &= pushOutOfBlocks(this.posX - this.width * 0.35D, this.boundingBox.minY, this.posZ + this.width * 0.35D);
/* 1694 */     grave &= pushOutOfBlocks(this.posX - this.width * 0.35D, this.boundingBox.minY, this.posZ - this.width * 0.35D);
/* 1695 */     grave &= pushOutOfBlocks(this.posX + this.width * 0.35D, this.boundingBox.minY, this.posZ - this.width * 0.35D);
/* 1696 */     grave &= pushOutOfBlocks(this.posX + this.width * 0.35D, this.boundingBox.minY, this.posZ + this.width * 0.35D);
/* 1697 */     if ((grave) && (this.onGround)) {
/* 1698 */       jump();
/*      */     }
/* 1700 */     if (lhealth > 0.0F)
/*      */     {
/*      */ 
/* 1703 */       if (!this.worldObj.isRemote) {
/* 1704 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0D, 0.0D, 1.0D));
/* 1705 */         if (list != null) {
/* 1706 */           for (int i = 0; i < list.size(); i++) {
/* 1707 */             Entity entity = (Entity)list.get(i);
/* 1708 */             if (!entity.isDead) {
/* 1709 */               if ((entity instanceof EntityArrow))
/*      */               {
/* 1711 */                 ((EntityArrow)entity).canBePickedUp = 1;
/*      */               }
/* 1713 */               entity.onCollideWithPlayer(this.maidAvatar);
/*      */             }
/*      */           }
/*      */           
/* 1717 */           if (((this.entityToAttack instanceof EntityItem)) && (this.maidInventory.getFirstEmptyStack() == -1)) {
/* 1718 */             setTarget(null);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1724 */       if ((isContractEX()) && (this.mstatClockMaid))
/*      */       {
/* 1726 */         this.mstatTime = ((int)(this.worldObj.getWorldTime() % 24000L));
/* 1727 */         if (this.mstatMasterEntity != null) {
/* 1728 */           boolean b = this.mstatMasterEntity.isPlayerSleeping();
/*      */           
/* 1730 */           if ((this.mstatMasterDistanceSq < 25.0D) && (getEntitySenses().canSee(this.mstatMasterEntity))) {
/* 1731 */             LMM_EnumSound lsound = LMM_EnumSound.Null;
/* 1732 */             if ((this.mstatFirstLook) && ((this.mstatTime > 23500) || (this.mstatTime < 1500))) {
/* 1733 */               lsound = LMM_EnumSound.goodmorning;
/* 1734 */               this.mstatFirstLook = false;
/*      */             }
/* 1736 */             else if ((!this.mstatFirstLook) && (b)) {
/* 1737 */               lsound = LMM_EnumSound.goodnight;
/* 1738 */               this.mstatFirstLook = true;
/*      */             }
/* 1740 */             else if ((this.mstatFirstLook) && (!b)) {
/* 1741 */               this.mstatFirstLook = false;
/*      */             }
/*      */             
/* 1744 */             if (lsound != LMM_EnumSound.Null) {
/* 1745 */               playSound(lsound, true);
/* 1746 */               setLooksWithInterest(true);
/*      */             }
/*      */           }
/* 1749 */           else if ((!this.mstatFirstLook) && ((b) || ((this.mstatTime > 18000) && (this.mstatTime < 23500)))) {
/* 1750 */             this.mstatFirstLook = true;
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/* 1755 */         this.mstatTime = 6000;
/*      */       }
/*      */       
/*      */ 
/* 1759 */       this.maidOverDriveTime.onUpdate();
/* 1760 */       if (this.maidOverDriveTime.isDelay()) {
/* 1761 */         for (int li = 0; li < this.mstatSwingStatus.length; li++) {
/* 1762 */           this.mstatSwingStatus[li].attackTime -= 1;
/*      */         }
/* 1764 */         if (this.maidOverDriveTime.isEnable()) {
/* 1765 */           this.worldObj.spawnParticle("reddust", this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, 1.2D, 0.4D, 0.4D);
/*      */         }
/* 1767 */         if (!this.worldObj.isRemote) {
/* 1768 */           Entity lattackentity = getAttackTarget();
/* 1769 */           if (lattackentity == null) {
/* 1770 */             lattackentity = getEntityToAttack();
/*      */           }
/* 1772 */           if (lattackentity != null) {
/* 1773 */             PathEntity pe = this.worldObj.getPathEntityToEntity(this, lattackentity, 16.0F, true, false, false, true);
/*      */             
/* 1775 */             if (pe != null) {
/* 1776 */               pe.incrementPathIndex();
/* 1777 */               if (!pe.isFinished()) {
/* 1778 */                 Vec3 v = pe.getPosition(this);
/* 1779 */                 setPosition(v.xCoord, v.yCoord, v.zCoord);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1786 */       if ((!this.worldObj.isRemote) && 
/* 1787 */         (getSwingStatusDominant().canAttack()))
/*      */       {
/*      */ 
/* 1790 */         if ((getHealth() < getMaxHealth()) && 
/* 1791 */           (this.maidInventory.consumeInventoryItem(Items.sugar))) {
/* 1792 */           eatSugar(true, false);
/*      */         }
/*      */         
/*      */ 
/* 1796 */         if ((this.rand.nextInt(50000) == 0) && (this.maidInventory.consumeInventoryItem(Items.sugar))) {
/* 1797 */           eatSugar(true, false);
/*      */         }
/*      */         
/* 1800 */         if (isContractEX()) {
/* 1801 */           float f = getContractLimitDays();
/* 1802 */           if ((f <= 6.0F) && (this.maidInventory.consumeInventoryItem(Items.sugar)))
/*      */           {
/* 1804 */             eatSugar(true, true);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void onUpdate()
/*      */   {
/* 1814 */     int litemuse = 0;
/*      */     
/*      */ 
/*      */ 
/* 1818 */     if (this.firstload > 0)
/*      */     {
/*      */ 
/* 1821 */       if ((--this.firstload == 0) && 
/* 1822 */         (this.worldObj.isRemote)) {
/* 1823 */         LMM_Net.sendToEServer(this, new byte[] { Byte.MIN_VALUE, 0, 0, 0, 0 });
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1830 */     this.weaponFullAuto = false;
/* 1831 */     this.weaponReload = false;
/*      */     
/*      */ 
/* 1834 */     this.mstatMasterEntity = getMaidMasterEntity();
/* 1835 */     if (this.mstatMasterEntity != null) {
/* 1836 */       this.mstatMasterDistanceSq = getDistanceSqToEntity(this.mstatMasterEntity);
/*      */     }
/*      */     
/* 1839 */     this.textureData.onUpdate();
/*      */     
/* 1841 */     if (this.worldObj.isRemote)
/*      */     {
/* 1843 */       boolean lupd = false;
/* 1844 */       lupd |= updateMaidContract();
/* 1845 */       lupd |= updateMaidColor();
/*      */       
/* 1847 */       updateTexturePack();
/* 1848 */       if (lupd) {
/* 1849 */         setTextureNames();
/*      */       }
/* 1851 */       setMaidMode(this.dataWatcher.getWatchableObjectShort(24));
/* 1852 */       setDominantArm(this.dataWatcher.getWatchableObjectByte(25));
/* 1853 */       updateMaidFlagsClient();
/* 1854 */       updateGotcha();
/*      */       
/*      */ 
/* 1857 */       litemuse = this.dataWatcher.getWatchableObjectInt(26);
/* 1858 */       for (int li = 0; li < this.mstatSwingStatus.length; li++) {
/* 1859 */         ItemStack lis = this.mstatSwingStatus[li].getItemStack(this);
/* 1860 */         if (((litemuse & 1 << li) > 0) && (lis != null)) {
/* 1861 */           this.mstatSwingStatus[li].setItemInUse(lis, lis.getMaxItemUseDuration(), this);
/*      */         } else {
/* 1863 */           this.mstatSwingStatus[li].stopUsingItem(this);
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1869 */       updateRemainsContract();
/*      */       
/* 1871 */       boolean lf = this.maidOverDriveTime.isEnable();
/* 1872 */       if (getMaidFlags(4096) != lf) {
/* 1873 */         if (lf) {
/* 1874 */           playSound(LMM_EnumSound.TNT_D, true);
/*      */         }
/* 1876 */         setMaidFlags(lf, 4096);
/*      */       }
/*      */       
/* 1879 */       lf = this.mstatWorkingCount.isEnable();
/* 1880 */       if (getMaidFlags(128) != lf) {
/* 1881 */         setMaidFlags(lf, 128);
/*      */       }
/*      */       
/* 1884 */       if ((!isContractEX()) && (!isFreedom())) {
/* 1885 */         setFreedom(true);
/* 1886 */         setMaidWait(false);
/*      */       }
/*      */       
/* 1889 */       IAttributeInstance latt = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*      */       
/* 1891 */       latt.removeModifier(attCombatSpeed);
/* 1892 */       if ((isContract()) && (
/* 1893 */         (!isFreedom()) || (this.entityToAttack != null) || (getAttackTarget() != null)))
/*      */       {
/* 1895 */         latt.applyModifier(attCombatSpeed);
/*      */       }
/*      */       
/*      */ 
/* 1899 */       latt.removeModifier(attSneakingSpeed);
/* 1900 */       if (((this.onGround) && (isSneaking())) || (isUsingItem())) {
/* 1901 */         latt.applyModifier(attSneakingSpeed);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1907 */     for (LMM_EntityModeBase leb : this.maidEntityModeList) {
/* 1908 */       leb.onUpdate(this.maidMode);
/*      */     }
/*      */     
/*      */ 
/* 1912 */     super.onUpdate();
/*      */     
/* 1914 */     LMM_SwingStatus lmss1 = getSwingStatusDominant();
/* 1915 */     this.prevSwingProgress = (this.maidAvatar.prevSwingProgress = lmss1.prevSwingProgress);
/* 1916 */     this.swingProgress = (this.maidAvatar.swingProgress = lmss1.swingProgress);
/* 1917 */     this.swingProgressInt = (this.maidAvatar.swingProgressInt = lmss1.swingProgressInt);
/* 1918 */     this.isSwingInProgress = (this.maidAvatar.isSwingInProgress = lmss1.isSwingInProgress);
/*      */     
/*      */ 
/* 1921 */     if (this.maidAvatar != null) {
/* 1922 */       this.maidAvatar.getValue();
/* 1923 */       this.maidAvatar.onUpdate();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1928 */     if (this.mstatWaitCount > 0) {
/* 1929 */       if (hasPath()) {
/* 1930 */         this.mstatWaitCount = 0;
/*      */       } else {
/* 1932 */         this.mstatWaitCount -= 1;
/*      */       }
/*      */     }
/* 1935 */     if (this.maidSoundInterval > 0) {
/* 1936 */       this.maidSoundInterval -= 1;
/*      */     }
/*      */     
/*      */ 
/* 1940 */     this.prevRotateAngleHead = this.rotateAngleHead;
/* 1941 */     if (getLooksWithInterest()) {
/* 1942 */       this.rotateAngleHead += (1.0F - this.rotateAngleHead) * 0.4F;
/* 1943 */       this.numTicksToChaseTarget = 10;
/*      */     } else {
/* 1945 */       this.rotateAngleHead += (0.0F - this.rotateAngleHead) * 0.4F;
/* 1946 */       if (this.numTicksToChaseTarget > 0) { this.numTicksToChaseTarget -= 1;
/*      */       }
/*      */     }
/* 1949 */     if ((getAttackTarget() != null) || (getEntityToAttack() != null)) {
/* 1950 */       setWorking(true);
/*      */     }
/*      */     
/* 1953 */     this.mstatWorkingCount.onUpdate();
/*      */     
/*      */ 
/* 1956 */     litemuse = 0;
/* 1957 */     for (int li = 0; li < this.mstatSwingStatus.length; li++) {
/* 1958 */       this.mstatSwingStatus[li].onUpdate(this);
/* 1959 */       if (this.mstatSwingStatus[li].isUsingItem()) {
/* 1960 */         litemuse |= 1 << li;
/*      */       }
/*      */     }
/*      */     
/* 1964 */     LMM_SwingStatus lmss = getSwingStatusDominant();
/* 1965 */     this.prevSwingProgress = (this.maidAvatar.prevSwingProgress = lmss.prevSwingProgress);
/* 1966 */     this.swingProgress = (this.maidAvatar.swingProgress = lmss.swingProgress);
/* 1967 */     this.swingProgressInt = (this.maidAvatar.swingProgressInt = lmss.swingProgressInt);
/* 1968 */     this.isSwingInProgress = (this.maidAvatar.isSwingInProgress = lmss.isSwingInProgress);
/*      */     
/*      */ 
/* 1971 */     if (this.maidInventory.inventoryChanged) {
/* 1972 */       onInventoryChanged();
/* 1973 */       this.maidInventory.inventoryChanged = false;
/*      */     }
/* 1975 */     if (!this.worldObj.isRemote)
/*      */     {
/*      */ 
/* 1978 */       this.dataWatcher.updateObject(26, Integer.valueOf(litemuse));
/*      */       
/*      */ 
/* 1981 */       for (int li = 0; li < this.maidInventory.getSizeInventory(); li++) {
/* 1982 */         boolean lchange = false;
/* 1983 */         int lselect = 255;
/*      */         
/* 1985 */         for (int lj = 0; lj < this.mstatSwingStatus.length; lj++) {
/* 1986 */           lchange = this.mstatSwingStatus[lj].checkChanged();
/* 1987 */           if (this.mstatSwingStatus[lj].index == li) {
/* 1988 */             lselect = lj;
/*      */           }
/*      */         }
/*      */         
/* 1992 */         if ((lchange) || (this.maidInventory.isChanged(li))) {
/* 1993 */           ((WorldServer)this.worldObj).getEntityTracker().func_151247_a(this, new S04PacketEntityEquipment(getEntityId(), (li | lselect << 8) + 5, this.maidInventory.getStackInSlot(li)));
/* 1994 */           this.maidInventory.resetChanged(li);
/* 1995 */           LMM_LittleMaidMobX.Debug(String.format("ID:%d-%s - Slot(%x:%d-%d,%d) Update.", new Object[] { Integer.valueOf(getEntityId()), this.worldObj.isRemote ? "Client" : "Server", Integer.valueOf(lselect), Integer.valueOf(li), Integer.valueOf(this.mstatSwingStatus[0].index), Integer.valueOf(this.mstatSwingStatus[1].index) }), new Object[0]);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2001 */       this.mstatAimeBow &= !getSwingStatusDominant().canAttack();
/*      */       
/* 2003 */       updateAimebow();
/*      */       
/*      */ 
/* 2006 */       if (this.dataWatcher.getWatchableObjectInt(27) != this.experienceValue) {
/* 2007 */         this.dataWatcher.updateObject(27, Integer.valueOf(this.experienceValue));
/*      */       }
/*      */       
/*      */ 
/* 2011 */       if ((this.riddenByEntity != null) && (!(this.riddenByEntity instanceof EntitySquid)) && 
/* 2012 */         (this.height * this.width < this.riddenByEntity.height * this.riddenByEntity.width)) {
/* 2013 */         if ((this.riddenByEntity instanceof EntityLivingBase)) {
/* 2014 */           attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.riddenByEntity), 0.0F);
/*      */         }
/* 2016 */         this.riddenByEntity.mountEntity(null);
/* 2017 */         return;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2022 */       IAttributeInstance latt = getEntityAttribute(SharedMonsterAttributes.attackDamage);
/*      */       
/* 2024 */       latt.removeModifier(attAxeAmp);
/* 2025 */       ItemStack lis = getCurrentEquippedItem();
/* 2026 */       if ((lis != null) && ((lis.getItem() instanceof net.minecraft.item.ItemAxe)))
/*      */       {
/* 2028 */         latt.applyModifier(attAxeAmp);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2033 */       this.experienceValue = this.dataWatcher.getWatchableObjectInt(27);
/*      */     }
/*      */     
/*      */ 
/* 2037 */     if (this.mstatgotcha != null) {
/* 2038 */       double d = this.mstatgotcha.getDistanceSqToEntity(this);
/* 2039 */       if (this.entityToAttack == null)
/*      */       {
/* 2041 */         if (d > 4.0D)
/*      */         {
/* 2043 */           getNavigator().clearPathEntity();
/* 2044 */           getLookHelper().setLookPositionWithEntity(this.mstatgotcha, 15.0F, 15.0F);
/*      */         }
/* 2046 */         if (d > 12.25D)
/*      */         {
/* 2048 */           getNavigator().tryMoveToXYZ(this.mstatgotcha.posX, this.mstatgotcha.posY, this.mstatgotcha.posZ, 1.0D);
/* 2049 */           getLookHelper().setLookPositionWithEntity(this.mstatgotcha, 15.0F, 15.0F);
/*      */         }
/*      */       }
/* 2052 */       if (d > 25.0D) {
/* 2053 */         double d1 = this.mstatgotcha.posX - this.posX;
/* 2054 */         double d3 = this.mstatgotcha.posZ - this.posZ;
/* 2055 */         double d5 = 0.125D / (Math.sqrt(d1 * d1 + d3 * d3) + 0.0625D);
/* 2056 */         d1 *= d5;
/* 2057 */         d3 *= d5;
/* 2058 */         this.motionX += d1;
/* 2059 */         this.motionZ += d3;
/*      */       }
/* 2061 */       if (d > 42.25D) {
/* 2062 */         double d2 = this.mstatgotcha.posX - this.posX;
/* 2063 */         double d4 = this.mstatgotcha.posZ - this.posZ;
/* 2064 */         double d6 = 0.0625D / (Math.sqrt(d2 * d2 + d4 * d4) + 0.0625D);
/* 2065 */         d2 *= d6;
/* 2066 */         d4 *= d6;
/* 2067 */         this.mstatgotcha.motionX -= d2;
/* 2068 */         this.mstatgotcha.motionZ -= d4;
/*      */       }
/* 2070 */       if (d > 64.0D) {
/* 2071 */         setGotcha(0);
/* 2072 */         this.mstatgotcha = null;
/* 2073 */         playSound("random.drr");
/*      */       }
/* 2075 */       if (this.rand.nextInt(16) == 0) {
/* 2076 */         List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(8.0D, 8.0D, 8.0D));
/* 2077 */         for (int k = 0; k < list.size(); k++) {
/* 2078 */           Entity entity = (Entity)list.get(k);
/* 2079 */           if ((entity instanceof EntityMob))
/*      */           {
/*      */ 
/* 2082 */             EntityMob entitymob = (EntityMob)entity;
/* 2083 */             if (entitymob.getEntityToAttack() == this.mstatgotcha) {
/* 2084 */               entitymob.setTarget(this);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void onDeath(DamageSource par1DamageSource)
/*      */   {
/* 2095 */     super.onDeath(par1DamageSource);
/*      */     
/*      */ 
/* 2098 */     if (!this.worldObj.isRemote)
/*      */     {
/* 2100 */       if ((LMM_LittleMaidMobX.cfg_DeathMessage) && (this.mstatMasterEntity != null)) {
/* 2101 */         String ls = par1DamageSource.getDamageType();
/* 2102 */         Entity lentity = par1DamageSource.getEntity();
/* 2103 */         if (lentity != null) {
/* 2104 */           if ((par1DamageSource.getEntity() instanceof EntityPlayer)) {
/* 2105 */             ls = ls + ":" + MMM_Helper.getPlayerName((EntityPlayer)lentity);
/*      */           } else {
/* 2107 */             String lt = EntityList.getEntityString(lentity);
/* 2108 */             if (lt != null) {
/* 2109 */               ls = ls + ":" + lt;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2118 */         String lt = func_145748_c_().getUnformattedTextForChat();
/*      */         
/* 2120 */         ChatComponentText text = new ChatComponentText(String.format("your %s killed by %s", new Object[] { lt, ls }));
/* 2121 */         this.mstatMasterEntity.addChatMessage(text);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void onNewPotionEffect(PotionEffect par1PotionEffect)
/*      */   {
/* 2129 */     super.onNewPotionEffect(par1PotionEffect);
/* 2130 */     if ((this.mstatMasterEntity instanceof EntityPlayerMP)) {
/* 2131 */       ((EntityPlayerMP)this.mstatMasterEntity).playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(getEntityId(), par1PotionEffect));
/*      */     }
/*      */   }
/*      */   
/*      */   protected void onChangedPotionEffect(PotionEffect par1PotionEffect, boolean par2)
/*      */   {
/* 2137 */     super.onChangedPotionEffect(par1PotionEffect, par2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void onFinishedPotionEffect(PotionEffect par1PotionEffect)
/*      */   {
/* 2146 */     super.onFinishedPotionEffect(par1PotionEffect);
/* 2147 */     if ((this.mstatMasterEntity instanceof EntityPlayerMP)) {
/* 2148 */       ((EntityPlayerMP)this.mstatMasterEntity).playerNetServerHandler.sendPacket(new S1EPacketRemoveEntityEffect(getEntityId(), par1PotionEffect));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void onInventoryChanged()
/*      */   {
/* 2158 */     checkClockMaid();
/* 2159 */     checkMaskedMaid();
/* 2160 */     checkHeadMount();
/* 2161 */     getNextEquipItem();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getNextEquipItem()
/*      */   {
/* 2169 */     if (this.worldObj.isRemote)
/*      */     {
/* 2171 */       return false;
/*      */     }
/*      */     int li;
/*      */     int li;
/* 2175 */     if (isActiveModeClass()) {
/* 2176 */       li = getActiveModeClass().getNextEquipItem(this.maidMode);
/*      */     } else {
/* 2178 */       li = -1;
/*      */     }
/* 2180 */     setEquipItem(this.maidDominantArm, li);
/* 2181 */     return li > -1;
/*      */   }
/*      */   
/*      */   public void setEquipItem(int pArm, int pIndex) {
/* 2185 */     if (pArm == this.maidDominantArm) {
/* 2186 */       this.maidInventory.currentItem = pIndex;
/*      */     }
/* 2188 */     int li = this.mstatSwingStatus[pArm].index;
/* 2189 */     if (li != pIndex) {
/* 2190 */       if (li > -1) {
/* 2191 */         this.maidInventory.setChanged(li);
/*      */       }
/* 2193 */       if (pIndex > -1) {
/* 2194 */         this.maidInventory.setChanged(pIndex);
/*      */       }
/* 2196 */       this.mstatSwingStatus[pArm].setSlotIndex(pIndex);
/*      */     }
/*      */   }
/*      */   
/* 2200 */   public void setEquipItem(int pIndex) { setEquipItem(this.maidDominantArm, pIndex); }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void getWeaponStatus()
/*      */   {
/* 2209 */     ItemStack is = this.maidInventory.getCurrentItem();
/* 2210 */     if (is == null) return;
/*      */     try
/*      */     {
/* 2213 */       Method me = is.getItem().getClass().getMethod("isWeaponReload", new Class[] { ItemStack.class, EntityPlayer.class });
/* 2214 */       this.weaponReload = ((Boolean)me.invoke(is.getItem(), new Object[] { is, this.maidAvatar })).booleanValue();
/*      */     }
/*      */     catch (NoSuchMethodException e) {}catch (Exception e) {}
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 2222 */       Method me = is.getItem().getClass().getMethod("isWeaponFullAuto", new Class[] { ItemStack.class });
/* 2223 */       this.weaponFullAuto = ((Boolean)me.invoke(is.getItem(), new Object[] { is })).booleanValue();
/*      */     }
/*      */     catch (NoSuchMethodException e) {}catch (Exception e) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ItemStack getCurrentEquippedItem()
/*      */   {
/* 2237 */     return this.maidInventory.getCurrentItem();
/*      */   }
/*      */   
/*      */   public ItemStack getHeldItem() {
/* 2241 */     return this.maidInventory.getCurrentItem();
/*      */   }
/*      */   
/*      */   public ItemStack getEquipmentInSlot(int par1)
/*      */   {
/* 2246 */     if (par1 == 0)
/* 2247 */       return getHeldItem();
/* 2248 */     if (par1 < 5) {
/* 2249 */       return this.maidInventory.armorItemInSlot(par1 - 1);
/*      */     }
/* 2251 */     return this.maidInventory.getStackInSlot(par1 - 5);
/*      */   }
/*      */   
/*      */ 
/*      */   public ItemStack func_130225_q(int par1)
/*      */   {
/* 2257 */     return this.maidInventory.armorItemInSlot(par1);
/*      */   }
/*      */   
/*      */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
/*      */   {
/* 2262 */     par1 &= 0xFFFF;
/* 2263 */     if (par1 == 0) {
/* 2264 */       this.maidInventory.setInventoryCurrentSlotContents(par2ItemStack);
/* 2265 */     } else if ((par1 > 0) && (par1 < 4)) {
/* 2266 */       this.maidInventory.armorInventory[(par1 - 1)] = par2ItemStack;
/* 2267 */       setTextureNames();
/* 2268 */     } else if (par1 == 4)
/*      */     {
/* 2270 */       if (this.mstatMaskSelect > -1) {
/* 2271 */         this.maidInventory.mainInventory[this.mstatMaskSelect] = par2ItemStack;
/*      */       }
/* 2273 */       setTextureNames();
/*      */     } else {
/* 2275 */       par1 -= 5;
/*      */       
/*      */ 
/*      */ 
/* 2279 */       int lslotindex = par1 & 0x7F;
/* 2280 */       int lequip = par1 >>> 8 & 0xFF;
/* 2281 */       this.maidInventory.setInventorySlotContents(lslotindex, par2ItemStack);
/* 2282 */       this.maidInventory.resetChanged(lslotindex);
/* 2283 */       this.maidInventory.inventoryChanged = true;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2288 */       for (LMM_SwingStatus lss : this.mstatSwingStatus) {
/* 2289 */         if (lslotindex == lss.index) {
/* 2290 */           lss.index = -1;
/*      */         }
/*      */       }
/* 2293 */       if (lequip != 255) {
/* 2294 */         setEquipItem(lequip, lslotindex);
/*      */       }
/*      */       
/* 2297 */       if (lslotindex >= 18) {
/* 2298 */         setTextureNames();
/*      */       }
/* 2300 */       String s = par2ItemStack == null ? null : par2ItemStack.getDisplayName();
/* 2301 */       LMM_LittleMaidMobX.Debug(String.format("ID:%d Slot(%2d:%d):%s", new Object[] { Integer.valueOf(getEntityId()), Integer.valueOf(lslotindex), Integer.valueOf(lequip), s == null ? "NoItem" : s }), new Object[0]);
/*      */     }
/*      */   }
/*      */   
/*      */   public ItemStack[] getLastActiveItems()
/*      */   {
/* 2307 */     return this.maidInventory.armorInventory;
/*      */   }
/*      */   
/*      */   protected void checkClockMaid()
/*      */   {
/* 2312 */     this.mstatClockMaid = (this.maidInventory.getInventorySlotContainItem(Items.clock) > -1);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isClockMaid()
/*      */   {
/* 2318 */     return this.mstatClockMaid;
/*      */   }
/*      */   
/*      */   protected void checkMaskedMaid()
/*      */   {
/* 2323 */     for (int i = this.maidInventory.mainInventory.length - 1; i >= 0; i--) {
/* 2324 */       ItemStack is = this.maidInventory.getStackInSlot(i);
/* 2325 */       if ((is != null) && ((is.getItem() instanceof ItemArmor)) && (((ItemArmor)is.getItem()).armorType == 0))
/*      */       {
/* 2327 */         this.mstatMaskSelect = i;
/* 2328 */         this.maidInventory.armorInventory[3] = is;
/* 2329 */         if (this.worldObj.isRemote) {
/* 2330 */           setTextureNames();
/*      */         }
/* 2332 */         return;
/*      */       }
/*      */     }
/*      */     
/* 2336 */     this.mstatMaskSelect = -1;
/* 2337 */     this.maidInventory.armorInventory[3] = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isMaskedMaid()
/*      */   {
/* 2344 */     return this.mstatMaskSelect > -1;
/*      */   }
/*      */   
/*      */   protected void checkHeadMount()
/*      */   {
/* 2349 */     ItemStack lis = this.maidInventory.getHeadMount();
/* 2350 */     this.mstatPlanter = false;
/* 2351 */     this.mstatCamouflage = false;
/* 2352 */     if (lis != null) {
/* 2353 */       if ((lis.getItem() instanceof ItemBlock)) {
/* 2354 */         Block lblock = Block.getBlockFromItem(lis.getItem());
/*      */         
/* 2356 */         this.mstatPlanter = ((lblock.getRenderType() == 1) || (((lblock instanceof BlockDoublePlant)) && (lblock.getRenderType() == 40)));
/*      */         
/* 2358 */         this.mstatCamouflage = (((lblock instanceof BlockLeaves)) || ((lblock instanceof BlockPumpkin)) || ((lblock instanceof net.minecraft.block.BlockStainedGlass)));
/* 2359 */       } else if ((lis.getItem() instanceof net.minecraft.item.ItemSkull)) {
/* 2360 */         this.mstatCamouflage = true;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isCamouflage()
/*      */   {
/* 2368 */     return this.mstatCamouflage;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isPlanter()
/*      */   {
/* 2374 */     return this.mstatPlanter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getSwingSpeedModifier()
/*      */   {
/* 2381 */     if (isPotionActive(Potion.digSpeed)) {
/* 2382 */       return 6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1;
/*      */     }
/*      */     
/* 2385 */     if (isPotionActive(Potion.digSlowdown)) {
/* 2386 */       return 6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
/*      */     }
/* 2388 */     return 6;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void destroyCurrentEquippedItem()
/*      */   {
/* 2396 */     this.maidInventory.setInventoryCurrentSlotContents(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void displayGUIMaidInventory(EntityPlayer pEntityPlayer)
/*      */   {
/* 2404 */     if (!this.worldObj.isRemote) {
/* 2405 */       LMM_GuiCommonHandler.maidServer = this;
/* 2406 */       pEntityPlayer.openGui(LMM_LittleMaidMobX.instance, 0, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2411 */       LMM_GuiCommonHandler.maidClient = this;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean interact(EntityPlayer par1EntityPlayer)
/*      */   {
/* 2418 */     MMMLib.Debug(this.worldObj.isRemote, "LMM_EntityLittleMaid.interact:" + par1EntityPlayer.getGameProfile().getName(), new Object[0]);
/* 2419 */     float lhealth = getHealth();
/* 2420 */     ItemStack itemstack1 = par1EntityPlayer.getCurrentEquippedItem();
/*      */     
/*      */ 
/* 2423 */     for (int li = 0; li < this.maidEntityModeList.size(); li++) {
/* 2424 */       if (((LMM_EntityModeBase)this.maidEntityModeList.get(li)).preInteract(par1EntityPlayer, itemstack1)) {
/* 2425 */         return true;
/*      */       }
/*      */     }
/*      */     
/* 2429 */     if (par1EntityPlayer.isSneaking()) {
/* 2430 */       return false;
/*      */     }
/*      */     
/* 2433 */     if ((lhealth > 0.0F) && (par1EntityPlayer.riddenByEntity != null) && (!(par1EntityPlayer.riddenByEntity instanceof LMM_EntityLittleMaid)))
/*      */     {
/* 2435 */       par1EntityPlayer.riddenByEntity.mountEntity(this);
/* 2436 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2441 */     if ((this.mstatgotcha == null) && (par1EntityPlayer.fishEntity == null)) {
/* 2442 */       if ((itemstack1 != null) && (itemstack1.getItem() == Items.string))
/*      */       {
/* 2444 */         setGotcha(par1EntityPlayer.getEntityId());
/* 2445 */         this.mstatgotcha = par1EntityPlayer;
/* 2446 */         MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2447 */         playSound("random.pop");
/* 2448 */         return true;
/*      */       }
/*      */       
/* 2451 */       if (isContract())
/*      */       {
/* 2453 */         if ((lhealth > 0.0F) && (isMaidContractOwner(par1EntityPlayer))) {
/* 2454 */           if (itemstack1 != null)
/*      */           {
/* 2456 */             setPathToEntity(null);
/*      */             
/* 2458 */             for (int li = 0; li < this.maidEntityModeList.size(); li++) {
/* 2459 */               if (((LMM_EntityModeBase)this.maidEntityModeList.get(li)).interact(par1EntityPlayer, itemstack1)) {
/* 2460 */                 return true;
/*      */               }
/*      */             }
/* 2463 */             if (isRemainsContract())
/*      */             {
/* 2465 */               if (itemstack1.getItem() == Items.sugar)
/*      */               {
/* 2467 */                 MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2468 */                 eatSugar(false, true);
/* 2469 */                 this.worldObj.setEntityState(this, (byte)11);
/*      */                 
/* 2471 */                 LMM_LittleMaidMobX.Debug("give suger." + this.worldObj.isRemote, new Object[0]);
/* 2472 */                 if (!this.worldObj.isRemote) {
/* 2473 */                   setFreedom(isFreedom());
/* 2474 */                   if (isMaidWait())
/*      */                   {
/* 2476 */                     boolean lflag = false;
/* 2477 */                     setActiveModeClass(null);
/* 2478 */                     for (int li = 0; (li < this.maidEntityModeList.size()) && (!lflag); li++) {
/* 2479 */                       lflag = ((LMM_EntityModeBase)this.maidEntityModeList.get(li)).changeMode(par1EntityPlayer);
/* 2480 */                       if (lflag) {
/* 2481 */                         setActiveModeClass((LMM_EntityModeBase)this.maidEntityModeList.get(li));
/*      */                       }
/*      */                     }
/* 2484 */                     if (!lflag) {
/* 2485 */                       setMaidMode("Escorter");
/* 2486 */                       setEquipItem(-1);
/*      */                     }
/*      */                     
/* 2489 */                     setMaidWait(false);
/* 2490 */                     getNextEquipItem();
/*      */                   }
/*      */                   else {
/* 2493 */                     setMaidWait(true);
/*      */                   }
/*      */                 }
/* 2496 */                 return true;
/*      */               }
/* 2498 */               if (itemstack1.getItem() == Items.dye)
/*      */               {
/* 2500 */                 if (!this.worldObj.isRemote) {
/* 2501 */                   setColor(15 - itemstack1.getItemDamage());
/*      */                 }
/* 2503 */                 MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2504 */                 return true;
/*      */               }
/* 2506 */               if (itemstack1.getItem() == Items.feather)
/*      */               {
/* 2508 */                 MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2509 */                 setFreedom(!isFreedom());
/* 2510 */                 this.worldObj.setEntityState(this, (byte)(isFreedom() ? 12 : 13));
/* 2511 */                 return true;
/*      */               }
/* 2513 */               if (itemstack1.getItem() == Items.saddle)
/*      */               {
/* 2515 */                 if (!this.worldObj.isRemote) {
/* 2516 */                   if (this.ridingEntity == par1EntityPlayer) {
/* 2517 */                     mountEntity(null);
/*      */                   } else {
/* 2519 */                     mountEntity(par1EntityPlayer);
/*      */                   }
/* 2521 */                   return true;
/*      */                 }
/*      */               } else {
/* 2524 */                 if (itemstack1.getItem() == Items.gunpowder)
/*      */                 {
/*      */ 
/* 2527 */                   this.maidOverDriveTime.setValue(itemstack1.stackSize * 10);
/* 2528 */                   MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, itemstack1.stackSize);
/* 2529 */                   return true;
/*      */                 }
/* 2531 */                 if (itemstack1.getItem() == Items.book)
/*      */                 {
/* 2533 */                   MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2534 */                   if (this.worldObj.isRemote) {
/* 2535 */                     par1EntityPlayer.openGui(LMM_LittleMaidMobX.instance, 1, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
/*      */                   }
/*      */                   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2542 */                   return true;
/*      */                 }
/* 2544 */                 if ((itemstack1.getItem() == Items.glass_bottle) && (this.experienceValue >= 5))
/*      */                 {
/* 2546 */                   MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2547 */                   if (!this.worldObj.isRemote) {
/* 2548 */                     entityDropItem(new ItemStack(Items.experience_bottle), 0.5F);
/* 2549 */                     this.experienceValue -= 5;
/* 2550 */                     if (this.maidAvatar != null) {
/* 2551 */                       this.maidAvatar.experienceTotal -= 5;
/*      */                     }
/*      */                   }
/* 2554 */                   return true;
/*      */                 }
/* 2556 */                 if ((itemstack1.getItem() instanceof ItemPotion))
/*      */                 {
/* 2558 */                   if (!this.worldObj.isRemote) {
/* 2559 */                     List list = ((ItemPotion)itemstack1.getItem()).getEffects(itemstack1);
/* 2560 */                     if (list != null) {
/*      */                       PotionEffect potioneffect;
/* 2562 */                       for (Iterator iterator = list.iterator(); iterator.hasNext(); addPotionEffect(new PotionEffect(potioneffect))) {
/* 2563 */                         potioneffect = (PotionEffect)iterator.next();
/*      */                       }
/*      */                     }
/*      */                   }
/* 2567 */                   MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2568 */                   return true;
/*      */                 }
/* 2570 */                 if ((isFreedom()) && (itemstack1.getItem() == Items.redstone))
/*      */                 {
/* 2572 */                   MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2573 */                   setPathToEntity(null);
/* 2574 */                   setMaidWait(false);
/* 2575 */                   setTracer(!isTracer());
/* 2576 */                   if (isTracer()) {
/* 2577 */                     this.worldObj.setEntityState(this, (byte)14);
/*      */                   } else {
/* 2579 */                     this.worldObj.setEntityState(this, (byte)12);
/*      */                   }
/*      */                   
/* 2582 */                   return true;
/*      */                 }
/*      */               }
/*      */             } else {
/* 2586 */               if (itemstack1.getItem() == Items.sugar)
/*      */               {
/* 2588 */                 this.worldObj.setEntityState(this, (byte)10);
/* 2589 */                 return true; }
/* 2590 */               if (itemstack1.getItem() == Items.cake)
/*      */               {
/* 2592 */                 MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/* 2593 */                 this.maidContractLimit = 168000;
/* 2594 */                 setFreedom(false);
/* 2595 */                 setTracer(false);
/* 2596 */                 setMaidWait(false);
/* 2597 */                 setMaidMode("Escorter");
/* 2598 */                 this.worldObj.setEntityState(this, (byte)11);
/* 2599 */                 playSound(LMM_EnumSound.Recontract, true);
/* 2600 */                 return true;
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 2605 */           W_Common.setOwner(this, MMM_Helper.getPlayerName(par1EntityPlayer));
/* 2606 */           getNavigator().clearPathEntity();
/* 2607 */           this.isJumping = false;
/* 2608 */           displayGUIMaidInventory(par1EntityPlayer);
/* 2609 */           return true;
/*      */         }
/*      */         
/*      */       }
/* 2613 */       else if ((itemstack1 != null) && 
/* 2614 */         (itemstack1.getItem() == Items.cake))
/*      */       {
/* 2616 */         MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
/*      */         
/* 2618 */         this.deathTime = 0;
/* 2619 */         if (!this.worldObj.isRemote) {
/* 2620 */           if (LMM_LittleMaidMobX.ac_Contract != null) {
/* 2621 */             par1EntityPlayer.triggerAchievement(LMM_LittleMaidMobX.ac_Contract);
/*      */           }
/* 2623 */           setContract(true);
/* 2624 */           W_Common.setOwner(this, MMM_Helper.getPlayerName(par1EntityPlayer));
/* 2625 */           setHealth(20.0F);
/* 2626 */           setMaidMode("Escorter");
/* 2627 */           setMaidWait(false);
/* 2628 */           setFreedom(false);
/* 2629 */           playSound(LMM_EnumSound.getCake, true);
/*      */           
/*      */ 
/* 2632 */           this.worldObj.setEntityState(this, (byte)7);
/*      */           
/* 2634 */           this.maidContractLimit = 168000;
/* 2635 */           this.maidAnniversary = this.worldObj.getTotalWorldTime();
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 2640 */         return true;
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 2646 */     else if ((lhealth > 0.0F) && (this.mstatgotcha != null)) {
/* 2647 */       if (!this.worldObj.isRemote) {
/* 2648 */         EntityItem entityitem = new EntityItem(this.worldObj, this.mstatgotcha.posX, this.mstatgotcha.posY, this.mstatgotcha.posZ, new ItemStack(Items.string));
/* 2649 */         this.worldObj.spawnEntityInWorld(entityitem);
/* 2650 */         setGotcha(0);
/* 2651 */         this.mstatgotcha = null;
/*      */       }
/* 2653 */       return true;
/*      */     }
/*      */     
/* 2656 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isTamed()
/*      */   {
/* 2662 */     return isContract();
/*      */   }
/*      */   
/*      */   public boolean isContract() {
/* 2666 */     return super.isTamed();
/*      */   }
/*      */   
/* 2669 */   public boolean isContractEX() { return (isContract()) && (isRemainsContract()); }
/*      */   
/*      */ 
/*      */   public void setTamed(boolean par1)
/*      */   {
/* 2674 */     setContract(par1);
/*      */   }
/*      */   
/*      */   public void setContract(boolean flag) {
/* 2678 */     super.setTamed(flag);
/* 2679 */     this.textureData.setContract(flag);
/* 2680 */     if (flag) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void updateRemainsContract()
/*      */   {
/* 2690 */     boolean lflag = false;
/* 2691 */     if (this.maidContractLimit > 0) {
/* 2692 */       this.maidContractLimit -= 1;
/* 2693 */       lflag = true;
/*      */     }
/* 2695 */     if (getMaidFlags(32) != lflag) {
/* 2696 */       setMaidFlags(lflag, 32);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isRemainsContract()
/*      */   {
/* 2704 */     return getMaidFlags(32);
/*      */   }
/*      */   
/*      */   public float getContractLimitDays() {
/* 2708 */     return this.maidContractLimit > 0 ? this.maidContractLimit / 24000.0F : -1.0F;
/*      */   }
/*      */   
/*      */   public boolean updateMaidContract()
/*      */   {
/* 2713 */     boolean lf = isContract();
/* 2714 */     if (this.textureData.isContract() != lf) {
/* 2715 */       this.textureData.setContract(lf);
/* 2716 */       return true;
/*      */     }
/* 2718 */     return false;
/*      */   }
/*      */   
/*      */   public EntityLivingBase getOwner()
/*      */   {
/* 2723 */     return getMaidMasterEntity();
/*      */   }
/*      */   
/* 2726 */   public String getMaidMaster() { return W_Common.getOwnerName(this); }
/*      */   
/*      */ 
/*      */   public EntityPlayer getMaidMasterEntity()
/*      */   {
/* 2731 */     if (isContract()) {
/* 2732 */       EntityPlayer entityplayer = this.mstatMasterEntity;
/* 2733 */       if ((this.mstatMasterEntity == null) || (this.mstatMasterEntity.isDead))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2738 */         EntityPlayer clientPlayer = LMM_LittleMaidMobX.proxy.getClientPlayer();
/*      */         String lname;
/* 2740 */         String lname; if ((!LMM_LittleMaidMobX.proxy.isSinglePlayer()) || (LMM_LittleMaidMobX.cfg_checkOwnerName) || (clientPlayer == null))
/*      */         {
/*      */ 
/* 2743 */           lname = getMaidMaster();
/*      */         } else {
/* 2745 */           lname = MMM_Helper.getPlayerName(clientPlayer);
/*      */         }
/* 2747 */         entityplayer = this.worldObj.getPlayerEntityByName(lname);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 2752 */         if ((entityplayer != null) && (this.maidAvatar != null)) {
/* 2753 */           this.maidAvatar.capabilities.isCreativeMode = entityplayer.capabilities.isCreativeMode;
/*      */         }
/*      */       }
/*      */       
/* 2757 */       return entityplayer;
/*      */     }
/* 2759 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isMaidContractOwner(String pname)
/*      */   {
/* 2764 */     return pname.equalsIgnoreCase(MMM_Helper.getPlayerName(this.mstatMasterEntity));
/*      */   }
/*      */   
/*      */   public boolean isMaidContractOwner(EntityPlayer pentity) {
/* 2768 */     return pentity == getMaidMasterEntity();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isMaidWait()
/*      */   {
/* 2775 */     return this.maidWait;
/*      */   }
/*      */   
/*      */   public boolean isMaidWaitEx() {
/* 2779 */     return isMaidWait() | this.mstatWaitCount > 0 | isOpenInventory();
/*      */   }
/*      */   
/*      */   public void setMaidWait(boolean pflag)
/*      */   {
/* 2784 */     this.maidWait = pflag;
/* 2785 */     setMaidFlags(pflag, 256);
/*      */     
/* 2787 */     this.aiSit.setSitting(pflag);
/* 2788 */     this.maidWait = pflag;
/* 2789 */     this.isJumping = false;
/* 2790 */     setAttackTarget(null);
/* 2791 */     setRevengeTarget(null);
/* 2792 */     setPathToEntity(null);
/* 2793 */     getNavigator().clearPathEntity();
/* 2794 */     this.velocityChanged = true;
/*      */   }
/*      */   
/*      */   public void setMaidWaitCount(int count) {
/* 2798 */     this.mstatWaitCount = count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOpenInventory(boolean flag)
/*      */   {
/* 2805 */     this.mstatOpenInventory = flag;
/*      */   }
/*      */   
/*      */   public boolean isOpenInventory() {
/* 2809 */     return this.mstatOpenInventory;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void onGuiOpened()
/*      */   {
/* 2816 */     setOpenInventory(true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void onGuiClosed()
/*      */   {
/* 2823 */     setOpenInventory(false);
/* 2824 */     int li = this.maidMode & 0x80;
/* 2825 */     setMaidWaitCount(li == 0 ? 50 : 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2830 */   public void setSwing(int attacktime, LMM_EnumSound enumsound) { setSwing(attacktime, enumsound, this.maidDominantArm); }
/*      */   
/*      */   public void setSwing(int pattacktime, LMM_EnumSound enumsound, int pArm) {
/* 2833 */     this.mstatSwingStatus[pArm].attackTime = pattacktime;
/*      */     
/*      */ 
/* 2836 */     if (!this.weaponFullAuto) {
/* 2837 */       setSwinging(pArm, enumsound);
/*      */     }
/* 2839 */     if (!this.worldObj.isRemote) {
/* 2840 */       byte[] lba = { -127, 0, 0, 0, 0, (byte)pArm, 0, 0, 0, 0 };
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2846 */       MMM_Helper.setInt(lba, 6, enumsound.index);
/* 2847 */       LMM_Net.sendToAllEClient(this, lba);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/* 2852 */   public void setSwinging(LMM_EnumSound pSound) { setSwinging(this.maidDominantArm, pSound); }
/*      */   
/*      */   public void setSwinging(int pArm, LMM_EnumSound pSound) {
/* 2855 */     if (this.mstatSwingStatus[pArm].setSwinging()) {
/* 2856 */       playLittleMaidSound(pSound, true);
/* 2857 */       this.maidAvatar.swingProgressInt = -1;
/*      */       
/* 2859 */       this.maidAvatar.isSwingInProgress = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getSwinging() {
/* 2864 */     return getSwinging(this.maidDominantArm);
/*      */   }
/*      */   
/* 2867 */   public boolean getSwinging(int pArm) { return this.mstatSwingStatus[pArm].isSwingInProgress; }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public LMM_SwingStatus getSwingStatusDominant()
/*      */   {
/* 2874 */     return this.mstatSwingStatus[this.maidDominantArm];
/*      */   }
/*      */   
/*      */   public LMM_SwingStatus getSwingStatus(int pindex) {
/* 2878 */     return this.mstatSwingStatus[pindex];
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBloodsuck(boolean pFlag)
/*      */   {
/* 2884 */     this.mstatBloodsuck = pFlag;
/* 2885 */     setMaidFlags(pFlag, 2048);
/*      */   }
/*      */   
/*      */   public boolean isBloodsuck() {
/* 2889 */     return this.mstatBloodsuck;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLookSuger(boolean pFlag)
/*      */   {
/* 2895 */     this.mstatLookSuger = pFlag;
/* 2896 */     setMaidFlags(pFlag, 1024);
/*      */   }
/*      */   
/*      */   public boolean isLookSuger() {
/* 2900 */     return this.mstatLookSuger;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void eatSugar(boolean motion, boolean recontract)
/*      */   {
/* 2909 */     if (motion) {
/* 2910 */       setSwing(2, getMaxHealth() - getHealth() <= 1.0F ? LMM_EnumSound.eatSugar_MaxPower : LMM_EnumSound.eatSugar);
/*      */     }
/* 2912 */     int h = this.hurtResistantTime;
/* 2913 */     heal(1.0F);
/* 2914 */     this.hurtResistantTime = h;
/* 2915 */     playSound("random.pop");
/* 2916 */     LMM_LittleMaidMobX.Debug("eat Suger." + this.worldObj.isRemote, new Object[0]);
/*      */     
/* 2918 */     if (recontract)
/*      */     {
/* 2920 */       this.maidContractLimit += 24000;
/* 2921 */       if (this.maidContractLimit > 168000) {
/* 2922 */         this.maidContractLimit = 168000;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2927 */     if (this.maidAvatar != null) {
/* 2928 */       this.maidAvatar.getFoodStats().addStats(20, 20.0F);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWorking(boolean pFlag)
/*      */   {
/* 2938 */     this.mstatWorkingCount.setEnable(pFlag);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isWorking()
/*      */   {
/* 2945 */     return this.mstatWorkingCount.isEnable();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isWorkingDelay()
/*      */   {
/* 2952 */     return this.mstatWorkingCount.isDelay();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTracer(boolean pFlag)
/*      */   {
/* 2959 */     this.maidTracer = pFlag;
/* 2960 */     setMaidFlags(pFlag, 16);
/* 2961 */     if (this.maidTracer) {
/* 2962 */       setFreedom(true);
/*      */     }
/* 2964 */     this.aiTracer.setEnable(pFlag);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isTracer()
/*      */   {
/* 2971 */     return this.maidTracer;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setPlayingRole(int pValue)
/*      */   {
/* 2977 */     if (this.mstatPlayingRole != pValue) {
/* 2978 */       this.mstatPlayingRole = pValue;
/* 2979 */       if (pValue == 0) {
/* 2980 */         setAttackTarget(null);
/* 2981 */         setMaidMode(this.mstatWorkingInt, true);
/*      */       } else {
/* 2983 */         setMaidMode(255, true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public int getPlayingRole() {
/* 2989 */     return this.mstatPlayingRole;
/*      */   }
/*      */   
/*      */   public boolean isPlaying() {
/* 2993 */     return this.mstatPlayingRole != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFreedom(boolean pFlag)
/*      */   {
/* 3000 */     this.maidFreedom = pFlag;
/* 3001 */     this.aiRestrictRain.setEnable(pFlag);
/* 3002 */     this.aiFreeRain.setEnable(pFlag);
/* 3003 */     this.aiWander.setEnable(pFlag);
/*      */     
/* 3005 */     this.aiAvoidPlayer.setEnable(!pFlag);
/* 3006 */     this.aiFollow.setEnable(!pFlag);
/* 3007 */     this.aiTracer.setEnable(false);
/*      */     
/*      */ 
/*      */ 
/* 3011 */     if ((this.maidFreedom) && (isContract())) {
/* 3012 */       setHomeArea(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 16);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 3018 */       detachHome();
/* 3019 */       setPlayingRole(0);
/*      */     }
/*      */     
/* 3022 */     setMaidFlags(this.maidFreedom, 8);
/*      */   }
/*      */   
/*      */   public boolean isFreedom() {
/* 3026 */     return this.maidFreedom;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean sendTextureToServer()
/*      */   {
/* 3036 */     MMM_TextureManager.instance.postSetTexturePack(this, this.textureData.getColor(), this.textureData.getTextureBox());
/* 3037 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean updateTexturePack()
/*      */   {
/* 3044 */     boolean lflag = false;
/*      */     
/*      */ 
/* 3047 */     int ltexture = this.dataWatcher.getWatchableObjectInt(20);
/* 3048 */     int larmor = ltexture >>> 16 & 0xFFFF;
/* 3049 */     ltexture &= 0xFFFF;
/* 3050 */     if (this.textureData.textureIndex[0] != ltexture) {
/* 3051 */       this.textureData.textureIndex[0] = ltexture;
/* 3052 */       lflag = true;
/*      */     }
/* 3054 */     if (this.textureData.textureIndex[1] != larmor) {
/* 3055 */       this.textureData.textureIndex[1] = larmor;
/* 3056 */       lflag = true;
/*      */     }
/* 3058 */     if (lflag) {
/* 3059 */       MMM_TextureManager.instance.postGetTexturePack(this, this.textureData.getTextureIndex());
/*      */     }
/* 3061 */     return lflag;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getColor()
/*      */   {
/* 3067 */     return this.dataWatcher.getWatchableObjectByte(19);
/*      */   }
/*      */   
/*      */   public void setColor(int index)
/*      */   {
/* 3072 */     this.textureData.setColor(index);
/* 3073 */     this.dataWatcher.updateObject(19, Byte.valueOf((byte)index));
/*      */   }
/*      */   
/*      */   public boolean updateMaidColor()
/*      */   {
/* 3078 */     int lc = getColor();
/* 3079 */     if (this.textureData.getColor() != lc) {
/* 3080 */       this.textureData.setColor(lc);
/* 3081 */       return true;
/*      */     }
/* 3083 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateGotcha()
/*      */   {
/* 3090 */     int lid = this.dataWatcher.getWatchableObjectInt(23);
/* 3091 */     if (lid == 0) {
/* 3092 */       this.mstatgotcha = null;
/* 3093 */       return;
/*      */     }
/* 3095 */     if ((this.mstatgotcha != null) && (this.mstatgotcha.getEntityId() == lid)) {
/* 3096 */       return;
/*      */     }
/* 3098 */     for (int li = 0; li < this.worldObj.loadedEntityList.size(); li++) {
/* 3099 */       if (((Entity)this.worldObj.loadedEntityList.get(li)).getEntityId() == lid) {
/* 3100 */         this.mstatgotcha = ((Entity)this.worldObj.loadedEntityList.get(li));
/* 3101 */         break;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setGotcha(int pEntityID) {
/* 3107 */     this.dataWatcher.updateObject(23, Integer.valueOf(pEntityID));
/*      */   }
/*      */   
/* 3110 */   public void setGotcha(Entity pEntity) { setGotcha(pEntity == null ? 0 : pEntity.getEntityId()); }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAimebow()
/*      */   {
/* 3118 */     boolean lflag = ((this.maidAvatar != null) && (this.maidAvatar.isUsingItemLittleMaid())) || (this.mstatAimeBow);
/* 3119 */     setMaidFlags(lflag, 4);
/*      */   }
/*      */   
/*      */   public boolean isAimebow() {
/* 3123 */     return (this.dataWatcher.getWatchableObjectInt(22) & 0x4) > 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateMaidFlagsClient()
/*      */   {
/* 3131 */     int li = this.dataWatcher.getWatchableObjectInt(22);
/* 3132 */     this.maidFreedom = ((li & 0x8) > 0);
/* 3133 */     this.maidTracer = ((li & 0x10) > 0);
/* 3134 */     this.maidWait = ((li & 0x100) > 0);
/* 3135 */     this.mstatAimeBow = ((li & 0x4) > 0);
/* 3136 */     this.mstatLookSuger = ((li & 0x400) > 0);
/* 3137 */     this.mstatBloodsuck = ((li & 0x800) > 0);
/* 3138 */     this.looksWithInterest = ((li & 0x1) > 0);
/* 3139 */     this.looksWithInterestAXIS = ((li & 0x2) > 0);
/* 3140 */     this.maidOverDriveTime.updateClient((li & 0x1000) > 0);
/* 3141 */     this.mstatWorkingCount.updateClient((li & 0x80) > 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaidFlags(boolean pFlag, int pFlagvalue)
/*      */   {
/* 3150 */     int li = this.dataWatcher.getWatchableObjectInt(22);
/* 3151 */     li = pFlag ? li | pFlagvalue : li & (pFlagvalue ^ 0xFFFFFFFF);
/* 3152 */     this.dataWatcher.updateObject(22, Integer.valueOf(li));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getMaidFlags(int pFlagvalue)
/*      */   {
/* 3159 */     return (this.dataWatcher.getWatchableObjectInt(22) & pFlagvalue) > 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDominantArm(int pindex)
/*      */   {
/* 3166 */     if (this.mstatSwingStatus.length <= pindex) return;
/* 3167 */     if (this.maidDominantArm == pindex) return;
/* 3168 */     for (LMM_SwingStatus lss : this.mstatSwingStatus) {
/* 3169 */       lss.index = (lss.lastIndex = -1);
/*      */     }
/* 3171 */     this.maidDominantArm = pindex;
/* 3172 */     this.dataWatcher.updateObject(25, Byte.valueOf((byte)this.maidDominantArm));
/* 3173 */     LMM_LittleMaidMobX.Debug("Change Dominant.", new Object[0]);
/*      */   }
/*      */   
/*      */   public void setHomeArea(int par1, int par2, int par3, int par4)
/*      */   {
/* 3178 */     this.homeWorld = this.dimension;
/* 3179 */     super.setHomeArea(par1, par2, par3, par4);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTexturePackIndex(int pColor, int[] pIndex)
/*      */   {
/* 3185 */     this.textureData.setTexturePackIndex(pColor, pIndex);
/* 3186 */     this.dataWatcher.updateObject(20, Integer.valueOf(this.textureData.textureIndex[0] & 0xFFFF | (this.textureData.textureIndex[1] & 0xFFFF) << 16));
/* 3187 */     LMM_LittleMaidMobX.Debug("changeSize-ID:%d: %f, %f, %b", new Object[] { Integer.valueOf(getEntityId()), Float.valueOf(this.width), Float.valueOf(this.height), Boolean.valueOf(this.worldObj.isRemote) });
/* 3188 */     setColor(pColor);
/* 3189 */     setTextureNames();
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTexturePackName(MMM_TextureBox[] pTextureBox)
/*      */   {
/* 3195 */     this.textureData.setTexturePackName(pTextureBox);
/* 3196 */     setTextureNames();
/* 3197 */     LMM_LittleMaidMobX.Debug("ID:%d, TextureModel:%s", new Object[] { Integer.valueOf(getEntityId()), this.textureData.getTextureName(0) });
/*      */     
/* 3199 */     ((MMM_TextureBox)this.textureData.textureBox[0]).models[0].setCapsValue(768, new Object[] { this.maidCaps });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3206 */     this.maidSoundRate = LMM_SoundManager.getSoundRate(this.textureData.getTextureName(0), getColor());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTextureNames()
/*      */   {
/* 3214 */     if (!this.textureData.setTextureNames())
/*      */     {
/*      */ 
/* 3217 */       setNextTexturePackege(0);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setNextTexturePackege(int pTargetTexture)
/*      */   {
/* 3223 */     this.textureData.setNextTexturePackege(pTargetTexture);
/*      */   }
/*      */   
/*      */   public void setPrevTexturePackege(int pTargetTexture) {
/* 3227 */     this.textureData.setPrevTexturePackege(pTargetTexture);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTextureBox(MMM_TextureBoxBase[] pTextureBox)
/*      */   {
/* 3235 */     this.textureData.setTextureBox(pTextureBox);
/*      */   }
/*      */   
/*      */   public MMM_TextureBoxBase[] getTextureBox()
/*      */   {
/* 3240 */     return this.textureData.getTextureBox();
/*      */   }
/*      */   
/*      */   public void setTextureIndex(int[] pTextureIndex)
/*      */   {
/* 3245 */     this.textureData.setTextureIndex(pTextureIndex);
/*      */   }
/*      */   
/*      */   public int[] getTextureIndex()
/*      */   {
/* 3250 */     return this.textureData.getTextureIndex();
/*      */   }
/*      */   
/*      */   public void setTextures(int pIndex, ResourceLocation[] pNames)
/*      */   {
/* 3255 */     this.textureData.setTextures(pIndex, pNames);
/*      */   }
/*      */   
/*      */   public ResourceLocation[] getTextures(int pIndex)
/*      */   {
/* 3260 */     return this.textureData.getTextures(pIndex);
/*      */   }
/*      */   
/*      */   public MMM_TextureData getTextureData()
/*      */   {
/* 3265 */     return this.textureData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isUsingTile(TileEntity pTile)
/*      */   {
/* 3274 */     if (isActiveModeClass()) {
/* 3275 */       return getActiveModeClass().isUsingTile(pTile);
/*      */     }
/* 3277 */     for (int li = 0; li < this.maidTiles.length; li++) {
/* 3278 */       if ((this.maidTiles[li] != null) && (pTile.xCoord == this.maidTiles[li][0]) && (pTile.yCoord == this.maidTiles[li][1]) && (pTile.zCoord == this.maidTiles[li][2]))
/*      */       {
/*      */ 
/*      */ 
/* 3282 */         return true;
/*      */       }
/*      */     }
/* 3285 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isEqualTile() {
/* 3289 */     return this.worldObj.getTileEntity(this.maidTile[0], this.maidTile[1], this.maidTile[2]) == this.maidTileEntity;
/*      */   }
/*      */   
/*      */ 
/* 3293 */   public boolean isTilePos() { return this.maidTileEntity != null; }
/*      */   
/*      */   public boolean isTilePos(int pIndex) {
/* 3296 */     if (pIndex < this.maidTiles.length) {
/* 3297 */       return this.maidTiles[pIndex] != null;
/*      */     }
/* 3299 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getTilePos(int pIndex)
/*      */   {
/* 3306 */     if ((pIndex < this.maidTiles.length) && (this.maidTiles[pIndex] != null)) {
/* 3307 */       this.maidTile[0] = this.maidTiles[pIndex][0];
/* 3308 */       this.maidTile[1] = this.maidTiles[pIndex][1];
/* 3309 */       this.maidTile[2] = this.maidTiles[pIndex][2];
/* 3310 */       return true;
/*      */     }
/* 3312 */     return false;
/*      */   }
/*      */   
/*      */   public void setTilePos(int pX, int pY, int pZ) {
/* 3316 */     this.maidTile[0] = pX;
/* 3317 */     this.maidTile[1] = pY;
/* 3318 */     this.maidTile[2] = pZ;
/*      */   }
/*      */   
/* 3321 */   public void setTilePos(TileEntity pEntity) { this.maidTile[0] = pEntity.xCoord;
/* 3322 */     this.maidTile[1] = pEntity.yCoord;
/* 3323 */     this.maidTile[2] = pEntity.zCoord;
/* 3324 */     this.maidTileEntity = pEntity;
/*      */   }
/*      */   
/* 3327 */   public void setTilePos(int pIndex) { if (pIndex < this.maidTiles.length) {
/* 3328 */       if (this.maidTiles[pIndex] == null) {
/* 3329 */         this.maidTiles[pIndex] = new int[3];
/*      */       }
/* 3331 */       this.maidTiles[pIndex][0] = this.maidTile[0];
/* 3332 */       this.maidTiles[pIndex][1] = this.maidTile[1];
/* 3333 */       this.maidTiles[pIndex][2] = this.maidTile[2];
/*      */     }
/*      */   }
/*      */   
/* 3337 */   public void setTilePos(int pIndex, int pX, int pY, int pZ) { if (pIndex < this.maidTiles.length) {
/* 3338 */       if (this.maidTiles[pIndex] == null) {
/* 3339 */         this.maidTiles[pIndex] = new int[3];
/*      */       }
/* 3341 */       this.maidTiles[pIndex][0] = pX;
/* 3342 */       this.maidTiles[pIndex][1] = pY;
/* 3343 */       this.maidTiles[pIndex][2] = pZ;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/* 3348 */   public TileEntity getTileEntity() { return this.maidTileEntity = this.worldObj.getTileEntity(this.maidTile[0], this.maidTile[1], this.maidTile[2]); }
/*      */   
/*      */   public TileEntity getTileEntity(int pIndex) {
/* 3351 */     if ((pIndex < this.maidTiles.length) && (this.maidTiles[pIndex] != null)) {
/* 3352 */       TileEntity ltile = this.worldObj.getTileEntity(this.maidTiles[pIndex][0], this.maidTiles[pIndex][1], this.maidTiles[pIndex][2]);
/*      */       
/* 3354 */       if (ltile == null) {
/* 3355 */         clearTilePos(pIndex);
/*      */       }
/* 3357 */       return ltile;
/*      */     }
/* 3359 */     return null;
/*      */   }
/*      */   
/*      */ 
/* 3363 */   public void clearTilePos() { this.maidTileEntity = null; }
/*      */   
/*      */   public void clearTilePos(int pIndex) {
/* 3366 */     if (pIndex < this.maidTiles.length)
/* 3367 */       this.maidTiles[pIndex] = null;
/*      */   }
/*      */   
/*      */   public void clearTilePosAll() {
/* 3371 */     for (int li = 0; li < this.maidTiles.length; li++) {
/* 3372 */       this.maidTiles[li] = null;
/*      */     }
/*      */   }
/*      */   
/*      */   public double getDistanceTilePos() {
/* 3377 */     return getDistance(this.maidTile[0] + 0.5D, this.maidTile[1] + 0.5D, this.maidTile[2] + 0.5D);
/*      */   }
/*      */   
/*      */ 
/*      */   public double getDistanceTilePosSq()
/*      */   {
/* 3383 */     return getDistanceSq(this.maidTile[0] + 0.5D, this.maidTile[1] + 0.5D, this.maidTile[2] + 0.5D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getDistanceTilePos(int pIndex)
/*      */   {
/* 3390 */     if ((this.maidTiles.length > pIndex) && (this.maidTiles[pIndex] != null)) {
/* 3391 */       return getDistance(this.maidTiles[pIndex][0] + 0.5D, this.maidTiles[pIndex][1] + 0.5D, this.maidTiles[pIndex][2] + 0.5D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3396 */     return -1.0D;
/*      */   }
/*      */   
/* 3399 */   public double getDistanceTilePosSq(int pIndex) { if ((this.maidTiles.length > pIndex) && (this.maidTiles[pIndex] != null)) {
/* 3400 */       return getDistanceSq(this.maidTiles[pIndex][0] + 0.5D, this.maidTiles[pIndex][1] + 0.5D, this.maidTiles[pIndex][2] + 0.5D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3405 */     return -1.0D;
/*      */   }
/*      */   
/* 3408 */   public double getDistanceTilePos(TileEntity pTile) { if (pTile != null) {
/* 3409 */       return getDistance(pTile.xCoord + 0.5D, pTile.yCoord + 0.5D, pTile.zCoord + 0.5D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3414 */     return -1.0D;
/*      */   }
/*      */   
/* 3417 */   public double getDistanceTilePosSq(TileEntity pTile) { if (pTile != null) {
/* 3418 */       return getDistanceSq(pTile.xCoord + 0.5D, pTile.yCoord + 0.5D, pTile.zCoord + 0.5D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3423 */     return -1.0D;
/*      */   }
/*      */   
/*      */   public void looksTilePos() {
/* 3427 */     getLookHelper().setLookPosition(this.maidTile[0] + 0.5D, this.maidTile[1] + 0.5D, this.maidTile[2] + 0.5D, 10.0F, getVerticalFaceSpeed());
/*      */   }
/*      */   
/*      */   public void looksTilePos(int pIndex)
/*      */   {
/* 3432 */     if ((this.maidTiles.length > pIndex) && (this.maidTiles[pIndex] != null)) {
/* 3433 */       getLookHelper().setLookPosition(this.maidTiles[pIndex][0] + 0.5D, this.maidTiles[pIndex][1] + 0.5D, this.maidTiles[pIndex][2] + 0.5D, 10.0F, getVerticalFaceSpeed());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isUsingItem()
/*      */   {
/* 3442 */     return this.dataWatcher.getWatchableObjectInt(26) > 0;
/*      */   }
/*      */   
/*      */   public boolean isUsingItem(int pIndex) {
/* 3446 */     return (this.dataWatcher.getWatchableObjectInt(26) & 1 << pIndex) > 0;
/*      */   }
/*      */   
/*      */   public void setExperienceValue(int val)
/*      */   {
/* 3451 */     this.experienceValue = val;
/*      */   }
/*      */   
/*      */   public void setFlag(int par1, boolean par2) {
/* 3455 */     super.setFlag(par1, par2);
/*      */   }
/*      */   
/*      */   public void updateWanderPath()
/*      */   {
/* 3460 */     super.updateWanderPath();
/*      */   }
/*      */   
/*      */   protected boolean isMovementCeased() {
/* 3464 */     return super.isMovementCeased();
/*      */   }
/*      */   
/*      */   public void setSize2(float par1, float par2)
/*      */   {
/* 3469 */     super.setSize(par1, par2);
/*      */   }
/*      */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityLittleMaid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
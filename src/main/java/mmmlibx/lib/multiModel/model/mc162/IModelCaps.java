package mmmlibx.lib.multiModel.model.mc162;

import java.util.Map;

public abstract interface IModelCaps
{
  public static final int caps_onGround = 1;
  public static final int caps_isRiding = 2;
  public static final int caps_isChild = 3;
  public static final int caps_isUpdateSize = 4;
  public static final int caps_heldItemLeft = 16;
  public static final int caps_heldItemRight = 17;
  public static final int caps_heldItems = 18;
  public static final int caps_isSneak = 19;
  public static final int caps_aimedBow = 20;
  public static final int caps_Entity = 32;
  public static final int caps_health = 33;
  public static final int caps_ticksExisted = 34;
  public static final int caps_currentEquippedItem = 35;
  public static final int caps_currentArmor = 36;
  public static final int caps_healthFloat = 37;
  public static final int caps_TextureEntity = 38;
  public static final int caps_isWet = 48;
  public static final int caps_isDead = 49;
  public static final int caps_isJumping = 50;
  public static final int caps_isInWeb = 51;
  public static final int caps_isSwingInProgress = 52;
  public static final int caps_isBurning = 54;
  public static final int caps_isInWater = 55;
  public static final int caps_isInvisible = 56;
  public static final int caps_isSprinting = 57;
  public static final int caps_isLeeding = 58;
  public static final int caps_getRidingName = 59;
  public static final int caps_posX = 96;
  public static final int caps_posY = 97;
  public static final int caps_posZ = 98;
  public static final int caps_pos = 99;
  public static final int caps_motionX = 100;
  public static final int caps_motionY = 101;
  public static final int caps_motionZ = 102;
  public static final int caps_motion = 103;
  public static final int caps_boundingBox = 104;
  public static final int caps_rotationYaw = 105;
  public static final int caps_rotationPitch = 106;
  public static final int caps_prevRotationYaw = 107;
  public static final int caps_prevRotationPitch = 108;
  public static final int caps_renderYawOffset = 109;
  public static final int caps_PosBlockID = 129;
  public static final int caps_PosBlockMeta = 130;
  public static final int caps_PosBlockAir = 131;
  public static final int caps_PosBlockLight = 132;
  public static final int caps_PosBlockPower = 133;
  public static final int caps_isRidingPlayer = 134;
  public static final int caps_WorldTotalTime = 65280;
  public static final int caps_WorldTime = 65281;
  public static final int caps_MoonPhase = 65282;
  public static final int caps_isRendering = 256;
  public static final int caps_isBloodsuck = 257;
  public static final int caps_isFreedom = 258;
  public static final int caps_isTracer = 259;
  public static final int caps_isPlaying = 260;
  public static final int caps_isLookSuger = 261;
  public static final int caps_isBlocking = 262;
  public static final int caps_isWait = 263;
  public static final int caps_isWaitEX = 264;
  public static final int caps_isOpenInv = 265;
  public static final int caps_isWorking = 266;
  public static final int caps_isWorkingDelay = 267;
  public static final int caps_isContract = 268;
  public static final int caps_isContractEX = 269;
  public static final int caps_isRemainsC = 270;
  public static final int caps_isClock = 271;
  public static final int caps_isMasked = 272;
  public static final int caps_isCamouflage = 273;
  public static final int caps_isPlanter = 274;
  public static final int caps_isOverdrive = 275;
  public static final int caps_isOverdriveDelay = 276;
  public static final int caps_entityIdFactor = 288;
  public static final int caps_height = 289;
  public static final int caps_width = 290;
  public static final int caps_YOffset = 291;
  public static final int caps_mountedYOffset = 292;
  public static final int caps_dominantArm = 293;
  public static final int caps_render = 304;
  public static final int caps_Arms = 305;
  public static final int caps_HeadMount = 306;
  public static final int caps_HardPoint = 307;
  public static final int caps_stabiliser = 308;
  public static final int caps_Items = 309;
  public static final int caps_Actions = 310;
  public static final int caps_Grounds = 311;
  public static final int caps_Inventory = 312;
  public static final int caps_Ground = 313;
  public static final int caps_interestedAngle = 336;
  public static final int caps_ScaleFactor = 512;
  public static final int caps_PartsVisible = 513;
  public static final int caps_Posing = 514;
  public static final int caps_Actors = 515;
  public static final int caps_PartsStrings = 516;
  public static final int caps_changeModel = 768;
  public static final int caps_renderFace = 784;
  public static final int caps_renderBody = 785;
  public static final int caps_setFaceTexture = 786;
  public static final int caps_textureData = 787;
  public static final int caps_textureLightColor = 788;
  
  public abstract Map<String, Integer> getModelCaps();
  
  public abstract Object getCapsValue(int paramInt, Object... paramVarArgs);
  
  public abstract boolean setCapsValue(int paramInt, Object... paramVarArgs);
}


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/IModelCaps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
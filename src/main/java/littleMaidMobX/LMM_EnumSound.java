/*    */ package littleMaidMobX;
/*    */ 
/*    */ public enum LMM_EnumSound
/*    */ {
/*  5 */   death(256, "Deid Voice. Null is no Voice", "minecraft:mob.ghast.death"), 
/*  6 */   attack(272, "Attack Voice. Null is no Voice", "minecraft:mob.ghast.charge"), 
/*  7 */   attack_bloodsuck(273, "Attack Bloodsucker Voice. Null is no Voice", ""), 
/*  8 */   laughter(288, "Laughter Voice. Null is no Voice", ""), 
/*  9 */   shoot(304, "shoot Voice. Null is no Voice", "minecraft:mob.ghast.charge"), 
/* 10 */   shoot_burst(305, "burst shoot Voice. Null is no Voice", "minecraft:mob.ghast.charge"), 
/* 11 */   sighting(320, "Adopt a fire Voice. Null is no Voice", ""), 
/* 12 */   healing(336, "Healing Voice. Null is no Voice", ""), 
/* 13 */   healing_potion(337, "Healing with potion Voice. Null is no Voice", ""), 
/* 14 */   TNT_D(352, "Enable TNT-D Voice. Null is no Voice", ""), 
/*    */   
/*    */ 
/* 17 */   eatSugar(512, "Eat Sugar Voice. Null is no Voice", ""), 
/* 18 */   eatSugar_MaxPower(513, "Eat Sugar to MAX healing Voice. Null is no Voice", ""), 
/* 19 */   getCake(528, "Get Cake Voice. Null is no Voice", ""), 
/* 20 */   Recontract(529, "Recontract Voice. Null is no Voice", ""), 
/* 21 */   addFuel(544, "Add Fuel Voice. Null is no Voice", ""), 
/* 22 */   cookingStart(545, "Cooking Start Voice. Null is no Voice", ""), 
/* 23 */   cookingOver(546, "Cooking Over Voice. Null is no Voice", ""), 
/* 24 */   installation(560, "Installation Voice. Null is no Voice", ""), 
/* 25 */   collect_snow(576, "Collecting snow Voice. Null is no Voice", ""), 
/*    */   
/* 27 */   hurt(768, "Dameged Voice. Null is no Voice", "minecraft:mob.ghast.scream"), 
/* 28 */   hurt_snow(769, "Dameged Voice from snowball. Null is no Voice", ""), 
/* 29 */   hurt_fire(770, "Dameged Voice from fire. Null is no Voice", ""), 
/* 30 */   hurt_guard(771, "Dameged Voice on Guard. Null is no Voice", "minecraft:mob.blaze.hit"), 
/* 31 */   hurt_fall(772, "Dameged Voice from Fall. Null is no Voice", ""), 
/* 32 */   hurt_nodamege(777, "No Dameged Voice. Null is no Voice", "minecraft:mob.blaze.hit"), 
/*    */   
/* 34 */   findTarget_N(1024, "Find target Normal Voice. Null is no Voice", ""), 
/* 35 */   findTarget_B(1025, "Find target Bloodsuck Voice. Null is no Voice", ""), 
/* 36 */   findTarget_I(1026, "Find target Item Voice. Null is no Voice", ""), 
/* 37 */   findTarget_D(1027, "Find target Darkness Voice. Null is no Voice", ""), 
/*    */   
/* 39 */   living_daytime(1280, "Living Voice(Default) in Daytime. Null is no Voice", "minecraft:mob.ghast.moan"), 
/* 40 */   living_morning(1281, "Living Voice in Mornig. Null is no Voice", ""), 
/* 41 */   living_night(1282, "Living Voice in Night. Null is no Voice", ""), 
/* 42 */   living_whine(1283, "Living Voice at Whine. Null is no Voice", ""), 
/* 43 */   living_rain(1284, "Living Voice at Rain. Null is no Voice", ""), 
/* 44 */   living_snow(1285, "Living Voice at Snow. Null is no Voice", ""), 
/* 45 */   living_cold(1286, "Living Voice at Cold. Null is no Voice", ""), 
/* 46 */   living_hot(1287, "Living Voice at Hot. Null is no Voice", ""), 
/* 47 */   goodmorning(1361, "Goodmorning Voice. Null is no Voice", "minecraft:mob.wolf.bark"), 
/* 48 */   goodnight(1377, "Goodnight Voice. Null is no Voice", "minecraft:mob.ghast.affectionate_scream"), 
/*    */   
/*    */ 
/* 51 */   Null(0, "", null);
/*    */   
/*    */ 
/*    */   public final int index;
/*    */   
/*    */   public final String info;
/*    */   public final String DefaultValue;
/*    */   
/*    */   private LMM_EnumSound(int findex, String finfo, String fdefault)
/*    */   {
/* 61 */     this.index = findex;
/* 62 */     this.info = finfo;
/* 63 */     this.DefaultValue = fdefault;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static LMM_EnumSound getEnumSound(int pindex)
/*    */   {
/* 70 */     for (LMM_EnumSound le : ) {
/* 71 */       if (le.index == pindex) {
/* 72 */         return le;
/*    */       }
/*    */     }
/* 75 */     return Null;
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EnumSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
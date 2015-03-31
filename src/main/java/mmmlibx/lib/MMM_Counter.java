/*    */ package mmmlibx.lib;
/*    */ 
/*    */ 
/*    */ public class MMM_Counter
/*    */ {
/*    */   protected int fSetValue;
/*    */   
/*    */   protected int fMaxValue;
/*    */   
/*    */   protected int fDelayValue;
/*    */   
/*    */   protected int fCounter;
/*    */   
/*    */ 
/*    */   public MMM_Counter()
/*    */   {
/* 17 */     this(25, 20, -10);
/*    */   }
/*    */   
/*    */   public MMM_Counter(int pSetValue, int pMaxValue, int pDelayValue) {
/* 21 */     this.fSetValue = pSetValue;
/* 22 */     this.fMaxValue = pMaxValue;
/* 23 */     this.fDelayValue = pDelayValue;
/* 24 */     this.fCounter = pDelayValue;
/*    */   }
/*    */   
/*    */   public void setCountValue(int pSetValue, int pMaxValue, int pDelayValue)
/*    */   {
/* 29 */     this.fSetValue = pSetValue;
/* 30 */     this.fMaxValue = pMaxValue;
/* 31 */     this.fDelayValue = pDelayValue;
/*    */   }
/*    */   
/*    */   public void setValue(int pValue) {
/* 35 */     this.fCounter = pValue;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 39 */     return this.fCounter;
/*    */   }
/*    */   
/*    */   public void setEnable(boolean pFlag) {
/* 43 */     this.fCounter = (pFlag ? this.fSetValue : isEnable() ? this.fMaxValue : this.fDelayValue);
/*    */   }
/*    */   
/*    */   public boolean isEnable() {
/* 47 */     return this.fCounter > 0;
/*    */   }
/*    */   
/*    */   public boolean isDelay() {
/* 51 */     return this.fCounter > this.fDelayValue;
/*    */   }
/*    */   
/*    */   public boolean isReady() {
/* 55 */     return this.fCounter >= this.fMaxValue;
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 59 */     if (this.fCounter > this.fDelayValue) {
/* 60 */       this.fCounter -= 1;
/*    */     }
/*    */   }
/*    */   
/*    */   public void updateClient(boolean pFlag) {
/* 65 */     if (pFlag) {
/* 66 */       this.fCounter = this.fMaxValue;
/* 67 */     } else if (this.fCounter > 0) {
/* 68 */       this.fCounter = 0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_Counter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
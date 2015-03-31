/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import mmmlibx.lib.MMM_TextureBox;
/*     */ import mmmlibx.lib.MMM_TextureBoxServer;
/*     */ import mmmlibx.lib.MMM_TextureData;
/*     */ import mmmlibx.lib.MMM_TextureManager;
/*     */ import net.minecraft.command.CommandTime;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import wrapper.W_Common;
/*     */ 
/*     */ public class LMM_EntityMode_Test extends LMM_EntityModeBase implements net.minecraft.command.ICommand
/*     */ {
/*  24 */   public static boolean isEnable = false;
/*     */   
/*     */ 
/*     */   public LMM_EntityMode_Test(LMM_EntityLittleMaid pEntity)
/*     */   {
/*  29 */     super(pEntity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void init() {}
/*     */   
/*     */ 
/*     */   public int priority()
/*     */   {
/*  39 */     return 9900;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {}
/*     */   
/*     */ 
/*     */   public void showSpecial(LMM_RenderLittleMaid prenderlittlemaid, double px, double py, double pz)
/*     */   {
/*  49 */     if (!isEnable) { return;
/*     */     }
/*     */     
/*  52 */     List<String> llist = new ArrayList();
/*     */     
/*     */ 
/*  55 */     if (this.owner.maidDominantArm == 0) {
/*  56 */       llist.add(String.format("[R]:%d, L:%d, I:%d", new Object[] { Integer.valueOf(this.owner.mstatSwingStatus[0].index), Integer.valueOf(this.owner.mstatSwingStatus[1].index), Integer.valueOf(this.owner.maidInventory.currentItem) }));
/*  57 */       llist.add(String.format("swing[R]:%b:%f", new Object[] { Boolean.valueOf(this.owner.getSwingStatusDominant().isSwingInProgress), Float.valueOf(this.owner.getSwingStatusDominant().swingProgress) }));
/*     */     } else {
/*  59 */       llist.add(String.format("R:%d, [L]:%d, I:%d", new Object[] { Integer.valueOf(this.owner.mstatSwingStatus[0].index), Integer.valueOf(this.owner.mstatSwingStatus[1].index), Integer.valueOf(this.owner.maidInventory.currentItem) }));
/*  60 */       llist.add(String.format("swing[L]:%b:%f", new Object[] { Boolean.valueOf(this.owner.getSwingStatusDominant().isSwingInProgress), Float.valueOf(this.owner.getSwingStatusDominant().swingProgress) }));
/*     */     }
/*  62 */     llist.add(String.format("health:%f, death:%d, Exp:%d", new Object[] { Float.valueOf(this.owner.getHealth()), Integer.valueOf(this.owner.deathTime), Integer.valueOf(this.owner.getExperiencePoints(null)) }));
/*     */     
/*  64 */     llist.add(String.format("working:%b, sneak:%b, sugar:%b", new Object[] { Boolean.valueOf(this.owner.isWorking()), Boolean.valueOf(this.owner.isSneaking()), Boolean.valueOf(this.owner.isLookSuger()) }));
/*  65 */     llist.add(String.format("%s[%s]", new Object[] { this.owner.getMaidModeString(), this.owner.maidActiveModeClass == null ? "" : this.owner.maidActiveModeClass.getClass().getSimpleName() }));
/*  66 */     llist.add(String.format("Limit: %b[%b]", new Object[] { Boolean.valueOf(this.owner.isContract()), Boolean.valueOf(this.owner.isContractEX()) }));
/*  67 */     int li = this.owner.getDataWatcher().getWatchableObjectInt(20);
/*  68 */     llist.add(String.format("Texture=%s(%x/ %x), %s(%x / %x)", new Object[] { this.owner.textureData.getTextureName(0), Integer.valueOf(this.owner.textureData.textureIndex[0]), Integer.valueOf(li & 0xFFFF), this.owner.textureData.getTextureName(1), Integer.valueOf(this.owner.textureData.textureIndex[1]), Integer.valueOf(li >>> 16) }));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  73 */     double ld = llist.size() * 0.25D - 0.5D;
/*  74 */     for (String ls : llist) {
/*  75 */       prenderlittlemaid.renderLivingLabel(this.owner, ls, px, py + ld, pz, 64);
/*  76 */       ld -= 0.25D;
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object arg0)
/*     */   {
/* 101 */     return 0;
/*     */   }
/*     */   
/*     */   public String getCommandName()
/*     */   {
/* 106 */     return "LMMtest";
/*     */   }
/*     */   
/*     */ 
/*     */   public String getCommandUsage(ICommandSender var1)
/*     */   {
/* 112 */     return "/" + getCommandName() + " <0-4>";
/*     */   }
/*     */   
/*     */   public List getCommandAliases()
/*     */   {
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public void processCommand(ICommandSender var1, String[] var2)
/*     */   {
/* 122 */     if (var2.length > 0) {
/* 123 */       switch (Integer.valueOf(var2[0]).intValue()) {
/*     */       case 0: 
/* 125 */         isEnable = false;
/*     */         
/* 127 */         W_Common.notifyAdmins(var1, new CommandTime(), 0, "LMM TestMessage Disable", new Object[0]);
/* 128 */         break;
/*     */       case 1: 
/* 130 */         isEnable = true;
/*     */         
/* 132 */         W_Common.notifyAdmins(var1, new CommandTime(), 0, "LMM TestMessage Enable", new Object[0]);
/* 133 */         break;
/*     */       
/*     */ 
/*     */       case 2: 
/* 137 */         var1.addChatMessage(new ChatComponentText("textureServer:"));
/* 138 */         for (int li = 0; li < MMM_TextureManager.instance.textureServer.size(); li++) {
/* 139 */           MMM_TextureBoxServer lb = MMM_TextureManager.instance.getTextureBoxServer(li);
/* 140 */           var1.addChatMessage(new ChatComponentText(String.format("%4d : %04x : %s", new Object[] { Integer.valueOf(li), Integer.valueOf(lb.wildColor), lb.textureName })));
/*     */         }
/* 142 */         break;
/*     */       
/*     */       case 3: 
/* 145 */         var1.addChatMessage(new ChatComponentText("textures:"));
/* 146 */         for (MMM_TextureBox ltb : MMM_TextureManager.getTextureList()) {
/* 147 */           var1.addChatMessage(new ChatComponentText(ltb.textureName));
/*     */         }
/* 149 */         break;
/*     */       
/*     */       case 4: 
/* 152 */         var1.addChatMessage(new ChatComponentText("textureServerIndex:"));
/* 153 */         for (Map.Entry<MMM_TextureBox, Integer> ltb : MMM_TextureManager.instance.textureServerIndex.entrySet()) {
/* 154 */           var1.addChatMessage(new ChatComponentText(String.format("%04x, %s", new Object[] { ltb.getValue(), ((MMM_TextureBox)ltb.getKey()).textureName })));
/*     */         }
/*     */       
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */     } else {
/* 162 */       throw new WrongUsageException(getCommandUsage(var1), new Object[0]);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canCommandSenderUseCommand(ICommandSender var1)
/*     */   {
/* 168 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public List addTabCompletionOptions(ICommandSender var1, String[] var2)
/*     */   {
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isUsernameIndex(String[] var1, int var2)
/*     */   {
/* 179 */     return false;
/*     */   }
/*     */   
/*     */   public boolean interact(EntityPlayer pentityplayer, ItemStack pitemstack)
/*     */   {
/* 184 */     if ((isEnable) && (pitemstack.getItem() == Items.slime_ball))
/*     */     {
/* 186 */       this.owner.maidContractLimit -= 24000;
/* 187 */       return true;
/*     */     }
/* 189 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_EntityMode_Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
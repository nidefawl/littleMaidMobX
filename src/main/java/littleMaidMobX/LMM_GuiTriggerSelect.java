/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class LMM_GuiTriggerSelect extends GuiContainer
/*     */ {
/*     */   protected float scrolleWeaponset;
/*     */   protected float scrolleContainer;
/*  23 */   private static InventoryBasic inventory1 = new InventoryBasic("tmpsel", false, 40);
/*  24 */   private static InventoryBasic inventory2 = new InventoryBasic("tmpwep", false, 32);
/*     */   private int lastX;
/*     */   private int lastY;
/*     */   private boolean ismousePress;
/*     */   private int isScrolled;
/*     */   public LMM_GuiIFF owner;
/*  30 */   private GuiButton[] guiButton = new GuiButton[3];
/*     */   private LMM_ContainerTriggerSelect inventoryTrigger;
/*     */   private int selectPage;
/*     */   protected EntityPlayer target;
/*  34 */   protected static final ResourceLocation fguiTex = new ResourceLocation("lmmx", "textures/gui/container/littlemaidtrigger.png");
/*     */   
/*     */ 
/*     */   public LMM_GuiTriggerSelect(EntityPlayer entityplayer, LMM_GuiIFF guiowner)
/*     */   {
/*  39 */     super(new LMM_ContainerTriggerSelect(entityplayer));
/*  40 */     this.ySize = 216;
/*  41 */     this.owner = guiowner;
/*  42 */     this.inventoryTrigger = ((LMM_ContainerTriggerSelect)this.inventorySlots);
/*  43 */     this.target = entityplayer;
/*     */   }
/*     */   
/*     */   public void initGui()
/*     */   {
/*  48 */     super.initGui();
/*     */     
/*  50 */     this.guiButton[0] = new GuiButton(100, this.guiLeft + 7, this.guiTop + 193, 20, 20, "<");
/*  51 */     this.guiButton[1] = new GuiButton(101, this.guiLeft + 35, this.guiTop + 193, 106, 20, (String)LMM_TriggerSelect.selector.get(0));
/*  52 */     this.guiButton[2] = new GuiButton(102, this.guiLeft + 149, this.guiTop + 193, 20, 20, ">");
/*  53 */     this.buttonList.add(this.guiButton[0]);
/*  54 */     this.buttonList.add(this.guiButton[1]);
/*  55 */     this.buttonList.add(this.guiButton[2]);
/*  56 */     this.guiButton[1].enabled = false;
/*  57 */     this.selectPage = 0;
/*     */   }
/*     */   
/*     */   protected void keyTyped(char c, int i)
/*     */   {
/*  62 */     if (i == 1) {
/*  63 */       this.mc.displayGuiScreen(this.owner);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onGuiClosed()
/*     */   {
/*  70 */     setItemList();
/*     */     
/*  72 */     super.onGuiClosed();
/*     */   }
/*     */   
/*     */   public boolean doesGuiPauseGame()
/*     */   {
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   protected void actionPerformed(GuiButton guibutton)
/*     */   {
/*  82 */     setItemList();
/*  83 */     if ((guibutton.id == 100) && 
/*  84 */       (--this.selectPage < 0)) {
/*  85 */       this.selectPage = (LMM_TriggerSelect.selector.size() - 1);
/*     */     }
/*     */     
/*  88 */     if ((guibutton.id != 101) || (
/*     */     
/*     */ 
/*  91 */       (guibutton.id == 102) && 
/*  92 */       (++this.selectPage >= LMM_TriggerSelect.selector.size()))) {
/*  93 */       this.selectPage = 0;
/*     */     }
/*     */     
/*  96 */     String ls = (String)LMM_TriggerSelect.selector.get(this.selectPage);
/*  97 */     this.guiButton[1].displayString = ls;
/*  98 */     this.inventoryTrigger.setWeaponSelect(mmmlibx.lib.MMM_Helper.getPlayerName(this.target), ls);
/*  99 */     this.inventoryTrigger.setWeaponlist(0.0F);
/*     */   }
/*     */   
/*     */   protected void handleMouseClick(Slot slot, int i, int j, int flag)
/*     */   {
/* 104 */     boolean var5 = flag == 1;
/* 105 */     flag = (i == 64537) && (flag == 0) ? 4 : flag;
/* 106 */     ItemStack itemstack; if (slot != null) {
/* 107 */       if ((slot.inventory == inventory1) && (flag == 0)) {
/* 108 */         InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
/* 109 */         ItemStack itemstack1 = inventoryplayer.getItemStack();
/* 110 */         ItemStack itemstack4 = slot.getStack();
/* 111 */         if ((itemstack1 != null) && (itemstack4 != null) && (itemstack1.getItem() == itemstack4.getItem()))
/*     */         {
/*     */ 
/* 114 */           if (j != 0) {
/* 115 */             inventoryplayer.setItemStack(null);
/*     */           }
/* 117 */         } else if (itemstack1 != null) {
/* 118 */           inventoryplayer.setItemStack(null);
/* 119 */         } else if (itemstack4 == null) {
/* 120 */           inventoryplayer.setItemStack(null);
/*     */         } else {
/* 122 */           inventoryplayer.setItemStack(ItemStack.copyItemStack(itemstack4));
/*     */         }
/*     */       } else {
/* 125 */         this.inventorySlots.slotClick(slot.slotNumber, j, flag, this.mc.thePlayer);
/* 126 */         itemstack = this.inventorySlots.getSlot(slot.slotNumber).getStack();
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 132 */       InventoryPlayer inventoryplayer1 = this.mc.thePlayer.inventory;
/* 133 */       inventoryplayer1.setItemStack(null);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2)
/*     */   {
/* 139 */     this.mc.fontRenderer.drawString("Item selection", 8, 6, 4210752);
/* 140 */     this.mc.fontRenderer.drawString("Trigger Items", 8, 110, 4210752);
/*     */   }
/*     */   
/*     */   public void handleMouseInput()
/*     */   {
/* 145 */     super.handleMouseInput();
/* 146 */     int i = Mouse.getEventDWheel();
/* 147 */     if (i != 0) {
/* 148 */       if (this.lastY < this.height / 2) {
/* 149 */         int j = this.inventoryTrigger.itemList.size() / 8 - 5 + 1;
/* 150 */         if (i > 0) {
/* 151 */           i = 1;
/*     */         }
/* 153 */         if (i < 0) {
/* 154 */           i = -1;
/*     */         }
/* 156 */         this.scrolleContainer = ((float)(this.scrolleContainer - i / j));
/* 157 */         if (this.scrolleContainer < 0.0F) {
/* 158 */           this.scrolleContainer = 0.0F;
/*     */         }
/* 160 */         if (this.scrolleContainer > 1.0F) {
/* 161 */           this.scrolleContainer = 1.0F;
/*     */         }
/* 163 */         this.inventoryTrigger.scrollTo(this.scrolleContainer);
/*     */       } else {
/* 165 */         int j = this.inventoryTrigger.weaponSelect.size() / 8 - 4 + 1;
/* 166 */         if (i > 0) {
/* 167 */           i = 1;
/*     */         }
/* 169 */         if (i < 0) {
/* 170 */           i = -1;
/*     */         }
/* 172 */         if (j > 0) {
/* 173 */           this.scrolleWeaponset = ((float)(this.scrolleWeaponset - i / j));
/*     */         } else {
/* 175 */           this.scrolleWeaponset = 0.0F;
/*     */         }
/* 177 */         if (this.scrolleWeaponset < 0.0F) {
/* 178 */           this.scrolleWeaponset = 0.0F;
/*     */         }
/* 180 */         if (this.scrolleWeaponset > 1.0F) {
/* 181 */           this.scrolleWeaponset = 1.0F;
/*     */         }
/* 183 */         this.inventoryTrigger.setWeaponlist(this.scrolleWeaponset);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void drawScreen(int i, int j, float f)
/*     */   {
/* 190 */     this.lastX = i;
/* 191 */     this.lastY = j;
/* 192 */     boolean flag = Mouse.isButtonDown(0);
/* 193 */     int k = this.guiLeft;
/* 194 */     int l = this.guiTop;
/* 195 */     int i1 = k + 155;
/* 196 */     int j1 = l + 17;
/* 197 */     int k1 = i1 + 14;
/* 198 */     int l1 = j1 + 90;
/* 199 */     if (!flag) {
/* 200 */       this.isScrolled = 0;
/*     */     }
/* 202 */     if ((!this.ismousePress) && (flag) && (i >= i1) && (j >= j1) && (i < k1) && (j < l1)) {
/* 203 */       this.isScrolled = 1;
/*     */     }
/* 205 */     if (this.isScrolled == 1) {
/* 206 */       this.scrolleContainer = ((j - (j1 + 8)) / (l1 - j1 - 16.0F));
/* 207 */       if (this.scrolleContainer < 0.0F) {
/* 208 */         this.scrolleContainer = 0.0F;
/*     */       }
/* 210 */       if (this.scrolleContainer > 1.0F) {
/* 211 */         this.scrolleContainer = 1.0F;
/*     */       }
/* 213 */       this.inventoryTrigger.scrollTo(this.scrolleContainer);
/*     */     }
/* 215 */     j1 = l + 120;
/* 216 */     l1 = j1 + 72;
/* 217 */     if ((!this.ismousePress) && (flag) && (i >= i1) && (j >= j1) && (i < k1) && (j < l1)) {
/* 218 */       this.isScrolled = 2;
/*     */     }
/* 220 */     if (this.isScrolled == 2) {
/* 221 */       this.scrolleWeaponset = ((j - (j1 + 8)) / (l1 - j1 - 16.0F));
/* 222 */       if (this.scrolleWeaponset < 0.0F) {
/* 223 */         this.scrolleWeaponset = 0.0F;
/*     */       }
/* 225 */       if (this.scrolleWeaponset > 1.0F) {
/* 226 */         this.scrolleWeaponset = 1.0F;
/*     */       }
/* 228 */       this.inventoryTrigger.setWeaponlist(this.scrolleWeaponset);
/*     */     }
/* 230 */     this.ismousePress = flag;
/* 231 */     super.drawScreen(i, j, f);
/* 232 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 233 */     GL11.glDisable(2896);
/*     */   }
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
/*     */   {
/* 238 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 239 */     mmmlibx.lib.Client.setTexture(fguiTex);
/* 240 */     int l = this.guiLeft;
/* 241 */     int i1 = this.guiTop;
/* 242 */     drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
/*     */     
/* 244 */     int j1 = l + 155;
/* 245 */     int k1 = i1 + 17;
/* 246 */     int l1 = k1 + 88 + 2;
/*     */     
/*     */ 
/* 249 */     drawTexturedModalRect(l + 154, i1 + 17 + (int)((l1 - k1 - 17) * this.scrolleContainer), 176, 0, 16, 16);
/*     */     
/*     */ 
/* 252 */     drawTexturedModalRect(l + 154, i1 + 120 + (int)((l1 - k1 - 35) * this.scrolleWeaponset), 176, 0, 16, 16);
/*     */   }
/*     */   
/*     */ 
/*     */   private void setItemList()
/*     */   {
/* 258 */     List list1 = this.inventoryTrigger.getItemList();
/* 259 */     list1.clear();
/* 260 */     for (int i = 0; i < this.inventoryTrigger.weaponSelect.size(); i++) {
/* 261 */       ItemStack is = (ItemStack)this.inventoryTrigger.weaponSelect.get(i);
/* 262 */       if ((is != null) && (!list1.contains(is.getItem()))) {
/* 263 */         list1.add(is.getItem());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static InventoryBasic getInventory1() {
/* 269 */     return inventory1;
/*     */   }
/*     */   
/*     */   public static InventoryBasic getInventory2() {
/* 273 */     return inventory2;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_GuiTriggerSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
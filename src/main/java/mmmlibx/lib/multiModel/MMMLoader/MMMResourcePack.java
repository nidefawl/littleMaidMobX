/*     */ package mmmlibx.lib.multiModel.MMMLoader;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import cpw.mods.fml.common.ModContainer;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Set;
/*     */ import javax.imageio.ImageIO;
/*     */ import littleMaidMobX.LMM_SoundManager;
/*     */ import mmmlibx.lib.MMMLib;
/*     */ import net.minecraft.client.resources.DefaultResourcePack;
/*     */ import net.minecraft.client.resources.IResourcePack;
/*     */ import net.minecraft.client.resources.data.IMetadataSection;
/*     */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ public class MMMResourcePack
/*     */   implements IResourcePack
/*     */ {
/*     */   protected ModContainer ownerContainer;
/*     */   
/*     */   public MMMResourcePack(ModContainer pContainer)
/*     */   {
/*  37 */     this.ownerContainer = pContainer;
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(ResourceLocation par1ResourceLocation) throws IOException
/*     */   {
/*  42 */     InputStream inputstream = getResourceStream(par1ResourceLocation, true);
/*     */     
/*  44 */     if (inputstream != null) {
/*  45 */       return inputstream;
/*     */     }
/*  47 */     throw new FileNotFoundException(par1ResourceLocation.getResourcePath());
/*     */   }
/*     */   
/*     */   private InputStream getResourceStream(ResourceLocation resource, boolean b)
/*     */   {
/*  52 */     String path = resource.getResourcePath();
/*  53 */     InputStream lis = MMMResourcePack.class.getResourceAsStream(path);
/*  54 */     if (resource.getResourceDomain().equalsIgnoreCase("lmmx"))
/*     */     {
/*  56 */       if (lis == null)
/*     */       {
/*  58 */         lis = LMM_SoundManager.getResourceStream(resource);
/*     */       }
/*     */       
/*  61 */       MMMLib.Debug("getResource:" + b + ":%s : %s", new Object[] { resource, lis });
/*     */     }
/*  63 */     return lis;
/*     */   }
/*     */   
/*     */   public boolean resourceExists(ResourceLocation par1ResourceLocation)
/*     */   {
/*  68 */     InputStream is = getResourceStream(par1ResourceLocation, false);
/*     */     
/*     */ 
/*     */ 
/*  72 */     return is != null;
/*     */   }
/*     */   
/*  75 */   public static final Set lmmxResourceDomains = ImmutableSet.of("lmmx");
/*     */   
/*     */   public Set getResourceDomains()
/*     */   {
/*  79 */     return lmmxResourceDomains;
/*     */   }
/*     */   
/*     */ 
/*     */   public IMetadataSection getPackMetadata(IMetadataSerializer par1MetadataSerializer, String par2Str)
/*     */   {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   public BufferedImage getPackImage()
/*     */   {
/*     */     try
/*     */     {
/*  92 */       return ImageIO.read(DefaultResourcePack.class.getResourceAsStream("/" + new ResourceLocation("pack.png").getResourcePath()));
/*     */     }
/*     */     catch (IOException e) {
/*  95 */       e.printStackTrace();
/*     */     }
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   public String getPackName()
/*     */   {
/* 102 */     return "Default";
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/MMMLoader/MMMResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
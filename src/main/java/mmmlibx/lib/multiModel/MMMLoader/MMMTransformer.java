/*     */ package mmmlibx.lib.multiModel.MMMLoader;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import net.minecraft.launchwrapper.IClassTransformer;
/*     */ import org.objectweb.asm.ClassReader;
/*     */ import org.objectweb.asm.ClassWriter;
/*     */ import org.objectweb.asm.Opcodes;
/*     */ import org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.objectweb.asm.tree.ClassNode;
/*     */ import org.objectweb.asm.tree.FieldInsnNode;
/*     */ import org.objectweb.asm.tree.FieldNode;
/*     */ import org.objectweb.asm.tree.InsnList;
/*     */ import org.objectweb.asm.tree.InvokeDynamicInsnNode;
/*     */ import org.objectweb.asm.tree.LocalVariableNode;
/*     */ import org.objectweb.asm.tree.MethodInsnNode;
/*     */ import org.objectweb.asm.tree.MethodNode;
/*     */ import org.objectweb.asm.tree.MultiANewArrayInsnNode;
/*     */ import org.objectweb.asm.tree.TypeInsnNode;
/*     */ 
/*     */ public class MMMTransformer implements IClassTransformer, Opcodes
/*     */ {
/*  25 */   private static String packege = "mmmlibx/lib/multiModel/model/mc162/";
/*     */   
/*  27 */   private static final Map<String, String> targets = new HashMap()
/*     */   {
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
/*     */     private void add(String pName)
/*     */     {
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
/*  53 */       String replaceName = pName;
/*  54 */       put("MMM_" + pName, MMMTransformer.packege + replaceName);
/*     */     }
/*     */   };
/*     */   
/*  58 */   public static boolean isEnable = false;
/*     */   
/*     */   private boolean isChange;
/*     */   
/*     */   public static void Debug(String pText, Object... pData)
/*     */   {
/*  64 */     if (mmmlibx.lib.MMMLib.isDebugMessage)
/*     */     {
/*  66 */       System.out.println(String.format("MMMTransformer-" + pText, pData));
/*     */     }
/*     */   }
/*     */   
/*  70 */   public static java.util.List<String> ignoreNameSpace = com.google.common.collect.Lists.newArrayList(new String[] { "modchu.model", "modchu.lib", "net.minecraft.src.mod_Modchu_ModchuLib", "modchu.pflm", "modchu.pflmf" });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] transform(String name, String transformedName, byte[] basicClass)
/*     */   {
/*  81 */     isEnable = true;
/*     */     
/*  83 */     for (String header : ignoreNameSpace) {
/*  84 */       if (name.startsWith(header)) { return basicClass;
/*     */       }
/*     */     }
/*  87 */     if ((basicClass != null) && (isEnable)) {
/*  88 */       return replacer(name, transformedName, basicClass);
/*     */     }
/*  90 */     return basicClass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private byte[] replacer(String name, String transformedName, byte[] basicClass)
/*     */   {
/* 101 */     ClassReader lcreader = new ClassReader(basicClass);
/* 102 */     String superName = lcreader.getSuperName();
/* 103 */     boolean replaceSuper = targets.containsKey(superName);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 108 */     this.isChange = false;
/*     */     
/*     */ 
/* 111 */     ClassNode lcnode = new ClassNode();
/* 112 */     lcreader.accept(lcnode, 0);
/* 113 */     lcnode.superName = checkMMM(lcnode.superName);
/* 114 */     if (replaceSuper)
/*     */     {
/* 116 */       Debug("Load Old-MulitiModel: %s extends %s -> %s", new Object[] { name, superName, lcnode.superName });
/*     */     }
/*     */     
/*     */ 
/* 120 */     for (FieldNode lfn : lcnode.fields) {
/* 121 */       lfn.desc = checkMMM(lfn.desc);
/*     */     }
/*     */     
/*     */ 
/* 125 */     for (MethodNode lmn : lcnode.methods) {
/* 126 */       lmn.desc = checkMMM(lmn.desc);
/*     */       
/* 128 */       if (lmn.localVariables != null)
/*     */       {
/* 130 */         for (LocalVariableNode lvn : lmn.localVariables)
/*     */         {
/* 132 */           if (lvn.desc != null) lvn.desc = checkMMM(lvn.desc);
/* 133 */           if (lvn.name != null) lvn.name = checkMMM(lvn.name);
/* 134 */           if (lvn.signature != null) { lvn.signature = checkMMM(lvn.signature);
/*     */           }
/*     */         }
/*     */       }
/* 138 */       AbstractInsnNode lin = lmn.instructions.getFirst();
/* 139 */       while (lin != null) {
/* 140 */         if ((lin instanceof FieldInsnNode)) {
/* 141 */           ((FieldInsnNode)lin).desc = checkMMM(((FieldInsnNode)lin).desc);
/* 142 */           ((FieldInsnNode)lin).name = checkMMM(((FieldInsnNode)lin).name);
/* 143 */           ((FieldInsnNode)lin).owner = checkMMM(((FieldInsnNode)lin).owner);
/* 144 */         } else if ((lin instanceof InvokeDynamicInsnNode)) {
/* 145 */           ((InvokeDynamicInsnNode)lin).desc = checkMMM(((InvokeDynamicInsnNode)lin).desc);
/* 146 */           ((InvokeDynamicInsnNode)lin).name = checkMMM(((InvokeDynamicInsnNode)lin).name);
/* 147 */         } else if ((lin instanceof MethodInsnNode)) {
/* 148 */           ((MethodInsnNode)lin).desc = checkMMM(((MethodInsnNode)lin).desc);
/* 149 */           ((MethodInsnNode)lin).name = checkMMM(((MethodInsnNode)lin).name);
/* 150 */           ((MethodInsnNode)lin).owner = checkMMM(((MethodInsnNode)lin).owner);
/* 151 */         } else if ((lin instanceof MultiANewArrayInsnNode)) {
/* 152 */           ((MultiANewArrayInsnNode)lin).desc = checkMMM(((MultiANewArrayInsnNode)lin).desc);
/* 153 */         } else if ((lin instanceof TypeInsnNode)) {
/* 154 */           ((TypeInsnNode)lin).desc = checkMMM(((TypeInsnNode)lin).desc);
/*     */         }
/* 156 */         lin = lin.getNext();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 161 */     if (this.isChange) {
/* 162 */       ClassWriter lcwriter = new ClassWriter(3);
/* 163 */       lcnode.accept(lcwriter);
/* 164 */       byte[] lb = lcwriter.toByteArray();
/* 165 */       Debug("Replace: %s", new Object[] { name });
/* 166 */       return lb;
/*     */     }
/* 168 */     return basicClass;
/*     */   }
/*     */   
/*     */   private String checkMMM(String pText)
/*     */   {
/* 173 */     for (Map.Entry<String, String> le : targets.entrySet()) {
/* 174 */       if (pText.indexOf((String)le.getKey()) > -1) {
/* 175 */         String result = pText.replace((CharSequence)le.getKey(), (CharSequence)le.getValue());
/*     */         
/* 177 */         this.isChange = true;
/* 178 */         return result;
/*     */       }
/*     */     }
/* 181 */     return pText;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/MMMLoader/MMMTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */
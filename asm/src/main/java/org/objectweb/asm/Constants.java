// ASM: a very small and fast Java bytecode manipulation framework
// Copyright (c) 2000-2011 INRIA, France Telecom
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
// 3. Neither the name of the copyright holders nor the names of its
//    contributors may be used to endorse or promote products derived from
//    this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
// THE POSSIBILITY OF SUCH DAMAGE.
package org.objectweb.asm;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * Defines additional JVM opcodes, access flags and constants which are not part of the ASM public
 * API.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-6.html">JVMS 6</a>
 * @author Eric Bruneton
 */
public final class Constants {

  // The ClassFile attribute names, in the order they are defined in
  // https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-4.html#jvms-4.7-300.

  public static final String CONSTANT_VALUE = "ConstantValue";
  public static final String CODE = "Code";
  public static final String STACK_MAP_TABLE = "StackMapTable";
  public static final String EXCEPTIONS = "Exceptions";
  public static final String INNER_CLASSES = "InnerClasses";
  public static final String ENCLOSING_METHOD = "EnclosingMethod";
  public static final String SYNTHETIC = "Synthetic";
  public static final String SIGNATURE = "Signature";
  public static final String SOURCE_FILE = "SourceFile";
  public static final String SOURCE_DEBUG_EXTENSION = "SourceDebugExtension";
  public static final String LINE_NUMBER_TABLE = "LineNumberTable";
  public static final String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
  public static final String LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
  public static final String DEPRECATED = "Deprecated";
  public static final String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
  public static final String RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
  public static final String RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
  public static final String RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS =
      "RuntimeInvisibleParameterAnnotations";
  public static final String RUNTIME_VISIBLE_TYPE_ANNOTATIONS = "RuntimeVisibleTypeAnnotations";
  public static final String RUNTIME_INVISIBLE_TYPE_ANNOTATIONS = "RuntimeInvisibleTypeAnnotations";
  public static final String ANNOTATION_DEFAULT = "AnnotationDefault";
  public static final String BOOTSTRAP_METHODS = "BootstrapMethods";
  public static final String METHOD_PARAMETERS = "MethodParameters";
  public static final String MODULE = "Module";
  public static final String MODULE_PACKAGES = "ModulePackages";
  public static final String MODULE_MAIN_CLASS = "ModuleMainClass";
  public static final String NEST_HOST = "NestHost";
  public static final String NEST_MEMBERS = "NestMembers";
  public static final String PERMITTED_SUBCLASSES = "PermittedSubclasses";
  public static final String RECORD = "Record";

  // ASM specific access flags.
  // WARNING: the 16 least significant bits must NOT be used, to avoid conflicts with standard
  // access flags, and also to make sure that these flags are automatically filtered out when
  // written in class files (because access flags are stored using 16 bits only).

  public static final int ACC_CONSTRUCTOR = 0x40000; // method access flag.

  // ASM specific stack map frame types, used in {@link ClassVisitor#visitFrame}.

  /**
   * A frame inserted between already existing frames. This internal stack map frame type (in
   * addition to the ones declared in {@link Opcodes}) can only be used if the frame content can be
   * computed from the previous existing frame and from the instructions between this existing frame
   * and the inserted one, without any knowledge of the type hierarchy. This kind of frame is only
   * used when an unconditional jump is inserted in a method while expanding an ASM specific
   * instruction. Keep in sync with Opcodes.java.
   */
  public static final int F_INSERT = 256;

  // The JVM opcode values which are not part of the ASM public API.
  // See https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-6.html.

  public static final int LDC_W = 19;
  public static final int LDC2_W = 20;
  public static final int ILOAD_0 = 26;
  public static final int ILOAD_1 = 27;
  public static final int ILOAD_2 = 28;
  public static final int ILOAD_3 = 29;
  public static final int LLOAD_0 = 30;
  public static final int LLOAD_1 = 31;
  public static final int LLOAD_2 = 32;
  public static final int LLOAD_3 = 33;
  public static final int FLOAD_0 = 34;
  public static final int FLOAD_1 = 35;
  public static final int FLOAD_2 = 36;
  public static final int FLOAD_3 = 37;
  public static final int DLOAD_0 = 38;
  public static final int DLOAD_1 = 39;
  public static final int DLOAD_2 = 40;
  public static final int DLOAD_3 = 41;
  public static final int ALOAD_0 = 42;
  public static final int ALOAD_1 = 43;
  public static final int ALOAD_2 = 44;
  public static final int ALOAD_3 = 45;
  public static final int ISTORE_0 = 59;
  public static final int ISTORE_1 = 60;
  public static final int ISTORE_2 = 61;
  public static final int ISTORE_3 = 62;
  public static final int LSTORE_0 = 63;
  public static final int LSTORE_1 = 64;
  public static final int LSTORE_2 = 65;
  public static final int LSTORE_3 = 66;
  public static final int FSTORE_0 = 67;
  public static final int FSTORE_1 = 68;
  public static final int FSTORE_2 = 69;
  public static final int FSTORE_3 = 70;
  public static final int DSTORE_0 = 71;
  public static final int DSTORE_1 = 72;
  public static final int DSTORE_2 = 73;
  public static final int DSTORE_3 = 74;
  public static final int ASTORE_0 = 75;
  public static final int ASTORE_1 = 76;
  public static final int ASTORE_2 = 77;
  public static final int ASTORE_3 = 78;
  public static final int WIDE = 196;
  public static final int GOTO_W = 200;
  public static final int JSR_W = 201;

  // Constants to convert between normal and wide jump instructions.

  // The delta between the GOTO_W and JSR_W opcodes and GOTO and JUMP.
  public static final int WIDE_JUMP_OPCODE_DELTA = GOTO_W - Opcodes.GOTO;

  // Constants to convert JVM opcodes to the equivalent ASM specific opcodes, and vice versa.

  // The delta between the ASM_IFEQ, ..., ASM_IF_ACMPNE, ASM_GOTO and ASM_JSR opcodes
  // and IFEQ, ..., IF_ACMPNE, GOTO and JSR.
  public static final int ASM_OPCODE_DELTA = 49;

  // The delta between the ASM_IFNULL and ASM_IFNONNULL opcodes and IFNULL and IFNONNULL.
  public static final int ASM_IFNULL_OPCODE_DELTA = 20;

  // ASM specific opcodes, used for long forward jump instructions.

  public static final int ASM_IFEQ = Opcodes.IFEQ + ASM_OPCODE_DELTA;
  public static final int ASM_IFNE = Opcodes.IFNE + ASM_OPCODE_DELTA;
  public static final int ASM_IFLT = Opcodes.IFLT + ASM_OPCODE_DELTA;
  public static final int ASM_IFGE = Opcodes.IFGE + ASM_OPCODE_DELTA;
  public static final int ASM_IFGT = Opcodes.IFGT + ASM_OPCODE_DELTA;
  public static final int ASM_IFLE = Opcodes.IFLE + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ICMPEQ = Opcodes.IF_ICMPEQ + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ICMPNE = Opcodes.IF_ICMPNE + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ICMPLT = Opcodes.IF_ICMPLT + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ICMPGE = Opcodes.IF_ICMPGE + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ICMPGT = Opcodes.IF_ICMPGT + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ICMPLE = Opcodes.IF_ICMPLE + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ACMPEQ = Opcodes.IF_ACMPEQ + ASM_OPCODE_DELTA;
  public static final int ASM_IF_ACMPNE = Opcodes.IF_ACMPNE + ASM_OPCODE_DELTA;
  public static final int ASM_GOTO = Opcodes.GOTO + ASM_OPCODE_DELTA;
  public static final int ASM_JSR = Opcodes.JSR + ASM_OPCODE_DELTA;
  public static final int ASM_IFNULL = Opcodes.IFNULL + ASM_IFNULL_OPCODE_DELTA;
  public static final int ASM_IFNONNULL = Opcodes.IFNONNULL + ASM_IFNULL_OPCODE_DELTA;
  public static final int ASM_GOTO_W = 220;

  private Constants() {}

  public static void checkAsmExperimental(final Object caller) {
    Class<?> callerClass = caller.getClass();
    String internalName = callerClass.getName().replace('.', '/');
    if (!isWhitelisted(internalName)) {
      checkIsPreview(callerClass.getClassLoader().getResourceAsStream(internalName + ".class"));
    }
  }

  public static boolean isWhitelisted(final String internalName) {
    if (!internalName.startsWith("org/objectweb/asm/")) {
      return false;
    }
    String member = "(Annotation|Class|Field|Method|Module|RecordComponent|Signature)";
    return internalName.contains("Test$")
        || Pattern.matches(
            "org/objectweb/asm/util/Trace" + member + "Visitor(\\$.*)?", internalName)
        || Pattern.matches(
            "org/objectweb/asm/util/Check" + member + "Adapter(\\$.*)?", internalName);
  }

  public static void checkIsPreview(final InputStream classInputStream) {
    if (classInputStream == null) {
      throw new IllegalStateException("Bytecode not available, can't check class version");
    }
    int minorVersion;
    try (DataInputStream callerClassStream = new DataInputStream(classInputStream); ) {
      callerClassStream.readInt();
      minorVersion = callerClassStream.readUnsignedShort();
    } catch (IOException ioe) {
      throw new IllegalStateException("I/O error, can't check class version", ioe);
    }
    if (minorVersion != 0xFFFF) {
      throw new IllegalStateException(
          "ASM9_EXPERIMENTAL can only be used by classes compiled with --enable-preview");
    }
  }
}

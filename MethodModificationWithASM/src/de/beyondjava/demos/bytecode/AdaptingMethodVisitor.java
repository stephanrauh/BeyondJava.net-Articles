package de.beyondjava.demos.bytecode;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AdaptingMethodVisitor extends MethodVisitor implements Opcodes {
	
	@Override
	public void visitCode() {
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(16, l0);
		mv.visitIntInsn(SIPUSH, 10000);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitInsn(IMUL);
		mv.visitInsn(ISUB);
		mv.visitInsn(I2D);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "sqrt", "(D)D", false);
		mv.visitInsn(D2I);
		mv.visitVarInsn(ISTORE, 3);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLineNumber(17, l1);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ILOAD, 3);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKESPECIAL, "de/beyondjava/demos/bytecode/CircleWithoutImplementation", "draw8Pixels", "(IILjava/awt/Graphics;)V", false);
		Label l2 = new Label();
		mv.visitLabel(l2);
		mv.visitEnd();
	}

	public AdaptingMethodVisitor(MethodVisitor mv) {
		super(Opcodes.ASM5, mv);
	}

}

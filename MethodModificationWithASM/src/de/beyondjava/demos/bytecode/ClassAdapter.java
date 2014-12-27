package de.beyondjava.demos.bytecode;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassAdapter extends ClassVisitor {

	public ClassAdapter(ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
	}

	@Override
	public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature,
			final String[] exceptions) {
		if (name.equals("calculateAndDrawPixel")) {
			MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
			return new AdaptingMethodVisitor(mv);
		} else {
			return cv.visitMethod(access, name, desc, signature, exceptions);
		}
	}
}
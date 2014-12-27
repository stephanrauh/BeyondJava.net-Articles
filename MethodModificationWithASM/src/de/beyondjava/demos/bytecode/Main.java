package de.beyondjava.demos.bytecode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class Main extends ClassLoader {

	public static void main(String[] args) {
		try {
			ClassLoader loader = new Main();
			Class<?> c = loader.loadClass("de.beyondjava.demos.bytecode.CircleWithoutImplementation");
			c.newInstance();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected synchronized Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
		if (!name.contains("CircleWithoutImplementation")) {
			return super.loadClass(name, resolve);
		}
		try {
			InputStream is = readClass(name);
			ClassWriter cw = modifyClass(is);
			return loadClassIntoMemory(name, cw);
		} catch (Exception e) {
			throw new ClassNotFoundException(name, e);
		}
	}

	private Class<?> loadClassIntoMemory(final String name, ClassWriter cw) throws ClassFormatError {
		byte[] b = cw.toByteArray();
		return defineClass(name, b, 0, b.length);
	}

	private ClassWriter modifyClass(InputStream is) throws IOException {
		ClassReader cr = new ClassReader(is);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor cv = new ClassAdapter(cw);
		cr.accept(cv, 0);
		return cw;
	}

	private InputStream readClass(final String name) {
		String resource = name.replace('.', '/') + ".class";
		InputStream is = getResourceAsStream(resource);
		return is;
	}

}

public class CacheSize {
	private static int[] array;
	private static long cumulated3 = 0;

	public static void main2(String[] args) {
		array = new int[128 * 1024 * 1024];
		for (int i = 0; i < 1000; i++) {
			loop1();
			loop2();
			loop4();
			loop8();
			loop16();
			loop32();
			loop64();
			loop128();
		}
		cumulated3 = 0;
		loop1();
		System.out.println(" 1 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop2();
		System.out.println(" 2 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop4();
		System.out.println(" 4 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop8();
		System.out.println(" 8 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop16();
		System.out.println("16 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop32();
		System.out.println("32 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop64();
		System.out.println("64 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;
		loop128();
		System.out.println("128MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		cumulated3 = 0;

	}

	public static void main(String[] args) {
		array = new int[128 * 1024 * 1024];
		for (int i = 0; i < 100; i++) {
			for (int step = 1; step <= 256; step *= 2) {
				loopVariableStepWithLoopUnrolling(step);
			}
		}
		for (int step = 1; step <= 256; step *= 2) {
			cumulated3 = 0;
			loopVariableStepWithLoopUnrolling(step);
			System.out.println(" 1 MB: " + (cumulated3 / 1000) / 1000.0d + " ms");
		}

	}

	public static void loop1() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 1 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop2() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 2 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop4() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 4 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop8() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 8 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop16() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 16 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop32() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 32 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop64() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 64 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	public static void loop128() {
		int steps = 1024 * 1024; // Arbitrary number of steps
		int lengthMod = 128 * 1024 * 4;
		long start = System.nanoTime();
		for (int i = 0; i < steps; i++) {
			array[(i << 4) & lengthMod]++; // (x & lengthMod) is equal to (x % arr.Length)
		}
		long time = System.nanoTime() - start;
		cumulated3 += time;
	}

	private static void loopVariableStepWithLoopUnrolling(final int step) {
		int length = array.length;
		for (int i = 0; i < length;) {
			array[i]--;
			i+=step;
			array[i]--;
			i += step;
		}
	}
}
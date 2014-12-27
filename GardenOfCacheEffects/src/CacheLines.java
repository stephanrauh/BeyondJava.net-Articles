public class CacheLines {
	private static int[] array = new int[64 * 1024 * 10];
	private static long cumulated1 = 0;
	private static long cumulated2 = 0;
	private static long cumulated3 = 0;
	
	public static void main(String[] args) {
//		for (int warmup = 1; warmup <= 10000; warmup++) {
//			if (warmup % 10000 == 0) {
//				System.out.print("Loop 1/" + warmup + " took " + (cumulated1 / 1000) / 1000.0 + " ms, ");
//				System.out.println("Loop 2/" + warmup + " took " + (cumulated2 / 1000) / 1000.0 + " ms (cumulated over 10000 repetitions)");
//				cumulated1 = 0l;
//				cumulated2 = 0l;
//			}
//			test();
//		}
		for (int step=1; step<=64;step++ )
		{
			for (int warmup = 1; warmup <= 10000; warmup++) {
				if (step==1) test1();
				else if (step==2) test2();
				else if (step==4) test4();
				else if (step==8) test8();
				else if (step==12) test12();
				else if (step==13) test13();
				else if (step==14) test14();
				else if (step==15) test15();
				else if (step==16) test16();
				else if (step==17) test17();
				else if (step==18) test18();
				else if (step==24) test24();
				else if (step==32) test32();
				else if (step==64) test64();
//				else
//				testVariableStep(step);
				if (warmup % 10000 == 0 && cumulated3>1000) {
					System.out.println("Step " + step + "/" + warmup + " took " + (cumulated3 / 1000) / 1000.0 + " ms (cumulated over 10000 repetitions)");
					cumulated3 = 0l;
				}
			}
			
		}

	}

	private static void test() {
		long start1 = System.nanoTime();
		loop1();
		long time1 = System.nanoTime() - start1;
		cumulated1 += time1;

		long start2 = System.nanoTime();
		loop2();
		long time2 = System.nanoTime() - start2;
		cumulated2 += time2;
	}
	
	private static void testVariableStep(final int step) {
		long start3 = System.nanoTime();
		loopVariableStep(step);
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test1() {
		long start3 = System.nanoTime();
		loop1();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test2() {
		long start3 = System.nanoTime();
		loop2();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test4() {
		long start3 = System.nanoTime();
		loop4();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test8() {
		long start3 = System.nanoTime();
		loop8();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test12() {
		long start3 = System.nanoTime();
		loop12();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test13() {
		long start3 = System.nanoTime();
		loop13();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test14() {
		long start3 = System.nanoTime();
		loop14();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test15() {
		long start3 = System.nanoTime();
		loop15();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test16() {
		long start3 = System.nanoTime();
		loop16();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test17() {
		long start3 = System.nanoTime();
		loop17();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test18() {
		long start3 = System.nanoTime();
		loop18();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test24() {
		long start3 = System.nanoTime();
		loop24();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test32() {
		long start3 = System.nanoTime();
		loop32();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}
	private static void test64() {
		long start3 = System.nanoTime();
		loop64();
		long time3 = System.nanoTime() - start3;
		cumulated3 += time3;
	}

	private static void loop1() {
		int length = array.length;
		for (int i = 0; i < length; i++)
			array[i] --;
	}

	private static void loop2() {
		int length = array.length;
		for (int i = 0; i < length; i += 2)
			array[i] --;
	}

	private static void loop4() {
		int length = array.length;
		for (int i = 0; i < length; i += 4)
			array[i] --;
	}

	private static void loop8() {
		int length = array.length;
		for (int i = 0; i < length; i += 8)
			array[i] --;
	}

	private static void loop12() {
		int length = array.length;
		for (int i = 0; i < length; i += 12)
			array[i] --;
	}


	private static void loop13() {
		int length = array.length;
		for (int i = 0; i < length; i += 13)
			array[i] --;
	}
	private static void loop14() {
		int length = array.length;
		for (int i = 0; i < length; i += 14)
			array[i] --;
	}


	private static void loop15() {
		int length = array.length;
		for (int i = 0; i < length; i += 15)
			array[i] --;
	}
	private static void loop16() {
		int length = array.length;
		for (int i = 0; i < length; i += 16)
			array[i] --;
	}
	private static void loop17() {
		int length = array.length;
		for (int i = 0; i < length; i += 17)
			array[i] --;
	}
	private static void loop18() {
		int length = array.length;
		for (int i = 0; i < length; i += 18)
			array[i] --;
	}

	private static void loop24() {
		int length = array.length;
		for (int i = 0; i < length; i += 24)
			array[i] --;
	}
	private static void loop32() {
		int length = array.length;
		for (int i = 0; i < length; i += 32)
			array[i] --;
	}

	private static void loop64() {
		int length = array.length;
		for (int i = 0; i < length; i += 64)
			array[i] --;
	}

	private static void loopVariableStep(final int step) {
		for (int i = 0; i < array.length; i += step)
			array[i] --;
	}

}

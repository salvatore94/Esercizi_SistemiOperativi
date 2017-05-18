public class ThreadTest {

	public static void main (String args[]) {
		Thread t1 = new Thread (Thread.currentThread(), "t1") () -> {
				@Override 
				public void run() {
					for (int i=0; i<10; i++) {
						System.out.println("Thread T1");
						try { 
							Thread.sleep(100);
						} catch (Exception e) {
						}
					}
				}
		};
		
		Thread t2 = new Thread (Thread.currentThread(), "t2") {
				@Override 
				public void run() {
					for (int i=0; i<10; i++) {
						System.out.println("Thread T2");
						try {
							Thread.sleep(100);
						} catch (Exception e) {
						}
					}
				}
		};

		t1.start();
		t2.start();
	}

}

public class ThreadCreator implements Runnable {
	public ThreadCreator() {
		Thread ct = Thread.currentThread();
		ct.setName("Padre");
		System.out.println("- " + ct);
		ThreadChreator t = new Thread(this, "Figlio");
		t.start();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		System.out.println("Padre morto");
	}

	@Override
	public void run() {
		System.out.println("Figlio");
		for (int i=0; i<5; i++) {
		System.out.println("- " + i);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		}
		System.out.println("Figlio morto");
	}

	public static void main(String args[]){
		new ThreadCreator();
	}
}

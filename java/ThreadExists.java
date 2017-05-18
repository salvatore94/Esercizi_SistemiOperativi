public class ThreadExists {

	public static void main (String args[]) {
	Thread ct = Thread.currentThread();
	ct.setName("Test");
	ct.setPriority(10);
	System.out.println(ct);
	}
}


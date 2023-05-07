package src;

// READ ONLY!
public abstract class Person13 {

	public void useToilet(Toilet13 t) throws InterruptedException {
        Thread.sleep(1000 * (long)(Math.random() * 5));
    }

	protected boolean isOccupied(Toilet13 t) {
		return false;
	}

    final protected void enterToilet(Toilet13 t) {
        synchronized (t) { t.dude++; }
    }

    final protected void exitToilet(Toilet13 t) {
        synchronized (t) { t.dude--; }
    }
}
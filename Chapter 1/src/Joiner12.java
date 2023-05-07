package src;

public class Joiner12 {

    Thread t;

    public Joiner12() { }
    public Joiner12(Thread target) {
        t = target;
    }

    public void start() {
        t.start();
    }

    // todo: Implement your own join
    public void join() {

    }

    // Test on your own
    public static void main(String[] args) {
        
    }

}

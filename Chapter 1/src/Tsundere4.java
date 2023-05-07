package src;

// This File is read-only, don't get dumb ideas >:(
public class Tsundere4 extends Thread{
    private boolean hasBeenHeadpat = false;

    public Tsundere4(){
        super("Taiga Aizawa");
    }
    public Tsundere4(Runnable t) {
        super(t,"Taiga Aizawa");
    }
    public void headpat() {
        hasBeenHeadpat = true;
    }
    @Override
    public synchronized void start(){
        if (!hasBeenHeadpat) {
            System.out.println(getName() + ": There is no way I'm gonna help someone like you out... idiot!");
        } else {
            System.out.println(getName() + ": Okay fine, but it's not like I help you out because I like you or anything...");
            super.start();
        }
    }
}

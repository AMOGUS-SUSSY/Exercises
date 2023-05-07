package test;

import org.junit.Assert;
import org.junit.Test;

import src.Joiner12;

public class Test12 {

    @Test
    public void single() {
        int[] res = {0};
        Thread dummy = new Thread(() -> {
            try { Thread.sleep(500); }
            catch (InterruptedException e) { }
            res[0] = 1;
        });
        Joiner12 test = new Joiner12(dummy);
        test.start();
        test.join();
        Assert.assertEquals(1, res[0]);
    }

    @Test
    public void multiple() {
        int[] res = {0};
        Thread dummy1 = new Thread(() -> {
            try { Thread.sleep(550); }
            catch (InterruptedException e) { }
            res[0] += 1;
        });
        Thread dummy2 = new Thread(() -> {
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { }
            res[0] += 1;
        });
        Thread dummy3 = new Thread(() -> {
            try { Thread.sleep(50); }
            catch (InterruptedException e) { }
            res[0] += 1;
        });
        Thread dummy4 = new Thread(() -> {
            try { Thread.sleep(2500); }
            catch (InterruptedException e) { }
            res[0] += 1;
        });
        Joiner12 test1 = new Joiner12(dummy1);
        Joiner12 test2 = new Joiner12(dummy2);
        Joiner12 test3 = new Joiner12(dummy3);
        Joiner12 test4 = new Joiner12(dummy4);
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        test1.join();
        test2.join();
        test3.join();
        test4.join();
        Assert.assertEquals(4, res[0]);
    }
    
}

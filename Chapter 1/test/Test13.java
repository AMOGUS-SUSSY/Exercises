package test;

import org.junit.Test;

import src.Peasant13;
import src.Toilet13;
import src.Premium13;

public class Test13 {

    @Test
    public void onlyPeasants() {
        Toilet13 toitoi = new Toilet13();
        Thread[] people = new Thread[8];
        for (int i = 0; i < 8; i++) {
            Peasant13 peasant1 = new Peasant13();
            people[i] = new Thread(() -> {
                try { 
                    peasant1.useToilet(toitoi);
                }
                catch (InterruptedException e) { }
            });
        }
        for (Thread t : people) {
            t.start();
        }
        loop: while (true) {
            if (toitoi.dude > 1) {
                throw new AssertionError("More than one peasant on the toilet!");
            }
            for (Thread t : people) {
                if (t.getState() != Thread.State.TERMINATED) {
                    continue loop;
                }
            }
            break;
        }
    }

    @Test
    public void onlyPremium() {
        Toilet13 toitoi = new Toilet13();
        Thread[] people = new Thread[8];
        for (int i = 0; i < 8; i++) {
            Premium13 premium = new Premium13();
            people[i] = new Thread(() -> {
                try { 
                    premium.useToilet(toitoi);
                }
                catch (InterruptedException e) { }
            });
        }
        for (Thread t : people) {
            t.start();
        }
        loop: while (true) {
            if (toitoi.dude > 1) {
                throw new AssertionError("More than one premium-member on the toilet!");
            }
            for (Thread t : people) {
                if (t.getState() != Thread.State.TERMINATED) {
                    continue loop;
                }
            }
            break;
        }
    }
    
    @Test
    public void both() {
        Toilet13 toitoi = new Toilet13();
        Thread[] people = new Thread[4];
        for (int i = 0; i < 2; i++) {
            Peasant13 premium = new Peasant13();
            people[i] = new Thread(() -> {
                try { 
                    premium.useToilet(toitoi);
                }
                catch (InterruptedException e) { }
            });
        }
        for (int i = 0; i < 2; i++) {
            Premium13 premium = new Premium13();
            people[i + 2] = new Thread(() -> {
                try { 
                    premium.useToilet(toitoi);
                }
                catch (InterruptedException e) { }
            });
        }
        for (Thread t : people) {
            t.start();
        }
        loop: while (true) {
            if (toitoi.dude > 1) {
                throw new AssertionError("More than one person on the toilet!");
            }
            if ((people[0].getState() == Thread.State.TIMED_WAITING || people[1].getState() == Thread.State.TIMED_WAITING) && 
                (people[2].getState() == Thread.State.WAITING || people[3].getState() == Thread.State.WAITING)) {
                    throw new AssertionError("Peasant has skipped the line!");
            }
            for (Thread t : people) {
                if (t.getState() != Thread.State.TERMINATED) {
                    continue loop;
                }
            }
            break;
        }
    }
    
}

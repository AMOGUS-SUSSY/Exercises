package test;

import org.junit.Assert;
import org.junit.Test;

import src.Ex9;

public class Test9 {

    @Test
    public void doesItCount(){
        int[] batch1 = {1,2,3,4,5,6,7,8};
        int[] batch2 = {5,5,5,5,1,2,3,4};
        int[] batch3 = {6,9,6,9,6,9,14,14};
        Assert.assertEquals(36,Ex9.countTheCoconuts(batch1));
        Assert.assertEquals(30,Ex9.countTheCoconuts(batch2));
        Assert.assertEquals(70,Ex9.countTheCoconuts(batch3));
    }

    @Test
    public void doesItCountPrimeBatches() {
        int[] batch1 = createBatch(2017);
        Assert.assertEquals(naive(batch1), Ex9.countTheCoconuts(batch1));
        int[] batch2 = createBatch(5003);
        Assert.assertEquals(naive(batch2), Ex9.countTheCoconuts(batch2));
    }

    @Test
    public void isParallel() {
        int[] batch = createBatch(500000000);
        long t1 = System.nanoTime();
        int res1 = naive(batch);
        long t2 = System.nanoTime();
        int res2 = Ex9.countTheCoconuts(batch);
        long t3 = System.nanoTime();
        Assert.assertTrue("You can't even count.",res1 == res2);
        Assert.assertTrue("Not parallel",(t2 - t1) > (t3 - t2));
    }

    @Test
    public void beatTheExpert() {
        int[] batch = createBatch(500000000);
        long t1 = System.nanoTime();
        int res1 = expert(batch);
        long t2 = System.nanoTime();
        int res2 = Ex9.countTheCoconuts(batch);
        long t3 = System.nanoTime();
        Assert.assertTrue("You can't even count",res1 == res2);
        Assert.assertTrue("You lost",(t2 - t1) > (t3 - t2));
    }



    private static int[] createBatch(int size) {
        int[] coco = new int[size];
        for (int i = 0; i < size; i++) {
            coco[i] = (int)(Math.random() * 1000);
        }
        return coco;
    }

    private static int naive(int[] coconuts) {
        int res = 0;
        for (int i : coconuts) {
            res += i;
        }
        return res;
    }

    private static int expert(int[] coconuts) {
        int result = 0;
        int proc = Runtime.getRuntime().availableProcessors();
        int[] intermediate = new int[proc];
        int partition = coconuts.length / proc;
        Thread[] p = new Thread[proc];
        for (int i = 0; i < proc; i++) {
            final int start = i * partition;
            p[i] = new Thread(() -> {
                int temp = 0;
                for (int j = start; j < start + partition; j++) {
                    temp += coconuts[j];
                }
                intermediate[start] = temp;
            });
        }
        for (Thread thread : p) {
            thread.start();
        }
        for (int i = proc * partition; i < coconuts.length; i++) {
            result += coconuts[i];
        }
        for (Thread thread : p) {
            try { thread.join(); }
            catch (InterruptedException e) { }
        }
        for (int res : intermediate) {
            result += res;
        }
        return result;
    }
}

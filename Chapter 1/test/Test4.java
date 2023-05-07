package test;
import org.junit.Assert;
import org.junit.Test;

import src.Ex4;
import src.Tsundere4;

public class Test4 {
    @Test
    public void taigaDoesStuff() throws InterruptedException {
        String[] test1 = new String[1];
        int[] test = new int[1];
        int val = (int)(Math.random() * 100);
        Tsundere4 taiga1 = new Tsundere4(() -> {
            test1[0] = "Baka";
        });
        Tsundere4 taiga2 = new Tsundere4(() -> {
            test[0] = val;
        });
        Ex4.tsundere(taiga1);
        Ex4.tsundere(taiga2);
        taiga1.join();
        taiga2.join();
        Assert.assertEquals("Baka",test1[0]);
        Assert.assertEquals(val,test[0]);
    }
}

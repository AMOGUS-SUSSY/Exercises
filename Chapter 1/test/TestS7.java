package test;

import org.junit.Assert;
import org.junit.Test;

import src.CrewmateS7;
import src.ExS7;

public class TestS7 {
    @Test
    public void simple() {
        CrewmateS7[] crew1 = new CrewmateS7[2];
        crew1[0] = new CrewmateS7(new long[] {4,6,8,10});
        crew1[1] = new CrewmateS7(new long[] {3,9,2,4,6});

        Assert.assertArrayEquals(new CrewmateS7[] {crew1[0]},ExS7.findImposter(crew1));

        crew1[0] = new CrewmateS7(new long[] {3,9,5,6,7});
        crew1[1] = new CrewmateS7(new long[] {3,9,5,25});

        Assert.assertArrayEquals(new CrewmateS7[] {crew1[0]},ExS7.findImposter(crew1));

        crew1[0] = new CrewmateS7(new long[] {3,9,10,10,10,7,49});
        crew1[1] = new CrewmateS7(new long[] {3,9,5,25,25});

        Assert.assertArrayEquals(new CrewmateS7[] {crew1[1]},ExS7.findImposter(crew1));
    }

    @Test
    public void edgeCases() {
        CrewmateS7[] crew1 = new CrewmateS7[2];
        crew1[0] = new CrewmateS7(new long[] {0,1,0,1});
        crew1[1] = new CrewmateS7(new long[] {0,1,0,1,2,4});

        Assert.assertArrayEquals(new CrewmateS7[] {crew1[0]},ExS7.findImposter(crew1));
    }

    @Test
    public void oneImposterBig() {
        CrewmateS7[] crew1 = new CrewmateS7[8];
        crew1[0] = new CrewmateS7(createID(1000, 2017));
        crew1[7] = new CrewmateS7(createID(1000, 6));
        crew1[2] = new CrewmateS7(createID(1000, 2017));
        crew1[3] = new CrewmateS7(createID(1000, 2017));
        crew1[4] = new CrewmateS7(createID(1000, 2017));
        crew1[5] = new CrewmateS7(createID(1000, 2017));
        crew1[6] = new CrewmateS7(createID(1000, 2017));
        crew1[1] = new CrewmateS7(createID(1000, 2017));

        Assert.assertArrayEquals(new CrewmateS7[] {crew1[7]},ExS7.findImposter(crew1));
    }

    private long[] createID(int size, long prime) {
        int index1 = (int)(Math.random() * size);
        int index2 = (int)(Math.random() * size);
        long[] ret = new long[size];
        for (int i = 0; i < size; i++) {
            ret[i] = 10 * (i+1);
        }
        if (index1 == index2) {
            index2++;
        }
        ret[index1] = prime;
        ret[index2] = prime * prime;
        return ret;
    }
}

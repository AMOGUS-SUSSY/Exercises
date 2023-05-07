package src;

public class Premium13 extends Person13 {

    // Add your own fields if necessary

    /* todo: Modify useToilet() and if necessary isOccupied()
    *  Add your own methods when necessary.
    *  Do not delete enter/exit-Toilet but you are allowed to enclose it.
    */
    @Override
    public void useToilet(Toilet13 t) throws InterruptedException {
        enterToilet(t);     // DO NOT DELETE!
        super.useToilet(t);
        exitToilet(t);      // DO NOT DELETE!
    }

    @Override
    protected boolean isOccupied(Toilet13 t) {
        return false;
    }

}

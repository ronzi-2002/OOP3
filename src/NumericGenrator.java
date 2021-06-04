public class  NumericGenrator implements Ganrator {
    protected static NumericGenrator numericGenrator;

    protected NumericGenrator(){

    }
    public static Ganrator getInstance() {
        if(numericGenrator==null)
            numericGenrator =  new NumericGenrator();
        return numericGenrator;
    }
    public int NextInt(int low, int high){
        return -1;
    }
}

package Backend;

import java.util.Random;

public class RandomNumericGenerator extends NumericGenrator {
    private Random r= new Random();
    public int NextInt(int low, int high){
        return (r.nextInt(high+1-low)+low);
    }
    private RandomNumericGenerator(){

    }
    public static Ganrator getInstance() {
        if(numericGenrator==null)
            numericGenrator =  new RandomNumericGenerator();
        return numericGenrator;
    }

}

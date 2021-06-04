public class DetriminstGenrator extends NumericGenrator{

        private DetriminstGenrator(){

        }
        public static Ganrator getInstance() {

            if(numericGenrator==null)
                numericGenrator =  new DetriminstGenrator();
            return numericGenrator;
        }

        public int NextInt(int low, int high){return (low+high)/2;}
    }


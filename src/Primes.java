public class Primes {
    public static boolean checkPrime(int num){
        if(num%2==0) return false;

        for(int i=3; i<Math.sqrt(num); i+=2){
            if(num%i==0) return  false;
        }
        return true;
    }

    public static  void rangePrime(int low, int high){
        for(int i=low; i<=high;i++){
            if(checkPrime(i)) System.out.println(""+i+" is prime");
        }
    }

    public static void main(String[] args) {
        System.out.println(checkPrime(6));
        System.out.println(checkPrime(7));

        rangePrime(1,100);
    }

}

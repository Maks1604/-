package Lesson5;

public class Main {

    public static void main(String[] args) {
        System.out.println(powerRec(3,5));
    }

    public static long powerRec(int num, int pow) {

        if (pow == 0) {
            return  1;
        } else if (pow == 1) {
            return num;
        } else if (pow % 2 == 0){
           long a = powerRec(num,pow/2);
           return a*a;
        } else {
            long a = powerRec(num,pow/2);
            return a*a*num;
        }


    }
}

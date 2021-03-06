package Lesson5;

public class Main {

    public static void main(String[] args) {
        System.out.println(powerRec(5, 15));
        System.out.println(power(3, 16));
        Towers(3, 1, 2, 3);
    }
    public static void Towers(int count, int from, int to, int buf)	{
        if (count != 0)
        {
            Towers(count-1, from, buf, to);

            System.out.println("" + from + " -> " + to );

            Towers(count-1, buf, to, from);
        }
    }

    public static long powerRec(int num, int pow) {

        if (pow == 0) {
            return 1;
        } else if (pow == 1) {
            return num;
        } else if (pow % 2 == 0) {
            long a = powerRec(num, pow / 2);
            return a * a;
        } else {
            long a = powerRec(num, pow / 2);
            return a * a * num;
        }
    }

    public static long power(int num, int pow) {
        long sum = num;
        if (pow == 0) {
            return 1;
        } else if (pow == 1) {
            return num;
        } else {
            while (pow > 1) {
                sum *= num;
                pow--;
            }
        }

        return sum;
    }
}

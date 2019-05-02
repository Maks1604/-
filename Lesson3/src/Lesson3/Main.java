package Lesson3;

public class Main {

    public static void main(String[] args) {
        System.out.println(revers("Улыбок тебе дед макар"));
    }
    static String revers (String str){
       StringBuilder stringBuilder = new StringBuilder(str);
        int last = stringBuilder.length()-1;
       char temp;
        for (int i = 0; i < stringBuilder.length()/2 ; i++ ,last--) {
            temp = stringBuilder.charAt(i);
            stringBuilder.setCharAt(i,stringBuilder.charAt(last));
            stringBuilder.setCharAt(last,temp);
        }
        return stringBuilder.toString();
    }
//    static String revers (String str){
//        String rev="";
//        for (int i = str.length()-1; i >=0 ; i--) {
//            rev+=str.charAt(i);
//        }
//        return rev;
//    }
}

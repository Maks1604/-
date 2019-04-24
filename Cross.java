package Lesson_4.DZ;

import java.util.Random;
import java.util.Scanner;

public class Cross {

    //1. параметры игрового поля
    static final int SIZE_Y = 5; //размер поля по вертикале
    static final int SIZE_X = 5; //расчет поля по горизонтале
    static final int SIZE_WIN =4; //кол-во заполненных подряж полей для победы
    static final char[][] fieldg =  new char [SIZE_Y][SIZE_X];
    // игровые элемент
    static final char player_DOT= 'X';
    static final char Ai_DOT= 'O';
    static final char EMPTY_DOT= '.';

    // обявляется классов ввода и случайного числа для игры
    static Scanner scr = new Scanner(System.in);
    static Random rnd = new Random();

    //поле в начале игры
    private static void emtpyField () {

        for (int i=0; i<SIZE_Y; i++) {
            for (int j=0; j<SIZE_X; j++) {
                fieldg [i][j] = EMPTY_DOT;
            }
        }
    }

    // печать верхней и нижней границ
    private static void printBorder(int count){
        System.out.print("  -");
        for (int i = 0; i < count; i++) System.out.print("-");
        System.out.println();
    }
    // метод для вывода на консоль поля
    private static void printField() {
        System.out.print("   ");
        for (int i = 0; i < SIZE_X; i++) System.out.print(""+(i+1)+" ");
        System.out.println();
        printBorder(SIZE_X*2);
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print(""+(i+1)+" |");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(fieldg[i][j] + "|");
            }
            System.out.println();
        }
        printBorder(SIZE_X*2);
    }

    //чертим линию для поля
    private static void printFieldLine () {
        for (int i =0; i<fieldg.length*2+1; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
    //запись хода игрока на поле
    private static void dotField (int y, int x, char dot) {
        fieldg [y][x] = dot;
    }
    //Ход человева
    private static void playerMove () {
        int x, y;
        do {
            System.out.println("Введите координаты вашего хода в диапозоне от 1 до " + SIZE_Y);
            System.out.print ("Координат по строке ");
            y = scr.nextInt()-1;
            System.out.print ("Координат по столбцу ");
            x = scr.nextInt()-1;

        } while (!checkMove(y,x));
        dotField(y, x, player_DOT);
    }
    //Ход компьютера
    private static void AiMove () {
        int x, y;
        //блокировка ходов человека
        for (int v = 0; v<SIZE_Y; v++) {
            for (int h = 0; h < SIZE_X; h++) {
                //анализ наличие поля для проверки
                if (h+SIZE_WIN<=SIZE_X) {                           //по горизонтале
                    if (checkLineHorisont(v, h, player_DOT) == SIZE_WIN - 1) {
                        if (MoveAiLineHorisont(v, h, Ai_DOT)) return;
                    }

                    if (v - SIZE_WIN > -2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, player_DOT) == SIZE_WIN - 1) {
                            if (MoveAiDiaUp(v, h, Ai_DOT)) return;
                        }
                    }
                    if (v + SIZE_WIN <= SIZE_Y) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, player_DOT) == SIZE_WIN - 1) {
                            if (MoveAiDiaDown(v, h, Ai_DOT)) return;
                        }
                    }
                }
                if (v+SIZE_WIN<=SIZE_Y) {                       //по вертикале
                    if (checkLineVertical(v,h,player_DOT) ==SIZE_WIN-1) {
                        if(MoveAiLineVertical(v,h,Ai_DOT)) return;
                    }
                }
            }
        }
        //игра на победу
        for (int v = 0; v<SIZE_Y; v++) {
            for (int h = 0; h < SIZE_X; h++) {
                //анализ наличие поля для проверки
                if (h+SIZE_WIN<=SIZE_X) {                           //по горизонтале
                    if (checkLineHorisont(v,h,Ai_DOT) == SIZE_WIN-1) {
                        if (MoveAiLineHorisont(v,h,Ai_DOT)) return;
                    }

                    if (v-SIZE_WIN>-2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, Ai_DOT) == SIZE_WIN-1) {
                            if (MoveAiDiaUp(v,h,Ai_DOT)) return;
                        }
                    }
                    if (v+SIZE_WIN<=SIZE_Y) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, Ai_DOT) == SIZE_WIN-1) {
                            if (MoveAiDiaDown(v,h,Ai_DOT)) return;
                        }
                    }

                }
                if (v+SIZE_WIN<=SIZE_Y) {                       //по вертикале
                    if (checkLineVertical(v,h,Ai_DOT) ==SIZE_WIN-1) {
                        if(MoveAiLineVertical(v,h,Ai_DOT)) return;
                    }
                }
            }
        }

        //случайный ход
        do {
            y = rnd.nextInt(SIZE_Y);
            x = rnd.nextInt(SIZE_X);
        } while (!checkMove(y,x));
        dotField(y, x, Ai_DOT);
    }

    //ход компьютера по горизонтале
    private static boolean MoveAiLineHorisont(int v, int h, char dot) {
        for (int j = h; j < SIZE_WIN; j++) {
            if ((fieldg[v][j] == EMPTY_DOT)) {
                fieldg[v][j] = dot;
                return true;
            }
        }
        return false;
    }
    //ход компьютера по вертикале
    private static boolean MoveAiLineVertical(int v, int h, char dot) {
        for (int i = v; i<SIZE_WIN; i++) {
            if ((fieldg[i][h] == EMPTY_DOT)) {
                fieldg[i][h] = dot;
                return true;
            }
        }
        return false;
    }
    //проверка заполнения всей линии по диагонале вверх

    private static boolean MoveAiDiaUp(int v, int h, char dot) {
        for (int i = 0, j = 0; j < SIZE_WIN; i--, j++) {
            if ((fieldg[v+i][h+j] == EMPTY_DOT)) {
                fieldg[v+i][h+j] = dot;
                return true;
            }
        }
        return false;
    }
    //проверка заполнения всей линии по диагонале вниз

    private static boolean MoveAiDiaDown(int v, int h, char dot) {

        for (int i = 0; i < SIZE_WIN; i++) {
            if ((fieldg[i+v][i+h] == EMPTY_DOT)) {
                fieldg[i+v][i+h] = dot;
                return true;
            }
        }
        return false;
    }
    //проверка заполнения выбранного для хода игроком
    private static boolean checkMove(int y, int x) {
        if (x<0 || x >=SIZE_X || y<0 || y>=SIZE_Y) return false;
        else if (!(fieldg[y][x]==EMPTY_DOT)) return false;

        return true;
    }
    //проверка на ничью (все  ячейки поля заполнены ходами)
    private  static boolean fullField() {
        for (int i=0; i<SIZE_Y; i++) {
            for (int j=0; j<SIZE_X; j++) {
                if (fieldg[i][j] == EMPTY_DOT) return false;
            }
        }
        System.out.println("Игра закончилась в ничью");
        return true;
    }

    //проверка победы
    private static boolean checkWin(char dot) {
        for (int v = 0; v<SIZE_Y; v++){
            for (int h = 0; h<SIZE_X; h++) {
                //анализ наличие поля для проверки
                if (h + SIZE_WIN <= SIZE_X) {                           //по горизонтале
                    if (checkLineHorisont(v, h, dot) >= SIZE_WIN) return true;

                    if (v - SIZE_WIN > -2) {                            //вверх по диагонале
                        if (checkDiaUp(v, h, dot) >= SIZE_WIN) return true;
                    }
                    if (v + SIZE_WIN <= SIZE_Y) {                       //вниз по диагонале
                        if (checkDiaDown(v, h, dot) >= SIZE_WIN) return true;
                    }
                }
                if (v + SIZE_WIN <= SIZE_Y) {                       //по вертикале
                    if (checkLineVertical(v, h, dot) >= SIZE_WIN) return true;
                }
            }
        }
        return false;
    }

    //проверка заполнения всей линии по диагонале вверх
    private static int checkDiaUp(int v, int h, char dot) {
        int count=0;
        for (int i = 0, j = 0; j < SIZE_WIN; i--, j++) {
            if ((fieldg[v+i][h+j] == dot)) count++;
        }
        return count;
    }
    //проверка заполнения всей линии по диагонале вниз

    private static int checkDiaDown(int v, int h, char dot) {
        int count=0;
        for (int i = 0; i < SIZE_WIN; i++) {
            if ((fieldg[i+v][i+h] == dot)) count++;
        }
        return count;
    }

    private static int checkLineHorisont(int v, int h, char dot) {
        int count=0;
        for (int j = h; j < SIZE_WIN + h; j++) {
            if ((fieldg[v][j] == dot)) count++;
        }
        return count;
    }
    //проверка заполнения всей линии по вертикале
    private static int checkLineVertical(int v, int h, char dot) {
        int count=0;
        for (int i = v; i< SIZE_WIN + v; i++) {
            if ((fieldg[i][h] == dot)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        emtpyField ();
        printField();
        do {
            playerMove();
            System.out.println("Ваш ход на поле");
            printField();
            if (checkWin(player_DOT)) {
                System.out.println("Вы выиграли");
                break;
            } else if (fullField()) break;
            AiMove();
            System.out.println("Ход Компа на поле");
            printField();
            if (checkWin(Ai_DOT)) {
                System.out.println("Выиграли Комп");
                break;
            } else if (fullField()) break;
        } while (true);
        System.out.println("!Конец игры!");
    }

}





















































//////////////
//
//
//
//
//
//
//
//    boolean checkWin(char c) {
//        int countV;
//        int countH;
//        int countDiagonalA = 0;
//        int countDiagonalB = 0;
//        for (int i = 0; i <= size - 1; i++) {
//            countH = 0;
//            countV = 0;
//            for (int j = 0; j <= size - 1; j++) {
//                if (map[i][j] == c) {
//                    countH++;
//                    if (countH == size) return true;
//                }
//
//                if (map[j][i] == c) {
//                    countV++;
//                    if (countV == size) return true;
//                }
//            }
//
//            if (map[i][i] == c) {
//                countDiagonalA++;
//                if (countDiagonalA == size) return true;
//            } else countDiagonalA = 0;
//
//            if (map[i][size - 1 - i] == c) {
//                countDiagonalB++;
//                if (countDiagonalB == size) return true;
//            } else countDiagonalB = 0;
//        }
//        return false;
//    }
//
//    boolean isMapFull() {
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (map[i][j] == cNull) return false;
//            }
//        }
//        return true;
//    }
//
//    void aiTurn(char c) {
//        int x = 0, y = 0, countH = 0, countHNull = 0, countV = 0, countVNull = 0,
//                countDiagonalA = 0, countDiagonalB = 0, countDANull = 0, countDBNull = 0;
//
//        System.out.println("’од бота [" + c + "]:");
//
//        for (int i = 0; i < size; i++) {
//            countH = 0;
//            countHNull = 0;
//            countV = 0;
//            countVNull = 0;
//            for (int j = 0; j < size; j++) {
//                if (map[j][i] == c) countV++;
//                else if (map[j][i] == cNull) countVNull++;
//                if ((countV == size - 1) && (countVNull == 1)) {
//                    for (int k = 0; k < size; k++) {
//                        if (map[k][i] == cNull) {
//                            map[k][i] = c;
//                            return;
//                        }
//                    }
//                }
//                if (map[i][j] == c) countH++;
//                else if (map[i][j] == cNull) countHNull++;
//                if ((countH == size - 1) && (countHNull == 1)) {
//                    for (int k = 0; k < size; k++) {
//                        if (map[i][k] == cNull) {
//                            map[i][k] = c;
//                            return;
//                        }
//                    }
//                }
//
//            }
//
//            if (map[i][i] == c) countDiagonalA++;
//            else if (map[i][i] == cNull) countDANull++;
//            if ((countDiagonalA == size - 1) && (countDANull == 1)) {
//                for (int j = 0; j < size; j++) {
//                    if (map[j][j] == cNull) {
//                        map[j][j] = c;
//                        return;
//                    }
//                }
//            }
//
//            if (map[i][size - 1 - i] == c) countDiagonalB++;
//            else if (map[i][size - 1 - i] == cNull) countDBNull++;
//            if ((countDiagonalB == size - 1) && (countDBNull == 1)) {
//                for (int j = 0; j < size; j++) {
//                    if (map[j][size - 1 - j] == cNull) {
//                        map[j][size - 1 - j] = c;
//                        return;
//                    }
//                }
//            }
//        }
//
//        countH = 0;
//        countHNull = 0;
//        countV = 0;
//        countVNull = 0;
//        countDiagonalA = 0;
//        countDiagonalB = 0;
//        countDANull = 0;
//        countDBNull = 0;
//
//        for (int i = 0; i < size; i++) {
//            countH = 0;
//            countHNull = 0;
//            countV = 0;
//            countVNull = 0;
//            for (int j = 0; j < size; j++) {
//                if (map[j][i] == player) countV++;
//                else if (map[j][i] == cNull) countVNull++;
//                if ((countV == size - 1) && (countVNull == 1)) {
//                    for (int k = 0; k < size; k++) {
//                        if (map[k][i] == cNull) {
//                            map[k][i] = c;
//                            return;
//                        }
//                    }
//                }
//                if (map[i][j] == player) countH++;
//                else if (map[i][j] == cNull) countHNull++;
//                if ((countH == size - 1) && (countHNull == 1)) {
//                    for (int k = 0; k < size; k++) {
//                        if (map[i][k] == cNull) {
//                            map[i][k] = c;
//                            return;
//                        }
//                    }
//                }
//
//            }
//
//            if (map[i][i] == player) countDiagonalA++;
//            else if (map[i][i] == cNull) countDANull++;
//            if ((countDiagonalA == size - 1) && (countDANull == 1)) {
//                for (int j = 0; j < size; j++) {
//                    if (map[j][j] == cNull) {
//                        map[j][j] = c;
//                        return;
//                    }
//                }
//            }
//
//            if (map[i][size - 1 - i] == player) countDiagonalB++;
//            else if (map[i][size - 1 - i] == cNull) countDBNull++;
//            if ((countDiagonalB == size - 1) && (countDBNull == 1)) {
//                for (int j = 0; j < size; j++) {
//                    if (map[j][size - 1 - j] == cNull) {
//                        map[j][size - 1 - j] = c;
//                        return;
//                    }
//                }
//            }
//        }
//
//        if (!(size % 2 == 0)) {
//            int center = (((size + 1) / 2) - 1);
//            if (map[center][center] == cNull) {
//                map[center][center] = c;
//                return;
//            }
//        }
//
//        if (map[0][0] == cNull) {
//            map[0][0] = c;
//            return;
//        }
//        if (map[0][map.length - 1] == cNull) {
//            map[0][map.length - 1] = c;
//            return;
//        }
//        if (map[map.length - 1][0] == cNull) {
//            map[map.length - 1][0] = c;
//            return;
//        }
//        if (map[map.length - 1][map.length - 1] == cNull) {
//            map[map.length - 1][map.length - 1] = c;
//            return;
//        }
//
//        do {
//            x = r.nextInt(size);
//            y = r.nextInt(size);
//        } while (!isCellValid(x, y));
//        map[y][x] = c;
//        System.out.println("AI X: " + (x + 1) + " Y: " + (y + 1));
//
//
//
//        ///////////
//
//
//
//
//
//
//
//
//
//
//
//
//
//        public static boolean checkWin(char[][] arr) {
//            int sumGor;
//            int sumVer;
//            int sumD1 = 0;
//            int sumD2 = 0;
//
//            for (int i = 0; i < arr.length; i++) {  //проверяю горизонтали
//                sumGor = 0;
//                for (int j = 0; j < arr[i].length; j++) {
//                    switch (arr[i][j]) {
//                        case 'X':
//                            sumGor += 1;
//                            break;
//                        case 'O':
//                            sumGor += -1;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//
//                if (abs(sumGor) == arr.length) {
//                    return true;
//                }
//            }
//
//            for (int i = 0; i < arr.length; i++) {  //проверяю вертикали
//                sumVer = 0;
//                for (int j = 0; j < arr[i].length; j++) {
//                    switch (arr[j][i]) {
//                        case 'X':
//                            sumVer += 1;
//                            break;
//                        case 'O':
//                            sumVer += -1;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//
//                if (abs(sumVer) == arr.length) {
//                    return true;
//                }
//            }
//
//            for (int i = 0; i < arr.length; i++) {  //проверяю диагонали
//                for (int j = 0; j < arr[i].length; j++) {
//                    switch (arr[j][i]) {
//                        case 'X':
//                            if (i == j) {
//                                sumD1 += 1;
//                            }
//                            if (i + j == arr.length - 1) {
//                                sumD2 += 1;
//                            }
//                            break;
//                        case 'O':
//                            if (i == j) {
//                                sumD1 += -1;
//                            }
//
//                            if (i + j == arr.length - 1) {
//                                sumD2 += -1;
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//
//                    if ((abs(sumD1) == arr.length) || (abs(sumD2) == arr.length)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//
//
//
//
//            ///////////
//
//
//
//
//
//
//            boolean checkWin(char dot) {
//                for (int y = 0; y < SIZE; y++)
//                    for (int x = 0; x < SIZE; x++)
//                        for (int dy = -1; dy < 2; dy++)
//                            for (int dx = -1; dx < 2; dx++)
//                                if (checkLine(x, y, dx, dy, dot) == WIN_SIZE)
//                                    return true;
//                return false;
//            }
//
//            ////////////
//
//
//
//
//
//
//
//            if (j+i < SIZE) {
//                if (map[j][j+i] == dt)                   { checkMainDiagonalCounter++;   }
//                else if (checkMainDiagonalCounter > 0)   { checkMainDiagonalCounter = 0; }
//
//                if (map[SIZE-j-1][j+i] == dt)            { checkSideDiagonalCounter++;   }
//                else if (checkSideDiagonalCounter > 0)   { checkSideDiagonalCounter = 0; }
//
//                if (checkMainDiagonalCounter == SIZE_LINE_WIN || checkSideDiagonalCounter == SIZE_LINE_WIN)
//                    return true;
//            }
//            else {
//                checkMainDiagonalCounter = 0;
//                checkSideDiagonalCounter = 0;
//            }
//
//            // Check of diagonals that are lower than the main and secondary diagonals
//            if (j-i >=0) {
//                if (map[j][j-i] == dt)                       { checkBelowMainDiagonalCounter++;   }
//                else if (checkBelowMainDiagonalCounter > 0)  { checkBelowMainDiagonalCounter = 0; }
//
//                if (map[SIZE-j-1][j-i] == dt)                { checkBelowSideDiagonalCounter++;   }
//                else if (checkBelowSideDiagonalCounter > 0)  { checkBelowSideDiagonalCounter = 0; }
//
//
//
//                //////////
//
//
//
//
//
//                //    2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
//                //    3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
//                //     Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
//                // проверка на победу
//
//                // название методов одинаковое
//                private static boolean checkWin(char sym) {
//                    for (int i = 0; i < SIZE_Y; i++) {
//                        for (int j = 0; j < SIZE_X; j++) {
//                            if (checkLine(i, j, 0, 1,  sym)) return true;   // проверим линию по х
//                            if (checkLine(i, j, 1, 1,  sym)) return true;   // проверим по диагонали х у
//                            if (checkLine(i, j, 1, 0,  sym)) return true;   // проверим линию по у
//                            if (checkLine(i, j, -1, 1, sym)) return true;  // проверим по диагонали х -у
//                        }
//                    }
//                    return false;
//                }
//
//                // проверка линии
//                private static boolean checkLine(int y, int x, int vy, int vx, char sym) {
//                    int wayX = x + (TO_WIN - 1) * vx;
//                    int wayY = y + (TO_WIN - 1) * vy;
//                    if (wayX < 0 || wayY < 0 || wayX > SIZE_X - 1 || wayY > SIZE_Y - 1) return false;
//                    for (int i = 0; i < TO_WIN; i++) {
//                        int itemY = y + i * vy;
//                        int itemX = x + i * vx;
//                        if (field[itemY][itemX] != sym) return false;
//                    }
//                    return true;
//                }
//
//
//
//
//////////////
//
//
//
//                public static boolean CheckWin(char symb) {
//                    int diag1, diag2, hor, ver;
//                    for (int i = 0; i < fieldSIZE; i++) {
//                        hor = 0; ver = 0;
//                        for (int j = 0; j < fieldSIZE; j++) {
//                            if (map[i][j] == symb) {
//                                hor++;
//                            }
//                            if (map[j][i] == symb) {
//                                ver++;
//                            }
//                        }
//                        if (hor == fieldSIZE|| ver == fieldSIZE) {
//                            return true; //проверка по горизонтали и вертикали
//                        }
//                    }
//                    diag1 = 0; diag2 = 0;
//                    for (int i = 0; i < fieldSIZE; i++) {
//                        if (map[i][i] == symb) {
//                            diag1++;
//                        }
//                        if (map[i][fieldSIZE - i - 1] == symb) {
//                            diag2++;
//                        }
//                    }
//                    if (diag1 == fieldSIZE || diag2 == fieldSIZE) {
//                        return true; //проверка по диагоналям
//                    }
//                    return false;
//                }
//
//
//
//
//
//
/////////////
//
//// изменени победной серии
//
//
//                static private boolean checkWinner(char[][] arr, char sym){
//                    for (int i = 0; i < arr.length; i++) {
//                        int sum = 0;
//                        for (int j = 0; j < arr[i].length; j++) {
//                            if(arr[i][j] == sym)
//                                sum++;
//                        }
//                        if(sum == 3) {
//                            return true;
//                        }
//                    }
//                    for (int i = 0; i < arr[0].length; i++) {
//                        int sum = 0;
//                        for (int j = 0; j < arr.length; j++) {
//                            if(arr[j][i] == sym)
//                                sum++;
//                        }
//                        if(sum == 3) {
//                            return true;
//                        }
//                    }
//                    int sum = 0;
//                    for (int i = 0; i < arr.length; i++) {
//                        if(arr[i][i] == sym){
//                            sum++;
//                        }
//                        if(sum == 3) {
//                            return true;
//                        }
//                    }
//                    sum = 0;
//                    for (int i = 0; i < arr.length; i++) {
//                        if(arr[i][arr.length - i - 1] == sym){
//                            sum++;
//                        }
//                        if(sum == 3) {
//                            return true;
//                        }
//                    }
//                    return false;
//                }
//
//
//
//
////////////////
//
//
//
//
//
//
//
//// объединить
//                while (true) {
//                    playerStep();
//                    printField();
//                    if (checkWinColumnsRowsRight(PLAYER_DOT)) {
//                        System.out.println("Player win!");
//                        break;
//                    }
//                    if (checkWinColumnsLeft(PLAYER_DOT)) {
//                        System.out.println("Player win!");
//                        break;
//                    }
//                    if (checkDiagonalsRight(PLAYER_DOT)) {
//                        System.out.println("Player win!");
//                        break;
//                    }
//                    if (checkDiagonalsLeft(PLAYER_DOT)) {
//                        System.out.println("Player win!");
//                        break;
//                    }
//                    if (isFieldFull()) {
//                        System.out.println("Draw!");
//                        break;
//                    }
//
//                    aiStep();
//                    printField();
//                    if (checkWinColumnsRowsRight(AI_DOT)) {
//                        System.out.println("System win!");
//                        break;
//                    }
//                    if (checkWinColumnsLeft(AI_DOT)) {
//                        System.out.println("System win!");
//                        break;
//                    }
//                    if (checkDiagonalsRight(AI_DOT)) {
//                        System.out.println("System win!");
//                        break;
//                    }
//                    if (checkDiagonalsLeft(AI_DOT)) {
//                        System.out.println("System win!");
//                        break;
//                    }
//                    if (isFieldFull()) {
//                        System.out.println("Draw!");
//                        break;
//                    }
//                }
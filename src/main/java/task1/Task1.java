package task1;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int n = sc.nextInt();
        System.out.println("Введите интервал длины");
        int m = sc.nextInt();
        int[] circle = new int[n];
        Arrays.setAll(circle, i -> ++i);
        int current = 0;
        System.out.println("path:");
        do{
            System.out.println(circle[current]);
            current=(current+m-1)%n;
        }while (current !=0);
    }
}

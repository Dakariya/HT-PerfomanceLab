package task4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу с  массивом целых чисел nums: ");
        String filePath = scanner.nextLine();
        try {
            scanner = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (!scanner.hasNextInt()) {
            System.out.println("Файл пустой!");
            return;
        }
        ArrayList<Integer> nums = new ArrayList<>();
        while (scanner.hasNextInt()) {
            nums.add(scanner.nextInt());
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int meanNumber = (int) Math.round((sum * 1.0) / nums.size());

        int stepsCount = 0;
        for (int num : nums) {
            stepsCount += Math.abs(meanNumber - num);
        }
        System.out.println(stepsCount);
    }
    }

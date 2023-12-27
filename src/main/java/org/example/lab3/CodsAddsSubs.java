package org.example.lab3;
public class CodsAddsSubs {
    public static void main(String[] args) {
        int num1 = -13;
        int num2 = 9;

        System.out.printf("Двоичное представление для числа %s: %s", num1, convertToBinary(num1) + "\n");
        System.out.printf("Двоичное представление для числа   %s: %s", num2, convertToBinary(num2) + "\n");


        int addResult = addIntegers(num1, num2);
        System.out.println("Результат сложения:  " + convertToBinary(addResult));

        int subResult = subIntegers(num1, num2);
        System.out.println("Результат вычитания: " + convertToBinary(subResult));
    }

    public static int addIntegers(int num1, int num2) {
        return num1 + num2;
    }

    public static int subIntegers(int num1, int num2) {
        return num1 - num2;
    }
    public static String convertToBinary(int num) {
        String binaryStr = Integer.toBinaryString(num);
        if (num >= 0 && binaryStr.length() < 32) { // если не хватает нулей добавляем
            int missingZeros = 32 - binaryStr.length();
            binaryStr = "0".repeat(missingZeros) + binaryStr;
        }
        return binaryStr;
    }
}

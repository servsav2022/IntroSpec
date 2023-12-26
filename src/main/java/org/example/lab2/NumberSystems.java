package org.example.lab2;

public class NumberSystems {
    public static String convertDecimalToBinary(double decimalNumber, int precision) {
        long intPart = (long) decimalNumber;
        double fractionalPart = decimalNumber - intPart;

        StringBuilder binaryIntPart = new StringBuilder(Long.toBinaryString(intPart));
        StringBuilder binaryFractionalPart = new StringBuilder(".");

        for (int i = 0; i < precision; i++) {
            fractionalPart *= 2;
            int bit = (int) fractionalPart;
            binaryFractionalPart.append(bit);
            fractionalPart -= bit;
        }

        return binaryIntPart.toString() + binaryFractionalPart.toString();
    }
    public static double convertBinaryToDecimal(String binaryNumber, int precision) {
       String[] parts = binaryNumber.split("\\.");

        // Целая часть
        int intPart = Integer.parseInt(parts[0], 2);

        // Дробная часть (если есть)
        double fractionalPart = 0;
        if (parts.length > 1) {
            String fractionString = parts[1];
            for (int i = 0; i < fractionString.length() && i < precision; i++) {
                if (fractionString.charAt(i) == '1') {
                    fractionalPart += 1.0 / Math.pow(2, i + 1);
                }
            }
        }

        return intPart + fractionalPart;
    }
    public static void main(String[] args) {

        double decimalNumber1 = 987.116;
        String binaryNumber1 = convertDecimalToBinary(decimalNumber1,5);
        String binaryNumber2 = "11011011";
        double decimalNumber2 = convertBinaryToDecimal(binaryNumber2, 0);

        System.out.println("Десятичное число: " + decimalNumber1);
        System.out.println("Число после перевода в двоичное: " + binaryNumber1+"\n");

        System.out.println("Двоичное число: " + binaryNumber2);
        System.out.println("Число после перевода в десятичное: " + (int)decimalNumber2+"\n");
    }
}

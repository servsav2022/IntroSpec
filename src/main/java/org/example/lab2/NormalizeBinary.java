package org.example.lab2;
import static org.example.lab2.NumberSystems.convertDecimalToBinary;

public class NormalizeBinary {
    public static void main(String[] args) {
        double decimalNumber = 98.71;
        int precision = 5;

        String binaryNumber = convertDecimalToBinary(decimalNumber, precision);
        String normalizedBinaryNumber = normalizeBinary(binaryNumber);

        System.out.println("Original binary: " + binaryNumber);
        System.out.println("Normalized binary: " + normalizedBinaryNumber);
    }

    public static String normalizeBinary(String binaryNumber) {
        int dotIndex = binaryNumber.indexOf('.');
        if (dotIndex == -1) {
            // Если нет дробной части, то число уже нормализовано
            return binaryNumber;
        }

        // Выделение мантиссы и экспоненты
        String mantissa = binaryNumber.replace(".", "");
        int exponent = dotIndex - binaryNumber.indexOf('1') - 1;

        // Приведение экспоненты к biased форме (добавление bias)
        int biasedExponent = exponent + 127;

        // Преобразование biased экспоненты в двоичный формат
        String binaryExponent = Integer.toBinaryString(biasedExponent);

        // Формирование нормализованного числа в формате IEEE 754
        String normalizedBinaryNumber = mantissa.substring(1) + binaryExponent;

        // Добавление нулей в конец, чтобы дополнить до 32 бит
        while (normalizedBinaryNumber.length() < 32) {
            normalizedBinaryNumber += "0";
        }

        return normalizedBinaryNumber;
    }

}

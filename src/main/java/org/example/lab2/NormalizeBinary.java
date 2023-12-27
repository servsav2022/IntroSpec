package org.example.lab2;
import static org.example.lab2.NumberSystems.convertDecimalToBinary;

public class NormalizeBinary {
    public static void main(String[] args) {
        double decimalNumber = 98.71;
        int precision = 5;

        String [] normalizedBinaryNumber = normalizeBinary(decimalNumber,precision);
        System.out.println("Исходное число: "+ decimalNumber);
        System.out.println("После перевода в двоичное: " + convertDecimalToBinary(decimalNumber, precision));
        System.out.println("Знак числа: "+normalizedBinaryNumber[0]);
        System.out.println("Мантисса: " + normalizedBinaryNumber[1]);
        System.out.println("Знак порядка: "+ normalizedBinaryNumber[2]);
        System.out.println("Порядок: "+ normalizedBinaryNumber[3]);
    }
    public static String [] normalizeBinary(double decimalNumber, int precision) {
        String binaryNumber = convertDecimalToBinary(decimalNumber, precision);
        int dotIndex = binaryNumber.indexOf('.');
        if (dotIndex == -1) {
            // Если нет дробной части, то число уже нормализовано
            return new String[]{binaryNumber};
        }
        String Sign="0";
        String OrderSign="0";
        StringBuilder order = new StringBuilder();
        if (decimalNumber<0){Sign="1";}

        int exponent = dotIndex - binaryNumber.indexOf('1') - 1;
        // Преобразование экспоненты в двоичный формат
        String binaryExponent = Integer.toBinaryString(exponent);
        if ("0".equals(binaryNumber.substring(0,1))) {
            OrderSign = "1";
            exponent = dotIndex + binaryNumber.indexOf('1');
            binaryExponent = Integer.toBinaryString(exponent);
            for (int i = 0; i < 4-order.length(); i++) {
                order.insert(0,'0');
            }
        }
        // Выделение мантиссы и экспоненты
        String mantissa = binaryNumber.replace(".", "");
        mantissa=mantissa.substring(0,exponent+2);
        order.append(binaryExponent);
        for (int i = 0; i < 4-order.length(); i++) {
            order.insert(0,'0');
        }
        return new String[] {Sign,mantissa,OrderSign,order.toString()};
    }
}

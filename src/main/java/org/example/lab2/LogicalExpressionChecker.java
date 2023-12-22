package org.example.lab2;

public class LogicalExpressionChecker {
    public static void main(String[] args) {
        // Перебор всех возможных значений переменных x1 и x2
        for (int x1 = 0; x1 <= 1; x1++) {
            for (int x2 = 0; x2 <= 1; x2++) {
                // Исходное логическое выражение
                boolean originalExpression = (x1 * x2 + x2 + x1 * (x1 + x2)) != 0;

                // Упрощенное логическое выражение
                boolean simplifiedExpression = (x1 * x2 + x2 + x1) != 0;

                // Вывод результатов для текущих значений переменных
                System.out.println("x1=" + x1 + ", x2=" + x2 +
                        ", Исходное: " + originalExpression +
                        ", Упрощенное: " + simplifiedExpression);

                // Проверка равносильности
                if (originalExpression == simplifiedExpression) {
                    System.out.println("  Логические выражения равносильны.");
                } else {
                    System.out.println("  Логические выражения не равносильны.");
                }
            }
        }
    }
}

package org.example;

public class StringNumbersToIntSum {

    public static void main(String[] args) {
        System.out.println(stringNumbersToIntSum("2*5"));
    }

    public static int stringNumbersToIntSum(String expression) {
        int result = 0; // Итоговый результат
        int currentNumber = 0; // Текущее число
        int currentProduct = 1; // Текущий результат умножения
        char lastOperator = '+'; // Последний оператор (+ или *)

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                currentNumber = currentNumber * 10 + (expression.charAt(i) - '0');
            }
            if (expression.charAt(i) == '+' || expression.charAt(i) == '*' || i == expression.length() - 1) {
                if (lastOperator == '*') {
                    currentProduct *= currentNumber;
                } else if (lastOperator == '+') {
                    result += currentProduct;
                    currentProduct = currentNumber;
                }
                lastOperator = expression.charAt(i);
                currentNumber = 0;
            }
        }
        result += currentProduct;
        return result;
    }
}
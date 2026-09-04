package org.example;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String row = "([])";

        System.out.println(isValidV2(row));
    }

    public static boolean isValidV(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Открывающая скобка
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }

            // Закрывающая скобка
            else if (ch == ')' || ch == ']' || ch == '}') {

                // Нет открывающей скобки
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Проверяем соответствие
                if (ch == ')' && top != '(') {
                    return false;
                }

                if (ch == ']' && top != '[') {
                    return false;
                }

                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }

        // Если стек пуст — все скобки закрыты правильно
        return stack.isEmpty();
    }

    public static boolean isValidV2(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .reduce(new Stack<Character>(), (stack, c) -> {
                    if (c == '(' || c == '{' || c == '[') {
                        stack.push(c);
                    } else if (c == ')' && stack.peek() == '(') {
                        stack.pop();
                    } else if (c == '}' && stack.peek() == '{') {
                        stack.pop();
                    } else if (c == ']' && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    return stack;
                }, (stack1, stack2) -> stack1)
                .isEmpty();
    }
}

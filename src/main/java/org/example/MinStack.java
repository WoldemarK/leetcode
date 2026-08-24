package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Нужно реализовать структуру данных стек, которая помимо стандартных операций умеет получать минимальный элемент за O(1).
 * <p>
 * Поддерживаемые операции
 * push(int val) — добавить элемент в стек.
 * pop() — удалить верхний элемент.
 * top() — вернуть верхний элемент стека.
 * getMin() — вернуть минимальный элемент, находящийся в стеке.
 * Требования
 * Все операции должны выполняться за O(1) по времени.
 * Разрешается использовать дополнительные внутренние структуры данных.
 * Идея решения
 * <p>
 * Обычный стек не позволяет быстро узнавать минимум, поэтому обычно используют:
 * <p>
 * Два стека:
 * основной стек хранит все элементы;
 * второй стек хранит текущие минимумы.
 * При push(val):
 * элемент кладётся в основной стек;
 * если второй стек пуст или val <= текущий минимум, значение также кладётся во второй стек.
 * При pop():
 * если удаляемый элемент равен текущему минимуму, он удаляется и из стека минимумов.
 * getMin() просто возвращает вершину стека минимумов.
 * Сложность
 * push — O(1)
 * pop — O(1)
 * top — O(1)
 * getMin — O(1)
 */
public class MinStack {

    private final Stack<Integer> stack;
    private final Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    // Добавление элемента
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val < minStack.peek()) {
            minStack.push(val);
        }
    }
    // Удаление верхнего элемента
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int pop = stack.pop();
        if (pop == minStack.peek()) {
            minStack.pop();
        }
    }

    // Верхний элемент
    public int top() {
        return stack.peek();
    }
    // Минимальный элемент
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(6);
        minStack.push(3);
        minStack.push(1);

        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());


    }
}

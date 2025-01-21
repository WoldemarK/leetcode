package org.example;

import java.util.Scanner;

public class Main {

    private static double balance = 10000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Добро пожаловать в ATM!");

        while (running) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1. Проверить баланс");
            System.out.println("2. Снять средства");
            System.out.println("3. Пополнить счет");
            System.out.println("4. Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawFunds(scanner);
                    break;
                case 3:
                    depositFunds(scanner);
                    break;
                case 4:
                    running = false;
                    System.out.println("Спасибо за использование ATM. До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void checkBalance() {
        System.out.printf("Ваш текущий баланс: %.2f рублей\n", balance);
    }

    private static void withdrawFunds(Scanner scanner) {
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Недостаточно средств на счете.");
        } else if (amount <= 0) {
            System.out.println("Сумма должна быть положительной.");
        } else {
            balance -= amount;
            System.out.printf("Вы успешно сняли %.2f рублей.\n", amount);
            checkBalance();
        }
    }

    private static void depositFunds(Scanner scanner) {
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Сумма должна быть положительной.");
        } else {
            balance += amount;
            System.out.printf("Вы успешно пополнили счет на %.2f рублей.\n", amount);
            checkBalance();
        }
    }
}

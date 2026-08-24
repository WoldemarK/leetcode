package org.example;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Setter
@Getter
public class ATM {
    private static final Logger log = Logger.getLogger(ATM.class.getName());
    private Map<Integer, Integer> cashCassettes;

    public ATM() {
        cashCassettes = new HashMap<>();
        cashCassettes.put(100, 0);
        cashCassettes.put(500, 0);
        cashCassettes.put(1000, 0);
        cashCassettes.put(5000, 0);
    }

    private void loadCash(Integer nominal, Integer count) {
        validateNominalAndCount(nominal, count);
        if (cashCassettes.containsKey(nominal)) {
            cashCassettes.put(nominal, count);
            log.info("Загружено: " + count + " банкнот номиналом " + nominal);
        } else {
            log.warning("Ошибка: Неверный номинал " + nominal);
        }
    }

    public boolean withdrawCash(int amount) {
        log.info("Запрашиваемая сумма: " + amount);

        if (!validateAmount(amount)) {
            return false;
        }

        Map<Integer, Integer> cashToDispense = new HashMap<>();

        amount = calculateDispense(amount, cashToDispense);

        if (amount > 0) {
            log.info("Невозможно выдать точную сумму");
            return false;
        }
        //Снятие наличных из кассет
        withdrawal(cashToDispense);

        log.info("Выдача успешна: " + cashToDispense);
        return true;
    }

    private void withdrawal(Map<Integer, Integer> totals) {
        totals.forEach((k, v) -> {
            cashCassettes.put(k, cashCassettes.get(k) - v);
            System.out.printf("Выдано: %d банкнот номиналом %d%n", v, k);
        });
    }

    private int calculateDispense(int amount, Map<Integer, Integer> cashToDispense) {
        for (Map.Entry<Integer, Integer> entry : cashCassettes.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                .toList()) {

            int countNeeded = amount / entry.getKey();

            if (countNeeded > 0) {
                int countToDispense = Math.min(countNeeded, entry.getValue());
                cashToDispense.put(entry.getKey(), countToDispense);
                amount -= countToDispense * entry.getKey();
            }
        }
        return amount;
    }

    private static boolean validateAmount(int amount) {
        if (amount <= 0 || amount % 100 != 0) {
            log.warning("Некорректная сумма");
            return false;
        }
        return true;
    }

    private static void validateNominalAndCount(Integer nominal, Integer count) {
        if (nominal == null || (count == null)) {
            throw new IllegalArgumentException("nominal и count не должны быть null");
        }
        if (count < 0) {
            throw new IllegalArgumentException("count не может быть отрицательным");
        }
    }


    public void displayCassettes() {
        System.out.println("Состояние кассет банкомата:");
        cashCassettes.forEach(((nominal, count) ->
                System.out.printf("Номинал: %d руб., Количество: %d%n", nominal, count)));
    }

    public static void runTests() {
        ATM atm = new ATM();
        // Тест 1: Загрузка и отображение банкнот
        atm.loadCash(100, 10);
        atm.loadCash(500, 10);
        atm.loadCash(1000, 10);
        atm.loadCash(5000, 10);
        atm.displayCassettes();

        // Тест 2: Успешное снятие наличных
        //      System.out.println("Тест 2: Успешное снятие 7600");
        atm.withdrawCash(5300);
        atm.displayCassettes();

        // Тест 3: Попытка снять недоступную сумму
//        System.out.println("Тест 3: Попытка снять 12500 (ожидается ошибка)");
//        atm.withdrawCash(12500);
//        atm.displayCassettes();

//        // Тест 4: Попытка снять сумму, превышающую остаток
//        System.out.println("Тест 4: Попытка снять 50000 (ожидается ошибка)");
//        atm.withdrawCash(500000);

//        // Тест 5: Загрузка банкнот с неверным номиналом
//        System.out.println("Тест 5: Загрузка с неверным номиналом 200");
//        atm.loadCash(200, 10);
    }

    public static void main(String[] args) {
        runTests();

    }
}

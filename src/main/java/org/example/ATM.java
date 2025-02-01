package org.example;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class ATM {

    private Map<Integer, Integer> cashCassettes;

    public ATM() {
        cashCassettes = new HashMap<>();
        cashCassettes.put(100, 0);
        cashCassettes.put(500, 0);
        cashCassettes.put(1000, 0);
        cashCassettes.put(5000, 0);
    }

    /**
     * @param nominals номинал или Key
     * @param count    сума на которую будет произведено пополнение
     */
    public void loadCash(int nominals, int count) {
        if (cashCassettes.containsKey(nominals)) {
            cashCassettes.put(nominals, cashCassettes.get(nominals) + count);
            System.out.printf("Загружено: %d банкнот номиналом %d%n", count, nominals);
        } else {
            System.out.printf("Ошибка: Неверный номинал %d%n", nominals);
        }

    }

    /**
     * Метод для отображения состояния кассет
     */
    public void displayCassettes() {
        System.out.println("Состояние кассет банкомата:");
        cashCassettes.forEach((nominals, count) -> {
            System.out.printf("Номинал: %d руб., Количество: %d%n", nominals, count);
        });

    }

    //    /**
//     * @param amount сума снятия
//     * @return True если все OK, если что-то пошло не так, то False
//     */
//    public boolean withdrawCash(int amount) {
//        if (amount <= 0 || amount % 100 != 0) {
//            System.out.println("Ошибка: Некорректная сумма для снятия.");
//            return false;
//        }
//        Map<Integer, Integer> cashToDispense = new HashMap<>();
//        int remainingAmount = amount; // нужно снять 12_500
//
//        // Сортировка номиналов по убыванию для оптимального снятия
//        for (Integer nominals : cashCassettes.keySet().stream().sorted(Comparator.reverseOrder()).toList()) {
//
//            int countAvailable = cashCassettes.get(nominals); // key(nominal) 5000 value-количество 5 = 25_000
//            int countNeeded = remainingAmount / nominals; // 12_5000/ 5_000=2
//            if (countNeeded > 0) {                        // 2>0 yes
//                int countToDispense = Math.min(countNeeded, countAvailable); //  2<== (2, 10_000) == 2
//                cashToDispense.put(nominals, countToDispense);  // Kys (nominals)=5000 Value (countToDispense) =2
//                 remainingAmount -= countToDispense * nominals; // 10_000 -= 2 * 5000
//            }
//        }
//        if (remainingAmount > 0) {
//            System.out.println("Ошибка: Невозможно выдать запрошенную сумму.");
//            return false;
//        }
//        // Снятие наличных из кассет
//        cashToDispense.forEach((nominals, count) -> {
//            cashCassettes.put(nominals, cashCassettes.get(nominals) - count);
//            System.out.printf("Выдано: %d банкнот номиналом %d%n", count, nominals);
//        });
//        System.out.printf("Сумма %d выдана успешно.%n", amount);
//        return true;
//    }
    public boolean withdrawCash(int amount) {
        if (amount <= 0 || amount % 100 != 0) {
            System.out.println("Ошибка: Некорректная сумма для снятия.");
            return false;
        }
        Map<Integer, Integer> cashToDispense = new HashMap<>();
        int tem = amount;
        for (Integer nominals : cashCassettes.keySet().stream().sorted(Comparator.reverseOrder()).toList()) {
            int key = nominals;
            int value = cashCassettes.getOrDefault(key, -129);
            int countNeeded = tem / key;
            if (countNeeded > 0) {
                int countToDispense = Math.min(countNeeded, value);
                cashToDispense.put(key, countToDispense);
                tem -= countToDispense * key;
            }
        }
        if (tem > 0) {
            System.out.println("Ошибка: Невозможно выдать запрошенную сумму.");
            return false;
        }
        //Снятие наличных из кассет
        cashToDispense.forEach((nominals, count) -> {
            cashCassettes.put(nominals, cashCassettes.get(nominals) - count);
            System.out.printf("Выдано: %d банкнот номиналом %d%n", count, nominals);
        });
        System.out.printf("Сумма %d выдана успешно.%n", amount);
        return true;
    }

    public static void runTests() {
        ATM atm = new ATM();
        // Тест 1: Загрузка и отображение банкнот
        atm.loadCash(100, 0);
        atm.loadCash(500, 1);
        atm.loadCash(1000, 2);
        atm.loadCash(5000, 1);
        atm.displayCassettes();

        // Тест 2: Успешное снятие наличных
        //      System.out.println("Тест 2: Успешное снятие 7600");
        atm.withdrawCash(7600);
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

package org.example.MapgroupingBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MainMapDemo {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", Department.IT, PositionLevel.SENIOR, 120_000),
                new Employee("Bob", Department.IT, PositionLevel.MIDDLE, 90_000),
                new Employee("Charlie", Department.FINANCE, PositionLevel.LEAD, 150_000),
                new Employee("Diana", Department.FINANCE, PositionLevel.JUNIOR, 60_000)
        );
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, BigDecimal.valueOf(20.), TransactionStatus.COMPLETED, LocalDate.now()),
                new Transaction(2, BigDecimal.valueOf(10.), TransactionStatus.PENDING, LocalDate.now()),
                new Transaction(3, BigDecimal.valueOf(30.), TransactionStatus.FAILED, LocalDate.now()),
                new Transaction(4, BigDecimal.valueOf(40.), TransactionStatus.REFUNDED, LocalDate.now())
        );

//        String json = mapper.writeValueAsString(employees);
//        System.out.println(json);
//        System.out.print("________________________________________________");
//
//        Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::department));
//        System.out.println(mapper.writeValueAsString(byDept));
//        System.out.print("________________________________________________");
//
        // Количество сотрудников в отделе
        Map<Department, Long> counts = employees.stream().collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
        System.out.println(mapper.writeValueAsString(counts));
        System.out.print("________________________________________________");
//
//        // Максимальная зарплата в отделе
//        Map<Department, Optional<Employee>> maxSalary = employees.stream()
//                .collect(Collectors.groupingBy(Employee::department,
//                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));
//        System.out.println(mapper.writeValueAsString(maxSalary));
//        System.out.print("________________________________________________");
//
//        // Суммарная зарплата по отделам
//        Map<Department, Double> totalSalaries = employees.stream()
//                .collect(Collectors.groupingBy(Employee::department, Collectors.summingDouble(Employee::salary)));
//        System.out.println(mapper.writeValueAsString(totalSalaries));
//        System.out.print("________________________________________________");
//
//        // LinkedHashMap для сохранения порядка вставки
//        Map<Department, List<Employee>> ordered = employees.stream()
//                .collect(Collectors.groupingBy(Employee::department, LinkedHashMap::new, Collectors.toList()));
//        System.out.println(mapper.writeValueAsString(ordered));
//        System.out.print("________________________________________________");
//
//        // Группировка по отделу → по уровню должности
//        Map<Department, Map<PositionLevel, List<Employee>>> nested = employees.stream()
//                .collect(Collectors.groupingBy(Employee::department, Collectors.groupingBy(Employee::positionLevel)));
//        System.out.println(mapper.writeValueAsString(nested));
//
//        System.out.print("________________________________________________");
//
//        // Группировка по году → месяцу → списку транзакций (полезно для финансовых приложений)
//        Map<Integer, Map<Integer, List<Transaction>>> byYearMonth = transactions.stream()
//                .collect(Collectors.groupingBy(t -> t.date().getYear(),
//                        Collectors.groupingBy(t -> t.date().getMonthValue(),
//                                Collectors.toList())));
//        System.out.println(byYearMonth);
//        System.out.print("________________________________________________");
//
//        // Группировка транзакций по статусу + агрегация сумм для СберКидс
//        Map<TransactionStatus, BigDecimal> amountsByStatus = transactions.stream()
//                .collect(Collectors.groupingBy(Transaction::status, Collectors.mapping(
//                        Transaction::amount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
//        System.out.println(mapper.writeValueAsString(amountsByStatus));
//        System.out.print("________________________________________________");
//
//        // Имена сотрудников в отделе через запятую
//        Map<Department, String> namesByDept = employees.stream()
//                .collect(Collectors.groupingBy(
//                        Employee::department,
//                        Collectors.mapping(
//                                Employee::name,
//                                Collectors.joining(", ")
//                        )
//                ));
//        System.out.println(mapper.writeValueAsString(namesByDept));
//        System.out.print("________________________________________________");
//// Статистика по зарплатам в отделе
//        Map<Department, DoubleSummaryStatistics> stats = employees.stream()
//                .collect(Collectors.groupingBy(
//                        Employee::department,
//                        Collectors.summarizingDouble(Employee::salary)
//                ));
//        System.out.println(mapper.writeValueAsString(stats));
//        System.out.print("________________________________________________");
    }
}

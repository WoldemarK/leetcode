package org.example;


import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(getValue());

        MyClass myClass = new MyClass(10);
        Set<MyClass> set = new HashSet<>();
        set.add(myClass);
        myClass.setValue(100);
        System.out.println(set.contains(myClass));
        System.out.println(set.contains(new MyClass(10))); // во всех трех случаях будет false
        System.out.println(set.contains(new MyClass(1000))); // так как не корректен метод hashCode и equals
        System.out.print(set.equals(new MyClass(1000)));
        System.out.println("-----------------------------------");

        try {  // 2 4 5 6
            try {
                throw new Exception(""); // Внутренний блок catch пытается поймать исключение типа RuntimeException,
                // но выброшенное исключение Exception не является RuntimeException, поэтому этот блок catch не срабатывает.
            } catch (RuntimeException e) {
                System.out.println("1");
            } finally {
                System.out.println("2");
            }
            System.out.println("3");
        } catch (Exception e) {
            System.out.println("4");
        } finally {
            System.out.println("5");
        }
        System.out.println("6");

    }

    private static int getValue() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }
}

class MyClass {
    private int value;

    public MyClass(int value) { // был private стал public
        setValue(value);
    }

    public void setValue(int value) { // был private стал public
        this.value = value;
    }

    public int hashCode() {
        return value;
    }

//    public boolean equals(Object obj) { // был MyClass стал Object
//        return (obj.value == this.value);   // return (obj.value == this.value);
//    }


}
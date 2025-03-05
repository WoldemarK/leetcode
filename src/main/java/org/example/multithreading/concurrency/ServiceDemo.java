package org.example.multithreading.concurrency;

public class ServiceDemo {
    public static void main(String[] args) {
        Service service = new Service();

        service.readData();
        service.showGreetingMessage();
        service.calculateFactorial(10);
        service.calculateSum(20);
        service.finishProgram();
    }
}

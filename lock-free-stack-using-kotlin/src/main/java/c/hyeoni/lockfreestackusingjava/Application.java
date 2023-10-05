package c.hyeoni.lockfreestackusingjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        ClassicStack<Integer> operStack = new ClassicStack<>();

        Random randomIntegerGenerator = new Random();

        for (int j = 0; j < 10; j++) {
            operStack.push(randomIntegerGenerator.nextInt());
        }

        // Defining threads for Stack Operations
        List<Thread> threads = new ArrayList<>();
        int stackPushThreads = 2;
        int stackPopThreads = 2;

        for (int k = 0; k < stackPushThreads; k++) {
            Thread pushThread = new Thread(() -> {
                System.out.println("Pushing into stack...");

                while (true) {
                    operStack.push(randomIntegerGenerator.nextInt());
                }
            });

            // making the threads low priority before
            // starting them
            pushThread.setDaemon(true);
            threads.add(pushThread);
        }

        for (int k = 0; k < stackPopThreads; k++) {
            Thread popThread = new Thread(() -> {
                System.out.println("Popping from stack ...");
                while (true) {
                    operStack.pop();
                }
            });

            popThread.setDaemon(true);
            threads.add(popThread);
        }

        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(500);

        System.out.println(
                "The number of stack operations performed in 1/2 a second--> " + operStack.getNoOfOperations());
    }
}

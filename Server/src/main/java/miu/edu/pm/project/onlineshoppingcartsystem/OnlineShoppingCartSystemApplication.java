package miu.edu.pm.project.onlineshoppingcartsystem;

import java.lang.Runtime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShoppingCartSystemApplication {

    public static void main(String[] args) {
        // Register a shutdown hook to stop the threads when the application shuts down
//        Runtime runtime = Runtime.getRuntime();
//        runtime.addShutdownHook(new Thread(() -> {
//            try {
//                // Stop the "HikariPool-1 housekeeper" thread
//                Thread[] threads = new Thread[Thread.activeCount()];
//                Thread.enumerate(threads);
//                for (Thread thread : threads) {
//                    if (thread.getName().equals("HikariPool-1 housekeeper")) {
//                        thread.interrupt();
//                        thread.join();
//                    }
//                }
//
//                // Stop the "HikariPool-1 connection adder" thread
//                threads = new Thread[Thread.activeCount()];
//                Thread.enumerate(threads);
//                for (Thread thread : threads) {
//                    if (thread.getName().equals("HikariPool-1 connection adder")) {
//                        thread.interrupt();
//                        thread.join();
//                    }
//                }
//            } catch (InterruptedException e) {
//                // Handle the exception if necessary
//            }
//        }));

        // Start the web application here
        SpringApplication.run(OnlineShoppingCartSystemApplication.class, args);
    }
}

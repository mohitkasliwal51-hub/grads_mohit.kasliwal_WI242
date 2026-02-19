import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Game {

    private int distanceToCovered;
    private int numberOfBiker;

    public Game() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Distance (KM): ");
        distanceToCovered = sc.nextInt() * 1000;

        System.out.print("Enter Number of bikers: ");
        numberOfBiker = sc.nextInt();
    }

    public void startRace() throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfBiker);
        CountDownLatch cdl = new CountDownLatch(3);
        List<Future<Biker>> futures = new ArrayList<>();
        List<Biker> bikers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        List<Biker> bikerTasks = new ArrayList<>();

        // Step 1: Take all inputs first
        for (int i = 0; i < numberOfBiker; i++) {
            System.out.print("Enter biker name: ");
            String name = sc.next();
            bikerTasks.add(new Biker(name, cdl, distanceToCovered));
        }

        System.out.println("\nAll bikers ready!");
        System.out.println("Race starting in 3 seconds...\n");
        for (int i = 3; i >= 1; i--) {
            System.out.println(i);
            cdl.countDown();
            Thread.sleep(1000);
        }

        // Step 2: Start race together
        for (Biker biker : bikerTasks) {
            futures.add(executor.submit(biker));
        }

        System.out.println("\nRace Started...\n");

        // Wait for all bikers to finish
        for (Future<Biker> future : futures) {
            bikers.add(future.get());
        }

        executor.shutdown();

        showDashboard(bikers);
    }

    private void showDashboard(List<Biker> bikers) {

        bikers.sort(Comparator.comparing(Biker::getEndTime));

        System.out.println("\n========== FINAL DASHBOARD ==========");
        System.out.println("Rank\tName\tStart Time\t\tEnd Time\t\tTime(ms)");
        int rank = 1;
        for (Biker b : bikers) {
            System.out.println(rank++ + "\t"
                    + b.getName() + "\t"
                    + b.getStartTime() + "\t"
                    + b.getEndTime() + "\t"
                    + b.getTimeTaken());
        }
    }
}

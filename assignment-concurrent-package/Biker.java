import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

class Biker implements Callable<Biker> {

    private String name;
    private int totalDistance;
    private int distanceCovered = 0;
    private int speed;
    private LocalTime startTime;
    private LocalTime endTime;
    private CountDownLatch cdl;

    public Biker(String name, CountDownLatch c, int totalDistance) {
        this.cdl = c;
        this.name = name;
        this.totalDistance = totalDistance;
        this.speed = new Random().nextInt(200) + 100;
    }

    @Override
    public Biker call() throws Exception {
        cdl.await();

        System.out.println(name + " started race!");
        startTime = LocalTime.now();

        while (distanceCovered < totalDistance) {
            distanceCovered += 100;
            System.out.println(name + " covered " + distanceCovered + " meters");
            Thread.sleep(300);
        }

        endTime = LocalTime.now();
        System.out.println(name + " FINISHED");

        return this;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public long getTimeTaken() {
        return Duration.between(startTime, endTime).toMillis();
    }
}
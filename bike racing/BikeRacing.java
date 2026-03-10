
import java.util.Random;
import java.util.*;

class RaceCourse {
    public static int courseLength = 1000;
    public static int ranking = 0;

    public void setCourseLength(int length) {
        courseLength = length;
    }

    public int getCourseLength() {
        return courseLength;
    }

    public void setRanking(int rank) {
        ranking = rank;
    }

    public int getRanking() {
        return ranking;
    }
}

class Bike extends Thread {
    protected String bikerName;
    protected int currentLocation;
    protected int startTime;
    protected int endTime;

    public Bike(String bikerName) {
        this.bikerName = bikerName;
        this.currentLocation = 0;
        this.startTime = 0;
        this.endTime = 0;
    }
}

class RaceBike extends Bike {
    int ranking = 0;

    public RaceBike(String bikerName) {
        super(bikerName);
    }

    Random rand = new Random();


    public void run() {
        while (this.currentLocation < RaceCourse.courseLength) {
            try {
                Thread.sleep(rand.nextInt(100));
            } 
            catch (InterruptedException e) {
                System.out.println("Something went wrong!");
            }
            this.currentLocation += 100;
            System.out.println(bikerName + " has covered " + this.currentLocation + "m");
        }
        System.out.println("Biker " + bikerName + " has completed the race!");
        this.ranking = ++RaceCourse.ranking;
    }
}

class Race {
    RaceBike[] racers = new RaceBike[10];
    RaceCourse course = new RaceCourse();

    public void initializeRacers() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter biker name:");
            String name = sc.nextLine();
            RaceBike r = new RaceBike(name);
            racers[i] = r;
        }
    }

    public void initializeRace() {
        RaceCourse course = new RaceCourse();
        course.setCourseLength(1000);
        this.course = course;
    }

    public void startRace() {
        System.out.println("Race starts!");

        for (RaceBike r : racers) {
            try {
                r.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (RaceBike r : racers) {
            try {
                r.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void displayRanking() {
        System.out.println("Final rankings: ");
        for (RaceBike racer : racers) {
            System.out.println("Biker " + racer.bikerName + " is at " + racer.ranking + " position");
        }
    }
}

public class BikeRacing {
    public static void main(String[] args) {
        Race race = new Race();
        race.initializeRace();
        race.initializeRacers();
        race.startRace();
        race.displayRanking();
    }
}

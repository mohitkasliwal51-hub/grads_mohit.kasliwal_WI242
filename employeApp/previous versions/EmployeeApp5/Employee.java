import java.io.Serializable;
import java.util.*;

/* -------- EMPLOYEE -------- */
abstract class Employee extends Organization implements Serializable {
    private String name;
    private int age;
    protected double salary;
    protected Designation designation;
    private Gender gender;
    private String eid;

    public Employee(double sal, Designation desig, char type) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Name : ");
            this.name = sc.nextLine();

            System.out.print("Enter Age : ");
            this.age = Integer.parseInt(sc.nextLine());
            if (age < 18 || age > 60)
                throw new IllegalArgumentException("Age must be between 18 and 60");

            System.out.print("Enter Gender (M/F) : ");
            char g = sc.nextLine().toUpperCase().charAt(0);
            if (g == 'M')
                this.gender = Gender.MALE;
            else if (g == 'F')
                this.gender = Gender.FEMALE;
            else
                throw new IllegalArgumentException("Invalid gender");

            this.salary = sal;
            this.designation = desig;

            /* AUTO-GENERATE EID */
            count++;
            this.eid = "E" + type + String.format("%04d", count);

            System.out.println("Employee created successfully.");
            System.out.println("Generated Employee ID : " + eid);

        } catch (NumberFormatException e) {
            System.out.println("Age must be numeric. Try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void raiseSalary() {
        setSalary(3000);
    }

    public void setSalary(int increment) {
        salary += increment;
    }

    public final void display() {
        System.out.println("-------------------------");
        System.out.println("Name        : " + name);
        System.out.println("Age         : " + age);
        System.out.println("Gender      : " + gender);
        System.out.println("Employee ID : " + eid);
        System.out.println("Designation : " + designation);
        System.out.println("Salary      : " + salary);
    }

    public String getSummary() {
        return name + " - " + designation;
    }

    public String toCSV() {
        return eid + "," + name + "," + age + "," + salary + "," + designation;
    }

    public String getEid() {
        return this.eid;
    }
}

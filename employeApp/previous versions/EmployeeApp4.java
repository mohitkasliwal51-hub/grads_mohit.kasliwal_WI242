// Employee -> Clerk, Programmer, Manager

// * Should not be able to create object of Employee class
// * No sub-class of Employee can skip raiseSalary() method
// * No sub-class should modify display() method
// * No sub-class from Clerk/Programmer/Manager
// * Employee should have gender property
// * Employee with eid which should have EC9999 (First letter E followed by P/C and then last 4 digits)
// * Should display the number of employees created at the end

import java.util.Scanner;
import java.util.InputMismatchException;

/* -------- ENUMS -------- */
enum Gender {
    MALE, FEMALE
}

enum Designation {
    CLERK, PROGRAMMER, MANAGER
}

/* -------- ORGANIZATION -------- */
abstract class Organization {
    protected static int count = 0;

    public static void showEmployeeCount() {
        System.out.println("Total Employees Created : " + count);
    }
}

/* -------- EMPLOYEE -------- */
abstract class Employee extends Organization {

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
            name = sc.nextLine();

            System.out.print("Enter Age : ");
            age = Integer.parseInt(sc.nextLine());
            if (age < 18 || age > 60)
                throw new IllegalArgumentException("Age must be between 18 and 60");

            System.out.print("Enter Gender (M/F) : ");
            char g = sc.nextLine().toUpperCase().charAt(0);
            if (g == 'M')
                gender = Gender.MALE;
            else if (g == 'F')
                gender = Gender.FEMALE;
            else
                throw new IllegalArgumentException("Invalid gender");

            salary = sal;
            designation = desig;

            /* AUTO-GENERATE EID */
            count++;
            eid = "E" + type + String.format("%04d", count);

            System.out.println("Employee created successfully.");
            System.out.println("Generated Employee ID : " + eid);

        } catch (NumberFormatException e) {
            System.out.println("Age must be numeric. Try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public abstract void raiseSalary();

    protected void setSalary(double amt) {
        salary += amt;
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
}

/* -------- CLERK -------- */
final class Clerk extends Employee {
    public Clerk() {
        super(20000, Designation.CLERK, 'C');
    }

    public void raiseSalary() {
        setSalary(3000);
    }
}

/* -------- PROGRAMMER -------- */
final class Programmer extends Employee {
    public Programmer() {
        super(50000, Designation.PROGRAMMER, 'P');
    }

    public void raiseSalary() {
        setSalary(10000);
    }
}

/* -------- MANAGER -------- */
final class Manager extends Employee {
    public Manager() {
        super(100000, Designation.MANAGER, 'M');
    }

    public void raiseSalary() {
        setSalary(25000);
    }
}

/* -------- MAIN -------- */
public class EmployeeApp4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[10];
        int empCount = 0;

        while (true) {
            System.out.println("\n---------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.println("---------------------------");
            System.out.print("Enter choice : ");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        if (empCount >= employees.length) {
                            System.out.println("Employee limit reached");
                            break;
                        }

                        System.out.println("1. Clerk  2. Programmer  3. Manager");
                        System.out.print("Choose type : ");
                        int type = sc.nextInt();

                        if (type == 1)
                            employees[empCount++] = new Clerk();
                        else if (type == 2)
                            employees[empCount++] = new Programmer();
                        else if (type == 3)
                            employees[empCount++] = new Manager();
                        else
                            System.out.println("Invalid employee type");
                        break;

                    case 2:
                        for (int i = 0; i < empCount; i++)
                            employees[i].display();
                        break;

                    case 3:
                        if (empCount == 0) {
                            System.out.println("No employees available.");
                            break;
                        }

                        System.out.println("Select employee to raise salary:");
                        for (int i = 0; i < empCount; i++) {
                            System.out.println((i + 1) + ". " + employees[i].getSummary());
                        }

                        System.out.print("Enter employee number: ");
                        int empChoice = sc.nextInt();

                        if (empChoice < 1 || empChoice > empCount) {
                            System.out.println("Invalid employee selection.");
                            break;
                        }

                        employees[empChoice - 1].raiseSalary();
                        System.out.println("Salary raised successfully.");
                        break;

                    case 4:
                        Organization.showEmployeeCount();
                        System.out.println("Thank you!");
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers only.");
                sc.nextLine();
            }
        }
    }
}


//now in same employee app use array to store multiple employees
// * Write a program to Create multiple employees which should be asking to enter the details like name, age, salary, designation
// * Also should be able to raise the salary of an employee
// * All this should be menu driven
//*use the concepts of exception handeling where required
import java.util.InputMismatchException;
import java.util.Scanner;

class Employee {
    private String name, designation;
    private int age;
    private double salary;

    public void create() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Name: ");
            this.name = sc.nextLine();
            System.out.print("Enter Age: ");
            this.age = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Designation: ");
            this.designation = sc.nextLine();
            System.out.print("Enter Salary: ");
            this.salary = Double.parseDouble(sc.nextLine());
            System.out.println("Employee created successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Age and salary must be numbers.");
        }
    }

    public void raiseSalary(double increment) {
        this.salary += increment;
    }

    public void display() {
        System.out.println("---------------------");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Designation: " + this.designation);
        System.out.println("Salary: " + this.salary);
    }
}

public class EmployeeApp3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[10];
        int count = 0;
        while (true) {
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        if (count < employees.length) {
                            employees[count] = new Employee();
                            employees[count].create();
                            count++;
                        } else {
                            System.out.println("Employee limit reached.");
                        }
                        break;
                    case 2:
                        for (Employee employee : employees) {
                            employee.display();
                        }
                        break;
                    case 3:
                        System.out.print("Enter Employee Number to raise salary: ");
                        int empNum = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        if (empNum > 0 && empNum <= count) {
                            System.out.print("Enter increment amount: ");
                            double increment = sc.nextDouble();
                            employees[empNum - 1].raiseSalary(increment);
                            System.out.println("Salary raised successfully.");
                        } else {
                            System.out.println("Invalid Employee Number.");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.out.println("Total Employees Created: " + count);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                System.exit();
            } catch (NullPointerException e) {
                System.out.println("No employees to display.");
                continue;
            } finally {
                System.out.println("program completed.");
            }
        }
    }
}
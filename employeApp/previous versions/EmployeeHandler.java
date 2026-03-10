import java.util.*;

// E is fixed, C/P for 2nd letter, then 4 numbers
enum Gender {
    MALE, FEMALE;
}

enum Designation {
    CLERK, PROGRAMMER, MANAGER;
}

abstract class CompanyResources {
    protected static int count = 0;
    public static void totalEmployees() {
        System.out.println("Number of employees in ORG: " + count);
    }
}

abstract class Employee extends CompanyResources {
    private String name;
    private int age;
    protected double salary;
    protected Designation designation;
    private String employeeID;
    private Gender gender;

    public void addDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Kindly enter the name of the employee\n");
        String name = sc.nextLine();
        setName(name);

        System.out.println("Kindly enter the age of the employee\n");
        int age = Integer.parseInt(sc.nextLine()); 
        setAge(age);

        System.out.println("Kindly enter the gender of the employee\n");
        String gender = sc.nextLine();
        setGender(gender);

        System.out.println("Kindly enter the employee ID\n");
        String employeeID = sc.nextLine();
        while (!employeeIDVerification(employeeID)) {
            System.out.println("Kindly enter the correct employee ID\n");
            employeeID = sc.nextLine();
        }

        count += 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 15 || age > 75) {
            System.out.println("The Employee's age is incorrect\n");
            return;
        }
        this.age = age;
    }

    public void setGender(String gender) {
        String regex = "^.*[Ff]{1}.*$";
        if (gender.matches(regex)) {
            this.gender = Gender.FEMALE;
        }
        else {
            this.gender = Gender.MALE;
        }
        System.out.println("Gender set to: " + this.gender);
    }

    public String getName() {
        return this.name;
    }

    public final void printEmployeeDetails() {
        System.out.println("Employee name " + this.name);
        System.out.println("Employee Age " + this.age);
        System.out.println("Employee Salary " + this.salary);
        System.out.println("Employee Designation " + this.designation);
        System.out.println("Employee Gender " + this.gender);
        System.out.println("Employee ID " + this.employeeID);
    }

    public abstract void raiseSalary();

    private boolean employeeIDVerification(String employeeID) {
        String regex = "^[E]{1}[PC]{1}[0-9]{4}$";
        if (employeeID.matches(regex)) {
            System.out.println(employeeID + " is accepted.");
            this.employeeID = employeeID;
            return true;
        }
        else {
            System.out.println(employeeID + " is an invalid employee ID.");
            return false;
        }
    }
}

final class Clerk extends Employee {

    public Clerk() {
        this.salary = 20000;
        this.designation = Designation.CLERK;
    }

    @Override
    public void raiseSalary() {
        this.salary += 3000;
    }
}

final class Programmer extends Employee {
    public Programmer() {
        this.salary = 50000;
        this.designation = Designation.PROGRAMMER;
    }

    @Override
    public void raiseSalary() {
        this.salary += 10000;
    }
}

final class Manager extends Employee {
    public Manager() {
        this.salary = 100000;
        this.designation = Designation.MANAGER;
    }

    @Override
    public void raiseSalary() {
        this.salary += 30000;
    }
}

public class EmployeeHandler {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("Kindly enter the choice of operation: ");
            System.out.println("1. Create Employee ");
            System.out.println("2. Display Employee ");
            System.out.println("3. Raise Salary of Employee ");
            System.out.println("4. Exit ");
            System.out.println("-----------------------------------------");

            int choiceOfOperation = sc.nextInt();
            switch (choiceOfOperation) {

                case 1 -> {
                    employees = employeeCreation();
                }

                case 2 -> {
                    System.out.println("Employees in the organisation: ");
                    sc.nextLine();
                    for (Employee emp : employees) {
                        emp.printEmployeeDetails();
                    }
                }

                case 3 -> {
                    System.out.println("Raising the salary of all employees.");

                    for (Employee emp : employees) {
                        emp.raiseSalary();
                        System.out.println("Salary successfully raised! Receipt: ");
                        emp.printEmployeeDetails();
                    }
                }
                case 4 -> {
                    System.out.println("Total Employees created: " + employees.size());
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please retry.");
            }
        }
    }

    private static ArrayList<Employee> employeeCreation() {
        ArrayList<Employee> employees = new ArrayList<>();
        int choiceOfEmployeeType;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("Kindly enter the Employee type you're creating.");
            System.out.println("1. Enter 1 for Clerk ");
            System.out.println("2. Enter 2 for Programmer ");
            System.out.println("3. Enter 3 for Manager ");
            System.out.println("4. Enter 4 to end new Employee entry ");
            System.out.println("-----------------------------------------");
            choiceOfEmployeeType = sc.nextInt();
            switch (choiceOfEmployeeType) {
                case 1 -> {
                    System.out.println("Creating Employee Clerk...");   
                    Clerk c = new Clerk();                 
                    c.addDetails();
                    employees.add(c);
                }
                case 2 -> {
                    System.out.println("Creating Employee Programmer...");
                    Programmer p = new Programmer();
                    p.addDetails();
                    employees.add(p);
                }
                case 3 -> {
                    System.out.println("Creating Employee Manager...");
                    Manager m = new Manager();
                    m.addDetails();
                    employees.add(m);
                }
                case 4 -> {
                    System.out.println("Employees created. Appending...");
                    return employees;
                }
                default -> {
                    Employee.totalEmployees();
                    System.out.println("Invalid choice, try again!");
                }
            }

        }
    }
}

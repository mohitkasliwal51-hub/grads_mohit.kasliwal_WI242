import java.util.*;
import java.io.*;

public class EmployeeApp {
    public static void main(String[] args) {
        EmployeeFileHandler.initializeFile(); // Ensure file exists
        Scanner sc = new Scanner(System.in);
        Employee emp = null;
        ArrayList<Employee> empList = new ArrayList<>();
        int choice;
        do {
            System.out.println("\n---------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.println("---------------------------");
            System.out.print("Enter choice : ");
            choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.println("1. Clerk  2. Programmer  3. Manager");
                        System.out.print("Choose type : ");
                        int type = sc.nextInt();
                        if (type == 1)
                            emp = new Clerk();
                        else if (type == 2)
                            emp = new Programmer();
                        else if (type == 3)
                            emp = new Manager();
                        else {
                            System.out.println("Invalid employee type");
                            break;
                        }
                        empList.add(emp);
                        EmployeeFileHandler.write(emp);
                        break;
                    case 2:
                        EmployeeFileHandler.read();
                        break;

                    case 3:
                        if (Organization.getEmployeeCount() == 0) {
                            System.out.println("No employees available.");
                            break;
                        }

                        System.out.print("Enter Employee ID to raise salary: ");
                        String id = sc.next();

                        boolean found = false;
                        for (Employee e : empList) {
                            if (emp.getEid().equals(id)) {
                                emp.raiseSalary(); // 🔥 polymorphism
                                EmployeeFileHandler.updateEmployee(emp);
                                System.out.println("Salary updated.");
                                found = true;

                                break;
                            }
                        }
                        if (!found)
                            System.out.println("Employee not found in current session.");
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
        } while (choice != 4);
        sc.close();
    }
}
import java.util.*;

public class EmployeeApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee emp = null;
        int choice = 0;
        do {
            System.out.println("\n---------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.println("---------------------------");

            // Read menu choice safely
            while (true) {
                System.out.print("Enter choice : ");
                String line = sc.nextLine().trim();
                try {
                    choice = Integer.parseInt(line);
                    break;

                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter numbers only.");
                }
            }

            try {
                switch (choice) {
                    case 1: {
                        System.out.println("1. Clerk  2. Programmer  3. Manager");
                        int type;
                        while (true) {
                            System.out.print("Choose type : ");
                            String t = sc.nextLine().trim();
                            try {
                                type = Integer.parseInt(t);
                                if (type < 1 || type > 3) {
                                    System.out.println("Invalid employee type");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Please enter numbers only.");
                            }
                        }

                        if (type == 1)
                            emp = new Clerk();
                        else if (type == 2)
                            emp = new Programmer();
                        else
                            emp = new Manager();

                        EmployeeFileHandler.serialize(emp);
                        break;
                    }
                    case 2: {
                        ArrayList<Employee> list = EmployeeFileHandler.deserializeAll();

                        if (list.isEmpty()) {
                            System.out.println("No employees found.");
                            break;
                        }

                        for (Employee e : list) {
                            e.display();
                        }
                        break;
                    }
                    case 3: {
                        ArrayList<Employee> list = EmployeeFileHandler.deserializeAll();

                        if (list.isEmpty()) {
                            System.out.println("No employees found.");
                            break;
                        }

                        System.out.print("Enter Employee ID: ");
                        String id = sc.next();

                        boolean found = false;

                        for (Employee e : list) {
                            if (e.getEid().equals(id)) {
                                e.raiseSalary(); // 🔥 polymorphism from deserialized object
                                found = true;
                                break;
                            }
                        }

                        if (found) {
                            EmployeeFileHandler.rewriteAll(list);
                            System.out.println("Salary updated successfully.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    }
                    case 4: {
                        System.out.println("Total number of employees : " + EmployeeFileHandler.getSerializedCount());
                        System.out.println("Thank you!");
                        sc.close();
                        return;
                    }
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers only.");
                sc.nextLine(); // defensive clear
            }
        } while (choice != 4);
        sc.close();
    }
}
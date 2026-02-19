import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeApp {

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<Employee>();

        list.add(new Employee("Suresh", 35, 45000, Gender.MALE, Designation.MANAGER, "HR"));
        list.add(new Employee("Mahesh", 30, 38000, Gender.MALE, Designation.CLERK, "QA"));
        list.add(new Employee("Rajesh", 26, 30000, Gender.MALE, Designation.PROGRAMMER, "IT"));
        list.add(new Employee("Dinesh", 40, 50000, Gender.MALE, Designation.MANAGER, "ADMIN"));

        list.add(new Employee("Priya", 25, 28000, Gender.FEMALE, Designation.CLERK, "QA"));
        list.add(new Employee("Meena", 32, 42000, Gender.FEMALE, Designation.MANAGER, "IT"));
        list.add(new Employee("Kavya", 29, 35000, Gender.FEMALE, Designation.PROGRAMMER, "IT"));
        list.add(new Employee("Anita", 38, 47000, Gender.FEMALE, Designation.MANAGER, "FINANCE"));
        list.add(new Employee("Savita", 24, 26000, Gender.FEMALE, Designation.CLERK, "CUSTOMER_CARE"));

        list.add(new Employee("Rohit", 27, 31000, Gender.MALE, Designation.PROGRAMMER, "IT"));
        list.add(new Employee("Amit", 33, 43000, Gender.MALE, Designation.MANAGER, "IT"));
        list.add(new Employee("Kiran", 29, 34000, Gender.MALE, Designation.CLERK, "QA"));
        list.add(new Employee("Naveen", 36, 48000, Gender.MALE, Designation.MANAGER, "SALES"));
        list.add(new Employee("Yogesh", 23, 25000, Gender.MALE, Designation.CLERK, "CUSTOMER_CARE"));

        list.add(new Employee("Shreya", 31, 41000, Gender.FEMALE, Designation.MANAGER, "HR"));
        list.add(new Employee("Pooja", 37, 46000, Gender.FEMALE, Designation.MANAGER, "HR"));
        list.add(new Employee("Sneha", 28, 33000, Gender.FEMALE, Designation.PROGRAMMER, "IT"));
        list.add(new Employee("Vidya", 34, 44000, Gender.FEMALE, Designation.MANAGER, "QA"));
        list.add(new Employee("Rina", 22, 24000, Gender.FEMALE, Designation.CLERK, "IT"));

        list.add(new Employee("Kunal", 26, 29000, Gender.MALE, Designation.CLERK, "QA"));
        list.add(new Employee("Harish", 45, 52000, Gender.MALE, Designation.CEO, "OPERATIONS"));
        list.add(new Employee("Lokesh", 38, 49000, Gender.MALE, Designation.MANAGER, "IT"));
        list.add(new Employee("Manoj", 27, 30500, Gender.MALE, Designation.PROGRAMMER, "IT"));
        list.add(new Employee("Sanjay", 30, 36000, Gender.MALE, Designation.CLERK, "FINANCE"));

        // *Find the highest salary paid Employee
        List<Employee> emp1 = list.stream()
                .sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary()))
                .limit(1)
                .toList();

        System.out.println("Employee with highest salary : " + emp1);
        System.out.println("---------------------");

        // *Find how many male & Female employee working in the company(numbers)
        Map<Gender, Long> emp2 = list.stream()
                .collect(Collectors.groupingBy(e -> e.getGender(), Collectors.counting()));

        System.out.println("Count of MALE & FEMALE Employee : " + emp2);
        System.out.println("---------------------");

        // *Total expense for the company department wise
        Map<String, Integer> emp3 = list.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(),
                        Collectors.summingInt(e -> e.getSalary())));

        System.out.println("Total expense Department wise : ");
        System.out.println(emp3);
        System.out.println("------------------------");

        // *Who is the top 5 senior most employee in the company
        List<Employee> emp4 = list.stream()
                .sorted((e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()))
                .limit(5)
                .toList();

        System.out.println("5 senior most employee in the company :");
        System.out.println(emp4);
        System.out.println("------------------------");

        // *Find only the name the employee whose designation is manager
        Predicate<Employee> p1 = (e -> e.getDesignation() == Designation.MANAGER);

        List<String> emp5 = list.stream()
                .filter(p1)
                .map(e -> e.getName())
                .toList();

        System.out.print("MANAGERS : ");
        System.out.println(emp5);
        System.out.println("----------------------");

        // *Hike the salary by 20% for everyone except manager
        List<Employee> emp6 = list.stream()
                .filter(p1.negate())
                .toList();

        emp6.forEach(e -> e.setSalary((int) (e.getSalary() * 1.2)));

        System.out.println("Hike in salary for employee other than MANAGER :");
        System.out.println(emp6);
        System.out.println("----------------------");

        // *Find the total number of Employee
        System.out.println("Total Employee : " + list.size());

    }
}
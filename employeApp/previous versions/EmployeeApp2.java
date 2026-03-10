// * We have 3 types of employees Clerk, Programmer & Manager
// * Need to create 1 object of each type
// * The salary and designation are fixed for the given types 
// 	Clerk 		- 20000 & "CLERK"
// 	Programmer	- 50000 & "PROGRAMMER"
// 	Manager		- 100000 & "MANAGER"
// * The raise salary should now increment the salary of Clerk by 3000, Programmer by 10000 & Manager by 25000
// ---------------------
// 1. Create
// 2. Display
// 3. Raise Salary
// 4. Exit
// ---------------------
// Enter choice :- 

import java.util.Scanner;

class Employee {
   private String name, designation;
   private int age, salary;

   public Employee(int sal, String desig) {
      Scanner sc = new Scanner(System.in);
      System.out.println("enter name:");
      name = sc.nextLine();
      System.out.println("enter age:");
      age = sc.nextInt();
      salary = sal;
      designation = desig;
   }

   public void raiseSalary() {
      setSalary(3000);
   }

   public void setSalary(int increment) {
      salary += increment;
   }

   public void display() {
      System.out.println("Name: " + name);
      System.out.println("Age: " + age);
      System.out.println("Designation: " + designation);
      System.out.println("Salary: " + salary);
   }
}

class Clerk extends Employee {
   public Clerk() {
      super(20000, "CLERK");
   }
}

class Programmer extends Employee {
   public Programmer() {
      super(50000, "Programmer");
   }

   public void raiseSalary() {
      setSalary(10000);
   }
}

class Manager extends Employee {
   public Manager() {
      super(100000, "MANAGER");
   }

   public void raiseSalary() {
      setSalary(25000);
   }
}

public class EmployeeApp2 {
   public static void main(String[] args) {
      int choice=0;
      Employee emp1=null;
      Employee emp2=null;
      Employee emp3=null;
      Scanner sc=new Scanner(System.in);
      do{
         System.out.println("1. Create\n2. Display\n3. Raise Salary\n4. Exit\nEnter choice :- ");
         choice=sc.nextInt();
         switch(choice){
            case 1:
               emp1=new Clerk();
               emp2=new Programmer();
               emp3=new Manager();
               break;
            case 2:
               if(emp1!=null && emp2!=null && emp3!=null){
                  System.out.println("Clerk Details:");
                  emp1.display();
                  System.out.println("Programmer Details:");
                  emp2.display();
                  System.out.println("Manager Details:");
                  emp3.display();
               } else {
                  System.out.println("Please create employees first.");
               }
               break;
            case 3:
               if(emp1!=null && emp2!=null && emp3!=null){
                  emp1.raiseSalary();
                  emp2.raiseSalary();
                  emp3.raiseSalary();
                  System.out.println("Salaries raised successfully.");
               } else {
                  System.out.println("Please create employees first.");
               }
               break;
            case 4:
               System.out.println("Exiting...");
               break;
            default:
               System.out.println("Invalid choice. Please try again.");
         }
      } while(choice!=4);
      sc.close();
   }
}
import java.util.Scanner;

class Employee
{
	private String name;
	private int age;
	private int salary;
	private String designation;

	public Employee()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name : ");
		name = sc.next();
		System.out.print("Enter age : ");
		age = sc.nextInt();
		System.out.print("Enter salary : ");
		salary = sc.nextInt();
		System.out.print("Enter designation : ");
		designation = sc.next();
	}

	public void raiseSalary()
	{
		System.out.print("Enter amount to raise salary by : ");
		int amt = new Scanner(System.in).nextInt();
		salary += amt;
		System.out.println("Successfully incremented the salary of "+name+" by "+amt);
	}

	public void display()
	{
		System.out.println("Name : "+name);
		System.out.println("Age : "+age);
		System.out.println("Salary : "+salary);
		System.out.println("Designation : "+designation);
	}
}

public class EmpMain
{
	public static void main(String args[])
	{
		int ch = 0;
		Employee emp = null;
		do
		{
			System.out.println("---------------------------");
			System.out.println("   1.  Create ");
			System.out.println("   2.  Display ");
			System.out.println("   3.  Raise Salary ");
			System.out.println("   4.  Exit ");
			System.out.println("---------------------------");
			System.out.print("   Enter choice :-  ");
			ch = new Scanner(System.in).nextInt();
			switch(ch)
			{
				case 1 : emp = new Employee();
						break;
				case 2 : emp.display();
						break;
				case 3 : emp.raiseSalary();
						break;
			}
		}while(ch!=4);		
	}
}


/* -------- EMPLOYEE -------- */
public class Employee {

    private String name;
    private int age;
    private int salary;
    private Gender gender;
    private Designation designation;
    private String department;

    public Employee(String n, int a, int s, Gender g, Designation desig, String dept) {
        this.name = n;
        this.age = a;
        this.salary = s;
        this.gender = g;
        this.designation = desig;
        this.department = dept;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
    }

    public Designation getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender=" + gender +
                ", designation=" + designation +
                ", department='" + department + '\'' +
                '}';
    }
}

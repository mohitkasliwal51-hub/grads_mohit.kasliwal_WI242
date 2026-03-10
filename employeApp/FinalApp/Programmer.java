/* -------- PROGRAMMER -------- */
final class Programmer extends Employee {
    public Programmer() {
        super(50000, Designation.PROGRAMMER, 'P');
    }

    public void raiseSalary() {
        setSalary(10000);
    }
}


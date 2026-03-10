/* -------- MANAGER -------- */
final class Manager extends Employee {
    public Manager() {
        super(100000, Designation.MANAGER, 'M');
    }
    public void raiseSalary() {
        setSalary(25000);
    }
}


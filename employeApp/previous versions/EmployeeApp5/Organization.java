abstract class Organization {
    protected static int count = 0;

    public static void showEmployeeCount() {
        System.out.println("Total Employees Created : " + count);
    }
    public static int getEmployeeCount() {
        return count;
    }
}


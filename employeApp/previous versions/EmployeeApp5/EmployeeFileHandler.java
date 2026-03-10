// file handeling logic

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class EmployeeFileHandler {
    private static final String FOLDER = "assignment";
    private static final String FILE = "assignment/employees.csv";

    static {
        File dir = new File(FOLDER);
        if (!dir.exists())
            dir.mkdir();
    }

    public static void initializeFile() {
                try {
            // Ensure folder exists
            File dir = new File(FOLDER);
            if (!dir.exists()) dir.mkdir();

            // Truncate (or create) the CSV
            try (FileWriter fw = new FileWriter(FILE, false);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                // bw.write("EID,Name,Age,Salary,Designation");
                // bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error initializing file: " + ex.getMessage());
        }
    }

    public static void write(Employee e) {
        try (FileWriter fw = new FileWriter(FILE, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(e.toCSV());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    public static void read() {
        File file = new File(FILE);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No employee records found.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Employee Records:");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("ID : " + data[0].trim());
                System.out.println("Name : " + data[1].trim());
                System.out.println("Age : " + data[2].trim());
                System.out.println("Salary : " + data[3].trim());
                System.out.println("Designation : " + data[4].trim());
                System.out.println("----------------------------");
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    public static void updateEmployee(Employee emp) {
        File f = new File(FILE);
        File temp = new File("assignment/temp.csv");

        try (
                BufferedReader br = new BufferedReader(new FileReader(f));
                FileWriter fw = new FileWriter(temp)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(emp.getEid())) {
                    fw.write(emp.toCSV() + "\n");
                } else {
                    fw.write(line + "\n");
                }
        }
        } catch (IOException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }

        f.delete();
        temp.renameTo(f);
    }
}
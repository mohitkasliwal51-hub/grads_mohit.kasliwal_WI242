
// libraries for serialization
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// other libraries
import java.io.IOException;
import java.util.ArrayList;
class EmployeeFileHandler {
    private static final String FOLDER = "assignment";
    private static final String FILE = "assignment/employees.ser";

    static {
        File dir = new File(FOLDER);
        if (!dir.exists())
            dir.mkdir();
    }

    public static void serialize(Employee emp) {
        File file = new File(FILE);

        try {
            boolean append = file.exists();

            ObjectOutputStream oos = append
                    ? new AppendableObjectOutputStream(new FileOutputStream(file, true))
                    : new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(emp);
            oos.close();

            System.out.println("Employee object serialized successfully.");

        } catch (IOException e) {
            System.out.println("Unable to serialize employee: " + e.getMessage());
        }
    }

    public static ArrayList<Employee> deserializeAll() {
        ArrayList<Employee> list = new ArrayList<>();
        File file = new File("assignment/employees.ser");

        if (!file.exists()) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                Employee emp = (Employee) ois.readObject();
                list.add(emp);
            }

        } catch (EOFException e) {
            // end of file – normal
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unable to read serialized file: " + e.getMessage());
        }

        return list;
    }

    public static void rewriteAll(ArrayList<Employee> list) {
        File file = new File("assignment/employees.ser");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            for (Employee emp : list) {
                oos.writeObject(emp);
            }

        } catch (IOException e) {
            System.out.println("Unable to update serialized file: " + e.getMessage());
        }
    }

    public static int getSerializedCount() {
        ArrayList<Employee> list = deserializeAll();
        return list.size();
    }
}

class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset(); // prevents header corruption
    }
}

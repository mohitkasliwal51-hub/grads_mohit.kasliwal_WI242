//now store the data in a proper database using JDBC
// Employee table should have columns id, name, age, salary, designation
//should be able to add and delete records from the database
//also should be able to raise salary
// this operation should be done by selecting the employee by id
// add one more designation of CEO


class Employee
{
}
class Clerk extends Employee
{
}
class Programmer extends Employee
{
}
class Manager extends Employee
{
}
class CEO extends Employee
{
}
 
interface EmployeeDAO
{
	public boolean save(Employee e);
	public boolean delete(int id);
	public boolean update(Employee e);
	public List<Employee> retreive();
}
 
abstract class StorageFactory 
{
	public EmployeeDAO getStorage(String s)
	{
		case "file": return new EmployeeFileOperations();
		case "db" : return new EmployeeDBOperations();
		default : return null;
	}
}
class DBConnection
{
	private Connection con = null;
	private DBConnection()
	{
	}
	public static Connection getConnection(String dbname)
	{
		if(con==null);
		{
			Class.forName("");
			Connection con = DriverManager.getConnection("", "", "");
			return con;
		}
	}
}
 
class EmployeeDBOperations implements EmployeeDAO
{
	public boolean save(Employee e)
	{
		try
		{
			Connection con = DBConnection.getConnection("cassandra");
			PreparedStatement pstmt  = con.prepareStatement("insert into EMPLOYEE values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, e.getId());
			......
			pstmt.execute();
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}
	......
}
class EmployeFileOperations implements EmployeeDAO
{
	public boolean save(Employee e)
	{
		try
		{
			list.add(e);
		}
	}
}
class EmployeeCSVOperations implements EmployeeDAO
{
}
class EmployeeJSONOperations implements EmployeeDAO
{
}
class EmployeeXMLOperations implements EmployeeDAO
{
}
 
main()
{
	System.out.println("Enter your preferred storage (eg := 'file', 'db', 'csv', 'json') : ");
	String storage = br.readLine();
	EmployeeDAO dao = StorageFactory.getStorage(storage);
	case 1: 
	{
			Employee e = null;
			case 1 : e = new Clerk();
			case 2 : e = new Programmer();
			case 3 : e = new Manager();
			case 4 : e = CEO.getCEO();
			dao.save(e);
	}			
	case 2: 
	{
		dao.update(e);
	}			
	case 3: 
	{
			dao.delete(e.getId());
	}			
	case 4: 
	{
			List<Emp> list = dao.retrieve();
			display(list);
	}			
}
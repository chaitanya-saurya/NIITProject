package daoImpl;

import Config.JDBCConfiguration;
import Dao.EmployeeDao;
import Dao.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDaoImpl implements EmployeeDao {


        Connection conn=null;
        @Override
        public void getAllEmployees() {
            try {
                conn= JDBCConfiguration.getDBConnection();
                Statement stmt=conn.createStatement();
                ResultSet rst=stmt.executeQuery("select * from account2");
                if(rst!=null) {
                    Person emp=new Person();
                    while(rst.next()) {
                        emp.setuNAME(rst.getString(3));
                        emp.setFirstName(rst.getString(1));
                        emp.setLastName(rst.getString(2));
                        emp.setEmail(rst.getString(5));
                        emp.setuPass(rst.getString(4));
                        emp.setRole(rst.getString(8));
                        emp.setGender(rst.getString(7));
                        emp.setPhone(rst.getString(6));
                        System.out.println(emp);
                    }
                }
                conn.close();
            }
            catch(ClassNotFoundException e) {
                System.out.println(e.getMessage());

            }
            catch(SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    @Override
    public void addEmployee(Person emp) {

    }

    @Override
    public Person getEmployeeByUserName(String user) {
        return null;
    }


    @Override
    public void updateEmployee(Person emp) {

    }

    @Override
    public void deactivateEmployee(String user) {

    }
}

/*
Implementation in PCS Project:
// dao package
List<Employee> getAllEmployees();

// daoImpl package:
public List<Employee> getAllEmployees() {
		List<Employee> empList=null;
		try {
			conn=JDBCConnection.getDBConnection();
			Statement stmt=conn.createStatement();
			empList=new ArrayList<Employee>();
			ResultSet rst=stmt.executeQuery("select * from Employee");
			if(rst!=null) {
				Employee emp=new Employee();
				while(rst.next()) {
					emp.setEmpId(rst.getInt(1));
					emp.setFirstName(rst.getString(2));
					emp.setLastName(rst.getString(3));
					emp.setUserId(rst.getString(4));
					emp.setPassword(rst.getString(5));
					emp.setRole(rst.getString(6));
					emp.setGender(rst.getString(7));
					emp.setActive(rst.getString(8));
					//System.out.println(emp);
					empList.add(emp);
				}
			}
			conn.close();
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());

		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return empList;

	}

// service package:
public void getEmployees() {
		EmployeeDao empDao=new EmployeeDaoImpl();
		List<Employee> empList=empDao.getAllEmployees();
		Iterator<Employee> itr=empList.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

// main()
emp.getEmployees();

 */
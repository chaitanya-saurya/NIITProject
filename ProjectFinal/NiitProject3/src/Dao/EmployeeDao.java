package Dao;

public interface EmployeeDao {
        void getAllEmployees();
        void addEmployee(Person emp);
        Person getEmployeeByUserName(String user);
        void updateEmployee(Person emp);
        void deactivateEmployee(String user);
}


import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int EmployeeId;

    String Name;
    String Assignment;
    double Salary;
    String DepartmentName;


    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId=" + EmployeeId +
                ", Name='" + Name + '\'' +
                ", Assignment='" + Assignment + '\'' +
                ", Salary=" + Salary +
                ", DepartmentName='" + DepartmentName + '\'' +
                '}';
    }

    public Employee() {
    }

    public int getEmployeeId() {

        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAssignment() {
        return Assignment;
    }

    public void setAssignment(String assignment) {
        Assignment = assignment;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public Employee(String name, String assignment, double salary, String departmentName) {

        Name = name;
        Assignment = assignment;
        Salary = salary;
        DepartmentName = departmentName;
    }

    public void getDepHEad(EntityManager manager){
        // Task 1.
        TypedQuery<Employee> HeadEmployeesQuery = manager.createQuery("select e from Employee e where e.Assignment = 'Head'", Employee.class);
        List<Employee> HeadEmployees = HeadEmployeesQuery.getResultList();

        for (Employee headEmployee : HeadEmployees) {
            System.out.println("Head of " + headEmployee.DepartmentName + " department is " + headEmployee.Name);
        }
    }

    public void getStatistic(String depName, EntityManager manager){

        // Task 2.
        TypedQuery<Employee> DepartmentQuery = manager.createQuery("select e from Employee e where e.DepartmentName = :department "  , Employee.class);
        DepartmentQuery.setParameter("department", depName);
        List<Employee> Department = DepartmentQuery.getResultList();

        int JunCount = 0;
        int MidCount = 0;
        int SenCount = 0;
        for (Employee employee : Department) {
            switch (employee.Assignment){
                case "Junior": JunCount++; break;
                case "Middle": MidCount++; break;
                case "Senior": SenCount++; break;
            }
        }
        System.out.println("Juniors - " + JunCount);
        System.out.println("Middles - " + MidCount);
        System.out.println("Seniors - " + SenCount);
    }

    public void getAvgSalary(String depName, EntityManager manager){
        // Task 3.
        TypedQuery<Double> DepQuery = manager.createQuery("select e.Salary from Employee e where e.DepartmentName = :DepInp", Double.class);
        DepQuery.setParameter("DepInp", depName);
        List<Double> Salarys = DepQuery.getResultList();
        double Sum = 0;
        for (Double aDouble : Salarys) {
            Sum += aDouble;
        }
        System.out.println("The average salary of " + depName + " is " + Sum/Salarys.size());
    }

    public void getDepCount(String depName, EntityManager manager){
        // Task4.
        TypedQuery<Employee> DepartmentQuery = manager.createQuery("select e from Employee e where e.DepartmentName = :department "  , Employee.class);
        DepartmentQuery.setParameter("department", depName);
        List<Employee> Employees = DepartmentQuery.getResultList();
        System.out.println("Number of workers in this department: " + Employees.size());
    }

    public void globalSearch(String searchString, EntityManager manager){
        // Task 5.
        TypedQuery<Employee> SearchResultQuery = manager.createQuery("select e from Employee e where e.Name LIKE :search", Employee.class);
        SearchResultQuery.setParameter("search", "%" + searchString + "%");
        List<Employee> SearchResultList = SearchResultQuery.getResultList();

        for (Employee employee : SearchResultList) {
            System.out.println(employee.Name + " is working at " + employee.DepartmentName);
        }

    }
}

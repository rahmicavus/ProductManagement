package labs.pm.data;

public class Employee {
    private int id;
    String name;
    double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(int id, String name, double salary){
        this.id=id;
        this.name=name;
        this.salary=salary;
    }

    public void salaryIncrement(double increment){
        setSalary(salary+=salary*increment);
    }

    public Employee findEmployee(int empId){
        return new Employee(id,"id name",500);
    }
}

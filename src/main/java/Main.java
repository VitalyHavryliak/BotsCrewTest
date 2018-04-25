import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dbconfig");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        Employee employee = new Employee();

        //Easy way)
        /*manager.persist(new Employee("Adam", "Head", 3000, "BotsCrew"));
        manager.persist(new Employee("Victor", "Junior", 450, "Sombra"));
        manager.persist(new Employee("Leo", "Head", 3000, "Epam"));
        manager.persist(new Employee("Nick", "Middle", 1500, "Eleks"));
        manager.persist(new Employee("Rick", "Senior", 2000, "Intellias"));
        manager.persist(new Employee("Alex", "Middle", 1450, "Sombra"));
        manager.persist(new Employee("Bob", "Senior", 2350, "Sombra"));
        */

        boolean shouldContinue = true;

        while (shouldContinue) {

            System.out.println("1 - Add employee\n" +
                    "2 - Who is the head of department\n" +
                    "3 - Show statistics\n" +
                    "4 - Average salary\n" +
                    "5 - Number of employee\n" +
                    "6 - Global search\n" +
                    "7 - Exit");
            Scanner choice = new Scanner(System.in);

            switch (choice.nextInt()) {
                case 1: {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Input name");
                    String name = scanner.nextLine();
                    System.out.println("Input assignment");
                    String assignment = scanner.nextLine();
                    System.out.println("Input departure name ");
                    String depName = scanner.nextLine();
                    System.out.println("Input salary");
                    double salary = scanner.nextDouble();
                    manager.persist(new Employee(name, assignment, salary, depName));
                    manager.getTransaction().commit();
                    System.out.println("Added successfully");
                    break;
                }
                case 2: {
                    employee.getDepHEad(manager);
                    break;
                }
                case 3: {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Input department's name");
                    String depName = scanner.nextLine();
                    employee.getStatistic(depName, manager);
                    break;
                }
                case 4: {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Input department's name");
                    employee.getAvgSalary(scanner.nextLine(), manager);
                    break;
                }
                case 5: {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Input department's name");
                    employee.getDepCount(scanner.nextLine(), manager);
                    break;
                }
                case 6: {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Search by: ");
                    employee.globalSearch(scanner.nextLine(), manager);
                    break;
                }
                case 7:{
                    shouldContinue = false;
                    break;
                }
                default:
                    System.out.println("Wrong input!");
            }
        }




        manager.getTransaction().commit();
        manager.close();
        factory.close();

    }


}

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static HibernateUtil h = new HibernateUtil();

    public static void main(String[] args) throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException {




        play();
        h.closeEMF();
    }

    public static void play() {

        boolean quit = false;
        while (!quit) {
            showMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    boolean quit1 = false;
                    while (!quit1) {
                        createMenu();
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                System.out.println("Please enter user's details");
                                createEmployee();
                                break;
                            case 2:
                                System.out.println("Please enter phone details");
                                createPhone();
                                break;
                            case 3:
                                System.out.println("Please enter laptop details");
                                createLaptop();
                                break;
                            case 4:
                                System.out.println("Please enter car details");
                                createCar();
                                break;
                            case 0:
                                quit1 = true;
                                break;
                            default:
                                System.out.println("Wrong key !");
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean quit2 = false;
                    while (!quit2) {
                        findMenu();
                        int option1 = scanner.nextInt();
                        switch (option1) {
                            case 1:
                                System.out.println("Employees in db: ");
                                showEmployee();
                                break;
                            case 2:
                                System.out.println("Phones in db: ");
                                showPhones();
                                break;
                            case 3:
                                System.out.println("Laptops in db:");
                                showLaptops();
                                break;
                            case 4:
                                System.out.println("Cars in db:");
                                showCars();
                                break;
                            case 0:
                                quit2 = true;
                                break;
                            default:
                                System.out.println("Wrong key !");
                                break;
                        }
                    }
                    break;
                case 0:

                    quit = true;
                    break;
                default:
                    System.out.println("Wrong key !");
                    break;
            }
        }

    }

    public static void showMenu() {

        System.out.println("1 -> add objects in db\n" +
                "2 -> find objects in db\n" +
                "0 -> quit");

    }

    public static void createMenu() {
        System.out.println("  1 -> add employee\n" +
                "  2 -> add phone\n" +
                "  3 -> add laptop\n" +
                "  4 -> add car\n" +
                "  0 -> back to Menu");

    }

    public static void findMenu() {
        System.out.println("  1 -> show all employees\n" +
                "  2 -> show all phones\n" +
                "  3 -> show all laptops\n" +
                "  4 -> show all cars\n" +
                "  0 -> back to Menu");
    }

    public static void createEmployee() {
        Employee employee = new Employee();
        System.out.println("id:");
        employee.setId(scanner.nextInt());
        System.out.println("first name:");
        employee.setFirstName(scanner.next());
        System.out.println("last name: ");
        employee.setLastName(scanner.next());
        System.out.println("assign object to user ?\n" +
                "  press y or n ");
        char choice = scanner.next().charAt(0);

        if (choice == 'y') {
            assignObjects(employee);
        }
        System.out.println("ai ajuns aici");

        HibernateUtil h = new HibernateUtil();
        h.save(employee);
    }

    public static void assignmentMenu() {
        System.out.println("  1 -> phone assignment\n" +
                "  2 -> laptop assignment\n" +
                "  3 -> car assignment\n" +
                "  0 -> done assignment & save object ");
    }

    public static void assignObjects(Employee e) {
        boolean quit = false;
        while (!quit) {
            assignmentMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    showPhones();
                    System.out.println("assign phone by typing the primary key");
                    int p = scanner.nextInt();
                    Phone phone = h.findByKey(Phone.class, p);
                    e.setPhone(phone);
                    break;
                case 2:
                    showLaptops();
                    System.out.println("assign laptop by typing the primary key");
                    int l = scanner.nextInt();
                    Laptop laptop = h.findByKey(Laptop.class, l);
                    e.getLaptops().add(laptop);
                    break;
                case 3:
                    showCars();
                    System.out.println("assign car by typing the primary key");
                    int c = scanner.nextInt();
                    Car car = h.findByKey(Car.class,c);
                    e.getCars().add(car);
                    car.getEmployees().add(e);
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Wrong key !");
                    break;
            }
        }
    }

    public static void createPhone() {
        Phone phone = new Phone();
        System.out.println("phone id:");
        phone.setId(scanner.nextInt());
        System.out.println("phone brand: ");
        phone.setBrand(scanner.next());
        System.out.println("phone model:");
        phone.setModel(scanner.next());
        System.out.println("phone number: ");
        phone.setPhoneNumber(scanner.next());

        HibernateUtil h = new HibernateUtil();

        h.save(phone);
    }

    public static void createLaptop() {
        Laptop laptop = new Laptop();
        System.out.println("laptop id:");
        laptop.setId(scanner.nextInt());
        System.out.println("laptop brand:");
        laptop.setBrand(scanner.next());
        System.out.println("laptop model: ");
        laptop.setModel(scanner.next());

        HibernateUtil h = new HibernateUtil();

        h.save(laptop);
    }

    public static void createCar() {
        Car car = new Car();
        System.out.println("car id:");
        car.setId(scanner.nextInt());
        System.out.println("car brand:");
        car.setBrand(scanner.next());
        System.out.println("car model:");
        car.setModel(scanner.next());
        System.out.println("car license plate:");
        car.setLicensePlate(scanner.next());

        HibernateUtil h = new HibernateUtil();

        h.save(car);
    }

    public static void showEmployee() {

        EntityManager em = h.getEm();

        List<Employee> list = h.readAll(em, Employee.class);

        for (Employee e : list) {
            System.out.println(e);
        }

        em.close();
    }

    public static void showPhones() {

        EntityManager em = h.getEm();

        List<Phone> list = h.readAll(em, Phone.class);

        for (Phone p : list) {
            System.out.println(p);
        }

        h.closeEM();
    }

    public static void showLaptops() {

        EntityManager em = h.getEm();


        List<Laptop> list = h.readAll(em, Laptop.class);

        for (Laptop l : list) {
            System.out.println(l);
        }

        h.closeEM();
    }

    public static void showCars() {

        EntityManager em = h.getEm();

        List<Car> list = h.readAll(em, Car.class);

        for (Car c : list) {
            System.out.println(c);
        }

        h.closeEM();
    }

}

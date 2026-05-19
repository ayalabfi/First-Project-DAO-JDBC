package application;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.lang.classfile.instruction.SwitchCase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        DepartmentDAO departmentDAO = DAOFactory.createDepartamentoDAO();
        SellerDAO sellerDAO = DAOFactory.createSellerDAO();
        List<Department> listDepartment = new ArrayList<>();
        List<Seller> listSeller = new ArrayList<>();
        Department department = new Department();

        boolean loop = false;

        while (!loop) {

            int option = 0;


            System.out.println("""
                    Select the field:
                    0 - Exit
                    1 - Department
                    2 - Seller
                    """);
            System.out.print("Option: ");

            option = sc.nextInt();

            System.out.println();

            switch (option) {

                case 0:
                    System.out.println("Turn off system!");
                    loop = true;
                    break;

                case 1:
                    departmentFunctions(sc, departmentDAO, listDepartment, option);
                    break;

                case 2:
                    sellerFunctions(sc, sellerDAO, listSeller, option, department);
                    break;
                default:
                    System.out.println("Invalid option!\n");
                    break;
            }
        }
    }

    public static void departmentFunctions(Scanner keyboard, DepartmentDAO departmentDAO, List<Department> list, Integer option) {

        System.out.println("""
                Select the option:
                1 - Create new department
                2 - Change department name
                3 - Delete a department
                4 - Search department
                5 - Show Departments list
                """);
        System.out.print("Option: ");
        option = keyboard.nextInt();
        switch (option) {
            case 1:
                System.out.print("Enter new department name: ");
                keyboard.nextLine();
                String name = keyboard.nextLine();
                Department dep = new Department(null, name);
                departmentDAO.insert(dep);
                break;

            case 2:
                System.out.print("Enter the ID you want to change: ");
                int id = keyboard.nextInt();
                keyboard.nextLine();
                dep = departmentDAO.findById(id);
                System.out.print("Enter the new department name: ");
                dep.setName(keyboard.nextLine());
                departmentDAO.update(dep);
                break;

            case 3:
                System.out.print("Enter the ID you want to delete: ");
                id = keyboard.nextInt();
                departmentDAO.deleteById(id);
                break;

            case 4:
                System.out.print("Enter the ID you are looking for: ");
                id = keyboard.nextInt();
                Department department = departmentDAO.findById(id);
                System.out.println("\n" + department + "\n");
                break;

            case 5:
                list = departmentDAO.findAll();

                System.out.println();

                for (Department obj : list) {
                    System.out.println(obj);
                }

                System.out.println();
                break;

            default:
                System.out.println("Opção digitada invalida!");
        }
    }

    public static void sellerFunctions(Scanner keyboard, SellerDAO sellerDAO, List<Seller> list, Integer option, Department department) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("""
                Select the option:
                1 - Create new seller
                2 - Change seller information
                3 - Delete a seller
                4 - Search seller
                5 - Show sellers list
                """);
        System.out.print("Option: ");
        option = keyboard.nextInt();

        switch (option) {
            case 1:
                keyboard.nextLine();
                System.out.println("Enter the seller's details: ");
                System.out.print("Name: ");
                String name = keyboard.nextLine();
                System.out.print("E-mail: ");
                String email = keyboard.nextLine();
                System.out.print("Salary: ");
                double baseSalary = keyboard.nextDouble();
                System.out.print("Department: ");
                int depId = keyboard.nextInt();
                keyboard.nextLine();
                department = new Department(depId, null);
                System.out.print("Date of birth (dd/mm/yyyy): ");
                Date birthDate = sdf.parse(keyboard.nextLine());
                Seller seller = new Seller(null, name, email, birthDate, baseSalary, department);
                sellerDAO.insert(seller);
                break;

            case 2:
                System.out.print("Enter the ID you want to change: ");
                int id = keyboard.nextInt();
                keyboard.nextLine();
                seller = sellerDAO.findById(id);
                System.out.println("""
                        Enter the information you want to change:
                        1 - Name
                        2 - E-mail
                        3 - BirthDate
                        4 - baseSalary
                        5 - Department
                        """);
                System.out.print("Option: ");
                int updCode = keyboard.nextInt();
                if (updCode == 1) {
                    System.out.print("Enter the new seller name: ");
                    keyboard.nextLine();
                    seller.setName(keyboard.nextLine());
                    sellerDAO.update(seller);
                } else if (updCode == 2) {
                    System.out.print("Enter the new seller e-mail: ");
                    keyboard.nextLine();
                    seller.setEmail(keyboard.nextLine());
                    sellerDAO.update(seller);
                } else if (updCode == 3) {
                    System.out.print("Enter the new seller birth date (dd/mm/yyyy): ");
                    keyboard.nextLine();
                    seller.setBirthDate(sdf.parse(keyboard.nextLine()));
                    sellerDAO.update(seller);
                } else if (updCode == 4) {
                    System.out.print("Enter the new seller salary: ");
                    keyboard.nextLine();
                    seller.setBaseSalary(keyboard.nextDouble());
                    sellerDAO.update(seller);
                } else if (updCode == 5) {
                    System.out.print("Enter the new seller department: ");
                    keyboard.nextLine();
                    int updDepId = keyboard.nextInt();
                    Department updDepartment = new Department(updDepId, null);
                    seller.setDepartment(updDepartment);
                    sellerDAO.update(seller);
                } else {
                    System.out.println("Invalid option!");
                }
                break;

            case 3:
                System.out.print("Enter the ID you want to delete: ");
                id = keyboard.nextInt();
                sellerDAO.deleteById(id);
                break;

            case 4:
                System.out.print("Enter the ID you are looking for: ");
                id = keyboard.nextInt();
                seller = sellerDAO.findById(id);
                System.out.println("\n" + seller + "\n");
                break;

            case 5:
                list = sellerDAO.findAll();

                System.out.println();

                for (Seller obj : list) {
                    System.out.println(obj);
                }

                System.out.println();

                break;

            default:
                System.out.println("Opção digitada invalida!");
        }
    }


}

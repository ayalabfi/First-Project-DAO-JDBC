package application;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDAO departmentDAO = DAOFactory.createDepartamentoDAO();

        List<Department> list = new ArrayList<>();

        System.out.println("=== Test 1: department findById ===");

        System.out.print("Enter the ID you are looking for: ");
        int id = sc.nextInt();

        Department dep = departmentDAO.findById(id);

        System.out.println(dep);

        System.out.println("=== Test 2: department findAll ===");

        list = departmentDAO.findAll();

        for (Department obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== Test 3: department delete ===");

        System.out.print("Enter the ID you want to delete: ");
        id = sc.nextInt();

        departmentDAO.deleteById(id);

        System.out.println("Deleted successfuly!");

        System.out.println("=== Test 4: department update ===");

        System.out.print("Enter the ID you want to change: ");
        id = sc.nextInt();
        dep = departmentDAO.findById(id);
        sc.nextLine();
        System.out.print("Enter the new department name: ");
        String name = sc.nextLine();
        dep.setName(name);
        departmentDAO.update(dep);
        System.out.println("Updated successfuly!");

        System.out.println("=== Test 5: department insert ===");

        System.out.print("Enter the name of the new department: ");
        name = sc.nextLine();
        Department newDepartment = new Department(null, name);
        departmentDAO.insert(newDepartment);
        System.out.println("Sucessfuly new department insert! ID: " + newDepartment.getId());
    }
}

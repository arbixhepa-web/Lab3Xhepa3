/**
 * Project: Lab 3 Database CRUD
 * Purpose Details: Customer model for MySQL and MongoDB operations
 * Course: IST
 * Author: Arbi Xhepa
 * Date Developed: 06/01/2026
 * Last Date Changed: 06/01/2026
 * Rev: 1.0
 */


public class Main {
    public static void main(String[] args) {

        MySQLCRUD mysql = new MySQLCRUD();
        MongoCRUD mongo = new MongoCRUD();

        System.out.println("=== RESET ===");

        // MUST BE FIRST LINE OF LOGIC
        mysql.reset();
        mongo.reset();

        System.out.println("\n=== CREATE ===");

        Customer c1 = new Customer(1, "John", "Doe", "john@email.com", "12345");
        Customer c2 = new Customer(2, "Jane", "Smith", "jane@email.com", "54321");
        Customer c3 = new Customer(3, "Mike", "Brown", "mike@email.com", "99999");

        mysql.create(c1);
        mysql.create(c2);
        mysql.create(c3);

        mongo.create(c1);
        mongo.create(c2);
        mongo.create(c3);

        System.out.println("\n=== READ ===");

        mysql.read();
        mongo.read();
    }
}
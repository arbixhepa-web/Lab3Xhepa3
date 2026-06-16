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

        Customer c1 = new Customer(1, "John", "Smith", "john@email.com", "2151111111");
        Customer c2 = new Customer(2, "Mary", "Jones", "mary@email.com", "2152222222");
        Customer c3 = new Customer(3, "Bob", "Wilson", "bob@email.com", "2153333333");

        MySQLCRUD mysql = new MySQLCRUD();
        MongoCRUD mongo = new MongoCRUD();

        System.out.println("=== CREATE ===");
        mysql.create(c1);
        mysql.create(c2);
        mysql.create(c3);

        mongo.create(c1);
        mongo.create(c2);
        mongo.create(c3);

        System.out.println("\n=== READ ===");
        mysql.read();
        mongo.read();

        System.out.println("\n=== UPDATE ===");
        c1.setEmail("updated@email.com");
        mysql.update(c1);
        mongo.update(c1);

        System.out.println("\n=== DELETE ===");
        mysql.delete(3);
        mongo.delete(3);
    }
}
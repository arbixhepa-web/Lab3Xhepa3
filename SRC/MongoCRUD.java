import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoCRUD {

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> col;

    public MongoCRUD() {
        try {
            client = MongoClients.create("mongodb://localhost:27017");
            db = client.getDatabase("lab3");
            col = db.getCollection("customers");

            System.out.println("Mongo Connection SUCCESS");

        } catch (Exception e) {
            System.out.println("Mongo Connection ERROR: " + e.getMessage());
        }
    }

    // CREATE
    public void create(Customer c) {
        try {
            Document doc = new Document("id", c.getId())
                    .append("firstName", c.getFirstName())
                    .append("lastName", c.getLastName())
                    .append("email", c.getEmail())
                    .append("phone", c.getPhone());

            col.insertOne(doc);
            System.out.println("Mongo INSERT SUCCESS: " + c.getId());

        } catch (Exception e) {
            System.out.println("Mongo INSERT ERROR: " + e.getMessage());
        }
    }

    // READ
    public void read() {
        try {
            System.out.println("\n--- Mongo READ ---");

            for (Document d : col.find()) {
                System.out.println(
                        d.getInteger("id") + " " +
                                d.getString("firstName") + " " +
                                d.getString("lastName") + " " +
                                d.getString("email") + " " +
                                d.getString("phone")
                );
            }

        } catch (Exception e) {
            System.out.println("Mongo READ ERROR: " + e.getMessage());
        }
    }

    // UPDATE
    public void update(Customer c) {
        try {
            col.updateOne(
                    eq("id", c.getId()),
                    set("email", c.getEmail())
            );

            System.out.println("Mongo UPDATE SUCCESS: " + c.getId());

        } catch (Exception e) {
            System.out.println("Mongo UPDATE ERROR: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(int id) {
        try {
            col.deleteOne(eq("id", id));
            System.out.println("Mongo DELETE SUCCESS: " + id);

        } catch (Exception e) {
            System.out.println("Mongo DELETE ERROR: " + e.getMessage());
        }
    }

    // RESET (IMPORTANT FOR LAB RUNS)
    public void reset() {
        try {
            col.deleteMany(new Document());
            System.out.println("Mongo RESET DONE");

        } catch (Exception e) {
            System.out.println("Mongo RESET ERROR: " + e.getMessage());
        }
    }
}
import com.mongodb.client.*;
import org.bson.Document;

public class MongoCRUD {

    private final MongoClient client =
            MongoClients.create("mongodb://localhost:27017");

    private final MongoDatabase db = client.getDatabase("lab3");
    private final MongoCollection<Document> col =
            db.getCollection("customer");

    // CREATE
    public void create(Customer c) {
        Document doc = new Document("id", c.getId())
                .append("firstName", c.getFirstName())
                .append("lastName", c.getLastName())
                .append("email", c.getEmail())
                .append("phone", c.getPhone());

        col.insertOne(doc);
        System.out.println("Mongo Inserted: " + c.getId());
    }

    // READ
    public void read() {
        System.out.println("\n--- Mongo Data ---");

        for (Document d : col.find()) {
            System.out.println(
                    d.getInteger("id") + " " +
                            d.getString("firstName") + " " +
                            d.getString("lastName") + " " +
                            d.getString("email") + " " +
                            d.getString("phone")
            );
        }
    }

    // UPDATE
    public void update(Customer c) {
        col.updateOne(
                new Document("id", c.getId()),
                new Document("$set",
                        new Document("email", c.getEmail()))
        );

        System.out.println("Mongo Updated: " + c.getId());
    }

    // DELETE
    public void delete(int id) {
        col.deleteOne(new Document("id", id));
        System.out.println("Mongo Deleted: " + id);
    }
}
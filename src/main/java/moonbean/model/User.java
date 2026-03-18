package moonbean.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class User {

    @BsonId
    private ObjectId id; // Changed to ObjectId for MongoDB

    private String name;
    private String email;
    private String password;
    private String role; // From ERD: Admin/Customer/Guest
    private String address; // From ERD
    private String phoneNumber; // From ERD

    // 1. Empty constructor (Strictly required by MongoDB)
    public User() {}

    // 2. Constructor for creating a new user (App usage)
    public User(String name, String email, String password, String role, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // --- Getters & Setters ---
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
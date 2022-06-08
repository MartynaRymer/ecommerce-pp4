package pl.mrymer.crm;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    String id;
    String email;

    Client() { }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

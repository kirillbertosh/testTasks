package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private String value;

    public Condition() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

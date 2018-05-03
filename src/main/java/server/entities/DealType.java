package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "deal_types")
public class DealType {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 30)
    private String name;

    public DealType() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DealType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

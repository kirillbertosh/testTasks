package server.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private Date date;
    @Column
    private String name;
    @Column
    private double sum;
    @OneToOne
    private User seller;
    @OneToMany
    @JoinTable(name = "deal_user", joinColumns
            = @JoinColumn(name = "deal_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"))
    private List<User> customers;

    public Deal() {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List<User> getCustomers() {
        return customers;
    }

    public void setCustomers(List<User> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deal{");
        sb.append("id=").append(id);
        sb.append(", date='").append(date).append('\'');
        sb.append(", sum='").append(sum).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", seller='").append(seller).append('\'');
        sb.append(", customers='").append(customers).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Deal deal = (Deal) obj;
        return id == deal.id &&
                Objects.equals(name, deal.name) &&
                Objects.equals(date, deal.date) &&
                Objects.equals(sum, deal.sum) &&
                Objects.equals(customers, deal.customers) &&
                Objects.equals(seller, deal.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, name, sum, seller, customers);
    }
}

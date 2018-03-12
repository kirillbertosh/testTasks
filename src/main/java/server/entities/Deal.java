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
    @Column
    private long sellerId;

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

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deal{");
        sb.append("id=").append(id);
        sb.append(", date='").append(date).append('\'');
        sb.append(", sum='").append(sum).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", sellerId='").append(sellerId).append('\'');
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
                Objects.equals(sellerId, deal.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, name, sum, sellerId);
    }
}

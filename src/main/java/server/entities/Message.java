package server.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private Date date;
    @Column
    private Time time;
    @Column
    private String message;
    @OneToOne
    private User author;

    public Message() {

    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", message='" + message + '\'' +
                ", author=" + author +
                '}';
    }
}

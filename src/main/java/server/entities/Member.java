package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private User user;
    @OneToOne
    private Role role;

    public Member() {

    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}

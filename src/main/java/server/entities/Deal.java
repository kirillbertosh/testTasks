package server.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String description;
    @OneToOne
    private DealType type;
    @OneToOne
    private Chat chat;
    @OneToMany
    @JoinTable(name = "deal_member", joinColumns
            = @JoinColumn(name = "deal_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id",
                    referencedColumnName = "id"))
    private Set<Member> members;
    @OneToMany
    @JoinTable(name = "deal_attachment", joinColumns
            = @JoinColumn(name = "deal_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id",
                    referencedColumnName = "id"))
    private Set<Attachment> attachments;
    @OneToMany
    @JoinTable(name = "deal_condition", joinColumns
            = @JoinColumn(name = "deal_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id",
                    referencedColumnName = "id"))
    private Set<Condition> conditions;

    public Deal() {

    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", chat=" + chat +
                ", members=" + members +
                ", attachments=" + attachments +
                ", conditions=" + conditions +
                '}';
    }
}

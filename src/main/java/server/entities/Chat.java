package server.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    @JoinTable(name = "chat_message", joinColumns
            = @JoinColumn(name = "chat_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "message_id",
                    referencedColumnName = "id"))
    private Set<Message> messages;
    @OneToMany
    @JoinTable(name = "chat_attachment", joinColumns
            = @JoinColumn(name = "chat_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id",
                    referencedColumnName = "id"))
    private Set<Attachment> attachments;

    public Chat() {

    }

    public long getId() {
        return id;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", messages=" + messages +
                ", attachments=" + attachments +
                '}';
    }
}

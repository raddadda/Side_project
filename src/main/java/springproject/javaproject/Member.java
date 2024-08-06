package springproject.javaproject;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Member{
    @Id
    @Column(length=16)
    private UUID id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String nickname;

    @PrePersist
    public void createId(){
        this.id = UuidCreator.getTimeOrdered();
    }
}
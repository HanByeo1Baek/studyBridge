package com.green.acamatch.entity.joinClass;

import com.green.acamatch.entity.acaClass.AcaClass;
import com.green.acamatch.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "joinClass")
public class JoinClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joinClassId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "class_id", nullable = false)
    private AcaClass classId;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int certification;

    @Column(nullable = false)
    private LocalDate registrationDate;
}

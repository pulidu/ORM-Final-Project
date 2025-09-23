package lk.ijse.orm_final.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String studentId;

    private String name;
    private String address;
    private Long tel;
    private Date registrationDate;
    private int someInt;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id", referencedColumnName = "programId")
    )
    private List<course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    // Constructors
    public Student(String studentId, String name, String address, Long tel, Date registrationDate) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.registrationDate = registrationDate;
        this.someInt = 0;
        this.courses = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public Student(String studentId) {
        this.studentId = studentId;
        this.name = "";
        this.address = "";
        this.tel = 0L;
        this.registrationDate = new Date(System.currentTimeMillis());
        this.someInt = 0;
        this.courses = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.payments = new ArrayList<>();
    }
}
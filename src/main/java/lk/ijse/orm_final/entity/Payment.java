package lk.ijse.orm_final.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {


    @Id
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    private String status; // COMPLETED, PENDING, FAILEDD



    @ManyToOne
    @JoinColumn(name = "programId")
    private course program;
}
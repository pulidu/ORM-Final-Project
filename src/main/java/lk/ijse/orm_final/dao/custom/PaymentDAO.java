package lk.ijse.orm_final.dao.custom;

import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.entity.Payment;

import java.util.List;

public interface PaymentDAO extends SuperDAO {
    boolean save(Payment payment);

    boolean update(Payment payment);

    boolean delete(String id);

    Payment findById(String id);

    List<Payment> findAll();

    String generatePaymentId();
}
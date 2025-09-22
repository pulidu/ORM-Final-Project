module lk.ijse.orm_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.naming;

    requires jbcrypt;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires static lombok;

    exports lk.ijse.orm_final;

    opens lk.ijse.orm_final.controller to javafx.fxml;
    opens lk.ijse.orm_final to javafx.fxml;

    // Hibernate entity
    opens lk.ijse.orm_final.entity to org.hibernate.orm.core;

    // TableView Tm classes
    opens lk.ijse.orm_final.tdm to javafx.base;
}
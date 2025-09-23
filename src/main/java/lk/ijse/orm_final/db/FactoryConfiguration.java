package lk.ijse.orm_final.db;


import lk.ijse.orm_final.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            // 🔹 Load properties from resources folder
            Properties properties = new Properties();
            properties.load(
                    getClass().getClassLoader().getResourceAsStream("hibernate.properties")
            );

            // 🔹 Hibernate Configuration
            Configuration configuration = new Configuration();
            configuration.setProperties(properties)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(course.class)
                    .addAnnotatedClass(Instructor.class)
                    .addAnnotatedClass(Lesson.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Student.class);

            // 🔹 Build SessionFactory
            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("✅ Hibernate SessionFactory created successfully, DB is ready!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Hibernate configuration failed", e);
        }
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

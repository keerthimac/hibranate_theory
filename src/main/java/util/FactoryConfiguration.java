package util;

import entity.Licence;
import entity.NicEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.StudentEntity;

public class FactoryConfiguration {
    private static FactoryConfiguration instance;
    private SessionFactory factory;

    private FactoryConfiguration() {
        Configuration configure = new Configuration().configure().addAnnotatedClass(StudentEntity.class).addAnnotatedClass(Licence.class).addAnnotatedClass(NicEntity.class);
        factory = configure.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return instance == null ? instance = new FactoryConfiguration() : instance;
    }

    public Session getSession() {
        return factory.openSession();
    }
}
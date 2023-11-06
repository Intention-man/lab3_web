package com.app.server.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Properties;

public class JPAUtil {

    private static final EntityManagerFactory factory;

    static {
        try {
            Properties info = new Properties();
            info.load(JPAUtil.class.getClassLoader().getResourceAsStream("/db.cfg"));
            factory = Persistence.createEntityManagerFactory("default", info);
        } catch (Throwable ex) {
            System.err.println("Something went wrong during initializing EclipseLink: " + ex);
            throw new ExceptionInInitializerError();
        }
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }
//    private static final SessionFactory factory;
//
//    static {
//        try {
//            Properties info = new Properties();
//            info.load(HibernateUtil.class.getClassLoader().getResourceAsStream("/db.cfg"));
//            factory = new Configuration().configure()
//                    .setProperty(AvailableSettings.USER,
//                            info.getProperty("user"))
//                    .setProperty(AvailableSettings.PASS,
//                            info.getProperty("password"))
//                    .addAnnotatedClass(ResultEntity.class)
//                    .buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Something went wrong during initializing Hibernate: " + ex);
//            throw new ExceptionInInitializerError();
//        }
//    }
//
//    public static SessionFactory getFactory() {
//        return factory;
//    }
}

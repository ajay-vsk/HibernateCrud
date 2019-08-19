package com.telusko.hbm1;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 

 
public class HibernateUtil {
 
    private static SessionFactory sessionFactory = null;
    static Session retSession=null;
 
    static {
        try{
            loadSessionFactory();
        }catch(Exception e){
            System.err.println("Exception while initializing hibernate util.. ");
            e.printStackTrace();
        }
    }
 
    public static void loadSessionFactory(){
 
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Employee.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(srvcReg);
    }
 
    public static Session getSession() throws HibernateException {
 
        
            try {
                retSession=sessionFactory.openSession();
            }catch(Throwable t){
            System.err.println("Exception while getting session.. ");
            t.printStackTrace();
            }
            if(retSession == null) {
                System.err.println("session is discovered null");
            }
 
            return retSession;
    }
}
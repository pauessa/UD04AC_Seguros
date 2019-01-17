/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Calendar;
import model.Direccion;
import model.Seguro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pauessa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SessionFactory sessionfactory;
        Configuration config = new Configuration();
        config.configure();
        config.addClass(Seguro.class);
        sessionfactory = config.buildSessionFactory();

        //CREAMOS UN OBJETO
        Direccion direccion = new Direccion(11, "Calle Reina", 5, "Xàtiva", "Valencia");
        Seguro seguro = new Seguro(55, "1111A", "Manola", "García", "Pérez", 40, 0, Calendar.getInstance().getTime());
        seguro.setDireccion(direccion);

        //CREAR UNA SESION
        Session session = sessionfactory.openSession();
        session.beginTransaction();

        //GUARDAR OBJETO
        session.save(seguro);

        //ACTUALIZAR
        seguro = new Seguro(52, "11111M", "Antonia", "García", "Pérez", 50, 6, Calendar.getInstance().getTime());
        //session.update(seguro);

        //BORRAR
        //session.delete(seguro);
        //LEER
        Seguro s = (Seguro) session.get(Seguro.class, 50);
        System.out.println(s);

        //CERRAR CONEXIÓN
        session.getTransaction().commit();
        session.close();
        sessionfactory.close();
    }

}

package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class  Util  {

        private static final String PASSWORD_KEY = "db.password";
        private static final String USERNAME_KEY = "db.username";
        private static final String URL_KEY = "db.url";

        static {
            loadDriver();
        }

        private Util() {
        }

        public static Connection openConnection() throws SQLException {

                return DriverManager.getConnection(
                        PropertiesUtil.get(URL_KEY),
                        PropertiesUtil.get(USERNAME_KEY),
                        PropertiesUtil.get(PASSWORD_KEY)
                );
        }

        private static void loadDriver() {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    private static Configuration configuration = new Configuration().addAnnotatedClass(User.class);
    public static SessionFactory getSessionFactory() {
        return configuration.buildSessionFactory();
    }
}
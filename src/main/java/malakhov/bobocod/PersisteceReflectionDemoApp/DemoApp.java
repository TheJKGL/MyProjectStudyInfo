package malakhov.bobocod.PersisteceReflectionDemoApp;

import malakhov.bobocod.PersisteceReflectionDemoApp.entity.Note;
import malakhov.bobocod.PersisteceReflectionDemoApp.entity.Person;
import malakhov.bobocod.PersisteceReflectionDemoApp.orm.Session;
import malakhov.bobocod.PersisteceReflectionDemoApp.orm.SessionImpl;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DemoApp {
    public static void main(String[] args) {
        var dataSource = initializeDataSource();
        Session session = new SessionImpl(dataSource);

        Person person = session.find(Person.class, 4);
        System.out.println(person);
        Note note = session.find(Note.class, 6);
        System.out.println(note);
    }

    private static DataSource initializeDataSource() {
        var dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:demo;INIT=runscript from 'classpath:db/init.sql'");
        dataSource.setUser("sa");
        return dataSource;
    }
}

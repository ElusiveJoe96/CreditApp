package ru.axiomatika.CreditApp.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.axiomatika.CreditApp.model.Client;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientDAO {

    private final SessionFactory sessionFactory;

    public void save(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.save(client);
        } catch (Exception e) {
            throw e;
        }
    }

    public Client findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        }
    }


    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
            }
        }
    }


    public List<Client> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Client", Client.class)
                    .list();
        }
    }

    public List<Client> findByPhoneNumber(String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client WHERE phoneNumber = :phone", Client.class)
                    .setParameter("phone", phoneNumber)
                    .list();
        }
    }

    public List<Client> findByFIO(String firstName, String lastName, String middleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client WHERE firstName = :firstName " +
                            "AND lastName = :lastName AND middleName = :middleName", Client.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .setParameter("middleName", middleName)
                    .list();
        }
    }

    public List<Client> findByPassportData(String passportData) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client WHERE passportData = :passportData",
                            Client.class)
                    .setParameter("passportData", passportData)
                    .list();
        }
    }


}

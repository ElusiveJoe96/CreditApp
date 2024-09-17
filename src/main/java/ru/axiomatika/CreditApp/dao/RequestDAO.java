package ru.axiomatika.CreditApp.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.axiomatika.CreditApp.model.Request;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestDAO {

    private final SessionFactory sessionFactory;

    public void save(Request request) {
        try (Session session = sessionFactory.openSession()) {
            session.save(request);
        } catch (Exception e) {
            throw e;
        }
    }


    public Request findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Request.class, id);
        }
    }

    public List<Request> findAll() {
        return currentSession().createQuery("FROM Request", Request.class).list();
    }


    public List<Request> findByStatus(Boolean status) {
        return currentSession().createQuery("FROM Request WHERE approvalStatus = :status", Request.class)
                .setParameter("status", status)
                .list();
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }
}

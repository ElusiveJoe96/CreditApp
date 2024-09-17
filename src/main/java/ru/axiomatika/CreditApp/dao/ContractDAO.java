package ru.axiomatika.CreditApp.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.axiomatika.CreditApp.model.Contract;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractDAO {

    private final SessionFactory sessionFactory;

    public void save(Contract contract) {
        try (Session session = sessionFactory.openSession()) {
            session.save(contract);
        } catch (Exception e) {
            throw e;
        }
    }

    public void update(Contract contract) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(contract);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public Contract findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Contract.class, id);
        }
    }

    public List<Contract> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Contract", Contract.class).list();
        }
    }

    public List<Contract> findSignedContracts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Contract WHERE signingStatus = true",
                    Contract.class).list();
        }
    }
}

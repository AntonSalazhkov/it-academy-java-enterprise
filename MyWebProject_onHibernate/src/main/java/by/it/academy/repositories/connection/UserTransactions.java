package by.it.academy.repositories.connection;

import by.it.academy.entities.user.User;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Методы соединения с базой данных через JPA.
 * Обработка сущности User.
 */

@Log4j
public class UserTransactions {

    private EntityManager entityManager;


    public UserTransactions() {
    }

    public UserTransactions(HttpServletRequest req) {
        entityManager = (EntityManager) req.getSession().getAttribute("sessionJPA");
    }


    /**
     * Получение List пользователей из базы данных.
     */
    public List<User> getUsersByJPA() {

        List<User> users = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();

            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> userRoot = userCriteriaQuery.from(User.class);

            userCriteriaQuery.select(userRoot);
            users = entityManager.createQuery(userCriteriaQuery).getResultList();

            entityManager.getTransaction().commit();

        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Get users in database: " + e);
        }
        return users;
    }


    /**
     * Добавление нового пользователя в базу данных.
     */
    public boolean addUserByJPA(User user) {

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(user);

            entityManager.getTransaction().commit();
            return true;

        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Add user in database: " + e);
            return false;
        }
    }


    /**
     * Обновление пользователя в базе данных.
     */
    public boolean updateUserByJPA(User user) {

        try {
            entityManager.getTransaction().begin();

            entityManager.merge(user);

            entityManager.getTransaction().commit();
            return true;

        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            log.info("Update user in database: " + e);
            return false;
        }
    }
}

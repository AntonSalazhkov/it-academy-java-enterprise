package by.it.academy.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Фабрика создания Entity Manager
 */

public class JPAUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = buildEntityManager();

    private static EntityManagerFactory buildEntityManager() {
        final EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPAConnection");

        return entityManagerFactory;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}

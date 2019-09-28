package dao;

import hibernate.util.HibernateUtil;

import javax.persistence.EntityManager;

public class AbstractDAO {
    final HibernateUtil hibernateUtil = HibernateUtil.getInstance();
    final EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
}
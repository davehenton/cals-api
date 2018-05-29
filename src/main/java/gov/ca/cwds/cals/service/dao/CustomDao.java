package gov.ca.cwds.cals.service.dao;

import static java.util.Objects.requireNonNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Alex Serbin
 */
public abstract class CustomDao {

  private final SessionFactory sessionFactory;

  public CustomDao(SessionFactory sessionFactory) {
    this.sessionFactory = requireNonNull(sessionFactory);
  }

  protected Session currentSession() {
    return sessionFactory.getCurrentSession();
  }
}

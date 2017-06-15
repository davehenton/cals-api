package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.IPlacementHomeDao;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPlacementHome;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedPlacementHomeDao extends BaseDaoImpl<ReplicatedPlacementHome> implements
        IPlacementHomeDao<ReplicatedPlacementHome> {

  /**
   * Constructor
   *
   * @param sessionFactory The sessionFactory
   */
  @Inject
  public ReplicatedPlacementHomeDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public ReplicatedPlacementHome findByParameterObject(FacilityParameterObject parameterObject) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<ReplicatedPlacementHome> stream(FacilityParameterObject parameterObject) {
    Session session = this.getSessionFactory().getCurrentSession();
    Class<ReplicatedPlacementHome> entityClass = this.getEntityClass();

    Query<ReplicatedPlacementHome> query = session
            .createNamedQuery(entityClass.getSimpleName() + ".findUpdated", entityClass)
            .setParameter(0, parameterObject.getAfter())
            .setReadOnly(true);
    ScrollableResults results = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
    return StreamSupport
            .stream((ReplicatedPlacementHomeDao.this.iterableScrollableResults(results)).spliterator(), false);
  }

  private Iterable<ReplicatedPlacementHome> iterableScrollableResults(ScrollableResults scrollableResults) {
    return () -> new ScrollableResultsIterator(scrollableResults);
  }

  private static class ScrollableResultsIterator implements Iterator<ReplicatedPlacementHome> {
    private ReplicatedPlacementHome next;
    private ScrollableResults scrollableResults;

    ScrollableResultsIterator(ScrollableResults scrollableResults) {
      this.scrollableResults = scrollableResults;
      moveForward();
    }

    @Override
    public boolean hasNext() {
      return next != null;
    }

    @Override
    public ReplicatedPlacementHome next() {
      if (next != null) {
        ReplicatedPlacementHome result = next;
        moveForward();
        return result;
      } else {
        throw new NoSuchElementException();
      }
    }

    private void moveForward() {
      if (scrollableResults.next()) {
        next = (ReplicatedPlacementHome) scrollableResults.get(0);
      } else {
        next = null;
      }
    }
  }
}

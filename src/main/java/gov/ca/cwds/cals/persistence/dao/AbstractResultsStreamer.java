package gov.ca.cwds.cals.persistence.dao;

import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * @author CWDS TPT-2
 */
public abstract class AbstractResultsStreamer<T extends PersistentObject> {

  private BaseDaoImpl<T> dao;
  private QueryCreator<T> queryCreator;

  AbstractResultsStreamer(BaseDaoImpl<T> dao, QueryCreator<T> queryCreator) {
    this.dao = dao;
    this.queryCreator = queryCreator;
  }

  protected abstract T getResult(ScrollableResults scrollableResults);

  public Stream<T> createStream() {
    Session session = dao.getSessionFactory().getCurrentSession();
    Class<T> entityClass = dao.getEntityClass();
    Query<T> query = queryCreator.createQuery(session, entityClass);
    ScrollableResults results = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
    return StreamSupport.stream(newIterable(results).spliterator(), false);
  }

  private Iterable<T> newIterable(ScrollableResults scrollableResults) {
    return () -> new ScrollableResultsIterator<T>(scrollableResults) {
      @Override
      protected T extractResult(ScrollableResults scrollableResults) {
        return getResult(scrollableResults);
      }
    };
  }
}

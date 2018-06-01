package gov.ca.cwds.cals.persistence.dao.calsns;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.CalsBaseEntity;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Abstract class for encapsulation create/update data tracking.
 *
 * @author CWDS TPT-2 Team
 */
public abstract class CalsBaseEntityDao<T extends CalsBaseEntity> extends BaseDaoImpl<T> {

  public CalsBaseEntityDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public T create(T entity) {
    LocalDateTime now = LocalDateTime.now();
    entity.setCreateDateTime(now);
    entity.setUpdateDateTime(now);
    entity.setCreateUserId(PrincipalUtils.getStaffPersonId());
    entity.setUpdateUserId(PrincipalUtils.getStaffPersonId());
    return super.create(entity);
  }

  @Override
  public T update(T entity) {
    Session session = this.grabSession();
    T databaseObject = this.find(entity.getPrimaryKey());
    if (databaseObject == null) {
      String msg = MessageFormat
          .format("Unable to find entity with id={0}", entity.getPrimaryKey());
      throw new EntityNotFoundException(msg);
    } else {
      // Replace create  
      entity.setCreateDateTime(databaseObject.getCreateDateTime());
      entity.setCreateUserId(databaseObject.getCreateUserId());

      LocalDateTime now = LocalDateTime.now();
      entity.setUpdateDateTime(now);
      entity.setUpdateUserId(PrincipalUtils.getStaffPersonId());
      session.evict(databaseObject);
      return persist(entity);
    }
  }

}

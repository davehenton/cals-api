package gov.ca.cwds.cals.persistence.hibernate;

import static java.sql.Types.JAVA_OBJECT;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

/**
 * @author CWDS CALS API Team.
 */
public abstract class JsonType implements UserType {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public int[] sqlTypes() {
    return new int[]{JAVA_OBJECT};
  }

  @Override
  public boolean equals(Object x, Object y) {
    return x.equals(y);
  }

  @Override
  public int hashCode(Object x) {
    return x.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session,
      Object owner) throws SQLException {
    final String cellContent = rs.getString(names[0]);
    if (cellContent == null) {
      return null;
    }
    try {
      return mapper.readValue(cellContent.getBytes("UTF-8"), returnedClass());
    } catch (final Exception ex) {
      throw new ConvertingException(
          String.format("Failed to convert String to %s: %s", returnedClass().getSimpleName(),
              ex.getMessage()), ex);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index,
      SharedSessionContractImplementor session) throws SQLException {
    if (value == null) {
      st.setNull(index, Types.OTHER);
      return;
    }
    try {
      final StringWriter stringWriter = new StringWriter();
      mapper.writeValue(stringWriter, value);
      stringWriter.flush();
      st.setObject(index, stringWriter.toString(), Types.OTHER);
    } catch (final Exception ex) {
      throw new ConvertingException(
          String.format("Failed to convert %s to String: %s", returnedClass().getSimpleName(),
              ex.getMessage()), ex);
    }
  }

  @Override
  public Object deepCopy(Object value) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(value);
      oos.flush();
      oos.close();
      bos.close();
      ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
      return new ObjectInputStream(bais).readObject();
    } catch (ClassNotFoundException | IOException ex) {
      throw new HibernateException(ex);
    }
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  @Override
  public Serializable disassemble(Object value) {
    return (Serializable) deepCopy(value);
  }

  @Override
  public Object assemble(Serializable cached, Object owner) {
    return deepCopy(cached);
  }

  @Override
  public Object replace(Object original, Object target, Object owner) {
    return deepCopy(original);
  }
}

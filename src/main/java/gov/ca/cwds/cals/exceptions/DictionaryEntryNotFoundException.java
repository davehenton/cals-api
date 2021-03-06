package gov.ca.cwds.cals.exceptions;

/**
 * Exception for dictionary entity not found.
 *
 * @author CWDS CALS-1 Team
 */
public class DictionaryEntryNotFoundException extends RuntimeException {

  public DictionaryEntryNotFoundException() {
    super();
  }

  public DictionaryEntryNotFoundException(String message) {
  }

  public DictionaryEntryNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}

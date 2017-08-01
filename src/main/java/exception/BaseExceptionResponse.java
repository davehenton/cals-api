package exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

public abstract class BaseExceptionResponse implements Serializable {

  @JsonProperty("user_messages")
  private List<String> userMessages = new ArrayList<>();

  public BaseExceptionResponse() {
  }

  public List<String> getUserMessages() {
    return userMessages;
  }

  public void setUserMessages(List<String> userMessages) {
    this.userMessages = userMessages;
  }

}

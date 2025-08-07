package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@JsonInclude(Include.NON_DEFAULT)
public final class ActionResponse {
  private int actionStatusCode;
  private String actionDescription;
  private List<ErrorResponse> actionErrors;

  public ActionResponse() {
  }

  private ActionResponse(Builder builder) {
    this.actionDescription = builder.description;
    this.actionErrors = builder.errors;
    this.actionStatusCode = builder.statusCode;
  }

  public List<ErrorResponse> getActionErrors() {
    return this.actionErrors == null ? null : Collections.unmodifiableList(this.actionErrors);
  }

  public String getActionDescription() {
    return this.actionDescription;
  }

  public int getActionStatusCode() {
    return actionStatusCode;
  }

  public void setActionStatusCode(int actionStatusCode) {
    this.actionStatusCode = actionStatusCode;
  }

  public void setActionDescription(String actionDescription) {
    this.actionDescription = actionDescription;
  }

  public void setActionErrors(List<ErrorResponse> actionErrors) {
    this.actionErrors = actionErrors;
  }

  public String toString() {
    return "{statusCode=" + this.actionStatusCode + ", description='" + this.actionDescription + '\'' + ", errors=" + this.actionErrors + '}';
  }

  public static final class Builder {
    private final int statusCode;
    private String description;
    private List<ErrorResponse> errors = new ArrayList();

    Builder(int statusCode) {
      this.statusCode = statusCode;
    }

    public static final Builder newBuilder(int statusCode) {
      return new Builder(statusCode);
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder addError(String message, String type, int code) {
      this.errors.add(new ErrorResponse(message, type, code));
      return this;
    }

    public ActionResponse build() {
      return new ActionResponse(this);
    }
  }
}

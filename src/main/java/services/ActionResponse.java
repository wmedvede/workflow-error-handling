package services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@JsonInclude(Include.NON_DEFAULT)
public final class ActionResponse {
  private int statusCode;
  private String description;
  private List<ErrorResponse> errors;

  public ActionResponse() {
  }

  private ActionResponse(Builder builder) {
    this.description = builder.description;
    this.errors = builder.errors;
    this.statusCode = builder.statusCode;
  }

  public List<ErrorResponse> getErrors() {
    return this.errors == null ? null : Collections.unmodifiableList(this.errors);
  }

  public String getDescription() {
    return this.description;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setErrors(List<ErrorResponse> errors) {
    this.errors = errors;
  }

  public String toString() {
    return "{statusCode=" + this.statusCode + ", description='" + this.description + '\'' + ", errors=" + this.errors + '}';
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

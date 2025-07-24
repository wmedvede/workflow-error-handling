package services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Objects;

//@JsonInclude(Include.NON_DEFAULT)
public class ErrorResponse {
  private String message;
  private String type;
  private int code;

  public ErrorResponse(String message, String type, int code) {
    this.message = message;
    this.type = type;
    this.code = code;
  }

  public ErrorResponse() {
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String toString() {
    return "{message='" + this.message + '\'' +'\'' + ", type='" + this.type + '\'' + ", code=" + this.code + '}';
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      ErrorResponse that = (ErrorResponse)o;
      return Objects.equals(this.message, that.message) && Objects.equals(this.type, that.type) && this.code == that.code;
    } else {
      return false;
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[]{this.message, this.type, this.code});
  }
}

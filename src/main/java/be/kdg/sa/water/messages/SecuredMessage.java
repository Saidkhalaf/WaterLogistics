package be.kdg.sa.water.messages;

import jakarta.validation.constraints.AssertFalse;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.net.http.HttpClient;

public class SecuredMessage extends UnSecuredMessage {

    private String subjectId;
    private String email;
    private String firstName;
    private String lastName;
    private String message;

    private SecuredMessage(Builder builder) {
        subjectId = builder.subjectId;
        email = builder.email;
        firstName = builder.firstName;
        lastName = builder.lastName;
        message = builder.message;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String subjectId;
        private String email;
        private String firstName;
        private String lastName;
        private String message;

        private Builder() {
        }

        public Builder subjectId(String subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public SecuredMessage build() {
            return new SecuredMessage(this);
        }
    }
}

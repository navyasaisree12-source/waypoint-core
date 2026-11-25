package waypoint.model;

import java.util.Objects;

 /** VALUE OBJECT. Validates and normalises registration numbers. */
public record Registration(String value) {

    public Registration {
        Objects.requireNonNull(value, "Registration cannot be null");

        value = value.trim().toUpperCase();

        if (!value.matches("[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}")) {
            throw new IllegalArgumentException("Invalid registration number");
        }
    }
}
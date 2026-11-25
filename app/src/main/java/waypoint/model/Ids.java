package waypoint.model;

import java.util.Objects;

/** Distinct ID types so they can never be swapped. */

record TicketId(String value) {
    TicketId {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("Ticket ID cannot be blank");
        }
    }
}

record SpotId(String value) {
    SpotId {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("Spot ID cannot be blank");
        }
    }
}

record VehicleId(String value) {
    VehicleId {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("Vehicle ID cannot be blank");
        }
    }
}
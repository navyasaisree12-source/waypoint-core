package waypoint.model;

import java.time.Instant;
import java.util.Objects;

public final class Ticket {

    private final TicketId id;
    private final SpotId spot;
    private final Instant entry;
    private Instant exit;
    private Money fare;

    private Ticket(TicketId id, SpotId spot, Instant entry) {
        this.id = Objects.requireNonNull(id);
        this.spot = Objects.requireNonNull(spot);
        this.entry = Objects.requireNonNull(entry);
    }

    public static Ticket open(TicketId id, SpotId spot, Instant entry) {
        return new Ticket(id, spot, entry);
    }

    public boolean isOpen() {
        return exit == null;
    }

    public Money fare() {
        return fare;
    }

    public Money close(Instant at, FarePolicy policy) {
        if (!isOpen()) {
            throw new TicketAlreadyClosed();
        }

        Objects.requireNonNull(at);
        Objects.requireNonNull(policy);

        if (at.isBefore(entry)) {
            throw new IllegalArgumentException("Exit cannot be before entry");
        }

        Money calculatedFare = policy.fareFor(entry, at, spot);

        if (calculatedFare == null || calculatedFare.isNegative()) {
            throw new IllegalArgumentException("Invalid fare");
        }

        exit = at;
        fare = calculatedFare;

        return fare;
    }
}

class TicketAlreadyClosed extends RuntimeException {
    public TicketAlreadyClosed() {
        super("Ticket is already closed");
    }
}
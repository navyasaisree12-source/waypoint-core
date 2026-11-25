package waypoint.model;

/** ENTITY. Identity = id. Invariant: a spot cannot be assigned while occupied. */
public final class Spot {

    private final SpotId id;
    private VehicleId occupant;

    public Spot(SpotId id) {
        this.id = java.util.Objects.requireNonNull(id);
    }

    public boolean tryAssign(VehicleId v) {
        if (occupant != null) {
            return false;
        }
        occupant = java.util.Objects.requireNonNull(v);
        return true;
    }

    public void release(VehicleId v) {
        if (java.util.Objects.equals(occupant, v)) {
            occupant = null;
        }
    }

    public boolean isFree() {
        return occupant == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Spot other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
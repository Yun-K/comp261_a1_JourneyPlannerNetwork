package myCode;

import java.util.List;

public class Trip {

    /** A unique id */
    private String trip_id;

    /** A list of strings that store stops in sequence */
    private List<String> stopSequence;

    /**
     * A constructor. It construct a new instance of Trip.
     *
     * @param trip_id
     *            the id of the Trip
     * @param stopSequence
     *            A list of strings that store stops in sequence
     */
    public Trip(String trip_id, List<String> stopSequence) {
        // TODO Auto-generated constructor stub
        this.trip_id = trip_id;
        this.stopSequence = stopSequence;
    }

    /**
     * Get the trip_id.
     *
     * @return the trip_id
     */
    public String getTrip_id() {
        return trip_id;
    }

    /**
     * Get the stopSequence.
     *
     * @return the stopSequence
     */
    public List<String> getStopSequence() {
        return stopSequence;
    }

    /**
     * Set the trip_id.
     *
     * @param trip_id
     *            the trip_id to set
     */
    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    /**
     * Set the stopSequence.
     *
     * @param stopSequence
     *            the stopSequence to set
     */
    public void setStopSequence(List<String> stopSequence) {
        this.stopSequence = stopSequence;
    }

}

package myCode;

/**
 * Description: <br/>
 * The Connection object represent the edge that connect two stop nodes, one for start
 * stop, one for the destination stop. Also, the Connection object has another field which
 * is the weight field. Assert two Connection object also depends on the Weight field.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Connection {

    /** the start node */
    private Stop fromStop;

    /** the end node */
    private Stop toStop;

    /**
     * the weight field that represent the time need to cost from the startStop to the
     * endStop.
     */
    private double weight;

    /** the trip that this Connection obj belongs to */
    private String trip_name;

    /**
     * A constructor. It construct a new instance of Connection.
     * 
     * @param trip
     *            the trip that this Connection object belongs to
     *
     * @param fromStop
     *            the start stop
     * @param toStop
     *            the end stop
     */
    public Connection(String trip, Stop fromStop, Stop toStop) {
        // TODO Auto-generated constructor stub
        this.fromStop = fromStop;
        this.toStop = toStop;
        this.trip_name = trip;

    }

    public void drawConnection() {
    }

    /**
     * Get the fromStop.
     *
     * @return the fromStop
     */
    public Stop getFromStop() {
        return fromStop;
    }

    /**
     * Get the toStop.
     *
     * @return the toStop
     */
    public Stop getToStop() {
        return toStop;
    }

    /**
     * Get the weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the fromStop.
     *
     * @param fromStop
     *            the fromStop to set
     */
    public void setFromStop(Stop fromStop) {
        this.fromStop = fromStop;
    }

    /**
     * Set the toStop.
     *
     * @param toStop
     *            the toStop to set
     */
    public void setToStop(Stop toStop) {
        this.toStop = toStop;
    }

    /**
     * Set the weight.
     *
     * @param weight
     *            the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Get the trip_name.
     *
     * @return the trip_name
     */
    public final String getTrip_name() {
        return trip_name;
    }

    /**
     * Set the trip_name.
     *
     * @param trip_name
     *            the trip_name to set
     */
    public final void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

}

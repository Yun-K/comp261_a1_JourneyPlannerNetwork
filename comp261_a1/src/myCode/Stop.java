package myCode;

/**
 * Description: <br/>
 * A stop object has the id, name, latitude and longitude.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Stop {

    /**
     * the id of the stop, it contains characters and numbers.
     */
    private String stop_id;

    /**
     * the stop name.
     */
    private String stop_name;

    /**
     * the latitude of the stop.
     */
    private double stop_lat;

    /**
     * the longitude of the stop.
     */
    private double stop_lon;

    /**
     * A constructor. It construct a new instance of Stop.
     *
     * @param stop_id
     *            the id of the stop
     * @param stop_name
     *            the stop name
     * @param stop_lat
     *            the latitude of the stop.
     * @param stop_lon
     *            the longitude of the stop.
     */
    public Stop(String stop_id, String stop_name, double stop_lat, double stop_lon) {
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
    }

    /**
     * Get the stop_id.
     *
     * @return the stop_id
     */
    public String getStop_id() {
        return stop_id;
    }

    /**
     * Get the stop_name.
     *
     * @return the stop_name
     */
    public String getStop_name() {
        return stop_name;
    }

    /**
     * Get the stop_lat.
     *
     * @return the stop_lat
     */
    public double getStop_lat() {
        return stop_lat;
    }

    /**
     * Get the stop_lon.
     *
     * @return the stop_lon
     */
    public double getStop_lon() {
        return stop_lon;
    }

    /**
     * Set the stop_id.
     *
     * @param stop_id
     *            the stop_id to set
     */
    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    /**
     * Set the stop_name.
     *
     * @param stop_name
     *            the stop_name to set
     */
    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    /**
     * Set the stop_lat.
     *
     * @param stop_lat
     *            the stop_lat to set
     */
    public void setStop_lat(double stop_lat) {
        this.stop_lat = stop_lat;
    }

    /**
     * Set the stop_lon.
     *
     * @param stop_lon
     *            the stop_lon to set
     */
    public void setStop_lon(double stop_lon) {
        this.stop_lon = stop_lon;
    }

}

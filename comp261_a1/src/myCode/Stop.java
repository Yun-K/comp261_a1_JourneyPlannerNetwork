package myCode;

import java.util.ArrayList;
import java.util.List;

import codeResource.Location;

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
     * the latitude and the longitude of the stop.
     */
    private double stop_lat, stop_lon;

    /** the location of the Stop object */
    private Location location;

    /** a list of Connections for storing outgoing and incoming Connections */
    @SuppressWarnings("javadoc")
    private List<Connection> adj_outgoingConnections, adj_incomingConnections;

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
        // for assign to fields directly
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;

        // for others
        location = Location.newFromLatLon(stop_lat, stop_lon);
        adj_incomingConnections = new ArrayList<Connection>();
        adj_outgoingConnections = new ArrayList<Connection>();

    }

    public void drawStop() {
        // TODO Auto-generated method stub

    }

    /**
     * Get the location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location.
     *
     * @param location
     *            the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get the adj_outgoingConnections.
     *
     * @return the adj_outgoingConnections
     */
    public List<Connection> getAdj_outgoingConnections() {
        return adj_outgoingConnections;
    }

    /**
     * Get the adj_incomingConnections.
     *
     * @return the adj_incomingConnections
     */
    public List<Connection> getAdj_incomingConnections() {
        return adj_incomingConnections;
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

    /**
     * Set the adj_outgoingConnections.
     *
     * @param adj_outgoingConnections
     *            the adj_outgoingConnections to set
     */
    public void setAdj_outgoingConnections(List<Connection> adj_outgoingConnections) {
        this.adj_outgoingConnections = adj_outgoingConnections;
    }

    /**
     * Set the adj_incomingConnections.
     *
     * @param adj_incomingConnections
     *            the adj_incomingConnections to set
     */
    public void setAdj_incomingConnections(List<Connection> adj_incomingConnections) {
        this.adj_incomingConnections = adj_incomingConnections;
    }

    /**
     * Briefly describe the feature of the function:
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stop_id == null) ? 0 : stop_id.hashCode());
        long temp;
        temp = Double.doubleToLongBits(stop_lat);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stop_lon);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((stop_name == null) ? 0 : stop_name.hashCode());
        return result;
    }

    /**
     * Briefly describe the feature of the function:
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Stop other = (Stop) obj;
        if (stop_id == null) {
            if (other.stop_id != null) {
                return false;
            }
        } else if (!stop_id.equals(other.stop_id)) {
            return false;
        }
        if (Double.doubleToLongBits(stop_lat) != Double.doubleToLongBits(other.stop_lat)) {
            return false;
        }
        if (Double.doubleToLongBits(stop_lon) != Double.doubleToLongBits(other.stop_lon)) {
            return false;
        }
        if (stop_name == null) {
            if (other.stop_name != null) {
                return false;
            }
        } else if (!stop_name.equals(other.stop_name)) {
            return false;
        }
        return true;
    }

}

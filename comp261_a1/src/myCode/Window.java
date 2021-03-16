package myCode;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import codeResource.GUI;
import codeResource.Location;

/**
 * Description: <br/>
 * The class that extends the GUI to make my Journey Planner network.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Window extends GUI {

    static {
        id_stops_map = new HashMap<String, Stop>();
        id_trips_map = new HashMap<String, Trip>();
        all_connections = new ArrayList<Connection>();
        // origion_location = new Location(0, 0);
    }

    /** variable for zooming in/out */
    private double ZOOM = 1.08, SCALE = 25.041;

    /** coordinate variables */
    @SuppressWarnings("javadoc")
    private double min_x = Double.POSITIVE_INFINITY, min_y = Double.POSITIVE_INFINITY,
            max_x = Double.NEGATIVE_INFINITY, max_y = Double.NEGATIVE_INFINITY, map_width,
            map_height;

    /**
     * each stop id map to the assosiated Stop object
     */
    public static Map<String, Stop> id_stops_map;

    /**
     * each trip id map to the assosiated trip object
     */
    public static Map<String, Trip> id_trips_map;

    /** for storing all connections between all stops */
    public static List<Connection> all_connections;

    private static Location origion_location;

    @Override
    protected void redraw(Graphics g) {

        // calculate the origional location and assign it
        // this.origation_location = new Location(calculateOrigion()[0],
        // calculateOrigion()[1]);

        for (Stop stop : id_stops_map.values()) {

            stop.drawStop(g, origion_location, SCALE);
        }

        for (Connection connection : all_connections) {
            connection.drawConnection(g, origion_location, SCALE);
        }

    }

    @Override
    protected void onClick(MouseEvent e) {
        // TODO Auto-generated method stub
        Point clickedPoint = e.getPoint();
        Location mouse_clicked_location = Location.newFromPoint(clickedPoint, origion_location,
                SCALE);

        // loop through all the stops to find the closest one
        Stop closest_stop = null;
        double closest_distance = Double.POSITIVE_INFINITY;
        for (Stop stop : id_stops_map.values()) {
            // if the distance if closer, then assign it
            if (stop.getLocation().distance(mouse_clicked_location) < closest_distance) {
                closest_distance = stop.getLocation().distance(mouse_clicked_location);
                closest_stop = stop;
            }
            // reset each stop to not HighLighted
            stop.setHighLighted(false);
        }

        // highlight the closest stop
        closest_stop.setHighLighted(true);

        // print out the name of the Stop and the id of all trips going through the stop
        String info = "The Stop Name: " + closest_stop.getStop_name()
                      + "\nTrip IDs that going through the Stop: \n";
        for (Trip trip : id_trips_map.values()) {
            List<String> stopID_through_trip = trip.getStopSequence();
            if (stopID_through_trip.contains(closest_stop.getStop_id())) {
                info += trip.getTrip_id() + "\n";
            }
        }
        getTextOutputArea().setText(info);

    }

    @Override
    protected void onSearch() {
        // TODO Auto-generated method stub

    }

    /**
     * Allow the user to navigate the map, which can zooming and panning the map within
     * use the buttons.
     * <p>
     * For panning which is moving NORTH,EAST,WEST,SOUTH, the corresponding x\y value of
     * origional_position will be increased/decreased by 1.
     * <p>
     * For zooming, the scale will times/divide the ZOOM variable and the
     * origional_position will also be changed based on the prarmeters of the data graph.
     * 
     * @see codeResource.GUI#onMove(codeResource.GUI.Move)
     */
    @Override
    protected void onMove(Move m) {
        // for zooming
        double dx = (map_width - (map_width / ZOOM)) / 2;
        double dy = (map_height - (map_height / ZOOM)) / 2;
        // System.out.println("dy:" + dy + "\ndx:" + dx);

        // each value in the Move ENUM has the corresponding situation, so use the Switch
        // loop to switch.
        switch (m) {
        case NORTH:
            origion_location = origion_location.moveBy(0, 1);

            break;

        case SOUTH:
            origion_location = origion_location.moveBy(0, -1);

            break;

        case EAST:
            origion_location = origion_location.moveBy(1, 0);

            break;
        case WEST:
            origion_location = origion_location.moveBy(-1, 0);

            break;
        /*
         * for zooming, the scale should be changed. Also, the dy,dx for origion are
         * entirely different
         * 
         */
        case ZOOM_IN:
            SCALE *= ZOOM;// set up the scale first
            origion_location = origion_location.moveBy(dx, dy);
            break;
        case ZOOM_OUT:
            SCALE /= ZOOM;// set up the scale first
            origion_location = origion_location.moveBy(dx, dy);
            break;
        }

    }

    @Override
    protected void onLoad(File stopFile, File tripFile) {

        // call two methods to load the file
        readStopFile(stopFile);
        readTripFile(tripFile);
        calculateOrigion();

    }

    /**
     * Description: <br/>
     * The method for converting the data from stop.txt file into the programme.
     * 
     * @author Yun Zhou
     * @param stopFile
     *            the stop file that need to be read
     * 
     */
    private void readStopFile(File stopFile) {

        try {
            // the fileReader objecet for reading
            FileReader fileReader = new FileReader(stopFile);
            BufferedReader bReader = new BufferedReader(fileReader);

            // the first line is the describtion not the data, so read it first
            String stop_line = bReader.readLine();

            /*
             * keep reading the txt file until the end of thestream has been reached
             * without reading any characters
             */
            while (stop_line != null) {
                stop_line = bReader.readLine();
                // it reaches the end of the file, nothing to read, break the loop
                if (stop_line == null) {
                    break;
                }

                // split the line by tab and assign the representitve stop information
                // into the variable
                String[] stop_info = stop_line.split("\t");
                String id = stop_info[0];
                String name = stop_info[1];
                double latitde = Double.valueOf(stop_info[2]);
                double longitude = Double.valueOf(stop_info[3]);
                // put it into the map of the id_stops map
                id_stops_map.put(id, new Stop(id, name, latitde, longitude));

            }

            // close the BufferedReader and releases the resourses assoiate with it
            bReader.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("The file has not beed read successful", e);
        }
    }

    /**
     * Description: <br/>
     * The method for converting the trip data from trip.txt file into the programme.
     * 
     * @author Yun Zhou
     * @param tripFile
     *            the trip file that need to be read
     */
    private void readTripFile(File tripFile) {
        try {
            // the fileReader objecet for reading
            FileReader fileReader = new FileReader(tripFile);
            BufferedReader bReader = new BufferedReader(fileReader);

            // the first line is the describtion not the data, so read it first
            String trip_line = bReader.readLine();

            /*
             * keep reading the txt file until the end of thestream has been reached
             * without reading any characters
             */
            while (trip_line != null) {
                trip_line = bReader.readLine();
                // it reaches the end of the file, nothing to read, break the loop
                if (trip_line == null) {
                    break;
                }

                // split the line by tab and assign the representitve stop information
                // into the variable
                String[] trip_info = trip_line.split("\t");
                // String trip_id = trip_info[0];

                // convert the array into the List
                LinkedList<String> stopSequence = new LinkedList<String>(Arrays.asList(trip_info));
                String trip_id = stopSequence.removeFirst();// pop out and store the id

                // this loop if for iterating the list of the stopSequence in order for
                // constructing the corresponding Connection object and store it into the
                // programme
                for (int i = 0; i < stopSequence.size(); i++) {
                    // if the index of the next stop reaches the end
                    // then it's the final stop , break the loop
                    if (i + 1 >= stopSequence.size()) {
                        break;
                    }

                    // assign the Stop objects and
                    // construct the approiate Connection object
                    Stop fromStop = id_stops_map.get(stopSequence.get(i));
                    Stop toStop = id_stops_map.get(stopSequence.get(i + 1));
                    Connection connection = new Connection(trip_id, fromStop, toStop);
                    // add it into the field
                    Window.all_connections.add(connection);

                    // assign the outgoing and incoming Adjacent Connections to the Stop
                    // objects
                    List<Connection> outgoing_list = fromStop.getAdj_outgoingConnections();
                    outgoing_list.add(connection);
                    fromStop.setAdj_outgoingConnections(outgoing_list);

                    List<Connection> incoming_list = toStop.getAdj_incomingConnections();
                    incoming_list.add(connection);
                    toStop.setAdj_incomingConnections(incoming_list);

                }

                Trip trip = new Trip(trip_id, stopSequence);
                id_trips_map.put(trip_id, trip);

            }

            // close the BufferedReader and releases the resourses assoiate with it
            bReader.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("The file has not beed read successful", e);
        }

    }

    /**
     * Description: <br/>
     * Helper method for calculating the origion value
     * 
     * @author Yun Zhou
     * @return the array of the x_y value in decimal
     */
    private void calculateOrigion() {

        // get the minX,minY,maxX,maxY
        for (Stop stop : id_stops_map.values()) {
            if (stop.getLocation().x < min_x) {
                min_x = stop.getLocation().x;
            } else if (stop.getLocation().x > max_x) {
                max_x = stop.getLocation().x;

            }
            if (stop.getLocation().y < min_y) {
                min_y = stop.getLocation().y;
            } else if (stop.getLocation().y > max_y) {
                max_y = stop.getLocation().y;
            }
        }

        map_width = max_x - min_x;
        map_height = max_y - min_y;

        // calculate the mid point which is the origion
        double mid_x = min_x + map_width / 2;
        double mid_y = min_y + map_height / 2;

        origion_location = new Location(mid_x, mid_y);
        // System.out.println(
        // "left: " + min_x + "\nright: " + max_x + "\ntop: " + max_y + "\nbottom: " +
        // min_y);
        // System.out.println("scale: " + SCALE);// debug

    }

    public static void main(String[] args) {
        new Window();
    }

}

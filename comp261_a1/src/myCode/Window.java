package myCode;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import codeResource.GUI;

/**
 * Description: <br/>
 * The class that extends the GUI to make my Journey Planner network.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Window extends GUI {

    /**
     * each id map to the assosiated Stop object
     */
    static Map<String, Stop> id_stops_map;

    /**
     * each id map to the assosiated trip object
     */
    static Map<String, Trip> id_trips_map;

    /** for storing all connections between all stops */
    private List<Connection> all_connections;

    /**
     * A constructor. It construct a new instance of Window.
     *
     */
    public Window() {
        super();
        id_stops_map = new HashMap<String, Stop>();
        id_trips_map = new HashMap<String, Trip>();

    }

    @Override
    protected void redraw(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onClick(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onSearch() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onMove(Move m) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onLoad(File stopFile, File tripFile) {
        // call two methods to load the file
        readStopFile(stopFile);
        readTripFile(tripFile);

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
                    this.all_connections.add(connection);

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

}

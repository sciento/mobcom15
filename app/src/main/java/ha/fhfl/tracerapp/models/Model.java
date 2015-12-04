package ha.fhfl.tracerapp.models;

import android.location.Location;

/**
 * Created by cablecore on 21.11.15.
 */
public class Model {


    private Location location;

    public Model() {
        // TODO Auto-generated constructor stub
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }



}
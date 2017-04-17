package com.example.sdj.sherlockmc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdj on 4/16/17.
 */

public class TrainData {
    private String id;
    private List<Double> oneDataPoint;
    private GPSData gpsDataObject;



    public String getId() {
        return id;
    }

    public List<Double> getOneDataPoint() {
        return oneDataPoint;
    }

    public GPSData getGpsDataObject(){return gpsDataObject;}

    // Send only list of size 300 to this constructor it will crash else
    public TrainData(String id, List<AtomData> inpData,GPSData gpsDataObject){
        this.id = id;
        oneDataPoint = new ArrayList<>();
        for (int j = 0; j < inpData.size(); j++) {
            // Uncomment this if we need timestamp in future
            //oneDataPoint[i++]=inpData.get(j).getTimeStamp();
            oneDataPoint.add(inpData.get(j).getxAccel());
            oneDataPoint.add(inpData.get(j).getyAccel());
            oneDataPoint.add(inpData.get(j).getzAccel());
        }
        this.gpsDataObject = gpsDataObject;
    }
}

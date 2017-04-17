package com.example.sdj.sherlockmc.beans;

/**
 * Created by sdj on 4/16/17.
 */

public class AtomData {
    private long timeStamp;
    private double xAccel;
    private double yAccel;
    private double zAccel;

    public AtomData(long timeStamp,double xAccel,double yAccel,double zAccel){
        this.timeStamp = timeStamp;
        this.xAccel = xAccel;
        this.yAccel = yAccel;
        this.zAccel = zAccel;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public double getxAccel() {
        return xAccel;
    }

    public double getyAccel() {
        return yAccel;
    }

    public double getzAccel() {
        return zAccel;
    }

}

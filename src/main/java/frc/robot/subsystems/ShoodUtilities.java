package frc.robot.subsystems;

import frc.robot.Constants;

public class ShoodUtilities {



    public int searchInTable(double x) {;
        for (int i=0; i<Constants.LOOK_UP_TABLE.length; i++) {
            if (Constants.LOOK_UP_TABLE[0][i]>=x) {
                return i;
            }
        }
        return Constants.LOOK_UP_TABLE.length-1;
    }


    public double interp(double x) {
        int I = searchInTable(x);
        if (I==0) {
            return 0;
        }
        if (I==Constants.LOOK_UP_TABLE.length-1) {
            return 0;
        }
        double tot = Constants.LOOK_UP_TABLE[0][I]-Constants.LOOK_UP_TABLE[0][I-1];
        double part = Constants.LOOK_UP_TABLE[0][I] - x;
        return part/tot;



    }



    public double calcVelocity(double x) {
        if (searchInTable(x)==0) {
            return Constants.LOOK_UP_TABLE[1][searchInTable(x)];
        }
        double vel = Constants.LOOK_UP_TABLE[1][searchInTable(x)];
        double downvel = Constants.LOOK_UP_TABLE[1][searchInTable(x)-1];
        double tot = vel - downvel;
        return vel - interp(x)*tot;
        
    }


    public double calcAngle(double x) {
        if (searchInTable(x)==0) {
            return Constants.LOOK_UP_TABLE[2][searchInTable(x)];
        }
        double angle = Constants.LOOK_UP_TABLE[2][searchInTable(x)];
        double downangle = Constants.LOOK_UP_TABLE[2][searchInTable(x)-1];
        double tot = angle - downangle;
        return angle - interp(x)*tot;
        
    }


    public double[] calculate(double dis) {
        double[] db = new double[2];
        db[0] = calcVelocity(dis);
        db[1] = calcAngle(dis);
        return db;
    }
    
}

package frc.robot.subsystems;

import java.util.ArrayList;

import frc.robot.Constants;

public class ShoodUtilities {



    public int searchInTable(double x) {;
        for (int i=0; i<Constants.LOOK_UP_TABLE.length; i++) {
            if (Constants.LOOK_UP_TABLE[0][i]>=x) {
                return i;
            }
        }
        return Constants.LOOK_UP_TABLE.length;
    }


    public double interp(double x) {
        int I = searchInTable(x);
        if (I==0) {
            return 0;
        }
        if (I==Constants.LOOK_UP_TABLE.length){
            return 0;
        }
        double tot = Constants.LOOK_UP_TABLE[0][I]-Constants.LOOK_UP_TABLE[0][I-1];
        double part = Constants.LOOK_UP_TABLE[0][I] - x;
        return part/tot;



    }



    public double calcVelocity(double x) {
        if (searchInTable(x)==0) {
            return Constants.LOOK_UP_TABLE[1][0];
        }
        if(searchInTable(x)==Constants.LOOK_UP_TABLE.length) {
            return Constants.LOOK_UP_TABLE[1][Constants.LOOK_UP_TABLE.length-1];
        }
        double vel = Constants.LOOK_UP_TABLE[1][searchInTable(x)];
        double downvel = Constants.LOOK_UP_TABLE[1][searchInTable(x)-1];
        double tot = vel - downvel;
        return vel - interp(x)*tot;
        
    }


    public double calcAngle(double x) {
        if (searchInTable(x)==0) {
            return Constants.LOOK_UP_TABLE[2][0];
        }
        if(searchInTable(x)==Constants.LOOK_UP_TABLE.length) {
            return Constants.LOOK_UP_TABLE[2][Constants.LOOK_UP_TABLE.length-1];
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
    public static double todegsin(double degr){
        return Math.toDegrees(Math.sin(Math.toRadians(degr)));
    }
    public static double todegcosin(double degr){
        return Math.toDegrees(Math.cos(Math.toRadians(degr)));
        
    }

    public double[] decider(double disitan){
        ArrayList<ArrayList<Double>> posblsoultion = new ArrayList<ArrayList<Double>>(calcanglNLT(disitan));
        int i=0;
        int maxi=0;
        double sum = posblsoultion.get(0).get(0)*Constants.shootratio+posblsoultion.get(1).get(0)*Constants.hoodratio;
            // for(Double num : posblsoultion.get(0)){
            //     double sum2 = num*Constants.shootratio+posblsoultion.get(1).get(i)*Constants.hoodratio;
            //     if(sum>sum2){
            //         maxi =i;
            //     }
            //     i++;
            // }
        double[] velang ={posblsoultion.get(0).get(maxi),posblsoultion.get(1).get(maxi)};
        return velang;

    }
    
    public ArrayList<ArrayList<Double>> calcanglNLT(double dist){
        ArrayList<ArrayList<Double>> d1 = new ArrayList<ArrayList<Double>>();
        d1.add(new ArrayList<Double>());
        d1.add(new ArrayList<Double>());
        for (double i = 0.01; i < 89.99; i+=0.01) {
            double spedd = calcvelNLT(i, dist);
            switch ((int)spedd) {
                case 0:
                    
                    break;
            
                default:
                    d1.get(0).add(spedd);
                    d1.get(1).add(i);
                    break;
            }
        }
        return d1;
    }
    public double calcvelNLT(double deg,double dist){
        double speed =0;
        for(double i = Constants.minspeed;i<Constants.maxspeed;i+=0.01){
            double distV = calcdist(deg,i);
            if(distV-dist<=0.1&&distV-dist>=-0.1){
                return i;
            }


        }
        return speed;
    }
    public double calcdist(double angle,double speed){
        double angl1 = angle/2 +Math.PI/4;
        double Va = speed*todegcosin(angle)/Math.sqrt(1+Constants.K*Math.pow(speed, 2)*
        (todegsin(angle)+Math.pow(todegcosin(angle), 2)*Math.log(todegsin(angl1)/todegcosin(angl1))));
        double L = Va*Constants.T;
        double Xa = Math.sqrt(L*Constants.H*(todegcosin(angle)/todegcosin(angle)));
        double distan =quadeq(-Constants.H,Constants.H*L-((L-2*Xa)*Constants.hieght), Constants.hieght*(Math.pow(Xa, 2)));
        return distan;

    }
    public double quadeq(double a,double b,double c){
        double firstroot, secondroot;
 
        double det = b * b - 4 * a * c;
 
        if (det > 0) {
 
            // two real and distinct roots
            firstroot = (-b + Math.sqrt(det)) / (2 * a);
            secondroot = (-b - Math.sqrt(det)) / (2 * a);
            return Math.max(firstroot,secondroot);
        }else{return 0;}



    }
    
}

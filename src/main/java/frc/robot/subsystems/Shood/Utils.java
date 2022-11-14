package frc.robot.subsystems.Shood;

import java.util.ArrayList;

import frc.robot.Constants;

public class Utils {

    public int searchInTable(double x) {
        for (int i = 0; i < Constants.LOOK_UP_TABLE.length; i++) {
            if (Constants.LOOK_UP_TABLE[0][i] >= x) {
                return i;
            }
        }
        return Constants.LOOK_UP_TABLE.length;
    }

    public double interp(double x) {
        int I = searchInTable(x);
        if (I == 0) {
            return 0;
        }
        if (I == Constants.LOOK_UP_TABLE.length) {
            return 0;
        }
        double tot = Constants.LOOK_UP_TABLE[0][I] - Constants.LOOK_UP_TABLE[0][I - 1];
        double part = Constants.LOOK_UP_TABLE[0][I] - x;
        return part / tot;

    }

    public double calcVelocity(double x) {
        if (searchInTable(x) == 0) {
            return Constants.LOOK_UP_TABLE[1][0];
        }
        if (searchInTable(x) == Constants.LOOK_UP_TABLE.length) {
            return Constants.LOOK_UP_TABLE[1][Constants.LOOK_UP_TABLE.length - 1];
        }
        double vel = Constants.LOOK_UP_TABLE[1][searchInTable(x)];
        double downvel = Constants.LOOK_UP_TABLE[1][searchInTable(x) - 1];
        double tot = vel - downvel;
        return vel - interp(x) * tot;

    }

    public double calcAngle(double x) {
        if (searchInTable(x) == 0) {
            return Constants.LOOK_UP_TABLE[2][0];
        }
        if (searchInTable(x) == Constants.LOOK_UP_TABLE.length) {
            return Constants.LOOK_UP_TABLE[2][Constants.LOOK_UP_TABLE.length - 1];
        }

        double angle = Constants.LOOK_UP_TABLE[2][searchInTable(x)];
        double downangle = Constants.LOOK_UP_TABLE[2][searchInTable(x) - 1];
        double tot = angle - downangle;
        return angle - interp(x) * tot;

    }

    public double[] calculate(double dis) {
        double[] db = new double[2];
        db[0] = calcVelocity(dis);
        db[1] = calcAngle(dis);
        return db;
    }

    public static double todegsin(double degr) {
        return Math.toDegrees(Math.sin(Math.toRadians(degr)));
    }

    public static double toDegCos(double degr) {
        return Math.toDegrees(Math.cos(Math.toRadians(degr)));

    }

    public double[] decider(double disitan) {
        ArrayList<Double> posblsoultion = new ArrayList<Double>(calcanglNLT(disitan));//made the arraylist a singel dimesion one

        int i = 0;
        int maxi = 0;
        double sum = posblsoultion.get(0)* Constants.shootratio
                + posblsoultion.get(1) * Constants.hoodratio;
        for (int j = 0; j < posblsoultion.size(); j+=2) {
            System.out.println("shooter speed: "+posblsoultion.get(j));
            System.out.println("shooter angle: "+posblsoultion.get(j+1));
        
        // double sum2 =
        // +posblsoultion.get(j)*Constants.shootratio+posblsoultion.get(j+1)*Constants.hoodratio;
        // if(sum>sum2){
        // maxi =i;
        // }
        }
        double[] velang = { posblsoultion.get(maxi), posblsoultion.get(maxi+1)};
        return velang;

    }

    public ArrayList<Double> calcanglNLT(double dist) {
        ArrayList<Double> d1 = new ArrayList<Double>();
       
        for (double i = 0.01; i < 89.99; i += 0.01) {
            double speed = calcvelNLT(Math.min(Constants.maxshooterspeed,Math.min(Constants.maxanliticalequtionvelocity,
            Math.sqrt(Constants.maxH * Constants.g * 2 / Utils.todegsin(89.999) * (Utils.todegsin(89.999) - Constants.g * Constants.K)))), i, dist);
            switch ((int) speed) {
                case 0:

                    break;

                default:
                    d1.add(speed);
                    d1.add(i);
                    break;
            }
        }
        return d1;
    }

    public double calcvelNLT(double maxspeed, double deg, double dist) {

        for (double i = Constants.minspeed; i < maxspeed; i += 0.01) { // kinda think we should change the max min speed
                                                                       // to just speeds we know the robot can reach.
                                                                       //jhony:the maximmup speed of the falcon is very high and depndent on the gear ratio
                                                                       //
            double distV = calcdist(deg, i);
            if (Math.abs(distV - dist) <= 0.1 && calcH(i, deg) < Constants.maxH) { // added the H thing to the if
                return i;
            }

        }
        return 0;

    }

    public double calcH(double speed, double angle) { // wrote H as a function
        double H = Math.pow(speed, 2) * Math.pow(todegsin(angle), 2) / Constants.g
                * (2 + Constants.K * Math.pow(speed, 2) * Math.pow(todegsin(angle), 2));
        return H;
    }

    public double calcdist(double angle, double speed) {
        double H = calcH(speed, angle);
        double T = 2 * Math.sqrt(2 * H / Constants.g);
        double angl1 = angle / 2 + Math.PI / 4;
        double Va = speed * toDegCos(angle) / Math.sqrt(1 + Constants.K * Math.pow(speed, 2) *
                (todegsin(angle) + Math.pow(toDegCos(angle), 2) * Math.log(todegsin(angl1) / toDegCos(angl1))));
        double L = Va * T;
        double Xa = Math.sqrt(L * H * (toDegCos(angle) / toDegCos(angle)));
        double distan = quadeq(-H, H * L - ((L - 2 * Xa) * Constants.hieght), Constants.hieght * (Math.pow(Xa, 2)));
        return distan;

    }

    public double quadeq(double a, double b, double c) {
        double firstroot, secondroot;

        double det = b * b - 4 * a * c;

        if (det > 0) {

            // two real and distinct roots
            firstroot = (-b + Math.sqrt(det)) / (2 * a);
            secondroot = (-b - Math.sqrt(det)) / (2 * a);
            return Math.max(firstroot, secondroot);
        } else {
            return 0;
        }

    }
    public static double convertshooterspeedtoballspeed(double shooterspeed){
        return shooterspeed*Constants.shooterexitspeedgradient+Constants.shooterexitspeedintercept;
    }

}

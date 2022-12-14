// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Shood.Utils;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {


    public static final int hoodM_port_num = 0;
    public static final int shootM_port_num = 0;
    public static final int controlerport=0;
    public static final int top_lim_num = 0;
    public static final int bot_lim_num = 0;
    public static final double Kp_shooter = 0;
    public static final double Ki_shooter = 0;
    public static final double Kd_shooter = 0;
    public static final double Kp_hood = 0;
    public static final double Ki_hood = 0;
    public static final double Kd_hood = 0;
    public static final double hood_ks=0;
    public static final double hood_kv=0;
    public static final double inch = 0.0254;
    public static final int hoogearRatio = 0;
    public static final int pulsePerRotation = 2048;
    public static final double pulsesPer360angle = hoogearRatio *pulsePerRotation;
    public static final double diamShoot = 6*inch;
    public static final double peremiterShoot = diamShoot*Math.PI;
    public static final int gearRatioS = 1;
    public static final double pulsesPerMeterS = gearRatioS * pulsePerRotation / peremiterShoot;
    public static final double SPEED_ER = 0;
    public static final double ANGLE_ER = 0;
    public static final double WRONG_ANGLE = 0;
    public static final double WRONG_SPEED = 0;
    public static final double CALIBRATION_SPEED = 0;
    public static final double shooks=0;
    public static final double shookv=0;
    public static final double shookv2=0;


    public static final double commandKp = 0;
    public static final double commandKi = 0;
    public static final double commandKd = 0;

    public static final double hoodKp = 0;
    public static final double hoodKi = 0;
    public static final double hoodKd = 0;




    public static final double[][] LOOK_UP_TABLE = new double[10][10];
    
    public static final double g=0;
    public static double hieght =0;
    private static final double H = 0;
    public static final double K=0;
    public static final double minspeed = Math.sqrt(H*g*2/
    Utils.todegsin(0.001)*(Utils.todegsin(0.001)-g*K));
    public static double hoodratio =0;
    public static double shootratio =0;
    public static double maxshooterspeed =-1;
    public static double maxanliticalequtionvelocity = Utils.convertshooterspeedtoballspeed(52);
    public static final double maxH = 0;
    public static double shooterexitspeedgradient  =-1;
    public static double shooterexitspeedintercept =-1;








}

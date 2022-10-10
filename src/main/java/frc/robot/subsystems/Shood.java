package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ShootThroughDashBoard;



public class Shood extends SubsystemBase {

    private final TalonFX hoodM;
    private final TalonFX shooterM;
    private final DigitalInput limswTop;
    private final DigitalInput limswBot;
    private PIDController PIDshoot;
    private PIDController PIDhood;
    private boolean isBrake; //so we can set the hood motor to brake, if that's what we decide




    public Shood() {
        hoodM = new TalonFX(Constants.hoodM_port_num);
        shooterM = new TalonFX(Constants.shootM_port_num);
        limswTop = new DigitalInput(Constants.top_lim_num);
        limswBot = new DigitalInput(Constants.bot_lim_num);
        PIDshoot = new PIDController(Constants.Kp_shooter, 
        Constants.Ki_shooter, Constants.Kd_shooter);
        PIDhood = new PIDController(Constants.Kp_hood, 
        Constants.Ki_hood, Constants.Kd_hood);

    }

    // I figured I'll write the function names so I can
    //call them in the commands and you'll actually write them,
    //also add the dashboard stuff and any necessary functions I might have forgotten, yay!

    public double getHoodAngle() {
        if (limitTopReached()) {
            hoodM.setSelectedSensorPosition(0);
        }
        if (limitBotReached()) {
            hoodM.setSelectedSensorPosition(90);
        }
        return 0;
    }

    public double getShooterSpeed() {
        return 0;
    }
    public void setHoodAngle(double angle) {
    }
    public void setShooterSpeed(double speed) {
    }

    public void setHoodPower(double p) {
    }
    public void setHoodVel(double v) {
    }
    public void setShootPower(double p) {
    }

    public void setNeutralModeHood(boolean isBrake) {

    }

    public boolean limitTopReached() {
        return limswTop.get();
    }
    public boolean limitBotReached() {
        return limswBot.get();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        SmartDashboard.putNumber("wanted velocity", 0);
        SmartDashboard.putNumber("wanted angle", 0);
    }










}

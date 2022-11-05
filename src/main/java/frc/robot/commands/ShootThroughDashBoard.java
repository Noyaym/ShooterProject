package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Shood.Shood;

public class ShootThroughDashBoard extends CommandBase{
    private Shood sh;
    private double v;
    private double angle;
    private boolean hoodInPos;
    private boolean shooterInSpeed;
    private boolean islookuptable;

    public ShootThroughDashBoard(Shood sh) {
        this.sh=sh;
        
    }

    @Override
    public void initialize() {
        this.v= SmartDashboard.getNumber("target velocity", 0);
        this.angle=SmartDashboard.getNumber("target angle", 0);
    }


    @Override
    public void execute() {
        sh.setHoodPosition(angle);
        sh.setShooterSpeed(v);
        hoodInPos = Math.abs(sh.getHoodAngle()-angle)<=Constants.ANGLE_ER;
        shooterInSpeed = Math.abs(sh.getShooterSpeed()-v)<=Constants.SPEED_ER;
        if (hoodInPos && shooterInSpeed) {
            SmartDashboard.putBoolean("is in position", true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        sh.setShootPower(0);
        sh.setHoodPower(0);
        
    }


    
}

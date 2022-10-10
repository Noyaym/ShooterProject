package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shood;
import frc.robot.subsystems.Turret;

public class ShootThroughDashBoard extends CommandBase{
    private Shood sh;
    private double v;
    private double angle;
    private boolean hoodInPos;
    private boolean shooterInSpeed;

    public ShootThroughDashBoard(Shood sh) {
        this.sh=sh;
        
    }

    @Override
    public void initialize() {
        this.v= SmartDashboard.getNumber("wanted velocity", 0);
        this.angle=SmartDashboard.getNumber("wanted angle", 0);
    }

    @Override
    public void execute() {
        sh.setHoodAngle(angle);
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

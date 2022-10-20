package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
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
    private PIDController PID;

    public ShootThroughDashBoard(Shood sh) {
        this.sh=sh;
        this.PID = new PIDController(Constants.commandKp, Constants.commandKi, Constants.commandKd);
        
    }

    @Override
    public void initialize() {
        this.v= SmartDashboard.getNumber("target velocity", 0);
        this.angle=SmartDashboard.getNumber("target angle", 0);
    }

    private double PIDofV(double angle) {
        return PID.calculate(angle - sh.getHoodAngle());
    }

    @Override
    public void execute() {
        sh.setHoodAngle(PIDofV(angle));
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

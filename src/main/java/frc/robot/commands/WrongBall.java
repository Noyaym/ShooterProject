package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Shood.Shood;

public class WrongBall  extends CommandBase{

    private Shood sh;
    private Tower tow;
    private Turret tur;
    private boolean hoodInPos;
    private boolean shooterInSpeed;
    private boolean turretInPose;
    private PIDController PID;

    public WrongBall(Shood sh, Tower tow, Turret tur) {
        this.sh = sh;
        this.tow = tow;
        this.tur = tur;
        this.PID = new PIDController(Constants.commandKp, Constants.commandKi, Constants.commandKd);
    }


    private double PIDofV() {
        return PID.calculate(Constants.WRONG_ANGLE - sh.getHoodAngle());

    }

   

    @Override
    public void execute() {
        sh.setHoodVel(PIDofV());
        sh.setShooterSpeed(Constants.WRONG_SPEED);
        hoodInPos = Math.abs(sh.getHoodAngle()-Constants.WRONG_ANGLE)<=Constants.ANGLE_ER;
        shooterInSpeed = Math.abs(sh.getShooterSpeed()-Constants.WRONG_SPEED)<=Constants.SPEED_ER;
        turretInPose = tur.isInPosition();
        if (hoodInPos && shooterInSpeed && turretInPose) {
            tow.setTowerMode(true);
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        sh.setNeutralModeHood(true);
        tow.setTowerMode(false);
        sh.setShootPower(0);
        sh.setHoodPower(0);
    }
    
}

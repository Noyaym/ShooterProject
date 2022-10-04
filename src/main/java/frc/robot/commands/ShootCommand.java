package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shood;
import frc.robot.subsystems.ShoodUtilities;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Turret;

public class ShootCommand extends CommandBase {

    private double dis;
    private Shood sh;
    private Tower tow;
    private Turret tur;
    private boolean isAutonomus;
    private ShoodUtilities util;
    private boolean hoodInPos;
    private boolean shooterInSpeed;
    private boolean visionSee;
    private boolean turretInPose;

    public ShootCommand(Shood sh, Tower tow, Turret tur, boolean isAutonomus, ShoodUtilities util) {
        this.sh = sh;
        this.tow = tow;
        this.tur = tur;
        this.isAutonomus = isAutonomus;
        this.util = util;
    }

    @Override
    public void execute() {
        dis = 0; //need to read from vision somehow
        sh.setHoodAngle(util.calculate(dis)[0]);
        sh.setShooterSpeed(util.calculate(dis)[1]);
        hoodInPos = Math.abs(sh.getHoodAngle()-Constants.WRONG_ANGLE)<=Constants.ANGLE_ER;
        shooterInSpeed = Math.abs(sh.getShooterSpeed()-Constants.WRONG_SPEED)<=Constants.SPEED_ER;
        visionSee = true; //need to read vision
        turretInPose = tur.isInPosition();
        if (hoodInPos && shooterInSpeed && visionSee && turretInPose) {
            tow.setTowerMode(true);
        }
        
    }

    @Override
    public boolean isFinished() {
        if (isAutonomus) {
            return true; //need to return true a few seconds after tower
            //mode is set to true for the first time
        }
        return false;
    }


    public void end(boolean interrupted) {
        sh.setNeutralModeHood(true);
        tow.setTowerMode(false);
        sh.setShootPower(0);
        sh.setHoodPower(0);
    }

    
}

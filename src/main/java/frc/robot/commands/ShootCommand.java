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
    private double angle;
    private double speed;
    private int countdown;

    public ShootCommand(Shood sh, Tower tow, Turret tur, boolean isAutonomus, ShoodUtilities util) {
        this.sh = sh;
        this.tow = tow;
        this.tur = tur;
        this.isAutonomus = isAutonomus;
        this.util = util;
    }

    @Override
    public void initialize() {
        dis = 0; //need to read from vision somehow
        angle = util.calculate(dis)[0];
        speed = util.calculate(dis)[1];
        countdown = 100;
    }

    @Override
    public void execute() {
        sh.setHoodAngle(angle);
        sh.setShooterSpeed(speed);
        hoodInPos = Math.abs(sh.getHoodAngle()-angle)<=Constants.ANGLE_ER;
        shooterInSpeed = Math.abs(sh.getShooterSpeed()-speed)<=Constants.SPEED_ER;
        visionSee = true; //need to read vision
        turretInPose = tur.isInPosition();
        if (hoodInPos && shooterInSpeed && visionSee && turretInPose) {
            tow.setTowerMode(true);
            countdown-=1;
        }
        
    }

    @Override
    public boolean isFinished() {
        if (isAutonomus && countdown==0) {
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

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shood;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Turret;

public class WrongBall  extends CommandBase{

    private Shood sh;
    private Tower tow;
    private Turret tur;
    private boolean hoodInPos;
    private boolean shooterInSpeed;

    public WrongBall(Shood sh, Tower tow, Turret tur) {
        this.sh = sh;
        this.tow = tow;
        this.tur = tur;
    }

   

    @Override
    public void execute() {
        sh.setHoodAngle(Constants.WRONG_ANGLE);
        sh.setShooterSpeed(Constants.WRONG_SPEED);
        hoodInPos = Math.abs(sh.getHoodAngle()-Constants.WRONG_ANGLE)<=Constants.ANGLE_ER;
        shooterInSpeed = Math.abs(sh.getShooterSpeed()-Constants.WRONG_SPEED)<=Constants.SPEED_ER;
        if (hoodInPos && shooterInSpeed) {
            tow.setTowerMode(true);
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        tow.setTowerMode(false);
    }
    
}

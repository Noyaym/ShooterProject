package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shood;

public class Calibrate extends CommandBase{

    private Shood sh;

    public Calibrate(Shood sh) {
        this.sh = sh;
    }

    @Override
    public void execute() {
        sh.setHoodVel(Constants.CALIBRATION_SPEED);
    }

    @Override
    public boolean isFinished() {
        return (sh.limitBotReached() || sh.limitTopReached());
    }

    @Override
    public void end(boolean interrupted) {
        sh.setNeutralModeHood(true);
        sh.setHoodPower(0);
    }

    
}

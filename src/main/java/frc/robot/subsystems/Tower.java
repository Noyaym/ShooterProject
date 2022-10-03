package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tower  extends SubsystemBase {

    private boolean isWorking;

    public Tower() {

    }

    public void setTowerMode(boolean work) {
        isWorking = work;
    }
    
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

    private boolean inPosition = true;

    public Turret() {

    }



    public boolean isInPosition() {
        return inPosition;
    }
    
}

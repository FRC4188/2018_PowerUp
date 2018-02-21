package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void cylinderOut() {
    	RobotMap.climberSolenoid1.set(Value.kReverse);
    }
    
    public void cylinderIn() {
    	RobotMap.climberSolenoid1.set(Value.kForward);
    }
    
    public void cylinderOff() {
    	RobotMap.climberSolenoid1.set(Value.kOff);
    }
    
}


package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX leadScrew = RobotMap.leadScrew;
	XboxController coPilotXboxController = Robot.m_oi.coPilotXboxController;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void cylinderOut() {
    	RobotMap.climberSolenoid1.set(Value.kReverse);
    	RobotMap.climberSolenoid2.set(Value.kReverse);
    	RobotMap.climberSolenoid3.set(Value.kReverse);
    }
    
    public void cylinderIn() {
    	RobotMap.climberSolenoid1.set(Value.kForward);
    	RobotMap.climberSolenoid2.set(Value.kForward);
    	RobotMap.climberSolenoid3.set(Value.kForward);
    }
    
    public void cylinderOff() {
    	RobotMap.climberSolenoid1.set(Value.kOff);
    	RobotMap.climberSolenoid2.set(Value.kOff);
    	RobotMap.climberSolenoid3.set(Value.kOff);
    }

    public void leadScrewRun() {
    	leadScrew.set(coPilotXboxController.getY(Hand.kRight));
    }
    
    public void leadScrewStop() {
    	leadScrew.set(0);
    }
    
}


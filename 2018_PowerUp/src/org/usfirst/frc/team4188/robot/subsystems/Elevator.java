package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_TalonSRX innerElevator = RobotMap.innerElevator;
	XboxController coPilotXboxController = Robot.m_oi.coPilotXboxController;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void innerElevatorUp() {
    	innerElevator.set(coPilotXboxController.getY(Hand.kLeft));
    }
    public void innerElevatorStop() {
    	innerElevator.set(0);
    }
}


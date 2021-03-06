package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_TalonSRX climberMotor = RobotMap.climberMotor;

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
    
    public void setMotorPower(double power) {
    	climberMotor.set(power);
    }
    
}


package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
		WPI_TalonSRX intakeRight = RobotMap.intakeRight;
		WPI_TalonSRX intakeLeft = RobotMap.intakeLeft;
		DoubleSolenoid intakeSolenoid = RobotMap.intakeSolenoid;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runIntakeMotors() {
    	intakeRight.set(1.0);
    	intakeLeft.set(-1.0);
    }
    public void stopIntakeMotors() {
    	intakeRight.set(0);
    	intakeLeft.set(0);
    }
}


package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
		
		WPI_VictorSPX intakeRight = RobotMap.intakeRight;
		WPI_VictorSPX intakeLeft = RobotMap.intakeLeft;
		WPI_VictorSPX intakeRelease = RobotMap.intakeRelease;
		DoubleSolenoid intakeSolenoid = RobotMap.intakeSolenoid;
		
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runIntakeMotors(double speed) {
    	intakeRight.set(-speed);
    	intakeLeft.set(speed);
    }
    
    public void stopIntakeMotors() {
    	intakeRight.set(0);
    	intakeLeft.set(0);
    }
    
    public void intakeOut() {
    	intakeSolenoid.set(Value.kForward);
    }
    
    public void intakeIn() {
    	intakeSolenoid.set(Value.kReverse);
    }
    
    public void intakeSolenoidOff() {
    	intakeSolenoid.set(Value.kOff);
    }
    
    public void intakeReleaseDown() {
    	intakeRelease.set(1.0);
    }
    
    public void intakeReleaseUp() {
    	intakeRelease.set(-1.0);
    }
    
    public void intakeReleaseStop() {
    	intakeRelease.set(0.0);
    }
    
}


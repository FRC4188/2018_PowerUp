package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.CSPRobotDrive;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.ManualDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX frontLeft = RobotMap.frontLeft;
	WPI_TalonSRX midLeft = RobotMap.midLeft;
	WPI_TalonSRX rearLeft = RobotMap.rearLeft;
	WPI_TalonSRX frontRight = RobotMap.frontRight;
	WPI_TalonSRX midRight = RobotMap.midRight;
	WPI_TalonSRX rearRight = RobotMap.rearRight;
	
	SpeedControllerGroup rightSide = RobotMap.rightSet;
	SpeedControllerGroup leftSide = RobotMap.leftSet;
	
	AnalogGyro gyro = RobotMap.gyro;
	
	CSPRobotDrive driveTrain = RobotMap.driveTrain;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualDrive());
    }
    
    public void setRampRate(double rampRate) {
    	frontLeft.configClosedloopRamp(rampRate, 0);
    	midLeft.configClosedloopRamp(rampRate, 0);
    	rearLeft.configClosedloopRamp(rampRate, 0);
    	frontRight.configClosedloopRamp(rampRate, 0);
    	midRight.configClosedloopRamp(rampRate, 0);
    	rearRight.configClosedloopRamp(rampRate, 0);
    }
    @SuppressWarnings("deprecation")
	public void arcadeDrive(double x, double twist, double throttle){
    	driveTrain.arcadeDrive(x*throttle, twist*throttle);
    }
    
    @SuppressWarnings("deprecation")
	public void autoDrive(double moveValue, double rotateValue) {
		// TODO Auto-generated method stub
		driveTrain.arcadeDrive(moveValue, rotateValue);
	}
    

    public void resetEncoders(){
		rearRight.setSelectedSensorPosition(0, 0, 0);
		rearLeft.setSelectedSensorPosition(0, 0, 0);
    }
    
    
    public void gyroReset(){
		gyro.reset();
	}
}



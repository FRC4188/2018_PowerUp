package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualDrive extends Command {

	XboxController pilotXboxController = Robot.m_oi.pilotXboxController;
	final double ERROR_CONSTANT = 0.001;
	
    public ManualDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
  
    	//double moveValue;
    	//double rotateValue;
    	double rotateConstant = 0.7;
    	//double brownOutConstant = 1.0;
    
    	if(pilotXboxController.getTriggerAxis(Hand.kRight)-0.5>0 && !((pilotXboxController.getTriggerAxis(Hand.kLeft)-0.5)>0)){
    		rotateConstant = 0.7+(0.3*(pilotXboxController.getTriggerAxis(Hand.kRight)-0.5)) ;
    	}
    	if((pilotXboxController.getTriggerAxis(Hand.kLeft)-0.5>0) && !((pilotXboxController.getTriggerAxis(Hand.kRight)-0.5)>0)){
    		rotateConstant = 0.7-(0.2*(pilotXboxController.getTriggerAxis(Hand.kLeft)-0.5));
    	}
    	
    	double errorCorrection = ERROR_CONSTANT * (Robot.m_drivetrain.getLeftEncoderRotation() - Robot.m_drivetrain.getRightEncoderRotation());
    	
    	Robot.m_drivetrain.arcadeDrive(pilotXboxController.getY(Hand.kLeft)*RobotMap.brownoutMultiplier, pilotXboxController.getX(Hand.kRight)*rotateConstant, 1.0);
    	/*
    	if((RobotMap.rearRight.getSelectedSensorVelocity(0)*Robot.m_pidDriveTrain.SENSOR_UNITS_PER_ROTATION) > 10.0 ){
    		Robot.m_pidDriveTrain.shiftGearIn();
    		Robot.m_pidDriveTrain.shiftGearOff();
    	}
    	else {
    		Robot.m_pidDriveTrain.shiftGearOut();
    		Robot.m_pidDriveTrain.shiftGearOff();
    	}
    	*/
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

    // Called once after isFinished returns true
    protected void end() {
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
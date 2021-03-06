package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveTimeBased extends Command {
	Timer timer;
	boolean isTimerStartedYet;
	boolean doneYet;
	double timerValue;
	double moveDirection;
	double rotation;

    public AutoDriveTimeBased(double moveValue, double rotateValue, double timerValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_drivetrain);
    	moveDirection = moveValue;
    	rotation = rotateValue;
    	this.timerValue = timerValue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.resetEncoders();
    	timer = new Timer();
    	isTimerStartedYet = false;
    	doneYet = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!isTimerStartedYet) {
			timer.start();
			isTimerStartedYet = true;
		}
    	else{
    		if(timer.get() < this.timerValue) {
    		Robot.m_drivetrain.autoDrive(moveDirection, rotation); //negative means it goes right
    		}
    		else{
    			Robot.m_drivetrain.autoDrive(0, 0);
    			doneYet = true;
    		}
    	}
    }
    	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        doneYet = false;
        isTimerStartedYet = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
	}
    
}


package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BothElevatorsRun extends Command {
	
	XboxController coPilot = Robot.m_oi.coPilotXboxController;
	double inner, outer;

    public BothElevatorsRun() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(coPilot.getY(Hand.kRight) < 0) {
    		inner = coPilot.getY(Hand.kRight)*-.75;
    	} else {
    		inner = coPilot.getY(Hand.kRight)*-.25;
    	}
    	outer = coPilot.getY(Hand.kLeft)*0.75;
    	Robot.m_elevator.bothElevatorsRun(inner, outer);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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

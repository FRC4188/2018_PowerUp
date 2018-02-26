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
    	// inner elevator power ranges from +1 to -0.5
    	double innerInput = coPilot.getY(Hand.kRight);
    	if(innerInput <= 0) {
    		// going up
    		inner = innerInput*(Robot.INNER_ELEVATOR_FLAT_POWER-1) + Robot.INNER_ELEVATOR_FLAT_POWER;
    	} else {
    		// going down
    		inner = innerInput*(-0.5 - Robot.INNER_ELEVATOR_FLAT_POWER) + Robot.INNER_ELEVATOR_FLAT_POWER;
    	}
    	// inner elevator power ranges from -1 to +1
    	double outerInput = coPilot.getY(Hand.kLeft);
    	if(outerInput <= 0) {
    		// going up
    		outer = outerInput * (Robot.OUTER_ELEVATOR_FLAT_POWER + 1) + Robot.OUTER_ELEVATOR_FLAT_POWER;
    	}
    	else {
    		// going down
    		outer = outerInput * (1 - Robot.OUTER_ELEVATOR_FLAT_POWER)+ Robot.OUTER_ELEVATOR_FLAT_POWER;
    	}
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

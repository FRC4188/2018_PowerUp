package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToFloor extends Command {

    public ElevatorToFloor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_elevator.bothElevatorsRun(-0.75, 0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return Robot.m_Ultrasonic.reading < 12.0;
        //return false;
    	return (RobotMap.innerElevator.getSensorCollection().isRevLimitSwitchClosed() &&
    			RobotMap.outerElevatorRight.getSensorCollection().isRevLimitSwitchClosed());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.bothElevatorsStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoInnerElevatorRun extends Command {
	
	private double innerPower;
	
    public AutoInnerElevatorRun(double innerPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.innerPower = innerPower;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_elevator.innerElevatorRun(innerPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.innerElevatorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

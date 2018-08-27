package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoBothElevatorsRun extends Command {

	private double innerPower;
	private double outerPower;
	
    public AutoBothElevatorsRun(double innerPower, double outerPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.innerPower = innerPower;
    	this.outerPower = outerPower;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_elevator.bothElevatorsRun(innerPower, outerPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.bothElevatorsRun(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

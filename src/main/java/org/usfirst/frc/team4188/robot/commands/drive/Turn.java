package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

    private double leftSpeed;
	private double rightSpeed;

	public Turn(double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.turn(leftSpeed, rightSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

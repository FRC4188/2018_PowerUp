package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngleEncoderBased extends Command {

	double angle;
	double turnDistance;
	double tolerance;
    public TurnToAngleEncoderBased(double angle, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	this.angle = angle;
    	this.tolerance = tolerance;
    	turnDistance = 1.04 * Math.toRadians(angle);
    	requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.setPIDInput(PIDInput.encoderToAngle);
    	Robot.m_drivetrain.resetEncoders();
    	Robot.m_drivetrain.setAbsoluteTolerance(tolerance);
    	Robot.m_drivetrain.setSetpoint(turnDistance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_drivetrain.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_drivetrain.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_drivetrain.disable();
    	Robot.m_drivetrain.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

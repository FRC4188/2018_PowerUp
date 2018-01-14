package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.subsystems.PIDDriveTrain.PIDInput;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	public double turnAngle;
	public double tolerance;
    public TurnToAngle(double setPoint, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_pidDriveTrain);
    	this.tolerance = tolerance;
    	this.turnAngle = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_pidDriveTrain.setPIDInput(PIDInput.gyro);
    	Robot.m_pidDriveTrain.gyroReset();
    	Robot.m_pidDriveTrain.setAbsoluteTolerance(tolerance);
    	Robot.m_pidDriveTrain.setSetpoint(turnAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_pidDriveTrain.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_pidDriveTrain.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_pidDriveTrain.disable();
    	Robot.m_pidDriveTrain.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

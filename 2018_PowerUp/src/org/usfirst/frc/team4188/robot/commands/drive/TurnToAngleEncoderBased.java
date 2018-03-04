package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngleEncoderBased extends Command {

	double turnDistance;
	double tolerance;
    public TurnToAngleEncoderBased(double angle, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	this.tolerance = tolerance;
    	turnDistance = 12.5/12.0 * Math.toRadians(-angle);
    	requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.gyro.reset();
    	//RobotMap.frontLeft.setInverted(true);
    	//RobotMap.rearLeft.setInverted(true);
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
    	RobotMap.frontLeft.setInverted(false);
    	RobotMap.rearLeft.setInverted(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

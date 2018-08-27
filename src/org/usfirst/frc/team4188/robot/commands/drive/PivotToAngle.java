package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotToAngle extends Command {
	double angle, tolerance;
	private static int instanceCounterNext = 0;
	private int instanceCounter = 0;

    public PivotToAngle(double angle, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.angle = angle;
    	this.tolerance = tolerance;
    	requires(Robot.m_drivetrain);
		instanceCounter = instanceCounterNext;
    	instanceCounterNext++;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.setBrake();
    	if(angle > 0) {
    		Robot.m_drivetrain.setPIDInput(PIDInput.leftOnlyGyro);
    	} else {
    		Robot.m_drivetrain.setPIDInput(PIDInput.rightOnlyGyro);
    	}
    	Robot.m_drivetrain.resetEncoders();
    	Robot.m_drivetrain.gyroReset();
    	Robot.m_drivetrain.setAbsoluteTolerance(tolerance);
    	Robot.m_drivetrain.setSetpoint(angle);
    	Robot.m_drivetrain.enable();
    	System.out.println("starting pivot " + instanceCounter + " now " + angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_drivetrain.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_drivetrain.setCoast();
    	Robot.m_drivetrain.disable();
    	Robot.m_drivetrain.free();
    	System.out.println("ending pivot " + instanceCounter + " now");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupted pivot " + instanceCounter);
    	end();
    }
}

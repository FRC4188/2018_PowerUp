package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistanceBased extends Command {
	private static int instanceCounterNext = 0;
	private int instanceCounter = 0;
	double distance;
	double tolerance;
	//public final double PID_CORRECTION = 10.0/16.0;
	//public final double AUTO_CORRECTION = 20.0/12.5;
	protected boolean firstTime = true;
	protected long startMs = 0L;
	protected long finCounter = 0L;
	
    public AutoDriveDistanceBased(double distance, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.distance = distance;
    	this.tolerance = tolerance;
    	requires(Robot.m_drivetrain);
    	instanceCounter = instanceCounterNext;
    	instanceCounterNext++;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.setBrake();
    	Robot.m_drivetrain.setPIDInput(PIDInput.encoder);
    	Robot.m_drivetrain.resetEncoders();
    	Robot.m_drivetrain.setAbsoluteTolerance(tolerance);
    	Robot.m_drivetrain.setSetpoint(distance);
    	Robot.m_drivetrain.enable();
    	System.out.println("starting drive " + instanceCounter + " now " + distance);
    	startMs = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (firstTime) {
    		System.out.println("first execute " + instanceCounter + " at " + this.elapsedMs() + " ms");
    		firstTime = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean fin = Robot.m_drivetrain.onTarget();
    	if (fin) {
    		System.out.println("drive " + instanceCounter + " is finished at " + this.elapsedMs() + ", finCounter = " + this.finCounter);
    	}
    	this.finCounter++;
    	return fin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_drivetrain.setCoast();
    	Robot.m_drivetrain.disable();
    	Robot.m_drivetrain.free();
    	System.out.println("ending drive " + instanceCounter + " now at " + this.elapsedMs());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupted drive " + instanceCounter + " at " + this.elapsedMs());
    	end();
    }
    
    protected long elapsedMs() {
    	return System.currentTimeMillis() - this.startMs;
    }
}

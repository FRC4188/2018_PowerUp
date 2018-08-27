package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import org.usfirst.frc.team4188.robot.subsystems.JevoisCamera;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToCube extends Command {
    private static int instanceCounterNext = 0;
    protected int instanceCounter = 0;
	public double turnAngle;
	final double tolerance = 3.0;
	boolean found = false;
	JevoisCamera camera = Robot.m_jevoisCamera;
	static Timer timer = new Timer();
	
    public TurnToCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_drivetrain);
    	instanceCounter = instanceCounterNext;
    	instanceCounterNext++;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	turnAngle = 0;
    	if(camera.timeSinceDetected() < 0.5) {
    		found = true;
        	turnAngle = Robot.m_jevoisCamera.getAngle();
	    	Robot.m_drivetrain.setBrake();
	    	Robot.m_drivetrain.setPIDInput(PIDInput.gyro);
	    	Robot.m_drivetrain.gyroReset();
	    	Robot.m_drivetrain.setAbsoluteTolerance(tolerance);
	    	Robot.m_drivetrain.setSetpoint(turnAngle);
	    	Robot.m_drivetrain.enable();
	    	System.out.println("starting turn to cube" + instanceCounter + " now " + turnAngle);
    	} else {
    		found = false;
    		System.out.println("cube not found, not executing turn");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.m_drivetrain.onTarget() || timer.get() > 3.0 || !found);
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(found) {
	    	Robot.m_drivetrain.disable();
	    	Robot.m_drivetrain.free();
	    	System.out.println("ending turn to cube" + instanceCounter + " now");
	    	Robot.m_drivetrain.setCoast();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupted turn to cube" + instanceCounter);
    	end();
    }
}

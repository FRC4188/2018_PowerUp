package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {
    private static int instanceCounterNext = 0;
    protected int instanceCounter = 0;
	public double turnAngle;
	public double tolerance;
	
    public TurnToAngle(double setPoint, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_drivetrain);
    	this.tolerance = tolerance;
    	this.turnAngle = setPoint;
    	instanceCounter = instanceCounterNext;
    	instanceCounterNext++;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.setBrake();
    	Robot.m_drivetrain.setPIDInput(PIDInput.gyro);
    	Robot.m_drivetrain.gyroReset();
    	Robot.m_drivetrain.setAbsoluteTolerance(tolerance);
    	Robot.m_drivetrain.setSetpoint(turnAngle);
    	Robot.m_drivetrain.enable();
    	System.out.println("starting turn " + instanceCounter + " now " + turnAngle);
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
    	Robot.m_drivetrain.disable();
    	Robot.m_drivetrain.free();
    	System.out.println("ending turn " + instanceCounter + " now");
    	Robot.m_drivetrain.setCoast();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupted turn " + instanceCounter);
    	end();
    }
}

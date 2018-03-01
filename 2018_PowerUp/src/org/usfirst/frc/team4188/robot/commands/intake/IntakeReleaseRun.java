package org.usfirst.frc.team4188.robot.commands.intake;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeReleaseRun extends Command {
	
	DigitalInput intakeReleaseTopLimit = RobotMap.intakeReleaseTopLimit;
	DigitalInput intakeReleaseBottomLimit = RobotMap.intakeReleaseBottomLimit;
	private double speed;

    public IntakeReleaseRun(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intake.runIntakeRelease(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    	//return ((intakeReleaseTopLimit.get() || intakeReleaseBottomLimit.get()) == true);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
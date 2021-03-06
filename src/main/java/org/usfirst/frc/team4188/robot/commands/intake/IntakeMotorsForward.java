package org.usfirst.frc.team4188.robot.commands.intake;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeMotorsForward extends Command {

    private boolean finishNow;

	public IntakeMotorsForward(boolean finishNow) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.finishNow = finishNow;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.m_oi.coPilotXboxController.getTriggerAxis(Hand.kRight) > 0.5) {
    		Robot.m_intake.runIntakeMotors(-0.75*RobotMap.brownoutMultiplier);
    	} else {
    		Robot.m_intake.runIntakeMotors(-0.25*RobotMap.brownoutMultiplier);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finishNow;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

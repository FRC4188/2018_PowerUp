package org.usfirst.frc.team4188.robot.commands.intake;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAutoClose extends Command {

	public static PowerDistributionPanel pdp = RobotMap.pdp;
	
    public IntakeAutoClose() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intake.runIntakeMotors(-0.75*RobotMap.brownoutMultiplier);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(pdp.getCurrent(10) > 2) {
        	return true;
        }
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

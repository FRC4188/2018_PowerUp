package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToScale extends Command {

    public ElevatorToScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_elevator.bothElevatorsRun(0.5, 0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	if(outerLimitSwitch()) {
    		Robot.m_elevator.bothElevatorsRun(0.5, 0.0);
    	else if(innerLimitSwitch()) {
    		Robot.m_elevator.bothElevatorsRun(0.0, 0.5);
    	}
    	else if(innerLimitSwitch && outerLimitSwitch){
    		Robot.m_elevator.bothElevatorsStop()
    	}
    	 */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return Robot.m_Ultrasonic.reading < 12.0;
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.bothElevatorsStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToHeight extends Command {

	private double setpoint;
	private double tolerance;
	
	public ElevatorToHeight(double setpoint, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.m_elevator);
		this.setpoint = setpoint;
		this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_elevator.setSetpoint(setpoint);
    	Robot.m_elevator.setAbsoluteTolerance(tolerance);
    	Robot.m_elevator.setPID(.25, .1, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_elevator.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_elevator.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_elevator.disable();
    	Robot.m_elevator.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
}

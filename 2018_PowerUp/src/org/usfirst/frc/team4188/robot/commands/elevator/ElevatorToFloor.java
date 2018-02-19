package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorToFloor extends Command {

	final private double setpoint = 0.0;
	final private double tolerance = 0.1;
	
	public ElevatorToFloor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.m_elevator);
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
}

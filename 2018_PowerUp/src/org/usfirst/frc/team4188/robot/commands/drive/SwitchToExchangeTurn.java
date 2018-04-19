package org.usfirst.frc.team4188.robot.commands.drive;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchToExchangeTurn extends Command {
    private static int instanceCounterNext = 0;
    protected int instanceCounter = 0;
	
    public SwitchToExchangeTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_drivetrain);
    	instanceCounter = instanceCounterNext;
    	instanceCounterNext++;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_drivetrain.setBrake();
    	Robot.m_drivetrain.setPIDInput(PIDInput.gyro);
    	Robot.m_drivetrain.gyroReset();
    	Robot.m_drivetrain.setAbsoluteTolerance(5.0);
    	if(Robot.gameMessage.charAt(2) == 'L') {
    		Robot.m_drivetrain.setSetpoint(-120);
    	} else if(Robot.gameMessage.charAt(2) == 'R') {
    		Robot.m_drivetrain.setSetpoint(120);
    	} else {
    		Robot.m_drivetrain.setSetpoint(0);
    	}
    	Robot.m_drivetrain.enable();
    	System.out.println("starting switch to exchange turn " + instanceCounter + " now ");
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
    	System.out.println("ending switch to exchange turn " + instanceCounter + " now");
    	Robot.m_drivetrain.setCoast();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("interrupted switch to exchange turn " + instanceCounter);
    	end();
    }
}

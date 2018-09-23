package org.usfirst.frc.team4188.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAutoClose extends Command {

    public static PowerDistributionPanel pdp = RobotMap.pdp;
    Timer timer = new Timer();
    public final double STOP_CURRENT = 12.0;
    public boolean isFinished;

	
    public IntakeAutoClose() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.m_intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
        timer.start();
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.m_intake.intakeOut();
        Robot.m_intake.runIntakeMotors(0.75*RobotMap.brownoutMultiplier);
        if((pdp.getCurrent(10) > STOP_CURRENT && timer.get() > 1) || timer.get() > 5){
                Robot.m_intake.intakeIn();
                Robot.m_intake.runIntakeMotors(0);
                isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

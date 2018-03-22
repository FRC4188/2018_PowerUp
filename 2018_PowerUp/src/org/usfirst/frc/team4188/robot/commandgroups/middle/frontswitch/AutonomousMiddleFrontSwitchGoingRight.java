package org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch;

import org.usfirst.frc.team4188.robot.Robot;

import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddleFrontSwitchGoingRight extends CommandGroup {

    public AutonomousMiddleFrontSwitchGoingRight() {
    	boolean doNothing = true;
    	// switch is on right
    	//addParallel(new IntakeReleaseRun(.05));
		//addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT, 1.0));
    	
    	// Drive Forward
    	addSequential(new AutoDriveDistanceBased(6.0 - Robot.ROBOT_LENGTH / 2, 0.5),2.0);
    	addSequential(new Delay(), 0.2);
    	
    	// Turn right
		addSequential(new TurnToAngle(90.0, 5.0),1.5);
		addSequential(new Delay(), 0.2);
		
		if (doNothing) {
			// Do-nothing drive
			addSequential(new AutoDriveDistanceBased(0, 0),1.0);
			addSequential(new Delay(), 0.2);
		}
		
		// Drive right
		addSequential(new AutoDriveDistanceBased(5.0 + Robot.ROBOT_WIDTH / 2, 0.5),2.0);
		addSequential(new Delay(), 0.2);
		
		// Turn left 
		addSequential(new TurnToAngle(-90.0, 5.0), 1.5);
		addSequential(new Delay(), 0.2);
		
		if (doNothing) {
			// Do-nothing drive
			addSequential(new AutoDriveDistanceBased(0, 0), 0.2);
		}
		
		// Drive forward
		addSequential(new AutoDriveDistanceBased(6.0 - Robot.ROBOT_LENGTH / 2, 0.5), 2.0);
		
		addSequential(new IntakeReleaseDown(), 0.1);
		addSequential(new IntakeMotorsForward(false), 0.5);
		addSequential(new IntakeMotorsStop());
    }
}

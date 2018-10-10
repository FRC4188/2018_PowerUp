package org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch;

import org.usfirst.frc.team4188.robot.Robot;

import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftIn;
import org.usfirst.frc.team4188.robot.commands.drive.PivotToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoNewElevatorRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeIn;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeOut;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddleFrontSwitchGoingRight extends CommandGroup {

    public AutonomousMiddleFrontSwitchGoingRight() {
    
    	boolean doNothing = true;
    	// switch is on right
		addSequential(new GearShiftIn());
		
		// raise elevator
		addParallel(new AutoNewElevatorRun(0.4), 1.0);
    	
    	// Drive Forward
    	addSequential(new AutoDriveDistanceBased(6.0 - Robot.ROBOT_LENGTH / 2, 0.5),2.0);
    	addSequential(new Delay(), 0.2);
    	
    	// Turn right
		addSequential(new TurnToAngle(90.0, 5.0), 1.0);
		addSequential(new Delay(), 0.2);
		
		if (doNothing) {
			// Do-nothing drive
			addSequential(new AutoDriveDistanceBased(0, 0),0.2);
			addSequential(new Delay(), 0.2);
		}
		
		// Drive right
		//addSequential(new AutoDriveDistanceBased(4.25 + Robot.ROBOT_WIDTH / 2, 0.5),2.0);
		addSequential(new AutoDriveDistanceBased(5.0 - Robot.ROBOT_WIDTH / 2, 0.5),2.0);
		addSequential(new Delay(), 0.2);
		
		// Turn left 
		addSequential(new TurnToAngle(-90.0, 5.0), 1.0);
		addSequential(new Delay(), 0.2);
		
		if (doNothing) {
			// Do-nothing drive
			addSequential(new AutoDriveDistanceBased(0, 0), 0.2);
		}
		
		// Drive forward
		addSequential(new AutoDriveDistanceBased(6.5 - Robot.ROBOT_LENGTH / 2, 0.5), 2.0);
		
		addParallel(new IntakeReleaseDown(), 0.2);
		addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
/*
    	
    	addSequential(new AutoDriveDistanceBased(11.5, 0.5), 1.6);
    	addSequential(new AutoDriveDistanceBased(1.0, 0.5), .3);
    	addSequential(new IntakeReleaseRun(0.75, false), 0.2);
    	//addSequential(new Delay(), 0.3);
    	addSequential(new IntakeMotorsForward(false), 3.0);
		addSequential(new IntakeMotorsStop());
*/
    }
}

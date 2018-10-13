package org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch;

import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.PivotToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoBothElevatorsRun;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoInnerElevatorRun;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoNewElevatorRun;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToFloor;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.elevator.InnerElevatorRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeIn;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeOut;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeSolenoidOff;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddleFrontSwitchGoingRightDouble extends CommandGroup {

    public AutonomousMiddleFrontSwitchGoingRightDouble() {
		
		// raise elevator
		addParallel(new AutoNewElevatorRun(0.7), 1.5);

		//Starts middle goes to switch right, deposits cube
    	addSequential(new AutoDriveDistanceBased(8.0, 0.5), 1.6);
    	addSequential(new AutoDriveDistanceBased(1.0, 0.1), .4);
    	addSequential(new IntakeReleaseRun(0.75, false), 0.2);
    	addParallel(new PivotToAngle(50.0, 3.0), 0.3);
    	addSequential(new IntakeMotorsForward(false), 1.0);
    	addSequential(new IntakeMotorsStop());
    	/*
    	//Drives forward to square up, drives reverse, turns 60? degrees
    	addSequential(new AutoDriveDistanceBased(2.0, 0.5), 0.75);
    	addSequential(new AutoDriveDistanceBased(-6.65, 0.2), 1.8);
    	addParallel(new AutoNewElevatorRun(-0.3), 0.7);
    	addParallel(new IntakeReleaseRun(0.5, false), 0.95);
		addParallel(new IntakeOut(), .2);
		addSequential(new TurnToAngle(-60, 3.0), 1.0);
		
		//Lowers intake, lowers elevator, intake out, drive forward and suck in cube
		addParallel(new IntakeMotorsReverse(false), 1.7);
		addSequential(new AutoDriveDistanceBased(5.3, 0.2), 1.5);
		addSequential(new AutoDriveDistanceBased(0.3, 0.0), 0.3);
		addSequential(new IntakeSolenoidOff());
		
		//Intake in, continues to take cube in
		addParallel(new IntakeIn(), .2);
		addSequential(new IntakeMotorsReverse(false), .4);
		addSequential(new IntakeMotorsStop());
		
		//Drives back while spinning in, raises elevator
		addParallel(new IntakeMotorsReverse(false), 1.0);
		addSequential(new AutoDriveDistanceBased(-4.6, 0.2), 1.2);
		addParallel(new IntakeMotorsReverse(false), 1.0);
		
		//Turns 60? degrees, drives forward, deposits cube
		addParallel(new TurnToAngle(70, 3.0), 1.0);
		addSequential(new AutoNewElevatorRun(1.0), 0.9);
		addSequential(new AutoDriveDistanceBased(7.0, 0.2), 1.0);
		addSequential(new IntakeMotorsForward(false), 1.5);
		addSequential(new IntakeMotorsStop());
		addSequential(new IntakeSolenoidOff());
		*/
		// backup and lower everything
		addSequential(new AutoDriveDistanceBased(-3.0, 0.5), 0.6);
		addParallel(new TurnToAngle(-80, 3.0), 1.0);
		addParallel(new IntakeOut(), 0.2);
		addSequential(new AutoNewElevatorRun(-0.3), 0.9);
		
		
    }
}
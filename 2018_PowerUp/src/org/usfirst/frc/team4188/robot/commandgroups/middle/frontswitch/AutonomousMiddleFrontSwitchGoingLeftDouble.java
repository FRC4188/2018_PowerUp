package org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch;

import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.PivotToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoInnerElevatorRun;
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
public class AutonomousMiddleFrontSwitchGoingLeftDouble extends CommandGroup {

    public AutonomousMiddleFrontSwitchGoingLeftDouble() {
    	
    	
    	//Starts in middle, turns left, drives to left switch, deposits cube
    	addSequential(new PivotToAngle(-54, 3.0), 1.0);
    	addSequential(new AutoDriveDistanceBased(12.0, 0.5), 1.6);
    	addSequential(new AutoDriveDistanceBased(1.0, 0.5), .3);
    	addSequential(new IntakeReleaseRun(0.75, false), 0.2);
    	addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
    	
		//Drives forward to square up, drives back, turns 90
		addSequential(new AutoDriveDistanceBased(2.0, 0.5), 1.0);
		addSequential(new Delay(), 0.2);
    	addSequential(new AutoDriveDistanceBased(-2.0, 0.2), 1.0);
		addSequential(new TurnToAngle(90, 3.0), 1.0);
		
		//Lowers intake, lowers elevator, intake out, drives forward
		addSequential(new IntakeReleaseRun(0.5, false), 0.6);
		addSequential(new ElevatorToFloor(), 0.5);
		addSequential(new IntakeOut(), .2);
		addSequential(new AutoDriveDistanceBased(3.0, 0.5), 0.6);
		
		//Takes in cube, drives forward, continues to take in cube
		addSequential(new IntakeMotorsReverse(false), 0.5);
		addSequential(new AutoDriveDistanceBased(1.0, 0.5), 1.0);
		addSequential(new IntakeMotorsReverse(false), 1.0);
		addSequential(new IntakeSolenoidOff());
		
		//intake in, continues taking cubes in
		addSequential(new IntakeIn(), .2);
		addSequential(new IntakeMotorsReverse(false), .4);
		addSequential(new IntakeMotorsStop());
		
		//Drive reverse while spinning in, raise elevator
		addParallel(new IntakeMotorsReverse(false), 1.0);
		addSequential(new AutoDriveDistanceBased(-3.2, 0.5), 1.0);
		addParallel(new IntakeMotorsReverse(false), 1.0);
		addSequential(new ElevatorToScale(), 1.0);
		
		//turn 90, drive forward, deposits cube
		addSequential(new TurnToAngle(-90, 3.0), 1.0);
		addSequential(new AutoDriveDistanceBased(2.0, 0.2), 1.2);
		addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
		addSequential(new IntakeSolenoidOff());
		
    }
}
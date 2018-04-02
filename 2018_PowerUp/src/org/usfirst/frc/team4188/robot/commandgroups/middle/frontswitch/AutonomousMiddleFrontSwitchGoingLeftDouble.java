package org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch;

import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.PivotToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoInnerElevatorRun;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
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
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	
    	addSequential(new PivotToAngle(-50, 3.0), 1.0);
    	addSequential(new AutoDriveDistanceBased(12.0, 0.5), 1.6);
    	addSequential(new AutoDriveDistanceBased(1.0, 0.5), .3);
    	addSequential(new IntakeReleaseRun(-0.75, false), 0.2);
    	addSequential(new Delay(), 0.2);
    	addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
    	
    	addSequential(new AutoDriveDistanceBased(-2.0, 0.2), 1.0);
		addSequential(new TurnToAngle(90, 3.0), .6);
		
		//TEST ELEVATOR TO HEIGHT DIRECTION VALUE
		addSequential(new AutoInnerElevatorRun(.7), 1.6);
		addSequential(new AutoDriveDistanceBased(2.5, 0.5), 0.6);
		
		addSequential(new IntakeReleaseRun(-0.9, false), 0.25);
		addSequential(new IntakeOut(), .2);
		addSequential(new IntakeMotorsReverse(false), .8);
		addSequential(new AutoDriveDistanceBased(1.0, 0.5), .5);
		addSequential(new IntakeMotorsReverse(false), .8);
		addSequential(new IntakeSolenoidOff());
		
		addSequential(new IntakeIn(), .2);
		addSequential(new IntakeMotorsReverse(false), .4);
		addSequential(new IntakeMotorsStop());
		
		addSequential(new AutoDriveDistanceBased(-2.5, 0.5), 1);
		//TEST ELEVATOR TO HEIGHT DIRECTION VALUE
		addSequential(new AutoInnerElevatorRun(-.8), 1);
		
		addSequential(new TurnToAngle(90, 3.0), .6);
		addSequential(new AutoDriveDistanceBased(2.0, 0.2));
		addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
		addSequential(new IntakeSolenoidOff());
		
    }
}

package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoInnerElevatorRun;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToFloor;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeIn;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftScaleGoingLeftDouble extends CommandGroup {
	
    public AutonomousLeftScaleGoingLeftDouble() {
    	addSequential(new AutoDriveDistanceBased(23.0  - Robot.ROBOT_LENGTH / 2, 1.0), 4.0);
    	addParallel(new IntakeReleaseRun(0.75, false), 0.4);
    	addParallel(new ElevatorToScale(), 3.0);
		addSequential(new TurnToAngle(65.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(1.8, 0.1), 0.7);
		//addSequential(new Delay(), 0.2);
		addSequential(new IntakeMotorsForward(false), 1.5);
		addSequential(new IntakeMotorsStop());
		addSequential(new AutoDriveDistanceBased(-3.0, 0.2), 1.3);
		addSequential(new ElevatorToFloor(0.4), 1.7);
		addParallel(new IntakeReleaseRun(0.75, false), 0.35);
		addSequential(new TurnToAngle(95, 3.0), 1.0);
		addSequential(new AutoInnerElevatorRun(2.4), 1.8);
		addParallel(new IntakeOut());
		addParallel(new IntakeMotorsReverse(false), 3.2);
		addSequential(new AutoDriveDistanceBased(4.5, 0.5), 3.2);
		addSequential(new IntakeIn(), 0.2);
		addSequential(new Delay(), 0.3);
		addSequential(new IntakeMotorsStop());
		
    }
}

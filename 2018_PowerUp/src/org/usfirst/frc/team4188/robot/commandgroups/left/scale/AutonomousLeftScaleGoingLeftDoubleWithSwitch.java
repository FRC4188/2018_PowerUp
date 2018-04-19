package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToFloor;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftScaleGoingLeftDoubleWithSwitch extends CommandGroup {
	
    public AutonomousLeftScaleGoingLeftDoubleWithSwitch() {
    	
    	// scale on left, go forward, turn, and deposit
    	addSequential(new AutoDriveDistanceBased(22.0 - Robot.ROBOT_LENGTH / 2, 1.0), 4.0);
    	addParallel(new IntakeReleaseRun(0.75, false), 0.4);
    	addSequential(new ElevatorToScale(), 3);
		addSequential(new TurnToAngle(75.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(0.5, 0.1), 0.7);
		//addSequential(new Delay(), 0.2);
		addSequential(new IntakeMotorsRun(-0.5), 2.0);
		addSequential(new IntakeMotorsStop());
		addSequential(new AutoDriveDistanceBased(-2.0, 0.2), 1.0);
		addSequential(new ElevatorToFloor(0.6));
		addSequential(new TurnToAngle(120.0, 7.0), 1.5);
		addSequential(new AutoDriveDistanceBased(5.0, 0.5), 1.0);
		addSequential(new IntakeOut(), 0.2);
		
    }
}

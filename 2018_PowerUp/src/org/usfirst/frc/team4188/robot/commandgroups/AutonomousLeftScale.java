package org.usfirst.frc.team4188.robot.commandgroups;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToSwitch;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftScale extends CommandGroup {
	
	public AutonomousLeftScale() {};

    public AutonomousLeftScale(String positions) {
    	char side = positions.charAt(1);
    	if(side == 'L') {
    		// scale on left, go forward, turn, and deposit
    		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		addSequential(new IntakeReleaseStop());
    		//addParallel(new ElevatorToSwitch(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(323.65/12 - RobotMap.ROBOT_LENGTH / 2, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new IntakeMotorsReverse());
    		// EXTRA
    		/*
    		addSequential(new ElevatorFloor());
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(24.0/12, 0.1));
    		addSequential(new GotoCube());
    		//addSequential(new RunIntake(1));
    		*/
    	}
    	else {
    		// scale on right, go around switch and deposit
    		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		addSequential(new IntakeReleaseStop());
    		//addParallel(new ElevatorToSwitch(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(228.735/12 - RobotMap.ROBOT_LENGTH / 2, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(264.62/12- RobotMap.ROBOT_WIDTH, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(290.915/12, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new IntakeMotorsReverse());
    		// EXTRA
    		/*
    		addSequential(new ElevatorFloor());
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(24.0/12, 0.1));
    		addSequential(new GotoCube());
    		//addSequential(new RunIntake(1));
    		*/
    	}
    }
}

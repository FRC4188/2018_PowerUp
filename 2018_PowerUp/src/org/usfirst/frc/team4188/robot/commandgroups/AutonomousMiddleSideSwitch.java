package org.usfirst.frc.team4188.robot.commandgroups;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddleSideSwitch extends CommandGroup {
	
	public AutonomousMiddleSideSwitch() {}

    public AutonomousMiddleSideSwitch(String positions) {
    	char side = positions.charAt(0);
    	if(side == 'L') {
    		// switch is on left
    		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		addSequential(new IntakeReleaseStop());
    		addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(20.0/12, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(84.0/12 + RobotMap.ROBOT_WIDTH / 2 + RobotMap.ROBOT_LENGTH / 2, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(144.0/12, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(12.0/12, 0.1));
    		addSequential(new IntakeMotorsReverse());
    	}
    	else {
    		// switch is on right
    		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		addSequential(new IntakeReleaseStop());
    		addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(20.0/12, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(84.0/12 - RobotMap.ROBOT_WIDTH / 2 + RobotMap.ROBOT_LENGTH / 2, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(144.0/12, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(12.0/12, 0.1));
    		addSequential(new IntakeMotorsReverse());
    	}
    }
}

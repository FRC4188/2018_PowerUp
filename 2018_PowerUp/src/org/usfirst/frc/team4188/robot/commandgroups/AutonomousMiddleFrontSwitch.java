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
public class AutonomousMiddleFrontSwitch extends CommandGroup {

    public AutonomousMiddleFrontSwitch() {}
    
    public AutonomousMiddleFrontSwitch(String positions) {
    	char side = positions.charAt(0);
    	if(side == 'L') {
    		// switch is on left
    		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		addSequential(new IntakeReleaseStop());
    		addParallel(new ElevatorToSwitch(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(20.0/12, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(54.0/12 + RobotMap.ROBOT_WIDTH / 2, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(120.0/12 - RobotMap.ROBOT_LENGTH, 0.1));
    		addSequential(new IntakeMotorsReverse());
    	}
    	else {
    		// switch is on right
    		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		addSequential(new IntakeReleaseStop());
    		addParallel(new ElevatorToSwitch(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));;
    		addSequential(new AutoDriveDistanceBased(20.0/12, 0.1));
    		addSequential(new TurnToAngle(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(54.0/12 - RobotMap.ROBOT_WIDTH / 2, 0.1));
    		addSequential(new TurnToAngle(-90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(120.0/12 - RobotMap.ROBOT_LENGTH, 0.1));
    		addSequential(new IntakeMotorsReverse());
    	}
    }
}

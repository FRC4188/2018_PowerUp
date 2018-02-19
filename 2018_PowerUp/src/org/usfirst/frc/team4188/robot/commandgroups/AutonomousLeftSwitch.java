package org.usfirst.frc.team4188.robot.commandgroups;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftSwitch extends CommandGroup {
	
	public AutonomousLeftSwitch() {}

    public AutonomousLeftSwitch(String positions) {
    	
    	//final double INVERSE_ROOT_3 = 1 / Math.sqrt(3.00);
    	char side = positions.charAt(0);
    	if(side == 'L') {
    		// switch is on left, go straight, turn, and deposit
    		//addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		//addSequential(new IntakeReleaseStop());
    		//addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(168.0/12 - Robot.ROBOT_LENGTH / 2, 1.0));
    		addSequential(new TurnToAngleEncoderBased(90.0, 0.001), 3.0);
    		addSequential(new AutoDriveDistanceBased(55.56/12 - Robot.ROBOT_WIDTH / 2 - Robot.ROBOT_LENGTH / 2, 0.1));
    		//addSequential(new IntakeMotorsReverse());
    		// EXTRA
    		/*
    		//addParallel(new ElevatorFloor());
    		addSequential(new AutoDriveDistanceBased(-0.5, 0.1));
    		addSequential(new TurnToAngleEncoderBased(-90.0, 0.5));
    		addSequential(new AutoDriveDistanceBased(
    				41.0/12 + (0.5 + Robot.ROBOT_LENGTH / 2) * INVERSE_ROOT_3, 0.1));
    		addSequential(new TurnToAngleEncoderBased(120.0, 0.5));
    		addSequential(new GotoCube());
    		//addSequential(new RunIntake(1));
    		//addSequential(new ElevatorSwitch());
    		//addSequential(new RunIntake(-1));
    		*/
    	}
    	else if(side == 'R'){
    		// switch is on right, go around switch and deposit
    		//addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
    		//addSequential(new IntakeReleaseStop());
    		//addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT_ROTATIONS, 0.1));
    		addSequential(new AutoDriveDistanceBased(228.735/12 - Robot.ROBOT_LENGTH / 2, 0.1));
    		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(264.62/12- Robot.ROBOT_WIDTH, 0.1));
    		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
    		addSequential(new AutoDriveDistanceBased(60.735/12, 0.1));
    		addSequential(new TurnToAngleEncoderBased(90.0, 0.5));
    		addSequential(new AutoDriveDistanceBased(55.56/12 - Robot.ROBOT_WIDTH / 2 - Robot.ROBOT_LENGTH / 2, 0.1));
    		addSequential(new IntakeMotorsReverse());
    		// EXTRA
    		/*
    		//addParallel(new ElevatorFloor());
    		addSequential(new AutoDriveDistanceBased(-0.5, 0.1));
    		addSequential(new TurnToAngleEncoderBased(90.0, 0.5));
    		addSequential(new AutoDriveDistanceBased(
    				41.0/12 + (0.5 + Robot.ROBOT_LENGTH / 2) * INVERSE_ROOT_3, 0.1));
    		addSequential(new TurnToAngleEncoderBased(-120.0, 0.5));
    		addSequential(new GotoCube());
    		//addSequential(new RunIntake(1));
    		//addSequential(new ElevatorSwitch());
    		//addSequential(new RunIntake(-1));
    		*/
    	}
    }
}

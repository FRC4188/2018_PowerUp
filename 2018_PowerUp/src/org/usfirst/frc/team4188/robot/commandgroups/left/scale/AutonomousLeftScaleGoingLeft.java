package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

import org.usfirst.frc.team4188.robot.Robot;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.Turn;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoBothElevatorsRun;
import org.usfirst.frc.team4188.robot.commands.elevator.BothElevatorsRun;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftScaleGoingLeft extends CommandGroup {

    public AutonomousLeftScaleGoingLeft() {
    	// scale on left, go forward, turn, and deposit
		//addSequential(new IntakeReleaseRun(-.5));
		
    	//addSequential(new AutoDriveDistanceBased(200/12 - Robot.ROBOT_LENGTH / 2, 1.0));
		
    	/*
    	addSequential(new IntakeReleaseUp(), 0.01);
		addSequential(new AutoBothElevatorsRun(.75, -.75), 2.5);
		addSequential(new IntakeReleaseRun(-0.75), 0.2);
		//addParallel(new ElevatorToScale());
		addSequential(new Turn(0.75, 0.75), 0.5);
		//addSequential(new TurnToAngleEncoderBased(90.0, 3.0));
		addSequential(new Delay(), 2.0);
		addSequential(new IntakeMotorsForward(false), 2.0);
		addSequential(new IntakeMotorsStop());
		*/
    }
}

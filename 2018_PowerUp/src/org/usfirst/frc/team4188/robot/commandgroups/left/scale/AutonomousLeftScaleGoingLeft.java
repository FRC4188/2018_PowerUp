package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.Turn;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
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
    	addSequential(new AutoDriveDistanceBased(22.0 - Robot.ROBOT_LENGTH / 2, 1.0), 4.0);
    	addSequential(new IntakeReleaseRun(-0.75, false), 0.4);
    	addSequential(new ElevatorToScale(), 3);
		addSequential(new TurnToAngle(65.0, 5.0), 1.5);
		//addSequential(new Delay(), 0.2);
		addSequential(new IntakeMotorsForward(false), 2.0);
		addSequential(new IntakeMotorsStop());
		
    }
}

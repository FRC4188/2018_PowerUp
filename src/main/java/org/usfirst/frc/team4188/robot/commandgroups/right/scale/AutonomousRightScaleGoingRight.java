package org.usfirst.frc.team4188.robot.commandgroups.right.scale;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.Turn;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToFloor;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeOut;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseUp;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRightScaleGoingRight extends CommandGroup {

    public AutonomousRightScaleGoingRight() {
		// scale on right, go forward, turn, and deposit
    	addSequential(new AutoDriveDistanceBased(22.0 - Robot.ROBOT_LENGTH / 2, 1.0), 4.0);
    	addSequential(new IntakeReleaseRun(0.75, false), 0.4);
    	addSequential(new ElevatorToScale(), 4.0);
		addSequential(new TurnToAngle(-75.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(0.5, 0.1), 0.7);
		//addSequential(new Delay(), 0.2);
		addSequential(new IntakeMotorsForward(false), 2.0);
		addSequential(new IntakeMotorsStop());
		addSequential(new AutoDriveDistanceBased(-2.0, 0.2), 1.0);
		addSequential(new ElevatorToFloor(0.6));
		addSequential(new TurnToAngle(-120.0, 7.0), 1.5);
		addSequential(new AutoDriveDistanceBased(5.0, 0.5), 1.0);
		addSequential(new IntakeOut(), 0.2);
    }
}

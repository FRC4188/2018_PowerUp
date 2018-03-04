package org.usfirst.frc.team4188.robot.commandgroups.right.scale;

import org.usfirst.frc.team4188.robot.Robot;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRightScaleGoingRight extends CommandGroup {

    public AutonomousRightScaleGoingRight() {
		// scale on right, go forward, turn, and deposit
    	//addSequential(new IntakeReleaseRun(-.5));
		addSequential(new AutoDriveDistanceBased(323.65/12 - Robot.ROBOT_LENGTH / 2, 0.1));
		addParallel(new ElevatorToScale());
		addSequential(new TurnToAngleEncoderBased(-90.0, 0.1));
		addSequential(new IntakeMotorsForward(false), 0.5);
		addSequential(new IntakeMotorsStop());
    }
}

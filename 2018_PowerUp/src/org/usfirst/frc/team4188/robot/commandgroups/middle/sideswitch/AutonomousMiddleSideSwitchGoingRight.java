package org.usfirst.frc.team4188.robot.commandgroups.middle.sideswitch;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddleSideSwitchGoingRight extends CommandGroup {

    public AutonomousMiddleSideSwitchGoingRight() {
    	// switch is on right
		addSequential(new IntakeReleaseDown(), RobotMap.INTAKE_RELEASE_TIME);
		addSequential(new IntakeReleaseStop());
		addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT, 1.0));
		addSequential(new AutoDriveDistanceBased(20.0/12, 0.2));
		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(84.0/12 - Robot.ROBOT_WIDTH / 2 + Robot.ROBOT_LENGTH / 2, 0.2));
		addSequential(new TurnToAngleEncoderBased(-90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(144.0/12, 0.2));
		addSequential(new TurnToAngleEncoderBased(-90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(12.0/12, 0.2));
		addSequential(new IntakeMotorsReverse(), 0.5);
		addSequential(new IntakeMotorsStop());
    }
}

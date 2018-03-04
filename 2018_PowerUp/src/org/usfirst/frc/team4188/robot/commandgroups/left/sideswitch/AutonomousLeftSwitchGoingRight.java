package org.usfirst.frc.team4188.robot.commandgroups.left.sideswitch;

import org.usfirst.frc.team4188.robot.Robot;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftSwitchGoingRight extends CommandGroup {

    public AutonomousLeftSwitchGoingRight() {
    	// switch is on right, go around switch and deposit
    	//addSequential(new IntakeReleaseRun(-.5));
		//addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT, 1.0));
		addSequential(new AutoDriveDistanceBased(228.735/12 - Robot.ROBOT_LENGTH / 2, 0.2));
		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(264.62/12- Robot.ROBOT_WIDTH, 0.2));
		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(60.735/12, 0.2));
		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(55.56/12 - Robot.ROBOT_WIDTH / 2 - Robot.ROBOT_LENGTH / 2, 0.2));
		addSequential(new IntakeMotorsForward(false), 0.5);
		addSequential(new IntakeMotorsStop());
    }
}

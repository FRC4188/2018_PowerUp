package org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddleFrontSwitchGoingLeft extends CommandGroup {

    public AutonomousMiddleFrontSwitchGoingLeft() {
    	// switch is on left
    	//addParallel(new IntakeReleaseRun(.05));
		//addParallel(new ElevatorToHeight(RobotMap.SWITCH_HEIGHT, 1.0));
    	addSequential(new AutoDriveDistanceBased(6.0 - Robot.ROBOT_LENGTH / 2, 0.5), 1.5);
    	addSequential(new Delay(), 0.2);
		addSequential(new TurnToAngle(-90.0, 3.0), 1.5);
		addSequential(new Delay(), 0.2);
		addSequential(new AutoDriveDistanceBased(5.0 + Robot.ROBOT_WIDTH / 2, 0.5), 1.5);
		addSequential(new Delay(), 0.2);
		addSequential(new TurnToAngle(90.0, 3.0), 1.5);
		addSequential(new Delay(), 0.2);
		addSequential(new AutoDriveDistanceBased(6.0 - Robot.ROBOT_LENGTH / 2, 0.5), 1.5);
		//addSequential(new IntakeReleaseDown(), 0.1);
		addSequential(new IntakeMotorsForward(false), 0.5);
		addSequential(new IntakeMotorsStop());
    }
}

package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

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
public class AutonomousLeftScaleGoingRight extends CommandGroup {

    public AutonomousLeftScaleGoingRight() {
    	// scale on right, go around switch and deposit
    	//addSequential(new IntakeReleaseRun(-.5));
    	/*
		addSequential(new AutoDriveDistanceBased(228.735/12 - Robot.ROBOT_LENGTH / 2, 0.2));
		addSequential(new TurnToAngleEncoderBased(90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(264.62/12- Robot.ROBOT_WIDTH, 0.2));
		addSequential(new TurnToAngleEncoderBased(-90.0, 0.1));
		addSequential(new AutoDriveDistanceBased(290.915/12, 0.2));
		addParallel(new ElevatorToScale());
		addSequential(new TurnToAngleEncoderBased(-90.0, 0.1));
		addSequential(new IntakeMotorsForward(false), 0.5);
		addSequential(new IntakeMotorsStop());
		*/
    }
}

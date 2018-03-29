package org.usfirst.frc.team4188.robot.commandgroups.right.scale;

import org.usfirst.frc.team4188.robot.Robot;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
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
public class AutonomousRightScaleGoingLeft extends CommandGroup {

    public AutonomousRightScaleGoingLeft() {
    	// scale on left, go around switch and deposit
    	
		addSequential(new AutoDriveDistanceBased(20.0 - Robot.ROBOT_LENGTH / 2, 0.5), 3.0);
		addSequential(new TurnToAngle(-90.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(0.0, 0.0), 0.1);
		addSequential(new AutoDriveDistanceBased(17.5 - Robot.ROBOT_WIDTH, 0.5), 3.0);
		addSequential(new TurnToAngle(100.0, 5.0), 1.5);
		//addSequential(new AutoDriveDistanceBased(4.0, 0.5), 1.5);
    	addSequential(new IntakeReleaseRun(-0.75, false), 0.7);
		addSequential(new ElevatorToScale(), 4.0);
		addSequential(new AutoDriveDistanceBased(2.5, 0.3), 1.0);
		//addSequential(new TurnToAngle(-90.0, 5.0), 1.5);
		//addSequential(new Delay(), 0.0);
		addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
		
    }
}

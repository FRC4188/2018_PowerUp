package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToFloor;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToScale;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsRun;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftScaleGoingRightDouble extends CommandGroup {

    public AutonomousLeftScaleGoingRightDouble() {
    	// scale on right, go around switch and deposit
    	
		addSequential(new AutoDriveDistanceBased(20.0 - Robot.ROBOT_LENGTH / 2, 0.5), 3.0);
		addSequential(new TurnToAngle(90.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(0.0, 0.0), 0.1);
		addSequential(new AutoDriveDistanceBased(18.0 - Robot.ROBOT_WIDTH, 0.5), 3.0);
		addParallel(new TurnToAngle(-94.0, 5.0), 1.5);
		//addSequential(new AutoDriveDistanceBased(4.0, 0.5), 1.5);
    	addParallel(new IntakeReleaseRun(0.75, false), 0.7);
		addSequential(new ElevatorToScale(), 3);
		addSequential(new AutoDriveDistanceBased(2.5, 0.3), 1.0);
		//addSequential(new TurnToAngle(-90.0, 5.0), 1.5);
		//addSequential(new Delay(), 0.0);
		addSequential(new IntakeMotorsRun(-0.5), 1.0);
		addSequential(new IntakeMotorsStop());
		addSequential(new AutoDriveDistanceBased(-3.0, 0.2), 1.0);
		addSequential(new ElevatorToFloor(0.6));
		
    }
}

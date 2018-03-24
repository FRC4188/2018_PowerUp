package org.usfirst.frc.team4188.robot.commandgroups.left.scale;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
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
public class AutonomousLeftScaleGoingRight extends CommandGroup {

    public AutonomousLeftScaleGoingRight() {
    	// scale on right, go around switch and deposit
    	//addSequential(new IntakeReleaseRun(-.5));
    	
		addSequential(new AutoDriveDistanceBased(19.0 - Robot.ROBOT_LENGTH / 2, 0.5), 3.0);
		addSequential(new TurnToAngle(90.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(22 - Robot.ROBOT_WIDTH, 0.5), 3.0);
		addSequential(new TurnToAngle(-90.0, 5.0), 1.5);
		addSequential(new AutoDriveDistanceBased(7.0, 0.5), 1.5);
		addSequential(new IntakeReleaseRun(-0.75, false), 0.7);
		addSequential(new ElevatorToScale(), 3.0);
		addSequential(new TurnToAngle(-90.0, 5.0), 1.5);
		addSequential(new Delay());
		addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
		
		
    }
}

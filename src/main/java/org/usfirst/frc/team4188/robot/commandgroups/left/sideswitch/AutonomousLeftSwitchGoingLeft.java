package org.usfirst.frc.team4188.robot.commandgroups.left.sideswitch;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.Delay;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftIn;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngleEncoderBased;
import org.usfirst.frc.team4188.robot.commands.elevator.AutoNewElevatorRun;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToHeight;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseRun;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftSwitchGoingLeft extends CommandGroup {

    public AutonomousLeftSwitchGoingLeft() {
		addSequential(new GearShiftIn());
		addParallel(new AutoNewElevatorRun(0.7), 1.0);
    	addSequential(new AutoDriveDistanceBased(14.0 - Robot.ROBOT_LENGTH / 2, 0.75), 2.0);
    	addSequential(new Delay(), 0.2);
    	addSequential(new TurnToAngle(90.0, 5.0), 1.0);
    	addSequential(new Delay(), 0.2);
    	addSequential(new AutoDriveDistanceBased(5.5 - Robot.ROBOT_WIDTH, 0.5), 1.0);
		addParallel(new IntakeReleaseDown(), 0.4);
    	addSequential(new IntakeMotorsForward(false), 5.0);
    	addSequential(new IntakeMotorsStop());
    }
}

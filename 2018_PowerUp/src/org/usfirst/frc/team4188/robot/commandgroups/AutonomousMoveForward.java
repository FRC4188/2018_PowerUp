package org.usfirst.frc.team4188.robot.commandgroups;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMoveForward extends CommandGroup {

	public AutonomousMoveForward() {
		addSequential(new AutoDriveDistanceBased(140/12 - Robot.ROBOT_LENGTH / 2, 0.1));
	}
    public AutonomousMoveForward(String positions) {
    	addSequential(new AutoDriveDistanceBased(140/12 - Robot.ROBOT_LENGTH / 2, 0.1));
    }
}

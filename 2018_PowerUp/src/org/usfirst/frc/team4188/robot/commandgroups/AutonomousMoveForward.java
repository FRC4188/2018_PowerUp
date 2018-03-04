package org.usfirst.frc.team4188.robot.commandgroups;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftIn;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMoveForward extends CommandGroup {

	public AutonomousMoveForward() {
		addSequential(new GearShiftIn());
		addSequential(new AutoDriveDistanceBased(200/12 - Robot.ROBOT_LENGTH / 2, 0.1));
		addSequential(new GearShiftOut());
	}
    public AutonomousMoveForward(String positions) {
    	addSequential(new GearShiftIn());
    	addSequential(new AutoDriveDistanceBased(200/12 - Robot.ROBOT_LENGTH / 2, 0.1));
    	addSequential(new GearShiftOut());
    }
}

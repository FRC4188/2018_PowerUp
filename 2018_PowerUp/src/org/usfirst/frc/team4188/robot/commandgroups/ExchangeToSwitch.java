package org.usfirst.frc.team4188.robot.commandgroups;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExchangeToSwitch extends CommandGroup {

    public ExchangeToSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	/*
    	addSequential(new AutoDriveDistanceBased(-9.5, 0.2), 2.0);
    	if(Robot.getGameMessage(2) == 'L') {
    		addSequential(new TurnToAngle(-120, 5.0), 1.0);
    	} else if (Robot.getGameMessage(2) == 'R') {
    		addSequential(new TurnToAngle(120, 5.0), 1.0);
    	}
		addSequential(new IntakeMotorsForward(false), 1.0);
		addSequential(new IntakeMotorsStop());
    	if(Robot.getGameMessage(2) == 'L') {
    		addSequential(new TurnToAngle(120, 5.0), 1.0);
    	} else if (Robot.getGameMessage(2) == 'R') {
    		addSequential(new TurnToAngle(-120, 5.0), 1.0);
    	}
		addSequential(new AutoDriveDistanceBased(9.5, 0.2), 2.0);
		*/
    }
}

package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.  You can replace me with your own command.
 */
public class ElevatorClimbUp extends Command {

  double power;

  public ElevatorClimbUp() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    power = 0.75;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(RobotMap.pdp.getVoltage() < 9.0 && power > 0) {
        power -= 0.1;
    } else if(RobotMap.pdp.getVoltage() > 9.0 && power < 1.0) {
        power += 0.1;
    }
    Robot.m_elevator.elevatorClimb(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
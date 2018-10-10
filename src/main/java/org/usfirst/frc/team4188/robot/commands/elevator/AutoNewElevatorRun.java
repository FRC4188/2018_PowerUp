package org.usfirst.frc.team4188.robot.commands.elevator;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class AutoNewElevatorRun extends Command {

  double power;

  public AutoNewElevatorRun(double power) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_elevator);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      System.out.println("Raising elevator with power " + power);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.m_elevator.newElevatorRun(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      end();
  }
}
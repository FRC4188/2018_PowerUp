/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import org.usfirst.frc.team4188.robot.commands.drive.*;
import org.usfirst.frc.team4188.robot.commands.elevator.*;
import org.usfirst.frc.team4188.robot.commands.intake.*;
import org.usfirst.frc.team4188.robot.commands.wings.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController pilotXboxController;
	public XboxController coPilotXboxController;
	
	public JoystickButton pilot1;
	public JoystickButton pilot2;
	public JoystickButton pilot3;
	public JoystickButton pilot4;
	public JoystickButton pilot5;
	public JoystickButton pilot6;
	public JoystickButton pilot7;
	public JoystickButton pilot8;
	public JoystickButton pilot9;
	public JoystickButton pilot10;
	public JoystickButton pilot11;
	public JoystickButton pilot12;
	
	public JoystickButton coPilot1;
	public JoystickButton coPilot2;
	public JoystickButton coPilot3;
	public JoystickButton coPilot4;
	public JoystickButton coPilot5;
	public JoystickButton coPilot6;
	public JoystickButton coPilot7;
	public JoystickButton coPilot8;
	public JoystickButton coPilot9;
	public JoystickButton coPilot10;
	public JoystickButton coPilot11;
	public JoystickButton coPilot12;
	
	public OI(){
	
	pilotXboxController = new XboxController(0);
	pilot1 = new JoystickButton(pilotXboxController, 1);
    pilot2 = new JoystickButton(pilotXboxController, 2);
    pilot3 = new JoystickButton(pilotXboxController, 3);
    pilot4 = new JoystickButton(pilotXboxController, 4);
    pilot5 = new JoystickButton(pilotXboxController, 5);
    pilot6 = new JoystickButton(pilotXboxController, 6);
    pilot7 = new JoystickButton(pilotXboxController, 7);
    pilot8 = new JoystickButton(pilotXboxController, 8);
    pilot9 = new JoystickButton(pilotXboxController, 9);
    pilot10 = new JoystickButton(pilotXboxController, 10);
    pilot11 = new JoystickButton(pilotXboxController, 11);
    pilot12 = new JoystickButton(pilotXboxController, 12);
    
    //pilot1.whenPressed(new ExchangeToSwitch());
    //pilot2.whenPressed(new PivotToAngle(-70, 5.0));
    
    pilot3.whileHeld(new ElevatorClimbDown());
    pilot3.whenReleased(new ElevatorClimbStop());
    pilot4.whileHeld(new ElevatorClimbUp());
    pilot4.whenReleased(new ElevatorClimbStop());
    
    pilot5.whenPressed(new LowerWings());
    pilot6.whenPressed(new RaiseWings());
    
	pilot8.whenPressed(new CalibrateGyro());
	 
	pilot9.whileHeld(new GearShiftIn());
    pilot9.whenReleased(new GearShiftOff());
    pilot10.whileHeld(new GearShiftOut());
    pilot10.whenReleased(new GearShiftOff());
    
	coPilotXboxController = new XboxController(1);
	coPilot1 = new JoystickButton(coPilotXboxController, 1);
	coPilot2 = new JoystickButton(coPilotXboxController, 2);
	coPilot3 = new JoystickButton(coPilotXboxController, 3);
	coPilot4 = new JoystickButton(coPilotXboxController, 4);
	coPilot5 = new JoystickButton(coPilotXboxController, 5);
	coPilot6 = new JoystickButton(coPilotXboxController, 6);
	coPilot7 = new JoystickButton(coPilotXboxController, 7);
	coPilot8 = new JoystickButton(coPilotXboxController, 8);
	coPilot9 = new JoystickButton(coPilotXboxController, 9);
	coPilot10 = new JoystickButton(coPilotXboxController, 10);
	coPilot11 = new JoystickButton(coPilotXboxController, 11);
	coPilot12 = new JoystickButton(coPilotXboxController, 12);
	
	coPilot1.whileHeld(new IntakeMotorsReverse(true));
	coPilot1.whenReleased(new IntakeMotorsStop());
	coPilot2.whileHeld(new IntakeMotorsForward(true));
	coPilot2.whenReleased(new IntakeMotorsStop());
	
	coPilot4.whenPressed(new IntakeAutoClose());
	
	coPilot5.whileHeld(new IntakeIn());
	coPilot5.whenReleased(new IntakeSolenoidOff());
	coPilot6.whileHeld(new IntakeOut());
	coPilot6.whenReleased(new IntakeSolenoidOff());

	coPilot7.whileHeld(new IntakeReleaseUp());
	coPilot7.whenReleased(new IntakeReleaseOff());
	coPilot8.whileHeld(new IntakeReleaseDown());
	coPilot8.whenReleased(new IntakeReleaseOff());
	
	
	
	}

}
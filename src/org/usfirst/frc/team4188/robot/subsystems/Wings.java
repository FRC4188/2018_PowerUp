package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wings extends Subsystem {
	Servo leftWingFront = RobotMap.leftWingFront;
	Servo leftWingBack = RobotMap.leftWingBack;
	Servo rightWingFront = RobotMap.rightWingFront;
	Servo rightWingBack = RobotMap.rightWingBack;
	
	
    
	@Override
    public void initDefaultCommand() {
    	
    }
	
    public void raiseWings(double raisePositionFront, double raisePositionBack) {
    	leftWingFront.setAngle(raisePositionFront);
    	leftWingBack.setAngle(raisePositionBack);
    	rightWingFront.setAngle(raisePositionFront);
    	rightWingBack.setAngle(raisePositionBack);
    }
    public void lowerWings(double lowerPositionFront, double lowerPositionBack){
    	leftWingFront.setAngle(lowerPositionFront);
    	leftWingBack.setAngle(lowerPositionBack);
    	rightWingFront.setAngle(lowerPositionFront);
    	rightWingBack.setAngle(lowerPositionBack);
    }

}
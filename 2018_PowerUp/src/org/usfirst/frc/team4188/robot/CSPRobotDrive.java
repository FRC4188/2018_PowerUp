package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("deprecation")
public class CSPRobotDrive extends RobotDrive implements PIDOutput {

	
	@SuppressWarnings("deprecation")
	public CSPRobotDrive(SpeedControllerGroup left, SpeedControllerGroup right) {
		super(left, right);
		
		// TODO Auto-generated constructor stub
	}
	private static final double OUTPUT_MIN = 0.3;
	public enum PIDType {
    	noType,
		turnToAngle,
    	driveToDistance,
    	driveToDistanceTwoEncoder
    	
    }
    
    private static PIDType driveType;
    public static void setPIDType(PIDType type) {    	
    	driveType = type;
    	switch (driveType) {
    	case turnToAngle:
        	SmartDashboard.putString("Setting PIDType =", "turnToAngle");
        	break;
    
    	case driveToDistance:
        	SmartDashboard.putString("Setting PIDType =", "driveToDistance");
        	break;
    	case driveToDistanceTwoEncoder:
    		SmartDashboard.putString("Setting PIDType =", "driveToDistance");
    		break;
    	}
    }

	@SuppressWarnings("deprecation")
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		if (Math.abs(output)< OUTPUT_MIN) {
    		output = OUTPUT_MIN * Math.signum(output);
    	}
    	SmartDashboard.putNumber("PID", output);
    	switch(driveType){
    	case turnToAngle:
    	
    		super.setLeftRightMotorOutputs(output,-output);
    		
        
    		break;
    	case driveToDistance:
    		
        	super.setLeftRightMotorOutputs(output,output);
       
        	break;
        
        default:
        
        	break;
        
    	

	}

	}
}

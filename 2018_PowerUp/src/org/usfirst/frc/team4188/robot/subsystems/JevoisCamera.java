package org.usfirst.frc.team4188.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JevoisCamera extends Subsystem {
	
	// boolean for camera view
	private static final boolean CAMERA_VIEW = true;
	
	// constants for camera setup
	private static final int BUAD_RATE = 115200;
	
	// constants for finding distance and angle
	private static final double PIXEL_WIDTH = 2000;
	private static final double PIXEL_HEIGHT = 1400;
	private static final double CAMERA_ANGLE_HOR = 65.0;
	private static final double CAMERA_ANGLE_VER = 65.0 * 254/320;
	private static final double TAN_ANGLE = Math.tan((CAMERA_ANGLE_VER/2) * (Math.PI/180));
	private static final double UPRIGHT_DISTANCE_FACTOR = (PIXEL_HEIGHT* 13.0/12.0)/(2 * TAN_ANGLE);
	private static final double FLAT_DISTANCE_FACTOR = (PIXEL_HEIGHT* 11.0/12.0)/(2 * TAN_ANGLE);
	private static final double CORRECTION_FACTOR = 1.52394349686;
	
	// constants for ObjectTracker for finding power-cubes
	private static final String NO_STREAM_MAP = "6";
	private static final int WIDTH = 320;
	private static final int HEIGHT = 254;
	private static final int FPS = 60;
	private static final VideoMode detectMode = new VideoMode(VideoMode.PixelFormat.kYUYV, WIDTH, HEIGHT, FPS);

	// default hsv values
	public final int H_MIN = 35;
	public final int H_MAX = 48;
	public final int S_MIN = 0;//90;
	public final int S_MAX = 255;
	public final int V_MIN = 0;//70;
	public final int V_MAX = 255;
	
	// constants for retrying
	private static final int MAX_TRIES_CONNECT = 10;
	private static final int MAX_TRIES_READ = 10;
	
	// thread
	private Thread listenerThread = null;
	
	// components
	private UsbCamera camera = null;
	private SerialPort serial = null;
	
	// variables for detection
	private int x = 0, y = 0, w = 0, h = 0;
	private double distance = 0.0, angle = 0.0;
	private String orientation = "NONE";
	private double timeWhenDetect = 0.0;
	
	// serial control functions
	public void sendSerial(String str) {
		serial.writeString(str + "\n");
	}
	public String readSerial(double timeout_s) {
		double startTime = Timer.getFPGATimestamp();
		while(Timer.getFPGATimestamp() - startTime < timeout_s) {
			if(serial.getBytesReceived() > 0) {
				// remove newlines
				return serial.readString().replaceAll("\n", "").replaceAll("\r", "");
			}
		}
		return null;
	}
	
	// accessor methods
	public double timeSinceDetected() {
		return Timer.getFPGATimestamp() - timeWhenDetect;
	}
	public double getDistance() {
		return distance;
	}
	public double getAngle() {
		return angle;
	}

	// main function 
	public void detectCube() {
		boolean found = false;
		int tries = 0;
		String str;
		String[] values;
		while(!found && tries++ < MAX_TRIES_READ){
			str = readSerial(0.2);
			if(str != null) {
		   	values = str.split(" ");
				if(values.length == 6 && values[1].equals("blob")) {
					// declare cube found
					found = true;
					// update time since cube was detected
					timeWhenDetect = Timer.getFPGATimestamp();
					// get values and push to dashboard
					x = Integer.parseInt(values[2]);
					y = Integer.parseInt(values[3]);
					w = Integer.parseInt(values[4]);
					h = Integer.parseInt(values[5]);
					// detect if cube is on edge
					if(x - w/2 <= -950 || x + w/2 >= 950) {
						// calculate distance, assume the cube is upright to guarantee distance traveled is enough
						distance = UPRIGHT_DISTANCE_FACTOR / h;
			   			orientation = "Unknown";
					}
					else {
						// calculate distance
				   		if(h >= w) {
				   			// upright
				   			distance = UPRIGHT_DISTANCE_FACTOR / h;
				   			orientation = "Upright";
				   		}
				   		else {
				   			// flat
				   			distance = FLAT_DISTANCE_FACTOR / h;
				   			orientation = "Flat";
				   		}
					}
					// apply magic correction factor
					distance *= CORRECTION_FACTOR;
			   		// calculate angle. not exact, max error is 1.4 degrees
			   		angle = CAMERA_ANGLE_HOR * x / PIXEL_WIDTH;
			   		// update values
			   		SmartDashboard.putNumber("X pos", x);
					SmartDashboard.putNumber("Y pos", y);
					SmartDashboard.putNumber("Width", w);
					SmartDashboard.putNumber("Height", h);
					SmartDashboard.putNumber("Distance", distance);
					SmartDashboard.putNumber("Angle", angle);
					SmartDashboard.putString("Orientation", orientation);
				}
			}
		}
	}
	
	// sets the hsv values to detect
	public void setHSV(int hMin, int hMax, int sMin, int sMax, int vMin, int vMax) {
		if(hMin < hMax) sendSerial("setpar hrange " + Integer.toString(hMin) + "..." + Integer.toString(hMax));
		if(sMin < sMax) sendSerial("setpar srange " + Integer.toString(sMin) + "..." + Integer.toString(sMax));
		if(vMin < vMax) sendSerial("setpar vrange " + Integer.toString(vMin) + "..." + Integer.toString(vMax));
	}
	
	// constructor
	public JevoisCamera() {		
		// setup serial
		int tries = 0;
		while(serial == null && tries++ < MAX_TRIES_CONNECT) {
			try {
				// open serial port on MoreBoard
				serial = new SerialPort(BUAD_RATE, SerialPort.Port.kMXP);
				serial.enableTermination();
			} catch(Exception e) {
				System.out.println("SERIAL RETRY " + Integer.toString(tries));
			}
		}
		if(serial == null) {
			DriverStation.reportError("Unable to start serial connection", false);
		}
		else {
			// start capture
			if(CAMERA_VIEW) {
				try {
					camera = CameraServer.getInstance().startAutomaticCapture();
					camera.setVideoMode(detectMode);
					//camera.setExposureHoldCurrent();
					//camera.setWhiteBalanceHoldCurrent();
				} catch(Exception e) {
					// if camera connection fails, default to no stream
					if(camera != null) camera.free();
					sendSerial("setmapping " + NO_STREAM_MAP);
					sendSerial("streamon");
				}
			}
			else {
				sendSerial("setmapping " + NO_STREAM_MAP);
				sendSerial("streamon");
			}
			// set default HSV
			setHSV(H_MIN, H_MAX, S_MIN, S_MAX, V_MIN, V_MAX);
			// set default data
			SmartDashboard.putNumber("X pos", x);
			SmartDashboard.putNumber("Y pos", y);
			SmartDashboard.putNumber("Width", w);
			SmartDashboard.putNumber("Height", h);
			SmartDashboard.putNumber("Distance", distance);
			SmartDashboard.putNumber("Angle", angle);
			SmartDashboard.putString("Orientation", orientation);
			// setup thread
			listenerThread = new Thread(new Runnable() {
				public void run() {
					while(!Thread.interrupted()) {
						detectCube();
					}
				}
			});
			listenerThread.setDaemon(true);
			listenerThread.start();
		}
	}
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}


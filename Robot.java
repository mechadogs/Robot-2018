/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5123.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	private DifferentialDrive m_robotDrive
			= new DifferentialDrive(new Spark(0), new Spark(1));
	Spark intake = new Spark(2);
	Spark elevator = new Spark(3);
	private Joystick m_stick = new Joystick(0);
	private Timer m_timer = new Timer(); 
	private Timer d_timer = new Timer();
	
	SendableChooser<int> chooser; 
	AnalogInput ultra;
	AnalogGyro gyro;
	int mode = 1;
	double kp, angle, timer, distance, dtimer;
	
  /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() { 
	chooser = new SendableChooser<int>();
    	chooser.addDefault("DR Straight line", 1);
	chooser.addXXXXX("SB Simple Switch", 2);
		
	SmartDashboard.putData("Autonomous Selector:", chooser);
	
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		mode = (int) chooser.getSelected(); // this
		m_timer.reset(); 
		d_timer.start();
                ultra = new AnalogInput(0);
		gyro = new AnalogGyro(1);
		angle = gyro.getAngle();
		timer = m_timer.get();
		distance = ultra.getAverageValue();
		kp = 0.003;
	
	   
  
  }

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		case 1: 
                        if (distance > 600) {
				m_robotDrive.arcadeDrive(-0.5, -angle * kp);
				d_timer.reset();
				d_timer.start();
			}
			
			else if (distance < 600 && dtimer > 2 && timer < 2) {
				m_robotDrive.arcadeDrive(0.0, 0);
				intake.set(-0.4);// release cube at 2/5  speed
				m_timer.start();
			}
			
		   	else if (timer > 1)){
		   		intake.set(0.0);// release cube at 2/5  speed
		   		m_robotDrive.arcadeDrive(0, 0);
			}		
		break;
			
		case 2:
			if (timer < 5.0) {
				m_robotDrive.arcadeDrive(-0.5, -angle * kp); // drive forwards 1/2 speed
			}
			else if (timer > 5.0 && timer < 6) {
				m_robotDrive.arcadeDrive(0.0, 0.0); // drive forwards 0 speed
				intake.set(-0.4);// release cube at 2/5  speed
			
			} 
			else {
				intake.set(0.0);// release cube at 0 speed
			}
		break; 
	 
	       case 3:
			if (distance > 600) {       
				m_robotDrive.arcadeDrive(-0.5, -angle * kp);
		}       
			else if (distance < 600) {     
				m_robotDrive.arcadeDrive(0.0, -angle * kp);    
				intake.set(-0.4);// release cube at 2/5  speed. 
				m_timer.reset(); 
				m_timer.start();
			}
			else if (timer > 1){
				intake.set(0.0);// stop intake
			}
	        break;


	}
	

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_robotDrive.curvatureDrive(m_stick.getRawAxis(1), m_stick.getRawAxis(4), m_stick.getRawButton(5) );
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	
}

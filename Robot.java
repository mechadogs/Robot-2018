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
	SendableChooser<String> chooser; 
	AnalogInput ultra;
	AnalogGyro gyro;
	int mode = 1;
	double kp, angle, timer, distance;
         boolean schwifty, team_umizoomi
	
  /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() { 
	chooser = new SendableChooser<>();
    chooser.addDefault("Auto 1", "mode");
	SmartDashboard.putData("Autonomous Selector: ", chooser);
	
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		mode = (int) chooser.getSelected(); // this
		m_timer.reset();
		m_timer.start();
    ultra = new AnalogInput(0);
    gyro = new AnalogGyro(1);
    angle = gyro.getAngle();
    timer = m_timer.get();
    distance = ultra.getAverageVoltage();
    kp = 0.003;
  
  }

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if (distance >10) {
		m_robotDrive.arcadeDrive(-0.5, -angle * kp);
		}
		   else if (distance <  10 && distance > 0) {
			m_robotDrive.arcadeDrive(0.0, -angle * kp);
			intake.set(-0.4);// release cube at 2/5  speed
			m_timer.reset();
			m_timer.start();
			}
		   else if ((m_timer.get() > 0.1)){
		   intake.set(0.0);// set intake to 0
                	m_timer.reset();
	         m_timer.start();
                else if (m_timer.get() > 0.1 && m_timer.get() < 0.3) {
                m_robotDrive.arcadeDrive(-1,0)
                else if (m_timer.get() > 0.3) {
		   m_robotDrive.arcadeDrive(0, 5400);
			}
		}
}
		
		
		
		
		// REVISE
		// Drive for a few seconds
		if (if m_timer.get < 5.0) {
			m_robotDrive.arcadeDrive(-0.5, -angle * kp); // drive forwards 1/2 speed
		}
		if (m_timer.get > 5.0 && m_timer.get() < 6.0) {
			m_robotDrive.arcadeDrive(0.0, 0.0); // drive forwards 0 speed
			intake.set(-0.4);// release cube at 2/5  speed
			
		} 
		else if (m_timer.get() > 6.0) {
			intake.set(0.0);// release cube at 0 speed
		

			
			
		// Drive for 2 seconds 	
	//** if (m_timer.get() < 2.0) {
        //robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
	 {
	//robotDrive.stopMotor(); // stop robot 
        switch (mode) { 
		case 1: 
// auton			
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

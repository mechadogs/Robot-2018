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
	private Joystick m_stick = new Joystick(0);
	private Timer m_timer = new Timer();
        SendableChooser chooser; 
	int mode = 1;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() { 
	chooser = new SendableChooser();
	chooser.addDefault("Auto 1", 1);
	SmartDashboard.putData("Autonomous Selector: ", chooser);
	
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() { 
		mode = (int) chooser.getSelected;
		m_timer.reset();
		m_timer.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds 	
	//** if (m_timer.get() < 2.0) {
        //robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
	 {
	//robotDrive.stopMotor(); // stop robot 
        switch (mode) { 
		case 1: 
			
		break;
	 }
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
	
	//hi
}
	pblic class Robot exteds Robot {
		AnalogSensor ultra = new AnalogSenor(0);
		
		public void robotInit(){
		ultra.setAutomaticMode(true);
	}
	pulic void analogSensor() {
		double rage = ultra.getRangeInches();
	}

package org.usfirst.frc.team5123.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GyroBase;
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
	private Joystick l_stick = new Joystick(1);
	private Timer m_timer = new Timer();
	Spark l_intake = new Spark(4);
	Spark r_intake = new Spark(3);
	Spark lift = new Spark(2);
	AnalogInput sensor = new AnalogInput(1);
	GyroBase gyro = new ADXRS450_Gyro();
	Compressor compressor = new Compressor();
	DoubleSolenoid sol = new DoubleSolenoid(0,1);

	double d_switch, timer, angle, intakeSpeed;
	boolean cross, JaCLOSE, JaFAR;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds
//		if (m_timer.get() < 2.0) {
//			m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
//		} else {
//		m_robotDrive.stopMotor(); // stop robot
	d_switch = sensor.getAverageValue();
	angle = gyro.getAngle();
	timer = m_timer.get();
	if(timer < 6) {
		m_robotDrive.curvatureDrive(-0.4, -0.05 * angle, false);
	}
	else {
		m_robotDrive.arcadeDrive(0, 0);
	}
	
	
	/*else if (d_switch > 700) {
		m_robotDrive.curvatureDrive(-0.4, -0.05 * angle, false);
	}
	else if (d_switch < 700) {
		m_robotDrive.arcadeDrive(0, 0);*/
		//m_timer.reset();
		//l_intake.set(1);
		//r_intake.set(-1);
		//}
		//if (timer >= 2 && timer <= 4) {
		//l_intake.set(-1);
		//r_intake.set(1);
		//}
		//else {
		//l_intake.set(0);
		//r_intake.set(0);
		//m_timer.reset();
		
	SmartDashboard.putNumber("Distance (Analog)", d_switch);
	SmartDashboard.putNumber("Angle", angle);

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
		
		compressor.start();
		
		JaCLOSE = m_stick.getRawButton(1);
		JaFAR = m_stick.getRawButton(2);
		
		d_switch = sensor.getAverageValue();
		intakeSpeed = l_stick.getRawAxis(1);
		
		
		lift.set(m_stick.getRawAxis(2)-m_stick.getRawAxis(3));
		
		/*if (m_stick.getRawButton(1)) {
		shoot.set(1);
		}*/
		
		//else if ()
		r_intake.set(intakeSpeed);
		l_intake.set(intakeSpeed);

		if (JaCLOSE) {
			sol.set(DoubleSolenoid.Value.kForward);
		}
		else if (JaFAR) {
			sol.set(DoubleSolenoid.Value.kReverse);
		}
		else {
			sol.set(DoubleSolenoid.Value.kOff);
		}
		
		SmartDashboard.putNumber("Distance (Analog)", d_switch);

		m_robotDrive.curvatureDrive(m_stick.getRawAxis(1) * 0.7, m_stick.getRawAxis(4) * 0.9, m_stick.getRawButton(5));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

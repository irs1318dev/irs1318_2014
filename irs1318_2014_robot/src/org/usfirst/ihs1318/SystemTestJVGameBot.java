package org.usfirst.ihs1318;

import java.util.TimerTask;
import java.util.Vector;

import org.usfirst.ihs1318.autonomous.RobotProcessAutonomousController;
import org.usfirst.ihs1318.discrete.AirCompressorController;
import org.usfirst.ihs1318.discrete.ArmController;
import org.usfirst.ihs1318.discrete.CameraController;
import org.usfirst.ihs1318.discrete.MiniBotController;
import org.usfirst.ihs1318.kinematics.AirCompressorKinematics;
import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.kinematics.CameraFlipKinematics;
import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.kinematics.MiniBotKinematics;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.LineSensorReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ButtonRef;

import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The best robot code you will EVER see. Runs 4Finger.
 * 
 * @author dowork 2011
 * 
 */
public class SystemTestJVGameBot extends IterativeRobot {
	private JoystickReader jsr;
	private WheelEncoderReader wheelEncoders;
	private MecanumDrive md;
	private WheelSpeedPID wheelPID;
	private TimerTask recordTicks;
	private TimerTask recordRates;

	private MiniBotKinematics miniKin;
	private MiniBotController mini;
	private ArmController armSolenoids;
	private AirCompressorKinematics airKin;
	private AirCompressorController airComp;
	private ArmPID armPID;
	private GyroReader gyro;
	private LineSensorReader lineSensorReader;

	private RobotProcessAutonomousController autonomousController;

	private DashboardOutput dash;
	private CameraFlipKinematics cfk;
	private CameraController cc;

	public void robotInit() {
		System.out.println("Initializing game bot..");
		getWatchdog().feed();
		TimerUtil.setStartTime();
		System.out.println("RobotInit()");
		wheelEncoders = new WheelEncoderReader();
		wheelEncoders.init();
		jsr = new JoystickReader();
		jsr.init();

		md = new MecanumDrive();
		md.init();
		wheelPID = new WheelSpeedPID();
		wheelPID.init();

		gyro = new GyroReader();
		gyro.init();

		cfk = new CameraFlipKinematics();
		cc = new CameraController();
		cc.init();
		
		airKin = new AirCompressorKinematics();

		airComp = new AirCompressorController();
		airComp.init();

		
		// turn on air by default
		synchronized (ReferenceData.getInstance()) {
			ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.AIR_JS][ButtonRef.AIR_ENABLE] = true;
		}
		airKin.calculateAirCompressorKinematics();
		airComp.determineStatus();

		miniKin = new MiniBotKinematics();

		mini = new MiniBotController();
		mini.init();
		armSolenoids = new ArmController();
		armSolenoids.init();
		armSolenoids.setEnabled(true);

		armPID = new ArmPID();
		armPID.init();

		autonomousController = new RobotProcessAutonomousController();
		autonomousController.init();

		lineSensorReader = new LineSensorReader();
		lineSensorReader.init();

		dash = new DashboardOutput();

		System.out.println("Robot initialized");

	}

	public void disabledInit() {
		autonomousController.reset();
	}

	public void autonomousInit() {
	}

	public void autonomousContinuous() {
		getWatchdog().feed();

//		if (autonomousController.isAutonomousFinished())
//			return;

		// //Read inputs
		wheelEncoders.readWheelEncoders();
		armPID.readEncoder();
		lineSensorReader.readSensors();
		gyro.readAngle();

		// run autonomous code
		autonomousController.run();

		// //Do calculations
		md.calculateWheelSpeeds();
		airKin.calculateAirCompressorKinematics();
		wheelPID.calculateCurrentWheelSpeed();
		armPID.calculateArmSpeed();

		// //Calculations using buttons get done last.
		airComp.determineStatus();

		// Make stuff go.
		armSolenoids.run(); // includes wrist and claw kinematics
		wheelPID.sendWheelSpeeds();
		armPID.sendSpeed();

		dash.sendData();
		updateHighPriDashboard(); // for camera
		updateLowPriDashboard(); // for voltage

	}

	public void teleopInit() {
		System.out.println("Full Bot--Teleop");
		autonomousController.reset();
	}

	public void teleopPeriodic() {
		getWatchdog().feed();
		jsr.readJoysticks();
		wheelEncoders.readWheelEncoders();

		gyro.readAngle();

		md.calculateWheelSpeeds();

		wheelPID.calculateCurrentWheelSpeed();
		wheelPID.sendWheelSpeeds();

		airKin.calculateAirCompressorKinematics();
		airComp.determineStatus();

		armSolenoids.getClawKin().calculateClawKinematics();
		armSolenoids.getClaw().setAsCurrent();

		armSolenoids.getWristKin().calculateWristValues();
		armSolenoids.getWrist().setAsCurrent();

		armSolenoids.getTargetHeight(); // no-op

		armPID.readEncoder();

		armPID.calculateArmSpeed();
		armPID.sendSpeed();

		miniKin.calculateMiniBot();
		mini.getDeployValues();

		dash.sendData();
		updateHighPriDashboard(); // for camera
		updateLowPriDashboard(); // for voltage
	}

	public void teleopDisabled() {
		updateHighPriDashboard(); // for camera
		updateLowPriDashboard(); // for voltage
	}

	public void autonomousDisabled() {
		autonomousController.reset();
		updateHighPriDashboard(); // for camera
		updateLowPriDashboard(); // for voltage

	}

	/**
	 * Update the high priority dashboard data.
	 */
	private void updateHighPriDashboard() {
		Dashboard dashBoard = DriverStation.getInstance()
				.getDashboardPackerHigh();

		dashBoard.addCluster();
		{

			dashBoard.addCluster();
			{ // Target Info
				dashBoard.addArray();
				{ // Targets
					dashBoard.addCluster();
					{
						dashBoard.addDouble(0.0); // Target Score
						dashBoard.addCluster();
						{ // Circle Descriptor
							dashBoard.addCluster();
							{ // Position
								dashBoard.addFloat((float) 0.0); // X
								dashBoard.addFloat((float) 0.0); // Y
							}
							dashBoard.finalizeCluster();

							dashBoard.addDouble(0.0); // Angle
							dashBoard.addDouble(0.0); // Major Radius
							dashBoard.addDouble(0.0); // Minor Radius
							dashBoard.addDouble(1.0); // Raw Score
						}
						dashBoard.finalizeCluster();
					}
					dashBoard.finalizeCluster();
				}
				dashBoard.finalizeArray();

				dashBoard.addInt(0);
			}
			dashBoard.finalizeCluster();
		}
		dashBoard.finalizeCluster();

		dashBoard.commit();
	}

	/**
	 * Update the low priority dashboard data.
	 */
	private void updateLowPriDashboard() {
		Dashboard lowDashData = DriverStation.getInstance()
				.getDashboardPackerLow();

		lowDashData.addCluster();
		{
			lowDashData.addCluster();
			{
				lowDashData.addCluster();
				{ // all analog modules
					lowDashData.addCluster();
					{
						for (int i = 1; i <= 8; i++) {
							lowDashData.addFloat((float) AnalogModule
									.getInstance(1).getAverageVoltage(i));
						}
					}
					lowDashData.finalizeCluster();

					lowDashData.addCluster();
					{
						for (int i = 1; i <= 8; i++) {
							lowDashData.addFloat((float) AnalogModule
									.getInstance(2).getAverageVoltage(i));
						}
					}
					lowDashData.finalizeCluster();
				}
				lowDashData.finalizeCluster();

				lowDashData.addCluster();
				{ // digital modules
					lowDashData.addCluster();
					{ // digital modules
						lowDashData.addCluster();
						{ // digital module port 4
							int module = 4;
							lowDashData.addByte(DigitalModule.getInstance(
									module).getRelayForward());
							lowDashData.addByte(DigitalModule.getInstance(
									module).getRelayReverse());

							lowDashData.addShort(ReverseBits(DigitalModule
									.getInstance(module).getAllDIO()));
							lowDashData.addShort(ReverseBits(DigitalModule
									.getInstance(module).getDIODirection()));

							lowDashData.addCluster();
							{
								for (int i = 1; i <= 10; i++) {
									lowDashData.addByte((byte) DigitalModule
											.getInstance(module).getPWM(i));
								}
							}
							lowDashData.finalizeCluster();
						}
						lowDashData.finalizeCluster();
					}
					lowDashData.finalizeCluster();

					lowDashData.addCluster();
					{
						lowDashData.addCluster();
						{
							int module = 6;
							lowDashData.addByte(DigitalModule.getInstance(
									module).getRelayForward());
							lowDashData.addByte(DigitalModule.getInstance(
									module).getRelayReverse());

							lowDashData.addShort(ReverseBits(DigitalModule
									.getInstance(module).getAllDIO()));
							lowDashData.addShort(ReverseBits(DigitalModule
									.getInstance(module).getDIODirection()));

							lowDashData.addCluster();
							{
								for (int i = 1; i <= 10; i++) {
									lowDashData.addByte((byte) DigitalModule
											.getInstance(module).getPWM(i));
								}
							}
							lowDashData.finalizeCluster();
						}
						lowDashData.finalizeCluster();
					}
					lowDashData.finalizeCluster();
				}
				lowDashData.finalizeCluster();

				// TODO:
				// lowDashData.addByte(Solenoid.getAllFromDefaultModule());
			}
			lowDashData.finalizeCluster();
		}
		lowDashData.finalizeCluster();

		lowDashData.commit();
	}

	/**
	 * This function reverses the bits of a short.
	 * 
	 * @param source
	 *            The short who's bits need to be reversed.
	 * @return A short with the bits in the reverse order of the source
	 *         parameter.
	 */
	private static short ReverseBits(short source) {
		short result = 0;

		for (int i = 0; i < 16; i++) {
			short sourceLowBit = (short) (source & 1);
			result = (short) (result << 1);
			result = (short) (result | sourceLowBit);
			source = (short) (source >>> 1);
		}

		return (short) result;
	}
}

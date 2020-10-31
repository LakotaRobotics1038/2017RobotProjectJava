package frc.robotLibraries;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

import frc.robotLibraries.CANSpark1038;;

public class DriveTrain1038 implements Subsystem {
    public enum DriveModes {
        tankDrive, singleArcadeDrive, dualArcadeDrive
    };

    public DriveModes currentDriveMode = DriveModes.dualArcadeDrive;

    //Change these numbers for each new robot       v
    public final double WHEEL_DIAMETER = 4;
    private final int HIGH_GEAR_PORT = 0;
    private final int LOW_GEAR_PORT = 1;
    private final static int RIGHT_FRONT_PORT = 0;
    private final static int RIGHT_BACK_PORT = 0;
    private final static int LEFT_FRONT_PORT = 0;
    private final static int LEFT_BACK_PORT = 0;
    //Change these numbers for each new robot       ^


    public DoubleSolenoid GearChangeSolenoid = new DoubleSolenoid(LOW_GEAR_PORT, HIGH_GEAR_PORT);
    public boolean isHighGear = false;

    public static Spark rightFront = new Spark(RIGHT_FRONT_PORT);
    public static Spark leftFront = new Spark(LEFT_FRONT_PORT);
    public static Spark rightBack = new Spark(RIGHT_BACK_PORT);
    public static Spark leftBack = new Spark(LEFT_FRONT_PORT);

    private DifferentialDrive differentialDrive;
    private static DriveTrain1038 driveTrain;

    public static DriveTrain1038 getInstance() {
        if (driveTrain == null) {
            System.out.println("Creating a new DriveTrain");
            driveTrain = new DriveTrain1038();
        }
        return driveTrain;
    }

    private DriveTrain1038() {

        leftBack.setInverted(true);
        leftFront.setInverted(true);
        rightBack.setInverted(true);
        rightFront.setInverted(true);

        

        rightFront.
        leftFront.follow(leftBack);
        
        differentialDrive = new DifferentialDrive(CANSparkLeftBack, CANSparkRightBack);
    }

    // Get and return distance driven by the left of the robot in inches
    public double getLeftDriveEncoderDistance() {
        return CANSparkLeftEncoder.getPosition() * Math.PI * WHEEL_DIAMETER;
    }

    // Get and return distance driven by the right of the robot in inches
    public double getRightDriveEncoderDistance() {
        return CANSparkRightEncoder.getPosition() * Math.PI * WHEEL_DIAMETER;
    }

    public double getCANSparkRightEncoder() {
        return -CANSparkRightEncoder.getPosition();
    }

    public double getCANSparkLeftEncoder() {
        return -CANSparkLeftEncoder.getPosition();
    }

    // Pneumatics
    public void highGear() {
        isHighGear = true;
        GearChangeSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void lowGear() {
        isHighGear = false;
        GearChangeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void resetEncoders() {
        CANSparkRightEncoder.setPosition(0);
        CANSparkLeftEncoder.setPosition(0);
    }

    // Switch between drive modes
    public void driveModeToggler() {
        switch (currentDriveMode) {
        case tankDrive:
            currentDriveMode = DriveModes.singleArcadeDrive;
            break;
        case singleArcadeDrive:
            currentDriveMode = DriveModes.dualArcadeDrive;
            break;
        case dualArcadeDrive:
            currentDriveMode = DriveModes.tankDrive;
            break;
        default:
            System.out.println("Help I have fallen and I can't get up!");
            break;
        }
    }

    // Drive robot with tank controls (input range -1 to 1 for each stick)
    public void tankDrive(double leftStickInput, double rightStickInput) {
        differentialDrive.tankDrive(leftStickInput, rightStickInput, true);
    }

    // Drive robot using a single stick (input range -1 to 1)
    public void singleAracadeDrive(double speed, double turnValue) {
        differentialDrive.arcadeDrive(speed, turnValue, true);
    }

    // Drive robot using 2 sticks (input ranges -1 to 1)
    public void dualArcadeDrive(double yaxis, double xaxis) {
        differentialDrive.arcadeDrive(yaxis, xaxis, true);
    }
}
package frc.robotLibraries;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;


public class DriveTrain1038 implements Subsystem {
    public enum DriveModes {
        tankDrive, singleArcadeDrive, dualArcadeDrive
    };

    public DriveModes currentDriveMode = DriveModes.dualArcadeDrive;

    //Change these numbers for each new robot       v
    private final int HIGH_GEAR_PORT = 0;
    private final int LOW_GEAR_PORT = 1;
    private final static int RIGHT_SPARKS_PORT = 1;
    private final static int LEF_SPARKS_PORT = 0;
    //Change these numbers for each new robot       ^


    public DoubleSolenoid GearChangeSolenoid = new DoubleSolenoid(LOW_GEAR_PORT, HIGH_GEAR_PORT);
    public boolean isHighGear = false;

    public static Spark rightSpark = new Spark(RIGHT_SPARKS_PORT);
    public static Spark leftSpark = new Spark(LEF_SPARKS_PORT);

    private final DifferentialDrive differentialDrive;
    private static DriveTrain1038 driveTrain;

    public static DriveTrain1038 getInstance() {
        if (driveTrain == null) {
            System.out.println("Creating a new DriveTrain");
            driveTrain = new DriveTrain1038();
        }
        return driveTrain;
    }

    private DriveTrain1038() {

        leftSpark.setInverted(false);
        rightSpark.setInverted(false);

        
        differentialDrive = new DifferentialDrive(leftSpark, rightSpark);
    }


    // Pneumatics
    public void gearToggle() {
        if(!isHighGear){ 
            highGear();
        }
        else {
            lowGear();
        }
    }
    public void highGear() {
        isHighGear = true;
        GearChangeSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void lowGear() {
        isHighGear = false;
        GearChangeSolenoid.set(DoubleSolenoid.Value.kReverse);
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
    public void tankDrive(final double leftStickInput, final double rightStickInput) {
        differentialDrive.tankDrive(leftStickInput, rightStickInput, true);
    }

    // Drive robot using a single stick (input range -1 to 1)
    public void singleAracadeDrive(final double speed, final double turnValue) {
        differentialDrive.arcadeDrive(speed, turnValue, true);
    }

    // Drive robot using 2 sticks (input ranges -1 to 1)
    public void dualArcadeDrive(final double yaxis, final double xaxis) {
        differentialDrive.arcadeDrive(yaxis, xaxis, true);
    }
}
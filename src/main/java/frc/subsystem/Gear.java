package frc.subsystem;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
public class Gear {
    public static Gear gear;
    public static Gear getInstance() {
        if (gear == null) {
            System.out.println("Creating a new Squirrel");
            gear = new Gear();
        }
        return gear;
    }
    private final int RAISED_PORT = 0;
    private final int LOWERED_PORT = 1;
    private final int EJECT_ON = 3;
    private final int EJECT_OFF = 2;
    //Change these numbers for each new robot       ^


    public DoubleSolenoid GearChangeSolenoid = new DoubleSolenoid(LOWERED_PORT, RAISED_PORT);
    public boolean isRaised = false;

    public DoubleSolenoid EjectChangeSolenoid = new DoubleSolenoid(EJECT_OFF, EJECT_ON);
    public boolean isEjected = false;


    

   


    // Pneumatics
    public void gateToggle() {
        if(!isRaised){ 
            raise();
        }
        else {
            lower();
        }
    }
    private void raise() {
        //raise the gate
        isRaised = true;
        GearChangeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
        
    private void lower() {
        //lower the gate
        isRaised = false;
        GearChangeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void ejectToggle() {
        if(!isEjected){ 
            eject();
        }    
        else {
            dontEject();
        }
    }
    private void eject() {
        //raise the gate
        isEjected = true;
        EjectChangeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
        
    private void dontEject() {
        //lower the gate
        isEjected = false;
        EjectChangeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
        
}
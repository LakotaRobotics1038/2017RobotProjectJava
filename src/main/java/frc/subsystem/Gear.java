package frc.subsystem;
public class Gear {
    public static Gear gear;
    public static Gear getInstance() {
        if (gear == null) {
            System.out.println("Creating a new DriveTrain");
            gear = new Gear();
        }
        return gear;
    }
    private void raise() {
        //raise the gate
    }
        
    private void lower() {
        //lower the gate
    }
        
    public void eject() {
        //run the raise command
        //eject the gear
        //run the lower command
    }
}
package frc.subsystem;
public class Fuel {
    public static Fuel fuel;
    public static Fuel getInstance() {
        if (fuel == null) {
            System.out.println("Creating a new DriveTrain");
            fuel = new Fuel();
        }
        return fuel;
    }
    public void acquire() {
        //Run Acquisition Bar
    }
    
    public void shoot() {
        //run shooter motors(make code for binary and analog, comment one out)
        //check if shooters are up to speed(use bampas as reference)
        //rumble operator remote if up to speed
        //maybe leds at full screen?
    }
    
    public void feed() {
        //Run Acquisition Bar
        //Check if the shooter is running(maybe check for specific speed?)
        //Run Feeder Bar if shooter is running
    }
}

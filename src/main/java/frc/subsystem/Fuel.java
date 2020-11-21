package frc.subsystem;


import edu.wpi.first.wpilibj.Spark;
public class Fuel {
    public static Fuel fuel;
    public static Fuel getInstance() {
        if (fuel == null) {
            System.out.println("Creating a new DriveTrain");
            fuel = new Fuel();
        }
        return fuel;
    }
    Spark acquisition = new Spark(5);
    Spark leftShooter = new Spark(2);
    Spark rightShooter = new Spark(3);
    Spark feeder = new Spark(4);


    
    public void acquire(int speed) {
        //Run Acquisition Bar
        acquisition.set(speed);
    }
    
    public void shoot(double speed) {
        //run shooter motors(make code for binary and analog, comment one out)
        //check if shooters are up to speed(use bampas as reference)
        //rumble operator remote if up to speed
        //maybe leds at full screen?
        leftShooter.set(speed);
        rightShooter.set(speed * -1);
    }
    
    public void feed(int speed) {
        //Run Acquisition Bar
        //Check if the shooter is running(maybe check for specific speed?)
        //Run Feeder Bar if shooter is running
        acquisition.set(speed);
        feeder.set(speed);
    }
}

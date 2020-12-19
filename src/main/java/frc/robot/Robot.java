/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.subsystem.auton.*;
//import jdk.javadoc.internal.doclets.formats.html.resources.standard;
import frc.robotLibraries.DriveTrain1038;
import frc.robotLibraries.Joystick1038;
import frc.subsystem.Fuel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  private Joystick1038 driverJoystick = new Joystick1038(0);
  private Joystick1038 operatorJoystick = new Joystick1038(1);
  public static DriveTrain1038 robotDrive = DriveTrain1038.getInstance();
  public static Fuel fuel = new Fuel();
  private Auton auton = new Auton();
  private CommandScheduler schedule = CommandScheduler.getInstance();
  public static SequentialCommandGroup autonPath;
  

  boolean previousGearState = false;
  boolean previousPTOState = false;
  boolean singleController = false;
  boolean previousControllerState = false;

  @Override
  public void robotInit() {
        autonPath = new GearAuton();
        schedule.schedule(autonPath);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(driverJoystick.getLineButton() && operatorJoystick.getLineButton() && !previousControllerState) {
     previousControllerState = true;
     if (driverJoystick.controllerToggle(singleController)){
       singleController=true;
     }
     else{
       singleController=false;
     }
    }
    if(previousControllerState == true && !driverJoystick.getLineButton()) {
		previousControllerState = false;
    }
    if(singleController)
    {
      if(driverJoystick.getLeftButton() && !previousPTOState) {
        previousPTOState = true;
        robotDrive.PTOToggle();
      }
      if(!driverJoystick.getLeftButton()) {
        previousPTOState = false;
      }
      if(robotDrive.isPTO) {
        robotDrive.PTOControl(driverJoystick.getLeftJoystickVertical());
      }
      else {
        robotDrive.dualArcadeDrive((driverJoystick.getLeftJoystickVertical()*.7), (driverJoystick.getRightJoystickHorizontal()));
        if(driverJoystick.getRightButton() && !previousGearState) {
          previousGearState = true;
          robotDrive.gearToggle();
        }
        if(!driverJoystick.getRightButton()) {
          previousGearState = false;
        }
      }
      
      fuel.shoot(driverJoystick.getRightTrigger());
      if(driverJoystick.getBButton()) {
        fuel.acquire(driverJoystick.intBButton());
      }
      else {
        if(driverJoystick.getLeftTriggerDigital())
          {fuel.feed(1);}
        else
          {fuel.feed(0);}
      }
    }
    else
    { 
      if(driverJoystick.getLeftTriggerDigital() && !previousPTOState) {
      previousPTOState = true;
      robotDrive.PTOToggle();
    }
    if(!driverJoystick.getLeftTriggerDigital()) {
      previousPTOState = false;
    }
    if(robotDrive.isPTO) {
      robotDrive.PTOControl(driverJoystick.getLeftJoystickVertical());
    }
    else {
      robotDrive.dualArcadeDrive((driverJoystick.getLeftJoystickVertical()*.7), (driverJoystick.getRightJoystickHorizontal()));
      if(driverJoystick.getRightTriggerDigital() && !previousGearState) {
        previousGearState = true;
        robotDrive.gearToggle();
      }
      if(!driverJoystick.getRightTriggerDigital()) {
        previousGearState = false;
      }
    }
    
    fuel.shoot(operatorJoystick.getRightTrigger());
    if(operatorJoystick.getBButton()) {
      fuel.acquire(operatorJoystick.intBButton());
    }
    else {
      if(operatorJoystick.getLeftTriggerDigital())
        {fuel.feed(1);}
      else
        {fuel.feed(0);}
    }}
    if(operatorJoystick.getAButton()) {
        schedule.run();
      }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledInit() {
    if(robotDrive.isPTO) {        //PTO off to not break robot
      robotDrive.PTOOff();
    }
  }

}

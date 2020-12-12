package frc.subsystem.auton.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystem.Gear;

public class GateCommands extends CommandBase {

  private Gear gear = Gear.getInstance();
  private double timeBoi = 0;

  private static double END_TIME = .25;
  /**
   * Creates a new MoveAcquisitionCommand.
   */
  public GateCommands() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeBoi = Timer.getMatchTime();
    gear.gateToggle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getMatchTime() - timeBoi >= END_TIME;
  }
}
package frc.subsystem.auton;
import frc.subsystem.auton.Auton;
import frc.subsystem.auton.Commands.EjectCommands;
import frc.subsystem.auton.Commands.GateCommands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;


public class GearAuton extends Auton{
    public GearAuton() {
        super();
        
        addCommands(
            new GateCommands(),
            new EjectCommands(),
            new ParallelCommandGroup(
                new EjectCommands(),
                new GateCommands()
            )
        );
    }
}

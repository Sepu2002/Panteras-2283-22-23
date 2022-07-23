package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.math.controller.PIDController;

public class ElevatorPIDCmd extends CommandBase{
    private final ElevatorSubsystem elevatorSubsystem;
    private final PIDController pidController;

    public ElevatorPIDCmd(ElevatorSubsystem elevatorSubsystem, double setpoint){
       this.elevatorSubsystem = elevatorSubsystem;
       this.pidController = new PIDController(3,0,0.8);
       pidController.setSetpoint(setpoint);
       addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize(){
        pidController.reset();

    }
    @Override
    public void execute(){
        double speed = pidController.calculate(elevatorSubsystem.getEncoderMeters());
        elevatorSubsystem.setMotor(speed);
    }
    @Override
    public void end(boolean interrupted){
        elevatorSubsystem.setMotor(0);
        System.out.println("ElevatorPIDCmd ended!");

    }
    @Override
    public boolean isFinished(){
        return false;
    }
    
}

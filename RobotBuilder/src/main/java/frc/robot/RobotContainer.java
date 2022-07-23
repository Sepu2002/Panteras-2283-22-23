// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.DriveForwardCmd;
import frc.robot.commands.ElevatorJoystickCmd;
import frc.robot.commands.ElevatorPIDCmd;
import frc.robot.commands.IntakeSetCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private final Joystick joystick1 = new Joystick(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    driveSubsystem.setDefaultCommand(new ArcadeDriveCmd(driveSubsystem, () -> -joystick1.getRawAxis(1), () -> joystick1.getRawAxis(3)));
    elevatorSubsystem.setDefaultCommand(new ElevatorPIDCmd(elevatorSubsystem, 0));
    intakeSubsystem.setDefaultCommand(new IntakeSetCmd(intakeSubsystem, true));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton (joystick1, 1).whileActiveOnce(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kRaisedPosition));
    new JoystickButton (joystick1, 2).whileActiveOnce(new ElevatorPIDCmd(elevatorSubsystem, 0));
    new JoystickButton(joystick1, 3).whileActiveOnce(new ElevatorJoystickCmd(elevatorSubsystem, 0.5));
    new JoystickButton(joystick1, 4).whileActiveOnce(new ElevatorJoystickCmd(elevatorSubsystem, -0.5));
    new JoystickButton(joystick1, 5).whileActiveOnce(new IntakeSetCmd(intakeSubsystem, false)); 
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(
              new DriveForwardCmd(driveSubsystem,1.5),//

              new ParallelCommandGroup(
                  new IntakeSetCmd(intakeSubsystem, false), new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kRaisedPosition)//
              )//
    );
  }
}

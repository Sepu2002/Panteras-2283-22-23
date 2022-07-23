// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final Spark intakeLeftMotor = new Spark(3);
  private final Spark intakeRightMotor= new Spark(4);
  
  public IntakeSubsystem() {}

  public void setPosition(boolean open){
    if(open){
      intakeLeftMotor.set(-1);
      intakeRightMotor.set(-1);
    }else{
      intakeLeftMotor.set(1);
      intakeRightMotor.set(1);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

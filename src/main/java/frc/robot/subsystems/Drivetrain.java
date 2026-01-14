// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  TalonSRX backright = new TalonSRX(10);
  TalonSRX frontright = new TalonSRX(11);
  TalonSRX frontleft = new TalonSRX(12);
  TalonSRX backleft = new TalonSRX(13);
  XboxController controller = new XboxController(0);
  double speed = 0.5;

  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    backright.setInverted(false);
    frontright.setInverted(false);
    backleft.setInverted(true);
    frontleft.setInverted(true);

    Constants.sendNumberToElastic("Speed", speed, 3);
  }

  @Override
  public void periodic() {
    double drivespeed = controller.getLeftY() * speed;
    double turnspeed = controller.getRightX() * speed;
    if (Math.abs(drivespeed) < 0.1 && Math.abs(turnspeed) < 0.1) {
      turnspeed = 0;
      drivespeed = 0;
    }
    backright.set(TalonSRXControlMode.PercentOutput, drivespeed + turnspeed);
    frontright.set(TalonSRXControlMode.PercentOutput, drivespeed + turnspeed);
    frontleft.set(TalonSRXControlMode.PercentOutput, drivespeed - turnspeed);
    backleft.set(TalonSRXControlMode.PercentOutput, drivespeed - turnspeed);

    updateEntries();
  }

  private void updateEntries() {
    speed = SmartDashboard.getNumber("Speed", 0);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

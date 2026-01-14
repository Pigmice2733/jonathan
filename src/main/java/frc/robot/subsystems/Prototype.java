package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Prototype extends SubsystemBase {
    SparkMax motorOne = new SparkMax(14, MotorType.kBrushless);
    SparkMax motorTwo = new SparkMax(15, MotorType.kBrushless);
    double motorSpeed;

    public Prototype() {
        motorOne.configure(new SparkMaxConfig().inverted(false).idleMode(IdleMode.kBrake),
                ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorTwo.configure(new SparkMaxConfig().inverted(false).idleMode(IdleMode.kBrake),
                ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {
        setSpeeds();

        updateEntries();
    }

    private void updateEntries() {
        Constants.sendNumberToElastic("Motor 1 Speed", motorOne.get(), 3);
        Constants.sendNumberToElastic("Motor 2 Speed", motorTwo.get(), 3);
        Constants.sendNumberToElastic("Motor Setpoint", motorSpeed, 3);

        // motorSpeed = SmartDashboard.getNumber("Motor Setpoint", 0);
    }

    public void setSpeeds() {
        motorOne.set(motorSpeed);
        motorTwo.set(motorSpeed);
    }

    public void setMotors(double speed) {
        motorSpeed = speed;
    }

    public void stopMotors() {
        motorSpeed = 0;
    }
}

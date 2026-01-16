package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Prototype extends SubsystemBase {
    SparkMax motorOne = new SparkMax(14, MotorType.kBrushless);
    SparkMax motorTwo = new SparkMax(15, MotorType.kBrushless);
    double motorOneSpeed;
    double motorTwoSpeed;

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
        Constants.sendNumberToElastic("Motor 1 Setpoint", motorOneSpeed, 3);
        Constants.sendNumberToElastic("Motor 2 Setpoint", motorTwoSpeed, 3);

        // motorSpeed = SmartDashboard.getNumber("Motor Setpoint", 0);
    }

    /**
     * Sets the speed of both motors to the target value
     */
    public void setSpeeds() {
        motorOne.set(motorOneSpeed);
        motorTwo.set(motorTwoSpeed);
    }

    public void incrementMotors(double delta) {
        motorOneSpeed += delta;
        motorTwoSpeed += delta;
        motorOneSpeed = MathUtil.clamp(motorOneSpeed, -1.0, 1.0);
        motorTwoSpeed = MathUtil.clamp(motorTwoSpeed, -1.0, 1.0);
    }

    /**
     * Sets the target speed of both motors
     * 
     * @param speed The target speed
     */
    public void setMotors(double speed) {
        motorOneSpeed = speed;
        motorTwoSpeed = speed;
    }

    /**
     * Sets the target speed of both motors to 0
     */
    public void stopMotors() {
        motorOneSpeed = 0;
        motorTwoSpeed = 0;
    }

    public void setMotor(double speed, boolean motorOne) {
        if (motorOne == true) {
            motorOneSpeed = speed;
        } else {
            motorTwoSpeed = speed;
        }
    }

    public void stopMotor(boolean motorOne) {
        if (motorOne == true) {
            motorOneSpeed = 0;
        } else {
            motorTwoSpeed = 0;
        }
    }

    public void incrementMotor(double delta, boolean motorOne) {
        if (motorOne == true) {
            motorOneSpeed += delta;
        } else {
            motorTwoSpeed += delta;
        }
    }
}

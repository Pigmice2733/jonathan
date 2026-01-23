package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PrototypeConfig;

public class Prototype extends SubsystemBase {
    PrototypeNeo motorOne;
    PrototypeNeo motorTwo;

    public Prototype() {
        motorOne = new PrototypeNeo(PrototypeConfig.MOTOR_ONE_ID, MotorType.kBrushless, false, IdleMode.kBrake,
                ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorTwo = new PrototypeNeo(PrototypeConfig.MOTOR_TWO_ID, MotorType.kBrushless, false, IdleMode.kBrake,
                ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {
        setSpeeds();

        updateEntries();
    }

    private void updateEntries() {
        Constants.sendNumberToElastic("Motor 1 Speed", motorOne.motor.get(), 3);
        Constants.sendNumberToElastic("Motor 2 Speed", motorTwo.motor.get(), 3);
        Constants.sendNumberToElastic("Motor 1 Setpoint", motorOne.targetSpeed, 3);
        Constants.sendNumberToElastic("Motor 2 Setpoint", motorTwo.targetSpeed, 3);
        Constants.sendNumberToElastic("Motor 1 RPM", motorOne.motor.getEncoder().getVelocity(), 2);
        Constants.sendNumberToElastic("Motor 2 RPM", motorTwo.motor.getEncoder().getVelocity(), 2);

        // motorSpeed = SmartDashboard.getNumber("Motor Setpoint", 0);
    }

    /**
     * Sets the speed of both motors to the target value
     */
    public void setSpeeds() {
        motorOne.setSpeed();
        motorTwo.setSpeed();
    }

    /**
     * Changes the target speed of both motors by a given amount
     * 
     * @param delta The change in target speed
     */
    public void incrementMotors(double delta) {
        motorOne.incrementMotor(delta);
        motorTwo.incrementMotor(delta);
    }

    /**
     * Sets the target speed of both motors
     * 
     * @param speed The target speed
     */
    public void setMotors(double speed) {
        motorOne.setMotor(speed);
        motorTwo.setMotor(speed);
    }

    /**
     * Sets the target speed of both motors to 0
     */
    public void stopMotors() {
        motorOne.stopMotor();
        motorTwo.stopMotor();
    }

    /**
     * Sets the target speed of a given motor to a given value
     * 
     * @param speed The target speed of the motor
     * @param one   Which motor to set, with true being 1 and false being 2
     */
    public void setMotor(double speed, boolean one) {
        if (one == true) {
            motorOne.setMotor(speed);
        } else {
            motorTwo.setMotor(speed);
        }
    }

    /**
     * Sets the target speeed of a given motor to 0
     * 
     * @param one Which motor to set, with true being 1 and false being 2
     */
    public void stopMotor(boolean one) {
        if (one == true) {
            motorOne.stopMotor();
        } else {
            motorTwo.stopMotor();
        }
    }

    /**
     * Changes the target speed of a given motor by a given amount
     * 
     * @param delta The change in target speed
     * @param one   Which motor to set, with true being 1 and false being 2
     */
    public void incrementMotor(double delta, boolean one) {
        if (one == true) {
            motorOne.incrementMotor(delta);
        } else {
            motorTwo.incrementMotor(delta);
        }
    }
}

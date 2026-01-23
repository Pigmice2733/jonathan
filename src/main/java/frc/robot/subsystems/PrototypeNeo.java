package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PrototypeNeo extends SubsystemBase {
    SparkMax motor;
    double targetSpeed;

    public PrototypeNeo(int id, MotorType type, boolean inverted, IdleMode idleMode, ResetMode resetMode,
            PersistMode persistMode) {
        motor = new SparkMax(id, type);
        motor.configure(new SparkMaxConfig().inverted(inverted).idleMode(IdleMode.kBrake), resetMode, persistMode);
    }

    public void setSpeed() {
        motor.set(targetSpeed);
    }

    public void setMotor(double speed) {
        targetSpeed = speed;
    }

    public void incrementMotor(double increment) {
        targetSpeed += increment;
        targetSpeed = MathUtil.clamp(targetSpeed, -1.0, 1.0);
    }

    public void stopMotor() {
        targetSpeed = 0;
    }
}

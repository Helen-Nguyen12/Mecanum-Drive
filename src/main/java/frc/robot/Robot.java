// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.button.Button;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 0;
  private static final int kRearLeftChannel = 2;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 3;
  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick = new Joystick(kJoystickChannel);
  
  private JoystickButton flyWheelRun;

  // private final PowerDistribution PDP = new PowerDistribution();

  VictorSP frontLeft = new VictorSP(kFrontLeftChannel);
  VictorSP rearLeft = new VictorSP(kRearLeftChannel);
  VictorSP frontRight = new VictorSP(kFrontRightChannel);
  VictorSP rearRight = new VictorSP(kRearRightChannel);

   CANSparkMax flyWheel = new CANSparkMax(1, MotorType.kBrushless);

  @Override
  public void robotInit() {
    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);
    flyWheel.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick =  new Joystick(kJoystickChannel);
    flyWheel.set(0);

  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.

  

    SmartDashboard.getNumber("Fly Wheel", flyWheel.get());
    
    m_robotDrive.driveCartesian(m_stick.getX() * 0.1, m_stick.getY() * 0.1, m_stick.getZ() * 0.1, 0.0);


  //  if (m_stick.whileHeld(4)){
  //   flyWheel.set(0.5);
  //  } 
  //  else {
  //    flyWheel.set(0);
  //  }


   while (m_stick.getRawButtonPressed(4)){
     flyWheel.set(0.5);
   }
    
  }

 
}

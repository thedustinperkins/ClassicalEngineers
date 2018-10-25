package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * 2018 Classical Engineers 8579
 * Bedford FTC Robotics
 * Temperance, MI
 */

@Autonomous(name="Autonomous", group="Linear Opmode")
@Disabled
public class Auton extends LinearOpMode{

    // Declare Motors
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor liftDrive = null;
    //private Servo latch = null;


    @Override
    public void runOpMode() throws InterruptedException {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        liftDrive = hardwareMap.dcMotor.get("liftDrive");

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Wait for the game to start
        waitForStart();

        //Go go gadget robot! Don't blow up!
        //TETRIX_TICKS_PER_REV = 1440;
    }
    public void LiftArmUp(double power)
    {
        liftDrive.setPower(power);
    }
    public void LiftArmDown(double power)
    {
        liftDrive.setPower(-power);
    }
    public void DriveForward(double power)
    {
        leftDrive.setPower(power);
        rightDrive.setPower(power);
    }

    public void StopDriving(double power)
    {
        DriveForward(0);
    }

    public void DriveForwardDistance(double power, int distance)
    {
        //Reset encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set target position
        leftDrive.setTargetPosition(distance);
        rightDrive.setTargetPosition(distance);

        //Set to RUN_TO_POSITION mode
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveForward(power);

        while(leftDrive.isBusy() && rightDrive.isBusy())
        {
            //Wait until target position is reached.
        }

        //Stop and change modes back to normal
        StopDriving(0);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void TurnLeft(double power)
    {
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
    }
    public void TurnRight(double power)
    {
        leftDrive.setPower(power);
        rightDrive.setPower(-power);
    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.io.InterruptedIOException;


/**
 * 2018 Classical Engineers 8579
 * Bedford FTC Robotics
 * Temperance, MI
 */

@Autonomous(name="EncoderTest", group="Linear Opmode")
@Disabled
public class EncoderTest extends LinearOpMode{

    // Declare Motors
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Wait for the game to start
        waitForStart();

        //Go go gadget robot! Don't blow up!
        //TETRIX_TICKS_PER_REV = 1440;
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
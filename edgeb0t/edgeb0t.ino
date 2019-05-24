/*
    LineFollower Program
    Colin Mettler
*/

#include <Arduino.h>
#include <Wire.h>
#include <SoftwareSerial.h>
#include <MeMCore.h>

MeDCMotor motor_9(9); // Motor Pin
MeDCMotor motor_10(10); // Motor Pin

void move(int direction, int speed) {
  int leftSpeed = 0;
  int rightSpeed = 0;
  if (direction == 0) { // line sensor reads 0, move forward
    leftSpeed = -speed;
    rightSpeed = speed;
  } else if (direction == 1) { // line sensor reads 1, turn left
    leftSpeed = -speed;
    rightSpeed = speed;
  } else if (direction == 2) { // line sensor reads 2, turn right
    leftSpeed = speed;
    rightSpeed = speed;
  } else if (direction == 3) { // line sensor reads 3, move backward
    leftSpeed = speed;
    rightSpeed = -speed;
  }
  motor_9.run((9) == M1 ? -(leftSpeed) : (leftSpeed));
  motor_10.run((10) == M1 ? -(rightSpeed) : (rightSpeed));
}

double angle_rad = PI / 180.0;
double angle_deg = 180.0 / PI;
MeUltrasonicSensor ultrasonic_3(3); // Define ultrasonic sensor for use
MeRGBLed rgbled_7(7, 7==7?2:4); // Define on board LED strip for use
MeLineFollower linefollower_2(2); // Define linefollower sensor for use

void setup() {
  pinMode(A7, INPUT);
  while (!((0 ^ (analogRead(A7) > 10 ? 0 : 1)))) // wait until onboard button is pressed to start program
  {
    _loop();
  }
  _delay(1); // delay for 1 second
}

void loop() {
  move(linefollower_2.readSensors(), 200); // get direction from sensor reading, passed to move() function, at speed
  _loop();
}

void _delay(float seconds) {
  long endTime = millis() + seconds * 1000;
  while (millis() < endTime) _loop();
}

void _loop() {
}

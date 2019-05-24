/*
    Explore Cautiously Program
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
  if (direction == 1) {
    leftSpeed = speed;
    rightSpeed = speed;
  } else if (direction == 2) {
    leftSpeed = -speed;
    rightSpeed = -speed;
  } else if (direction == 3) {
    leftSpeed = -speed;
    rightSpeed = speed;
  } else if (direction == 4) {
    leftSpeed = speed;
    rightSpeed = -speed;
  }
  motor_9.run((9) == M1 ? -(leftSpeed) : (leftSpeed));
  motor_10.run((10) == M1 ? -(rightSpeed) : (rightSpeed));
}

double angle_rad = PI / 180.0;
double angle_deg = 180.0 / PI;
MeUltrasonicSensor ultrasonic_3(3); // Define ultrasonic sensor for use

void setup() {
  pinMode(A7, INPUT);
  while (!((0 ^ (analogRead(A7) > 10 ? 0 : 1)))) // wait until onboard button is pressed to start program
  {
    _loop();
  }
  _delay(1); // delay for 1 second
}

void loop() {
  if (ultrasonic_3.distanceCm() > 15) { // if distance is greater than 15 cm
    move(1, 100); // move forward at speed 100
  } else { // if distance is less than or equal to 15 cm
    move(1, 0); // move with speed 0, AKA stop
    _delay(0.5); // delay for 1/2 of a second
    move(3, 100); // turn left at speed 100 (speed will change based on trial determining what is necessary to turn 90 degrees)
    _delay(0.5); // delay for 1/2 of a second
  }
  _loop();
}

void _delay(float seconds) {
  long endTime = millis() + seconds * 1000;
  while (millis() < endTime)_loop();
}

void _loop() {
}

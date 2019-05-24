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
double angle_rad = PI / 180.0;
double angle_deg = 180.0 / PI;
MeLineFollower linefollower_2(2); // Define linefollower sensor for use
MeRGBLed rgbled_7(7, 7==7?2:4);
MeBuzzer buzzer;
MeUltrasonicSensor ultrasonic_3(3);
MeLightSensor lightsensor_6(6);
int speed = 150;

void move(int direction, int speed) {
  int leftSpeed;
  int rightSpeed;
  if (speed > 0) {
    rgbled_7.setColor(0,0,0,0);
    rgbled_7.show();
  }
  if (direction == 0) { // line sensor reads 0, turn back
    leftSpeed = -(3 * speed) / 4;
    rightSpeed = -(3 * speed) / 4;
  } else if (direction == 1) { // line sensor reads 1, turn right
    leftSpeed = speed;
    rightSpeed = -speed / 2;
  } else if (direction == 2) { // line sensor reads 2, turn left
    leftSpeed = -speed / 2;
    rightSpeed = speed;
  } else if (direction == 3) { // line sensor reads 3, move forward
    leftSpeed = speed;
    rightSpeed = speed;
  }
  motor_9.run((9) == M1 ? -(leftSpeed) : (leftSpeed));
  motor_10.run((10) == M1 ? -(rightSpeed) : (rightSpeed));
}

void setup() {
  pinMode(A7, INPUT);
  while (!((0 ^ (analogRead(A7) > 10 ? 0 : 1)))) // wait until onboard button is pressed to start program
  {
    _loop();
  }
}

void loop() {
  int dist = ultrasonic_3.distanceCm();
  if (dist < 25) {
    buzzer.tone(262, 500);
    delay(20);
  }
  if (dist < 15) {
    rgbled_7.setColor(0,255,0,0);
    rgbled_7.show();
    move(0, 0);
  } else {
    move(linefollower_2.readSensors(), speed); // get direction from sensor reading, passed to move() function, at speed
  }
  _loop();
}

void _delay(float seconds) {
  long endTime = millis() + seconds * 1000;
  while (millis() < endTime) _loop();
}

void _loop() {
}

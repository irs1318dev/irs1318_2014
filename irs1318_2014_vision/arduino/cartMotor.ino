const int in1Pin=5; // pwm speed
const int in2Pin=4; // direction

void setup() {
  Serial.begin(9600);
  pinMode(in1Pin,OUTPUT);
  pinMode(in2Pin,OUTPUT);
  Serial.println("");
}

void loop() {
  if (Serial.available()) {
    char ch = Serial.read();
    if (ch=='+') {
      Serial.println("CW");
      analogWrite(in1Pin,100);
      digitalWrite(in2Pin,HIGH);
    }
    else if (ch=='-'){
      Serial.println("CCW");
      analogWrite(in1Pin,100);
      digitalWrite(in2Pin,LOW);
    }
    else {
      Serial.println("Stop");
      analogWrite(in1Pin,0);
      digitalWrite(in2Pin,LOW);
    }
  }
}
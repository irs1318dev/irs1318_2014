const int in1Pin=5; // pwm speed
const int in2Pin=4; // direction
int maxSpeed;
int minSpeed;
int cartSpeed;

void setup() {
  Serial.begin(9600);
  pinMode(in1Pin,OUTPUT);
  pinMode(in2Pin,OUTPUT);
  Serial.println("u,d change speed, +,- change direction, 0 stop");
  maxSpeed=80;
  minSpeed=50;
  cartSpeed=60;
}

void loop() {
  if (Serial.available()) {
    char ch = Serial.read();
    if (ch=='+') {
      Serial.println("CW");
      analogWrite(in1Pin,cartSpeed);
      digitalWrite(in2Pin,HIGH);
    }
    else if (ch=='-'){
      Serial.println("CCW");
      analogWrite(in1Pin,cartSpeed);
      digitalWrite(in2Pin,LOW);
    }
    else if (ch=='u') {
      cartSpeed+=5;
      if (cartSpeed>maxSpeed) {
        cartSpeed=maxSpeed;
      }
      analogWrite(in1Pin,cartSpeed);
      Serial.println(cartSpeed);
    }
    else if (ch=='d') {
      cartSpeed-=5;
      if (cartSpeed<minSpeed) {
        cartSpeed=minSpeed;
      }
      analogWrite(in1Pin,cartSpeed);
      Serial.println(cartSpeed);
    }
    else {
      Serial.println("Stop");
      analogWrite(in1Pin,0);
      digitalWrite(in2Pin,LOW);
    }
  }
}
package examples;

import src.AddressableLED;
import src.AddressableLEDBuffer;
import src.Color;

class ValenceFade {

  static AddressableLED led;
  static AddressableLEDBuffer ledBuffer;
  static int chaseSize = 5; // Number of LEDs in the chase
  static int chaseSpeed = 50; // Delay between each step of the chase
  private static final double FADE_FACTOR = 0.01;
  private static final Color COLOR = Color.kOrange;

  public static void main(String[] args) {
    led = new AddressableLED(1);
    ledBuffer = new AddressableLEDBuffer(100);

    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setLED(i, Color.kBlack);
    }

    while (true) {
      loop();
      delay(10);
    }
  }

  public static void loop() {
    int numPixels = ledBuffer.getLength();
    for (int i = 0; i < numPixels; i++) {
      Color pixelColor = ledBuffer.getLED(i);
      Double brightness = pixelColor.red;
      if (brightness > 0) {
        ledBuffer.setLED(i,
            new Color(pixelColor.red - FADE_FACTOR, pixelColor.green - FADE_FACTOR, pixelColor.blue - FADE_FACTOR));
      }
    }

    for (int i = 0; i < chaseSize; i++) {
      int pixelIndex = (int) ((System.currentTimeMillis() / chaseSpeed + i) % numPixels);
      ledBuffer.setLED(pixelIndex, COLOR); // Set the color of the chasing LED
    }

    led.setData(ledBuffer);
  }

  private static void delay(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

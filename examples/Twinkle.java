package examples;

import src.AddressableLED;
import src.AddressableLEDBuffer;
import src.Color;

import java.util.Random;

class Twinkle {
  static AddressableLED led;
  static AddressableLEDBuffer ledBuffer;
  static Random random = new Random();

  public static double FADE_FACTOR = 0.1;
  public static double TWINKLE_PROBABILITY = 0.02;

  public static void main(String[] args) {
    led = new AddressableLED(1);
    ledBuffer = new AddressableLEDBuffer(100);

    for (int i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setLED(i, Color.kBlack);
    }

    while (true) {
      loop();
      delay(100);
    }
  }

  public static void loop() {
    int numPixels = ledBuffer.getLength();

    // Randomly select a few LEDs to turn on
    for (int i = 0; i < numPixels; i++) {
      if (random.nextDouble() < TWINKLE_PROBABILITY) {
        ledBuffer.setLED(i, Color.kWhite); // Set the LED to a bright color
      }
    }

    led.setData(ledBuffer);

    // Fade out the bright spots gradually
    for (int i = 0; i < numPixels; i++) {
      Color pixelColor = ledBuffer.getLED(i);
      Double brightness = pixelColor.red;
      if (brightness > 0) {
        ledBuffer.setLED(i, new Color(brightness - FADE_FACTOR, brightness - FADE_FACTOR, brightness - FADE_FACTOR));
      }
    }
  }

  private static void delay(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

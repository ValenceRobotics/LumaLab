package examples;

import src.AddressableLED;
import src.AddressableLEDBuffer;
import src.Color;

class Playground {
  static AddressableLED led;
  static AddressableLEDBuffer ledBuffer;
  static float rainbowHue = 0.0f;

  public static float HUE_DIFFERENCE = 0.01f;
  // public s

  public static void main(String[] args) {
    led = new AddressableLED(1);
    ledBuffer = new AddressableLEDBuffer(100);

    while (true) {
      loop();
      delay(10);
    }

  }

  public static void loop() {
    int numPixels = ledBuffer.getLength();

    for (int i = 0; i < numPixels; i++) {
      float pixelHue = rainbowHue + i * HUE_DIFFERENCE;
      Color ledColor = Color.fromHSV(Math.round(180 * pixelHue), 255, 255);
      ledBuffer.setLED(i, ledColor);
    }

    led.setData(ledBuffer);

    rainbowHue += HUE_DIFFERENCE;
    if (rainbowHue >= 1.0f) {
      rainbowHue -= 1.0f; // Reset hue back to 0 after a full cycle
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

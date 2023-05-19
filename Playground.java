import src.AddressableLED;
import src.AddressableLEDBuffer;

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
    // implement logic here
    // check the `examples` folder for examples!

  }

  private static void delay(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

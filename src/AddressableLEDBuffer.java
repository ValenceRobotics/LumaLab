package src;

public class AddressableLEDBuffer {
  public Color[] buffer;

  public AddressableLEDBuffer(int length) {
    System.out.println("AddressableLEDBuffer created with length " + length);
    buffer = new Color[length];

  }

  public void setLED(int index, Color color) {
    if (index < 0 || index >= buffer.length) {
      throw new IllegalArgumentException("Index " + index + " is out of bounds");
    }
    buffer[index] = color;
  }

  public int getLength() {
    return buffer.length;
  }

  public Color getLED(int i) {
    return buffer[i];
  }
}

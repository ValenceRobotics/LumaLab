package src;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class AddressableLED {
  private JFrame frame;

  public AddressableLED(int port) {
    // port is a placeholder
    frame = new JFrame("Addressable LED");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    frame.setPreferredSize(new Dimension(screenSize.width, 50));
    frame.pack();
    frame.setVisible(true);

  }

  public void setData(AddressableLEDBuffer buffer) {
    frame.getContentPane().removeAll();
    LEDPanel panel = new LEDPanel(buffer);
    frame.getContentPane().add(panel);
    frame.revalidate();
    frame.repaint();
  }

  class LEDPanel extends JPanel {
    private AddressableLEDBuffer buffer;
    public int ledSize;

    public LEDPanel(AddressableLEDBuffer buffer) {
      this.buffer = buffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      ledSize = Math.max(1, frame.getWidth() / buffer.getLength());
      int bufferLength = buffer.getLength();
      int panelWidth = bufferLength * ledSize;
      setPreferredSize(new Dimension(panelWidth, ledSize));
      frame.pack();

      for (int i = 0; i < bufferLength; i++) {
        Color ledColor = buffer.getLED(i);
        if (ledColor == null) {
          ledColor = Color.kBlack; // Set unset LEDs to black
        }
        int x = i * ledSize;
        int y = 0;
        java.awt.Color awtColor = new java.awt.Color((float) ledColor.red, (float) ledColor.green,
            (float) ledColor.blue);
        g2d.setColor(awtColor);
        g2d.fillRect(x, y, ledSize, ledSize);
      }
    }
  }
}

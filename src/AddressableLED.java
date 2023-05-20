package src;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class AddressableLED {
  private JFrame frame;
  private JPanel contentPane;
  public int ledSize;
  Dimension screenSize;

  public AddressableLED(int port) {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    screenSize = toolkit.getScreenSize();
    // port is a placeholder
    frame = new JFrame("Addressable LED");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout(0, 0));
    frame.setContentPane(contentPane);

    LEDPanel panel = new LEDPanel(new AddressableLEDBuffer(100)); // create a new LEDPanel object and add
                                                                  // it to the
    // content pane
    contentPane.add(panel);
    frame.setSize(screenSize);

    frame.pack(); // pack the frame after adding the panel to the content pane

    frame.setVisible(true);

  }

  public void setData(AddressableLEDBuffer buffer) {
    LEDPanel panel = (LEDPanel) contentPane.getComponent(0); // get the existing LEDPanel object

    panel.setBuffer(buffer); // update the LEDPanel with the new AddressableLEDBuffer object

    panel.repaint(); // repaint the panel only
    // int width = screenSize.width / 2;
    // int height = width / panel.buffer.getLength();

    frame.pack(); // pack the frame after updating the panel

  }

  class LEDPanel extends JPanel {
    private AddressableLEDBuffer buffer;

    public LEDPanel(AddressableLEDBuffer buffer) {
      this.buffer = buffer;
    }

    public void setBuffer(AddressableLEDBuffer buffer) {
      this.buffer = buffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      ledSize = Math.max(1, screenSize.width / buffer.getLength());
      int bufferLength = buffer.getLength();
      int panelWidth = bufferLength * ledSize;
      Dimension preferredSize = new Dimension(panelWidth, ledSize);
      // System.out.println(preferredSize);
      setPreferredSize(preferredSize);

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

      // repack
      frame.pack();
    }
  }
}
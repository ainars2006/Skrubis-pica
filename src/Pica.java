import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Pica {
    private static Random rand = new Random();
    private static String[] styles = {"Biezā", "Plānā"};
    private static String[] toppings = {"Pepperoni", "Sēnes", "Sīpoli", "Bekons", "Olīvas"};
    private static int[] sizes = {30, 50, 20, 42};
    private static String[] klients = {
        "atteli/persona1-removebg-preview.png",
        "atteli/persona2-removebg-preview.png",
        "atteli/persona3-removebg-preview.png"
    };

    private static Klients currentClient = null;
    private static boolean isOrderComplete = true;
    private static String selectedClientImage = null;

    private static JFrame Kframe = null;
    private static JLabel clientLabel = null;
    private static JTextArea orderDetails = null;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Ummmm Pica");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JPanel buttonPanel = new JPanel();
        JButton klientButton = new JButton("Pieņemt klientu");
        JButton picaButton = new JButton("Taisīt picu");
        JButton cekuVesture = new JButton("Čeku vēsture");
        picaButton.setBackground(Color.YELLOW);
        klientButton.setBackground(Color.YELLOW);
        cekuVesture.setBackground(Color.YELLOW);
        buttonPanel.add(klientButton);
        buttonPanel.add(cekuVesture);
        buttonPanel.add(picaButton);
        buttonPanel.setBackground(Color.RED);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().setBackground(Color.YELLOW);
        ImageIcon pica = new ImageIcon(new ImageIcon("atteli/a-cheesy-delicious-pizza-with-tasty-pepperoni-on-a-transparent-background-png.png")
                .getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH));
        JLabel picaLabel = new JLabel(pica);
        JPanel imagePanel = new JPanel();
        imagePanel.add(picaLabel);
        frame.add(imagePanel, BorderLayout.CENTER);
        klientButton.addActionListener(e -> openClientFrame());
        picaButton.addActionListener(e -> openPizzaMaker());
        cekuVesture.addActionListener(e -> read());

        frame.setVisible(true);
    }
    private static void read() {
    	try {
            FileReader fr = new FileReader("pasutijumi.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder nolasitais = new StringBuilder();
            String teksts;
            while ((teksts = br.readLine()) != null) {
                nolasitais.append(teksts).append("\n");
            }
            br.close();

            JTextArea textArea = new JTextArea(nolasitais.toString(), 15, 40);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textArea.setBackground(Color.YELLOW);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            JOptionPane.showMessageDialog(null, scrollPane, "Saglabātie pasūtījumi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Neizdevās nolasīt failu.");
        }
    }
    private static void openClientFrame() {
        if (Kframe == null) {
            Kframe = new JFrame("Pieņem klientu");
            Kframe.setSize(600, 500);
            Kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Kframe.setLayout(null);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 600, 500);

            ImageIcon backgroundIcon = new ImageIcon(new ImageIcon("atteli/backPica.png")
                    .getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(backgroundIcon);
            backgroundLabel.setBounds(0, 0, 600, 500);
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

            ImageIcon tableIcon = new ImageIcon(new ImageIcon("atteli/empty-wooden-table-front-view-isolated-transparent-png.png")
                    .getImage().getScaledInstance(650, 300, Image.SCALE_SMOOTH));
            JLabel tableLabel = new JLabel(tableIcon);
            tableLabel.setBounds(-50, 150, 700, 300);
            layeredPane.add(tableLabel, JLayeredPane.PALETTE_LAYER);

            ImageIcon phoneIcon = new ImageIcon(new ImageIcon("atteli/phone.png")
                    .getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
            JLabel phoneLabel = new JLabel(phoneIcon);
            phoneLabel.setBounds(100, 180, 700, 300);
            layeredPane.add(phoneLabel, JLayeredPane.MODAL_LAYER);

            clientLabel = new JLabel();
            clientLabel.setBounds(220, 213, 150, 150);
            layeredPane.add(clientLabel, JLayeredPane.DRAG_LAYER);

            orderDetails = new JTextArea(5, 30);
            orderDetails.setBounds(150, 375, 300, 100);
            orderDetails.setEditable(false);
            layeredPane.add(orderDetails, JLayeredPane.DRAG_LAYER);

            JButton backButton = new JButton("Back");
            backButton.setBounds(500, 20, 80, 30);
            backButton.addActionListener(e -> {
                frame.setVisible(true);
                Kframe.setVisible(false);
            });
            layeredPane.add(backButton, JLayeredPane.MODAL_LAYER);

            phoneLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (isOrderComplete) {
                        String style = styles[rand.nextInt(styles.length)];
                        String topping = toppings[rand.nextInt(toppings.length)];
                        int size = sizes[rand.nextInt(sizes.length)];
                        currentClient = new Klients("Telefoniski", style, topping, size);
                        orderDetails.setText("📞 Telefona zvans!\n" +
                                "Klients pasūta:\n" +
                                style + " ar " + topping + "\nIzmērs: " + size + " cm");
                        clientLabel.setIcon(null);
                        isOrderComplete = false;
                    }
                }
            });

            backgroundLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (isOrderComplete) {
                        String style = styles[rand.nextInt(styles.length)];
                        String topping = toppings[rand.nextInt(toppings.length)];
                        int size = sizes[rand.nextInt(sizes.length)];
                        currentClient = new Klients("Ienāca veikalā", style, topping, size);
                        selectedClientImage = klients[rand.nextInt(klients.length)];
                        ImageIcon clientIcon = new ImageIcon(new ImageIcon(selectedClientImage)
                                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                        clientLabel.setIcon(clientIcon);
                        orderDetails.setText("🧍 Klients ienāca un saka:\n" +
                                style + " ar " + topping + "\nIzmērs: " + size + " cm");
                        isOrderComplete = false;
                    }
                }
            });

            Kframe.add(layeredPane);
        }

        Kframe.setVisible(true);
        frame.setVisible(false);
    }

    private static void openPizzaMaker() {
        if (currentClient != null && !isOrderComplete) {
            JFrame pizzaFrame = new JFrame("Taisīt picu");
            pizzaFrame.setSize(600, 400);
            pizzaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pizzaFrame.setLayout(new FlowLayout());
            pizzaFrame.getContentPane().setBackground(Color.YELLOW);

            JLabel pizzaOrderLabel = new JLabel("Klienta pasūtījums: " +
                    currentClient.getStyle() + " ar " +
                    currentClient.getToping() + ", Izmērs: " +
                    currentClient.getSize() + " cm\n");
            pizzaFrame.add(pizzaOrderLabel);

            JComboBox<String> styleComboBox = new JComboBox<>(styles);
            styleComboBox.setSelectedItem(currentClient.getStyle());
            pizzaFrame.add(new JLabel("\nIzvēlieties stilu:"));
            pizzaFrame.add(styleComboBox);

            JComboBox<String> toppingComboBox = new JComboBox<>(toppings);
            toppingComboBox.setSelectedItem(currentClient.getToping());
            pizzaFrame.add(new JLabel("Izvēlieties piedevas:"));
            pizzaFrame.add(toppingComboBox);

            JComboBox<Integer> sizeComboBox = new JComboBox<>();
            for (int size : sizes) {
                sizeComboBox.addItem(size);
            }
            sizeComboBox.setSelectedItem(currentClient.getSize());
            pizzaFrame.add(new JLabel("Izvēlieties izmēru:"));
            pizzaFrame.add(sizeComboBox);

            JButton makePizzaButton = new JButton("Taisīt picu");
            makePizzaButton.addActionListener(e -> {
            	  String style = (String) styleComboBox.getSelectedItem();
            	    String topping = (String) toppingComboBox.getSelectedItem();
            	    int size = (Integer) sizeComboBox.getSelectedItem();

            	    currentClient = new Klients("Custom", style, topping, size);

            	    try (PrintWriter writer = new PrintWriter(new FileWriter("pasutijumi.txt", true))) {
            	        writer.println("Pasūtījums:");
            	        writer.println("Stils: " + style);
            	        writer.println("Piedevas: " + topping);
            	        writer.println("Izmērs: " + size + " cm");
            	        writer.println("===========");
            	    } catch (IOException ex) {
            	        ex.printStackTrace();
            	    }

            	    JOptionPane.showMessageDialog(pizzaFrame, "Jūsu picu ir pasūtīta: " +
            	            style + " ar " + topping + ", Izmērs: " + size + " cm.");

            	    if (!style.equals(currentClient.getStyle()) ||
            	        !topping.equals(currentClient.getToping()) ||
            	        size != currentClient.getSize()) {
            	        JOptionPane.showMessageDialog(null, "Tu uztaisīji nepareizu picu dēļ tā klients aizmuka/nolika klausuli");
            	    } else {
            	        JOptionPane.showMessageDialog(null, "Klients: Ummm paldies par picu");
            	    }

            	    isOrderComplete = true;
            	    resetClient();
            	    pizzaFrame.dispose();
            	    frame.setVisible(true);
            	    if (Kframe != null) {
            	        Kframe.setVisible(false);
            	    }
            	});
            pizzaFrame.add(makePizzaButton);

            pizzaFrame.setVisible(true);
        }
    }

    private static void resetClient() {
        currentClient = null;
        isOrderComplete = true;
        if (clientLabel != null) clientLabel.setIcon(null);
        if (orderDetails != null) orderDetails.setText("");
    }
}
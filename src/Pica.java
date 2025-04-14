import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Pica {
    private static Random rand = new Random();
    private static String[] styles = {"Biezā", "Plānā"};
    private static String[] toppings = {"Pepperoni", "Sēnes", "Sīpoli", "Bekons", "Olīvas"};
    private static int[] sizes = {30, 50, 20, 42};
    private static String[] klients = {
        "/atteli/persona1-removebg-preview.png",
        "/atteli/persona2-removebg-preview.png",
        "/atteli/persona3-removebg-preview.png"
    };

    private static final Map<String, Double> priceMap = Map.ofEntries(
        Map.entry("style:Biezā", 2.0),
        Map.entry("style:Plānā", 1.5),
        Map.entry("topping:Pepperoni", 1.5),
        Map.entry("topping:Sēnes", 1.0),
        Map.entry("topping:Sīpoli", 0.8),
        Map.entry("topping:Bekons", 1.7),
        Map.entry("topping:Olīvas", 1.2),
        Map.entry("size:20", 4.0),
        Map.entry("size:30", 5.5),
        Map.entry("size:42", 7.0),
        Map.entry("size:50", 8.0)
    );

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

        ImageIcon pica = new ImageIcon(new ImageIcon(Pica.class.getResource("/atteli/a-cheesy-delicious-pizza-with-tasty-pepperoni-on-a-transparent-background-png.png"))
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

            ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(Pica.class.getResource("/atteli/backPica.png"))
                .getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(backgroundIcon);
            backgroundLabel.setBounds(0, 0, 600, 500);
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

            ImageIcon tableIcon = new ImageIcon(new ImageIcon(Pica.class.getResource("/atteli/empty-wooden-table-front-view-isolated-transparent-png.png"))
                .getImage().getScaledInstance(650, 300, Image.SCALE_SMOOTH));
            JLabel tableLabel = new JLabel(tableIcon);
            tableLabel.setBounds(-50, 150, 700, 300);
            layeredPane.add(tableLabel, JLayeredPane.PALETTE_LAYER);

            ImageIcon phoneIcon = new ImageIcon(new ImageIcon(Pica.class.getResource("/atteli/phone.png"))
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
                        String[] deliveryOptions = {"Saņemt šeit", "Piegāde"};
                        String deliveryMethod = deliveryOptions[rand.nextInt(deliveryOptions.length)];
                        currentClient = new Klients("Telefoniski", style, topping, size, deliveryMethod);
                        orderDetails.setText("\uD83D\uDCDE Telefona zvans!\nKlients pasūta:\n" +
                                style + " ar " + topping + "\nIzmērs: " + size + " cm\nPiegāde: " + deliveryMethod);
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
                        String[] deliveryOptions = {"Saņemt šeit", "Piegāde"};
                        String deliveryMethod = deliveryOptions[rand.nextInt(deliveryOptions.length)];
                        currentClient = new Klients("Ienāca veikalā", style, topping, size, deliveryMethod);
                        selectedClientImage = klients[rand.nextInt(klients.length)];
                        ImageIcon clientIcon = new ImageIcon(new ImageIcon(Pica.class.getResource(selectedClientImage))
                                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                        clientLabel.setIcon(clientIcon);
                        orderDetails.setText("\uD83E\uDDD9 Klients ienāca un saka:\n" +
                                style + " ar " + topping + "\nIzmērs: " + size + " cm\nPiegāde: " + deliveryMethod);
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
                    currentClient.getSize() + " cm");
            pizzaFrame.add(pizzaOrderLabel);

            pizzaFrame.add(new JLabel("Izvēlieties stilu:"));
            JComboBox<String> styleComboBox = new JComboBox<>(styles);
            styleComboBox.setSelectedItem(currentClient.getStyle());
            JLabel stylePriceLabel = new JLabel();
            pizzaFrame.add(styleComboBox);
            pizzaFrame.add(stylePriceLabel);

            pizzaFrame.add(new JLabel("Izvēlieties piedevas:"));
            JComboBox<String> toppingComboBox = new JComboBox<>(toppings);
            toppingComboBox.setSelectedItem(currentClient.getToping());
            JLabel toppingPriceLabel = new JLabel();
            pizzaFrame.add(toppingComboBox);
            pizzaFrame.add(toppingPriceLabel);

            pizzaFrame.add(new JLabel("Izvēlieties izmēru:"));
            JComboBox<Integer> sizeComboBox = new JComboBox<>();
            for (int size : sizes) sizeComboBox.addItem(size);
            sizeComboBox.setSelectedItem(currentClient.getSize());
            JLabel sizePriceLabel = new JLabel();
            pizzaFrame.add(sizeComboBox);
            pizzaFrame.add(sizePriceLabel);

            JLabel totalPriceLabel = new JLabel("Kopējā cena: ");
            totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            pizzaFrame.add(totalPriceLabel);

            Runnable updatePrices = () -> {
                String style = (String) styleComboBox.getSelectedItem();
                String topping = (String) toppingComboBox.getSelectedItem();
                int size = (Integer) sizeComboBox.getSelectedItem();
                double stylePrice = priceMap.get("style:" + style);
                double toppingPrice = priceMap.get("topping:" + topping);
                double sizePrice = priceMap.get("size:" + size);
                double total = stylePrice + toppingPrice + sizePrice;
                stylePriceLabel.setText("Cena: " + stylePrice + " €");
                toppingPriceLabel.setText("Cena: " + toppingPrice + " €");
                sizePriceLabel.setText("Cena: " + sizePrice + " €");
                totalPriceLabel.setText("Kopējā cena: " + total + " €");
            };

            styleComboBox.addActionListener(e -> updatePrices.run());
            toppingComboBox.addActionListener(e -> updatePrices.run());
            sizeComboBox.addActionListener(e -> updatePrices.run());
            updatePrices.run();

            JButton makePizzaButton = new JButton("Taisīt picu");
            makePizzaButton.addActionListener(e -> {
                String style = (String) styleComboBox.getSelectedItem();
                String topping = (String) toppingComboBox.getSelectedItem();
                int size = (Integer) sizeComboBox.getSelectedItem();

                if (!style.equals(currentClient.getStyle()) ||
                    !topping.equals(currentClient.getToping()) ||
                    size != currentClient.getSize()) {
                    JOptionPane.showMessageDialog(null, "Tu uztaisīji nepareizu picu dēļ tā klients aizmuka/nolika klausuli");
                } else {
                    JOptionPane.showMessageDialog(null, "Klients: Ummm paldies par picu");
                }

                double price = priceMap.get("style:" + style)
                              + priceMap.get("topping:" + topping)
                              + priceMap.get("size:" + size);

                try (PrintWriter writer = new PrintWriter(new FileWriter("pasutijumi.txt", true))) {
                    writer.println("Pasūtījums:");
                    writer.println("Stils: " + style);
                    writer.println("Piedevas: " + topping);
                    writer.println("Izmērs: " + size + " cm");
                    writer.println("Cena: " + price + " €");
                    writer.println("===========");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Jūsu picu ir pasūtīta: " +
                    style + " ar " + topping + ", Izmērs: " + size + " cm.\nCena: " + price + " €");

                isOrderComplete = true;
                resetClient();
                pizzaFrame.dispose();
                frame.setVisible(true);
                if (Kframe != null) Kframe.setVisible(false);
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

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

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
    private static boolean isClientVisible = false;
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
        buttonPanel.add(klientButton);
        buttonPanel.add(picaButton);
        frame.add(buttonPanel, BorderLayout.NORTH);

        ImageIcon pica = new ImageIcon(new ImageIcon("atteli/a-cheesy-delicious-pizza-with-tasty-pepperoni-on-a-transparent-background-png.png")
                .getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH));
        JLabel picaLabel = new JLabel(pica);
        JPanel imagePanel = new JPanel();
        imagePanel.add(picaLabel);
        frame.add(imagePanel, BorderLayout.CENTER);

        klientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openClientFrame();
            }
        });

        frame.setVisible(true);
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
                resetClient();
            });
            layeredPane.add(backButton, JLayeredPane.MODAL_LAYER);

            phoneLabel.addMouseListener(new MouseAdapter() {
                @Override
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
                        isClientVisible = false;
                        isOrderComplete = false;
                    }
                }
            });

            backgroundLabel.addMouseListener(new MouseAdapter() {
                @Override
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
                        isClientVisible = true;
                        isOrderComplete = false;
                    }
                }
            });

            Kframe.add(layeredPane);
        }

        Kframe.setVisible(true);
        frame.setVisible(false);
    }

    private static void resetClient() {
        currentClient = null;
        isOrderComplete = true;
        isClientVisible = false;
        clientLabel.setIcon(null);
        orderDetails.setText("");
    }
}
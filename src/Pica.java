import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class Pica {
    private static Random rand = new Random();
    private static String[] styles = {"Biezā", "Plānā"};
    private static String[] toppings = {"Pepperoni", "Sēnes", "Sīpoli", "Bekons", "Olīvas"};
    private static int[] sizes = {30, 50, 20, 42};
    private static String[] klients = {"atteli/persona1-removebg-preview.png", "atteli/persona2-removebg-preview.png", "atteli/persona3-removebg-preview.png"};

    private static String currentClientOrder = "";
    private static boolean isClientSelected = false;
    private static boolean isOrderComplete = true;

    private static String selectedClientImage = null;
    private static JLabel clientLabel = null;  
    private static JTextArea orderDetails = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ummmm Pica");
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
                JFrame Kframe = new JFrame("Pieņem klientu");
                Kframe.setSize(600, 500);
                Kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Kframe.setLayout(null);

                clientLabel = new JLabel();
                clientLabel.setBounds(220, 213, 150, 150); 

                // Restore previously selected client image if available
                if (selectedClientImage != null) {
                    ImageIcon clientIcon = new ImageIcon(new ImageIcon(selectedClientImage)
                            .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                    clientLabel.setIcon(clientIcon);
                }

                orderDetails = new JTextArea(5, 30);
                orderDetails.setBounds(150, 375, 300, 100);
                orderDetails.setEditable(false);
                orderDetails.setText(currentClientOrder);

                JButton completeOrderButton = new JButton("Complete Order");
                completeOrderButton.setBounds(200, 380, 150, 30);

                clientLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!isClientSelected && isOrderComplete) {
                            selectedClientImage = klients[rand.nextInt(klients.length)];
                            ImageIcon clientIcon = new ImageIcon(new ImageIcon(selectedClientImage)
                                    .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                            clientLabel.setIcon(clientIcon);

                            String style = styles[rand.nextInt(styles.length)];
                            String topping = toppings[rand.nextInt(toppings.length)];
                            int size = sizes[rand.nextInt(sizes.length)];
                            currentClientOrder = "Jauns pasūtījums:\n" + style + " ar " + topping + "\nIzmērs: " + size;

                            orderDetails.setText(currentClientOrder);
                            isClientSelected = true;
                            isOrderComplete = false;
                        }
                    }
                });

                completeOrderButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isClientSelected && !isOrderComplete) {
                            isOrderComplete = true;
                            orderDetails.setText("Pasūtījums pabeigts!\n" + currentClientOrder);
                            selectedClientImage = null;
                            isClientSelected = false;
                        }
                    }
                });

                Kframe.add(clientLabel);
                Kframe.add(orderDetails);
                Kframe.add(completeOrderButton);

                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBounds(0, 0, 600, 500);

                ImageIcon backgroundIcon = new ImageIcon(new ImageIcon("atteli/backPica.png")
                        .getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH));
                JLabel backgroundLabel = new JLabel(backgroundIcon);
                backgroundLabel.setBounds(0, 0, 600, 500);

                ImageIcon tableIcon = new ImageIcon(new ImageIcon("atteli/empty-wooden-table-front-view-isolated-transparent-png.png")
                        .getImage().getScaledInstance(650, 300, Image.SCALE_SMOOTH));
                JLabel tableLabel = new JLabel(tableIcon);
                tableLabel.setBounds(-50, 150, 700, 300);

                ImageIcon phoneIcon = new ImageIcon(new ImageIcon("atteli/phone.png")
                        .getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
                JLabel phoneLabel = new JLabel(phoneIcon);
                phoneLabel.setBounds(100, 180, 700, 300);

                layeredPane.add(phoneLabel, JLayeredPane.DEFAULT_LAYER);
                layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
                layeredPane.add(tableLabel, JLayeredPane.PALETTE_LAYER);

                JButton backButton = new JButton("Back");
                backButton.setBounds(500, 20, 80, 30);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(true);
                        Kframe.setVisible(false);
                    }
                });

                layeredPane.add(backButton, JLayeredPane.MODAL_LAYER);

                Kframe.add(layeredPane);
                Kframe.setVisible(true);
                frame.setVisible(false);
            }
        });

        picaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame Pframe = new JFrame("Pica");
                Pframe.setSize(600, 400);
                Pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Pframe.setLayout(new FlowLayout());

                JButton backButton = new JButton("Back");
                backButton.setBounds(500, 20, 80, 30);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(true);
                        Pframe.setVisible(false);
                    }
                });

                Pframe.add(backButton);
                JButton klientGaumeButton = new JButton("Taisīt originālu picu");
                JButton gatavaButton = new JButton("Izvēlēties picas recepti");
                
                klientGaumeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	createCustomPizzaFrame();
                    }
                });
                Pframe.add(klientGaumeButton);
                Pframe.add(gatavaButton);

                Pframe.setVisible(true);
                frame.setVisible(false);
            }
            private static void createCustomPizzaFrame() {
                JFrame customPizzaFrame = new JFrame("Izveido savu picu");
                customPizzaFrame.setSize(400, 300);
                customPizzaFrame.setLayout(new GridLayout(5, 2));

                JLabel styleLabel = new JLabel("Izvēlies mīklas veidu:");
                JComboBox<String> styleBox = new JComboBox<>(styles);

                JLabel toppingLabel = new JLabel("Izvēlies piedevas:");
                JList<String> toppingList = new JList<>(toppings);
                toppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                JLabel sizeLabel = new JLabel("Izvēlies izmēru:");
                JComboBox<Integer> sizeBox = new JComboBox<>(new Integer[]{20, 30, 42, 50});

                JButton confirmButton = new JButton("Apstiprināt");
                JTextArea orderSummary = new JTextArea(5, 30);
                orderSummary.setEditable(false);

                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!isClientSelected) {
                            orderSummary.setText("Nav neviena klienta!");
                            return;
                        }

                        String selectedStyle = (String) styleBox.getSelectedItem();
                        java.util.List<String> selectedToppings = toppingList.getSelectedValuesList();
                        int selectedSize = (int) sizeBox.getSelectedItem();

                        StringBuilder orderText = new StringBuilder("Tava pasūtītā pica:\n");
                        orderText.append("Mīklas veids: ").append(selectedStyle).append("\n");
                        orderText.append("Piedevas: ").append(String.join(", ", selectedToppings)).append("\n");
                        orderText.append("Izmērs: ").append(selectedSize).append(" cm");

                        currentClientOrder = orderText.toString();
                        orderDetails.setText(currentClientOrder + "\n\nPasūtījums pabeigts!");

                        isOrderComplete = true;
                        isClientSelected = false;
                        selectedClientImage = null;
                        clientLabel.setIcon(null);

                        customPizzaFrame.dispose();
                    }
                });

                customPizzaFrame.add(styleLabel);
                customPizzaFrame.add(styleBox);
                customPizzaFrame.add(toppingLabel);
                customPizzaFrame.add(new JScrollPane(toppingList));
                customPizzaFrame.add(sizeLabel);
                customPizzaFrame.add(sizeBox);
                customPizzaFrame.add(confirmButton);
                customPizzaFrame.add(new JScrollPane(orderSummary));

                customPizzaFrame.setVisible(true);
            }
        

        });

        frame.setVisible(true);
        
    }
}

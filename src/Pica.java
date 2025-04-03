import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Pica {
	private static JTextArea orderTextArea;
    private static Random rand = new Random();
    private static String[] styles = {"Biezā", "Plānā"};
    private static String[] toppings = {"Pepperoni", "Sēnes", "Sīpoli", "Bekons", "Olīvas"};
    private static int[] sizes = {30, 50, 20, 42};
    private static String[] klients = {"atteli/persona1-removebg-preview.png", "atteli/persona2-removebg-preview.png", "atteli/persona3-removebg-preview.png"};
    String toping, style;
    int size;

    public Pica(String toping, String style, int size) {
        this.toping = toping;
        this.style = style;
        this.size = size;
    }

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
                String clientImage = klients[rand.nextInt(klients.length)];
                ImageIcon clientIcon = new ImageIcon(new ImageIcon(clientImage)
                        .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                JLabel clientLabel = new JLabel(clientIcon);
                clientLabel.setBounds(220, 213, 150, 150); 

                JTextArea orderDetails = new JTextArea(5, 30);
                orderDetails.setBounds(150, 375, 300, 100);
                orderDetails.setEditable(false);

                clientLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String style = styles[rand.nextInt(styles.length)];
                        String topping = toppings[rand.nextInt(toppings.length)];
                        int size = sizes[rand.nextInt(sizes.length)];
                        orderDetails.setText("Jauns pasūtījums:\n" + style + " ar " + topping + "\nIzmērs: " + size);
                    }
                });

                Kframe.add(clientLabel);
                Kframe.add(orderDetails);
                
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

                Pframe.add(klientGaumeButton);
                Pframe.add(gatavaButton);

                Pframe.setVisible(true);
                frame.setVisible(false);
            }
        });

        frame.add(klientButton);
        frame.add(picaButton);

        frame.setVisible(true);
    }
    private static void RandomPasutijums() {
        String type = rand.nextBoolean() ? "Originalu" : "No receptes";
        String style = styles[rand.nextInt(styles.length)];
        String topping = toppings[rand.nextInt(toppings.length)];
        int size = sizes[rand.nextInt(sizes.length)];

        Klients klient = new Klients(type, style, topping, size);
        orderTextArea.append("Jauns pasūtijums: " + klient.getVeid() + " - " + klient.getStyle() + " ar " + klient.getToping() + ", \nIzmērs: " + klient.getSize() + "\n");
    }
}

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Pica {
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

        JButton klientButton = new JButton("Pieņemt klientu");
        JButton picaButton = new JButton("Taisīt picu");

        klientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame Kframe = new JFrame("Pieņem klientu");
                Kframe.setSize(600, 500);
                Kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Kframe.setLayout(null);

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
}

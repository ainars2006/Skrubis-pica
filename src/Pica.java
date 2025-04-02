import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Pica {
	String toping,style;
	int size;
	public Pica(String toping, String style, int size) {
		this.toping = toping;
		this.style=style;
		this.size=size;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Ummmm Pica");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        JButton klientButton = new JButton("Pieņemt klientu");
        JButton picaButton = new JButton("Taisīt picu");
        
        klientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame Kframe = new JFrame("Pieņem klientu");
            	Kframe.setSize(600,500);
            	Kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Kframe.setLayout(new FlowLayout());
                ImageIcon icon = new ImageIcon (new ImageIcon("atteli/backPica.png").getImage().getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
                JLabel label = new JLabel(icon);
                Kframe.add(label);


                Kframe.setVisible(true);
                frame.setVisible(false);
            }
        });
        
        picaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame Pframe = new JFrame("Pica");
            	Pframe.setSize(600,400);
            	Pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Pframe.setLayout(new FlowLayout());
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

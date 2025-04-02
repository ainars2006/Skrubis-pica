import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

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
            	Kframe.setSize(600,400);
            	Kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Kframe.setLayout(new FlowLayout());
                
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

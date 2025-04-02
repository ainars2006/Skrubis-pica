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
		JFrame frame = new JFrame("Pica menu");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        JButton klientButton = new JButton("Pieņemt klientu");
        JButton picaButton = new JButton("Taisīt picu");
        
        klientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        picaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        frame.add(klientButton);
        frame.add(picaButton);
        
        frame.setVisible(true);
	}

}

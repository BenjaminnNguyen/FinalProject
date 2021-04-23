package converter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.TextArea;

import converter.writer;

public class convert extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					convert frame = new convert();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public convert() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final Label label = new Label("File path");
		label.setBounds(10, 110, 300, 21);
		contentPane.add(label);
		Button button = new Button("Upload file");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
	            int option = fileChooser.showOpenDialog(contentPane);
	            if(option == JFileChooser.APPROVE_OPTION){
	               File file = fileChooser.getSelectedFile();
	               label.setText(file.getName());
	            }else{
	               label.setText("Open command canceled");
	            }
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 13));
		button.setBackground(Color.YELLOW);
		button.setBounds(10, 154, 120, 30);
		contentPane.add(button);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(346, 10, 394, 345);
		contentPane.add(textArea);
		
		Button button_1 = new Button("Convert");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				writer w = new writer();
				w.writing();
				
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 13));
		button_1.setBackground(Color.YELLOW);
		button_1.setBounds(190, 154, 120, 30);
		contentPane.add(button_1);


	}
}

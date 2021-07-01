package converter;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
//import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import com.google.common.io.Files;

import junit.framework.TestCase;
import model.*;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Convert extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Convert frame = new Convert();
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
	public Convert() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final Label label = new Label("Choose a file to convert");
		final String fileNameString = "";
		label.setBounds(30, 27, 300, 21);
		contentPane.add(label);
		final JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 10, 376, 345);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		// textArea.setEditable(false);
		// final PrintStream printStream = new PrintStream(new
		// CustomOutputStream(textArea));
		// System.setOut(printStream);
		// System.setErr(printStream);
		final JList list = new JList();

//		list.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent arg0) {
//				list.setSelectedIndex(0);
//				String selected = list.getSelectedValue().toString();
//				textArea.setText(selected);
//				
//			}
//		});

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = listFilesForFolder(System.getProperty("user.dir") + "/TESTSUITE/");

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		// list.setSelectedIndex(0);
		list.setBounds(30, 64, 212, 220);
		contentPane.add(list);
		// scrollPane.setViewportView(list);
		Button button = new Button("Upload file");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "xls");
				fileChooser.setFileFilter(filter);
				int option = fileChooser.showOpenDialog(null);
				File file = fileChooser.getSelectedFile();
				if (option == JFileChooser.APPROVE_OPTION) {
					label.setText(file.getPath());
					System.out.println(file.getPath() + "\n" + System.getProperty("user.dir") + "/TESTSUITE/"
							+ file.getName() + "\n" + file.getName() + "\n");
					String[] values = listFilesForFolder(System.getProperty("user.dir") + "/TESTSUITE/");
					boolean check = false;
					for (int i = 0; i < values.length; i++) {
						if (values[i].equals(file.getName())) {
							check = true;

						}
					}
					if (check == true) {
						JOptionPane.showMessageDialog(null, "File already exists!");
					} else {
						try {
							Files.copy(new File(file.getPath()),
									new File(System.getProperty("user.dir") + "/TESTSUITE/" + file.getName()));
							list.setModel(new AbstractListModel() {
								String[] values = listFilesForFolder(System.getProperty("user.dir") + "/TESTSUITE/");

								public int getSize() {
									return values.length;
								}

								public Object getElementAt(int index) {
									return values[index];
								}
							});
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
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
		button.setBounds(30, 302, 110, 30);
		contentPane.add(button);

		/*
		 * Button Convert
		 * 
		 * 
		 */

		Button button_1 = new Button("Convert");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WriteJavaFile w = new WriteJavaFile(
						System.getProperty("user.dir") + "/TESTSUITE/" + list.getSelectedValue().toString());
				// readExcel re = new readExcel();
				String t = list.getSelectedValue().toString();
				textArea.setText(t + "\n");
				try {
					String fileNameString = FilenameUtils.removeExtension(t);
					w.writing(fileNameString);
					List<model.TestCase> ltc = new ArrayList();
					ltc = w.readTestCase();
					for (model.TestCase a : ltc) {
						textArea.setText(textArea.getText() + "\n" + a.getName());
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					textArea.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 13));
		button_1.setBackground(Color.YELLOW);
		button_1.setBounds(238, 151, 120, 30);
		contentPane.add(button_1);

	}

	public String[] listFilesForFolder(String path) {
		File folder = new File(path);
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File folder, String name) {
				if (name.endsWith(".xls")) {
					return true;
				} else if (name.endsWith(".xlsx")) {
					return true;
				}

				return false;
			}
		};
		File[] listOfFiles = folder.listFiles(filter);

		List<String> listFileStrings = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				listFileStrings.add(listOfFiles[i].getName());
			}
		}
		String[] list = new String[listFileStrings.size()];
		listFileStrings.toArray(list);
		return list;
	}

	public void myPrintStream() {
		System.out.println("Hello");

	}
}

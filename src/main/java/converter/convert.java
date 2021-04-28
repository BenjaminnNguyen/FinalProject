package converter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import common.TestDetail;
import common.TestObject;
import common.TestParam;
import common.testCase1;

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
import java.util.ArrayList;
import java.util.List;
import java.awt.TextArea;

import converter.writer;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

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
		final String fileNameString ="";
		label.setBounds(10, 43, 300, 21);
		contentPane.add(label);
		Button button = new Button("Upload file");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(contentPane);
				File file = fileChooser.getSelectedFile();
				
				if (option == JFileChooser.APPROVE_OPTION) {
					label.setText(file.getName());
					//fileNameString=file.getName().replace(".xlsx", "").replace(".xls", "");
					
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
		button.setBounds(10, 312, 120, 30);
		contentPane.add(button);

		TextArea textArea = new TextArea();
		textArea.setBounds(346, 10, 394, 345);
		contentPane.add(textArea);

		Button button_1 = new Button("Convert");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				writer w = new writer();
				try {
					w.writing("Testcase");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				readExcel re = new readExcel();
//				try {
//					List<testCase1> lst = new ArrayList();
//					lst = re.readTestCase();
//					for (testCase1 a : lst) {
//						System.out.println(a.name + "\t" + a.runMode);
//					}
//
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				;
				try {
					List<TestDetail> lst = new ArrayList();
					lst = re.readDetail();
					for (TestDetail a : lst) {
						System.out.println(a.getTestCase() + "\t" + a.getScript()+ "\t" +a.getObject()+ "\t" +a.getInput()+ "\t" +a.getOutput());
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
//				try {
//					List<TestObject> lst = new ArrayList();
//					lst = re.readTestObject();
//					for (TestObject a : lst) {
//						System.out.println(a.getName() + "\t" + a.getValue()+ "\t" +a.getType());
//					}
//
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				;
//				try {
//					List<TestParam> lst = new ArrayList();
//					lst = re.readTestParam();
//					for (TestParam a : lst) {
//						System.out.println(a.getName() + "\t" + a.getValue());
//					}
//
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				;

			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 13));
		button_1.setBackground(Color.YELLOW);
		button_1.setBounds(220, 153, 120, 30);
		contentPane.add(button_1);
		
		JList list = new JList();
//	    DefaultListModel<String> dlm = new DefaultListModel<String>();
//	    dlm.addElement("Huhong");
//	    JList<String> jlist = new JList<>(dlm);

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
		list.setSelectedIndex(0);
		list.setBounds(20, 64, 200, 220);
		contentPane.add(list);

	}
	public String[] listFilesForFolder(String path) {
		File folder = new File(path);
		FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
            	if(name.endsWith(".xls")) {
            		return true;
            	}else if (name.endsWith(".xlsx")) {
					return true;
				}
            		
                return false;
            }
        };
		File[] listOfFiles = folder.listFiles(filter);
		
		List <String> listFileStrings = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
		    if (listOfFiles[i].isFile()) {		    	
		        listFileStrings.add(listOfFiles[i].getName());
		    }
		}
		String[]list=new String[listFileStrings.size()];
		listFileStrings.toArray(list);
		return list;
	}
}

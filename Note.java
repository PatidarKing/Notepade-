package note;

//import java.awt.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Note extends JFrame implements ActionListener {

	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu help = new JMenu("Help");

	JMenuItem newFlie = new JMenuItem("New");
	JMenuItem openFlie = new JMenuItem("Open");
	JMenuItem saveFlie = new JMenuItem("Save");
	JMenuItem printFlie = new JMenuItem("Print");
	JMenuItem Exit = new JMenuItem("Exit");

	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");
	JMenuItem selectall = new JMenuItem("Select All");

	JMenuItem about = new JMenuItem("About");

	JTextArea textArea = new JTextArea();
	private String fileName;

	Note() {
		setTitle("Notepade");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("gp.png"));
		setIconImage(icon.getImage());
		


		setJMenuBar(menubar);

		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);

		file.add(newFlie);
		file.add(openFlie);
		file.add(saveFlie);
		file.add(printFlie);
		file.add(Exit);

		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectall);

		help.add(about);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		newFlie.addActionListener(this);
		openFlie.addActionListener(this);
		saveFlie.addActionListener(this);
		printFlie.addActionListener(this);
		Exit.addActionListener(this);

		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectall.addActionListener(this);

		about.addActionListener(this);

		newFlie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		openFlie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		saveFlie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		printFlie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("New")) {
			textArea.setText(null);
		} else if (e.getActionCommand().equalsIgnoreCase("Open")) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter textFilter = new FileNameExtensionFilter(".txt", "txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textFilter);

			int action = fileChooser.showOpenDialog(null);

			if (action != JFileChooser.APPROVE_OPTION) {
				return;
			} else {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
					textArea.read(reader, null);

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} else if (e.getActionCommand().equalsIgnoreCase("Save")) {
			JFileChooser fileName = new JFileChooser();
			fileName.setApproveButtonText("Save");
			// Opening the dialog and asking from user where to save the file.
			int actionDialog = fileName.showOpenDialog(null);
			if (actionDialog != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File FileName = new File(fileName.getSelectedFile() + ".txt");
			BufferedWriter outFile = null;
			try {
				outFile = new BufferedWriter(new FileWriter(FileName));
				textArea.write(outFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		else if (e.getActionCommand().equalsIgnoreCase("Print")) {
			try {
				textArea.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
			textArea.cut();
		} else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
			textArea.copy();
		} else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
			textArea.paste();
		} else if (e.getActionCommand().equalsIgnoreCase("Select all")) {
			textArea.selectAll();
		} else if (e.getActionCommand().equalsIgnoreCase("About")) {
			new About().setVisible(true);
		}
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Note().setVisible(true);

	}

}

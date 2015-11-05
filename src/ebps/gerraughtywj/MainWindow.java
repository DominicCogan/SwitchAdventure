package ebps.gerraughtywj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6190801599523089154L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final static JButton btnYes = new JButton("Yes");
	private final static JButton btnNo = new JButton("No");
	private static Semaphore semaphore = new Semaphore(0);
	public static String btnUsed = "";
	private final static JTextArea textField = new JTextArea();

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 */

	public static void Switch() throws InterruptedException {
		boolean isRunning = true;
		String place = "doorway";
		String tempPlace = place;
		String choice = "";
		textField.setLineWrap(true);
		// Begin the loop
		do {
			switch (place) {
			case "doorway":
				semaphore.drainPermits();
				textField.setText(Prompts.doorwayPrompt);
				tempPlace = place;
				btnYes.setText("Kitchen");
				btnNo.setText("Upstairs");
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals("yes") || choice.equals("no")) {
					if (choice.equals("yes")) {
						place = "kitchen";
					} else {
						place = "upstairs";
					}
				} else {
					place = "default";
				}
				break;
			case "kitchen":
				textField.setText("temporary response. Working!");
				btnYes.setText("Yes");
				btnNo.setText("No");
				semaphore.drainPermits();
				semaphore.acquire();
				isRunning = false;
				break;
			case "upstairs":
				textField.setText("temporary respons. Working!");
				btnYes.setText("Yes");
				btnNo.setText("No");
				semaphore.drainPermits();
				semaphore.acquire();
				isRunning = false;
				break;

			default:
				textField.setText("Not a valid answer!"); // Print when answer
															// isn't valid
				place = tempPlace; // Go back to previous place
				break; // Go back to loop

			}

		} while (isRunning); // Loop until done
		textField.setText("All done!");
	}

	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textField.setText("Yes or no?");
		Switch();
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUsed = "yes";
				semaphore.release();
			}
		});
		btnYes.setBounds(77, 167, 89, 23);

		panel.add(btnYes);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUsed = "no";
				semaphore.release();
			}
		});
		btnNo.setBounds(256, 167, 89, 23);

		panel.add(btnNo);
		textField.setBounds(77, 48, 270, 80);

		panel.add(textField);
	}
}

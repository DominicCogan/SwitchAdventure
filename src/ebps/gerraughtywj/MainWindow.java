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
	private final static JButton btnYes = new JButton(Prompts.yes);
	private final static JButton btnNo = new JButton(Prompts.no);
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
		String place = Prompts.doorway;
		String tempPlace = place;
		String choice = "";
		textField.setLineWrap(true);
		// Begin the loop
		do {
			switch (place) {
			case Prompts.doorway:
				semaphore.drainPermits();
				textField.setText(Prompts.doorwayPrompt);
				btnYes.setText(Prompts.kitchen);
				btnNo.setText(Prompts.upstairs);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						place = Prompts.kitchen;
					} else {
						place = Prompts.upstairs;
					}
				} else {
					place = "default";
				}
				break;
			case Prompts.kitchen:
				semaphore.drainPermits();
				textField.setText(Prompts.kitchenPrompt);
				btnYes.setText(Prompts.fridge);
				btnNo.setText(Prompts.cabinet);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						place = Prompts.fridge;
					} else {
						place = Prompts.cabinet;
					}
				} else {
					place = "default";
				}
				break;
			case Prompts.upstairs:
				semaphore.drainPermits();
				textField.setText(Prompts.upstairsPrompt);
				btnYes.setText(Prompts.bedroom);
				btnNo.setText(Prompts.bathroom);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						place = Prompts.bedroom;
					} else {
						place = Prompts.bathroom;
					}
				} else {
					place = "default";
				}
				break;
			case Prompts.fridge:
				semaphore.drainPermits();
				textField.setText(Prompts.fridgePrompt);
				btnYes.setText(Prompts.yes);
				btnNo.setText(Prompts.no);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						textField.setText(Prompts.fridgeYes);
						place = "done";
					} else {
						textField.setText(Prompts.fridgeNo);
						place = "done";
					}
				}
				break;
			case Prompts.cabinet:
				semaphore.drainPermits();
				textField.setText(Prompts.cabinetPrompt);
				btnYes.setText(Prompts.yes);
				btnNo.setText(Prompts.no);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						textField.setText(Prompts.cabinetYes);
						place = "done";
					} else {
						textField.setText(Prompts.cabinetNo);
						place = "done";
					}
				}
				break;
			case Prompts.bedroom:
				semaphore.drainPermits();
				textField.setText(Prompts.bedroomPrompt);
				btnYes.setText(Prompts.yes);
				btnNo.setText(Prompts.no);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						textField.setText(Prompts.bedroomYes);
						place = "done";
					} else {
						textField.setText(Prompts.bedroomNo);
						place = "done";
					}
				}
				break;
			case Prompts.bathroom:
				semaphore.drainPermits();
				textField.setText(Prompts.bathroomPrompt);
				btnYes.setText(Prompts.yes);
				btnNo.setText(Prompts.no);
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						textField.setText(Prompts.bathroomYes);
						place = "done";
					} else {
						textField.setText(Prompts.bathroomNo);
						place = "done";
					}
				}
				break;
			case "done":
				semaphore.drainPermits();
				btnYes.setText("Again");
				btnNo.setText("Finish");
				semaphore.acquire();
				choice = btnUsed;
				if (choice.equals(Prompts.yes) || choice.equals(Prompts.no)) {
					if (choice.equals(Prompts.yes)) {
						place = Prompts.doorway;
					} else {
						isRunning = false;
					}
				}
			default:
				textField.setText("Not a valid answer!");
				place = tempPlace;
				break;
			}
		} while (isRunning);
		System.exit(0);
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
				btnUsed = Prompts.yes;
				semaphore.release();
			}
		});
		btnYes.setBounds(77, 167, 89, 23);

		panel.add(btnYes);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUsed = Prompts.no;
				semaphore.release();
			}
		});
		btnNo.setBounds(256, 167, 89, 23);

		panel.add(btnNo);
		textField.setBounds(77, 48, 270, 80);

		panel.add(textField);
	}
}

package farm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import animals.Chicken;
import animals.Sheep;
import entities.Cultivator;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton chicken, sheep, cultivator, ok;
	private JTextArea txt;

	private static final int CHICKEN_PRICE = Chicken.getPrice();
	private static final int SHEEP_PRICE = Sheep.getPrice();
	private static final int CULTIVATOR_PRICE = Cultivator.getPrice();

	private static final int MAX_X = 500;
	private static final int MAX_Y = 250;

	public Window() {
		super("Mercado");
		setSize(MAX_X, MAX_Y);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		chicken = new JButton(new ImageIcon("images/chicken.png"));
		sheep = new JButton(new ImageIcon("images/sheep.png"));
		cultivator = new JButton(new ImageIcon("images/cultivator.png"));
		ok = new JButton("Ok");

		txt = new JTextArea();

		chicken.setLayout(new BorderLayout());
		JLabel label1 = new JLabel(CHICKEN_PRICE + " Pontos");
		chicken.add(BorderLayout.SOUTH, label1);

		sheep.setLayout(new BorderLayout());
		JLabel label2 = new JLabel(SHEEP_PRICE + " Pontos");
		sheep.add(BorderLayout.SOUTH, label2);

		cultivator.setLayout(new BorderLayout());
		JLabel label3 = new JLabel(CULTIVATOR_PRICE + " Pontos");
		cultivator.add(BorderLayout.SOUTH, label3);

		chicken.setBounds(30, 30, 100, 100);
		sheep.setBounds(200, 30, 100, 100);
		cultivator.setBounds(370, 30, 100, 100);
		ok.setBounds(400, 150, 50, 50);

		txt.setBounds(10, 10, 200, 20);
		txt.setFont(txt.getFont().deriveFont(14f));
		txt.setText("Pontos: " + Farm.getInstance().getPontos());
		txt.setOpaque(false);
		txt.setEditable(false);

		add(chicken);
		add(sheep);
		add(cultivator);
		add(ok);
		add(txt);

		setVisible(true);

		chicken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Farm.getInstance().getPontos() < CHICKEN_PRICE)
					aviso("Sem pontos suficientes!");
				else {
					Farm.getInstance().addImage(new Chicken(Farm.getInstance().getFarmer().getPosition()));
					refresh(CHICKEN_PRICE);
				}
			}
		});

		sheep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Farm.getInstance().getPontos() < SHEEP_PRICE)
					aviso("Sem pontos suficientes!");
				else {
					Farm.getInstance().addImage(new Sheep(Farm.getInstance().getFarmer().getPosition()));
					refresh(SHEEP_PRICE);
				}
			}
		});

		cultivator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Farm.getInstance().getPontos() < CULTIVATOR_PRICE)
					aviso("Sem pontos suficientes!");
				else {
					Farm.getInstance().addImage(new Cultivator(Farm.getInstance().getFarmer().getPosition()));
					refresh(CULTIVATOR_PRICE);
				}
			}
		});

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	public static void aviso(String message) {
		JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.WARNING_MESSAGE);
	}

	private void refresh(int pontos) {
		Farm.getInstance().takePontos(pontos);
		Farm.getInstance().refresh();
		txt.setText("Pontos: " + Farm.getInstance().getPontos());
	}

}

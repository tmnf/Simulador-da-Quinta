package farm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import animals.Chicken;
import animals.Sheep;
import entities.Cultivator;

public class Market extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton chicken, sheep, cultivator, ok;

	private static final int CHICKEN_POINTS = 50;
	private static final int SHEEP_POINTS = 100;
	private static final int CULTIVATOR_POINTS = 600;

	private JTextArea txt = new JTextArea();

	private static final int MAX_X = 500;
	private static final int MAX_Y = 250;

	public Market() {
		super("Mercado");
		setSize(MAX_X, MAX_Y);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		chicken = new JButton(new ImageIcon("images/chicken.png"));
		sheep = new JButton(new ImageIcon("images/sheep.png"));
		cultivator = new JButton(new ImageIcon("images/cultivator.png"));
		ok = new JButton("Ok");

		chicken.setLayout(new BorderLayout());
		JLabel label1 = new JLabel(CHICKEN_POINTS + " Pontos");
		chicken.add(BorderLayout.SOUTH, label1);

		sheep.setLayout(new BorderLayout());
		JLabel label2 = new JLabel(SHEEP_POINTS + " Pontos");
		sheep.add(BorderLayout.SOUTH, label2);

		cultivator.setLayout(new BorderLayout());
		JLabel label3 = new JLabel(CULTIVATOR_POINTS + " Pontos");
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
				if (Farm.getInstance().getPontos() < CHICKEN_POINTS)
					new Market("Sem pontos suficientes!");
				else {
					Farm.getInstance().addImage(new Chicken(Farm.getInstance().getFarmer().getPosition()));
					refresh(CHICKEN_POINTS);
				}
			}
		});

		sheep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Farm.getInstance().getPontos() < SHEEP_POINTS)
					new Market("Sem pontos suficientes!");
				else {
					Farm.getInstance().addImage(new Sheep(Farm.getInstance().getFarmer().getPosition()));
					refresh(SHEEP_POINTS);
				}
			}
		});

		cultivator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Farm.getInstance().getPontos() < CULTIVATOR_POINTS)
					new Market("Sem pontos suficientes!");
				else {
					Farm.getInstance().addImage(new Cultivator(Farm.getInstance().getFarmer().getPosition()));
					refresh(CULTIVATOR_POINTS);
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

	private Market(String message) {
		super("Aviso");
		setSize(300, 200);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		txt.setBounds(50, 20, 200, 50);
		txt.setFont(txt.getFont().deriveFont(18f));
		txt.setEditable(false);
		txt.setText(message);
		txt.setOpaque(false);

		ok = new JButton("Ok");
		ok.setBounds(125, 100, 50, 50);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(txt);
		add(ok);

		setVisible(true);

	}

	private void refresh(int pontos) {
		Farm.getInstance().takePontos(pontos);
		Farm.getInstance().refresh();
		txt.setText("Pontos: " + Farm.getInstance().getPontos());
	}

}

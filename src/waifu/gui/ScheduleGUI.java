package waifu.gui;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import waifu.Anime;
import waifu.Week;

public class ScheduleGUI extends JFrame {

	private JPanel contentPane;

	public ScheduleGUI(Anime anime, Week tiempoLibre) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTiempo = new JPanel();
		pnlTiempo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nº Episodios por día", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlTiempo, BorderLayout.WEST);
		GridBagLayout gbl_pnlTiempo = new GridBagLayout();
		gbl_pnlTiempo.columnWidths = new int[]{10, 39, 29, 47, 10, 0};
		gbl_pnlTiempo.rowHeights = new int[]{15, 19, 23, 32, 43, 0};
		gbl_pnlTiempo.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlTiempo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlTiempo.setLayout(gbl_pnlTiempo);
		
		JPanel pnlLunes = new JPanel();
		pnlLunes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlLunes = new GridBagConstraints();
		gbc_pnlLunes.fill = GridBagConstraints.BOTH;
		gbc_pnlLunes.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLunes.gridx = 1;
		gbc_pnlLunes.gridy = 1;
		pnlTiempo.add(pnlLunes, gbc_pnlLunes);
		pnlLunes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLunes = new JLabel("Lunes");
		lblLunes.setHorizontalAlignment(SwingConstants.CENTER);
		pnlLunes.add(lblLunes, BorderLayout.NORTH);
		
		JLabel lblEplunes = new JLabel(tiempoLibre.getDay(Week.MONDAY)+"");
		lblEplunes.setHorizontalAlignment(SwingConstants.CENTER);
		pnlLunes.add(lblEplunes, BorderLayout.SOUTH);
		
		JPanel pnlMartes = new JPanel();
		pnlMartes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlMartes = new GridBagConstraints();
		gbc_pnlMartes.fill = GridBagConstraints.BOTH;
		gbc_pnlMartes.insets = new Insets(0, 0, 5, 5);
		gbc_pnlMartes.gridx = 2;
		gbc_pnlMartes.gridy = 1;
		pnlTiempo.add(pnlMartes, gbc_pnlMartes);
		pnlMartes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMartes = new JLabel("Martes");
		lblMartes.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMartes.add(lblMartes, BorderLayout.NORTH);
		
		JLabel lblEpmartes = new JLabel(tiempoLibre.getDay(Week.TUESDAY)+"");
		lblEpmartes.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMartes.add(lblEpmartes, BorderLayout.SOUTH);
		
		JPanel pnlMiercoles = new JPanel();
		pnlMiercoles.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlMiercoles = new GridBagConstraints();
		gbc_pnlMiercoles.fill = GridBagConstraints.BOTH;
		gbc_pnlMiercoles.insets = new Insets(0, 0, 5, 5);
		gbc_pnlMiercoles.gridx = 3;
		gbc_pnlMiercoles.gridy = 1;
		pnlTiempo.add(pnlMiercoles, gbc_pnlMiercoles);
		pnlMiercoles.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMircoles = new JLabel("Miércoles");
		lblMircoles.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMiercoles.add(lblMircoles, BorderLayout.NORTH);
		
		JLabel lblEpmiercoles = new JLabel(tiempoLibre.getDay(Week.WEDNESDAY)+"");
		lblEpmiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMiercoles.add(lblEpmiercoles, BorderLayout.SOUTH);
		
		JPanel pnlJueves = new JPanel();
		pnlJueves.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlJueves = new GridBagConstraints();
		gbc_pnlJueves.fill = GridBagConstraints.BOTH;
		gbc_pnlJueves.insets = new Insets(0, 0, 5, 5);
		gbc_pnlJueves.gridx = 1;
		gbc_pnlJueves.gridy = 2;
		pnlTiempo.add(pnlJueves, gbc_pnlJueves);
		pnlJueves.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJueves = new JLabel("Jueves");
		lblJueves.setHorizontalAlignment(SwingConstants.CENTER);
		pnlJueves.add(lblJueves, BorderLayout.NORTH);
		
		JLabel lblEpjueves = new JLabel(tiempoLibre.getDay(Week.THURSDAY)+"");
		lblEpjueves.setHorizontalAlignment(SwingConstants.CENTER);
		pnlJueves.add(lblEpjueves, BorderLayout.SOUTH);
		
		JPanel pnlViernes = new JPanel();
		pnlViernes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlViernes = new GridBagConstraints();
		gbc_pnlViernes.fill = GridBagConstraints.BOTH;
		gbc_pnlViernes.insets = new Insets(0, 0, 5, 5);
		gbc_pnlViernes.gridx = 2;
		gbc_pnlViernes.gridy = 2;
		pnlTiempo.add(pnlViernes, gbc_pnlViernes);
		pnlViernes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblViernes = new JLabel("Viernes");
		lblViernes.setHorizontalAlignment(SwingConstants.CENTER);
		pnlViernes.add(lblViernes, BorderLayout.NORTH);
		
		JLabel lblEpviernes = new JLabel(tiempoLibre.getDay(Week.FRIDAY)+"");
		lblEpviernes.setHorizontalAlignment(SwingConstants.CENTER);
		pnlViernes.add(lblEpviernes, BorderLayout.SOUTH);
		
		JPanel pnlSabado = new JPanel();
		pnlSabado.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlSabado = new GridBagConstraints();
		gbc_pnlSabado.fill = GridBagConstraints.BOTH;
		gbc_pnlSabado.insets = new Insets(0, 0, 5, 5);
		gbc_pnlSabado.gridx = 3;
		gbc_pnlSabado.gridy = 2;
		pnlTiempo.add(pnlSabado, gbc_pnlSabado);
		pnlSabado.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSbado = new JLabel("Sábado");
		lblSbado.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSabado.add(lblSbado, BorderLayout.NORTH);
		
		JLabel lblEpsabado = new JLabel(tiempoLibre.getDay(Week.SATURDAY)+"");
		lblEpsabado.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSabado.add(lblEpsabado, BorderLayout.SOUTH);
		
		JPanel pnlDomingo = new JPanel();
		pnlDomingo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlDomingo = new GridBagConstraints();
		gbc_pnlDomingo.fill = GridBagConstraints.BOTH;
		gbc_pnlDomingo.insets = new Insets(0, 0, 5, 5);
		gbc_pnlDomingo.gridx = 1;
		gbc_pnlDomingo.gridy = 3;
		pnlTiempo.add(pnlDomingo, gbc_pnlDomingo);
		pnlDomingo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDomingo = new JLabel("Domingo");
		lblDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDomingo.add(lblDomingo, BorderLayout.NORTH);
		
		JLabel lblEpdomingo = new JLabel(tiempoLibre.getDay(Week.SUNDAY)+"");
		lblEpdomingo.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDomingo.add(lblEpdomingo, BorderLayout.SOUTH);
		
		JPanel pnlAnime = new JPanel();
		pnlAnime.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), anime.getName(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlAnime, BorderLayout.CENTER);
		pnlAnime.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSinopsis = new JPanel();
		pnlAnime.add(pnlSinopsis, BorderLayout.CENTER);
		pnlSinopsis.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlSinopsis.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textSinopsis = new JTextPane();
		textSinopsis.setEditable(false);
		textSinopsis.setText(anime.getSynopsis());
		scrollPane.setViewportView(textSinopsis);
	}
}
package waifu.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

import waifu.Anime;

public class AnimeGUI extends JFrame {

	private JPanel contentPane;

	public AnimeGUI(Anime anime) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), anime.getName(), TitledBorder.LEADING, TitledBorder.TOP, new Font(null,Font.BOLD,20), new Color(0, 0, 0)));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSinopsis = new JPanel();
		contentPane.add(pnlSinopsis, BorderLayout.CENTER);
		pnlSinopsis.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlSinopsis.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textSinopsis = new JTextPane();
		textSinopsis.setText(anime.getSynopsis());
		textSinopsis.setEditable(false);
		scrollPane.setViewportView(textSinopsis);
		
		JPanel pnlTags = new JPanel();
		contentPane.add(pnlTags, BorderLayout.NORTH);
		pnlTags.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pnlInfo = new JPanel();
		contentPane.add(pnlInfo, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlInfo = new GridBagLayout();
		gbl_pnlInfo.columnWidths = new int[]{15, 0, 0, 0, 15, 0};
		gbl_pnlInfo.rowHeights = new int[]{15, 0, 15, 0};
		gbl_pnlInfo.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlInfo.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		pnlInfo.setLayout(gbl_pnlInfo);
		
		JPanel pnlEp = new JPanel();
		pnlEp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlEp = new GridBagConstraints();
		gbc_pnlEp.insets = new Insets(0, 0, 5, 5);
		gbc_pnlEp.fill = GridBagConstraints.VERTICAL;
		gbc_pnlEp.gridx = 1;
		gbc_pnlEp.gridy = 1;
		pnlInfo.add(pnlEp, gbc_pnlEp);
		pnlEp.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEpisodios = new JLabel("Episodios");
		lblEpisodios.setHorizontalAlignment(SwingConstants.CENTER);
		lblEpisodios.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlEp.add(lblEpisodios, BorderLayout.NORTH);
		
		JLabel lblEpNumber = new JLabel(anime.getNChapters()+"");
		lblEpNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblEpNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlEp.add(lblEpNumber, BorderLayout.CENTER);
		
		JPanel pnlDuracion = new JPanel();
		pnlDuracion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlDuracion = new GridBagConstraints();
		gbc_pnlDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_pnlDuracion.fill = GridBagConstraints.VERTICAL;
		gbc_pnlDuracion.gridx = 2;
		gbc_pnlDuracion.gridy = 1;
		pnlInfo.add(pnlDuracion, gbc_pnlDuracion);
		pnlDuracion.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracion.setAlignmentX(0.5f);
		pnlDuracion.add(lblDuracion, BorderLayout.NORTH);
		
		JLabel lblDuration = new JLabel(anime.getDuration()+"");
		lblDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuration.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlDuracion.add(lblDuration, BorderLayout.CENTER);
		
		JPanel pnlPuntuacion = new JPanel();
		pnlPuntuacion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlPuntuacion = new GridBagConstraints();
		gbc_pnlPuntuacion.insets = new Insets(0, 0, 5, 5);
		gbc_pnlPuntuacion.fill = GridBagConstraints.BOTH;
		gbc_pnlPuntuacion.gridx = 3;
		gbc_pnlPuntuacion.gridy = 1;
		pnlInfo.add(pnlPuntuacion, gbc_pnlPuntuacion);
		pnlPuntuacion.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPuntuacion = new JLabel("Puntuación");
		lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntuacion.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlPuntuacion.add(lblPuntuacion, BorderLayout.NORTH);
		
		JLabel lblScore = new JLabel(anime.getScore()+"");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		pnlPuntuacion.add(lblScore, BorderLayout.CENTER);
		for (String textTag : anime.getTags()){
			pnlTags.add(new JLabel("["+textTag+"]"));
		}
	}
}

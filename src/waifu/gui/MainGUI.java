package waifu.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.UIManager;

import java.util.List;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import waifu.Anime;

public class MainGUI extends JFrame implements View {

	private JPanel contentPane;
	private Controller controller;
	
	private JComboBox<String> cbCategoriaAnime;
	private JSpinner spinLunes;
	private JSpinner spinMartes;
	private JSpinner spinMiercoles;
	private JSpinner spinJueves;
	private JSpinner spinViernes;
	private JSpinner spinSabado;
	private JSpinner spinDomingo;
	private JList<Anime> lstAnime;

	public MainGUI(Controller controller) {
		/*Controller*/
		this.controller=controller;
		/*Opciones JPanel*/
		setResizable(false);
		setMinimumSize(new Dimension(500, 300));
		setTitle("WAIFU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTime = new JPanel();
		pnlTime.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tiempo libre (en minutos)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(pnlTime, BorderLayout.WEST);
		GridBagLayout gbl_pnlTime = new GridBagLayout();
		gbl_pnlTime.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
		gbl_pnlTime.rowHeights = new int[]{15, 0, 0, 0, 15, 0};
		gbl_pnlTime.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlTime.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlTime.setLayout(gbl_pnlTime);
		
		JPanel pnlLunes = new JPanel();
		pnlLunes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlLunes = new GridBagConstraints();
		gbc_pnlLunes.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLunes.fill = GridBagConstraints.BOTH;
		gbc_pnlLunes.gridx = 1;
		gbc_pnlLunes.gridy = 1;
		pnlTime.add(pnlLunes, gbc_pnlLunes);
		pnlLunes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLunes = new JLabel("Lunes");
		lblLunes.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlLunes.add(lblLunes, BorderLayout.NORTH);
		
		spinLunes = new JSpinner();
		spinLunes.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		spinLunes.setName("");
		pnlLunes.add(spinLunes, BorderLayout.SOUTH);
		
		JPanel pnlMartes = new JPanel();
		pnlMartes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlMartes = new GridBagConstraints();
		gbc_pnlMartes.insets = new Insets(0, 0, 5, 5);
		gbc_pnlMartes.fill = GridBagConstraints.BOTH;
		gbc_pnlMartes.gridx = 2;
		gbc_pnlMartes.gridy = 1;
		pnlTime.add(pnlMartes, gbc_pnlMartes);
		pnlMartes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMartes = new JLabel("Martes");
		pnlMartes.add(lblMartes, BorderLayout.WEST);
		
		spinMartes = new JSpinner();
		spinMartes.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		pnlMartes.add(spinMartes, BorderLayout.SOUTH);
		
		JPanel pnlMiercoles = new JPanel();
		pnlMiercoles.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlMiercoles = new GridBagConstraints();
		gbc_pnlMiercoles.insets = new Insets(0, 0, 5, 5);
		gbc_pnlMiercoles.fill = GridBagConstraints.BOTH;
		gbc_pnlMiercoles.gridx = 3;
		gbc_pnlMiercoles.gridy = 1;
		pnlTime.add(pnlMiercoles, gbc_pnlMiercoles);
		pnlMiercoles.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMircoles = new JLabel("Miércoles");
		pnlMiercoles.add(lblMircoles, BorderLayout.WEST);
		
		spinMiercoles = new JSpinner();
		spinMiercoles.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		pnlMiercoles.add(spinMiercoles, BorderLayout.SOUTH);
		
		JPanel pnlJueves = new JPanel();
		pnlJueves.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlJueves = new GridBagConstraints();
		gbc_pnlJueves.insets = new Insets(0, 0, 5, 5);
		gbc_pnlJueves.fill = GridBagConstraints.BOTH;
		gbc_pnlJueves.gridx = 1;
		gbc_pnlJueves.gridy = 2;
		pnlTime.add(pnlJueves, gbc_pnlJueves);
		pnlJueves.setLayout(new BorderLayout(0, 0));
		
		JLabel lblJueves = new JLabel("Jueves");
		pnlJueves.add(lblJueves, BorderLayout.WEST);
		
		spinJueves = new JSpinner();
		spinJueves.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		pnlJueves.add(spinJueves, BorderLayout.SOUTH);
		
		JPanel pnlViernes = new JPanel();
		pnlViernes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlViernes = new GridBagConstraints();
		gbc_pnlViernes.insets = new Insets(0, 0, 5, 5);
		gbc_pnlViernes.fill = GridBagConstraints.BOTH;
		gbc_pnlViernes.gridx = 2;
		gbc_pnlViernes.gridy = 2;
		pnlTime.add(pnlViernes, gbc_pnlViernes);
		pnlViernes.setLayout(new BorderLayout(0, 0));
		
		JLabel lblViernes = new JLabel("Viernes");
		pnlViernes.add(lblViernes, BorderLayout.WEST);
		
		spinViernes = new JSpinner();
		spinViernes.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		pnlViernes.add(spinViernes, BorderLayout.SOUTH);
		
		JPanel pnlSabado = new JPanel();
		pnlSabado.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlSabado = new GridBagConstraints();
		gbc_pnlSabado.insets = new Insets(0, 0, 5, 5);
		gbc_pnlSabado.fill = GridBagConstraints.BOTH;
		gbc_pnlSabado.gridx = 3;
		gbc_pnlSabado.gridy = 2;
		pnlTime.add(pnlSabado, gbc_pnlSabado);
		pnlSabado.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSbado = new JLabel("Sábado");
		pnlSabado.add(lblSbado, BorderLayout.WEST);
		
		spinSabado = new JSpinner();
		spinSabado.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		pnlSabado.add(spinSabado, BorderLayout.SOUTH);
		
		JPanel pnlDomingo = new JPanel();
		pnlDomingo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_pnlDomingo = new GridBagConstraints();
		gbc_pnlDomingo.insets = new Insets(0, 0, 5, 5);
		gbc_pnlDomingo.fill = GridBagConstraints.BOTH;
		gbc_pnlDomingo.gridx = 1;
		gbc_pnlDomingo.gridy = 3;
		pnlTime.add(pnlDomingo, gbc_pnlDomingo);
		pnlDomingo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDomingo = new JLabel("Domingo");
		pnlDomingo.add(lblDomingo, BorderLayout.WEST);
		
		spinDomingo = new JSpinner();
		spinDomingo.setModel(new SpinnerNumberModel(0, null, 1440, 1));
		pnlDomingo.add(spinDomingo, BorderLayout.SOUTH);
		
		JPanel pnlTags = new JPanel();
		pnlTags.setBorder(new TitledBorder(null, "Categoría anime", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlTags, BorderLayout.NORTH);
		pnlTags.setLayout(new BorderLayout(0, 0));
		
		cbCategoriaAnime = new JComboBox<String>();
		pnlTags.add(cbCategoriaAnime, BorderLayout.CENTER);
		
		JPanel pnlButtons = new JPanel();
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new BorderLayout(0, 0));
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new BtnBuscarActionListener());
		pnlButtons.add(btnBuscar, BorderLayout.CENTER);
		
		JScrollPane scrollAnime = new JScrollPane();
		scrollAnime.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollAnime.setBorder(new TitledBorder(null, "Anime", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollAnime, BorderLayout.CENTER);
		
		lstAnime = new JList<Anime>();
		lstAnime.addMouseListener(new LstAnimeMouseListener());
		lstAnime.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollAnime.setViewportView(lstAnime);
		setVisible(true);
		/*Conseguir tags*/
		waitTags();
	}
	public void waitTags(){
		JOptionPane.showMessageDialog(this, "Pulse aceptar para cargar las categorías de los animes.\nPuede llevar un tiempo.");
		controller.askTags();
	}
	
	public void giveTags(List<String> tags) {
		for (String tag : tags)
			cbCategoriaAnime.addItem(tag);
		cbCategoriaAnime.setSelectedIndex(0);
		
	}

	public void giveAnimes(List<Anime> animes) {
		lstAnime.setListData(new Vector<Anime>(animes));
	}
	
	public void notifyError(String error){
		
	}

	private class BtnBuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String tag = (String) cbCategoriaAnime.getSelectedItem();
			controller.askAnimes(tag,10);
		}
	}
	private class LstAnimeMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getClickCount() == 2) {
				AnimeGUI anime = new AnimeGUI(lstAnime.getSelectedValue());
				anime.setVisible(true);
			}
		}
	}
}

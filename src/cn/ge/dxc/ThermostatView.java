package cn.ge.dxc;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class ThermostatView extends JFrame
{

	private Thermostat thermostat;
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public JTextField textDesiredMaxTemp;

	public JTextField textDesiredMinTemp;

	public JTextField textOverrideActualTemp;

	public JLabel lblActualTemp;

	public JLabel lblHomeOccupied;

	public JLabel lblAirCon;

	public JLabel lblFurnace;

	public JButton btnSetDesiredMaxTemp;

	public JButton btnSetDesiredMinTemp;

	public JButton btnOverrideActualTemp;

	public JButton btnToggleHomeOccupied;
	
	private String actualTemperature;
	private String desiredMinTemperature;
	private String desiredMaxTemperature;

	/**
	 * Main entry point for the GUI.
	 * 
	 * @param args
	 *            Command-line arguments (not used)
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TemperatureSensor temperatureSensor = new TemperatureSensor();
					OccupationSensor occupationSensor = new OccupationSensor();
					Furnace furnace = new Furnace();
					Aircon aircon = new Aircon();
					Thermostat thermostat = new Thermostat(temperatureSensor, occupationSensor, furnace, aircon);
					
					ThermostatView frame = new ThermostatView(thermostat);
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructs a ThermostatView
	 */
	public ThermostatView(Thermostat thermostat)
	{
		//initiate Thermostat
		this.thermostat = thermostat;
		
		//initiate Data
		initData();
		
		//initiate GUI components
		initComponents();
			
	}

	/**
	 * Initializes the GUI components. This is IDE generated code.
	 */
	private void initComponents()
	{

		
		setResizable(false);
		setTitle("GE Home Automation Thermostat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]
		{ 141, 141, 34, 43 };
		gbl_contentPane.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0 };
		gbl_contentPane.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Desired Temperature (\u00B0C)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		JLabel lblNewLabel_1 = new JLabel("    Minimum");
		panel.add(lblNewLabel_1);

		textDesiredMinTemp = new JTextField();
		textDesiredMinTemp.setText(desiredMinTemperature);
		panel.add(textDesiredMinTemp);
		textDesiredMinTemp.setColumns(2);

		btnSetDesiredMinTemp = new JButton("Set");
		btnSetDesiredMinTemp.addActionListener(new BtnSetDesiredMinTempActionListener());
		panel.add(btnSetDesiredMinTemp);

		JLabel lblNewLabel = new JLabel("Maximum");
		panel.add(lblNewLabel);

		textDesiredMaxTemp = new JTextField();
		textDesiredMaxTemp.setText(desiredMaxTemperature);
		panel.add(textDesiredMaxTemp);
		textDesiredMaxTemp.setColumns(2);

		btnSetDesiredMaxTemp = new JButton("Set");
		btnSetDesiredMaxTemp.addActionListener(new BtnSetDesiredMaxTempActionListener());
		panel.add(btnSetDesiredMaxTemp);
		
		JLabel lblNewLabel_2 = new JLabel("Actual Temperature:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		lblActualTemp = new JLabel("-");
		GridBagConstraints gbc_lblActualTemp = new GridBagConstraints();
		gbc_lblActualTemp.anchor = GridBagConstraints.WEST;
		gbc_lblActualTemp.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualTemp.gridx = 1;
		gbc_lblActualTemp.gridy = 1;
		contentPane.add(lblActualTemp, gbc_lblActualTemp);

		textOverrideActualTemp = new JTextField();
		textOverrideActualTemp.setText(actualTemperature);
		GridBagConstraints gbc_textOverrideActualTemp = new GridBagConstraints();
		gbc_textOverrideActualTemp.insets = new Insets(0, 0, 5, 5);
		gbc_textOverrideActualTemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textOverrideActualTemp.gridx = 2;
		gbc_textOverrideActualTemp.gridy = 1;
		contentPane.add(textOverrideActualTemp, gbc_textOverrideActualTemp);
		textOverrideActualTemp.setColumns(2);

		btnOverrideActualTemp = new JButton("Override");
		btnOverrideActualTemp.addActionListener(new BtnOverrideActualTempActionListener());
		GridBagConstraints gbc_btnOverrideActualTemp = new GridBagConstraints();
		gbc_btnOverrideActualTemp.insets = new Insets(0, 0, 5, 0);
		gbc_btnOverrideActualTemp.gridx = 3;
		gbc_btnOverrideActualTemp.gridy = 1;
		contentPane.add(btnOverrideActualTemp, gbc_btnOverrideActualTemp);

		JLabel lblNewLabel_6 = new JLabel("Home Occupied:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

		lblHomeOccupied = new JLabel("-");
		GridBagConstraints gbc_lblHomeOccupied = new GridBagConstraints();
		gbc_lblHomeOccupied.anchor = GridBagConstraints.WEST;
		gbc_lblHomeOccupied.insets = new Insets(0, 0, 5, 5);
		gbc_lblHomeOccupied.gridx = 1;
		gbc_lblHomeOccupied.gridy = 2;
		contentPane.add(lblHomeOccupied, gbc_lblHomeOccupied);

		btnToggleHomeOccupied = new JButton("Toggle");
		btnToggleHomeOccupied.addActionListener(new BtnToggleHomeOccupiedActionListener());
		GridBagConstraints gbc_btnToggleHomeOccupied = new GridBagConstraints();
		gbc_btnToggleHomeOccupied.insets = new Insets(0, 0, 5, 0);
		gbc_btnToggleHomeOccupied.gridx = 3;
		gbc_btnToggleHomeOccupied.gridy = 2;
		contentPane.add(btnToggleHomeOccupied, gbc_btnToggleHomeOccupied);

		JLabel lblNewLabel_8 = new JLabel("Air Conditioning:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 3;
		contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);

		lblAirCon = new JLabel("-");
		GridBagConstraints gbc_lblAirCon = new GridBagConstraints();
		gbc_lblAirCon.anchor = GridBagConstraints.WEST;
		gbc_lblAirCon.insets = new Insets(0, 0, 5, 5);
		gbc_lblAirCon.gridx = 1;
		gbc_lblAirCon.gridy = 3;
		contentPane.add(lblAirCon, gbc_lblAirCon);

		JLabel lblNewLabel_4 = new JLabel("Furnace:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		lblFurnace = new JLabel("-");
		GridBagConstraints gbc_lblFurnace = new GridBagConstraints();
		gbc_lblFurnace.insets = new Insets(0, 0, 0, 5);
		gbc_lblFurnace.anchor = GridBagConstraints.WEST;
		gbc_lblFurnace.gridx = 1;
		gbc_lblFurnace.gridy = 4;
		contentPane.add(lblFurnace, gbc_lblFurnace);
		
    	updateTemperature();
    	updateOccupation();
    	updateFurnaceAndAirconStatus();
        
	}

	/**
	 * Initializes the thermostat data. 
	 */
	private void initData() { 

		actualTemperature="22";
		desiredMinTemperature="20";
		desiredMaxTemperature="26";
		
    	thermostat.setSensorTemperature(Double.parseDouble(actualTemperature));
    	thermostat.setDesiredMaxTemperature(Double.parseDouble(desiredMaxTemperature));
    	thermostat.setDesiredMinTemperature(Double.parseDouble(desiredMinTemperature));
    	thermostat.setOccupation(true);
    	
    }
	
	
	/**
	 * Action listener for clicking the override button
	 */
	private class BtnOverrideActualTempActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!Pattern.matches("^-?[0-9]*.?[0-9]*", textOverrideActualTemp.getText()))					
				{
				JOptionPane.showMessageDialog(contentPane, "Please enter a number");				
				textOverrideActualTemp.setText(actualTemperature);
				return;
				}
			   
			//Set temperature according to overrode temperature.
			actualTemperature=textOverrideActualTemp.getText();
			thermostat.setSensorTemperature(Double.parseDouble(actualTemperature));
			
			//update actual temperature in UI
			updateTemperature();
			
			//update Furnace and Aircon status in UI
			updateFurnaceAndAirconStatus();
		}
	}

	/**
	 * Action listener for clicking the set desired max temperature button
	 */
	private class BtnSetDesiredMaxTempActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Set desired max temperature of thermostat per UI input
			if(!isGoodRange((textDesiredMaxTemp.getText())))					
				{
				JOptionPane.showMessageDialog(contentPane, "Please enter a integer between 16 and 30");
				textDesiredMaxTemp.setText(desiredMaxTemperature);
				return;
				}

			//Set temperature according to overrode temperature.
			desiredMaxTemperature=textDesiredMaxTemp.getText();
			thermostat.setDesiredMaxTemperature(Double.parseDouble(desiredMaxTemperature));
			
			textDesiredMaxTemp.setText(String.valueOf((int)thermostat.getMaxTemperature()));
			//update Furnace and Aircon status in UI
			updateFurnaceAndAirconStatus();
		}
	}

	/**
	 * Action listener for clicking the set desired min temperature button
	 */
	private class BtnSetDesiredMinTempActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//Set desired min temperature of thermostat per UI input
			if(!isGoodRange((textDesiredMinTemp.getText())))					
				{
				JOptionPane.showMessageDialog(contentPane, "Please enter a integer between 16 and 30");
				textDesiredMinTemp.setText(desiredMinTemperature);
				return;
				}
			
			//Set temperature according to overrode temperature.
		    desiredMinTemperature=textDesiredMinTemp.getText();
			thermostat.setDesiredMinTemperature(Double.parseDouble(desiredMinTemperature));
			textDesiredMinTemp.setText(String.valueOf((int)thermostat.getMinTemperature()));
			
			//update Furnace and Aircon status in UI
			updateFurnaceAndAirconStatus();
		}
	}

	/**
	 * Action listener for clicking the toggle home occupied button
	 */
	private class BtnToggleHomeOccupiedActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//toggle occupied status of thermostat
			if(thermostat.isOccupied())
				thermostat.setOccupation(false);
			else
				thermostat.setOccupation(true);
			
			//update occupied status in UI
			updateOccupation();
			
			//update Furnace and Aircon status in UI
			updateFurnaceAndAirconStatus();
		}
	}

	/**
	 * Update actual temperature in UI
	 */
    private void updateTemperature() {
    	lblActualTemp.setText(String.valueOf(thermostat.getSenserTemperature()));
    }
    
	/**
	 * Update actual occupied status in UI
	 */
    private void updateOccupation() {
    	if(thermostat.isOccupied())
    		lblHomeOccupied.setText("Occupied");
    	else
    		lblHomeOccupied.setText("Empety");
    }
    
    /**
	 * Update Furnace and Aircon status in UI
	 */
    private void updateFurnaceAndAirconStatus() {
    	if(thermostat.isFurnaceRunning())
    		lblFurnace.setText("Running");
    	else
    		lblFurnace.setText("Stopped");
    	if(thermostat.isAirconRunning())
    		lblAirCon.setText("Running");
    	else
    		lblAirCon.setText("Stopped");
    }
    
    /**
	 * Decide if the input is a number between 16-30
	 */   
    private boolean isGoodRange(String str)
    {
    	if (Pattern.matches("[0-9]+", str)&& Double.valueOf(str)>=16 && Double.valueOf(str)<=30)
    		return true;
    	else
    		return false;
    }


}

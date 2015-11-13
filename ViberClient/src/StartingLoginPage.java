import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

import java.awt.Color;
public class StartingLoginPage 
{
	String User,Pass;
	static UDPClient connect;
	
	private JFrame frame;
	private JTextField UserName;
	private JPasswordField Password;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMobileNumber;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblBirthday;
	private JComboBox<?> Month;
	private JSpinner Year;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnSignUp;
	private JLabel lblPassword_1;
	private JLabel lblRetypePassword;
	private JLabel lblByClickingSign;
	private JLabel lblByClickingSign_1;
	private JLabel lblSignUp;
	
	static LoginPage L = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					StartingLoginPage window = new StartingLoginPage();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public StartingLoginPage() throws Exception {
		connect = new UDPClient();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame("NUCES Chat Services");
		frame.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 12));
		frame.setResizable(false);
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/icon.png")));
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel backpic = new JLabel("");
		backpic.setBounds(0, 0, 494, 371);
		frame.getContentPane().add(backpic);
		
		JPanel Error_Panel = new JPanel();
		Error_Panel.setBounds(0, 58, 209, 302);
		frame.getContentPane().add(Error_Panel);
		Error_Panel.setLayout(null);
		
		final JLabel ErrorStatement = new JLabel("");
		ErrorStatement.setBounds(10, 66, 197, 51);
		Error_Panel.add(ErrorStatement);
		ErrorStatement.setForeground(Color.RED);
		ErrorStatement.setFont(new Font("Charlemagne Std", Font.BOLD, 15));
		
		JPanel Login_Panel = new JPanel();
		Login_Panel.setBounds(97, 0, 397, 47);
		frame.getContentPane().add(Login_Panel);
		Login_Panel.setLayout(null);
		
		UserName = new JTextField();
		UserName.setBounds(83, 25, 92, 20);
		Login_Panel.add(UserName);
		UserName.setColumns(10);
		
		Password = new JPasswordField();
		Password.setBounds(185, 25, 102, 20);
		Login_Panel.add(Password);
		
		final JLabel lblUserName = new JLabel("User Name");
		lblUserName.setLabelFor(UserName);
		lblUserName.setBounds(83, 11, 92, 9);
		Login_Panel.add(lblUserName);
		
		final JLabel lblPassword = new JLabel("Password");
		lblPassword.setLabelFor(Password);
		lblPassword.setBounds(185, 11, 102, 9);
		Login_Panel.add(lblPassword);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				User = UserName.getText();
				Pass = Password.getText();
				if(User.equals(""))
					ErrorStatement.setText("User Name Required");
				else if(Pass.equals(""))
					ErrorStatement.setText("Password Required");
				else
				{
					if(connect.Login(User,Pass) == true)
					{
						try 
						{
							L = new LoginPage(User, Pass);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						L.running();
						frame.dispose();						
					}
					else
						ErrorStatement.setText("Wrong Input");
				}
			}
		});
		btnLogin.setFont(new Font("Snap ITC", Font.PLAIN, 14));
		btnLogin.setBounds(297, 11, 90, 34);
		Login_Panel.add(btnLogin);
		
		JPanel Signup_Panel = new JPanel();
		Signup_Panel.setBounds(216, 58, 278, 313);
		frame.getContentPane().add(Signup_Panel);
		Signup_Panel.setLayout(null);
		
		txtFirstName = new JTextField("FIRST NAME");
		txtFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(txtFirstName.getText().equals("FIRST NAME"))
					txtFirstName.setText("");	
			}
		});
		txtFirstName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtFirstName.getText().equals("FIRST NAME"))
					txtFirstName.setText("");		
			}
		});
		txtFirstName.setBounds(0, 29, 124, 20);
		Signup_Panel.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField("LAST NAME");
		txtLastName.setHorizontalAlignment(SwingConstants.CENTER);
		txtLastName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(txtLastName.getText().equals("LAST NAME"))
					txtLastName.setText("");
			}
		});
		txtLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(txtLastName.getText().equals("LAST NAME"))
					txtLastName.setText("");
			}
		});
		txtLastName.setBounds(134, 29, 134, 20);
		Signup_Panel.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtMobileNumber = new JTextField("MOBILE NUMBER");
		txtMobileNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtMobileNumber.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent arg0) 
			{

				if(txtMobileNumber.getText().equals("MOBILE NUMBER"))
					txtMobileNumber.setText("");
			}
		});
		txtMobileNumber.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(txtMobileNumber.getText().equals("MOBILE NUMBER"))
					txtMobileNumber.setText("");
			}
		});
		txtMobileNumber.setBounds(0, 60, 268, 20);
		Signup_Panel.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(0, 91, 268, 20);
		Signup_Panel.add(passwordField);
		
		lblPassword_1 = new JLabel("PASSWORD");
		lblPassword_1.setBounds(0, 78, 268, 14);
		Signup_Panel.add(lblPassword_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(0, 122, 268, 20);
		Signup_Panel.add(passwordField_1);
		
		lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(0, 147, 69, 14);
		Signup_Panel.add(lblBirthday);
		
		final JSpinner Date = new JSpinner();
		Date.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		Date.setBounds(69, 163, 43, 20);
		Signup_Panel.add(Date);
		
		Month = new JComboBox(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
		Month.setBounds(0, 163, 59, 20);
		Signup_Panel.add(Month);
		
		Year = new JSpinner();
		Year.setModel(new SpinnerNumberModel(1995, 1990, 2013, 1));
		Year.setBounds(117, 163, 68, 20);
		Signup_Panel.add(Year);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(0, 206, 59, 23);
		Signup_Panel.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(61, 206, 91, 23);
		Signup_Panel.add(rdbtnFemale);
		
		ButtonGroup Buttongroup = new ButtonGroup();
		Buttongroup.add(rdbtnMale);
		Buttongroup.add(rdbtnFemale);
        

		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				User = txtFirstName.getText();
				String Mob = txtMobileNumber.getText();
				Pass=passwordField.getText();
				if(User.equals("") || User.equals("FIRST NAME"))
					ErrorStatement.setText("First Name Empty");
				else if((txtLastName.getText()).equals("")||(txtLastName.getText()).equals("LAST NAME"))
					ErrorStatement.setText("Last Name Empty");
				else if(Mob.equals("")||Mob.equals("MOBILE NUMBER"))
					ErrorStatement.setText("Mobile Field Empty");
				else if(Pass.equals(""))
					ErrorStatement.setText("Password Field Empty");
				else if(!passwordField_1.getText().equals(Pass))
					ErrorStatement.setText("Password Doesn't Match");
				else 
				{
					User +=txtLastName.getText();
					String D = Date.getValue().toString();
					String Y = Year.getValue().toString();
					String M = Month.getSelectedItem().toString();
					String gender = null;
					if (rdbtnMale.isSelected())
						gender = "m";
					else if (rdbtnFemale.isSelected())
						gender = "f";
					if(!rdbtnMale.isSelected() && !rdbtnFemale.isSelected())
						ErrorStatement.setText("Gender Empty");
					else 
					{
						if (connect.SignUp(User, Pass, Mob, D, M, Y, gender).equals("true")) 
						{
							try 
							{
								L = new LoginPage(User, Pass);
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
							L.running();
							frame.dispose();
						} 
						else
							ErrorStatement.setText("Already Exist");
					}
				}
				
			}
		});
		btnSignUp.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		btnSignUp.setBounds(10, 265, 114, 37);
		Signup_Panel.add(btnSignUp);
		
		lblRetypePassword = new JLabel("RETYPE PASSWORD");
		lblRetypePassword.setBounds(0, 108, 124, 14);
		Signup_Panel.add(lblRetypePassword);
		
		lblByClickingSign = new JLabel("By clicking Sign Up, you agree to our Terms and that you");
		lblByClickingSign.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblByClickingSign.setBounds(10, 227, 258, 14);
		Signup_Panel.add(lblByClickingSign);
		
		lblByClickingSign_1 = new JLabel("have read our Data Use Policy, including our Cookie Use.");
		lblByClickingSign_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblByClickingSign_1.setBounds(10, 240, 258, 14);
		Signup_Panel.add(lblByClickingSign_1);
		
		lblSignUp = new JLabel("SIGN UP");
		lblSignUp.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		lblSignUp.setBounds(0, 0, 124, 30);
		Signup_Panel.add(lblSignUp);
	}
}

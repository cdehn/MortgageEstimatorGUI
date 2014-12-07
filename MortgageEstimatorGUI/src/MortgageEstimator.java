import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;


public class MortgageEstimator {

	private JFrame frame;
	private JTextField totalIncome;
	private JTextField debt;
	private JTextField maxPayment;
	private JTextField maxMortgage;
	private JTextField interest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MortgageEstimator window = new MortgageEstimator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MortgageEstimator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblTotalGrossIncome = new JLabel("Total Gross Income");
		springLayout.putConstraint(SpringLayout.WEST, lblTotalGrossIncome, 44, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblTotalGrossIncome);
		
		JLabel lblTotalDebt = new JLabel("Total Debt");
		springLayout.putConstraint(SpringLayout.NORTH, lblTotalDebt, 16, SpringLayout.SOUTH, lblTotalGrossIncome);
		springLayout.putConstraint(SpringLayout.WEST, lblTotalDebt, 0, SpringLayout.WEST, lblTotalGrossIncome);
		frame.getContentPane().add(lblTotalDebt);
		
		JLabel lblTerminYears = new JLabel("Term (in years)");
		springLayout.putConstraint(SpringLayout.WEST, lblTerminYears, 45, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblTerminYears);
		
		JLabel lblMaximumPayment = new JLabel("Maximum Payment");
		springLayout.putConstraint(SpringLayout.SOUTH, lblTerminYears, -16, SpringLayout.NORTH, lblMaximumPayment);
		springLayout.putConstraint(SpringLayout.WEST, lblMaximumPayment, 0, SpringLayout.WEST, lblTotalGrossIncome);
		frame.getContentPane().add(lblMaximumPayment);
		
		JLabel lblMaximumMortgage = new JLabel("Maximum Mortgage");
		springLayout.putConstraint(SpringLayout.NORTH, lblMaximumMortgage, 20, SpringLayout.SOUTH, lblMaximumPayment);
		springLayout.putConstraint(SpringLayout.WEST, lblMaximumMortgage, 44, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblMaximumMortgage);
		
		totalIncome = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblTotalGrossIncome, 3, SpringLayout.NORTH, totalIncome);
		springLayout.putConstraint(SpringLayout.NORTH, totalIncome, 24, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, totalIncome, -201, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, totalIncome, -71, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(totalIncome);
		totalIncome.setColumns(10);
		
		debt = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, debt, -3, SpringLayout.NORTH, lblTotalDebt);
		springLayout.putConstraint(SpringLayout.WEST, debt, 0, SpringLayout.WEST, totalIncome);
		springLayout.putConstraint(SpringLayout.EAST, debt, 0, SpringLayout.EAST, totalIncome);
		frame.getContentPane().add(debt);
		debt.setColumns(10);
		
		maxPayment = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblMaximumPayment, 3, SpringLayout.NORTH, maxPayment);
		springLayout.putConstraint(SpringLayout.WEST, maxPayment, 0, SpringLayout.WEST, totalIncome);
		springLayout.putConstraint(SpringLayout.EAST, maxPayment, 0, SpringLayout.EAST, totalIncome);
		frame.getContentPane().add(maxPayment);
		maxPayment.setColumns(10);
		
		maxMortgage = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, maxMortgage, 181, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, maxPayment, -17, SpringLayout.NORTH, maxMortgage);
		springLayout.putConstraint(SpringLayout.WEST, maxMortgage, 0, SpringLayout.WEST, totalIncome);
		springLayout.putConstraint(SpringLayout.EAST, maxMortgage, 0, SpringLayout.EAST, totalIncome);
		frame.getContentPane().add(maxMortgage);
		maxMortgage.setColumns(10);
		
		JComboBox term = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, term, -3, SpringLayout.NORTH, lblTerminYears);
		springLayout.putConstraint(SpringLayout.WEST, term, 0, SpringLayout.WEST, totalIncome);
		springLayout.putConstraint(SpringLayout.EAST, term, -117, SpringLayout.EAST, frame.getContentPane());
		term.setModel(new DefaultComboBoxModel(new String[] {"10", "15", "30"}));
		frame.getContentPane().add(term);
		
		final double income = Double.parseDouble(totalIncome.getText());
		final double totalDebt = Double.parseDouble(debt.getText());
		final double years = Double.parseDouble((String) term.getSelectedItem());
		final double interestRate = Double.parseDouble(interest.getText());
		
		JButton btnCalculate = new JButton("Calculate");
		springLayout.putConstraint(SpringLayout.NORTH, btnCalculate, 16, SpringLayout.SOUTH, maxMortgage);
		springLayout.putConstraint(SpringLayout.WEST, btnCalculate, 0, SpringLayout.WEST, totalIncome);
		frame.getContentPane().add(btnCalculate);
		
		btnCalculate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			double eighteen = Calculate.eighteenPercent(income);
			double thirtysix = Calculate.thirtysixPercent(income, totalDebt);
			double minimum = Calculate.minimum(eighteen, thirtysix);
			double interest = (interestRate / 12);
			double payments = (years * 12);
			double pv = Calculate.pv(interest, payments, minimum, 0, false);
			maxPayment.setText("$" + minimum);
			maxMortgage.setText("$" + pv);
		}
	}); 
		
		JLabel lblAnnualInterestRate = new JLabel("Annual Interest Rate");
		springLayout.putConstraint(SpringLayout.NORTH, lblAnnualInterestRate, 16, SpringLayout.SOUTH, lblTotalDebt);
		springLayout.putConstraint(SpringLayout.WEST, lblAnnualInterestRate, 0, SpringLayout.WEST, lblTotalGrossIncome);
		frame.getContentPane().add(lblAnnualInterestRate);
		
		interest = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, interest, 7, SpringLayout.SOUTH, debt);
		springLayout.putConstraint(SpringLayout.WEST, interest, 0, SpringLayout.WEST, totalIncome);
		springLayout.putConstraint(SpringLayout.EAST, interest, 0, SpringLayout.EAST, totalIncome);
		frame.getContentPane().add(interest);
		interest.setColumns(10);

	}
}
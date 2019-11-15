import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class Pizzeria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pizzeria implements ActionListener
{
    private JFrame frame = new JFrame("Pizzeria");
    private JLabel labelOne, labelTwo, labelThree, costLabel;
    private JPanel panel = new JPanel();
    double price;
    public Pizzeria()
    { 
        makeFrame();
    }

    public void makeFrame()
    {
        frame.setSize(700,700);
        Font labelFont = new Font("Arial", Font.BOLD, 36);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setVisible(true);

        JLabel labelOne = new JLabel("Dominos");
        JLabel labelTwo = new JLabel("Pizza Time");
        JLabel labelThree = new JLabel("Please Enter Your Name");
        JTextField nameField = new JTextField(25);
        JButton costButton = new JButton("Calculate cost of Pizza");
        JLabel labelFour = new JLabel("Please pick pizza size");
        JCheckBox boxSmall = new JCheckBox("Small");
        JCheckBox boxMedi = new JCheckBox("Medium");
        JCheckBox boxLarge = new JCheckBox("Large");
        JLabel toppings = new JLabel("Please select your toppings");
        JCheckBox boxToppingsOne = new JCheckBox("Pepperoni");
        JCheckBox boxToppingsTwo = new JCheckBox("Peppers");
        JCheckBox boxToppingsThree = new JCheckBox("Pineapple");
        JCheckBox boxToppingsFour = new JCheckBox("Ham");
        JLabel costLabel = new JLabel("£$£$");

        labelOne.setFont(labelFont);
        labelTwo.setFont(labelFont);
        labelThree.setFont(labelFont);
        labelFour.setFont(labelFont);
        toppings.setFont(labelFont);
        costLabel.setFont(labelFont);

        frame.add(labelOne);
        frame.add(Box.createVerticalStrut(100));
        frame.add(Box.createHorizontalStrut(100));
        frame.add(labelTwo);
        frame.add(Box.createVerticalStrut(100));
        frame.add(labelThree);
        frame.add(Box.createVerticalStrut(100));
        frame.add(nameField);
        frame.add(Box.createVerticalStrut(100));
        frame.add(costButton);
        frame.add(Box.createVerticalStrut(100));
        frame.add(labelFour);
        frame.add(boxSmall);
        frame.add(boxMedi);
        frame.add(boxLarge);
        frame.add(toppings);
        frame.add(boxToppingsOne);
        frame.add(boxToppingsTwo);
        frame.add(boxToppingsThree);
        frame.add(boxToppingsFour);
        frame.add(costLabel);

        costButton.addActionListener(source ->{
                price = 0;
                if(boxSmall.isSelected()) {
                    price += 7.50;
                }if(boxMedi.isSelected()){
                    price += 9.75;
                }if (boxLarge.isSelected()){
                    price += 11.50;
                }if (boxToppingsOne.isSelected()){
                    price += 0.50;
                }if (boxToppingsTwo.isSelected()){
                    price += 0.50;
                }
                if (boxToppingsThree.isSelected()){
                    price += 0.50;
                }if (boxToppingsFour.isSelected()){
                    price += 0.50;
            }
            costLabel.setText(Double.toString(price));
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

@Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        double price = 0;

    }
}

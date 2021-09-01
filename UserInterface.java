import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
* A graphical user interface for the calculator. No calculation is being
* done here. This class is responsible just for putting up the display on
* screen. It then refers to the "CalcEngine" to do all the real work.
* 
* @author David J. Barnes and Michael Kolling
* @version 2008.03.30
*/
public class UserInterface
    implements ActionListener
{
    private CalcEngine calc;
    private boolean showingAuthor;
    
    private JFrame frame;
    private JTextField display;
    private JLabel status;
    JCheckBox hexBox;
    
    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine engine)
    {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }
    
    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }
    
    /**
     * Make the frame for the user interface.
     */
    private void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
        
        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(6, 4));
        
        addButton(buttonPanel, "e");
        addButton(buttonPanel, "f");
        buttonPanel.add(new JLabel(" "));
        hexBox = new JCheckBox("Hex");
        buttonPanel.add(hexBox);
        hexBox.addActionListener(this);

        addButton(buttonPanel, "a");
        addButton(buttonPanel, "b");
        addButton(buttonPanel, "c");
        addButton(buttonPanel, "d");

        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "C");

        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");
        addButton(buttonPanel, "?");

        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");
        addButton(buttonPanel, "*");

        addButton(buttonPanel, "0");
        addButton(buttonPanel, "+");
        addButton(buttonPanel, "-");
        addButton(buttonPanel, "=");
        
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        
        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);
        
        frame.pack();
    }
    
    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }
    
    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();
        
        if(command.equals("Hex")) {
           calc.toggleHex();
        
        }
        if(command.equals("0") ||
           command.equals("1") ||
           command.equals("2") ||
           command.equals("3") ||
           command.equals("4") ||
           command.equals("5") ||
           command.equals("6") ||
           command.equals("7") ||
           command.equals("8") ||
           command.equals("9")) {
            int number = Integer.parseInt(command);
            calc.numberPressed(number);
        }
        if(command.equals("a")){
            calc.numberPressed(10);
        }
        if(command.equals("b")){
            calc.numberPressed(11);
        }
        if(command.equals("c")){
            calc.numberPressed(12);
        }
        if(command.equals("d")){
            calc.numberPressed(13);
        }
        if(command.equals("e")){
            calc.numberPressed(14);
        }
        if(command.equals("f")){
            calc.numberPressed(15);
        }
        else if(command.equals("+")) {
            calc.plus();
        }
        else if(command.equals("-")) {
            calc.minus();
        }
        else if(command.equals("*")) {
            calc.multiply();
        }
        else if(command.equals("=")) {
            calc.equals();
        }
        else if(command.equals("C")) {
            calc.clear();
        }
        
        else if(command.equals("?")) {
            showInfo();
        }
        // else unknown command.
        
        redisplay();
    }
    
    /**
     * Update the interface display to show the current value of the
     * calculator.
     */
    private void redisplay()
    {
        display.setText("" + calc.getDisplayValue());
    }
    
    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    private void showInfo()
    {
        if(showingAuthor)
            status.setText(calc.getVersion());
        else
            status.setText(calc.getAuthor());
        
        showingAuthor = !showingAuthor;
    }
}

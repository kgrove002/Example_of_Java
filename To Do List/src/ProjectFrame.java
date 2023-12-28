import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ProjectFrame extends JFrame implements ActionListener {

    JButton toDoListBtn;
    JButton shoppingListBtn;
    JButton manageDaysBtn;
    JPanel contentPanel;
    JPanel toDoPanel = ToDoList.ToDoPanel();
    JPanel shoppingPanel = ShoppingList.ShoppingPanel();

    ProjectFrame(){

        JPanel optionPanel = new JPanel();
        optionPanel.setBounds(0,0,750,100);
        optionPanel.setLayout(new GridLayout(1,1));

        toDoListBtn = new JButton();
        shoppingListBtn = new JButton();
        manageDaysBtn = new JButton();

        toDoListBtn.addActionListener(this);
        shoppingListBtn.addActionListener(this);
        manageDaysBtn.addActionListener(this);

        toDoListBtn.setBounds(0,0,50,50);
        shoppingListBtn.setBounds(50,0,50,50);
        manageDaysBtn.setBounds(100,0,50,50);

        toDoListBtn.setText("To Do List");
        shoppingListBtn.setText("Shopping List");
        manageDaysBtn.setText("Manage Days");

        optionPanel.add(toDoListBtn);
        optionPanel.add(shoppingListBtn);
        optionPanel.add(manageDaysBtn);

        contentPanel = new JPanel();
        contentPanel.setBounds(0,100,750,750);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(toDoPanel);

        ImageIcon image = new ImageIcon("src\\checkmark.png");
        this.setIconImage(image.getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("To Do List");
        this.setSize(750,750);
        this.setVisible(true);
        this.add(optionPanel);
        this.add(contentPanel);
        this.setLayout(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==toDoListBtn) {
            contentPanel.removeAll();
            ToDoList.loadTasks();
            contentPanel.add(toDoPanel);
            contentPanel.repaint();
            contentPanel.revalidate();
        } else if (e.getSource()==shoppingListBtn){
            contentPanel.removeAll();
            contentPanel.add(shoppingPanel);
            contentPanel.repaint();
            contentPanel.revalidate();
        } else if (e.getSource()==manageDaysBtn){
            contentPanel.removeAll();
            try {
                contentPanel.add(Settings.SettingsPanel());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            contentPanel.repaint();
            contentPanel.revalidate();
        }

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Settings extends JFrame implements ActionListener {

    static JButton sunday;
    static JButton monday;
    static JButton tuesday;
    static JButton wednesday;
    static JButton thursday;
    static JButton friday;
    static JButton saturday;

    static JPanel taskPanel;
    static JPanel SettingsPanel() throws IOException {
        JPanel settingsPanel = new JPanel();

        JPanel dayPanel = new JPanel();
        dayPanel.setBounds(0,0,750,75);
        dayPanel.setLayout(new GridLayout(1,1));

        sunday = new JButton();
        monday = new JButton();
        tuesday = new JButton();
        wednesday = new JButton();
        thursday = new JButton();
        friday = new JButton();
        saturday = new JButton();

        sunday.setBounds(0,0,50,100);
        monday.setBounds(0,0,50,100);
        tuesday.setBounds(0,0,50,100);
        wednesday.setBounds(0,0,50,100);
        thursday.setBounds(0,0,50,100);
        friday.setBounds(0,0,50,100);
        saturday.setBounds(0,0,50,100);

        sunday.setText("Sunday");
        monday.setText("Monday");
        tuesday.setText("Tuesday");
        wednesday.setText("Wednesday");
        thursday.setText("Thursday");
        friday.setText("Friday");
        saturday.setText("Saturday");

        taskPanel = new JPanel();
        taskPanel.add(GetSettings.dayTasks(1));

        sunday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(1));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
                }
            });
        monday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(2));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
            }
        });
        tuesday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(3));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
            }
        });
        wednesday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(4));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
            }
        });
        thursday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(5));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
            }
        });
        friday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(6));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
            }
        });
        saturday.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.removeAll();
                try {
                    taskPanel.add(GetSettings.dayTasks(7));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                taskPanel.repaint();
                taskPanel.revalidate();
            }
        });

        dayPanel.add(sunday);
        dayPanel.add(monday);
        dayPanel.add(tuesday);
        dayPanel.add(wednesday);
        dayPanel.add(thursday);
        dayPanel.add(friday);
        dayPanel.add(saturday);

        settingsPanel.add(dayPanel);
        settingsPanel.add(taskPanel);
        settingsPanel.setBounds(0,0,750,750);

        return settingsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


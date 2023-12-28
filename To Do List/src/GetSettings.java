import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GetSettings extends JFrame {

    static JLabel dayLabel;
    static  JPanel addPanel;
    static JPanel dayTasks;
    static String contents;

    static JSONObject o;
    static JSONArray tasks;
    static JSONArray isChecked;
    static String data;

    static JPanel dayTasks(int day) throws IOException {

        data = GetData.getDayData(day);
        getJSON();


        dayTasks = new JPanel();
        addPanel = new JPanel();
        JTextField taskField = new JTextField();
        taskField.setPreferredSize(new Dimension(250, 40));

        dayLabel = new JLabel();
        dayLabel.setText(GetData.dayText + "'s Tasks");
        dayLabel.setFont(new Font("MV Boli", Font.PLAIN, 32));
        dayLabel.setBounds(0,0,250,40);


        JButton addButton = new JButton();
        addButton.setText("Add");
        addButton.setBackground(Color.green);
        addButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (taskField.getText().matches(".*[a-zA-Z]+.*") == false){}
                else {
                    tasks.put(taskField.getText().trim());
                    isChecked.put(false);
                    taskField.setText("");
                    try {
                        updateJSON();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dayTasks.removeAll();
                    setPanel();
                    dayTasks.revalidate();
                    dayTasks.repaint();
                }
                }
        }));

        addPanel.add(taskField);
        addPanel.add(addButton);
        addPanel.setLayout(new GridLayout(1, 2));


        setPanel();

        dayTasks.setBounds(50,100,750,400);
        dayTasks.setLayout(new GridLayout(10,2,0,10));

        return dayTasks;
    }

    static void setPanel() {
        dayTasks.add(dayLabel);
        dayTasks.add(addPanel);
        if (tasks.isEmpty() == false){
            for (int i = 0; i < tasks.length(); i++) {

              String task =(String) tasks.get(i);

                JPanel taskPanel = new JPanel();
                taskPanel.setBounds(0,0,750,100);
                taskPanel.setLayout(new GridLayout(1,2,0,0));

                JLabel newLabel = new JLabel();
                newLabel.setText(task);
                newLabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
                newLabel.setOpaque(true);
                newLabel.setPreferredSize(new Dimension(250,35));
                taskPanel.add(newLabel);

                JButton deleteBTN = new JButton();
                deleteBTN.setBackground(Color.red);
                deleteBTN.setText("Delete");
                int finalI = i;
                deleteBTN.addActionListener((new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        tasks.remove(finalI);
                        isChecked.remove(finalI);
                        try {
                            updateJSON();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        dayTasks.removeAll();
                        setPanel();
                        dayTasks.revalidate();
                        dayTasks.repaint();
                    }
                }));
                taskPanel.add(deleteBTN);
                dayTasks.add(taskPanel);
            }
        } else {
            JLabel noTasks = new JLabel();
            noTasks.setText("You have no tasks for this day!");
            noTasks.setFont(new Font("MV Boli", Font.PLAIN, 18));
            noTasks.setOpaque(true);
            dayTasks.add(noTasks);
        }
    }

    private static void updateJSON() throws IOException {
        FileWriter file = new FileWriter(data);
        JSONObject newJSON = new JSONObject();
        newJSON.put("tasks",tasks);
        newJSON.put("isChecked",isChecked);
        file.write(String.valueOf(newJSON));
        file.close();
    }

    static void getJSON() throws  IOException {
        try {
            contents = new String((Files.readAllBytes(Paths.get(data))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        o = new JSONObject(contents);
        tasks = o.getJSONArray("tasks");
        isChecked = o.getJSONArray("isChecked");
    }
}

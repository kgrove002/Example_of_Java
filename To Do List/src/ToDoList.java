import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.metal.MetalProgressBarUI;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;


public class ToDoList extends JFrame {
    static String day = GetData.getDayData(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    static String contents;
    static JSONObject o;
    static JProgressBar bar;
    static int count;
    static int total;
    static int barPercent;
    static JPanel toDoList;
    static JSONArray tasks;
    static JSONArray isChecked;

    static JButton unSelectAllBTN;
    static JPanel ToDoPanel() {
        toDoList = new JPanel();
        toDoList.setLayout(new GridLayout(10, 1));
        toDoList.setBounds(125, 0, 750, 500);

        bar = new JProgressBar(0, 100);
        bar.setBounds(0, 0, 750, 150);
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli", Font.BOLD, 25));
        bar.setForeground(Color.red);
        bar.setBackground(Color.black);

        bar.setUI(ui);

        unSelectAllBTN = new JButton();
        unSelectAllBTN.setBackground(Color.red);
        unSelectAllBTN.setText("DeSelect All");
        unSelectAllBTN.setFont(new Font("MV Boli", Font.PLAIN, 18));

        loadTasks();
        return toDoList;
    }

    static void loadTasks() {
        toDoList.removeAll();
        toDoList.add(bar);
        count = 0;
        total = 0;

        try {
                contents = new String((Files.readAllBytes(Paths.get(day))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        JSONObject o = new JSONObject(contents);
        tasks = o.getJSONArray("tasks");
        isChecked = o.getJSONArray("isChecked");

        if (tasks.isEmpty() == false) {
            for (int i = 0; i < tasks.length(); i++) {

                String task = (String) tasks.get(i);
                boolean checked = (boolean) isChecked.get(i);

                JPanel taskPanel = new JPanel();
                taskPanel.setBounds(0, 0, 500, 750);

                JCheckBox checkBox = new JCheckBox();
                checkBox.setFocusable(false);
                checkBox.setText(task);
                checkBox.setSelected(checked);
                total = total + 1;
                checkBox.setFont(new Font("MV Boli", Font.PLAIN, 18));
                int finalI = i;
                checkBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            count = count + 1;
                            isChecked.put(finalI,true);
                            try {
                                updateJSON();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            count = count - 1;
                            isChecked.put(finalI,false);
                            try {
                                updateJSON();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        updateBar();
                    }
                });

                unSelectAllBTN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkBox.setSelected(false);
                        count = 0;
                        updateBar();
                    }
                });

                taskPanel.add(checkBox);

                toDoList.add(taskPanel);
                toDoList.add(unSelectAllBTN);


                if (checked == true){
                    count = count + 1;
                }

            }

        } else {
            JLabel noTasks = new JLabel();
            noTasks.setText("You have no tasks for this day!");
            noTasks.setFont(new Font("MV Boli", Font.PLAIN, 18));
            noTasks.setOpaque(true);
            toDoList.add(noTasks);
        }
        updateBar();
        toDoList.repaint();
        toDoList.revalidate();
    }
    static void updateBar(){
        barPercent = 100 * count / total;
        bar.setString("You are "+barPercent + "% done!");
        bar.setValue(barPercent);

        if (barPercent < 50){
            bar.setForeground(Color.red);
        } else if (barPercent >= 50 && barPercent < 100) {
            bar.setForeground(Color.yellow);
        } else {
            bar.setString("You are all done for today!");
            bar.setForeground(Color.green);
        }
    }

    private static void updateJSON() throws IOException {
        FileWriter file = new FileWriter(day);
        JSONObject newJSON = new JSONObject();
        newJSON.put("tasks",tasks);
        newJSON.put("isChecked",isChecked);
        file.write(String.valueOf(newJSON));
        file.close();
    }

    static ProgressBarUI ui = new MetalProgressBarUI(){
        protected Color getSelectionForeground(){
            return new Color(0,0,0	);
        }
        @Override
        protected Color getSelectionBackground(){
        return new Color(255,255,255);
    }
    };
}

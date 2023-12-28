import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
public class ShoppingList extends JFrame {
    static  JPanel addPanel;
    static JPanel shoppingList;
    static String day = "src\\data\\Shopping.json";
    static String contents;

    static {
        try {
            contents = new String((Files.readAllBytes(Paths.get(day))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static JSONObject o = new JSONObject(contents);
    static JSONArray items = o.getJSONArray("items");
    static JPanel ShoppingPanel(){
        shoppingList = new JPanel();

        addPanel = new JPanel();
        JTextField itemList = new JTextField();
        itemList.setPreferredSize(new Dimension(200, 40));

        JButton addButton = new JButton();
        addButton.setText("Add");
        addButton.setBackground(Color.green);
        addButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (itemList.getText().matches(".*[a-zA-Z]+.*") == false){}
                else {
                    items.put(itemList.getText().trim());
                    itemList.setText("");
                    try {
                        updateJSON();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    shoppingList.removeAll();
                    setPanel();
                    shoppingList.revalidate();
                    shoppingList.repaint();
                }
            }
        }));

        addPanel.add(itemList);
        addPanel.add(addButton);
        addPanel.setLayout(new GridLayout(1, 3));


        setPanel();
        shoppingList.setLayout(new GridLayout(20,2,10,10));

        return shoppingList;
    }

    static void setPanel() {
        shoppingList.add(addPanel);
        if (items.isEmpty() == false){
            for (int i = 0; i < items.length(); i++) {

                String task =(String) items.get(i);

                JPanel taskPanel = new JPanel();
                taskPanel.setLayout(new GridLayout(1,2,0,0));

                JLabel newLabel = new JLabel();
                newLabel.setText(task);
                newLabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
                newLabel.setOpaque(true);
                taskPanel.add(newLabel);

                JButton deleteBTN = new JButton();
                deleteBTN.setBackground(Color.red);
                deleteBTN.setText("Delete");
                int finalI = i;
                deleteBTN.addActionListener((new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        items.remove(finalI);
                        try {
                            updateJSON();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        shoppingList.removeAll();
                        setPanel();
                        shoppingList.revalidate();
                        shoppingList.repaint();
                    }
                }));
                taskPanel.add(deleteBTN);
                shoppingList.add(taskPanel);
            }
        } else {
            JLabel noitems = new JLabel();
            noitems.setText("You have bought all your items!");
            noitems.setFont(new Font("MV Boli", Font.PLAIN, 18));
            noitems.setOpaque(true);
            shoppingList.add(noitems);
        }
    }

    private static void updateJSON() throws IOException {
        FileWriter file = new FileWriter(day);
        file.write(String.valueOf(o));
        file.close();
    }
}
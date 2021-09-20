import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static JFrame frame;

    public static void main(String[] args) throws IOException {
        String currentPath = System.getProperty("user.dir");
        String inputPath = currentPath + File.separator + "input.txt";
        String outputPath = currentPath + File.separator + "output.txt";
        File inputFilePath = new File(inputPath);
        File outputFilePath = new File(outputPath);

        System.out.println("current path is: " + currentPath);
        System.out.println(inputFilePath);

        //LOGIC.TxtToArray(arrayList,"input.txt");
        //LOGIC.WordTranslator(arrayList);
        //LOGIC.ArrayToTxt(arrayList,"output.txt");

//===================TEXTAREA=====================
        JTextArea leftTextArea = new JTextArea();
        JTextArea rightTextArea = new JTextArea();
//===================SCROLLPANE===================
        JScrollPane leftScrollPane = new JScrollPane(leftTextArea);
        JScrollPane rightScrollPane = new JScrollPane(rightTextArea);

        leftScrollPane.setSize(300, 450);
        rightScrollPane.setSize(300, 450);

//====================BUTTON=====================
        MyButton button = new MyButton("Translate", 100, 50);

        MyButton button2 = new MyButton("Copy", 100, 50);

        MyButton button3 = new MyButton("Clear", 100, 50);

        button.addActionListener(e -> {
            rightTextArea.setText("");
            //Copy data from TextArea to List;
            List<String> arr = new ArrayList<>(Arrays.asList(leftTextArea.getText().split("\\n")));
            //Write data from List to Txt file
          /*  try {
                LOGIC.ListToTxt(arr,"input.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }*/
            //Translate audi numbers to acceptable for FIS format
            LOGIC.WordTranslator(arr);
            //Write data from List to Txt file
            /*try {
                LOGIC.ListToTxt(arr,"output.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }*/
            //Copy data from List to TextArea
            try {
                LOGIC.ListToTextArea(arr,rightTextArea);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        button2.addActionListener(e -> {
            LOGIC.CopyToClipboard(rightTextArea.getText());
        });

        button3.addActionListener(e -> {
            leftTextArea.setText("");
            rightTextArea.setText("");
        });


//====================PANELS=====================
        JPanel leftPanel = new JPanel();
        JPanel centralPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setBackground(Color.lightGray);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftPanel.setLayout(new GridLayout());

        centralPanel.setBackground(Color.lightGray);
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centralPanel.setMaximumSize(new Dimension(600, 200));

        rightPanel.setBackground(Color.lightGray);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightPanel.setLayout(new GridLayout());

        leftPanel.add(leftScrollPane);
        rightPanel.add(rightScrollPane);
        centralPanel.add(button);
        centralPanel.add(button2);
        centralPanel.add(button3);
//=========================FRAME========================
        frame = new JFrame("Translator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3, 1));
        frame.add(leftPanel);
        frame.add(centralPanel);
        frame.add(rightPanel);
        frame.validate();
        frame.repaint();

    }
}

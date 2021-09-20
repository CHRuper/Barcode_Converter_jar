import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LOGIC {

    static void TxtToList(ArrayList<String> arrayList, String textFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                arrayList.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void WordTranslator(List<String> list) throws StringIndexOutOfBoundsException {
        int listLength = list.size();

        System.out.println("INPUT:");
        for (int i = 0; i < listLength; i++) {
            if (list.get(i).equals("")) {
                list.remove(list.get(i));
                i=i-1;
                listLength = listLength-1;
            }
            System.out.println( list.get(i));
        }

        if (list.isEmpty()) {
            list.add("lista jest pusta");
        } else {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i).replace(" ", "");

                if (str.charAt(11) != '#') {
                    String subStr = (String.valueOf(str.charAt(11)) + str.charAt(12)) + "#HX01S#*Knn";
                    str = str.replace(subStr,"A");
                }else{
                    str = str.replace("#HX01S#*Knn", "_");
                }

                str = str.replace("*=","");
                list.set(i,str);
            }
        }
        System.out.println("OUTPUT:");
        for(String out: list) {
            System.out.println(out);
        }
    }

    static void ListToTxt(List<String> list, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for(String str: list) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    static void ListToTextArea(List<String> arrayList, JTextArea textArea) throws IOException {
        for (String str : arrayList) {
            textArea.append(str + System.lineSeparator());
        }
    }

    static void CopyToClipboard(String str) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(str);
        clipboard.setContents(strSel, null);
    }
}
package util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperations {
    private static NotepadMainFrame mainFrame;

    public FileOperations(NotepadMainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }


    public static void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(mainFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // ʹ��UTF-8�����ȡ�ļ�
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
                mainFrame.getTextArea().setText(content);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainFrame, "�ļ����ܱ���", "����", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        // �����ļ���������ֻ��ʾ�ͱ���.txt�ļ�
        FileNameExtensionFilter filter = new FileNameExtensionFilter("�ı��ļ� (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        // ��ѡ��������ѡ�������ļ�����
        fileChooser.setAcceptAllFileFilterUsed(false);

        int option = fileChooser.showSaveDialog(mainFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // ȷ���ļ���׺Ϊ.txt
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getParentFile(), file.getName() + ".txt");
            }
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(mainFrame.getTextArea().getText());
                fileWriter.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainFrame, "�ļ����ܱ�����", "����", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //�����ֱ���½�һ���ļ���û�б���ģʽ
    public static void newFile() {
        mainFrame.getTextArea().setText("");
    }

}

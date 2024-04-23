package util;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NotepadMainFrame extends JFrame {
    private JTextArea textArea;//�ı��༭����
    private FileOperations fileOperations;//�ļ�����
    private Clipboard clipboard;//���а�
    private StatusBar statusBar;//״̬��

    //���캯��
    public NotepadMainFrame() {

        textArea = new JTextArea();
        //��������
        Font textAreaFont = new Font("Monospaced", Font.PLAIN, 20);
        textArea.setFont(textAreaFont);
        // �����Զ�����
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        fileOperations = new FileOperations(this);
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();


        statusBar = new StatusBar();
        add(statusBar.createStatusBar(), BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(new JScrollPane(textArea));
        this.setVisible(true);

        // �����ֺ�
        JMenuBar menuBar = new JMenuBar();
        Font menuFont = new Font("SansSerif", Font.BOLD, 20);

        // �ļ�����
        JMenu fileMenu = new JMenu("�ļ�");
        fileMenu.setFont(menuFont); // ��������
        JMenuItem newMenuItem = new JMenuItem("�½�");
        JMenuItem openMenuItem = new JMenuItem("��");
        JMenuItem saveMenuItem = new JMenuItem("����");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);

      //�ļ��������¼�����
       openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileOperations.openFile();
            }
        });


        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileOperations.newFile();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileOperations.saveFile();
            }
        });


        // �༭����
        JMenu editMenu = new JMenu("�༭");
        editMenu.setFont(menuFont); // ��������
        JMenuItem cutMenuItem = new JMenuItem("����");
        JMenuItem copyMenuItem = new JMenuItem("����");
        JMenuItem pasteMenuItem = new JMenuItem("ճ��");
        JMenuItem searchMenuItem = new JMenuItem("����");
        JMenuItem replaceMenuItem = new JMenuItem("�滻");

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.addSeparator();
        editMenu.add(searchMenuItem);
        editMenu.add(replaceMenuItem);


        //�༭�¼��ļ���
        cutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        copyMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        searchMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FindAndReplaceDialog findDialog = new FindAndReplaceDialog(NotepadMainFrame.this, textArea);
                findDialog.setVisible(true);
            }
        });

        replaceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FindAndReplaceDialog replaceDialog = new FindAndReplaceDialog(NotepadMainFrame.this, textArea);
                replaceDialog.setVisible(true);
            }
        });


        // �������ڲ˵�
        JMenu aboutMenu = new JMenu("����");
        aboutMenu.setFont(menuFont); // ��������
        menuBar.add(aboutMenu);

        // ����Readme�˵���
        JMenuItem readMenuItem = new JMenuItem("Readme");
        aboutMenu.add(readMenuItem);

        // ΪReadme�˵�������¼�������
        readMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(NotepadMainFrame.this,
                        "<html><span style='font-size:18px;'>����һ����Ϊjava��ҵ���ұ�����Ƶ�<br>" +
                                "��windows���±���ϣ������ϲ��^.^<br>" +
                                "���������ֻ������������ı༭����<br><br>" +
                                "<p style='text-align:right;'>���� design by Wy</p></span></html>",
                        "Readme", // �Ի������
                        JOptionPane.PLAIN_MESSAGE); // �Ի������ͣ�����ʾͼ��
            }
        });



        //�˵���UI����
        UIManager.put("Menu.font", menuFont);
        UIManager.put("MenuItem.font", menuFont);

        //��ӵ��˵���
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);


        // ���ò˵�
        setJMenuBar(menuBar);

        // ״̬��
        statusBar = new StatusBar();
        getContentPane().add(statusBar.createStatusBar(), BorderLayout.SOUTH);
        // ״̬���¼�����
        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int pos = e.getDot();
                try {
                    int row = textArea.getLineOfOffset(pos) + 1;
                    int col = pos - textArea.getLineStartOffset(row - 1) + 1;
                    statusBar.updatePosition(row, col);
                } catch (Exception ex) {
                    statusBar.updatePosition(0, 0);
                }
            }
        });


        // ��������
        setTitle("Notepad");
        setSize(700, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ... �����÷� ...
    public JTextArea getTextArea() {
        return textArea;
    }

    public FileOperations getFileOperations() {
        return fileOperations;
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    // ������
    public static void main(String[] args) {

        //������windows��UI����
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotepadMainFrame();
            }
        });



    }


}









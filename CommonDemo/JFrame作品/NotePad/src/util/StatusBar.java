package util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBar {
    private JLabel positionLabel;
    private JLabel encodingLabel;

    public StatusBar() {
        positionLabel = new JLabel();
        encodingLabel = new JLabel("enCoding��Utf-8");
    }

    public JToolBar createStatusBar() {
        JToolBar statusBar = new JToolBar();
        statusBar.setFloatable(false);
        statusBar.setBorder(new EmptyBorder(5, 5, 5, 5)); // ����״̬���߿�
        statusBar.setLayout(new BorderLayout()); // ���ò��ֹ�����ΪBorderLayout

        // ���ñ�ǩ����ͱ߿�
        Font labelFont = new Font("SansSerif", Font.PLAIN, 12); // ѡ����ʵ�����ʹ�С
        positionLabel.setFont(labelFont);
        positionLabel.setBorder(new EmptyBorder(0, 0, 0, 10)); // ����λ�ñ�ǩ�ұ߾�
        encodingLabel.setFont(labelFont);
        encodingLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // ���ñ����ǩ��߾�

        // ��ӱ�ǩ��״̬��
        statusBar.add(positionLabel, BorderLayout.WEST);
        statusBar.add(encodingLabel, BorderLayout.EAST);

        return statusBar;
    }

    public void updatePosition(int line, int column) {
        positionLabel.setText("��ǰ��: " + line +"    "+"��ǰ��: " + column);
    }
}

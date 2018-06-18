package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Armando Almengor 8-923-936
 */
public class PuzzleGame implements ActionListener {
    //Field

    int tb = 60;
    int x, direx, direy, tmpdx, tmpdy, point;
    int toques = 0;
    JButton boton, btn_iniciar, btn_iniciar2, jbtmp;
    JTextField tf_temporizador;
    ArrayList<JButton> ListSync = new ArrayList<>();
    JLabel lbl_name, lbl_wall, lbl_tempo;
    JTextArea ta_winners;
    String nombre;
    JFrame ventana;
    int[] clone = new int[16];
    int points = 0;
    int mili = 0;
    private DefaultListModel<String> listModel;
    private JList<String> lst_lista;
    private JScrollPane listScroll;

//Constructor
    PuzzleGame() {
        frame();

    }
    // Main

    public static void main(String[] args) {
        new PuzzleGame();
    }
    // Methods  
    Timer cron = new Timer(1, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mili++;
            tf_temporizador.setText(String.valueOf(mili / 1000));
        }
    });
    Timer timer = new Timer(1, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            points = 0;
            ventana.setEnabled(false);
            ListSync.get(point).setLocation(ListSync.get(point).getLocation().x + direx, ListSync.get(point).getLocation().y + direy);

            if (ListSync.get(point).getLocation().x == ListSync.get(x).getLocation().x && ListSync.get(point).getLocation().y == ListSync.get(x).getLocation().y) {
                ventana.setEnabled(true);
                ListSync.get(x).setLocation(tmpdx, tmpdy);

                Collections.swap(ListSync, point, x);
                for (int i = 0; i < 15; i++) {
                    System.out.print(ListSync.get(i).getText() + " ");

                    if (Integer.parseInt(ListSync.get(i).getText()) < Integer.parseInt(ListSync.get(i + 1).getText()) && ListSync.get(i + 1) != null) {
                        points++;
                        if (points == 15) {
                            finished();
                        }
                    }
                    x = ListSync.indexOf(ListSync.get(point));
                }
                timer.stop();
            }
        }
    });

    public void frame() {

        //Ventana
        ventana = new JFrame("Puzzle Game");
        ventana.setVisible(true);
        ventana.setLayout(null);
        ventana.setBounds(100, 100, 600, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Labels
        lbl_name = new JLabel("Armando Almengor \n" + "8-923-936");
        lbl_name.setBounds(200, 530, 250, 40);
        lbl_name.setFont(new java.awt.Font("Tw Cen MT", 3, 14));
        ventana.add(lbl_name);

        lbl_wall = new JLabel("Top PuzzleMakers");
        lbl_wall.setBounds(415, 100, 150, 40);
        lbl_wall.setFont(new java.awt.Font("Tw Cen MT", 3, 18));
        ventana.add(lbl_wall);
        
        lbl_tempo = new JLabel("Tiempo");
        lbl_tempo.setBounds(450,370,100,40);
        lbl_tempo.setFont(new java.awt.Font("Tw Cen MT", 3, 16));
        ventana.add(lbl_tempo);
                
        //TextFields
        tf_temporizador = new JTextField();
        tf_temporizador.setBounds(500,380, 35, 22);
        tf_temporizador.setBackground(new java.awt.Color(66,93,166));
        tf_temporizador.setForeground(new java.awt.Color(255, 255, 255));
        tf_temporizador.setFont(new java.awt.Font("Tw Cen Mt Condensed", 1, 16));
        ventana.add(tf_temporizador);

        //Buttons
        btn_iniciar = new JButton("Iniciar");
        btn_iniciar.setBounds(55, 340, 75, 35);
        btn_iniciar.setFont(new java.awt.Font("Tw Cen Mt Condensed", 1, 16));
        btn_iniciar.setBackground(new java.awt.Color(66,93,166));
        btn_iniciar.setForeground(new java.awt.Color(255, 255, 255));
        btn_iniciar.addActionListener(this);
        ventana.add(btn_iniciar);

        btn_iniciar2 = new JButton("Iniciado Especial");
        btn_iniciar2.setBounds(55, 380, 130, 35);
        btn_iniciar2.setFont(new java.awt.Font("Tw Cen Mt Condensed", 1, 16));
        btn_iniciar2.setBackground(new java.awt.Color(66,93,166));
        btn_iniciar2.setForeground(new java.awt.Color(255, 255, 255));
        btn_iniciar2.addActionListener(this);
        ventana.add(btn_iniciar2);

        //ListModel
        listModel = new DefaultListModel<String>();
        lst_lista = new JList<String>(listModel);
        listScroll = new JScrollPane(lst_lista);
        listScroll.setBounds(380, 150, 200, 200);
        listScroll.setFont(new java.awt.Font("Tw Cen Mt Condensed",1,13));
        listScroll.setBackground(new java.awt.Color(66,93,166));
        listScroll.setForeground(new java.awt.Color(255, 255, 255));
        ventana.add(listScroll);

        for (x = 0; x <= 15; x++) {
            boton = new JButton(String.valueOf(x + 1));
            boton.setBounds(0 + tb + tb * (x % 4), 50 + tb * (x / 4), tb, tb);
            boton.addActionListener(this);
            boton.setVisible(true);
            boton.setFont(new java.awt.Font("Tw Cen Mt Condensed", 1, 18));
            boton.setBackground(new java.awt.Color(66,93,166));
            boton.setForeground(new java.awt.Color(255, 255, 255));
            ventana.add(boton);
            ListSync.add(boton);
        }
        ListSync.get(15).setVisible(false);
        ventana.repaint();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn_iniciar2) {
            btn_iniciar_2();
        }

        if (e.getSource() == btn_iniciar) {
            ingresnombre();
            if (nombre != null && !nombre.isEmpty()) {
                cron.start();
                disorder();
            }
        }

        if (e.getSource() != btn_iniciar) {
            toques++;
            direx = 0;
            direy = 0;

            jbtmp = (JButton) e.getSource();
            tmpdx = jbtmp.getLocation().x;
            tmpdy = jbtmp.getLocation().y;
            point = ListSync.indexOf(e.getSource());

            System.out.println(jbtmp.getText());

            if (ListSync.get(point).getLocation().x - tb == ListSync.get(x).getLocation().x && ListSync.get(point).getLocation().y == ListSync.get(x).getLocation().y) {
                direx--;
                timer.start();
            }

            if (ListSync.get(point).getLocation().x + tb == ListSync.get(x).getLocation().x && ListSync.get(point).getLocation().y == ListSync.get(x).getLocation().y) {
                direx++;
                timer.start();

            }

            if (ListSync.get(point).getLocation().x == ListSync.get(x).getLocation().x && ListSync.get(point).getLocation().y - tb == ListSync.get(x).getLocation().y) {
                direy--;
                timer.start();
            }

            if (ListSync.get(point).getLocation().x == ListSync.get(x).getLocation().x && ListSync.get(point).getLocation().y + tb == ListSync.get(x).getLocation().y) {
                direy++;
                timer.start();
            }
        }
    }

    public void ingresnombre() {
        cron.stop();
        nombre = JOptionPane.showInputDialog(ventana, "Ingresa tu nombre de pila:");
        mili = 0;
    }

    public void record() {
        FileWriter fw;
        try {
            fw = new FileWriter("PuzzleGamers.txt", true); //true=append, false=overwrite
            fw.write("\n" + nombre + ": ");
            fw.write(String.valueOf(mili / 1000) + " segundos");
            fw.close();
        } catch (Exception e) {
            System.out.println("Opps, error al grabar " + e.toString());
        }
    }

    public void read() {
        File f = new File("PuzzleGamers.txt");
        Scanner sc;
        try {
            listModel.clear();
            sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();

                listModel.addElement(s);
            }
        } catch (Exception e) {
            System.out.println("Opps, error al leer " + e.toString());
        }

    }

    public void disorder() {
        for (x = 0; x <= 15; x++) {
            ventana.remove(ListSync.get(x));
        }
        ventana.repaint();
        Collections.shuffle(ListSync, new Random(System.currentTimeMillis()));
        for (x = 0; x <= 15; x++) {
            ListSync.get(x).setLocation(0 + tb + tb * (x % 4), 50 + tb * (x / 4));
            clone[x] = Integer.parseInt(ListSync.get(x).getText());
            ventana.add(ListSync.get(x));
        }
        for (int i = 0; i < 16; i++) {
            if (clone[i] == 16) {
                x = i;
            }
        }
        ListSync.get(x).setVisible(false);
    }

    public void finished() {

        record();
        read();
        ventana.setEnabled(false);
        JOptionPane jOptionPane = new JOptionPane();
        jOptionPane.showMessageDialog(null, "¡Enhorabuena! Has Ganado. \n" + "Total de Movimientos: " + String.valueOf(toques) + "\nTiempo Transcurrido: " + String.valueOf(mili / 1000) + " segundos.");

        int end = JOptionPane.showConfirmDialog(null, "¿Deseas iniciar otra partida?", "¡Gracias por jugar!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (end == 0) {
            ventana.setVisible(false);
            new PuzzleGame();
        }

        if (end == 1) {
            ventana.dispose();
        }
    }

    private void btn_iniciar_2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

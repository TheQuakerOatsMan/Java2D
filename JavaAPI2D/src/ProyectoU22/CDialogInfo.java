package ProyectoU22;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CDialogInfo extends JDialog {

    JPanel botones, contenedor;
    JButton atras, siguiente, volver;
    JTextPane texto;
    JLabel titulo;
    int index = 0;

    String info[] = {
            "El programa presentado maneja el desarrollo de una figura aplicando la APIJava2D, como de algunas de sus funciones añadidas, "
            + "		el programa cuenta con una figura de más de 50 vertices generada con GeneraltPath. \n Se presentan opciones como trasladar, rotar sobre un eje, deformar, escalar, reflejar a " +
                    "trav\u00E9s del uso de AffineTransform, como acciones r\u00E1pidas donde se ejecutan esas acciones con un valor determinado, " +
                    "eventos de rat\u00F3n, etc. \nNOTAS:\n"
                    + "\u2023La secci\u00F3n de MENU ubicada en la parte superior, tiene la secci\u00F3n de OPCIONES MENU con las tranformaciones, "
                    + "la secci\u00F3n API donde se ven algunas aplicaciones donde se usan elementos de APIJava2D como aplicacion de relleno, texto, contorno, etc. Y por ultimo, la secci\u00F3n de EXTRAS cuenta con opciones de restaurar la figura, informaci\u00F3n o salir.\n"
                    +"\nLa BARRA DE HERRAMIENTAS ubicada en la parte derecha cuenta con transformaciones con valores predeterminados como algunas funciones rapidas del APIJava2D. \n",
            "TECLAS ACELERADORAS: (SON CON AFFINE TRANSFORM)\n\n\u2023ALT + R :Ejecuta la rotaci\u00F3n en el sentido de las manecillas del Reloj " +
                    "mediante un cuadro de di\u00E1logo.\n\u2023ALT + L :Ejecuta la rotaci\u00F3n en contra de las manecillas del " +
                    "Reloj mediante un cuadro de di\u00E1logo. \n\u2023ALT + E :Escala la figura a partir de un punto dado. \n"
                    + "\u2023ALT + M :Traslada la figura hasta el punto indicado mediante un cuadro de di\u00E1ogo. \n"
                    + "\u2023ALT + F :Refleja la figura al eje de las X, Y o X,Y. \n"
                    + "\u2023ALT + D :Deforma la figura a partir de un punto. \n",
            "\u2023CTRL + I :Ejecuta la rotaci\u00F3n en el sentido de las manecillas del Reloj n cantidad de grados. \n"
                    + "\u2023CTRL + D :Ejecuta la rotaci\u00F3n en contra de las manecillas del Reloj n cantidad de grados. \n"
                    + "\u2023CTRL + M :Hace un aumento de 1.01 a partir de un valor de escalamiento. \n"
                    + "\u2023CTRL + N :Hace una disminuci\u00F3n de .99 a partir de un valor de escalamiento. \n"
                    + "\u2023CTRL + R :Refleja la figura en el eje de las XY. \n"
                    + "\u2023ALT + R :Restaura la figura a sus valores iniciales.\n"
                    + "\u2023F1 : Muestra la Ayuda. \n"
                    + "\u2023ALT + Q :Salir del programa. \n"
                    +"\u2023 ACLARACION--> Si no tiene un stoke se aplica uno por defecto y color.\n"
                    + "      (No hay problema si no recuerdas todas, estar\u00E1n vistas en cada opci\u00F3n) \n",

            "ATAJOS SOBRE EL API 2D:\n"
            + "\u2023ALT + B : Crea una linea contornada a partir de diersos estilos de BasciStroke, como su color o quitar el Stroke\n"
            + "\u2023ALT + P : Genera relleno con linearGradient a patir de los colores basicos.\n"
            + "\u2023ALT + G : Rellena la figura con una Textura a partir de una imagen, usando TexturePaint.\n"
            + "\u2023ALT + C : Nodifica el color de la figura con un color basico o usando combinaciones RGB.\n"
            + "(EN LA MAYORIA SE VE UN RECUADRO DONDE MUESTRA LA SIMULACION DE LOS QUE SE HARA CON LA FIGURA PRINCIPAL) \n"
            + "\nPor \u00FAltimo, cada figura presenta interaciones del rat\u00F3n como aumentar o disminuir su " +
                    "tama\u00F1o por la rueda del rat\u00F3n.\nO bien, rotar la figura en un sentido haciendo dos clic's a un " +
                    "lado, incluso arrastar la figura. \nDiviertete y explora el programa. \n"
    };
    URL ruta;

    public CDialogInfo(Interfaz ref, boolean modal) {

        super(ref.Screen, modal);
        setSize(640, 480);
        setLocationRelativeTo(this);
        setUndecorated(true);

        botones = new JPanel(new GridLayout(1, 3));
        atras = new JButton(new ImageIcon(getClass().getResource("/ProyectoU22/rec/prev.png")));
        atras.setEnabled(false);
        atras.setBackground(Color.LIGHT_GRAY);
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atras.setEnabled(true);
                siguiente.setEnabled(true);
                if (index > 0) index --;
                if (index == 0) atras.setEnabled(false);
                texto.setText(info[index]);
            }
        });
        siguiente = new JButton(new ImageIcon(getClass().getResource("/ProyectoU22/rec/siguiente.png")));
        siguiente.setBackground(Color.LIGHT_GRAY);
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguiente.setEnabled(true);
                atras.setEnabled(true);
                if (index < 3) index ++;
                if (index == 3) siguiente.setEnabled(false);
                texto.setText(info[index]);
            }
        });
        volver = new JButton(new ImageIcon(getClass().getResource("/ProyectoU22/rec/home.png")));
        volver.setBackground(Color.LIGHT_GRAY);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(sa, .4f);
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);

        botones.add(atras);
        botones.add(volver);
        botones.add(siguiente);

        getContentPane().add(botones, BorderLayout.SOUTH);

        titulo = new JLabel("SECCION DE AYUDA (F1)");
        titulo.setIcon(new ImageIcon(getClass().getResource("/ProyectoU22/rec/info.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        getContentPane().add(titulo, BorderLayout.NORTH);

        texto = new JTextPane();
        texto.getStyledDocument().setParagraphAttributes(0, info.length, sa, false);
        texto.setText(info[index]);
        texto.setEnabled(false);
        texto.setOpaque(false);
        texto.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        texto.setForeground(Color.BLACK);
        texto.setBounds(20, 20, 600, 400);

        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.add(texto);
        getContentPane().add(contenedor, BorderLayout.CENTER);


    }

    public void Mostrar() {
        setVisible(true);
    }

}

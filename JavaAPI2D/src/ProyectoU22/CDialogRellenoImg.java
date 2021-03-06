package ProyectoU22;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.net.URL; 
public class CDialogRellenoImg extends JDialog implements ActionListener{
	JButton ba,bc;
	JPanel initial;
	int tipoimg,altura,anchura,cx,cy;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
	JSlider SLong, SAnch;
	TexturePaint textura,textP;
	ButtonGroup grupo;
	JRadioButton rb1,rb2,rb3;
	URL  rutaImg;

	public CDialogRellenoImg(Interfaz ref,boolean modal) {
		super(ref.Screen,modal);
		setSize(800,400);
		setTitle("TexturePaint");
		setResizable(false);
		setLayout(null);
		tipoimg=1;
		lbl1 = new JLabel ("Figura rellena");
		Font Fuente = new Font("Lucida Sans", Font.BOLD, 20);
		lbl1.setFont(Fuente);
		lbl2 = new JLabel ("Selecciona una imagen de relleno:");
		URL ruta = getClass().getResource("/ProyectoU22/rec/fon4.png");
		lbl3=new JLabel(new ImageIcon(ruta));
		ruta = getClass().getResource("/ProyectoU22/rec/fon2.png");
		lbl4=new JLabel(new ImageIcon(ruta));
		ruta = getClass().getResource("/ProyectoU22/rec/fon3.png");
		lbl5=new JLabel(new ImageIcon(ruta));
		
		SLong = new JSlider (JSlider.HORIZONTAL,5,100,50);
		SLong.setBounds(160, 240, 300, 40);
		SLong.setMajorTickSpacing(10);
		SLong.setMinorTickSpacing(5);
		SLong.setPaintTicks(true);
		SLong.setPaintLabels(true);
		SAnch = new JSlider (JSlider.HORIZONTAL,5,100,50);
		SAnch.setBounds(160, 280, 300, 40);
		SAnch.setMajorTickSpacing(10);
		SAnch.setMinorTickSpacing(5);
		SAnch.setPaintTicks(true);
		SAnch.setPaintLabels(true);
		
		anchura =50;
		altura=50;
		cx=0;
		cy=0;
		textura=null;
		rutaImg = null;
		textP = null;
		
		initial = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				//vemos el tipo
				if (tipoimg==1) {
					rutaImg = getClass().getResource("/ProyectoU22/rec/fon4g.png");
				}else if (tipoimg==2) {
					rutaImg = getClass().getResource("/ProyectoU22/rec/fon2g.png");
				}else if (tipoimg==3) {
					rutaImg = getClass().getResource("/ProyectoU22/rec/fon3g.png");
				}
				BufferedImage bI=null;
				Graphics2D g2 = (Graphics2D)g;
				try {
					bI=ImageIO.read(rutaImg);
				}catch (Exception e4) {
					e4.printStackTrace();
				}
				//RELLENO
				Rectangle2D rectan= new Rectangle2D.Double(0,0,altura,anchura);
				textura = new TexturePaint(bI, rectan);
				Ellipse2D elipse = new Ellipse2D.Double(500,20,200,200);
				g2.setPaint(textura);
				g2.fill(elipse);
			}
		};
		initial.setLayout(null);
		grupo = new ButtonGroup ();
		rb1 = new JRadioButton ("Puntos",true);
		rb2 = new JRadioButton ("Madera");
		rb3 = new JRadioButton ("Flores");
		grupo.add(rb1);grupo.add(rb2);grupo.add(rb3);
		rb1.setBounds(20, 50, 100, 30);
		rb2.setBounds(130, 50, 100, 30);
		rb3.setBounds(240, 50, 100, 30);
		initial.add(rb1);initial.add(rb2);initial.add(rb3);
		lbl1.setBounds(20, 2, 200, 20);
		lbl2.setBounds(20, 22, 200, 20);
		lbl3.setBounds(20, 90, 120, 120);
		lbl4.setBounds(150, 90, 120, 120);
		lbl5.setBounds(280, 90, 120, 120);
		initial.add(lbl1);initial.add(lbl2);initial.add(lbl3);initial.add(lbl4);initial.add(lbl5);
		initial.add(SAnch);
		initial.add(SLong);
		
		lbl6 = new JLabel ("Anchura del cuadro:");
		lbl6.setBounds(20, 250, 130, 30);
		lbl7 = new JLabel ("Altura del cuadro:");
		lbl7.setBounds(20, 280, 130, 30);
		initial.add(lbl6); initial.add(lbl7);
		ba = new JButton ("Aceptar");
		bc = new JButton ("Cancelar");
		ba.setBounds(520, 330,100,30);
		bc.setBounds(630,330,100,30);
		
		//tipo
		tipoimg=1;
		ba.addActionListener(this);
		bc.addActionListener(this);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		add(ba);
		add(bc);
		
		initial.setBounds(20, 2, getWidth(), getHeight()-70);
		add(initial);
		setLocationRelativeTo(this);
		SAnch.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				anchura = SAnch.getValue();
				initial.repaint();
			}
		});
		SLong.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				altura = SLong.getValue();
				initial.repaint();
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ba) {
			textP =textura;
			setVisible(false);
			dispose();
			
		}else if(e.getSource()==bc) {
			textP=null;
			setVisible(false);
			dispose();
		}else if (e.getSource()==rb1) {
			//ladrillo
			tipoimg=1;
			initial.repaint();
		}
		else if (e.getSource()==rb2) {
			//ladrillo
			tipoimg=2;
			initial.repaint();
		}
		else if (e.getSource()==rb3) {
			//ladrillo
			tipoimg=3;
			initial.repaint();
		}
	}
	public TexturePaint Despliega () {
		setVisible(true);
		return textP;
	}
}

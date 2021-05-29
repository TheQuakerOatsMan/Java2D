package ProyectoU22;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.geom.*;
import java.awt.event.*;

public class CDialogGradient extends JDialog implements ActionListener{
	//GRADIENTPAINT
	//NOTA: LOS GRADIENTES SE MANEJAN A PARTIR DELOS VALORES DE LA FIGURA ORIGINAL
	//POR SI NO SE VE TAN BIEN, ES UN PORQUE
	JButton ba,bc;
	ButtonGroup grupo1,grupo2;
	JRadioButton rb1,rb2,rb3,rb4;
	JTextField coordx,coordy,coordx2,coordy2;
	JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lblp1,lblp2,lblt,lblt2,lblt3,lblt4;
	Choice color1, color2;
	GeneralPath imgF;
	Boolean tipo,opcion1;
	Shape S,SO;
	GradientPaint colorGradient;
	JPanel initial;
	Double cx1,cy1,cx2,cy2;
	Color colog1,colog2,colors[]={Color.WHITE,Color.BLUE,Color.GREEN,Color.YELLOW,
			Color.ORANGE,Color.CYAN,Color.DARK_GRAY,
			Color.GRAY,Color.RED,Color.PINK,Color.MAGENTA,Color.BLACK
			};
	
	public CDialogGradient(Interfaz ref, boolean modal, Shape SOrigin) {
		super(ref.Screen,modal);
		setSize(800,400);
		setTitle("GradientPaint");
		//RESOLUCON 1000x620 del panel de la figura
		setLocationRelativeTo(this);
		setLayout(null);
		setResizable(false);
		
		//SHAPE
		SO = SOrigin;
		coordx = new JTextField(5);
		coordy = new JTextField(5);
		coordx2 = new JTextField(5);
		coordy2 = new JTextField(5);
		coordx.setText("0");
		coordy.setText("0");
		coordx2.setText("1000");
		coordy2.setText("620");
		cx1=setcoordX(Integer.parseInt(coordx.getText()));
		cy1=setcoordY(Integer.parseInt(coordy.getText()));
		cx2=setcoordX(Integer.parseInt(coordx2.getText()));
		cy2=setcoordY(Integer.parseInt(coordy2.getText()));
		//PANEL INICIAL
		TitledBorder tb1=new TitledBorder("Gradient paint");
		tb1.setTitleJustification(TitledBorder.CENTER);
		//PRUEBA NOMAS
		imgF = new GeneralPath();
		imgF.moveTo(520, 20);
		imgF.lineTo(520, 20);
		imgF.lineTo(770, 20);
		imgF.lineTo(770, 270);
		imgF.lineTo(520, 270);
		imgF.lineTo(520, 20);
		imgF.closePath();
		S=imgF;
		colog1 =buscaCol(0);
		colog2 =buscaCol(0);
		rb1 = new JRadioButton("Coordenadas de figura",true);
		rb2 = new JRadioButton("Puntos sobre el lienzo");
		rb3 = new JRadioButton("Normal",true);
		rb4 = new JRadioButton("Ciclico");
		lblp1 = new JLabel ("(PUNTOS INICIALES)");
		lblp2 = new JLabel ("(PUNTOS FINALES)");
		lblt = new JLabel("Tipo de secuencia:");
		lblt2 = new JLabel("NOTA: el cuadro representa una escala del lienzo del p. principal");
		lblt3 = new JLabel("(1000x620) Las casillas de los puntos estan en funcion del lienzo.");
		lblt4 = new JLabel("Los puntos de la figura estan en funcion ella misma");
		tipo=false;
		opcion1=false;
		
		 initial = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				GradientPaint color;
				if (tipo)
					 color = new GradientPaint((int)cx1.intValue(),(int)cy1.intValue(), colog1,(int)cx2.intValue(),(int)cy2.intValue(),colog2,true);
				else
					 color= new GradientPaint((int)cx1.intValue(),(int)cy1.intValue(), colog1,(int)cx2.intValue(),(int)cy2.intValue(),colog2);
				g2.setPaint(color);
				g2.fill(S);
			}
		};
		
		
		initial.setLayout(null);
		initial.setBounds(0, 0, this.getWidth()-20, 300);
		grupo1 = new ButtonGroup(); grupo2 = new ButtonGroup ();
		grupo1.add(rb1);grupo1.add(rb2);
		grupo2.add(rb3);grupo2.add(rb4);
		rb1.setBounds(20,4,170,20);
		rb2.setBounds(200,4,150,20);
		initial.add(rb1);initial.add(rb2);
		rb3.setBounds(20,230,150,20);
		rb4.setBounds(170,230,150,20);
		initial.add(rb3);initial.add(rb4);
		lblp1.setBounds(20, 30, 150, 20);
		initial.add(lblp1);
		lblp2.setBounds(200, 30, 150, 20);
		initial.add(lblp2);
		lbl1 = new JLabel("Coordenada en X:");
		lbl2 = new JLabel("Coordenada en Y:");
		lbl1.setBounds(20,50,100,30);
		lbl2.setBounds(20,80,100,30);
		coordx .setBounds(130, 50, 50, 30);
		coordy .setBounds(130, 80, 50, 30);
		initial.add(lbl1); initial.add(lbl2); 
		initial.add(coordx); initial.add(coordy);
		initial.setBorder(tb1);
		add(initial);
		lbl5= new JLabel ("Selecciona el color:");
		lbl5.setBounds(20, 130, 150, 30);
		initial.add(lbl5);
		color1 = new Choice();
		color2 = new Choice();
		cargaColor();
		color1.setBounds(20,160,200,50);
		color2.setBounds(250,160,200,50);
		initial.add(color1);
		initial.add(color2);
		lblt.setBounds(20, 210, 150, 20);
		initial.add(lblt);
		lblt2.setBounds(400, 300, 400, 20);
		add(lblt2);
		lblt3.setBounds(400, 320, 400, 20);
		add(lblt3);
		lblt4.setBounds(400, 340, 400, 20);
		add(lblt4);
		
		//FINAL
		lbl3 = new JLabel("Coordenada en X:");
		lbl4 = new JLabel("Coordenada en Y:");
		lbl6 = new JLabel("Selecciona el color:");
		lbl3.setBounds(250,50,100,30);
		lbl4.setBounds(250,80,100,30);
		coordx2 .setBounds(380, 50, 50, 30);
		coordy2 .setBounds(380, 80, 50, 30);
		initial.add(lbl3); initial.add(coordx2);
		initial.add(lbl4); initial.add(coordy2);
		lbl6.setBounds(250, 130, 150, 30);
		initial.add(lbl6);
		
		//SETEMAOS A FALSO
		coordx.setEnabled(false);
		coordy.setEnabled(false);
		coordx2.setEnabled(false);
		coordy2.setEnabled(false);
		
		ba = new JButton ("Aceptar");
		bc = new JButton ("Cancelar");
		ba.setBounds(20, 320,100,30);
		bc.setBounds(130,320,100,30);
		add(ba);
		add(bc);		
		
		ba.addActionListener(this);
		bc.addActionListener(this);
		//ACIONES DE COORDENADAS
		coordx.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            if (valiValues(ke)) {
	            	coordx.setEditable(true);
	            	try {
	            		cx1=setcoordX(Integer.parseInt(coordx.getText()));
	            	}catch (NumberFormatException e) {
	            		cx1=(double)0;
	            	}
		            initial.repaint();
	            } else {
	            	JOptionPane.showMessageDialog(null, "Solo puede haber numeros, reinicia el componente para debloquear");
	            	coordx.setEditable(false);
	            }
	         }
	      });
			coordy.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            
	            if (valiValues(ke)) {
	            	coordy.setEditable(true);
	            	try {
		            	cy1=setcoordY(Integer.parseInt(coordy.getText()));
	            	}catch (NumberFormatException e) {
	            		cy1=(double)cy1;
					}
		            initial.repaint();
	            } else {
	            	JOptionPane.showMessageDialog(null, "Solo puede haber numeros, reinicia el componente para debloquear");
	            	coordy.setEditable(false);
	            }
	         }
	      });
			coordx2.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            
	            if (valiValues(ke)) {
	            	coordx2.setEditable(true);
	            	try {
	            		cx2=setcoordX(Integer.parseInt(coordx2.getText()));
	            	}catch (NumberFormatException e) {
	            		cx2=(double)0;
					}
	            	initial.repaint();
	            } else {
	            	JOptionPane.showMessageDialog(null, "Solo puede haber numeros, reinicia el componente para debloquear");
	            	coordx2.setEditable(false);
	            }
	         }
	      });
			coordy2.addKeyListener(new KeyAdapter() {
		         public void keyPressed(KeyEvent ke) {
		           
		            if (valiValues(ke)) {
		            	coordy2.setEditable(true);
		            	try {
			            	cy2=setcoordY(Integer.parseInt(coordy2.getText()));
		            	}catch (NumberFormatException e) {
		            		cy2=(double)0;
						}
			            initial.repaint();
		            } else {
		            	JOptionPane.showMessageDialog(null, "Solo puede haber numeros, reinicia el componente para debloquear");
		            	coordy2.setEditable(false);
		            }
		         }
		      });
		
		color1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int cn= color1.getSelectedIndex();
				colog1=buscaCol(cn);
				repaint();
			}
		});
		color2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int cn= color2.getSelectedIndex();
				colog2=buscaCol(cn);
				repaint();
			}
		});
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		rb4.addActionListener(this);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

	}
	//VALORES VALIDOS
	public boolean valiValues(KeyEvent e) {
		if((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') 
        		|| e.getKeyCode() == KeyEvent.VK_BACK_SPACE
        		|| e.getKeyCode() == KeyEvent.VK_LEFT
        		|| e.getKeyCode() == KeyEvent.VK_RIGHT
        		|| e.getKeyCode() == KeyEvent.VK_UP
        		|| e.getKeyCode() == KeyEvent.VK_DOWN)
			return true;
		return false;
	}
	public Color buscaCol(int c) {
		Color cn= colors[c];
		return cn;
	}
	public double setcoordX(int n) {
		double cx=0;
		cx=n*0.25+520;//resolucion en base para el cuadro de muestra 250x250
		return cx;
	}
	public double setcoordY(int n) {
		double cy=0;
		cy=n*0.40+20; //resolucion en base para el cuadro de muestra 250x250
		return cy;
	}
	public void cargaColor() {
		color1.add("WHITE");
		color1.add("BLUE");
		color1.add("GREEN");
		color1.add("YELLOW");
		color1.add("ORANGE");
		color1.add("CYAN");
		color1.add("DARK_GRAY");
		color1.add("GRAY");
		color1.add("RED");
		color1.add("PINK");
		color1.add("MAGENTA");
		color1.add("BLACK");
		color2.add("WHITE");
		color2.add("BLUE");
		color2.add("GREEN");
		color2.add("YELLOW");
		color2.add("ORANGE");
		color2.add("CYAN");
		color2.add("DARK_GRAY");
		color2.add("GRAY");
		color2.add("RED");
		color2.add("PINK");
		color2.add("MAGENTA");
		color2.add("BLACK");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ba) {
			if (opcion1) {//va por puntos en el plano
				if (tipo)
					colorGradient = new GradientPaint(Integer.parseInt(coordx.getText()),Integer.parseInt(coordy.getText()) , colog1, Integer.parseInt(coordx2.getText()), Integer.parseInt(coordy2.getText()), colog2, true);
				else
					colorGradient = new GradientPaint(Integer.parseInt(coordx.getText()),Integer.parseInt(coordy.getText()) , colog1, Integer.parseInt(coordx2.getText()), Integer.parseInt(coordy2.getText()), colog2);
			}else { //va por el shape
				if (tipo)
					colorGradient = new GradientPaint((int)SO.getBounds().getMinX(),(int)SO.getBounds().getMinY(), colog1,(int)SO.getBounds().getMaxX(),(int)SO.getBounds().getMaxY(),colog2,true);
				else
					colorGradient = new GradientPaint((int)SO.getBounds().getMinX(),(int)SO.getBounds().getMinY(), colog1,(int)SO.getBounds().getMaxX(),(int)SO.getBounds().getMaxY(),colog2);
			}
			setVisible(false);
			dispose();
			
		}else if(e.getSource()==bc) {
			colorGradient=null;
			setVisible(false);
			dispose();
		}else if (e.getSource()==rb2) {
			//hablita
			coordx.setEnabled(true);
			coordy.setEnabled(true);
			coordx2.setEnabled(true);
			coordy2.setEnabled(true);
			opcion1=true;
		}else if (e.getSource()==rb1) {
			//deshablita
			coordx.setEnabled(false);
			coordy.setEnabled(false);
			coordx2.setEnabled(false);
			coordy2.setEnabled(false);
			opcion1=false;
		}else if (e.getSource()==rb3) {
			tipo=false;
			initial.repaint();
		}else if (e.getSource()==rb4) {
			tipo=true;
			initial.repaint();
		}
	}
	public GradientPaint Despliega() {
		setVisible(true);
		return colorGradient;
	}
}

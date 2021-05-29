package ProyectoU22;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.net.URL;

class Figura2D {
	GeneralPath figu;
	float vpuntos[] = {//51 Vertices
			156,171,147,126,134,88,125,56,115,67,117,93,118,123,
			156,171,142,157,132,145,111,117,97,92,79,63,70,69,64,84,
			63,106,67,123,70,136,79,157,89,178,98,199,109,219,103,236,
			98,254,99,276,102,297,106,324,119,359,132,338,134,316,
			150,312,140,269,159,260,175,254,185,245,196,228,202,214,
			211,192,216,176,225,163,235,154,253,153,276,150,254,146,235,145,
			223,139,210,127,182,133,172,147,171,165,156,171};
	
	public Figura2D() {
		//GENERAMOS EL SHAPE
		figu = new GeneralPath();
		figu.moveTo(156, 171);
		GeneraFigu();
		figu.closePath();	
		
	}
	public void GeneraFigu() {
		for (int i = 0; i < vpuntos.length-2; i+=2) {
			figu.lineTo(vpuntos[i], vpuntos[i+1]);
		}
	}
	public GeneralPath getFigu2D () {
		return figu;
	}
}

public class Interfaz extends JPanel implements ActionListener{
	
	JFrame Screen;
	Figura2D Figura2D;
	Shape S;
	Boolean move;
	JTabbedPane PanelM,workspace;
	JToolBar BarraH;
	JButton op1,op2,op3,op4,op5,op6,op7,op8,opRD,opRI,opEsc,opFlip,opDefo,opMov,opInfo,opRe,opSal;
	JButton opLG, opBS, opGP, opRGB;
	JPanel panel1;
	Image FondoLienzo, img;
	URL ruta;
	Boolean contorno=false,fondo=false,stroke, graPaint,txtPaint;
	int cantB, color1=-2,color2=0;
	//VECTOR DE COLORES
	Color colores[]={Color.RED,Color.BLUE,Color.BLACK,Color.LIGHT_GRAY,Color.CYAN,Color.BLUE,Color.ORANGE,Color.RED},newColor;
	Action Mov,Defo,Flip,RodD,RodI,Esc,Reset,Quit,BaStro,Crgb,Gradient,Relleno;
	//Strok
	BasicStroke Strok;
	Color colorStrok;
	GradientPaint gradiente;
	TexturePaint rimg;
	
	public Interfaz() {
		Screen = new JFrame("Transformaciones Usando API java 2D");
		Screen.setSize(1000,720);
		setSize(1000,720);
		Screen.setLocationRelativeTo(this);
		Screen.setResizable(false);
		//Figura2D & Shape
		Figura2D = new Figura2D();
		S = Figura2D.getFigu2D();
        ruta = getClass().getResource("/ProyectoU22/rec/cuadricula.png");    //ESTABLECER FONDO
        FondoLienzo = new ImageIcon(ruta).getImage(); 
		cantB = 0;
        stroke =false;
        Strok =null;
        gradiente = null;
        graPaint = false;
        txtPaint =false;
        rimg =null;
        colorStrok=Color.DARK_GRAY;
		newColor=Color.GREEN;
		setBackground(Color.LIGHT_GRAY);
		
        //CARGA MENU
		cargaMenu();
		Lienzo();
		Screen.add(this);
		Screen.setVisible(true);
		Screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//METODO PARA CREAR BOTONES
	public JButton CreaBoton(URL ruta, int x, int y, int w, int h, String TT, Action a) {
        JButton b = new JButton(a);
        b.setIcon(new ImageIcon(ruta));
        b.setBounds(x, y, w, h);
        b.setToolTipText(TT);
        b.setBackground(Color.DARK_GRAY);
        return b;
    }
	public void cargaMenu() {
		PanelM = new JTabbedPane(JTabbedPane.TOP);
        PanelM.setBounds(0, 0, getWidth(), 100);
        // PANEL 1
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        Border borde = BorderFactory.createTitledBorder("Opciones Menu");
        JPanel Herramientas = new JPanel();
        Herramientas.setLayout(null);
        Herramientas.setBorder(borde);
        Herramientas.setBounds(0, 0, 310, 70);
        borde = BorderFactory.createTitledBorder("API 2D");
        JPanel MApi2d = new JPanel();
        MApi2d.setLayout(new GridLayout(1,6));
        MApi2d.setBorder(borde);
        MApi2d.setBounds(330, 0, 290, 70);
        borde = BorderFactory.createTitledBorder("Extras");
        JPanel Extras =new JPanel ();
        Extras.setLayout(new GridLayout(1,3));
        Extras.setBorder(borde);
        Extras.setBounds(800, 0, 190, 70);
        PanelM.addTab("Menu", null, panel1, null);
        panel1.setBackground(Color.LIGHT_GRAY);
        Extras.setBackground(Color.LIGHT_GRAY);
        Herramientas.setBackground(Color.LIGHT_GRAY);
        MApi2d.setBackground(Color.LIGHT_GRAY);
        Extras.setForeground(Color.WHITE);
        Herramientas.setForeground(Color.WHITE);
        MApi2d.setForeground(Color.WHITE);
        //CARGA ACCIONES
        CargaAcciones();
        
        //AÑADE BOTONES
        ruta = getClass().getResource("/ProyectoU22/rec/rotate3.png");
        opRD = CreaBoton(ruta, 10, 20, 40, 40, "Rota a la derecha la figura", RodD);
        KeyStroke KS12 = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK);
        opRD.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS12, "");
        opRD.getActionMap().put("", RodD);
        
        ruta = getClass().getResource("/ProyectoU22/rec/rotate2.png");
        opRI = CreaBoton(ruta, 60, 20, 40, 40, "Rota a la izquierda de la figura",RodI);
        KeyStroke KS13 = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK);
        opRI.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS13, "");
        opRI.getActionMap().put("", RodI);

        ruta = getClass().getResource("/ProyectoU22/rec/scale2.png");
        opEsc = CreaBoton(ruta, 110, 20, 40, 40, "Escala la figura",Esc);
        KeyStroke KS14 = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK);
        opEsc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS14, "");
        opEsc.getActionMap().put("", Esc);

        ruta = getClass().getResource("/ProyectoU22/rec/movef.png");
        opMov = CreaBoton(ruta, 160, 20, 40, 40, "Mueve la figura hacia una coordenada", Mov);
        KeyStroke KS15 = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK);
        opMov.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS15, "");
        opMov.getActionMap().put("", Mov);
        
        ruta = getClass().getResource("/ProyectoU22/rec/flip.png");
        opFlip = CreaBoton(ruta, 210, 20, 40, 40, "Refleja la figura en el sentido",Flip);
        KeyStroke KS16 = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK);
        opFlip.getActionMap().put("", Flip);
        opFlip.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS16, "");
        
        ruta = getClass().getResource("/ProyectoU22/rec/deform.png");
        opDefo = CreaBoton(ruta, 260, 20, 40, 40, "Deforma la figura con affineTransform", Defo);
        KeyStroke KS17 = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK);
        opDefo.getActionMap().put("", Defo);
        opDefo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS17, "");
        
        Herramientas.add(opRD);
        Herramientas.add(opRI);
        Herramientas.add(opEsc);
        Herramientas.add(opMov);
        Herramientas.add(opFlip);
        Herramientas.add(opDefo);
        //API2D
        ruta = getClass().getResource("/ProyectoU22/rec/barra.png");
        opBS = CreaBoton(ruta, 320, 20, 40, 40, "Crea una linea de borde con BasicStroke", BaStro);
        KeyStroke KS21 = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_DOWN_MASK);
        opBS.getActionMap().put("", BaStro);
        opBS.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS21, "");
        
        ruta = getClass().getResource("/ProyectoU22/rec/gradient.png");
        opLG = CreaBoton(ruta, 370, 20, 40, 40, "Establece un relleno con linearGrdient", Gradient);
        KeyStroke KS22 = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK);
        opLG.getActionMap().put("", Gradient);
        opLG.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS22, "");

        ruta = getClass().getResource("/ProyectoU22/rec/image.png");
        opGP = CreaBoton(ruta, 470, 20, 40, 40, "Rellena el fondo con una imagen predeterminada", Relleno);
        KeyStroke KS24 = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_DOWN_MASK);
        opGP.getActionMap().put("", Relleno);
        opGP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS24, "");

        ruta = getClass().getResource("/ProyectoU22/rec/rgb.png");
        opRGB = CreaBoton(ruta, 570, 20, 40, 40, "Crea una nueva area de figura dentro de la ya establecida", Crgb);
        KeyStroke KS26 = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK);
        opRGB.getActionMap().put("", Crgb);
        opRGB.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS26, "");
        
        MApi2d.add(opBS);MApi2d.add(opLG);
        MApi2d.add(opGP);
        MApi2d.add(opRGB);
        //PANEL AFFINETRASN
        panel1.add(Herramientas);
        panel1.add(MApi2d);
        panel1.add(Extras);
        //EXTRAS
        Action Ayuda= new AbstractAction("") {
			public void actionPerformed(ActionEvent e) {
				//SALIR
				CDialogInfo c = new CDialogInfo(Interfaz.this, true);
				c.Mostrar(); 
			}
		};
        ruta = getClass().getResource("/ProyectoU22/rec/undo.png");
        opRe=CreaBoton(ruta, 810, 20, 40, 40, "Restaura la figura a sus valores originales", Reset);
        KeyStroke KS18 = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK);
        opRe.getActionMap().put("", Reset);
        opRe.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS18, "");
        
        ruta = getClass().getResource("/ProyectoU22/rec/como.png");
        opInfo=CreaBoton(ruta, 860, 20, 40, 40, "Muestra la ayuda del programa", Ayuda);
        KeyStroke KS19 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        opInfo.getActionMap().put("",Ayuda);
        opInfo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS19, "");
        
        ruta = getClass().getResource("/ProyectoU22/rec/exit2.png");
        opSal=CreaBoton(ruta, 910, 20, 40, 40, "Salimos del Editor", Quit);
        KeyStroke KS20 = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK);
        opSal.getActionMap().put("", Quit);
        opSal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS20, "");
       
        Extras.add(opRe);
        Extras.add(opInfo);
        Extras.add(opSal);
        //PANEL DE EXTRAS
        Screen.add(PanelM);

        
    }
    public void Lienzo() {
        workspace = new JTabbedPane(JTabbedPane.TOP);
        workspace.setBounds(0, 101, getWidth(), getHeight() - 100);
        // PANEL 1
        panel1 = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
            	super.paintComponent(g);
        		Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(FondoLienzo, 0, 0, getWidth(), getHeight(), this);
                if (contorno) {
                	if (stroke) {
                    	g2.setColor(colorStrok);
                		g2.setStroke(new BasicStroke(cantB,Strok.getEndCap(),Strok.getLineJoin(),Strok.getMiterLimit(),Strok.getDashArray(),Strok.getDashPhase()));
                        g2.draw(S);
                	}else {
                		//pone uno predeterminado
                	g2.setColor(colorStrok);
            		g2.setStroke(new BasicStroke(cantB,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
                    g2.draw(S);
                    }
                }
                if (fondo) {
            		GradientPaint color = new GradientPaint((int)S.getBounds().getMinX(),(int)S.getBounds().getMinY(), colores[color1],(int)S.getBounds().getMaxX(),(int)S.getBounds().getMinX(),colores[color2]);
            		g2.setPaint(color);
                }else if (graPaint) {
                	g2.setPaint(gradiente);
                }else if (txtPaint) {
                	g2.setPaint(rimg);
                }
                else {
                    g2.setColor(newColor);	
                }
                g2.fill(S);
            }
        };
        panel1.setBounds(0, 0, getWidth(), getHeight());
        workspace.addTab("Cuadro", null, panel1, null);
        
      //ACCIONES DEL RATON
      		panel1.addMouseListener(new MouseAdapter() {
      			public void mousePressed(MouseEvent e) {
      				int cx=e.getX();
      				//En caso de que este en center
      				int cy= e.getY();
      				if (S.contains(cx,cy)) {
      					move=true;
      				}
      				else
      					move=false;
      				if (cx>=S.getBounds2D().getMaxX() && e.getClickCount()>=2)
      					Rotacion(5);
      					else
      						if (cx<S.getBounds2D().getMinX()&& e.getClickCount()>=2)
      						Rotacion(-5);
      				workspace.repaint();
      			}
      		});
      		panel1.addMouseMotionListener(new MouseAdapter() {
      			
      			public void mouseDragged(MouseEvent e) {
      				if (move)
      				{
      				//HACEMOS LA MOVICION
      					int cx= e.getX(), cy=e.getY();
      					int tx=(int)cx-(int)S.getBounds2D().getCenterX();
      					int ty=(int)cy-(int)S.getBounds2D().getCenterY();
      					Trasladar(tx,ty);
      					workspace.repaint();
      				}
      				
      			}
      		});
      		panel1.addMouseWheelListener(new MouseWheelListener() {
      			
      			public void mouseWheelMoved(MouseWheelEvent e) {
      				int rotaS = e.getWheelRotation();
      				if (rotaS<0)
      					Escalar(1.01);
      				else
      					Escalar(.99);
      				workspace.repaint();
      			}
      		});
      		//ACCIONES Y ACELERADORAS
      		Action ZoomIn = new AbstractAction("") {
                public void actionPerformed(ActionEvent e) {
                    //ZOOM
                	Escalar(1.01);
					workspace.repaint();
                }
            };
            op1 = new JButton (ZoomIn);
            ruta = getClass().getResource("/ProyectoU22/rec/plus.png");
            op1.setIcon(new ImageIcon(ruta));
      		op1.setToolTipText("Aumenta el Zoom una n cantidad");
            KeyStroke KS1 = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK);
            op1.getActionMap().put("", ZoomIn);
            op1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS1, "");

            Action ZoomOut = new AbstractAction("") {
                public void actionPerformed(ActionEvent e) {
                    //ZOOM
                	Escalar(.99);
					workspace.repaint();
                }
            };
            op2 = new JButton (ZoomOut);
            ruta = getClass().getResource("/ProyectoU22/rec/minus.png");
            op2.setIcon(new ImageIcon(ruta));
            op2.setToolTipText("Decrementa el Zoom una n cantidad");
            KeyStroke KS2 = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
            op2.getActionMap().put("", ZoomOut);
            op2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS2, "");
      	
            Action Reflect = new AbstractAction("") {
                public void actionPerformed(ActionEvent e) {
                    //REFLEXION
					Reflect(-1, -1);
					workspace.repaint();
                }
            };
            op3 = new JButton (Reflect);
            ruta = getClass().getResource("/ProyectoU22/rec/grafico.png");
            op3.setIcon(new ImageIcon(ruta));
            op3.setToolTipText("Refleja la figura en el Eje XY");
            KeyStroke KS3 = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
            op3.getActionMap().put("", Reflect);
            op3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS3, "");
            
            Action rotaDer = new AbstractAction("") {
                public void actionPerformed(ActionEvent e) {
                    //REFLEXION
					Rotacion(10);
					workspace.repaint();
                }
            };
            op4 = new JButton (rotaDer);
            ruta = getClass().getResource("/ProyectoU22/rec/rota.png");
            op4.setIcon(new ImageIcon(ruta));
            op4.setToolTipText("Rota la figura 10 grados");
            KeyStroke KS4 = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK);
            op4.getActionMap().put("", rotaDer);
            op4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS4, "");
            
            Action rotaIzq = new AbstractAction("") {
                public void actionPerformed(ActionEvent e) {
                    //REFLEXION
					Rotacion(-10);
					workspace.repaint();
                }
            };
            op5 = new JButton (rotaIzq);
            ruta = getClass().getResource("/ProyectoU22/rec/rota2.png");
            op5.setIcon(new ImageIcon(ruta));
            op5.setToolTipText("Rota la figura 10 grados a la izquierda");
            KeyStroke KS5 = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
            op5.getActionMap().put("", rotaIzq);
            op5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KS5, "");
            op6 = new JButton();
            ruta =getClass().getResource("/ProyectoU22/rec/stroke.png");
            op6.setIcon(new ImageIcon(ruta));
            op6.setToolTipText("Aumenta una linea stroke 2px ala figura");
            op7 = new JButton();
            ruta =getClass().getResource("/ProyectoU22/rec/stroke2.png");
            op7.setIcon(new ImageIcon(ruta));
            op7.setToolTipText("Disminuye una linea stroke 2px ala figura");
            op8 = new JButton();
            op8.setToolTipText("Cambia el relleno a diferentes valores con gradientPaint");
            ruta =getClass().getResource("/ProyectoU22/rec/gradient1.png");
            op8.setIcon(new ImageIcon(ruta));
            //BARRA HERRAMIENTAS            
            BarraH = new JToolBar(JToolBar.VERTICAL);
            BarraH.add(op1);
            BarraH.add(op2);
            BarraH.add(op3);
            BarraH.add(op4);
            BarraH.add(op5);
            BarraH.add(op6);
            BarraH.add(op7);
            BarraH.add(op8);
            BarraH.setFloatable(false);
            
            op6.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					cantB+=2;
					contorno=true;
					workspace.repaint();
				}
			});
            op7.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					cantB-=2;
					if (!(cantB<=0)) {
						contorno=true;
					}
					else {
						cantB=0;
						contorno=false;
					}
					workspace.repaint();
				}
			});
            op8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//ESTABLECEMOS COLORES
					if (color2>=colores.length-1) {
						fondo=false;
						color1=-2;
						color2=0;
					}else {
						fondo=true;
						color1+=2;
						color2=color1+1;
					}
					workspace.repaint();
				}
			});
            BarraH.setBounds(getWidth()-50,150,40,300);
            Screen.add(BarraH);
            Screen.add(workspace);
    }

	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.GREEN);
		g2.fill(S);
	}*/
    //************************
	//ACCIONES AFFINETRASFORM
	public void Escalar (double esc) {
		AffineTransform AT1 = new AffineTransform();
		//Regresamos Escalamos y Devolvemos
		AT1.translate(S.getBounds2D().getCenterX(), S.getBounds2D().getCenterY());
		AT1.scale(esc,esc);
		AT1.translate(-S.getBounds2D().getCenterX(), -S.getBounds2D().getCenterY());
		S = AT1.createTransformedShape(S);
	}
	public void Trasladar (int Tx, int Ty) {
		AffineTransform AT = new AffineTransform();
		AT.translate(S.getBounds2D().getCenterX(), S.getBounds2D().getCenterY());
		AT.setToTranslation(Tx, Ty);
		S = AT.createTransformedShape(S);
	}
	public void Rotacion (int grados) {
		double angulo = Math.toRadians(grados);
		AffineTransform AT= new AffineTransform();
		AT.setToRotation(angulo, S.getBounds().getCenterX(), S.getBounds().getCenterY());
		S = AT.createTransformedShape(S);
	}
	public void Deformar (double def, double defy) {
		//EN ESTA PARTE LOS EVENTOS DE Affine transform trabajan sobre el origen, por lo que hay que hacer 3 pasos
		AffineTransform AT = new AffineTransform();
		AT.translate(S.getBounds2D().getCenterX(), S.getBounds2D().getCenterY());
		AT.shear(def, defy);
		AT.translate(-S.getBounds2D().getCenterX(), -S.getBounds2D().getCenterY());
		S = AT.createTransformedShape(S);
	}
	public void Reflect (int refX, int refY) {		AffineTransform AT = new AffineTransform();
		AT.translate(S.getBounds2D().getCenterX(), S.getBounds2D().getCenterY());
		AT.scale(refX, refY);
		AT.translate(-S.getBounds2D().getCenterX(), -S.getBounds2D().getCenterY());
		S = AT.createTransformedShape(S);
	}
	public static void main(String[] args) {
		new Interfaz();
	}
	//ACCIONES DE LOS MENUES
	public void CargaAcciones () {
		Mov = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                //MOVER
                double posN[] = new CDialogMovDef(Interfaz.this, true, 2).mostrar();
                if (posN != null) {
                	Trasladar((int)posN[0], (int)posN[0]);
                    workspace.repaint();
                }
            }
        };
        Defo = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                //DEFORMAR
                double cantN[] = new CDialogMovDef(Interfaz.this, true, 1).mostrar();
                if (cantN != null) {
                    Deformar(cantN[0],cantN[0]);
                    workspace.repaint();
                }
            }
        };
        Flip = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                //ACCION FLIP
                int tipo = new CDialogFlip(Interfaz.this, true).mostrar();
                if (tipo == 0) {
                    Reflect(-1, 1);
                } else if (tipo == 1) {
                    Reflect(1, -1);
                } else if (tipo == 2) {
                    Reflect(-1, -1);
                }
                workspace.repaint();
            }
        };
        RodI = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                double cantR = new CDialogRotEsc(Interfaz.this, true, 2).mostrar();
                Rotacion((int)-cantR);
                workspace.repaint();
            }
        };
        RodD = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
            	double cantR = new CDialogRotEsc(Interfaz.this, true, 3).mostrar();
                Rotacion((int)cantR);
                workspace.repaint();
            }
        };
        Esc = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                //ESCALAR
                double esc = new CDialogRotEsc(Interfaz.this, true,1).mostrar();
                Escalar(esc);
                workspace.repaint();
            }
        };
        Reset = new AbstractAction("") {
			public void actionPerformed(ActionEvent e) {
				//RESTAURAR SI HAY CAMBIO SE RESTAURA
				boolean bandera = new CDiaglogRestore(Interfaz.this,true).Mostrar();
				if(bandera) {
				S = Figura2D.getFigu2D();
				contorno=false;
				fondo=false;
				stroke=false;
				graPaint=false;
				gradiente =null;
				txtPaint=false;
				rimg = null;
				newColor=Color.GREEN;
				colorStrok=Color.DARK_GRAY;
				workspace.repaint();
				bandera=false;
				cantB=0;
				JOptionPane.showMessageDialog(null,"Valores restaurados");
				}
			}
		};
			Quit= new AbstractAction("") {
			public void actionPerformed(ActionEvent e) {
				//SALIR
				System.exit(0);
			}
		};
		   BaStro = new AbstractAction("") {
				public void actionPerformed(ActionEvent e) {
					BasicStroke sAnt=Strok;
					CDialogStroke c;
					int cantAnt = cantB;
					try {
						//activa
						c = new CDialogStroke(Interfaz.this, true);
						c.Despliega();
						Strok = c.ReturnS();
						colorStrok = c.returnC();
					} catch (Exception e2) {
						//Marca error y pasamos el anterior
						Strok=sAnt;
						colorStrok=Color.DARK_GRAY;
					}
					if (Strok==null) {
						Strok=sAnt;
						colorStrok=Color.DARK_GRAY;
						stroke=false;
						cantB=cantAnt;
					}else {
						stroke=true;
						contorno=true;
						cantB=(int)Strok.getLineWidth();
						}
					workspace.repaint();
				}
		}; 
		Crgb = new AbstractAction("") {
			public void actionPerformed(ActionEvent e) {
				Color colorAnt = newColor;
				newColor = new CDialogColors(Interfaz.this, true).Despliega();
				if (newColor==null) {
					if (!fondo || !graPaint ||!txtPaint)
						newColor=colorAnt;
				}
				else {//SETEAMOS BANDERAS
					if (fondo) {
						fondo=false;
						color1=-2;
						color2=0;
					}
					if (graPaint)
						graPaint=false;
					if (txtPaint)
						txtPaint=false;
				}
				workspace.repaint();
			}
		};
		//EL GRADIENTE
		Gradient = new AbstractAction("") {
			public void actionPerformed(ActionEvent e) {
				GradientPaint graAnt = gradiente;
				gradiente= new CDialogGradient(Interfaz.this, true, S).Despliega();
				if (!(gradiente ==null)) {
					graPaint = true;
					//SETEAMOS BANDERAS
					if (fondo) {
						fondo=false;
						color1=-2;
						color2=0;
					}if (txtPaint)
						txtPaint=false;
					workspace.repaint();
				}else {
					gradiente=graAnt;
					if (!fondo || !graPaint ||!txtPaint)
						gradiente=graAnt;
				}
			}
		};
		Relleno = new AbstractAction("") {
			public void actionPerformed(ActionEvent e) {
				TexturePaint rimgAnt=rimg;
				rimg = new CDialogRellenoImg(Interfaz.this, true).Despliega();
				if (!(rimg ==null)) {
					txtPaint= true;
					//SETEAMOS BANDERAS
					if (fondo) {
						fondo=false;
						color1=-2;
						color2=0;
					}
					if (graPaint)
						graPaint=false;
					workspace.repaint();
				}else {
					rimg=rimgAnt;
					if (!fondo || !graPaint ||!txtPaint)
						rimg=rimgAnt;
				}
			}
		};
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}

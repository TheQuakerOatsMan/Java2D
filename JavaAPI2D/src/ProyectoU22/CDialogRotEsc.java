package ProyectoU22;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CDialogRotEsc extends JDialog{
	double cantEsc=0;
	JLabel et1,et2;
	JTextField ct1;
	JButton bac,bca;
	URL ruta;
	
	public CDialogRotEsc (Interfaz ref, boolean modal, int tipo) {
		super(ref.Screen,modal);
		setSize(500,80);
		setLocationRelativeTo(this);
		setLayout(new FlowLayout());
		if (tipo==1) {
			setTitle("Escalar una figura");
			ruta = getClass().getResource("/ProyectoU22/rec/scale2.png");
			et2=new JLabel("Cantidad a escalar:");
		}
		else if (tipo==2) {
			setSize(600,80);
			setTitle("Rotar Izq una figura");
			ruta = getClass().getResource("/ProyectoU22/rec/rotate2.png");
			et2=new JLabel("Cantidad rotacion a la izquierda");
		}
		else if (tipo==3) {
			setSize(600,80);
			setTitle("Rotar Der una figura");
			ruta = getClass().getResource("/ProyectoU22/rec/rotate3.png");
			et2=new JLabel("Cantidad rotacion a la derecha:");
		}
		et1=new JLabel(new ImageIcon(ruta));
		ct1=new JTextField(10);
		bac= new JButton("Aceptar");
		bca= new JButton("Cancelar");
		add(et1);
		add(et2);
		add(ct1);
		add(bac);
		add(bca);
		bac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String res=ct1.getText();
				try {
					cantEsc= Double.parseDouble(res);
				} catch (NumberFormatException e2) {
					if (tipo==1) {
						cantEsc=1.0;	
					}else if (tipo==2 || tipo==3) {
						cantEsc=0;
					}
				}
				setVisible(false);
				dispose();
			}
		});
		bca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tipo==1) {
					cantEsc=1.0;	
				}else if (tipo==2 || tipo==3) {
					cantEsc=0;
				}
				setVisible(false);
				dispose();			
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	public double mostrar() {
		setVisible(true);
		return cantEsc;
	}
}

package ProyectoU22;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.net.URL;
public class CDialogMovDef  extends JDialog{
	double pos[];
	JLabel et1,et2,et3;
	JTextField ct1,ct2;
	JButton bac,bca;
	//DIALOG PARA MOVER Y DEFOMRAR, YA QUE SON PARECIDOS, PODEMOs REUTILIZARLO
	public CDialogMovDef(Interfaz ref, boolean modal,int tipo) {
		super(ref.Screen,modal);
		setSize(500,80);
		setLocationRelativeTo(this);
		setLayout(new FlowLayout());
		URL ruta;
		if (tipo==1) {
			setTitle("Deformar una figura");
			ruta = getClass().getResource("/ProyectoU22/rec/deform.png");
			et2=new JLabel("Eje X:");
			et3=new JLabel("Eje Y:");
		}else {
			setTitle("Mover/Trasladar una figura");
			ruta = getClass().getResource("/ProyectoU22/rec/movef.png");
			et2=new JLabel("Punto X:");
			et3=new JLabel("Punto Y:");
		}
		et1=new JLabel(new ImageIcon(ruta));
		ct1=new JTextField(5);
		ct2=new JTextField(5);
		bac= new JButton("Aceptar");
		bca= new JButton("Cancelar");
		add(et1);
		add(et2);
		add(ct1);
		add(et3);
		add(ct2);
		add(bac);
		add(bca);
		bac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String resX=ct1.getText();
				String resY=ct2.getText();
				pos = new double[2];
				try {
					pos[0]= Double.parseDouble(resX);
					pos[1]= Double.parseDouble(resY);
				} catch (NumberFormatException e2) {
					pos=null;
					JOptionPane.showMessageDialog(null, "No debe de haber campos vacios, pon un 0 al menos");
				}
				setVisible(false);
				dispose();
			}
		});
		bca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pos=null;
				setVisible(false);
				dispose();			
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	public double[] mostrar() {
		setVisible(true);
		return pos;
	}
}
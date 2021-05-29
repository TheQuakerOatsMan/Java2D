package ProyectoU22;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.URL;

public class CDialogFlip  extends JDialog{

	int argR;
	JLabel et1,et2,et3,et4,et5;
	JRadioButton rb1,rb2,rb3;
	ButtonGroup grupoR;
	JButton bac,bca;
	
	public CDialogFlip(Interfaz ref, boolean modal) {
		super(ref.Screen,modal);
		setTitle("Reflexi\u00F3n de una figura");
		setSize(550,80);
		setLocationRelativeTo(this);
		setLayout(new FlowLayout());
		
		//COMPONENTES
		URL ruta = getClass().getResource("/ProyectoU22/rec/flip.png");
		et1=new JLabel(new ImageIcon(ruta));
		ruta = getClass().getResource("/ProyectoU22/rec/flipv.png");
		et2=new JLabel(new ImageIcon(ruta));
		et3= new JLabel("Ref Eje X");
		et4= new JLabel("Ref Eje Y");
		et5= new JLabel("Ref Eje XY");
		grupoR = new ButtonGroup();
		rb1 = new JRadioButton("",true);
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();

		grupoR.add(rb1);
		grupoR.add(rb2);
		grupoR.add(rb3);

		bac= new JButton("Aceptar");
		bca= new JButton("Cancelar");
		add(et1);
		add(et2);
		add(et3);
		add(rb1);
		add(et4);
		add(rb2);
		add(et5);
		add(rb3);
		add(bac);
		add(bca);
		bac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//opcion,reflejo
				argR=5;;
				//Derecha
				if(rb1.isSelected()) {
					argR=0;
				}else if (rb2.isSelected()) {
					//Izquierda
					argR=1;
				}
				else if (rb3.isSelected()) {
					argR=2;
				}
				setVisible(false);
				dispose();
			}
		});
		bca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				argR=-1;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	public int mostrar() {
		setVisible(true);
		return argR;
	}
}

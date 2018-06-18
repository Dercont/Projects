import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Proyect1 implements ActionListener
{
	
   JFrame ventana;
   JButton btn_boton, btn_tmp, btn_ulti;
   JTextField tf_x, tf_y;
   JLabel lbl_norte, lbl_sur, lbl_oeste, lbl_este, lbl_nombre;
   JLabel lbl_x, lbl_y, lbl_n, lbl_n2, lbl_route, lbl_Inst;
   ArrayList <JButton> ultibutton = new ArrayList<>();
   Random randu = new Random();
	int x,y;
	int conte = 0;   
   
   public static void main(String[] args)
   {
      new Proyect1();
   }
   
   Proyect1()
   {
	  //Ventana
	  ventana = new JFrame("Cazador de Tesoros");
      ventana.setBounds(550,140,640,640); // X,Y,WIDTH,HEIGHT
	  ventana.setLayout(null);
      ventana.setResizable(false);
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	 


	 // Etiquetas
	  lbl_norte = new JLabel("NORTE");
	  lbl_norte.setBounds(310,50,70,40);
	  ventana.add(lbl_norte);
	  
	  lbl_sur = new JLabel("SUR");
	  lbl_sur.setBounds(310,305,70,40);
	  ventana.add(lbl_sur);
	    
	  lbl_este = new JLabel("ESTE");
	  lbl_este.setBounds(525,180,70,40);
	  ventana.add(lbl_este);
	  
	  lbl_oeste = new JLabel("OESTE");
	  lbl_oeste.setBounds(80,180,70,40);
	  ventana.add(lbl_oeste); 
      
	  lbl_nombre = new JLabel("NOMBRE: Armando Almengor" + "\n" + " CEDULA: 8-923-936");
      lbl_nombre.setBounds(165,500,290,40);
	  ventana.add(lbl_nombre);
	  
	  	 // Botones
	  int i;
      for (i=0;i<100;i++)
      {
         btn_boton = new JButton(); //String.valueOf(i)
         btn_boton.setBounds(150+35*(i%10), 90+20*(i/10),35,20);
         btn_boton.addActionListener(this);
         ventana.add(btn_boton);
		 ultibutton.add(btn_boton);
      }
	  
	   btn_ulti = ultibutton.get(randu.nextInt(ultibutton.size()));
	
        
    lbl_x = new JLabel("X");
	  lbl_x.setBounds(465,450,30,20); // X,Y,WIDTH,HEIGHT. Sumar Y+HEIGHT+5
	  ventana.add(lbl_x);
	  
	  tf_x = new JTextField();
	  tf_x.setBounds(500,450,30,20); // X,Y,WIDTH,HEIGHT. Sumar X+WIDTH+5
	  ventana.add(tf_x);
	  
	  lbl_y = new JLabel("Y");
	  lbl_y.setBounds(465,500,30,20);
	  ventana.add(lbl_y);
	  
	  tf_y = new JTextField();
	  tf_y.setBounds(500,500,30,20);
	  ventana.add(tf_y);
	 
	  lbl_route = new JLabel();
	  lbl_route.setBounds(40,400,290,100);
	  ventana.add(lbl_route);
	  
	  lbl_Inst = new JLabel("PRESIONA CUALQUIER BOTON PARA INICIAR EL JUEGO...");
	  lbl_Inst.setBounds(40,350,350,100);
	  ventana.add(lbl_Inst);
	  
	  int x1,y1;
	  for(x1=1; x1<11;x1++){
		  String n = String.valueOf(x1);
		  lbl_n = new JLabel(n);
		  lbl_n.setBounds(130+35*(x1%11), 290-20*(x1/11),35,20);
		  ventana.add(lbl_n);
	  }
	 for(y1=10;y1>0;y1--){
		  String n2 = String.valueOf(y1);
		  lbl_n2 = new JLabel(n2);
		  lbl_n2.setBounds(130+30*(y1%1), 290-20*(y1/1),35,20);
		  ventana.add(lbl_n2);
	 }
	 
      ventana.setVisible(true);
   
   }

   public void actionPerformed(ActionEvent e)
   {
	   this.conte++;
	   btn_tmp = (JButton)e.getSource();
	   this.x = btn_tmp.getLocation().x;
	   this.y = btn_tmp.getLocation().y; 
       
	  tf_x.setText(String.valueOf(x));
	  tf_y.setText(String.valueOf(y));
       sentencias();
   }
   
  public void sentencias() {
	  int obj_x, obj_y;
	  obj_x = btn_ulti.getLocation().x;
	  obj_y = btn_ulti.getLocation().y;
	  
	  if ((x == obj_x) && (y == obj_y)){
		  
		  JOptionPane fran = new JOptionPane();
		  fran.showMessageDialog(null, "Felicidades has ganado! \n"+"Intentos: "+String.valueOf(this.conte));
			int i = fran.showConfirmDialog(null, "Iniciar nuevo juego?");	

		if(i == 0) {
			ventana.setVisible(false);
			ventana.dispose();
			new Proyect1();
		}
		
		if (i == 1)
			ventana.dispose();
		
		if (i == 2) {
			System.out.println("Cancelar");
		}
	    }
		
		if (( x == obj_x) && ( y > obj_y))
			lbl_route.setText("Ve hacia el Norte");
		
		if ((x == obj_x) && ( y < obj_y))
			lbl_route.setText("Ve hacia el Sur");
		
		if ((x < obj_x) && ( y == obj_y))
			lbl_route.setText("Ve hacia el Este");
		
		if ((x > obj_x) && (y == obj_y))
			lbl_route.setText("Ve hacia el Oeste");
		
		if ((x < obj_x) && (y > obj_y))
			lbl_route.setText("Ve al Noreste");
		
		if ((x < obj_x) && (y < obj_y))
			lbl_route.setText("Ve al Sureste");
		
		if ((x > obj_x) && (y < obj_y))
			lbl_route.setText("Ve al Suroeste");
		
		if ((x > obj_x) && ( y > obj_y))
			lbl_route.setText("Ve al Noroeste");
		
  }
		

}	






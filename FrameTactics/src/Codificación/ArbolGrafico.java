
package Codificación;

  /**
 *
 * @author ploks
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import javax.swing.*;


public class ArbolGrafico extends JPanel 
{
    private ABBTactics miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 30, child2child = 30;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    public ArbolGrafico(ABBTactics miArbol) 
    {
          this.miArbol = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }


    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         DatoTactics root = this.miArbol.getRaiz();
         if (root != null) 
         {
             calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    

    private Dimension calcularTamañoSubarbol(DatoTactics n) 
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension ld = calcularTamañoSubarbol(n.getIzq());
          Dimension rd = calcularTamañoSubarbol(n.getDer());
          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }
    

    private void calcularPosicion(DatoTactics n, int left, int right, int top) 
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.getIzq());
      if (ld == null) 
          ld = empty;
      
      Dimension rd = (Dimension) subtreeSizes.get(n.getDer());
      if (rd == null) 
          rd = empty;
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fm.stringWidth(n.getDato()+"");
      
      Ellipse2D.Float r = new Ellipse2D.Float(center - width/2 - 3, top, width + 15, fm.getHeight()+15);
      
      posicionNodos.put(n,r);
      
      calcularPosicion(n.getIzq(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.getDer(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
    

 private void dibujarArbol(Graphics2D g, DatoTactics n, int puntox, int puntoy, int yoffs) 
    {
     if (n == null) 
         return;
     
    //Nodos en forma Elipse (Circunferencia) 
     Ellipse2D.Float r = (Ellipse2D.Float) posicionNodos.get(n);
     
     g.setColor(Color.white);
     g.draw(r);
     
     /*Área de la Elipse 
    
    Ellipse2D.Float r2 = r; 
    Graphics2D g2 = g;
    
    int w = getSize().width;
    int h = getSize().height;
    
    g2.setClip(r2);

    g2.setColor(Color.white);
    g2.fillRect(0, 0, w, h);
    */
    //Datos dentro de los Nodos 
     
     g.setColor(Color.white);
     g.drawString(n.getDato()+ "", r.x + 6, r.y + yoffs+5);
     
     //Aristas del Árbol

     g.setColor(Color.white);
     if (puntox != Integer.MAX_VALUE)
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), (int) r.y);
    
     dibujarArbol(g, n.getIzq(), (int)(r.x + r.width/2), (int) (r.y + r.height), yoffs);
     dibujarArbol(g, n.getDer(), (int)(r.x + r.width/2), (int) (r.y + r.height), yoffs);
     
   }
    

   public void paint(Graphics g) 
   {
         super.paint(g);
         fm = g.getFontMetrics();

         if (dirty) 
         {
           calcularPosiciones();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.setStroke(new BasicStroke(2.0f));
         g2d.setPaint(Color.white);
         g2d.setBackground(Color.yellow);
         g2d.translate(getWidth() / 2, parent2child);
         dibujarArbol(g2d, this.miArbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fm.getLeading() + fm.getAscent());
         fm = null;
   }
 }





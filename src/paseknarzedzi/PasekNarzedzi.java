package paseknarzedzi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasekNarzedzi extends JFrame{

    public PasekNarzedzi()
    {
        initComponents();
    }
    public void initComponents()
    {
        //<a target="_blank" href="https://icons8.com/icon/Zyo5wDjgJxRW/red-circle">Red Circle</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
        this.setTitle("Pasek Narzedzi");
        this.setBounds(300, 320, 400, 200);
        pasekNarzedzi.add(new KolorowyButton(new ActionColor("zmieniam kolor na zielony", new ImageIcon("zielony.png"), Color.GREEN)));
        pasekNarzedzi.add(new KolorowyButton(new ActionColor("zmieniam kolor na niebieski", new ImageIcon("niebieski.png"), Color.BLUE)));
        pasekNarzedzi.add(new KolorowyButton(new ActionColor("zmieniam kolor na czerwony", new ImageIcon("czerwony.png"), Color.RED)));
        pasekNarzedzi.add(button);
        
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                aktywny = null;
            }
        });
        
        panel.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e){
                if(aktywny != null)
                    panel.setBackground((Color) aktywny.getAction().getValue("kolor"));
            }
        });
        
        this.getContentPane().setLayout(new GridLayout(2, 1));
        this.getContentPane().add(pasekNarzedzi);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(3);
    }
    
    private JToolBar pasekNarzedzi = new JToolBar("Nazwa Nowej Ramki");
    private JButton button = new JButton("Wyłącz malowanie");
    private JPanel panel = new JPanel();
    private KolorowyButton aktywny = null;
    
    private class ActionColor extends AbstractAction
    {
        public ActionColor(String toolTip, Icon icon, Color colour)
        {
            this.putValue(Action.SHORT_DESCRIPTION, toolTip);
            this.putValue(Action.SMALL_ICON, icon);
            this.putValue("kolor", colour);
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            aktywny = (KolorowyButton) e.getSource();
        }
    }
    
    private class KolorowyButton extends JButton
    {
        public KolorowyButton(final ActionColor actionColor) 
        {
            super(actionColor);
            
            this.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    for(int i = 0; i < pasekNarzedzi.getComponentCount(); i++)
                    {
                        if(pasekNarzedzi.getComponent(i) instanceof KolorowyButton)
                        {
                            KolorowyButton tmp = (KolorowyButton)pasekNarzedzi.getComponent(i);
                            tmp.setBackground(Color.WHITE);
                            tmp.setZaznaczony(false);
                            panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("rozlej.png").getImage(), new Point(0, 0), "nazwa kursora"));
                            
                        }
                    }
                    
                    ten.setBackground((Color) actionColor.getValue("kolor"));
                    ten.setZaznaczony(true);
                }
            });
        }
        public void setZaznaczony(boolean zaznacz)
        {
            this.zaznaczony = zaznacz;
        }
        KolorowyButton ten = this;
        boolean zaznaczony = false;
    }
    
    public static void main(String[] args) {
        new PasekNarzedzi().setVisible(true);
    }
    
}

package src.main;

import java.awt.*;
import javax.swing.*;

import src.render.*;   // import members from src/render/Balls.java

public class Main {

    public static void main(String[] args) {
        /* Calling Constructor */
        new Main();
    }

    public Main() {
        /* https://stackoverflow.com/questions/6567870/what-does-swingutilities-invokelater-do */
        EventQueue.invokeLater(new Runnable() {  
        /* Runnable with anonymous class*/
            @Override
            public void run() {
                try 
                {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                    /* https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html */
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                JFrame jframe = new JFrame("BALLiSIM");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setLayout(new BorderLayout());  // construct new boderlayout with no gaps between components
                Balls balls = new Balls();
                jframe.add(balls);           // adding elements
                jframe.setSize(500, 500);
                jframe.setVisible(true);
                jframe.setResizable(false);

                new Thread(new BounceEngine(balls)).start();

            }
        });
    }
}

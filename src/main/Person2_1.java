/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.Label;

/**
 *
 * @author MD Abul Kashem
 */
/**
   * This class is used for ObservableList array list object. This is
   * 
   */
public class Person2_1 
{
    private Label proccess,arrival,burn,turn,wait,respons;
    /**
     * 
     * @param st initialise textField text and label name 
     */
    Person2_1(String p,int a,int b,int r,int w,int t)
    {
       proccess =new Label("                  "+p);
       arrival=new Label("                         "+a);
       burn=new Label("                             "+b);
            turn=new Label("                         "+t);
                 wait=new Label("                       "+w);
                      respons=new Label("                   "+r);
                     
    }
    /*
    *@return Lable name Objet.
    */
    public Label getProccess()
    {
        return proccess;
    }
     /*
    *@return Lable name Objet.
    */
    public Label getBurn()
    {
        return burn;
    }
    public Label getArrival()
    {
        return arrival;
    }
     public Label getRespons()
    {
        return respons;
    }
      public Label getTurn()
    {
        return turn;
    }
       public Label getWait()
    {
        return wait;
    }
}

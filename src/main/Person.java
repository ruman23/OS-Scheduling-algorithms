/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.TextField;

/**
 *
 * @author MD Abul Kashem
 */
/**
   * This class is used for ObservableList array list object. This is
   * 
   */
public class Person 
{
    private TextField proccess,arrival,burn;
    /**
     * 
     * @param st initialise textField text and label name 
     */
    Person()
    {
       proccess =new TextField();
       arrival=new TextField();
       burn=new TextField();
    }
    /*
    *@return Lable name Objet.
    */
    public TextField getProccess()
    {
        return proccess;
    }
     /*
    *@return Lable name Objet.
    */
    public TextField getBurn()
    {
        return burn;
    }
    public TextField getArrival()
    {
        return arrival;
    }
}

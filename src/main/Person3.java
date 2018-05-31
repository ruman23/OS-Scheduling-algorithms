/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author MD Abul Kashem
 */
public class Person3 
{
   GridPane grid;
    Person3(String st,int n)
    {
        grid=new GridPane();
        String st2[]=st.split(",");
        grid.setVgap(10);
        grid.setHgap(10);
        int a=1700/(st2.length*n+10);
        int last=1;
        for(int i=0;i<st2.length;i++)
        {
            Label l=new Label();
            String st3[]=st2[i].split("->");
            int j=Integer.parseInt(st3[1]);
            j=j-last;
            last=Integer.parseInt(st3[1]);
            //System.out.println(st3[1]);
            j=a*j;
            if(j<45){
            j=45;
            }
            l.setPrefSize(j,50);
            l.setAlignment(Pos.CENTER);
            if(i%3==0){
            l.setStyle("-fx-text-fill: black;-fx-background-color:red");
            }
            else if(i%3==1)
            {
              l.setStyle("-fx-text-fill: black;-fx-background-color:green");   
            }
            else
            {
                 l.setStyle("-fx-text-fill: black;-fx-background-color:blue");
            }
            l.setFont(Font.font ("Verdana",10));
           
            l.setText(st2[i]);
            grid.add(l, i, 0);       
       
        }
    }
    public GridPane getGrid()
    {
        return grid;
    }
    
}

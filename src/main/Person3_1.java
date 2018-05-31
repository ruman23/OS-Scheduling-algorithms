/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author MD Abul Kashem
 */
public class Person3_1 
{
   GridPane grid;
    Person3_1(String st,String time)
    {
        grid=new GridPane();
        String st2[]=st.split(",");
        String st4[]=time.split(",");
        grid.setVgap(10);
        grid.setHgap(10);
        int a=st2.length,b,k=0;
         //k=0;//
         //Integer.parseInt(st2[a-1]);
       // a=k/a;
       String st3[]=st2[a-1].split(" ");
       //System.out.println(st3[2]);
        if(a>10){
            a=a+10;
        }
        else
        {
            a=a+1;
        }
        b=a;
        k=Integer.parseInt(st3[2]);
        a=(2000)/(k+10);
        for(int i=0;i<st2.length;i++)
        {
            Label l=new Label();
            int m=Integer.parseInt(st4[i]);
             m=a*m/2;
           //System.out.println(m+" "+a+" ");
            l.setPrefSize(m+25,60);
           
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
            if(m>70)
            l.setFont(Font.font ("Verdana",18));
            else  l.setFont(Font.font ("Verdana",8));
            
            l.setText(st2[i]);
            grid.add(l, i, 0);        
        }
    }
    public GridPane getGrid()
    {
        return grid;
    }
    
}

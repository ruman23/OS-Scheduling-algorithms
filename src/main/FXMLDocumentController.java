/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static main.FCFS.pathFinder;

/**
 *
 * @author MD Abul Kashem
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button importFile;
    @FXML
    private Button input;
    private Stage stage;
    private Parent root;
    @FXML
    private ComboBox type; 
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException 
    {
        Button btn=(Button) event.getSource();
              if(type.getSelectionModel().getSelectedItem().toString().equals("Round Robin"))
              {
             
                 stage=(Stage)btn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/main/Input.fxml"));
               } 
              else if(type.getSelectionModel().getSelectedItem().toString().equals("Preemptive FCFS")) 
              {
                  stage=(Stage)btn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/main/Input1.fxml")); 
              }
              else 
              {
                   stage=(Stage)btn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/scan_scheduling/LineViewChart.fxml"));
              }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().add("Preemptive FCFS");
          type.getItems().add("Round Robin");
          type.getItems().add("Scan Disk Scheduling");
           type.getSelectionModel().select("Preemptive FCFS");
       
    }
    
    @FXML
    private void handleImport(ActionEvent event) throws FileNotFoundException, IOException
    {
        
        if(type.getSelectionModel().getSelectedItem().toString().equals("Preemptive FCFS"))
        {
              Stage stage=new Stage();
              FileChooser fileChooser = new FileChooser();
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
              fileChooser.getExtensionFilters().add(extFilter);
              File file = fileChooser.showOpenDialog(stage);
              if(file!=null)
              {
                  BufferedReader br=new BufferedReader(new FileReader(file));
                  BufferedWriter br1=new BufferedWriter(new FileWriter(pathFinder()+"/src/main/FCFS.csv"));
                  String st="";
                  while((st=br.readLine())!=null)
                  {
                      br1.write(st);
                      br1.newLine();
                  }
                  br.close();
                  br1.close();                  
              }
               Button btn=(Button)event.getSource();
             stage=(Stage) importFile.getScene().getWindow();
             root = FXMLLoader.load(getClass().getResource("/main/Output1.fxml"));
             Scene scene=new Scene(root);
             stage.setScene(scene);
                stage.show();
        }
        else if(type.getSelectionModel().getSelectedItem().toString().equals("Round Robin"))
        {
           
                          Stage stage=new Stage();
              FileChooser fileChooser = new FileChooser();
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
              fileChooser.getExtensionFilters().add(extFilter);
              File file = fileChooser.showOpenDialog(stage);
              if(file!=null)
              {
                  BufferedReader br=new BufferedReader(new FileReader(file));
                  BufferedWriter br1=new BufferedWriter(new FileWriter(pathFinder()+"/src/main/Data.csv"));
                  String st="";
                  while((st=br.readLine())!=null)
                  {
                      br1.write(st);
                      br1.newLine();
                  }
                  br.close();
                  br1.close();                  
              }
               Button btn=(Button)event.getSource();
             stage=(Stage) importFile.getScene().getWindow();
             root = FXMLLoader.load(getClass().getResource("/main/Output.fxml"));
             Scene scene=new Scene(root);
             stage.setScene(scene);
                stage.show();
        }
        else
        {
            check=true;
                          Stage stage=new Stage();
              FileChooser fileChooser = new FileChooser();
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
              fileChooser.getExtensionFilters().add(extFilter);
              File file = fileChooser.showOpenDialog(stage);
              if(file!=null)
              {
                  BufferedReader br=new BufferedReader(new FileReader(file));
                  BufferedWriter br1=new BufferedWriter(new FileWriter(pathFinder()+"/src/scan_scheduling/Data.csv"));
                  String st="";
                  while((st=br.readLine())!=null)
                  {
                      br1.write(st);
                      br1.newLine();
                  }
                  br.close();
                  br1.close();                  
              }
               Button btn=(Button)event.getSource();
             stage=(Stage) importFile.getScene().getWindow();
             root = FXMLLoader.load(getClass().getResource("/scan_scheduling/LineViewChart.fxml"));
             Scene scene=new Scene(root);
             stage.setScene(scene);
                stage.show();
        }
            
        
    }
   public  static boolean check=false;
}

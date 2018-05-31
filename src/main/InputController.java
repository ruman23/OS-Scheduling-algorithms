/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static main.FCFS.pathFinder;

/**
 * FXML Controller class
 *
 * @author MD Abul Kashem
 */
public class InputController implements Initializable {

    @FXML
    private Label process;
    @FXML
    private Label arrival;
    @FXML
    private Label brust;
    @FXML
    private Label number_of_proccess;
    @FXML
    private Button add;
    @FXML
    private TextField number_of_unit;
    @FXML
    private TableColumn t1;
    @FXML
    private TableColumn t2;
    @FXML
    private TableColumn t3;
    @FXML
    private TableView table;
    @FXML
    private Button submit;
    @FXML
    private Label warning;
    @FXML
    private TextField q_time;
    @FXML
    private Label warning1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        process.setManaged(false);
        brust.setManaged(false);
        arrival.setManaged(false);
        process.setVisible(false);
        brust.setVisible(false);
        arrival.setVisible(false);
        submit.setManaged(false);
        submit.setVisible(false);
        warning.setText("");
                warning1.setText("");
          number_of_unit.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    if (newValue.intValue() > oldValue.intValue()||newValue.intValue() < oldValue.intValue()) 
                    {   
                         int len=number_of_unit.getText().length();
                        if (len >10) 
                            number_of_unit.setText(number_of_unit.getText().substring(0,10));
                          else
                            for(int i=0;i<len;i++)
                            {
            
                                if((number_of_unit.getText().charAt(i)<'0'||number_of_unit.getText().charAt(i)>'9')&&
                                        number_of_unit.getText().charAt(i)!='.')
                                        {
                                            warning.setText("Give digit input only");
                                            break;
                                        }
                                else warning.setText("");
                            }
                    }});
          q_time.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    if (newValue.intValue() > oldValue.intValue()||newValue.intValue() < oldValue.intValue()) 
                    {   
                         int len=q_time.getText().length();
                        if (len >10) 
                            q_time.setText(q_time.getText().substring(0,10));
                          else
                            for(int i=0;i<len;i++)
                            {
            
                                if((q_time.getText().charAt(i)<'0'||q_time.getText().charAt(i)>'9')&&
                                        q_time.getText().charAt(i)!='.')
                                        {
                                            warning1.setText("Give digit input only");
                                            break;
                                        }
                                else warning1.setText("");
                            }
                    }});
    }
        
    
    @FXML
    private void handleAdd(ActionEvent event)
    {
        if(number_of_unit.getText().length()>=1&&q_time.getText().length()==1)
        {
            managedButton();
            createTable();
        }
    }
    @FXML
    private Label q;
    private ObservableList<Person>Data = FXCollections.observableArrayList();
    private void  managedButton()
    {
        
        q.setVisible(false);
        q_time.setManaged(false);
        q_time.setVisible(false);
          process.setManaged(true);
        brust.setManaged(true);
        arrival.setManaged(true);
        process.setVisible(true);
        brust.setVisible(true);
        arrival.setVisible(true);
        number_of_proccess.setManaged(false);
        number_of_proccess.setVisible(false);
        number_of_unit.setManaged(false);
        number_of_unit.setVisible(false);
        add.setManaged(false);
        add.setVisible(false);
        submit.setManaged(true);
        submit.setVisible(true);
        warning.setVisible(false);
        table.setManaged(true);
        table.setVisible(true);
    }
    private Parent root;
    private Stage stage;
    @FXML
    private void submitHandle(ActionEvent event) throws IOException
    {
               saveTable();
             Button btn=(Button) event.getSource();
               stage=(Stage)btn.getScene().getWindow();
               root = FXMLLoader.load(getClass().getResource("/main/Output.fxml"));    
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    private void createTable()
    {
        for(int i=1;i<=Integer.parseInt(number_of_unit.getText().toString());i++)
            Data.add(new Person());
            table.setItems(Data);
            t1.setCellValueFactory(new PropertyValueFactory<>("proccess"));
            t2.setCellValueFactory(new PropertyValueFactory<>("arrival"));
            t3.setCellValueFactory(new PropertyValueFactory<>("burn"));
    }
    private void saveTable() throws IOException 
    {
         TextField pro,br,arr;
        String st[]=new String[table.getItems().size()];
            for(int i=0;i<table.getItems().size();i++)
             { 
                     pro =(TextField) t1.getCellData(i);
                 arr=(TextField)t2.getCellData(i);
                br=(TextField) t3.getCellData(i);
                if(arr.getText().length()==0)
                    arr.setText("0");
                if(br.getText().length()==0)
                    br.setText("0");
                  for(int j=0;j<arr.getText().length();j++)
                  {
                      if(arr.getText().charAt(j)<'0'&&arr.getText().charAt(i)>'9')
                      {
                          arr.setText("0");
                          break;
                      }
                          
                  }
                  for(int j=0;j<br.getText().length();j++)
                  {
                      if(br.getText().charAt(j)<'0'&&br.getText().charAt(i)>'9')
                      {
                          br.setText("0");
                          break;
                      }
                          
                  }
                  st[i]=pro.getText()+",";
                 st[i]+=arr.getText()+",";
                     st[i]+=br.getText();
             
             }
            
           BufferedWriter buffer =new BufferedWriter(new FileWriter(pathFinder()+"/src/main/Data.csv"));
           buffer.write(q_time.getText());
            buffer.newLine();
           for(int i=0;i<st.length;i++)
           {
               buffer.write(st[i]);
               buffer.newLine();
           }
           buffer.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scan_scheduling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static main.FCFS.pathFinder;
import static main.FXMLDocumentController.check;

/**
 * FXML Controller class
 *
 * @author MD Abul Kashem
 */
public class LineViewChartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label warning;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.print("df");
        if(check==false)
        {
          //System.out.print("df");
            check=false;
            initInput();
       
        warning.setText("");
          text.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    if (newValue.intValue() > oldValue.intValue()||newValue.intValue() < oldValue.intValue()) 
                    {   
                         int len=text.getText().length();
                     
                            for(int i=0;i<len;i++)
                            {
            
                                if((text.getText().charAt(i)<'0'||text.getText().charAt(i)>'9')&&
                                        text.getText().charAt(i)!='.'&&text.getText().charAt(i)!=' ')
                                        {
                                            warning.setText("Give digit input only");
                                            break;
                                        }
                                else warning.setText("");
                            }
                    }});
            
        }
        else
        {
            check=false;
            try {
                resetOutPut();
            } catch (IOException ex) {
                Logger.getLogger(LineViewChartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    private void initInput()
    {
        chart.setVisible(false);
        pageFault.setVisible(false);
        chart.setManaged(false);
         pageFault.setManaged(false);
                homeButton.setManaged(false);
                homeButton.setVisible(false);
    }
    @FXML
    private void ButtonAction(ActionEvent event) throws IOException
    {
        
                String st1=text.getText().toString();
                String st[]=st1.split(" ");
        
        int i=0;
            BufferedWriter br=new BufferedWriter(new FileWriter(pathFinder()+"/src/scan_scheduling/Data.csv"));
                  //BufferedWriter br1=new BufferedWriter(new FileWriter(pathFinder()+"/src/main/FCFS.csv"));
                  //String st="";
                  while(i<st.length)
                  {
                      br.write(st[i++]);
                      br.write(",");
                  }
                  br.close();
          
            resetOutPut();
           // workOnOutput();
        
    }
    private void resetOutPut() throws IOException
    {
        chart.setVisible(true);
        pageFault.setVisible(true);
        chart.setManaged(true);
                pageFault.setManaged(true);
                homeButton.setManaged(true);
                homeButton.setVisible(true);
                btn.setManaged(false);
                btn.setVisible(false);
                disk1.setManaged(false);
                disk1.setVisible(false);
                text.setManaged(false);
                text.setVisible(false);
                warning.setVisible(false);
                warning.setManaged(false);
                workOnOutput();
    }
    private Stage stage;
    private Parent root;
    @FXML
    private void homeAction(ActionEvent event) throws IOException
    {
         stage=(Stage)btn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/main/FXMLDocument.fxml"));
              
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    private void   workOnOutput() throws IOException
    {
        
         BufferedReader br=new BufferedReader(new FileReader(pathFinder()+"/src/scan_scheduling/Data.csv"));
                  //String st="";
                  /*while(.length)
                  {
                      br.write(st[i++]);
                      br.write(",");
                  }*/
                 // System.out.println("like");
                  chart.getData().clear();
                  String st1=br.readLine();
                  br.close();
                  
                  
                  
                  String st[]=st1.split(",");
                  
                  int arr[]=new int[st.length+1];
                  
                  try{for(int i=0;i<arr.length-1;i++)
                      arr[i]=Integer.parseInt(st[i]);}
                        catch(Exception e){}
                  /*for(int i=0;i<arr.length-1;i++)
                      System.out.print(arr[i]+" ");*/
                              
                  for(int i=1;i<arr.length-2;i++)
                      for(int j=i+1;j<arr.length-1;j++)
                      if(arr[i]>arr[j])
                      {
                          int temp=arr[i];
                          arr[i]=arr[j];
                          arr[j]=temp;
                      }
                          /* for(int i=0;i<arr.length-1;i++)
                      System.out.print(arr[i]+" ");*/
                       XYChart.Series series1=new XYChart.Series<>();
                  int base=arr[0],ans=base+(arr[st.length-1]-base),i;
                  //System.out.print(" "+base+" "+arr[st.length-1]);
                  if(base<(arr[st.length-1]-base))
                  {
                      //base position;
                      for( i=1;i<st.length-1;i++)
                          if(arr[i]>=base)
                          {
                              break;
                          }
                      int next=i-1;
                        //System.out.print(" "+base+" "+i);
                       series1.getData().add(new XYChart.Data(base,st.length+11));
                       int j=0;
                       i=next;
                      while(i>=1)
                      {
                          series1.getData().add(new XYChart.Data(arr[i--],st.length+10-j));
                          j++;
                          
                      }
                      series1.getData().add(new XYChart.Data(0,st.length+10-j));
                      for(i=next+1;i<st.length;i++,j++)
                          series1.getData().add(new XYChart.Data(arr[i],st.length+10-j));
                  }
                  else 
                  {
                      for( i=st.length-1;i>=1;i--)
                          if(arr[i]<=base)
                          {
                              break;
                          }
                      int next=i+1;
                       series1.getData().add(new XYChart.Data(base,st.length+11));
                       int j=0;
                       i=next;
                      while(i<=st.length-1)
                      {
                          series1.getData().add(new XYChart.Data(arr[i++],st.length+10-j));
                          j++;
                          
                      }
                      //series1.getData().add(new XYChart.Data(0,st.length+10-j));
                      for(i=next-1;i>=1;i--,j++)
                          series1.getData().add(new XYChart.Data(arr[i],st.length+10-j));
                      series1.getData().add(new XYChart.Data(0,st.length+10-j));
                  }
                 
                  
      //                    XYChart.Series series1=new XYChart.Series<>();
         
                        //series1.getData().add(new XYChart.Data(5,20));
                        //series1.getData().add(new XYChart.Data(50,70));
        chart.setTitle("Scan Scheduling Graph");
        //xAxis.setLabel("Country");       
        //yAxis.setLabel("Name");
          chart.getData().add(series1);
        //chart=bc;
    }
    @FXML
    private Button homeButton;
    @FXML
    private TextField text;
     @FXML
    private Button btn;
      @FXML
     private  LineChart<Number,Number> chart;//=new LineChart<>(x,y);

       @FXML
    private Label disk1;

       @FXML
       private Label pageFault;
       private final NumberAxis x=new NumberAxis(10,20,50);
       private final NumberAxis y=new NumberAxis(10,20,50);
    
}

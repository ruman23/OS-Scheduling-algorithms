/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static main.FCFS.pathFinder;

/**
 * FXML Controller class
 *
 * @author MD Abul Kashem
 */
public class Output1Controller implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    @FXML
    private GridPane pane;
    @FXML
    private Label gantt;
    @FXML
    private TableView table;
    @FXML
    private TableColumn  pro;
    @FXML
    private TableColumn  br;
    @FXML
    private TableColumn  ar;
    @FXML
    private TableColumn  tt;
    @FXML
    private TableColumn  rt;
    @FXML
    private TableColumn  wt;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            createTable();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createTable() throws FileNotFoundException, IOException
    {
       
        String st[]=new String[inputLen()+1];
        BufferedReader buffer=new BufferedReader(new FileReader(pathFinder()+"/src/main/FCFS.csv"));
        
        int i=0,j;
        while((st[i]=buffer.readLine())!=null)
        {
            i++;
        }
        buffer.close();
        Info1 info[]=new Info1[st.length+1];
      
        for( i=0;i<st.length-1;i++)
        {
            String in[]=st[i].split(",");
            int a,b;
            a=b=0;
            try{
            a=Integer.parseInt(in[1]);
            try{
            b=Integer.parseInt(in[2]);
            }
            catch(Exception f){
            }
            }
            catch(Exception e)
            {              
            }
            info[i]=new Info1(in[0],a,b);
            st[i]=in[0];
        }
        for(i=0;i<st.length-1;i++)
            for(j=i+1;j<st.length-1;j++)
            {
                if(info[i].arrival>info[j].arrival)
                {
                    Info1 a;
                    a=info[i];
                    info[i]=info[j];
                    info[j]=a;
                }
            }
        int prev=info[0].arrival,a=0,b=0,c=0;
        int loop=1;
        String gantt1="",gantt2="";
        
        
        for(i=0;i<st.length-1;i++)
        {
            if(prev>info[i].arrival){
                info[i].respons=(prev-info[i].arrival);
            }
            else info[i].respons=0;
            a+=info[i].respons;
            if(prev>info[i].arrival)
            info[i].wait=(prev-info[i].arrival);
            else info[i].wait=0;
            b+=info[i].wait;
            gantt2+=info[i].burn+",";
            info[i].turnaround=prev+info[i].burn;
            c+=info[i].turnaround;
            prev=info[i].turnaround;
            gantt1+=info[i].process+" -- "+info[i].turnaround+",";
        }
        Data2.add(new Person3_1(gantt1,gantt2));
        gantttable.setItems(Data2);
      
           ganttrow.setCellValueFactory(new PropertyValueFactory<>("grid"));
        int len=st.length-1;
        double k,l,m;
        k=(double)a/len;
        l=(double)b/len;
        m=(double)c/len;
        ra.setText("Average Respons Time = "+k);
        wa.setText("Average Wating Time = "+l);
                ta.setText("AVG TurnAround Time = "+m);
        for(i=0;i<st.length-1;i++)
        {
            for(j=0;j<st.length-1;j++)
            {
               
                    if(st[i].equals(info[j].process)){                  
                Data.add(new Person2_1(info[j].process,info[j].arrival,info[j].burn,info[j].respons,info[j].wait,info[j].turnaround));}
            }
        }
        table.setItems(Data);
         pro.setCellValueFactory(new PropertyValueFactory<>("proccess"));
            ar.setCellValueFactory(new PropertyValueFactory<>("arrival"));
            br.setCellValueFactory(new PropertyValueFactory<>("burn"));
             rt.setCellValueFactory(new PropertyValueFactory<>("respons"));
            wt.setCellValueFactory(new PropertyValueFactory<>("wait"));
              tt.setCellValueFactory(new PropertyValueFactory<>("turn"));
            
    }
    private ObservableList<Person3_1>Data2 = FXCollections.observableArrayList();
    private Stage stage;
    private Parent root;
    @FXML
    private void handleAction(ActionEvent event) throws IOException
    {
             Button btn=(Button)event.getSource();
             stage=(Stage) btn.getScene().getWindow();
             root = FXMLLoader.load(getClass().getResource("/main/FXMLDocument.fxml"));
             Scene scene=new Scene(root);
             stage.setScene(scene);
                stage.show();
    }
    @FXML
    private Button home;
    @FXML
    private TableView gantttable;
    @FXML
    private TableColumn ganttrow;
    @FXML
    private Label ta;
        @FXML
    private Label ra;
            @FXML
    private Label wa;
     private ObservableList<Person2_1>Data = FXCollections.observableArrayList();

    private int inputLen() throws FileNotFoundException, IOException
    {
        BufferedReader buffer=new BufferedReader(new FileReader(pathFinder()+"/src/main/FCFS.csv"));
        String s;
        int line=0;
        while((s=buffer.readLine())!=null)
        {
            line++;
        }
        buffer.close();
        return line;
    }
}
class Info1
{
    int arrival,burn,respons,wait,turnaround;
      String process;
    Info1(String process,int ar,int b)
    {
        arrival=ar;
        burn=b;
        this.process=process;
    }
} 
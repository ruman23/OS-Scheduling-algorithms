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
import java.util.LinkedList;
import java.util.Queue;
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
public class OutputController implements Initializable {

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
       
        String st[]=new String[inputLen()-1],sk;
        BufferedReader buffer=new BufferedReader(new FileReader(pathFinder()+"/src/main/Data.csv"));
        int i=0,j,q_time=2;
        try{q_time=Integer.parseInt(buffer.readLine());}
        catch(Exception e){q_time=2;};
        while((sk=buffer.readLine())!=null)
        {
            st[i++]=sk;
        }
        
        buffer.close();
        Info info[]=new Info[st.length];
        for( i=0;i<st.length;i++)
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
            info[i]=new Info(in[0],a,b);
            st[i]=in[0];
        }
      //  System.out.print(info.length);
        for(i=0;i<info.length;i++)
            for(j=i+1;j<info.length;j++)
            {
                if(info[i].arrival>info[j].arrival)
                {
                    Info a;
                    a=info[i];
                    info[i]=info[j];
                    info[j]=a;
                }
            }
        String gantt1="",gantt2="";
        Queue<Integer>queue=new LinkedList<>();
        queue.add(0);
        int len=st.length;
        i=0;
        int in=0,turnAroundTime=info[0].arrival;
        while(queue.size()!=0)
        {
            in=queue.peek();
            if(info[in].first==-1)
            {
                if(info[in].arrival>=turnAroundTime)
       
                    info[in].respons=0;
                
                else
                
                    info[in].respons=(turnAroundTime-info[in].arrival);
                
                info[in].first=0;
            }
            if(info[in].remain>q_time)
            {
                turnAroundTime+=q_time;
                queue.remove();
                gantt1+=info[in].process+"->"+turnAroundTime+",";
                info[in].remain-=q_time;
                for(i=in+1;i<len;i++)
                {
                    if(info[i].remain>0&&turnAroundTime>=info[i].arrival&&info[i].check==false)
                    {
                        info[i].check=true;
                        queue.add(i);
                    }
                }
                queue.add(in);
            }
            else
            {
                turnAroundTime+=info[in].remain;
                info[in].remain=0;
                info[in].turnaround=turnAroundTime-info[in].arrival;
                info[in].wait=info[in].turnaround-info[in].burn;
                queue.remove();
                gantt1+=info[in].process+"->"+turnAroundTime+",";
                for(i=in+1;i<len;i++)
                {
                   // System.out.print(info[i].arrival);
                    if(info[i].remain>0&&turnAroundTime>=info[i].arrival&&info[i].check==false)
                    {
                        info[i].check=true;
                        queue.add(i);
                    }
                }
            }
            if(queue.size()==0)
            {
                 for(i=in+1;i<len;i++)
                {
                   // System.out.print(info[i].arrival);
                    if(info[i].remain>0&&info[i].arrival>=turnAroundTime&&info[i].check==false)
                    {
                        info[i].check=true;
                        queue.add(i);
                    }
                }
            }
            if(queue.size()==1)
            {
                in=queue.peek();
                turnAroundTime+=info[in].remain;
                queue.remove();
                gantt1+=info[in].process+"->"+turnAroundTime+",";
                info[in].turnaround=turnAroundTime-info[in].arrival;
            }
        }
        quantum.setText("Quantum Time   "+q_time);
        //System.out.print(info[in].process+" "+info[in].turnaround);
        Data2.add(new Person3(gantt1,q_time));
        gantttable.setItems(Data2);
        ganttrow.setCellValueFactory(new PropertyValueFactory<>("grid"));
        double k=0,l=0,m=0;
        for(i=0;i<st.length;i++)
        {
            for(j=0;j<st.length;j++)
            {
                    if(st[i].equals(info[j].process)){
                        k+=(info[j].respons);
                        l+=info[j].wait;
                        m+=info[j].turnaround;
                Data.add(new Person2(info[j].process,info[j].arrival,info[j].burn,info[j].respons,info[j].wait,info[j].turnaround));}
            }
        }
        k=(double)k/info.length;
        l=(double)l/info.length;
        m=(double)m/info.length;
        ra.setText("Average Respons Time = "+k);
        wa.setText("Average Wating Time = "+l);
        ta.setText("AVG TurnAround Time = "+m);
        table.setItems(Data);
         pro.setCellValueFactory(new PropertyValueFactory<>("proccess"));
            ar.setCellValueFactory(new PropertyValueFactory<>("arrival"));
            br.setCellValueFactory(new PropertyValueFactory<>("burn"));
             rt.setCellValueFactory(new PropertyValueFactory<>("respons"));
            wt.setCellValueFactory(new PropertyValueFactory<>("wait"));
              tt.setCellValueFactory(new PropertyValueFactory<>("turn"));
            
    }
    @FXML
    private Label quantum;
    private ObservableList<Person3>Data2 = FXCollections.observableArrayList();
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
     private ObservableList<Person2>Data = FXCollections.observableArrayList();

    private int inputLen() throws FileNotFoundException, IOException
    {
        BufferedReader buffer=new BufferedReader(new FileReader(pathFinder()+"/src/main/Data.csv"));
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
class Info
{
    int arrival,burn,respons,wait,turnaround,remain,first;
    boolean check=false;
      String process;
    Info(String process,int ar,int b)
    {
        remain=b;
        arrival=ar;
        burn=b;
        this.process=process;
        first=-1;
    }
} 
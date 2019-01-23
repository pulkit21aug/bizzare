/****************************************************************************
File            :           Bizare.java
@Author          :           Pulkit Saxena
Description      : offshore create folder tool-BIZARE
@version 1.0
*****************************************************************************/

//packages imported

import java.text.SimpleDateFormat;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;

/*************************************************************************
Class        : path
Description  :gets the path for directory
**************************************************************************/
class path

{
 dirFrame f;
 public path(dirFrame f){
 this.f = f;
 }

 public String sPath;

/*********************************************************************
Function         : getPath
Decription       : connects to the file database.properties and gets
                   the path for making the folder
Parameter         : none
Return            :void
*********************************************************************/

public void getPath()

{

try
{

//connection to file database.properties



Properties props =new Properties();
FileInputStream in = new FileInputStream("database.properties");
props.load(in);
in.close();


//getting the path from file database.properties
String sMainPath = props.getProperty("create.path");
String subPath=props.getProperty("create.subpath");

if(sMainPath!=null)
System.out.println("PATH present");
if(subPath!=null)
System.out.println("subpath present");

//formattig of date by formatters form and form1

SimpleDateFormat form1=new SimpleDateFormat("MMyyyy");
SimpleDateFormat form=new SimpleDateFormat("MMddyy");


//Date instance created
Date dt=new Date();

//formating date instance dt,storing in variables sMain and sSub
String sMain=form1.format(dt);
String sSub=form.format(dt);

//getting full path and name of folders to be created
    String sMainFolderName =sMainPath+sMain;
   System.out.println(sMainFolderName);
    String sSubFolderName=subPath+sSub;
   System.out.println(sSubFolderName);

  //creation of folders
    File fMainFolder = new File(sMainFolderName);
    if(fMainFolder.exists())
       {
             File fSubFolder = new File(sMainFolderName,sSubFolderName);
                
              if(fSubFolder.exists())
              {             
                  JOptionPane.showMessageDialog(f,"directory exists"); 
                    System.exit(0);
                   }

               else{   
       
                fSubFolder.mkdir();
               }
           }

    else
           {
             //creation of main folder
             fMainFolder.mkdir();
              File fSubFolder = new File(sMainFolderName,sSubFolderName);
                       
              
           //creation of subfolder
             fSubFolder.mkdir();
             
      }



}
catch(Exception  e)
{System.out.println(e);}
//catch exception

//sPath = "jai mata di";


}

}

/****************************************************************
Class           : dirFrame
Description     : for creating main form
****************************************************************/

class dirFrame extends JFrame

{
  JLabel lab1;
  JButton bt;
  JPanel panel;
  JTextField text1;



 public dirFrame(String a)
 {
   super(a);
   setLayout(new FlowLayout());
   panel = new JPanel();
   add(panel);

   //button creation on form panel
   bt = new JButton("create offshore folder");

   panel.add(bt);


   MyWindowAdapter4 adapter = new MyWindowAdapter4(this);
   this.addWindowListener(adapter);
   Handler4 handler= new Handler4(this);
   bt.addActionListener(handler);




}



}


/*****************************************************************
Class           :   MyWindoAdapter
Description     :For Window Event
******************************************************************/



class MyWindowAdapter4 extends WindowAdapter
{
  dirFrame f;

    public MyWindowAdapter4(dirFrame f)
   {

     this.f = f;
    }

    public void windowClosing(WindowEvent we)
 {
     f.setVisible(false);
     System.exit(0);
  }
}

/***********************************************************************
Class              : Handler4
Description        :for implemention action event on Button bt to create
                     the object of class path
************************************************************************/


class Handler4 implements ActionListener
{
   dirFrame f;
  public Handler4(dirFrame f)
    {
    this.f = f;
     }

 public void actionPerformed(ActionEvent ae)

  //button event generation to create an object of class path
  {
    if(ae.getSource()==f.bt)

      {


  path p=new path(f);

  p.getPath();

     JOptionPane.showMessageDialog(f,"directory created");
     f.text1.setText("");
      }

    }

}

/************************************************************************
Class                 :bizare
Description            :Main class
*************************************************************************/


public class bizare
{

 public static void main(String a[])
{
 dirFrame f = new dirFrame("BIZAERE - directory manager");
 f.setSize(400,100);
 f.setVisible(true);
}

}
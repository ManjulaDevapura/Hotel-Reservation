
package hotel.mgmt;

import hotel.mgmt.Connect;
import hotel.mgmt.UpdateLogin;
import hotel.mgmt.VerifyUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author manjula
 */
public class Menu extends javax.swing.JFrame {

    java.util.Date tdt;
    JLabel pic,pics;
    javax.swing.Timer tm,tm1;
    int x = 0,y=0,data=0;
    int bli=0;
    
    Connection conn=null;
    ResultSet rs = null,rs1=null,rs11=null,rs111=null; 
    PreparedStatement pst=null,pst11=null,pst1=null;
    Statement smt,smt1;
    
        String[] list = {
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/0.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/1.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/2.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/3.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/4.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/5.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/6.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/7.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/8.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/9.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/10.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/11.jpg",
"C:/Users/Manjula Devapura/Documents/NetBeansProjects/20170717/Hotel Management/pics/12.jpg"
                   };
    
    
    public Menu() {
        super("Java SlideShow");
        initComponents();
         conn=Connect.ConnecrDb();
        refreash();
        
        ScheduledThreadPoolExecutor exec= new ScheduledThreadPoolExecutor(1);
       exec.scheduleAtFixedRate(new Runnable(){public void run(){refreash();}},0,100,TimeUnit.MILLISECONDS);
       
       ScheduledThreadPoolExecutor exec1= new ScheduledThreadPoolExecutor(1);
       exec1.scheduleAtFixedRate(new Runnable(){public void run(){SetImageSize(x);}},0,1,TimeUnit.SECONDS);
       
       ScheduledThreadPoolExecutor exec2= new ScheduledThreadPoolExecutor(1);
       exec1.scheduleAtFixedRate(new Runnable(){public void run(){omegaBlink();}},0,1,TimeUnit.SECONDS);
       
       
       pic =jLabel1;
       SetImageSize(11);
        tm = new javax.swing.Timer(3000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 					
            }
        });
        add(pic);
        tm.start();
        setLayout(null);
        //setSize(800, 400);
        getContentPane().setBackground(Color.decode("#3399FF"));
        setLocationRelativeTo(null);
    //    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);bdb67b
        setVisible(true);
        
        
        
        
    }
 
        public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
      
    }

public void refreash()
{ 
    SimpleDateFormat dateFormat =new SimpleDateFormat("EEE, d MMM yyyy");
    SimpleDateFormat timeFormat =new SimpleDateFormat("h : mm : ss  a");
          
    Calendar currentCalendar = Calendar.getInstance();
    Date currentTime = currentCalendar.getTime();
    jLabel3.setText(dateFormat.format(currentTime));
    jLabel4.setText(timeFormat.format(currentTime));
     
             try
             {
                 String query = "SELECT Stf_No FROM verify";
             
                    smt=conn.createStatement();
                    rs111=smt.executeQuery(query);
                    if(rs111.next())
                         {data=0;}
             }       catch(Exception ex)
                {
        JOptionPane.showMessageDialog(null , ex);
                }   
             
    if(data==0)
    {
        verdata();
    }       
}
public void verdata()
{
           
    try{          
        int flag=1;   
        y++; 
          String sql="select * from staff";
          pst=conn.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next())
          {
          String sql11="select * from verify";
          pst11=conn.prepareStatement(sql11);
          rs11=pst11.executeQuery();
          while(rs11.next())
          {
              if(rs11.getString(3).equals(rs.getString(1))==true)
              { flag=2; }
          }
          if(flag==2){ 
                        jButton3.show();
    if(y==1){jButton3.setBackground(Color.red);}			
    else if(y==2){jButton3.setBackground(Color.blue);}		
    else if(y==3){jButton3.setBackground(Color.white);y=0;}		
    else{jButton3.setBackground(Color.white);jButton3.setForeground(Color.red);y=0;} 
   }
   
         }
          if(flag==1)
                    {  
                        jButton3.setVisible(false);
                        data=1;
                    }
                        
    }
                catch(Exception ex)
                {
    //    JOptionPane.showMessageDialog(null , ex); 
                } 
}
public void omegaBlink()
{
       if(bli==0)
       {
           jLabel2.setForeground(Color.red);
       bli++;//1
       }
       else if(bli==1)
       {
           jLabel2.setForeground(Color.blue);
           bli++;
       }
       else if(bli==2)
       {
           //jLabel2.setForeground(Color.green);
           bli=0;
       }

}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        menustaff = new javax.swing.JButton();
        menukitchen = new javax.swing.JButton();
        menucustomer = new javax.swing.JButton();
        menuroom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Main  Menu");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel/mgmt/8.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Arabic Typesetting", 3, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Hotel  Omega  Inn");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("jLabel4");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("No:06, Galle Rd,Wellawaththa . Colombo 06 . Sri Lanka");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("0777-623773");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("0777-142431");

        jButton1.setBackground(new java.awt.Color(0, 0, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Profile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("HELP");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 0));
        jButton3.setText("New User");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 204));
        jButton4.setText("G Mail");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton1))
                    .addComponent(jButton3)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));

        menustaff.setBackground(new java.awt.Color(204, 204, 204));
        menustaff.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        menustaff.setForeground(new java.awt.Color(255, 0, 255));
        menustaff.setText("Staff");
        menustaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menustaffActionPerformed(evt);
            }
        });

        menukitchen.setBackground(new java.awt.Color(204, 204, 204));
        menukitchen.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        menukitchen.setForeground(new java.awt.Color(255, 0, 255));
        menukitchen.setText("Payments");
        menukitchen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menukitchenActionPerformed(evt);
            }
        });

        menucustomer.setBackground(new java.awt.Color(204, 204, 204));
        menucustomer.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        menucustomer.setForeground(new java.awt.Color(255, 0, 255));
        menucustomer.setText("Customers");
        menucustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menucustomerActionPerformed(evt);
            }
        });

        menuroom.setBackground(new java.awt.Color(204, 204, 204));
        menuroom.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        menuroom.setForeground(new java.awt.Color(255, 0, 255));
        menuroom.setText("Rooms");
        menuroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuroomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menukitchen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menustaff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menucustomer)
                            .addComponent(menuroom, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(menucustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(menuroom, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menustaff, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(menukitchen, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuroomActionPerformed
        
             RoomSub rs = new RoomSub();
             rs.setVisible(true);        
    }//GEN-LAST:event_menuroomActionPerformed

    private void menucustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menucustomerActionPerformed
       
             CustomerSub cs = new CustomerSub();
             cs.setVisible(true);
    }//GEN-LAST:event_menucustomerActionPerformed

    private void menustaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menustaffActionPerformed
        
             StaffSub ss = new StaffSub();
             ss.setVisible(true);
    }//GEN-LAST:event_menustaffActionPerformed

    private void menukitchenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menukitchenActionPerformed
        
             PaymentSub k = new PaymentSub();
             k.setVisible(true);
    }//GEN-LAST:event_menukitchenActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        
        refreash();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       
        refreash();
    }//GEN-LAST:event_formMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
       UpdateLogin up = new UpdateLogin();
       up.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try{
//        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Manjula Devapura\\Documents\\NetBeansProjects\\Hotel Management\\hotelomegainnsl@gmail.com - Gmail.html");
        
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Manjula Devapura\\Documents\\NetBeansProjects\\20170717\\Hotel Management\\User Manual.docx");
        }catch(Exception e)
        {
         JOptionPane.showMessageDialog(null , e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
           
                try{                   
                    String queryj = "SELECT * FROM verify";
                    smt=conn.createStatement();
                    rs=smt.executeQuery(queryj);
                    if(rs.next())
                    {
                         VerifyUser vu = new VerifyUser();
       vu.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(null , "No more New Users");
                }
    catch(Exception e){JOptionPane.showMessageDialog(null , "No more New Users");}
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\Manjula Devapura\\Documents\\NetBeansProjects\\20170717\\Hotel Management\\hotelomegainnsl@gmail.com - Gmail.html");
        }catch(Exception e)
        {
         JOptionPane.showMessageDialog(null , e);
        }
    
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton menucustomer;
    private javax.swing.JButton menukitchen;
    private javax.swing.JButton menuroom;
    private javax.swing.JButton menustaff;
    // End of variables declaration//GEN-END:variables
}

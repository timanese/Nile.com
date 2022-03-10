// Name: Tim Yang
// Course: CNT 4714-Spring 2022
// Assignment title: Project 1- Event-driven Enterprise Simulation
// Date: Sunday january 30, 2022

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.TransferHandler.TransferSupport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;

public class FinishOrderSceneController implements Initializable {

    @FXML
    private Label dateLabel;

    @FXML
    private AnchorPane finishOrderPane;

    @FXML
    private TextArea finishOrderText;

    @FXML
    private Label headerLabel;

    @FXML
    private Label numItemLabel;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label totalLabel;

    @FXML Label taxAmtLabel;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    void completeOrderClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        finishOrderPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        finishOrderText.setEditable(false);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");  
        System.out.println(dtf.format(now));  
        dateLabel.setText("Date: " +dtf.format(now));
        numItemLabel.setText("Number of line items: " + Orderdata.totalNumItems);
        subtotalLabel.setText("Order subtotal:" + df.format(Orderdata.subtotal));
        double total = (Orderdata.subtotal * 0.06) + Orderdata.subtotal;
        totalLabel.setText("Order total:" + df.format(total));
        taxAmtLabel.setText("Tax Amount: 6%");
        
        int j = 1;
        if (!Orderdata.invoice.isEmpty()) {
            for (String I : Orderdata.invoice) {
                finishOrderText.appendText(j++ +". "+ I + "\n");
            }
        }
        String time = (dtf.format(now)).toString();
        String transactionNum = (dtf2.format(now)).toString();
        String transID = transactionNum.replaceAll(" ", "");
        try(FileWriter fw = new FileWriter("transaction.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            for (String I : Orderdata.invoice) {
                out.println(transID +", " + I + ", " + time);
            }
        } catch (IOException e) {     

           }
        
    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainSceneController implements Initializable {

    // this is like a constructor but itll happen after all the fxml stuff is loaded
    // When the java gui is loaded all this inside the method will execute. 

    // private ArrayList<Item> storeInventory = new ArrayList<Item>(); 
    // private ArrayList<String> invoice = new ArrayList<String>();

    @FXML
    private TextField itemIDField;
    @FXML
    private TextField detailField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField subtotalField;

    @FXML 
    private Button confirmButton;
    @FXML 
    private Button viewOrderButton;
    @FXML 
    private Button finishButton;
    @FXML
    private Button processButton;
    @FXML
    private Button newOrderButton;
    // @FXML
    // private Button viewOrderOkBtn;

    @FXML 
    private Label itemIDLabel;
    @FXML 
    private Label quantityLabel;
    @FXML 
    private Label detailLabel;
    @FXML 
    private Label subtotalLabel;

    // Wiring scene for viewOrder
    private Parent root;
    private Stage viewOrderStage;
    private Scene viewOrderScene;

    @FXML
    private AnchorPane rootPane;



    // private int quantity = 0;
    // private int discount = 0; 
    // private double itemTotal = 0;
    // private double subtotal = 0;

    // private String itemName;
    // private String itemID;
    // private boolean itemAvaiable;
    // private double itemPrice;

    // private int totalNumItems = 1;

    Dialog<String> popup = new Dialog<String>();
    ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);

    private static final DecimalFormat df = new DecimalFormat("0.00");


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        File file = new File("lib/inventory.txt"); 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(", ");

                Orderdata.storeInventory.add(new Item(values[0], values[1], Boolean.parseBoolean(values[2]), Double.parseDouble(values[3])));

            }
            // for (Item str : storeInventory) {
            //     System.out.println(str.getId());
            // }
            br.close();
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.confirmButton.setDisable(true);
        this.viewOrderButton.setDisable(true);
        this.finishButton.setDisable(true);
        System.out.println("DOES IT COME IN HERE");
    }


    // Returns a 0 if error popup appears 
    // Return 1 if processing is successful 
    // Returning a value so we can stop the process after a popup
    @FXML
    int processClick(ActionEvent event) {
        Orderdata.itemName = "";

        String enteredID = itemIDField.getText();

        if (!enteredID.isEmpty()) {

            for (Item items : Orderdata.storeInventory) {

                if (enteredID.equals(items.getId()) == true) {
                    System.out.println(items.getName());

                    Orderdata.itemName = items.getName();
                    Orderdata.itemID = items.getId();
                    Orderdata.itemAvaiable = items.getAvailability();
                    Orderdata.itemPrice = items.getPrice();
                    
                    break;
                }

            }

            if (Orderdata.itemName.isEmpty())
            {
                popup.setTitle("NILE DOT COME ERROR - INVALID ID");
                popup.setContentText("item " + enteredID + " not in file.");
                popup.getDialogPane().getButtonTypes().add(type);
                popup.showAndWait();

                itemIDField.clear();
                quantityField.clear();

                return 0;
            }
        }
        else {
            System.out.println("Please enter a item identification.");
            popup.setTitle("NILE DOT COME ERROR - NO ENTRY");
            popup.setContentText("Enter an item you would like please.");
            popup.getDialogPane().getButtonTypes().add(type);
            popup.showAndWait();
            return 0;
            
        }

        if (Orderdata.itemAvaiable && !enteredID.isEmpty()) {

            if (!quantityField.getText().isEmpty())
            {
                Orderdata.quantity = Integer.parseInt(quantityField.getText());
                if (Orderdata.quantity >= 15) {
                    Orderdata.discount = 20;
                }
                else if (Orderdata.quantity >= 10 && Orderdata.quantity <= 14) {
                    Orderdata.discount = 15;
                }
                else if (Orderdata.quantity >= 5 && Orderdata.quantity <= 9) {
                    Orderdata.discount = 10;
                } 
                else if (Orderdata.quantity >= 1 && Orderdata.quantity <= 4)
                {
                    Orderdata.discount = 0;
                }
                System.out.println("the discount" + Orderdata.discount / 100.0);
                if (Orderdata.discount != 0) {
                    double percentOff = Orderdata.itemPrice * (double) Orderdata.quantity * (Orderdata.discount / 100.0);
                    Orderdata.itemTotal = (Orderdata.itemPrice * Orderdata.quantity) - percentOff;
                }
                else {
                    Orderdata.itemTotal = Orderdata.itemPrice * Orderdata.quantity;
                }

                Orderdata.subtotal += Orderdata.itemTotal;

                detailField.setText(Orderdata.itemID + " " + Orderdata.itemName + " " + 
                Orderdata.itemPrice + " " + Orderdata.quantity + " " + Orderdata.discount + "%" + " " + df.format(Orderdata.itemTotal));



                processButton.setDisable(true);
                confirmButton.setDisable(false);
                viewOrderButton.setDisable(true);
                finishButton.setDisable(true);
            }
            else {
                // have a pop up here
                System.out.println("ENTER QUANTITY!");
                popup.setTitle("NILE DOT COME ERROR");
                popup.setContentText("Enter a quantity.");
                popup.getDialogPane().getButtonTypes().add(type);
                popup.showAndWait();
                return 0;
            }


        }
        else {
            popup.setTitle("NILE DOT COME ERROR - OUT OF STOCK");
            popup.setContentText("Sorry... that item is out of stock, please try another item.");
            popup.getDialogPane().getButtonTypes().add(type);
            popup.showAndWait();

            // clear out entered information 
            itemIDField.clear();
            quantityField.clear();
        }
        return 1;
        

    }

    @FXML
    void confirmClick(ActionEvent event) {

        subtotalField.setText(df.format(Orderdata.subtotal) +"");

        // add to invoice 
        Orderdata.invoice.add(Orderdata.itemID + " " + Orderdata.itemName + " " + 
        Orderdata.itemPrice + " " + Orderdata.quantity + " " + Orderdata.discount + "%" + " " + Orderdata.itemTotal);
        System.out.println(Orderdata.invoice.get(0));
        

        //pop up to notify user the item has been added
        System.out.println(Orderdata.totalNumItems);
        popup.setTitle("NILE DOT COM - Item Confirmed");
        popup.setContentText("item #" + Orderdata.totalNumItems + " accepted. Added to your cart.");
        popup.getDialogPane().getButtonTypes().add(type);
        popup.showAndWait();

        // update current item # shown on screen 
        Orderdata.totalNumItems++;

        itemIDLabel.setText("Enter item ID for item #" + Orderdata.totalNumItems);
        quantityLabel.setText("Enter quantity for item #" + Orderdata.totalNumItems);
        detailLabel.setText("Details for item #" + Orderdata.totalNumItems);
        confirmButton.setText("Confirm item #"+ Orderdata.totalNumItems);
        processButton.setText("Process item #" + Orderdata.totalNumItems);
        subtotalLabel.setText("Order subtotal for " + (Orderdata.totalNumItems - 1) + " item(s)");

        this.confirmButton.setDisable(true);
        this.processButton.setDisable(false);
        this.viewOrderButton.setDisable(false);
        this.finishButton.setDisable(false);

        itemIDField.clear();
        quantityField.clear();

        
    }

    @FXML   
    void finishClick(ActionEvent event) {
        System.out.println("GO\n");
    }

    @FXML
    void newOrderClick(ActionEvent event) {
        System.out.println("TO\n");

        // clear out the invoice / transaction arraylist 
        if (!Orderdata.invoice.isEmpty()) {
            Orderdata.invoice.clear();
        }

        // reset the gui back to default state 

        processButton.setText("Process item #1");
        confirmButton.setText("Confirm item #1");
        itemIDLabel.setText("Enter item ID for item #1");
        quantityLabel.setText("Enter quantity for item #1");
        detailLabel.setText("Details for item #1");
        subtotalLabel.setText("Order subtotal for 0 item(s)");
        itemIDField.clear();
        quantityField.clear();
        detailField.clear();
        subtotalField.clear();

        Orderdata.totalNumItems = 1;
        Orderdata.itemTotal = 0;
        Orderdata.subtotal = 0;

        this.viewOrderButton.setDisable(true);
        this.finishButton.setDisable(true);

    }

    @FXML
    void exitClick(ActionEvent event) {
        System.out.println("PLUTO\n");
        if (Orderdata.invoice.isEmpty())
        {
            System.out.println("SON OF A BITCH");
        }

        else {
            System.out.println("Okay some healthy information.");
        }
    }

    @FXML
    void viewClick(ActionEvent event) throws IOException {
        System.out.println("BREUH");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("viewOrderScene.fxml"));
        rootPane.getChildren().setAll(pane);
    //      viewOrderStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //    viewOrderScene = new Scene(root);
    //     viewOrderStage.setScene(viewOrderScene);
    //     viewOrderStage.show();

    }


}

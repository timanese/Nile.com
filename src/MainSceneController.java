import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainSceneController implements Initializable {

    // this is like a constructor but itll happen after all the fxml stuff is loaded
    // When the java gui is loaded all this inside the method will execute. 

    private ArrayList<Item> storeInventory = new ArrayList<Item>(); 
    @FXML
    private TextField itemIDField;
    @FXML
    private TextField detailField;
    @FXML
    private TextField quantityField;

    private int quantity = 0;
    private int discount = 0; 
    private double total = 0;

    private String itemName;
    private String itemID;
    private boolean itemAvaiable;
    private double itemPrice;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        File file = new File("lib/inventory.txt"); 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(", ");

                storeInventory.add(new Item(values[0], values[1], Boolean.parseBoolean(values[2]), Double.parseDouble(values[3])));

            }
            // for (Item str : storeInventory) {
            //     System.out.println(str.getId());
            // }
            br.close();
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @FXML
    void processClick(ActionEvent event) {

        String enteredID = itemIDField.getText();
        Item currentItem = new Item("empty", "empty", false, 0);

        if (!enteredID.isEmpty()) {

            for (Item items : storeInventory) {

                if (enteredID.equals(items.getId()) == true) {
                    System.out.println(items.getName());

                    this.itemName = items.getName();
                    this.itemID = items.getId();
                    this.itemAvaiable = items.getAvailability();
                    this.itemPrice = items.getPrice();
                    
                    break;
                }

            }
        }
        else {
            System.out.println("Please enter a item identification.");
        }

        if (this.itemAvaiable) {

            if (!quantityField.getText().isEmpty())
            {
                this.quantity = Integer.parseInt(quantityField.getText());
                if (this.quantity >= 15) {
                    discount = 20;
                }
                else if (this.quantity >= 10 && this.quantity <= 14) {
                    discount = 15;
                }
                else if (this.quantity >= 5 && this.quantity <= 9) {
                    discount = 10;
                } 
                else if (this.quantity >= 1 && this.quantity <= 4)
                {
                    discount = 0;
                }
                System.out.println("the discount" + discount / 100.0);
                if (discount != 0) {
                    double percentOff = this.itemPrice * (double) this.quantity * (this.discount / 100.0);
                    this.total = (this.itemPrice * this.quantity) - percentOff;
                }
                else {
                    this.total = this.itemPrice * this.quantity;
                }


                

                detailField.setText(this.itemID + " " + this.itemName + " " + 
                this.itemPrice + " " + quantity + " " + discount + "%" + " " + this.total);
            }
            else {
                // have a pop up here
                System.out.println("ENTER QUANTITY!");
            }

            // if (detailField.getText().isEmpty()) {
            //     System.out.println("Nile dot com currently does not carry that item!");
            // }


        }
        

    }

    @FXML
    void confirmClick(ActionEvent event) {
        System.out.println("WANNA\n");
    }

    @FXML   
    void finishClick(ActionEvent event) {
        System.out.println("GO\n");
    }

    @FXML
    void newOrderClick(ActionEvent event) {
        System.out.println("TO\n");
    }

    @FXML
    void exitClick(ActionEvent event) {
        System.out.println("PLUTO\n");
    }



    // @FXML
    // private void getInventory() throws Exception {
    //     File file = new File("lib/inventory.txt"); 
    //     BufferedReader br = new BufferedReader(new FileReader(file));
    //     String line = null;
    //     ArrayList<Item> storeInventory = new ArrayList<Item>(); 

    //     while ((line = br.readLine()) != null) {
    //         String[] values = line.split(", ");

    //         storeInventory.add(new Item(values[0], values[1], Boolean.parseBoolean(values[2]), Double.parseDouble(values[3])));

    //     }
    //     // for (Item str : storeInventory) {
    //     //     System.out.println(str.getId());
    //     // }
    //     br.close();
    // }

}

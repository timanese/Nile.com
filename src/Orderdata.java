// Name: Tim Yang
// Course: CNT 4714-Spring 2022
// Assignment title: Project 1- Event-driven Enterprise Simulation
// Date: Sunday january 30, 2022

import java.util.ArrayList;

public class Orderdata {
    
    static ArrayList<Item> storeInventory = new ArrayList<Item>(); 
    static ArrayList<String> invoice = new ArrayList<String>();

    static int quantity = 0;
    static int discount = 0; 
    static double itemTotal = 0;
    static double subtotal = 0;

    static String itemName;
    static String itemID;
    static boolean itemAvaiable;
    static double itemPrice;

    static int totalNumItems = 1;
}

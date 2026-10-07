/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

/**
 *
 * @author s23bukha
 */

import java.util.ArrayList;
import java.util.List;

public class CustomerClass {
    private String username;
    private String password;
    private int points;

    // Static list to manage all customers
    private static List<CustomerClass> customers = new ArrayList<>();

    // Constructor
    public CustomerClass(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getStatus() {
        return points >= 1000 ? "Gold" : "Silver";
    }

    // Static manager-like methods
    public static void addCustomer(CustomerClass customer) {
        customers.add(customer);
    }

    public static void deleteCustomer(CustomerClass customer) {
        customers.remove(customer);
    }

    public static List<CustomerClass> getCustomers() {
        return customers;
    }

    public static CustomerClass findCustomer(String username) {
        for (CustomerClass c : customers) {
            if (c.getUsername().equals(username)) return c;
        }
        return null;
    }
    
    public void setPassword(String password) {
    this.password = password;
}


}


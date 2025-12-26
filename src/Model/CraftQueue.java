/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ASUS
 */
public class CraftQueue {
    private static Queue<Craft> crafts = new LinkedList<>();
    // Method to add a craft
    public static void addCraft(Craft craft) {
        // Optional: prevent duplicates by name
        for (Craft c : crafts) {
            if (c.getName().equalsIgnoreCase(craft.getName())) return;
        }
        crafts.add(craft);
    }    
    // Method to remove a craft
    public static boolean removeCraft(Craft craft) {
        return crafts.remove(craft); // removes matching object
    }

    
    // Method to get all crafts
    public static Queue<Craft> getAllCrafts() {
        return new LinkedList<>(crafts);
    }
    public static void initializeSampleData() {
        // Aircraft 1
        Craft aircraft1 = new Craft(
            "AirBus A380",
             "A380-800",   
             "SIA 403",
             "Rolls-Royce Trent 900", 
            "London", "10:30 AM", "N/A", "N/A", 
            "Troposheric", "Commercial", "On-Board", "Aircraft","N/A"
        );
        Craft aircraft2 = new Craft(
            "NASA ER-2",                         // name
            "NASA 806",                          // modelNo
            "N/A",                               // flightNo
            "General Electric F118-101 Turbofan",// engineType
            "Rhode Island",                     // destination
            "9:00 AM",                          // departureTime
            "N806NA",                           // tailNumber
            "Palmdale, California",             // base
            "Stratospheric",                    // type
            "Research",                         // purpose
            "Yet to Board",                     // status
            "Aircraft",                         // craftType
            "N/A"                                // serialNo

        );
        Craft aircraft3 = new Craft(
            "Eurofighter Typhoon",   // name
            "Typhoon FGR4",          // modelNo
            "N/A",                   // flightNo
            "Eurojet EJ200",         // engineType
            "Hawaii",                // destination
            "5:00 AM",               // departureTime
            "ZK336",                 // tailNumber
            "United Kingdom",        // base
            "Stratospheric",         // type
            "Military",              // purpose
            "On Board",              // status
            "Aircraft",              // craftType
            "N/A"                    // serialNo
        );
        Craft spacecraft1 = new Craft(
            "LEO Research Satellite",     // name
            "LEO-SAT X1",                 // modelNo
            "N/A",                        // flightNo
            "Ion Thruster",               // engineType
            "Low Earth Orbit",            // destination
            "06:45 AM",                   // departureTime
            "N/A",                        // tailNumber
            "Kennedy Space Center",       // base
            "Low Earth Orbit",            // type
            "Research",                   // purpose
            "In Orbit",                   // status
            "Spacecraft",                 // craftType
            "SAT-LEO-1029"                // serialNo
        );
        CraftQueue.addCraft(aircraft1);
        CraftQueue.addCraft(spacecraft1);
        CraftQueue.addCraft(aircraft2);
        CraftQueue.addCraft(aircraft3);       
        
}
    
}


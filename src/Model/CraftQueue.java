/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 *
 * @author ASUS
 */

public class CraftQueue {
    private static boolean sampleLoaded = false;
    private static final int HISTORY_LIMIT = 3;

    // store messages like: "Falcon was added"
    private static final Deque<String> recentAdded = new ArrayDeque<>();
    private static final Deque<String> recentDeleted = new ArrayDeque<>();

    // for undo (store the actual craft object)
    private static final Deque<Craft> deletedUndoStack = new ArrayDeque<>();
    
    private static void pushHistory(Deque<String> q, String msg) {
    q.addFirst(msg);
    while (q.size() > HISTORY_LIMIT) q.removeLast();
    
}
    


    private static Queue<Craft> crafts = new LinkedList<>();
    // Method to add a craft
    public static String addCraft(Craft craft) {
    if (craft == null) return "Invalid craft";

    for (Craft c : crafts) {

         if (c.getModelNo() != null && craft.getModelNo() != null &&
            c.getModelNo().equalsIgnoreCase(craft.getModelNo())) {
            return "Model no already exist";
        }
    }

    crafts.add(craft);

    String nm = craft.getName() == null ? "Unknown craft" : craft.getName();
    pushHistory(recentAdded, nm + " was added");

    return null; 
}
  
    // Method to remove a craft
    public static boolean removeCraft(Craft craft) {
    if (craft == null) return false;

    boolean removed = crafts.remove(craft);

    if (removed) {
        
        deletedUndoStack.push(craft);
        String nm = craft.getName();
        pushHistory(recentDeleted, nm + " was deleted");
    }
    return removed;
    }
    public static Craft undoLastDelete() {
    if (deletedUndoStack.isEmpty()) return null;

    Craft c = deletedUndoStack.pop();
    crafts.add(c);

    // optional: show as added again (your choice)
    String nm = c.getName() == null ? "Unknown craft" : c.getName();
    pushHistory(recentAdded, nm + " was restored");

    return c;
}

    public static List<String> getRecentAdded() {
    return new ArrayList<>(recentAdded);
}

public static List<String> getRecentDeleted() {
    return new ArrayList<>(recentDeleted);
}

    
    // Method to get all crafts
    public static Queue<Craft> getAllCrafts() {
        return new LinkedList<>(crafts);
    }
    public static void sortByNameAsc() {
    if (crafts == null || crafts.size() <= 1) return;
    java.util.LinkedList<Craft> list = new java.util.LinkedList<>(crafts);

    for (int i = 0; i < list.size() - 1; i++) {
        for (int j = 0; j < list.size() - i - 1; j++) {

            String n1 = list.get(j).getName();
            String n2 = list.get(j + 1).getName();

            if (n1 == null) n1 = "";
            if (n2 == null) n2 = "";

            if (n1.compareToIgnoreCase(n2) > 0) {
                Craft temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
            }
        }
    }

    // Rebuild the queue in sorted order
   crafts.clear();
    for (Craft c : list) {
        crafts.add(c);
    }
}

    public static void sortByNameDesc() {
    if (crafts == null || crafts.size() <= 1) return;

    java.util.LinkedList<Craft> list = new java.util.LinkedList<>(crafts);

    for (int i = 0; i < list.size() - 1; i++) {
        for (int j = 0; j < list.size() - i - 1; j++) {

            String n1 = list.get(j).getName();
            String n2 = list.get(j + 1).getName();

            if (n1 == null) n1 = "";
            if (n2 == null) n2 = "";

            if (n1.compareToIgnoreCase(n2) < 0) {
                Craft temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
            }
        }
    }

    crafts.clear();
    for (Craft c : list) {
        crafts.add(c);
    }
}
    public static boolean canUndoDelete() {
    return !deletedUndoStack.isEmpty();
}


    public static void initializeSampleData() {
        // Aircraft 1
         if (sampleLoaded) return;
        sampleLoaded = true;
        Craft aircraft1 = new Craft(
            "AirBus A380",
             "A380-800",   
             "SIA 403",
             "Rolls-Royce Trent 900", 
            "London", "10:30", "N/A", "N/A", 
            "Troposheric", "Commercial", "On-Board", "Aircraft","N/A"
        );
        Craft aircraft2 = new Craft(
            "NASA ER-2",                         // name
            "NASA 806",                          // modelNo
            "N/A",                               // flightNo
            "General Electric F118-101 Turbofan",// engineType
            "Rhode Island",                     // destination
            "9:00",                          // departureTime
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
            "5:00",               // departureTime
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
            "06:45",                   // departureTime
            "N/A",                        // tailNumber
            "Kennedy Space Center",       // base
            "Low Earth Orbit",            // type
            "Research",                   // purpose
            "In Orbit",                   // status
            "Spacecraft",                 // craftType
            "SAT-LEO-1029"                // serialNo
        );
        Craft spacecraft2 = new Craft(
            "Mars Recon Orbiter",          // name
            "MRO-X9",                      // modelNo
            "N/A",                         // flightNo
            "Liquid Propulsion Engine",    // engineType
            "Mars Orbit",                  // destination
            "14:20",                       // departureTime
            "N/A",                         // tailNumber
            "Jet Propulsion Laboratory",   // base
            "Interplanetary",              // type
            "Planetary Research",          // purpose
            "En Route",                    // status
            "Spacecraft",                  // craftType
            "MRO-2045"                     // serialNo
        );

        CraftQueue.addCraft(aircraft1);
        CraftQueue.addCraft(spacecraft1);
        CraftQueue.addCraft(aircraft2);
        CraftQueue.addCraft(aircraft3);   
        CraftQueue.addCraft(spacecraft2);
       
}
    
}


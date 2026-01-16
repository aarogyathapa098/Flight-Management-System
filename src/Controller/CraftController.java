package Controller;

import Model.Craft;
import Model.CraftQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Controller for handling Craft-related operations.
 * Acts as an intermediary between the View and the Model (CraftQueue).
 */
public class CraftController {

    public CraftController() {
        // Initialize basic data if needed
        CraftQueue.initializeSampleData();
    }

 public String addCraft(Craft craft) {

    // Call model ONCE
    String error = CraftQueue.addCraft(craft);

    // Forward error (if any)
    if (error != null) {
        return error;
    }

    // success
    return null;
}


    public boolean removeCraft(Craft craft) {
        return CraftQueue.removeCraft(craft);
    }

    public Craft undoLastDelete() {
        return CraftQueue.undoLastDelete();
    }

    public void sortByName(boolean ascending) {
        if (ascending) {
            CraftQueue.sortByNameAsc();
        } else {
            CraftQueue.sortByNameDesc();
        }
    }

    public Queue<Craft> getAllCrafts() {
        return CraftQueue.getAllCrafts();
    }

    public List<String> getRecentAdded() {
        return CraftQueue.getRecentAdded();
    }

    public List<String> getRecentDeleted() {
        return CraftQueue.getRecentDeleted();
    }

    public boolean canUndoDelete() {
        return CraftQueue.canUndoDelete();
    }
    // CraftController.java
public String saveCraft(
        String name,
        String modelNo,
        String flightNo,
        String engineType,
        String destination,
        String departureTime,
        String tailNumber,
        String base,
        String type,
        String purpose,
        String status,
        boolean isAircraft,
        boolean isSpacecraft,
        String serialNo
) {

    String depart_time = departureTime.trim();

    if (name.trim().isEmpty()) return "Please enter craft name";
    if (!isAircraft && !isSpacecraft) return "Please select craft type (Aircraft or Spacecraft)";
    if (type == null) return "Please select type of the craft.";
    if (destination.trim().isEmpty()) return "Please enter the destination";
    if (departureTime.trim().isEmpty()) return "Please enter departure time";
    if (!depart_time.matches("([01]?\\d|2[0-3]):[0-5]\\d")) return "Enter time in HH:mm format! Example: 09:30 or 23:15";
    if (engineType.trim().isEmpty()) return "Please enter engine type";
    if (purpose == null) return "Please select purpose of the craft";
    if (modelNo.trim().isEmpty()) return "Please enter model number";
    if (status == null) return "Please select status of the craft";

    if (isAircraft) {
        if (type.equals("Tropospheric")) {
            if (flightNo.trim().isEmpty()) return "Please enter flight number";
        } else if (type.equals("Stratospheric")) {
            if (base.trim().isEmpty()) return "Please enter base";
            if (tailNumber.trim().isEmpty()) return "Please enter tail number";
        }
    } else if (isSpacecraft) {
        if (serialNo.trim().isEmpty()) return "Please enter spacecraft serial number";
    }

    Craft craft = new Craft(
            name.trim(),
            modelNo.trim(),
            flightNo.trim(),
            engineType.trim(),
            destination.trim(),
            departureTime.trim(),
            tailNumber.trim(),
            base.trim(),
            type,
            purpose,
            status,
            isAircraft ? "Aircraft" : "Spacecraft",
            serialNo.trim()
    );

    addCraft(craft);
    return null; // success
}
public String updateCraft(
        Craft craft,
        String name,
        String craftType,
        String engineType,
        String departureTime,
        String destination,
        String type,
        String purpose,
        String modelNo,
        String flightNo,
        String tailNo,
        String base,
        String serialNo
) {
    if (craft == null) return "No craft selected";

    // (optional) You can keep validation here or skip it if you don’t want
    // but at minimum update the same fields you were updating in the view

    craft.setName(name);
    craft.setCraftType(craftType);
    craft.setEngineType(engineType);
    craft.setDepartureTime(departureTime);
    craft.setDestination(destination);
    craft.setType(type);
    craft.setPurpose(purpose);
    craft.setModelNo(modelNo);

    if ("Aircraft".equals(craftType)) {
        if ("Tropospheric".equals(type)) {
            craft.setFlightNo(flightNo);
        } else if ("Stratospheric".equals(type)) {
            craft.setBase(base);
            craft.setTailNumber(tailNo);
        }
    } else if ("Spacecraft".equals(craftType)) {
        craft.setSerialNo(serialNo);
    }

    return null; // success
}
public Craft searchByName(String key) {
    if (key == null || key.trim().isEmpty()) {
        return null;
    }

    sortByName(true);
    List<Craft> list = new ArrayList<>(getAllCrafts());

    int low = 0, high = list.size() - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        int cmp = list.get(mid).getName().compareToIgnoreCase(key);

        if (cmp == 0) return list.get(mid);
        if (cmp > 0) high = mid - 1;
        else low = mid + 1;
    }
    return null;
}
public List<Craft> searchPartialByName(String key) {
    List<Craft> results = new ArrayList<>();

    if (key == null) return results;
    key = key.trim().toLowerCase();
    if (key.isEmpty()) return results;

    for (Craft c : getAllCrafts()) {   
        String name = c.getName();
        if (name != null && name.toLowerCase().contains(key)) {
            results.add(c);
        }
    }
    return results;
}



}


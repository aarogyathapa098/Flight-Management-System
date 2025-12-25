/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
public class Craft {
    private String name;
    private String modelNo;
    private String flightNo;
    private String engineType;
    private String destination;
    private String departureTime;
    private String tailNumber;
    private String base;
    private String type;
    private String purpose;
    private String status;
    private String craftType;
    private String serialNo;
    
    public Craft(String name, String modelNo, String flightNo, String engineType,
                 String destination, String departureTime, String tailNumber, 
                 String base, String type, String purpose, String status, String craftType,String serialNo) {
        this.name = name;
        this.modelNo = modelNo;
        this.flightNo = flightNo;
        this.engineType = engineType;
        this.destination = destination;
        this.departureTime = departureTime;
        this.tailNumber = tailNumber;
        this.base = base;
        this.type = type;
        this.purpose = purpose;
        this.status = status;
        this.craftType = craftType;
        this.serialNo= serialNo;
    }
    
    // Getters
    public String getName() { return name; }
    public String getModelNo() { return modelNo; }
    public String getFlightNo() { return flightNo; }
    public String getEngineType() { return engineType; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getTailNumber() { return tailNumber; }
    public String getBase() { return base; }
    public String getType() { return type; }
    public String getPurpose() { return purpose; }
    public String getStatus() { return status; }
    public String getCraftType() { return craftType; }
    public String getSerialNo() {return serialNo;}
    
    // In Model/Craft.java - add these if they're missing

public void setName(String name) { 
    this.name = name; 
}

public void setModelNo(String modelNo) { 
    this.modelNo = modelNo; 
}

public void setFlightNo(String flightNo) { 
    this.flightNo = flightNo; 
}

public void setEngineType(String engineType) { 
    this.engineType = engineType; 
}

public void setDestination(String destination) { 
    this.destination = destination; 
}

public void setDepartureTime(String departureTime) { 
    this.departureTime = departureTime; 
}

public void setTailNumber(String tailNumber) { 
    this.tailNumber = tailNumber; 
}

public void setBase(String base) { 
    this.base = base; 
}

public void setType(String type) { 
    this.type = type; 
}

public void setPurpose(String purpose) { 
    this.purpose = purpose; 
}

public void setStatus(String status) { 
    this.status = status; 
}

public void setCraftType(String craftType) { 
    this.craftType = craftType; 
}
public void setSerialNo (String serialNo){
    this.serialNo= serialNo;
}
 
}
 


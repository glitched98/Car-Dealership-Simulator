// Omar Syed, Student Number: 500809837
public class ElectricCar extends Car {
    //instance variables
    private int rechargeTime; 
    private String batteryType; 


    /**
     * 
     * @param mfr (manufacturer)
     * @param color
     * @param model
     * @param power
     * @param safetyRating
     * @param maxRange
     * @param AWD (All Wheel Drive)
     * @param price
     * @param rechargeTime
     * @param batteryType
     */
    public ElectricCar(String mfr, String color,  String model, String power,  double safetyRating, int maxRange, boolean AWD, double price, int rechargeTime, String batteryType) {
        super(mfr, color, model, power, safetyRating, maxRange, AWD, price);
        this.rechargeTime = rechargeTime;
        this.batteryType = batteryType;
    }

    /**
     * @return the batteryType
     */
    public String getBatteryType() {
        return batteryType;
    }

    /**
     * sets batteryType
     * @param batteryType
     */
    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    /**
     * @return the rechargeTime
     */
    public int getRechargeTime() {
        return rechargeTime;
    }

    /**
     * sets rechargeTime
     * @param rechargeTime
     */
    public void setRechargeTime(int rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    /**
     * @return a String containing all instance variables
     */
    public String display() {
        return super.display() + " EL, BAT: " + batteryType + " RCH " + rechargeTime;
    }
}
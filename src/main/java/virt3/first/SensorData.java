package virt3.first;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SensorData {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime time;
    private Integer temperature;
    private Integer CO2;
    public SensorData(Integer temperature, Integer CO2) {
        this.time = LocalDateTime.now();
        this.temperature = temperature;
        this.CO2 = CO2;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getCO2() {
        return CO2;
    }

    public String getTime() {
        return time.format(formatter);
    }
}

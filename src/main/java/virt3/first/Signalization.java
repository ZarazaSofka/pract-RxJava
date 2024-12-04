package virt3.first;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Signalization implements Observer<SensorData> {
    private static final int TEMPERATURE_NORMAL_LIMIT = 25;
    private static final int CO2_NORMAL_LIMIT = 70;

    public static Signalization getInstance() {
        return new Signalization();
    }

    @Override
    public void onSubscribe(Disposable d) {}

    @Override
    public void onNext(SensorData sensorData) {
        int temperature = sensorData.getTemperature();
        int CO2 = sensorData.getCO2();

        if (temperature > TEMPERATURE_NORMAL_LIMIT && CO2 > CO2_NORMAL_LIMIT) {
            System.out.println(sensorData.getTime() + " ALARM!!!");
        } else if (temperature > TEMPERATURE_NORMAL_LIMIT) {
            System.out.println(sensorData.getTime() +  " Temperature alert: " + temperature);
        } else if (CO2 > CO2_NORMAL_LIMIT) {
            System.out.println(sensorData.getTime() + " CO2 alert: " + CO2);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete(){};
}

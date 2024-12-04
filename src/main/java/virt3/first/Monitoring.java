package virt3.first;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Monitoring {
    private static final Random random = new Random();

    public static void start() {
        Observable<Integer> temperatureSensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> random.nextInt(16) + 15);

        Observable<Integer> CO2Sensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> random.nextInt(71) + 30);

        Observable.zip(temperatureSensor, CO2Sensor, SensorData::new)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(Signalization.getInstance());

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package virt3.fourth;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;

public class FileGenerator {

    static Random random = new Random();
    public static Observable<File> generateFiles(String[] fileTypes) {
        return Observable.fromCallable(() -> {
                try {
                    Thread.sleep(random.nextInt(901) + 100);
                    return new File(fileTypes[random.nextInt(fileTypes.length)], random.nextInt(91) + 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        })
                .repeat()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}


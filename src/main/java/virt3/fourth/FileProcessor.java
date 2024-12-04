package virt3.fourth;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Objects;

public class FileProcessor {

    private final String type;

    public FileProcessor(String type) {
        this.type = type;
    }

    public static void processFile(File file) {

        try {
            Thread.sleep(file.getSize() * 7L);
            System.out.println("4. Обработан файл c типом " + file.getType() + " и размером " + file.getSize());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribeToFileQueue(Observable<File> fileObservable) {
        fileObservable
                .filter(file -> Objects.equals(file.getType(), type))
                .observeOn(Schedulers.io())
                .subscribe(
                        FileProcessor::processFile,
                        Throwable::printStackTrace
                );
    }
}

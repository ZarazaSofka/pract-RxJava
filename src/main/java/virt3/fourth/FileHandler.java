package virt3.fourth;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FileHandler {
    private static final String[] fileTypes = {"XML", "JSON", "HTML"};
    public static void start() {
        FileQueue fileQueue = new FileQueue(5);

        FileGenerator.generateFiles(fileTypes)
                .doOnNext(fileQueue::addFile)
                .subscribe(
                    file -> System.out.println(
                            "4. Сгенерирован файл с типом " + file.getType() + " и размером " + file.getSize()
                    )
                );

        Observable<File> fileObservable = fileQueue.getFileObservable().subscribeOn(Schedulers.io());
        for (String fileType : fileTypes) {
            FileProcessor fileProcessor = new FileProcessor(fileType);
            fileProcessor.subscribeToFileQueue(fileObservable);
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Обработка завершена");
        }
    }
}

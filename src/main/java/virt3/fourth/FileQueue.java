package virt3.fourth;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

public class FileQueue {
    private final Subject<File> fileObservable;

    public FileQueue(int capacity) {
        this.fileObservable = ReplaySubject.createWithSize(capacity);
    }

    public void addFile(File file) {
        this.fileObservable.onNext(file);
    }

    public Observable<File> getFileObservable() {
        return this.fileObservable;
    }
}

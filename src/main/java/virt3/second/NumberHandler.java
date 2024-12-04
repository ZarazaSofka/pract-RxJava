package virt3.second;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberHandler {
    private static Random random = new Random();

    public static void start() {
        one();
        two();
        three();
    }

    private static void one() {
        Observable<Integer> nums = Observable.fromCallable(() -> {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 1000; i++) numbers.add(random.nextInt(1001));
            return numbers;
        }).flatMapIterable(numbers -> numbers);

        nums.filter(number -> number > 500)
                .toList().subscribe(
                        list -> System.out.println("2.1. Числа больше 500: " + list),
                        Throwable::printStackTrace
                );
    }

    private static void two() {
        Observable<Integer> nums1 = Observable.fromCallable(() -> {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 1000; i++) numbers.add(random.nextInt(10));
            System.out.println("2.2. Первый поток: " + numbers);
            return numbers;
        }).flatMapIterable(numbers -> numbers);

        Observable<Integer> nums2 = Observable.fromCallable(() -> {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 1000; i++) numbers.add(random.nextInt(10));
            System.out.println("2.2. Второй поток: " + numbers);
            return numbers;
        }).flatMapIterable(numbers -> numbers);

        Observable.concat(nums1, nums2).toList()
                .subscribe((list) -> System.out.println("2.2. Последовательно: " + list), Throwable::printStackTrace);
    }

    private static void three() {
        Observable<Integer> nums = Observable.fromCallable(() -> {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 10; i++) numbers.add(random.nextInt(1001));
            System.out.println("2.3. Изначальные числа: " + numbers);
            return numbers;
        }).flatMapIterable(numbers -> numbers);

        nums.take(5).toList()
                .subscribe(
                        (list) -> System.out.println("2.3. Взято 5 чисел: " + list),
                        Throwable::printStackTrace
                );
    }
}

package virt3.third;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

public class FriendSearch {
    static class UserFriend {
        int userId;
        int friendId;

        public UserFriend(int userId, int friendId) {
            this.userId = userId;
            this.friendId = friendId;
        }
    }

    private static UserFriend[] userFriends;
    private static final Random random = new Random();

    public static Observable<UserFriend> getFriends(int userId) {
        return Observable.fromArray(userFriends)
                .filter(friend -> friend.userId == userId);
    }

    public static void start() {
        int n = 100;
        userFriends = new UserFriend[n];
        for (int i = 0; i < n; i++) {
            int userId = random.nextInt(21);
            int friendId = random.nextInt(21);
            userFriends[i] = new UserFriend(userId, friendId);
        }

        Integer[] randomUserIds = new Integer[5];
        for (int i = 0; i < randomUserIds.length; i++) {
            randomUserIds[i] = random.nextInt(21);
        }

        Observable.fromArray(randomUserIds)
                .flatMap(FriendSearch::getFriends)
                .subscribe(
                        userFriend -> System.out.println("3. Друг " + userFriend.userId + " - " + userFriend.friendId),
                        Throwable::printStackTrace
                );
    }
}

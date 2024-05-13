package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Threads {
    private static final Object lock = new Object();
    private static int turn = 1;
    private static List<String> chat = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
    }

    public static void friendsChat() {

        Thread joey = new Thread(() -> {
            act("Joey", "Hey, hey.", 1);
            act("Joey", "Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.", 5);
            act("Joey", "You know those posters for the City Free Clinic?", 8);
            act("Joey", "No, but I hear lyme disease is open, so… (crosses fingers)", 12);
            act("Joey", "Thanks.", 14);
        });
        Thread chandler = new Thread(() -> {
            act("Chandler", "Hey.", 2);
            act("Chandler", "And this from the cry-for-help department. Are you wearing makeup?", 4);
            act("Chandler", "That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman.", 6);
            act("Chandler", "Do you know which one you're gonna be?", 11);
            act("Chandler", "Good luck, man. I hope you get it.", 13);

        });
        Thread monica = new Thread(() -> act("Monica", "Oh, wow, so you're gonna be one of those \"healthy, healthy, healthy guys\"?", 9));
        Thread phoebe = new Thread(() -> {
            act("Phoebe", "Hey.", 3);
            act("Phoebe", "What were you modeling for?", 7);
            act("Phoebe", "You know, the asthma guy was really cute.", 10);

        });
        Thread rachel = new Thread(() -> act("Rachel", "I didn't get a line in this convo, but my thread is working though!!", 15));
        Thread ross = new Thread(() -> act("Ross", "Yeah mine too!", 16));

        chandler.start();
        joey.start();
        monica.start();
        phoebe.start();
        rachel.start();
        ross.start();

        try {
            chandler.join();
            joey.join();
            monica.join();
            phoebe.join();
            rachel.join();
            ross.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String act(String name, String line, int order) {
        synchronized (lock) {
            while (turn != order) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            chat.add(name + ": " + line);
            turn++;
            lock.notifyAll();
            return (name + ": " + line);
        }
    }

    public static List<String> getChat() {
        return chat;
    }
}

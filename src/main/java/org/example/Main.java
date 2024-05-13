package org.example;


import java.util.stream.Collectors;

import static org.example.Threads.friendsChat;

public class Main {
    public static void main(String[] args) {
        friendsChat();

        String chat = Threads.getChat().stream()
                .map(line -> line + "\n")
                .collect(Collectors.joining());

        System.out.println(chat);


    }
}
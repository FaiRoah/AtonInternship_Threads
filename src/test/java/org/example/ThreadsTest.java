package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.example.Threads.friendsChat;

public class ThreadsTest {

    private static String text = """
                Joey: Hey, hey.
                Chandler: Hey.
                Phoebe: Hey.
                Chandler: And this from the cry-for-help department. Are you wearing makeup?
                Joey: Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.
                Chandler: That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman.
                Phoebe: What were you modeling for?
                Joey: You know those posters for the City Free Clinic?
                Monica: Oh, wow, so you're gonna be one of those "healthy, healthy, healthy guys"?
                Phoebe: You know, the asthma guy was really cute.
                Chandler: Do you know which one you're gonna be?
                Joey: No, but I hear lyme disease is open, so… (crosses fingers)
                Chandler: Good luck, man. I hope you get it.
                Joey: Thanks.
                Rachel: I didn't get a line in this convo, but my thread is working though!!
                Ross: Yeah mine too!
                """;
    private static Threads threads;

    @Test
    @DisplayName("When all lines are printed in correct order then success")
    void testOutput(){

        friendsChat();

        String chat = Threads.getChat().stream()
                .map(line -> line + "\n")
                .collect(Collectors.joining());

        Assertions.assertEquals(text, chat);
    }

}

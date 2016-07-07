package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jokes {
    public String tellJoke() {

        List<String> jokes = new ArrayList<String>();
        Random random = new Random();
        int min = 0,max = 10,num =0;
        num = random.nextInt(max - min + 1) + min;

        jokes.add("It’s hard to explain puns to kleptomaniacs because they always take things.. literally!");
        jokes.add("I used to think the brain was the most important organ. Then I thought, look what’s telling me that.");
        jokes.add("What does a nosey pepper do? Get jalapeño business :D");
        jokes.add("If you want to catch a squirrel just climb a tree and act like a nut!!");
        jokes.add("What do you call dangerous precipitation? A rain of terror.");
        jokes.add("What’s the best part about living in Switzerland? Not sure, but the flag is a big plus!!");
        jokes.add("Why can’t a bike stand on its own? It’s two tired.");
        jokes.add("Just went to an emotional wedding. Even the cake was in tiers :P");
        jokes.add(" I wrote a song about a tortilla. Well actually, it’s more of a wrap.");
        jokes.add("This is a totally funny joke!");
        jokes.add("This is another funny one! DERPPPPP!");

        return jokes.get(num);
    }
}

package com.ecologicstudios.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AddCardTool {

    public static void main(String[] args) throws Exception {
        if (args.length < 5) {
            System.out.println("Usage:");
            System.out.println("  AddCardTool <file> <id> <scenario> <difficulty> <choice1>:<co2_1> [<choice2>:<co2_2> ...]");
            System.out.println("Example:");
            System.out.println("  AddCardTool data/cards.json 5 \"Campus policy\" Easy \"Plant meals:-6\" \"Beef day:18\"");
            return;
        }

        String file = args[0];
        int id = Integer.parseInt(args[1]);
        String scenario = args[2];         // pass with quotes if it has spaces
        String difficulty = args[3];

        List<Choice> choices = new ArrayList<>();
        for (int i = 4; i < args.length; i++) {
            String token = args[i].trim();
            int sep = token.lastIndexOf(':');            // safer if text contains ':'
            if (sep <= 0 || sep == token.length() - 1) {
                System.out.println("Invalid choice format (use \"Text:NUMBER\"): " + token);
                return;
            }
            String text = token.substring(0, sep).trim();
            int co2 = Integer.parseInt(token.substring(sep + 1).trim());
            choices.add(new Choice(text, co2));
        }

        Card card = new Card(id, scenario, difficulty, choices);
        CardJsonIO.upsert(Path.of(file), card);
        System.out.println("Added/updated card id=" + id + " in " + Path.of(file).toAbsolutePath());
    }
}

package com.ecologicstudios.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Command-line utility tool for adding or updating cards in the game's JSON card database.
 * <p>
 * This tool allows developers and content creators to easily add new environmental scenario
 * cards to the game or update existing ones. Cards are stored in JSON format and contain
 * scenarios with multiple choice alternatives, each with associated CO2 emission values.
 * <p>
 * Usage example:
 * <pre>
 * java AddCardTool data/cards.json 5 "Campus policy" Easy "Plant meals:-6" "Beef day:18"
 * </pre>
 * 
 * @author Ecologic Studios
 * @version 1.0
 */
public class AddCardTool {

    /**
     * Main entry point for the AddCardTool command-line utility.
     * <p>
     * Parses command-line arguments to create a new card with the specified ID, scenario,
     * difficulty, and alternative choices. Each choice must be formatted as "text:co2_value".
     * The card is then added to or updated in the specified JSON file.
     * 
     * @param args command-line arguments in the following order:
     *             <ul>
     *             <li>args[0] - file path to the JSON cards file</li>
     *             <li>args[1] - unique card ID (integer)</li>
     *             <li>args[2] - scenario description (use quotes if contains spaces)</li>
     *             <li>args[3] - difficulty level (e.g., "Easy", "Medium", "Hard")</li>
     *             <li>args[4+] - alternative choices in format "choice text:co2_value"</li>
     *             </ul>
     * @throws Exception if file I/O operations fail or argument parsing encounters errors
     */
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

        List<Alternative> choices = new ArrayList<>();
        for (int i = 4; i < args.length; i++) {
            String token = args[i].trim();
            int sep = token.lastIndexOf(':');            // safer if text contains ':'
            if (sep <= 0 || sep == token.length() - 1) {
                System.out.println("Invalid choice format (use \"Text:NUMBER\"): " + token);
                return;
            }
            String text = token.substring(0, sep).trim();
            int co2 = Integer.parseInt(token.substring(sep + 1).trim());
            choices.add(new Alternative(text, co2));
        }

        Card card = new Card(id, scenario, difficulty, choices);
        CardJsonIO.upsert(Path.of(file), card);
        System.out.println("Added/updated card id=" + id + " in " + Path.of(file).toAbsolutePath());
    }
}

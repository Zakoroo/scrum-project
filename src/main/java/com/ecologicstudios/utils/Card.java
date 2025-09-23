package com.ecologicstudios.utils;
 
import java.util.List;


public class Card {
    public int id;
    public String scenario;
    public String difficulty;
    public List<Choice> alternatives;
    
    public Card(int id, String scenario, String difficulty, List<Choice> alternatives) {
        this.id = id;
        this.scenario = scenario;
        this.difficulty = difficulty;
        this.alternatives = alternatives;
    }
}



/*
{
    "card":
[
{
    "id": 1,
    "scenario": "water source?",
    "difficulty": "Easy",
    "alternatives": [
        {"choice": "Bottled Water", "co2": 20},
        {"choice": "Tap Water", "co2": 10}
    ],
},
{
    "id": 2,
    "scenario": "Coffee Cup?",
    "difficulty": "Easy",
    "alternatives": [
        {"choice": "Takeaway Cup", "co2": 30},
        {"choice": "Thermos", "co2": 15}
    ]
    
}
]
}
*/







/*
 * [
  {
    "id": "water-source-01",
    "scenario": "Water source?",
    "difficulty": "easy",
    "metrics": ["co2"], 
    "alternatives": [
      {
        "id": "bottled",
        "text": "Bottled Water",
        "effects": { "co2": 20 },
        "nextCardId": null
      },
      {
        "id": "tap",
        "text": "Tap Water",
        "effects": { "co2": 10 },
        "nextCardId": null
      }
    ]
  },
  {
    "id": "coffee-cup-01",
    "scenario": "Coffee Cup?",
    "difficulty": "easy",
    "metrics": ["co2"],
    "alternatives": [
      { "id": "takeaway", "text": "Takeaway Cup", "effects": { "co2": 30 } },
      { "id": "thermos",   "text": "Thermos",       "effects": { "co2": 15 } }
    ]
  }
]

 */
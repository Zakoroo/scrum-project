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


public int getId(){
  return this.id;
}

public String getScenario(){
  return this.scenario;
}

public String getDifficulty(){
  return this.difficulty;
}

public List<Choice> getAlternatives(){
  return this.alternatives;
}

}

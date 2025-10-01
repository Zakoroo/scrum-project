package com.ecologicstudios.utils;
 
import java.util.List;


public class Card {
    public int id;
    public String scenario;
    public String difficulty;
    public List<Alternative> alternatives;

    public Card() {}
  
    public Card(int id, String scenario, String difficulty, List<Alternative> alternatives) {
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

public List<Alternative> getAlternatives(){
  return this.alternatives;
}

}
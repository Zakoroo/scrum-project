package com.ecologicstudios.client.controllers;

public abstract class BaseController {
    protected SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public abstract void initialize();
}
package com.ecologicstudios.client.controllers;

import com.ecologicstudios.client.controllers.SceneManager;


public abstract class BaseController {
    protected SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
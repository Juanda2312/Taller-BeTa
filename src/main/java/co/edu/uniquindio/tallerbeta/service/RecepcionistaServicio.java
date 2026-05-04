package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.repository.RecepcionistaRepositorio;

public class RecepcionistaServicio {
    private final RecepcionistaRepositorio recepcionistaRepositorio;

    public RecepcionistaServicio(){
        this.recepcionistaRepositorio = new RecepcionistaRepositorio();
    }
}

package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;

public class MecanicoServicio {
    private final MecanicoRepositorio mecanicoRepositorio;

    public MecanicoServicio(){
        this.mecanicoRepositorio = new MecanicoRepositorio();
    }
}

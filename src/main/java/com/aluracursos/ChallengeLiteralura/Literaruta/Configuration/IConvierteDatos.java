package com.aluracursos.ChallengeLiteralura.Literaruta.Configuration;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}

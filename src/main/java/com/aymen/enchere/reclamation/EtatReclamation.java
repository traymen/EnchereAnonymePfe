package com.aymen.enchere.reclamation;

public enum EtatReclamation {
    EN_COURS("En cours de traitement"),  // Valeur textuelle pour stockage
    TRAITE("Traité");                    // Valeur textuelle pour stockage

    private String description;

    EtatReclamation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;  // Retourner la valeur textuelle
    }
}

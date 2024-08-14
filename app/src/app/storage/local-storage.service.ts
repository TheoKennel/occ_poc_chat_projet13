import { Injectable } from '@angular/core';

/**
 * Service pour gérer le stockage local dans l'application.
 * Fournit des méthodes pour stocker, récupérer, modifier et supprimer des données dans le localStorage du navigateur.
 */
@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

  /**
   * Définit une valeur dans le localStorage.
   * @param key La clé sous laquelle stocker la valeur.
   * @param value La valeur à stocker.
   */
  setItem(key: string, value: string) : void {
    localStorage.setItem(key, value);
  }

  /**
   * Récupère une valeur du localStorage.
   * @param key La clé de la valeur à récupérer.
   * @returns La valeur stockée ou null si rien n'est trouvé.
   */
  getItem(key: string) : any   {
    return localStorage.getItem(key);
  }

  /**
   * Vide tous les éléments du localStorage.
   */
  clear(): void {
    localStorage.clear();
  }
}


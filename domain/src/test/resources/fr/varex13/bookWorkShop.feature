Feature: Tout les élèves - Réserver un atelier

  Un élève veut réserver un cours.

  Background:
    Given des élèves existent:
      | id                                   | firstName | lastName |
      | bd0251cb-1ca1-40e0-8828-e85f0767f9be | Céline    | DAJA     |
      | 95ca7b4d-b14e-4147-9fbd-1c5cfe2f7ea9 | Sylva     | POLANIA  |
      | 6e20d6f8-980d-43d1-aee1-09bca4129c4d | Manon     | CARTIN   |
      | d77b2939-857e-4d66-a205-13c090f223b8 | Delphine  | HERTAIN  |

    Given des ateliers existent:
      | id                                   | label     | duration |
      | c306ad32-3fd3-41a6-9b35-fcf9d6a0be9f | atelier 1 | 3        |
      | 2e7b432d-462b-4b2d-915f-23fe3cadb244 | atelier 2 | 2        |

  Scenario Outline: solde suffisant
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours
    When je tente de réserver l'atelier "<label_atelier>"
    Then la réservation de l'atelier est effective
    And le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | solde_après | label_atelier |
      | Céline        | 10          | 7           | atelier 1     |
      | Sylva         | 8           | 5           | atelier 1     |
      | Manon         | 3           | 1           | atelier 2     |
      | Delphine      | 2           | 0           | atelier 2     |
      | Delphine      | 4           | 2           | atelier 2     |

  Scenario Outline: solde insuffisant
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours
    When je tente de réserver l'atelier "<label_atelier>"
    Then la réservation de l'atelier n est pas effective
    And et une alerte pour insuffisance de solde se lève
    And le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | solde_après | label_atelier |
      | Sylva         | 0           | 0           | atelier 1     |
      | Céline        | 1           | 1           | atelier 1     |
      | Sylva         | 2           | 2           | atelier 1     |
      | Manon         | 0           | 0           | atelier 2     |
      | Delphine      | 1           | 1           | atelier 2     |


  Scenario Outline: Je ne suis pas authentifié
    Given je ne suis pas authentifié
    When je tente de réserver l'atelier "<label_atelier>"
    Then la réservation de l'atelier n est pas effective
    And et une alerte pour identification du client impossible se lève
    Examples:
      | label_atelier |
      | atelier 1     |
      | atelier 2     |

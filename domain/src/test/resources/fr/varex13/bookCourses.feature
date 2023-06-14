Feature: Tout les élèves - Réserver un cours

  Un élève veut réserver un cours.

  Background:
    Given des élèves existent:
      | id                                   | firstName | lastName |
      | bd0251cb-1ca1-40e0-8828-e85f0767f9be | Céline    | DAJA     |
      | 95ca7b4d-b14e-4147-9fbd-1c5cfe2f7ea9 | Sylva     | POLANIA  |
      | 6e20d6f8-980d-43d1-aee1-09bca4129c4d | Manon     | CARTIN   |
      | d77b2939-857e-4d66-a205-13c090f223b8 | Delphine  | HERTAIN  |

    Given des cours existent:
      | id                                   | label   |
      | c306ad32-3fd3-41a6-9b35-fcf9d6a0be9f | cours 1 |
      | 2e7b432d-462b-4b2d-915f-23fe3cadb244 | cours 2 |

  Scenario Outline: solde suffisant
    And je suis authentifié en tant que "<prénom_client>"
    And le solde de mon compte est de <solde_avant> heures de cours
    When je tente de réserver le cours "<label_cours>" pour <nombre_heures>
    Then la réservation est effective
    And le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | solde_après | label_cours | nombre_heures |
      | Céline        | 10          | 9           | cours 1     | 1             |
      | Sylva         | 8           | 4           | cours 1     | 4             |
      | Manon         | 3           | 1           | cours 2     | 2             |
      | Delphine      | 1           | 0           | cours 2     | 1             |
      | Delphine      | 4           | 1           | cours 2     | 3             |
#
  Scenario Outline: solde insuffisant
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde de mon compte est de <solde_avant> heures de cours
    When je tente de réserver le cours "<label_cours>" pour <nombre_heures>
    Then la réservation n'est pas effective
    And et une alerte pour insuffisance de solde se lève
    And le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | solde_après | label_cours | nombre_heures |
      | Céline        | 10          | 10          | cours 1     | 11            |
      | Sylva         | 8           | 8           | cours 1     | 9             |
      | Manon         | 3           | 3           | cours 2     | 4             |
      | Delphine      | 1           | 1           | cours 2     | 2             |
      | Delphine      | 4           | 4           | cours 2     | 5             |


  Scenario Outline: Je ne suis pas authentifié
    Given je ne suis pas authentifié
    When je tente de réserver le cours "<label_cours>" pour <nombre_heures>
    Then la réservation n'est pas effective
    And et une alerte pour identification du client impossible se lève
    Examples:
      | label_cours | nombre_heures |
      | cours 1     | 1             |
      | cours 1     | 2             |
      | cours 2     | 3             |

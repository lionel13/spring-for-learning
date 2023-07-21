Feature: Tout les élèves - Réserver un cours

  Un élève veut réserver un cours.

  Background:
    Given des élèves existent:
      | id                                   | firstName | lastName  |
      | bd0251cb-1ca1-40e0-8828-e85f0767f9be | Harmon    | Carpenter |
      | 95ca7b4d-b14e-4147-9fbd-1c5cfe2f7ea9 | Alba      | Neal      |
      | 6e20d6f8-980d-43d1-aee1-09bca4129c4d | Erna      | Higgins   |
      | d77b2939-857e-4d66-a205-13c090f223b8 | Jaclyn    | Pena      |

  Scenario Outline: Ajout de credits
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours V2
    When je tente d'ajouter <nb_heures> de crédit
    Then le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | nb_heures | solde_après |
      | Harmon        | 0           | 20        | 20          |
      | Alba          | 8           | 4         | 12          |
      | Erna          | 3           | 1         | 4           |
      | Jaclyn        | 1           | 1         | 2           |
      | Jaclyn        | 4           | 1         | 5           |

  Scenario Outline: Ajout de plusieurs credits
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours V2
    When je tente d'ajouter <nb_heures1> de crédit
    And je tente d'ajouter <nb_heures2> de crédit
    Then le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | nb_heures1 | nb_heures2 | solde_après |
      | Harmon        | 0           | 20         | 7          | 27          |
      | Alba          | 8           | 4          | 14         | 26          |
      | Erna          | 3           | 1          | 6          | 10          |
      | Jaclyn        | 1           | 1          | 9          | 11          |
      | Jaclyn        | 4           | 1          | 11         | 16          |

  Scenario Outline: Ajout de débit
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours V2
    When je tente d'ajouter <nb_heures> de débit
    Then le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | nb_heures | solde_après |
      | Harmon        | 20          | 11        | 9           |
      | Alba          | 8           | 4         | 4           |
      | Erna          | 3           | 1         | 2           |
      | Jaclyn        | 1           | 1         | 0           |
      | Jaclyn        | 4           | 3         | 1           |

  Scenario Outline: Ajout de plusieurs débit
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours V2
    When je tente d'ajouter <nb_heures1> de débit
    And je tente d'ajouter <nb_heures2> de débit
    Then le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | nb_heures1 | nb_heures2 | solde_après |
      | Harmon        | 20          | 2          | 5          | 13          |
      | Alba          | 8           | 4          | 1          | 3           |
      | Erna          | 3           | 1          | 1          | 1           |
      | Jaclyn        | 1           | 1          | 0          | 0           |
      | Jaclyn        | 4           | 1          | 2          | 1           |

  Scenario Outline: Ajout de débit et de crédits
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours V2
    When je tente d'ajouter <nb_heures1> de crédit
    And je tente d'ajouter <nb_heures2> de débit
    And je tente d'ajouter <nb_heures3> de débit
    And je tente d'ajouter <nb_heures4> de crédit
    Then le solde de mon compte est de <solde_après> heures de cours
    Examples:
      | prénom_client | solde_avant | nb_heures1 | nb_heures2 | nb_heures3 | nb_heures4 | solde_après |
      | Harmon        | 20          | 2          | 5          | 17         | 5          | 5           |
      | Alba          | 8           | 4          | 1          | 3          | 1          | 9           |
      | Erna          | 3           | 1          | 1          | 1          | 1          | 3           |
      | Jaclyn        | 1           | 1          | 0          | 0          | 0          | 2           |
      | Jaclyn        | 4           | 16         | 3          | 1          | 2          | 18          |

  Scenario Outline: solde insuffisant
    Given je suis authentifié en tant que "<prénom_client>"
    And le solde initial de mon compte est de <solde_avant> heures de cours V2
    When je tente d'ajouter <nb_heures> de débit
    Then une alerte pour insuffisance de solde se lève
    Examples:
      | prénom_client | solde_avant | nb_heures |
      | Harmon        | 20          | 22        |
      | Alba          | 8           | 9         |
      | Erna          | 3           | 4         |
      | Jaclyn        | 1           | 3         |
      | Jaclyn        | 4           | 5         |
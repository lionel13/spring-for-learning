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
      | c306ad32-3fd3-41a6-9b35-fcf9d6a0be9f | atelier 1 | 2        |
      | 2e7b432d-462b-4b2d-915f-23fe3cadb244 | atelier 2 | 3        |
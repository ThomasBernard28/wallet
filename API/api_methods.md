# API

## Classes
### Utilisateur
- numero registre national
- nom et prénom
- addresse mail
- langue
- liste des portefeuilles financiers

### Institution
- nom
- code SWIFT/BIC

### Portefeuille financier
- institution
- produits financiers

### Produit fianancier : compte courant
- IBAN
- solde
- titulaire ou cotitulaire
- historique des transactions
- frais

### Produit financier : compte jeune
- IBAN
- solde
- titulaire ou cotitulaire
- historique des transactions

### Produit financier : compte épargne
- IBAN
- solde
- titulaire ou cotitulaire
- historique des transactions
- rendement

### Produit financier : compte à terme
- IBAN
- solde
- titulaire ou cotitulaire
- hitorique des transactions
- rendement
- durée du placement

### Transaction bancaire
- uuid
- date
- montant
- status (en attente, refusé, validé)
- compte d'origine
- compte d'arrivée
- communication

## Méthodes nécessaires
- récupérer tous les wallet d'un user
- ajouter un wallet à un user
- supprimer un wallet d'un user

- récupérer les produits financiers d'un wallet

- récupérer tous les produits financiers d'un user
- ajouter un prdoduit financier à un user
- supprimer un produit financier d'un user

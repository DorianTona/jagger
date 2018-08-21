# Membres :
Dorian Tona

# Travail réalisé :
Je suis allé jusqu'au support de l'assignation et ai rajouté le support de fonctions comme demandé.

# Difficultés rencontrées : 
Nous avons beaucoup travaillé sur la creation d'une grammaire cohérente pour que le programme soit le plus simple à implementer par la suite.
    
Le principal soucis auquel nous avons été confrontés est la création de scope imbriqués, mais nous avons réussi à l'implémenter tant bien que mal, et d'une manière générale, le prettyprint des scopes laisse à désirer. Les scopes de scope fonctionnent donc, mais changer la valeur d'une variable déjà instanciée génère une boucle sans-fin.

# Précisions :
Concernant le support des scopes, le "ne plus ressembler à une calculatrice" prend la forme suivante : les seules syntaxes autorisées pour la première expression sont un scope ou un print. 

Dans le sujet, il est écrit que l'exemple suivant est invalide:
    
    let var foo := 1
        var bar := 1
        var foo := 1
    in 1 end

Lorsque ce cas se produit, une erreur est écrite dans la console et l'expression s'exécute en gardant la première valeur de la variable.

La grammaire des scopes est la suivante :
        
    let
        (declaration)*
    in
        statement(, statement)*
    end

Avec décaration pouvant être la déclaration d'une variable ou d'une fonction, et statement représentant toutes les opérations autorisées dans un in, soit print, assignation, appel de fonction et n'importe quelle opération/comparaison.

# Erreurs des tests
- print(print(1)) ne fonctionne pas et c'est bien normal puisque print est une fonction void.
- a>b>c ne fonctionne pas car les opérateurs de comparaison ne peuvent comparer que des expressions de niveau inférieurs dans la grammaire.

# Pour lancer le projet :
Avec l'entrée clavier :
make

Pour exécuter les différents test :
make check
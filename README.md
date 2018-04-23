# Membres :
Dorian Tona - Paul Giovannini

# Travail réalisé :
Nous sommes alles jusqu'au support des variables et des scopes.

# Difficultés rencontrées : 
Nous avons beaucoup travaillé sur la creation d'une grammaire cohérente pour que le programme soit le plus simple à implementer par la suite.
    
Le principal soucis auquel nous avons été confrontés est la création de scope imbriqués, mais nous avons réussi à l'implémenter tant bien que mal, et d'une manière générale, le prettyprint des scopes laisse à désirer. Les scopes de scope fonctionnent donc, mais changer la valeur d'une variable déjà instanciée génère une boucle sans-fin.

# Précisions :
Concernant le support des scopes, le "ne plus ressembler à une calculatrice" prend la forme suivante : 
- Toute opération doit être placée dans un bloc let.
- Toute opération n'étant pas une assignation (a, a+2) ou un print est interdit dans le bloc in.

Dans le sujet, il est écrit que l'exemple suivant est invalide:
    
    let var foo := 1
        var bar := 1
        var foo := 1
    in 1 end

Nous avons donc compris que c'est la double déclaration d'un même identifiant de variable 
qui doit etre différents. Ainsi, nous avons fait en sorte que si une variable est declarée puis redéfinie dans une autre instruction, cette dernière n'est pas prise en compte et la valeur de la variable reste celle définie à la première assignation.

La grammaire des scopes est la suivante :
        
        let 
            (assignation)*
        in
            (instruction)+
        end

# Erreurs des tests
- print(print(1)) ne fonctionne pas et c'est bien normal puisque print est une fonction void.
- a>b>c ne fonctionne pas car nous avons restreint les opérateurs de comparaison à l'utilisation d'expressions de niveau plus bas dans la grammaire.
- Nous n'avons pas eu le temps de terminer les assignations, à la place le programme exécute une boucle sans fin composée d'allers retours entre l'opération "demandée" et le visiteur de la classe variable.




# Pour lancer le projet :
Avec l'entrée clavier :
make

Pour exécuter les différents test :
make check
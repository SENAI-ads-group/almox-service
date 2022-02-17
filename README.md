# almox-backend

Módulo backend do projeto ALMOX

## Requisitos

1. JDK 11
1. Maven 3.6+

## Padrão de branchs e pull requests

### Nomes de branchs

{tipo-atividade}/{nome-atividade}/{descricao}

* feature
* fix
* hotfix

ex: **feature/ALMOX-1/modelagem-entidade-grupo**


### Pull requests

1. Ao iniciar uma tarefa, se recomenda mudar para a branch da sprint atual:
```
git checkout {branch-sprint}
```
2. Após isso garanta que a branch local esteja atualizada com a branch remota:
```
git pull origin {branch-sprint}
```
3. Crie a branch na qual resolverá a atividade seguindo os padrões de nomes de branchs
```
git checkout -b {branch-tarefa}
```
4. Ao finalizar a tarefa, faça o commit localmente
```
git add .
git commit -m "{mensagem-commit}"
```
5. Atualize sua branch de sprint local com a branch de sprint remota
```
git checkout {branch-sprint}
git pull origin/{branch-sprint}
```
6. Retorne a sua branch da tarefa e faça o rebase com a branch da sprint
```
git checkout {branch-tarefa}
git rebase {branch-sprint}
```
7. Faça o push para a branch remota
```
git push origin/{branch-tarefa}
```

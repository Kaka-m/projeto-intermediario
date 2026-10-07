Kauan Deivid Alves Morais 07/10/2026 
A justificativa do tema foi basicamente que eu já tinha o projeto base pronto, só precisaria de algumas adições com arquitetura MVVM e o Room Database, esse app está sujeito a alterações e correções de erros
Descrição do funcionamento do aplicativo

O aplicativo é um gerenciador de tarefas (lista de afazeres) para Android, desenvolvido em Kotlin com Jetpack Compose. Ele permite cadastrar, consultar, concluir e excluir tarefas, e mantém tudo salvo no próprio aparelho com um banco de dados local (Room), organizado na arquitetura MVVM.

Uso do aplicativo

Ao abrir o app, o usuário vê a tela Minhas Tarefas. No topo há um campo de texto e o botão Adicionar. Ao digitar o título e tocar no botão, a tarefa é cadastrada e o campo é limpo. Títulos em branco não são aceitos.

Abaixo do campo aparece a lista das tarefas salvas. Cada item mostra:

uma caixa de seleção, que marca a tarefa como concluída (o título fica riscado e com cor mais clara) ou a devolve para pendente;
o título da tarefa;
um ícone de lixeira, que exclui a tarefa.

Ao tocar sobre uma tarefa, o app abre a tela Detalhes da Tarefa. Ela exibe o título em destaque e o status ("Pendente" ou "Concluída") em um indicador colorido. O botão Voltar leva de volta à lista.

Armazenamento dos dados

As tarefas são gravadas em um banco SQLite gerenciado pelo Room. Por isso permanecem salvas mesmo depois de fechar o aplicativo ou reiniciar o aparelho. Cada tarefa guarda três informações: um identificador único, o título e se está concluída.

Arquitetura MVVM
Model: a classe Tarefa (entidade do banco), o TarefaDao (consultas e operações de inserir, atualizar, excluir e listar) e o AppDatabase (criação do banco).
ViewModel: o TarefaViewModel fornece a lista de tarefas para a tela e recebe as ações do usuário (adicionar, alternar status e excluir), executando-as em segundo plano.
View: as telas em Compose (ToDoListApp, TelaListaPrincipal e TelaDetalhes), que apenas exibem os dados e avisam o ViewModel quando o usuário interage.
Fluxo de funcionamento
O usuário realiza uma ação na tela (por exemplo, adicionar uma tarefa).
A tela repassa essa ação ao ViewModel.
O ViewModel pede ao DAO que grave a alteração no banco.
O Room avisa que os dados mudaram, e a lista é reenviada automaticamente ao ViewModel.
A tela observa a lista e se atualiza sozinha, sem precisar recarregar nada manualmente.
